package cn.ichudian.jason.tetris.entity;

import java.awt.Point;
import java.util.List;

import cn.ichudian.jason.tetris.config.GameConfig;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * entity
 *
 * 2013-9-13
 */
public class GameAct {
	private Point[] actPoints = null;

	/*
	 * 方块编号
	 */
	private int typeCode;

	private static final int MIN_X = GameConfig.getSystemConfig().getMinX();
	private static final int MAX_X = GameConfig.getSystemConfig().getMaxX();
	private static final int MIN_Y = GameConfig.getSystemConfig().getMinY();
	private static final int MAX_Y = GameConfig.getSystemConfig().getMaxY();

	private final static List<Point[]> TYPE_CONFIG = GameConfig
			.getSystemConfig().getTypeConfig();
	private final static List<Boolean> TYPE_ROUND = GameConfig
			.getSystemConfig().getTypeRound();

	public GameAct(int typeCode) {
		this.init(typeCode);

	}

	public void init(int typeCode) {
		this.typeCode = typeCode;
		Point[] points = TYPE_CONFIG.get(typeCode);
		actPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}

	public Point[] getActPoints() {
		return actPoints;
	}

	/*
	 * 移动方块
	 * 
	 * @param ...偏移
	 */
	public boolean move(int moveX, int moveY, boolean[][] gameMap) {
		// 移动处理
		for (int i = 0; i < actPoints.length; i++) {
			int newX = actPoints[i].x + moveX;
			int newY = actPoints[i].y + moveY;
			if (isOverZone(newX, newY, gameMap)) {
				return false;
			}
		}
		for (int i = 0; i < actPoints.length; i++) {
			actPoints[i].x += moveX;
			actPoints[i].y += moveY;
		}
		return true;
	}

	/*
	 * 方块旋转笛卡尔公式镜像 顺时针: A.x = O.y + O.x - B.y A.y = O.y - O.x + B.x
	 */
	public void round(boolean[][] gameMap) {
		if (!TYPE_ROUND.get(typeCode)) {
			return;
		}
		for (int i = 1; i < actPoints.length; i++) {
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			if (this.isOverZone(newX, newY, gameMap)) {
				return;
			}
		}
		for (int i = 1; i < actPoints.length; i++) {
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			actPoints[i].x = newX;
			actPoints[i].y = newY;
		}
	}

	/*
	 * 判断是否超出边界
	 */
	private boolean isOverZone(int newX, int newY, boolean[][] gameMap) {
		return newX < MIN_X || newX > MAX_X || newY < MIN_Y || newY > MAX_Y
				|| gameMap[newX][newY];
	}

	public int getTypeCode() {
		return typeCode;
	}

}
