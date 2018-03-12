package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.GeneralDao;
import com.ssh.pojo.General;

public class GeneralDaoImpl implements GeneralDao{
	private HibernateTemplate hibernateTemplate;
	private Session session;
	
	//增加
	public boolean addGeneral(General general) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(general);
			isSave = true;
		}catch(Exception e){
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	//删除
	public boolean deleteGeneral(Integer id) {
	boolean isSave = false;
	try{
		General general = hibernateTemplate.get(General.class, id);
		hibernateTemplate.delete(general);
		isSave = true;
	}catch(Exception e){
		isSave = false;
	}
	return isSave;
	}

	//修改
	public boolean updateGeneral(General general) {
	boolean isSave = false;
	try{
		hibernateTemplate.update(general);
		isSave = true;
	}catch(Exception e){
		isSave = false;
	}
	return isSave;
	}

	//查询
	public List<General> selectGeneral() {
		List<General> list = hibernateTemplate.find("from General");
		return list;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public int getAllCount() {
		int count = 0;
		List<General> list = hibernateTemplate.find("from General");
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}

	@Override
	public General getGeneral(int gid) {
		General general =hibernateTemplate.get(General.class, gid);
		return general;
	}

	//根据输入的条件模糊查询(遍历招生简章表)
	public List<General> listLikeGeneral(String gtitle, int page, int row) {
		List<General> listLike = new ArrayList<General>();
		session = hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from General g where 1=1";
			if(gtitle==null || gtitle==""){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				sql +=" and g.gtitle like '%"+gtitle+"%'";
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}


}
