/*
 * Project: JIMS2
 * Class:   messaging.juvenilecase.GetAllMAYSIAssessmentsEvent
 * Version: 1.0.0
 *
 * Date:    2005-05-17
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetAllMAYSIAssessmentsEvent.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetAllMAYSIAssessmentsEvent extends RequestEvent
{
	// ------------------------------------------------------------------------
	// --- field                                                            ---
	// ------------------------------------------------------------------------

	private String juvenileNumber;
	private String referralNumber;

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42790E0401E4
	 */
	public GetAllMAYSIAssessmentsEvent()
	{

	} //end of messaging.juvenilecase.GetAllMAYSIAssessmentsEvent.GetAllMAYSIAssessmentsEvent

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param juvenileNumber @roseuid 427901890251
	 */
	public void setJuvenileNumber(String juvenileNumber)
	{
		this.juvenileNumber = juvenileNumber;

	} //end of messaging.juvenilecase.GetAllMAYSIAssessmentsEvent.setJuvenileNumber

	/**
	 *  
	 * @return  juvenileNumber
	 * @roseuid 427901890253
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	} //end of messaging.juvenilecase.GetAllMAYSIAssessmentsEvent.getJuvenileNumber



	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNum The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
} // end GetAllMAYSIAssessmentsEvent
