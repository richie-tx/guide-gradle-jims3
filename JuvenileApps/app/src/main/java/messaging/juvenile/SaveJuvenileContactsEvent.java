//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SaveJuvenileContactsEvent.java

package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileContactsEvent extends RequestEvent 
{
   private String addressType;
   private String agencyName;
   private String apartmentNum;
   private String cellPhone;
   private String county;
   private String city;
   private boolean currentAgencyInvolvement;
   private String eMail;
   private Date entryDate;
   private String fax;
   private String firstName;
   private String juvenileNum;
   private String lastName;
   private String middleName;
   private String pager;
   private boolean previousAgencyInvolvement;
   private String relationshipId;
   private String state;
   private String streetName;
   private String streetNum;
   private String streetType;
   private String tiele;
   private String workPhone;
   private String zipCode;
   private String additionalZipCode;
   
   /**
    * @roseuid 42A5E5D3038A
    */
   public SaveJuvenileContactsEvent() 
   {
    
   }
   
   
	/**
	 * @return
	 */
	public String getAddressType()
	{
		return addressType;
	}
	
	/**
	 * @return
	 */
	public String getApartmentNum()
	{
		return apartmentNum;
	}
	
	/**
	 * @return
	 */
	public String getCellPhone()
	{
		return cellPhone;
	}
	
	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}
	
	/**
	 * @return
	 */
	public boolean isCurrentAgencyInvolvement()
	{
		return currentAgencyInvolvement;
	}
	
	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}
	
	/**
	 * @return
	 */
	public String getFax()
	{
		return fax;
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
	public String getJuvenileNum()
	{
		return juvenileNum;
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
	public String getMiddleName()
	{
		return middleName;
	}
	
	/**
	 * @return
	 */
	public String getPager()
	{
		return pager;
	}
	
	/**
	 * @return
	 */
	public boolean isPreviousAgencyInvolvement()
	{
		return previousAgencyInvolvement;
	}
	
	/**
	 * @return
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}
	
	/**
	 * @return
	 */
	public String getStreetNum()
	{
		return streetNum;
	}
	
	/**
	 * @return
	 */
	public String getStreetType()
	{
		return streetType;
	}
	
	/**
	 * @return
	 */
	public String getWorkPhone()
	{
		return workPhone;
	}
	
	/**
	 * @return
	 */
	public String getZipCode()
	{
		return zipCode;
	}
	
	/**
	 * @param string
	 */
	public void setAddressType(String string)
	{
		addressType = string;
	}
	
	/**
	 * @param string
	 */
	public void setApartmentNum(String string)
	{
		apartmentNum = string;
	}
	
	/**
	 * @param string
	 */
	public void setCellPhone(String string)
	{
		cellPhone = string;
	}
	
	/**
	 * @param string
	 */
	public void setCity(String string)
	{
		city = string;
	}
	
	/**
	 * @param b
	 */
	public void setCurrentAgencyInvolvement(boolean b)
	{
		currentAgencyInvolvement = b;
	}
	
	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}
	
	/**
	 * @param string
	 */
	public void setFax(String string)
	{
		fax = string;
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
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
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
	public void setMiddleName(String string)
	{
		middleName = string;
	}
	
	/**
	 * @param string
	 */
	public void setPager(String string)
	{
		pager = string;
	}
	
	/**
	 * @param b
	 */
	public void setPreviousAgencyInvolvement(boolean b)
	{
		previousAgencyInvolvement = b;
	}
	
	/**
	 * @param string
	 */
	public void setState(String string)
	{
		state = string;
	}
	
	/**
	 * @param string
	 */
	public void setStreetName(String string)
	{
		streetName = string;
	}
	
	/**
	 * @param string
	 */
	public void setStreetNum(String string)
	{
		streetNum = string;
	}
	
	/**
	 * @param string
	 */
	public void setStreetType(String string)
	{
		streetType = string;
	}
	
	/**
	 * @param string
	 */
	public void setWorkPhone(String string)
	{
		workPhone = string;
	}
	
	/**
	 * @param string
	 */
	public void setZipCode(String string)
	{
		zipCode = string;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @return
	 */
	public String getCounty()
	{
		return county;
	}
	
	/**
	 * @param string
	 */
	public void setCounty(String string)
	{
		county = string;
	}

	/**
	 * @return
	 */
	public String getEMail()
	{
		return eMail;
	}
	
	/**
	 * @param string
	 */
	public void setEMail(String string)
	{
		eMail = string;
	}

	/**
	 * @return
	 */
	public String getRelationshipId()
	{
		return relationshipId;
	}
	
	/**
	 * @param string
	 */
	public void setRelationshipId(String string)
	{
		relationshipId = string;
	}

	/**
	 * @return
	 */
	public String getTiele()
	{
		return tiele;
	}
	
	/**
	 * @param string
	 */
	public void setTiele(String string)
	{
		tiele = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}
	
	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string)
	{
		additionalZipCode = string;
	}

}
