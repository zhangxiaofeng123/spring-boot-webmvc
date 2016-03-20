package com.spring.boot.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import com.spring.boot.service.FoodService;
import com.spring.boot.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	private FoodService foodService;
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	@RequestMapping("/")
	public void index(){
		//foodService.test();
		foodService.testTx();
		System.out.println(String.format("欢迎, %s, 进入主页", "admin"));
	}
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ModelAndView test(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tmp");
		List<Map<String, Object>> ma = userService.getAdmins();
		mv.addObject("admins", ma);
		
		return mv;
	}
	
	@RequestMapping(value="/test0")
	public ModelAndView test0(){
		
		userService.transactionTest();
		
		ModelAndView mv = new ModelAndView();
		String sayHello = "hello";
		int age = 20;
		Date now = new Date();
		List<String> courses = new ArrayList<String>();
		courses.add("语文");
		courses.add("数学");
		courses.add("英语");
		
		Map<String, Object> v= new HashMap<String, Object>();
		v.put("math", 98);
		v.put("chinese", "100");
		mv.addObject("msg", sayHello);
		mv.addObject("courses", courses);
		mv.addObject("age", age);
		mv.addObject("values", v);
		mv.addObject("now", now);
		mv.setViewName("tmp");
		return mv;
	}
	@RequestMapping(value="/test1")
	public String test1(){
		System.out.println("dfsdfd");
		userService.getArticle();
		return "测试";
	}
	
}
