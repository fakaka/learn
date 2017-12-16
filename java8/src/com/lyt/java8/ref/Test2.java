package com.lyt.java8.ref;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) {
		ITest2 t1 = arr -> Arrays.sort(arr);
		// ITest2 t2 = arr -> Arrays::sort;
		int[] array = new int[] { 3, 2, 1 };
		t1.test(array);
		System.out.println(Arrays.toString(array));
	}

}

interface ITest2 {

	void test(int[] arr);

}
