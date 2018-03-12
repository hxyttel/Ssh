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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Academy;
import com.ssh.pojo.Contact;
import com.ssh.pojo.General;
import com.ssh.pojo.Indexcontent;
import com.ssh.service.AcademySerice;
import com.ssh.service.ContactService;
import com.ssh.service.GeneralService;
import com.ssh.service.IndexContentService;

public class GeneralAction extends ActionSupport{
	private GeneralService generalService;
	private General general;
	private AcademySerice academyService;
	private Academy academy;
	private List<General> lists;
	private IndexContentService indexcontentService;
	private ContactService contactService;
	private List<Indexcontent> listContent;
	private List<Contact> listContact;
	
	
	//上传图片
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
	// 所以同时要提供myFileContentType,myFileFileName的set方法
	private File glogo;
	private File gpicture;
	private File gmajor;
	private File gdiploma;
	private String gintroduce;
	private String gtitle;
	private int gid;
	private String data;
	
	private String glogocontentType;// 上传文件类型
	private String glogofileName; // 上传文件名
	private String glogoimageFileName;
	
	private String gpicturecontentType;// 上传文件类型
	private String gpicturefileName; // 上传文件名
	private String gpictureimageFileName;
	
	private String gmajorcontentType;// 上传文件类型
	private String gmajorfileName; // 上传文件名
	private String gmajorimageFileName;
	
	private String gdiplomacontentType;// 上传文件类型
	private String gdiplomafileName; // 上传文件名
	private String gdiplomaimageFileName;
	
	// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
			// 所以同时要提供myFileContentType,myFileFileName的set方法
	
	public void setGlogoContentType(String glogocontentType) {
		this.glogocontentType =glogocontentType;
		System.out.println("文件类型 : " + glogocontentType);
	}
	
	public void setGlogoFileName(String glogofileName) {
		this.glogofileName =glogofileName;
		System.out.println("文件名称 :" + glogofileName);
		
	}
	
	public void setGpictureContentType(String gpicturecontentType) {
		this.gpicturecontentType =gpicturecontentType;
		System.out.println("文件类型 : " + gpicturecontentType);
	}
	
	public void setGpictureFileName(String gpicturefileName) {
		this.gpicturefileName =gpicturefileName;
		System.out.println("文件名称 :" + gpicturefileName);
		
	}

	public void setGmajorContentType(String gmajorcontentType) {
		this.gmajorcontentType =gmajorcontentType;
		System.out.println("文件类型 : " + gmajorcontentType);
	}
	
	public void setGmajorFileName(String gmajorfileName) {
		this.gmajorfileName =gmajorfileName;
		System.out.println("文件名称 :" + gmajorfileName);
		
	}
	
	public void setGdiplomaContentType(String gdiplomacontentType) {
		this.gdiplomacontentType =gdiplomacontentType;
		System.out.println("文件类型 : " + gdiplomacontentType);
	}
	
