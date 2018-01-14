package com.mj.io.nio.example1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

	ServerSocketChannel ssc;

	public void start() {
		try {
			Selector selector = Selector.open();
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ServerSocket ss = ssc.socket();
			ss.bind(new InetSocketAddress(55555));
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("端口注册完毕!");
			listen(selector);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listen(Selector selector) throws IOException, ClosedChannelException {
		while (true) {
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iter = selectionKeys.iterator();
			ByteBuffer echoBuffer = ByteBuffer.allocate(20);
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				handleKey(selector, echoBuffer, key);
				iter.remove();
			}
		}
	}

	private void handleKey(Selector selector, ByteBuffer echoBuffer, SelectionKey key) throws IOException, ClosedChannelException {
		SocketChannel sc = null;
		if (key.isAcceptable()) {
			ServerSocketChannel subssc = (ServerSocketChannel) key.channel();
			sc = subssc.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
			System.out.println("有新连接:" + sc);
		} else if (key.isReadable()) {
			sc = (SocketChannel) key.channel();
			while (true) {
				echoBuffer.clear();
				int len;
				try {
					len = sc.read(echoBuffer);
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
				if (len > 0) {
					byte[] b = echoBuffer.array();
					System.out.println("接收数据: " + new String(b));
					echoBuffer.flip();
					sc.write(echoBuffer);
					System.out.println("返回数据: " + new String(b));
				}
			}
			System.out.println("连接结束");
			System.out.println("=============================");
		}
		sc.close();
	}

	public static void main(String[] args) {
		new Server().start();
	}
}
