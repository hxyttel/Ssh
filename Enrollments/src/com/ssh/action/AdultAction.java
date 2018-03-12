package com.ssh.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Adult;
import com.ssh.service.AdultService;

public class AdultAction extends ActionSupport{
	private Adult adult;
	private AdultService adultService;
	private String fail;
	Map<String,Object> map = new HashMap<String,Object>();
	private int atid;
	private String atname;
	private String ataddress;
	private String attitle;
	private String atcontent;
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
	//上传图片
		private static final long serialVersionUID = 572146812454l;
		private static final int BUFFER_SIZE = 16 * 1024;
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
		// 所以同时要提供myFileContentType,myFileFileName的set方法
		private File atpicture;
		private File atphoto1;
		private File atphoto2;
		private String gintroduce;
		private String gtitle;
		private int gid;
		private String data;
		
		
		private String atpicturecontentType;// 上传文件类型
		private String atpicturefileName; // 上传文件名
		private String atpictureimageFileName;
		
		private String atphoto1contentType;// 上传文件类型
		private String atphoto1fileName; // 上传文件名
		private String atphoto1imageFileName;
		
		private String atphoto2contentType;// 上传文件类型
		private String atphoto2fileName; // 上传文件名
		private String atphoto2imageFileName;
		
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
				// 所以同时要提供myFileContentType,myFileFileName的set方法
		
		public void setAtpicturecontentType(String atpicturecontentType) {
			this.atpicturecontentType =atpicturecontentType;
			System.out.println("文件类型 : " + atpicturecontentType);
		}
		
		public void setAtpicturefileName(String atpicturefileName) {
			this.atpicturefileName =atpicturefileName;
			System.out.println("文件名称 :" + atpicturefileName);
			
		}

		public void setAtphoto1contentType(String atphoto1contentType) {
			this.atphoto1contentType =atphoto1contentType;
			System.out.println("文件类型 : " + atphoto1contentType);
		}
		
		public void setAtphoto1fileName(String atphoto1fileName) {
			this.atphoto1fileName =atphoto1fileName;
			System.out.println("文件名称 :" + atphoto1fileName);
			
		}
		
		public void setAtphoto2contentType(String atphoto2contentType) {
			this.atphoto2contentType =atphoto2contentType;
			System.out.println("文件类型 : " + atphoto2contentType);
		}
		
		public void setAtphoto2fileName(String atphoto2fileName) {
			this.atphoto2fileName = atphoto2fileName;
			System.out.println("文件名称 :" + atphoto2fileName);
			
		}
		//修改文件
				public String updateAdults(){
					Map<String,Object> map = new HashMap<String, Object>();
					atpictureimageFileName =atpicturefileName;
					atphoto1imageFileName =atphoto1fileName;
					atphoto2imageFileName =atphoto2fileName;
					File  atpictureimageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + atpictureimageFileName);
					File  atphoto1imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + atphoto1imageFileName);
					File  atphoto2imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + atphoto2imageFileName);
					copy(atpicture,atpictureimageFile);
					copy(atphoto1,atphoto1imageFile);
					copy(atphoto2,atphoto2imageFile);
					Adult adult =new Adult();
					adult.setAtid(atid);
					adult.setAtpicture("backstage/upload/"+atpicturefileName);
					adult.setAtphoto1("backstage/upload/"+atphoto1fileName);
					adult.setAtphoto2("backstage/upload/"+atphoto2fileName);
					adult.setAtname(atname);
					adult.setAttitle(attitle);
					adult.setAtaddress(ataddress);
					adult.setAtcontent(atcontent);
					boolean isSave = adultService.update(adult);
					if(isSave){
						map.put("msg","true");
						data = JSON.toJSONString(map);
					}else{
						data = JSON.toJSONString(map);
					}
					return SUCCESS;
				}
	//遍历
	public void getAdultList() throws Exception{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int ad = adultService.getAllAdult();
		List<Adult> lis =adultService.getAdultList(); 
		map.put("total",ad);
		map.put("rows",lis);
		String mapJson=JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
	}
	
	//查询单个
	public void  findListAdult() throws Exception{
		adult =  adultService.getAdultById(atid);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String Json=JSON.toJSONString(adult);
		PrintWriter out = resp.getWriter();
		out.print(Json);
		out.flush();
		out.close();
	}
	public String updateAdult(){
		boolean isUpdate = adultService.update(adult);
		Map<String,Object> map = new HashMap<String,Object>();
		if(isUpdate){
			map.put("update", "true");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	public Adult getAdult() {
		return adult;
	}
	public void setAdult(Adult adult) {
		this.adult = adult;
	}
	public AdultService getAdultService() {
		return adultService;
	}
	public void setAdultService(AdultService adultService) {
		this.adultService = adultService;
	}
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public int getAtid() {
		return atid;
	}
	public void setAtid(int atid) {
		this.atid = atid;
	}

	public String getAtname() {
		return atname;
	}

	public void setAtname(String atname) {
		this.atname = atname;
	}

	public String getAtaddress() {
		return ataddress;
	}

	public void setAtaddress(String ataddress) {
		this.ataddress = ataddress;
	}

	public String getAttitle() {
		return attitle;
	}

	public void setAttitle(String attitle) {
		this.attitle = attitle;
	}

	public String getAtcontent() {
		return atcontent;
	}

	public void setAtcontent(String atcontent) {
		this.atcontent = atcontent;
	}

	public File getAtpicture() {
		return atpicture;
	}

	public void setAtpicture(File atpicture) {
		this.atpicture = atpicture;
	}

	public File getAtphoto1() {
		return atphoto1;
	}

	public void setAtphoto1(File atphoto1) {
		this.atphoto1 = atphoto1;
	}

	public File getAtphoto2() {
		return atphoto2;
	}

	public void setAtphoto2(File atphoto2) {
		this.atphoto2 = atphoto2;
	}

	public String getGintroduce() {
		return gintroduce;
	}

	public void setGintroduce(String gintroduce) {
		this.gintroduce = gintroduce;
	}

	public String getGtitle() {
		return gtitle;
	}

	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAtpictureimageFileName() {
		return atpictureimageFileName;
	}

	public void setAtpictureimageFileName(String atpictureimageFileName) {
		this.atpictureimageFileName = atpictureimageFileName;
	}

	public String getAtphoto1imageFileName() {
		return atphoto1imageFileName;
	}

	public void setAtphoto1imageFileName(String atphoto1imageFileName) {
		this.atphoto1imageFileName = atphoto1imageFileName;
	}

	public String getAtphoto2imageFileName() {
		return atphoto2imageFileName;
	}

	public void setAtphoto2imageFileName(String atphoto2imageFileName) {
		this.atphoto2imageFileName = atphoto2imageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getAtpicturecontentType() {
		return atpicturecontentType;
	}

	public String getAtpicturefileName() {
		return atpicturefileName;
	}

	public String getAtphoto1contentType() {
		return atphoto1contentType;
	}

	public String getAtphoto1fileName() {
		return atphoto1fileName;
	}

	public String getAtphoto2contentType() {
		return atphoto2contentType;
	}

	public String getAtphoto2fileName() {
		return atphoto2fileName;
	}
}
