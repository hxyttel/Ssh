package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.DistanceDao;
import com.ssh.pojo.Distance;
import com.ssh.service.DistanceService;

public class DistanceServiceImpl implements DistanceService {
	private DistanceDao distanceDao;

	//获取到远程教育表信息
	public Distance getDistance() {
		return distanceDao.getDistance();
	}

	//通过id得到Distance对象
	public Distance getDistanceById(Integer cyid) {
		return distanceDao.getDistanceById(cyid);
	}

	//修改远程教育表
	public boolean update(Distance distance) {
		return distanceDao.update(distance);
	}

	public DistanceDao getDistanceDao() {
		return distanceDao;
	}

	public void setDistanceDao(DistanceDao distanceDao) {
		this.distanceDao = distanceDao;
	}

	@Override
	public List<Distance> getDistanceList() {
		return distanceDao.getDistanceList();
	}

	@Override
	public int getAllDistance() {
		return distanceDao.getAllDistance();	
	}
	
}
