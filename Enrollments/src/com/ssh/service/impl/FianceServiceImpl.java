package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.FianceDao;
import com.ssh.pojo.Finance;
import com.ssh.service.FianceService;

public class FianceServiceImpl implements FianceService{
	
	private FianceDao fianceDao;

	@Override
	public Finance getFinance(int id) {
		return fianceDao.getFinance(id);
	}

	@Override
	public boolean SaveFinance(Finance finance) {
		return fianceDao.SaveFinance(finance);
	}

	@Override
	public boolean UpdateFinance(Finance finance) {
		return fianceDao.UpdateFinance(finance);
	}

	@Override
	public boolean DeleteFinance(int id) {
		return fianceDao.DeleteFinance(id);
	}

	@Override
	public List<Finance> getFinanceList() {
		return fianceDao.getFinanceList();
	}

	@Override
	public int getAllCount() {
		return fianceDao.getAllCount();
	}
	
	//根据输入的条件模糊查询(遍历缴费表)
	public List<Finance> listLikeFinance(Finance fiance, int page, int row) {
		return fianceDao.listLikeFinance(fiance, page, row);
	}

	public FianceDao getFianceDao() {
		return fianceDao;
	}

	public void setFianceDao(FianceDao fianceDao) {
		this.fianceDao = fianceDao;
	}

	//根据学生id查询缴费情况
	public Finance getFinanceBySid(int sid) {
		return fianceDao.getFinanceBySid(sid);
	}

}
