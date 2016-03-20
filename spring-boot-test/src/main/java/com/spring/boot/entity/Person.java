package com.spring.boot.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author zhangxf
 *
 */
// 如果使用该注解，那么返回结果均为xml格式的
// @XmlRootElement
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
