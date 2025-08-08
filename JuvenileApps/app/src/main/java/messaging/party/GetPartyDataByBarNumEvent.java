//Source file: C:\\views\\dev\\app\\src\\messaging\\contact\\GetPartyDataEvent.java

package messaging.party;

import mojo.km.messaging.RequestEvent;

public class GetPartyDataByBarNumEvent extends RequestEvent
{
	private String barNum;
	
	/**
	 * @roseuid 416D2E380333
	 */
	public GetPartyDataByBarNumEvent()
	{

	}

	

	/**
	 * @return Returns the barNum.
	 */
	public String getBarNum() {
		return barNum;
	}
	/**
	 * @param barNum The barNum to set.
	 */
	public void setBarNum(String barNum) {
		this.barNum = barNum;
	}
}