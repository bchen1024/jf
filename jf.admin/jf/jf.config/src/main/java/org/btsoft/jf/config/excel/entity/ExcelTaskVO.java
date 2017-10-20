package org.btsoft.jf.config.excel.entity;

import java.util.Date;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName ExcelTaskVO
 * @Description excel任务VO
 * @author bchen
 * @created 2016-5-21 下午10:08:23
 */
public class ExcelTaskVO extends BaseVO {

	private static final long serialVersionUID = -8734293561097482745L;
	
	private Long taskId;
	
	private String taskName;
	
	private String taskType;
	
	private String fileName;
	
	private String filePath;
	
	private Long fileSize;
	
	private Long fileRows;
	
	private String taskStatus;
	
	private String taskStatusName;
	
	private Date taskStartTime;
	
	private Date taskEndTime;
	
	private Long taskCost;
	
	private String taskError;
	
	private String dataOwner;
	
	private String taskOwnerEmail;
	
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getFileRows() {
		return fileRows;
	}

	public void setFileRows(Long fileRows) {
		this.fileRows = fileRows;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskStatusName() {
		return taskStatusName;
	}

	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public Long getTaskCost() {
		return taskCost;
	}

	public void setTaskCost(Long taskCost) {
		this.taskCost = taskCost;
	}

	public String getTaskError() {
		return taskError;
	}

	public void setTaskError(String taskError) {
		this.taskError = taskError;
	}

	public String getDataOwner() {
		return dataOwner;
	}

	public void setDataOwner(String dataOwner) {
		this.dataOwner = dataOwner;
	}

	public String getTaskOwnerEmail() {
		return taskOwnerEmail;
	}

	public void setTaskOwnerEmail(String taskOwnerEmail) {
		this.taskOwnerEmail = taskOwnerEmail;
	}
}
