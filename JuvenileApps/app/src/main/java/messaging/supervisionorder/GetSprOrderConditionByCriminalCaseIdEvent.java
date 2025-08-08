//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\CreateSupervisionOrderEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetSprOrderConditionByCriminalCaseIdEvent extends RequestEvent 
{
	private String criminalCaseId;

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}	
}
