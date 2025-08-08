package mojo.km.security.securitytransactions;

import java.util.Map;

import mojo.km.config.SecurityProperties;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.security.AuthenticationFailedException;
import mojo.km.security.IAuthenticator;
import mojo.km.security.SecurityUser;
import mojo.km.transaction.Transactional;
import mojo.messaging.securitytransactionsevents.LoginEvent;
import mojo.messaging.securitytransactionsevents.reply.AuthenticationFailedResponseEvent;
import mojo.messaging.securitytransactionsevents.reply.LoginResponseEvent;

/**
 * Responsible for implementing behavior of analysis method login of control
 * class mojo.km.security.securitytransactions.CredentialController
 * 
 * @author JMcNabb
 * @version 1.0
 * @author James McNabb
 * @version 1.0
 * @modelguid {05ED7116-AD18-476B-B028-5D70F5B58098}
 */
public class LoginCommand implements ICommand, Transactional
{
    // private LoginContext loginContext;

    /**
     * Default constructor
     * 
     * @modelguid {99CF3EBA-77CB-49D8-89E0-134B4B19F3AC}
     */
    public LoginCommand()
    {
    }

    /**
     * Provides behavior for application requirements. It is executed when event
     * is posted from requesting context.
     * 
     * @param event
     *            - houses data for command operation.
     * @exception thrown
     *                if error in application behavior
     * @modelguid {F3E954F7-79F4-4DB4-9756-F3EC4328D259}
     */
    public void execute(IEvent event) throws Exception
    {
	System.out.println("Inside execute of LoginCommand");
	LoginEvent loginEvent = (LoginEvent) event;
	String userid = loginEvent.getUsername();
	String password = loginEvent.getPassword();
	// U.S #79250
	String credentialType = loginEvent.getCredentialType();
	String noOfAttempts = loginEvent.getNoOfAttempts();
	Map<String, SecurityUser> userInfoMap = null;
	IAuthenticator authenticator = null;

	if (authenticator == null)
	{
	    String authenticatorImpl = SecurityProperties.getInstance().getAuthenticatorImpl();

	    if (authenticatorImpl == null)
	    {
		throw new RuntimeException("No Authenticator found. You must register an Authenticator in the Security section in mojo.xml");
	    }
	    try
	    {
		authenticator = (IAuthenticator) Class.forName(authenticatorImpl).newInstance();
	    }
	    catch (Exception e)
	    {
		e.printStackTrace(System.out);
		throw new RuntimeException("Could not create instance of Authenticator. Exception is " + e);
	    }
	}
	try
	{
	    System.out.println("before authenticate" + userid);
	    LoginResponseEvent loginResp = null;
	    // U.S #79250
	    userInfoMap = authenticator.authenticate(userid, password, credentialType, noOfAttempts);
	    if (userInfoMap != null && !userInfoMap.isEmpty())
	    {
		SecurityUser user = userInfoMap.get("securityUser");
		if (user != null)
		{
		    // populate loginResponseEvent
		    loginResp = new LoginResponseEvent();
		    loginResp.setSecUser(user);
		}
	    }

	    System.out.println("after authenticate");
	    EventManager.getSharedInstance(EventManager.REPLY).postEvent(loginResp); // U.S
										     // #79250
	}
	catch (AuthenticationFailedException e)
	{
	    AuthenticationFailedResponseEvent response = new AuthenticationFailedResponseEvent();
	    e.fill(response);
	    EventManager.getSharedInstance(EventManager.REPLY).postEvent(response);
	}
	catch (Exception e)
	{
	    System.out.println("Throwing exception form login command:" + e.toString());

	    ExceptionHandler.executeCallbacks(e);
	    throw new ReturnException(e.toString());

	}
    }

    /**
     * populate loginResponseEvent
     * //#87188
     * @param userInfo
     * @return LoginResponseEvent
     */
   /* private LoginResponseEvent populateLoginResponseEvent(SecurityUserProfile userInfo)
    {
	LoginResponseEvent loginResp = new LoginResponseEvent();
	loginResp.setResponse(userInfo);
	loginResp.setUvCode(userInfo.getJIMSLogonId());
	List<CredentialBean> credentials = userInfo.getSecuritycredentials();
	if (credentials != null && !credentials.isEmpty())
	{
	    Iterator<CredentialBean> credentialItr = credentials.iterator();
	    while (credentialItr.hasNext())
	    {
		CredentialBean credential = credentialItr.next();
		if (credential != null)
		{
		    if (credential.getCredentialtypeDescription() != null && credential.getCredentialtypeDescription().equalsIgnoreCase("JUCODE"))
		    {
			System.out.println("*** UVCODE FROM SECURITY MANAGER SERVICE*****" + credential.getCredentialtypeValue());
			String uvCode = credential.getCredentialtypeValue();
			loginResp.setUvCode(uvCode);
			break;
		    }
		}
	    }
	}
	return loginResp;
    }*/

    /**
     * Upon command registration with context this method will get executed
     * 
     * @param event
     *            - sample event data.
     * @modelguid {05E8BE6B-5AFB-4EC6-9AEF-42793A989DA7}
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * Upon command unregistration from context this method will get executed
     * 
     * @param event
     *            - sample event
     * @modelguid {F8E68958-CE81-435F-A8CD-47EA7F11FB3C}
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * If command requires data before execute is called context will place the
     * in command
     * 
     * @param object
     *            - housing input data
     * @modelguid {2DB631C3-A33D-46EB-AB62-3C8A375A5719}
     */

    public void update(Object object)
    {
    }
}
