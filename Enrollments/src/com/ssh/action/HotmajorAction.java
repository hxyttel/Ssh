package com.ssh.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Hotmajor;
import com.ssh.service.HotmajorService;

public class HotmajorAction extends ActionSupport{
	private HotmajorService hotmajorService;
	private String data;
	private int id;
	private Hotmajor hotmajor;
	
	Map<String,Object> map= new HashMap<String, Object>();
	
	
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
	// 所以同时要提供myFileContentType,myFileFileName的set方法
	private File myFile; // 上传文件
	private String contentType;// 上传文件类型
	private String fileName; // 上传文件名
	private String imageFileName;
	private int hid;
	private String htitle;
	private String hcontent;
	
	public void setMyFileContentType(String contentType) {
		this.contentType =contentType;
	}
	
	public void setMyFileFileName(String fileName) {
		this.fileName =fileName;
	}
	
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	//添加热门专业
	public String addHotmajor(){
		Map<String,Object> map = new HashMap<String, Object>();
		imageFileName =fileName;
		File imageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + imageFileName);
		copy(myFile, imageFile);
		Hotmajor hotmajor = new Hotmajor();
		hotmajor.setHpicture("backstage/upload/"+fileName);
		hotmajor.setHcontent(hcontent);
		hotmajor.setHtitle(htitle);
		boolean isSave =hotmajorService.SaveHotmajor(hotmajor);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//遍历热门专业
	public void listHotmajor() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = hotmajorService.getAllCount();
		List<Hotmajor> lis=hotmajorService.getHotmajorList();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson=JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	
	//遍历热门专业(模糊查询)
	public void likeHotmajor() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		List<Hotmajor> lis =hotmajorService.listLikeHotMajor(htitle,page,row);
		int count = lis.size();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson=JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	
	//查找热门专业对象
	public void findHotmajor() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		hotmajor = hotmajorService.getHotmajor(id);
		String json = JSON.toJSONString(hotmajor);
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	//修改热门专业
	public String updateHotmajor(){
		Map<String,Object> map = new HashMap<String, Object>();
		imageFileName =fileName;
		File imageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + imageFileName);
		copy(myFile, imageFile);
		Hotmajor hot = new Hotmajor();
		hot.setHpicture("backstage/upload/"+fileName);
		hot.setHcontent(hcontent);
		hot.setHid(hid);
		hot.setHtitle(htitle);
		boolean isSave =hotmajorService.UpdateHotmajor(hot);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	
	//删除热门专业
	public String deleteHotmajor(){
		boolean isSave=hotmajorService.DeleteHotmajor(id);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
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

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public String getHtitle() {
		return htitle;
	}

	public void setHtitle(String htitle) {
		this.htitle = htitle;
	}

	public String getHcontent() {
		return hcontent;
	}

	public void setHcontent(String hcontent) {
		this.hcontent = hcontent;
	}

	public HotmajorService getHotmajorService() {
		return hotmajorService;
	}

	public void setHotmajorService(HotmajorService hotmajorService) {
		this.hotmajorService = hotmajorService;
	}

	public Hotmajor getHotmajor() {
		return hotmajor;
	}

	public void setHotmajor(Hotmajor hotmajor) {
		this.hotmajor = hotmajor;
	}
	

}
