//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GenerateSupervisionConditionNameEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class BasicGenerateNameEvent extends RequestEvent 
{
   private String agencyId;
   private String groupId;
   private String type;

   public BasicGenerateNameEvent()
   {
   }


	public BasicGenerateNameEvent( String aType )
	{
		type = aType;
	}

	public BasicGenerateNameEvent( BasicGenerateNameEvent evt )
	{
		agencyId = evt.agencyId;
		groupId = evt.groupId;
		type = evt.type;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}
	
	/**
	 * @param string
	 */
	public void setGroupId(String string)
	{
		groupId = string;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}
	

}
