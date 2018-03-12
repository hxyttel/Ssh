package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.VocationalDao;
import com.ssh.pojo.Accounting;
import com.ssh.pojo.Vocational;

public class VocationalDaoImpl implements VocationalDao {
	private HibernateTemplate hibernateTemplate;

	//获取到职业资格表信息
	public Vocational getVocational() {
		Vocational vocational = null;
		try{
			List<Vocational> list = hibernateTemplate.find("from Vocational");
			vocational = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return vocational;
	}

	//通过id得到Vocational对象
	public Vocational getVocationalById(Integer vlid) {
		Vocational vocational = null;
		try{
			vocational = hibernateTemplate.get(Vocational.class, vlid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return vocational;
	}

	//修改职业资格表
	public boolean update(Vocational vocational) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(vocational);
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
	public List<Vocational> getVocationalList() {
		List<Vocational> vocational = new ArrayList<Vocational>();
		try{
			vocational = hibernateTemplate.find("from Vocational");
		}catch(Exception e){
			e.printStackTrace();
		}
		return vocational;
	}

	@Override
	public int getAllVocational() {
		int count = 0;
		try{
			List<Vocational> list = hibernateTemplate.find("from Vocational");
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
