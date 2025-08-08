package mojo.messaging.securitytransactionsevents.reply;

import java.io.Serializable;

import mojo.km.messaging.ResponseEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.SecurityUser;

/** @author jmcnabb
 * 
 *         Responsible for housing data that will be sent back to LoginAction (client). */
public class LoginResponseEvent extends ResponseEvent
{
    private String message;

    private String userDisplayName;

    private ISecurityManager sm;

    private Serializable userProfile;

    private String errorType;

    private String userProfileSessionKey;

    private SecurityUser secUser; //#87188

    public LoginResponseEvent()
    {
    }

    public String getUserDisplayName()
    {
	return userDisplayName;
    }

    /** @param name */
    public void setUserDisplayName(String name)
    {
	this.userDisplayName = name;
    }

    /** @return */
    public String getMessage()
    {
	return message;
    }

    /** @return */
    public boolean isError()
    {
	return message != null;
    }

    /** @param string */
    public void setMessage(String string)
    {
	message = string;
    }

    public void setSecurityManager(ISecurityManager sm)
    {
	this.sm = sm;
    }

    public ISecurityManager getSecurityManager()
    {
	return sm;
    }

    public void setUserProfileImpl(Serializable userProfile)
    {
	this.userProfile = userProfile;
    }

    public Serializable getUserProfileImpl()
    {
	return userProfile;
    }

    /** @return */
    public String getErrorType()
    {
	return errorType;
    }

    /** @param string */
    public void setErrorType(String string)
    {
	errorType = string;
    }

    /** @return */
    public String getUserProfileSessionKey()
    {
	return userProfileSessionKey;
    }

    /** @param string */
    public void setUserProfileSessionKey(String string)
    {
	userProfileSessionKey = string;
    }

    /**
     * @return the secUser
     */
    public SecurityUser getSecUser()
    {
	return secUser;
    }

    /**
     * @param secUser the secUser to set
     */
    public void setSecUser(SecurityUser secUser)
    {
	this.secUser = secUser;
    }

  

}
