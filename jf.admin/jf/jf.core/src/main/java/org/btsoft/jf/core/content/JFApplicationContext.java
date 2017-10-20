package org.btsoft.jf.core.content;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.io.Resource;

/**
 * @ClassName JFApplicationContext
 * @Description 自定义的Spring的Context对象
 * @author bchen
 * @created 2013-12-2 20:46:15
 */
public class JFApplicationContext implements ApplicationContext {

	private ApplicationContext context;
	
	public JFApplicationContext(ApplicationContext context){
		this.context=context;
	}
	
	@Override
	public boolean containsBeanDefinition(String arg0) {
		return context.containsBeanDefinition(arg0);
	}

	@Override
	public <A extends Annotation> A findAnnotationOnBean(String arg0,
			Class<A> arg1) {
		return context.findAnnotationOnBean(arg0, arg1);
	}

	@Override
	public int getBeanDefinitionCount() {
		return context.getBeanDefinitionCount();
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return context.getBeanDefinitionNames();
	}

	@Override
	public String[] getBeanNamesForType(Class arg0) {
		return context.getBeanNamesForType(arg0);
	}

	@Override
	public String[] getBeanNamesForType(Class arg0, boolean arg1, boolean arg2) {
		return context.getBeanNamesForType(arg0, arg1, arg2);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> arg0)
			throws BeansException {
		return context.getBeansOfType(arg0);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> arg0, boolean arg1,
			boolean arg2) throws BeansException {
		return context.getBeansOfType(arg0, arg1, arg2);
	}

	@Override
	public Map<String, Object> getBeansWithAnnotation(
			Class<? extends Annotation> arg0) throws BeansException {
		return context.getBeansWithAnnotation(arg0);
	}

	@Override
	public boolean containsBean(String arg0) {
		return context.containsBean(arg0);
	}

	@Override
	public String[] getAliases(String arg0) {
		return context.getAliases(arg0);
	}

	@Override
	public Object getBean(String beanName)throws BeansException {
		return context.getBean(beanName);
	}

	@Override
	public <T> T getBean(Class<T> arg0) throws BeansException {
		return context.getBean(arg0);
	}

	@Override
	public <T> T getBean(String arg0, Class<T> arg1) throws BeansException {
		return context.getBean(arg0, arg1);
	}

	@Override
	public Object getBean(String arg0, Object... arg1) throws BeansException {
		return context.getBean(arg0, arg1);
	}

	@Override
	public Class<?> getType(String arg0) throws NoSuchBeanDefinitionException {
		return context.getType(arg0);
	}

	@Override
	public boolean isPrototype(String arg0)
			throws NoSuchBeanDefinitionException {
		return context.isPrototype(arg0);
	}

	@Override
	public boolean isSingleton(String arg0)
			throws NoSuchBeanDefinitionException {
		return context.isSingleton(arg0);
	}

	@Override
	public boolean isTypeMatch(String arg0, Class arg1)
			throws NoSuchBeanDefinitionException {
		return context.isTypeMatch(arg0, arg1);
	}

	@Override
	public boolean containsLocalBean(String arg0) {
		return context.containsLocalBean(arg0);
	}

	@Override
	public BeanFactory getParentBeanFactory() {
		return context.getParentBeanFactory();
	}

	@Override
	public String getMessage(MessageSourceResolvable arg0, Locale arg1)
			throws NoSuchMessageException {
		return context.getMessage(arg0, arg1);
	}

	@Override
	public String getMessage(String arg0, Object[] arg1, Locale arg2)
			throws NoSuchMessageException {
		return context.getMessage(arg0, arg1, arg2);
	}

	@Override
	public String getMessage(String arg0, Object[] arg1, String arg2,
			Locale arg3) {
		return context.getMessage(arg0, arg1, arg2, arg3);
	}

	@Override
	public void publishEvent(ApplicationEvent arg0) {
		context.publishEvent(arg0);
	}

	@Override
	public Resource[] getResources(String arg0) throws IOException {
		return context.getResources(arg0);
	}

	@Override
	public ClassLoader getClassLoader() {
		return context.getClassLoader();
	}

	@Override
	public Resource getResource(String arg0) {
		return context.getResource(arg0);
	}

	@Override
	public AutowireCapableBeanFactory getAutowireCapableBeanFactory()
			throws IllegalStateException {
		return context.getAutowireCapableBeanFactory();
	}

	@Override
	public String getDisplayName() {
		return context.getDisplayName();
	}

	@Override
	public String getId() {
		return context.getId();
	}

	@Override
	public ApplicationContext getParent() {
		return context.getParent();
	}

	@Override
	public long getStartupDate() {
		return context.getStartupDate();
	}

}
