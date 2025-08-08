/*
 * Created on Dec 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant.reply;

import mojo.km.messaging.ResponseEvent;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileJusticeSystemResultsResponseEvent extends ResponseEvent
{
	private String petitionNum;
	private String referralNum;
	private String juvenileName;

	/**
	 * @return
	 */
	public String getJuvenileName()
	{
		return juvenileName;
	}

	/**
	 * @return
	 */
	public String getPetitionNum()
	{
		return petitionNum;
	}

	/**
	 * @return
	 */
	public String getReferralNum()
	{
		return referralNum;
	}

	/**
	 * @param juvenileName
	 */
	public void setJuvenileName(String juvenileName)
	{
		this.juvenileName = juvenileName;
	}

	/**
	 * @param petitionNum
	 */
	public void setPetitionNum(String petitionNum)
	{
		this.petitionNum = petitionNum;
	}

	/**
	 * @param referralNum
	 */
	public void setReferralNum(String referralNum)
	{
		this.referralNum = referralNum;
	}

}
