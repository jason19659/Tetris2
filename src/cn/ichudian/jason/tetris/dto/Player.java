package cn.ichudian.jason.tetris.dto;

import java.io.Serializable;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * dto
 *
 * 2013-9-13
 */
public class Player implements Comparable<Player>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1560262108930690698L;
	private String name;
	private int point;

	public Player(String name, int point) {
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int compareTo(Player p) {
		return p.point - this.point;
	}

}
