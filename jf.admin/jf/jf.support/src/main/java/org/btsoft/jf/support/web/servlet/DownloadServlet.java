/**
 * @FileName DownloadServlet.java
 * @Package com.jf.web.support.download.servlet
 * @Description 下载支持类
 * @author bchen   
 * @version 1.0  
 * @created 2014-11-13 下午10:52:55 
 */
package org.btsoft.jf.support.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.file.FileInfo;
import org.btsoft.jf.core.file.IDownloadSupport;
import org.btsoft.jf.support.web.util.RequestUtil;
import org.btsoft.jf.support.web.util.WebSupport;
import org.springframework.beans.BeansException;

/**
 * @ClassName DownloadServlet
 * @Description 下载支持类
 * @author bchen
 * @created 2014-11-13 下午10:52:55
 */
@Named("/servlet/download")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -4391033795032408125L;

	private static Logger logger = Logger.getLogger(DownloadServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding(WebSupport.UTF_8);
		resp.setCharacterEncoding(WebSupport.UTF_8);

		// 获取下载处理类
		String dlType = req.getParameter("dlType");
		if (dlType == null || dlType.trim().length() == 0) {
			PrintWriter out = resp.getWriter();
			resp.setContentType(WebSupport.HTML_CONTENT_TYPE);
			out.println("下载出错,没有dlType参数");
			out.close();
		}

		Map<String, String> params =RequestUtil.getRequestParams(req);

		String downloadBean = "IDownloadSupport." + dlType;
		try {
			IDownloadSupport download = JF.getContext().getBean(
					downloadBean, IDownloadSupport.class);
			if (download.checkSecurity(params)) {
				FileInfo fileInfo = download.processFile(params);
				if (fileInfo != null) {
					String contentType = getServletContext().getMimeType(
							fileInfo.getFilePath());
					if (contentType == null) {
						contentType = "application/octet-stream";
					}
					resp.setContentType(contentType);
					String fileName=new String(fileInfo.getFileName().getBytes(),"ISO-8859-1");
					resp.setHeader("Content-disposition",
							"attachment;filename=\"" + fileName
									+ "\"");
					InputStream is = null;
					OutputStream os = null;
					FileInputStream fis=null;
					try {
						fis=new FileInputStream(fileInfo.getFilePath());
						is = new BufferedInputStream(fis);
						os = new BufferedOutputStream(resp.getOutputStream());
						byte[] buffer = new byte[4 * 1024*1024];
						int read = 0;
						while ((read = is.read(buffer)) != -1) {
							os.write(buffer, 0, read);
						}
						os.flush();
						//os.write(baos.toByteArray());
					} catch (Exception e) {
						PrintWriter out = resp.getWriter();
						resp.setContentType(WebSupport.HTML_CONTENT_TYPE);
						out.println("下载出错:"+e.getMessage());
						out.close();
					} finally {
						if(fis!=null){
							fis.close();
						}
						if (is != null) {
							is.close();
						}
						if (os != null) {
							os.close();
						}
					}
				}
			} else {
				logger.error("下载出错,无权下载");
			}
		} catch (BeansException | ApplicationException e) {
			PrintWriter out = resp.getWriter();
			resp.setContentType(WebSupport.HTML_CONTENT_TYPE);
			out.println("下载出错:"+e.getMessage());
			out.close();
		}

	}
}
