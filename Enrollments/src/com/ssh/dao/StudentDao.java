package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Student;

public interface StudentDao {
	boolean add(Student student); //保存(添加)学生信息
	boolean getPhone(String sphone); //判断电话号码是否存在
	boolean getSnumber(String idcard); //判断身份证号码是否存在
	int getTrainCount(); //返回学生表中艺考培训的一共有多少条数据
	int getEducationCount(); //返回学生表中教育考试的一共有多少条数据
	List<Student> getStudentList(); //获取到Student表中所有数据
	List<Student> getStudentLists(); //获取到Student表中所有数据
	boolean delete(Integer id);  //根据id删除学生信息
	Student getStudentById(Integer id); //根据id查询学生信息
	boolean update(Student student); //修改学生信息
	int getCountTeacher(int id);//通过老师id去查询学生
	int getCountStype(String type);//通过学生报名类型查询人数
	List<Student> listLikeTrain(Student student,int page,int row); //根据条件查询出所有符合的艺考考生
	List<Student> listLikeEducation(Student student,int page,int row); //根据条件查询出所有符合的成考考生
	List<Student> getTeacherList(int id);//遍历老师id去查询学生
	List<Student> getStuStype(); //通过缴费状态查找
	int getStypeTime(String stype,String sdate); //通过类型和时间查找
	int getIdByName(String name); //根据学生姓名获取id
	List<Student> getStudentList(int id);//遍历老师id去查询学生
 }
