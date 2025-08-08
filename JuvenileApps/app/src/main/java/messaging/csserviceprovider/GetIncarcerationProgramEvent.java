/**
 * 
 */
package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */
public class GetIncarcerationProgramEvent extends RequestEvent 
{
	String incarcerationConditionId;

	public String getIncarcerationConditionId() {
		return incarcerationConditionId;
	}

	public void setIncarcerationConditionId(String incarcerationConditionId) {
		this.incarcerationConditionId = incarcerationConditionId;
	}
		
}
