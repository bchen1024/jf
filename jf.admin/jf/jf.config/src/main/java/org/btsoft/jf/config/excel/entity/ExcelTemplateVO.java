package org.btsoft.jf.config.excel.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName ExcelTemplateVO
 * @Description Excel模板VO
 * @author bchen
 * @created 2016-5-22 下午5:21:02
 */
public class ExcelTemplateVO extends BaseVO {

	private static final long serialVersionUID = 6083724812476883369L;

	private Long excelTemplateId;
	
	private String excelTemplateCode;
	
	private String excelTemplateName;
	
	private String excelTemplateDesc;
	
	private String excelTemplateType;
	
	private String excelTemplatePath;
	
	private String excelFilePrefix;
	
	private String sendEmail;
	
	private String allowPartSuccess;
	
	private String compressFlag;
	
	private String asynFlag;

	public Long getExcelTemplateId() {
		return excelTemplateId;
	}

	public void setExcelTemplateId(Long excelTemplateId) {
		this.excelTemplateId = excelTemplateId;
	}

	public String getExcelTemplateCode() {
		return excelTemplateCode;
	}

	public void setExcelTemplateCode(String excelTemplateCode) {
		this.excelTemplateCode = excelTemplateCode;
	}

	public String getExcelTemplateName() {
		return excelTemplateName;
	}

	public void setExcelTemplateName(String excelTemplateName) {
		this.excelTemplateName = excelTemplateName;
	}

	public String getExcelTemplateDesc() {
		return excelTemplateDesc;
	}

	public void setExcelTemplateDesc(String excelTemplateDesc) {
		this.excelTemplateDesc = excelTemplateDesc;
	}

	public String getExcelTemplateType() {
		return excelTemplateType;
	}

	public void setExcelTemplateType(String excelTemplateType) {
		this.excelTemplateType = excelTemplateType;
	}

	public String getExcelTemplatePath() {
		return excelTemplatePath;
	}

	public void setExcelTemplatePath(String excelTemplatePath) {
		this.excelTemplatePath = excelTemplatePath;
	}

	public String getExcelFilePrefix() {
		return excelFilePrefix;
	}

	public void setExcelFilePrefix(String excelFilePrefix) {
		this.excelFilePrefix = excelFilePrefix;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getAllowPartSuccess() {
		return allowPartSuccess;
	}

	public void setAllowPartSuccess(String allowPartSuccess) {
		this.allowPartSuccess = allowPartSuccess;
	}

	public String getCompressFlag() {
		return compressFlag;
	}

	public void setCompressFlag(String compressFlag) {
		this.compressFlag = compressFlag;
	}

	public String getAsynFlag() {
		return asynFlag;
	}

	public void setAsynFlag(String asynFlag) {
		this.asynFlag = asynFlag;
	}
}
