package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.IndexPictureDao;
import com.ssh.pojo.Indexpicture;
import com.ssh.service.IndexPictureService;

public class IndexPictureServiceImpl implements IndexPictureService{
	private IndexPictureDao indexpictureDao;
	public Indexpicture getIndexpicture(int id) {
		return indexpictureDao.getIndexpicture(id);
	}

	public boolean SaveIndexpicture(Indexpicture indexpicture) {
		return indexpictureDao.SaveIndexpicture(indexpicture);
	}

	public boolean UpdateIndexpicture(Indexpicture indexpicture) {
		return indexpictureDao.UpdateIndexpicture(indexpicture);
	}

	public boolean DeleteIndexpicture(int id) {
		return indexpictureDao.DeleteIndexpicture(id);
	}

	public List<Indexpicture> getIndexpictureList() {
		return indexpictureDao.getIndexpictureList();
	}

	public IndexPictureDao getIndexpictureDao() {
		return indexpictureDao;
	}

	public void setIndexpictureDao(IndexPictureDao indexpictureDao) {
		this.indexpictureDao = indexpictureDao;
	}

	public int getAllCount() {
		return indexpictureDao.getAllCount();
	}

	
	

}
