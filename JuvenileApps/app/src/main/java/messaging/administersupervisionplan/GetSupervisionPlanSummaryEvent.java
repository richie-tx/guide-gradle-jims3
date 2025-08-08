//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisionplan\\GetSupervisionPlanSummaryEvent.java

package messaging.administersupervisionplan;

import mojo.km.messaging.RequestEvent;


public class GetSupervisionPlanSummaryEvent extends RequestEvent 
{
   
   private String defendantId;
   private boolean isSearchOnActiveSupervisionPeriod;
   
   
/**
 * @return Returns the defendantId.
 */
public String getDefendantId() {
	return defendantId;
}
/**
 * @param defendantId The defendantId to set.
 */
public void setDefendantId(String defendantId) {
	this.defendantId = defendantId;
}
/**
 * @return Returns the isSearchOnActiveSupervisionPeriod.
 */
public boolean isSearchOnActiveSupervisionPeriod() {
	return isSearchOnActiveSupervisionPeriod;
}
/**
 * @param isSearchOnActiveSupervisionPeriod The isSearchOnActiveSupervisionPeriod to set.
 */
public void setSearchOnActiveSupervisionPeriod(boolean isSearchOnActiveSupervisionPeriod) {
	this.isSearchOnActiveSupervisionPeriod = isSearchOnActiveSupervisionPeriod;
}
}
