package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.HotmajorDao;
import com.ssh.pojo.Hotmajor;

public class HotmajorDaoImpl implements HotmajorDao{
	private HibernateTemplate hibernateTemplate;
	private Session session;

	@Override
	public Hotmajor getHotmajor(int id) {
		Hotmajor hotmajor = hibernateTemplate.get(Hotmajor.class, id);
		return hotmajor;
	}

	@Override
	public boolean SaveHotmajor(Hotmajor hotmajor) {
		boolean isSave = false;
		try {
			hibernateTemplate.save(hotmajor);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean UpdateHotmajor(Hotmajor hotmajor) {
		boolean isSave = false;
		try {
			hibernateTemplate.update(hotmajor);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean DeleteHotmajor(int id) {
		boolean isSave = false;
		try {
			Hotmajor hotmajor=hibernateTemplate.get(Hotmajor.class, id);
			hibernateTemplate.delete(hotmajor);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public List<Hotmajor> getHotmajorList() {
		List<Hotmajor> list= hibernateTemplate.find("from Hotmajor");
		return list;
	}

	@Override
	public int getAllCount() {
		int count=0;
		List<Hotmajor> list = hibernateTemplate.find("from Hotmajor");
		if(list!=null&&list.size()>0){
			count = list.size();
            return count;
        }
		return count;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//模糊查询遍历所有热门专业
	public List<Hotmajor> listLikeHotMajor(String htitle, int page, int row) {
		List<Hotmajor> listLike = new ArrayList<Hotmajor>();
		session = hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Hotmajor h where 1=1";
			if(htitle==null || htitle==""){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				sql +=" and h.htitle like '%"+htitle+"%'";
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}
	

}
