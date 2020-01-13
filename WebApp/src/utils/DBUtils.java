package utils;

import java.awt.dnd.DnDConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	public static String DB_NAME;
	
	static {
		DB_NAME = AppSettings.getInstance().getProperty("db_name");
	}
	// FOR JUNIT
	public static void switchDB(String key) {
		DB_NAME = AppSettings.getInstance().getProperty(key);
	}
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	public static final String DB_USER = "root";
	public static final String DB_USER_PSW = "";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_USER_PSW);
			System.out.println("Connection is open - " + conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void release(Connection conn, Statement stmt, PreparedStatement pstmt) {

		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Connection is CLOSED - " + conn);
			}
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
				System.out.println("Statement is CLOSED - " + stmt);
			}
			
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
				System.out.println("PreparedStatement is CLOSED - " + pstmt);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		getConnection();
	}
}
