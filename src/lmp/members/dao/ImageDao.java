package lmp.members.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lmp.members.vo.ImageVO;

public class ImageDao extends MenuDao {

	public ImageVO getImage(String image_name) throws SQLException {
		
		String sql = "SELECT * FROM images WHERE image_name = ?";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, image_name + ".png");
		ResultSet rs = pstmt.executeQuery();
		
		ImageVO imageVO = null;
		while (rs.next()) {
			imageVO = new ImageVO(rs.getInt("image_id"),rs.getString("image_name"),rs.getString("image_path"));
		}
		
		return imageVO;
	}
	
	public void addImage(File file) throws SQLException {
		
		String sql = "INSERT INTO images VALUES(image_id_seq.nextval, ?,?)";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, file.getName());
		pstmt.setString(2, file.getPath());
		
		
		pstmt.executeUpdate();
	}
	
}
