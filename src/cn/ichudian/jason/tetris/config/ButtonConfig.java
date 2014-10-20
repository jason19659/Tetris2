package cn.ichudian.jason.tetris.config;

import java.io.Serializable;

import org.dom4j.Element;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * config
 *
 * 2013-9-13
 */
public class ButtonConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1643673420834870317L;
	private final int buttonW;
	private final int buttonH;

	private final int startX;
	private final int startY;

	private final int userConfigX;
	private final int userConfigY;

	public ButtonConfig(Element button) {
		this.buttonW = Integer.parseInt(button.attributeValue("w"));
		this.buttonH = Integer.parseInt(button.attributeValue("h"));
		this.startX = Integer.parseInt(button.element("start").attributeValue(
				"x"));
		this.startY = Integer.parseInt(button.element("start").attributeValue(
				"y"));
		this.userConfigX = Integer.parseInt(button.element("userConfig")
				.attributeValue("x"));
		this.userConfigY = Integer.parseInt(button.element("userConfig")
				.attributeValue("y"));
	}

	public int getButtonW() {
		return buttonW;
	}

	public int getButtonH() {
		return buttonH;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getUserConfigX() {
		return userConfigX;
	}

	public int getUserConfigY() {
		return userConfigY;
	}

}
