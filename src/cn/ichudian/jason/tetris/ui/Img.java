package cn.ichudian.jason.tetris.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import cn.ichudian.jason.tetris.config.GameConfig;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui
 *
 * 2013-9-13
 */
public class Img {
	private Img() {
	}

	public static final String GRAPHICS_PATH = "graphics/";
	private static final String DEFAULT_PATH = "miku/";

	public static Image SIGN = null;
	public static Image WINDOW = null;
	public static Image NUMBER = null;
	public static Image POINT = null;
	public static Image RMLINE = null;
	public static Image RECT = null;
	public static Image DATABASE = null;
	public static Image DISK = null;
	public static Image ACT = null;
	public static Image LEVEL = null;
	public static Image SHADOW = null;
	public static Image PAUSE = null;
	public static ImageIcon BTN_START = null;
	public static ImageIcon BTN_CONFIG = null;

	public static Image[] NEXT_ACT = null;

	public static List<Image> BG_LIST = null;
	static {
		setSkin(DEFAULT_PATH);
	}

	public static void setSkin(String path) {
		String skinPath = GRAPHICS_PATH + path;
		SIGN = new ImageIcon(skinPath + "string/sign.png").getImage();
		WINDOW = new ImageIcon(skinPath + "window/window.png").getImage();
		NUMBER = new ImageIcon(skinPath + "string/num.png").getImage();
		POINT = new ImageIcon(skinPath + "string/point.png").getImage();
		RMLINE = new ImageIcon(skinPath + "string/rmline.png").getImage();
		RECT = new ImageIcon(skinPath + "window/rect.png").getImage();
		DATABASE = new ImageIcon(skinPath + "string/db.png").getImage();
		DISK = new ImageIcon(skinPath + "string/disk.png").getImage();
		ACT = new ImageIcon(skinPath + "game/rect.png").getImage();
		LEVEL = new ImageIcon(skinPath + "string/level.png").getImage();
		SHADOW = new ImageIcon(skinPath + "game/shadow.png").getImage();
		PAUSE = new ImageIcon(skinPath + "string/pause.png").getImage();
		BTN_START = new ImageIcon(skinPath + "string/start.png");
		BTN_CONFIG = new ImageIcon(skinPath + "string/config.png");

		NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig()
				.size()];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon(skinPath + "game/" + i + ".png")
					.getImage();
		}

		File dir = new File(skinPath + "background");
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for (File file : files) {
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		}

	}

}
