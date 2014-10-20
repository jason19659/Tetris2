package cn.ichudian.jason.tetris.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * util
 *
 * 2013-9-13
 */
public class FrameUtil {
	/**
	 * 窗口居中
	 * 
	 * @param jf
	 *            windowUp
	 */
	public static void setFrameCenter(JFrame jf, int windowUp) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width - jf.getWidth() >> 1;
		int y = (screen.height - jf.getHeight() >> 1) - windowUp;
		jf.setLocation(x, y);
	}
}
