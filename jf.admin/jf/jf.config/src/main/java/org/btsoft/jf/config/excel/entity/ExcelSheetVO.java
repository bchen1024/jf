package org.btsoft.jf.config.excel.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName ExcelSheetVO
 * @Description excel sheetVO类
 * @author bchen
 * @created 2016-5-22 下午5:31:48
 */
public class ExcelSheetVO extends BaseVO {

	private static final long serialVersionUID = 4020069949099181987L;
	
	private Long excelSheetId;
	
	private Long excelTemplateId;
	
	private String sheetName;
	
	private int sheetIndex;
	
	private Integer titleRow;
	
	private Integer dataRow;

	public Long getExcelSheetId() {
		return excelSheetId;
	}

	public void setExcelSheetId(Long excelSheetId) {
		this.excelSheetId = excelSheetId;
	}

	public Long getExcelTemplateId() {
		return excelTemplateId;
	}

	public void setExcelTemplateId(Long excelTemplateId) {
		this.excelTemplateId = excelTemplateId;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public Integer getTitleRow() {
		return titleRow;
	}

	public void setTitleRow(Integer titleRow) {
		this.titleRow = titleRow;
	}

	public Integer getDataRow() {
		return dataRow;
	}

	public void setDataRow(Integer dataRow) {
		this.dataRow = dataRow;
	}

}
