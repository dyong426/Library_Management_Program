package lmp.members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThemeDao extends MenuDao{
	
	public String getTheme() throws SQLException {
		
		String sql = "SELECT * FROM themes WHERE theme_activation = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "1");
		ResultSet rs = pstmt.executeQuery();
		
		String getTheme = "";
		while (rs.next()) {
			getTheme = rs.getString("theme_name");
		}
		return getTheme;
	}
	
public ArrayList<String> getThemes() throws SQLException {
		
		String sql = "SELECT * FROM themes";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<String> getThemes = new ArrayList<>();
		while (rs.next()) {
			getThemes.add(rs.getString("theme_name"));
		}
		return getThemes;
	}
	
public void addTheme(String theme_name) throws SQLException {
		
		String sql = "INSERT INTO themes VALUES(theme_id_seq.nextval, ?)";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, theme_name);
		
		pstmt.executeUpdate();
	}

public void setTheme(String theme_name) throws SQLException {
	
	String sql1 = "UPDATE themes SET theme_activation = 0 WHERE theme_name <> ?";
	String sql2	= "UPDATE themes SET theme_activation = 1 WHERE theme_name = ?";
	
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