//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSResultsEvent.java
//this eVENT GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJJSCourtEvent extends RequestEvent
{
	public String petitionNumber;
	public String referralNumber;
	public String juvenileNumber;

	/**
	* @roseuid 41ACD56B01EF
	*/
	public GetJJSCourtEvent()
	{
	}

	/**
	* @param petitionNum
	* @roseuid 41AC81DD0242
	*/
	public void setPetitionNumber(String petitionNum)
	{
		this.petitionNumber = petitionNum;
	}

	/**
	* @return String
	* @roseuid 41AC81DD0244
	*/
	public String getPetitionNumber()
	{
		return this.petitionNumber;
	}

	/**
	 * @return
	 */
	public String getReferralNumber()
	{
		return this.referralNumber;
	}

	/**
	 * @param referralNum
	 */
	public void setReferralNumber(String referralNum)
	{
		this.referralNumber = referralNum;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}

}
