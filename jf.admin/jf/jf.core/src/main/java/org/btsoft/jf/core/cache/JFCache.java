package org.btsoft.jf.core.cache;

import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.utils.StringUtils;

/**
 * @ClassName JFCache
 * @Description 缓存管理器
 * @author bchen
 * @created 2016-9-30 下午11:39:53
 */
public class JFCache {
	
	public final static String CACHE_NAME="ICache.";

	/**
	 * @Description 获取缓存对象
	 * @param cacheName
	 * @param cacheKey
	 * @return
	 * @author bchen
	 * @created 2016-9-30 下午11:46:26
	 */
	public static Object getCache(String cacheName,String cacheKey){
		ICache cache=JFCache.getCacheManager(cacheName);
		return cache.getObject(cacheKey);
	}
	
	/**
	 * @Description 获取缓存字符串
	 * @param cacheName
	 * @param cacheKey
	 * @return
	 * @author bchen
	 * @created 2016-9-30 下午11:46:41
	 */
	public static String getCahce(String cacheName,String cacheKey){
		ICache cache=JFCache.getCacheManager(cacheName);
		return cache.getValue(cacheKey);
	}
	
	/**
	 * @Description 获取缓存字符串，没有返回默认值
	 * @param cacheName
	 * @param cacheKey
	 * @param defaultValue
	 * @return
	 * @author bchen
	 * @created 2017-5-30 下午10:39:44
	 */
	public static String getCahce(String cacheName,String cacheKey,String defaultValue){
		ICache cache=JFCache.getCacheManager(cacheName);
		String value=cache.getValue(cacheKey);
		return StringUtils.isEmpty(value)?defaultValue:value;
	}
	
	/**
	 * @Description 获取缓存管理器
	 * @param cacheName
	 * @return
	 * @author bchen
	 * @created 2016-9-30 下午11:46:53
	 */
	public static ICache getCacheManager(String cacheName){
		ICache cache=JF.getContext().getBean(CACHE_NAME+cacheName, ICache.class);
		return cache;
	} 
	
	/**
	 * @Description 重新加载缓存数据
	 * @param cacheName
	 * @author bchen
	 * @created 2016-10-1 下午12:46:38
	 */
	public static  void clearCache(String cacheName){
		ICache cache=JFCache.getCacheManager(cacheName);
		cache.clear();
	}
}
