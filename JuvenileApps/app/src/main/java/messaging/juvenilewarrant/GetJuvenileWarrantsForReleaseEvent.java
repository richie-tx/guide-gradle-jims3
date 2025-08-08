//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsForReleaseEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForReleaseEvent extends RequestEvent 
{
	private String juvenileFirstName;
	private String juvenileLastName;
	private String warrantNum;
	private String warrantStatus;
   
   /**
    * @roseuid 41FFDB980157
    */
   public GetJuvenileWarrantsForReleaseEvent() 
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
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

}
