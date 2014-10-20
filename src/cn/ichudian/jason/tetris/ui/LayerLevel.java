package cn.ichudian.jason.tetris.ui;

import java.awt.Graphics;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public class LayerLevel extends Layer {

	private static int levelW = Img.LEVEL.getWidth(null);

	// 32 320

	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		// 等级图片
		int centerX = (w - levelW >> 1);
		if (dto.getNowLevel() < 100) {
			g.drawImage(Img.LEVEL, x + centerX, y + PADDING, null);
			this.drawNumberLeftPad(centerX + 16, 64, dto.getNowLevel(), 2, g);
		} else {
			g.drawImage(Img.LEVEL, x + centerX, y + PADDING, null);
			this.drawNumberLeftPad(centerX, 64, dto.getNowLevel(), 3, g);
		}

	}

}
