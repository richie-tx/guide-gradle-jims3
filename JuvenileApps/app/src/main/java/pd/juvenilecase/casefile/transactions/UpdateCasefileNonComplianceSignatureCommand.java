package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.CasefileNonComplianceNotice;
import messaging.casefile.UpdateCasefileNonComplianceSignatureEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;

public class UpdateCasefileNonComplianceSignatureCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		UpdateCasefileNonComplianceSignatureEvent request = (UpdateCasefileNonComplianceSignatureEvent) anEvent;
		if (request.getCasefileNonComplianceId() != null) {
			CasefileNonComplianceNotice notice = CasefileNonComplianceNotice.find(request.getCasefileNonComplianceId());
			notice.setSignedDate(request.getSignedDate());
			notice.setSignatureStatusId(request.getSignatureStatusId());
			notice.setParentInformed(request.isParentInformed());
		} 
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
