package com.lyt.java8.ref;

import java.io.PrintStream;

public class Test3 {

	public static void main(String[] args) {
		ITest3 t1 = PrintStream::println;
		t1.test(System.out, "666");
	}

}

interface ITest3 {

	void test(PrintStream ps, String s);

}
