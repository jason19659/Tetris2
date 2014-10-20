package cn.ichudian.jason.tetris.dao;

import java.util.List;

import cn.ichudian.jason.tetris.dto.Player;


/**
 * 数据持久层
 * 
 * @author jason
 * 
 */
public interface Data {
	/**
	 * 获得数据
	 * 
	 * @return player
	 */
	List<Player> loadData();

	/**
	 * 存储数据
	 * 
	 * @param plyers
	 */
	void saveData(Player pla);

}
