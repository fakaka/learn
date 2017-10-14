package com.lyt.java8.lamda;

import java.util.ArrayList;
import java.util.List;

public class LamdaTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		list.add("1");
		list.add("2");
		list.add("3");

		list.forEach((ele) -> {
			System.out.println(ele);
		});
		
	}
}
