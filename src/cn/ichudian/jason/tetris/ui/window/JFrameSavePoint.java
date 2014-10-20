package cn.ichudian.jason.tetris.ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.ichudian.jason.tetris.control.GameControl;
import cn.ichudian.jason.tetris.util.FrameUtil;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * ui.window
 *
 * 2013-9-13
 */
public class JFrameSavePoint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8250791623619730862L;
	private JButton btnOk = null;
	private JLabel lbPoint = null;
	private JTextField txName = null;
	private JLabel errMsg = null;
	private GameControl gamecontrol = null;

	public JFrameSavePoint(GameControl gamecontrol) {
		this.gamecontrol = gamecontrol;
		this.setTitle("保存记录");
		this.setSize(256, 128);
		FrameUtil.setFrameCenter(this, 0);
		this.setLayout(new BorderLayout());
		this.createCom();
		this.creatAction();
	}

	public void show(int point) {
		this.lbPoint.setText("您的得分:" + point);
		this.setVisible(true);

	}

	private void creatAction() {
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txName.getText();
				if (name.length() > 16 || name == null || name.equals("")) {
					errMsg.setText("您输入的名字暂不支持,请修改");
				} else {
					setVisible(false);
					gamecontrol.savePoint(name);
				}
			}
		});
	}

	private void createCom() {
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.lbPoint = new JLabel();

		this.errMsg = new JLabel();
		this.errMsg.setForeground(Color.red);
		north.add(errMsg);
		north.add(lbPoint);
		this.add(north, BorderLayout.NORTH);

		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.txName = new JTextField(10);
		center.add(new JLabel("Your name is"));
		center.add(this.txName);
		this.add(center, BorderLayout.CENTER);

		btnOk = new JButton("确定");
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south.add(btnOk);
		this.add(south, BorderLayout.SOUTH);

	}

}
