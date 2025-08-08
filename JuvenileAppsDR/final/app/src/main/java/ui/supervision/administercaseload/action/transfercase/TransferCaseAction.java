package ui.supervision.administercaseload.action.transfercase;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.codetable.reply.ICode;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SuperviseeControllerServiceNames;
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

public class TransferCaseAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "setUp");
		keyMap.put("button.next", "transferCase");
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
	private String getCodeDescription(String codeTableName, String code, boolean stripCode){
		if (stripCode){
			code = this.stripLeadingZeroes(code);
		}
		ICode aCode = CodeHelper.getCode(codeTableName, code);
		String description = "";
		if (aCode != null){
			description = aCode.getDescription();
		}
		return description;
	}
	private static final String OUT_OF_COUNTY = "010";
	public ActionForward setUp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		SuperviseeTransferCasesInfo superviseeTransferCases = caseAssignmentForm.getSuperviseeTransferCases();
		//superviseeTransferCases.setCasesToTransfer(caseAssignmentForm.getActiveCases());
		
		String superviseeId = caseAssignmentForm.getDefendantId();
		TransferCasesService service = TransferCasesService.getInstance();
		
		List harrisCountyCases = service.getHarrisCountyTransferCases(superviseeId);			
		List courtesyCases = service.getOutOfCountyTransferCases(superviseeId);
		if (harrisCountyCases != null && harrisCountyCases.size() > 0) {
			int size = harrisCountyCases.size();
			for (int s = 0; s < size; s++){
				TransferResponseEvent hcCase = (TransferResponseEvent) harrisCountyCases.get(s);
				//if (hcCase.getCaseNum().equals(object))
				superviseeTransferCases.setTransferInDate(hcCase.getHcTransferInDate());
			}
		}
		superviseeTransferCases.setHarrisCountyCases(harrisCountyCases);
		superviseeTransferCases.setCourtesyCases(courtesyCases);
		
		//Retrieve previous transfer info.
		GetTransfersEvent request = (GetTransfersEvent)EventFactory.getInstance(SuperviseeControllerServiceNames.GETTRANSFERS);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		request.setSuperviseeId(superviseeId);
		request.setSearchType("C");
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		List transfers = MessageUtil.compositeToList(compositeResponse, TransferResponseEvent.class);
		String transferType = superviseeTransferCases.getTransferType();		
		boolean caseFound = false;
		
		if (transfers != null && transfers.size() > 0){
			for (int i = 0; i < transfers.size(); i++) {
				TransferResponseEvent tre = (TransferResponseEvent) transfers.get(i);
				CaseAssignmentTO caseAssignment = (CaseAssignmentTO) superviseeTransferCases.getCasesToTransfer().get(0);
				String transferCaseNum = caseAssignment.getCriminalCaseId().substring(3);
				if (tre.getCaseNum().equals(transferCaseNum)){
					if (tre.getCdi().equals(OUT_OF_COUNTY)){
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Out of County case cannot be transferred"));		
						saveErrors(aRequest, errors);
						return aMapping.findForward("failure");
					}
					caseFound = true;
					if (transferType.equalsIgnoreCase(UIConstants.CS_TRANSFER_IN_CASES) ) {
						if (tre.getHcTransferInDate() != null && !tre.getHcTransferInDate().trim().equals("")){
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Case already transferred in"));		
							saveErrors(aRequest, errors);
							return aMapping.findForward("failure");
						} else {
							superviseeTransferCases.setPersonId("");
							String countyStateId = tre.getReceivingCountyState();
							String description = null;
							if (countyStateId.length() == 2){
								description = this.getCodeDescription(PDCodeTableConstants.STATE_ABBR, countyStateId, false);
								superviseeTransferCases.setTransferStateId(countyStateId);
								superviseeTransferCases.setTransferStateName(description);
								superviseeTransferCases.setTransferTxCountyId("");
								superviseeTransferCases.setTransferTxCountyName("");

							} else {
								
								description = this.getCodeDescription(PDCodeTableConstants.COUNTY, countyStateId, true);
								superviseeTransferCases.setTransferTxCountyId(countyStateId);
								superviseeTransferCases.setTransferTxCountyName(description);
								superviseeTransferCases.setTransferStateId("");
								superviseeTransferCases.setTransferStateName("");
							}
							countyStateId = null;
							description = null;
						}
					} else {
						if (tre.getHcTransferOutDate() != null && !tre.getHcTransferOutDate().trim().equals("")){
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Case already transferred out " + tre.getCriminalCaseId()));		
							saveErrors(aRequest, errors);
							return aMapping.findForward("failure");
						} else {
							superviseeTransferCases.setPersonId("");
							String countyStateId = tre.getTransferringCountyState().trim();
							String description = null;
							if (countyStateId.length() == 2){
								description = this.getCodeDescription(PDCodeTableConstants.STATE_ABBR, countyStateId, false);
								superviseeTransferCases.setTransferStateId(countyStateId);
								superviseeTransferCases.setTransferStateName(description);
								superviseeTransferCases.setTransferTxCountyId("");
								superviseeTransferCases.setTransferTxCountyName("");

							} else {
								description = this.getCodeDescription(PDCodeTableConstants.COUNTY, countyStateId, true);
								superviseeTransferCases.setTransferTxCountyId(countyStateId);
								superviseeTransferCases.setTransferTxCountyName(description);
								superviseeTransferCases.setTransferStateId("");
								superviseeTransferCases.setTransferStateName("");
							}
							countyStateId = null;
							description = null;
						}			
					}
					break;
				}
			}
		} 
		// set local variables to null
		superviseeId = null;
		service = null;
		harrisCountyCases = null;			
		courtesyCases = null;
		request = null;
		dispatch = null;
		compositeResponse = null;
		transfers = null;
		if (!caseFound && transferType.equalsIgnoreCase(UIConstants.CS_TRANSFER_IN_CASES)){
			String errorMsg = "Transfer in not allowed because case is not currently transferred out";
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errorMsg));		
			saveErrors(aRequest, errors);
			transferType = null;
			return aMapping.findForward("failure");
		}
		transferType = null;
		
		return aMapping.findForward("initialSetup");
	}

	public ActionForward transferCase(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		
		SuperviseeTransferCasesInfo transferInfo = caseAssignmentForm.getSuperviseeTransferCases();
		if (transferInfo.getRejectedAsStr().equals("Y")){
			transferInfo.setRejected(true);
		}
		if (transferInfo.getTransferStateId().equals("")){
			transferInfo.setTransferStateName("");
		}
		if (transferInfo.getTransferTxCountyId().equals("")){
			transferInfo.setTransferTxCountyName("");
		}

		caseAssignmentForm.setSuperviseeTransferCases(transferInfo);
		
		GetTransfersEvent request = (GetTransfersEvent)EventFactory.getInstance(SuperviseeControllerServiceNames.GETTRANSFERS);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		request.setSuperviseeId(caseAssignmentForm.getDefendantId());
		request.setSearchType("C");
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		List transfers = MessageUtil.compositeToList(compositeResponse, TransferResponseEvent.class);
		if (transfers.size() < 1 ){
			transfers = caseAssignmentForm.getSuperviseeTransferCases().getHarrisCountyCases();
		}
	
	    if (transfers != null) {
			Iterator it = transfers.iterator();
			String cdiCase = "";
			String compDate1 = "";
			String compDate2 = "";
			String errorMsg = "";
			String transferType = caseAssignmentForm.getSuperviseeTransferCases().getTransferType();
			if (transferType.equalsIgnoreCase(UIConstants.CS_TRANSFER_IN_CASES) ) {
				compDate1 = convertDateStrToYYYYMMDD(caseAssignmentForm.getSuperviseeTransferCases().getTransferInDate());
			} else {
				compDate1 = convertDateStrToYYYYMMDD(caseAssignmentForm.getSuperviseeTransferCases().getTransferOutDate());
			}
			if (it != null && it.hasNext()){
				while (it.hasNext()){
					TransferResponseEvent evt = (TransferResponseEvent)it.next();
					// compare transfer dates				
					cdiCase = evt.getCdi() + evt.getCaseNum();
					Iterator ctt = caseAssignmentForm.getSuperviseeTransferCases().getCasesToTransfer().iterator();
					while (ctt.hasNext()){
						CaseAssignmentTO cat = (CaseAssignmentTO) ctt.next();
						if (cdiCase.equalsIgnoreCase(cat.getCriminalCaseId())) {
							if (evt.getCdi().trim().equals(OUT_OF_COUNTY)){
								ActionErrors errors = new ActionErrors();
								errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Out of County case cannot be transferred"));		
								saveErrors(aRequest, errors);
								return aMapping.findForward("failure");
							}
							if (transferType.equalsIgnoreCase(UIConstants.CS_TRANSFER_IN_CASES)){	
								if (evt.getHcTransferInDate() != null && !evt.getHcTransferInDate().trim().equals("")){
									ActionErrors errors = new ActionErrors();
									errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Case already transferred in"));		
									saveErrors(aRequest, errors);
									return aMapping.findForward("failure");
								}							
								compDate2 = convertDateStrToYYYYMMDD(evt.getHcTransferOutDate());
								if (compDate1.compareTo(compDate2) < 0){
									errorMsg = "Transfer in date is previous to transfer out date " + evt.getHcTransferOutDate() + " on case " + cdiCase;
									ActionErrors errors = new ActionErrors();
									errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errorMsg));		
									saveErrors(aRequest, errors);
									return aMapping.findForward("failure");
								}
							}
							if (transferType.equalsIgnoreCase(UIConstants.CS_TRANSFER_OUT_CASES)){	
								if (evt.getHcTransferInDate() == null && evt.getHcTransferOutDate().trim().equals("")){
									if (evt.getHcTransferOutDate() != null && !evt.getHcTransferOutDate().trim().equals("")){
										ActionErrors errors = new ActionErrors();
										errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Case already transferred out"));		
										saveErrors(aRequest, errors);
										return aMapping.findForward("failure");
									}	
								}
								compDate2 = convertDateStrToYYYYMMDD(evt.getHcTransferInDate());
								if (compDate1.compareTo(compDate2) < 0){	 
									errorMsg = "Transfer out date is previous to transfer in date " + evt.getHcTransferInDate() + " on case " + cdiCase;
									ActionErrors errors = new ActionErrors();
									errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errorMsg));		
									saveErrors(aRequest, errors);
									return aMapping.findForward("failure");
								}
								if (!evt.caseHasActiveSupervisionOrder()){
									errorMsg = "Transfer out not allowed.  No active supervision order on case " + cdiCase;
									ActionErrors errors = new ActionErrors();
									errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errorMsg));		
									saveErrors(aRequest, errors);
									return aMapping.findForward("failure");
								}
							}
						}
					}
				}
			} else if (transferType.equalsIgnoreCase(UIConstants.CS_TRANSFER_IN_CASES)){
				errorMsg = "Transfer in not allowed when case was not transferred out "; 
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",errorMsg));		
				saveErrors(aRequest, errors);
				return aMapping.findForward("failure");
			}
		}
		return aMapping.findForward("transferCaseSummary");
	}
	
	   /**
     * @param date string mm/dd/yyyy
     * @return date string yyyymmdd
     */
	private String convertDateStrToYYYYMMDD(String inDate){
		String outDate = "";
		String [] temp = inDate.split("/");
		outDate = temp[2] + temp[0] + temp[1];
		return outDate;
	}	
}
