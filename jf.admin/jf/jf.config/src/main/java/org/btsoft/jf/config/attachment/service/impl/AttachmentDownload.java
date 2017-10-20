/**
 * @FileName AttachmentDownload.java
 * @Package com.jf.web.support.download.servlet
 * @Description 附件下载支持类
 * @author bchen   
 * @version 1.0  
 * @created 2014-11-14 下午8:23:42 
 */
package org.btsoft.jf.config.attachment.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.btsoft.jf.config.attachment.entity.AttachmentConstant;
import org.btsoft.jf.config.attachment.entity.AttachmentVO;
import org.btsoft.jf.config.attachment.service.IAttachmentService;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.file.FileInfo;
import org.btsoft.jf.core.file.IDownloadSupport;
import org.btsoft.jf.core.utils.DESEncrypt;
import org.btsoft.jf.core.utils.StringUtils;

/**
 * @ClassName AttachmentDownload
 * @Description 附件下载支持类
 * @author bchen
 * @created 2014-11-14 下午8:23:42
 */
@Named("IDownloadSupport.attachment")
public class AttachmentDownload implements IDownloadSupport {

	private static Logger logger=Logger.getLogger(AttachmentDownload.class);
	
	@Inject
	private IAttachmentService attachmentService;
	
	@Override
	public FileInfo processFile(Map<String, String> params) throws ApplicationException{
		//附件id转型
		String attachmentId=params.get("attachmentId");
		Long attachId;
		if(StringUtils.isNumeric(attachmentId)){//传入的为附件id
			attachId=Long.parseLong(attachmentId);
		}else{//传入的为加密后的附件id，需先解密
			try {
				attachId=Long.parseLong(DESEncrypt.dncrypt(attachmentId));
			} catch (NumberFormatException e) {
				logger.error(e);
				throw new ApplicationException(AttachmentConstant.ATTACHMENT_ID_EXCEPTION_);
			}
		}
		//通过附件id查找附件信息
		AttachmentVO attachment=new AttachmentVO();
		attachment.setAttachmentId(attachId);
		attachment.setAttachmentType("attachment");
		AttachmentVO attach=attachmentService.findDownloadAttachment(attachment);
		if(attach==null){
			throw new ApplicationException(AttachmentConstant.ATTACHMENT_NOT_EXISTS_EXCEPTION_);
		}
		
		//设置下载名称和路径
		FileInfo fileInfo=new FileInfo();
		fileInfo.setFileName(attach.getAttachmentName());
		fileInfo.setFilePath(attach.getAttachmentPath());
		return fileInfo;
	}

	@Override
	public boolean checkSecurity(Map<String, String> params) {
		return true;
	}

}
