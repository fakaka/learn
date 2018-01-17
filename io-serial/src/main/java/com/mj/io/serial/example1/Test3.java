package com.mj.io.serial.example1;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Test3 {

	public static void main(String[] args) {

		ByteBuf buffer = Unpooled.buffer(8);
		buffer.writeInt(101);
		buffer.writeDouble(80.1);

		byte[] bytes = new byte[buffer.writerIndex()];
		buffer.readBytes(bytes);

		System.out.println(Arrays.toString(bytes));

		// ================================================
		ByteBuf wrappedBuffer = Unpooled.copiedBuffer(bytes);
		System.out.println(wrappedBuffer.readInt());
		System.out.println(wrappedBuffer.readDouble());

	}

}
