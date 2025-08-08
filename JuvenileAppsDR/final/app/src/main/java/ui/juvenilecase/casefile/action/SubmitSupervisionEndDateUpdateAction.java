// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\SubmitSupervisionEndDateUpdateAction.java

package ui.juvenilecase.casefile.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.UpdateJuvenileCasefileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper;

public class SubmitSupervisionEndDateUpdateAction extends LookupDispatchAction
{
	/**
	 * @roseuid 44CF776F0389
	 */
	public SubmitSupervisionEndDateUpdateAction()
	{
	}

	/*
	 * 
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) );
	}

	/*
	 * 
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.BACK ) );
	}

	/*
	 * 
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileCasefileForm form = (JuvenileCasefileForm)aForm;
		UpdateJuvenileCasefileEvent updateEvent = (UpdateJuvenileCasefileEvent)
				EventFactory.getInstance( JuvenileCasefileControllerServiceNames.UPDATEJUVENILECASEFILE );
		
		updateEvent.setSupervisionNumber( form.getSupervisionNum() );
		updateEvent.setSupervisionEndDate( form.getExpectedSupervisionEndDate() );
		//commented out for US 14459
		//updateEvent.setInterviewRiskNeeded(form.getIsInterviewAssessmentNeeded());
		updateEvent.setMAYSINeeded(form.getIsNewMAYSINeeded());
		updateEvent.setReferralRiskNeeded(form.getIsReferralAssessmentNeeded());

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( updateEvent );

		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( composite );

		// Generate notices for casefileclosing to JPO.
		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper();
		String superTypeID = form.getSupervisionTypeId() ;
		String supCatId = "";
		if (superTypeID != null) {
			supCatId = UIJuvenileCaseworkHelper.getSupCatFromType(superTypeID);
		}

		if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM ) || 
				StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES ) || 
				StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ ))
		{
			helper.sendPOSupervisionDueExpirePrior30daysNotification( updateEvent, form );
			helper.sendPOSupervisionDueExpirePrior60daysNotification( updateEvent, form );
		}
		
		boolean dateDiffFlg= false; //added for #24194
		if( form.getExpectedSupervisionEndDate() != null )
		{
			helper.sendPOSupervisionDueExpireAfter24hoursNotification( updateEvent, form );
			helper.sendCLMSupervisionDueExpireAfter24hoursNotification( updateEvent, form );
			//added for #24194 - calculate the no of days between current system date and expectedEndate 
			if(StringUtils.equalsIgnoreCase(supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES)){
				Calendar c1=Calendar.getInstance();
			    c1.setTime(DateUtil.getCurrentDate());
			    Calendar c2=Calendar.getInstance();
			    c2.setTime(form.getExpectedSupervisionEndDate());
			 
			    Date currentDate=c1.getTime(); 
			    Date expectedEndDate=c2.getTime();
						     
			    long diff=currentDate.getTime() - expectedEndDate.getTime(); //currentDate - expectedEndDate
			    int noofdays=Math.abs((int)(diff/(1000*24*60*60))); //Day in millisecs.
			    if(noofdays <= 30){
					dateDiffFlg = true;
				}
			    //added for #24194
			  //U.S #24194 
				//Resend the email if the expected end date is modified and the Current system date – Supervision Expected End Date = 30 days or less
				if( dateDiffFlg && form.getCaseStatusId().equalsIgnoreCase(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)){
					helper.sendPostAdjudicationResidential30daysEmailNotification(updateEvent, form); //30 day notice = Post Adjudication Residential (US #24194)
				}
				//U.S #24194 
			}
		}

		// End.

		form.setAction( UIConstants.CONFIRM );
		
		return aMapping.findForward( UIConstants.SUCCESS );
	}

	/* 
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put( "button.back", "back" );
		keyMap.put( "button.finish", "finish" );
		keyMap.put( "button.cancel", "cancel" );
		return keyMap;
	}
}
