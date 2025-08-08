package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import pd.juvenilecase.casefile.CasefileDocument;
import pd.juvenilecase.casefile.CasefileNonComplianceNotice;
import pd.juvenilecase.casefile.CasefileNonComplianceNoticeProbationViolation;
import pd.juvenilecase.casefile.CasefileNonComplianceNoticeSanction;
import messaging.casefile.SaveCasefileNonComplianceNoticeEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeSanctionResponseEvent;
import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

public class SaveCasefileNonComplianceNoticeCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		SaveCasefileNonComplianceNoticeEvent requestEvent = (SaveCasefileNonComplianceNoticeEvent)anEvent;
		CasefileNonComplianceNotice notice = new CasefileNonComplianceNotice();
		notice.setCompleteSanctionByDate(requestEvent.getCompleteSanctionByDate());
		notice.setCasefileId(requestEvent.getCasefileId());
		notice.setNonComplianceDate(requestEvent.getNonComplianceDate());
//		notice.setParentInformed(requestEvent.isParentInformed());
		notice.setSanctionAssignedDate(requestEvent.getSanctionAssignedDate());
		notice.setViolationLevelId(requestEvent.getViolationLevelId());
		notice.setSanctionLevelId(requestEvent.getSanctionLevelId());
		IHome home = new Home();
		home.bind(notice);
		String casefileNonComplianceNoticeId = notice.getOID();
   		CasefileDocument document = new CasefileDocument();
		document.setDocument(requestEvent.getDocument());
		document.setCasefileId(requestEvent.getCasefileId());
		document.setCasefileNonComplianceNoticeId(casefileNonComplianceNoticeId);
   		home.bind(document);
		Iterator violations = requestEvent.getViolations().iterator();
		while (violations.hasNext())
		{
			JuvenileTechnicalVOPCodesResponseEvent violationRespEvt = (JuvenileTechnicalVOPCodesResponseEvent) violations.next();
			CasefileNonComplianceNoticeProbationViolation violation = new CasefileNonComplianceNoticeProbationViolation();
			violation.setJuvenileTechnicalVOPCodesId(violationRespEvt.getCode());
			violation.setCasefileNonComplianceNoticeId(casefileNonComplianceNoticeId);
		}
		Iterator sanctions = requestEvent.getSanctions().iterator();
		while (sanctions.hasNext())
		{
			CasefileNonComplianceNoticeSanctionResponseEvent  sanctionRespEvt = (CasefileNonComplianceNoticeSanctionResponseEvent) sanctions.next();
			CasefileNonComplianceNoticeSanction sanction = new CasefileNonComplianceNoticeSanction();
			sanction.setJuvenileVOPSanctionCodesId(sanctionRespEvt.getJuvenileVOPSanctionCodesId());
			sanction.setCasefileNonComplianceNoticeId(casefileNonComplianceNoticeId);
			sanction.setOtherText(sanctionRespEvt.getOtherText());
			sanction.setComments(sanctionRespEvt.getComments());
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CasefileNonComplianceNoticeResponseEvent respEvent = new CasefileNonComplianceNoticeResponseEvent();
		respEvent.setCasefileNonComplianceNoticeId(casefileNonComplianceNoticeId);
		dispatch.postEvent(respEvent);
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
