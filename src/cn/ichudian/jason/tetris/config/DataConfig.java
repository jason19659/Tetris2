package cn.ichudian.jason.tetris.config;

import java.io.Serializable;

import org.dom4j.Element;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * config
 *
 * 2013-9-13
 */
public class DataConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7583588435372492962L;
	private final int maxRow;
	private final DataInterfaceConfig dataA;
	private final DataInterfaceConfig dataB;

	public DataConfig(Element data) {
		maxRow = Integer.parseInt(data.attributeValue("maxRow"));
		dataA = new DataInterfaceConfig(data.element("dataA"));
		dataB = new DataInterfaceConfig(data.element("dataB"));
	}

	public DataInterfaceConfig getDataA() {
		return dataA;
	}

	public DataInterfaceConfig getDataB() {
		return dataB;
	}

	public int getMaxRow() {
		return maxRow;
	}

}
