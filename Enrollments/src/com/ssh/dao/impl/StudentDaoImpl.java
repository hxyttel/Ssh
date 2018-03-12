package com.ssh.dao.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.StudentDao;
import com.ssh.pojo.Student;
import com.ssh.pojo.Teacher;

public class StudentDaoImpl implements StudentDao{
    private HibernateTemplate hibernateTemplate;
	private Session session = null;
    
    //判断身份证号码是否重复
	@SuppressWarnings("unchecked")
	public boolean getSnumber(String idcard) {
		boolean isExist= false;
		List<Student> list = hibernateTemplate.find("from Student s where s.snumber=?",idcard);
		if(list.size()==0){
			isExist = false;
		}else{
			isExist = true;
		}
		return isExist;
	}
    
    //保存(添加)学生信息
	public boolean add(Student student) {
		boolean isSave= false;
		try {
			List<String> list = hibernateTemplate.find("select max(stuno) from Student");
			if(list.get(0)==null){
				student.setStuno("20170301");
			}else{
				Integer stuno = Integer.parseInt(list.get(0))+1;
				student.setStuno(Integer.toString(stuno));
			}
			hibernateTemplate.save(student);
			isSave = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSave = false;
		}
		return isSave;
	}
	//判断电话号码是否存在
	public boolean getPhone(String sphone) {
		boolean isExist= false;
		List<Student> list = hibernateTemplate.find("from Student s where s.sphone=?", sphone);
		if(list.size()==0){
			isExist = false;
		}else{
			isExist = true;
		}
		return isExist;
	}
	
