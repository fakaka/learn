package com.lyt.java8.ref;

public class Test4 {

	public static void main(String[] args) {
		ITest4 t1 = String::new;
		String result = t1.test(new char[] { 'a', 'b' });
		System.out.println(result);
	}

}

interface ITest4 {

	String test(char[] s);

}
