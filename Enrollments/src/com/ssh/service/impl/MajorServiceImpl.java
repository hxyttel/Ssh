package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.MajorDao;
import com.ssh.pojo.Major;
import com.ssh.service.MajorService;

public class MajorServiceImpl implements MajorService {
	private MajorDao majorDao;

	//增加专业表
	public boolean add(Major major) {
		return majorDao.add(major);
	}

	//根据id获取到专业表相应信息
	public Major getMajorById(Integer id) {
		return majorDao.getMajorById(id);
	}

	//修改专业表
	public boolean update(Major major) {
		return majorDao.update(major);
	}

	//根据id删除专业表对应信息
	public boolean delete(Integer id) {
		return majorDao.delete(id);
	}

	//获取到专业表中所有信息
	public List<Major> getMajorList() {
		return majorDao.getMajorList();
	}
	
	//获取数据库表中总共有多少条数据
	public int getMajorCount() {
		return majorDao.getMajorCount();
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

}
