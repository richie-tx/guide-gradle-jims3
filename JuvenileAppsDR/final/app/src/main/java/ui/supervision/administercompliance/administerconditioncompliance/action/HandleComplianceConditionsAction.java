// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\HandleComplianceConditionsAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.CreateNonCompliantEventEvent;
import messaging.administercompliance.CreateNonCompliantLikeEventEvent;
import messaging.administercompliance.GetConditionNonCompliantEventTypesEvent;
import messaging.administercompliance.GetNonCompliantEventsEvent;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administercompliance.reply.LikeComplianceConditionResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventTypeCodeResponseEvent;
import messaging.supervisionoptions.GetCourtPolicyDetailsEvent;
import messaging.supervisionoptions.reply.CourtPolicyDetailResponseEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import messaging.supervisionoptions.reply.OrderConditionAgencyPolicyResponseEvent;
import messaging.supervisionorder.GetSprOrderConditionLiteralEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceCasenoteHelper;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

public class HandleComplianceConditionsAction extends JIMSBaseAction {

	/**
	 * @roseuid 464368F103D5
	 */
	public HandleComplianceConditionsAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.resolveNoncompliance", "resolveNonComp");
		keyMap.put("button.setToNoncompliant", "setToNonCompliant");
		keyMap.put("button.casenotes", "casenotes");
		keyMap.put("button.createConditionCasenote", "createCondCasenote");
		keyMap.put("button.filter", "filter");
		keyMap.put("button.viewAll", "viewAll");
		keyMap.put("button.go", "decrementCondition");
		keyMap.put("button.addAssociate", "associateAdded" );
		// mapping for popup hyperlinks
		keyMap.put("button.info", "casenotesPopUp");
		keyMap.put("button.link", "conditionPopUp");
		keyMap.put("button.details", "courtPolicyPopUp");
		keyMap.put("button.view", "departmentPolicyPopUp");

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward resolveNonComp(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		String forward = UIConstants.CANCEL;
		List selectedConditionEvents = this.obtainSelectedConditions(compForm.getSelectedConditionIds(), compForm
				.getLikeConditions(), compForm.getUniqueConditions(), false);
		if (selectedConditionEvents.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
					"No Noncompliant condition found to resolve"));
			saveErrors(aRequest, errors);

		} else {
			compForm.setSelectedConditions(selectedConditionEvents);
			forward = UIConstants.RESOLVE_NONCOMPLIANT_SUCCESS;
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
	public ActionForward setToNonCompliant(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		String forward = UIConstants.CANCEL;
/**	compliant state not needed as of 6/6/08 - defect#50547 */	
		List uniqueConditionEvents = this.obtainSelectedUniqueConditions(compForm.getSelectedConditionIds(), compForm
				.getUniqueConditions(), true);
		List likeConditionEvents = this.obtainSelectedLikeConditions(compForm.getSelectedConditionIds(), compForm
				.getLikeConditions(), true);
		if (uniqueConditionEvents.isEmpty() && likeConditionEvents.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
					"No condition found to set to nonCompliant"));
			saveErrors(aRequest, errors);			
		} else {
			compForm.setSelectedUniqueConditionsEvents(this.setCompliantEvents(uniqueConditionEvents));
			compForm.setSelectedLikeConditionsEvents(this.setLikeCompliantEvents(likeConditionEvents));
			forward = UIConstants.SET_NONCOMPLIANT_SUCCESS;
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
	public ActionForward casenotes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) getSessionForm(aMapping, aRequest, "superviseeSearchForm", true);
		myForm.setSpn(compForm.getDefendantId());
		myForm.setSelectedValue(UIConstants.BASIC);
		return aMapping.findForward(UIConstants.CASENOTES_SUCCESS);
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
	public ActionForward createCondCasenote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		compForm.clearCasenoteInputs();
		compForm.setCompliancePage(UIConstants.FROM_COMPLIANCE_CREATE_CASENOTE);
		String forward = UIConstants.CANCEL;
		List allConditionEvents = this.obtainAllSelectedConditions(compForm.getSelectedConditionIds(), compForm
				.getLikeConditions(), compForm.getUniqueConditions());
		if (allConditionEvents.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
					"No condition found to set add Casenote"));
			saveErrors(aRequest, errors);
		} else {
			compForm.setSelectedConditions(allConditionEvents);
			forward = UIConstants.CONDITION_CASENOTE_SUCCESS;
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
	public ActionForward filter(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		List likeCondList = new ArrayList();
		List uniqueCondList = new ArrayList();
		// loop thru allconditions List and compare values to those selected on
		// filter
		Map map = this.getGroupMap(compForm);		
		Iterator lgIter = compForm.getAllLikeConditions().iterator();
		
		//  Check like conditions for matches
		while (lgIter.hasNext()) {
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lgIter.next();
			Iterator ccrIter = lccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext()) {
				ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) ccrIter.next();
				String compliantStr = String.valueOf(ccre.isCompliant());
				if (compForm.getShowFilter() == null || "".equals(compForm.getShowFilter()) || compForm.getShowFilter().equalsIgnoreCase(compliantStr)) {
					
					if (compForm.getSelectedCaseNumbers() == null || compForm.getSelectedCaseNumbers().length == 0){
						this.addFilteredLikeConditions(ccre, compForm, lccre, likeCondList, map);
					}else{
						for(int i=0; i<compForm.getSelectedCaseNumbers().length;i++){
							if(compForm.getSelectedCaseNumbers()[i].equals(ccre.getCaseNumber())){
								this.addFilteredLikeConditions(ccre, compForm, lccre, likeCondList, map);
							}
						}
					}
				}
			}
		}
		
		//  Check unique conditions for matches, if necessary
		Iterator ucIter = compForm.getAllUniqueConditions().iterator();		
		while (ucIter.hasNext()) {
			ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent) ucIter.next();
			String compliantStr = String.valueOf(ccre2.isCompliant());
						
			if (compForm.getShowFilter().equalsIgnoreCase("") || compForm.getShowFilter().equalsIgnoreCase(
					compliantStr)) {
		
			    if (compForm.getSelectedCaseNumbers() == null || compForm.getSelectedCaseNumbers().length == 0){
				    this.addFilteredConditions(ccre2, compForm, uniqueCondList, map);
			    }else{
			    	for(int i=0; i<compForm.getSelectedCaseNumbers().length;i++){
			    		if(compForm.getSelectedCaseNumbers()[i].equals(ccre2.getCaseNumber())){
			    			this.addFilteredConditions(ccre2, compForm, uniqueCondList, map);
			    		}
			    	}
			    }
			}			
		}
		compForm.setLikeConditions(likeCondList);
		compForm.setUniqueConditions(uniqueCondList);
		return aMapping.findForward(UIConstants.FILTER_SUCCESS);
	}

	/**
	 * @param ccre
	 * @param compForm
	 * @param lccre
	 */
	private List addFilteredLikeConditions(ComplianceConditionResponseEvent ccre, ComplianceForm compForm, LikeComplianceConditionResponseEvent lccre, List likeConditions, Map map) {
		String groupId1 = compForm.getGroup1Id();
		String groupId2 = compForm.getGroup2Id();
		
		if (groupId1.equals("") && groupId2.equals("")) {
			if (!likeConditions.contains(lccre)) {
				likeConditions.add(lccre);
			}
		} else if (groupId2.equalsIgnoreCase("") && !groupId1.equals("")) {
			List list = (List) map.get(groupId1);
			if(list == null || list.isEmpty()){
				if (groupId1.equalsIgnoreCase(ccre.getGroupId())) {
					if (!likeConditions.contains(lccre)) {
						likeConditions.add(lccre);
					}
				}
			}else{
				list.add(compForm.getGroup1Id());
				for (int i = 0; i < list.size(); i++) {
					String groupdId = (String) list.get(i);
					if (groupdId.equalsIgnoreCase(ccre.getGroupId())) {
						if (!likeConditions.contains(lccre)) {
							likeConditions.add(lccre);
							break;
						}
					}
				}
			}
		} else if (compForm.getGroup2Id().equalsIgnoreCase(ccre.getGroupId())) {
			if(!likeConditions.contains(lccre)){
				likeConditions.add(lccre);
			}
		}
		return likeConditions;
	}
	
	/**
	 * @param compForm
	 * @return
	 */
	private Map getGroupMap(ComplianceForm compForm) {
		Map map = new HashMap();
		Iterator groupsIterator = compForm.getGroups().iterator();
		while (groupsIterator.hasNext()) {
			GroupResponseEvent resp = (GroupResponseEvent) groupsIterator.next();
			Iterator childIter = resp.getSubgroups().iterator();
			while (childIter.hasNext()) {
				GroupResponseEvent childResp = (GroupResponseEvent) childIter.next();
				if (!map.containsKey(resp.getGroupId())) {
					List list = new ArrayList();
					list.add(childResp.getGroupId());
					map.put(resp.getGroupId(), list);
				} else {
					List oldList = (List) map.get(resp.getGroupId());
					oldList.add(childResp.getGroupId());
					map.put(resp.getGroupId(), oldList);
				}
			}
		}
		return map;
	}

	/**
	 * @param ccre
	 * @param compForm
	 * @param lccre
	 */
	private List addFilteredConditions(ComplianceConditionResponseEvent ccre, ComplianceForm compForm, List conditions, Map map) {
		String groupId1 = compForm.getGroup1Id();
		String groupId2 = compForm.getGroup2Id();
				
		if (groupId1.equals("") && groupId2.equals("")) {
			if (!conditions.contains(ccre)) {
				conditions.add(ccre);
			}
		} else if (groupId2.equalsIgnoreCase("") && !groupId1.equalsIgnoreCase("")) {
			List list = (List) map.get(compForm.getGroup1Id());			
			if(list == null || list.isEmpty()){
				if (groupId1.equalsIgnoreCase(ccre.getGroupId())) {
					if (!conditions.contains(ccre)) {
						conditions.add(ccre);
					}
				}
			}else{			
				list.add(compForm.getGroup1Id());
				for (int i = 0; i < list.size(); i++) {
					String groupdId = (String) list.get(i);
					if (groupdId.equalsIgnoreCase(ccre.getGroupId())) {
						if (!conditions.contains(ccre)) {
							conditions.add(ccre);
						}
					}
				}
			}
		} else if (compForm.getGroup2Id().equalsIgnoreCase(ccre.getGroupId())) {
			if (!conditions.contains(ccre)) {
				conditions.add(ccre);
			}
		}
		return conditions;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewAll(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		compForm.setGroup1Id("");
		compForm.setGroup2Id("");
		compForm.setCaseNumber("");
		compForm.setLikeConditions(compForm.getAllLikeConditions());
		compForm.setUniqueConditions(compForm.getAllUniqueConditions());
		return aMapping.findForward(UIConstants.FILTER_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward decrementCondition(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setSelectedLikeConditions(new ArrayList());
		compForm.setSelectedUniqueConditions(new ArrayList());
		String orderCondId = aRequest.getParameter("conditionId");
		aRequest.setAttribute("conditionId", "");

		ComplianceConditionResponseEvent ccre = this.obtainDecrementConditionInfo(orderCondId, compForm
				.getLikeConditions(), compForm.getUniqueConditions());
		if (ccre != null) {
			compForm.setConditionName(ccre.getOrderConditionName());
			compForm.setCourt(ccre.getCourtId());
			compForm.setCaseNumber(ccre.getCaseNumber());
			compForm.setNonComplianceCount(new StringBuffer("").append(ccre.getNcCount()).toString());
		}
		GetNonCompliantEventsEvent getEvent = (GetNonCompliantEventsEvent) EventFactory
				.getInstance(ComplianceControllerServiceNames.GETNONCOMPLIANTEVENTS);

		getEvent.setSprOrderConditionId(orderCondId);
		getEvent.setConditionId(ccre.getConditionId());
		List NonCompliantEvents = MessageUtil.postRequestListFilter(getEvent, NonComplianceEventResponseEvent.class);

		if (NonCompliantEvents != null && !NonCompliantEvents.isEmpty()) {
			compForm.setSelectedConditions(NonCompliantEvents);
			compForm.setOrderId(orderCondId);
			compForm.setConditionId(ccre.getConditionId());
			compForm.setSprOrderConditionId(ccre.getSprOrderConditionId());
		}else{
			compForm.setSelectedConditions(new ArrayList());
		}
		return aMapping.findForward(UIConstants.DECREMENT_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward associateAdded(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		UIAdministerComplianceCasenoteHelper.associateAddhandler(compForm);
		return aMapping.findForward(UIConstants.CONDITION_CASENOTE_SUCCESS);
	}		
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward casenotesPopUp(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		String chainNum = compForm.getChainNum();
		String caseId = removeSpacefromCaseNumber(compForm.getCaseNumber());
		String conditionId = compForm.getConditionId();
		if (StringUtils.isNotEmpty(chainNum) && StringUtils.isNotEmpty(caseId) && StringUtils.isNotEmpty(conditionId)) {
			UIAdministerComplianceHelper.setAllConditionCaseNotes(compForm);
		} else {
			compForm.setPopupLiteral("No casenotes found");
//			List infos = new ArrayList();
		}
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward conditionPopUp(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setPopupLiteralName("");
		compForm.setPopupLiteral("");
		String sprOrderConditionId = aRequest.getParameter("sprOrderConditionId");
		if ( StringUtils.isNotEmpty(sprOrderConditionId) ) {
			this.obtainConditionInfo(compForm, sprOrderConditionId);
		} else {
			compForm.setPopupLiteralName("Condition Name");
			compForm.setPopupLiteral("No Condition literal found");
		}
		return aMapping.findForward(UIConstants.VIEW_SAMPLE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward courtPolicyPopUp(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setPopupLiteralName("");
		compForm.setPopupLiteral("");
		String courtPolicyId = aRequest.getParameter("courtPolicyId");
		if (StringUtils.isNotEmpty(courtPolicyId)) {
			this.obtainCourtPolicyInfo(compForm, courtPolicyId);
		} else {
			compForm.setPopupLiteralName("Court Policy");
			compForm.setPopupLiteral("No Court Policy literal found");
//			List infos = new ArrayList();
		}
		return aMapping.findForward(UIConstants.VIEW_SAMPLE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward departmentPolicyPopUp(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setPopupLiteralName("");
		compForm.setPopupLiteral("");
		String agencyPolicyId = aRequest.getParameter("agencyPolicyId");
		if (StringUtils.isNotEmpty(agencyPolicyId)) {
			this.obtainDepartmentPolicyInfo(compForm, agencyPolicyId);

		} else {
			compForm.setPopupLiteralName("Department Policy");
			compForm.setPopupLiteral("No Department Policy literal found");
		}
		return aMapping.findForward(UIConstants.VIEW_SAMPLE_SUCCESS);
	}

	/**
	 * @param selectedConditions,
	 *            likeConditions, uniqueCondition
	 * @return
	 */
	private ComplianceConditionResponseEvent obtainDecrementConditionInfo(String condId, Collection likeConditions,
			Collection uniqueConditions) {
		Iterator lgIter = likeConditions.iterator();
		// Check like conditions for selected match
		while (lgIter.hasNext()) {
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lgIter.next();
			Iterator ccrIter = lccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext()) {
				ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) ccrIter.next();
				if (ccre.getSprOrderConditionId().equalsIgnoreCase(condId)) {
					return ccre;
				}
			}
		}
		// Check unique conditions for selected match, if not found in like
		// conditions
		Iterator uIter = uniqueConditions.iterator();
		while (uIter.hasNext()) {
			ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent) uIter.next();
			if (ccre2.getSprOrderConditionId().equalsIgnoreCase(condId)) {
				return ccre2;
			}
		}
		return null;
	}

	/**
	 * @param selectedConditions,
	 *            uniqueCondition, isCompliant
	 * @return
	 */
	private List obtainSelectedConditions(String[] selectList, Collection likeConditions, Collection uniqueConditions,
			boolean complianceState) {
		List conditionEvents = new ArrayList();
		String condId = "";
		Iterator lgIter = likeConditions.iterator();
		//		 Check like conditions for selected match
		while (lgIter.hasNext()) {
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lgIter.next();
			Iterator ccrIter = lccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext()) {
				ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) ccrIter.next();
				if (StringUtils.isNotEmpty(ccre.getSprOrderConditionId())) {
					condId = ccre.getSprOrderConditionId();
					for (int x = 0; x < selectList.length; x++) {
						if (condId.equalsIgnoreCase(selectList[x]) && ccre.isCompliant() == complianceState) {
							conditionEvents.add(ccre);
							break;
						}
					}
				}
			}
		}
		if (conditionEvents.size() < selectList.length) {
			Iterator uIter = uniqueConditions.iterator();
			while (uIter.hasNext()) {
				ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent) uIter.next();
				if (StringUtils.isNotEmpty(ccre2.getSprOrderConditionId())) {
					condId = ccre2.getSprOrderConditionId();
					for (int x = 0; x < selectList.length; x++) {
						if (condId.equalsIgnoreCase(selectList[x]) && ccre2.isCompliant() == complianceState) {
							conditionEvents.add(ccre2);
							break;
						}
					}
				}
			}
		}

		return conditionEvents;
	}

	/**
	 * @param selectedConditions,
	 *            uniqueCondition, isCompliant
	 * @return
	 */
	private List obtainSelectedLikeConditions(String[] selectList, Collection likeConditions, boolean complianceState) {
		List conditionEvents = new ArrayList();
		List likeConditionEvents = new ArrayList();
		String condId = "";
		Iterator lgIter = likeConditions.iterator();
		// Check like conditions for selected match
		while (lgIter.hasNext()) {
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lgIter.next();
			Iterator ccrIter = lccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext()) {
				ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) ccrIter.next();
				if (StringUtils.isNotEmpty(ccre.getSprOrderConditionId())) {
					condId = ccre.getSprOrderConditionId();
					for (int x = 0; x < selectList.length; x++) {
/**	compliant state not needed as of 6/6/08 - defect#50547 */						
						if (condId.equalsIgnoreCase(selectList[x])){ // && ccre.isCompliant() == complianceState) {
							conditionEvents.add(ccre);
						}
					}
				}
			}
			if (conditionEvents.size() > 0) {
				LikeComplianceConditionResponseEvent likeEvent = new LikeComplianceConditionResponseEvent();
				likeEvent.setComplianceConditionResponseEvents(conditionEvents);
				likeConditionEvents.add(likeEvent);
				conditionEvents = new ArrayList();
			}
		}
		return likeConditionEvents;
	}

	/**
	 * @param selectedConditions,
	 *            uniqueCondition, isCompliant
	 * @return
	 */
	private List obtainSelectedUniqueConditions(String[] selectList, Collection uniqueConditions,
			boolean complianceState) {
		List conditionEvents = new ArrayList();
		String condId = "";
		Iterator uIter = uniqueConditions.iterator();
		while (uIter.hasNext()) {
			ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent) uIter.next();
			if (StringUtils.isNotEmpty(ccre2.getSprOrderConditionId())) {
				condId = ccre2.getSprOrderConditionId();
				for (int x = 0; x < selectList.length; x++) {
/**	compliant state not needed as of 6/6/08 - defect#50547 */						
					if (condId.equalsIgnoreCase(selectList[x])) { // && ccre2.isCompliant() == complianceState) {
						conditionEvents.add(ccre2);
						break;
					}
				}
			}
		}
		return conditionEvents;
	}

	/**
	 * @param selectedConditions,
	 *            likeConditions, uniqueCondition
	 * @return
	 */
	private List obtainAllSelectedConditions(String[] selectList, Collection likeConditions, Collection uniqueConditions) {
		List conditionEvents = new ArrayList();
		String condId = "";
		Iterator lgIter = likeConditions.iterator();
		// Check like conditions for selected match
		while (lgIter.hasNext()) {
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lgIter.next();
			Iterator ccrIter = lccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext()) {
				ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) ccrIter.next();
				if (StringUtils.isNotEmpty(ccre.getSprOrderConditionId())) {
					condId = ccre.getSprOrderConditionId();
					for (int x = 0; x < selectList.length; x++) {
						if (condId.equalsIgnoreCase(selectList[x])) {
							conditionEvents.add(ccre);
							break;
						}
					}
				}
			}
		}
		// Check unique conditions for selected match, if necessary
		if (selectList != null && conditionEvents.size() < selectList.length) {
			Iterator uIter = uniqueConditions.iterator();
			while (uIter.hasNext()) {
				ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent) uIter.next();
				if (StringUtils.isNotEmpty(ccre2.getSprOrderConditionId())) {
					condId = ccre2.getSprOrderConditionId();
					for (int x = 0; x < selectList.length; x++) {
						if (condId.equalsIgnoreCase(selectList[x])) {
							conditionEvents.add(ccre2);
							break;
						}
					}
				}
			}
		}
		return conditionEvents;
	}

	/**
	 * @param uniqueCondition
	 * @return
	 */
	private List setCompliantEvents(List selectedConditions) {
		List compliantEvents = new ArrayList();
		Iterator selIter = selectedConditions.iterator();
		while (selIter.hasNext()) {
			ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) selIter.next();
			CreateNonCompliantEventEvent compEvent = new CreateNonCompliantEventEvent();
			compEvent.setCaseNumber(ccre.getCaseNumber());
			compEvent.setDisplayCaseNum(UIAdministerComplianceHelper.removeCDIfromCaseNumber(ccre.getCaseNumber()));
			compEvent.setDetails("");
			compEvent.setSprOrderConditionId(Integer.parseInt(ccre.getSprOrderConditionId()));

			GetConditionNonCompliantEventTypesEvent getEvent = (GetConditionNonCompliantEventTypesEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETCONDITIONNONCOMPLIANTEVENTTYPES);
			getEvent.setConditionId(ccre.getConditionId());

			List eventTypesList = MessageUtil.postRequestListFilter(getEvent,
					NonComplianceEventTypeCodeResponseEvent.class);
			compEvent.setEventTypeList(eventTypesList);
			compEvent.setNewEventType("");
			compEvent.setOccurrenceDate("");
			compEvent.setOccurrenceTime("");
			compEvent.setOrderConditionName(ccre.getOrderConditionName());
			compEvent.setConditionId(ccre.getConditionId());
			compEvent.setOrderChainNumber(ccre.getOrderChainNumber());
			compEvent.setGroupId(ccre.getGroupId());
			compEvent.setCompliant(ccre.isCompliant());
			compliantEvents.add(compEvent);
		}

		return compliantEvents;
	}

	/**
	 * @param likeConditions
	 * @return
	 */
	private List setLikeCompliantEvents(List selectedLikeConditions) {
		List compliantLikeEvents = new ArrayList();
		List compliantEvents = new ArrayList();
		List likeEventTypesList = new ArrayList();
		String orderConditionName = "";
		String conditionId = "";
		String sprOrderConditionId = "";
		Iterator selIter = selectedLikeConditions.iterator();
		while (selIter.hasNext()) {
			likeEventTypesList = new ArrayList();
			orderConditionName = "";
			conditionId = "";
			LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) selIter.next();
			Iterator ccrIter = lccre.getComplianceConditionResponseEvents().iterator();
			while (ccrIter.hasNext()) {
				ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) ccrIter.next();
				CreateNonCompliantEventEvent compEvent = new CreateNonCompliantEventEvent();
				compEvent.setCaseNumber(ccre.getCaseNumber());
				compEvent.setDisplayCaseNum(UIAdministerComplianceHelper.removeCDIfromCaseNumber(ccre.getCaseNumber()));
				compEvent.setDetails("");
				compEvent.setSprOrderConditionId(Integer.parseInt(ccre.getSprOrderConditionId()));

				GetConditionNonCompliantEventTypesEvent getEvent = (GetConditionNonCompliantEventTypesEvent) EventFactory
						.getInstance(ComplianceControllerServiceNames.GETCONDITIONNONCOMPLIANTEVENTTYPES);
				getEvent.setConditionId(ccre.getConditionId());

				List eventTypesList = MessageUtil.postRequestListFilter(getEvent,
						NonComplianceEventTypeCodeResponseEvent.class);
				compEvent.setEventTypeList(eventTypesList);
				compEvent.setNewEventType("");
				compEvent.setOccurrenceDate("");
				compEvent.setOccurrenceTime("");
				compEvent.setOrderConditionName(ccre.getOrderConditionName());
				compEvent.setConditionId(ccre.getConditionId());
				compEvent.setSprOrderConditionId(Integer.parseInt(ccre.getSprOrderConditionId()));
				compEvent.setOrderChainNumber(ccre.getOrderChainNumber());
				compEvent.setGroupId(ccre.getGroupId());
				compEvent.setCompliant(ccre.isCompliant());
				
				//				compEvent.setSprvnBeginDate(sprBeginDate);
				compliantEvents.add(compEvent);
				if (orderConditionName == null || orderConditionName.equalsIgnoreCase("")) {
					orderConditionName = ccre.getOrderConditionName();
				}
				if (conditionId == null || conditionId.equalsIgnoreCase("")) {
					conditionId = ccre.getConditionId();
				}
				if (sprOrderConditionId == null || sprOrderConditionId.equalsIgnoreCase("")) {
					sprOrderConditionId = ccre.getSprOrderConditionId();
				}
				if (likeEventTypesList.isEmpty()) {
					likeEventTypesList = eventTypesList;
				}
			}
			if (compliantEvents.size() > 0) {
				CreateNonCompliantLikeEventEvent compLikeEvent = new CreateNonCompliantLikeEventEvent();
				compLikeEvent.setCreateNonCompliantEventEvent(compliantEvents);
				compLikeEvent.setOrderConditionName(orderConditionName);
				compLikeEvent.setConditionId(conditionId);
				compLikeEvent.setSprOrderConditionId(Integer.parseInt(sprOrderConditionId));
				compLikeEvent.setEventTypeList(likeEventTypesList);
				compliantLikeEvents.add(compLikeEvent);
				compliantEvents = new ArrayList();
			}
		}

		return compliantLikeEvents;
	}

	/**
	 * @param form,
	 *            policyId
	 * @return
	 */
	private void obtainCourtPolicyInfo(ComplianceForm compForm, String courtPolicyId) {
		GetCourtPolicyDetailsEvent postEvt = new GetCourtPolicyDetailsEvent();

		postEvt.setCourtPolicyId(courtPolicyId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(postEvt);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Collection searchResults = MessageUtil.compositeToCollection(compositeResponse,
				CourtPolicyDetailResponseEvent.class);

		Iterator iter = searchResults.iterator();
		if (iter.hasNext()) {
			CourtPolicyDetailResponseEvent reply = (CourtPolicyDetailResponseEvent) iter.next();
			compForm.setPopupLiteralName(reply.getName());
			compForm.setPopupLiteral(reply.getDescription());
		} else {
			compForm.setPopupLiteralName("Court Policy");
			compForm.setPopupLiteral("No Court Policy literal found");
		}
	}

	/**
	 * @param form,
	 *            policyId
	 * @return
	 */
	private void obtainDepartmentPolicyInfo(ComplianceForm compForm, String deptPolicyId) {
		int agencyPolicyId = Integer.parseInt(deptPolicyId);
		String policyName = "";
		String policyDesc = "";
		// search like conditions for match
		if (compForm.getLikeConditions() != null) {
			Iterator lgIter = compForm.getLikeConditions().iterator();
			while (lgIter.hasNext() && policyName == "") {
				LikeComplianceConditionResponseEvent lccre = (LikeComplianceConditionResponseEvent) lgIter.next();
				if (lccre.getDepartmentPolicies() != null) {
					Iterator dpIter = lccre.getDepartmentPolicies().iterator();
					while (dpIter.hasNext()) {
						OrderConditionAgencyPolicyResponseEvent ocapre = (OrderConditionAgencyPolicyResponseEvent) dpIter
								.next();
						if (agencyPolicyId == ocapre.getAgencyPolicyId()) {
							policyName = ocapre.getAgencyPolicyName();
							policyDesc = ocapre.getAgencyPolicyDescription();
							break;
						}
					}
				}
			}
		}
		//	Search unique conditions for match, if necessary
		if (policyName != null && policyName.equals("")) {
			if (compForm.getUniqueConditions() != null) {
				Iterator uIter = compForm.getUniqueConditions().iterator();
				while (uIter.hasNext() && policyName.equals("")) {
					ComplianceConditionResponseEvent ccre2 = (ComplianceConditionResponseEvent) uIter.next();
					if (ccre2.getDepartmentPolicies() != null) {
						Iterator dpIter2 = ccre2.getDepartmentPolicies().iterator();
						while (dpIter2.hasNext()) {
							OrderConditionAgencyPolicyResponseEvent ocapre2 = (OrderConditionAgencyPolicyResponseEvent) dpIter2
									.next();
							if (agencyPolicyId == ocapre2.getAgencyPolicyId()) {
								policyName = ocapre2.getAgencyPolicyName();
								policyDesc = ocapre2.getAgencyPolicyDescription();
								break;
							}
						}
					}
				}
			}
		}
		if (policyName != null && policyName.equals("")) {
			policyName = "Department Policy";
		}
		if (policyDesc != null && policyDesc.equals("")) {
			policyDesc = "No Department Policy literal found";
		}
		compForm.setPopupLiteralName(policyName);
		compForm.setPopupLiteral(policyDesc);
	}

	/**
	 * @param form,
	 *            policyId
	 * @return
	 */
	private void obtainConditionInfo(ComplianceForm compForm, String sprOrderConditionId) {
		GetSprOrderConditionLiteralEvent postEvt = new GetSprOrderConditionLiteralEvent();
		postEvt.setOrderConditionId(Integer.parseInt(sprOrderConditionId));

		List searchResults = MessageUtil.postRequestListFilter(postEvt, SupOrderConditionResponseEvent.class);

		if (searchResults != null && !searchResults.isEmpty()) {
			SupOrderConditionResponseEvent reply = (SupOrderConditionResponseEvent) searchResults.get(0);
			compForm.setPopupLiteralName(reply.getName());
			compForm.setPopupLiteral(reply.getResolvedDescription());
		} else {
			compForm.setPopupLiteralName("Condition Name");
			compForm.setPopupLiteral("No Condition literal found");
		}
	}
	
	/**
	 * @param caseNumber
	 * @return String
	 */
	public static String removeSpacefromCaseNumber(String caseNumber){
		StringBuffer trimCaseNum = new StringBuffer();
		if (StringUtils.isNotEmpty(caseNumber)){
			trimCaseNum.append(caseNumber.substring(0, 3));
			trimCaseNum.append(caseNumber.substring(4, caseNumber.length()));
		}
		return trimCaseNum.toString();
	}
}