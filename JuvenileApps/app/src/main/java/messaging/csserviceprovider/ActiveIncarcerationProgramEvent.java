/**
 * 
 */
package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class ActiveIncarcerationProgramEvent extends RequestEvent 
{
	String incarcerationConditionId;
	String statusCode;

	public String getIncarcerationConditionId() {
		return incarcerationConditionId;
	}

	public void setIncarcerationConditionId(String incarcerationConditionId) {
		this.incarcerationConditionId = incarcerationConditionId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
