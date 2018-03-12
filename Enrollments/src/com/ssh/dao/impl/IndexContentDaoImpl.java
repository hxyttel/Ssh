package com.ssh.dao.impl;



import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.IndexContentDao;
import com.ssh.pojo.Indexcontent;

public class IndexContentDaoImpl implements IndexContentDao{
	public HibernateTemplate hibernateTemplate;
	
	public Indexcontent getIndexcontent(int id) {
		Indexcontent indexcontent = hibernateTemplate.get(Indexcontent.class, id);
		return indexcontent;
	}

	public boolean SaveIndexcontext(Indexcontent indexcontent) {
		boolean isSave = false;
		try {
			hibernateTemplate.save(indexcontent);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public boolean UpdateIndexcontext(Indexcontent indexcontent) {
		boolean isSave = false;
		try {
			hibernateTemplate.update(indexcontent);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public boolean DeleteIndexcontext(int id) {
		return false;
	}

	public List<Indexcontent> getIndexcontentList() {
		List<Indexcontent> list = hibernateTemplate.find("from Indexcontent");
		return list;
	}

	public int getAllCount() {
		int count = 0;
		List<Indexcontent> list = hibernateTemplate.find("from Indexcontent");
		if(list!=null&&list.size()>0){
			count = list.size();
            return count;
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
