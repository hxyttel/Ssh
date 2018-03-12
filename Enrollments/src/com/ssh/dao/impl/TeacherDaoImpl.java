package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.TeacherDao;
import com.ssh.pojo.Logf;
import com.ssh.pojo.Teacher;

public class TeacherDaoImpl implements TeacherDao{
	public HibernateTemplate hibernateTemplate;
	private Session session = null;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//判断电话号码是否存在
	@Override
	public boolean getPhone(String tphone) {
		boolean isExist= false;
		List<Teacher> list = hibernateTemplate.find("from Teacher t where t.tphone=?", tphone);
		if(list.size()==0){
			isExist = false;
		}else{
			isExist = true;
		}
		return isExist;
	}
	
	//判断密码输入是否正确
	@Override
	public boolean getPassword(String tpwd,String tphone) {
		boolean isExist= false;
		List<Teacher> list = hibernateTemplate.find("from Teacher t where t.tpassword=? and t.tphone=?", tpwd,tphone);
		if(list.size()==0){
			isExist = false;
		}else{
			isExist = true;
		}
		return isExist;
	}

	@Override
	public Teacher getTeacher(String tphone) {
		List<Teacher> list= hibernateTemplate.find("from Teacher t where t.tphone=?",tphone);
		Teacher teacher = list.get(0);
		return teacher;
	}

	//根据id修改Teacher对象密码
	public boolean updatePasswordById(String password,int tid) {
		Teacher teacher = hibernateTemplate.get(Teacher.class, tid);
		teacher.setTpassword(password);
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(teacher);
			isUpdate = true;
		}catch(Exception e){
			e.printStackTrace();
			isUpdate = false;
		}
		return isUpdate;
	}

	@Override
	public Teacher getTeacher(int id) {
		Teacher teacher = hibernateTemplate.get(Teacher.class, id);
		return teacher;
	}

	@Override
	public boolean SaveTeacher(Teacher teacher) {
		boolean isSave = false;
		try{
			hibernateTemplate.save(teacher);
			isSave = true;
		}catch(Exception e){
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean UpdateTeacher(Teacher teacher) {
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(teacher);
			isUpdate = true;
		}catch(Exception e){
			isUpdate = false;
			e.printStackTrace();
		}
		return isUpdate;
	}

	@Override
	public boolean DeleteTeacher(int id) {
		boolean isDelete = false;
		try{
			Teacher teacher = hibernateTemplate.get(Teacher.class, id);
			hibernateTemplate.delete(teacher);
			isDelete = true;
		}catch(Exception e){
			isDelete = false;
			e.printStackTrace();
		}
		return isDelete;
	}

	@Override
	public List<Teacher> getTeacherList() {
		List<Teacher> list = new ArrayList<Teacher>();
		try{
			 list = hibernateTemplate.find("from Teacher");
			 return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
	}

	@Override
	public int getAllCount() {
		int count = 0;
		List<Teacher> list = hibernateTemplate.find("from Teacher");
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}
	
	
	//根据老师姓名获取到id
	public int getIdByName(String tname) {
		int id = 0;
		try{
			List<Teacher> list= hibernateTemplate.find("from Teacher t where t.tname like '%"+tname+"%'");
			System.out.println(tname);
			Teacher teacher = list.get(0);
			id = teacher.getTid();
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	//遍历教师表(模糊查询)
	public List<Teacher> listLikeTeacher(Teacher teacher, int page, int row) {
		String name = teacher.getTname();
		String phone = teacher.getTphone();
		String sex = teacher.getTsex();
		Integer age = teacher.getTage();
		Integer rid = teacher.getRoleid();
		String status = teacher.getTstatus();
		Integer did = teacher.getDepartmentid();
		List<Teacher> listLike = null;
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Teacher t where 1=1 ";
			if(name==null && phone==null && sex==null && rid==null && status==null && age==null && did==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else if("".equals(name) && "".equals(phone) &&"".equals(sex) &&"".equals(status) && age==null && did==null && rid==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				if(name!=null || name!=""){
					sql +=" and t.tname like '%"+name+"%'";
				}
				if(phone!=null || phone!=""){
					sql +=" and t.tphone like '%"+phone+"%'";
				}
				if(sex!=null || sex!=""){
					sql +=" and t.tsex like '%"+sex+"%'";
				}
				if(age!=null){
					sql +=" and t.tage like '%"+age+"%'";
				}
				if(did!=null){
					sql +=" and t.departmentid like '%"+did+"%'";
				}
				if(rid!=null){
					sql +=" and t.roleid like '%"+rid+"%'";
				}
				if(status!=null || status!=""){
					sql +=" and t.tstatus like '%"+status+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}

}
