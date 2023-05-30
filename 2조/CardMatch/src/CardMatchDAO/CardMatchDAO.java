package CardMatchDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CardMatchDBConn.CardMatchDBConn;

public class CardMatchDAO {	
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public CardMatchDAO() {
		con = new CardMatchDBConn().getConnection();
	}
	
	public boolean insert(String name, int minutes, int second) { //easy rank insert
		String sql = "insert into rank values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, minutes);
			pstmt.setInt(3, second);
		    pstmt.executeUpdate();///////
		}catch(SQLException e) {
			System.out.println("insert Exception~~~");
			return false;
		}
		return true;
	}
	
	public boolean insert2(String name, int minutes, int second) { //hard rank insert
		String sql = "insert into hrank values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, minutes);
			pstmt.setInt(3, second);
		    pstmt.executeUpdate();///////
		}catch(SQLException e) {
			System.out.println("insert Exception~~~");
			return false;
		}
		return true;
	}
}