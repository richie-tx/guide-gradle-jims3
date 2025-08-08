package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetFamilyMembersAdvancedEvent extends RequestEvent
{

    private String lastName;
    private String firstName;
    private String middleName;
    private String dateOfBirth;
    private String sex;
    private String ssn;
    private String searchById = "";
    private String driverLicenseNum;
    
    
    
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getMiddleName()
    {
        return middleName;
    }
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public String getSsn()
    {
        return ssn;
    }
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }
    public String getSearchById()
    {
	return searchById;
    }
    public void setSearchById(String string)
    {
	searchById = string;
    }
    public String getDriverLicenseNum()
    {
        return driverLicenseNum;
    }
    public void setDriverLicenseNum(String driverLicenseNum)
    {
        this.driverLicenseNum = driverLicenseNum;
    }
   
    
   
    
    
    
}
