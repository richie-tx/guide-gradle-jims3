//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SearchJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchJuvenileCasefileByOfficerIdsEvent extends RequestEvent 
{
   public String officerIds;
   
   /**
    * @roseuid 4278C831024E
    */
   public SearchJuvenileCasefileByOfficerIdsEvent() 
   {    
   }

   /**
    * @param firstName
    * @roseuid 4278C7B9010B
    */
   public void setOfficerIds(String aOfficerIds) 
   {
        this.officerIds = aOfficerIds;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B90115
    */
   public String getOfficerIds() 
   {
    return this.officerIds;
   }
}
