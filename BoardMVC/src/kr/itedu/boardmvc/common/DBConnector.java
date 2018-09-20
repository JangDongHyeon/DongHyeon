package kr.itedu.boardmvc.common;

import javax.sql.*;
import java.sql.*;
import javax.naming.*;

public class DBConnector {

	public static Connection getConn() throws SQLException {
		
			DataSource ds = null;
			try {
				Context init = new InitialContext();
				ds = (DataSource)init.lookup("java:comp/env/oracleDB");	
			}catch(NamingException e) {
				//TODO: 예외처리
			}catch(Exception e) {
				//TODO: 예외처리
			}
		return ds.getConnection();
	}	
	
	public static void close(Connection c, PreparedStatement p, ResultSet r) {
		if(r != null) {
			try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		if(p != null) {
			try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		if(c != null) {
			try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public static void close(Connection c) {
		close(c, null, null);
	}
	
	public static void close(PreparedStatement p) {
		close(null, p, null);
	}
		
	public static void close(PreparedStatement p, ResultSet r) {
		close(null, p, r);
	}
}
