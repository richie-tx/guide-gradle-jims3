//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.administercompliance.administercasehistory.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercompliance.administercasehistory.UICaseHistoryHelper;
import ui.supervision.administercompliance.administercasehistory.form.CaseHistoryForm;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;
//import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceSuperviseeInfoForm;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceSuperviseeInfoForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayCaseHistoryListAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayCaseHistoryListAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.casenotes", "casenotes");
		keyMap.put("button.advancedSuperviseeSearch", "casenotesSuperviseeSearch");

	}

	public ActionForward casenotesSuperviseeSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		// set up what is necessary to be able to to go back to casenotes
		// journal view
		ActionForward myForward = link(aMapping, aForm, aRequest, aResponse);
		compForm.setSecondaryAction(UIAdministerComplianceHelper.CASENOTE_SUPERVISEE_SEARCH);
		return myForward;
	}

	public ActionForward casenotes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		// set up what is necessary to be able to to go back to casenotes
		// journal view
		ActionForward myForward = link(aMapping, aForm, aRequest, aResponse);
		compForm.setSecondaryAction(UIAdministerComplianceHelper.CASENOTE_JOURNAL);
		return myForward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		String forward = UIConstants.BACK;
		ComplianceForm compForm = (ComplianceForm) aForm;
		if (compForm.getSecondaryAction() != null
				&& UIAdministerComplianceHelper.CASENOTE_JOURNAL.equals(compForm.getSecondaryAction())) {
			forward = "failureCasenotesJournal";
		} else if (compForm.getSecondaryAction() != null
				&& UIAdministerComplianceHelper.CASENOTE_SUPERVISEE_SEARCH.equals(compForm.getSecondaryAction())) {
			forward = "failureCasenotesSearch";
		}
		return aMapping.findForward(forward);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		String forward = UIConstants.CANCEL;
		ComplianceForm compForm = (ComplianceForm) aForm;
		if (compForm.getSecondaryAction() != null
				&& UIAdministerComplianceHelper.CASENOTE_JOURNAL.equals(compForm.getSecondaryAction())) {
			forward = "failureCasenotesJournal";
		} else if (compForm.getSecondaryAction() != null
				&& UIAdministerComplianceHelper.CASENOTE_SUPERVISEE_SEARCH.equals(compForm.getSecondaryAction())) {
			forward = "failureCasenotesSearch";
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseHistoryForm chForm = (CaseHistoryForm) getSessionForm(aMapping, aRequest, "caseHistoryForm", true);
		SuperviseeHeaderForm shForm = (SuperviseeHeaderForm) getSessionForm(aMapping, aRequest, "superviseeHeaderForm", true); //loading the supervisee header form so that the jsp page will have it
		chForm.clear();
		chForm.setAction("");
		chForm.setSecondaryAction("");
		chForm.setAllowUpdates(false);
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		Set userFeatures = securityManager.getFeatures(); 
		if (userFeatures.contains("CSCD-CASE-SUM-CREATE") || userFeatures.contains("CSCD-VIOL-RPT-CREATE") ) {
			chForm.setAllowUpdates(true);
		}		

		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		String spnStr=(String)aRequest.getParameter("superviseeId"); // value set in tab
		if (spnStr == null || spnStr.equals("")) {
			spnStr = (String)aRequest.getAttribute("superviseeId"); // value set on confirmation page
		}	
		if (spnStr == null || spnStr.equals("")) {
// access form compliance flow			
			ComplianceSuperviseeInfoForm csiForm = (ComplianceSuperviseeInfoForm) getSessionForm(aMapping, aRequest, "complianceSuperviseeInfoForm", true);
			if (csiForm != null){
				spnStr = csiForm.getSuperviseeSPN();
				shForm.setSuperviseeNameDesc(csiForm.getSuperviseeName());
				shForm.setSuperviseeSpn(spnStr);
				shForm.setOfficerNameDesc(csiForm.getOfficerName());
				shForm.setLOSDesc(csiForm.getLevelOfSupervision());
				shForm.setProgramUnitDesc(csiForm.getProgramUnit());
				shForm.setCompliant(csiForm.isSuperviseeCompliant());
			}
		}	
		if (spnStr == null || spnStr.equals("")) {			
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Not able to find supervisee information"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SUCCESS);
		}

// spn, defendantId and superviseeId are all same value
		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
// reload value in case value has changed		
		shForm.setCompliant(UICommonSupervisionHelper.isSuperviseeCompliant(spnStr));
		chForm.setSuperviseeId(spnStr);
		GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
        event.setSuperviseeId(spnStr);

        List cases = MessageUtil.postRequestListFilter(event, SuperviseeCaseOrderResponseEvent.class);
		if (cases == null || cases.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No cases found for this supervisee"));
			saveErrors(aRequest, errors);
		} else {
// remove CDI value from case number and court type from case number for correct display value	
			chForm.setCaseHistoryList(UICaseHistoryHelper.formatEventInfo(cases));
		} 
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param caseHistoryList
	 * @return List
	 */
	public static List sortCaseHistoryList(Collection caseList){
		Iterator iter = caseList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter.next();	
			map.put(score.getCdi() + score.getCaseNumber() + score.getSupervisionOrderId(), score);
		}
		return new ArrayList(map.values());
	} 	
}