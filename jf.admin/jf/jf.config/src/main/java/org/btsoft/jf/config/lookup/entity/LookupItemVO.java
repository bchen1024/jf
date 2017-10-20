package org.btsoft.jf.config.lookup.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName LookupItemVO
 * @Description Lookup项管理
 * @author bchen
 * @created 2017年8月11日 下午7:26:54
 */
public class LookupItemVO extends BaseVO {

	private static final long serialVersionUID = 5581461114690055875L;

	private Long itemId;

	private String codeNo;

	private String itemNo;

	private String itemNameCN;

	private String itemNameEN;

	private String itemName;

	private String itemParent;

	private int itemSort;

	private String itemDesc;
	
	private String itemAttr1;

	private String itemAttr2;

	private String itemAttr3;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemNameCN() {
		return itemNameCN;
	}

	public void setItemNameCN(String itemNameCN) {
		this.itemNameCN = itemNameCN;
	}

	public String getItemNameEN() {
		return itemNameEN;
	}

	public void setItemNameEN(String itemNameEN) {
		this.itemNameEN = itemNameEN;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemParent() {
		return itemParent;
	}

	public void setItemParent(String itemParent) {
		this.itemParent = itemParent;
	}

	public int getItemSort() {
		return itemSort;
	}

	public void setItemSort(int itemSort) {
		this.itemSort = itemSort;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemAttr1() {
		return itemAttr1;
	}

	public void setItemAttr1(String itemAttr1) {
		this.itemAttr1 = itemAttr1;
	}

	public String getItemAttr2() {
		return itemAttr2;
	}

	public void setItemAttr2(String itemAttr2) {
		this.itemAttr2 = itemAttr2;
	}

	public String getItemAttr3() {
		return itemAttr3;
	}

	public void setItemAttr3(String itemAttr3) {
		this.itemAttr3 = itemAttr3;
	}

}
