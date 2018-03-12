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
import com.ssh.pojo.Vocational;
import com.ssh.service.VocationalService;

public class VocationalAction extends ActionSupport{
	private Vocational vocational;
	private VocationalService vocationalService;
	private String fail;
	Map<String,Object> map = new HashMap<String,Object>();
	private int vlid;
	private String vlname;
	private String vladdress;
	private String vltitle;
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
		private File vlpicture;
		private File vlphoto1;
		private File vlphoto2;
		private String gintroduce;
		private String gtitle;
		private int gid;
		private String data;
		
		
		private String vlpicturecontentType;// 上传文件类型
		private String vlpicturefileName; // 上传文件名
		private String vlpictureimageFileName;
		
		private String vlphoto1contentType;// 上传文件类型
		private String vlphoto1fileName; // 上传文件名
		private String vlphoto1imageFileName;
		
		private String vlphoto2contentType;// 上传文件类型
		private String vlphoto2fileName; // 上传文件名
		private String vlphoto2imageFileName;
		
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
				// 所以同时要提供myFileContentType,myFileFileName的set方法
		
		public void setVlpicturecontentType(String vlpicturecontentType) {
			this.vlpicturecontentType =vlpicturecontentType;
			System.out.println("文件类型 : " + vlpicturecontentType);
		}
		
		public void setVlpicturefileName(String vlpicturefileName) {
			this.vlpicturefileName =vlpicturefileName;
			System.out.println("文件名称 :" + vlpicturefileName);
			
		}

		public void setVlphoto1contentType(String vlphoto1contentType) {
			this.vlphoto1contentType =vlphoto1contentType;
			System.out.println("文件类型 : " + vlphoto1contentType);
		}
		
		public void setVlphoto1fileName(String vlphoto1fileName) {
			this.vlphoto1fileName =vlphoto1fileName;
			System.out.println("文件名称 :" + vlphoto1fileName);
			
		}
		
		public void setVlphoto2contentType(String vlphoto2contentType) {
			this.vlphoto2contentType =vlphoto2contentType;
			System.out.println("文件类型 : " + vlphoto2contentType);
		}
		
		public void setVlphoto2fileName(String vlphoto2fileName) {
			this.vlphoto2fileName = vlphoto2fileName;
			System.out.println("文件名称 :" + vlphoto2fileName);
			
		}
		//修改文件
				public String updateVocationals(){
					Map<String,Object> map = new HashMap<String, Object>();
					vlpictureimageFileName =vlpicturefileName;
					vlphoto1imageFileName =vlphoto1fileName;
					vlphoto2imageFileName =vlphoto2fileName;
					File  vlpictureimageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + vlpictureimageFileName);
					File  vlphoto1imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + vlphoto1imageFileName);
					File  vlphoto2imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + vlphoto2imageFileName);
					copy(vlpicture,vlpictureimageFile);
					copy(vlphoto1,vlphoto1imageFile);
					copy(vlphoto2,vlphoto2imageFile);
					Vocational vocational =new Vocational();
					vocational.setVlid(vlid);
					vocational.setVlpicture("backstage/upload/"+vlpicturefileName);
					vocational.setVlphoto1("backstage/upload/"+vlphoto1fileName);
					vocational.setVlphoto2("backstage/upload/"+vlphoto2fileName);
					vocational.setVlname(vlname);
					vocational.setVltitle(vltitle);
					vocational.setVladdress(vladdress);
					boolean isSave = vocationalService.update(vocational);
					if(isSave){
						map.put("msg","true");
						data = JSON.toJSONString(map);
					}else{
						data = JSON.toJSONString(map);
					}
					return SUCCESS;
				}
	//遍历
			public void getVocationalList() throws Exception{
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				int vl = vocationalService.getAllVocational();
				List<Vocational> lis = vocationalService.getVocationalList(); 
				map.put("total",vl);
				map.put("rows",lis);
				String mapJson=JSON.toJSONString(map);
				PrintWriter out = resp.getWriter();
				out.print(mapJson);
			}
			
			//查询单个
			public void  findListVocational() throws Exception{
				vocational =  vocationalService.getVocationalById(vlid);
				HttpServletResponse resp = ServletActionContext.getResponse();
				resp.setCharacterEncoding("utf-8");
				resp.setContentType("text/html;charset=utf-8");
				String Json=JSON.toJSONString(vocational);
				PrintWriter out = resp.getWriter();
				out.print(Json);
				out.flush();
				out.close();
			}
			public String updateVocational(){
				boolean isUpdate = vocationalService.update(vocational);
				Map<String,Object> map = new HashMap<String,Object>();
				if(isUpdate){
					map.put("update", "true");
					fail = JSON.toJSONString(map);
				}else{
					fail = JSON.toJSONString(map);
				}
				return SUCCESS;
			}
	
	public Vocational getVocational() {
		return vocational;
	}
	public void setVocational(Vocational vocational) {
		this.vocational = vocational;
	}
	public VocationalService getVocationalService() {
		return vocationalService;
	}
	public void setVocationalService(VocationalService vocationalService) {
		this.vocationalService = vocationalService;
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
	public int getVlid() {
		return vlid;
	}
	public void setVlid(int vlid) {
		this.vlid = vlid;
	}

	public String getVlname() {
		return vlname;
	}

	public void setVlname(String vlname) {
		this.vlname = vlname;
	}

	public String getVladdress() {
		return vladdress;
	}

	public void setVladdress(String vladdress) {
		this.vladdress = vladdress;
	}

	public String getVltitle() {
		return vltitle;
	}

	public void setVltitle(String vltitle) {
		this.vltitle = vltitle;
	}

	public File getVlpicture() {
		return vlpicture;
	}

	public void setVlpicture(File vlpicture) {
		this.vlpicture = vlpicture;
	}

	public File getVlphoto1() {
		return vlphoto1;
	}

	public void setVlphoto1(File vlphoto1) {
		this.vlphoto1 = vlphoto1;
	}

	public File getVlphoto2() {
		return vlphoto2;
	}

	public void setVlphoto2(File vlphoto2) {
		this.vlphoto2 = vlphoto2;
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

	public String getVlpictureimageFileName() {
		return vlpictureimageFileName;
	}

	public void setVlpictureimageFileName(String vlpictureimageFileName) {
		this.vlpictureimageFileName = vlpictureimageFileName;
	}

	public String getVlphoto1imageFileName() {
		return vlphoto1imageFileName;
	}

	public void setVlphoto1imageFileName(String vlphoto1imageFileName) {
		this.vlphoto1imageFileName = vlphoto1imageFileName;
	}

	public String getVlphoto2imageFileName() {
		return vlphoto2imageFileName;
	}

	public void setVlphoto2imageFileName(String vlphoto2imageFileName) {
		this.vlphoto2imageFileName = vlphoto2imageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getVlpicturecontentType() {
		return vlpicturecontentType;
	}

	public String getVlpicturefileName() {
		return vlpicturefileName;
	}

	public String getVlphoto1contentType() {
		return vlphoto1contentType;
	}

	public String getVlphoto1fileName() {
		return vlphoto1fileName;
	}

	public String getVlphoto2contentType() {
		return vlphoto2contentType;
	}

	public String getVlphoto2fileName() {
		return vlphoto2fileName;
	}
	
	
}
