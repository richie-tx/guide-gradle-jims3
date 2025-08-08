//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisionplan\\reply\\SupervisionPlanResponseEvent.java

package messaging.administersupervisionplan.reply;

import mojo.km.messaging.ResponseEvent;


public class SupervisionPlanResponseEvent extends ResponseEvent
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
