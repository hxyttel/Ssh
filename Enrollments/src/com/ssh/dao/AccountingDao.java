package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Accounting;
import com.ssh.pojo.Adult;

public interface AccountingDao {
	Accounting getAccounting();  //获取到会计表信息
	Accounting getAccountingById(Integer aid);  //通过id得到Accounting对象
	boolean update(Accounting accounting);  //修改会计表
	List<Accounting> getAccountingList();
	int getAllAccounting();
}
