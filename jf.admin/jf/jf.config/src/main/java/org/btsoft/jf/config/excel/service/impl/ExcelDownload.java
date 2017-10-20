package org.btsoft.jf.config.excel.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.attachment.entity.AttachmentConstant;
import org.btsoft.jf.config.excel.dao.IExcelTaskDao;
import org.btsoft.jf.config.excel.entity.ExcelTaskVO;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.file.FileInfo;
import org.btsoft.jf.core.file.IDownloadSupport;
import org.btsoft.jf.core.utils.DESEncrypt;
import org.btsoft.jf.core.utils.StringUtils;

/**
 * @ClassName ExcelDownload
 * @Description Excel下载支持类
 * @author bchen
 * @created 2017年9月10日 下午11:55:45
 */
@Named("IDownloadSupport.excel")
public class ExcelDownload implements IDownloadSupport {
	
	@Inject
	private IExcelTaskDao dao;

	@Override
	public FileInfo processFile(Map<String, String> params) throws ApplicationException {
		//附件id转型
		String taskIdStr=params.get("taskId");
		Long taskId;
		if(StringUtils.isNumeric(taskIdStr)){//传入的为附件id
			taskId=Long.parseLong(taskIdStr);
		}else{//传入的为加密后的附件id，需先解密
			try {
				taskId=Long.parseLong(DESEncrypt.dncrypt(taskIdStr));
			} catch (NumberFormatException e) {
				throw new ApplicationException(AttachmentConstant.ATTACHMENT_ID_EXCEPTION_);
			}
		}
		//通过附件id查找附件信息
		ExcelTaskVO excelTask=new ExcelTaskVO();
		excelTask.setTaskId(taskId);
		excelTask=dao.findExcelTask(excelTask);
		if(excelTask==null){
			throw new ApplicationException(AttachmentConstant.ATTACHMENT_NOT_EXISTS_EXCEPTION_);
		}
		
		//设置下载名称和路径
		FileInfo fileInfo=new FileInfo();
		fileInfo.setFileName(excelTask.getFileName());
		fileInfo.setFilePath(excelTask.getFilePath());
		return fileInfo;
	}

	@Override
	public boolean checkSecurity(Map<String, String> params) {
		return true;
	}

}
