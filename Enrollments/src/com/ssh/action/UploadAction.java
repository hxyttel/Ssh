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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Contact;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Student;
import com.ssh.pojo.StudentFile;
import com.ssh.service.ContactService;
import com.ssh.service.IndexContentService;
import com.ssh.service.StudentService;
import com.ssh.service.UploadService;

@SuppressWarnings("serial")

//文件上传
public class UploadAction extends ActionSupport{
	private UploadService studentfileService;
	private IndexContentService indexcontentService;
	private ContactService contactService;
	private StudentService studentService;
	private List<Indexcontent> listContent;
	private List<Contact> listContact;
	private String data;
	private int id;
	
	private StudentFile studentfile;
	
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	private File sfphoto; // 上传相片
	private File sfvoucher; // 上传缴费凭证
	private File sfpaper; // 上传论文
	
	private String sfphotocontentType;// 上传文件类型
	private String sfphotofileName; // 上传文件名
	private String sfphotoimageFileName;
	
	private String sfvouchercontentType;// 上传文件类型
	private String sfvoucherfileName; // 上传文件名
	private String sfvoucherimageFileName;
	
	private String sfpapercontentType;// 上传文件类型
	private String sfpaperfileName; // 上传文件名
	private String sfpaperimageFileName;
	
	private int sid; //学生id
	private String sname;//学生姓名
	private String sfdate;//时间
	private String sfid;
	
	public void setSfphotoContentType(String sfphotocontentType) {
		this.sfphotocontentType =sfphotocontentType;
	}
	
	public void setSfphotoFileName(String sfphotofileName) {
		this.sfphotofileName =sfphotofileName;
	}
	
	public void setSfpaperContentType(String sfvouchercontentType) {
		this.sfvouchercontentType =sfvouchercontentType;
	}
	
	public void setSfpaperFileName(String sfvoucherfileName) {
		this.sfvoucherfileName =sfvoucherfileName;
	}
	
	public void setSfvoucherContentType(String sfpapercontentType) {
		this.sfpapercontentType =sfpapercontentType;
	}
	
