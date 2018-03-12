package com.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.IndexPictureDao;
import com.ssh.pojo.Indexpicture;

public class IndexPictureDaoImpl implements IndexPictureDao{
	private HibernateTemplate hibernateTemplate;
	public Indexpicture getIndexpicture(int id) {
		Indexpicture indexpicture = hibernateTemplate.get(Indexpicture.class, id);
		return indexpicture;
	}

	public boolean SaveIndexpicture(Indexpicture indexpicture) {
		boolean isSave = false;
		try {
			hibernateTemplate.save(indexpicture);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public boolean UpdateIndexpicture(Indexpicture indexpicture) {
		boolean isSave = false;
		try {
			hibernateTemplate.update(indexpicture);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public boolean DeleteIndexpicture(int id) {
		boolean isSave = false;
		try {
			Indexpicture indexpicture=hibernateTemplate.get(Indexpicture.class, id);
			hibernateTemplate.delete(indexpicture);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public List<Indexpicture> getIndexpictureList() {
		List<Indexpicture> list= hibernateTemplate.find("from Indexpicture");
		return list;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int getAllCount() {
		int count=0;
		List<Indexpicture> list = hibernateTemplate.find("from Indexpicture");
		if(list!=null&&list.size()>0){
			count = list.size();
            return count;
        }
		return count;
	}
	

}
