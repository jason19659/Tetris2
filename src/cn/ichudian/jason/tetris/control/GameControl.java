package cn.ichudian.jason.tetris.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.ichudian.jason.tetris.config.DataInterfaceConfig;
import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.dao.Data;
import cn.ichudian.jason.tetris.dto.GameDto;
import cn.ichudian.jason.tetris.dto.Player;
import cn.ichudian.jason.tetris.service.GameTetris;
import cn.ichudian.jason.tetris.ui.window.JFrameConfig;
import cn.ichudian.jason.tetris.ui.window.JFrameGame;
import cn.ichudian.jason.tetris.ui.window.JFrameSavePoint;
import cn.ichudian.jason.tetris.ui.window.JPanelGame;


/*
 * 锟斤拷锟斤拷锟铰硷拷 锟斤拷锟斤拷 锟竭硷拷
 */
/**
 *  GBK --> UTF-8
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * control
 *
 * 2013-9-13
 */
public class GameControl {
	/**
	 * 锟斤拷莘锟斤拷锟�
	 */
	private Data dataA;

	private Data dataB;

	private JPanelGame panelGame;
	private GameTetris gameService;

	private JFrameConfig frameConfig;
	private GameDto dto = null;

	private JFrameSavePoint frameSavePoint;

	/**
	 * game key controller
	 */
	private Map<Integer, Method> actionList;

	private Thread gameThread = null;

	public GameControl() {
		this.dto = new GameDto();
		this.gameService = new GameTetris(dto);
		this.dataA = createDataObject(GameConfig.getDataConfig().getDataA());
		this.dto.setDbRecode(dataA.loadData());
		dataB = createDataObject(GameConfig.getDataConfig().getDataB());
		this.dto.setDiskRecode(dataB.loadData());
		this.panelGame = new JPanelGame(this, dto);
		this.setControlConfig();
		this.frameConfig = new JFrameConfig(this);
		this.frameSavePoint = new JFrameSavePoint(this);
		new JFrameGame(this.panelGame);
	}

	private void setControlConfig() {
		actionList = new HashMap<Integer, Method>();

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>) ois
					.readObject();
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			for (Entry<Integer, String> e : entryset) {
				actionList.put(e.getKey(),
						gameService.getClass().getMethod(e.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Data createDataObject(DataInterfaceConfig cfg) {
		try {
			Class<?> cls = Class.forName(cfg.getClassName());
			Constructor<?> ctr = cls.getConstructor(HashMap.class);

			return (Data) ctr.newInstance(cfg.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void actionByKeyCode(int keyCode) {
		try {
			// add isStart
			if (this.actionList.containsKey(keyCode) && dto.isStart()) {
				this.actionList.get(keyCode).invoke(this.gameService);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}

	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}

	/**
	 * 锟接达拷锟节关憋拷锟铰硷拷
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}

	public void start() {
		panelGame.buttonSwitch(false);
		frameConfig.setVisible(false);
		frameSavePoint.setVisible(false);
		gameService.startGame();
		gameThread = new MainThread();
		gameThread.start();
		panelGame.repaint();
	}

	public void afterLose() {
		// 锟斤拷示锟斤拷锟斤拷梅锟�
		this.frameSavePoint.show(dto.getNowPoint());
		// 锟斤拷钮锟斤拷锟斤拷锟斤拷锟�
		this.panelGame.buttonSwitch(true);
	}

	private class MainThread extends Thread {
		@Override
		public void run() {
			while (dto.isStart()) {
				try {
					Thread.sleep(dto.getSleepTime());
					if (dto.isPause()) {
						continue;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gameService.mianAction();
				panelGame.repaint();
			}
			afterLose();
		}
	}

	public void savePoint(String name) {
		Player pla = new Player(name, dto.getNowPoint());
		this.dataA.saveData(pla);
		this.dataB.saveData(pla);
		dto.setDbRecode(dataA.loadData());
		dto.setDiskRecode(dataB.loadData());
		this.panelGame.repaint();
	}

	public void repaint() {
		panelGame.repaint();
	}
}
