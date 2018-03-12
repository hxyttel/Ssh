package com.ssh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ssh.dao.RoleDao;
import com.ssh.pojo.Jurisdication;
import com.ssh.pojo.Roels;
import com.ssh.pojo.Rolejur;

public class RoleDaoImpl implements RoleDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Roels getRoels(int id) {
		Roels roels = hibernateTemplate.get(Roels.class, id);
		return roels;
	}

	@Override
	public boolean SaveRoels(Roels roels) {
		boolean isSave = false;
		try {
			hibernateTemplate.save(roels);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean UpdateRoels(Roels roels) {
		boolean isSave = false;
		try {
			hibernateTemplate.update(roels);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean DeleteRoels(int id) {
		boolean isSave = false;
		try {
			Roels roels=hibernateTemplate.get(Roels.class, id);
			hibernateTemplate.delete(roels);
			isSave =true;
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public List<Roels> getRoelsList() {
		List<Roels> list= hibernateTemplate.find("from Roels");
		return list;
	}

	@Override
	public int getAllCount() {
		int count=0;
		List<Roels> list = hibernateTemplate.find("from Roels");
		if(list!=null&&list.size()>0){
			count = list.size();
            return count;
        }
		return count;
	}

	@Override
	public List<Jurisdication> getJurisdicationList() {
		List<Jurisdication> list= hibernateTemplate.find("from Jurisdication");
		return list;
	}

	@Override
	public int getJurisdicationCount() {
		int count=0;
		List<Roels> list = hibernateTemplate.find("from Jurisdication");
		if(list!=null&&list.size()>0){
			count = list.size();
            return count;
        }
		return count;
	}

	@Override
	public List<Integer> getRolejurList(int id) {
		List<Integer> list = hibernateTemplate.find("select r.jid from Rolejur r where r.whether=1 and  r.rid=?",id);
		return list;
	}

	@Override
	public Jurisdication getJurisdication(int id) {
		Jurisdication jurisdication = hibernateTemplate.get(Jurisdication.class, id);
		return jurisdication;
	}

	@Override
	public boolean saveRolejur(Integer rid,Integer[] ids) {
		boolean isSave = false;
		try {
			for (Integer integer : ids) {
				List<Rolejur> list = hibernateTemplate.find("from Rolejur r where r.rid="+rid+" and r.jid ="+integer);
				Rolejur rj = list.get(0);
				Rolejur r = new Rolejur();
				r.setRjid(rj.getRjid());
				r.setRid(rj.getRid());
				r.setJid(rj.getJid());
				r.setWhether(1);
				hibernateTemplate.update(r);
				isSave =true;
			}
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean updateNotRolejur(Integer rid,Integer[] notids ) {
		boolean isSave = false;
		try {
			for (Integer integer : notids) {
				List<Rolejur> list = hibernateTemplate.find("from Rolejur r where r.rid="+rid+" and r.jid ="+integer);
				Rolejur rj = list.get(0);
				Rolejur r = new Rolejur();
				r.setRjid(rj.getRjid());
				r.setRid(rj.getRid());
				r.setJid(rj.getJid());
				r.setWhether(0);
				hibernateTemplate.update(r);
				isSave =true;
			}
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	@Override
	public boolean updateRolejur(Integer rid, Integer[] ids, Integer[] notids) {
		boolean isSave = false;
		try {
			for (Integer integer : ids) {
				List<Rolejur> list = hibernateTemplate.find("from Rolejur r where r.rid="+rid+" and r.jid ="+integer);
				Rolejur rj = list.get(0);
				Rolejur r = new Rolejur();
				r.setRjid(rj.getRjid());
				r.setRid(rj.getRid());
				r.setJid(rj.getJid());
				r.setWhether(1);
				hibernateTemplate.update(r);
			}
			for (Integer notinteger : notids) {
				List<Rolejur> notlist = hibernateTemplate.find("from Rolejur r where r.rid="+rid+" and r.jid ="+notinteger);
				Rolejur notrj = notlist.get(0);
				Rolejur notr = new Rolejur();
				notr.setRjid(notrj.getRjid());
				notr.setRid(notrj.getRid());
				notr.setJid(notrj.getJid());
				notr.setWhether(0);
				hibernateTemplate.update(notr);
				isSave =true;
			}
		} catch (Exception e) {
			isSave = false;
			e.printStackTrace();
		}
		return isSave;
	}

	public List<Jurisdication> seleJurisByJid(List<Integer> list) {
		 List<Jurisdication> listPJurisd = new ArrayList<Jurisdication>();
			for (Integer integer : list) {
			List<Jurisdication>  lis = hibernateTemplate.find("from Jurisdication j where j.jtsort = 0 and j.jtid=?",integer);
			if(lis.size()>0){
				for (Jurisdication jurisdication : lis) {
					List<Rolejur> r = hibernateTemplate.find("from Rolejur r where r.jid="+jurisdication.getJtid());
					if(r.get(0).getWhether()==1){
						listPJurisd.add(jurisdication);
					}
				}
			}
			}
			return listPJurisd;
	}

	@Override
	public List<Jurisdication> seleJuiisByidChird(int jid) {
		List<Jurisdication> listCJurisd = new ArrayList<Jurisdication>();
		List<Jurisdication> list = hibernateTemplate.find("from Jurisdication j where  j.jtsort=?",jid);
		for (Jurisdication jurisdication : list) {
			List<Rolejur> r = hibernateTemplate.find("from Rolejur r where r.jid="+jurisdication.getJtid());
			if(r.get(0).getWhether()==1){
				listCJurisd.add(jurisdication);
			}
		}
		return listCJurisd;
	}
	
}
