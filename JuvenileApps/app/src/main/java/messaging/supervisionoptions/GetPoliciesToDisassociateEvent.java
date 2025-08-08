//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetPoliciesToDisassociateEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class GetPoliciesToDisassociateEvent extends RequestEvent 
{
   private Collection courtIds = new ArrayList();
   private String groupId;
   private String conditionId;
   
   /**
    * @roseuid 42F7C508030D
    */
   public GetPoliciesToDisassociateEvent() 
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

	/**
	 * @return
	 */
	public Collection getCourtIds()
	{
		return courtIds;
	}
	
	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}
	
	/**
	 * @param collection
	 */
	public void setCourtIds(Collection collection)
	{
		courtIds = collection;
	}
	
	/**
	 * @param string
	 */
	public void setGroupId(String string)
	{
		groupId = string;
	}

}
