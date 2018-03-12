package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.AccountingDao;
import com.ssh.pojo.Accounting;
import com.ssh.service.AccountingService;

public class AccountingServiceImpl implements AccountingService {
	private AccountingDao accountingDao;
	
	//获取到会计表list
	public Accounting getAccounting() {
		return accountingDao.getAccounting();
	}

	//通过id得到Accounting对象
	public Accounting getAccountingById(Integer aid) {
		return accountingDao.getAccountingById(aid);
	}

	//修改会计表
	public boolean update(Accounting accounting) {
		return accountingDao.update(accounting);
	}

	public AccountingDao getAccountingDao() {
		return accountingDao;
	}

	public void setAccountingDao(AccountingDao accountingDao) {
		this.accountingDao = accountingDao;
	}

	@Override
	public List<Accounting> getAccountingList() {
		return accountingDao.getAccountingList();
	}

	@Override
	public int getAllAccounting() {
		return accountingDao.getAllAccounting();
	}
	
}
