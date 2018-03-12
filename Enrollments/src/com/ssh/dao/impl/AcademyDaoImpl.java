package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.AcademyDao;
import com.ssh.pojo.Academy;
import com.ssh.pojo.Major;
import com.ssh.pojo.Teacher;

public class AcademyDaoImpl implements AcademyDao {
	private HibernateTemplate hibernateTemplate;
	private Session session = null;

	//保存专业管理表信息
	public boolean save(Academy academy) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(academy);
			isSave = true;
		}catch(Exception e){
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	//根据id获取具体哪个院校信息
	public Academy getAcademyById(int id) {
		Academy academy = null;
		try{
			academy = hibernateTemplate.get(Academy.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return academy;
	}

	//根据id修改信息
	public boolean update(Academy academy) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(academy);
			isUpdate = true;
		}catch(Exception e){
			isUpdate = false;
			e.printStackTrace();
		}
		return isUpdate;
	}

	//根据id删除信息
	public boolean delete(int id) {
		boolean isDelete = false;
		try{
			Academy academy = hibernateTemplate.get(Academy.class, id);
			hibernateTemplate.delete(academy);
			isDelete = true;
		}catch(Exception e){
			isDelete = false;
			e.printStackTrace();
		}
		return isDelete;
	}

	//获取专业表中所有信息
	public List<Academy> listAcademy() {
		List<Academy> list = null;
		try{
			list = hibernateTemplate.find("from Academy");
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	//获取数据库表中总共有多少条数据
	public int getAcademyCount() {
		List<Academy> list =  hibernateTemplate.find("from Academy");
		return list.size();
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List<Academy> selectAcademy() {
		List<Academy> list = hibernateTemplate.find("from Academy");
		return list;
	}
	
	public List<Major> selectMajor(int aid) {
		List<Major> list = hibernateTemplate.find("from Major where academyid =?",aid);
		return list;
	}
	
	//根据条件模糊查询院校表
	public List<Academy> listLikeAcademy(Academy academy,int page,int row) {
		String name = academy.getAname();
		String major = academy.getAmajor();
		List<Academy> listLike = null;
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Academy a where 1=1 ";
			if(name==null && major==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else if("".equals(name) && "".equals(major)){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else{
				if(name!=null || name!=""){
					sql +=" and a.aname like '%"+name+"%'";
				}
				if(major!=null || major!=""){
					sql +=" and a.amajor like '%"+major+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}

	//根据院校名称获取到id
	public int getIdByAname(String aname) {
		int id = 0;
		try{
			List<Academy> list= hibernateTemplate.find("from Academy a where a.aname like '%"+aname+"%'");
			Academy academy = list.get(0);
			id = academy.getAid();
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

}
