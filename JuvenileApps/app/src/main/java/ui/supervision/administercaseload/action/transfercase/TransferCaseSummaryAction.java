package ui.supervision.administercaseload.action.transfercase;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.codetable.reply.ICode;
import messaging.transfers.reply.TransferResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;
import ui.supervision.administercaseload.helper.TransferCasesService;

public class TransferCaseSummaryAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setUp");
		keyMap.put("button.finish", "transferComplete");
	}
	
	public ActionForward setUp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		SuperviseeTransferCasesInfo transferInfo = caseAssignmentForm.getSuperviseeTransferCases();	
		boolean validTransfer = validateTransfer(caseAssignmentForm, aRequest);
		if (!validTransfer) {
			return aMapping.findForward("failure");
		}

		String transferCountyId = transferInfo.getTransferTxCountyId();
		String transferStateId = transferInfo.getTransferStateId();

		if (transferCountyId != null && !transferCountyId.trim().equals("")){
			ICode aCode =CodeHelper.getCode(PDCodeTableConstants.COUNTY, this.stripLeadingZeroes(transferCountyId.trim()));
			transferInfo.setTransferTxCountyName(aCode.getDescription());
		}
		if (transferStateId != null && !transferStateId.trim().equals("")){
			ICode aCode =CodeHelper.getCode(PDCodeTableConstants.STATE_ABBR, transferStateId.trim());
			transferInfo.setTransferStateName(aCode.getDescription());
		}
		if (transferInfo.getRejectedAsStr().equals("Y")){
			transferInfo.setRejected(true);
		} else {
			transferInfo.setRejected(false);
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

	public ActionForward transferComplete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		boolean validTransfer = validateTransfer(caseAssignmentForm, aRequest);
		if (!validTransfer) {
			return aMapping.findForward("failure");
		}
		SuperviseeTransferCasesInfo transferInfo = caseAssignmentForm.getSuperviseeTransferCases();		
		TransferCasesService service = TransferCasesService.getInstance();
		service.transferSuperviseeCases(transferInfo);
		return aMapping.findForward("transferCaseConfirmation");
	}	
	
	private boolean validateTransfer(CaseAssignmentForm caseAssignmentForm, HttpServletRequest aRequest) {
		boolean validTransfer = true;
		SuperviseeTransferCasesInfo transferInfo = caseAssignmentForm.getSuperviseeTransferCases();		
		String transferType = transferInfo.getTransferType(); 
		if (transferType == UIConstants.CS_TRANSFER_OUT_CASES) { 
			validTransfer = validateTransferOut(caseAssignmentForm, aRequest);
		} else if (transferType == UIConstants.CS_TRANSFER_IN_CASES) {
			validTransfer = validateTransferIn(caseAssignmentForm, aRequest);
		} else {
			validTransfer = false;
		}
		return validTransfer;
	}
	
	/**
	 * Only Harris county cases can be transfered out. Out of county cases cannot be
	 * transfered out.
	 * @return
	 */
	private boolean validateTransferOut(CaseAssignmentForm caseAssignmentForm, HttpServletRequest aRequest) {
		boolean validTransfer = true;
		String defendantId = caseAssignmentForm.getDefendantId();
		SuperviseeTransferCasesInfo transferInfo = caseAssignmentForm.getSuperviseeTransferCases();	
		if (transferInfo.getTransferTxCountyId() != null && transferInfo.getTransferTxCountyId().equals("101")) {//cannot transfer out to Harris County
			validTransfer = false;
			//sendToErrorPage(aRequest, "error.generic", "The cases cannot be transferred out to Harris County.");
			/* ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("The cases cannot be transferred out to Harris County."));		
			saveErrors(aRequest, errors); */
			caseAssignmentForm.setErrorMsg("The cases cannot be transferred out to Harris County");
		} else {
			List casesToTransfer = transferInfo.getCasesToTransfer();
			TransferCasesService services = TransferCasesService.getInstance();
			List transferHistory = services.getHarrisCountyTransferCases(defendantId, CURRENT_TRANSFER_INFO);
			if (transferHistory == null || transferHistory.size() == 0) {  
				//means none of the cases for this defendant have been transfered.
				validTransfer = true;
			} else {
				for (Iterator transferHistoryIterator = transferHistory.iterator(); transferHistoryIterator.hasNext();) {
					TransferResponseEvent responseEvent = (TransferResponseEvent)transferHistoryIterator.next();
					String caseNum = responseEvent.getCaseNum();
					for (Iterator transferCasesIterator = casesToTransfer.iterator(); transferCasesIterator.hasNext();) {
						ICaseAssignment caseAssignment = (ICaseAssignment) transferCasesIterator.next();
						String criminalCaseId = caseAssignment.getCriminalCaseId();
						if (caseNum.equals(criminalCaseId)) {//previous transfer record found
							String transferInDate = responseEvent.getHcTransferInDate();
							if (transferInDate == null || !transferInDate.equals("")) { //case already transfered out, no corresponding transfer in record
								validTransfer = false;
								/* ActionErrors errors = new ActionErrors();
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("The case# " + criminalCaseId + " cannot be transferred out."));		
								saveErrors(aRequest, errors);*/
								//sendToErrorPage(aRequest, "error.generic", "The case# " + criminalCaseId + " cannot be transferred out.");
								caseAssignmentForm.setErrorMsg("The case# " + criminalCaseId + " cannot be transferred out.");
								break;
							}
						}
					}
					if (validTransfer == false) {
						break;
					}
				}			
			}			
		}
		return validTransfer;
	}
	private static final String CURRENT_TRANSFER_INFO = "C";
	/**
	 * Harris county cases that have been already transfered out can only be tranfereed in.
	 * @return
	 */
	private boolean validateTransferIn(CaseAssignmentForm caseAssignmentForm, HttpServletRequest aRequest) {
		boolean validTransfer = true;
		String defendantId = caseAssignmentForm.getDefendantId();
		List casesToTransfer = caseAssignmentForm.getSuperviseeTransferCases().getCasesToTransfer();
		//Need to get current transfer records only.
		TransferCasesService services = TransferCasesService.getInstance();
		List transferHistory = services.getHarrisCountyTransferCases(defendantId, CURRENT_TRANSFER_INFO);
		if (transferHistory == null || transferHistory.size() == 0) {  
			//means none of the cases for this defendant have been transfered out,
			//so no transfer  in is possible.
			/* ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("The case(s) cannot be transferred in - No cases transferred out for the defendant"));		
			saveErrors(aRequest, errors);*/
			caseAssignmentForm.setErrorMsg("The case(s) cannot be transferred in - No cases transferred out for the defendant");
			validTransfer = false;
		} else {
			for (Iterator transferHistoryIterator = transferHistory.iterator(); transferHistoryIterator.hasNext();) {
				TransferResponseEvent responseEvent = (TransferResponseEvent)transferHistoryIterator.next();
				String caseNum = responseEvent.getCaseNum();
				for (Iterator transferCasesIterator = casesToTransfer.iterator(); transferCasesIterator.hasNext();) {
					ICaseAssignment caseAssignment = (ICaseAssignment) transferCasesIterator.next();
					String criminalCaseId = caseAssignment.getCriminalCaseId();
					if (caseNum.equals(criminalCaseId)) {//previous transfer record found
						String transferInDate = responseEvent.getHcTransferInDate();
						if (transferInDate != null && !transferInDate.equals("")) { //case has been already transfered in.
							validTransfer = false;
							//sendToErrorPage(aRequest, "error.generic", "The case# " + criminalCaseId + " cannot be transfered in.");
							/* ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("The case# " + criminalCaseId + " cannot be transferred in"));		
							saveErrors(aRequest, errors); */
							caseAssignmentForm.setErrorMsg("The case# " + criminalCaseId + " cannot be transferred in");
							break;
						}
					}
				}
				if (validTransfer == false) {
					break;
				}
			}			
		}
		return validTransfer;		
	}	
	
}
