package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.AdultDao;
import com.ssh.pojo.Adult;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Student;

public class AdultDaoImpl implements AdultDao {
	
	private HibernateTemplate hibernateTemplate;

	//获取到成人考试表信息
	public Adult getAdult() {
		Adult adult = null;
		try{
			List<Adult> list = hibernateTemplate.find("from Adult");
			adult = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return adult;
	}

	//通过id得到Adult对象
	public Adult getAdultById(Integer atid) {
		Adult adult = null;
		try{
			adult = hibernateTemplate.get(Adult.class, atid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return adult;
	}

	//修改成人考试表
	public boolean update(Adult adult) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(adult);
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
	public List<Adult> getAdultList() {
		List<Adult> adult = new ArrayList<Adult>();
		try{
			adult = hibernateTemplate.find("from Adult");
		}catch(Exception e){
			e.printStackTrace();
		}
		return adult;

	}

	@Override
	public int getAllAdult() {
		int count = 0;
		try{
			List<Adult> list = hibernateTemplate.find("from Adult");
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
