package cn.ichudian.jason.tetris.ui.window;

import javax.swing.JFrame;

import cn.ichudian.jason.tetris.config.FrameConfig;
import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.util.FrameUtil;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui.window
 *
 * 2013-9-13
 */
public class JFrameGame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5893188858948407417L;

	public JFrameGame(JPanelGame jPanelGame) {

		FrameConfig fCfg = GameConfig.getFrameConfig();
		this.setTitle("This is not a game"); // fCfg.getTitle()
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setVisible(true);
		this.setSize(fCfg.getWidth(), fCfg.getHeight());
		this.setResizable(false);
		// 居中
		FrameUtil.setFrameCenter(this, fCfg.getWindowUp());
		this.setContentPane(jPanelGame);
		// 最后设置可见性
		this.setVisible(true);
	}
}
