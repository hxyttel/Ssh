package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Notice;

public interface NoticeDao {
	public abstract Notice  getNotice(int id);//得到公告表对象
	public abstract boolean SaveNotice(Notice notice); //保存公告表对象
	public abstract boolean UpdateNotice(Notice notice);//修改公告表
	public abstract boolean DeleteNotice(int id);//删除公告表
	public List<Notice> getNoticeList();//遍历公告表
	public abstract int getAllCount();//得到一共多少条数据
	List<Notice> listLikeNotice(Notice notice,int page,int row); //遍历公告表(模糊查询)
}
