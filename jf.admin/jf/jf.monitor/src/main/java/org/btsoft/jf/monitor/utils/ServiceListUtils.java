package org.btsoft.jf.monitor.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.enums.SecurityEnums;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @ClassName ServiceListUtils
 * @Description 服务工具类
 * @author bchen
 * @created 2017年7月19日 下午11:10:23
 */
public class ServiceListUtils {

	public static Map<String, String> beanAddress = new Hashtable<String, String>();

	public final static String SERVICE_TYPE_REST = "rest";
	
	public final static String SERVICE_TYPE_CACHE = "cache";
	
	public final static String SERVICE_TYPE_DOWNLOAD = "download";
	
	public final static String SERVICE_TYPE_UPLOAD = "upload";

	public final static String SERVICE_METHOD_GET = "GET";

	public final static String SERVICE_METHOD_POST = "POST";

	public final static String SERVICE_METHOD_PUT = "PUT";

	public final static String SERVICE_METHOD_DELETE = "DELETE";

	public final static String SERVICE_PERMISSION_LOGIN_USER = "LoginUser";

	public final static String SERVICE_PERMISSION_ALL_USER = "AllUser";

	public final static String SERVICE_REST_URL = "restUrl";

	public final static String SERVICE_REST_METHOD = "restMethod";

	public final static String SERVICE_REST_PRODUCES = "restProduces";

	/**
	 * @Description 获取BeanName
	 * @param clazz
	 * @return
	 * @author bchen
	 * @created 2017年7月19日 下午11:13:02
	 */
	public static String getBeanName(Class<?> clazz) {
		// 设置Bean名称
		Named named = clazz.getAnnotation(Named.class);
		String beanName = clazz.getSimpleName();
		if ("".equals(named.value())) {// 如果Named注解未设置值，取类名为Bean名称，首字母小写
			Character firstStr = beanName.charAt(0);
			beanName = beanName.replaceFirst(firstStr.toString(), firstStr.toString().toLowerCase());
		} else {// 取Named注解设置的值
			beanName = named.value();
		}
		return beanName;
	}

	/**
	 * @Description 获取方法参数
	 * @return
	 * @author bchen
	 * @created 2017年7月19日 下午11:15:30
	 */
	public static String getMethodArgs(Method method) {
		Class<?>[] paramsClass = method.getParameterTypes();
		StringBuffer paramsBuffer = new StringBuffer();
		for (int k = 0; k < paramsClass.length; k++) {
			paramsBuffer.append(paramsClass[k].getCanonicalName());
			if (k < paramsClass.length - 1) {
				paramsBuffer.append(",");
			}
		}
		return paramsBuffer.toString();
	}

	/**
	 * @Description 获取方法异常
	 * @param method
	 * @return
	 * @author bchen
	 * @created 2017年7月19日 下午11:18:10
	 */
	public static String getMethodException(Method method) {
		Class<?>[] exceptionsClass = method.getExceptionTypes();
		StringBuffer exceptionsBuffer = new StringBuffer();
		for (int k = 0; k < exceptionsClass.length; k++) {
			exceptionsBuffer.append(exceptionsClass[k].getCanonicalName());
			if (k < exceptionsClass.length - 1) {
				exceptionsBuffer.append(",");
			}
		}
		return exceptionsBuffer.toString();
	}

	/**
	 * @Description 获取方法权限点
	 * @param clazz
	 * @param method
	 * @return
	 * @author bchen
	 * @created 2017年7月19日 下午11:51:54
	 */
	public static String getMethodPermission(Class<?> clazz, Method method) {
		JResource jResource = clazz.getAnnotation(JResource.class);
		StringBuffer permissionBuffer = new StringBuffer();
		if (jResource != null) {
			JOperator jOperator = method.getAnnotation(JOperator.class);
			if (jOperator != null) {
				if (!StringUtils.isEmpty(jOperator.code())) {// 权限点控制
					permissionBuffer.append(jResource.code()).append("$").append(jOperator.code());
				} else if (SecurityEnums.LoginUser.equals(jOperator.security())) {// 登录用户可访问
					permissionBuffer.append(SERVICE_PERMISSION_LOGIN_USER);
				} else {// 所有用户可访问
					permissionBuffer.append(SERVICE_PERMISSION_ALL_USER);
				}
			}
		}
		return permissionBuffer.toString();
	}

