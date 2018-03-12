package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.WorkAssistDao;
import com.ssh.pojo.General;
import com.ssh.pojo.WorkAssist;

public class WorkAssistDaoImpl implements WorkAssistDao{
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addWorkAssist(WorkAssist workAssist) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(workAssist);
			isSave = true;
		}catch(Exception e){
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean deleteWorkAssist(Integer id) {
		boolean isSave = false;
		try{
			WorkAssist workAssist = hibernateTemplate.get(WorkAssist.class, id);
			hibernateTemplate.delete(workAssist);
			isSave = true;
		}catch(Exception e){
			isSave = false;
		}
		return isSave;
	}

	@Override
	public boolean updateWorkAssist(WorkAssist workAssist) {
		boolean isSave = false;
		try{
			hibernateTemplate.update(workAssist);
			isSave = true;
		}catch(Exception e){
			isSave = false;
		}
		return isSave;
	}

	@Override
	public List<WorkAssist> selectWorkAssist(Integer tid) {
		List<WorkAssist> list = new ArrayList<WorkAssist>();
		try{
			list= hibernateTemplate.find("from WorkAssist w where w.teacherid=?",tid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public WorkAssist getWorkAssist(int wa_id) {
		WorkAssist workAssist =hibernateTemplate.get(WorkAssist.class, wa_id);
		return workAssist;
	}

	@Override
	public int getAllCount() {
		int count = 0;
		List<WorkAssist> list = hibernateTemplate.find("from WorkAssist");
		if(list!=null&&list.size()>0){
			count = list.size();
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
