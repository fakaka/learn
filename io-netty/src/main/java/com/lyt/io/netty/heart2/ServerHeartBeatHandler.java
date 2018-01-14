
package com.lyt.io.netty.heart2;

import java.util.HashMap;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHeartBeatHandler extends ChannelHandlerAdapter {

	/** key:ip value:auth */
	private static HashMap<String, String> AUTH_IP_MAP = new HashMap<>();

	private static final String SUCCESS_KEY = "auth_success_key";

	static {
		AUTH_IP_MAP.put("169.254.177.115", "1234");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof String) {
			auth(ctx, (String) msg);
		} else if (msg instanceof RequestInfo) {

			RequestInfo info = (RequestInfo) msg;
			System.out.println("--------------------------------------------");
			System.out.println("当前主机ip为: " + info.getIp());
			System.out.println("--------------------------------------------");

			ctx.writeAndFlush("info received!");
		} else {
			ctx.writeAndFlush("connect failure!").addListener(ChannelFutureListener.CLOSE);
		}
	}

	private boolean auth(ChannelHandlerContext ctx, String msg) {
		System.out.println(msg);
		String[] ret = msg.split(","); // ip -> key
		String auth = AUTH_IP_MAP.get(ret[0]);
		if (auth != null && auth.equals(ret[1])) {
			ctx.writeAndFlush(SUCCESS_KEY);
			return true;
		} else {
			ctx.writeAndFlush("auth failure !").addListener(ChannelFutureListener.CLOSE);
			return false;
		}
	}

}
