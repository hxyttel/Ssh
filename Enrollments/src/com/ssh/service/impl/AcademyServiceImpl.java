package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.AcademyDao;
import com.ssh.pojo.Academy;
import com.ssh.pojo.Major;
import com.ssh.service.AcademySerice;

public class AcademyServiceImpl implements AcademySerice {
	private AcademyDao academyDao;
	
	//保存专业管理表信息
	public boolean save(Academy academy) {
		return academyDao.save(academy);
	}

	//根据id获取具体哪个院校信息
	public Academy getAcademyById(int id) {
		return academyDao.getAcademyById(id);
	}

	//根据id修改信息
	public boolean update(Academy academy) {
		return academyDao.update(academy);
	}

	//根据id删除信息
	public boolean delete(int id) {
		return academyDao.delete(id);
	}

	//获取专业表中所有信息
	public List<Academy> listAcademy() {
		return academyDao.listAcademy();
	}
	
	//获取数据库表中总共有多少条数据
	public int getAcademyCount() {
		return academyDao.getAcademyCount();
	}

	public AcademyDao getAcademyDao() {
		return academyDao;
	}

	public void setAcademyDao(AcademyDao academyDao) {
		this.academyDao = academyDao;
	}
	
	public List<Academy> selectAcademy() {
		return academyDao.selectAcademy();
	}
	
	public List<Major> selectMajor(int aid) {
		return academyDao.selectMajor(aid);
	}
	
	//根据条件模糊查询院校表
	public List<Academy> listLikeAcademy(Academy academy,int page,int row) {
		return academyDao.listLikeAcademy(academy,page,row);
	}

	//根据院校名称获取到id
	public int getIdByAname(String aname) {
		return academyDao.getIdByAname(aname);
	}

}
