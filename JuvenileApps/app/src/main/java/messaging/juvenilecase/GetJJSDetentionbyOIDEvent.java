//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSResultsEvent.java
//this eVENT GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJJSDetentionbyOIDEvent extends RequestEvent
{
	public String id;
	
	public String getId()
	{
	    return id;
	}

	public void setId(String id)
	{
	    this.id = id;
	}

	/**
	* @roseuid 41ACD56B01EF
	*/
	public GetJJSDetentionbyOIDEvent()
	{
	}
	
}
