/**
 * @FileName UserSessionListener.java
 * @Package com.chinasoft.cerp.listerner
 * @Description 用户session监听器
 * @author bchen   
 * @version 1.0  
 * @created 2013-4-4 23:53:11 
 */
package org.btsoft.jf.support.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.content.RequestManagement;

/**
 * @ClassName UserSessionListener
 * @Description 用户session监听器
 * @author bchen
 * @created 2013-4-4 23:53:11
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		 if ("userSession".equals(event.getName())) {  
			 RequestContext.setCurrent((RequestContext)event.getValue());
	     }  
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if ("userSession".equals(event.getName())) {  
			RequestManagement.removeCurrent();
	     } 
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		 if ("userSession".equals(event.getName())) {  
			 RequestContext.setCurrent((RequestContext)event.getValue());
	     }   
	}

}
