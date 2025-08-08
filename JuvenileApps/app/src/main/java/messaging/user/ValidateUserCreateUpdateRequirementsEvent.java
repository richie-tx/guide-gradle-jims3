//Source file: C:\\views\\MSA\\app\\src\\messaging\\user\\ValidateUserCreateUpdateRequirementsEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class ValidateUserCreateUpdateRequirementsEvent extends RequestEvent
{
	private String logonId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private String departmentId;
	private String ssn;
	private String genericUserType;
	private String phoneNumber;
	private String publicInd;
	private String customCodeGenerationId;

	/**
	 * default constructor
	 */
	public ValidateUserCreateUpdateRequirementsEvent()
	{

	}

	/**
	 * @param logonId
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	/**
	 * @return String
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return String
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @return String
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @param ssn
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @return String
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @param genericUserType
	 */
	public void setGenericUserType(String genericUserType)
	{
		this.genericUserType = genericUserType;
	}

	/**
	 * @return String
	 */
	public String getGenericUserType()
	{
		return genericUserType;
	}

	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return String
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * @param publicInd
	 */
	public void setPublicInd(String publicInd)
	{
		this.publicInd = publicInd;
	}

	/**
	 * @return String
	 */
	public String getPublicInd()
	{
		return publicInd;
	}
	/**
	 * @return Returns the customCodeGenerationId.
	 */
	public String getCustomCodeGenerationId() {
		return customCodeGenerationId;
	}
	/**
	 * @param customCodeGenerationId The customCodeGenerationId to set.
	 */
	public void setCustomCodeGenerationId(String customCodeGenerationId) {
		this.customCodeGenerationId = customCodeGenerationId;
	}
}