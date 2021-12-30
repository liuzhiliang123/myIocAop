package com.lzl.service.impl;

import com.lzl.dao.AccountDao;
import com.lzl.dao.impl.JdbcAccountDaoImpl;
import com.lzl.factory.BeanFactory;
import com.lzl.pojo.Account;
import com.lzl.service.TransferService;
import com.lzl.util.TransactionManager;

public class TransferServiceImpl implements TransferService {
//    private AccountDao accountDao = new JdbcAccountDaoImpl();
//    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

      private AccountDao accountDao;
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCarNo, String toCardNo, int money) throws Exception {

       /* try {
            TransactionManager.getInstance().beginTransaction();*/
            Account from = accountDao.queryAccountByCardNo(fromCarNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);
            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);
            accountDao.updateAccountByCardNo(to);
//            int c = 1/0;
            accountDao.updateAccountByCardNo(from);
         /*   TransactionManager.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionManager.getInstance().rollback();
            throw e;
        }*/
    }
}
