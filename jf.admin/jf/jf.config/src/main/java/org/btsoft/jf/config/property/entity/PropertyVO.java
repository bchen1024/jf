package org.btsoft.jf.config.property.entity;

import org.btsoft.jf.config.property.service.impl.PropertyCache;
import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName PropertyVO
 * @Description 数据字典VO对象
 * @author bchen
 * @created 2013-11-19 11:56:59
 */
public class PropertyVO extends BaseVO {

	private static final long serialVersionUID = -2773543286133255797L;

	private Long propertyId;// 主键流水号
	private String propertyKey;// 字典key
	private String propertyValue;// 字典value
	private String propertyType;// 字典类型
	private String propertyTypeName;// 字典类型名称
	private String propertyAttr1;// 扩展属性1
	private String propertyAttr2;// 扩展属性2
	private String propertyAttr3;// 扩展属性3
	private String propertyDesc;// 字典描述
	// 是否发布，Y:发布，前天可以通过服务获取该配置，N:不发布，只能在后台获取该配置
	private String propertyPublish;
	private String cache;// 是否缓存

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyKey() {
		return propertyKey;
	}

	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	public String getPropertyAttr1() {
		return propertyAttr1;
	}

	public void setPropertyAttr1(String propertyAttr1) {
		this.propertyAttr1 = propertyAttr1;
	}

	public String getPropertyAttr2() {
		return propertyAttr2;
	}

	public void setPropertyAttr2(String propertyAttr2) {
		this.propertyAttr2 = propertyAttr2;
	}

	public String getPropertyAttr3() {
		return propertyAttr3;
	}

	public void setPropertyAttr3(String propertyAttr3) {
		this.propertyAttr3 = propertyAttr3;
	}

	public String getPropertyDesc() {
		return propertyDesc;
	}

	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}

	public String getPropertyPublish() {
		return propertyPublish;
	}

	public void setPropertyPublish(String propertyPublish) {
		this.propertyPublish = propertyPublish;
	}

	public String getCache() {
		if(this.propertyKey==null) {
			cache = "N";
		}else {
			PropertyVO p = PropertyCache.propertyCache.get(this.propertyKey);
			if (p != null) {
				if (this.getLastUpdationDate() != null && p.getLastUpdationDate() != null
						&& this.getLastUpdationDate().getTime() <= p.getLastUpdationDate().getTime()) {
					// 缓存未过期
					cache = "Y";
				} else {
					// 缓存已过期
					cache = "T";
				}

			} else {
				// 没有缓存
				cache = "N";
			}
		}
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	@Override
	public String toString() {
		return "PropertyVO [propertyId=" + propertyId + ", propertyKey=" + propertyKey + ", propertyValue="
				+ propertyValue + ", propertyType=" + propertyType + ", propertyAttr1=" + propertyAttr1
				+ ", propertyAttr2=" + propertyAttr2 + ", propertyAttr3=" + propertyAttr3 + ", propertyDesc="
				+ propertyDesc + ", propertyPublish=" + propertyPublish + ", cache=" + cache + "]";
	}
}
