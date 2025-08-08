/*
 * Created on Aug 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.DeleteSupervisionConditionEvent;
import messaging.supervisionoptions.GenerateSupervisionConditionNameEvent;
import messaging.supervisionoptions.ValidateConditionChangeEvent;
import messaging.supervisionoptions.reply.ConditionInUseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import messaging.supervisionoptions.reply.SupervisionConditionNameResponseEvent;
import messaging.supervisionorder.ValidateConditionInUseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOptionsControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleSupervisionConditionSelectionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42F7C49903C8
	 */
	public HandleSupervisionConditionSelectionAction()
	{

	}

	public Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.copy", "copy");
		buttonMap.put("button.update", "update");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.inactivate", "delete");
		buttonMap.put("button.updateCourtPolicyAssociations", "updateCourtPolicyAssociations");
		buttonMap.put("button.updateDeptPolicyAssociations", "updateDeptPolicyAssociations");
		buttonMap.put("button.assocToCourtPolicy", "updateCourtPolicyAssociations");
		buttonMap.put("button.assocToDeptPolicy", "updateDeptPolicyAssociations");
		buttonMap.put("button.assocToConsequence", "updateCourtPolicyAssociations");
		buttonMap.put("button.assocToComplianceStandards", "updateCourtPolicyAssociations");
		buttonMap.put("button.assocToProcedures", "updateDeptPolicyAssociations");
		buttonMap.put("button.updateConsequenceAssociations", "updateCourtPolicyAssociations");
		buttonMap.put("button.updateComplianceStandardsAssociations", "updateCourtPolicyAssociations");
		buttonMap.put("button.updateProcedureAssociations", "updateDeptPolicyAssociations");
		

		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}

	public ActionForward updateCourtPolicyAssociations(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		ValidateConditionChangeEvent requestEvent =
			(ValidateConditionChangeEvent) EventFactory.getInstance(
				SupervisionOptionsControllerServiceNames.VALIDATECONDITIONCHANGE);

		requestEvent.setConditionId(form.getConditionId());
		requestEvent.setAction(UIConstants.UPDATE);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ConditionInUseEvent responseEvent =
			(ConditionInUseEvent) MessageUtil.filterComposite(response, ConditionInUseEvent.class);
		form.setInUse(false);
		if (responseEvent != null)
		{
			form.setInUse(true);
		}
		if(!(UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId()))){
			throw new RuntimeException("Condition could not be loaded.  Policy ID: " + form.getConditionId());
		}
		form.clearAssocSearchResults();
		form.setSearchResultText("");
		
		String agencyId = form.getAgencyId();
		//		if(form.getGroups() == null || form.getGroups().size() == 0)
		//		{
		//			// get groups	
		//			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
		//			form.setGroups(groups);
		//		}

		//		form.getAssocSearchCriteria().setObjName( form.getConditionName() );
		// Set the Group One Group Id because it was selected 
		form.getAssocSearchCriteria().setGroup1Id(form.getGroup1Id());
		form.getAssocSearchCriteria().setGroup1Name(form.getGroup1Name());
		if (form.getGroup2Name() != null)
		{
			form.getAssocSearchCriteria().setGroup2Id(form.getGroup2Id());
			form.getAssocSearchCriteria().setGroup2Name(form.getGroup2Name());
		}

		if (form.getGroup3Name() != null)
		{
			form.getAssocSearchCriteria().setGroup3Id(form.getGroup3Id());
			form.getAssocSearchCriteria().setGroup3Name(form.getGroup3Name());
		}

		//		// clear collections
		//		form.clearAssocSelections();
		//		form.clearAssociatedCourtPolicies();
		//		form.clearAssociatedDeptPolicies();

		// set selected elems list		
		//		form.setAssocSelections(form.getAssociatedCourtPolicies());
		//		// get associated CourtPolicies
		//		Collection courtPolicies =	UISupervisionOptionHelper.fetchAssociatedCourtPolicies(form.getConditionId());
		//		if(courtPolicies != null){
		//			Iterator cPols = courtPolicies.iterator();
		//			while(cPols.hasNext()){
		//				CourtPolicyResponseEvent cpre = (CourtPolicyResponseEvent)cPols.next();
		//				AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(cpre);
		//				form.insertAssocSelection(asscBean);
		//				form.insertAssociatedCourtPolicy(asscBean);
		//			}
		//		}
		//		
		//		// get associated DepartmentPolicies
		//		Collection deptPolicies = UISupervisionOptionHelper.fetchAssociatedDeptPolicies(form.getConditionId());
		//		if(deptPolicies != null){
		//			Iterator dPols = deptPolicies.iterator();
		//			while(dPols.hasNext()){
		//				DepartmentPolicyResponseEvent dpre = (DepartmentPolicyResponseEvent)dPols.next();
		//				AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(dpre);
		//				form.insertAssociatedDeptPolicy(asscBean);
		//			}
		//		}
		form.setAction(UIConstants.COND_ASSOC_COURT_POLICY);
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("courtPolicyAssociateSuccess",form.getAgencyId()));
	}

	public ActionForward updateDeptPolicyAssociations(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		ValidateConditionChangeEvent requestEvent =
			(ValidateConditionChangeEvent) EventFactory.getInstance(
				SupervisionOptionsControllerServiceNames.VALIDATECONDITIONCHANGE);

		requestEvent.setConditionId(form.getConditionId());
		requestEvent.setAction(UIConstants.UPDATE);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ConditionInUseEvent responseEvent =
			(ConditionInUseEvent) MessageUtil.filterComposite(response, ConditionInUseEvent.class);
		form.setInUse(false);
		if (responseEvent != null)
		{
			form.setInUse(true);
		}
		if(!(UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId()))){
			throw new RuntimeException("Condition could not be loaded.  Policy ID: " + form.getConditionId());
		}
		form.clearAssocSearchResults();
		form.setSearchResultText("");
		//		String agencyId = form.getAgencyId();
		//		if(form.getGroups() == null || form.getGroups().size() == 0)
		//		{
		//			// get groups	
		//			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
		//			form.setGroups(groups);
		//		}

		form.getAssocSearchCriteria().setGroup1Id(form.getGroup1Id());
		form.getAssocSearchCriteria().setGroup1Name(form.getGroup1Name());
		if (form.getGroup2Name() != null)
		{
			form.getAssocSearchCriteria().setGroup2Id(form.getGroup2Id());
			form.getAssocSearchCriteria().setGroup2Name(form.getGroup2Name());
		}

		if (form.getGroup3Name() != null)
		{
			form.getAssocSearchCriteria().setGroup3Id(form.getGroup3Id());
			form.getAssocSearchCriteria().setGroup3Name(form.getGroup3Name());
		}

		// clear collections
		//		form.clearAssocSelections();

		// set assc selections
		//		form.setAssocSelections(form.getAssociatedDeptPolicies());
		//		// get associated CourtPolicies
		//		Collection courtPolicies =	UISupervisionOptionHelper.fetchAssociatedCourtPolicies(form.getConditionId());
		//		if(courtPolicies != null){
		//			Iterator cPols = courtPolicies.iterator();
		//			while(cPols.hasNext()){
		//				CourtPolicyResponseEvent cpre = (CourtPolicyResponseEvent)cPols.next();
		//				AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(cpre);
		//				form.insertAssociatedCourtPolicy(asscBean);
		//			}
		//		}
		//		
		//		// get associated DepartmentPolicies
		//		Collection deptPolicies = UISupervisionOptionHelper.fetchAssociatedDeptPolicies(form.getConditionId());
		//		if(deptPolicies != null){
		//			Iterator dPols = deptPolicies.iterator();
		//			while(dPols.hasNext()){
		//				DepartmentPolicyResponseEvent dpre = (DepartmentPolicyResponseEvent)dPols.next();
		//				AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(dpre);
		//				form.insertAssociatedDeptPolicy(asscBean);
		//				form.insertAssocSelection(asscBean);
		//			}
		//		}

		form.setAction(UIConstants.COND_ASSOC_DEPT_POLICY);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("deptPolicyAssociateSuccess",form.getAgencyId()));
	}

	public ActionForward copy(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		form.setAction(UIConstants.COPY);
		if(!(UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId()))){
			throw new RuntimeException("Condition could not be loaded.  Policy ID: " + form.getConditionId());
		}
		form.setInUse(false);
		SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,true);
		//form.setConditionId(null);
		form.setNotes(""); //requirement not to retain any notes

		form.setFilteredDetailDictionary(form.getDetailDictionary());
		populateGroupsForDisplay(form);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GenerateSupervisionConditionNameEvent event = new GenerateSupervisionConditionNameEvent();
		event.setGroupId(form.getGroup1Id()); 
		event.setAgencyId(form.getAgencyId());
		dispatch.postEvent(event);
		Collection names =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), SupervisionConditionNameResponseEvent.class );
		if(names != null){
			Iterator iter = names.iterator();
			SupervisionConditionNameResponseEvent nameRespEvt = (SupervisionConditionNameResponseEvent)iter.next();
			form.setConditionName(nameRespEvt.getName());
		}
		
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("copySuccess",form.getAgencyId()));
	}

	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		String conditionId = form.getConditionId();
		form.clear();
		form.setConditionId(conditionId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		ValidateConditionInUseEvent requestEvent =
			(ValidateConditionInUseEvent) EventFactory.getInstance(
					SupervisionOrderControllerServiceNames.VALIDATECONDITIONINUSE);

		requestEvent.setConditionId(conditionId);
		requestEvent.setAction(UIConstants.UPDATE);

		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ConditionInUseEvent responseEvent =
			(ConditionInUseEvent) MessageUtil.filterComposite(response, ConditionInUseEvent.class);
		HashMap inUseCourtsMap=null;
		
		if (responseEvent != null)
		{
			form.setInUse(true);
			if(responseEvent.getCourtIds()!=null && responseEvent.getCourtIds().size()>0){
				inUseCourtsMap=new HashMap();
				Iterator courtsInUseIter=responseEvent.getCourtIds().iterator();
				while(courtsInUseIter.hasNext()){
					Object obj=courtsInUseIter.next();
					if (obj != null){
						inUseCourtsMap.put(((String)obj).trim(),((String)obj).trim());
					}
				}
			}
			//this.sendToErrorPage(aRequest, "error.condition.inuse");
			//return aMapping.findForward("conditionInUse");
		}
		//else
		//{
			form.setFilteredDetailDictionary(form.getDetailDictionary());
			populateGroupsForDisplay(form);
			form.setAction(UIConstants.UPDATE);
			if(!(UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId()))){
				throw new RuntimeException("Condition could not be loaded.  Policy ID: " + form.getConditionId());
			}
			if(inUseCourtsMap!=null && inUseCourtsMap.size()>0){
				Collection selCourts=form.getSelectedCourts();
				if(selCourts!=null && selCourts.size()>0){
					Iterator selCourtsIter=selCourts.iterator();
					while(selCourtsIter.hasNext()){
						Object selCourtsObj=selCourtsIter.next();
						if(selCourtsObj!=null){
							CourtBean myCourtBean=(CourtBean)selCourtsObj;
							Collection courtBeanCourts=myCourtBean.getCourts();
							if(courtBeanCourts!=null && courtBeanCourts.size()>0){
								Iterator courtBeanCourtsIter=courtBeanCourts.iterator();
								while(courtBeanCourtsIter.hasNext()){
									Object courtBeanCourtsObj=courtBeanCourtsIter.next();
									if(courtBeanCourtsObj!=null){
										CourtResponseEvent myCourtResp=(CourtResponseEvent)courtBeanCourtsObj;
										if(inUseCourtsMap.containsKey(myCourtResp.getCourtId())){
											myCourtResp.setCourtInUse(true);
										}
									}
								}
							}
						}
					}
				}
			}
			
			
			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("updateSuccess",form.getAgencyId()));
		//}
	}

	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		//form.setAction(UIConstants.DELETE); Is being set below
		//UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId()); 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		ValidateConditionChangeEvent requestEvent =
			(ValidateConditionChangeEvent) EventFactory.getInstance(
				SupervisionOptionsControllerServiceNames.VALIDATECONDITIONCHANGE);

		requestEvent.setConditionId(form.getConditionId());
		requestEvent.setAction(UIConstants.DELETE);

		dispatch.postEvent(requestEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ConditionInUseEvent responseEvent =
			(ConditionInUseEvent) MessageUtil.filterComposite(response, ConditionInUseEvent.class);


		form.setAction(UIConstants.DELETE);
		if(!(UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId()))){
			throw new RuntimeException("Condition could not be loaded.  Policy ID: " + form.getConditionId());
		}

		DeleteSupervisionConditionEvent deleteSupervisionConditionEvent = new DeleteSupervisionConditionEvent();
		deleteSupervisionConditionEvent.setConditionId(form.getConditionId());
		form.setDeleteSupervisionConditionEvent(deleteSupervisionConditionEvent);
		if (responseEvent != null)
		{ // condition in use
			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
			form.setInUse(true);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("conditionInUse",form.getAgencyId()));
		}
		else // condition not in use
		{
			form.setInUse(false);
			deleteSupervisionConditionEvent.setTrueDelete(true);
			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
			form.setPageType(UIConstants.SUMMARY);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("deleteSuccess",form.getAgencyId()));
		}

		
