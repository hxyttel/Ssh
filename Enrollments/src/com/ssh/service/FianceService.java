package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Finance;

public interface FianceService {
	public abstract Finance  getFinance(int id);//得到缴费对象
	public abstract boolean SaveFinance(Finance finance); //保存缴费对象
	public abstract boolean UpdateFinance(Finance finance);//修改缴费
	public abstract boolean DeleteFinance(int id);//删除缴费
	public List<Finance> getFinanceList();//遍历缴费
	public abstract int getAllCount();//得到一共多少条数据
	List<Finance> listLikeFinance(Finance fiance,int page,int row); //根据输入的条件模糊查询(遍历缴费表)
	Finance getFinanceBySid(int sid);//根据学生id查询缴费情况
}
