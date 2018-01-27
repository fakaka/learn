package com.mj.netty.chatroom.common.core.coder;

import com.mj.netty.chatroom.common.core.model.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * |  包头	|  模块号      |  命令号    |   长度     |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 
 * @author MJ
 *
 */
public class RequestEncoder extends MessageToByteEncoder<Request> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Request message, ByteBuf buffer) throws Exception {

		// 包头
		buffer.writeInt(ConstantValue.HEADER_FLAG);
		// module
		buffer.writeShort(message.getModule());
		// cmd
		buffer.writeShort(message.getCmd());
		// 长度
		int length = message.getData() == null ? 0 : message.getData().length;
		if (length <= 0) {
			buffer.writeInt(length);
		} else {
			buffer.writeInt(length);
			buffer.writeBytes(message.getData());
		}
	}
}
