package com.spring.boot.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class UserService {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public UserService(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public List<Map<String, Object>> getAdmins(){
		List<Map<String, Object>> a = jdbcTemplate.queryForList("select * from admin");
		System.out.println(String.format("获取的结果集为:%s", new Gson().toJson(a)));
		return a;
	}
	
	public void getArticle(){
		List<Map<String, Object>> a = jdbcTemplate.queryForList("select * from article");
		
		System.out.println(String.format("获取的结果集为:%s", new Gson().toJson(a)));
	}
	/**
	 * 测试事务
	 * 对jdbcTransactionManager有效
	 */
	@Transactional
	public void transactionTest(){
		String update_01 = "update article set title=? where articleId=1";
		String update_02 = "update article set title=? where articleId=3";
		try {
			jdbcTemplate.update(update_01, new Object[]{"更新第一个title1"});
			//throw new Exception("更新第一个的时候出错了...");
			jdbcTemplate.update(update_02, new Object[]{"更新第一个title3"});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
