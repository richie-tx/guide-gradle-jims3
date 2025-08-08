//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayActivitySummaryAction.java

package ui.juvenilecase.activities.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetActivityDetailsEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.GetJuvenileActivityCodesEvent;
import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.form.JuvenileCasefileForm;


public class DisplayActivitySummaryAction extends LookupDispatchAction
{
   
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		
		ActivitiesForm form = (ActivitiesForm) aForm;
		activityDescriptionHelper(form);
/*		
		String comments = form.getComments();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.setComments(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		*/
		form.setAction(UIConstants.SUMMARY);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		ActivitiesForm form = (ActivitiesForm) aForm;
		form.setLatitude("");
		form.setLongitude("");
		form.setUpdateBtnAllowed("");
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
		
		
		String forward = UIConstants.SUCCESS;
		if(form.getAction().equalsIgnoreCase("JUVPROFILECASEPLAN")) {
			forward = "juvProfileCaseplan";
		}
		form.setAction(UIConstants.VIEW_DETAIL);
		form.setSecondaryAction("");
		form.setSelectedValue("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		boolean isJPOORCLMOfCurrentCasefile = false;
		boolean showComments = false;
		String currentUser = UIUtil.getCurrentUserID();
		String clm = juvenileCasefileForm.getCaseloadManagerId();
		String jpo = juvenileCasefileForm.getProbationOfficerLogonId();
		

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
		form.setActivityTimeStr(activityDetails.getActivityTime());
		//u.s 11097 starts
		if(currentUser.equals(clm)||currentUser.equals(jpo)){
			isJPOORCLMOfCurrentCasefile = true;
		}
		
		if(activityDetails.getPermissionId()!=null && activityDetails.getPermissionId().equals("C") && !isJPOORCLMOfCurrentCasefile)
		{
			showComments= true;
		}
		if(!showComments){
			form.setComments(activityDetails.getComments());
		}
		//u.s 11097 ends.
		
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
		
		if( activityDetails.getUpdateComments() == null && StringUtils.isNotBlank( activityDetails.getLatitude()) ){
		    
		    if( activityDetails.getCreateUserID() != null && currentUser.equalsIgnoreCase( activityDetails.getCreateUserID().trim()) ){
			form.setUpdateBtnAllowed("Y");
		    }		    
		}
		
		form.setUpdateComments( activityDetails.getUpdateComments());
		form.setActivityEndTimeStr(activityDetails.getActivityendTime() );
		if( StringUtils.isNotBlank( activityDetails.getLatitude()) ){
		    
		    String x = activityDetails.getLatitude();
		    x = x.substring(0, 10);
		    form.setLatitude(x);
		}
		  
		if( StringUtils.isNotBlank( activityDetails.getLongitude()) ){
		    
		    String y = activityDetails.getLongitude();
		    y = y.substring(0, 10);
		    form.setLongitude(y);
		   }
		
		return aMapping.findForward(forward);
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
		
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActivitiesForm form = (ActivitiesForm) aForm;
		if(form.getVendorActivity()!=null && !form.getVendorActivity().equalsIgnoreCase("Y")){
		form.setSelectedCategoryId(form.getActivityCatIdForReload());
		form.setSelectedTypeId(form.getActivityTypeIdForReload());
		form.setSelectedDescriptionId(form.getActivityDescForReload());}
		//form.setActivityResults(new ArrayList());
		//form.clearAll();
		
		GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);

		JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
				.postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
		if(form.getVendorActivity()!=null && !form.getVendorActivity().equalsIgnoreCase("Y")){
		form.setActivityCodes(response.getReturnValues());}
		
