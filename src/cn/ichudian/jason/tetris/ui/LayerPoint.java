package cn.ichudian.jason.tetris.ui;

import java.awt.Graphics;

import cn.ichudian.jason.tetris.config.GameConfig;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public class LayerPoint extends Layer {

	private static final int POINT_BIT = 5;
	// 升级行数
	private static final int LEVEL_UP = GameConfig.getSystemConfig()
			.getLevelUp();

	private final int rmLineY;

	private final int pointY;

	private final int comX;

	private final int expY;

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		comX = w - NUMBER_W * POINT_BIT - PADDING;
		pointY = PADDING;
		rmLineY = pointY + Img.POINT.getHeight(null) + PADDING;
		expY = rmLineY + Img.RMLINE.getHeight(null) + PADDING;
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		// 分数图片
		g.drawImage(Img.POINT, x + PADDING, y + PADDING, null);
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT,
				g);
		// 消行图片
		g.drawImage(Img.RMLINE, x + PADDING, y + rmLineY, null);
		this.drawNumberLeftPad(comX, rmLineY, this.dto.getNowRemoveLine(),
				POINT_BIT, g);
		int rmLine = dto.getNowRemoveLine();
		drawRect(expY, "", null, (double) (rmLine % LEVEL_UP)
				/ (double) LEVEL_UP, g, 16);
	}

}
