package com.lzl.util;

import java.sql.SQLException;

public class TransactionManager {

   /* private TransactionManager() {
    }*/

    private  static  TransactionManager transactionManager = new TransactionManager();
    public static TransactionManager getInstance(){
        return transactionManager;
    }

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void beginTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }

    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }

}