//		if (responseEvent != null)
//		{
//			this.sendToErrorPage(aRequest, "error.condition.inuse");
//			return aMapping.findForward("conditionInUse");
//		}
//		else
//		{
//			form.setAction(UIConstants.DELETE);
//			UISupervisionOptionHelper.loadSupervisionConditionForm(form, form.getConditionId());
//			SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
//			return aMapping.findForward("deleteSuccess");
//		}
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK,form.getAgencyId()));
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL,form.getAgencyId()));
		return forward;
	}

	private void populateGroupsForDisplay(SupervisionConditionForm form)
	{

		Iterator group1Iter = form.getGroups().iterator();
		while (group1Iter.hasNext())
		{
			GroupResponseEvent group1 = (GroupResponseEvent) group1Iter.next();
			if (group1.getGroupId().equals(form.getGroup1Id()))
			{
				Collection group2s = group1.getSubgroups();

				if (group2s != null && group2s.size() > 0)
				{
					form.setGroup2(group2s); //save group2 collection in the form
					Iterator group2Iter = group2s.iterator();
					while (group2Iter.hasNext())
					{
						GroupResponseEvent group2 = (GroupResponseEvent) group2Iter.next();
						if (group2.getGroupId().equals(form.getGroup2Id()))
						{
							Collection group3s = group2.getSubgroups();
							if (group3s != null && group3s.size() > 0)
							{
								form.setGroup3(group3s); //save group3 collection in the form
							}
						}
					}
				}
			}
		}
	}
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
