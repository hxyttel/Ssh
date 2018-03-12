package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Notice;
import com.ssh.service.NoticeService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/")
public class NoticeAction extends ActionSupport{
	private NoticeService noticeService;
	private Notice notice;
	private String data;
	private int id;
	private List<Notice> list;
	Map<String,Object> map = new HashMap<String, Object>();

	//遍历公告表
	public void listNotice() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int count=noticeService.getAllCount();
		List<Notice> lis = noticeService.getNoticeList();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//遍历公告表(模糊查询)
	@Action(value="likeNotices")
	public void likeNotice() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		List<Notice> lis = noticeService.listLikeNotice(notice,page,row);
		int count=lis.size();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//增加公告表
	public String addNotice(){
		if(notice.getNflag().equals("a")){
			notice.setNflag("公告");
		}
		else{
			notice.setNflag("简章");
		}
		//定义时间
		Date date = new Date();
		SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		notice.setNdate(fmat.format(date));
		boolean isSave = noticeService.SaveNotice(notice);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//查找公告对象
	public void findNotice() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		Notice notice =noticeService.getNotice(id);
		String mapJson = JSON.toJSONString(notice);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//修改公告
	public String updateNotice(){
		if(notice.getNflag().equals("a")){
			notice.setNflag("公告");
		}
		else{
			notice.setNflag("简章");
		}
		boolean isSave = noticeService.UpdateNotice(notice);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//删除公告
	public String deleteNotice(){
		boolean isSave = noticeService.DeleteNotice(id);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
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
	public List<Notice> getList() {
		return list;
	}
	public void setList(List<Notice> list) {
		this.list = list;
	}
	
	
}
