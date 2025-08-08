//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendSuccessfulWarrantServiceNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

import java.sql.Timestamp;
import java.util.Date;

import messaging.contact.to.PhoneNumberBean;

public class SendSuccessfulWarrantServiceNotificationEvent extends PersistentEvent 
{
   private String agencyName;
   private String warrantNum;
   private String warrantTypeID;
   private String juvenileNameFirstMiddleLastSuffix;
   private String juvenileNameLastFirstMiddleSuffix;
   private String officerFirstName;
   private String officerLastName;
   private String officerWorkPhone;
   private Timestamp arrestTimeStamp;
   private String emailFrom;
   private String emailTo;
   private String warrantType;
   
   /**
    * @roseuid 420BB197035B
    */
   public SendSuccessfulWarrantServiceNotificationEvent() 
   {
    
   }
   
   /**
    * Access method for the agencyName property.
    * 
    * @return   the current value of the agencyName property
    */
   public String getAgencyName()
   {
      return agencyName;
   }
   
   /**
    * Sets the value of the agencyName property.
    * 
    * @param aAgencyName the new value of the agencyName property
    */
   public void setAgencyName(String aAgencyName)
   {
      agencyName = aAgencyName;
   }
   
   /**
    * Access method for the warrantNum property.
    * 
    * @return   the current value of the warrantNum property
    */
   public String getWarrantNum()
   {
      return warrantNum;
   }
   
   /**
    * Sets the value of the warrantNum property.
    * 
    * @param aWarrantNum the new value of the warrantNum property
    */
   public void setWarrantNum(String aWarrantNum)
   {
      warrantNum = aWarrantNum;
   }
   
	/**
	 * @return Returns the warrantTypeID.
	 */
	public String getWarrantTypeID()
	{
	    return warrantTypeID;
	}
	
	/**
	 * @param warrantTypeID The warrantTypeID to set.
	 */
	public void setWarrantTypeID(String warrantTypeID)
	{
	    this.warrantTypeID = warrantTypeID;
	}
	
	/**
	 * @return Returns the juvenileNameFirstMiddleLastSuffix.
	 */
	public String getJuvenileNameFirstMiddleLastSuffix()
	{
	    return juvenileNameFirstMiddleLastSuffix;
	}
	
	/**
	 * @param juvenileNameFirstMiddleLastSuffix The juvenileNameFirstMiddleLastSuffix to set.
	 */
	public void setJuvenileNameFirstMiddleLastSuffix(String nameFirstMiddleLastSuffix)
	{
	    this.juvenileNameFirstMiddleLastSuffix = nameFirstMiddleLastSuffix;
	}
	
	/**
	 * @return Returns the juvenileNameLastFirstMiddleSuffix.
	 */
	public String getJuvenileNameLastFirstMiddleSuffix()
	{
	    return juvenileNameLastFirstMiddleSuffix;
	}
	
	/**
	 * @param juvenileNameLastFirstMiddleSuffix The juvenileNameLastFirstMiddleSuffix to set.
	 */
	public void setJuvenileNameLastFirstMiddleSuffix(String nameLastFirstMiddleSuffix)
	{
	    this.juvenileNameLastFirstMiddleSuffix = nameLastFirstMiddleSuffix;
	}
	
   /**
    * Access method for the officerFirstName property.
    * 
    * @return   the current value of the officerFirstName property
    */
   public String getOfficerFirstName()
   {
      return officerFirstName;
   }
   
   /**
    * Sets the value of the officerFirstName property.
    * 
    * @param aOfficerFirstName the new value of the officerFirstName property
    */
   public void setOfficerFirstName(String aOfficerFirstName)
   {
      officerFirstName = aOfficerFirstName;
   }
   
   /**
    * Access method for the officerLastName property.
    * 
    * @return   the current value of the officerLastName property
    */
   public String getOfficerLastName()
   {
      return officerLastName;
   }
   
   /**
    * Sets the value of the officerLastName property.
    * 
    * @param aOfficerLastName the new value of the officerLastName property
    */
   public void setOfficerLastName(String aOfficerLastName)
   {
      officerLastName = aOfficerLastName;
   }
   
   /**
    * Access method for the officerWorkPhone property.
    * 
    * @return   the current value of the officerWorkPhone property
    */
   public String getOfficerWorkPhone()
   {
      return officerWorkPhone;
   }
   
   /**
    * 
    * @return Returns a formatted executorPhoneNum
    */
   public String getOfficerWorkPhoneString()
   {
       StringBuffer buffer = new StringBuffer(50);
	    if(getOfficerWorkPhone() != null && !getOfficerWorkPhone().equals(""))
       {
//           PhoneNumberBean phoneNumFormatter;
           PhoneNumberBean officerWorkPhone = new PhoneNumberBean(getOfficerWorkPhone());
           officerWorkPhone = new PhoneNumberBean("A-P-F");
           buffer.append("work phone: ");
           buffer.append(officerWorkPhone.getFormattedPhoneNumber());
       }
	    String officerWorkPhoneString = buffer.toString();
	    return officerWorkPhoneString;
   }
   
   /**
    * Sets the value of the officerWorkPhone property.
    * 
    * @param aOfficerWorkPhone the new value of the officerWorkPhone property
    */
   public void setOfficerWorkPhone(String aOfficerWorkPhone)
   {
      officerWorkPhone = aOfficerWorkPhone;
   }
   
   /**
    * Access method for the arrestTimeStamp property.
    * 
    * @return   the current value of the arrestTimeStamp property
    */
   public Date getArrestTimeStamp() 
   {
      return arrestTimeStamp;
   }
   
   /**
    * Sets the value of the arrestTimeStamp property.
    * 
    * @param aArrestTimeStamp the new value of the arrestTimeStamp property
    */
   public void setArrestTimeStamp(Timestamp aArrestTimeStamp) 
   {
      arrestTimeStamp = aArrestTimeStamp;
   }
   
	/**
	 * @return
	 */
	public String getEmailFrom()
	{
		return emailFrom;
	}
	
	/**
	 * @return
	 */
	public String getEmailTo()
	{
		return emailTo;
	}
	
	/**
	 * @param string
	 */
	public void setEmailFrom(String string)
	{
		emailFrom = string;
	}
	
	/**
	 * @param string
	 */
	public void setEmailTo(String string)
	{
		emailTo = string;
	}

	/**
	 * @return
	 */
	public String getWarrantType()
	{
		return warrantType;
	}
	
	/**
	 * @param string
	 */
	public void setWarrantType(String string)
	{
		warrantType = string;
	}

}
