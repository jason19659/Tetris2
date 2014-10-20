package cn.ichudian.jason.tetris.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.ichudian.jason.tetris.dto.Player;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * dao
 *
 * 2013-9-13
 */
public class DataTest implements Data {

	public DataTest(HashMap<String, String> param) {

	}

	@Override
	public List<Player> loadData() {
		List<Player> players = new ArrayList<Player>();
		return players;
	}

	@Override
	public void saveData(Player plyers) {
		// TODO Auto-generated method stub

	}

}
