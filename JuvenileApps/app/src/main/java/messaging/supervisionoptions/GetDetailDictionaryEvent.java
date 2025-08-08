//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetDetailDictionaryEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetDetailDictionaryEvent extends RequestEvent 
{
   private String agencyId;
   
   /**
    * @roseuid 42F7C50700AB
    */
   public GetDetailDictionaryEvent() 
   {
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
