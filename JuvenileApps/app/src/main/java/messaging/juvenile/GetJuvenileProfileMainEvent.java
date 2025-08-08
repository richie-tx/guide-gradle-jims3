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
 * Class GetJuvenileProfileMainEvent.
 *  
 * @author  Anand Thorat
 */
public class GetJuvenileProfileMainEvent extends RequestEvent
{

	// ------------------------------------------------------------------------
	// --- field                                                            ---
	// ------------------------------------------------------------------------

	public String juvenileNum;
	
	//added for hot fix Bug #42651
	public String fromProfile;
	

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A9C41F002E
	 */
	public GetJuvenileProfileMainEvent()
	{

	} //end of messaging.juvenile.GetJuvenileProfileMainEvent.GetJuvenileProfileMainEvent

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param juvenileNum @roseuid 42A9B4860232
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	} //end of messaging.juvenile.GetJuvenileProfileMainEvent.setJuvenileNum

	/**
	 *  
	 * @return  String
	 * @roseuid 42A9B4860234
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	} //end of messaging.juvenile.GetJuvenileProfileMainEvent.getJuvenileNum

	/**
	 * @return the fromProfile
	 */
	public String getFromProfile() {
		return fromProfile;
	}

	/**
	 * @param fromProfile the fromProfile to set
	 */
	public void setFromProfile(String fromProfile) {
		this.fromProfile = fromProfile;
	}

} // end GetJuvenileProfileMainEvent
