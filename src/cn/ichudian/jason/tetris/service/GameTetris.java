package cn.ichudian.jason.tetris.service;

import java.awt.Point;
import java.util.Map;
import java.util.Random;

import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.dto.GameDto;
import cn.ichudian.jason.tetris.entity.GameAct;


/**
 * 增加按键方法在ui.cfg.FrameConfig 中 synchronized 已經加入到 key方法
 * 
 * @author jason
 * 
 */
public class GameTetris implements GameService {
	private GameDto dto;

	private static final int LEVEL_UP = GameConfig.getSystemConfig()
			.getLevelUp();

	private Random random = new Random();
	private static final int MAX_TYPE = GameConfig.getSystemConfig()
			.getTypeConfig().size();
	private static final Map<Integer, Integer> PLUS_POINT = GameConfig
			.getSystemConfig().getPlusPoint();

	public GameTetris(GameDto dto) {
		this.dto = dto;
	}

	public boolean keyUp() {
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}

	public boolean keyDown() {
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
				return false;
			}
			boolean[][] map = this.dto.getGameMap();
			Point[] act = this.dto.getGameAct().getActPoints();
			for (int i = 0; i < act.length; i++) {
				map[act[i].x][act[i].y] = true;
			}
			int plusExp = this.plusExp();
			if (plusExp > 0) {
				if (plusExp > 4)
					plusExp = 4;
				plusPoint(plusExp);
			}
			// 生成随机方块
			this.dto.getGameAct().init(this.dto.getNext());
			this.dto.setNext(random.nextInt(MAX_TYPE));
			if (isLose()) {
				dto.setStart(false);
			}
			return true;
		}
	}

	private boolean isLose() {
		Point[] actPoints = dto.getGameAct().getActPoints();
		boolean[][] map = dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if (map[actPoints[i].x][actPoints[i].y]) {
				return true;
			}
		}
		return false;
	}

	private void plusPoint(int plusExp) {
		int level = dto.getNowLevel();
		int rmLine = dto.getNowRemoveLine();
		int point = dto.getNowPoint();
		if (rmLine % LEVEL_UP + plusExp >= LEVEL_UP) {
			level++;
		}
		dto.setNowPoint(point + PLUS_POINT.get(plusExp));
		dto.setNowRemoveLine(rmLine + plusExp);
		dto.setNowLevel(level);
	}

	private int plusExp() {
		boolean[][] map = dto.getGameMap();
		int exp = 0;
		// 扫描地图
		for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
			if (isCanRemoveLine(y, map)) {
				removeLine(y, map);
				exp++;
			}
		}
		return exp;
	}

	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y > 0; y--) {
				map[x][y] = map[x][y - 1];
			}
			map[x][0] = false;
		}
	}

	private boolean isCanRemoveLine(int y, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if (!map[x][y]) {
				return false;
			}
		}
		return true;
	}

	public boolean keyLeft() {
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
			return true;
		}
	}

	public boolean keyRight() {
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
			return true;
		}
	}

	/**
	 * debug key 作弊键
	 */
	public boolean keyCheat() {
		int point = dto.getNowPoint();
		int rmLine = dto.getNowRemoveLine();
		int lv = dto.getNowLevel();
		point += 10;
		rmLine += 1;
		if (rmLine % 20 == 0) {
			lv += 1;
		}
		dto.setNowPoint(point);
		dto.setNowLevel(lv);
		dto.setNowRemoveLine(rmLine);
		return true;
	}

	@Override
	public void startGame() {
		dto.setNext(random.nextInt(MAX_TYPE));
		dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		dto.setStart(true);
		dto.dtoInit();
	}

	@Override
	public boolean keySpace() {
		if (dto.isPause()) {
			return true;
		}
		while (!keyDown()) {

		}
		return true;
	}

	@Override
	public void mianAction() {
		this.keyDown();
	}

	@Override
	public boolean keyPause() {
		if (dto.isStart()) {
			dto.changePause();
		}
		return true;
	}

	@Override
	public boolean keyShadow() {
		dto.changeShowShadow();
		return true;
	}

}
