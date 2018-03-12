package com.ssh.service.impl;

import java.util.List;

import com.ssh.dao.CountryDao;
import com.ssh.pojo.Country;
import com.ssh.service.CountryService;

public class CountryServiceImpl implements CountryService {
	private CountryDao countryDao;

	//获取到国家开放表信息
	public Country getCountry() {
		return countryDao.getCountry();
	}

	//通过id得到Country对象
	public Country getCountryById(Integer cyid) {
		return countryDao.getCountryById(cyid);
	}

	//修改国家开放表
	public boolean update(Country country) {
		return countryDao.update(country);
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	@Override
	public List<Country> getCountryList() {
		return countryDao.getCountryList();
	}

	@Override
	public int getAllCountry() {
		return countryDao.getAllCountry();
	}
	
}
