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
import com.ssh.pojo.Accounting;
import com.ssh.service.AccountingService;

public class AccountingAction extends ActionSupport{
	private Accounting accounting;
	private AccountingService accountingService;
	private String fail;
	Map<String,Object> map = new HashMap<String,Object>();
	private int agid;
	private String agname;
	private String agaddress;
	private String agtitle;
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
		private File agpicture;
		private File agphoto1;
		private File agphoto2;
		private String gintroduce;
		private String gtitle;
		private int gid;
		private String data;
		
		
		private String agpicturecontentType;// 上传文件类型
		private String agpicturefileName; // 上传文件名
		private String agpictureimageFileName;
		
		private String agphoto1contentType;// 上传文件类型
		private String agphoto1fileName; // 上传文件名
		private String agphoto1imageFileName;
		
		private String agphoto2contentType;// 上传文件类型
		private String agphoto2fileName; // 上传文件名
		private String agphoto2imageFileName;
		
		// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
				// 所以同时要提供myFileContentType,myFileFileName的set方法
		
		public void setAgpicturecontentType(String agpicturecontentType) {
			this.agpicturecontentType =agpicturecontentType;
			System.out.println("文件类型 : " + agpicturecontentType);
		}
		
		public void setAgpicturefileName(String agpicturefileName) {
			this.agpicturefileName =agpicturefileName;
			System.out.println("文件名称 :" + agpicturefileName);
			
		}

		public void setAgphoto1contentType(String agphoto1contentType) {
			this.agphoto1contentType =agphoto1contentType;
			System.out.println("文件类型 : " + agphoto1contentType);
		}
		
		public void setAgphoto1fileName(String agphoto1fileName) {
			this.agphoto1fileName =agphoto1fileName;
			System.out.println("文件名称 :" + agphoto1fileName);
			
		}
		
		public void setAgphoto2contentType(String agphoto2contentType) {
			this.agphoto2contentType =agphoto2contentType;
			System.out.println("文件类型 : " + agphoto2contentType);
		}
		
		public void setAgphoto2fileName(String agphoto2fileName) {
			this.agphoto2fileName = agphoto2fileName;
			System.out.println("文件名称 :" + agphoto2fileName);
			
		}
		//修改文件
				public String updateAccountings(){
					Map<String,Object> map = new HashMap<String, Object>();
					agpictureimageFileName =agpicturefileName;
					agphoto1imageFileName =agphoto1fileName;
					agphoto2imageFileName =agphoto2fileName;
					File  agpictureimageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + agpictureimageFileName);
					File  agphoto1imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + agphoto1imageFileName);
					File  agphoto2imageFile = new File(
							ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + agphoto2imageFileName);
					copy(agpicture,agpictureimageFile);
					copy(agphoto1,agphoto1imageFile);
					copy(agphoto2,agphoto2imageFile);
					Accounting accounting =new Accounting();
					accounting.setAgid(agid);
					accounting.setAgpicture("backstage/upload/"+agpicturefileName);
					accounting.setAgphoto1("backstage/upload/"+agphoto1fileName);
					accounting.setAgphoto2("backstage/upload/"+agphoto2fileName);
					accounting.setAgname(agname);
					accounting.setAgtitle(agtitle);
					accounting.setAgaddress(agaddress);
					boolean isSave = accountingService.update(accounting);
					if(isSave){
						map.put("msg","true");
						data = JSON.toJSONString(map);
					}else{
						data = JSON.toJSONString(map);
					}
					return SUCCESS;
				}
	//遍历
		public void getAccountingList() throws Exception{
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			int ag = accountingService.getAllAccounting();
			List<Accounting> lis =accountingService.getAccountingList(); 
			map.put("total",ag);
			map.put("rows",lis);
			String mapJson=JSON.toJSONString(map);
			PrintWriter out = resp.getWriter();
			out.print(mapJson);
		}
		
		//查询单个
		public void  findListAccounting() throws Exception{
			accounting =  accountingService.getAccountingById(agid);
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			String Json=JSON.toJSONString(accounting);
			PrintWriter out = resp.getWriter();
			out.print(Json);
			out.flush();
			out.close();
		}
		public String updateAccounting(){
			boolean isUpdate = accountingService.update(accounting);
			Map<String,Object> map = new HashMap<String,Object>();
			if(isUpdate){
				map.put("update", "true");
				fail = JSON.toJSONString(map);
			}else{
				fail = JSON.toJSONString(map);
			}
			return SUCCESS;
		}
	public Accounting getAccounting() {
		return accounting;
	}
	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}
	public AccountingService getAccountingService() {
		return accountingService;
	}
	public void setAccountingService(AccountingService accountingService) {
		this.accountingService = accountingService;
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
	public int getAgid() {
		return agid;
	}
	public void setAgid(int agid) {
		this.agid = agid;
	}

	public String getAgname() {
		return agname;
	}

	public void setAgname(String agname) {
		this.agname = agname;
	}

	public String getAgaddress() {
		return agaddress;
	}

	public void setAgaddress(String agaddress) {
		this.agaddress = agaddress;
	}

	public String getAgtitle() {
		return agtitle;
	}

	public void setAgtitle(String agtitle) {
		this.agtitle = agtitle;
	}

	public File getAgpicture() {
		return agpicture;
	}

	public void setAgpicture(File agpicture) {
		this.agpicture = agpicture;
	}

	public File getAgphoto1() {
		return agphoto1;
	}

	public void setAgphoto1(File agphoto1) {
		this.agphoto1 = agphoto1;
	}

	public File getAgphoto2() {
		return agphoto2;
	}

	public void setAgphoto2(File agphoto2) {
		this.agphoto2 = agphoto2;
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

	public String getAgpictureimageFileName() {
		return agpictureimageFileName;
	}

	public void setAgpictureimageFileName(String agpictureimageFileName) {
		this.agpictureimageFileName = agpictureimageFileName;
	}

	public String getAgphoto1imageFileName() {
		return agphoto1imageFileName;
	}

	public void setAgphoto1imageFileName(String agphoto1imageFileName) {
		this.agphoto1imageFileName = agphoto1imageFileName;
	}

	public String getAgphoto2imageFileName() {
		return agphoto2imageFileName;
	}

	public void setAgphoto2imageFileName(String agphoto2imageFileName) {
		this.agphoto2imageFileName = agphoto2imageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public String getAgpicturecontentType() {
		return agpicturecontentType;
	}

	public String getAgpicturefileName() {
		return agpicturefileName;
	}

	public String getAgphoto1contentType() {
		return agphoto1contentType;
	}

	public String getAgphoto1fileName() {
		return agphoto1fileName;
	}

	public String getAgphoto2contentType() {
		return agphoto2contentType;
	}

	public String getAgphoto2fileName() {
		return agphoto2fileName;
	}
	
}
