package cn.ichudian.jason.tetris.ui;

import java.awt.Graphics;
import java.awt.Point;

import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.entity.GameAct;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public class LayerGame extends Layer {
	// private static int ACT_SIZE = 32;
	private static final int SIZE_ROL = GameConfig.getFrameConfig()
			.getSizeRol();
	private static final int LEFT_SIDE = 0;
	private static final int RIGHT_SIDE = GameConfig.getSystemConfig()
			.getMaxX();
	private static final int LOSE_IDX = GameConfig.getFrameConfig()
			.getLoseIdx();

	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		GameAct act = this.dto.getGameAct();
		if (act != null) {
			Point[] points = act.getActPoints();
			this.drawShadow(points, g);
			this.drawMainAct(points, g);
		}
		this.drawMap(g);
		if (dto.isPause()) {
			drawImageAtCenter(Img.PAUSE, g);
		}
	}

	/**
	 * 绘制游戏地图
	 */
	private void drawMap(Graphics g) {
		// 打印地图
		boolean[][] map = this.dto.getGameMap();
		// 堆积颜色
		int lv = dto.getNowLevel();
		int imgIdx = lv == 0 ? 0 : (lv - 1) % 7 + 1;
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y]) {
					drawActByPoint(x, y, imgIdx, g);
				}
			}
		}
	}

	/**
	 * 绘制活动方块
	 * 
	 * @param g
	 */
	private void drawMainAct(Point[] points, Graphics g) {
		int typeCode = this.dto.getGameAct().getTypeCode();
		// 绘制方块
		for (int i = 0; i < points.length; i++) {
			drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
		}
	}

	/**
	 * * 绘制阴影
	 * 
	 * @param points
	 * @param isShowShadow
	 * @param g
	 */
	private void drawShadow(Point[] points, Graphics g) {
		if (!dto.isShowShadow()) {
			return;
		}
		int leftX = RIGHT_SIDE;
		int rightX = LEFT_SIDE;
		for (Point p : points) {
			leftX = p.x < leftX ? p.x : leftX;
			rightX = p.x > rightX ? p.x : rightX;

		}
		g.drawImage(Img.SHADOW, this.x + BORDER + (leftX << SIZE_ROL) - 1,
				this.y + BORDER, rightX - leftX + 1 << SIZE_ROL, this.h
						- BORDER * 2, null);
	}

	/**
	 * 绘制正方形块 imgIDX 第几张图片0-6
	 **/
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		imgIdx = dto.isStart() ? imgIdx : LOSE_IDX;
		g.drawImage(Img.ACT, this.x + (x << SIZE_ROL) + 6, this.y
				+ (y << SIZE_ROL) + 6, this.x + (x + 1 << SIZE_ROL) + 6, this.y
				+ (y + 1 << SIZE_ROL) + 6, imgIdx << SIZE_ROL, 0,
				(imgIdx + 1) << SIZE_ROL, 1 << SIZE_ROL, null);
	}
}
