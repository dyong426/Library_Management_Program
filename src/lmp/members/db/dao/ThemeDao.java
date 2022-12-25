package lmp.members.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.members.db.vo.ThemeVO;

public class ThemeDao extends MenuDao {

	public ThemeVO getTheme() throws SQLException {

		String sql = "SELECT * FROM themes WHERE theme_activation = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 1);
		ResultSet rs = pstmt.executeQuery();

		ThemeVO themeVO = null;
		while (rs.next()) {
			themeVO = new ThemeVO(rs.getInt("theme_id"), rs.getString("theme_name"), rs.getString("theme_activation"));
		}

		rs.close();
		pstmt.close();
		conn.close();

		return themeVO;
	}

	public ArrayList<ThemeVO> getThemes() {

		String sql = "SELECT * FROM themes";
		ArrayList<ThemeVO> getThemes = new ArrayList<>();

		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				getThemes.add(new ThemeVO(rs.getInt("theme_id"), rs.getString("theme_name"),
						rs.getString("theme_activation")));
			}

		} catch (SQLException e) {

		}
		return getThemes;
	}

	public void addTheme(String theme_name) throws SQLException {

		String sql = "INSERT INTO themes VALUES(theme_id_seq.nextval, ?)";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, theme_name);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void setTheme(String theme_name) throws SQLException {

		String sql1 = "UPDATE themes SET theme_activation = 0 WHERE theme_name <> ?";
		String sql2 = "UPDATE themes SET theme_activation = 1 WHERE theme_name = ?";

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		pstmt.setString(1, theme_name);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, theme_name);
		pstmt.executeUpdate();

		conn.commit();

		pstmt.close();
		conn.close();
	}

}
