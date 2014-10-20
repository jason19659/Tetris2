package cn.ichudian.jason.tetris.service;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * service
 *
 * 2013-9-13
 */
public interface GameService {

	boolean keyUp();

	boolean keyDown();

	boolean keyRight();

	boolean keyLeft();

	boolean keyCheat();

	boolean keySpace();

	boolean keyPause();

	boolean keyShadow();

	void startGame();

	void mianAction();

}
