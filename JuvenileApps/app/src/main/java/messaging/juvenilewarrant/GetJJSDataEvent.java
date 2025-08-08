//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSDataEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJJSDataEvent extends RequestEvent
{
	private String petitionNum;
	private String referralNum;
	private String juvenileNum;
	private String warrantType;

	/**
	 * @roseuid 41ACD56300FD
	 */
	public GetJJSDataEvent()
	{

	}
	
	/**
	 * @return juvenile number
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}
	
	/**
	 * @param aJuvenileNum
	 */
	public void setJuvenileNum(String aJuvenileNum)
	{
		this.juvenileNum = aJuvenileNum;
	}

	/**
	 * @param petitionNum
	 * @roseuid 4195213701C9
	 */
	public void setPetitionNum(String petitionNum)
	{
		this.petitionNum = petitionNum;
	}

	/**
	 * @return String
	 * @roseuid 4195213701CB
	 */
	public String getPetitionNum()
	{
		return this.petitionNum;
	}

	/**
	 * @param referralNum
	 * @roseuid 4195213701D7
	 */
	public void setReferralNum(String referralNum)
	{
		this.referralNum = referralNum;
	}

	/**
	 * @return String
	 * @roseuid 4195213701D9
	 */
	public String getReferralNum()
	{
		return this.referralNum;
	}
	/**
	 * @return
	 */
	public String getWarrantType()
	{
		return warrantType;
	}

	/**
	 * @param string
	 */
	public void setWarrantType(String warrantType)
	{
		this.warrantType = warrantType;
	}

}
