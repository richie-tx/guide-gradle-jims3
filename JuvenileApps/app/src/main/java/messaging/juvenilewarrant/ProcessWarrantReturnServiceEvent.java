//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\ProcessWarrantReturnServiceEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class ProcessWarrantReturnServiceEvent extends RequestEvent 
{
	private String warrantNum;
	private String serviceReturnSignatureStatusID;
	private String serviceID;
   
   /**
    * @roseuid 41FFDB4102BF
    */
   public ProcessWarrantReturnServiceEvent() 
   {
    
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

	/**
	 * @return
	 */
	public String getServiceReturnSignatureStatusID()
	{
		return serviceReturnSignatureStatusID;
	}

	/**
	 * @param string
	 */
	public void setServiceReturnSignatureStatusID(String string)
	{
		serviceReturnSignatureStatusID = string;
	}

	/**
	 * @return
	 */
	public String getServiceID()
	{
		return serviceID;
	}

	/**
	 * @param string
	 */
	public void setServiceID(String string)
	{
		serviceID = string;
	}

}
