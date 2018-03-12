package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.UploadDao;
import com.ssh.pojo.StudentFile;

public class UploadDaoImpl implements UploadDao{
	private HibernateTemplate hibernateTemplate;
	private Session session = null;
	@Override
	public StudentFile getStudentFile(int id) {
		StudentFile studentFile = hibernateTemplate.get(StudentFile.class, id);
		return studentFile;
	}

	@Override
	public boolean SaveStudentFile(StudentFile studentfile) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(studentfile);
			isSave = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean UpdateStudentFile(StudentFile studentfile) {
		boolean isSave = false;
		try{
			hibernateTemplate.update(studentfile);
			isSave = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean DeleteStudentFile(int id) {
		boolean isSave = false;
		try{
			StudentFile studentfile = hibernateTemplate.get(StudentFile.class, id);
			hibernateTemplate.delete(studentfile);
			isSave = true;
		}catch(Exception e){
			isSave = false;
		}
		return isSave;
	}

	@Override
	public List<StudentFile> getStudentFileList() {
		List<StudentFile> list = hibernateTemplate.find("from StudentFile");
		return list;
	}

	@Override
	public int getAllCount() {
		int count = 0;
		List<StudentFile> list = hibernateTemplate.find("from StudentFile");
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}
	//根据条件模糊查询上传文件表
	public List<StudentFile> listLikeStudentFile(int sid, int page, int row) {
		List<StudentFile> listLike = null;
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from StudentFile u where 1=1 ";
			if(sid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else{
				sql +=" and u.sid like '%"+sid+"%'";
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
