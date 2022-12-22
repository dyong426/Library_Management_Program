package lmp.members.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeDao extends MenuDao{
	
	public String getTheme(int theme_id) throws SQLException {
		
		String sql = "SELECT * FROM themes WHERE theme_id = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, theme_id);
		ResultSet rs = pstmt.executeQuery();
		
		String theme_name = "";
		while (rs.next()) {
			theme_name = rs.getString("theme_name");
		}
		return theme_name;
	}
	
public void addTheme(String theme_name) throws SQLException {
		
		String sql = "INSERT INTO themes VALUES(theme_id_seq.nextval, ?)";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, theme_name);
		
		pstmt.executeUpdate();
	}

}