	//返回学生表中艺考培训的一共有多少条数据
	public int getTrainCount() {
		int count = 0;
		try{
			List<Student> listAll = hibernateTemplate.find("from Student");
			List<Student> listEducation = hibernateTemplate.find("from Student stu where stu.snumber!=''");
			count = listAll.size()-listEducation.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	//返回学生表中教育考试的一共有多少条数据
	public int getEducationCount() {
		int count = 0;
		try{
			List<Student> listEducation = hibernateTemplate.find("from Student stu where stu.snumber!=''");
			count = listEducation.size();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	//获取Student表中的所有数据
	public List<Student> getStudentList() {
		List<Student> list = new ArrayList<Student>();
		try{
			list = hibernateTemplate.find("from Student");
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	//获取Student表中的所有数据
	public List<Student> getStudentLists() {
		List<Student> lists = new ArrayList<Student>();
		try{
			lists = hibernateTemplate.find("from Student");
		}catch(Exception e){
			e.printStackTrace();
		}
		return lists;
	}
	
	//根据id删除学生信息
	public boolean delete(Integer id){
		boolean isDelete = false;
		try{
			Student student = hibernateTemplate.get(Student.class, id);
			hibernateTemplate.delete(student);
			isDelete = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isDelete;
	}
	//根据id查询学生信息
	public Student getStudentById(Integer id){
		Student student = new Student();
		try{
			student = hibernateTemplate.get(Student.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return student;
	}
	//修改学生信息
	public boolean update(Student student){
		boolean isUpdate = false;
		try{
			hibernateTemplate.update(student);
			isUpdate = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isUpdate;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//通过老师id得到学生人数
	public int getCountTeacher(int id) {
		int count = 0;
		List<Student> list = hibernateTemplate.find("from Student stu where stu.teacherid=?",id);
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}

	//根据学生报名类型查询学生人数
	public int getCountStype(String type) {
		int count = 0;
		try{
			List<Student> list = hibernateTemplate.find("from Student stu where stu.stype=?",type);
			if(list!=null&&list.size()>0){
				count = list.size();
			}
		}catch(Exception e){
			count = 0;
		}
		return count;
	}

	//根据条件查询出所有符合的艺考考生
	public List<Student> listLikeTrain(Student student, int page, int row) {
		String stuno = student.getStuno();
		String sname = student.getSname();
		String sphone = student.getSphone();
		int tid = student.getTeacherid();
		String stype = student.getStype();
		List<Student> listLike = new ArrayList<Student>();
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Student s where 1=1";
			if(stuno==null && sname==null && sphone==null && stype==null && tid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else if("".equals(stuno) && "".equals(sname) && "".equals(sphone) && "".equals(stype) && tid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else{
				if(stuno!=null && stuno!=""){
					sql +=" and s.stuno like '%"+stuno+"%'";
				}
				if(sname!=null && sname!=""){
					sql +=" and s.sname like '%"+sname+"%'";
				}
				if(sphone!=null && sphone!=""){
					sql +=" and s.sphone like '%"+sphone+"%'";
				}
				if(stype!=null && stype!=""){
					sql +=" and s.stype like '%"+stype+"%'";
				}
				if(tid!=0){
					sql +=" and s.teacherid like '%"+tid+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}

	//根据条件查询出所有符合的成考考生
	public List<Student> listLikeEducation(Student student, int page, int row) {
		String stuno = student.getStuno();
		String sname = student.getSname();
		String sphone = student.getSphone();
		int tid = student.getTeacherid();
		String stype = student.getStype();
		String snumber = student.getSnumber();
		String sgradations = student.getSgradations();
		String system = student.getSsystem();
		int aid =  student.getAcademyid();
		List<Student> listLike = new ArrayList<Student>();
		session=hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Student s where 1=1";
			if(stuno==null && sname==null && sphone==null && stype==null && tid==0 && snumber==null && sgradations==null && system==null && aid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else if("".equals(stuno) && "".equals(sname) && "".equals(sphone) && "".equals(stype) && tid==0 && "".equals(snumber) && "".equals(sgradations) && aid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				if(stuno!="" && stuno!=null){
					sql +=" and s.stuno like '%"+stuno+"%'";
				}
				if(sname!="" && sname!=null){
					sql +=" and s.sname like '%"+sname+"%'";
				}
				if(sphone!="" && sphone!=null){
					sql +=" and s.sphone like '%"+sphone+"%'";
				}
				if(stype!="" && stype!=null){
					sql +=" and s.stype like '%"+stype+"%'";
				}
				if(tid!=0){
					sql +=" and s.teacherid like '%"+tid+"%'";
				}
				if(snumber!="" && snumber!=null){
					sql +=" and s.snumber like '%"+snumber+"%'";
				}
				if(sgradations!="" && sgradations!=null){
					sql +=" and s.sgradations like '%"+sgradations+"%'";
				}
				if(system!="" && system!=null){
					sql +=" and s.ssystem like '%"+system+"%'";
				}
				if(aid!=0){
					sql +=" and s.academyid like '%"+aid+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}

	@Override
	public List<Student> getTeacherList(int id) {
		List<Student> list = hibernateTemplate.find("from Student stu  where   stu.festate =? and stu.teacherid=?","未缴费",id);
		return list;
	}

	//通过缴费状态查找
	public List<Student> getStuStype() {
		List<Student> list = hibernateTemplate.find(" from Student stu where stu.festate =?","未缴费");
		return list;
	}

	@Override
	public int getStypeTime(String stype,String sdate) {
		int count = 0;
		List<Student> list =hibernateTemplate.find("from Student stu where stu.stype=? and stu.sdate like '%"+sdate+"%'",stype);
		if(list!=null&&list.size()>0){
			count = list.size();
		}
		return count;
	}

	//根据学生姓名获取id
	public int getIdByName(String name) {
		int id = 0;
		try{
			List<Student> list= hibernateTemplate.find("from Student s where s.sname like '%"+name+"%'");
			Student student = list.get(0);
			id = student.getSid();
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	//遍历老师id去查询学生
	public List<Student> getStudentList(int id) {
		List<Student> list = hibernateTemplate.find("from Student stu  where stu.teacherid=?",id);
		return list;
	}

}
