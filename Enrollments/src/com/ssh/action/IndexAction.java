package com.ssh.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Contact;
import com.ssh.pojo.Hotmajor;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Indexpicture;
import com.ssh.pojo.Notice;
import com.ssh.pojo.Teacher;
import com.ssh.service.ContactService;
import com.ssh.service.HotmajorService;
import com.ssh.service.IndexContentService;
import com.ssh.service.IndexPictureService;
import com.ssh.service.NoticeService;
import com.ssh.service.TeacherService;

@SuppressWarnings("serial")
/*@ParentPackage("json-default")
@Namespace("/")*/
public class IndexAction extends ActionSupport{
	private IndexContentService indexcontentService;
	private IndexPictureService indexpictureService;
	private ContactService contactService;
	private NoticeService noticeService;
	private HotmajorService hotmajorService;
	public TeacherService teacherService;
	private List<Indexpicture> listPicture;
	private List<Indexcontent> listContent;
	private List<Contact> listContact;
	private List<Notice> listNotice;
	private List<Hotmajor> listHotmajor;
	private int id;
	private Notice notice;
	private String tphone;
	
	
	public String listindex(){
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		listPicture = indexpictureService.getIndexpictureList();
		listContent = indexcontentService.getIndexcontentList(); 
		listContact = contactService.getContactList();
		listHotmajor = hotmajorService.getHotmajorList();
		
		listNotice  = noticeService.getNoticeList();
		Teacher teacher = new Teacher();
		if(tphone!=null){
			teacher = teacherService.getTeacher(tphone);
		}
		session.setAttribute("teacher", teacher);
		return SUCCESS;
	}
	/*查询首页公告详情
		public String selectNoticemore() throws IOException{
			listContent = indexcontentService.getIndexcontentList();
			listNotice = noticeService.getNoticeList();
			return SUCCESS;
		}*/
	//查询首页公告对象
	   public  String selectNoticeById(){
		   listContent = indexcontentService.getIndexcontentList();
		   notice = noticeService.getNotice(id);
		   return SUCCESS;
	   }
	
	   
	 //移除后台用户登录session
	 //@Action(value="RemoveTeacher",results={@Result(name="success",location="/backstage/index.jsp")})
    public String  removeTeacherSession(){
	   HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("teacher");
		return SUCCESS;
    }
    
    //移除前台用户登录session
    public String removeAllSession(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpSession session = request.getSession();
    	session.removeAttribute("teacher");
    	session.removeAttribute("signMsg");
    	session.removeAttribute("uploadMsg");
    	return SUCCESS;
    }
   
	public IndexContentService getIndexcontentService() {
		return indexcontentService;
	}
	public void setIndexcontentService(IndexContentService indexcontentService) {
		this.indexcontentService = indexcontentService;
	}
	public IndexPictureService getIndexpictureService() {
		return indexpictureService;
	}
	public void setIndexpictureService(IndexPictureService indexpictureService) {
		this.indexpictureService = indexpictureService;
	}
	public ContactService getContactService() {
		return contactService;
	}
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	public List<Indexpicture> getListPicture() {
		return listPicture;
	}
	public void setListPicture(List<Indexpicture> listPicture) {
		this.listPicture = listPicture;
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

	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public List<Notice> getListNotice() {
		return listNotice;
	}

	public void setListNotice(List<Notice> listNotice) {
		this.listNotice = listNotice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public String getTphone() {
		return tphone;
	}
	public void setTphone(String tphone) {
		this.tphone = tphone;
	}
	public HotmajorService getHotmajorService() {
		return hotmajorService;
	}
	public void setHotmajorService(HotmajorService hotmajorService) {
		this.hotmajorService = hotmajorService;
	}
	public List<Hotmajor> getListHotmajor() {
		return listHotmajor;
	}
	public void setListHotmajor(List<Hotmajor> listHotmajor) {
		this.listHotmajor = listHotmajor;
	}
	
}
