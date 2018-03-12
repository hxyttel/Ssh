package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.GeneralDao;
import com.ssh.dao.WorkAssistDao;
import com.ssh.pojo.WorkAssist;
import com.ssh.service.WorkAssistService;

public class WorkAssistServiceImpl implements WorkAssistService{
	private WorkAssistDao workAssistDao;
	@Override
	public boolean addWorkAssist(WorkAssist workAssist) {
		return workAssistDao.addWorkAssist(workAssist);
	}

	@Override
	public boolean deleteWorkAssist(Integer id) {
		
		return workAssistDao.deleteWorkAssist(id);
	}

	@Override
	public boolean updateWorkAssist(WorkAssist workAssist) {
		
		return workAssistDao.updateWorkAssist(workAssist);
	}

	@Override
	public List<WorkAssist> selectWorkAssist(Integer tid) {
	
		return workAssistDao.selectWorkAssist(tid);
	}

	@Override
	public WorkAssist getWorkAssist(int wa_id) {
	
		return workAssistDao.getWorkAssist(wa_id);
	}

	@Override
	public int getAllCount() {
		
		return workAssistDao.getAllCount();
	}

	public WorkAssistDao getWorkAssistDao() {
		return workAssistDao;
	}

	public void setWorkAssistDao(WorkAssistDao workAssistDao) {
		this.workAssistDao = workAssistDao;
	}

}
