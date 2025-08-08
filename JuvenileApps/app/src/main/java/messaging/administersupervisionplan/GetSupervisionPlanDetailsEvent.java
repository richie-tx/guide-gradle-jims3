//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisionplan\\GetSupervisionPlanDetailsEvent.java

package messaging.administersupervisionplan;

import mojo.km.messaging.RequestEvent;


public class GetSupervisionPlanDetailsEvent extends RequestEvent 
{
   private String supervisionPlanId;
   
	/**
	 * @return Returns the supervisionPlanId.
	 */
	public String getSupervisionPlanId() {
		return supervisionPlanId;
	}
	/**
	 * @param supervisionPlanId The supervisionPlanId to set.
	 */
	public void setSupervisionPlanId(String supervisionPlanId) {
		this.supervisionPlanId = supervisionPlanId;
	}
}
