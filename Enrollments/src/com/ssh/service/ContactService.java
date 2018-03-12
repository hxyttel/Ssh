package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Contact;

public interface ContactService {
	public abstract Contact  getContact(int id);//得到联系表对象
	public abstract boolean SaveContact(Contact contact); //保存联系表对象
	public abstract boolean UpdatContact(Contact contact);//修改联系表
	public abstract boolean DeleteContact(int id);//删除联系表
	public List<Contact> getContactList();//遍历联系表
	public abstract int getAllCount();//得到一共多少条数据
	List<Contact> listLikeContact(Contact contact,int page,int row); //根据条件模糊查询联系表
	//int getLikeContactCount(Contact contact); //根据条件查询出有多少条数据
}
