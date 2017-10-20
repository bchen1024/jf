package org.btsoft.jf.core.cloud.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.cloud.ICloudService;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @ClassName CloudService
 * @Description 云服务Service
 * @author bchen
 * @created 2017年7月13日 下午10:07:34
 */
public class CloudService implements ICloudService {

	private final static Logger logger = Logger.getLogger(CloudService.class);

	private String beanName;

	public CloudService(String beanName) {
		this.beanName = beanName;
	}

	@Override
	public Object getBean() {
		return JF.getContext().getBean(beanName);
	}

	@Override
	public Object invoke(String methodName) throws ApplicationException {
		Object serviceObj = getBean();
		Class clazz = AopUtils.getTargetClass(serviceObj);
		try {
			Object target = getTarget(serviceObj);
			Method method = ReflectionUtils.findMethod(clazz, methodName, null);
			return ReflectionUtils.invokeMethod(method, target, null);
		} catch (Exception e) {
			logger.error(e);
			throw new ApplicationException("jf.exception.message.invokeException");
		}
	}

	@Override
	public Object invoke(String methodName, Object... args) throws ApplicationException {
		Object serviceObj = getBean();
		Class clazz = AopUtils.getTargetClass(serviceObj);
		try {
			Object target = getTarget(serviceObj);
			Class[] argsClass = new Class[args.length];

			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}
			Method method = ReflectionUtils.findMethod(clazz, methodName, argsClass);
			return ReflectionUtils.invokeMethod(method, target, args);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new ApplicationException("jf.exception.message.invokeException");
		}
	}

	private Object getTarget(Object proxy) throws Exception {
		if (!AopUtils.isAopProxy(proxy)) {
			return proxy;
		}
		if (AopUtils.isJdkDynamicProxy(proxy)) {
			Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
			h.setAccessible(true);
			AopProxy aopProxy = (AopProxy) h.get(proxy);

			Field advised = aopProxy.getClass().getDeclaredField("advised");
			advised.setAccessible(true);

			Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

			return target;
		} else {
			Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
			h.setAccessible(true);
			Object dynamicAdvisedInterceptor = h.get(proxy);

			Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
			advised.setAccessible(true);

			Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

			return target;
		}

	}
}
