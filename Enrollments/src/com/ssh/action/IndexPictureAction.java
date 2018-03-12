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
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Indexpicture;
import com.ssh.service.IndexContentService;
import com.ssh.service.IndexPictureService;




//轮播图action
public class IndexPictureAction extends ActionSupport{
	private IndexPictureService indexpictureService;
	private Indexpicture indexpicture;
	private String data;
	private List<Indexpicture> listPicture;
	private int id;
	private int pid;
	
	Map<String,Object> map= new HashMap<String, Object>();
	
	//上传图片
		private static final long serialVersionUID = 572146812454l;
		private static final int BUFFER_SIZE = 16 * 1024;
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
		// 所以同时要提供myFileContentType,myFileFileName的set方法
		private File myFile; // 上传文件
		private String contentType;// 上传文件类型
		private String fileName; // 上传文件名
		private String imageFileName;
		private String ptitle;
		private String pcontent;
		
		
		public void setMyFileContentType(String contentType) {
			this.contentType =contentType;
			System.out.println("文件类型 : " + contentType);
		}
		
		public void setMyFileFileName(String fileName) {
			this.fileName =fileName;
			System.out.println("文件名称 :" + fileName);
			
		}
		public void setMyFile(File myFile) {
			this.myFile = myFile;
		}

		public String getPtitle() {
			return ptitle;
		}

		public void setPtitle(String ptitle) {
			this.ptitle = ptitle;
		}

		public String getPcontent() {
			return pcontent;
		}

		public void setPcontent(String pcontent) {
			this.pcontent = pcontent;
		}

		public String getImageFileName() {
			return imageFileName;
		}
		public File getMyFile() {
			return myFile;
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
	
	//添加轮播图
	public String savePicture() throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		imageFileName =fileName;
		File imageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + imageFileName);
		copy(myFile, imageFile);
		Indexpicture indexpicture= new Indexpicture();
		indexpicture.setPpicture("backstage/upload/"+fileName);
		indexpicture.setPcontent(pcontent);
		indexpicture.setPtitle(ptitle);
		boolean isSave =indexpictureService.SaveIndexpicture(indexpicture);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//遍历轮播图
	public void listPicture() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int count = indexpictureService.getAllCount();
		List<Indexpicture> lis=indexpictureService.getIndexpictureList();
		map.put("total",count);
		map.put("rows",lis);
		String mapJson=JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
	}
	//查找轮播对象
	public void findPicture() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		indexpicture = indexpictureService.getIndexpicture(id);
		String json = JSON.toJSONString(indexpicture);
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
	//修改轮播图
	public String updatePicture(){
		Map<String,Object> map = new HashMap<String, Object>();
		imageFileName =fileName;
		File imageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + imageFileName);
		copy(myFile, imageFile);
		Indexpicture indexpicture= new Indexpicture();
		indexpicture.setPid(pid);
		indexpicture.setPpicture("backstage/upload/"+fileName);
		indexpicture.setPcontent(pcontent);
		indexpicture.setPtitle(ptitle);
		boolean isSave =indexpictureService.UpdateIndexpicture(indexpicture);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//删除轮播图
	public String deletePicture(){
		boolean isSave=indexpictureService.DeleteIndexpicture(id);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	public IndexPictureService getIndexpictureService() {
		return indexpictureService;
	}



	public void setIndexpictureService(IndexPictureService indexpictureService) {
		this.indexpictureService = indexpictureService;
	}

	public Indexpicture getIndexpicture() {
		return indexpicture;
	}

	public void setIndexpicture(Indexpicture indexpicture) {
		this.indexpicture = indexpicture;
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

	public List<Indexpicture> getListPicture() {
		return listPicture;
	}

	public void setListPicture(List<Indexpicture> listPicture) {
		this.listPicture = listPicture;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
	
}
