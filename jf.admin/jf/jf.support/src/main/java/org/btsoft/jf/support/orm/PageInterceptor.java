package org.btsoft.jf.support.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName PageInterceptor
 * @Description mybatis分页拦截器
 * @author bchen
 * @created 2014-1-7 20:39:35
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }) })
public class PageInterceptor implements Interceptor {

	private final static Logger logger = Logger
			.getLogger(PageInterceptor.class);
	@Override
	public Object intercept(Invocation invocate) throws Throwable {
		// 获取拦截方法的参数数组
		Object[] args = invocate.getArgs();

		// 获取MappedStatement对象
		MappedStatement mappedStatement = (MappedStatement) args[0];
		// 获取方法名称
		String methodName = mappedStatement.getId();
		// 获取Dao接口参数对象
		Object params = args[1];

		//判断参数是否存在PageVO对象，如果不存在，则不进行下面分页拦截操作
		PageVO page = this.genPageVO(params, methodName);
		if (page == null) {
			return invocate.proceed();
		}

		Executor executor = (Executor) invocate.getTarget();
		// 分页查询总数查询
		int total = this.executeQueryCount(executor, mappedStatement, args,
				methodName);
		//如果总数不为0，继续往下执行分页查询
		List<Object> result = null;
		if (total != 0) {
			result = this.executeQueryList(executor, mappedStatement, args);
			page.setTotal(total);
		}
		if (result == null) {
			result = new ArrayList();
		}
		//封装分页结果对象
		PagedResult pageResult = new PagedResult();
		pageResult.setResult(result);
		pageResult.setTotal(total);
		pageResult.setPage(page);
		//pageResult.setPageVO(page);
		List resultList = new ArrayList();
		resultList.add(pageResult);
		return resultList;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {

	}

	/**
	 * @Description 判断是否存在PageVO对象
	 * @param params
	 * @param methodName
	 * @return
	 * @author bchen
	 * @created 2014-7-13 下午11:48:43
	 */
	private PageVO genPageVO(Object params, String methodName) {
		if (params == null) {
			return null;
		}
		if (methodName == null || methodName.endsWith("Count")) {
			return null;
		}
		if (params instanceof Map) {
			Map maps = (Map) params;
			for (Object param : maps.values()) {
				if (param instanceof PageVO) {
					return (PageVO) param;
				}
			}
		}
		return null;
	}

	private int executeQueryCount(Executor executor,
			MappedStatement mappedStatement, Object[] args, String methodName)
			throws SQLException {
		String queryCount = methodName + "Count";
		MappedStatement queryCountStatement = mappedStatement
				.getConfiguration().getMappedStatement(queryCount);
		List<Object> resultCount = this.executeQueryList(executor, queryCountStatement,
				args);
		int total=0;
		try {
			total = (int) resultCount.get(0);
		} catch (NumberFormatException e) {
			total=0;
		}
		
		return total;
	}

	/**
	 * @Description 执行查询语句
	 * @param executor
	 * @param mappedStatement
	 * @param params
	 * @param rowBounds
	 * @param resultHandler
	 * @return
	 * @author bchen
	 * @throws SQLException
	 * @created 2013-12-11 20:15:13
	 */
	private List<Object> executeQueryList(Executor executor,
			MappedStatement mappedStatement, Object[] args) throws SQLException {

		Object params = args[1];
		RowBounds rowBounds = (RowBounds) args[2];
		ResultHandler resultHandler = (ResultHandler) args[3];
		BoundSql boundSql = mappedStatement.getBoundSql(params);

		Configuration config = mappedStatement.getConfiguration();
		StatementHandler handler = config.newStatementHandler(executor,
				mappedStatement, params, rowBounds, resultHandler, boundSql);
		Statement st = null;
		try {
			st = genStatement(handler, executor, mappedStatement);
			List<Object> result = handler.query(st, resultHandler);
			return result;
		}  finally {
			closeConnection(null, st);
		}

	}

	private Statement genStatement(StatementHandler handler, Executor executor,
			MappedStatement mappedStatement) {
		Statement st = null;
		Connection con =null;
		try {
			con= ConnectionLogger.newInstance(executor
					.getTransaction().getConnection(), mappedStatement
					.getStatementLog());
			st = handler.prepare(con);
			handler.parameterize(st);
			return st;
		} catch (SQLException e) {
			logger.error("获取数据库Statement:"+e);
			closeConnection(con, st);
		}
		return st;
	}
	
	private void closeConnection(Connection con,Statement st){
		try {
			if(st!=null){
				st.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			logger.error("关闭数据库连接异常:"+e);
		}
	}

}
