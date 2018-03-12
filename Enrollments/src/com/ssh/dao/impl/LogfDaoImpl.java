package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.LogfDao;
import com.ssh.pojo.Logf;

public class LogfDaoImpl implements LogfDao{
	private HibernateTemplate hibernateTemplate;
	private Session session = null;
		
	@Override
	public void addJournal(Logf logf) {
		hibernateTemplate.save(logf);
	}

	@Override
	public boolean DeleteLogf(int id) {
		boolean isSave = false;
		try{
			Logf logf= hibernateTemplate.get(Logf.class, id);
			hibernateTemplate.delete(logf);
			isSave = true;
		}catch(Exception e){
			isSave = false;
		}
		return isSave;
		
	}

	@Override
	public List<Logf> getLogfList() {
		List<Logf> list = hibernateTemplate.find("from Logf");
		return list;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//一共获取多少数据
	public int getAllCount() {
		int count = 0;
		List<Logf> list = hibernateTemplate.find("from Logf");
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}

	//根据条件查询日志表(模糊查询)
	public List<Logf> listLikeLogf(Logf logf, int page, int row) {
		String name = logf.getUsername();
		String method = logf.getMethod();
		List<Logf> listLike = null;
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Logf l where 1=1 ";
			if(name==null && method==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else if("".equals(name) && "".equals(method)){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				if(name!=null || name!=""){
					sql +=" and l.username like '%"+name+"%'";
				}
				if(method!=null || method!=""){
					sql +=" and l.method like '%"+method+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}
	

}
