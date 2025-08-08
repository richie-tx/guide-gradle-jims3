//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\CreateJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class CreateJIMS2AccountEvent extends RequestEvent
{
	public String logonId;
	public String answer;
	public String JIMS2AccountTypeId;
	public String JIMS2AccountTypeOID;
	public String JIMS2LogonId;
	public String JIMS2Password;
	private String firstName;
	private String middleName;
	private String password;
	private String lastName;
	private String passwordQuestionId;
	private String departmentId;
	private String status;
	private boolean isCreate;

	/**
	 * @roseuid 4399CD3803DC
	 */
	public CreateJIMS2AccountEvent()
	{

	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}

	/**
	 * Access method for the answer property.
	 * 
	 * @return   the current value of the answer property
	 */
	public String getAnswer()
	{
		return answer;
	}

	/**
	 * Sets the value of the answer property.
	 * 
	 * @param aAnswer the new value of the answer property
	 */
	public void setAnswer(String aAnswer)
	{
		answer = aAnswer;
	}

	/**
	 * Access method for the JIMS2AccountTypeId property.
	 * 
	 * @return   the current value of the JIMS2AccountTypeId property
	 */
	public String getJIMS2AccountTypeId()
	{
		return JIMS2AccountTypeId;
	}

	/**
	 * Sets the value of the JIMS2AccountTypeId property.
	 * 
	 * @param aJIMS2AccountTypeId the new value of the JIMS2AccountTypeId property
	 */
	public void setJIMS2AccountTypeId(String aJIMS2AccountTypeId)
	{
		JIMS2AccountTypeId = aJIMS2AccountTypeId;
	}

	/**
	 * Access method for the JIMS2AccountTypeOID property.
	 * 
	 * @return   the current value of the JIMS2AccountTypeOID property
	 */
	public String getJIMS2AccountTypeOID()
	{
		return JIMS2AccountTypeOID;
	}

	/**
	 * Sets the value of the JIMS2AccountTypeOID property.
	 * 
	 * @param aJIMS2AccountTypeOID the new value of the JIMS2AccountTypeOID property
	 */
	public void setJIMS2AccountTypeOID(String aJIMS2AccountTypeOID)
	{
		JIMS2AccountTypeOID = aJIMS2AccountTypeOID;
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
	 * Access method for the JIMS2Password property.
	 * 
	 * @return   the current value of the JIMS2Password property
	 */
	public String getJIMS2Password()
	{
		return JIMS2Password;
	}

	/**
	 * Sets the value of the JIMS2Password property.
	 * 
	 * @param aJIMS2Password the new value of the JIMS2Password property
	 */
	public void setJIMS2Password(String aJIMS2Password)
	{
		JIMS2Password = aJIMS2Password;
	}

	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
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
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @return
	 */
	public String getPasswordQuestionId()
	{
		return passwordQuestionId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string)
	{
		password = string;
	}

	/**
	 * @param string
	 */
	public void setPasswordQuestionId(String string)
	{
		passwordQuestionId = string;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @return Returns the isCreate.
	 */
	public boolean isCreate() {
		return isCreate;
	}
	/**
	 * @param isCreate The isCreate to set.
	 */
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}
	/**
	 * Access method for the status property.
	 * 
	 * @return   the current value of the status property
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param aStatus the new value of the status property
	 */
	public void setStatus(String aStatus)
	{
		status = aStatus;
	}
}