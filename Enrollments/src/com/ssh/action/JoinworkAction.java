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
import com.ssh.pojo.Contact;
import com.ssh.pojo.General;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Joinwork;
import com.ssh.service.ContactService;
import com.ssh.service.IndexContentService;
import com.ssh.service.JoinworkService;

public class JoinworkAction extends ActionSupport{
	private JoinworkService joinworkService;
	private IndexContentService indexcontentService;
	private ContactService contactService;
	private Joinwork joinwork;
	private List<Joinwork> lists;
	private List<Joinwork> listjoinwork;
	private List<Indexcontent> listContent;
	private List<Contact> listContact;
	

	//上传图片
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
	// 所以同时要提供myFileContentType,myFileFileName的set方法
	private Integer jwid;//主键id
	private String  jwtitle;//标题
	private File  jwpicture; //图片
	private String  jwcontent; //内容
	private String data;
	
	private String jwpicturecontentType;// 上传文件类型
	private String jwpicturefileName; // 上传文件名
	private String jwpictureimageFileName;
	
	// 注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
			// 所以同时要提供myFileContentType,myFileFileName的set方法
	
	public void setJwpictureContentType(String jwpicturecontentType) {
		this.jwpicturecontentType =jwpicturecontentType;
		System.out.println("文件类型 : " + jwpicturecontentType);
	}
	
