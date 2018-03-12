package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Indexcontent;

public interface IndexContentDao {
	public abstract Indexcontent  getIndexcontent(int id);//得到首页内容对象
	public abstract boolean SaveIndexcontext(Indexcontent indexcontent); //保存首页内容
	public abstract boolean  UpdateIndexcontext(Indexcontent indexcontent); //修改首页内容
	public abstract boolean  DeleteIndexcontext(int id); //删除首页内容
	public abstract List<Indexcontent> getIndexcontentList(); //得到首页内容集合
	public abstract int getAllCount();//得到一共多少条数据
}
