package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.CountryDao;
import com.ssh.pojo.Accounting;
import com.ssh.pojo.Adult;
import com.ssh.pojo.Country;

public class CountryDaoImpl implements CountryDao {
	private HibernateTemplate hibernateTemplate;

	//获取到国家开放表信息
	public Country getCountry() {
		Country country = null;
		try{
			List<Country> list = hibernateTemplate.find("from Country");
			country = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return country;
	}

	//通过id得到Country对象
	public Country getCountryById(Integer cyid) {
		Country country = null;
		try{
			country = hibernateTemplate.get(Country.class, cyid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return country;
	}

	//修改国家开放表
	public boolean update(Country country) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(country);
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
	public List<Country> getCountryList() {
		List<Country> country = new ArrayList<Country>();
		try{
			country = hibernateTemplate.find("from Country");
		}catch(Exception e){
			e.printStackTrace();
		}
		return country;
	}

	@Override
	public int getAllCountry() {
		int count = 0;
		try{
			List<Country> list = hibernateTemplate.find("from Country");
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
