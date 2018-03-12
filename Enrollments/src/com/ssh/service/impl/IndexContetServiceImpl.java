package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.IndexContentDao;
import com.ssh.pojo.Indexcontent;
import com.ssh.service.IndexContentService;

public class IndexContetServiceImpl implements IndexContentService{
	private IndexContentDao indexcontentDao;
	
	public Indexcontent getIndexcontent(int id) {
		return indexcontentDao.getIndexcontent(id);
	}

	public boolean SaveIndexcontext(Indexcontent indexcontent) {
		indexcontentDao.SaveIndexcontext(indexcontent);
		return false;
	}

	public boolean UpdateIndexcontext(Indexcontent indexcontent) {
		return indexcontentDao.UpdateIndexcontext(indexcontent);
	}

	public boolean DeleteIndexcontext(int id) {
		return false;
	}

	public List<Indexcontent> getIndexcontentList() {
		return indexcontentDao.getIndexcontentList();
	}

	public int getAllCount() {
		return indexcontentDao.getAllCount();
	}

	public IndexContentDao getIndexcontentDao() {
		return indexcontentDao;
	}

	public void setIndexcontentDao(IndexContentDao indexcontentDao) {
		this.indexcontentDao = indexcontentDao;
	}
	

}
