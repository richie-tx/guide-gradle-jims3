//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\ValidateDepartmentRequirementsEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class ValidateDepartmentCreateRequirementsEvent extends RequestEvent
{
	private String agencyId;
	private String departmentId;
	private String departmentName;
	private String orgCode;
	

	/**
	 * @roseuid 43063490027F
	 */
	public ValidateDepartmentCreateRequirementsEvent()
	{

	}

	/**
	 * Access method for the agencyId property.
	 * 
	 * @return   the current value of the agencyId property
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * Access method for the departmentId property.
	 * 
	 * @return   the current value of the departmentId property
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * Access method for the departmentName property.
	 * 
	 * @return   the current value of the departmentName property
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * Access method for the orgCode property.
	 * 
	 * @return   the current value of the orgCode property
	 */
	public String getOrgCode()
	{
		return orgCode;
	}

	/**
	 * Sets the value of the agencyId property.
	 * 
	 * @param aAgencyId the new value of the agencyId property
	 */
	public void setAgencyId(String aAgencyId)
	{
		agencyId = aAgencyId;
	}

	/**
	 * Sets the value of the departmentId property.
	 * 
	 * @param aDepartmentId the new value of the departmentId property
	 */
	public void setDepartmentId(String aDepartmentId)
	{
		departmentId = aDepartmentId;
	}

	/**
	 * Sets the value of the departmentName property.
	 * 
	 * @param aDepartmentName the new value of the departmentName property
	 */
	public void setDepartmentName(String aDepartmentName)
	{
		departmentName = aDepartmentName;
	}

	/**
	 * Sets the value of the orgCode property.
	 * 
	 * @param aOrgCode the new value of the orgCode property
	 */
	public void setOrgCode(String aOrgCode)
	{
		orgCode = aOrgCode;
	}
}