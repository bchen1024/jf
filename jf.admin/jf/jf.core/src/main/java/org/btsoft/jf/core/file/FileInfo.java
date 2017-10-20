package org.btsoft.jf.core.file;

import java.io.Serializable;

/**
 * @ClassName FileInfo
 * @Description 下载文件信息
 * @author bchen
 * @created 2014-11-14 下午7:28:03
 */
public class FileInfo implements Serializable {
	
	private static final long serialVersionUID = -8826200386213703903L;
	
	private String fileName;
	private String filePath;
	private long fileSize;
	private String fileType;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
