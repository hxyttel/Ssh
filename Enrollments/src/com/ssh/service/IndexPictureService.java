package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Indexpicture;

public interface IndexPictureService {
	public abstract Indexpicture  getIndexpicture(int id);//得到轮播图对象
	public abstract boolean SaveIndexpicture(Indexpicture indexpicture); //保存轮播图对象
	public abstract boolean UpdateIndexpicture(Indexpicture indexpicture);//修改轮播图
	public abstract boolean DeleteIndexpicture(int id);//删除轮播图
	public List<Indexpicture> getIndexpictureList();//遍历轮播图
	public abstract int getAllCount();//得到一共多少条数据
}
