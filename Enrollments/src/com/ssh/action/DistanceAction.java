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
import com.ssh.pojo.Distance;
import com.ssh.service.DistanceService;

public class DistanceAction extends ActionSupport{
	private Distance distance;
	private DistanceService distanceService;
	private String fail;
	Map<String,Object> map = new HashMap<String,Object>();
	private int deid;
	private String dename;
	private String deaddress;
	private String detitle;
	private String decontent;
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
		private File depicture;
		private File dephoto1;
		private File dephoto2;
		private String gintroduce;
		private String gtitle;
		private int gid;
		private String data;
		
		
		private String depicturecontentType;// 上传文件类型
		private String depicturefileName; // 上传文件名
		private String depictureimageFileName;
		
		private String dephoto1contentType;// 上传文件类型
		private String dephoto1fileName; // 上传文件名
		private String dephoto1imageFileName;
		
		private String dephoto2contentType;// 上传文件类型
		private String dephoto2fileName; // 上传文件名
		private String dephoto2imageFileName;
		
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
				// 所以同时要提供myFileContentType,myFileFileName的set方法
		
		public void setDepicturecontentType(String depicturecontentType) {
			this.depicturecontentType =depicturecontentType;
			System.out.println("文件类型 : " + depicturecontentType);
		}
		
		public void setDepicturefileName(String depicturefileName) {
			this.depicturefileName =depicturefileName;
			System.out.println("文件名称 :" + depicturefileName);
			
		}

		public void setDephoto1contentType(String dephoto1contentType) {
			this.dephoto1contentType =dephoto1contentType;
			System.out.println("文件类型 : " + dephoto1contentType);
		}
		
		public void setDephoto1fileName(String dephoto1fileName) {
			this.dephoto1fileName =dephoto1fileName;
			System.out.println("文件名称 :" + dephoto1fileName);
			
		}
		
		public void setDephoto2contentType(String dephoto2contentType) {
			this.dephoto2contentType =dephoto2contentType;
			System.out.println("文件类型 : " + dephoto2contentType);
		}
		
		public void setDephoto2fileName(String dephoto2fileName) {
			this.dephoto2fileName = dephoto2fileName;
			System.out.println("文件名称 :" + dephoto2fileName);
			
		}
		//修改文件
				public String updateDistances(){
					Map<String,Object> map = new HashMap<String, Object>();
					depictureimageFileName =depicturefileName;
					dephoto1imageFileName =dephoto1fileName;
					dephoto2imageFileName =dephoto2fileName;
					File  depictureimageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + depictureimageFileName);
					File  dephoto1imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + dephoto1imageFileName);
					File  dephoto2imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + dephoto2imageFileName);
					copy(depicture,depictureimageFile);
					copy(dephoto1,dephoto1imageFile);
					copy(dephoto2,dephoto2imageFile);
					Distance distance =new Distance();
					distance.setDeid(deid);
					distance.setDepicture("backstage/upload/"+depicturefileName);
					distance.setDephoto1("backstage/upload/"+dephoto1fileName);
					distance.setDephoto2("backstage/upload/"+dephoto2fileName);
					distance.setDename(dename);
					distance.setDetitle(detitle);
					distance.setDeaddress(deaddress);
					distance.setDecontent(decontent);
					boolean isSave = distanceService.update(distance);
					if(isSave){
						map.put("msg","true");
						data = JSON.toJSONString(map);
					}else{
						data = JSON.toJSONString(map);
					}
					return SUCCESS;
				}
	//遍历
			public void getDistanceList() throws Exception{
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				int de = distanceService.getAllDistance();
				List<Distance> lis =distanceService.getDistanceList(); 
				map.put("total",de);
				map.put("rows",lis);
				String mapJson=JSON.toJSONString(map);
				PrintWriter out = resp.getWriter();
				out.print(mapJson);
			}
			
			//查询单个
			public void  findListDistance() throws Exception{
				distance =  distanceService.getDistanceById(deid);
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				String Json=JSON.toJSONString(distance);
				PrintWriter out = resp.getWriter();
				out.print(Json);
				out.flush();
				out.close();
			}
			public String updateDistance(){
				boolean isUpdate = distanceService.update(distance);
				Map<String,Object> map = new HashMap<String,Object>();
				if(isUpdate){
					map.put("update", "true");
					fail = JSON.toJSONString(map);
				}else{
					fail = JSON.toJSONString(map);
				}
				return SUCCESS;
			}	
	
	
	public Distance getDistance() {
		return distance;
	}
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
	public DistanceService getDistanceService() {
		return distanceService;
	}
	public void setDistanceService(DistanceService distanceService) {
		this.distanceService = distanceService;
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
	public int getDeid() {
		return deid;
	}
	public void setDeid(int deid) {
		this.deid = deid;
	}

	public String getDename() {
		return dename;
	}

	public void setDename(String dename) {
		this.dename = dename;
	}

	public String getDeaddress() {
		return deaddress;
	}

	public void setDeaddress(String deaddress) {
		this.deaddress = deaddress;
	}

	public String getDetitle() {
		return detitle;
	}

	public void setDetitle(String detitle) {
		this.detitle = detitle;
	}

	public String getDecontent() {
		return decontent;
	}

	public void setDecontent(String decontent) {
		this.decontent = decontent;
	}

	public File getDepicture() {
		return depicture;
	}

	public void setDepicture(File depicture) {
		this.depicture = depicture;
	}

	public File getDephoto1() {
		return dephoto1;
	}

	public void setDephoto1(File dephoto1) {
		this.dephoto1 = dephoto1;
	}

	public File getDephoto2() {
		return dephoto2;
	}

	public void setDephoto2(File dephoto2) {
		this.dephoto2 = dephoto2;
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

	public String getDepictureimageFileName() {
		return depictureimageFileName;
	}

	public void setDepictureimageFileName(String depictureimageFileName) {
		this.depictureimageFileName = depictureimageFileName;
	}

	public String getDephoto1imageFileName() {
		return dephoto1imageFileName;
	}

	public void setDephoto1imageFileName(String dephoto1imageFileName) {
		this.dephoto1imageFileName = dephoto1imageFileName;
	}

	public String getDephoto2imageFileName() {
		return dephoto2imageFileName;
	}

	public void setDephoto2imageFileName(String dephoto2imageFileName) {
		this.dephoto2imageFileName = dephoto2imageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getDepicturecontentType() {
		return depicturecontentType;
	}

	public String getDepicturefileName() {
		return depicturefileName;
	}

	public String getDephoto1contentType() {
		return dephoto1contentType;
	}

	public String getDephoto1fileName() {
		return dephoto1fileName;
	}

	public String getDephoto2contentType() {
		return dephoto2contentType;
	}

	public String getDephoto2fileName() {
		return dephoto2fileName;
	}
	
}
