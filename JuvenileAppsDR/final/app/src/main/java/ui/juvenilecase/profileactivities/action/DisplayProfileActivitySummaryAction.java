/*
 * Created on Dec 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.profileactivities.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetActivityDetailsEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.GetJuvenileActivityCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayProfileActivitySummaryAction extends LookupDispatchAction {

	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		
		ActivitiesForm form = (ActivitiesForm) aForm;
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
		
		form.setAction(UIConstants.VIEW_DETAIL);
		form.setSecondaryAction("profile");
		form.setSelectedValue("");
		
		if (juvenileCasefileForm == null)
		{
			juvenileCasefileForm = new JuvenileCasefileForm();
			String casefileId = aRequest.getParameter("casefileId");
			UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(juvenileCasefileForm, casefileId);
			aRequest.getSession().setAttribute("juvenileCasefileForm", juvenileCasefileForm);
		}	
		
		boolean isJPOORCLMOfCurrentCasefile = false;
		String currentUser = UIUtil.getCurrentUserID();
		String clm = juvenileCasefileForm.getCaseloadManagerId();
		String jpo = juvenileCasefileForm.getProbationOfficerLogonId();
		
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetActivityDetailsEvent reqEvent = 
			(GetActivityDetailsEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITYDETAILS);		
		
		reqEvent.setActivityId(form.getActivityId());
		dispatch.postEvent(reqEvent);
		
		IEvent replyEvent = dispatch.getReply();

		CompositeResponse composite = (CompositeResponse) replyEvent;
		ActivityResponseEvent activityDetails = (ActivityResponseEvent) MessageUtil.filterComposite(composite, ActivityResponseEvent.class);
		

		Collection activitiesCategories = CodeHelper.getActivityCategory(true);
		Collection activitiesTypes = CodeHelper.getActivityType(true);

		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileActivityCodesEvent req =
			(GetJuvenileActivityCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYCODES);		

		dispatch1.postEvent(req);
		IEvent reply = dispatch1.getReply();
		CompositeResponse composite1 = (CompositeResponse) reply;
		Collection responses = MessageUtil.compositeToCollection(composite1, JuvenileActivityTypeCodeResponseEvent.class);
		Iterator iterActivityCodes = responses.iterator(); 
		HashMap hashActivityCodes = new HashMap();

		form.setActivityDate(activityDetails.getActivityDate());
		//u.s 11097
		if(currentUser.equals(clm)||currentUser.equals(jpo)){
			isJPOORCLMOfCurrentCasefile = true;
		}
		boolean showComments = false;
		if(activityDetails.getPermissionId()!=null && activityDetails.getPermissionId().equals("C") && !isJPOORCLMOfCurrentCasefile)
		{
			showComments= true;
		}
		if(!showComments){
			form.setComments(activityDetails.getComments());
		}
		//u.s 11097 ends
		juvenileCasefileForm.populateJuvenileCasefileForm(activityDetails.getCasefileId());
		
		while(iterActivityCodes.hasNext()) {
			JuvenileActivityTypeCodeResponseEvent respAct = (JuvenileActivityTypeCodeResponseEvent) iterActivityCodes.next();
			hashActivityCodes.put(respAct.getCode(), respAct.getDescription());
		}
		if(activityDetails.getCategoryId() != null && !(activityDetails.getCategoryId().trim().equals("")))
		{
			form.setCategoryDesc(CodeHelper.getCodeDescriptionByCode(activitiesCategories, activityDetails.getCategoryId()));
		}
		else
		{
			form.setCategoryDesc("");
		}
		if(activityDetails.getTypeId() != null && !(activityDetails.getTypeId().trim().equals("")) )
		{
			form.setTypeDesc(CodeHelper.getCodeDescriptionByCode(activitiesTypes, activityDetails.getTypeId()));
		}
		else
		{
			form.setTypeDesc("");
		}   			
		if(activityDetails.getCodeId() != null && !(activityDetails.getCodeId().trim().equals("")) )
		{
			if(hashActivityCodes.get(activityDetails.getCodeId())!=null)
				form.setActivityDesc(hashActivityCodes.get(activityDetails.getCodeId()).toString());
		}
		else
		{
			form.setActivityDesc("");
		}
		
		if( activityDetails.getActivityTime() != null
			&& !(activityDetails.getActivityTime().trim().equals("")) ) {
		    	form.setActivityTimeStr(activityDetails.getActivityTime());
		} else {
		    form.setActivityTimeStr("");
		}
		return aMapping.findForward(UIConstants.SUCCESS);
		
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			{
				return aMapping.findForward(UIConstants.BACK);
			}	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.link", "link");
		return keyMap;
	}}
