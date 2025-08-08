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

import messaging.supervisionoptions.GenerateCourtPolicyNameEvent;
import messaging.supervisionoptions.reply.CourtPolicyNameResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
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
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

/** 
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleCourtPolicySelectionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42F7C49903C8
	 */
	public HandleCourtPolicySelectionAction() 
	{
    
	}

	public Map getKeyMethodMap(){
		Map buttonMap = new HashMap();
		buttonMap.put("button.copy", "copy");
		buttonMap.put("button.update", "update");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.updateAssociations", "updateAssociations");
		return buttonMap;
	}

	public ActionForward copy(ActionMapping aMapping, 
								 ActionForm aForm, 
								 HttpServletRequest aRequest, 
								 HttpServletResponse aResponse)
	 {
	 	CourtPolicyForm form = (CourtPolicyForm)aForm; 	
	 	
	 		
		if(!(UISupervisionOptionHelper.loadCourtPolicyForm(form, form.getPolicyId()))){
			throw new RuntimeException("Policy could not be loaded.  Policy ID: " + form.getPolicyId());
		}
		form.setInUse(false);
		form.setAction(UIConstants.COPY);
		SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,true);
		//form.setPolicyId(null);
		form.setNotes(""); //requirement not to retain any notes

		// get other collections
		String agencyId = form.getAgencyId();
		
//		if(form.getGroups() == null || form.getGroups().size() == 0)
//		{
//			// get groups	
//			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
//			form.setGroups(groups);
//		}
//		if(form.getDetailDictionary() == null || form.getDetailDictionary().size() == 0)
//		{
//			// get detail dictionary
//			Collection varElementTypes = UISupervisionOptionHelper.fetchDetailDictionary(agencyId);
//			form.setDetailDictionary(varElementTypes); 
//		}		
//		form.setFilteredDetailDictionary(form.getDetailDictionary());
		
