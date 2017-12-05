package org.btsoft.jf.config.attachment.entity;

import java.io.File;

import org.btsoft.jf.core.entity.BaseVO;
import org.btsoft.jf.core.utils.DESEncrypt;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @ClassName AttachmentVO
 * @Description 系统附件VO对象
 * @author bchen
 * @created 2017年6月29日 下午11:12:09
 */
@JsonIgnoreProperties(value = {"attachmentPath", "currentUserId", "currentLanguage", "createBy", "lastUpdateBy" })
public class AttachmentVO extends BaseVO {

	private static final long serialVersionUID = -124288466781316815L;

	/**
	 * 附件id
	 */
	private Long attachmentId;
	/**
	 * 附件名称
	 */
	private String attachmentName;

	/**
	 * 附件路径
	 */
	private String attachmentPath;
	/**
	 * 附件大小，单位B
	 */
	private Long attachmentSize;

	/**
	 * 附件类型
	 */
	private String attachmentType;

	/**
	 * 是否存在
	 */
	private String attachmentExists;
	
	/**
	 * 附件下载地址
	 */
	private String downloadUrl;

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public Long getAttachmentSize() {
		return attachmentSize;
	}

	public void setAttachmentSize(Long attachmentSize) {
		this.attachmentSize = attachmentSize;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getAttachmentExists() {
		if(this.attachmentPath==null) {
			return "N";
		}
		File f = new File(this.attachmentPath);
		if (f.exists()) {
			this.attachmentExists = "Y";
		} else {
			this.attachmentExists = "N";
		}
		f = null;
		return this.attachmentExists;
	}
	
	public String getDownloadUrl() {
		this.downloadUrl = AttachmentConstant.DOWNLOAD_URL + "&attachmentId="
				+ DESEncrypt.encrypt(this.attachmentId.toString());
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
