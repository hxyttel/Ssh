package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.LogfDao;
import com.ssh.pojo.Logf;
import com.ssh.service.LogfService;

public class LogfServiceImpl implements LogfService{
	private LogfDao logfDao;

	public void addJournal(Logf logf) {
		logfDao.addJournal(logf);
	}

	public boolean DeleteLogf(int id) {
		return logfDao.DeleteLogf(id);
	}

	public List<Logf> getLogfList() {
		return logfDao.getLogfList();
	}

	public LogfDao getLogfDao() {
		return logfDao;
	}

	public void setLogfDao(LogfDao logfDao) {
		this.logfDao = logfDao;
	}

	//获取一共多少数据
	public int getAllCount() {
		return logfDao.getAllCount();
	}

	//根据条件查询日志表(模糊查询)
	public List<Logf> listLikeLogf(Logf logf, int page, int row) {
		return logfDao.listLikeLogf(logf, page, row);
	}
	

}
