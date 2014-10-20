package cn.ichudian.jason.tetris.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.ichudian.jason.tetris.dto.Player;

/**
 * 
 * @author <a href="mailto:jason19659@163.com">jason19659</a>
 *
 * dao
 *
 * 2013-9-13
 */
public class DataBase implements Data {

	private final String dbUrl;

	private final String dbUser;
	private final String dbPwd;
	private String sql = "select * from rank";

	public DataBase(HashMap<String, String> param) {
		dbUrl = param.get("dbUrl");
		dbUser = param.get("dbUser");
		dbPwd = param.get("dbPwd");
		try {
			Class.forName(param.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Player> loadData() {
		List<Player> players = new ArrayList<Player>();
		try {
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Player p = new Player(rs.getString("name"), rs.getInt("point"));
				players.add(p);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return players;
	}

	@Override
	public void saveData(Player player) {
		try {
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into rank values(null,?,?,now())");
			pstmt.setString(1, player.getName());
			pstmt.setInt(2, player.getPoint());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
