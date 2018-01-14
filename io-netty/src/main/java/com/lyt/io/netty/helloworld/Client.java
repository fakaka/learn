package com.lyt.io.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public static void main(String[] args) throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				sc.pipeline().addLast(new ClientHandler());
			}
		});

		ChannelFuture channelFuture = b.connect("127.0.0.1", 8765).sync();
		// 发送消息
		Thread.sleep(1000);
		channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
		channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
		Thread.sleep(2000);
		channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));

		channelFuture.channel().closeFuture().sync();
		group.shutdownGracefully();

	}
}