	public void setSfvoucherFileName(String sfpaperfileName) {
		this.sfpaperfileName =sfpaperfileName;
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
	public String addUpload() throws Exception{
		String addResult = "fail";
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		Map<String,Object> map = new HashMap<String, Object>();
		sfphotoimageFileName =sfphotofileName;
		sfvoucherimageFileName =sfvoucherfileName;
		sfpaperimageFileName = sfpaperfileName;
		File  sfphotoimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + sfphotoimageFileName);
		File  sfvoucherimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + sfvoucherimageFileName);
		File  sfpaperimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + sfpaperimageFileName);
		copy(sfphoto,sfphotoimageFile);
		copy(sfvoucher,sfvoucherimageFile);
		copy(sfpaper, sfpaperimageFile);
		StudentFile sfile = new StudentFile();
		sfile.setSfphoto("backstage/upload/"+sfphotofileName);
		sfile.setSfvoucher("backstage/upload/"+sfvoucherfileName);
		sfile.setSfpaper("backstage/upload/"+sfpaperfileName);
		Date date = new Date();
		SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sfile.setSfdate(fmat.format(date));
		boolean isSave = false;
		if(sid==0){
			isSave = false;
		}else{
			Student stu =studentService.getStudentById(sid);
			sfile.setStudent(stu);
			sfile.setSid(stu.getSid());
			isSave = studentfileService.SaveStudentFile(sfile);
		}
		if(isSave){
			session.setAttribute("uploadMsg","上传成功!");
			session.removeAttribute("student");
			addResult = SUCCESS;
		}else{
			session.setAttribute("uploadMsg","学生没有报名,上传失败!");
		}
		return addResult;
	}
	
	//移除session中的值(当跳出报名成功后便移除success)
	public void removeUpload(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("uploadMsg");
	}
	
	//遍历文件
	public  void listUpload() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		Map<String,Object> map = new HashMap<String,Object>();
		int count = studentfileService.getAllCount();
		List<StudentFile> lis =studentfileService.getStudentFileList();
		List<StudentFile> li  = new ArrayList<StudentFile>();
		for(int i=0;i<lis.size();i++){
			studentfile = lis.get(i);
			StudentFile sfile = new StudentFile();
			sfile.setSfid(studentfile.getSfid());
			sfile.setSfphoto(studentfile.getSfphoto());
			sfile.setSfvoucher(studentfile.getSfvoucher());
			sfile.setSfdate(studentfile.getSfdate());
			sfile.setSfpaper(studentfile.getSfpaper());
			int sid =studentfile.getSid();
			Student stu =studentService.getStudentById(sid);
			sfile.setSid(studentfile.getSid());
			sfile.setSname(stu.getSname());
			li.add(sfile);
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
	public  void likeUpload() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));
		String sname = request.getParameter("sname");
		int ssid = 0;
		if(sname=="" || sname==null){
			ssid=0;
		}else if(sname.isEmpty()){
			ssid=0;
		}else{
			ssid = studentService.getIdByName(sname);
		}
		List<StudentFile> lis =studentfileService.listLikeStudentFile(ssid,page,row);
		List<StudentFile> li  = new ArrayList<StudentFile>();
		for(int i=0;i<lis.size();i++){
			studentfile = lis.get(i);
			StudentFile sfile = new StudentFile();
			sfile.setSfid(studentfile.getSfid());
			sfile.setSfphoto(studentfile.getSfphoto());
			sfile.setSfvoucher(studentfile.getSfvoucher());
			sfile.setSfdate(studentfile.getSfdate());
			sfile.setSfpaper(studentfile.getSfpaper());
			int sid =studentfile.getSid();
			Student stu =studentService.getStudentById(sid);
			sfile.setSname(stu.getSname());
			li.add(sfile);
		}
		Map<String,Object> map = new HashMap<String,Object>();
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
	public void findUpload() throws IOException{
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		StudentFile sfile = studentfileService.getStudentFile(id);
		StudentFile s = new StudentFile();
		s.setSfid(sfile.getSfid());
		s.setSfphoto(sfile.getSfphoto());
		s.setSfvoucher(sfile.getSfvoucher());
		s.setSfdate(sfile.getSfdate());
		s.setSfpaper(sfile.getSfpaper());
		s.setSid(sfile.getSid());
		String mapJson = JSON.toJSONString(s);
		PrintWriter out = resp.getWriter();
		out.print(mapJson);
		out.flush();
		out.close();
	}
	//修改文件
	public String updateUpload(){
		HttpServletRequest req = ServletActionContext.getRequest();
		String id = req.getParameter("sfid");
		String sid =req.getParameter("sid");
		Map<String,Object> map = new HashMap<String, Object>();
		sfphotoimageFileName =sfphotofileName;
		sfvoucherimageFileName =sfvoucherfileName;
		sfpaperimageFileName = sfpaperfileName;
		File  sfphotoimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + sfphotoimageFileName);
		File  sfvoucherimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + sfvoucherimageFileName);
		File  sfpaperimageFile = new File(
				ServletActionContext.getServletContext().getRealPath("backstage/upload")+  "/" + sfpaperimageFileName);
		copy(sfphoto,  sfphotoimageFile);
		copy(sfvoucher,sfvoucherimageFile);
		copy(sfpaper, sfpaperimageFile);
		StudentFile sfile = new StudentFile();
		sfile.setSfid(Integer.parseInt(id));
		sfile.setSfphoto("backstage/upload/"+sfphotofileName);
		sfile.setSfvoucher("backstage/upload/"+sfvoucherfileName);
		sfile.setSfpaper("backstage/upload/"+sfpaperfileName);
		sfile.setSfdate(sfdate);
		sfile.setSid(Integer.parseInt(sid));
		boolean isSave = studentfileService.UpdateStudentFile(sfile);
		if(isSave){
			map.put("msg","true");
			data = JSON.toJSONString(map);
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//删除文件
	public String deleteUpload(){
		Map<String,Object> map = new HashMap<String, Object>();
		boolean isDelete = studentfileService.DeleteStudentFile(id);
		if(isDelete){
			map.put("msg","true");
			data = JSON.toJSONString(map);
			return SUCCESS;
		}else{
			data = JSON.toJSONString(map);
		}
		return SUCCESS;
	}
	//得到尾部和侧边栏内容
	public String Upload(){
		listContent = indexcontentService.getIndexcontentList(); 
		listContact = contactService.getContactList(); 
		return SUCCESS;
	}
	public File getSfphoto() {
		return sfphoto;
	}

	public void setSfphoto(File sfphoto) {
		this.sfphoto = sfphoto;
	}

	public File getSfvoucher() {
		return sfvoucher;
	}

	public void setSfvoucher(File sfvoucher) {
		this.sfvoucher = sfvoucher;
	}

	public File getSfpaper() {
		return sfpaper;
	}

	public void setSfpaper(File sfpaper) {
		this.sfpaper = sfpaper;
	}

	public String getSfphotofileName() {
		return sfphotofileName;
	}

	public void setSfphotofileName(String sfphotofileName) {
		this.sfphotofileName = sfphotofileName;
	}
	
	public String getSfphotoimageFileName() {
		return sfphotoimageFileName;
	}

	public void setSfphotoimageFileName(String sfphotoimageFileName) {
		this.sfphotoimageFileName = sfphotoimageFileName;
	}

	public String getSfvoucherfileName() {
		return sfvoucherfileName;
	}

	public void setSfvoucherfileName(String sfvoucherfileName) {
		this.sfvoucherfileName = sfvoucherfileName;
	}

	public String getSfvoucherimageFileName() {
		return sfvoucherimageFileName;
	}

	public void setSfvoucherimageFileName(String sfvoucherimageFileName) {
		this.sfvoucherimageFileName = sfvoucherimageFileName;
	}

	public String getSfpaperfileName() {
		return sfpaperfileName;
	}

	public void setSfpaperfileName(String sfpaperfileName) {
		this.sfpaperfileName = sfpaperfileName;
	}

	public String getSfpaperimageFileName() {
		return sfpaperimageFileName;
	}

	public void setSfpaperimageFileName(String sfpaperimageFileName) {
		this.sfpaperimageFileName = sfpaperimageFileName;
	}
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public UploadService getStudentfileService() {
		return studentfileService;
	}

	public void setStudentfileService(UploadService studentfileService) {
		this.studentfileService = studentfileService;
	}
	public IndexContentService getIndexcontentService() {
		return indexcontentService;
	}
	public void setIndexcontentService(IndexContentService indexcontentService) {
		this.indexcontentService = indexcontentService;
	}
	public List<Indexcontent> getListContent() {
		return listContent;
	}
	public void setListContent(List<Indexcontent> listContent) {
		this.listContent = listContent;
	}
	public StudentFile getStudentfile() {
		return studentfile;
	}
	public void setStudentfile(StudentFile studentfile) {
		this.studentfile = studentfile;
	}
	
	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public List<Contact> getListContact() {
		return listContact;
	}

	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String getSfdate() {
		return sfdate;
	}

	public void setSfdate(String sfdate) {
		this.sfdate = sfdate;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	
}
