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

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Logf;
import com.ssh.service.LogfService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/")
public class LogfAction extends ActionSupport{
	private LogfService logfService;
	private Logf logf;
	private List<Logf> logfList;
	private String data;
	private int id;
	
	//遍历文件
	@Action(value="GetLogfList")
	public  void getLogfLists() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		int count = logfService.getAllCount();
		logfList =logfService.getLogfList();
		map.put("total",count);
		map.put("rows",logfList);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//遍历文件(模糊查询)
	@Action(value="likeLogf")
	public  void getLikeLogfList() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		Map<String,Object> map = new HashMap<String,Object>();
		logfList =logfService.listLikeLogf(logf,page,row);
		int count = logfList.size();
		map.put("total",count);
		map.put("rows",logfList);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//删除文件
	public String deletelogf(){
		Map<String,Object> map = new HashMap<String, Object>();
		
		boolean isDelete = logfService.DeleteLogf(id);
		if(isDelete){
			map.put("msg","true");
			data = JSON.toJSONString(map);
			return SUCCESS;
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
		
		public LogfService getLogfService() {
			return logfService;
		}

		public void setLogfService(LogfService logfService) {
			this.logfService = logfService;
		}
		public Logf getLogf() {
			return logf;
		}
		public void setLogf(Logf logf) {
			this.logf = logf;
		}
		public List<Logf> getLogfList() {
			return logfList;
		}
		public void setLogfList(List<Logf> logfList) {
			this.logfList = logfList;
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
		
		
}
