package mojo.km.security;//U.S #79250

/*package mojo.km.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.naming.CommonConstants;

public class ACF2Authenticator implements IAuthenticator {

	public void authenticate(String logonid, String password) {
		boolean success = false;
		String message = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties("M204");
		if (cProp.getName().equals("M204")) {
			String driver = cProp.getDriver();
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			DriverManager.setLoginTimeout(10);
			String URL = cProp.getURL();
			System.err.println("URL " + URL);
			String query = "logon " + logonid + ";" + password + ";j2con test;";
			System.err.println("query=" + query);
			Date curDate = new Date();			
			String connectionLit = URL + "/RCL/" + "/jircl2/rcl@jims";

			try {
				System.err.println("connectionLit= " + connectionLit);
				conn = DriverManager.getConnection(connectionLit);
			    st = conn.createStatement();
				System.err.println("query=" + query);
				rs = st.executeQuery(query);
				while (rs.next()) {
					String rec = rs.getString(1);
					System.err.println("rec=" + rec);
					if (rec == null)
						continue;
					if (rec.indexOf("HAS EXPIRED") >= 0) {
						curDate = new Date();
						System.out.println("[" + curDate + "] JIMS2 Distributed ::PASSWORD EXPIRED:: " + logonid);
						//conn.close();
						message = CommonConstants.PASSWORD_EXPIRED_ERROR;
						throw new AuthenticationFailedException(message, "Password has expired. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
					}
					if (rec.indexOf("SUSPENDED") >= 0) {
						curDate = new Date();
						System.out.println("[" + curDate + "] JIMS2 Distributed ::LOGIN SUSPENDED:: " + logonid);
						//conn.close();
						message = CommonConstants.PROFILE_SUSPENDED_ERROR;
						throw new AuthenticationFailedException(message, "User ID has been suspended for password violations. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
					}
					if (rec.indexOf("LOGONID " + logonid + " EXPIRED") >= 0) {
						curDate = new Date();
						System.out.println("[" + curDate + "] JIMS2 Distributed ::USERID EXPIRED:: " + logonid);
						//conn.close();
						message = CommonConstants.PROFILE_EXPIRED_ERROR;
						throw new AuthenticationFailedException(message, "Password has expired. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
					}
					if (rec.indexOf("EVERYTHING IS COOL") >= 0) {
						curDate = new Date();
						System.out.println("[" + curDate + "] JIMS2 Distributed ::SUCCESSFUL LOGIN:: " + logonid);
						success = true;
						message = "Logon Successful";
					}
					if (rec.indexOf("LOGIN FAILED") >= 0) {
						curDate = new Date();
						System.err.println("[" + curDate + "] JIMS2 Distributed ::LOGON FAILED:: " + logonid);
						//conn.close();
						message = CommonConstants.INCORRECT_PASSWORD_ERROR;
						throw new AuthenticationFailedException(message, "Incorrect password supplied.  Supply the correct password or your account will be suspended. ");
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
			}  finally {		
				
				
				try {				
					if(conn != null && !conn.isClosed())
						conn.close();					
				} catch (Exception e) {
					e.printStackTrace();
					throw new AuthenticationFailedException("Authentication failed. Failed to close connection.");
				}
				
			}
		}
	}
}*/