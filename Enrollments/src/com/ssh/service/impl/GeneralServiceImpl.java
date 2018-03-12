package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.GeneralDao;
import com.ssh.pojo.General;
import com.ssh.service.GeneralService;

public class GeneralServiceImpl implements GeneralService{
	private GeneralDao generalDao;
	
	public boolean addGeneral(General general) {
		return generalDao.addGeneral(general);
		
	}

	
	public boolean deleteGeneral(Integer id) {
		return generalDao.deleteGeneral(id);
		
	}


	public boolean updateGeneral(General general) {
		return generalDao.updateGeneral(general);
	}


	public List<General> selectGeneral() {
		
		return generalDao.selectGeneral();
	}


	public GeneralDao getGeneralDao() {
		return generalDao;
	}


	public void setGeneralDao(GeneralDao generalDao) {
		this.generalDao = generalDao;
	}


	@Override
	public int getAllCount() {
		return generalDao.getAllCount();
	}


	@Override
	public General getGeneral(int gid) {
		return generalDao.getGeneral(gid);
	}


	//根据输入的条件模糊查询(遍历招生简章表)
	public List<General> listLikeGeneral(String gtitle, int page, int row) {
		return generalDao.listLikeGeneral(gtitle, page, row);
	}


	
}
