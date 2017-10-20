package org.btsoft.jf.core.entity;

import java.util.List;

/**
 * @ClassName TreeVO
 * @Description 树结构VO
 * @author bchen
 * @created 2016-4-8 下午11:22:56
 */
public class TreeVO extends BaseVO{

	private static final long serialVersionUID = -5887267367477257362L;

	private Long id;

	private String text;

	private String state;

	private Long parentId;

	private String iconCls;

	private List<TreeVO> children;

	private boolean checked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<TreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
