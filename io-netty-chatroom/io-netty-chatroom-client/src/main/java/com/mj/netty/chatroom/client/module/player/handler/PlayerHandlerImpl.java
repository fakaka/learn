package com.mj.netty.chatroom.client.module.player.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.netty.chatroom.client.swing.ResultCodeTip;
import com.mj.netty.chatroom.client.swing.Swingclient;
import com.mj.netty.chatroom.common.core.model.ResultCode;
import com.mj.netty.chatroom.common.module.player.response.PlayerResponse;

/**
 * 玩家模块
 * 
 * @author MJ
 *
 */
@Component
public class PlayerHandlerImpl implements PlayerHandler {

	@Autowired
	private Swingclient swingclient;

	@Autowired
	private ResultCodeTip resultCodeTip;

	@Override
	public void registerAndLogin(int resultCode, byte[] data) {
		if (resultCode == ResultCode.SUCCESS) {
			PlayerResponse playerResponse = new PlayerResponse();
			playerResponse.readFromBytes(data);

			swingclient.setPlayerResponse(playerResponse);
			swingclient.getTips().setText("注册登录成功");
		} else {
			swingclient.getTips().setText(resultCodeTip.getTipContent(resultCode));
		}
	}

	@Override
	public void login(int resultCode, byte[] data) {
		if (resultCode == ResultCode.SUCCESS) {
			PlayerResponse playerResponse = new PlayerResponse();
			playerResponse.readFromBytes(data);

			swingclient.setPlayerResponse(playerResponse);
			swingclient.getTips().setText("登录成功");
		} else {
			swingclient.getTips().setText(resultCodeTip.getTipContent(resultCode));
		}
	}
}
