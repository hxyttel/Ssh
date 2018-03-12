package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.DistanceDao;
import com.ssh.pojo.Adult;
import com.ssh.pojo.Distance;

public class DistanceDaoImpl implements DistanceDao {
	private HibernateTemplate hibernateTemplate;
	
	//获取到远程教育表信息
	public Distance getDistance() {
		Distance distance = null;
		try{
			List<Distance> list = hibernateTemplate.find("from Distance");
			distance = list.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return distance;
	}

	//通过id得到Distance对象
	public Distance getDistanceById(Integer cyid) {
		Distance distance = null;
		try{
			distance = hibernateTemplate.get(Distance.class, cyid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return distance;
	}

	//修改远程教育表
	public boolean update(Distance distance) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(distance);
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
	public List<Distance> getDistanceList() {
		List<Distance> distance = new ArrayList<Distance>();
		try{
			distance = hibernateTemplate.find("from Distance");
		}catch(Exception e){
			e.printStackTrace();
		}
		return distance;
	}

	@Override
	public int getAllDistance() {
		int count = 0;
		try{
			List<Distance> list = hibernateTemplate.find("from Distance");
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
