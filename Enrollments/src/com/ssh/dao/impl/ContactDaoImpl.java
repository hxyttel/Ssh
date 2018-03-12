package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.ContactDao;
import com.ssh.pojo.Contact;

public class ContactDaoImpl implements ContactDao{
	
	private HibernateTemplate hibernateTemplate;
	private Session session = null;
	
	public Contact getContact(int id) {
		Contact contact = hibernateTemplate.get(Contact.class, id);
		return contact;
	}

	public boolean SaveContact(Contact contact) {
		boolean isSave = false;
		try {
			hibernateTemplate.save(contact);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public boolean UpdatContact(Contact contact) {
		boolean isSave = false;
		try{
		hibernateTemplate.update(contact);
		isSave=true;
		}
		catch (Exception e) {
			isSave= false;
			e.printStackTrace();
		}
		return isSave;
	}

	public boolean DeleteContact(int id) {
		boolean isSave = false;
		try{
		Contact contact = hibernateTemplate.get(Contact.class, id);
		hibernateTemplate.delete(contact);
		isSave=true;
		}
		catch (Exception e) {
			isSave= false;
			e.printStackTrace();
		}
		return isSave;
	}

	public List<Contact> getContactList() {
		List<Contact> list = hibernateTemplate.find("from Contact");	
		return list;
	}

	public int getAllCount() {
		int count = 0;
		List<Contact> list = hibernateTemplate.find("from Contact");
		if(list.size()>0&&list!=null){
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

	//根据条件模糊查询联系表
	public List<Contact> listLikeContact(Contact contact,int page,int row) {
		String qqnumber = contact.getCqqnumber();
		String phone = contact.getCphone();
		String people = contact.getCpeople();
		String type = contact.getCtype();
		session=hibernateTemplate.getSessionFactory().openSession();
		List<Contact> listLike = null;
		try{
			String sql = "from Contact c where 1=1";
			if(qqnumber==null && phone==null && people==null && type==null){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else if("".equals(qqnumber) && "".equals(phone) && "".equals(people) && "".equals(type)){
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}else{
				if(qqnumber!=null || qqnumber!=""){
					sql +=" and c.cqqnumber like '%"+qqnumber+"%'";
				}
				if(phone!=null || phone!=""){
					sql +=" and c.cphone like '%"+phone+"%'";
				}
				if(people!=null || people!=""){
					sql +=" and c.cpeople like '%"+people+"%'";
				}
				if(type!=null || type!=""){
					sql +=" and c.ctype like '%"+type+"%'";;
				}
				listLike=session.createQuery(sql).setFirstResult((page-1)*row).list();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listLike;
	}
	
}
