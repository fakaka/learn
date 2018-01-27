package com.mj.netty.chatroom.common.core.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求命令
 * 
 * @author MJ
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketCommand {

	/**
	 * 请求的命令号
	 * 
	 * @return
	 */
	short cmd();

}
