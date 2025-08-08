//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\CreateServiceProviderEvent.java

package messaging.administerserviceprovider;

import messaging.contact.domintf.IPhoneNumber;

import mojo.km.messaging.Composite.CompositeRequest;

import java.util.Date;

public class CreateServiceProviderEvent extends CompositeRequest 
{
   public String adminLogonId;
   public String apartmentNum;
   public String city;
   public String contactLogonId;
   public IPhoneNumber fax;
   public String ftpSite;
   public String ifasNum;
   public boolean isInHouse;
   public String mailingAptNum;
   public String mailingCity;
   public String mailingState;
   public String mailingStreetName;
   public String mailingStreetNum;
   public String mailingStreetType;
   public String mailingZipCode;
   public IPhoneNumber phone;
   public String serviceProviderName;
   public Date startDate;
   public String state;
   public String streetName;
   public String streetNum;
   public String streetType;
   public String webSite;
   public String zipCode;
   public String departmentId;
   public String statusId;
   private boolean isCreate;
   private boolean inactivate;
   public String serviceProviderId;
   private String maxYouth; //US 177341
   private String email;
   public boolean isEmailCheck;
   
   /**
    * @roseuid 4474584B008C
    */
   public CreateServiceProviderEvent() 
   {
    
   }
   
   /**
    * Access method for the adminLogonId property.
    * 
    * @return   the current value of the adminLogonId property
    */
   public String getAdminLogonId()
   {
      return adminLogonId;
   }
   public String getDepartmentId()
   {
	  return departmentId;
   }   
   /**
    * Sets the value of the adminLogonId property.
    * 
    * @param aAdminLogonId the new value of the adminLogonId property
    */
   public void setAdminLogonId(String aAdminLogonId)
   {
      adminLogonId = aAdminLogonId;
   }
   public void setDepartmentId(String aDepartmentId) 
   {
	  departmentId = aDepartmentId;
   }

   /**
    * Access method for the apartmentNum property.
    * 
    * @return   the current value of the apartmentNum property
    */
   public String getApartmentNum()
   {
      return apartmentNum;
   }
   
   /**
    * Sets the value of the apartmentNum property.
    * 
    * @param aApartmentNum the new value of the apartmentNum property
    */
   public void setApartmentNum(String aApartmentNum)
   {
      apartmentNum = aApartmentNum;
   }
   
   /**
    * Access method for the city property.
    * 
    * @return   the current value of the city property
    */
   public String getCity()
   {
      return city;
   }
   
   /**
    * Sets the value of the city property.
    * 
    * @param aCity the new value of the city property
    */
   public void setCity(String aCity)
   {
      city = aCity;
   }
   
   /**
    * Access method for the contactLogonId property.
    * 
    * @return   the current value of the contactLogonId property
    */
   public String getContactLogonId()
   {
      return contactLogonId;
   }
   
   /**
    * Sets the value of the contactLogonId property.
    * 
    * @param aContactLogonId the new value of the contactLogonId property
    */
   public void setContactLogonId(String aContactLogonId)
   {
      contactLogonId = aContactLogonId;
   }
   
   /**
    * Access method for the fax property.
    * 
    * @return   the current value of the fax property
    */
   public IPhoneNumber getFax() 
   {
      return fax;
   }
   
   /**
    * Sets the value of the fax property.
    * 
    * @param aFax the new value of the fax property
    */
   public void setFax(IPhoneNumber aFax) 
   {
      fax = aFax;
   }
   
   /**
    * Access method for the ftpSite property.
    * 
    * @return   the current value of the ftpSite property
    */
   public String getFtpSite()
   {
      return ftpSite;
   }
   
   /**
    * Sets the value of the ftpSite property.
    * 
    * @param aFtpSite the new value of the ftpSite property
    */
   public void setFtpSite(String aFtpSite)
   {
      ftpSite = aFtpSite;
   }
   
   /**
    * Access method for the ifasNum property.
    * 
    * @return   the current value of the ifasNum property
    */
   public String getIfasNum()
   {
      return ifasNum;
   }
   
   /**
    * Sets the value of the ifasNum property.
    * 
    * @param aIfasNum the new value of the ifasNum property
    */
   public void setIfasNum(String aIfasNum)
   {
      ifasNum = aIfasNum;
   }
   
