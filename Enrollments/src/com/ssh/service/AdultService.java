package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Adult;

public interface AdultService {
	Adult getAdult();  //获取到成人考试表信息
	Adult getAdultById(Integer atid);  //通过id得到Adult对象
	boolean update(Adult adult);  //修改成人考试表
	List<Adult> getAdultList();
	int getAllAdult();
}
