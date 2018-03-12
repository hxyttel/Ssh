package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.ContactDao;
import com.ssh.pojo.Contact;
import com.ssh.service.ContactService;

public class ContactServiceImpl implements ContactService{

	private ContactDao contactDao;
	public Contact getContact(int id) {
		return contactDao.getContact(id);
	}

	public boolean SaveContact(Contact contact) {
		return contactDao.SaveContact(contact);
	}

	public boolean UpdatContact(Contact contact) {
		return contactDao.UpdatContact(contact);
	}

	public boolean DeleteContact(int id) {
		return contactDao.DeleteContact(id);
	}

	public List<Contact> getContactList() {
		return contactDao.getContactList();
	}

	public int getAllCount() {
		return contactDao.getAllCount();
	}

	public ContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	//根据条件模糊查询联系表
	public List<Contact> listLikeContact(Contact contact,int page,int row) {
		return contactDao.listLikeContact(contact,page,row);
	}

}
