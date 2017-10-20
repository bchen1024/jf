/**
 * @FileName AttachmentUpload.java
 * @Package com.jf.system.attachment.service.impl
 * @Description 附件上传支持类
 * @author bchen   
 * @version 1.0  
 * @created 2017-6-2 下午9:39:22 
 */
package org.btsoft.jf.config.attachment.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.attachment.entity.AttachmentVO;
import org.btsoft.jf.config.attachment.service.IAttachmentService;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.file.IUploadSupport;
import org.btsoft.jf.core.file.UploadSetting;
import org.btsoft.jf.core.utils.PathUtils;
import org.btsoft.jf.core.utils.StringUtils;

/**
 * @ClassName AttachmentUpload
 * @Description 附件上传支持类
 * @author bchen
 * @created 2017-6-2 下午9:39:22
 */
@Named("IUploadSupport.attachment")
public class AttachmentUpload implements IUploadSupport{
	
	@Inject
	private IAttachmentService attachmentService;

	@Override
	public UploadSetting uploadSetting(Map<String, String> params)  throws ApplicationException{
		UploadSetting uploadSetting=new UploadSetting();
		if(!StringUtils.isEmpty(params.get("uploadType"))){
			uploadSetting.setUploadFolder(PathUtils.getAppTypePath(params.get("uploadType")));
		}
		return uploadSetting;
	}

	@Override
	public boolean checkSecurity(Map<String, String> params) throws ApplicationException {
		return true;
	}

	@Override
	public Map<String, Object> uploadCallback(Map<String, Object> params) throws ApplicationException {
		AttachmentVO attachment=new AttachmentVO();
		attachment.setAttachmentName(params.get("fileName").toString());
		attachment.setAttachmentPath(params.get("filePath").toString());
		attachment.setAttachmentSize(Long.parseLong(params.get("fileSize").toString()));
		attachment.setAttachmentType(params.get("uploadType").toString());
		attachmentService.createAttachment(attachment);
		params.put("fileId", attachment.getAttachmentId());
		return params;
	}

}
