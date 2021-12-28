package com.lzl.util;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidUtils {

    private DruidUtils(){}

    private static DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/zdy_mybatis?serverTimezone=GMT");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("aaaaa345");

    }

    public static DruidDataSource getInstance(){
        return  druidDataSource;
    }

}
