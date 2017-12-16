package com.lyt.java8.ref;

public class Test1 {

	public static void main(String[] args) {
		ITest1 t1 = s -> {
			System.out.println(s);
		};
		t1.test("test1");

		ITest1 t2 = System.out::println;
		t2.test("test2");

	}

}

interface ITest1 {

	void test(String s);

}
