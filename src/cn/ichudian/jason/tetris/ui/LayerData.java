package cn.ichudian.jason.tetris.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.dto.Player;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public abstract class LayerData extends Layer {
	private static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();
	private static int START_Y = 0;
	private static int SPA = 0;

	private static int RECT_H2 = RECT_H + 4;

	protected LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (h - (RECT_H + 4) * 5 - 2 * PADDING - Img.DATABASE
				.getHeight(null)) / MAX_ROW;
		START_Y = PADDING + Img.DATABASE.getHeight(null) + SPA;
	}

	/**
	 * 绘制所有图片值槽
	 * 
	 * @param imgTitle
	 *            标题图片
	 * @param players
	 *            数据源
	 * @param g
	 *            Graphics
	 */
	public void showData(Image imgTitle, List<Player> players, Graphics g) {
		this.createWindow(g);
		g.drawImage(imgTitle, this.x + PADDING, this.y + PADDING, null);
		int nowPoint = dto.getNowPoint();
		for (int i = 0; i < MAX_ROW; i++) {
			Player p = players.get(i);
			int recodePoint = p.getPoint();
			double percent = (double) nowPoint / p.getPoint();
			percent = percent > 1 ? 1.0 : percent;
			String strPoint = recodePoint == -1 ? null : Integer
					.toString(recodePoint);
			this.drawRect(START_Y + i * (RECT_H2 + SPA), p.getName(), strPoint,
					percent, g, 3);
		}

	}

	@Override
	public abstract void paint(Graphics g);
}
