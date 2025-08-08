//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionoptions\\ArchiveSupervisionConditionEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class ArchiveSupervisionConditionEvent extends RequestEvent 
{
   private String conditionId;
   
   /**
    * @roseuid 4421BDB80119
    */
   public ArchiveSupervisionConditionEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}
	
	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

}
