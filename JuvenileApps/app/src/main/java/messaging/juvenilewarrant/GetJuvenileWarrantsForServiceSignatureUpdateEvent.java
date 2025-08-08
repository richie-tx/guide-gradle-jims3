//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsForServiceSignatureUpdateEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForServiceSignatureUpdateEvent extends RequestEvent 
{
   public String juvenileFirstName;
   public String juvenileLastName;
   public String serviceReturnSignatureStatus;
   public String warrantNum;
   public String warrantStatus;
   public String warrantTypeId;
   
   /**
    * @roseuid 421F406E008C
    */
   public GetJuvenileWarrantsForServiceSignatureUpdateEvent() 
   {
    
   }
   
   /**
    * Access method for the juvenileFirstName property.
    * 
    * @return   the current value of the juvenileFirstName property
    */
   public String getJuvenileFirstName()
   {
		return juvenileFirstName;    
   }
   
   /**
    * Sets the value of the juvenileFirstName property.
    * 
    * @param aJuvenileFirstName the new value of the juvenileFirstName property
    */
   public void setJuvenileFirstName(String aJuvenileFirstName)
   {
		juvenileFirstName = aJuvenileFirstName;    
   }
   
   /**
    * Access method for the juvenileLastName property.
    * 
    * @return   the current value of the juvenileLastName property
    */
   public String getJuvenileLastName()
   {
		return juvenileLastName;    
   }
   
   /**
    * Sets the value of the juvenileLastName property.
    * 
    * @param aJuvenileLastName the new value of the juvenileLastName property
    */
   public void setJuvenileLastName(String aJuvenileLastName)
   {
		juvenileLastName = aJuvenileLastName;    
   }
   
   /**
    * Access method for the serviceReturnSignatureStatus property.
    * 
    * @return   the current value of the serviceReturnSignatureStatus property
    */
   public String getServiceReturnSignatureStatus()
   {
		return serviceReturnSignatureStatus;    
   }
   
   /**
    * Sets the value of the serviceReturnSignatureStatus property.
    * 
    * @param aServiceReturnSignatureStatus the new value of the serviceReturnSignatureStatus property
    */
   public void setServiceReturnSignatureStatus(String aServiceReturnSignatureStatus)
   {
		serviceReturnSignatureStatus = aServiceReturnSignatureStatus;    
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
    * Access method for the warrantStatus property.
    * 
    * @return   the current value of the warrantStatus property
    */
   public String getWarrantStatus()
   {
		return warrantStatus;    
   }
   
   /**
    * Sets the value of the warrantStatus property.
    * 
    * @param aWarrantStatus the new value of the warrantStatus property
    */
   public void setWarrantStatus(String aWarrantStatus)
   {
		warrantStatus = aWarrantStatus;    
   }
   
   /**
    * Access method for the warrantTypeId property.
    * 
    * @return   the current value of the warrantTypeId property
    */
   public String getWarrantTypeId()
   {
      return warrantTypeId;
   }
   
   /**
    * Sets the value of the warrantTypeId property.
    * 
    * @param aWarrantTypeId the new value of the warrantTypeId property
    */
   public void setWarrantTypeId(String aWarrantTypeId)
   {
      warrantTypeId = aWarrantTypeId;
   }
   
}
