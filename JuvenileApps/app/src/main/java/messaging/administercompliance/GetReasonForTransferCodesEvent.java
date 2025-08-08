//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCCommunityServicesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * @author mchowdhury
 */

public class GetReasonForTransferCodesEvent extends RequestEvent 
{
   private String requestType;

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}
	
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
