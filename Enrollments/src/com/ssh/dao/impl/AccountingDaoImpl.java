package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.AccountingDao;
import com.ssh.pojo.Accounting;
import com.ssh.pojo.Adult;

public class AccountingDaoImpl implements AccountingDao {
	private HibernateTemplate hibernateTemplate;
	
	//获取到会计表信息
	public Accounting getAccounting() {
		Accounting accounting = null;
		try{
			List<Accounting> list= hibernateTemplate.find("from Accounting");
			accounting = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return accounting;
	}

	//通过id得到Accounting对象
	public Accounting getAccountingById(Integer aid) {
		Accounting accounting = null;
		try{
			accounting = hibernateTemplate.get(Accounting.class, aid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return accounting;
	}

	//修改会计表
	public boolean update(Accounting accounting) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(accounting);
			isUpdate = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isUpdate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<Accounting> getAccountingList() {
		List<Accounting> accounting = new ArrayList<Accounting>();
		try{
			accounting = hibernateTemplate.find("from Accounting");
		}catch(Exception e){
			e.printStackTrace();
		}
		return accounting;
	}

	@Override
	public int getAllAccounting() {
		int count = 0;
		try{
			List<Accounting> list = hibernateTemplate.find("from Accounting");
			if(list!=null&&list.size()>0){
				count = list.size();
		        return count;
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
}
