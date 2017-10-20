package org.btsoft.jf.core.cache;

/**
 * @ClassName ICache
 * @Description 缓存接口
 * @author bchen
 * @created 2015-2-6 23:28:00
 */
public interface ICache {
	
	/**
	 * @Description 查询数据库数据缓存到缓存池中
	 * @param param
	 * @return
	 * @author bchen
	 * @created 2015-2-8 23:42:53
	 */
	public Object getObject(String param);

	/**
	 * @Description 获取缓存数据
	 * @param param
	 * @return
	 * @author bchen
	 * @created 2015-2-8 23:44:04
	 */
	public String getValue(String param);
	
	/**
	 * @Description 获取缓存数据数量
	 * @return
	 * @author bchen
	 * @created 2015-2-8 8:45:12
	 */
	public int getSize();
	
	/**
	 * @Description 清除缓存数据
	 * @author bchen
	 * @created 2015-2-8 8:45:55
	 */
	public void clear();
	
}