	/*	//User Story 35099 for Vendor Activities 
		if(form.getVendorActivity()!=null && form.getVendorActivity().equalsIgnoreCase("Y")){
			 ISecurityManager securityManager = (ISecurityManager) mojo.km.context.ContextManager.getSession().get("ISecurityManager");
		        String userTypeId = securityManager.getIUserInfo().getUserTypeId();
		        String logID= securityManager.getIUserInfo().getJIMSLogonId();
		        String logIDCompare = logID.substring(logID.length() - 3);
		        if(userTypeId.equalsIgnoreCase("BA")){
		        ArrayList<JuvenileActivityTypeCodeResponseEvent> activityCodesList= (ArrayList<JuvenileActivityTypeCodeResponseEvent>) response.getReturnValues();
		        for( Iterator activityListItr = activityCodesList.iterator(); activityListItr.hasNext();){
		        	JuvenileActivityTypeCodeResponseEvent currentActivityCode = (JuvenileActivityTypeCodeResponseEvent) activityListItr.next();
		        	Collection activityCodesModified = new ArrayList();
		        	if (currentActivityCode.getCode().equalsIgnoreCase("VEN")){
		        		int size1=currentActivityCode.getSubTypes().size(); 
					   for (int i=size1-1; i> -1; i--){
						   JuvenileActivityTypeCodeResponseEvent t =(JuvenileActivityTypeCodeResponseEvent) currentActivityCode.getSubTypes().get(i);
						   if(!t.getCode().equalsIgnoreCase(logIDCompare)) {
							   currentActivityCode.subTypes.remove(i);
							        			
								}
					   activityCodesModified.add(currentActivityCode);
			         	form.setActivityCodes(activityCodesModified);}
		        	}
		        }
		        }
		}*/
		
		
		form.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
		
