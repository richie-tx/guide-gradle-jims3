//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayDepartmentPolicyNameAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GenerateDepartmentPolicyNameEvent;
import messaging.supervisionoptions.GetConditionsToDisassociateFromDepartmentPoliciesEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyNameResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class DisplayDepartmentPolicyNameAction extends Action 
{
   
   /**
    * @roseuid 42F7C489035B
    */
   public DisplayDepartmentPolicyNameAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A0801E9
    */
   protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param1, String param2) {
    ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param1,param2));
    saveErrors(aRequest, errors);
}
   
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	
		DepartmentPolicyForm form = (DepartmentPolicyForm) aForm;
		
		String testLit=UIUtil.removeXMLtags(form.getDepartmentPolicy(),true);
		if(testLit==null || testLit.trim().equals("")){
			sendToErrorPage(aRequest,"error.requiredNonEditableMissing", "Policy");
			form.setDepartmentPolicy("");
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("createFailure",form.getAgencyId()));
		}
		if(testLit!=null){
			if(testLit.length()<10){
				sendToErrorPage(aRequest,"errors.minlength","Policy","10");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("createFailure",form.getAgencyId()));
			}
			else if(testLit.length()>1000){
				sendToErrorPage(aRequest,"errors.maxlength","Policy","1000");
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("createFailure",form.getAgencyId()));
			}
		}
		ArrayList selectedCourts = new ArrayList();
		 // check if All courts have been selected
		 boolean allSelected = form.isAllCourtsSelected();
		 if(allSelected){
			 selectedCourts = (ArrayList)form.getCourts();
		 }else{
			 // create a CourtRespEvent map to make search fast for the selected courts
			 Map courtMap = UISupervisionOptionHelper.createCourtMap(form.getCourts());
			 selectedCourts = new ArrayList();
			 Collection courtBeans = form.getCourts();
			 if(courtBeans != null){
				 Iterator it = courtBeans.iterator();
				 while(it.hasNext()){
					 CourtBean courtBean = (CourtBean)it.next();
					 String[] selCourts = aRequest.getParameterValues(courtBean.getCategory());
					 if(selCourts != null){
						 CourtBean selCourtBean = new CourtBean();
						 selCourtBean.setCategory(courtBean.getCategory());
						 selCourtBean.setCategoryDesc(courtBean.getCategoryDesc());
						 for(int i = 0; i < selCourts.length; i++){
							 //get the selected CourtResponseEvent
							 CourtResponseEvent cre = (CourtResponseEvent)courtMap.get(selCourts[i]); 
							 selCourtBean.insertCourt(cre);
						 }
						 selectedCourts.add(selCourtBean);
					 }
				 }
			 }
		 }
		 form.setSelectedCourts(selectedCourts);
	
		
		//get the group name from group Id
		setGroupName(form,form.getGroups());

		if(UIConstants.UPDATE.equals(form.getAction())){
			CompositeResponse response = UISupervisionOptionHelper.validateDepartmentPolicy(form.getAgencyId(), form.getName(), form.getPolicyId());
			
			ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
			if (returnException != null)
			{
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE,form.getAgencyId()));
			}
			DuplicationNameErrorEvent duplicateException = (DuplicationNameErrorEvent) MessageUtil.filterComposite(response, DuplicationNameErrorEvent.class);
			if (duplicateException  != null)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.name.exist", "Duplicate Name"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CREATE_FAILURE,form.getAgencyId()));
			}
		}else{
			if(form.getName()!=null && !(form.getName().equals(""))){
			// skip generating name
			}
			else{
					GenerateDepartmentPolicyNameEvent event = new GenerateDepartmentPolicyNameEvent();
					event.setGroupId(form.getGroup1Id()); 
					event.setAgencyId(form.getAgencyId());
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatch.postEvent(event);
					Collection names =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), DepartmentPolicyNameResponseEvent.class );
					if(names != null){
						Iterator iter = names.iterator();
						DepartmentPolicyNameResponseEvent nameRespEvt = (DepartmentPolicyNameResponseEvent)iter.next();
						form.setName(nameRespEvt.getName());
					}
				}
		}
		
		//	get policies to be removed if Group1/Group2 have been changed
		if(UIConstants.UPDATE.equals(form.getAction()) || UIConstants.COPY.equals(form.getAction()))
		{
			Collection removedAssociations = new ArrayList();
			GetConditionsToDisassociateFromDepartmentPoliciesEvent asscEvent = new GetConditionsToDisassociateFromDepartmentPoliciesEvent();
				
			String groupId = null;
			String group1Id = form.getGroup1Id();
			String group2Id = form.getGroup2Id();
			if(group2Id != null && !group2Id.equals("")){
				groupId = form.getGroup2Id();
			}else{
				groupId = form.getGroup1Id();
			}
			
			asscEvent.setGroupId(groupId);
			asscEvent.setPolicyId(form.getPolicyId());
			asscEvent.setCourtIds(form.getSelectedCourtIds());
			
			// post event
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(asscEvent);
		
			// check for conditions
			Collection condres = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), ConditionResponseEvent.class );
			if(condres != null){
				Iterator condresIter = condres.iterator();
				while(condresIter.hasNext())
				{
					ConditionResponseEvent condre = (ConditionResponseEvent)condresIter.next();
					AssociateBean ab = UISupervisionOptionHelper.createAsscBean(condre);
					removedAssociations.add(ab);
				}
		
			}
		 
			form.setRemovedAssociations(removedAssociations);
				 
		}
		// make sure we don't pass courtPolicy id from here on for copy operation
		if(UIConstants.COPY.equals(form.getAction())){
			form.setPolicyId(null);
		}
		if(UIConstants.UPDATE.equals(form.getAction()) ){
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUMMARY_SUCCESS,form.getAgencyId()));
		}
		else{
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
   }
   
   private DepartmentPolicyForm setGroupName(DepartmentPolicyForm form, Collection groups)
	{

		//recursion breaking condition 
		if(groups == null)
			return null;
			
		Iterator groupsIter = groups.iterator();
		if(form.getGroup1Id()==null || form.getGroup1Id().equalsIgnoreCase(""))
						form.setGroup1Name("");
				if(form.getGroup2Id()==null || form.getGroup2Id().equalsIgnoreCase(""))
								form.setGroup2Name("");
				if(form.getGroup3Id()==null || form.getGroup3Id().equalsIgnoreCase(""))
								form.setGroup3Name("");
		while(groupsIter.hasNext())
		{
			 GroupResponseEvent eachGroup = (GroupResponseEvent) groupsIter.next();

			if(form.getGroup1Id() != null && form.getGroup1Id().equals(eachGroup.getGroupId()))
			{
				form.setGroup1Name(eachGroup.getName());
			}
			if(form.getGroup2Id() != null && form.getGroup2Id().equals(eachGroup.getGroupId()))
			{
				form.setGroup2Name(eachGroup.getName());
			}
			if(form.getGroup3Id() != null && form.getGroup3Id().equals(eachGroup.getGroupId()))
			{
				form.setGroup3Name(eachGroup.getName());
			}

			//recursion here
				 setGroupName(form, eachGroup.getSubgroups());

			}
   
			return null;
	}	  
   
   private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
  
  private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
   ActionErrors errors = new ActionErrors();
   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg,param));
   saveErrors(aRequest, errors);
}
}
