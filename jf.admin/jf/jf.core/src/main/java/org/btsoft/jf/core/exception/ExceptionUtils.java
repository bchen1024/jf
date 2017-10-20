package org.btsoft.jf.core.exception;

import java.text.MessageFormat;

import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.utils.StringUtils;

/**
 * @ClassName ExceptionUtils
 * @Description 异常工具类
 * @author bchen
 * @created 2016-5-29 下午11:03:19
 */
public class ExceptionUtils {

	public static String createExceptionMessage(String errorCode, Object[] args) {
		if (StringUtils.isEmpty(errorCode)) {
			// 如果没有指定异常编码，设置默认异常编码
			errorCode = "exception.message.systemException";
		}
		// 从缓存中获取异常配置信息
		String result = JFCache.getCahce("i18n", errorCode,errorCode);
		if (args != null && args.length > 0) {
			return MessageFormat.format(result, args);
		}
		return result;
	}
}
