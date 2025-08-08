//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsforInactivateEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsForInactivateEvent extends RequestEvent 
{
   public String firstName;
   public String lastName;
   public String warrantNum;
   
   /**
    * @roseuid 41F7C29C0055
    */
   public GetJuvenileWarrantsForInactivateEvent() 
   {
    
   }
   
   /**
    * Access method for the firstName property.
    * 
    * @return   the current value of the firstName property
    */
   public String getFirstName()
   {
      return firstName;
   }
   
   /**
    * Access method for the lastName property.
    * 
    * @return   the current value of the lastName property
    */
   public String getLastName()
   {
      return lastName;
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
    * Sets the value of the firstName property.
    * 
    * @param aFirstName the new value of the firstName property
    */
   public void setFirstName(String aFirstName)
   {
      firstName = aFirstName;
   }
   
   /**
    * Sets the value of the lastName property.
    * 
    * @param aLastName the new value of the lastName property
    */
   public void setLastName(String aLastName)
   {
      lastName = aLastName;
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
}