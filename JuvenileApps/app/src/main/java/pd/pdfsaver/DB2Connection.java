/*
 * Created on Sep 07, 2007
 *
 */
package pd.pdfsaver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author mchowdhury
 *
 */
public class DB2Connection{
	public static Connection getConnection(String url) throws ClassNotFoundException, SQLException{
		final String username = "db2admin";
		final String password = "db2admin";
		
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		Connection con = DriverManager.getConnection(url, username, password);
        return con;
	}
	
	public static void close(Connection con) throws SQLException{
		con.close();
	}
}