/*
 * Created on Aug 23, 2018
 * author nemathew
 * 
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetJuvReferralFamilyInfoEvent extends RequestEvent {
    
    	/**
    	 * @roseuid 43299A4D0157
    	 */
    	public GetJuvReferralFamilyInfoEvent() {
    	    
    	}
	public String juvenileNum;

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
