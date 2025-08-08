package mojo.km.security;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/** Response object for the user-authenticate web-service.
 * 
 * @author sthyagarajan */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthenticationResponse
{
    //public Map<Integer, String> credentials;  // not used . Garys stuff
    //public Map<Integer, String> contacts;  // not used . Garys stuff
    //  public String agency; //agencyId
    //  public String department;//departmentId
    // private String claimgrantedtime; // not used . Garys stuff
    //  public String lastemailupdate; garys stuff
    //  public String adcredential; //garys stuff
    // public String phone;
    // private String firstname;
    // private String lastname;
    // public AllUserAccessInfoBean useraccesses; //user Roles and Features Stuff.

    //private String username; // uvcode
    protected String displayname;//
    protected int userid; //sm userId
    protected boolean disabled;
    // U.S #79250
    protected boolean logonFailed;
    protected boolean logonSuspended;
    protected boolean logonSuccessful;
    protected List<CredentialStoreEntityBean> securitycredentials; // uvCode or ad Logon.
    protected String phone;
    protected String firstname;
    protected String lastname;
    protected AllUserAccessInfoBean useraccesses; //user Roles and Features Stuff.
    protected DepartmentEntityBean dept;
    protected Date dateofbirth;
    protected String contactemail;
    protected String email;

    

	/** @return the userid */
    public int getUserid()
    {
	return userid;
    }

    /** @param userid
     *            the userid to set */
    public void setUserid(int userid)
    {
	this.userid = userid;
    }

 
    /** @return the displayname */
    public String getDisplayname()
    {
	return displayname;
    }

    /** @param displayname
     *            the displayname to set */
    public void setDisplayname(String displayname)
    {
	this.displayname = displayname;
    }

  
    /** @return the disabled */
    public boolean isDisabled()
    {
	return disabled;
    }

    /** @param disabled
     *            the disabled to set */
    public void setDisabled(boolean disabled)
    {
	this.disabled = disabled;
    }

    /** @return the logonFailed */
    public boolean isLogonFailed()
    {
	return logonFailed;
    }

    /** @param logonFailed
     *            the logonFailed to set */
    public void setLogonFailed(boolean logonFailed)
    {
	this.logonFailed = logonFailed;
    }

    /** @return the logonSuspended */
    public boolean isLogonSuspended()
    {
	return logonSuspended;
    }

    /** @param logonSuspended
     *            the logonSuspended to set */
    public void setLogonSuspended(boolean logonSuspended)
    {
	this.logonSuspended = logonSuspended;
    }

    /** @return the logonSuccessful */
    public boolean isLogonSuccessful()
    {
	return logonSuccessful;
    }

    /** @param logonSuccessful
     *            the logonSuccessful to set */
    public void setLogonSuccessful(boolean logonSuccessful)
    {
	this.logonSuccessful = logonSuccessful;
    }

    /** @return the securitycredentials */
    public List<CredentialStoreEntityBean> getSecuritycredentials()
    {
	return securitycredentials;
    }
    /** @param securitycredentials
     *            the securitycredentials to set */
    public void setSecuritycredentials(List<CredentialStoreEntityBean> securitycredentials)
    {
	this.securitycredentials = securitycredentials;
    }

    /**
     * @return the phone
     */
    public String getPhone()
    {
	return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone)
    {
	this.phone = phone;
    }

    /**
     * @return the firstname
     */
    public String getFirstname()
    {
	return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname)
    {
	this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname()
    {
	return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname)
    {
	this.lastname = lastname;
    }

    /**
     * @return the useraccesses
     */
    public AllUserAccessInfoBean getUseraccesses()
    {
	return useraccesses;
    }

    /**
     * @param useraccesses the useraccesses to set
     */
    public void setUseraccesses(AllUserAccessInfoBean useraccesses)
    {
	this.useraccesses = useraccesses;
    }

    /**
     * @return the dept
     */
    public DepartmentEntityBean getDept()
    {
	return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(DepartmentEntityBean dept)
    {
	this.dept = dept;
    }

    /**
     * @return the dateofbirth
     */
    public Date getDateofbirth()
    {
	return dateofbirth;
    }

    /**
     * @param dateofbirth the dateofbirth to set
     */
    public void setDateofbirth(Date dateofbirth)
    {
	this.dateofbirth = dateofbirth;
    }

   /* *//**
     * @return the agency
     *//*
    public AgencyEntityBean getAgency()
    {
	return agency;
    }

    *//**
     * @param agency the agency to set
     *//*
    public void setAgency(AgencyEntityBean agency)
    {
	this.agency = agency;
    }*/

    /**
     * @return the contactemail
     */
    public String getContactemail()
    {
	return contactemail;
    }

    /**
     * @param contactemail the contactemail to set
     */
    public void setContactemail(String contactemail)
    {
	this.contactemail = contactemail;
    }
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
