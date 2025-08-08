// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetCasenotesEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenotesByIdsEvent extends RequestEvent
{
	private String supervisionPeriodId;
	private String spn;
	
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}	
}
