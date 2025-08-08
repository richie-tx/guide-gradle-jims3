package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.CasefileNonComplianceNotice;
import messaging.casefile.UpdateCasefileNonComplianceCompletionEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;

public class UpdateCasefileNonComplianceCompletionCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		UpdateCasefileNonComplianceCompletionEvent request = (UpdateCasefileNonComplianceCompletionEvent) anEvent;
		if (request.getCasefileNonComplianceId() != null) {
			CasefileNonComplianceNotice notice = CasefileNonComplianceNotice.find(request.getCasefileNonComplianceId());
			notice.setActionTakenId(request.getActionTakenId());
			notice.setActionTakenComments(request.getActionTakenComments());
			notice.setCompletionComments(request.getCompletionComments());
			notice.setActionTakenOtherText(request.getActionTakenOtherText());
			notice.setCompletionDate(request.getCompletionDate());
			notice.setCompletionStatusId(request.getCompletionStatusId());
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
