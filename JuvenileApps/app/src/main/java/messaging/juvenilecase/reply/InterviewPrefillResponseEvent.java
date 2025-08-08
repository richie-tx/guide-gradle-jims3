/*
 * Created on Oct 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 */
public class InterviewPrefillResponseEvent extends ResponseEvent
{
	private int onsetAge; 
	private String juvSex;
	private String juvSexId;
	private int latestReferralScore;
	//hot fix: #32710 
	private String juvNum;
	private String juvenileName;

	/**
	 * @return
	 */
	public String getJuvSex()
	{
		return juvSex;
	}

	/**
	 * @return
	 */
	public int getOnsetAge()
	{
		return onsetAge;
	}

	/**
	 * @param string
	 */
	public void setJuvSex(final String string)
	{
		juvSex = string;
	}

	/**
	 * @param i
	 */
	public void setOnsetAge(final int i)
	{
		onsetAge = i;
	}

	/**
	 * @return Returns the juvSexId.
	 */
	public String getJuvSexId() {
		return juvSexId;
	}
	/**
	 * @param juvSexId The juvSexId to set.
	 */
	public void setJuvSexId(String juvSexId) {
		this.juvSexId = juvSexId;
	}

	public void setLatestReferralScore(int latestReferralScore) {
		this.latestReferralScore = latestReferralScore;
	}

	public int getLatestReferralScore() {
		return latestReferralScore;
	}

	/**
	 * @return the juvNum
	 */
	public String getJuvNum() {
		return juvNum;
	}

	/**
	 * @param juvNum the juvNum to set
	 */
	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}

	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName() {
		return juvenileName;
	}

	/**
	 * @param juvenileName the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	
}
