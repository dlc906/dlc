package com.dlc.dlc.system.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


public class SqlLogInterceptor implements InnerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SqlLogInterceptor.class);

    @Override
    public boolean willDoQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        String sqlId = ms.getId();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        long start = System.currentTimeMillis();
        //Object result = resultHandler;
        long end = System.currentTimeMillis();
        long time = end - start;
        String params = getParams(boundSql);
        logger.info("SQL: [{}] - 参数: [{}] - 执行时间: [{}ms]", sql, params, time);
        return true;
    }

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        String params = getParams(boundSql);
        logger.info("SQL: [{}] - 参数: [{}] - 执行时间: [{}ms]", sql, params, time);
        return result;
    }
    private String getParams(BoundSql boundSql) {
        String sql = boundSql.getSql();
        String param = null;
        if (sql.contains("WHERE")){
            param = sql.substring(sql.indexOf("WHERE")+7);
        }
        else {
            param = boundSql.getParameterObject().toString();
        }

        return param;
    }


}
