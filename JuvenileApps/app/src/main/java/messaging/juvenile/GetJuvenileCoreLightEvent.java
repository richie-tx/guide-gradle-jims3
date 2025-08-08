/*
 * Project: JIMS
 * Class:   messaging.juvenile.GetJuvenileProfileMainEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetJuvenileCoreLightEvent.
 *  
 * @author  Richard Young
 */
public class GetJuvenileCoreLightEvent extends RequestEvent
{

    	private static final long serialVersionUID = 1L;
	private String juvenileId;
    	private String lastName;
	private String firstName;
	private String middleName;
	private String caller;
	
	
	
	public String getJuvenileId()
	{
	    return juvenileId;
	}
	public void setJuvenileId(String juvenile_id)
	{
	    this.juvenileId = juvenile_id;
	}
	public String getLastName()
	{
	    return lastName;
	}
	public void setLastName(String lastName)
	{
	    this.lastName = lastName;
	}
	public String getFirstName()
	{
	    return firstName;
	}
	public void setFirstName(String firstName)
	{
	    this.firstName = firstName;
	}
	public String getMiddleName()
	{
	    return middleName;
	}
	public void setMiddleName(String middleName)
	{
	    this.middleName = middleName;
	}
	public String getCaller()
	{
	    return caller;
	}
	public void setCaller(String caller)
	{
	    this.caller = caller;
	}

} //  Used in WorkShop Calendar
