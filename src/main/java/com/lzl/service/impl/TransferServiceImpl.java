package com.lzl.service.impl;

import com.lzl.dao.AccountDao;
import com.lzl.dao.impl.JdbcAccountDaoImpl;
import com.lzl.pojo.Account;
import com.lzl.service.TransferService;

public class TransferServiceImpl implements TransferService {
    private AccountDao accountDao = new JdbcAccountDaoImpl();

    @Override
    public void transfer(String fromCarNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCarNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);

        accountDao.updateAccountByCardNo(to);
//        int c = 1/0;
        accountDao.updateAccountByCardNo(from);
    }
}
