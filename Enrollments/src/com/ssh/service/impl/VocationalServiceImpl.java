package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.VocationalDao;
import com.ssh.pojo.Vocational;
import com.ssh.service.VocationalService;

public class VocationalServiceImpl implements VocationalService {
	private VocationalDao vocationalDao;

	//获取到职业资格表信息
	public Vocational getVocational() {
		return vocationalDao.getVocational();
	}

	//通过id得到Vocational对象
	public Vocational getVocationalById(Integer vlid) {
		return vocationalDao.getVocationalById(vlid);
	}

	//修改职业资格表
	public boolean update(Vocational vocational) {
		return vocationalDao.update(vocational);
	}

	public VocationalDao getVocationalDao() {
		return vocationalDao;
	}

	public void setVocationalDao(VocationalDao vocationalDao) {
		this.vocationalDao = vocationalDao;
	}

	@Override
	public List<Vocational> getVocationalList() {
		return vocationalDao.getVocationalList();
	}

	@Override
	public int getAllVocational() {
		return vocationalDao.getAllVocational();
	}
	
}
