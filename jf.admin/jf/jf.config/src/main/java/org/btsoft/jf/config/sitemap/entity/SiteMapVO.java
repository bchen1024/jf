package org.btsoft.jf.config.sitemap.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName SiteMapVO
 * @Description 菜单栏目VO
 * @author bchen
 * @created 2017年7月14日 下午9:49:20
 */
public class SiteMapVO extends BaseVO {

	private static final long serialVersionUID = -6339270924992020217L;
	
	/**
	 * 菜单栏目主键id
	 */
	private Long siteMapId;
	
	/**
	 * 菜单栏目code
	 */
	private String siteMapCode;
	
	/**
	 * 菜单栏目中文名称
	 */
	private String siteMapNameCN;
	
	/**
	 * 菜单栏目英文名称
	 */
	
	private String siteMapNameEN;
	
	/**
	 * 菜单栏目名称
	 */
	private String siteMapName;
	
	/**
	 * 菜单栏目url
	 */
	private String siteMapUrl;
	
	/**
	 * 菜单栏目图标
	 */
	private String siteMapIcon;
	
	/**
	 * 菜单栏目排序
	 */
	private int siteMapSort;
	
	/**
	 * 菜单栏目描述
	 */
	private String siteMapDesc;
	
	/**
	 * 显示方式
	 */
	private String showType;
	
	/**
	 * 权限点
	 */
	private String permissionCode;
	
	/**
	 * 父菜单id
	 */
	private Long parentId;
	
	/**
	 * 父菜单名称
	 */
	private String parentName;

	public Long getSiteMapId() {
		return siteMapId;
	}

	public void setSiteMapId(Long siteMapId) {
		this.siteMapId = siteMapId;
	}

	public String getSiteMapCode() {
		return siteMapCode;
	}

	public void setSiteMapCode(String siteMapCode) {
		this.siteMapCode = siteMapCode;
	}

	public String getSiteMapNameCN() {
		return siteMapNameCN;
	}

	public void setSiteMapNameCN(String siteMapNameCN) {
		this.siteMapNameCN = siteMapNameCN;
	}

	public String getSiteMapNameEN() {
		return siteMapNameEN;
	}

	public void setSiteMapNameEN(String siteMapNameEN) {
		this.siteMapNameEN = siteMapNameEN;
	}

	public String getSiteMapName() {
		return siteMapName;
	}

	public void setSiteMapName(String siteMapName) {
		this.siteMapName = siteMapName;
	}

	public String getSiteMapUrl() {
		return siteMapUrl;
	}

	public void setSiteMapUrl(String siteMapUrl) {
		this.siteMapUrl = siteMapUrl;
	}

	public String getSiteMapIcon() {
		return siteMapIcon;
	}

	public void setSiteMapIcon(String siteMapIcon) {
		this.siteMapIcon = siteMapIcon;
	}

	public int getSiteMapSort() {
		return siteMapSort;
	}

	public void setSiteMapSort(int siteMapSort) {
		this.siteMapSort = siteMapSort;
	}

	public String getSiteMapDesc() {
		return siteMapDesc;
	}

	public void setSiteMapDesc(String siteMapDesc) {
		this.siteMapDesc = siteMapDesc;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