	/**
	 * @Description 获取方法是否开启审计
	 * @param method
	 * @return
	 * @author bchen
	 * @created 2017年7月20日 下午8:22:57
	 */
	public static String getMethodAudit(Method method) {
		JOperator jOperator = method.getAnnotation(JOperator.class);
		if (jOperator != null && jOperator.isAudit()) {
			return "Y";
		}
		return "N";
	}

	/**
	 * @Description 获取方法描述
	 * @param method
	 * @return
	 * @author bchen
	 * @created 2017年7月20日 下午8:27:03
	 */
	public static String getMethodDesc(Method method) {
		JOperator jOperator = method.getAnnotation(JOperator.class);
		if (jOperator != null) {
			return jOperator.descCN();
		}
		return "";
	}

	/**
	 * @Description 获取rest信息
	 * @param clazz
	 * @param method
	 * @param beanName
	 * @return
	 * @author bchen
	 * @created 2017年7月20日 下午11:20:59
	 */
	public static Map<String, String> getRestInfo(Class<?> clazz, Method method, String beanName) {
		Map<String, String> result = new HashMap<String, String>();
		// 获取该bean所有的接口
		Class<?>[] interfaces = clazz.getInterfaces();
		if (!CollectionUtils.isNullOrEmpty(interfaces)) {
			for (int i = 0; i < interfaces.length; i++) {
				Class<?> in = interfaces[i];
				Path pa = in.getAnnotation(Path.class);
				Produces p = in.getAnnotation(Produces.class);
				StringBuffer restUrlStr = new StringBuffer();

				Method[] ms = in.getDeclaredMethods();
				if (ms != null) {
					for (int k = 0; k < ms.length; k++) {
						Method m = ms[k];
						if (m.getName().equals(method.getName())) {
							Path path = m.getAnnotation(Path.class);

							// 获取服务方法的path路径
							if (path != null) {
								restUrlStr.append(beanAddress.get(beanName));
								if (pa != null) {
									restUrlStr.append(pa.value());
								}
								restUrlStr.append(path.value());
								result.put(SERVICE_REST_URL, restUrlStr.toString());
							}

							// 获取Produces配置
							if (m.isAnnotationPresent(Produces.class)) {
								p = m.getAnnotation(Produces.class);
							}
							if (p != null) {
								result.put(SERVICE_REST_PRODUCES, CollectionUtils.join(p.value(), ";"));
							}

							// 获取httpMethod
							if (m.isAnnotationPresent(GET.class)) {
								result.put(SERVICE_REST_METHOD, SERVICE_METHOD_GET);
							} else if (m.isAnnotationPresent(POST.class)) {
								result.put(SERVICE_REST_METHOD, SERVICE_METHOD_POST);
							} else if (m.isAnnotationPresent(PUT.class)) {
								result.put(SERVICE_REST_METHOD, SERVICE_METHOD_PUT);
							} else if (m.isAnnotationPresent(DELETE.class)) {
								result.put(SERVICE_REST_METHOD, SERVICE_METHOD_DELETE);
							}
							break;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * @Description 扫描所有的cxf配置文件,找出bean对应的address
	 * @author bchen
	 * @created 2017-5-22 下午10:40:16
	 */
	public static void scanBeanAddress() throws ApplicationException {
		if (beanAddress.isEmpty()) {
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			try {
				Resource[] resources = resourcePatternResolver.getResources("classpath*:config/*.services.xml");
				for (Resource resource : resources) {
					SAXReader reader = new SAXReader();
					Document doc = reader.read(resource.getFile());
					Element root = doc.getRootElement();
					List<Element> servers = root.elements("server");
					for (Element service : servers) {
						List<Element> serviceBeans = service.elements("serviceBeans");
						for (Element beans : serviceBeans) {
							List<Element> refs = beans.elements("ref");
							for (Element ref : refs) {
								beanAddress.put(ref.attributeValue("bean"), service.attributeValue("address"));
							}
						}
					}
				}
			} catch (IOException | DocumentException e) {
				throw new ApplicationException("jf.exception.tools.scanAddress");
			}
		}
	}
}
