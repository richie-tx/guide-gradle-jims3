package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileMasterStatusEvent;
import messaging.juvenile.SaveJuvJPOOfRecEvent;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JuvenileMasterStatusResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileReferralProbationDatesResponseEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilewarrant.UpdateJJSPetitionsTerminationDateEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.security.RegionType;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;

public class SubmitCasefileClosingDataAction extends LookupDispatchAction
{
	
	/**
	 * @roseuid 4396047D00FE
	 */
	public SubmitCasefileClosingDataAction()
	{
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * 
	 */
	public ActionForward saveAndContinue(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		CasefileClosingForm myClosingForm = (CasefileClosingForm)aForm;
// check for missing controlling referral values on both forms		
		if (casefileForm.getControllingReferralNumber() == null || "".equals(casefileForm.getControllingReferralNumber() ) ) {
			if (myClosingForm.getControllingReferral() != null && !"".equals(myClosingForm.getControllingReferral() ) ) { 
				casefileForm.setControllingReferralNumber(myClosingForm.getControllingReferral());
			}
		}	
		if (myClosingForm.getControllingReferral() == null || "".equals(myClosingForm.getControllingReferral() ) ) {
			if (casefileForm.getControllingReferralNumber() != null && !"".equals(casefileForm.getControllingReferralNumber() ) ) { 
				myClosingForm.setControllingReferral(casefileForm.getControllingReferralNumber());
			}
		}
		//added by Sruti for ER JIMS200076599
		//getting the program referral list for the casefile
		Date closingSupEndDAte = DateUtil.stringToDate(myClosingForm.getSupervisionEndDate(), "MM/dd/yyyy");
        String casefileId = myClosingForm.getSupervisionNumber( );
        List<ProgramReferralResponseEvent> closedReferrals = UIProgramReferralHelper.getClosedCasefileProgramReferrals( casefileId ) ;         
        
        String juvenileNum = myClosingForm.getJuvenileNum();
        
        //flag to check for one or more program referrals.
        boolean programReferralListEmail = false; //changes added for #13331
        boolean casefileClosingEmail = false; //changes added for #13331
        boolean noProgramRefferals = false; //changes added for #13331
        RegionType regionType = new RegionType();
        String region 	 = regionType.getRegion();
         //List progRefDetailList;
         StringBuffer sbProgRefDetails = new StringBuffer();
        
       
		if(closedReferrals != null && !closedReferrals.isEmpty()){
			Iterator<ProgramReferralResponseEvent> clProgRefItr = closedReferrals.iterator();
			while (clProgRefItr.hasNext()){
				ProgramReferralResponseEvent progRefResp = clProgRefItr.next();
				Date progRefEndDate = progRefResp.getEndDate();
				String progRefId = progRefResp.getReferralId();
			//checking the program referrals whose end date is after the casefile close date
				//scenario 1: If the supervision has one or more program referrals with an end date which is greater than the casefile closing date, send Program Referral List Closing email.
				if(closingSupEndDAte.before(progRefEndDate)){
					String progRefDetails = progRefId+" - End Date :"+DateUtil.dateToString(progRefEndDate, "MM/dd/yyyy")+"";
					sbProgRefDetails.append("<br/>");
					sbProgRefDetails.append(progRefDetails);
					programReferralListEmail = true;  //changes added for #13331
				}
				//scenario 2: If the supervision has one or more program referrals with an end date which is less than or equal to the casefile closing date, send Casefile closing email.
				if(closingSupEndDAte.after(progRefEndDate) || closingSupEndDAte.equals(progRefEndDate)){
					// do the other email content.
					casefileClosingEmail = true; //changes added for #13331
				}
			}		
		}else{
			noProgramRefferals= true;//changes added for #13331
		}
		
		//pull all casefile list. Similar to casefile info.  //changes added for #13331 starts
		SearchJuvenileProfileCasefileListEvent search = (SearchJuvenileProfileCasefileListEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);
		search.setJuvenileId(casefileForm.getJuvenileNum());
		List<JuvenileProfileCasefileListResponseEvent> caseFileCollection = MessageUtil.postRequestListFilter(search,JuvenileProfileCasefileListResponseEvent.class);
		Collections.sort(caseFileCollection);
		 
		if(casefileClosingEmail||programReferralListEmail||noProgramRefferals){
			 SendEmailEvent sendEmailEvent = new SendEmailEvent();
             sendEmailEvent.setFromAddress(UIConstants.JIMS2_NOTIFICATION_TEAM);
             UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, UIConstants.DATA_CORRECTION_TEAM);
             String userName = UIUtil.getCurrentUserName();
	        if(casefileClosingEmail||noProgramRefferals){      
				    StringBuffer message = new StringBuffer(100);
				    setCommonMessage(message,myClosingForm,casefileForm,sendEmailEvent,caseFileCollection);
			
				    //set subject
				    StringBuffer buffer = new StringBuffer(100);        
					buffer.append(" Casefile has been Closed");
					buffer.append(" Juvenile Number# ");
					buffer.append(juvenileNum);
					buffer.append(" Casefile# ");
					buffer.append(casefileId);
					sendEmailEvent.setSubject(buffer.toString() + " - " + region);
					sendEmailEvent.setContentType("text/html");
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatch.postEvent(sendEmailEvent);
	        }
	        if(programReferralListEmail){
				    StringBuffer message = new StringBuffer(100);
				    message.append("Officer ");
			        message.append(userName);
			        message.append(" is closing the Casefile ");
			        message.append(casefileId);
			        message.append(" with Closing Date as ");
			        message.append(DateUtil.dateToString(closingSupEndDAte, "MM/dd/yyyy"));
			        message.append(". Below are the Program Referral(s) with the End Date which is after the Closing Date of the Casefile: ");
			        message.append(sbProgRefDetails);
			        message.append("<br/>");
			        message.append("<br/>");
			        setCommonMessage(message,myClosingForm,casefileForm,sendEmailEvent,caseFileCollection);
			        
			        //set subject
				    StringBuffer buffer = new StringBuffer(100);	    	
			    	buffer.append(" Program Referral EndDate is after Casefile Closing Date");
			    	buffer.append(" Juvenile Number# ");
			    	buffer.append(juvenileNum);
			    	buffer.append(" Casefile# ");
			    	buffer.append(casefileId);
			    	sendEmailEvent.setSubject(buffer.toString() +  " - " + region);
			    	sendEmailEvent.setContentType("text/html");  //changes added for #13331 ends
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatch.postEvent(sendEmailEvent);
	        }
		}
		
		//Ended
		
		myClosingForm.setAction(UIConstants.CONFIRM_UPDATE);
		myClosingForm.setSecondaryAction(UIConstants.EMPTY_STRING);
		myClosingForm.setSelectedValue(UIConstants.EMPTY_STRING);

		UpdateCasefileClosingEvent myEvent = 
				UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
						myClosingForm, UIConstants.CASEFILE_CASE_STATUS_CLOSING_PENDING);
		
