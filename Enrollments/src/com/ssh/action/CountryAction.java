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
import com.ssh.pojo.Country;
import com.ssh.service.CountryService;

public class CountryAction extends ActionSupport{
	private Country country;
	private CountryService countryService;
	private String fail;
	Map<String,Object> map = new HashMap<String,Object>();
	private int cyid;
	private String cyname;
	private String cyaddress;
	private String cytitle;
	private String cycontent;
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
		private File cypicture;
		private File cyphoto1;
		private File cyphoto2;
		private String gintroduce;
		private String gtitle;
		private int gid;
		private String data;
		
		
		private String cypicturecontentType;// 上传文件类型
		private String cypicturefileName; // 上传文件名
		private String cypictureimageFileName;
		
		private String cyphoto1contentType;// 上传文件类型
		private String cyphoto1fileName; // 上传文件名
		private String cyphoto1imageFileName;
		
		private String cyphoto2contentType;// 上传文件类型
		private String cyphoto2fileName; // 上传文件名
		private String cyphoto2imageFileName;
		
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
				// 所以同时要提供myFileContentType,myFileFileName的set方法
		
		public void setCypicturecontentType(String cypicturecontentType) {
			this.cypicturecontentType =cypicturecontentType;
			System.out.println("文件类型 : " + cypicturecontentType);
		}
		
		public void setCypicturefileName(String cypicturefileName) {
			this.cypicturefileName =cypicturefileName;
			System.out.println("文件名称 :" + cypicturefileName);
			
		}

		public void setCyphoto1contentType(String cyphoto1contentType) {
			this.cyphoto1contentType =cyphoto1contentType;
			System.out.println("文件类型 : " + cyphoto1contentType);
		}
		
		public void setCyphoto1fileName(String cyphoto1fileName) {
			this.cyphoto1fileName =cyphoto1fileName;
			System.out.println("文件名称 :" + cyphoto1fileName);
			
		}
		
		public void setCyphoto2contentType(String cyphoto2contentType) {
			this.cyphoto2contentType =cyphoto2contentType;
			System.out.println("文件类型 : " + cyphoto2contentType);
		}
		
		public void setCyphoto2fileName(String cyphoto2fileName) {
			this.cyphoto2fileName = cyphoto2fileName;
			System.out.println("文件名称 :" + cyphoto2fileName);
			
		}
		//修改文件
				public String updateCountrys(){
					Map<String,Object> map = new HashMap<String, Object>();
					cypictureimageFileName =cypicturefileName;
					cyphoto1imageFileName =cyphoto1fileName;
					cyphoto2imageFileName =cyphoto2fileName;
					File  cypictureimageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + cypictureimageFileName);
					File  cyphoto1imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + cyphoto1imageFileName);
					File  cyphoto2imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + cyphoto2imageFileName);
					copy(cypicture,cypictureimageFile);
					copy(cyphoto1,cyphoto1imageFile);
					copy(cyphoto2,cyphoto2imageFile);
					Country country =new Country();
					country.setCyid(cyid);
					country.setCypicture("backstage/upload/"+cypicturefileName);
					country.setCyphoto1("backstage/upload/"+cyphoto1fileName);
					country.setCyphoto2("backstage/upload/"+cyphoto2fileName);
					country.setCyname(cyname);
					country.setCytitle(cytitle);
					country.setCyaddress(cyaddress);
					country.setCycontent(cycontent);
					boolean isSave = countryService.update(country);
					if(isSave){
						map.put("msg","true");
						data = JSON.toJSONString(map);
					}else{
						data = JSON.toJSONString(map);
					}
					return SUCCESS;
				}
	//遍历
	public void getCountryList() throws Exception{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		int cy = countryService.getAllCountry();
		List<Country> lis = countryService.getCountryList(); 
		map.put("total",cy);
		map.put("rows",lis);
		String mapJson=JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
	}
	
	//查询单个
	public void  findListCountry() throws Exception{
		country =  countryService.getCountryById(cyid);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String Json=JSON.toJSONString(country);
		PrintWriter out = resp.getWriter();
		out.print(Json);
		out.flush();
		out.close();
	}
	public String updateCountry(){
		boolean isUpdate = countryService.update(country);
		Map<String,Object> map = new HashMap<String,Object>();
		if(isUpdate){
			map.put("update", "true");
			fail = JSON.toJSONString(map);
		}else{
			fail = JSON.toJSONString(map);
		}
		return SUCCESS;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
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

	public int getCyid() {
		return cyid;
	}

	public void setCyid(int cyid) {
		this.cyid = cyid;
	}

	public String getCyname() {
		return cyname;
	}

	public void setCyname(String cyname) {
		this.cyname = cyname;
	}

	public String getCyaddress() {
		return cyaddress;
	}

	public void setCyaddress(String cyaddress) {
		this.cyaddress = cyaddress;
	}

	public String getCytitle() {
		return cytitle;
	}

	public void setCytitle(String cytitle) {
		this.cytitle = cytitle;
	}

	public String getCycontent() {
		return cycontent;
	}

	public void setCycontent(String cycontent) {
		this.cycontent = cycontent;
	}

	public File getCypicture() {
		return cypicture;
	}

	public void setCypicture(File cypicture) {
		this.cypicture = cypicture;
	}

	public File getCyphoto1() {
		return cyphoto1;
	}

	public void setCyphoto1(File cyphoto1) {
		this.cyphoto1 = cyphoto1;
	}

	public File getCyphoto2() {
		return cyphoto2;
	}

	public void setCyphoto2(File cyphoto2) {
		this.cyphoto2 = cyphoto2;
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

	public String getCypictureimageFileName() {
		return cypictureimageFileName;
	}

	public void setCypictureimageFileName(String cypictureimageFileName) {
		this.cypictureimageFileName = cypictureimageFileName;
	}

	public String getCyphoto1imageFileName() {
		return cyphoto1imageFileName;
	}

	public void setCyphoto1imageFileName(String cyphoto1imageFileName) {
		this.cyphoto1imageFileName = cyphoto1imageFileName;
	}

	public String getCyphoto2imageFileName() {
		return cyphoto2imageFileName;
	}

	public void setCyphoto2imageFileName(String cyphoto2imageFileName) {
		this.cyphoto2imageFileName = cyphoto2imageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getCypicturecontentType() {
		return cypicturecontentType;
	}

	public String getCypicturefileName() {
		return cypicturefileName;
	}

	public String getCyphoto1contentType() {
		return cyphoto1contentType;
	}

	public String getCyphoto1fileName() {
		return cyphoto1fileName;
	}

	public String getCyphoto2contentType() {
		return cyphoto2contentType;
	}

	public String getCyphoto2fileName() {
		return cyphoto2fileName;
	}

}
