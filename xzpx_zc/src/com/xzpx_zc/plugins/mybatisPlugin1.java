package com.xzpx_zc.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
/**
 * mybatis自定义的插件 拦截ExeCutor方法，实现对sql语句和参数列表的日志记录
 * mybatis通过自定义的插件可以对下列方法进行拦截（通过源码发现是通过jdk代理实现拦截的）
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 *  ParameterHandler (getParameterObject, setParameters)
 *  ResultSetHandler (handleResultSets, handleOutputParameters)
 *  StatementHandler (prepare, parameterize, batch, update, query)
* @projectName sptingDemo
* @ClassName: mybatisPlugin1 
* @Description: TODO
* @author zhangchao
* @date 2017年8月30日 下午3:18:22 
*
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }),

			@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class })

})
public class mybatisPlugin1 implements Interceptor{
	private static Logger logger = Logger.getLogger("sql");
	/**
	 * 执行拦截的主要方法
	 * Invocation 里面封装了target被代理的目标类， Method代理的目标方法， 代理的目标方法的参数
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		//Executor executor = (Executor) invocation.getTarget();
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = invocation.getArgs()[1];  //执行sql实际的参数值
		mappedStatement.getSqlSource().getBoundSql(parameter);
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String sql = "";
		if(boundSql != null && boundSql.getSql() != null && !"".equals(boundSql.getSql())) {
			sql = boundSql.getSql();
		}
		
		logger.debug("sql:" + sql +  System.getProperty("line.separator") + "parameters:" + parameter + System.getProperty("line.separator"));
		return invocation.proceed(); //相当于拦截器链（代理链）放行，去执行下一个拦截器（代理）
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);  //获取到代理对象（Executor）， 方法， 参数列表
	}

	public void setProperties(Properties arg0) {
		
	}

}
