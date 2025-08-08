//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsForProcessServiceEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForProcessServiceEvent extends RequestEvent 
{
   private String juvenileFirstName;
   private String juvenileLastName;
   private String warrantNum;
   private String warrantStatus;
   private String serviceReturnGeneratedStatus;
   
   
   /**
    * @roseuid 41FFDB2C0177
    */
   public GetJuvenileWarrantsForProcessServiceEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getWarrantStatus()
	{
		return warrantStatus;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileFirstName(String string)
	{
		juvenileFirstName = string;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileLastName(String string)
	{
		juvenileLastName = string;
	}
	
	/**
	 * @param string
	 */
	public void setWarrantStatus(String string)
	{
		warrantStatus = string;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}
	
	/**
	 * @return
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}
	
	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}
	
	/**
	 * @return
	 */
	public String getServiceReturnGeneratedStatus()
	{
		return serviceReturnGeneratedStatus;
	}
	
	/**
	 * @param string
	 */
	public void setServiceReturnGeneratedStatus(String string)
	{
		serviceReturnGeneratedStatus = string;
	}
	
	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}
	
}
