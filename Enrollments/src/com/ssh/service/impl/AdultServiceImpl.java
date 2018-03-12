package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.AdultDao;
import com.ssh.pojo.Adult;
import com.ssh.service.AdultService;

public class AdultServiceImpl implements AdultService {
	private AdultDao adultDao;

	//获取到成人考试表信息
	public Adult getAdult() {
		return adultDao.getAdult();
	}

	//通过id得到Adult对象
	public Adult getAdultById(Integer atid) {
		return adultDao.getAdultById(atid);
	}

	//修改成人考试表
	public boolean update(Adult adult) {
		return adultDao.update(adult);
	}

	public AdultDao getAdultDao() {
		return adultDao;
	}

	public void setAdultDao(AdultDao adultDao) {
		this.adultDao = adultDao;
	}

	@Override
	public List<Adult> getAdultList() {
		return adultDao.getAdultList();
	}

	@Override
	public int getAllAdult() {
		return adultDao.getAllAdult();
	}
	
}
