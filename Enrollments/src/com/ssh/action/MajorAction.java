package com.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Academy;
import com.ssh.pojo.Major;
import com.ssh.service.AcademySerice;
import com.ssh.service.MajorService;

//专业表
@SuppressWarnings("serial")
public class MajorAction extends ActionSupport{
	private Major major;
	private MajorService majorService;
	private AcademySerice academyService;
	private String data;
	private List<Academy> list;
	
	//遍历专业表中所有信息
	public void getMajorList(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		List<Major> listmajor = majorService.getMajorList();
		List<Major> lm = new ArrayList<Major>();
		Academy academy = new Academy(); 
		for(int i=0;i<listmajor.size();i++){
			major = listmajor.get(i);
			Major mr = new Major();
			mr.setMrid(major.getMrid());
			mr.setMrname(major.getMrname());
			mr.setAcademyid(major.getAcademyid());
			academy = academyService.getAcademyById(major.getAcademyid());
			mr.setAname(academy.getAname());
			lm.add(mr);
		}
		int count = majorService.getMajorCount();
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("total",count);
		m.put("rows",lm);
		String jsonmap = JSON.toJSONString(m);
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(jsonmap);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加专业信息
	public String addMajor(){
		boolean isAdd = majorService.add(major);
		Map<String,String> map = new HashMap<>();
		if(isAdd){
			map.put("add", "true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//删除专业信息
	public String deleteMajor(){
		boolean isDelete = majorService.delete(major.getMrid());
		Map<String,String> map = new HashMap<>();
		if(isDelete){
			map.put("delete", "true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//根据id获取专业信息
	public void getOneMajor(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		Major ma = majorService.getMajorById(major.getMrid());
		Major mr = new Major();
		mr.setMrid(ma.getMrid());
		mr.setMrname(ma.getMrname());
		mr.setAcademyid(ma.getAcademyid());
		Academy academy = academyService.getAcademyById(ma.getAcademyid());
		mr.setAname(academy.getAname());
		String mapJsons = JSON.toJSONString(mr);
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(mapJsons);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改专业信息
	public String Major(){
		boolean isUpdate = majorService.update(major);
		Map<String,String> map = new HashMap<>();
		if(isUpdate){
			map.put("update", "true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//查询院校表中的所有信息
	public String getAllSchool(){
		list = academyService.listAcademy();
		return SUCCESS;
	}
	
	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
	public MajorService getMajorService() {
		return majorService;
	}
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

	public AcademySerice getAcademyService() {
		return academyService;
	}

	public void setAcademyService(AcademySerice academyService) {
		this.academyService = academyService;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Academy> getList() {
		return list;
	}

	public void setList(List<Academy> list) {
		this.list = list;
	}
	
}
