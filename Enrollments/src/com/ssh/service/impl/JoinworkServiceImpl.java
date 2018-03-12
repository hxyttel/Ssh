package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.JoinworkDao;
import com.ssh.pojo.Joinwork;
import com.ssh.service.JoinworkService;

public class JoinworkServiceImpl implements JoinworkService{
	private JoinworkDao joinworkDao;
	@Override
	public boolean addJoinwork(Joinwork joinwork) {
		return joinworkDao.addJoinwork(joinwork);
	}

	@Override
	public boolean deleteJoinwork(Integer id) {
		return joinworkDao.deleteJoinwork(id);
	}

	@Override
	public Joinwork getJoinwork(int jwid) {
		return joinworkDao.getJoinwork(jwid);
	}

	@Override
	public boolean updateJoinwork(Joinwork joinwork) {
		return joinworkDao.updateJoinwork(joinwork);
	}

	@Override
	public List<Joinwork> selectJoinwork() {
		return joinworkDao.selectJoinwork();
	}

	@Override
	public int getAllCount() {
		return joinworkDao.getAllCount();
	}
	public JoinworkDao getJoinworkDao() {
		return joinworkDao;
	}

	public void setJoinworkDao(JoinworkDao joinworkDao) {
		this.joinworkDao = joinworkDao;
	}

	//模糊遍历查询
	public List<Joinwork> listLikeJoinwork(String title, Integer page, Integer row) {
		return joinworkDao.listLikeJoinwork(title, page, row);
	}

}
