package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.FianceDao;
import com.ssh.pojo.Finance;

public class FianceDaoImpl implements FianceDao{
	
	private HibernateTemplate hibernateTemplate;
	private Session session = null;

	@Override
	public Finance getFinance(int id) {
	Finance finance = hibernateTemplate.get(Finance.class, id);
		return finance;
	}

	@Override
	public boolean SaveFinance(Finance finance) {
		boolean isSave = false;
		try {
			hibernateTemplate.save(finance);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean UpdateFinance(Finance finance) {
		boolean isSave = false;
		try {
			hibernateTemplate.update(finance);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean DeleteFinance(int id) {
		boolean isSave = false;
		try {
			Finance finance=hibernateTemplate.get(Finance.class, id);
			hibernateTemplate.delete(finance);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public List<Finance> getFinanceList() {
		List<Finance> list= hibernateTemplate.find("from Finance");
		return list;
	}

	@Override
	public int getAllCount() {
		int count=0;
		List<Finance> list = hibernateTemplate.find("from Finance");
		if(list!=null&&list.size()>0){
			count = list.size();
            return count;
        }
		return count;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//根据输入的条件模糊查询(遍历缴费表)
	public List<Finance> listLikeFinance(Finance fiance, int page, int row) {
		List<Finance> listLike= new ArrayList<Finance>();
		String feway = fiance.getFeway();
		String sfestate = fiance.getSfestate();
		int sid = fiance.getStudentid();
		session = hibernateTemplate.getSessionFactory().openSession();
		try{
			String sql = "from Finance f where 1=1";
			if(feway==null && sfestate==null && sid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else if("".equals(feway) && "".equals(sfestate) && sid==0){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				if(feway!=null && feway!=""){
					sql +=" and f.feway like '%"+feway+"%'";
				}
				if(sfestate!=null && sfestate!=""){
					sql +=" and f.sfestate like '%"+sfestate+"%'";
				}
				if(sid!=0){
					sql +=" and f.studentid like '%"+sid+"%'";
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}

	//根据学生id查询缴费情况
	public Finance getFinanceBySid(int sid) {
		Finance finance = null;
		try{
			List<Finance> list= hibernateTemplate.find("from Finance f where f.studentid="+sid);
			if(list.size()>0){
				finance = list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return finance;
	}
	
}
