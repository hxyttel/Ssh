package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Jurisdication;
import com.ssh.pojo.Roels;
import com.ssh.pojo.Rolejur;

public interface RoleService {
	public abstract Roels  getRoels(int id);//得到角色表对象
	public abstract boolean SaveRoels(Roels roels); //保存角色表对象
	public abstract boolean UpdateRoels(Roels roels);//修改角色表
	public abstract boolean DeleteRoels(int id);//删除角色表
	public List<Roels> getRoelsList();//遍历角色表
	public abstract int getAllCount();//得到一共多少条数据
	public abstract List<Jurisdication> getJurisdicationList();//遍历权限表
	public abstract int getJurisdicationCount();//得到权限表的数据
	public abstract List<Integer> getRolejurList(int id);//根据角色id查询有哪些权限
	public abstract Jurisdication getJurisdication(int id);//根据权限id查权限表；
	public abstract boolean saveRolejur(Integer rid,Integer[] ids); //修改权限
	public abstract boolean updateNotRolejur(Integer rid,Integer[] notids);//修改没有选中的
	public abstract boolean updateRolejur(Integer rid,Integer[] ids,Integer[] notids);//修改没有选中的和没有选中的
	public abstract List<Jurisdication> seleJurisByJid(List<Integer> list); //根据Jid的数组查询权限表的集合
	public abstract List<Jurisdication> seleJuiisByidChird(int jid);//根据Jid的数组查询权限子集合
}
