//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSResultsEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJJSPetitionsEvent extends RequestEvent
{
	public String petitionNum;
	public String referralNum;
	public String juvenileNum;

	/**
	* @roseuid 41ACD56B01EF
	*/
	public GetJJSPetitionsEvent()
	{
	}

	/**
	* @param petitionNum
	* @roseuid 41AC81DD0242
	*/
	public void setPetitionNum(String petitionNum)
	{
		this.petitionNum = petitionNum;
	}

	/**
	* @return String
	* @roseuid 41AC81DD0244
	*/
	public String getPetitionNum()
	{
		return this.petitionNum;
	}

	/**
	 * @return
	 */
	public String getReferralNum()
	{
		return this.referralNum;
	}

	/**
	 * @param referralNum
	 */
	public void setReferralNum(String referralNum)
	{
		this.referralNum = referralNum;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

}
