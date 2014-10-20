package cn.ichudian.jason.tetris.ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.ichudian.jason.tetris.config.FrameConfig;
import cn.ichudian.jason.tetris.config.GameConfig;
import cn.ichudian.jason.tetris.config.LayerConfig;
import cn.ichudian.jason.tetris.control.GameControl;
import cn.ichudian.jason.tetris.control.PlayerControl;
import cn.ichudian.jason.tetris.dto.GameDto;
import cn.ichudian.jason.tetris.ui.Img;
import cn.ichudian.jason.tetris.ui.Layer;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui.window
 *
 * 2013-9-13
 */
public class JPanelGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 759103704461906159L;
	private static final int BTN_SIZE_W = GameConfig.getFrameConfig()
			.getButtonConfig().getButtonW();
	private static final int BTN_SIZE_H = GameConfig.getFrameConfig()
			.getButtonConfig().getButtonH();

	private List<Layer> layers = null;

	private JButton btnStart;
	private JButton btnConfig;

	private GameControl gameControl = null;

	public JPanelGame(GameControl gameControl, GameDto dto) {
		this.gameControl = gameControl;
		setLayout(null);
		initLayer(dto);
		initComponent();
		this.addKeyListener(new PlayerControl(gameControl));
	}

	public void initComponent() {
		btnStart = new JButton(Img.BTN_START);
		btnConfig = new JButton(Img.BTN_CONFIG);
		btnStart.setBounds(GameConfig.getFrameConfig().getButtonConfig()
				.getStartX(), GameConfig.getFrameConfig().getButtonConfig()
				.getStartY(), BTN_SIZE_W, BTN_SIZE_H);
		btnConfig.setBounds(GameConfig.getFrameConfig().getButtonConfig()
				.getUserConfigX(), GameConfig.getFrameConfig()
				.getButtonConfig().getUserConfigY(), BTN_SIZE_W, BTN_SIZE_H);
		btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
				// 返回焦点,否则无法使用keylistener
				requestFocus();
			}
		});
		add(btnStart);
		add(btnConfig);
	}

	private void initLayer(GameDto dto) {
		try {
			FrameConfig fCfg = GameConfig.getFrameConfig();
			List<LayerConfig> layersCfg = fCfg.getLayersConfig();
			layers = new ArrayList<Layer>(layersCfg.size());
			;

			for (LayerConfig layerCfg : layersCfg) {
				Class<?> cls = Class.forName(layerCfg.getClassName());
				Constructor<?> ctr = cls.getConstructor(int.class, int.class,
						int.class, int.class);
				Layer ls = (Layer) ctr.newInstance(layerCfg.getX(),
						layerCfg.getY(), layerCfg.getW(), layerCfg.getH());

				ls.setDto(dto);
				layers.add(ls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Override
	public void paintComponent(Graphics g) {
		// new LayerBackGround().paint(g);
		super.paintComponent(g);
		// NBFOR
		for (int i = 0; i < layers.size(); layers.get(i++).paint(g))
			;
	}

	/**
	 * 控制按钮是否可以点击
	 */
	public void buttonSwitch(boolean on) {
		btnConfig.setEnabled(on);
		btnStart.setEnabled(on);
	}

	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}
}
