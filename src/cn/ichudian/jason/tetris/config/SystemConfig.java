package cn.ichudian.jason.tetris.config;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
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
public class SystemConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345072963689730259L;
	private final int minX;
	private final int maxX;
	private final int minY;
	private final int maxY;
	private final int levelUp;

	private final List<Point[]> typeConfig;

	private final List<Boolean> typeRound;

	private final Map<Integer, Integer> plusPoint;

	public SystemConfig(Element system) {
		minX = Integer.parseInt(system.attributeValue("minX"));
		maxX = Integer.parseInt(system.attributeValue("maxX"));
		minY = Integer.parseInt(system.attributeValue("minY"));
		maxY = Integer.parseInt(system.attributeValue("maxY"));
		levelUp = Integer.parseInt(system.attributeValue("levelUp"));
		@SuppressWarnings("unchecked")
		List<Element> rects = system.elements("rect");
		typeConfig = new ArrayList<Point[]>(rects.size());
		typeRound = new ArrayList<Boolean>();
		for (Element rect : rects) {
			// 是否可以旋转
			typeRound.add(Boolean.parseBoolean(rect.attributeValue("round")));

			@SuppressWarnings("unchecked")
			List<Element> pointConfig = rect.elements("point");
			Point[] points = new Point[pointConfig.size()];
			for (int i = 0; i < points.length; i++) {
				int x = Integer
						.parseInt(pointConfig.get(i).attributeValue("x"));
				int y = Integer
						.parseInt(pointConfig.get(i).attributeValue("y"));
				points[i] = new Point(x, y);
			}

			typeConfig.add(points);
		}
		plusPoint = new HashMap<Integer, Integer>();
		plusPoint.put(1, 10);
		plusPoint.put(2, 30);
		plusPoint.put(3, 50);
		plusPoint.put(4, 80);
	}

	public Map<Integer, Integer> getPlusPoint() {
		return plusPoint;
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}

	public int getLevelUp() {
		return levelUp;
	}

	public List<Boolean> getTypeRound() {
		return typeRound;
	}

}
