package ui.juvenilecase.activities.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.activities.form.ActivitiesForm;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;

/**
 * @author dapte
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayActivitiesAction extends LookupDispatchAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward displayActivities(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	ISecurityManager securityManager = (ISecurityManager) mojo.km.context.ContextManager.getSession().get("ISecurityManager");
        String userTypeId = securityManager.getIUserInfo().getUserTypeId();
        String logID= securityManager.getIUserInfo().getJIMSLogonId();
        //==========================
        String jims2logon = securityManager.getIUserInfo().getJIMS2LogonId();
        int position = 6;
        String jims2LogonType = null;
        if(jims2logon != null){
            jims2LogonType = jims2logon.substring(position, jims2logon.length() - 4);
        }
        //===========================
        String logIDCompare = logID.substring(logID.length() - 3);
     // Task 39013	    	
    	String superVisionID = aRequest.getParameter(PDJuvenileCaseConstants.SUPERVISIONNUM_PARAM);
    	//get program referral status too
    	String progrefStatus = aRequest.getParameter(PDJuvenileCaseConstants.PROGREFERRALSTATUS_PARAM);
    	
      if ( superVisionID != null){
    	// Populating juvenilecasefileForm from  
    	UIJuvenileCaseworkHelper.populateJuvenileCasefileForm( 
				UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ), superVisionID );}      
        ActivitiesForm form = (ActivitiesForm) aForm;        
        if (form == null)
        {
            form = new ActivitiesForm();
            form.setVendorActivity("");
            aRequest.getSession().setAttribute(UIConstants.ACTIVITIES_FORM, form);
        }
        form.setProgrefStatus(progrefStatus);
        // bug fix for 100840
        HttpSession session = aRequest.getSession();
        JuvenileBriefingDetailsForm juvenileBriefingForm = null;

        juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

        if (juvenileBriefingForm == null)
        {

            juvenileBriefingForm = new JuvenileBriefingDetailsForm();
            setProfileDetail(UIJuvenileCaseworkHelper.getHeaderForm( aRequest, true ).getJuvenileNum(), juvenileBriefingForm);
            session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
        }
        //
        if(!userTypeId.equalsIgnoreCase("BA")){
        	form.setVendorActivity("");
        }
        form.setActivityCatIdForReload("");
        form.setActivityDescForReload("");
        form.setActivityTypeIdForReload("");
        form.setActivityResults(new ArrayList());
        form.clearAll();
        

        GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);

        JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
                .postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
        // User Story 35099 for Vendor Activities 
        
        if(userTypeId.equalsIgnoreCase("BA")){
       // ArrayList<JuvenileActivityTypeCodeResponseEvent> activityCodesList= (ArrayList<JuvenileActivityTypeCodeResponseEvent>) response.getReturnValues();
        /*for( Iterator activityListItr = activityCodesList.iterator(); activityListItr.hasNext();){
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
			   form.setVendorActivity("Y");
			   form.setSelectedTypeId("BAQ");
		       activityCodesModified.add(currentActivityCode);
		       form.setActivityCodes(activityCodesModified);
        	}
        }
        }else{   */    
        form.setActivityCodes(response.getReturnValues());
        form.setVendorActivity("Y");
	form.setSelectedCategoryId("VEN");
	     	/*if(logIDCompare.equals("BAQ"))	{
		    	form.setSelectedTypeId("BAQ");
		    	}
  		if(logIDCompare.equals("BVB")){	
  		    	form.setSelectedTypeId("BVB");	
  		    	}
  		if(logIDCompare.equals("WCH")){
  		    	form.setSelectedTypeId("WCH"); 
  		    	}*/ //commented for Bug# 49920
	if (!logIDCompare.equalsIgnoreCase("null")){
		 form.setSelectedTypeId(logIDCompare.toUpperCase().toString()); //added for Bug# 49920: to include more activity types for Vendor
		}
  	//form.setSelectedTypeId(logIDCompare.toUpperCase().toString());//added for bug# 49920
        
        }
        //else if (jims2LogonType.equalsIgnoreCase("MBK")  || logIDCompare.equalsIgnoreCase("HRJ")) {
        //    form.setActivityCodes(response.getReturnValues());
        //    form.setSelectedCategoryId("VEN");
        //    form.setSelectedTypeId(logIDCompare);
        //    form.setVendorActivity("Y");		
    		
    	//       ArrayList<JuvenileActivityTypeCodeResponseEvent> activityCodesList= (ArrayList<JuvenileActivityTypeCodeResponseEvent>) response.getReturnValues();
        //    for( Iterator activityListItr = activityCodesList.iterator(); activityListItr.hasNext();){
    	//        	JuvenileActivityTypeCodeResponseEvent currentActivityCode = (JuvenileActivityTypeCodeResponseEvent) activityListItr.next();
    	//        	Collection activityCodesModified = new ArrayList();
    	//        	if (currentActivityCode.getCode().equalsIgnoreCase("VEN")){
    	//        		int size1=currentActivityCode.getSubTypes().size(); 
    	//			   for (int i=size1-1; i> -1; i--){
    	//				   JuvenileActivityTypeCodeResponseEvent t =(JuvenileActivityTypeCodeResponseEvent) currentActivityCode.getSubTypes().get(i);
    	//				   if(!t.getCode().equalsIgnoreCase(logIDCompare)) {
    	//					   currentActivityCode.subTypes.remove(i);
    	//				   		}
    	//				   }

    	//		       form.setActivityTypeList(currentActivityCode.getSubTypes());
    	//		       
    	//		   form.setVendorActivity("Y");
			   //form.setSelectedTypeId("BAQ");
	//	       activityCodesModified.add(currentActivityCode);
	//	       form.setActivityCodes(activityCodesModified);
    	//        	}
    	//        }
    		
        // }
        else{
        	 form.setActivityCodes(response.getReturnValues());
        	 form.setSelectedCategoryId("selectAll");
        }
       
 		 //default to all. on load default ALL - U.S 11098
       

        form.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
        
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }
    
    private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	reqProfileMain.setJuvenileNum(juvenileNum);
	reqProfileMain.setFromProfile(form.getFromProfile());
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	form.setJisInfo(new JuvenileJISResponseEvent());
	if (juvProfileRE != null)
	{
	    form.setProfileDetail(juvProfileRE);
	    form.setProfileDescriptions();

	    if (juvProfileRE.getDateOfBirth() != null)
	    { // Get age based on
	      // year
		int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
		if (age > 0)
		{
		    form.setAge(String.valueOf(age));
		}
		else
		{
		    form.setAge(UIConstants.EMPTY_STRING);
		}
	    }
	    Collection jisResps = juvProfileRE.getJisInfo();
	    if (jisResps != null)
	    {
		Collections.sort((List) jisResps);
		Iterator jisIter = jisResps.iterator();
		if (jisIter.hasNext())
		{
		    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
		    form.setJisInfo(jis);
		}
	    }

	    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
	    //U.S 88526
	    if (juvProfileRE.getHispanic() != null)
	    {
		if (juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
		{
		    form.setHispanic(UIConstants.YES_FULL_TEXT);
		}
		else
		{
		    form.setHispanic(UIConstants.NO_FULL_TEXT);
		}
	    }
	    else
	    {
		form.setHispanic(UIConstants.EMPTY_STRING);
	    }
	}
    }
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.link", "displayActivities");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }
}
