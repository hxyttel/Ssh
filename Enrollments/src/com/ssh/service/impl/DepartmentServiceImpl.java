package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.DepartmentDao;
import com.ssh.pojo.Department;
import com.ssh.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentDao departmentDao;
	@Override
	public Department getDepartment(int id) {
		return departmentDao.getDepartment(id);
	}

	@Override
	public boolean SaveDepartment(Department department) {
		return departmentDao.SaveDepartment(department);
	}

	@Override
	public boolean UpdatDepartment(Department department) {
		return departmentDao.UpdatDepartment(department);
	}

	@Override
	public boolean DeleteDepartment(int id) {
		return departmentDao.DeleteDepartment(id);
	}

	@Override
	public List<Department> getDepartmentList() {
		return departmentDao.getDepartmentList();
	}

	@Override
	public int getAllCount() {
		return departmentDao.getAllCount();
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	//根据输入的条件模糊查询(遍历部门表)
	public List<Department> listLikeDepartment(Department department, int page, int row) {
		return departmentDao.listLikeDepartment(department, page, row);
	}
}
