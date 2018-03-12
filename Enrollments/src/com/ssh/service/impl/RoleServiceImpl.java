package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.RoleDao;
import com.ssh.pojo.Jurisdication;
import com.ssh.pojo.Roels;
import com.ssh.pojo.Rolejur;
import com.ssh.service.RoleService;

public class RoleServiceImpl implements RoleService{
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Roels getRoels(int id) {
		return roleDao.getRoels(id);
	}

	@Override
	public boolean SaveRoels(Roels roels) {
		return roleDao.SaveRoels(roels);
	}

	@Override
	public boolean UpdateRoels(Roels roels) {
		return roleDao.UpdateRoels(roels);
	}

	@Override
	public boolean DeleteRoels(int id) {
		return roleDao.DeleteRoels(id);
	}

	@Override
	public List<Roels> getRoelsList() {
		return roleDao.getRoelsList();
	}

	@Override
	public int getAllCount() {
		return roleDao.getAllCount();
	}

	@Override
	public List<Jurisdication> getJurisdicationList() {
		return roleDao.getJurisdicationList();
	}

	@Override
	public int getJurisdicationCount() {
		return roleDao.getJurisdicationCount();
	}

	@Override
	public List<Integer> getRolejurList(int id) {
		return roleDao.getRolejurList(id);
	}

	@Override
	public Jurisdication getJurisdication(int id) {
		return roleDao.getJurisdication(id);
	}

	@Override
	public boolean saveRolejur(Integer rid, Integer[] ids) {
		return roleDao.saveRolejur(rid, ids);
	}

	@Override
	public boolean updateNotRolejur(Integer rid, Integer[] notids) {
		return roleDao.updateNotRolejur(rid, notids);
	}

	@Override
	public boolean updateRolejur(Integer rid, Integer[] ids, Integer[] notids) {
		return roleDao.updateRolejur(rid, ids, notids);
	}

	@Override
	public List<Jurisdication> seleJurisByJid(List<Integer> list) {
		return roleDao.seleJurisByJid(list);
	}

	@Override
	public List<Jurisdication> seleJuiisByidChird(int jid) {
		return roleDao.seleJuiisByidChird(jid);
	}

	
	
	
}
