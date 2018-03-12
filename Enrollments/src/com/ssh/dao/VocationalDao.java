package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Vocational;

public interface VocationalDao {
	Vocational getVocational();  //获取到职业资格表信息
	Vocational getVocationalById(Integer vlid);  //通过id得到Vocational对象
	boolean update(Vocational vocational);  //修改职业资格表
	List<Vocational> getVocationalList();
	int getAllVocational();
}
