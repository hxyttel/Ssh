package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Academy;
import com.ssh.pojo.Major;

public interface AcademySerice {
	boolean save(Academy academy); //保存专业管理表信息
	Academy getAcademyById(int id); //根据id获取具体哪个院校信息
	boolean update(Academy academy);  //根据id修改信息
	boolean delete(int id);  //根据id删除信息
	List<Academy> listAcademy();  //获取专业表中所有信息
	int getAcademyCount(); //获取数据库表中总共有多少条数据
	List<Academy> selectAcademy();//获取成人教育的所有学校
	List<Major> selectMajor(int aid);//获取成人教育的专业
	List<Academy> listLikeAcademy(Academy academy,int page,int row); //根据条件模糊查询院校表
	int getIdByAname(String aname); //根据院校名称获取到id
}
