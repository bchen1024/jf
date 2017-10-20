package org.btsoft.jf.security.interceptor;

import javax.inject.Named;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * @ClassName SecurityInterceptor
 * @Description 权限验证
 * @author bchen
 * @created 2013-12-10 18:47:33
 */
public class SecurityInterceptor implements MethodInterceptor {

	private static Logger logger = Logger.getLogger(SecurityInterceptor.class);

	/*@Override
	public boolean execute(MethodInvocation invocate,Map<String,Object> params)throws ApplicationException {
		
		RequestContext rc=RequestContext.getCurrent();
		Method method =ClassUtils.getMostSpecificMethod(invocate.getMethod(), invocate.getThis().getClass());
		Class<?> clazz = method.getDeclaringClass();
		JResource resource = clazz.getAnnotation(JResource.class);
		JOperator operator = method.getAnnotation(JOperator.class);
		// 类上没有JResource注解或者方法没有JOperator注解跳过权限验证
		if (!clazz.isAnnotationPresent(JResource.class) || 
				!method.isAnnotationPresent(JOperator.class) ||
				SecurityEnums.ALLUser.equals(operator.security())) {
			return true;
		}
		
		if(rc==null){
			throw new ApplicationException("Sys-Security-00003");
		}
		//如果未设置权限点，设置当前用户可访问，并且当前用户不为空则通过
		IUserPrincipal user=rc.getUser();
		if("".equals(operator.code()) &&
				SecurityEnums.LoginUser.equals(operator.security()) && 
				user!=null){
			return true;
		}
			
		String resourceCode = resource.code();
		String methodCode = operator.code();
		StringBuffer sb = new StringBuffer();
		sb.append(resourceCode).append("$");
		sb.append(methodCode);
		logger.info("Permissions verification code is 【" + sb.toString() + "】");
		
		if(user!=null && !CollectionUtil.isNullOrEmpty(user.getPermissions()) && user.getPermissions().contains(sb.toString())){
			return true;
		}else{
			return false;
		}
	}*/

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		return null;
	}
}
