/*
 * Created on Jan 18, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security.securitytransactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.AuthenticationFailedException;
import mojo.km.transaction.Transactional;
import mojo.messaging.securitytransactionsevents.UpdateACF2PasswordEvent;
import mojo.naming.CommonConstants;

/**
 * @author Rcooper
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateACF2PasswordCommand implements ICommand, Transactional {
	public static final int INVALID_PASSWORD = 111;
	public static final int HOUR_PASSWORD = 169;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		System.out.println("Inside execute of UpdateACF2PasswordCommand");
		Connection conn = null;
		boolean success = false;
		UpdateACF2PasswordEvent loginEvent = (UpdateACF2PasswordEvent) event;
		String userid = loginEvent.getLogonId();
		String password = loginEvent.getPassword();
		String newPassword = loginEvent.getNewPassword();

		//PlatformReturned pr = PlatformUser.changePassword(userid, password, newPassword);
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
			String query = "logoncp " + userid + ";" + password + ";" + newPassword + ";" + newPassword + ";";

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
		
					if ((rec.indexOf("NOT SET") >= 0) ||
					   (rec.indexOf("NOT MATCHED") >= 0)||
					   (rec.indexOf("LOGIN FAILED") >= 0))
					{
						rec = rec.substring(49);
						conn.close();
						throw new AuthenticationFailedException(CommonConstants.INCORRECT_PASSWORD_ERROR,rec);
					}
					else if (rec.indexOf("NEW PASSWORD MATCHES A PREVIOUS PASSWORD") >= 0) {
						rec = rec.substring(49);
						conn.close();
						throw new AuthenticationFailedException(CommonConstants.INVALID_PASSWORD_ERROR,rec);
					}
					else
					{
						success = true;
						System.out.println("[" + curDate + "] JIMS2 Distributed ::SUCCESSFUL UPDATE OF ACF2 PASSWORD FOR USER:: " + userid);
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}
}