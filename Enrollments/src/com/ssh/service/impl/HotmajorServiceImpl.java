package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.HotmajorDao;
import com.ssh.pojo.Hotmajor;
import com.ssh.service.HotmajorService;

public class HotmajorServiceImpl implements HotmajorService{
	private HotmajorDao hotmajorDao;
	@Override
	public Hotmajor getHotmajor(int id) {
		return hotmajorDao.getHotmajor(id);
	}

	@Override
	public boolean SaveHotmajor(Hotmajor hotmajor) {
		return hotmajorDao.SaveHotmajor(hotmajor);
	}

	@Override
	public boolean UpdateHotmajor(Hotmajor hotmajor) {
		return hotmajorDao.UpdateHotmajor(hotmajor);
	}

	@Override
	public boolean DeleteHotmajor(int id) {
		return hotmajorDao.DeleteHotmajor(id);
	}

	@Override
	public List<Hotmajor> getHotmajorList() {
		return hotmajorDao.getHotmajorList();
	}

	@Override
	public int getAllCount() {
		return hotmajorDao.getAllCount();
	}

	public HotmajorDao getHotmajorDao() {
		return hotmajorDao;
	}

	public void setHotmajorDao(HotmajorDao hotmajorDao) {
		this.hotmajorDao = hotmajorDao;
	}

	//模糊查询遍历所有热门专业
	public List<Hotmajor> listLikeHotMajor(String htitle, int page, int row) {
		return hotmajorDao.listLikeHotMajor(htitle, page, row);
	}
	

}
