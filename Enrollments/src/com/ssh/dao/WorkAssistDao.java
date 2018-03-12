package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.WorkAssist;

public interface WorkAssistDao {
	//增加
	boolean addWorkAssist(WorkAssist workAssist);
	//删除
	boolean deleteWorkAssist(Integer id);
	//修改
	boolean updateWorkAssist(WorkAssist workAssist);
	//遍历
	List<WorkAssist> selectWorkAssist(Integer tid);
	//得到对象
	WorkAssist getWorkAssist(int wa_id);
	
	public abstract int getAllCount();//得到一共多少条数据
}
