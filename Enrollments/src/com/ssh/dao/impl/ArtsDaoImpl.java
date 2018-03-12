package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.ArtsDao;
import com.ssh.pojo.Adult;
import com.ssh.pojo.Arts;

public class ArtsDaoImpl implements ArtsDao {
	private HibernateTemplate hibernateTemplate;
	
	//获取到艺考表信息
	public Arts getArts() {
		Arts arts = null;
		try{
			List<Arts> list = hibernateTemplate.find("from Arts");
			arts = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arts;
	}

	//通过id得到Arts对象
	public Arts getArtsById(Integer asid) {
		Arts arts = null;
		try{
			arts = hibernateTemplate.get(Arts.class, asid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return arts;
	}

	//修改艺考表
	public boolean update(Arts arts) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(arts);
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
	public List<Arts> getArtsList() {
		List<Arts> arts = new ArrayList<Arts>();
		try{
			arts = hibernateTemplate.find("from Arts");
		}catch(Exception e){
			e.printStackTrace();
		}
		return arts;
	}

	@Override
	public int getAllArts() {
		int count = 0;
		try{
			List<Arts> list = hibernateTemplate.find("from Arts");
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
