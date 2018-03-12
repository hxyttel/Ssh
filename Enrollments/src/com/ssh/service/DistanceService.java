package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Distance;

public interface DistanceService {
	Distance getDistance();  //获取到远程教育表信息
	Distance getDistanceById(Integer cyid);  //通过id得到Distance对象
	boolean update(Distance distance);  //修改远程教育表
	List<Distance> getDistanceList();
	int getAllDistance();
}
