// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\DisplayComplianceConditionSelectAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administercompliance.reply.LikeComplianceConditionResponseEvent;
import messaging.administercompliance.GetComplianceConditionsEvent;
import messaging.supervisionorder.GetSupervisionPeriodForComplianceEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceSuperviseeInfoForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayComplianceConditionSelectAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayComplianceConditionSelectAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.casenotes", "casenotes");
		keyMap.put("button.advancedSuperviseeSearch", "casenotesSuperviseeSearch");

	}

	public ActionForward casenotesSuperviseeSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		// set up what is necessary to be abel to to go back to casenotes
		// journal view
		ActionForward myForward = link(aMapping, aForm, aRequest, aResponse);
		compForm.setSecondaryAction(UIAdministerComplianceHelper.CASENOTE_SUPERVISEE_SEARCH);
		return myForward;
	}

	public ActionForward casenotes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		// set up what is necessary to be abel to to go back to casenotes
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
		ComplianceForm compForm = (ComplianceForm) getSessionForm(aMapping, aRequest, "complianceForm", true);
		getSessionForm(aMapping, aRequest, "superviseeHeaderForm", true); //loading the supervisee header form so that the jsp page will have it
		
		compForm.clear();
		compForm.setAction("");
		compForm.setSecondaryAction("");
		ComplianceSuperviseeInfoForm compSupForm = (ComplianceSuperviseeInfoForm) getSessionForm(aMapping, aRequest,
				"complianceSuperviseeInfoForm", true);
		compSupForm.clear();
		String spnStr = aRequest.getParameter("superviseeId");
		aRequest.setAttribute("superviseeId", "");
		if (spnStr == null || spnStr.equals("")) {
			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);			
			spnStr = myHeaderForm.getSuperviseeSpn();
		}	
		if (spnStr == null || spnStr.equals("")) { 
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Not able to find supervisee information."));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SUCCESS);
	    } 
		compSupForm.setSuperviseeCompliant(false);
		// spn, defendantId and superviseeId are all same value

		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
		compSupForm.setSuperviseeSPN(spnStr);
		setSuperviseeHeaderInfo(compSupForm);
		compForm.setDefendantId(spnStr);
		compForm.setSuperviseeId(spnStr);
		String agencyId = SecurityUIHelper.getUserAgencyId();
		compForm.setAgencyId(agencyId);
//		compForm.setShowDecrement(UIAdministerComplianceHelper.isDecrementAllowed());
		compForm.setSupervisionPeriodBeginDateAsString(getSupervisionBeginDate(spnStr));
//		boolean sort = true;
		compForm.setContactMethodList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_METHOD));
		compForm.setCasenoteSubjectList(ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.CASENOTE_SUBJECT));
		compForm.setCasenoteTypeList(ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.CASENOTE_TYPE));
		compForm.setCollateralList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CONTACT_WITH));
		compForm.setGeneratedByList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.HOW_GENERATED));
		compForm.setSearchByList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CASENOTE_SEARCHBY));

		GetComplianceConditionsEvent compEvent = (GetComplianceConditionsEvent) EventFactory
				.getInstance(ComplianceControllerServiceNames.GETCOMPLIANCECONDITIONS);
		compEvent.setDefendantId(compForm.getDefendantId());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(compEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

		List orderConditions = MessageUtil.compositeToList(compositeResponse, ComplianceConditionResponseEvent.class);
		List likeOrderConditions = MessageUtil.compositeToList(compositeResponse,
				LikeComplianceConditionResponseEvent.class);

		if ((orderConditions == null || orderConditions.isEmpty())
				&& (likeOrderConditions == null || likeOrderConditions.isEmpty())) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
					"No conditions found for this supervisee"));
			saveErrors(aRequest, errors);
		} else {
			UIAdministerComplianceHelper.loadComplianceFormDropDowns(compForm);
			UIAdministerComplianceHelper
					.loadComplianceFormConditionInfo(compForm, orderConditions, likeOrderConditions);
			compSupForm.setSuperviseeCompliant(UIAdministerComplianceHelper.getSuperviseeComplianceStatus(
					orderConditions, likeOrderConditions));
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param superviseeId
	 * @return
	 */
	private String getSupervisionBeginDate(String defendantId) {
		String sprBeginDate = "";

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetSupervisionPeriodForComplianceEvent requestEvent = (GetSupervisionPeriodForComplianceEvent) EventFactory
				.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISIONPERIODFORCOMPLIANCE);

		requestEvent.setDefendantId(defendantId);
		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response, SupervisionPeriodResponseEvent.class);
		if (coll != null && !coll.isEmpty()) {
			Iterator spreIter = coll.iterator();
			while (spreIter.hasNext()) {
				SupervisionPeriodResponseEvent sprEvent = (SupervisionPeriodResponseEvent) spreIter.next();
				if (sprEvent.getSupervisionBeginDate() != null) {
					sprBeginDate = DateUtil.dateToString(sprEvent.getSupervisionBeginDate(), DateUtil.DATETIME24_FMT_1)
							+ ":00";
				}
			}
		}
		if ("".equals(sprBeginDate)){
			sprBeginDate = DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATETIME24_FMT_1) + ":00";
		}
		return sprBeginDate;
	}

	/**
	 * @param form
	 * @param superviseeId
	 * @return
	 */
	private void setSuperviseeHeaderInfo(ComplianceSuperviseeInfoForm compSupForm) {
		GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
		getEvent.setDefendantId(compSupForm.getSuperviseeSPN());
		SuperviseeInfoResponseEvent sprResponse = (SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent,
				SuperviseeInfoResponseEvent.class);
		if (sprResponse != null) {
			compSupForm.setLevelOfSupervision(sprResponse.getSupervisionLevel());
			compSupForm.setOfficerName(sprResponse.getOfficerName());
			compSupForm.setProgramUnit(sprResponse.getProgramUnit());
			compSupForm.setSuperviseeName(sprResponse.getDefendantName());
		}
	}
}