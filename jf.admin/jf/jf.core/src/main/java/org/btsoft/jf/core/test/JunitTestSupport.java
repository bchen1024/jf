package org.btsoft.jf.core.test;

import org.btsoft.jf.core.application.IApplication;
import org.btsoft.jf.core.content.Application;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.content.RequestManagement;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ITestSupport
 * @Description 单元测试支持核心抽象类
 * @author bchen
 * @created 2015-2-4 下午10:05:00
 */
@RunWith(JFJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
@Transactional
public abstract class JunitTestSupport {

	@Before
	public void initJunit(){
		System.out.println("--------------------------junit test start---------------------------");
		RequestContext rc=new RequestContext();
		IApplication app=Application.getCurrent();
		rc.setApplication(app);
		rc.setHostIp("127.0.0.1");
		rc.setHostName("127.0.0.1");
		RequestManagement.setCurrent(rc);
	}
	
	@After
	public void destroyJunit(){
		RequestManagement.removeCurrent();
		System.out.println("--------------------------junit test end------------------------------");
	}
}
