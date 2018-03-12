package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Logf;
import com.ssh.pojo.Teacher;

public interface TeacherDao {
	boolean getPhone(String tphone);//判断电话号码是否存在
	boolean getPassword(String tpwd,String tphone);//判断密码输入是否正确
	boolean updatePasswordById(String password,int tid);//根据id修改Teacher对象密码
	Teacher getTeacher(String tphone);//根据电话号码去得到Teacher对象
	public abstract Teacher  getTeacher(int id);//根据id得到Teacher对象
	public abstract boolean SaveTeacher(Teacher teacher); //保存Teacher对象
	public abstract boolean UpdateTeacher(Teacher teacher);//修改Teacher
	public abstract boolean DeleteTeacher(int id);//删除Teacher
	public List<Teacher> getTeacherList();//遍历Teacher
	public abstract int getAllCount();//得到一共多少条数据
	int getIdByName(String tname); //根据老师姓名获取到id
	List<Teacher> listLikeTeacher(Teacher teacher,int page,int row); //遍历教师表(模糊查询)
}
