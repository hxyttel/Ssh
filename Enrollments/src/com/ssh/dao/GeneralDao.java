package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.General;
import com.ssh.pojo.StudentFile;

public interface GeneralDao {
		//增加
	boolean addGeneral(General general);
		//删除
	boolean deleteGeneral(Integer id);
	
		//修改
	boolean updateGeneral(General general);
		//遍历
	List<General> selectGeneral();
	//得到对象
    General getGeneral(int gid);
	
	public abstract int getAllCount();//得到一共多少条数据
	List<General> listLikeGeneral(String gtitle,int page,int row); //根据输入的条件模糊查询(遍历招生简章表)
	
	
	
	
}
