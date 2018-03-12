package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Department;


public interface DepartmentDao {
	public abstract Department  getDepartment(int id);//得到部门表对象
	public abstract boolean SaveDepartment(Department department); //保存部门表对象
	public abstract boolean UpdatDepartment(Department department);//修改部门表
	public abstract boolean DeleteDepartment(int id);//删除部门表
	public List<Department> getDepartmentList();//遍历部门表
	public abstract int getAllCount();//得到一共多少条数据
	//根据输入的条件模糊查询(遍历部门表)
	List<Department> listLikeDepartment(Department department,int page,int row);
}