		return aMapping.findForward(UIConstants.BACK);
	}	
	
	public ActionForward add(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActivitiesForm form = (ActivitiesForm) aForm;
		ActivityResponseEvent casefileActivity = this.createActivity(form);
		form.getNewActivities().add(casefileActivity);
		String vendorActivity= form.getVendorActivity();
		
		 // user-story #32796
        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
        if(!(vendorActivity==null) && vendorActivity.equalsIgnoreCase("Y")){
        }else{
        if(casefileForm!=null && casefileForm.getSupervisionCategoryId()!=null)
        {
        	if(casefileForm.getSupervisionCategoryId().equals("AR")){
        		form.setSelectedCategoryId("RES");
        	}else if(casefileForm.getSupervisionCategoryId().equals("AC") || casefileForm.getSupervisionCategoryId().equals("DA")){
        		form.setSelectedCategoryId("COM");
        	}else if(casefileForm.getSupervisionCategoryId().equals("AD")){
        		form.setSelectedCategoryId("COU");
        	}else if(casefileForm.getSupervisionCategoryId().equals("PP")){
        		form.setSelectedCategoryId("INT");
        	}
        }
        }
        //user-story #32796
		return aMapping.findForward(UIConstants.ADD);
	}
	
	private ActivityResponseEvent createActivity( ActivitiesForm form )
	{
		ActivityResponseEvent casefileActivity = new ActivityResponseEvent( );
		activityDescriptionHelper(form);
		casefileActivity.setActivityDate(form.getActivityDate());
		casefileActivity.setActivityDateAsStr(form.getActivityDateAsStr());
		casefileActivity.setCategoryId(form.getSelectedCategoryId());
		casefileActivity.setCategoryDesc(form.getCategoryDesc());
		casefileActivity.setTypeId(form.getSelectedTypeId());
		casefileActivity.setTypeDesc(form.getTypeDesc());
		casefileActivity.setCodeId(form.getSelectedDescriptionId());
		casefileActivity.setActivityDesc(form.getActivityDesc());
		casefileActivity.setComments(form.getComments());
		casefileActivity.setActivityTime(form.getActivityTimeStr());
		form.setActivityDateAsStr("");
		if(!form.getSelectedCategoryId().equalsIgnoreCase("VEN")){
			form.setSelectedCategoryId("");
			form.setSelectedTypeId("");
			form.setSelectedDescriptionId("");
		}
		form.setComments("");
		return casefileActivity;
	}
	private void activityDescriptionHelper(ActivitiesForm form)
	{
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
		while(iterActivityCodes.hasNext()) {
			JuvenileActivityTypeCodeResponseEvent respAct = (JuvenileActivityTypeCodeResponseEvent) iterActivityCodes.next();
			hashActivityCodes.put(respAct.getCode(), respAct.getDescription());
		}
		if(form.getSelectedCategoryId() != null && !(form.getSelectedCategoryId().trim().equals("")) )
		{
			form.setCategoryDesc(CodeHelper.getCodeDescriptionByCode(activitiesCategories, form.getSelectedCategoryId()));
		}
		else
		{
			form.setCategoryDesc("");
		}
		if(form.getSelectedTypeId() != null && !(form.getSelectedTypeId().trim().equals("")) )
		{
			form.setTypeDesc(CodeHelper.getCodeDescriptionByCode(activitiesTypes, form.getSelectedTypeId()));
		}
		else
		{
			form.setTypeDesc("");
		}   			
		if(form.getSelectedDescriptionId() != null && !(form.getSelectedDescriptionId().trim().equals("")) )
		{
			form.setActivityDesc(hashActivityCodes.get(form.getSelectedDescriptionId()).toString());
		}
		else
		{
			form.setActivityDesc("");
		}
	}
	
	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		ActivitiesForm form = (ActivitiesForm) aForm;
		String forward = UIConstants.SUCCESS;
		if(	  form.getActivityId()!= null && 
				!(form.getActivityId().equals("")) && 
				  form.getNewActivities()!= null )
		{
			Iterator iter = form.getNewActivities().iterator(); 
			while(iter.hasNext())
			{
				ActivityResponseEvent casefileActivity = (ActivityResponseEvent)iter.next();
				form.setActivityDateAsStr(casefileActivity.getActivityDateAsStr());
				form.setActivityEndTimeStr(casefileActivity.getActivityendTime());
				form.setCategoryDesc(casefileActivity.getCategoryDesc());
				form.setTypeDesc(casefileActivity.getTypeDesc());
				form.setActivityDesc(casefileActivity.getActivityDesc());
				form.setComments(casefileActivity.getComments());
				form.setUpdateComments(casefileActivity.getUpdateComments());
				form.setAction(UIConstants.VIEW_DETAIL);
				form.setSecondaryAction("");
				form.setSelectedValue("");
				
			}
		}
		return aMapping.findForward(forward);
		
	}
	
	public ActionForward finish(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.FINISH);
		ActivitiesForm activities = (ActivitiesForm)aForm;
		String vendorActivity = activities.getVendorActivity();
		 if(!(vendorActivity==null) && vendorActivity.equalsIgnoreCase("Y")){
			 activities.setActivityResults(new ArrayList());
		 }else{
		activities.setActivityCatIdForReload("");
		activities.setActivityDescForReload("");
		activities.setActivityTypeIdForReload("");
		activities.setSelectedCategoryId("selectAll");
		activities.setActivityResults(new ArrayList());}
		
		return forward;
	}
	//US 105436
		public ActionForward displayActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
		{
			ActivitiesForm form = (ActivitiesForm) aForm;
			String forward = "activityPopUp";
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			boolean showComments = false;
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
			//u.s 11097 starts
			/*if(currentUser.equals(clm)||currentUser.equals(jpo)){
				isJPOORCLMOfCurrentCasefile = true;
			}*/
			form.setAction("viewDetail");
			if(activityDetails.getPermissionId()!=null && activityDetails.getPermissionId().equals("C") )//&& !isJPOORCLMOfCurrentCasefile)
			{
				showComments= true;
			}
			if(!showComments){
				form.setComments(activityDetails.getComments());
			}
			//u.s 11097 ends.
			
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
			
			return aMapping.findForward(forward);
		}
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
		keyMap.put("button.link", "link");
		keyMap.put("button.addActivity", "add");
		keyMap.put("button.view", "view");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.linkActivity","displayActivity");
		return keyMap;
	}
}
