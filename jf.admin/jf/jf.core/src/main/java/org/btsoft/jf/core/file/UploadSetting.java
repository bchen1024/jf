package org.btsoft.jf.core.file;

import java.io.Serializable;

import org.btsoft.jf.core.utils.PathUtils;

/**
 * @ClassName UploadSetting
 * @Description 上传设置类
 * @author bchen
 * @created 2015-2-3 下午11:48:38
 */
public class UploadSetting implements Serializable {

	private static final long serialVersionUID = -1298744533242976434L;

	private int totalFileNumber = 1;// 最大上传数量,默认为1个

	private long singleFileSize = 2 * 1024 * 1024;// 每个文件最大大小限制，默认为2M

	private String uploadFolder = PathUtils.getAppTypePath("attachment");// 上传的目录

	public int getTotalFileNumber() {
		return totalFileNumber;
	}

	public void setTotalFileNumber(int totalFileNumber) {
		this.totalFileNumber = totalFileNumber;
	}
	
	public long getSingleFileSize() {
		return singleFileSize;
	}

	public void setSingleFileSize(long singleFileSize) {
		this.singleFileSize = singleFileSize;
	}

	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}

}
