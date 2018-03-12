package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Contact;
import com.ssh.service.ContactService;
@SuppressWarnings("serial")

@ParentPackage("json-default")
@Namespace("/")
public class ContactAction extends ActionSupport{
	private ContactService contactService;
	private Contact contact;
	Map<String,Object> map = new HashMap<String,Object>();
	private String data;
	private int id;
	private List<Contact> listType;
	
	//添加联系表
	public String AddContact(){
		boolean isSave = contactService.SaveContact(contact);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//遍历联系表
	public void ListContact() throws IOException{
		HttpServletResponse resp =ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = contactService.getAllCount();
		List<Contact> lis = contactService.getContactList();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
	}
	//删除联系表信息
	public String deleteContact(){
		boolean isSave = contactService.DeleteContact(id);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
				
	}
	//根据id获取联系表信息
	public void findContact() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		Contact contact = contactService.getContact(id);
		String MapJson = JSON.toJSONString(contact);
		PrintWriter out = resp.getWriter();
		out.print(MapJson);
		out.flush();
		out.close();
	}
	//修改联系表
	public String updateContact(){
		boolean isSave = contactService.UpdatContact(contact);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//得到联系表中的所属类型
	@Action(value="contact",results={@Result(name="get_success",location="/backstage/Contact.jsp")})
	public String getContactType(){
		listType = contactService.getContactList();
		return "get_success";
	}
	
	//根据输入条件模糊查询联系表
	public void getlikeContact() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp =ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		
		List<Contact> lis = contactService.listLikeContact(contact,page,row);
		int count = lis.size();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
	}
	
	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Contact> getListType() {
		return listType;
	}
	public void setListType(List<Contact> listType) {
		this.listType = listType;
	}
	
}
