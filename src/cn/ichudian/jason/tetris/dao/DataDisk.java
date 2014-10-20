package cn.ichudian.jason.tetris.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cn.ichudian.jason.tetris.dto.Player;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 * 
 *         dao
 * 
 *         2013-9-13
 */
public class DataDisk implements Data {

	private final String filePath;

	public DataDisk(HashMap<String, String> param) {
		filePath = param.get("path");
	}

	public DataDisk() {
		filePath = "data/recode.dat";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> loadData() {
		ObjectInputStream ois = null;
		List<Player> players = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			try {
				players = (List<Player>) ois.readObject();
			} catch (Exception e) {
				Player p = new Player("test", 0);
				players = new ArrayList<Player>();
				players.add(p);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public void saveData(Player pla) {
		List<Player> players = loadData();
		if (players == null) {
			players = new ArrayList<Player>();
		}
		// 锟叫断硷拷录锟角否超癸拷100锟斤拷
		if (players != null && players.size() > 100) {
			Collections.sort(players);
			for (int i = 101; i < players.size(); i++) {
				players.remove(i);
			}
		}
		players.add(pla);

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(players);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				oos.flush();
				oos.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

	}

}
