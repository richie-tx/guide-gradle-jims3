/*
 * Created on Jan 13, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.authentication;

import mojo.km.messaging.RequestEvent;
import mojo.messaging.securitytransactionsevents.reply.LoginResponseEvent;

/**
 * @author Rcooper To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ManageSessionEvent extends RequestEvent
{
    public String firstName;
    public String JIMS2AccountTypeId;
    public String JIMS2AccountTypeOID;
    public String JIMS2LogonId;
    public String JIMS2Password;
    public String JIMSLogonId;
    public String JIMSPassword;
    public String lastName;
    public String middleName;
    private LoginResponseEvent loginResponse; //87188
    //86322
    public String securityManagerUserId;

    /**
     * @return the securityManagerUserId
     */
    public String getSecurityManagerUserId()
    {
	return securityManagerUserId;
    }

    /**
     * @param securityManagerUserId
     *            the securityManagerUserId to set
     */
    public void setSecurityManagerUserId(String securityManagerUserId)
    {
	this.securityManagerUserId = securityManagerUserId;
    }

    /**
     * @return
     */
    public String getFirstName()
    {
	return firstName;
    }

    /**
     * @return
     */
    public String getJIMS2AccountTypeId()
    {
	return JIMS2AccountTypeId;
    }

    /**
     * @return
     */
    public String getJIMS2AccountTypeOID()
    {
	return JIMS2AccountTypeOID;
    }

    /**
     * @return
     */
    public String getJIMS2LogonId()
    {
	return JIMS2LogonId;
    }

/*    *//**
     * @return
     *//*
    public String getJIMS2Password()
    {
	return JIMS2Password;
    }*/

    /**
     * @return
     */
    public String getJIMSLogonId()
    {
	return JIMSLogonId;
    }

  /*  *//**
     * @return
     *//*
    public String getJIMSPassword()
    {
	return JIMSPassword;
    }*/

    /**
     * @return
     */
    public String getLastName()
    {
	return lastName;
    }

    /**
     * @return
     */
    public String getMiddleName()
    {
	return middleName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }

    /**
     * @param JIMS2AccountTypeId
     */
    public void setJIMS2AccountTypeId(String JIMS2AccountTypeId)
    {
	this.JIMS2AccountTypeId = JIMS2AccountTypeId;
    }

    /**
     * @param JIMS2AccountTypeOID
     */
    public void setJIMS2AccountTypeOID(String JIMS2AccountTypeOID)
    {
	this.JIMS2AccountTypeOID = JIMS2AccountTypeOID;
    }

    /**
     * @param string
     */
    public void setJIMS2LogonId(String string)
    {
	JIMS2LogonId = string;
    }

/*    *//**
     * @param string
     *//*
    public void setJIMS2Password(String string)
    {
	JIMS2Password = string;
    }*/

    /**
     * @param JIMSLogonId
     */
    public void setJIMSLogonId(String JIMSLogonId)
    {
	this.JIMSLogonId = JIMSLogonId;
    }

/*    *//**
     * @param JIMSPassword
     *//*
    public void setJIMSPassword(String JIMSPassword)
    {
	this.JIMSPassword = JIMSPassword;
    }*/

    /**
     * @param lastName
     */
    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    /**
     * @param middleName
     */
    public void setMiddleName(String middleName)
    {
	this.middleName = middleName;
    }

    /**
     * @return the loginResponse
     */
    public LoginResponseEvent getLoginResponse()
    {
	return loginResponse;
    }

    /**
     * @param loginResponse
     *            the loginResponse to set
     */
    public void setLoginResponse(LoginResponseEvent loginResponse)
    {
	this.loginResponse = loginResponse;
    }

}
