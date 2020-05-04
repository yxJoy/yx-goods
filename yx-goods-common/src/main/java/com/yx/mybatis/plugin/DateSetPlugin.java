package com.yx.mybatis.plugin;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class DateSetPlugin implements Interceptor {

    private static final String GMT_CREATE_NAME = "setGmtCreate";
    private static final String GMT_MODIFY_NAME = "setGmtModified";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        Object parameter = args[1];
        SqlCommandType commandType = statement.getSqlCommandType();
        try {
            Method setGmtCreate = parameter.getClass().getMethod(GMT_CREATE_NAME, Date.class);
            Method setModified = parameter.getClass().getMethod(GMT_MODIFY_NAME, Date.class);

            Date now = new Date();
            if (SqlCommandType.INSERT.equals(commandType)) {
                setGmtCreate.invoke(parameter, now);
                setModified.invoke(parameter, now);
            } else if (SqlCommandType.UPDATE.equals(commandType)) {
                setModified.invoke(parameter, now);
            }
            invocation.getArgs()[1] = parameter;
        } catch (Exception e) {

        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}
}
