package org.btsoft.jf.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName JfResource
 * @Description 类上权限控制注解
 * @author bchen
 * @created 2013-11-6 21:19:16
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface JResource {

	public String code();// 资源编码

	public String descCN();// 资源中文描述

	public String descEN();// 资源英文描述
}
