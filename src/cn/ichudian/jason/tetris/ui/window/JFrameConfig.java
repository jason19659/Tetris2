package cn.ichudian.jason.tetris.ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import cn.ichudian.jason.tetris.control.GameControl;
import cn.ichudian.jason.tetris.ui.Img;
import cn.ichudian.jason.tetris.util.FrameUtil;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui.window
 *
 * 2013-9-13
 */
public class JFrameConfig extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -883412005820306646L;
	private JButton btnOk = new JButton("确定");
	private JButton btnCannel = new JButton("取消");
	private JButton btnUser = new JButton("应用");

	private static final String PATH = "data/control.dat";

	private JLabel tipMessage = new JLabel();
	private JList skinList = null;

	private JPanel skinView = null;

	private DefaultListModel skinData = new DefaultListModel();

	private GameControl gameControl;

	private TextControl[] keyText = new TextControl[7];// [8]
	// 230,0,100,20
	private static final Image IMG_KEYCONFIG = new ImageIcon(
			"data/keyconfig.jpg").getImage();
	private Image[] skinViewList = null;
	/**
	 * 在此增加按键方法 增加需要在GameTetris增加对应方法 然后修改keyText最大值+1 之后在initKeyText中初始化
	 * 修改后第一次运行可能会报空指针,改建后消失
	 */
	private final static String[] METHOD_NAMES = { "keyUp", "keyLeft",
			"keyDown", "keyRight", "keySpace", "keyPause", "keyShadow",// "keyCheat"
	};

	public JFrameConfig(GameControl gameControl) {
		this.gameControl = gameControl;
		setLayout(new BorderLayout());
		setTitle("游戏设置");
		// 初始化按键输入框
		initKeyText();
		add(createMainPanel(), BorderLayout.CENTER);
		add(createButtonPanle(), BorderLayout.SOUTH);
		setSize(652, 475);
		// 居中
		FrameUtil.setFrameCenter(this, 32);
	}

	/**
	 * init key input text
	 */
	private void initKeyText() {
		keyText[0] = new TextControl(160, 55, 60, 20, METHOD_NAMES[0]);
		keyText[1] = new TextControl(5, 178, 60, 20, METHOD_NAMES[1]);
		keyText[2] = new TextControl(160, 310, 60, 20, METHOD_NAMES[2]);
		keyText[3] = new TextControl(285, 178, 60, 20, METHOD_NAMES[3]);
		keyText[4] = new TextControl(520, 185, 60, 20, METHOD_NAMES[4]);
		keyText[5] = new TextControl(520, 100, 60, 20, METHOD_NAMES[5]);
		keyText[6] = new TextControl(520, 275, 60, 20, METHOD_NAMES[6]);
		// keyText[7] = new TextControl(520,275,60,40,METHOD_NAMES[7]);

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					PATH));
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>) ois
					.readObject();
			ois.close();
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			for (Entry<Integer, String> e : entryset) {
				for (TextControl tc : keyText) {
					if (tc.getMethodName().equals(e.getValue())) {
						tc.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel createButtonPanle() {
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// 给确定按钮增加时间监听
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okButtonEvent();
			}
		});
		tipMessage.setForeground(Color.red);
		jp.add(tipMessage);
		jp.add(btnOk);
		btnCannel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelButtonEvent();
			}
		});
		jp.add(btnCannel);
		btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();

			}
		});
		jp.add(btnUser);
		return jp;
	}

	/**
	 * 创建选项卡
	 * 
	 * @return
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("Control Config", createControlPanel());
		jtp.addTab("Skin Config", createSkinPanel());
		jtp.addTab("Other Config", new JLabel("Other Config"));

		return jtp;
	}

	private JPanel createSkinPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		File dir = new File(Img.GRAPHICS_PATH);
		File[] files = dir.listFiles();
		skinViewList = new Image[files.length];
		for (int i = 0; i < files.length; i++) {
			skinData.addElement(files[i].getName());
			skinViewList[i] = new ImageIcon(files[i].getPath() + "/view.jpg")
					.getImage();
		}

		skinList = new JList(skinData);
		skinList.setSelectedIndex(0);
		skinList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				repaint();
			}

		});
		this.skinView = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2292469746040390058L;

			@Override
			public void paintComponent(Graphics g) {
				Image showImg = skinViewList[skinList.getSelectedIndex()];
				int x = getWidth() - showImg.getWidth(null) >> 1;
				int y = getHeight() - showImg.getHeight(null) >> 1;
				g.drawImage(skinViewList[skinList.getSelectedIndex()], x, y,
						null);
			}
		};

		panel.add(new JScrollPane(skinList), BorderLayout.WEST);
		panel.add(skinView, BorderLayout.CENTER);
		return panel;
	}

	private void okButtonEvent() {
		Boolean b = writeConfig();
		if (b) {
			setVisible(false);
			gameControl.setOver();
		}
	}

	private boolean writeConfig() {
		HashMap<Integer, String> keySet = new HashMap<Integer, String>();
		for (int i = 0; i < keyText.length; i++) {
			int keyCode = keyText[i].getKeyCode();
			if (keyCode == 0) {
				tipMessage.setText("按键错误");
				return false;
			}
			keySet.put(keyCode, keyText[i].getMethodName());
		}
		if (keySet.size() != keyText.length) {
			tipMessage.setText("按键重复");
			return false;
		}
		try {
			// 切换皮肤
			Img.setSkin(skinData.get(skinList.getSelectedIndex()).toString()
					+ "/");
			// 写入配置
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			tipMessage.setText(e.getMessage());
			return false;
		}
		tipMessage.setText("设置成功");
		gameControl.repaint();
		return true;
	}

	private void cancelButtonEvent() {
		setVisible(false);
		gameControl.setOver();
	}

	/**
	 * 玩家控制设置面板
	 * 
	 * @return
	 */
	private JPanel createControlPanel() {
		JPanel jp = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6885277811727859148L;

			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(IMG_KEYCONFIG, 0, 0, null);
			}
		};
		jp.setLayout(null);
		for (int i = 0; i < keyText.length; i++) {
			jp.add(keyText[i]);
		}

		return jp;
	}

}
