package com.mj.netty.chatroom.server.module.player.handler;

import com.mj.netty.chatroom.common.core.annotion.SocketCommand;
import com.mj.netty.chatroom.common.core.annotion.SocketModule;
import com.mj.netty.chatroom.common.core.model.Result;
import com.mj.netty.chatroom.common.core.session.Session;
import com.mj.netty.chatroom.common.module.ModuleId;
import com.mj.netty.chatroom.common.module.player.PlayerCmd;
import com.mj.netty.chatroom.common.module.player.request.LoginRequest;
import com.mj.netty.chatroom.common.module.player.request.RegisterRequest;
import com.mj.netty.chatroom.common.module.player.response.PlayerResponse;

/**
 * 玩家模块
 * 
 * @author MJ
 *
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {

	/**
	 * 创建并登录帐号
	 * 
	 * @param channel
	 * @param data
	 *            {@link RegisterRequest}
	 * @return
	 */
	@SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
	public Result<PlayerResponse> registerAndLogin(Session session, byte[] data);

	/**
	 * 登录帐号
	 * 
	 * @param channel
	 * @param data
	 *            {@link LoginRequest}
	 * @return
	 */
	@SocketCommand(cmd = PlayerCmd.LOGIN)
	public Result<PlayerResponse> login(Session session, byte[] data);

}
