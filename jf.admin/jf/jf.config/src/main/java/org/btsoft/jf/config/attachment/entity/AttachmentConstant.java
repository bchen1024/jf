package org.btsoft.jf.config.attachment.entity;

/**
 * @ClassName CodeConstant
 * @Description 附件管理常量类
 * @author bchen
 * @created 2016-3-23 上午12:30:54
 */
public interface AttachmentConstant {

	public final static String RESOURCE_CODE = "config.attachment";

	public final static String RESOURCE_DESC_CN = "附件管理";
	
	public final static String RESOURCE_DESC_EN = "Attachment";
	
	public final static String LIST_DESC_CN = "附件列表";
	
	public final static String LIST_DESC_EN = "Attachment List";
	
	public final static String UPLOAD_DESC_CN = "上传附件";
	
	public final static String UPLOAD_DESC_EN = "Upload Attachment";
	
	public final static String SINGLE_DESC_CN = "附件详情";
	
	public final static String SINGLE_DESC_EN = "Attachment Detail";
	
	public final static String DOWNLOAD_DESC_CN = "下载附件";
	
	public final static String DOWNLOAD_DESC_EN = "Download Attachment";
	
	public final static String DOWNLOAD_URL="servlet/download?dlType=attachment";
	
	//附件id非数字异常
	public final static String ATTACHMENT_ID_EXCEPTION_="attachement.exception.attachmentIdError";
	
	//附件记录不存在异常
	public final static String ATTACHMENT_NOT_EXISTS_EXCEPTION_="attachement.exception..attachementNotExists";
}
