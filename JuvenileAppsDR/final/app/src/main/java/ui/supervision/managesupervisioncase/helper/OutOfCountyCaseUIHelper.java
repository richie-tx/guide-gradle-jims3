/*
 * Created on May 9, 2006
 */
package ui.supervision.managesupervisioncase.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ui.supervision.administercaseload.helper.CloseCaseResultBean;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;

import messaging.administercaseload.CloseCaseResponseEvent;
import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.domintf.contact.party.IParty;
import messaging.managesupervisioncase.CloseOutOfCountyCaseEvent;
import messaging.party.GetPartyNameEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.CaseloadControllerServiceNames;
import naming.CloseCaseConstants;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.PartyControllerServiceNames;

/**
 * @author jmcnabb
 * 
 * Helper class for AOOC UI.
 */
public class OutOfCountyCaseUIHelper
{
	private static final String ZERO = "0";
	
	
	
	/**
	 * 
	 * @param oocForm
	 * @param defendantId
	 * @param caseNum
	 */
	public static void populateCaseloadForOOCCase(OutOfCountyCaseForm oocForm, String defendantId, String caseNum)
	{
		oocForm.setCloseCaseAssignmentList(new ArrayList());
		
		while(defendantId.length() < 8)
		{
			defendantId = ZERO + defendantId;
		}
		
		GetActiveCasesEvent getCaseloadEvent = (GetActiveCasesEvent)EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASES);
		getCaseloadEvent.setDefendantId(defendantId);
		
		List responsesList = MessageUtil.postRequestListFilter(getCaseloadEvent, CaseAssignmentResponseEvent.class);
		
		if (responsesList != null && responsesList.size() > 0)
		{
			CaseAssignmentResponseEvent responseEvent = (CaseAssignmentResponseEvent) responsesList.get(0);
			if (responseEvent != null)
			{
				List caseAssignmentsList = responseEvent.getCaseAssignments();
				if(caseAssignmentsList != null)
				{
					Iterator it = caseAssignmentsList.iterator();
					while(it.hasNext())
					{
						CaseAssignmentTO caseAssignment = (CaseAssignmentTO)it.next();
						if(caseAssignment.getCriminalCaseId().equalsIgnoreCase(caseNum))
						{
							ArrayList closeCaseAssignmentList = new ArrayList();
							closeCaseAssignmentList.add(caseAssignment);
							oocForm.setCloseCaseAssignmentList(closeCaseAssignmentList);
							break;
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param oocCaseForm
	 * @return
	 */
	public static CloseOutOfCountyCaseEvent getCloseOutOfCountyCaseEvent(OutOfCountyCaseForm oocCaseForm)
	{
		CloseOutOfCountyCaseEvent closeOOCCaseEvent = (CloseOutOfCountyCaseEvent) EventFactory
						.getInstance(OutOfCountyCaseControllerServiceNames.CLOSEOUTOFCOUNTYCASE);
		
		closeOOCCaseEvent.setCaseNum(oocCaseForm.getCaseNum());
		closeOOCCaseEvent.setCourtDivisionId(oocCaseForm.getCdi());
		closeOOCCaseEvent.setPreviousDispositionTypeId(oocCaseForm.getDispositionTypeId());
		closeOOCCaseEvent.setDispositionTypeId(oocCaseForm.getClosureReasonId());
		Date transferOutDate = DateUtil.stringToDate(oocCaseForm.getTransferOutDateStr(), DateUtil.DATE_FMT_1);
		closeOOCCaseEvent.setDispositionDate(transferOutDate);
		closeOOCCaseEvent.setReasonForUpdateId(null);
		
		if(oocCaseForm.getAction().equalsIgnoreCase(CloseCaseConstants.OOC_CASE_UPDATE_CLOSURE))
		{
			closeOOCCaseEvent.setReasonForUpdateId(oocCaseForm.getReasonForUpdateId());
		}
		
		return closeOOCCaseEvent;
	}
	
	
	/**
	 * 
	 * @param oocCaseForm
	 * @param closeCaseRespEventList
	 */
	public static void convertRespEvtToCaseCloseBean(OutOfCountyCaseForm oocCaseForm, List closeCaseRespEventList)
	{
		oocCaseForm.setCloseCaseSuccess(true);
		
		ArrayList resultBeanList = new ArrayList();
		
		Iterator iter = closeCaseRespEventList.iterator();
		while(iter.hasNext())
		{
			CloseCaseResponseEvent closeCaseRespEvent = (CloseCaseResponseEvent)iter.next();
			
			CloseCaseResultBean beanObj = new CloseCaseResultBean();
			
			beanObj.setDefendantId(closeCaseRespEvent.getDefendantId());
			beanObj.setCaseNum(closeCaseRespEvent.getCriminalCaseId());
			String result = closeCaseRespEvent.getResult();
			if(result.equalsIgnoreCase(CloseCaseConstants.FAILURE))
			{
				oocCaseForm.setCloseCaseSuccess(false);
			}
			beanObj.setResult(result);
			beanObj.setReasonList(closeCaseRespEvent.getFailureReasonsList());
			
			resultBeanList.add(beanObj);
		}
		
		oocCaseForm.setCloseCasesResultBeanList(resultBeanList);
		
	}//end of convertRespEvtToCaseCloseBean()
	
	public static Name getPartyName(String spn, String nameSeqNum) {

		Name aName = null;
		GetPartyNameEvent getNameEvent =
			(GetPartyNameEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTYNAME);
		getNameEvent.setNameSeqNum(nameSeqNum);
		getNameEvent.setSpn(spn);
		IParty party = (IParty) MessageUtil.postRequest(getNameEvent, PartyResponseEvent.class);
		if (party != null){
			aName = new Name();
			aName.setFirstName(party.getFirstName());
			aName.setMiddleName(party.getMiddleName());
			aName.setLastName(party.getLastName());
		}
		return aName;
	}
}
