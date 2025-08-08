package mojo.km.security.authentication.acf2;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.

/*package mojo.km.security.authentication.acf2;

import javax.security.auth.login.LoginException;

import messaging.authentication.UpdatePasswordEvent;
import messaging.authentication.ValidateUserEvent;
import mojo.km.security.authentication.ExpiredProfileException;
import mojo.km.security.authentication.IncorrectPasswordException;
import mojo.km.security.authentication.IncorrectUserException;
import mojo.km.security.authentication.SuspendedProfileException;
import mojo.km.security.jaas.loginModule.DefaultLoginModule;
import mojo.km.security.role.IUser;
import mojo.naming.CommonConstants;

import com.ibm.os390.security.PlatformReturned;
import com.ibm.os390.security.PlatformUser;

*//**
 * @author dnikolis
 *
 * This class is used to validate a user against ACF2.
 * 
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			11/25/03		Added Javadocs
 * Dnikolis			12/03/03		Updating Javadocs
 * JMcNabb			2/05/05			Changing implementation
 * 
 *//*
public class ACF2LoginModule extends DefaultLoginModule {
	private ValidateUserEvent validateUserEvent;
	private String userID;
	private String password;

	public static final int ERRNO_EXPIRED = 168;
	public static final int ERRNO_INCORRECT_USER = 143;
	public static final int ERRNO_INVALID_PASSWORD = 111;
	public static final int ERRNO_PASSWORD_LIMIT = 163;

	*//**
	 * Constructor ACF2LoginModule.
	 * @param aUserID
	 * @param aPassword
	 *//*
	public ACF2LoginModule(String aUserID, String aPassword) {
		this.userID = aUserID;
		this.password = aPassword;
	}

	*//**
	 * Constructor ACF2LoginModule.
	 *//*
	public ACF2LoginModule() {
	}

	*//**
	 * Method login.
	 * @return loginSuccess
	 * @throws LoginException
	 * This method checks to see the status of a user.
	 *//*
	public boolean login() throws LoginException {
		try {
			validateUserEvent = getUser(userID, password);
			if (this.isProfileExpired(validateUserEvent)) {
				System.out.println(userID + " Profile has Expired");
				throw new ExpiredProfileException(userID + " profile has expired.");
			} else if (this.isProfileSuspended(validateUserEvent)) {
				System.out.println(userID + " Profile has been Suspended");
				throw new SuspendedProfileException(userID + " profile has been suspended.");
			}
			return true;
		} catch (IncorrectUserException e) {
			throw new IncorrectUserException(e.getMessage());
		} catch (IncorrectPasswordException e) {
			throw new IncorrectPasswordException(e.getMessage());
		} catch (ExpiredProfileException e) {
			throw new ExpiredProfileException(e.getMessage());
		} catch (SuspendedProfileException e) {
			throw new SuspendedProfileException(e.getMessage());
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	*//**
	 * Method getUser.
	 * @param aUserID
	 * @param aPassword
	 * @return lUserLogin
	 * @throws LoginException
	 * This method validates the user against ACF2
	 *//*
	private ValidateUserEvent getUser(String aUserID, String aPassword)
		throws LoginException {
		ValidateUserEvent lUserLogin = new ValidateUserEvent();
		//System.out.println("ACF2LoginModule aUserID   = (" + aUserID + ")");
		//System.out.println("ACF2LoginModule aPassword = (" + aPassword + ")");
		PlatformReturned pr = PlatformUser.authenticate(aUserID, aPassword);
		System.out.println("Platform Returned= (" + pr + ")");
		if (pr != null) {
			System.out.println("errno     = " + pr.errno);
			System.out.println("errno2    = " + pr.errno2);
			System.out.println("errnoMsg  = " + pr.errnoMsg);
			System.out.println("objectRet = " + pr.objectRet);
			System.out.println("stringRet = " + pr.stringRet);
			System.out.println("success   = " + pr.success);
			if (pr.errno == ERRNO_INCORRECT_USER) {
				System.out.println(aUserID + " Throwing Incorrect User Exception");
				throw new IncorrectUserException("Incorrect user: " + aUserID);
			} else if (pr.errno == ERRNO_INVALID_PASSWORD) {
				System.out.println(aUserID + " Throwing Incorrect Password Attempt Exception");
				throw new IncorrectPasswordException("Incorrect password attempt.");
			} else if (pr.errno == ERRNO_EXPIRED) {
				lUserLogin.setStatus(CommonConstants.EXPIRED);
				System.out.println(aUserID + " Setting Profile to Expired");
			} else if (pr.errno == ERRNO_PASSWORD_LIMIT) {
				lUserLogin.setStatus(CommonConstants.SUSPENDED);
				System.out.println(aUserID + " Setting Profile to Suspended");
			}
		} else {
			lUserLogin.setUserID(aUserID);
			lUserLogin.setPassword(aPassword);
			lUserLogin.setStatus(CommonConstants.ACTIVE);
			System.out.println(lUserLogin.getUserID() + " Setting Profile to Active");
		}
		return lUserLogin;
	}

	*//**
	 * Method updatePassword.
	 * @param aPassword
	 * @param aNewPassword
	 * @return boolean
	 * @throws LoginException
	 * @throws IncorrectPasswordException
	 * This method updates a users password in ACF2
	 *//*
	public boolean updatePassword(UpdatePasswordEvent anEvent) throws LoginException, IncorrectPasswordException {
		
		try {
			PropertyBundleProperties lProp = PropertyBundleProperties.getInstance();
			platform = lProp.getProperty(ACF2PLATFORM);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
		
		ValidateUserEvent lvalidateUserEvent = new ValidateUserEvent();
		
		PropertyCopier.copyProperties(anEvent, lvalidateUserEvent);
		if (platform.equals(WORKSTATION_PLATFORM)) {
			if (anEvent.getLogonID().equals("JIRAC") && anEvent.getPassword().equals("TEST1")) {
				//for testing on the workstation
				System.out.println(anEvent.getUserID() + " Throwing Incorrect Password Exception");
				throw new IncorrectPasswordException("Incorrect password");
			} else {
				lvalidateUserEvent.setPassword(anEvent.getNewPassword());
				lvalidateUserEvent.setStatus(CommonConstants.ACTIVE);
			}		
		} else {
		
			PlatformReturned pr = PlatformUser.changePassword(anEvent.getUserID(),anEvent.getPassword(),anEvent.getNewPassword());
			if (pr != null) {		
				System.out.println("errno     = " + pr.errno);
				System.out.println("errno2    = " + pr.errno2);
				System.out.println("errnoMsg  = " + pr.errnoMsg);
				System.out.println("objectRet = " + pr.objectRet);
				System.out.println("stringRet = " + pr.stringRet);
				System.out.println("success   = " + pr.success);
				System.out.println(anEvent.getUserID() + " Throwing Incorrect Password Exception");
				throw new IncorrectPasswordException("Incorrect password");
			} else {
				lvalidateUserEvent.setPassword(anEvent.getNewPassword());
				lvalidateUserEvent.setStatus(CommonConstants.ACTIVE);
				System.out.println(lvalidateUserEvent.getUserID() + " Setting Profile to Active");
			}
		//}
		this.setValidateUserEvent(lvalidateUserEvent);
		return true;
	}
	
	*//**
	 * Method getValidateUserEvent.
	 * @return validateUserEvent
	 *//*
	public ValidateUserEvent getValidateUserEvent() {
		return validateUserEvent;
	}
	*//**
	 * Method setValidateUserEvent. 
	 * @param event
	 *//*
	public void setValidateUserEvent(ValidateUserEvent event) {
		validateUserEvent = event;
	}

	*//**
	 * @see km.security.ILoginHelper#isProfileExpired()
	 *//*
	public boolean isProfileExpired(ValidateUserEvent aUserLogin) {
		return aUserLogin.getStatus().equals(CommonConstants.EXPIRED);
	}

	*//**
	 * @see km.security.ILoginHelper#isProfileSuspended()
	 *//*
	public boolean isProfileSuspended(ValidateUserEvent aUserLogin) {
		return aUserLogin.getStatus().equals(CommonConstants.SUSPENDED);
	}

}
*/