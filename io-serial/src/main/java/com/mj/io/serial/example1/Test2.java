package com.mj.io.serial.example1;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) {
		int id = 102;
		int age = 22;

		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putInt(id);
		buffer.putInt(age);
		byte[] array = buffer.array();
		System.out.println(Arrays.toString(buffer.array()));

		// ====================================================

		ByteBuffer buffer2 = ByteBuffer.wrap(array);
		System.out.println("id: " + buffer2.getInt());
		System.out.println("age: " + buffer2.getInt());
	}
}
