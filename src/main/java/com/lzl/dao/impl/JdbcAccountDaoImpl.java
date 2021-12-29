package com.lzl.dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.lzl.dao.AccountDao;
import com.lzl.pojo.Account;
import com.lzl.util.ConnectionUtils;
import com.lzl.util.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcAccountDaoImpl implements AccountDao {
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
//        Connection connection = DruidUtils.getInstance().getConnection();
//        Connection connection = ConnectionUtils.getInstance().getCurrentThreadConn();
          Connection connection = connectionUtils.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = new Account();
        while (resultSet.next()){
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }
        resultSet.close();
        preparedStatement.close();
//        connection.close();
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
//        Connection connection = DruidUtils.getInstance().getConnection();
//        Connection connection = ConnectionUtils.getInstance().getCurrentThreadConn();
        Connection connection = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,account.getMoney());
        preparedStatement.setString(2,account.getCardNo());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
//        connection.close();
        return i;
    }
}
