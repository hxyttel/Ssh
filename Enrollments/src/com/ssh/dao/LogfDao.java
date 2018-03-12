package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Logf;

public interface LogfDao {
	void addJournal(Logf logf);  //向日志表中添加字段
	boolean DeleteLogf(int id); //删除日志表
	List<Logf> getLogfList();    //遍历日志表
	public abstract int getAllCount();//得到一共多少条数据
	List<Logf> listLikeLogf(Logf logf,int page,int row); //根据条件查询日志表(模糊查询)
}
