package pd.supervision.administerprogramreferrals.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.administerprogramreferrals.GetSuperviseeCaseEvent;
import messaging.administerprogramreferrals.reply.SuperviseeCaseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administerprogramreferrals.CSCaseHelper;

public class GetSuperviseeCaseCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		GetSuperviseeCaseEvent reqEvent = (GetSuperviseeCaseEvent)event;
		
		List caseAssignmentList = CaseAssignmentOrder.findByCaseNumber(reqEvent.getCriminalCaseId());
		Iterator iter = caseAssignmentList.iterator();
		if(iter.hasNext())
		{
			CaseAssignmentOrder caseAssignmentOrder = (CaseAssignmentOrder)iter.next();
			SuperviseeCaseResponseEvent superviseeCaseRespEvt = CSCaseHelper.getSuperviseeCaseResponseEvent(caseAssignmentOrder);
			
			MessageUtil.postReply(superviseeCaseRespEvt);
		}
//		If the case is not assigned
		else
		{
			SuperviseeCaseResponseEvent superviseeCaseRespEvt = new SuperviseeCaseResponseEvent();
			
			CriminalCase criminalCase = CriminalCase.find(reqEvent.getCriminalCaseId());
			
			//set response event properties
			superviseeCaseRespEvt.setCdi(criminalCase.getCourtDivisionId());
			superviseeCaseRespEvt.setCaseNumber(criminalCase.getCaseNum());
			superviseeCaseRespEvt.setCourtNumber(criminalCase.getCourtId());
			
			if (criminalCase.getOffenseCode() != null)
			{
				superviseeCaseRespEvt.setOffense(criminalCase.getOffenseCode().getDescription());			
			}
			
			MessageUtil.postReply(superviseeCaseRespEvt);
		}
	}

}
