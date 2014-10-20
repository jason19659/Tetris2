package cn.ichudian.jason.tetris.config;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * config
 *
 * 2013-9-13
 */
public class GameConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4469589645475446344L;

	private static FrameConfig FRAME_CONFIG = null;

	private static DataConfig DATA_CONFIG = null;

	private static SystemConfig SYSTEM_CONFIG = null;
	/**********************************************************
	 * * Debug mode * * *
	 **********************************************************/
	private static final boolean IS_DEBUG = true;

	static {
		try {
			if (IS_DEBUG) {
				// 锟斤拷锟斤拷xml锟斤拷取锟斤拷
				SAXReader read = new SAXReader();
				// 锟斤拷取xml锟侥硷拷
				Document doc = read.read("data/cfg.xml");
				// 锟斤拷锟絰ml锟侥硷拷锟侥革拷诘锟�
				Element game = doc.getRootElement();
				FRAME_CONFIG = new FrameConfig(game.element("frame"));
				DATA_CONFIG = new DataConfig(game.element("data"));
				SYSTEM_CONFIG = new SystemConfig(game.element("system"));
			} else {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("data/frameConfig.dat"));
				FRAME_CONFIG = (FrameConfig) ois.readObject();
				ois.close();
				ois = new ObjectInputStream(new FileInputStream(
						"data/systemConfig.dat"));
				SYSTEM_CONFIG = (SystemConfig) ois.readObject();
				ois.close();
				ois = new ObjectInputStream(new FileInputStream(
						"data/dataConfig.dat"));
				DATA_CONFIG = (DataConfig) ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GameConfig() {
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟�
	 * 
	 * @return 锟斤拷锟斤拷
	 */
	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}

	public static FrameConfig getFrameConfig() {
		return FRAME_CONFIG;
	}

	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}

	/************************************
	 * * Debug Code 锟斤拷锟斤拷模式写锟斤拷锟斤拷锟斤拷锟斤拷 * *
	 ************************************/
	// public static void main(String[] args) throws FileNotFoundException,
	// IOException {
	// ObjectOutputStream oos = new ObjectOutputStream(new
	// FileOutputStream("data/frameConfig.dat"));
	// oos.writeObject(FRAME_CONFIG);
	// oos = new ObjectOutputStream(new
	// FileOutputStream("data/systemConfig.dat"));
	// oos.writeObject(SYSTEM_CONFIG);
	// oos = new ObjectOutputStream(new
	// FileOutputStream("data/dataConfig.dat"));
	// oos.writeObject(DATA_CONFIG);
	// }
}
