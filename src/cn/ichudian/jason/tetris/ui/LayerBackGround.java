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
public class LayerBackGround extends Layer {
	public LayerBackGround(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		int bgIdx = dto.getNowLevel() % Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0, 1162, 654, null);
	}

}
