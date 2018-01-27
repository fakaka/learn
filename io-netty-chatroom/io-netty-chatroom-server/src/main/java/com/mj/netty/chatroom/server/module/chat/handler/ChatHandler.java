package com.mj.netty.chatroom.server.module.chat.handler;

import com.mj.netty.chatroom.common.core.annotion.SocketCommand;
import com.mj.netty.chatroom.common.core.annotion.SocketModule;
import com.mj.netty.chatroom.common.core.model.Result;
import com.mj.netty.chatroom.common.module.ModuleId;
import com.mj.netty.chatroom.common.module.chat.ChatCmd;
import com.mj.netty.chatroom.common.module.chat.request.PrivateChatRequest;
import com.mj.netty.chatroom.common.module.chat.request.PublicChatRequest;

/**
 * 聊天
 * 
 * @author MJ
 *
 */
@SocketModule(module = ModuleId.CHAT)
public interface ChatHandler {

	/**
	 * 广播消息
	 * 
	 * @param playerId
	 * @param data
	 *            {@link PublicChatRequest}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PUBLIC_CHAT)
	public Result<?> publicChat(long playerId, byte[] data);

	/**
	 * 私人消息
	 * 
	 * @param playerId
	 * @param data
	 *            {@link PrivateChatRequest}
	 * @return
	 */
	@SocketCommand(cmd = ChatCmd.PRIVATE_CHAT)
	public Result<?> privateChat(long playerId, byte[] data);
}
