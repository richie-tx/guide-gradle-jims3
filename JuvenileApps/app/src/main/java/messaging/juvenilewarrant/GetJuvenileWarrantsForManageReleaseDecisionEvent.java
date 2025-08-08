//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsForManageReleaseDecisionEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForManageReleaseDecisionEvent extends RequestEvent 
{
   public String warrantNum;
   public String juvenileFirstName;
   public String juvenileLastName;
   public String releaseDecision;
   
   /**
    * @roseuid 421371B801D4
    */
   public GetJuvenileWarrantsForManageReleaseDecisionEvent() 
   {
    
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
    * Access method for the releaseDecision property.
    * 
    * @return   the current value of the releaseDecision property
    */
   public String getReleaseDecision()
   {
      return releaseDecision;
   }
   
   /**
    * Sets the value of the releaseDecision property.
    * 
    * @param aReleaseDecision the new value of the releaseDecision property
    */
   public void setReleaseDecision(String aReleaseDecision)
   {
      releaseDecision = aReleaseDecision;
   }
   
}
