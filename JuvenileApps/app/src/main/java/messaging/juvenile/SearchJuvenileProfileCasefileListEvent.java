/*
 * Project: JIMS
 * Class:   messaging.juvenile.SearchJuvenileProfileCasefileListEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * Class SearchJuvenileProfileCasefileListEvent.
 *  
 * @author  Anand Thorat
 */
public class SearchJuvenileProfileCasefileListEvent extends mojo.km.messaging.RequestEvent {

	// ------------------------------------------------------------------------
	// --- field                                                            ---
	// ------------------------------------------------------------------------

	public String juvenileId;


	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42B88AE50399
	 */
	public SearchJuvenileProfileCasefileListEvent() {

	}//end of messaging.juvenile.SearchJuvenileProfileCasefileListEvent.SearchJuvenileProfileCasefileListEvent


	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param juvenileId @roseuid 42B8889202AF
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}//end of messaging.juvenile.SearchJuvenileProfileCasefileListEvent.setJuvenileId

	/**
	 *  
	 * @return  String
	 * @roseuid 42B8889202B1
	 */
	public String getJuvenileId() {
		return juvenileId;
	}//end of messaging.juvenile.SearchJuvenileProfileCasefileListEvent.getJuvenileId

} // end SearchJuvenileProfileCasefileListEvent
