/**
 * @FileName UploadServlet.java
 * @Package com.jf.web.support.upload.servlet
 * @Description 上传处理Servlet
 * @author bchen   
 * @version 1.0  
 * @created 2014-11-16 上午10:57:42 
 */
package org.btsoft.jf.support.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.entity.ResultVO;
import org.btsoft.jf.core.file.IUploadSupport;
import org.btsoft.jf.core.file.UploadSetting;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.JsonUtils;
import org.btsoft.jf.core.utils.PathUtils;
import org.btsoft.jf.core.utils.StringUtils;
import org.btsoft.jf.support.web.util.RequestUtil;

/**
 * @ClassName UploadServlet
 * @Description 上传处理Servlet
 * @author bchen
 * @created 2014-11-16 上午10:57:42
 */
@Named("/servlet/upload")
public class UploadServlet extends HttpServlet {
	
	public static final Logger logger=Logger.getLogger(UploadServlet.class);

	private static final long serialVersionUID = 2228064406113069940L;
	
	public static final String UPLOAD_FILE_SUFFIX=".jfile";
	
	public static final int SIZE_THRESHOLD_DEFAULT=2*1024;
	
	public static final int NO_MULTIPART=1;
	
	public static final int NO_UPLOAD_SUPPORT=2;
	
	public static final int UPLOAD_EXCEPTION=3;
	
	public static final int NO_UPLOAD_SETTING=4;
	
	public static final int NO_VALID_UPLOAD_FILES=5;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ResultVO result=new ResultVO();
		OutputStream out=resp.getOutputStream();
		
		//判断请求是否为文件上传类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置上传临时存储目录
			factory.setRepository(new File(PathUtils.getTempPath("upload")));
			//最大缓存  
	        factory.setSizeThreshold(SIZE_THRESHOLD_DEFAULT); 
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//获取请求参数
			Map<String, String> params =RequestUtil.getRequestParams(req);
			try {
				// 获取上传支持类
				String uploadSuppot=params.get("uploadSupport");
				if(StringUtils.isEmpty(uploadSuppot)){
					result.setStatus(NO_UPLOAD_SUPPORT);
					result.setMessage("No upload support");
					JsonUtils.writeValue(out, result);
					return;
				}
				IUploadSupport uploadSup = JF.getContext().getBean("IUploadSupport." +uploadSuppot ,IUploadSupport.class);
				//获取上传设置类
				UploadSetting uploadSetting=uploadSup.uploadSetting(params);
				List<FileItem> fileItems = upload.parseRequest(req);
				if(uploadSetting!=null){
					int totalFileNumber=uploadSetting.getTotalFileNumber();
					long singleFileSize=uploadSetting.getSingleFileSize();
					String uploadFolder=uploadSetting.getUploadFolder();
					//设置最大上传大小
					upload.setSizeMax(singleFileSize*totalFileNumber);
					logger.info(MessageFormat.format("[IUploadSupport={0},uploadFolder={1},totalFileNumber={2},singleFileSize={3}]", 
							new Object[]{uploadSuppot,uploadFolder,totalFileNumber,singleFileSize}));
					
					//筛选出有效的文件
					List<FileItem> fileList=new ArrayList<FileItem>();
					List<String> messageList=new ArrayList<String>();
					fileFilter(fileItems, fileList, messageList, singleFileSize);
					
					if(CollectionUtils.isNullOrEmpty(fileList)){
						result.setStatus(NO_VALID_UPLOAD_FILES);
						result.setMessage(messageList.toArray().toString());
						JsonUtils.writeValue(out, result);
						return;
					}
					
					if(fileList.size()>totalFileNumber){
						
						return;
					}
					
					List<Map<String,Object>> attachments=new ArrayList<Map<String,Object>>();
					for (FileItem fileItem : fileList) {
							String filePath=uploadFolder+File.separator +UUID.randomUUID()+UPLOAD_FILE_SUFFIX;
							File file = new File(filePath);
							fileItem.write(file);
							logger.info(MessageFormat.format("File {0} uploaded successfully,size:{1}", 
									new Object[]{fileItem.getName(),fileItem.getSize()}));
							Map<String,Object> fileMap=new HashMap<String, Object>();
							fileMap.put("fileName", fileItem.getName());
							fileMap.put("filePath", filePath);
							fileMap.put("fileSize", fileItem.getSize());
							if(!StringUtils.isEmpty(params.get("uploadType"))){
								fileMap.put("uploadType", params.get("uploadType"));
							}else{
								fileMap.put("uploadType", uploadSuppot);
							}
							uploadSup.uploadCallback(fileMap);
							attachments.add(fileMap);
					}
					logger.info(attachments);
					result.setResult(attachments);
					JsonUtils.writeValue(out, result);
				}else{
					result.setStatus(NO_UPLOAD_SETTING);
					result.setMessage("No upload settings");
					JsonUtils.writeValue(out, result);
				}
			} catch (Exception e) {
				logger.error(e);
				result.setStatus(UPLOAD_EXCEPTION);
				result.setMessage(e.getMessage());
				JsonUtils.writeValue(out, result);
			}
		}else{
			result.setStatus(NO_MULTIPART);
			result.setMessage("Request type is not file upload");
			JsonUtils.writeValue(out, result);
		}
	}
	
	/**
	 * @Description 筛选出有效的文件
	 * @param fileItems
	 * @param fileList
	 * @param messageList
	 * @param singleFileSize
	 * @author bchen
	 * @created 2017-6-8 下午9:59:59
	 */
	private void fileFilter(List<FileItem> fileItems,List<FileItem> fileList,List<String> messageList,long singleFileSize){
		for (FileItem fileItem : fileItems) {
			if (fileItem != null && !fileItem.isFormField() && !StringUtils.isEmpty(fileItem.getName())) {
				if(fileItem.getSize()<=singleFileSize){
					fileList.add(fileItem);
				}else{//文件大小超出限制
					messageList.add("The file size is out of bounds");
				}
			}else{//不是有效的文件
				messageList.add("Not a valid file");
			}
		}
	}

}
