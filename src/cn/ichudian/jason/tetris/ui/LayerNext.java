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
public class LayerNext extends Layer {

	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		if (dto.isStart()) {
			this.drawImageAtCenter(Img.NEXT_ACT[dto.getNext()], g);
		}
	}

}
