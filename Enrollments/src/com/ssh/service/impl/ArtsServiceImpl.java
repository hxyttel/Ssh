package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.ArtsDao;
import com.ssh.pojo.Arts;
import com.ssh.service.ArtsService;

public class ArtsServiceImpl implements ArtsService {
	private ArtsDao artsDao;
	
	//获取到艺考表信息
	public Arts getArts() {
		return artsDao.getArts();
	}

	//通过id得到Arts对象
	public Arts getArtsById(Integer asid) {
		return artsDao.getArtsById(asid);
	}

	//修改艺考表
	public boolean update(Arts arts) {
		return artsDao.update(arts);
	}

	public ArtsDao getArtsDao() {
		return artsDao;
	}

	public void setArtsDao(ArtsDao artsDao) {
		this.artsDao = artsDao;
	}

	@Override
	public List<Arts> getArtsList() {
		return artsDao.getArtsList();
	}

	@Override
	public int getAllArts() {
		return artsDao.getAllArts();
	}
	
}
