//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\SendInvalidAddressNotificationEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.PersistentEvent;

public class SendInvalidAddressNotificationEvent extends PersistentEvent 
{
   private String warrantNum;
   private String serviceStreetName;
   private String serviceStreetNum;
   private String serviceZipCode;
   private String serviceCity;
   private String logonId;
   private String officerId;
   private String agencyName;
   private String emailFrom;
   private String emailTo;
   
   /**
    * @roseuid 420A7456000F
    */
   public SendInvalidAddressNotificationEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}
	
	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}
	
	/**
	 * @return
	 */
	public String getOfficerId()
	{
		return officerId;
	}
	
	/**
	 * @return
	 */
	public String getServiceCity()
	{
		return serviceCity;
	}
	
	/**
	 * @return
	 */
	public String getServiceStreetName()
	{
		return serviceStreetName;
	}
	
	/**
	 * @return
	 */
	public String getServiceStreetNum()
	{
		return serviceStreetNum;
	}
	
	/**
	 * @return
	 */
	public String getServiceZipCode()
	{
		return serviceZipCode;
	}
	
	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}
	
	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}
	
	/**
	 * @param string
	 */
	public void setOfficerId(String string)
	{
		officerId = string;
	}
	
	/**
	 * @param string
	 */
	public void setServiceCity(String string)
	{
		serviceCity = string;
	}
	
	/**
	 * @param string
	 */
	public void setServiceStreetName(String string)
	{
		serviceStreetName = string;
	}
	
	/**
	 * @param string
	 */
	public void setServiceStreetNum(String string)
	{
		serviceStreetNum = string;
	}
	
	/**
	 * @param string
	 */
	public void setServiceZipCode(String string)
	{
		serviceZipCode = string;
	}
	
	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
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

}
