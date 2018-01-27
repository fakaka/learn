package com.mj.netty.chatroom.server.module.player.service;

import com.mj.netty.chatroom.common.core.session.Session;
import com.mj.netty.chatroom.common.module.player.response.PlayerResponse;

/**
 * 玩家服务
 * 
 * @author MJ
 *
 */
public interface PlayerService {

	/**
	 * 登录注册用户
	 * 
	 * @param playerName
	 * @param passward
	 * @return
	 */
	public PlayerResponse registerAndLogin(Session session, String playerName, String passward);

	/**
	 * 登录
	 * 
	 * @param playerName
	 * @param passward
	 * @return
	 */
	public PlayerResponse login(Session session, String playerName, String passward);

}
