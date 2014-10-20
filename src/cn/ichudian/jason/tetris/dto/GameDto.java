package cn.ichudian.jason.tetris.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.entity.GameAct;
import cn.ichudian.jason.tetris.util.GameFuction;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * dto
 *
 * 2013-9-13
 */
public class GameDto {

	public static final int GAMEZONE_W = GameConfig.getSystemConfig().getMaxX() + 1;
	public static final int GAMEZONE_H = GameConfig.getSystemConfig().getMaxY() + 1;

	private List<Player> dbRecode;
	private List<Player> diskRecode;

	private boolean[][] gameMap;
	private boolean start;
	private boolean pause;

	private GameAct gameAct;

	private int next;
	private int nowLevel;

	private int nowPoint;
	private int nowRemoveLine;
	private boolean showShadow = true;

	private long sleepTime = 1000;

	public GameDto() {
		dtoInit();
	}

	public void dtoInit() {
		this.gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
		this.nowLevel = 0;
		this.nowPoint = 0;
		this.nowRemoveLine = 0;
		this.pause = false;
	}

	public List<Player> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<Player> dbRecode) {
		this.dbRecode = setFillRecode(dbRecode);
	}

	public List<Player> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<Player> diskRecode) {
		this.diskRecode = setFillRecode(diskRecode);
	}

	private List<Player> setFillRecode(List<Player> players) {
		if (players == null) {
			players = new ArrayList<Player>();
		}
		while (players.size() < 5) {
			players.add(new Player("暂无排名", -1));
		}
		Collections.sort(players);
		return players;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getNowLevel() {
		return nowLevel;
	}

	public void setNowLevel(int level) {
		this.nowLevel = level;
		this.sleepTime = GameFuction.getSleepTimeByLevel(level);
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}

	public int getNowRemoveLine() {
		return nowRemoveLine;
	}

	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public void setShowShadow(boolean showShadow) {
		this.showShadow = showShadow;
	}

	public void changeShowShadow() {
		showShadow = !showShadow;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public void changePause() {
		this.pause = !this.pause;
	}

	public long getSleepTime() {
		return sleepTime;
	}

}
