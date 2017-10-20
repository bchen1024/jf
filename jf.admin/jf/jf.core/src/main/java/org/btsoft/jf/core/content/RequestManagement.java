package org.btsoft.jf.core.content;

/**
 * @ClassName RequestManagement
 * @Description 上下文管理器
 * @author bchen
 * @created 2013-12-14 下午8:05:40
 */
public class RequestManagement {

	private static ThreadLocal<RequestContext> tl=new ThreadLocal<RequestContext>();
	
	public static void setCurrent(RequestContext current){
		tl.set(current);
	}
	
	public static void removeCurrent(){
		tl.remove();
	}
	
	public static RequestContext getCurrent(){
		return tl.get();
	}
}
