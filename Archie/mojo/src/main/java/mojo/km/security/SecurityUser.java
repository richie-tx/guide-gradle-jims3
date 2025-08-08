package mojo.km.security;


/** Logged IN User information
 * @param <T> */
public class SecurityUser implements IUserInfo
{
    
    private String userOID;
    private String firstName;
    private String lastName;
    private String middleName="";
    private String jims2logonId;
    private String uvCode;
    private String departmentName;
    private String departmentId;
    private String agencyName;
    private String agencyId;
    private String userTypeId;
    private String orgCode;
    
    private String fullName;
    private String dateOfBirth;
    private String workPhone;
    private String email;
    private AllUserAccessInfoBean userAccess;
    private String accountType;   

    

	/**
     * @return the userAccess
     */
    public AllUserAccessInfoBean getUserAccess()
    {
        return userAccess;
    }

    /**
     * @param userAccess the userAccess to set
     */
    public void setUserAccess(AllUserAccessInfoBean userAccess)
    {
        this.userAccess = userAccess;
    }

    public String getUserOID()
    {
	return userOID;
    }

    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName= firstName;
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName= lastName;
	
    }

    public String getMiddleName()
    {
	return middleName;
    }

    public void setMiddleName(String middleName)
    {
	this.middleName=middleName;
	
    }

    public String getJIMS2LogonId()
    {
	return jims2logonId;
    }

    public void setJIMS2LogonId(String JIMS2LogonId)
    {
	this.jims2logonId = JIMS2LogonId;
	
    }

    public String getJIMSLogonId()
    {
	return uvCode;
    }

    public void setJIMSLogonId(String JIMSLogonId)
    {
	this.uvCode = JIMSLogonId;
	
    }

    public String getDepartmentId()
    {
	return departmentId;
    }

    public void setDepartmentId(String departmentId)
    {
	this.departmentId= departmentId;
	
    }

    public String getDepartmentName()
    {
	return departmentName;
    }

    public void setDepartmentName(String departmentName)
    {
	this.departmentName= departmentName;
    }

    public String getOrgCode()
    {
	return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
	this.orgCode= orgCode;
    }

    public String getUserTypeId()
    {
	return userTypeId;
    }

    public void setUserTypeId(String userTypeId)
    {
	this.userTypeId= userTypeId;
    }

    public String getAgencyId()
    {
	return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
	this.agencyId= agencyId;
    }

    public String getAgencyName()
    {
	return agencyName;
    }

    public void setAgencyName(String agencyName)
    {
	this.agencyName= agencyName;
	
    }

    /**
     * @return the fullName
     */
    public String getFullName()
    {
	return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName)
    {
	this.fullName = fullName;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
	return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
	return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
	this.email = email;
    }

    /**
     * @return the workPhone
     */
    public String getWorkPhone()
    {
	return workPhone;
    }

    /**
     * @param workPhone the workPhone to set
     */
    public void setWorkPhone(String workPhone)
    {
	this.workPhone = workPhone;
    }

    
    public void setUserOID(String userId)
    {
	this.userOID= userId;
    }
    
    public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
