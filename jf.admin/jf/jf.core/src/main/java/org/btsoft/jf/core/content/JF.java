package org.btsoft.jf.core.content;

/**
 * @ClassName JavaFramework
 * @Description JF框架全局的对象
 * @author bchen
 * @created 2013-12-2 22:14:49
 */
public final class JF {

	private static JFApplicationContext context;

	public static JFApplicationContext getContext() {
		return context;
	}

	public static void setContext(JFApplicationContext applicationContext) {
		context = applicationContext;
	}
}
