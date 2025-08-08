//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicyEditDetailsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GetConditionsToDisassociateFromCourtPoliciesEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class DisplayCourtPolicyEditDetailsAction extends Action
{
   
   /**
    * @roseuid 42F7C47E0261
    */
   public DisplayCourtPolicyEditDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997C0295
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	
   
		  CourtPolicyForm form = (CourtPolicyForm)aForm;
	   		
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
		  
			//get policies to be removed if Group1/Group2 have been changed

		   if(UIConstants.UPDATE.equals(form.getAction()))
		   {
			  Collection removedAssociations = new ArrayList();
			  GetConditionsToDisassociateFromCourtPoliciesEvent asscEvent = new GetConditionsToDisassociateFromCourtPoliciesEvent();
				
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
			
		  return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   	
	 }

	 
	 
}
