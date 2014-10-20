package cn.ichudian.jason.tetris.util;
/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * util
 *
 * 2013-9-13
 */
public class GameFuction {

	public static long getSleepTimeByLevel(int level) {
		long sleep = (-40 * level + 740);
		sleep = sleep < 100 ? 100 : sleep;
		return sleep;
	}
}