	public void setJwpictureFileName(String jwpicturefileName) {
		this.jwpicturefileName =jwpicturefileName;
		System.out.println("文件名称 :" + jwpicturefileName);
		
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
	public String saveJoinwork() throws Exception{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		Map<String,Object> map = new HashMap<String, Object>();
		jwpictureimageFileName =jwpicturefileName;
		File  jwpictureimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + jwpictureimageFileName);
		copy(jwpicture,jwpictureimageFile);
		Joinwork joinwork =new Joinwork();
		joinwork.setJwpicture("backstage/upload/"+jwpicturefileName);
		joinwork.setJwid(jwid);
		joinwork.setJwtitle(jwtitle);
		joinwork.setJwcontent(jwcontent);
		boolean isSave = joinworkService.addJoinwork(joinwork);
		if(isSave){
			session.setAttribute("message","提交成功!");
			return SUCCESS;
		}else{
			session.setAttribute("message","提交失败!");
			return SUCCESS;
		}
		
	}
	//遍历文件
	public  void listJoinwork() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		int count = joinworkService.getAllCount();
		List<Joinwork> lis =joinworkService.selectJoinwork();
		List<Joinwork> li  = new ArrayList<Joinwork>();
		for(int i=0;i<lis.size();i++){
			joinwork = lis.get(i);
			Joinwork jk = new Joinwork();
			jk.setJwid(joinwork.getJwid());
			jk.setJwtitle(joinwork.getJwtitle());
			jk.setJwpicture(joinwork.getJwpicture());
			jk.setJwcontent(joinwork.getJwcontent());
			li.add(jk);
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
	public  void likeJoinwork() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		Map<String,Object> map = new HashMap<String,Object>();
		List<Joinwork> lis =joinworkService.listLikeJoinwork(jwtitle,page,row);
		List<Joinwork> li  = new ArrayList<Joinwork>();
		for(int i=0;i<lis.size();i++){
			joinwork = lis.get(i);
			Joinwork jk = new Joinwork();
			jk.setJwid(joinwork.getJwid());
			jk.setJwtitle(joinwork.getJwtitle());
			jk.setJwpicture(joinwork.getJwpicture());
			jk.setJwcontent(joinwork.getJwcontent());
			li.add(jk);
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
		public void findJoinwork() throws IOException{
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setContentType("text/html;charset=utf-8");
			resp.setCharacterEncoding("UTF-8");
			Joinwork joinwork = joinworkService.getJoinwork(jwid);
			Joinwork jk = new Joinwork();
			jk.setJwid(joinwork.getJwid());
			jk.setJwtitle(joinwork.getJwtitle());
			jk.setJwpicture(joinwork.getJwpicture());
			jk.setJwcontent(joinwork.getJwcontent());
			String mapJson = JSON.toJSONString(jk);
			PrintWriter out = resp.getWriter();
			out.print(mapJson);
			out.flush();
			out.close();
		}
		//修改文件
		public String updateJoinwork(){
			Map<String,Object> map = new HashMap<String, Object>();
			jwpictureimageFileName =jwpicturefileName;
			File  jwpictureimageFile = new File(
					ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + jwpictureimageFileName);
			copy(jwpicture,jwpictureimageFile);
			Joinwork joinwork =new Joinwork();
			joinwork.setJwpicture("backstage/upload/"+jwpicturefileName);
			joinwork.setJwid(jwid);
			joinwork.setJwtitle(jwtitle);
			joinwork.setJwcontent(jwcontent);
			boolean isSave = joinworkService.updateJoinwork(joinwork);
			if(isSave){
				map.put("msg","true");
				data = JSON.toJSONString(map);
			}else{
				data = JSON.toJSONString(map);
			}
			return SUCCESS;
		}
		//删除文件
		public String deleteJoinwork(){
			Map<String,Object> map = new HashMap<String, Object>();
			boolean isDelete = joinworkService.deleteJoinwork(jwid);
			if(isDelete){
				map.put("msg","true");
				data = JSON.toJSONString(map);
			}else{
				data = JSON.toJSONString(map);
			}
			return SUCCESS;
		}
	
		//首页遍历共享合作
		public String joinwork(){
			listjoinwork =joinworkService.selectJoinwork();
			listContent = indexcontentService.getIndexcontentList(); 
			listContact = contactService.getContactList();
			return SUCCESS;
		}
		//首页详情共享合作
		public String detailsJoinWork(){
			joinwork = joinworkService.getJoinwork(jwid);
			listContent = indexcontentService.getIndexcontentList(); 
			listContact = contactService.getContactList();
			return SUCCESS;
		}
	
	public JoinworkService getJoinworkService() {
		return joinworkService;
	}
	public void setJoinworkService(JoinworkService joinworkService) {
		this.joinworkService = joinworkService;
	}
	public Joinwork getJoinwork() {
		return joinwork;
	}
	public void setJoinwork(Joinwork joinwork) {
		this.joinwork = joinwork;
	}
	public List<Joinwork> getLists() {
		return lists;
	}
	public void setLists(List<Joinwork> lists) {
		this.lists = lists;
	}

	public Integer getJwid() {
		return jwid;
	}

	public void setJwid(Integer jwid) {
		this.jwid = jwid;
	}

	public String getJwtitle() {
		return jwtitle;
	}

	public void setJwtitle(String jwtitle) {
		this.jwtitle = jwtitle;
	}

	public File getJwpicture() {
		return jwpicture;
	}

	public void setJwpicture(File jwpicture) {
		this.jwpicture = jwpicture;
	}

	public String getJwcontent() {
		return jwcontent;
	}

	public void setJwcontent(String jwcontent) {
		this.jwcontent = jwcontent;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getJwpicturecontentType() {
		return jwpicturecontentType;
	}

	public void setJwpicturecontentType(String jwpicturecontentType) {
		this.jwpicturecontentType = jwpicturecontentType;
	}

	public String getJwpicturefileName() {
		return jwpicturefileName;
	}

	public void setJwpicturefileName(String jwpicturefileName) {
		this.jwpicturefileName = jwpicturefileName;
	}

	public String getJwpictureimageFileName() {
		return jwpictureimageFileName;
	}

	public void setJwpictureimageFileName(String jwpictureimageFileName) {
		this.jwpictureimageFileName = jwpictureimageFileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public List<Joinwork> getListjoinwork() {
		return listjoinwork;
	}

	public void setListjoinwork(List<Joinwork> listjoinwork) {
		this.listjoinwork = listjoinwork;
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
	
	
}
