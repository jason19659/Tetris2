package cn.ichudian.jason.tetris.config;

import java.io.Serializable;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * config
 *
 * 2013-9-13
 */
public class LayerConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1982754479249197371L;

	private String className;

	private int x;
	private int y;
	private int w;
	private int h;

	public LayerConfig(String className, int x, int y, int w, int h) {
		this.className = className;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public String getClassName() {
		return className;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

}