   /**
    * Determines if the isInHouse property is true.
    * 
    * @return   <code>true<code> if the isInHouse property is true
    */
   public boolean getIsInHouse() 
   {
   	 return isInHouse;

   }
   
   /**
    * Sets the value of the isInHouse property.
    * 
    * @param aIsInHouse the new value of the isInHouse property
    */
   public void setIsInHouse(boolean aIsInHouse) 
   {
      isInHouse = aIsInHouse;
   }
   
   /**
    * Access method for the mailingAptNum property.
    * 
    * @return   the current value of the mailingAptNum property
    */
   public String getMailingAptNum()
   {
      return mailingAptNum;
   }
   
   /**
    * Sets the value of the mailingAptNum property.
    * 
    * @param aMailingAptNum the new value of the mailingAptNum property
    */
   public void setMailingAptNum(String aMailingAptNum)
   {
      mailingAptNum = aMailingAptNum;
   }
   
   /**
    * Access method for the mailingCity property.
    * 
    * @return   the current value of the mailingCity property
    */
   public String getMailingCity()
   {
      return mailingCity;
   }
   
   /**
    * Sets the value of the mailingCity property.
    * 
    * @param aMailingCity the new value of the mailingCity property
    */
   public void setMailingCity(String aMailingCity)
   {
      mailingCity = aMailingCity;
   }
   
   /**
    * Access method for the mailingState property.
    * 
    * @return   the current value of the mailingState property
    */
   public String getMailingState()
   {
      return mailingState;
   }
   
   /**
    * Sets the value of the mailingState property.
    * 
    * @param aMailingState the new value of the mailingState property
    */
   public void setMailingState(String aMailingState)
   {
      mailingState = aMailingState;
   }
   
   /**
    * Access method for the mailingStreetName property.
    * 
    * @return   the current value of the mailingStreetName property
    */
   public String getMailingStreetName()
   {
      return mailingStreetName;
   }
   
   /**
    * Sets the value of the mailingStreetName property.
    * 
    * @param aMailingStreetName the new value of the mailingStreetName property
    */
   public void setMailingStreetName(String aMailingStreetName)
   {
      mailingStreetName = aMailingStreetName;
   }
   
   /**
    * Access method for the mailingStreetNum property.
    * 
    * @return   the current value of the mailingStreetNum property
    */
   public String getMailingStreetNum()
   {
      return mailingStreetNum;
   }
   
   /**
    * Sets the value of the mailingStreetNum property.
    * 
    * @param aMailingStreetNum the new value of the mailingStreetNum property
    */
   public void setMailingStreetNum(String aMailingStreetNum)
   {
      mailingStreetNum = aMailingStreetNum;
   }
   
   /**
    * Access method for the mailingStreetType property.
    * 
    * @return   the current value of the mailingStreetType property
    */
   public String getMailingStreetType()
   {
      return mailingStreetType;
   }
   
   /**
    * Sets the value of the mailingStreetType property.
    * 
    * @param aMailingStreetType the new value of the mailingStreetType property
    */
   public void setMailingStreetType(String aMailingStreetType)
   {
      mailingStreetType = aMailingStreetType;
   }
   
   /**
    * Access method for the mailingZipCode property.
    * 
    * @return   the current value of the mailingZipCode property
    */
   public String getMailingZipCode()
   {
      return mailingZipCode;
   }
   
   /**
    * Sets the value of the mailingZipCode property.
    * 
    * @param aMailingZipCode the new value of the mailingZipCode property
    */
   public void setMailingZipCode(String aMailingZipCode)
   {
      mailingZipCode = aMailingZipCode;
   }
   
   /**
    * Access method for the phone property.
    * 
    * @return   the current value of the phone property
    */
   public IPhoneNumber getPhone() 
   {
      return phone;
   }
   
   /**
    * Sets the value of the phone property.
    * 
    * @param aPhone the new value of the phone property
    */
   public void setPhone(IPhoneNumber aPhone) 
   {
      phone = aPhone;
   }
   
   /**
    * Access method for the serviceProviderName property.
    * 
    * @return   the current value of the serviceProviderName property
    */
   public String getServiceProviderName()
   {
      return serviceProviderName;
   }
   
