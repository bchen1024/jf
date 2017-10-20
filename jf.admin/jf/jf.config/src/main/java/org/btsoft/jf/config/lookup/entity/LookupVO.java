package org.btsoft.jf.config.lookup.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName LookupVO
 * @Description Lookup管理VO
 * @author bchen
 * @created 2017年8月11日 下午7:25:56
 */
public class LookupVO extends BaseVO {

	private static final long serialVersionUID = -2821141998665175853L;

	private Long codeId;

	private String codeNo;

	private String codeName;

	private String codeType;

	private String codeTypeName;

	private String codeParent;

	private String codeDesc;
	
	private String codePublish;

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}

	public String getCodeParent() {
		return codeParent;
	}

	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodePublish() {
		return codePublish;
	}

	public void setCodePublish(String codePublish) {
		this.codePublish = codePublish;
	}
}
