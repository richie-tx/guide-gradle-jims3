package mojo.km.security;

import java.util.Date;
import mojo.km.messaging.MetaDataResponseEvent;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/** @author sthyagarajan Added temporarily.
 * @param <T> */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntityBean 
{
    private String firstname;
    private String lastname;
    private String phone;
    private DepartmentEntityBean dept;
    private int userid;
    private String displayname;
    private String contactemail;
    private String email;
    private Date dateofbirth;
    private String username;
    private AllUserAccessInfoBean useraccesses;
    private boolean disabled;
    private int maxRecCount;



    /** @return the firstname */
    public String getFirstname()
    {
	return firstname;
    }

    /** @param firstname
     *            the firstname to set */
    public void setFirstname(String firstname)
    {
	this.firstname = firstname;
    }

    /** @return the lastname */
    public String getLastname()
    {
	return lastname;
    }

    /** @param lastname
     *            the lastname to set */
    public void setLastname(String lastname)
    {
	this.lastname = lastname;
    }

    /** @return the phone */
    public String getPhone()
    {
	return phone;
    }

    /** @param phone
     *            the phone to set */
    public void setPhone(String phone)
    {
	this.phone = phone;
    }

    /** @return the dept */
    public DepartmentEntityBean getDept()
    {
	return dept;
    }

    /** @param dept
     *            the dept to set */
    public void setDept(DepartmentEntityBean dept)
    {
	this.dept = dept;
    }

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

    /**
     * @return the username
     */
    public String getUsername()
    {
	return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
	this.username = username;
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
     * @return the disabled
     */
    public boolean isDisabled()
    {
	return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled)
    {
	this.disabled = disabled;
    }

    /**
     * @return the maxRecCount
     */
    public int getMaxRecCount()
    {
	return maxRecCount;
    }

    /**
     * @param maxRecCount the maxRecCount to set
     */
    public void setMaxRecCount(int maxRecCount)
    {
	this.maxRecCount = maxRecCount;
    }

	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}
  

}