   /**
    * Sets the value of the serviceProviderName property.
    * 
    * @param aServiceProviderName the new value of the serviceProviderName property
    */
   public void setServiceProviderName(String aServiceProviderName)
   {
      serviceProviderName = aServiceProviderName;
   }
   
   /**
    * Access method for the startDate property.
    * 
    * @return   the current value of the startDate property
    */
   public Date getStartDate()
   {
      return startDate;
   }
   
   /**
    * Sets the value of the startDate property.
    * 
    * @param aStartDate the new value of the startDate property
    */
   public void setStartDate(Date aStartDate)
   {
      startDate = aStartDate;
   }
   
   /**
    * Access method for the state property.
    * 
    * @return   the current value of the state property
    */
   public String getState()
   {
      return state;
   }
   
   /**
    * Sets the value of the state property.
    * 
    * @param aState the new value of the state property
    */
   public void setState(String aState)
   {
      state = aState;
   }
   
   /**
    * Access method for the streetName property.
    * 
    * @return   the current value of the streetName property
    */
   public String getStreetName()
   {
      return streetName;
   }
   
   /**
    * Sets the value of the streetName property.
    * 
    * @param aStreetName the new value of the streetName property
    */
   public void setStreetName(String aStreetName)
   {
      streetName = aStreetName;
   }
   
   /**
    * Access method for the streetNum property.
    * 
    * @return   the current value of the streetNum property
    */
   public String getStreetNum()
   {
      return streetNum;
   }
   
   /**
    * Sets the value of the streetNum property.
    * 
    * @param aStreetNum the new value of the streetNum property
    */
   public void setStreetNum(String aStreetNum)
   {
      streetNum = aStreetNum;
   }
   
   /**
    * Access method for the streetType property.
    * 
    * @return   the current value of the streetType property
    */
   public String getStreetType()
   {
      return streetType;
   }
   
   /**
    * Sets the value of the streetType property.
    * 
    * @param aStreetType the new value of the streetType property
    */
   public void setStreetType(String aStreetType)
   {
      streetType = aStreetType;
   }
   
   /**
    * Access method for the webSite property.
    * 
    * @return   the current value of the webSite property
    */
   public String getWebSite()
   {
      return webSite;
   }
   
   /**
    * Sets the value of the webSite property.
    * 
    * @param aWebSite the new value of the webSite property
    */
   public void setWebSite(String aWebSite)
   {
      webSite = aWebSite;
   }
   
   /**
    * Access method for the zipCode property.
    * 
    * @return   the current value of the zipCode property
    */
   public String getZipCode()
   {
      return zipCode;
   }
   
   /**
    * Sets the value of the zipCode property.
    * 
    * @param aZipCode the new value of the zipCode property
    */
   public void setZipCode(String aZipCode)
   {
      zipCode = aZipCode;
   }
   

/**
 * @return
 */
public String getStatusId()
{
	return statusId;
}

/**
 * @param b
 */
public void setInHouse(boolean b)
{
	isInHouse = b;
}

/**
 * @param string
 */
public void setStatusId(String string)
{
	statusId = string;
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
 * @return Returns the serviceProviderId.
 */
public String getServiceProviderId() {
	return serviceProviderId;
}
/**
 * @param serviceProviderId The serviceProviderId to set.
 */
public void setServiceProviderId(String serviceProviderId) {
	this.serviceProviderId = serviceProviderId;
}
/**
 * @return Returns the inactivate.
 */
public boolean isInactivate() {
	return inactivate;
}
/**
 * @param inactivate The inactivate to set.
 */
public void setInactivate(boolean inactivate) {
	this.inactivate = inactivate;
}

public String getMaxYouth()
{
    return maxYouth;
}

public void setMaxYouth(String maxYouth)
{
    this.maxYouth = maxYouth;
}

public String getEmail()
{
    return this.email;
}

public void setEmail(String email)
{
    this.email = email;
}

public boolean isEmailCheck()
{
    return isEmailCheck;
}

public void setEmailCheck(boolean isEmailCheck)
{
    this.isEmailCheck = isEmailCheck;
}

}
