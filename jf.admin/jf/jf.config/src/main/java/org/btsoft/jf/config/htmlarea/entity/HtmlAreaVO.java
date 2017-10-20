package org.btsoft.jf.config.htmlarea.entity;

import org.btsoft.jf.config.htmlarea.service.impl.HtmlAreaCache;
import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName HtmlAreaVO
 * @Description 富文本VO
 * @author bchen
 * @created 2013-11-19 11:34:25
 */
public class HtmlAreaVO extends BaseVO {

	private static final long serialVersionUID = -4150604992846759992L;

	private Long htmlAreaId;// 流水号id
	private String htmlAreaName;// 富文本名称，唯一
	private String htmlAreaType;// 富文本类型
	private String htmlAreaTypeName;
	private String htmlAreaTitle;// 富文本标题
	private String htmlAreaMappingUrl;// 资源映射url
	private String htmlAreaContent;// 富文本内容
	private String htmlAreaDesc;// 富文本说明
	private String cache;//是否缓存

	public Long getHtmlAreaId() {
		return htmlAreaId;
	}

	public void setHtmlAreaId(Long htmlAreaId) {
		this.htmlAreaId = htmlAreaId;
	}

	public String getHtmlAreaName() {
		return htmlAreaName;
	}

	public void setHtmlAreaName(String htmlAreaName) {
		this.htmlAreaName = htmlAreaName;
	}

	public String getHtmlAreaType() {
		return htmlAreaType;
	}

	public void setHtmlAreaType(String htmlAreaType) {
		this.htmlAreaType = htmlAreaType;
	}

	public String getHtmlAreaTypeName() {
		return htmlAreaTypeName;
	}

	public void setHtmlAreaTypeName(String htmlAreaTypeName) {
		this.htmlAreaTypeName = htmlAreaTypeName;
	}

	public String getHtmlAreaTitle() {
		return htmlAreaTitle;
	}

	public void setHtmlAreaTitle(String htmlAreaTitle) {
		this.htmlAreaTitle = htmlAreaTitle;
	}

	public String getHtmlAreaMappingUrl() {
		return htmlAreaMappingUrl;
	}

	public void setHtmlAreaMappingUrl(String htmlAreaMappingUrl) {
		this.htmlAreaMappingUrl = htmlAreaMappingUrl;
	}

	public String getHtmlAreaContent() {
		return htmlAreaContent;
	}

	public void setHtmlAreaContent(String htmlAreaContent) {
		this.htmlAreaContent = htmlAreaContent;
	}

	public String getHtmlAreaDesc() {
		return htmlAreaDesc;
	}

	public void setHtmlAreaDesc(String htmlAreaDesc) {
		this.htmlAreaDesc = htmlAreaDesc;
	}

	public String getCache() {
		HtmlAreaVO h=HtmlAreaCache.htmlareaCache.get(this.htmlAreaName);
		if(h!=null){
			if (this.getLastUpdationDate() != null && h.getLastUpdationDate() != null
					&& this.getLastUpdationDate().getTime() <= h.getLastUpdationDate().getTime()) {
				// 缓存未过期
				cache = "Y";
			} else {
				// 缓存已过期
				cache = "T";
			}
		}else{
			cache="N";
		}
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}
}
