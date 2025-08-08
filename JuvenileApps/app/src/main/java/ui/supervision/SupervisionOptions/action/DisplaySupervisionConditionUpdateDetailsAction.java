//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionUpdateDetailsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GetPoliciesToDisassociateEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
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
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionUpdateDetailsAction extends Action
{
   
   /**
    * @roseuid 42F7C49602FD
    */
   public DisplaySupervisionConditionUpdateDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3B033E
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
	   		
		ArrayList selectedCourts = new ArrayList();
		// check if All courts have been selected
		boolean allSelected = form.isAllCourtsSelected();
		if(allSelected){
			selectedCourts = (ArrayList)form.getCourts();
		}else{
			// create a CourtRespEvent map to make search fast for the selected courts
			Map courtMap = createCourtMap(form.getCourts());
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
						selCourtBean.setCategoryDesc((courtBean.getCategoryDesc()));
						for(int i = 0; i < selCourts.length; i++){
							//get the selected CourtResponseEvent
							CourtResponseEvent cre = (CourtResponseEvent)courtMap.get(selCourts[i]);
							if(cre != null){
								selCourtBean.insertCourt(cre);
							}
						}
						selectedCourts.add(selCourtBean);
					}
				}
			} 
		}
		// courtResponseEvent exceptionCourt value does not contain correct value, use values in ExceptionCourtVarElemBeans 
		if (selectedCourts != null){
			Collection execptionCourtBeans = form.getExceptionCourtVarElemBeans();
			Iterator<CourtBean> scIter = selectedCourts.iterator();
			while (scIter.hasNext()){
				CourtBean courtBean = scIter.next();
				if (courtBean.getCourts() != null){
					Iterator<CourtResponseEvent> crtIter = courtBean.getCourts().iterator();
					while(crtIter.hasNext()) {
						CourtResponseEvent cre2 = crtIter.next();
							if (cre2.getCourtId() != null){
								if (execptionCourtBeans != null && !execptionCourtBeans.isEmpty()){
									Iterator<CourtVariableElementBean> itr = execptionCourtBeans.iterator();
									while(itr.hasNext()){
										CourtVariableElementBean vre = itr.next(); 
										// courtNumber in CourtVariableElementBean corresponds to courtId in CourtResponseEvent
										if (vre.getCourtNumber() != null && vre.getCourtNumber().equalsIgnoreCase(cre2.getCourtId())){
											Iterator iter = vre.getVariableElements().iterator();
											while(iter.hasNext()){
												VariableElementResponseEvent res = (VariableElementResponseEvent) iter.next();
												cre2.setExceptionCourt(res.isExceptionCourt());
											}
											break;
										}
									}
								}
							}
						}		
					}
				}
		}
		form.setSelectedCourts(selectedCourts);

		// get policies to be removed if Group1/Group2 have been changed
		if(UIConstants.UPDATE.equals(form.getAction())){
			Collection removedAssociations = new ArrayList();
			GetPoliciesToDisassociateEvent asscEvent = new GetPoliciesToDisassociateEvent();
					
			String groupId = null;
//			String group1Id = form.getGroup1Id();
			String group2Id = form.getGroup2Id();
			if(group2Id != null && !group2Id.equals("")){
				groupId = form.getGroup2Id();
			}else{
				groupId = form.getGroup1Id();
			}
			
			asscEvent.setGroupId(groupId);
			asscEvent.setConditionId(form.getConditionId());
			asscEvent.setCourtIds(form.getSelectedCourtIds());
			
			// post event
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(asscEvent);
			
			// check for court Policies
			Collection cpres =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyResponseEvent.class );
			if(cpres != null){
				Iterator cpresIter = cpres.iterator();
				while(cpresIter.hasNext())
				{
					CourtPolicyResponseEvent cpre = (CourtPolicyResponseEvent)cpresIter.next();
					AssociateBean ab = UISupervisionOptionHelper.createAsscBean(cpre);
					removedAssociations.add(ab);
				}
			}
			
			// check for department Policies
			Collection dpres =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), DepartmentPolicyResponseEvent.class );
			if(dpres != null){
				Iterator dpresIter = dpres.iterator();
				while(dpresIter.hasNext())
				{
					DepartmentPolicyResponseEvent dpre = (DepartmentPolicyResponseEvent)dpresIter.next();
					AssociateBean ab = UISupervisionOptionHelper.createAsscBean(dpre);
					removedAssociations.add(ab);
				}
			}
			
			form.setRemovedAssociations(removedAssociations);
			
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }

   private Map createCourtMap(Collection courts){
		Map map = new HashMap();
		Iterator it = courts.iterator();
		while(it.hasNext()){
			CourtBean cb = (CourtBean)it.next();
			Iterator cres = cb.getCourts().iterator();
			while(cres.hasNext()){
				CourtResponseEvent cre = (CourtResponseEvent)cres.next();
				map.put(cre.getCourtId(), cre);
			}
		}
		return map;
   }
}
