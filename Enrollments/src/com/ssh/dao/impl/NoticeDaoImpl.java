package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.NoticeDao;
import com.ssh.pojo.Logf;
import com.ssh.pojo.Notice;

public class NoticeDaoImpl implements NoticeDao{
	
	private HibernateTemplate hibernateTemplate;
	private Session session = null;
	
	public Notice getNotice(int id) {
		Notice notice = hibernateTemplate.get(Notice.class, id);
		return notice;
	}

	public boolean SaveNotice(Notice notice) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(notice);
			isSave = true;
		}catch(Exception e){
			e.printStackTrace();
			isSave = false;
			
		}
		return isSave;
	}

	public boolean UpdateNotice(Notice notice) {
		boolean isSave = false;
		try{
			hibernateTemplate.update(notice);
			isSave = true;
		}catch(Exception e){
			e.printStackTrace();
			isSave = false;
		}
		return isSave;
	}
	
	public boolean DeleteNotice(int id) {
		boolean isSave = false;
		try{
			Notice notice =hibernateTemplate.get(Notice.class, id);
			hibernateTemplate.delete(notice);
			isSave = true;
		}catch(Exception e){
			e.printStackTrace();
			isSave = false;
		}
		return isSave;
	}

	public List<Notice> getNoticeList() {
		List<Notice> list = hibernateTemplate.find("from Notice");
		return list;
	}

	public int getAllCount() {
		int count = 0;
		List<Notice> list = hibernateTemplate.find("from Notice");
		if(list!=null&&list.size()>0){
			count=list.size();
		}
		return count;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//遍历公告表(模糊查询)
	public List<Notice> listLikeNotice(Notice notice, int page, int row) {
		String title = notice.getNtitle();
		String nflag = notice.getNflag();
		List<Notice> listLike = null;
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Notice n where 1=1 ";
			if(title==null && nflag==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else if("".equals(title) && "".equals(nflag)){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				if(title!=null || title!=""){
					sql +=" and n.ntitle like '%"+title+"%'";
				}
				if(nflag!=null || nflag!=""){
					sql +=" and n.nflag like '%"+nflag+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}
	
}
