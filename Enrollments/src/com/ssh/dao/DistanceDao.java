package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Accounting;
import com.ssh.pojo.Distance;

public interface DistanceDao {
	Distance getDistance();  //获取到远程教育表信息
	Distance getDistanceById(Integer cyid);  //通过id得到Distance对象
	boolean update(Distance distance);  //修改远程教育表
	List<Distance> getDistanceList();
	int getAllDistance();
}
