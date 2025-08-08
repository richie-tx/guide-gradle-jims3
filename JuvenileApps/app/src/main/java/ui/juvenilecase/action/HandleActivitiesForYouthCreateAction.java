package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SearchJuvenileCasefileListEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.casefile.CreateActivityEvent;
import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import naming.CodeTableControllerServiceNames;
import mojo.km.messaging.EventFactory;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import pd.juvenile.Juvenile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.ProcessActivitiesForMultipleYouthForm;
import ui.juvenilecase.form.TransferredOffenseForm;

public class HandleActivitiesForYouthCreateAction extends JIMSBaseAction
{
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.next","next");
	keyMap.put("button.addJuvenile","addJuvenile");
	keyMap.put("button.removeJuvenile","removeJuvenile");
	keyMap.put("button.finish","finish");
	keyMap.put("button.cancel","cancel");
	keyMap.put("button.back","cancel");
	
    }
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("addActivitySuccess");
    }
    
    public ActionForward addJuvenile(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProcessActivitiesForMultipleYouthForm processActivitiesForMultipleYouthForm = (ProcessActivitiesForMultipleYouthForm)aForm;
	processActivitiesForMultipleYouthForm.setMessage("");
	if ( processActivitiesForMultipleYouthForm.getJuvenileId() != null
		&& processActivitiesForMultipleYouthForm.getJuvenileId().length() > 0 ) {
	    JuvenileProfileDetailResponseEvent juvenileResp = Juvenile.findDetailJuvenile( processActivitiesForMultipleYouthForm.getJuvenileId() );
	   // adding a check for no juvenile found as part of US 185889
	    if(juvenileResp ==  null){
		processActivitiesForMultipleYouthForm.setMessage("Juvenile " +  processActivitiesForMultipleYouthForm.getJuvenileId()+ " not found. You must enter a valid JuvenileID.");
		return aMapping.findForward("error");
	    }
	    
	    
	    
	    // US 185890 changes STARTS Do not allow the same juvenile entered twice on the same activity/date combination
	    String juvNum = processActivitiesForMultipleYouthForm.getJuvenileId().trim();
	    if (processActivitiesForMultipleYouthForm.getJuveniles()!= null && processActivitiesForMultipleYouthForm.getJuveniles().size() > 0)
	    {
		for (JuvenileProfileDetailResponseEvent juv : processActivitiesForMultipleYouthForm.getJuveniles())
		{
		    if (juv.getJuvenileNum().equalsIgnoreCase(juvNum))
		    {
			processActivitiesForMultipleYouthForm.setMessage("This juvenile has already been assigned to the selected activity date.");
			return aMapping.findForward("error");
		    }
		}
	    }
	    // US 185890 changes ENDS Do not allow the same juvenile entered twice on the same activity/date combination ENDS
	    
	    HashMap<String, String> map = new HashMap<String,String>();
	    if ( juvenileResp != null ){
        	    SearchJuvenileCasefileListEvent searchEvent = (SearchJuvenileCasefileListEvent)
        			EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILECASEFILELIST);
        	    juvenileResp.setComments(processActivitiesForMultipleYouthForm.getComments());
        	    searchEvent.setJuvenileId( processActivitiesForMultipleYouthForm.getJuvenileId() );
        	    List<JuvenileProfileCasefileListResponseEvent> casefiles = MessageUtil.postRequestListFilter(searchEvent,JuvenileProfileCasefileListResponseEvent.class);
        	    List<JuvenileProfileCasefileListResponseEvent> activeCasefiles = new ArrayList<>();
        	    for ( JuvenileProfileCasefileListResponseEvent casefile : casefiles ){
        		if ( "ACTIVE".equals( casefile.getCaseStatus() ) ){
        		    activeCasefiles.add(casefile);
        		}
        	    }
        	    if (activeCasefiles != null  && activeCasefiles.size() > 0 ) {
        		
        		Collections.sort(activeCasefiles, Collections.reverseOrder(new Comparator<JuvenileProfileCasefileListResponseEvent>(){
        		    @Override
        		    public int compare(JuvenileProfileCasefileListResponseEvent c1, JuvenileProfileCasefileListResponseEvent c2 ){
        			return c2.getSequenceNum().compareTo(c1.getSequenceNum());
        		    }
        		}));
        		
        		for ( JuvenileProfileCasefileListResponseEvent casefile : activeCasefiles ){
            		
        		    if ( UIConstants.POST_ADJUDICATION_RESIDENTIAL.equals( casefile.getSupervisionCategory() ) ){
        			
        			map.put(UIConstants.POST_ADJUDICATION_RESIDENTIAL, casefile.getSupervisionNum());
        			
            		      
            		}else if( UIConstants.POST_ADJUDICATION_COMMUNITY.equals( casefile.getSupervisionCategory() )) {
            		    
            		    	map.put(UIConstants.POST_ADJUDICATION_COMMUNITY, casefile.getSupervisionNum());
            		    	
            		}
            		else if( UIConstants.DEFERRED_ADJUDICATION.equals( casefile.getSupervisionCategory() )) {
            		    
            		    	map.put(UIConstants.DEFERRED_ADJUDICATION, casefile.getSupervisionNum());
        		    	
        		}
            		
            	    }
        		
        		if ( map.containsKey(UIConstants.POST_ADJUDICATION_RESIDENTIAL) ){
        		     
        		    	juvenileResp.setLatestCasefileId(map.get(UIConstants.POST_ADJUDICATION_RESIDENTIAL) );
        		    	
        		 }else if (map.containsKey(UIConstants.POST_ADJUDICATION_COMMUNITY)){
        		     
        		     	juvenileResp.setLatestCasefileId(map.get(UIConstants.POST_ADJUDICATION_COMMUNITY) );
        		     	
        		 }else if(map.containsKey(UIConstants.DEFERRED_ADJUDICATION)) {
        		     
        		     	juvenileResp.setLatestCasefileId(map.get(UIConstants.DEFERRED_ADJUDICATION) );
        		 }else {
        		     
        		     juvenileResp.setLatestCasefileId(activeCasefiles.get(0).getSupervisionNum());
        		 }
        		 
        	    }
    	    
        	    if (activeCasefiles.size() > 0 ) {
        		processActivitiesForMultipleYouthForm.getJuveniles().add(juvenileResp);
        	    } else {
        		processActivitiesForMultipleYouthForm.setMessage("There is no active casefile for Juvenile number " 
        			+  processActivitiesForMultipleYouthForm.getJuvenileId()+ ". Please add another Juvenile number." );
        	    }
	    }
	   
	}
	return aMapping.findForward("addActivitySuccess");
    }
    
    public ActionForward removeJuvenile(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProcessActivitiesForMultipleYouthForm processActivitiesForMultipleYouthForm = (ProcessActivitiesForMultipleYouthForm)aForm;
	int juvenileIndex = Integer.parseInt( aRequest.getParameter("juvenileIndex") );
	if ( juvenileIndex >=0 ){
	    processActivitiesForMultipleYouthForm.getJuveniles().remove(juvenileIndex);
	}
	
	return aMapping.findForward("addActivitySuccess");
    }
    
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProcessActivitiesForMultipleYouthForm processActivitiesForMultipleYouthForm = (ProcessActivitiesForMultipleYouthForm)aForm;
	
	if ( processActivitiesForMultipleYouthForm.getJuveniles().size() > 0 ) {
	    for ( JuvenileProfileDetailResponseEvent juvenile : processActivitiesForMultipleYouthForm.getJuveniles() ){
		/*CreateActivityEvent createEvent = (CreateActivityEvent)EventFactory.getInstance( JuvenileCasefileControllerServiceNames.CREATEACTIVITY ) ;
		createEvent.setActivityDate( DateUtil.stringToDate( processActivitiesForMultipleYouthForm.getActivityDate(), DateUtil.DATE_FMT_1  ) );
		createEvent.setActivityCategoryId( processActivitiesForMultipleYouthForm.getSelectedCategoryId() );
		createEvent.setActivityTypeId( processActivitiesForMultipleYouthForm.getSelectedTypeId() );
		createEvent.setActivityCodeId( processActivitiesForMultipleYouthForm.getSelectedActivityId() );
		createEvent.setComments( juvenile.getComments() );
		createEvent.setSupervisionNumber( juvenile.getLatestCasefileId() );
		createEvent.setActivityTime( new SimpleDateFormat("HH:mm").format( new Date().getTime()) );
		MessageUtil.postRequest( createEvent ) ;*/ //commented for US 185889
		//added for US 185889 STARTS
		Activity activity = new Activity();
		activity.setSupervisionNumber(juvenile.getLatestCasefileId());
		activity.setActivityCodeId(processActivitiesForMultipleYouthForm.getSelectedActivityId());
		activity.setComments(juvenile.getComments());
		activity.setActivityDate(DateUtil.stringToDate( processActivitiesForMultipleYouthForm.getActivityDate(), DateUtil.DATE_FMT_1  ));
		activity.setActivityTime(new SimpleDateFormat("HH:mm").format( new Date().getTime()));
		IHome home = new Home();
		home.bind(activity);
		juvenile.setActivityId(activity.getOID());//NEED OID to display in the summary screen 
		//added for US 185889 ENDS
	    }
	} else {
	    processActivitiesForMultipleYouthForm.setMessage("Juvenile is required. Please add a Juvenile.");
	    return aMapping.findForward("error");
	}
	
	processActivitiesForMultipleYouthForm.setMessage("Activity for Youth successfully added.");
	
	return aMapping.findForward("success");
    }
    
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProcessActivitiesForMultipleYouthForm form = (ProcessActivitiesForMultipleYouthForm) aForm;
	form.clear();
	GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);
	reqEvent.setAction("add");
	JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
		.postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
	form.setActivityCodes(response.getReturnValues());
	return aMapping.findForward("cancelAddActivity");
    }

}
