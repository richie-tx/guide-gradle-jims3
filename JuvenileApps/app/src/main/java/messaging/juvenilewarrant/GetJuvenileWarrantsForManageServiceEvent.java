//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsForManageServiceEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForManageServiceEvent extends RequestEvent 
{
   private String juvenileFirstName;
   private String juvenileLastName;
   private String warrantNum;
   private String warrantStatus;
   private String warrantActivationStatus;
   
   /**
    * @roseuid 41FFDAF90242
    */
   public GetJuvenileWarrantsForManageServiceEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getWarrantActivationStatus()
	{
		return warrantActivationStatus;
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
	public void setWarrantActivationStatus(String string)
	{
		warrantActivationStatus = string;
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
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

}
