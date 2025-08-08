//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetPoliciesToDisassociateEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class GetConditionsToDisassociateFromCourtPoliciesEvent extends RequestEvent 
{
   private Collection courtIds = new ArrayList();
   private String groupId;
   private String policyId;
   
   /**
    * @roseuid 42F7C508030D
    */
   public GetConditionsToDisassociateFromCourtPoliciesEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getPolicyId()
	{
		return policyId;
	}
	
	/**
	 * @param string
	 */
	public void setPolicyId(String string)
	{
		policyId = string;
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
