package cn.ichudian.jason.tetris.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * control
 *
 * 2013-9-13
 */
public class PlayerControl extends KeyAdapter {

	private GameControl gameControl;

	public PlayerControl(GameControl ctrl) {
		gameControl = ctrl;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());

	}
}
