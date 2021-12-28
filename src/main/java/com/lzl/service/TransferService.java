package com.lzl.service;

public interface TransferService {

    void  transfer(String fromCarNo,String toCardNo,int money) throws Exception;

}
