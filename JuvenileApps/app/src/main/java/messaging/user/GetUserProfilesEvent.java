//Source file: C:\\Views\\MSA\\app\\src\\messaging\\user\\GetUserProfilesEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class GetUserProfilesEvent extends RequestEvent
{
	private String logonId;
	private String jims2LogonId;
	private String operatorId;
	private String lastName;
	private String firstName;
	private String middleName;
	private Date dateOfBirthFrom;
	private Date dateOfBirthTo;
	private String ssn;
	private String userStatus;
	private String agencyId;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private String genericUserType;
	private String publicInd;
	private String userType;
	private String requestorLastName;
	private String requestorFirstName;
	private Date activationDateFrom;
	private Date activationDateTo;
	private Date inactivationDateFrom;
	private Date inactivationDateTo;
	private Date changeDateFrom;
	private Date changeDateTo;

	/**
	 * default constructor
	 * @roseuid 4107BEE2018A
	 */
	public GetUserProfilesEvent()
	{
	}

	/**
	 * @return java.util.Date
	 * @roseuid 4107B06C0285
	 */
	public Date getActivationDateFrom()
	{
		return activationDateFrom;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 4107B06C02C3
	 */
	public Date getActivationDateTo()
	{
		return activationDateTo;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C027A
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return Date
	 * @roseuid 4107B06C0290
	 */
	public Date getDateOfBirthFrom()
	{
		return dateOfBirthFrom;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 411A7AE2017E
	 */
	public Date getDateOfBirthTo()
	{
		return dateOfBirthTo;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C0297
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C029B
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C02B3
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return String
	 */
	public String getOperatorId()
	{
		return operatorId;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C02A2
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C02A6
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @return String
	 * @roseuid 4107B06C02AC
	 */
	public String getUserStatus()
	{
		return userStatus;
	}

	/**
	 * @return String
	 * @roseuid 43F3570103E2
	 */
	public String getRequestorFirstName()
	{
		return requestorFirstName;
	}

	/**
	 * @return String
	 * @roseuid 43F3570103E3
	 */
	public String getRequestorLastName()
	{
		return requestorLastName;
	}

	/**
	 * @return Date
	 * @roseuid 4107B06C028C
	 */
	public Date getChangeDateFrom()
	{
		return changeDateFrom;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 4107B06C02C9
	 */
	public Date getChangeDateTo()
	{
		return changeDateTo;
	}

	/**
	 * @return String
	 * @roseuid 43F357390324
	 */
	public String getUserType()
	{
		return userType;
	}

	/**
	 * @return String
	 * @roseuid 43F357390325
	 */
	public String getGenericUserType()
	{
		return genericUserType;
	}

	/**
	 * @return String
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return String
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return String
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getInactivationDateFrom()
	{
		return inactivationDateFrom;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getInactivationDateTo()
	{
		return inactivationDateTo;
	}

	/**
	 * @return String
	 */
	public String getPublicInd()
	{
		return publicInd;
	}

	/**
	 * @return String
	 */
	public String isPublicInd()
	{
		return publicInd;
	}

	/**
	 * @param activationDateFrom
	 * @roseuid 4107B06C0283
	 */
	public void setActivationDateFrom(Date activationDateFrom)
	{
		this.activationDateFrom = activationDateFrom;
	}

	/**
	 * @param activationDateTo
	 * @roseuid 4107B06C02C1
	 */
	public void setActivationDateTo(Date activationDateTo)
	{
		this.activationDateTo = activationDateTo;
	}

	/**
	 * @param agencyId
	 * @roseuid 4107B06C0278
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 * @param agencyName
	 * @roseuid 43F3564A01E0
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	/**
	 * @param dateOfBirthFrom
	 * @roseuid 4107B06C028E
	 */
	public void setDateOfBirthFrom(Date dateOfBirthFrom)
	{
		this.dateOfBirthFrom = dateOfBirthFrom;
	}

	/**
	 * @param dateOfBirthTo
	 * @roseuid 411A7AF80360
	 */
	public void setDateOfBirthTo(Date dateOfBirthTo)
	{
		this.dateOfBirthTo = dateOfBirthTo;
	}

	/**
	 * @param departmentId
	 * @roseuid 4107B06C027C
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @param departmentName
	 * @roseuid 43F3561B0016
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * @param firstName
	 * @roseuid 4107B06C0295
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 * @roseuid 4107B06C0299
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param logonId
	 * @roseuid 4107B06C02AE
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	/**
	 * @param operatorId
	 */
	public void setOperatorId(String operatorId)
	{
		this.operatorId = operatorId;
	}

	/**
	 * @param middleName
	 * @roseuid 4107B06C02A0
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param ssn
	 * @roseuid 4107B06C02A4
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @param userStatus
	 * @roseuid 4107B06C02AA
	 */
	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}

	/**
	 * @param inactivationDateFrom
	 * @roseuid 4107B06C0287
	 */
	public void setInactivationDateFrom(Date inactivationDateFrom)
	{
		this.inactivationDateFrom = inactivationDateFrom;
	}

	/**
	 * @param inactivationDateTo
	 * @roseuid 4107B06C02C7
	 */
	public void setInactivationDateTo(Date inactivationDateTo)
	{
		this.inactivationDateTo = inactivationDateTo;
	}

	/**
	 * @param requestorFirstName
	 * @roseuid 43F3568601B4
	 */
	public void setRequestorFirstName(String requestorFirstName)
	{
		this.requestorFirstName = requestorFirstName;
	}

	/**
	 * @param requestorLastName
	 * @roseuid 43F3568601B6
	 */
	public void setRequestorLastName(String requestorLastName)
	{
		this.requestorLastName = requestorLastName;
	}

	/**
	 * @param userType
	 * @roseuid 43F3576A0270
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	/**
	 * @param genericUserType
	 * @roseuid 43F3576A0272
	 */
	public void setGenericUserType(String genericUserType)
	{
		this.genericUserType = genericUserType;
	}

	/**
	 * @param  changeDateFrom
	 */
	public void setChangeDateFrom(Date changeDateFrom)
	{
		this.changeDateFrom = changeDateFrom;
	}

	/**
	 * @param changeDateTo
	 */
	public void setChangeDateTo(Date changeDateTo)
	{
		this.changeDateTo = changeDateTo;
	}

	/**
	 * @param publicInd
	 */
	public void setPublicInd(String publicInd)
	{
		this.publicInd = publicInd;
	}

	/**
	 * @return Returns the jims2LogonId.
	 */
	public String getJims2LogonId() {
		return jims2LogonId;
	}
	/**
	 * @param jims2LogonId The jims2LogonId to set.
	 */
	public void setJims2LogonId(String jims2LogonId) {
		this.jims2LogonId = jims2LogonId;
	}
}
