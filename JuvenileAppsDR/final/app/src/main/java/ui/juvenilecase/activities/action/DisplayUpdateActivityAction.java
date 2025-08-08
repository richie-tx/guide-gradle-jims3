package ui.juvenilecase.activities.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.security.SecurityUIHelper;

/**
 * @author C_NAggarwal
 * 
 */
public class DisplayUpdateActivityAction extends LookupDispatchAction
{
	private static final String REMOVE_ACTIVITY = "removeActivity";

    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward addActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActivitiesForm form = (ActivitiesForm) aForm;
        if (form == null)
        {
            form = new ActivitiesForm();
            aRequest.getSession().setAttribute(UIConstants.ACTIVITIES_FORM, form);
        }
       form.setActivityTypeIdForReload(form.getSelectedTypeId());
       form.setActivityDescForReload(form.getSelectedDescriptionId());
       form.setActivityCatIdForReload(form.getSelectedCategoryId());
 
       form.clearAll();
       
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);
        reqEvent.setAction("add");

        JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
                .postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
               form.setActivityCodes(response.getReturnValues());	
    // user-story 35099
               
               // To set category based on usertype
           ISecurityManager securityManager = (ISecurityManager) mojo.km.context.ContextManager.getSession().get("ISecurityManager");
           String userTypeId = securityManager.getIUserInfo().getUserTypeId();
           String logID= securityManager.getIUserInfo().getJIMSLogonId();
           String logIDCompare = logID.substring(logID.length() - 3);
           
           //String jims2logon = securityManager.getIUserInfo().getJIMS2LogonId();
           //int position = 6;
           //String jims2LogonType = null;
           //if(jims2logon != null){
           //    jims2LogonType = jims2logon.substring(position, jims2logon.length() - 4);
           //}
           // user-story #147139
           /*if (logIDCompare.equalsIgnoreCase("HRJ")) { 
               
               form.setActivityCodes(response.getReturnValues());
               form.setSelectedCategoryId("VEN");
               form.setSelectedTypeId(logIDCompare);
               form.setVendorActivity("Y");
               
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
   					   }

   			       form.setActivityTypeList(currentActivityCode.getSubTypes());
   			       form.setVendorActivity("Y");
   	        	}
   	        }
           
           }
           else*/ //commenting this as we dont have to check individual SP for defaulting but to make that as BA in Security Manager
           if( userTypeId.equalsIgnoreCase("BA"))
           		{	form.setSelectedCategoryId("VEN");
           			/*if(logIDCompare.equals("BAQ")){
           			    form.setSelectedTypeId("BAQ");
           			    }
           			if(logIDCompare.equals("BVB")){	
           			    form.setSelectedTypeId("BVB");
           			    }
           			if(logIDCompare.equals("WCH")){
           			    form.setSelectedTypeId("WCH");
           			    }*/ //commented for Bug# 49920
           			//form.getActivityCodes().
           		form.setVendorActivity("Y");
           		form.setActivityCodes(response.getReturnValues());	 
           		if (!logIDCompare.equalsIgnoreCase("null")){
           		 form.setSelectedTypeId(logIDCompare.toUpperCase().toString()); //added for Bug# 49920: to include more activity types for Vendor
           		}
           	}
           	
        else{
        // user-story #32796
        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
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
        //user-story #32796
        
        }
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward viewActivities(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActivitiesForm form = (ActivitiesForm) aForm;
        JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute(
                "juvenileCasefileForm");
        form.setAction(UIConstants.CONFIRM);
        form.setSecondaryAction("");
        form.setSelectedValue("");
        form.setActivityTypeIdForReload(form.getSelectedTypeId());
        form.setActivityDescForReload(form.getSelectedDescriptionId());
        form.setActivityCatIdForReload(form.getSelectedCategoryId());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        GetActivitiesEvent reqEvent = (GetActivitiesEvent) EventFactory
                .getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);

        reqEvent.setCasefileID(juvenileCasefileForm.getSupervisionNum());
        
        String serviceProviderId = SecurityUIHelper.getServiceProviderId();
        
        if (juvenileCasefileForm.getJuvenileNum() != null && ((serviceProviderId != null && serviceProviderId != "") || (reqEvent.getCasefileID() == null || reqEvent.getCasefileID() == ""))) {           
         reqEvent.setJuvenileNum(juvenileCasefileForm.getJuvenileNum());
        }
        if (form.getStartDate() != null) {
        	reqEvent.setStartDate(form.getStartDate());
        }
        if (form.getEndDate() != null) {
        	Date endDate = form.getEndDate();
        	endDate = new Date((endDate.getTime()) + 24*3600*1000);
        	reqEvent.setEndDate(endDate);
        }
        

        if (form.getSelectedCategoryId().equalsIgnoreCase("selectAll"))
        {
            reqEvent.setCategoryId("");
        }
        else
        {
            reqEvent.setCategoryId(form.getSelectedCategoryId());
        }
        

        reqEvent.setActivityTypeId(form.getSelectedTypeId());
        reqEvent.setActivityCodeId(form.getSelectedDescriptionId());
        
        if ( form.getActivityTimeStr() != null 
        	&& form.getActivityTimeStr().length() > 0) {
            reqEvent.setActivityTime(form.getActivityTimeStr());
        }

        List activityResults = MessageUtil.postRequestListFilter(reqEvent, ActivityResponseEvent.class);

        form.setActivityResults(activityResults);
        return aMapping.findForward(UIConstants.LISTSUCCESS);
    }
    /*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward removeActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
    			HttpServletResponse aResponse)
    {
    	ActivitiesForm activityForm = (ActivitiesForm)aForm;
    	ArrayList arr = (ArrayList)activityForm.getNewActivities();
    	if(activityForm.getActivityId()!=null && !activityForm.getActivityId().equals("") && activityForm.getNewActivities()!=null )
    	{
    		int offset = Integer.parseInt(activityForm.getActivityId());
    		if(arr.size()>offset)
    			arr.remove(offset);
    		
    		activityForm.setNewActivities(arr);
    	}
    	return aMapping.findForward(REMOVE_ACTIVITY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.submit", "submit");
        keyMap.put("button.addMoreActivities", "addActivity");
        keyMap.put("button.viewActivities", "viewActivities");
        keyMap.put("button.remove", "removeActivity");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.reset", "reset");
        return keyMap;
    }
}
