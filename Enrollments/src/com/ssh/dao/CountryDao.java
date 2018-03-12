package com.ssh.dao;

import java.util.List;

import com.ssh.pojo.Adult;
import com.ssh.pojo.Country;

public interface CountryDao {
	Country getCountry();  //获取到国家开放表信息
	Country getCountryById(Integer cyid);  //通过id得到Country对象
	boolean update(Country country);  //修改国家开放表
	List<Country> getCountryList();
	int getAllCountry();
}
