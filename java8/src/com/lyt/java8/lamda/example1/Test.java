package com.lyt.java8.lamda.example1;

public class Test {

	public static void main(String[] args) {

		UserInterface u = () -> "mj";
		System.out.println(u.name());

		UserInterface2 u2 = n -> "hello " + n;
		System.out.println(u2.name("mj"));

		UserInterface3 u3 = (f, t) -> f + " like " + t;
		System.out.println(u3.name("mj", "lyt"));

	}
}
