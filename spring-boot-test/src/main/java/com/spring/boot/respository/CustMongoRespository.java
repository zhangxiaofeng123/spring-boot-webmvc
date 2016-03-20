package com.spring.boot.respository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Food;

@Repository
public interface CustMongoRespository extends MongoRepository<Food, String>{
	 public Food findByName(String name);
}
