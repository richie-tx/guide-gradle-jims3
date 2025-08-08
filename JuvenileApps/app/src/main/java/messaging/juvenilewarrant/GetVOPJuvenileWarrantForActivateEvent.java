//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetVOPJuvenileWarrantsForActivateEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetVOPJuvenileWarrantForActivateEvent extends RequestEvent 
{
   public String warrantNum;
   public String juvenileNum;
   public String referralNum;
   
   /**
    * @roseuid 4210FFA201E1
    */
   public GetVOPJuvenileWarrantForActivateEvent() 
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
    * Access method for the juvenileNum property.
    * 
    * @return   the current value of the juvenileNum property
    */
   public String getJuvenileNum()
   {
      return juvenileNum;    
   }
   
   /**
    * Access method for the referralNum property.
    * 
    * @return   the current value of the referralNum property
    */
   public String getReferralNum()
   {
      return referralNum;    
   }
   
   /**
    * @param warrantNum
    * @roseuid 4210FB96001E
    */
   public void setWarrantNum(String warrantNum) 
   {
    	this.warrantNum = warrantNum;
   }
   
   /**
    * @param juvenileNum
    * @roseuid 4210FB960022
    */
   public void setJuvenileNum(String juvenileNum) 
   {
    	this.juvenileNum = juvenileNum;
   }
   
   /**
    * @param referralNum
    * @roseuid 4210FB96002E
    */
   public void setReferralNum(String referralNum) 
   {
    	this.referralNum = referralNum;
   }	
}
