package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Hotmajor;

public interface HotmajorDao {
	public abstract Hotmajor  getHotmajor(int id);//得到热门专业对象
	public abstract boolean SaveHotmajor(Hotmajor hotmajor); //保存热门专业对象
	public abstract boolean UpdateHotmajor(Hotmajor hotmajor);//修改热门专业
	public abstract boolean DeleteHotmajor(int id);//删除热门专业
	public List<Hotmajor> getHotmajorList();//遍历热门专业
	public abstract int getAllCount();//得到一共多少条数据
	List<Hotmajor> listLikeHotMajor(String htitle,int page,int row); //模糊查询遍历所有热门专业
}
