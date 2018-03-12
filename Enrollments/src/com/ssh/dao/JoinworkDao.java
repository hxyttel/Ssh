package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Joinwork;

public interface JoinworkDao {
	//增加
			boolean addJoinwork(Joinwork joinwork);
				//删除
			boolean deleteJoinwork(Integer id);
			//得到对象
			Joinwork getJoinwork(int jwid);
				//修改
			boolean updateJoinwork(Joinwork joinwork);
				//查询
			List<Joinwork> selectJoinwork();
			
			public abstract int getAllCount();//得到一共多少条数据
			List<Joinwork> listLikeJoinwork(String title,Integer page,Integer row);//模糊遍历查询
}
