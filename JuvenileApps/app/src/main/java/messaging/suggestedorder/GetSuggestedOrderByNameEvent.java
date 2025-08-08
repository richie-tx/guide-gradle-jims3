//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\GetSuggestedOrderEvent.java

package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

public class GetSuggestedOrderByNameEvent extends RequestEvent 
{
   private String suggestedOrderName;
   private String agencyId;
   
   /**
    * @roseuid 433AF2AA02B7
    */
   public GetSuggestedOrderByNameEvent() 
   {
    
   }
   
   /**
    * Access method for the suggestedOrderId property.
    * 
    * @return   the current value of the suggestedOrderId property
    */
   public String getSuggestedOrderName()
   {
      return suggestedOrderName;
   }
   
   /**
    * @param aSuggestedOrderName
    * @roseuid 433AF05001CD
    */
   public void setSuggestedOrderName(String aSuggestedOrderName) 
   {
		suggestedOrderName = aSuggestedOrderName;    
   }
/**
 * @return
 */
public String getAgencyId()
{
	return agencyId;
}

/**
 * @param string
 */
public void setAgencyId(String string)
{
	agencyId = string;
}

}
