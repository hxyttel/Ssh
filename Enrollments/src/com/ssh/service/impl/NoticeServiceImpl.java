package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.NoticeDao;
import com.ssh.pojo.Notice;
import com.ssh.service.NoticeService;

public class NoticeServiceImpl implements NoticeService{

	private NoticeDao noticeDao;
	public Notice getNotice(int id) {
		return noticeDao.getNotice(id);
	}

	public boolean SaveNotice(Notice notice) {
		return noticeDao.SaveNotice(notice);
	}

	public boolean UpdateNotice(Notice notice) {
		return noticeDao.UpdateNotice(notice);
	}

	public boolean DeleteNotice(int id) {
		return noticeDao.DeleteNotice(id);
	}

	public List<Notice> getNoticeList() {
		return noticeDao.getNoticeList();
	}

	public int getAllCount() {
		return noticeDao.getAllCount();
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	//遍历公告表(模糊查询)
	public List<Notice> listLikeNotice(Notice notice, int page, int row) {
		return noticeDao.listLikeNotice(notice, page, row);
	}
	

}
