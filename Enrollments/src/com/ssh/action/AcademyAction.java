package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Academy;
import com.ssh.pojo.Contact;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Major;
import com.ssh.service.AcademySerice;
import com.ssh.service.ContactService;
import com.ssh.service.IndexContentService;

@SuppressWarnings("serial")
public class AcademyAction extends ActionSupport{
	private AcademySerice academyService;
	private IndexContentService indexcontentService;
	private ContactService contactService;
	private List<Indexcontent> listContent;
	private List<Contact> listContact;
	private Academy academy;
	private String data;
	private Integer id;
	private Integer aid;
	private List<Academy> list;
	private List<Major> lists;
	private Major major;

	Map<String,Object> map = new HashMap<String, Object>();
	
	//保存专业管理表信息
	public String saveAcademy(){
		boolean isSave = academyService.save(academy);
		if(isSave){
			map.put("msg", "保存成功!");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//根据id获取具体哪个院校信息
	public void getOneAcademy() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		Academy academy = academyService.getAcademyById(id);
		Academy academys = new Academy();
		academys.setAid(academy.getAid());
		academys.setAname(academy.getAname());
		academys.setAmajor(academy.getAmajor());
		String mapJson = JSON.toJSONString(academys);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//查询的所有学校(并且得到联系电话和尾部内容)
	public String getSchool(){
		list = academyService.selectAcademy();
		listContent = indexcontentService.getIndexcontentList(); 
		listContact = contactService.getContactList();
		return SUCCESS;
	}
	
	//查询学校对应的所有专业
	public void getMajor() throws Exception {
		lists = academyService.selectMajor(aid);
		List<Major> li = new ArrayList<Major>();
		for(int i=0;i<lists.size();i++){
			Major ma = new Major();
			major = lists.get(i);
			ma.setMrid(major.getMrid());
			ma.setMrname(major.getMrname());
			li.add(ma);
		}
		String strJson = JSON.toJSONString(li);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(strJson);
		out.flush();
		out.close();
	}
	//修改具体哪个院校
	public String update(){
		boolean isUpdate = academyService.update(academy);
		if(isUpdate){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//根据id删除信息
	public String delete(){
		boolean isDelete = academyService.delete(id);
		if(isDelete){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//获取(遍历)专业表中所有信息
	public void getListAcademy(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Academy> list=academyService.listAcademy();
		List<Academy> listAcademy = new ArrayList<Academy>();
		for(int i=0;i<list.size();i++){
			academy = list.get(i);
			Academy academys = new Academy();
			academys.setAid(academy.getAid());
			academys.setAname(academy.getAname());
			academys.setAmajor(academy.getAmajor());
			listAcademy.add(academys);
		}
		int count = academyService.getAcademyCount();
		Map<String,Object> map = new HashMap<>();
		map.put("total",count);
		map.put("rows",listAcademy);
		String mapJson=JSON.toJSONString(map);
		try {
			PrintWriter out = resp.getWriter();
			out.print(mapJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//根据输入条件模糊查询院校
	public void getlikeAcademy(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		List<Academy> list=academyService.listLikeAcademy(academy,page,row);
		List<Academy> listAcademy = new ArrayList<Academy>();
		for(int i=0;i<list.size();i++){
			academy = list.get(i);
			Academy academys = new Academy();
			academys.setAid(academy.getAid());
			academys.setAname(academy.getAname());
			academys.setAmajor(academy.getAmajor());
			listAcademy.add(academys);
		}
		int count = list.size();
		Map<String,Object> map = new HashMap<>();
		map.put("total",count);
		map.put("rows",listAcademy);
		String mapJson=JSON.toJSONString(map);
		try {
			PrintWriter out = resp.getWriter();
			out.print(mapJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AcademySerice getAcademyService() {
		return academyService;
	}
	public void setAcademyService(AcademySerice academyService) {
		this.academyService = academyService;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getDate() {
		return data;
	}

	public void setDate(String date) {
		this.data = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public List<Academy> getList() {
		return list;
	}

	public void setList(List<Academy> list) {
		this.list = list;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public List<Major> getLists() {
		return lists;
	}

	public void setLists(List<Major> lists) {
		this.lists = lists;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public IndexContentService getIndexcontentService() {
		return indexcontentService;
	}

	public void setIndexcontentService(IndexContentService indexcontentService) {
		this.indexcontentService = indexcontentService;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public List<Indexcontent> getListContent() {
		return listContent;
	}

	public void setListContent(List<Indexcontent> listContent) {
		this.listContent = listContent;
	}

	public List<Contact> getListContact() {
		return listContact;
	}

	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
	}
	
}
