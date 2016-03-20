package com.spring.boot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spring.boot.entity.Food;
import com.spring.boot.respository.CustMongoRespository;

@Service
public class FoodService {
		
	@Autowired
	private CustMongoRespository custMongoRespository;
	
	public void test(){
		Food food = new Food();
		food.setName("测试");
		food.setPrice(100.00);
		food.setRemark("这是一个测试商品!");
		custMongoRespository.save(food);
		System.out.println(String.format("商品被添加成功...%s", "ok"));
		
		Food f = custMongoRespository.findByName("测试");
		System.out.println(String.format("查询的添加的商品为:%s", new Gson().toJson(f)));
		
		f.setName("美年达商品");
		Food savedEntity = custMongoRespository.save(f);
		System.out.println(String.format("查询的修改过的商品为:%s", new Gson().toJson(savedEntity)));
		
	}
	
	@Transactional
	public void testTx(){
		
		Food f = custMongoRespository.findByName("美年达商品");
		System.out.println(String.format("查询的添加的商品为:%s", new Gson().toJson(f)));
		
		f.setPrice(10);
		
		// 更新商品
		Food f2 = custMongoRespository.save(f);
		
		System.out.println(String.format("查询的修改过的商品为:%s", new Gson().toJson(f2)));
		
		String a = null;
		System.out.println(a.toString());
		
		// 删除商品
		custMongoRespository.delete(f);
		
		Food f3 = custMongoRespository.findByName("美年达商品");
		System.out.println(String.format("查询的修改过的商品2的商品为:%s", new Gson().toJson(f3)));
	
		
	}
}
