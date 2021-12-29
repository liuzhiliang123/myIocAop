package com.lzl.util;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

/*    private ConnectionUtils(){}
    private static ConnectionUtils connectionUtils = new ConnectionUtils();
    public static  ConnectionUtils getInstance(){
        return  connectionUtils;
    }*/
    private ThreadLocal<Connection>  threadLocal = new ThreadLocal<>();

    public Connection getCurrentThreadConn() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection==null){
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

}
