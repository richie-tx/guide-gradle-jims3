//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantByActivationStatusEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantByActivationStatusEvent extends RequestEvent 
{
   public Integer juvenileNum;
   public String warrantActivationStatus;
   public String warrantStatusId;
   /**
    * @roseuid 41DC2CD00232
    */
   public GetJuvenileWarrantByActivationStatusEvent() 
   {
    
   }
   
   /**
    * Access method for the juvenileNum property.
    * 
    * @return   the current value of the juvenileNum property
    */
   public Integer getJuvenileNum() 
   {
      return juvenileNum;
   }
   
   /**
    * Sets the value of the juvenileNum property.
    * 
    * @param aJuvenileNum the new value of the juvenileNum property
    */
   public void setJuvenileNum(Integer aJuvenileNum) 
   {
      this.juvenileNum = aJuvenileNum;
   }
   
   /**
    * Access method for the warrantActivationStatus property.
    * 
    * @return   the current value of the warrantActivationStatus property
    */
   public String getWarrantActivationStatus()
   {
      return warrantActivationStatus;
   }
   
   /**
    * Sets the value of the warrantActivationStatus property.
    * 
    * @param aWarrantActivationStatus the new value of the warrantActivationStatus property
    */
   public void setWarrantActivationStatus(String aWarrantActivationStatus)
   {
      warrantActivationStatus = aWarrantActivationStatus;
   }

/**
 * @return Returns the warrantStatusId.
 */
public String getWarrantStatusId() {
	return warrantStatusId;
}
/**
 * @param warrantStatusId The warrantStatusId to set.
 */
public void setWarrantStatusId(String warrantStatusId) {
	this.warrantStatusId = warrantStatusId;
}
}
