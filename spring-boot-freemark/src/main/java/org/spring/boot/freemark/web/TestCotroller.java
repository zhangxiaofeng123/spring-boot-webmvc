package org.spring.boot.freemark.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author zhangxf
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestCotroller {

	@RequestMapping(value="/001")
	public ModelAndView test(){
		//Map<String, Object> model
		ModelAndView mv = new ModelAndView();
		String sayHello = "hello";
		int age = 20;
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
		mv.setViewName("/welcome");
		return mv;
	}
	
	@RequestMapping(value="/002")
	public String test(Map<String,Object> model, Map<String,Object> test){
		//Map<String, Object> model
		ModelAndView mv = new ModelAndView();
		String sayHello = "hello";
		int age = 20;
		List<String> courses = new ArrayList<String>();
		courses.add("语文");
		courses.add("数学");
		courses.add("英语");
		
		Map<String, Object> v= new HashMap<String, Object>();
		v.put("math", 98);
		v.put("chinese", "100");
		model.put("msg", sayHello);
		String a="123";
		test.put("a", a);
		test.put("b", "abc");
		
//		model.addObject("courses", courses);
//		model.addObject("age", age);
//		model.addObject("values", v);
		//model.setViewName("/welcome");
		return "welcome";
	}
}
