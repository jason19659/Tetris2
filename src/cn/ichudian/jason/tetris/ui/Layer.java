package cn.ichudian.jason.tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import cn.ichudian.jason.tetris.config.FrameConfig;
import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.dto.GameDto;


/*
 * 提供给子类绘制窗口
 */
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public abstract class Layer {

	protected static final int PADDING;

	protected static final int BORDER;

	static {
		FrameConfig fCfg = GameConfig.getFrameConfig();
		PADDING = fCfg.getPadding();
		BORDER = fCfg.getBorder();
	}

	private static int windowWidth = Img.WINDOW.getWidth(null);
	private static int windowHeight = Img.WINDOW.getHeight(null);

	protected static final int RECT_H = Img.RECT.getHeight(null);
	private static final int RECT_W = Img.RECT.getWidth(null);

	private int rectW;

	protected static final int NUMBER_W = Img.NUMBER.getWidth(null) / 10;
	protected static final int NUMBER_H = Img.NUMBER.getHeight(null);
	// 默认字体格式
	private static final Font DE_FONT = new Font("黑体", Font.BOLD, 22);
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected GameDto dto = null;

	protected Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		rectW = w - PADDING * 3;
	}

	protected void createWindow(Graphics g) {
		// ↖
		g.drawImage(Img.WINDOW, x, y, x + BORDER, y + BORDER, 0, 0, BORDER,
				BORDER, null);
		// ↑
		g.drawImage(Img.WINDOW, x + BORDER, y, x + w - BORDER, y + BORDER,
				BORDER, 0, windowWidth - BORDER, BORDER, null);
		// ↗
		g.drawImage(Img.WINDOW, x + w - BORDER, y, x + w, y + BORDER,
				windowWidth - BORDER, 0, windowWidth, BORDER, null);
		// ←
		g.drawImage(Img.WINDOW, x, y + BORDER, x + BORDER, y + h - BORDER, 0,
				BORDER, BORDER, windowHeight - BORDER, null);
		// ＋
		g.drawImage(Img.WINDOW, x + BORDER, y + BORDER, x + w - BORDER, y + h
				- BORDER, BORDER, BORDER, windowWidth - BORDER, windowHeight
				- BORDER, null);
		// →
		g.drawImage(Img.WINDOW, x + w - BORDER, y + BORDER, x + w, y + h
				- BORDER, windowWidth - BORDER, BORDER, windowWidth,
				windowWidth - BORDER, null);
		// ↙
		g.drawImage(Img.WINDOW, x, y + h - BORDER, x + BORDER, y + h, 0,
				windowHeight - BORDER, BORDER, windowHeight, null);
		// ↓
		g.drawImage(Img.WINDOW, x + BORDER, y + h - BORDER, x + w - BORDER, y
				+ h, BORDER, windowHeight - BORDER, windowWidth - BORDER,
				windowHeight, null);
		// ↘
		g.drawImage(Img.WINDOW, x + w - BORDER, y + h - BORDER, x + w, y + h,
				windowWidth - BORDER, windowHeight - BORDER, windowWidth,
				windowHeight, null);
	}

	public void setDto(GameDto dto) {
		this.dto = dto;
	}

	protected void drawNumberLeftPad(int x, int y, int num, int maxBit,
			Graphics g) {
		// 取出num每一位
		String strNum = Integer.toString(num);
		for (int i = 0; i < maxBit; i++) {
			if (maxBit - i <= strNum.length()) {
				int idx = i - maxBit + strNum.length();
				int bit = strNum.charAt(idx) - '0';
				g.drawImage(Img.NUMBER, this.x + x + NUMBER_W * i, this.y + y,
						this.x + x + NUMBER_W * (i + 1), this.y + y + NUMBER_H,
						NUMBER_W * bit, 0, (bit + 1) * NUMBER_W, NUMBER_H, null);
			}
		}
	}

	/*
	 * @param draw window
	 * 
	 * @author jason19659
	 */

	/**
	 * 绘制值cao;
	 * 
	 * @param title
	 * @param number
	 * @param value
	 * @param maxValue
	 * @param g
	 */
	protected void drawRect(int yW, String title, String number,
			double percent, Graphics g, int offest) {
		// bgimg
		int rectX = x + PADDING + PADDING;
		int rectY = y + yW;
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(rectX, rectY, rectW, 32);
		g.setColor(Color.yellow);
		g.fillRect(rectX + 1, rectY + 1, rectW - 2, 30);
		g.setColor(new Color(0x66, 0xcc, 0xff));
		g.fillRect(rectX + 2, rectY + 2, rectW - 4, 28);
		g.setColor(Color.blue);
		// draw exp ---- TODO
		int w = (int) ((percent) * (rectW - 4)) + offest;
		int subIndex = (int) (percent * RECT_W) - 1;

		g.drawImage(Img.RECT, rectX + 2, rectY + 2, rectX + w, rectY + 2
				+ RECT_H, subIndex, 0, subIndex + 1, RECT_H, null);
		/**
		 * 下一级String提示
		 */
		g.setColor(Color.white);
		g.setFont(DE_FONT);
		g.drawString(title, rectX, rectY + 22);

		if (number != null) {
			g.drawString(number, rectX + 200, rectY + 22);
		}
		g.setColor(c);
	}

	/**
	 * 绘制正中
	 * 
	 * @param g
	 *            画笔
	 */
	protected void drawImageAtCenter(Image img, Graphics g) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		g.drawImage(img, this.x + (this.w - imgW >> 1), this.y
				+ (this.h - imgH >> 1), null);
	}

	public abstract void paint(Graphics g);
}
