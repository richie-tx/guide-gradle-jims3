//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;
import ui.supervision.posttrial.form.CSCDTaskForm;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supplementalDocuments.form.SupplementalDocumentsForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class HandleActiveCasesSelectionAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public HandleActiveCasesSelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.createTask", "createTask");
		keyMap.put("button.supplementalReports", "supplementalDocuments");
		keyMap.put("button.cancel", "cancel");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward createTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CSCDTaskForm ctForm = (CSCDTaskForm) getSessionForm(aMapping, aRequest, "cscdTaskForm", true);
		ctForm.setSearchById("");
		SuperviseeSearchForm sForm = (SuperviseeSearchForm) aForm;
		
		//Prefill form from selected case or free fill at the end??
		ctForm.setSpn(sForm.getSpn());
		ctForm.setOfficerName("");
		String criminalCaseId = sForm.getSelectedCaseValue();
		if ( criminalCaseId.length() > 10 ){
			
			ctForm.setTaskCdi( criminalCaseId.substring(0, 3) );
			ctForm.setTaskCaseNumber( criminalCaseId.substring(3) );
		}		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
	 */
	public ActionForward supplementalDocuments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SupplementalDocumentsForm suppDocForm =(SupplementalDocumentsForm)getSessionForm(aMapping, aRequest, "supplementalDocumentsForm", true);
		SuperviseeSearchForm sForm = (SuperviseeSearchForm) aForm;
		
		//Prefill form from selected case or free fill at the end??
		if ( StringUtils.isNotEmpty( sForm.getSpn() ) ) {
			suppDocForm.setSpn(sForm.getSpn());
		}
		if ( StringUtils.isNotEmpty( sForm.getSelectedCaseValue() ) ) {
			String criminalCaseId = sForm.getSelectedCaseValue();
			suppDocForm.setCdi(criminalCaseId.substring(0, 3));
			suppDocForm.setCaseNumber( criminalCaseId.substring(3) );
			if ( !sForm.getActiveCases().isEmpty() ) {
				List activeCases = (List) sForm.getActiveCases();
				CaseAssignmentTO activeCase = null;
				for ( Iterator caseIterator = activeCases.iterator(); caseIterator.hasNext(); ) {
		        	activeCase = (CaseAssignmentTO) caseIterator.next();
		        	//set assigned officer name.
		        	if ( activeCase.getCriminalCaseId() != null && activeCase.getCriminalCaseId().equals(criminalCaseId) ) {
		        		if ( StringUtils.isNotEmpty( activeCase.getCourtId() ) ) {
		        			suppDocForm.setCourt(activeCase.getCourtId());
		        		}
		        		if ( StringUtils.isNotEmpty( activeCase.getAssignedStaffPositionName() ) ) {
		        			suppDocForm.setPoi(activeCase.getAssignedStaffPositionName());
		        		}
		        		if ( !activeCase.getOfficerName().equals(null) )  {
		        			suppDocForm.setOfficer(activeCase.getOfficerName().toString());
		        		}
		        	}
		        }
			}
		}
		
// forward to DisplaySupplementalDocumentListAction	
		return aMapping.findForward(UIConstants.SUPP_REPORT_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse){
		
	
		SuperviseeForm superviseeForm = null;
		try {
			superviseeForm = (SuperviseeForm) 
						getSessionForm(aMapping, aRequest, "superviseeForm", true);
		} catch (GeneralFeedbackMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        String forwardStr = UIConstants.CANCEL;
        if (superviseeForm.getFromPage() != null){
             if (superviseeForm.getFromPage().equalsIgnoreCase(UIConstants.FROM_CASELOAD)){
                    forwardStr = UIConstants.CANCEL_CASELOAD;
            }
        }
        return aMapping.findForward(forwardStr); 

	}	
}