		myEvent.setCasefileID(casefileForm.getSupervisionNum());
		myEvent.setJuvUnitId(casefileForm.getJuvUnitId());
		
		if (myEvent.getSupervisionOutcomeId() == null || myEvent.getSupervisionOutcomeId().equals("") ||
			myEvent.getClosingEvaluation() == null || myEvent.getClosingEvaluation().equals("") ||
			myEvent.getSupervisionEndDate() == null || myEvent.getSupervisionEndDate().equals("")) {
				ActionMessage myError=new ActionMessage("error.casefileUnavailable",casefileForm.getSupervisionNum());
				ArrayList coll=new ArrayList();
				coll.add(myError);
				sendToErrorPage(aRequest,coll);
				return aMapping.findForward(UIConstants.CANCEL);
		} 
		
		if ( myClosingForm.getSupervisionOutcomeId().equals("D")){
		    myEvent.setYouthDeathReason( myClosingForm.getYouthDeathReason());
		    myEvent.setYouthDeathVerification( myClosingForm.getYouthDeathVerification());
		    if (myClosingForm.getDeathDate() != null
			    && myClosingForm.getDeathDate().length() > 0) {
				myEvent.setDeathDate(DateUtil.stringToDate(myClosingForm.getDeathDate(),DateUtil.DATE_FMT_1));
		    }
		    myEvent.setDeathAge(myClosingForm.getDeathAge());
		    
		}

		
		CompositeResponse compositeResponse = null;
		if( myEvent != null )
		{
			myEvent.setCreate(true);
			compositeResponse = MessageUtil.postRequest(myEvent);
		}

