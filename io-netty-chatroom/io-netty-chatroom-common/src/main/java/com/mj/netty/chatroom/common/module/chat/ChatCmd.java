package com.mj.netty.chatroom.common.module.chat;

/**
 * 聊天模块
 * 
 * @author MJ
 *
 */
public interface ChatCmd {

	/**
	 * 广播消息
	 */
	short PUBLIC_CHAT = 1;

	/**
	 * 私人消息
	 */
	short PRIVATE_CHAT = 2;

	/**
	 * 消息推送命令
	 */
	short PUSHCHAT = 101;

}
