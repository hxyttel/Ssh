package com.ssh.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Arts;
import com.ssh.service.ArtsService;

public class ArtsAction extends ActionSupport{
	private Arts arts;
	private ArtsService artsService;
	private String fail;
	Map<String,Object> map = new HashMap<String,Object>();
	private int asid;
	private String asname;//艺考培训报名
	private String asaddress;//跳转的jsp页面路径
	private String astitle;//标题
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
		private File aspicture;
		private File asphoto1;
		private File asphoto2;
		private String gintroduce;
		private String gtitle;
		private int gid;
		private String data;
		
		
		private String aspicturecontentType;// 上传文件类型
		private String aspicturefileName; // 上传文件名
		private String aspictureimageFileName;
		
		private String asphoto1contentType;// 上传文件类型
		private String asphoto1fileName; // 上传文件名
		private String asphoto1imageFileName;
		
		private String asphoto2contentType;// 上传文件类型
		private String asphoto2fileName; // 上传文件名
		private String asphoto2imageFileName;
		
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
				// 所以同时要提供myFileContentType,myFileFileName的set方法
		
		public void setAspicturecontentType(String aspicturecontentType) {
			this.aspicturecontentType =aspicturecontentType;
			System.out.println("文件类型 : " + aspicturecontentType);
		}
		
		public void setAspicturefileName(String aspicturefileName) {
			this.aspicturefileName =aspicturefileName;
			System.out.println("文件名称 :" + aspicturefileName);
			
		}

		public void setAsphoto1contentType(String asphoto1contentType) {
			this.asphoto1contentType =asphoto1contentType;
			System.out.println("文件类型 : " + asphoto1contentType);
		}
		
		public void setAsphoto1fileName(String asphoto1fileName) {
			this.asphoto1fileName =asphoto1fileName;
			System.out.println("文件名称 :" + asphoto1fileName);
			
		}
		
		public void setAsphoto2contentType(String asphoto2contentType) {
			this.asphoto2contentType =asphoto2contentType;
			System.out.println("文件类型 : " + asphoto2contentType);
		}
		
		public void setAsphoto2fileName(String asphoto2fileName) {
			this.asphoto2fileName = asphoto2fileName;
			System.out.println("文件名称 :" + asphoto2fileName);
			
		}
		//修改文件
				public String updateArtss(){
					Map<String,Object> map = new HashMap<String, Object>();
					aspictureimageFileName =aspicturefileName;
					asphoto1imageFileName =asphoto1fileName;
					asphoto2imageFileName =asphoto2fileName;
					File  aspictureimageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + aspictureimageFileName);
					File  asphoto1imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + asphoto1imageFileName);
					File  asphoto2imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + asphoto2imageFileName);
					copy(aspicture,aspictureimageFile);
					copy(asphoto1,asphoto1imageFile);
					copy(asphoto2,asphoto2imageFile);
					Arts arts =new Arts();
					arts.setAsid(asid);
					arts.setAspicture("backstage/upload/"+aspicturefileName);
					arts.setAsphoto1("backstage/upload/"+asphoto1fileName);
					arts.setAsphoto2("backstage/upload/"+asphoto2fileName);
					arts.setAsname(asname);
					arts.setAstitle(astitle);
					arts.setAsaddress(asaddress);
					boolean isSave = artsService.update(arts);
					if(isSave){
						map.put("msg","true");
						data = JSON.toJSONString(map);
					}else{
						data = JSON.toJSONString(map);
					}
					return SUCCESS;
				}
	//遍历
			public void getArtsList() throws Exception{
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				int as = artsService.getAllArts();
				List<Arts> lis = artsService.getArtsList(); 
				map.put("total",as);
				map.put("rows",lis);
				String mapJson=JSON.toJSONString(map);
				PrintWriter out = resp.getWriter();
				out.print(mapJson);
			}
			
			//查询单个
			public void  findListArts() throws Exception{
				arts =  artsService.getArtsById(asid);
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				List<Arts> lists = new ArrayList<>();
				lists.add(arts);
				Map<String,Object> map = new HashMap<>();
				map.put("arts", lists);
				String Json=JSON.toJSONString(map);
				System.out.println(Json);
				PrintWriter out = resp.getWriter();
				out.print(Json);
				out.flush();
				out.close();
			}
			public String updateArts(){
				boolean isUpdate = artsService.update(arts);
				Map<String,Object> map = new HashMap<String,Object>();
				if(isUpdate){
					map.put("update", "true");
					fail = JSON.toJSONString(map);
				}else{
					fail = JSON.toJSONString(map);
				}
				return SUCCESS;
			}
	public Arts getArts() {
		return arts;
	}
	public void setArts(Arts arts) {
		this.arts = arts;
	}
	public ArtsService getArtsService() {
		return artsService;
	}
	public void setArtsService(ArtsService artsService) {
		this.artsService = artsService;
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
	public int getAsid() {
		return asid;
	}
	public void setAsid(int asid) {
		this.asid = asid;
	}

	public String getAsname() {
		return asname;
	}

	public void setAsname(String asname) {
		this.asname = asname;
	}

	public String getAsaddress() {
		return asaddress;
	}

	public void setAsaddress(String asaddress) {
		this.asaddress = asaddress;
	}

	public String getAstitle() {
		return astitle;
	}

	public void setAstitle(String astitle) {
		this.astitle = astitle;
	}

	public File getAspicture() {
		return aspicture;
	}

	public void setAspicture(File aspicture) {
		this.aspicture = aspicture;
	}

	public File getAsphoto1() {
		return asphoto1;
	}

	public void setAsphoto1(File asphoto1) {
		this.asphoto1 = asphoto1;
	}

	public File getAsphoto2() {
		return asphoto2;
	}

	public void setAsphoto2(File asphoto2) {
		this.asphoto2 = asphoto2;
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


	public String getAsphoto1imageFileName() {
		return asphoto1imageFileName;
	}

	public void setAsphoto1imageFileName(String asphoto1imageFileName) {
		this.asphoto1imageFileName = asphoto1imageFileName;
	}

	public String getAsphoto2imageFileName() {
		return asphoto2imageFileName;
	}

	public void setAsphoto2imageFileName(String asphoto2imageFileName) {
		this.asphoto2imageFileName = asphoto2imageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getAsphoto1contentType() {
		return asphoto1contentType;
	}

	public String getAsphoto1fileName() {
		return asphoto1fileName;
	}

	public String getAsphoto2contentType() {
		return asphoto2contentType;
	}

	public String getAsphoto2fileName() {
		return asphoto2fileName;
	}

	public String getAspictureimageFileName() {
		return aspictureimageFileName;
	}

	public void setAspictureimageFileName(String aspictureimageFileName) {
		this.aspictureimageFileName = aspictureimageFileName;
	}

	public String getAspicturecontentType() {
		return aspicturecontentType;
	}

	public String getAspicturefileName() {
		return aspicturefileName;
	}
	
}
