package lmp.members.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FontDao extends MenuDao{
		
		public String getFont() throws SQLException {
			
			String sql = "SELECT * FROM fonts WHERE font_activation = ?";
			
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();
			
			String getFont = "";
			while (rs.next()) {
				getFont = rs.getString("font_size");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return getFont;
		}
		
	public ArrayList<String> getFonts() throws SQLException {
			
			String sql = "SELECT * FROM fonts";
			
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<String> getFonts = new ArrayList<>();
			while (rs.next()) {
				getFonts.add(rs.getString("font_size"));
			}
			return getFonts;
		}
		
	public void addTheme(String font_size) throws SQLException {
			
			String sql = "INSERT INTO fonts VALUES(font_id_seq.nextval, ?,?)";
			
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, font_size);
			pstmt.setString(2, "0");
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}

	public void setFont(String font_size) throws SQLException {
		
		String sql1 = "UPDATE fonts SET font_activation = 0 WHERE font_size <> ?";
		String sql2	= "UPDATE fonts SET font_activation = 1 WHERE font_size = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		pstmt.setString(1, font_size);
		pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, font_size);
		pstmt.executeUpdate();
		
		conn.commit();
		
		pstmt.close();
		conn.close();
	}


}
