package com.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.MajorDao;
import com.ssh.pojo.Major;

public class MajorDaoImpl implements MajorDao {
	private HibernateTemplate hibernateTemplate;
	
	//增加专业表
	public boolean add(Major major) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(major);
			isSave = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isSave;
	}

	//根据id获取到专业表相应信息
	public Major getMajorById(Integer id) {
		Major major = null;
		try{
			major = hibernateTemplate.get(Major.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return major;
	}

	//修改专业表
	public boolean update(Major major) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(major);
			isUpdate = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isUpdate;
	}

	//根据id删除专业表对应信息
	public boolean delete(Integer id) {
		boolean isDelete = false;
		try{
			Major major = hibernateTemplate.get(Major.class, id);
			hibernateTemplate.delete(major);
			isDelete = true;
		}catch(Exception e){
			isDelete = false;
			e.printStackTrace();
		}
		return isDelete;
	}

	//获取到专业表中所有信息
	public List<Major> getMajorList() {
		List<Major> list = null;
		try{
			list = hibernateTemplate.find("from Major");
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	//获取数据库表中总共有多少条数据
	public int getMajorCount() {
		int count = 0;
		try{
			List<Major> list =  hibernateTemplate.find("from Major");
			count = list.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
