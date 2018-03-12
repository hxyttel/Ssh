package com.ssh.service;

import java.util.List;

import com.ssh.pojo.Arts;

public interface ArtsService {
	Arts getArts();  //获取到艺考表信息
	Arts getArtsById(Integer asid);  //通过id得到Arts对象
	boolean update(Arts arts);  //修改艺考表
	List<Arts> getArtsList();
	int getAllArts();
}
