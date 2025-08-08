package pd.juvenilecase.casefile.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pd.juvenilecase.casefile.CasefileDocument;
import pd.juvenilecase.casefile.CasefileNonComplianceNotice;
import pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation;
import pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction;
import messaging.casefile.GetCasefileNonComplianceNoticesEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeProbationViolationResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeSanctionResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class GetCasefileNonComplianceNoticesCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetCasefileNonComplianceNoticesEvent requestEvent = (GetCasefileNonComplianceNoticesEvent)anEvent;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		CasefileNonComplianceNoticeResponseEvent resp = new CasefileNonComplianceNoticeResponseEvent();
		Iterator notices = CasefileNonComplianceNotice.findAll("casefileId", requestEvent.getCasefileId());
		while (notices.hasNext())
		{
			List sanctions = new ArrayList();
			List violations = new ArrayList();
			CasefileNonComplianceNotice notice = (CasefileNonComplianceNotice) notices.next();
			resp = notice.getResponseEvent();
			Iterator nonComplianceSanctions = CasefileNonComplianceNoticeSanction.findAll("casefileNonComplianceNoticeId", resp.getCasefileNonComplianceNoticeId());
			while (nonComplianceSanctions.hasNext())
			{
				CasefileNonComplianceNoticeSanction sanction = (CasefileNonComplianceNoticeSanction) nonComplianceSanctions.next();
				CasefileNonComplianceNoticeSanctionResponseEvent responseSanction = sanction.getResponseEvent();
				if (responseSanction != null)
				{
					sanctions.add(responseSanction);
				}
			} 
			if ( sanctions.size() > 0) {
				resp.setSanctions(sanctions);
			}
			Iterator nonComplianceViolations = CasefileNonComplianceNoticeProbationViolation.findAll("casefileNonComplianceNoticeId", resp.getCasefileNonComplianceNoticeId());
			while (nonComplianceViolations.hasNext())
			{
				CasefileNonComplianceNoticeProbationViolation violation = (CasefileNonComplianceNoticeProbationViolation) nonComplianceViolations.next();
				CasefileNonComplianceNoticeProbationViolationResponseEvent responseViolation = violation.getResponseEvent();
				if (responseViolation != null)
				{
					violations.add(responseViolation);
				}
			} 
			if ( violations.size() > 0) {
				resp.setProbationViolations(violations);
			}
			dispatch.postEvent(resp);
		}
		
		Iterator iter = CasefileDocument.findAllForCasefile(requestEvent.getCasefileId());
		while ( iter.hasNext() )
		{
			CasefileDocument doc = (CasefileDocument)iter.next();
			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
			document.setEntryDate(doc.getCreationDate());
			document.setReportType("NON COMPLIANCE NOTICE");
			document.setReportId(doc.getOID().toString());
			document.setReport(doc.getDocument());
			dispatch.postEvent(document);
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
