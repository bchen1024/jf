package org.btsoft.jf.config.excel.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName ExcelFieldVO
 * @Description excel字段VO
 * @author bchen
 * @created 2016-5-23 下午10:28:36
 */
public class ExcelFieldVO extends BaseVO {

	private static final long serialVersionUID = 7353708793749717752L;
	
	private Long excelFieldId;
	
	private Long excelSheetId;
	
	private String excelFieldName;
	
	private String excelDisplayName;
	
	private String excelDataType;
	
	private short excelFieldWidth;
	
	private int excelFieldIndex;

	public Long getExcelFieldId() {
		return excelFieldId;
	}

	public void setExcelFieldId(Long excelFieldId) {
		this.excelFieldId = excelFieldId;
	}

	public Long getExcelSheetId() {
		return excelSheetId;
	}

	public void setExcelSheetId(Long excelSheetId) {
		this.excelSheetId = excelSheetId;
	}

	public String getExcelFieldName() {
		return excelFieldName;
	}

	public void setExcelFieldName(String excelFieldName) {
		this.excelFieldName = excelFieldName;
	}

	public String getExcelDisplayName() {
		return excelDisplayName;
	}

	public void setExcelDisplayName(String excelDisplayName) {
		this.excelDisplayName = excelDisplayName;
	}

	public String getExcelDataType() {
		return excelDataType;
	}

	public void setExcelDataType(String excelDataType) {
		this.excelDataType = excelDataType;
	}

	public short getExcelFieldWidth() {
		return excelFieldWidth;
	}

	public void setExcelFieldWidth(short excelFieldWidth) {
		this.excelFieldWidth = excelFieldWidth;
	}

	public int getExcelFieldIndex() {
		return excelFieldIndex;
	}

	public void setExcelFieldIndex(int excelFieldIndex) {
		this.excelFieldIndex = excelFieldIndex;
	}
	
}