	public void setGdiplomaFileName(String gdiplomafileName) {
		this.gdiplomafileName =gdiplomafileName;
		System.out.println("文件名称 :" + gdiplomafileName);
		
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
	
	//添加文件上传
	public String saveGeneral() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		Map<String,Object> map = new HashMap<String, Object>();
		glogoimageFileName =glogofileName;
		gpictureimageFileName =gpicturefileName;
		gmajorimageFileName =gmajorfileName;
		gdiplomaimageFileName =gdiplomafileName;
		File  glogoimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + glogoimageFileName);
		File  gpicturelimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + gpictureimageFileName);
		File  gmajorimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + gmajorimageFileName);
		File  gdiplomaimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + gdiplomaimageFileName);
		copy(glogo,glogoimageFile);
		copy(gpicture,gpicturelimageFile);
		copy(gmajor, gmajorimageFile);
		copy(gdiploma, gdiplomaimageFile);
		General general =new General();
		general.setGlogo("backstage/upload/"+glogofileName);
		general.setGpicture("backstage/upload/"+gpicturefileName);
		general.setGmajor("backstage/upload/"+gmajorfileName);
		general.setGdiploma("backstage/upload/"+gdiplomafileName);
		general.setGtitle(gtitle);
		general.setGintroduce(gintroduce);
		boolean isSave = generalService.addGeneral(general);
		if(isSave){
			session.setAttribute("message","提交成功!");
			return SUCCESS;
		}else{
			session.setAttribute("message","提交失败!");
			return SUCCESS;
		}
		
	}
	//遍历文件
	public  void listGeneral() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		int count = generalService.getAllCount();
		List<General> lis =generalService.selectGeneral();
		List<General> li  = new ArrayList<General>();
		for(int i=0;i<lis.size();i++){
			general = lis.get(i);
			General gen = new General();
			gen.setGid(general.getGid());
			gen.setGlogo(general.getGlogo());;
			gen.setGintroduce(general.getGintroduce());
			gen.setGpicture(general.getGpicture());;
			gen.setGtitle(general.getGtitle());
			gen.setGmajor(general.getGmajor());
			gen.setGdiploma(general.getGdiploma());;
			li.add(gen);
		}
 		map.put("total",count);
		map.put("rows",li);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	
	//遍历文件(模糊查询)
	public  void likeGeneral() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		Map<String,Object> map = new HashMap<String,Object>();
		List<General> lis =generalService.listLikeGeneral(gtitle,page,row);
		List<General> li  = new ArrayList<General>();
		for(int i=0;i<lis.size();i++){
			general = lis.get(i);
			General gen = new General();
			gen.setGid(general.getGid());
			gen.setGlogo(general.getGlogo());;
			gen.setGintroduce(general.getGintroduce());
			gen.setGpicture(general.getGpicture());;
			gen.setGtitle(general.getGtitle());
			gen.setGmajor(general.getGmajor());
			gen.setGdiploma(general.getGdiploma());;
			li.add(gen);
		}
		int count = li.size();
 		map.put("total",count);
		map.put("rows",li);
		String mapJson = JSON.toJSONString(map);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//查文件对象
		public void findGeneral() throws IOException{
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setContentType("text/html;charset=utf-8");
			resp.setCharacterEncoding("UTF-8");
			General general = generalService.getGeneral(gid);
			General gen = new General();
			gen.setGid(general.getGid());
			gen.setGlogo(general.getGlogo());
			gen.setGintroduce(general.getGintroduce());
			gen.setGpicture(general.getGpicture());
			gen.setGtitle(general.getGtitle());
			gen.setGmajor(general.getGmajor());
			gen.setGdiploma(general.getGdiploma());
			String mapJson = JSON.toJSONString(gen);
			PrintWriter out = resp.getWriter();
			out.print(mapJson);
			out.flush();
			out.close();
		}
		//修改文件
		public String updateGeneral(){
			Map<String,Object> map = new HashMap<String, Object>();
			glogoimageFileName =glogofileName;
			gpictureimageFileName =gpicturefileName;
			gmajorimageFileName =gmajorfileName;
			gdiplomaimageFileName =gdiplomafileName;
			File  glogoimageFile = new File(
					ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + glogoimageFileName);
			File  gpicturelimageFile = new File(
					ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + gpictureimageFileName);
			File  gmajorimageFile = new File(
					ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + gmajorimageFileName);
			File  gdiplomaimageFile = new File(
					ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + gdiplomaimageFileName);
			copy(glogo,glogoimageFile);
			copy(gpicture,gpicturelimageFile);
			copy(gmajor, gmajorimageFile);
			copy(gdiploma, gdiplomaimageFile);
			General general =new General();
			general.setGid(gid);
			general.setGlogo("backstage/upload/"+glogofileName);
			general.setGpicture("backstage/upload/"+gpicturefileName);
			general.setGmajor("backstage/upload/"+gmajorfileName);
			general.setGdiploma("backstage/upload/"+gdiplomafileName);
			general.setGtitle(gtitle);
			general.setGintroduce(gintroduce);
			boolean isSave = generalService.updateGeneral(general);
			if(isSave){
				map.put("msg","true");
				data = JSON.toJSONString(map);
			}else{
				data = JSON.toJSONString(map);
			}
			return SUCCESS;
		}
		//删除文件
		public String DeleteGeneral(){
			Map<String,Object> map = new HashMap<String, Object>();
			boolean isDelete = generalService.deleteGeneral(gid);
			if(isDelete){
				map.put("msg","true");
				data = JSON.toJSONString(map);
			}else{
				data = JSON.toJSONString(map);
			}
			return SUCCESS;
		}
		//前台获取后台学校信息
		public String school() {
			//招生简章
			lists = generalService.selectGeneral();
			listContent = indexcontentService.getIndexcontentList(); 
			listContact = contactService.getContactList();
			return SUCCESS;
		}
		//前台获取后台学校专业
		public String school_2() {
			general = generalService.getGeneral(gid);
			listContent = indexcontentService.getIndexcontentList(); 
			listContact = contactService.getContactList();
			return SUCCESS;
		}
		
	
	public GeneralService getGeneralService() {
		return generalService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}

	public AcademySerice getAcademyService() {
		return academyService;
	}

	public void setAcademyService(AcademySerice academyService) {
		this.academyService = academyService;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public File getGlogo() {
		return glogo;
	}

	public void setGlogo(File glogo) {
		this.glogo = glogo;
	}

	public File getGpicture() {
		return gpicture;
	}

	public void setGpicture(File gpicture) {
		this.gpicture = gpicture;
	}

	public File getGmajor() {
		return gmajor;
	}

	public void setGmajor(File gmajor) {
		this.gmajor = gmajor;
	}

	public File getGdiploma() {
		return gdiploma;
	}

	public void setGdiploma(File gdiploma) {
		this.gdiploma = gdiploma;
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

	public String getGlogofileName() {
		return glogofileName;
	}

	public void setGlogofileName(String glogofileName) {
		this.glogofileName = glogofileName;
	}

	public String getGlogoimageFileName() {
		return glogoimageFileName;
	}

	public void setGlogoimageFileName(String glogoimageFileName) {
		this.glogoimageFileName = glogoimageFileName;
	}

	public String getGpicturefileName() {
		return gpicturefileName;
	}

	public void setGpicturefileName(String gpicturefileName) {
		this.gpicturefileName = gpicturefileName;
	}

	public String getGpictureimageFileName() {
		return gpictureimageFileName;
	}

	public void setGpictureimageFileName(String gpictureimageFileName) {
		this.gpictureimageFileName = gpictureimageFileName;
	}

	public String getGmajorfileName() {
		return gmajorfileName;
	}

	public void setGmajorfileName(String gmajorfileName) {
		this.gmajorfileName = gmajorfileName;
	}

	public String getGmajorimageFileName() {
		return gmajorimageFileName;
	}

	public void setGmajorimageFileName(String gmajorimageFileName) {
		this.gmajorimageFileName = gmajorimageFileName;
	}

	public String getGdiplomafileName() {
		return gdiplomafileName;
	}

	public void setGdiplomafileName(String gdiplomafileName) {
		this.gdiplomafileName = gdiplomafileName;
	}

	public String getGdiplomaimageFileName() {
		return gdiplomaimageFileName;
	}

	public void setGdiplomaimageFileName(String gdiplomaimageFileName) {
		this.gdiplomaimageFileName = gdiplomaimageFileName;
	}

	public List<General> getLists() {
		return lists;
	}

	public void setLists(List<General> lists) {
		this.lists = lists;
	}

	public List<Indexcontent> getListContent() {
		return listContent;
	}

	public void setListContent(List<Indexcontent> listContent) {
		this.listContent = listContent;
	}

	public List<Contact> getListContact() {
		return listContact;
	}

	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
	}

	public IndexContentService getIndexcontentService() {
		return indexcontentService;
	}

	public void setIndexcontentService(IndexContentService indexcontentService) {
		this.indexcontentService = indexcontentService;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
}