//		if(form.getCourts() == null || form.getCourts().size() == 0)
//		{	
//			Collection courts =	UISupervisionOptionHelper.getCourtBeans();
//			form.setCourts(courts);
//		}
		form.setFilteredDetailDictionary(form.getDetailDictionary());
		populateGroupsForDisplay(form);
		
		GenerateCourtPolicyNameEvent event = new GenerateCourtPolicyNameEvent();
		event.setGroupId(form.getGroup1Id()); 
		event.setAgencyId(form.getAgencyId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection names =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyNameResponseEvent.class );
		if(names != null){
			Iterator iter = names.iterator();
			CourtPolicyNameResponseEvent nameRespEvt = (CourtPolicyNameResponseEvent)iter.next();
			form.setCourtPolicyName(nameRespEvt.getName());
		}
		 return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("copySuccess",form.getAgencyId()));
	 }

	public ActionForward update(ActionMapping aMapping, 
								 ActionForm aForm, 
								 HttpServletRequest aRequest, 
								 HttpServletResponse aResponse)
	 {
	 	CourtPolicyForm form = (CourtPolicyForm)aForm; 	
		String courtPolicyId = form.getPolicyId();
		form.setInUse(false);
		HashMap inUseCourtsMap=null;
		Collection inUseCourts=null;
		inUseCourts=UISupervisionOptionHelper.isCourtPolicyInUse(courtPolicyId,form);
		if (inUseCourts!=null && inUseCourts.size()>0)
		{
			form.setInUse(true);
			inUseCourtsMap=new HashMap();
			Iterator courtsInUseIter=inUseCourts.iterator();
			while(courtsInUseIter.hasNext()){
				Object obj=courtsInUseIter.next();
				inUseCourtsMap.put(((String)obj).trim(),((String)obj).trim());
			}
		}				
	 	form.setAction(UIConstants.UPDATE);
	 	if(!(UISupervisionOptionHelper.loadCourtPolicyForm(form, form.getPolicyId()))){
			throw new RuntimeException("Policy could not be loaded.  Policy ID: " + form.getPolicyId());
		}

		// get other collections
		String agencyId = form.getAgencyId();
		
//		if(form.getGroups() == null || form.getGroups().size() == 0)
//		{
//			// get groups	
//			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
//			form.setGroups(groups);
//		}
//		if(form.getDetailDictionary() == null || form.getDetailDictionary().size() == 0)
//		{
//			// get detail dictionary
//			Collection varElementTypes = UISupervisionOptionHelper.fetchDetailDictionary(agencyId);
//			form.setDetailDictionary(varElementTypes); 
//		}		
//		form.setFilteredDetailDictionary(form.getDetailDictionary());
//		if(form.getCourts() == null || form.getCourts().size() == 0)
//		{	
//			Collection courts =	UISupervisionOptionHelper.getCourtBeans();
//			form.setCourts(courts);
//		}

		form.setFilteredDetailDictionary(form.getDetailDictionary());
		populateGroupsForDisplay(form);
		SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
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
								if(inUseCourtsMap!=null && inUseCourtsMap.size()>0){
									if(inUseCourtsMap.containsKey(myCourtResp.getCourtId())){
										myCourtResp.setCourtInUse(true);
									}
									else{
										myCourtResp.setCourtInUse(false);
									}
								}
								else{
									myCourtResp.setCourtInUse(false);
								}
							}
						}
					}
				}
			}
		}
		 return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("updateSuccess",form.getAgencyId()));
	 }


	


	public ActionForward delete(ActionMapping aMapping, 
								 ActionForm aForm, 
								 HttpServletRequest aRequest, 
								 HttpServletResponse aResponse)
	 {
		CourtPolicyForm form = (CourtPolicyForm)aForm; 	
		form.setInUse(false);
		form.setAction(UIConstants.DELETE);
		String myForward=UIConstants.SUMMARY_SUCCESS;
		if (UISupervisionOptionHelper.isCourtPolicyInUse(form.getPolicyId()))
						{
							form.setInUse(true);
						//	this.sendToErrorPage(aRequest, "error.courtPolicy.inuse");
							myForward="deleteSuccess";
							
					//		return aMapping.findForward("policyInUse");
						}
		else{
			form.setPageType(UIConstants.SUMMARY);
		}
		if(!(UISupervisionOptionHelper.loadCourtPolicyForm(form, form.getPolicyId()))){
			throw new RuntimeException("Policy could not be loaded.  Policy ID: " + form.getPolicyId());
		}
		SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(myForward,form.getAgencyId()));
		//return aMapping.findForward("deleteSuccess");
	 }

	public ActionForward cancel(ActionMapping aMapping, 
								 ActionForm aForm, 
								 HttpServletRequest aRequest, 
								 HttpServletResponse aResponse)
	 {
		CourtPolicyForm form=(CourtPolicyForm)aForm;
		 return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("copySuccess",form.getAgencyId()));
	 }
	 
	public ActionForward updateAssociations(ActionMapping aMapping, 
									ActionForm aForm, 
									HttpServletRequest aRequest, 
									HttpServletResponse aResponse)
	 {
		CourtPolicyForm form = (CourtPolicyForm) aForm; 	

		if(!(UISupervisionOptionHelper.loadCourtPolicyForm(form, form.getPolicyId()))){
			throw new RuntimeException("Policy could not be loaded.  Policy ID: " + form.getPolicyId());
		}
		form.setInUse(false);
								if (UISupervisionOptionHelper.isCourtPolicyInUse(form.getPolicyId()))
						{
							form.setInUse(true);
							//this.sendToErrorPage(aRequest, "error.condition.inuse");
							//return aMapping.findForward("conditionInUse");
						}

		String agencyId = form.getAgencyId();
//		if(form.getGroups() == null || form.getGroups().size() == 0)
//		{
//			// get groups	
//			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
//			form.setGroups(groups);
//		}
		
		// Set the Group One Group Id because it was selected 
		form.clearAssocSearchResults();
		form.getAssocSearchCriteria().setGroup1Id( form.getGroup1Id() );
		
		form.getAssocSearchCriteria().setGroup1Name( form.getGroup1Name() );
		if ( form.getGroup2Name() != null )
		{
			form.getAssocSearchCriteria().setGroup2Id( form.getGroup2Id() );
			form.getAssocSearchCriteria().setGroup2Name( form.getGroup2Name() );
		}
		
		if ( form.getGroup3Name() != null )
		{
			form.getAssocSearchCriteria().setGroup3Id( form.getGroup3Id() );
			form.getAssocSearchCriteria().setGroup3Name( form.getGroup3Name() );
		}
		
//		// clear collections
//		form.clearAssocSelections();
//		form.clearAssociatedConditions();
//		// get associated CourtPolicies
//		Collection conditions =	UISupervisionOptionHelper.fetchAssociatedConditionsForCourtPolicy(form.getPolicyId());
//		if(conditions != null){
//			Iterator conds = conditions.iterator();
//			while(conds.hasNext()){
//				ConditionResponseEvent cre = (ConditionResponseEvent)conds.next();
//				AssociateBean asscBean = UISupervisionOptionHelper.createAsscBean(cre);
//				form.insertAssocSelection(asscBean);
//				form.insertAssociatedCondition(asscBean);
//			}
//		}
				
		form.setAction("asscConditions");
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("conditionAssociateSuccess",form.getAgencyId()));
	 }
	 
	private void populateGroupsForDisplay(CourtPolicyForm form)
	 {
 	
		Iterator group1Iter = form.getGroups().iterator();
		while(group1Iter.hasNext())
		{
			GroupResponseEvent group1 = (GroupResponseEvent)group1Iter.next();
			if(group1.getGroupId().equals(form.getGroup1Id()))
			{
				Collection group2s = group1.getSubgroups();
	
				if(group2s != null && group2s.size() > 0)
				{
					form.setGroup2(group2s); //save group2 collection in the form
					Iterator group2Iter = group2s.iterator();
					while(group2Iter.hasNext())
					{
						GroupResponseEvent group2 = (GroupResponseEvent)group2Iter.next();
						if(group2.getGroupId().equals(form.getGroup2Id()))
						{
							Collection group3s = group2.getSubgroups();
							if(group3s != null && group3s.size() > 0)
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
