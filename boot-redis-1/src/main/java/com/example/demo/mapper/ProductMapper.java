package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Product;

@Mapper
public interface ProductMapper {

	Product select(@Param("id") long id);

	void update(Product product);
}
