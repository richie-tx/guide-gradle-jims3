//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenileAbuseListEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileAbuseListEvent extends RequestEvent
{
	public String juvenileNum;
	public String casefileId;

	/**
	 * @roseuid 42B1967F0157
	 */
	public GetJuvenileAbuseListEvent()
	{

	}

	/**
	 * @param juvenileNum
	 * @roseuid 42B18B40014B
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 42B18B400157
	 */
	public String getJuvenileNum()
	{
		return this.juvenileNum;
	}
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}
