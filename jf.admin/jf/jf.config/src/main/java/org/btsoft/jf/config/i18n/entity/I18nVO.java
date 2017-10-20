package org.btsoft.jf.config.i18n.entity;

import org.btsoft.jf.config.i18n.service.impl.I18nCahce;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName I18nVO
 * @Description 国际化信息VO
 * @author bchen
 * @created 2013-11-19 11:37:38
 */
public class I18nVO extends BaseVO {

	private static final long serialVersionUID = 4790976502123562085L;

	private Integer i18nId;// 国际化流水号id
	private String i18nKey;// 国际化key
	private String i18nValueCN;// 中文内容
	private String i18nValueEN;// 英文内容
	private String i18nValue;//国际化内容
	private String i18nDesc;// 国际化描述
	private String cache;//是否缓存

	public Integer getI18nId() {
		return i18nId;
	}

	public void setI18nId(Integer i18nId) {
		this.i18nId = i18nId;
	}

	public String getI18nKey() {
		return i18nKey;
	}

	public void setI18nKey(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nValue() {
		if (CoreConstant.LANGUAGE_ZH_CN.equals(super.getCurrentLanguage())) {
			return this.i18nValue=this.i18nValueCN;
		}else{
			this.i18nValue=this.i18nValueEN;
		}
		return i18nValue;
	}

	public void setI18nValue(String i18nValue) {
		this.i18nValue = i18nValue;
	}

	public String getI18nDesc() {
		return i18nDesc;
	}

	public void setI18nDesc(String i18nDesc) {
		this.i18nDesc = i18nDesc;
	}

	public String getI18nValueCN() {
		return i18nValueCN;
	}

	public void setI18nValueCN(String i18nValueCN) {
		this.i18nValueCN = i18nValueCN;
	}

	public String getI18nValueEN() {
		return i18nValueEN;
	}

	public void setI18nValueEN(String i18nValueEN) {
		this.i18nValueEN = i18nValueEN;
	}
	public String getCache() {
		I18nVO p = I18nCahce.i18nCaches.get(this.i18nKey);
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
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}
}
