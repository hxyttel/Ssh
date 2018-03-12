package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Major;

public interface MajorDao {
	boolean add(Major major); //增加专业表
	Major getMajorById(Integer id);  //根据id获取到专业表相应信息
	boolean update(Major major);  //修改专业表
	boolean delete(Integer id);  //根据id删除专业表对应信息
	List<Major> getMajorList();  //获取到专业表中所有信息
	int getMajorCount();  //获取数据库表中总共有多少条数据
}
