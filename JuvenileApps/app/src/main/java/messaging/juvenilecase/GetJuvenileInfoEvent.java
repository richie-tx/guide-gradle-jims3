package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileInfoEvent extends RequestEvent
{	
	public String juvenileNum;

	/**
	 * @roseuid 42A9A16D0190
	 */
	public GetJuvenileInfoEvent()
	{
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}	
}
