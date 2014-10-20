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
public class LayerDisk extends LayerData {

	public LayerDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		this.showData(Img.DISK, dto.getDiskRecode(), g);
	}
}
