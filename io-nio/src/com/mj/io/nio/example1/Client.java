package com.mj.io.nio.example1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

	public void start() {
		try {
			SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 55555));
			client.configureBlocking(false);
			String a = "asdasdasdasddffasfas";
			ByteBuffer buffer = ByteBuffer.allocate(20);
			buffer.put(a.getBytes());
			buffer.clear();
			client.write(buffer);
			System.out.println("发送数据: " + new String(buffer.array()));
			while (true) {
				buffer.flip();
				int i = client.read(buffer);
				if (i > 0) {
					byte[] b = buffer.array();
					System.out.println("接收数据: " + new String(b));
					client.close();
					System.out.println("连接关闭!");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Client().start();
	}
}
