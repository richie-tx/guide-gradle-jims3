package mojo.km.security;/*
 * //U.S #79250
 * Created on Mar 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 
package mojo.km.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.naming.CommonConstants;

*//**
 * @author fsjodin
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *//*
public class DummyAuthenticator implements IAuthenticator {

	
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.security.IAuthenticator#authenticate(java.lang.String,
	 * java.lang.String)
	 
	public void authenticateOld(String userID, String password)throws AuthenticationFailedException {
		System.out.println(" You are using the DummyAuthenticator to validate your userId/Password");
		Iterator users = User.findAll("JIMSLogonId", userID);
		if (users == null || !users.hasNext()) {
			String message = "User with user login name " + userID + " could not be located.";
			throw new AuthenticationFailedException(CommonConstants.INCORRECT_USER_ERROR, message);
		}
		try {
			ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties("M204");
			if (cProp.getName().equals("M204")) {
				String driver = cProp.getDriver();
				Class.forName(driver);
				DriverManager.setLoginTimeout(10);
				String URL = cProp.getURL();

				String connectionLit = URL + "/RCL/" + userID + "/" + password;
				Connection resource = DriverManager.getConnection(connectionLit);
				resource.close();
			}
		} catch (SQLException e1) {
			throw new AuthenticationFailedException(CommonConstants.INCORRECT_PASSWORD_ERROR, "DummyAuthenticator - password validation error " + e1.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String authenticate(String logonid, String password) {
		boolean success = false;
		String message = "";
		Connection conn = null;
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
			String query = "logon " + logonid + ";" + password + ";j2con test;";

			Date curDate = new Date();
			String connectionLit = URL + "/RCL/" + "/jircl2/rcl@jims";

			try {
				conn = DriverManager.getConnection(connectionLit);
				Statement st = conn.createStatement();
				System.err.println("query=" + query);
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					String rec = rs.getString(1);
					System.err.println("rec=" + rec);
					if (rec == null)
						continue;
					if (rec.indexOf("HAS EXPIRED") >= 0) {
						curDate = new Date();
						System.out.println("[" + curDate + "] JIMS2 Distributed ::PASSWORD EXPIRED:: " + logonid);
						conn.close();
						message = CommonConstants.PROFILE_EXPIRED_ERROR;
						throw new AuthenticationFailedException(message, "Password has expired. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
					}
					if (rec.indexOf("SUSPENDED") >= 0) {
						curDate = new Date();
						System.out.println("[" + curDate + "] JIMS2 Distributed ::LOGIN SUSPENDED:: " + logonid);
						conn.close();
						message = CommonConstants.PROFILE_SUSPENDED_ERROR;
						throw new AuthenticationFailedException(message, "User ID has been suspended for password violations. Please contact the ITC Help Desk at 713-755-6624 or contact your department's security administrator: ");
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
						conn.close();
						message = CommonConstants.INCORRECT_PASSWORD_ERROR;
						throw new AuthenticationFailedException(message, "Incorrect password supplied.  Supply the correct password or your account will be suspended. ");
					}
				}
			} catch (SQLException e) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				if (success == false) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
*/