package cn.ichudian.jason.tetris.ui;

import java.awt.Color;
import java.awt.Graphics;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public class LayerAbout extends Layer {

	public LayerAbout(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		Color c = g.getColor();
		g.setColor(Color.red);
		g.drawString("Copyright by jason19659", 822, 580);
		g.setColor(c);
	}
}
