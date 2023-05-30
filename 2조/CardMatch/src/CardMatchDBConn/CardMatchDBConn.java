package CardMatchDBConn;

import java.sql.Connection;
import java.sql.DriverManager;

public class CardMatchDBConn {
	
	private Connection con;

	public CardMatchDBConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return con;
	}
	
}

