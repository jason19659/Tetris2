package cn.ichudian.jason.tetris.ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui.window
 *
 * 2013-9-13
 */
public class TextControl extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5321860571897705516L;
	private int keyCode;
	private final String methodName;

	public TextControl(int x, int y, int w, int h, String methodName) {
		this.setBounds(x, y, w, h);

		this.methodName = methodName;
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				setKeyCode(e.getKeyCode());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}

}
