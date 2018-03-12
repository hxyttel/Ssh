package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.JoinworkDao;
import com.ssh.pojo.Joinwork;

public class JoinworkDaoImpl implements JoinworkDao {
	private HibernateTemplate hibernateTemplate;
	private Session session = null;
	@Override
	//增加
	public boolean addJoinwork(Joinwork joinwork) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(joinwork);
			isSave = true;
		}catch(Exception e){
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	//删除
	public boolean deleteJoinwork(Integer id) {
		boolean isSave = false;
		try{
			Joinwork joinwork = hibernateTemplate.get(Joinwork.class, id);
			hibernateTemplate.delete(joinwork);
			isSave = true;
		}catch(Exception e){
			isSave = false;
		}
		return isSave;
	}

	@Override
	public Joinwork getJoinwork(int jwid) {
		Joinwork joinwork =hibernateTemplate.get(Joinwork.class, jwid);
		return joinwork;
	}

	@Override
	public boolean updateJoinwork(Joinwork joinwork) {
		boolean isSave = false;
		try{
			hibernateTemplate.update(joinwork);
			isSave = true;
		}catch(Exception e){
			isSave = false;
		}
		return isSave;
	}

	@Override
	public List<Joinwork> selectJoinwork() {
		List<Joinwork> list = hibernateTemplate.find("from Joinwork");
		return list;	
	}

	@Override
	public int getAllCount() {
		int count = 0;
		List<Joinwork> list = hibernateTemplate.find("from Joinwork");
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//模糊遍历查询
	public List<Joinwork> listLikeJoinwork(String title, Integer page, Integer row) {
		List<Joinwork> listLike = null;
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Joinwork j where 1=1 ";
			if(title==null || "".equals(title)){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else{
				sql +=" and j.jwtitle like '%"+title+"%'";
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}

}
