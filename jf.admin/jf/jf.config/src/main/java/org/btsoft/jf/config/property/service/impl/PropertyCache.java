package org.btsoft.jf.config.property.service.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.btsoft.jf.config.property.dao.IPropertyDao;
import org.btsoft.jf.config.property.entity.PropertyVO;
import org.btsoft.jf.core.cache.ICache;

/**
 * @ClassName ExceptionCache
 * @Description 异常缓存处理类
 * @author bchen
 * @created 2015-2-6 23:59:13
 */
@Named("ICache.property")
public class PropertyCache implements ICache {

	public static Logger logger = Logger.getLogger(PropertyCache.class);

	public final static Map<String, PropertyVO> propertyCache = new Hashtable<String, PropertyVO>();

	@Inject
	private IPropertyDao dao;

	@Override
	public Object getObject(String param) {
		PropertyVO property = propertyCache.get(param);
		if (property == null) {
			property = new PropertyVO();
			property.setPropertyKey(param);
			property = dao.findProperty(property);
			if (property != null) {
				propertyCache.put(param, property);
			}
			return propertyCache.get(param);
		}
		return property;
	}

	@Override
	public String getValue(String param) {
		Object propertyObj = getObject(param);
		if (propertyObj == null) {
			return null;
		}
		PropertyVO property = (PropertyVO) propertyObj;
		return property.getPropertyValue();
	}

	@Override
	public int getSize() {
		return propertyCache.size();
	}

	@Override
	public void clear() {
		propertyCache.clear();
	}
}
