package com.ssh.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.pojo.Accounting;
import com.ssh.pojo.Adult;
import com.ssh.pojo.Arts;
import com.ssh.pojo.Contact;
import com.ssh.pojo.Country;
import com.ssh.pojo.Distance;
import com.ssh.pojo.Indexcontent;
import com.ssh.pojo.Vocational;
import com.ssh.service.AccountingService;
import com.ssh.service.AdultService;
import com.ssh.service.ArtsService;
import com.ssh.service.ContactService;
import com.ssh.service.CountryService;
import com.ssh.service.DistanceService;
import com.ssh.service.IndexContentService;
import com.ssh.service.VocationalService;

//综合所有报名的Action
@SuppressWarnings("serial")
public class AlltypeAction extends ActionSupport{
	private IndexContentService indexcontentService;
	private ContactService contactService;
	private AccountingService accountingService;
	private ArtsService artsService;
	private VocationalService vocationalService;
	private AdultService adultService;
	private CountryService countryService;
	private DistanceService distanceService;
	private List<Indexcontent> listContent;
	private List<Contact> listContact;
	
	public void getAlltype(){
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		listContent = indexcontentService.getIndexcontentList();
		listContact = contactService.getContactList();
		Accounting accounting = accountingService.getAccounting();
		Arts arts = artsService.getArts();
		Vocational vocational = vocationalService.getVocational();
		Adult adult = adultService.getAdult();
		Country country = countryService.getCountry();
		Distance distance = distanceService.getDistance();
		session.setAttribute("accounting", accounting);
		session.setAttribute("arts", arts);
		session.setAttribute("vocational", vocational);
		session.setAttribute("adult", adult);
		session.setAttribute("country", country);
		session.setAttribute("distance", distance);
		try {
			req.getRequestDispatcher("alltype.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AccountingService getAcountingService() {
		return accountingService;
	}
	public void setAcountingService(AccountingService accountingService) {
		this.accountingService = accountingService;
	}
	public ArtsService getArtsService() {
		return artsService;
	}
	public void setArtsService(ArtsService artsService) {
		this.artsService = artsService;
	}
	public VocationalService getVocationalService() {
		return vocationalService;
	}
	public void setVocationalService(VocationalService vocationalService) {
		this.vocationalService = vocationalService;
	}
	public AdultService getAdultService() {
		return adultService;
	}
	public void setAdultService(AdultService adultService) {
		this.adultService = adultService;
	}
	public CountryService getCountryService() {
		return countryService;
	}
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
	public DistanceService getDistanceService() {
		return distanceService;
	}
	public void setDistanceService(DistanceService distanceService) {
		this.distanceService = distanceService;
	}

	public AccountingService getAccountingService() {
		return accountingService;
	}

	public void setAccountingService(AccountingService accountingService) {
		this.accountingService = accountingService;
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
	
}
