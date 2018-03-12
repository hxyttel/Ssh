package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Contact;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Teacher;
import com.ssh.pojo.WorkAssist;
import com.ssh.service.ContactService;
import com.ssh.service.IndexContentService;
import com.ssh.service.TeacherService;
import com.ssh.service.WorkAssistService;

@ParentPackage("json-default")
@Namespace("/")
public class WorkAssistAction extends ActionSupport{
  private WorkAssistService workAssistService;
  private WorkAssist workAssist;
  private int tid;
  private String data;
  private int id;
  private JSONObject result = new JSONObject();
  List<WorkAssist> list;
  private IndexContentService indexcontentService;
  private ContactService contactService;
  private List<Indexcontent> listContent;
  private List<Contact> listContact;
  private TeacherService teacherService;
  
  //查询
  @Action(value="selectAssist",results={@Result(type="json")})
  public String selectAssist() throws IOException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		List<WorkAssist> lis =workAssistService.selectWorkAssist(tid);
		List<WorkAssist> li  = new ArrayList<WorkAssist>();
		for(int i=0;i<lis.size();i++){
			workAssist = lis.get(i);
			WorkAssist wA = new WorkAssist();
			wA.setWa_id(workAssist.getWa_id());
			wA.setWa_title(workAssist.getWa_title());
			wA.setWa_content(workAssist.getWa_content());
			wA.setWa_createtime(workAssist.getWa_createtime());
			wA.setWa_state(workAssist.getWa_state());
			
			li.add(wA);
		}
 		map.put("total",li.size());
		map.put("row",li);
		map.put("data", li);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
		return null;
	}
	//增加工作助手
	@Action(value="addAssist",results= {@Result(name="success",type="json",params= {"root","result"})
										,@Result(name="defeated",type="json",params= {"root","result"})})
	public String addAssist() {
	    Date d = new Date();
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		workAssist.setWa_createtime(f.format(d));
		HttpServletRequest req=ServletActionContext.getRequest();
		String str=req.getParameter("tid");
		Integer tid = Integer.valueOf(str);
		workAssist.setTeacherid(tid);
		workAssist.setWa_state("0");
		if(workAssist.getWa_title()==null ||workAssist.getWa_title().equals("")||workAssist.getWa_content()==null||workAssist.getWa_content().equals("")) {
			result.put("msg", "增加失败!");
			return "defeated";
		}else {
			workAssistService.addWorkAssist(workAssist);
			result.put("msg", "增加成功!");
			return SUCCESS;
		}
	}
	//删除文件
	public String deleteAssist(){
		Map<String,Object> map = new HashMap<String, Object>();
		boolean isDelete = workAssistService.deleteWorkAssist(id);
		if(isDelete){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//工作助手得到尾部和侧边栏内容
	@Action(value="getWorkAssist",results={@Result(name="success",location="/workAssist.jsp")})
	public String getContant(){
		HttpServletRequest req = ServletActionContext.getRequest();
		listContent = indexcontentService.getIndexcontentList(); 
		listContact = contactService.getContactList();
		Teacher teacher = teacherService.getTeacher(tid);
		req.setAttribute("teachers", teacher);
		return SUCCESS;
	}
	//缴费查询得到尾部和侧边栏内容
	@Action(value="getFiance",results={@Result(name="success",location="/pay.jsp")})
	public String getContants(){
		HttpServletRequest req = ServletActionContext.getRequest();
		listContent = indexcontentService.getIndexcontentList(); 
		listContact = contactService.getContactList();
		Teacher teacher = teacherService.getTeacher(tid);
		req.setAttribute("teachers", teacher);
		return SUCCESS;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
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
	public WorkAssistService getWorkAssistService() {
		return workAssistService;
	}
	public void setWorkAssistService(WorkAssistService workAssistService) {
		this.workAssistService = workAssistService;
	}
	public WorkAssist getWorkAssist() {
		return workAssist;
	}
	public void setWorkAssist(WorkAssist workAssist) {
		this.workAssist = workAssist;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public List<WorkAssist> getList() {
		return list;
	}
	public void setList(List<WorkAssist> list) {
		this.list = list;
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
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
}
