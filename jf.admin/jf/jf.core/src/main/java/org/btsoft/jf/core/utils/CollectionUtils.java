package org.btsoft.jf.core.utils;

import java.util.Collection;

/**
 * @ClassName CollectionUtil
 * @Description 集合框架工具类
 * @author bchen
 * @created 2017年6月30日 下午9:37:40
 */
public class CollectionUtils {

	/**
	 * @Description 判断集合是否为空
	 * @param list
	 * @return
	 * @author bchen
	 * @created 2014-4-26 19:02:32
	 */
	public static boolean isNullOrEmpty(Collection<?> list) {
		return (list == null || list.isEmpty());
	}

	/**
	 * @Description 判断数组对象是否为空
	 * @param arrays
	 * @return
	 * @author bchen
	 * @created 2017年7月20日 下午7:46:45
	 */
	public static boolean isNullOrEmpty(Object[] arrays) {
		return (arrays == null || arrays.length == 0);
	}
	
	/**
	 * @Description 将字符串数组用分隔符拼接
	 * @param arrays
	 * @param split
	 * @return
	 * @author bchen
	 * @created 2017年7月20日 下午10:37:13
	 */
	public static String join(String[] arrays,String split){
		// 如果数组为空
		if (arrays == null || arrays.length == 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			if(split==null){
				split=",";
			}
			for (int i = 0; i < arrays.length; i++) {
				sb.append(arrays[i]);
				// 去掉最后的分隔符
				if (i < arrays.length - 1) {
					sb.append(split);
				}
			}
			return sb.toString();
		}
	}
}
