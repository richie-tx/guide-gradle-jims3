package ui.juvenilecase.activities.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap ;
import java.util.Iterator;
import java.util.Map ;

import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import messaging.casefile.CreateActivityEvent ;
import messaging.casefile.UpdateActivityEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.messaging.EventFactory ;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil ;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames ;
import naming.UIConstants ;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm ;
import org.apache.struts.action.ActionForward ;
import org.apache.struts.action.ActionMapping ;
import org.apache.struts.actions.LookupDispatchAction ;

import ui.juvenilecase.UIJuvenileHelper ;
import ui.juvenilecase.activities.form.ActivitiesForm ;
import ui.juvenilecase.form.JuvenileCasefileForm ;

public class SubmitActivityAction extends LookupDispatchAction
{
	/**
	 * @roseuid 455A2A8E0189
	 */
	public SubmitActivityAction()
	{
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActivitiesForm form = (ActivitiesForm)aForm ;
		String activityTimePattern = new String("hh:mm a");
		String activityTime24Pattern = new String ("HH:mm");
		Date   activityTime    = null;
		form.setSecondaryAction( "" ) ;
		form.setSelectedValue( "" ) ;
		form.setAction( "" ) ;
		Iterator iter = form.getNewActivities().iterator(); 
		while(iter.hasNext())
		{
			form.setAction( UIConstants.FINISH ) ;
			ActivityResponseEvent casefileActivity = (ActivityResponseEvent)iter.next();
			CreateActivityEvent reqEvent = (CreateActivityEvent)EventFactory.getInstance( JuvenileCasefileControllerServiceNames.CREATEACTIVITY ) ;
			if( reqEvent != null )
			{
				JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;		
				if( juvenileCasefileForm != null )
				{
					reqEvent.setSupervisionNumber( juvenileCasefileForm.getSupervisionNum() ) ;
				}
	
				if( form.getActivityDate() == null  )
				{
					form.setActivityDate( new Date() ) ;
				}
				reqEvent.setActivityDate( casefileActivity.getActivityDate() ) ;
				reqEvent.setActivityCategoryId( casefileActivity.getCategoryId()) ;
				//reqEvent.setActivityCategoryId( form.getSelectedCategoryId() ) ;
				reqEvent.setActivityTypeId( casefileActivity.getTypeId()) ;
				//reqEvent.setActivityTypeId( form.getSelectedTypeId()) ;
				reqEvent.setActivityCodeId( casefileActivity.getCodeId()) ;
				//reqEvent.setActivityCodeId( form.getSelectedDescriptionId() ) ;
				reqEvent.setComments( casefileActivity.getComments() );
				//reqEvent.setComments( form.getComments() ) ;
				if ( casefileActivity.getActivityTime() != null ) {
				    activityTime = DateUtil.stringToDate(casefileActivity.getActivityTime(), activityTimePattern);
				    reqEvent.setActivityTime(  DateUtil.dateToString(activityTime, activityTime24Pattern) );
				}
				
				MessageUtil.postRequest( reqEvent ) ;
			}
		}
		
		return aMapping.findForward( UIConstants.CASEFILE_ACTIVITY_LIST_SUCCESS) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward updateComments( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    	ActivitiesForm form = (ActivitiesForm)aForm ;
	    	
	    	if( StringUtils.isEmpty(form.getActivityDateAsStr())){
	    	    
	    	    form.setActivityDateAsStr(DateUtil.dateToString(form.getActivityDate(), "MM/dd/yyyy"));
	    	}
	    	
		ActionForward forward = aMapping.findForward( UIConstants.UPDATE ) ;
		return forward ;
	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    	ActivitiesForm form = (ActivitiesForm)aForm ;
	    	form.setAction("");
		ActionForward forward = aMapping.findForward( UIConstants.NEXT ) ;
		return forward ;
	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward casefileActivityList( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActivitiesForm form = (ActivitiesForm)aForm ;
		if(form.getVendorActivity()==null){
		form.setSelectedCategoryId( "" ) ;}
		//added below -bug fix for 143170
		form.setActivityResults(new ArrayList());
	        form.clearAll();
		GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
	                .getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);

	        JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
	                .postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
	        
	        form.setActivityCodes(response.getReturnValues());
	        ///
		ActionForward forward = aMapping.findForward( UIConstants.CASEFILE_ACTIVITY_LIST_SUCCESS ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward profileActivityList( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;
		if( juvenileCasefileForm != null )
		{
			UIJuvenileHelper.populateJuvenileProfileHeaderForm( aRequest, juvenileCasefileForm.getJuvenileNum() ) ;
		}
		//added below -bug fix for 143170
		ActivitiesForm form = (ActivitiesForm)aForm ;
		form.setActivityResults(new ArrayList());
	        form.clearAll();
	        if(form.getVendorActivity()==null){
			form.setSelectedCategoryId( "" ) ;}
		GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
	                .getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);

	        JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
	                .postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
	        
	        form.setActivityCodes(response.getReturnValues());	        
	        //
		ActionForward forward = aMapping.findForward( UIConstants.PROFILE_ACTIVITY_LIST_SUCCESS ) ;
		return forward ;
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward updateActivityComment( ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse )
{
	    ActivitiesForm form = (ActivitiesForm)aForm ;
	
	    UpdateActivityEvent updateEvent = (UpdateActivityEvent)EventFactory.getInstance( JuvenileCasefileControllerServiceNames.UPDATEACTIVITY);
	    updateEvent.setUpdateComments(form.getUpdateComments());
	    updateEvent.setActivityId(form.getActivityId());
	    
	    MessageUtil.postRequest( updateEvent );
	
	    form.setAction( UIConstants.SUCCESS ) ;
	    return aMapping.findForward( UIConstants.UPDATE_SUCCESS) ;
}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap() ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.submit", "updateActivityComment" ) ;
		keyMap.put( "button.next", "next" ) ;
		keyMap.put( "button.casefileActivityList", "casefileActivityList" ) ;
		keyMap.put( "button.profileActivityList", "profileActivityList" ) ;
		keyMap.put( "button.updateComments", "updateComments" ) ;
		
		return keyMap ;
	}
}
