package org.btsoft.jf.core.cloud;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.btsoft.jf.core.cloud.impl.CloudService;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @ClassName ClundFactory
 * @Description 云服务工厂
 * @author bchen
 * @created 2017年7月13日 下午10:20:44
 */
public final class CloudFactory {

	private static CloudFactory instance;

	private CloudFactory() {

	}

	public static CloudFactory getInstance() {
		if (instance == null) {
			instance = new CloudFactory();
		}
		return instance;
	}

	/**
	 * @Description 获取云服务Service
	 * @param beanName
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年7月18日 下午7:28:29
	 */
	public ICloudService getService(String beanName) throws ApplicationException {
		if (!JF.getContext().containsBean(beanName)) {
			throw new ApplicationException("jf.exception.message.beanNotDefined", new Object[] { beanName });
		}
		CloudService service = new CloudService(beanName);
		return service;
	}

	/**
	 * @Description 获取数据源
	 * @return
	 * @author bchen
	 * @created 2017年7月18日 下午7:24:53
	 */
	public DataSource getDataSource() {
		return (DataSource) JF.getContext().getBean("dataSource");
	}

	/**
	 * @Description 获取Sql会话工厂
	 * @return
	 * @author bchen
	 * @created 2017年7月18日 下午7:26:19
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return (SqlSessionFactory) JF.getContext().getBean("sqlSessionFactory");
	}

	/**
	 * @Description 获取事务管理器
	 * @return
	 * @author bchen
	 * @created 2017年7月18日 下午7:28:05
	 */
	public DataSourceTransactionManager getTransactionManager() {
		return (DataSourceTransactionManager) JF.getContext().getBean("transactionManager");
	}
}
