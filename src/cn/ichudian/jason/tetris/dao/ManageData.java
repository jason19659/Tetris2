//package cn.ichudian.jason.tetris.dao;
//
//
//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
//import java.util.Collections;
//import java.util.List;
//import java.util.Scanner;
//
//import cn.ichudian.jason.tetris.dto.Player;
//
///*
// * 
// * @author <a href="mailto:jason19659@163.com">jason19659</a>
// * 
// *         dao
// * 			test class
// *         娴嬭瘯绫�class for test 娴嬭瘯绫�class for test 娴嬭瘯绫�class for test
// *         娴嬭瘯绫�class for test 娴嬭瘯绫�class for test 娴嬭瘯绫�class for test
// * 		GBK ---> UTF-8
// *         2013-9-4
// */
//public class ManageData {
//	Scanner sc = new Scanner(System.in);
//
//	public static void main(String[] args) {
//		DataDisk dd = new DataDisk();
//		List<Player> listDisk = dd.loadData();
//		ManageData md = new ManageData();
//
//		listDisk.add(new Player("aaa", 111));
//		listDisk.add(new Player("aaa", 222));
//		listDisk.add(new Player("aaa", 123));
//		listDisk.add(new Player("aaa", 122));
//		saveData();
//		Collections.sort(listDisk);
//		md.print(listDisk);
//	}
//
//	private void prs(DataDisk dd, List<Player> listDisk, int[] num) {
//
//		remove(listDisk, num);
//		save(dd, listDisk);
//		print(listDisk);
//	}
//
//	private void print(List<Player> listDisk) {
//		int i = 0;
//		for (Player p : listDisk) {
//			System.out.println(i + " " + p.getName() + ":" + p.getPoint());
//			;
//			i++;
//		}
//	}
//
//	private void remove(List<Player> listDisk, int[] num) {
//		for (int i = 0; i < num.length; i++) {
//			listDisk.remove(num[i]);
//		}
//	}
//
//	private void save(DataDisk dd, List<Player> listDisk) {
//		for (int i = 0; i < listDisk.size(); i++) {
//			dd.saveData(listDisk.get(i));
//		}
//
//	}
//
//	public static void saveData() {
//		List<Player> players = new DataDisk().loadData();
//		players.removeAll(players);
//		// TODO 锟叫断硷拷录锟角否超癸拷20锟斤拷
//		players.add(new Player("jason", 100));
//
//		ObjectOutputStream oos = null;
//		try {
//			oos = new ObjectOutputStream(
//					new FileOutputStream("data/recode.dat"));
//			oos.writeObject(players);
//		} catch (Throwable t) {
//			t.printStackTrace();
//		} finally {
//			try {
//				oos.flush();
//				oos.close();
//			} catch (Throwable t) {
//				t.printStackTrace();
//			}
//		}
//
//	}
//}