		CasefileClosingResponseEvent event = (CasefileClosingResponseEvent)
				MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);
		UIJuvenileCasefileClosingHelper.setClosingInfoFROMClosingRespEvt( myClosingForm, event);

		casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_CLOSING_PENDING);

		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(),
				ActivityConstants.CASE_CLOSING_PENDING, UIConstants.EMPTY_STRING );
		myClosingForm.setClosingInfoFound(true);
		// bug 163012 to refresh JPO after closing the casefile
		SearchJuvenileCasefilesEvent srch =
			(SearchJuvenileCasefilesEvent) 
			EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
		srch.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);
		srch.setJuvenileNum(juvenileNum);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(srch);
		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);
		List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
		CodeResponseEvent codeResp = UIJuvenileCaseworkHelper.setJPOOfRecord(casefiles);   		 
		//persist to juvenile record
		SaveJuvJPOOfRecEvent saveJPO = (SaveJuvJPOOfRecEvent)EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVJPOOFREC);
		saveJPO.setJuvenileNum(juvenileNum);
		saveJPO.setJpoId(codeResp.getCode());
		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(saveJPO);
		ErrorResponseEvent errRespEvt = (ErrorResponseEvent)MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if(errRespEvt != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( errRespEvt.getMessage() ) );
			saveErrors( aRequest, errors );
			return aMapping.findForward( UIConstants.SEARCH_FAILURE );
		}
		//
		
		// Add Termination Date logic here
	
		//Check here for masterStatus = 'C'
		GetJuvenileMasterStatusEvent statusEvent = (GetJuvenileMasterStatusEvent) 
							EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEMASTERSTATUS);
					
		statusEvent.setJuvenileNum( casefileForm.getJuvenileNum() );
					
		JuvenileMasterStatusResponseEvent status = (JuvenileMasterStatusResponseEvent) 
								MessageUtil.postRequest(statusEvent, JuvenileMasterStatusResponseEvent.class);
		if( status != null && "C".equalsIgnoreCase( status.getStatusId()) ){
					    
		    // Update JJSMSPETITION termination Date
		    Collection listOfDates = myClosingForm.getNewDates();
		    Iterator iter = listOfDates.iterator( ) ;
		    String referralCloseDate = "";
		    String referralNum = "";
		    String refNo = "";
		    String referraldisp = "";
		    HashMap reffDisp = new HashMap();
		    JuvenileDispositionCode juvDispCode =null;

			while( iter.hasNext() )
			{
			    JuvenileReferralProbationDatesResponseEvent juvProbresp = (JuvenileReferralProbationDatesResponseEvent)iter.next( );
			    referralCloseDate = juvProbresp.getRefCloseDate();
			    referralNum = juvProbresp.getReferralNumber();
			    	// dispositioncode 1304 dps change US 186995
				Collection reffs = myClosingForm.getReferrals();
				Iterator iter2 = reffs.iterator( ) ;			
	                	    while (iter2.hasNext())
	                	    {
	                		CasefileClosingForm.Refferal ref = (CasefileClosingForm.Refferal) iter2.next();
	                		if (ref.getReferralNumber() != null)
	                		{
	                		    if (ref.getReferralNumber().contains("-"))
	                			refNo = ref.getReferralNumber().split("-")[0].trim();
	                
	                		    if (refNo!=null && refNo.equalsIgnoreCase(referralNum))
	                		    {
	                			referraldisp = ref.getFinalDisposition();
	                			//add key value pair reff, disp	                			
	                			if (referraldisp!=null && !referraldisp.isEmpty())
	                			{
	                			    juvDispCode = JuvenileDispositionCode.find("codeAlpha",referraldisp);
	                			    if (juvDispCode != null && juvDispCode.getDpsCode() != null && !juvDispCode.getDpsCode().isEmpty()&& !juvDispCode.getDpsCode().equalsIgnoreCase("000"))
	                				reffDisp.put(referralNum, juvDispCode.getDpsCode());
	                			}
	                		    }
	                		}
	                	    }
			}    
		    
						
		    UpdateJJSPetitionsTerminationDateEvent updt = (UpdateJJSPetitionsTerminationDateEvent) 
						    		EventFactory.getInstance(JuvenileWarrantControllerServiceNames.UPDATEJJSPETITIONSTERMINATIONDATE);
		    updt.setJuvenileNum( casefileForm.getJuvenileNum() );
		    updt.setReferralNum( referralNum );
		    updt.setTerminationDate( DateUtil.stringToDate( referralCloseDate, DateUtil.DATE_FMT_1));		  
		    updt.setReffDps(reffDisp);
		    MessageUtil.postRequest(updt);
		}
		
		return( aMapping.findForward(UIConstants.SUCCESS_HOME) );
	}
	
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
   {
		ActionErrors errors = new ActionErrors();
		if(aActionErrors!=null && aActionErrors.size()>0){
			Iterator i=aActionErrors.iterator();
			while(i.hasNext()){
				ActionMessage error=(ActionMessage)i.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE,error);
			}
		   saveErrors(aRequest, errors);
		}
   }
   
   /**
    * Common Message.  //changes added for #13331
    * @param message
    * @param myClosingForm
    * @param casefileForm
    * @param sendEmailEvent
    */
   private void setCommonMessage(StringBuffer message,CasefileClosingForm myClosingForm,JuvenileCasefileForm casefileForm,SendEmailEvent sendEmailEvent,List<JuvenileProfileCasefileListResponseEvent> caseFileCollection ){
	   //Supervision History Details.
	    message.append("<html><body>");
	    message.append("<table border=\"1\" colspan=\"9\">");
	    message.append("<tr><th colspan=\"9\">Supervision History</th></tr>");
	    message.append("<tr>");
	    message.append("<td>Supervision #: </td>");
	    message.append("<td>Seq #: </td>");
	    message.append("<td>Probation Officer: </td>");
	    message.append("<td>Case Status: </td>");
	    message.append("<td>Supervision Type: </td>");
	    message.append("<td>Assignment Date: </td>");
	    message.append("<td>Supervision End Date: </td>");
	    message.append("<td>Controlling Referral: </td>");
	    message.append("<td>Supervision Outcome: </td>");
	    message.append("</tr>");
	    if(caseFileCollection!=null && !caseFileCollection.isEmpty()){
			   Iterator<JuvenileProfileCasefileListResponseEvent> juvProfCaseFileListIter = caseFileCollection.iterator();
			   while(juvProfCaseFileListIter.hasNext()){
				   JuvenileProfileCasefileListResponseEvent juvProfCaseFileListResp =juvProfCaseFileListIter.next();
				    message.append("<tr>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getSupervisionNum()!=null)
				    	message.append(juvProfCaseFileListResp.getSupervisionNum());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getSequenceNum()!=null)
				    	message.append(juvProfCaseFileListResp.getSequenceNum());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getProbationOfficer()!=null)
				    	message.append(juvProfCaseFileListResp.getProbationOfficer());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCaseStatus()!=null)
				    	message.append(juvProfCaseFileListResp.getCaseStatus());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getSupervisionType()!=null)
				    	message.append(juvProfCaseFileListResp.getSupervisionType());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getAssignmentAddDate()!=null)
				    	message.append(juvProfCaseFileListResp.getAssignmentAddDate());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getSupervisionEndDate()!=null)
				    	message.append(juvProfCaseFileListResp.getSupervisionEndDate());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getControllingReferralId()!=null)
				    	message.append(juvProfCaseFileListResp.getControllingReferralId());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getSupervisionOutcome()!=null)
				    	message.append(juvProfCaseFileListResp.getSupervisionOutcome());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("</tr>");
			   }
		   }
	    message.append("</table></body></html>");
	    sendEmailEvent.setMessage(message.toString());
   }
   /*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.saveAndContinue", "saveAndContinue");
		return buttonMap;
	}
}// END CLASS
