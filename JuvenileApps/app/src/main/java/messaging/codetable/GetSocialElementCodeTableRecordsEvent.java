/*
 * Project: JIMS
 * Class:   messaging.codetable.GetSocialElementCodeTableRecordsEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetSocialElementCodeTableRecordsEvent.
 *  
 * @author  Anand Thorat
 */
public class GetSocialElementCodeTableRecordsEvent extends mojo.km.messaging.RequestEvent
{

	// ------------------------------------------------------------------------
	// --- field                                                            ---
	// ------------------------------------------------------------------------

	public String element;

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42B9C5C70138
	 */
	public GetSocialElementCodeTableRecordsEvent()
	{

	} //end of messaging.codetable.GetSocialElementCodeTableRecordsEvent.GetSocialElementCodeTableRecordsEvent

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param element @roseuid 42B9C28F0128
	 */
	public void setElement(String element)
	{
		this.element = element;
	} //end of messaging.codetable.GetSocialElementCodeTableRecordsEvent.setElement

	/**
	 *  
	 * @return  String
	 * @roseuid 42B9C28F012A
	 */
	public String getElement()
	{
		return element;
	} //end of messaging.codetable.GetSocialElementCodeTableRecordsEvent.getElement

} // end GetSocialElementCodeTableRecordsEvent
