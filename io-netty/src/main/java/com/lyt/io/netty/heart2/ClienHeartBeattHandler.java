package com.lyt.io.netty.heart2;

import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClienHeartBeattHandler extends ChannelHandlerAdapter {

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private ScheduledFuture<?> heartBeat;

	// 主动向服务器发送认证信息
	private InetAddress addr;

	private static final String SUCCESS_KEY = "auth_success_key";

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();
		String key = "1234";
		// 证书
		String auth = ip + "," + key;
		ctx.writeAndFlush(auth);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			if (msg instanceof String) {
				String ret = (String) msg;
				if (SUCCESS_KEY.equals(ret)) {
					// 握手成功，主动发送心跳消息
					System.out.println("握手成功，主动发送心跳消息");
					heartBeat = scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 5, TimeUnit.SECONDS);
				} else {
					System.out.println(msg);
				}
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	private class HeartBeatTask implements Runnable {

		private final ChannelHandlerContext ctx;

		public HeartBeatTask(final ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			try {
				RequestInfo info = new RequestInfo();
				// ip
				info.setIp(addr.getHostAddress());

				ctx.writeAndFlush(info.toString());

			} catch (Exception e) {
				e.printStackTrace();
				if (heartBeat != null) {
					heartBeat.cancel(true);
					heartBeat = null;
				}
				ctx.fireExceptionCaught(e);
			}
		}

	}
}
