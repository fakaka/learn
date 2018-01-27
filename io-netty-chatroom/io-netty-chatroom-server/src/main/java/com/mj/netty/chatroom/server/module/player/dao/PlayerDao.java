package com.mj.netty.chatroom.server.module.player.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.mj.netty.chatroom.server.module.player.dao.entity.Player;

/**
 * 玩家dao
 * 
 * @author MJ
 *
 */
@Component
public class PlayerDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 获取玩家通过id
	 * 
	 * @param playerId
	 * @return
	 */
	public Player getPlayerById(long playerId) {
		return hibernateTemplate.get(Player.class, playerId);
	}

	/**
	 * 获取玩家通过玩家名
	 * 
	 * @param playerName
	 * @return
	 */
	public Player getPlayerByName(final String playerName) {

		return hibernateTemplate.execute(session -> {
			SQLQuery query = session.createSQLQuery("SELECT * FROM player where playerName = ?");
			query.setString(0, playerName);
			query.addEntity(Player.class);

			@SuppressWarnings("unchecked")
			List<Player> list = query.list();
			if (list == null || list.isEmpty()) {
				return null;
			}
			return list.get(0);
		});
	}

	/**
	 * 创建玩家
	 * 
	 * @param player
	 * @return
	 */
	public Player createPlayer(Player player) {
		long playerId = (Long) hibernateTemplate.save(player);
		player.setPlayerId(playerId);
		return player;
	}

}
