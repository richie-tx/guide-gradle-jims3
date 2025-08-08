//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\UpdateJIMS2AccountPasswordEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class UpdateJIMS2AccountPasswordEvent extends RequestEvent
{
	public String JIMS2LogonId;
	//86322
	public String currentPassword;
	public String newPassword;
	public String securityManagerUserId;
	

	/**
	 * @roseuid 4399CD3C0330
	 */
	public UpdateJIMS2AccountPasswordEvent()
	{

	}

	/**
	 * Access method for the JIMS2LogonId property.
	 * 
	 * @return   the current value of the JIMS2LogonId property
	 */
	public String getJIMS2LogonId()
	{
		return JIMS2LogonId;
	}

	/**
	 * Sets the value of the JIMS2LogonId property.
	 * 
	 * @param aJIMS2LogonId the new value of the JIMS2LogonId property
	 */
	public void setJIMS2LogonId(String aJIMS2LogonId)
	{
		JIMS2LogonId = aJIMS2LogonId;
	}

	/**
	 * @return the currentPassword
	 */
	public String getCurrentPassword()
	{
	    return currentPassword;
	}

	/**
	 * @param currentPassword the currentPassword to set
	 */
	public void setCurrentPassword(String currentPassword)
	{
	    this.currentPassword = currentPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword()
	{
	    return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword)
	{
	    this.newPassword = newPassword;
	}

	/**
	 * @return the securityManagerUserId
	 */
	public String getSecurityManagerUserId()
	{
	    return securityManagerUserId;
	}

	/**
	 * @param securityManagerUserId the securityManagerUserId to set
	 */
	public void setSecurityManagerUserId(String securityManagerUserId)
	{
	    this.securityManagerUserId = securityManagerUserId;
	}

	

/*	*//**
	 * Access method for the passwordQuestion property.
	 * 
	 * @return   the current value of the passwordQuestion property
	 *//*
	public java.lang.String getPasswordQuestionId()
	{
		return passwordQuestionId;
	}*/

/*	*//**
	 * Sets the value of the passwordQuestion property.
	 * 
	 * @param aPasswordQuestion the new value of the passwordQuestion property
	 *//*
	public void setPasswordQuestionId(java.lang.String aPasswordQuestionId)
	{
		passwordQuestionId = aPasswordQuestionId;
	}

	*//**
	 * Access method for the answer property.
	 * 
	 * @return   the current value of the answer property
	 *//*
	public java.lang.String getAnswer()
	{
		return answer;
	}

	*//**
	 * Sets the value of the answer property.
	 * 
	 * @param aAnswer the new value of the answer property
	 *//*
	public void setAnswer(java.lang.String aAnswer)
	{
		answer = aAnswer;
	}*/
}