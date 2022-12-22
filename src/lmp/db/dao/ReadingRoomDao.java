package lmp.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lmp.db.vo.ReadingRoomVO;

public class ReadingRoomDao extends MenuDao{

	@Override
	public ArrayList<ReadingRoomVO> get() throws SQLException {
		String sql = "SELECT * FROM readingroom";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<ReadingRoomVO> readingrooms = new ArrayList<>();
		ReadingRoomVO readingRoomVO = null; 
		while (rs.next()) {
			readingRoomVO = new ReadingRoomVO(rs.getInt("seat_num"), rs.getString("table_divider"));
			readingrooms.add(readingRoomVO);
		}
		
		return readingrooms;
	}
	
}
