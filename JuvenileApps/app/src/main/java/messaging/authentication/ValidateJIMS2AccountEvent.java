//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\authentication\\ValidateJIMS2AccountEvent.java

package messaging.authentication;

import mojo.km.messaging.RequestEvent;

public class ValidateJIMS2AccountEvent extends RequestEvent
{
	//public String logonId;
	private String JIMS2AccountTypeId;
	private String JIMS2AccountTypeOID;
	private String JIMS2LogonId;
	private String jimsLogonId;
	//private String departmentId;
	//private String payrollNum;
	//private String badgeNum;

	/**
	 * @roseuid 4399CD3D0207
	 */
	public ValidateJIMS2AccountEvent()
	{

	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
//	public java.lang.String getLogonId()
//	{
//		return logonId;
//	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
//	public void setLogonId(java.lang.String aLogonId)
//	{
//		logonId = aLogonId;
//	}

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
	 * @return
	 */
//	public String getDepartmentId()
//	{
//		return departmentId;
//	}

	/**
	 * @param string
	 */
//	public void setDepartmentId(String string)
//	{
//		departmentId = string;
//	}

	/**
	 * @return
	 */
//	public String getBadgeNum()
//	{
//		return badgeNum;
//	}

	/**
	 * @return
	 */
//	public String getPayrollNum()
//	{
//		return payrollNum;
//	}

	/**
	 * @param string
	 */
//	public void setBadgeNum(String string)
//	{
//		badgeNum = string;
//	}

	/**
	 * @param string
	 */
//	public void setPayrollNum(String string)
//	{
//		payrollNum = string;
//	}

	/**
	 * @return Returns the jimsLogonId.
	 */
	public String getJimsLogonId() {
		return jimsLogonId;
	}
	/**
	 * @param jimsLogonId The jimsLogonId to set.
	 */
	public void setJimsLogonId(String jimsLogonId) {
		this.jimsLogonId = jimsLogonId;
	}
}