package messaging.administercaseload;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class CloseCaseEvent extends RequestEvent
{
//	list of CaseAssignmentTo objects which implement ICaseAssignment interface
	private Collection closeCasesList;

	public Collection getCloseCasesList() {
		return closeCasesList;
	}

	public void setCloseCasesList(Collection closeCasesList) {
		this.closeCasesList = closeCasesList;
	} 
	
	
}
