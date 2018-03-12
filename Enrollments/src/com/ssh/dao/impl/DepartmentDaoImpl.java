package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.DepartmentDao;
import com.ssh.pojo.Department;

public class DepartmentDaoImpl implements DepartmentDao{
	
	private HibernateTemplate hibernateTemplate;
	private Session session = null;
	
	@Override
	public Department getDepartment(int id) {
		Department department = hibernateTemplate.get(Department.class, id);
		return department;
	}

	@Override
	public boolean SaveDepartment(Department department) {
		boolean isSave = false;
		try{
			/*List<String> list = hibernateTemplate.find("select max(dnumber) from Department");
			Integer dnumber = Integer.parseInt(list.get(0))+1;
			department.setDnumber(Integer.toString(dnumber));*/
			hibernateTemplate.save(department);
			isSave = true;
		}catch(Exception e){
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean UpdatDepartment(Department department) {
		boolean isUpde = false;
		try{
			hibernateTemplate.update(department);
			isUpde = true;
		}catch(Exception e){
			e.printStackTrace();
			isUpde = false;
		}
		return isUpde;
	}

	@Override
	public boolean DeleteDepartment(int id) {
		boolean isDelete = false;
		try{
			Department department = hibernateTemplate.get(Department.class, id);
			hibernateTemplate.delete(department);
			isDelete = true;
		}catch(Exception e){
			isDelete = false;
			e.printStackTrace();
		}
		return isDelete;
	}

	@Override
	public List<Department> getDepartmentList() {
		List<Department> list = hibernateTemplate.find("from Department");
		return list;
	}

	@Override
	public int getAllCount() {
		int count = 0;
		List<Department> list = hibernateTemplate.find("from Department");
		if(list.size()>0&&list!=null){
			count = list.size();
			return count;
		}
		return count;
	}
	
	//根据输入的条件模糊查询(遍历部门表)
	public List<Department> listLikeDepartment(Department department, int page, int row) {
		List<Department> listLike = null;
		String number = department.getDnumber();
		String name = department.getDname();
		String address = department.getDaddress();
		
		session=hibernateTemplate.getSessionFactory().openSession();
		
		try{
			String sql = "from Department d where 1=1";
			if(number==null && name==null && address==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else if("".equals(number) && "".equals(name) && "".equals(address)){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).setMaxResults(row).list();
			}else{
				if(number!=null && number!=""){
					sql +=" and d.dnumber like '%"+number+"%'";
				}
				if(name!=null && name!=""){
					sql +=" and d.dname like '%"+name+"%'";
				}
				if(address!=null && address!=""){
					sql +=" and d.daddress like '%"+address+"%'";
				}
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
