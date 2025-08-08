package messaging.administerassessments;

import mojo.km.messaging.RequestEvent;


public class GetAssessmentsSummaryEvent extends RequestEvent 
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
