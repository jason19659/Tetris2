package cn.ichudian.jason.tetris.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * config
 *
 * 2013-9-13
 */
public class DataInterfaceConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8562324101650946264L;

	private final String className;

	private final Map<String, String> param;

	public DataInterfaceConfig(Element dataInterfaceConfig) {
		this.className = dataInterfaceConfig.attributeValue("className");
		this.param = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Element> params = dataInterfaceConfig.elements("param");
		for (Element e : params) {
			this.param.put(e.attributeValue("key"), e.attributeValue("value"));
		}

	}

	public String getClassName() {
		return className;
	}

	public Map<String, String> getParam() {
		return param;
	}

}
