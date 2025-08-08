//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\UpdateWarrantServiceSignatureStatusEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class UpdateWarrantServiceSignatureStatusEvent extends RequestEvent 
{
   private String serviceReturnSignatureStatus;
   private String warrantNum;
   
   /**
    * @roseuid 41FFDB83006D
    */
   public UpdateWarrantServiceSignatureStatusEvent() 
   {
    
   }
   
   /**
    * @param serviceReturnSignatureStatus
    * @roseuid 41F1732A031F
    */
   public void setServiceReturnSignatureStatus(String serviceReturnSignatureStatus) 
   {
    	this.serviceReturnSignatureStatus = serviceReturnSignatureStatus;
   }
   
   /**
    * @return String
    * @roseuid 41F1732A032D
    */
   public String getServiceReturnSignatureStatus() 
   {
    return serviceReturnSignatureStatus;
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
