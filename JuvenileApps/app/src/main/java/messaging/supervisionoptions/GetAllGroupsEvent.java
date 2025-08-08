//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetAllGroupsEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetAllGroupsEvent extends RequestEvent 
{
   private String agencyId;
   
   /**
    * @roseuid 42F7C4FE01F4
    */
   public GetAllGroupsEvent() 
   {
    
   }
   
   /**
    * @param agencyId
    * @roseuid 42F7997B03DB
    */
   public void setAgencyId( String anAgencyId ) 
   {
    	agencyId = anAgencyId;
   }
   
   /**
    * @return String
    * @roseuid 42F7997C0001
    */
   public String getAgencyId() 
   {
	    return agencyId;
   }
}
