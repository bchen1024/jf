package org.btsoft.jf.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtils
 * @Description 字符串工具类，封装对字符串的一些常用操作
 * @author bchen
 * @created 2017年6月29日 下午10:48:01
 */
public class StringUtils {

	/**
	 * @Description 判断字符串是否为空
	 * @param str
	 * @return
	 * @author bchen
	 * @created 2017年6月30日 上午12:18:43
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * @Description 判断字符串是否为数字
	 * @param str
	 * @return
	 * @author bchen
	 * @created 2017-6-7 下午10:32:32
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
