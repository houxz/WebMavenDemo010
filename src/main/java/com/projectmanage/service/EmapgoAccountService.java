package com.projectmanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanage.dao.emapgoaccount.AuthorityModelDao;
import com.projectmanage.pojo.AuthorityModel;
import com.projectmanage.pojo.EmployeeModel;

@Service
public class EmapgoAccountService {

	final static private String CACHEVALUE = "EmapAccountCache";
	final static private String CACHEKEYGENERATOR = "baseCacheKeyGenerator";
	
	@Autowired
	private AuthorityModelDao authorityModelDao;
	
public AuthorityModel getAuthorityByUsername(String username) {
		
	
	
		//fot test;
		AuthorityModel au = new AuthorityModel();
		au.setId(417);
		au.setUsername("houxizhao");
		au.setEnabled(1);
		
		AuthorityModel auth = authorityModelDao.getAuthorityByUsername(username);
		
		return auth;
	}
	
//	@Cacheable(value = CACHEVALUE, keyGenerator = CACHEKEYGENERATOR)
	public EmployeeModel getOneEmployeeWithCache(EmployeeModel record) {
		///return employeeModelDao.getOneEmployee(record);
		return null;
	}
}
