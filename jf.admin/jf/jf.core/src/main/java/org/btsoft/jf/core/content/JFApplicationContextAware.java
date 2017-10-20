package org.btsoft.jf.core.content;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName JFApplicationContextAware
 * @Description Spring应用上下文注册
 * @author bchen
 * @created 2014-7-13 下午11:26:09
 */
public class JFApplicationContextAware implements ApplicationContextAware{

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		JFApplicationContext jfContext=new JFApplicationContext(context);
		JF.setContext(jfContext);
	}

}
