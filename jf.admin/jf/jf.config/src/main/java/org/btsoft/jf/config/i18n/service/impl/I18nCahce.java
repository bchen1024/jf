package org.btsoft.jf.config.i18n.service.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.i18n.dao.II18nDao;
import org.btsoft.jf.config.i18n.entity.I18nVO;
import org.btsoft.jf.core.cache.ICache;

/**
 * @ClassName I18nCahce
 * @Description 国际化缓存
 * @author bchen
 * @created 2017-6-10 上午11:43:13
 */
@Named("ICache.i18n")
public class I18nCahce implements ICache {

	public static Map<String, I18nVO> i18nCaches = new Hashtable<String, I18nVO>();

	@Inject
	private II18nDao i18nDao;

	@Override
	public Object getObject(String param) {
		I18nVO i18n = i18nCaches.get(param);
		if (i18n == null) {
			i18n = i18nDao.findI18nByKey(param);
			if (i18n == null) {
				i18n = new I18nVO();
				i18n.setI18nKey(param);
				i18n.setI18nValueCN(param);
				i18n.setI18nValueEN(param);
			}else{
				i18nCaches.put(param, i18n);
			}
		}
		return i18n;
	}

	@Override
	public String getValue(String param) {
		Object i18nObj =getObject(param);
		if(i18nObj==null){
			return null;
		}
		I18nVO i18n = (I18nVO) i18nObj;
		return i18n.getI18nValue();
	}

	@Override
	public int getSize() {
		return i18nCaches.size();
	}

	@Override
	public void clear() {
		i18nCaches.clear();
	}

}
