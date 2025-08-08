package ui.supervision.administersupervisee.action.transfercase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.codetable.reply.ICode;
import messaging.transfers.reply.TransferResponseEvent;
import naming.PDCodeTableConstants;
import naming.PDConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class UpdateTransferCaseHistoryAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setUp");
		keyMap.put("button.submit", "updateTransferHistoryConfirm");
	}
	public ActionForward setUp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm; 
		CaseAssignmentForm caseAssignmentForm = initializeCaseAssignmentHeader(aMapping, aRequest);
		SuperviseeTransferCasesInfo superviseeTransferCasesInfo = superviseeForm.getTransferCasesInfo();
		String caseNumSelectedForTransferUpdate = superviseeTransferCasesInfo.getCaseNumSelectedForTransferUpdate();
		
		Collection harrisCountyCases = superviseeForm.getHarrisCountyCases();
		//Dawn - there may be more than one transfer record!!!  How do we identify the correct one?
		for (Iterator iterator = harrisCountyCases.iterator(); iterator.hasNext();) {
			TransferResponseEvent responseEvent = (TransferResponseEvent) iterator.next();
			if (caseNumSelectedForTransferUpdate.equals(responseEvent.getCaseSelectedKey())) {
				caseAssignmentForm.setCdi(responseEvent.getCdi());
				caseAssignmentForm.setCaseNum( responseEvent.getCaseNum());
				caseAssignmentForm.setCourtNumber(responseEvent.getCourtNum());	
				superviseeTransferCasesInfo.setTransferOutDate(responseEvent.getHcTransferOutDate());
				String ctyStateId = null;
				String ctyStateName = null;
				boolean isTransferredIn = false;
				if (responseEvent.getClassificationSeqNumForTransferIn() != null && 
						!responseEvent.getClassificationSeqNumForTransferIn().equals("")){
					isTransferredIn = true;
				}
				boolean isTransferredOut = false;
				if (responseEvent.getClassificationSeqNumForTransferOut() != null && 
						!responseEvent.getClassificationSeqNumForTransferOut().equals("")){
					isTransferredOut = true;
				}
				if (isTransferredIn && isTransferredOut){
					 ctyStateId= responseEvent.getReceivingCountyState();
					 ctyStateName = responseEvent.getReceivingCountyStateName();
					 superviseeTransferCasesInfo.setPersonId(responseEvent.getOtherCountyStatePersonIdNumber());
				} else if (isTransferredIn){
					 ctyStateId= responseEvent.getTransferringCountyState();
					 ctyStateName = responseEvent.getTransferringCountyStateName();
					 superviseeTransferCasesInfo.setPersonId(responseEvent.getOtherCountyStatePersonIdNumber());
				} else {
					ctyStateId = responseEvent.getReceivingCountyState();
					ctyStateName = responseEvent.getReceivingCountyStateName();
				}
				if (ctyStateId.length() == 2){
					superviseeTransferCasesInfo.setTransferStateId(ctyStateId);
					superviseeTransferCasesInfo.setTransferStateName(ctyStateName);
					superviseeTransferCasesInfo.setTransferTxCountyId("");
					superviseeTransferCasesInfo.setTransferTxCountyName("");
				} else {
					superviseeTransferCasesInfo.setTransferTxCountyId(this.stripLeadingZeroes(ctyStateId));
					superviseeTransferCasesInfo.setTransferTxCountyName(ctyStateName);
					superviseeTransferCasesInfo.setTransferStateId("");
					superviseeTransferCasesInfo.setTransferStateName("");
				}
				superviseeTransferCasesInfo.setTransferOutClassificationSeqNum(responseEvent.getClassificationSeqNumForTransferOut());
				superviseeTransferCasesInfo.setTransferOutSubclassificationSeqNum(responseEvent.getSubclassificationSeqNumForTransferOut());
				superviseeTransferCasesInfo.setTransferInDate(responseEvent.getHcTransferInDate());				
				superviseeTransferCasesInfo.setTransferInClassificationSeqNum(responseEvent.getClassificationSeqNumForTransferIn());
				superviseeTransferCasesInfo.setTransferInSubclassificationSeqNum(responseEvent.getSubclassificationSeqNumForTransferIn());
				superviseeTransferCasesInfo.setRejected(responseEvent.isRejected());	
				superviseeTransferCasesInfo.setRejectedAsStr(responseEvent.getRejectedAsStr());
				
				ICaseAssignment caseAssignment = new CaseAssignmentTO();
				caseAssignment.setDefendantId(caseAssignmentForm.getDefendantId());
				caseAssignment.setCdi(responseEvent.getCdi());
				caseAssignment.setCriminalCaseId(responseEvent.getCaseNum());
				caseAssignment.setCourtId(responseEvent.getCourtNum());
				List casesToTransfer = new ArrayList();
				casesToTransfer.add(caseAssignment);
				
				superviseeTransferCasesInfo.setCasesToTransfer(casesToTransfer);
				superviseeForm.setTransferCasesInfo(superviseeTransferCasesInfo);
				superviseeForm.setPrevTransferCasesInfo(new SuperviseeTransferCasesInfo(superviseeTransferCasesInfo));
			}
		}		
		return aMapping.findForward("initialSetup");
	}
	   private String stripLeadingZeroes(String aString) {
		   StringBuffer sb = new StringBuffer(aString);
		   StringBuffer newSb = new StringBuffer();
		   for (int i = 0; i < aString.length(); i++) {
			   if (sb.charAt(i) != '0'){
				   newSb.append(sb.substring(i));
				   break;
			   }
		   }
		   return newSb.toString();
	   }

	public ActionForward updateTransferHistoryConfirm(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		SuperviseeTransferCasesInfo superviseeTransferCasesInfo = superviseeForm.getTransferCasesInfo();

		if (!superviseeTransferCasesInfo.getTransferStateId().equals("")){
			ICode aCode = CodeHelper.getCode(PDCodeTableConstants.STATE_ABBR, superviseeTransferCasesInfo.getTransferStateId());
			if (aCode != null){
				superviseeTransferCasesInfo.setTransferStateName(aCode.getDescription());
				superviseeTransferCasesInfo.setTransferTxCountyName("");
			}
		} else {
			ICode aCode = CodeHelper.getCode(PDCodeTableConstants.COUNTY, superviseeTransferCasesInfo.getTransferTxCountyId());
			if (aCode != null){
				superviseeTransferCasesInfo.setTransferTxCountyName(aCode.getDescription());
				superviseeTransferCasesInfo.setTransferStateName("");
			}
		}
		if (superviseeTransferCasesInfo.getRejectedAsStr().equals("Y")){
			superviseeTransferCasesInfo.setRejected(true);
		} else {
			superviseeTransferCasesInfo.setRejected(false);
		}

		return aMapping.findForward("updateTransferCaseHistoryConfirmation");
	}
	
	private CaseAssignmentForm initializeCaseAssignmentHeader(ActionMapping aMapping, HttpServletRequest aRequest) {
		SuperviseeHeaderForm superviseeHeaderForm = getSuperviseeHeader(aMapping, aRequest);		
		CaseAssignmentForm caseAssignmentForm = null;
		try {
			String formName = "caseAssignmentForm";
			boolean createFrom = true;
			caseAssignmentForm = (CaseAssignmentForm) this.getSessionForm(aMapping, aRequest, formName, createFrom);
			caseAssignmentForm.clear();
			caseAssignmentForm.setSuperviseeNameStr(superviseeHeaderForm.getSuperviseeNameDesc());
			caseAssignmentForm.setDefendantId(superviseeHeaderForm.getSuperviseeId());
			caseAssignmentForm.setOfficerNameStr(superviseeHeaderForm.getOfficerNameDesc());
			caseAssignmentForm.setLevelOfSupervision(superviseeHeaderForm.getLOSDesc());
			//todo:			caseAssignmentForm.setOffenseDesc(offenseDesc);
			caseAssignmentForm.setProgramUnitName(superviseeHeaderForm.getProgramUnitDesc());
			
		} catch (GeneralFeedbackMessageException e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
		return caseAssignmentForm;
	}
	
	private SuperviseeHeaderForm getSuperviseeHeader(ActionMapping aMapping, HttpServletRequest aRequest) {
		try {
			String formName = "superviseeHeaderForm";
			boolean createFrom = true;
			SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm) this.getSessionForm(aMapping, aRequest, formName, createFrom);
			return superviseeHeaderForm;
		} catch (GeneralFeedbackMessageException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
