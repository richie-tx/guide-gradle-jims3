package ui.juvenilecase.facility.action;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityEscapeEvent;
import messaging.facility.SaveJuvenileFacilityInTransferEvent;
import messaging.facility.SaveJuvenileFacilityReferralTransferEvent;
import messaging.facility.SaveJuvenileFacilityReleaseEvent;
import messaging.facility.SaveJuvenileFacilityReturnEscapeEvent;
import messaging.facility.SaveJuvenileFacilityReturnTempReleaseEvent;
import messaging.facility.SaveJuvenileFacilityTempReleaseEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.UpdateJuvenileTraitstoFormerEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.referral.JJSOffense;

import ui.action.JIMSBaseAction;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;

/**
 * This Action Includes facility release, escape, return, temporary
 * Release,return temporary release,in transfer.
 * 
 * @author sthyagarajan
 */
public class SubmitJuvenileFacilityReleaseAction extends JIMSBaseAction
{

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		AdmitReleaseForm form =(AdmitReleaseForm) aForm;
		StringBuffer comments =null;
		JuvenileDetentionFacilitiesResponseEvent detResp=(JuvenileDetentionFacilitiesResponseEvent) aRequest.getSession().getAttribute("FACILITY_DETENTION_RESP");
		//Return Escape conditions
		if(form.getFacilityStatus()!=null && form.getFacilityStatus().equals("E")){
			SaveJuvenileFacilityReturnEscapeEvent returnEscapeEvt = (SaveJuvenileFacilityReturnEscapeEvent)EventFactory.
					   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYRETURNESCAPE); 
			
			returnEscapeEvt.setReturnDate(DateUtil.stringToDate(form.getReturnDate(),DateUtil.DATE_FMT_1));
			returnEscapeEvt.setReturnStatus(form.getReturnStatus());
			returnEscapeEvt.setReturnTime(form.getReturnTime());
			returnEscapeEvt.setReturnReason(form.getReturnReason());
			returnEscapeEvt.setHeaderFacility(form.getDetainedFacility());
			returnEscapeEvt.setFacilitySequenceNum(form.getFacilitySeqNum());
			returnEscapeEvt.setJuvenileNum(form.getJuvenileNum());
			returnEscapeEvt.setFacilityAdmissionComments(form.getAdmissionComments());

			boolean isObservationStatusChanged = false;
			if( detResp.getSaReason()!=null && !detResp.getSaReason().equals(form.getSaReason())){
				isObservationStatusChanged = true;
			}
			if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
				isObservationStatusChanged = true;
			}
			String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
			if(!splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
				isObservationStatusChanged = true;
			}
			returnEscapeEvt.setObservationStatusChanged(isObservationStatusChanged);
			if(isObservationStatusChanged){
				//Add special attention to the update.
				returnEscapeEvt.setSaReason(form.getSaReason());
				returnEscapeEvt.setSpecialAttentionCd(form.getSpecialAttentionCd());
				returnEscapeEvt.setSaReasonOtherComments(form.getSaReasonOtherComments());
			}
			
			CompositeResponse compositeResponse = MessageUtil.postRequest(returnEscapeEvt);
			JuvenileFacilityReleaseResponseEvent resp = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
	        
			Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	        if (errorResponse != null)
	        {
	            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	            try {
					handleFatalUnexpectedException(myEvt.getMessage());
				} catch (GeneralFeedbackMessageException e) {
					e.printStackTrace();
				}
	        }	
	        
	        if(resp!=null && resp.isSaveable()){
				//2.4.6. Activity: Generate Return Activity
				String title= "Returned to facility following Escape";
				comments = new StringBuffer();
				comments.append("Comments: ")
							.append(form.getAdmissionComments())
							.append(". Juvenile was returned to "+form.getDetainedFacility()+" on "+form.getReturnDate()+" following an escape on "+form.getEscapeDate());
					
				JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,
						 ActivityConstants.FACILITY_DOCUMENT_RETURN, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
	        }
	        if(isObservationStatusChanged){
				//spl attention changed
				if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
					String title= "Facility Observation Status modification.";
					comments = new StringBuffer();
					comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
					if(form.getSaReason().contains("OT")){
						comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
					}
					JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
							 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				}
				// SA comments changed
				if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
					String title= "Facility Observation modification.";
					comments = new StringBuffer();
					comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
					JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
							ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				}
			}
			form.setReturnAction("confirm");
		}
		else if(form.getAction().equals("referralTransferView")) // referral transfer has no spl attention
		{
			//facility Status D,P,V,R
			//Referral Transfer Release logic goes here.
			SaveJuvenileFacilityReferralTransferEvent referralTransfer = (SaveJuvenileFacilityReferralTransferEvent)EventFactory.
							   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYREFERRALTRANSFER); 
					
			referralTransfer.setBookingOffense(form.getBookingOffense());
			referralTransfer.setBookingSupervision(form.getBookingSupervisionNum());
			referralTransfer.setBookingOffenseLevel(form.getBookingOffenseLevel());
			referralTransfer.setBookingReferral(form.getBookingReferral());
			
			referralTransfer.setCurrentReferral(form.getCurrentReferral());
			referralTransfer.setCurrentOffense(form.getCurrentOffense());
			referralTransfer.setCurrentSupervision(form.getCurrentSupervisionNum());
			referralTransfer.setCurrentOffenseLevel(form.getCurrentOffenseLevel());
			referralTransfer.setCurrentOffenseCode(form.getCurrentOffenseCd());
			
			referralTransfer.setPetition(form.getCurrentPetitionNum());
			referralTransfer.setJuvenileNum(form.getJuvenileNum());
			referralTransfer.setFacilitySequenceNum(form.getFacilitySeqNum());
			
			referralTransfer.setTransferFromReferral(form.getTransferFromReferral());
			
			String transferToRef = null;
			if(form.getTransferToReferral().contains("-")){
				transferToRef = form.getTransferToReferral().split("-")[0];
			}else{
				transferToRef = form.getTransferToReferral();
			}
			referralTransfer.setTransferToReferral(transferToRef);
			
			CompositeResponse compositeResponse = MessageUtil.postRequest(referralTransfer);
			JuvenileFacilityReleaseResponseEvent resp = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
			        
			Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			  if (errorResponse != null)
			  {
			    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
			    try {
			    	  	handleFatalUnexpectedException(myEvt.getMessage());
			      } catch (GeneralFeedbackMessageException e) {
					e.printStackTrace();
			 	 }
			  }
			  if(resp!=null && resp.isSaveable()){
		      	//Generate Temporary Release activity.
				String title= "Detention Referral Transfer";
				comments = new StringBuffer();
				comments.append("Comments: ")
						.append("Current Admission was transferred from " + form.getTransferFromReferral()+ " to "+transferToRef +" by "+UIUtil.getCurrentUserID()+" on "+DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1)+" "+DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)+".");
							
				JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
					 ActivityConstants.FACILITY_REFERRAL_TRANSFER, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
									
				 //Send email to the Detention hearing group
		    	SendEmailEvent sendEmailEvent = new SendEmailEvent();
		    	StringBuffer message = new StringBuffer(100);
		    	String currentUserEmail = "Data.corrections@hcjpd.hctx.net";
		    	if((currentUserEmail!=null && !currentUserEmail.equals("")))
		    	{
		    		sendEmailEvent.setFromAddress(currentUserEmail);
		    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,currentUserEmail);
		    		message.append("The Current Detention record ");
		    		message.append(" for ");
		    		message.append(form.getProfileDetail().getFormattedName());
		    		message.append(", Juvenile# ");
		    		message.append(form.getJuvenileNum());
		    		message.append(" was transferred from ");
		    		message.append(form.getTransferFromReferral());
		    		message.append(" to ");
		    		message.append(transferToRef+".");
		    		sendEmailEvent.setMessage(message.toString());
		    		sendEmailEvent.setSubject("Facility Admission Record was transferred from "+form.getTransferFromReferral()+" to "+transferToRef+".");
		    		CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
		    		MessageUtil.processReturnException( res ) ;
			    }
			  }
			  
				form.setReferralTransferAction("confirm");
				form.setAction("confirm");
				return aMapping.findForward("refTransferSuccess");		 
		}
		else if(form.getReleaseReason()!=null && form.getReleaseReason().equals("E")) //escape/
		{
			//escape logic goes here
			SaveJuvenileFacilityEscapeEvent escapeEvt = (SaveJuvenileFacilityEscapeEvent)EventFactory.
					   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYESCAPE);
			
			escapeEvt.setOutcome(form.getOutcome());
			escapeEvt.setEscapeCode(form.getEscapeFrom());
			escapeEvt.setComments(form.getAdmissionComments()); //escape comments.
			escapeEvt.setRelocationDate(DateUtil.stringToDate(form.getEscapeDate(),DateUtil.DATE_FMT_1));
			escapeEvt.setRelocationTime(form.getEscapeTime());
			escapeEvt.setLcDate(DateUtil.getCurrentDate());
			escapeEvt.setLcTime(Calendar.getInstance().getTime());
			escapeEvt.setHeaderFacility(form.getDetainedFacility());
			escapeEvt.setEscapeDate(form.getEscapeDate());
			escapeEvt.setCurrentSupervisionNum(form.getCurrentSupervisionNum());
			escapeEvt.setNextHearingDate(form.getNextHearingDate());
			escapeEvt.setEscapeFromDesc(form.getEscapeFromDesc());
			escapeEvt.setFacilitySequenceNum(form.getFacilitySeqNum());
			escapeEvt.setJuvenileNum(form.getJuvenileNum());
			escapeEvt.setFacilityAdmissionComments(form.getAdmissionComments());
			escapeEvt.setReleaseReason(form.getReleaseReason());
			
			boolean isObservationStatusChanged = false;
			if( detResp.getSaReason()!=null && !detResp.getSaReason().equals(form.getSaReason())){
				isObservationStatusChanged = true;
			}
			if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
				isObservationStatusChanged = true;
			}
			String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
			if(!splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
				isObservationStatusChanged = true;
			}
			escapeEvt.setObservationStatusChanged(isObservationStatusChanged);
			// Add special attention to the update.
			if(isObservationStatusChanged){
				escapeEvt.setSaReason(form.getSaReason());
				escapeEvt.setSpecialAttentionCd(form.getSpecialAttentionCd());
				escapeEvt.setSaReasonOtherComments(form.getSaReasonOtherComments());
			}
			
			escapeEvt.setDetentionFacilityDesc(form.getDetainedFacilityStr());
			CompositeResponse compositeResponse = MessageUtil.postRequest(escapeEvt);
			JuvenileFacilityReleaseResponseEvent respEvent = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
			Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	        if (errorResponse != null)
	        {
	            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	            try {
					handleFatalUnexpectedException(myEvt.getMessage());
				} catch (GeneralFeedbackMessageException e) {
					e.printStackTrace();
				}
	        }	
	        if(respEvent!=null && respEvent.isSaveable()){
				//2.4.6. Activity: Generate Escape Activity
				String title= form.getEscapeFromDesc();
				comments = new StringBuffer();
				comments.append("Escape Comments: ")
							.append(form.getAdmissionComments())
							.append(". Juvenile has been documented as having escaped on "+form.getEscapeDate()+" at " +form.getEscapeTime() +" while in the custody of "+form.getDetainedFacilityStr()); // bug fix:40616
					
				JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
						 ActivityConstants.FACILITY_DOCUMENT_ESCAPE, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			
	        }
	        if(isObservationStatusChanged){
				//spl attention changed
				if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
					String title= "Facility Observation Status modification.";
					comments = new StringBuffer();
					comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
					if(form.getSaReason().contains("OT")){
						comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
					}
					JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
							 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				}
				// SA comments changed
				if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
					String title= "Facility Observation modification.";
					comments = new StringBuffer();
					comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
					JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
							ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				}
			}
			form.setEscapeAction("confirm");
		}
		else if(form.getReleaseReason()!=null && form.getReleaseReason().equals("R")) //facility Status D,P,V,R // hard release
		{ 
			if(form.getReleasedTo()!=null && !form.getReleasedTo().isEmpty()){
				
					SaveJuvenileFacilityReleaseEvent releaseEvent = (SaveJuvenileFacilityReleaseEvent)EventFactory.
							   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYRELEASE); 
					
					//release logic goes here
					releaseEvent.setReleaseAuthorizedBy(form.getReleaseAuthority());
					releaseEvent.setReleaseReason(form.getReleaseReason());
					releaseEvent.setReleaseDate(DateUtil.stringToDate(form.getReleaseDate(),DateUtil.DATE_FMT_1));
					releaseEvent.setReleaseTime(form.getReleaseTime());
					releaseEvent.setReleasedBy(form.getReleasedBy());
					releaseEvent.setReleaseTo(form.getReleasedTo());
					releaseEvent.setOutcome(form.getOutcome());
					releaseEvent.setCustodylastName(form.getCustodylastName());
					releaseEvent.setCustodyfirstName(form.getCustodyfirstName());
					
					releaseEvent.setBookingReferralNumber(form.getBookingReferral());
					releaseEvent.setFacilityStatus(form.getFacilityStatus());
					releaseEvent.setCurrentSupervisionNum(form.getCurrentSupervisionNum());
					releaseEvent.setDetainedFacility(form.getDetainedFacilityStr());
					releaseEvent.setFacilityAdmissionComments(form.getAdmissionComments());
					
					releaseEvent.setJuvenileName(form.getProfileDetail().getFormattedName());
					releaseEvent.setFacilitySequenceNum(form.getFacilitySeqNum());
					releaseEvent.setJuvenileNum(form.getJuvenileNum());
					
					boolean isObservationStatusChanged = false;
					if( detResp.getSaReason()!=null && !detResp.getSaReason().equals(form.getSaReason())){
						isObservationStatusChanged = true;
					}
					if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
						isObservationStatusChanged = true;
					}
					String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
					if(!splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
						isObservationStatusChanged = true;
					}
					
					releaseEvent.setObservationStatusChanged(isObservationStatusChanged);
					if(isObservationStatusChanged){
						// Add special attention to the update.
						releaseEvent.setSaReason(form.getSaReason());
						releaseEvent.setSpecialAttentionCd(form.getSpecialAttentionCd());
						releaseEvent.setSaReasonOtherComments(form.getSaReasonOtherComments());
					}
				
					CompositeResponse compositeResponse = MessageUtil.postRequest(releaseEvent);
					
					JuvenileFacilityReleaseResponseEvent respEvent = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
					Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			        if (errorResponse != null)
			        {
			            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
			            try {
							handleFatalUnexpectedException(myEvt.getMessage());
						} catch (GeneralFeedbackMessageException e) {
							e.printStackTrace();
						}
			        }	
			        if(respEvent!=null && respEvent.isSaveable()){
				        
        				if (form.getReleasedTo().equalsIgnoreCase("TYD"))
        				{
        				    //send email as TYD
        				    List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(form.getJuvenileNum());
        				    Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNum());
        				    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
        					@Override
        					public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
        					{
        					    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
        						//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
        						return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
        					    else
        						return -1;
        					}
        				    }));
        				    Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
        				    while (referralIterator.hasNext())
        				    {
        					JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
        					Collection<JJSOffense> offenses = referral.getOffenses();
        					if (offenses != null)
        					{
        					    Iterator<JJSOffense> offenseItr = offenses.iterator();
        					    while (offenseItr.hasNext())
        					    {
        						JJSOffense offense = offenseItr.next();
        						if (offense.getSequenceNum().equalsIgnoreCase("1"))
        						{
        						    referral.setOffense(offense.getOffenseCodeId());
        						    referral.setOffenseDesc(offense.getOffenseDescription());
        						    if ("TRNDSP".equals(offense.getOffenseCodeId()) || "TRNSIN".equals(offense.getOffenseCodeId()) || "REGION".equals(offense.getOffenseCodeId()) || "ISCOIN".equals(offense.getOffenseCodeId()))
        						    {
        							for (JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses)
        							{
        							    if (referral.getReferralNumber().equals(transferredOffense.getReferralNum()))
        							    {
        								referral.setOffenseDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
        								referral.setPetitionAllegationDescr(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
        							    }
        							}
        
        						    }
        						    break;
        						}
        					    }
        					}
        				    }
        
        				    Collection<OfficerProfileResponseEvent> securityRespEvent = PDOfficerProfileHelper.getOfficerProfilesInUserGroup("JIMS2 Release to Youth Diversion Center");
        				    if (securityRespEvent != null)
        				    {
        					Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
        
        					while (securityRespIter.hasNext())
        					{
        					    OfficerProfileResponseEvent securityResEvent = securityRespIter.next();
        					    if (securityResEvent.getEmail() != null && !securityResEvent.getEmail().equals(""))
        					    {
        						SendEmailEvent sendEmailEvent = new SendEmailEvent();
        						StringBuffer message = new StringBuffer(100);
        						String fromEmail = "jims2notification@itc.hctx.net";
        
        						sendEmailEvent.setFromAddress(fromEmail);
        						UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, securityResEvent.getEmail());
        						message.append(form.getProfileDetail().getFirstName()+" "+form.getProfileDetail().getLastName()+", "+ form.getJuvenileNum() + ", was released to the Diversion Center on " + form.getReleaseDate()+":");
        						message.append(System.getProperty("line.separator"));
        						sendEmailEvent.setMessage(message.toString());
        						sendEmailEvent.setSubject(form.getJuvenileNum() + " was released to the Diversion Center on " + form.getReleaseDate());
        						setCommonMessage(message, sendEmailEvent, referralList);
        						sendEmailEvent.setContentType("text/html");
        						CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
        						MessageUtil.processReturnException(res);
        
        					    }
        					}
        				    }
        				    //				        
        				}
        				
        				//If ReleasedTo = TRN, a transfer release has been processed.  A transfer release activity and transfer release notice need to be generated.
				        if(form.getReleasedTo()=="TRN"){
							String title= "Release during transfer from "+form.getDetainedFacilityStr();
							comments = new StringBuffer();
							comments.append("Comments: ")
										.append(form.getAdmissionComments())
										.append(". Juvenile was released for transfer from "+form.getDetainedFacilityStr()+" on "+form.getReleaseDate() +"."+form.getReleasedBy() + " documented the transfer."+" Transfer was authorized by "+form.getReleaseAuthorityDesc()+".");
								
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
									 ActivityConstants.FACILITY_TRANSFER_RELEASE, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				        	
				        }else{ //If ReleasedTo is not TRN, a final release has been processed.  A final release activity and final release notice need to be generated.
				        	String title= "Released From " +form.getDetainedFacilityStr();
							comments = new StringBuffer();
							comments.append("Comments: ")
										.append(form.getAdmissionComments())
										.append(". Juvenile was released from "+form.getDetainedFacilityStr()+" on "+form.getReleaseDate()+". Release was entered by "+form.getReleasedBy()+". Release was authorized by "+form.getReleaseAuthority()+".");
								
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
									 ActivityConstants.FACILITY_FINAL_RELEASE, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				        }
        			    }
			        if(isObservationStatusChanged){
						//spl attention changed
						if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
							String title= "Facility Observation Status modification.";
							comments = new StringBuffer();
							comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
							if(form.getSaReason().contains("OT")){
								comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
							}
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
									 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
						}
						// SA comments changed
						if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
							String title= "Facility Observation modification.";
							comments = new StringBuffer();
							comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
									ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
						}
					}
			        //setting all the facility  traits to Former on hard release and transfer 126783
			        UpdateJuvenileTraitstoFormerEvent saveEvent = (UpdateJuvenileTraitstoFormerEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJUVENILETRAITSTOFORMER ) ;
                    		// add code to update trait status -- insert with OID (TRAIT_ID)
                    		saveEvent.setJuvenileNum( form.getJuvenileNum() ) ;
                    		saveEvent.setTraitType("FACILITY TRAITS");
                    		saveEvent.setOID(form.getDetentionId());
                    		//saveEvent.setAction("release");
                    		saveEvent.setOriginaladmitOID(detResp.getOriginalAdmitOID());
                    
                    		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
                    		dispatch.postEvent( saveEvent ) ;			        
			        //
				form.setReleaseAction("confirm");
			}
		}
		
		else if(form.getAction().equals("tempReleaseDecisionView"))
		{
		    
		  //Generate Temporary Release activity.
			String title= "Temporary Release Confirmation - " +form.getDetainedFacility();
			comments = new StringBuffer();
			comments.append("Comments: ")
						.append("Temporary Release Decision = "+form.getTemporaryReleaseAllowed());
				
			JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
					 ActivityConstants.TEMPORARY_RELEASE_OUTCOME, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			

			
			 //Send email to the Detention hearing group
			 Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("TEMP REL CONTACT GROUP");
			 if(securityRespEvent!=null){
			     Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
				
			     while(securityRespIter.hasNext())
			     {
				OfficerProfileResponseEvent securityResEvent =	securityRespIter.next();
				if(securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals(""))
			    	{
                		    	SendEmailEvent sendEmailEvent = new SendEmailEvent();
                		    	StringBuffer message = new StringBuffer(100);
                //		    	String currentUserEmail = "Data.corrections@hcjpd.hctx.net";
                		    	//String currentUserEmail = "Sindhu.Gouru@us.hctx.net";
                		    	String fromEmail = "jims2notification@itc.hctx.net";
                		    	 
                		    	
                		    		sendEmailEvent.setFromAddress(fromEmail);
                		    		//UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,currentUserEmail);
                		    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
                		    		message.append("Court Administration has responded with the following: ");
                		    		message.append(System.getProperty("line.separator"));
                		    		message.append("Temporary Release Allowed?: ");
                		    		message.append(form.getTemporaryReleaseAllowed());
                		    		message.append(System.getProperty("line.separator"));
                		    		message.append("Comments: ");
                		    		message.append(form.getTempReleaseDecisionComments());
                		    		message.append(System.getProperty("line.separator"));
                		    		message.append("Authorizing Officer: ");
                		    		message.append(form.getAuthorizingOfficer());
                		    		sendEmailEvent.setMessage(message.toString());
                		    		sendEmailEvent.setSubject("Court Administration Temporary Release Confirmation: "+form.getJuvenileNum()+" , "+form.getProfileDetail().getFormattedName());
                		    		CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
                		    		MessageUtil.processReturnException( res ) ;
                			    
			    	}
			     }
			 } 
		    	JJSHeader header = JuvenileFacilityHelper.getJJSHeader(form.getJuvenileNum());
			IHome home= new Home();
			//add code to distinguish detention or district
			if (form.isTempRelDecisionEnabled())
			{
        		    	if(form.getTemporaryReleaseAllowed().equals("YES")){
        		    	    header.setReleaseDecisionStatus("A");
        		    	 }
        		    	else{
        		    	    header.setReleaseDecisionStatus("R");
        		    	}
			}
			if (form.isTempRelDecisionDistEnabled())
			{
        		    	if(form.getTemporaryReleaseAllowed().equals("YES")){
        		    	    header.setDistReleaseDecisionStatus("A");
        		    	 }
        		    	else{
        		    	    header.setDistReleaseDecisionStatus("R");
        		    	}
			}
		  	home.bind(header); 
		  	form.setAction("confirm");
			return aMapping.findForward("tempReleaseDecisionNext");
		}
		
		
		else if(form.getReleaseReason()!=null && form.getReleaseReason().equals("T") && form.getAction().equals("tempReleaseView")) //temp release.
		{
					// 2.7.5. Activity: Record Temporary Release information
					//Temp Release logic goes here
					SaveJuvenileFacilityTempReleaseEvent tempReleaseEvent = (SaveJuvenileFacilityTempReleaseEvent)EventFactory.
							   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYTEMPRELEASE); 
					
					tempReleaseEvent.setReleaseAuthorizedBy(form.getReleaseAuthority());
					tempReleaseEvent.setReleaseReason(form.getReleaseReason());
					tempReleaseEvent.setReleaseDate(DateUtil.stringToDate(form.getReleaseDate(),DateUtil.DATE_FMT_1));
					tempReleaseEvent.setReleaseTime(form.getReleaseTime());
					tempReleaseEvent.setReleasedBy(form.getReleasedBy());
					tempReleaseEvent.setReleaseTo(form.getReleasedTo());
					tempReleaseEvent.setTempReleaseReason(form.getTempReleaseReason());
					tempReleaseEvent.setTempReleaseReasonOtherComments(form.getTempReleaseReasonOtherComments());
					tempReleaseEvent.setBookingReferralNumber(form.getBookingReferral());
					tempReleaseEvent.setFacilityStatus("T");
					tempReleaseEvent.setCurrentSupervisionNum(form.getCurrentSupervisionNum());
					tempReleaseEvent.setDetainedFacility(form.getDetainedFacilityStr());
					tempReleaseEvent.setFacilityAdmissionComments(form.getAdmissionComments());
					
					tempReleaseEvent.setJuvenileName(form.getProfileDetail().getFormattedName());
					tempReleaseEvent.setFacilitySequenceNum(form.getFacilitySeqNum());
					tempReleaseEvent.setJuvenileNumber(form.getJuvenileNum());
					tempReleaseEvent.setNextHearingDate(form.getNextHearingDate());
							
					
					boolean isObservationStatusChanged = false;
					if( detResp.getSaReason()!=null && !detResp.getSaReason().equals(form.getSaReason())){
						isObservationStatusChanged = true;
					}
					if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
						isObservationStatusChanged = true;
					}
					String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
					if(!splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
						isObservationStatusChanged = true;
					}
					tempReleaseEvent.setObservationStatusChanged(isObservationStatusChanged);
					if(isObservationStatusChanged){
						// Add special attention to the update.
						tempReleaseEvent.setSaReason(form.getSaReason());
						tempReleaseEvent.setSpecialAttentionCd(form.getSpecialAttentionCd());
						tempReleaseEvent.setSaReasonOtherComments(form.getSaReasonOtherComments());
					}
					CompositeResponse compositeResponse = MessageUtil.postRequest(tempReleaseEvent);
					
					JuvenileFacilityReleaseResponseEvent respEvent = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
					Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			        if (errorResponse != null)
			        {
			            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
			            try {
							handleFatalUnexpectedException(myEvt.getMessage());
						} catch (GeneralFeedbackMessageException e) {
							e.printStackTrace();
						}
			        }	
			        if(respEvent!=null && respEvent.isSaveable()){
			        		//Generate Temporary Release activity.
							String title= "Temporarily Released From " +form.getDetainedFacility();
							comments = new StringBuffer();
							comments.append("Comments: ")
										.append(form.getAdmissionComments())
										.append(". Juvenile was temporarily released from "+form.getDetainedFacilityStr()+" on "
													+form.getReleaseDate()+". Release was entered by "+form.getReleasedBy()+". Release was authorized by "+form.getReleaseAuthority()+".");
							//pass JJS_DETENTION_ID while adding the activity  103972 release date for 103957
							// US 105411 add time also with activity
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
									 ActivityConstants.TEMP_RELEASE_FACILITY, title, comments.toString(),form.getDetentionId(),DateUtil.stringToDate(form.getReleaseDate(), DateUtil.DATE_FMT_1),form.getReleaseTime() );
				    }
			        if(isObservationStatusChanged){
						//spl attention changed
						if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
							String title= "Facility Observation Status modification.";
							comments = new StringBuffer();
							comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
							if(form.getSaReason().contains("OT")){
								comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
							}
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
									 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
						}
						// SA comments changed
						if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
							String title= "Facility Observation modification.";
							comments = new StringBuffer();
							comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
							JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
									ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
						}
					}
			        form.setTempReleaseAction("confirm");
		}
		else if(form.getReleaseReason()!=null && form.getReleaseReason().equals("N") && form.getAction().equals("inTransferView")) // intransfer view
		{ 
			if(form.getTransferToFacility()!=null && !form.getTransferToFacility().isEmpty())
			{
				SaveJuvenileFacilityInTransferEvent transferEvt = (SaveJuvenileFacilityInTransferEvent)EventFactory.
									   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYINTRANSFER); 
							
				//release logic goes here
				transferEvt.setReleaseAuthorizedBy(form.getReleaseAuthority());
				transferEvt.setReleaseReason(form.getReleaseReason());
				transferEvt.setReleaseDate(DateUtil.stringToDate(form.getReleaseDate(),DateUtil.DATE_FMT_1));
				transferEvt.setReleaseTime(form.getReleaseTime());
				transferEvt.setReleasedBy(form.getReleasedBy());
				transferEvt.setReleaseTo(form.getReleasedTo());
				transferEvt.setOutcome(form.getOutcome());
				transferEvt.setTransferToFacility(form.getTransferToFacility());
				transferEvt.setTransferToFacilityDesc(form.getTransferToFacilityDesc());
				transferEvt.setBookingReferralNumber(form.getBookingReferral());
				transferEvt.setFacilityStatus("N");
				transferEvt.setCurrentSupervisionNum(form.getCurrentSupervisionNum());
				transferEvt.setDetainedFacility(form.getDetainedFacilityStr());
				transferEvt.setFacilityAdmissionComments(form.getAdmissionComments());
						
				transferEvt.setJuvenileName(form.getProfileDetail().getFormattedName());
				transferEvt.setFacilitySequenceNum(form.getFacilitySeqNum());
				transferEvt.setJuvenileNumber(form.getJuvenileNum());
				transferEvt.setDetainedDate(DateUtil.stringToDate(form.getDetainedDate(),DateUtil.DATE_FMT_1));
				
				boolean isObservationStatusChanged = false;
				if( detResp.getSaReason()!=null && !detResp.getSaReason().equals(form.getSaReason())){
					isObservationStatusChanged = true;
				}
				if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
					isObservationStatusChanged = true;
				}
				String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
				if(!splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
					isObservationStatusChanged = true;
				}
				transferEvt.setObservationStatusChanged(isObservationStatusChanged);
				if(isObservationStatusChanged){
					// Add special attention to the update.
					transferEvt.setSaReason(form.getSaReason());
					transferEvt.setSpecialAttentionCd(form.getSpecialAttentionCd());
					transferEvt.setSaReasonOtherComments(form.getSaReasonOtherComments());
				}
				CompositeResponse compositeResponse = MessageUtil.postRequest(transferEvt);
						
				JuvenileFacilityReleaseResponseEvent respEvent = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
				Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
				if (errorResponse != null)
				{
				    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
				    try{
				    	handleFatalUnexpectedException(myEvt.getMessage());
					   } catch (GeneralFeedbackMessageException e) {
								e.printStackTrace();
					     }
				}	
				if(respEvent!=null && respEvent.isSaveable())
				{
				    //If ReleasedTo = TRN, a transfer release has been processed.  A transfer release activity and transfer release notice need to be generated.
				    if(form.getReleasedTo()=="NTS"){
				    	String title= "Release during transfer from "+form.getDetainedFacilityStr();
						comments = new StringBuffer();
						comments.append("Comments: ")
									.append(form.getAdmissionComments())
									.append(". Juvenile was released for transfer from "+form.getDetainedFacilityStr()+" on "+form.getReleaseDate() +"."+form.getReleasedBy() + " documented the transfer."+" Transfer was authorized by "+form.getReleaseAuthorityDesc()+".");
									
						JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
								 ActivityConstants.FACILITY_TRANSFER_RELEASE, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				   }
				   else
				   { //If ReleasedTo is not TRN, a final release has been processed.  A final release activity and final release notice need to be generated.
					   String title= "Released From " +form.getDetainedFacilityStr();
						comments = new StringBuffer();
						comments.append("Comments: ")
									.append(form.getAdmissionComments())
									.append(". Juvenile was released from "+form.getDetainedFacilityStr()+" on "+form.getReleaseDate()+". Release was entered by "+form.getReleasedBy()+". Release was authorized by "+form.getReleaseAuthority()+".");
							
						JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
								 ActivityConstants.FACILITY_FINAL_RELEASE, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				   }
				}
				if(isObservationStatusChanged){
					//spl attention changed
					if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
						String title= "Facility Observation Status modification.";
						comments = new StringBuffer();
						comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
						if(form.getSaReason().contains("OT")){
							comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
						}
						JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
								 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
					}
					// SA comments changed
					if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
						String title= "Facility Observation modification.";
						comments = new StringBuffer();
						comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
						JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
								ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
					}
				}
				//setting all the facility  traits to Former on hard release and transfer 126783
			        UpdateJuvenileTraitstoFormerEvent saveEvent = (UpdateJuvenileTraitstoFormerEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJUVENILETRAITSTOFORMER ) ;
                    		// add code to update trait status -- insert with OID (TRAIT_ID)
                    		saveEvent.setJuvenileNum( form.getJuvenileNum() ) ;
                    		saveEvent.setTraitType("FACILITY TRAITS");
                    		saveEvent.setOID(form.getDetentionId());
                    		//saveEvent.setAction("transfer");
                    		saveEvent.setOriginaladmitOID(detResp.getOriginalAdmitOID());
                    
                    		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
                    		dispatch.postEvent( saveEvent ) ;
                    		//
				form.setTransferAction("confirm");
			}
		}
		else
		{
				 //return temp release.
			if(form.getAction().equals("retTempReleaseView")){ //facility Status D,P,V,R //return temp release.
				//Return Temp Release logic goes here
			  
				SaveJuvenileFacilityReturnTempReleaseEvent retTempReleaseEvent = (SaveJuvenileFacilityReturnTempReleaseEvent)EventFactory.
									   getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYRETURNTEMPRELEASE); 
					
				retTempReleaseEvent.setReturnDate(DateUtil.stringToDate(form.getReturnDate(),DateUtil.DATE_FMT_1));
				retTempReleaseEvent.setReturnStatus(form.getReturnStatus());
				retTempReleaseEvent.setReturnTime(form.getReturnTime());
				retTempReleaseEvent.setReturnReason(form.getReturnReason());
					
				retTempReleaseEvent.setHeaderFacility(form.getDetainedFacility());
				retTempReleaseEvent.setFacilitySequenceNum(form.getFacilitySeqNum());
				retTempReleaseEvent.setJuvenileNum(form.getJuvenileNum());
				retTempReleaseEvent.setBookingRefNum(form.getBookingReferral());
				retTempReleaseEvent.setFacilityAdmissionComments(form.getAdmissionComments());
				

				boolean isObservationStatusChanged = false;
				if( detResp.getSaReason()!=null && !detResp.getSaReason().equals(form.getSaReason())){
					isObservationStatusChanged = true;
				}
				if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
					isObservationStatusChanged = true;
				}
				String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
				if(!splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
					isObservationStatusChanged = true;
				}
				retTempReleaseEvent.setObservationStatusChanged(isObservationStatusChanged);
				if(isObservationStatusChanged){
					//Add special attention to the update.
					retTempReleaseEvent.setSaReason(form.getSaReason());
					retTempReleaseEvent.setSpecialAttentionCd(form.getSpecialAttentionCd());
					retTempReleaseEvent.setSaReasonOtherComments(form.getSaReasonOtherComments());
				}
				
				String tempReleaseReasonOther="";
				if(form.getTempReleaseReason()!=null && !form.getTempReleaseReason().isEmpty()&& form.getTempReleaseReason().equals("OT")){
					 tempReleaseReasonOther = form.getTempReleaseReasonOtherComments();
				}
			
				CompositeResponse compositeResponse = MessageUtil.postRequest(retTempReleaseEvent);
				JuvenileFacilityReleaseResponseEvent resp = (JuvenileFacilityReleaseResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileFacilityReleaseResponseEvent.class);
		        
				Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		        if (errorResponse != null)
		        {
		            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		            try {
						handleFatalUnexpectedException(myEvt.getMessage());
					} catch (GeneralFeedbackMessageException e) {
						e.printStackTrace();
					}
		        }
		        if(resp.isSaveable()){
		       		//Generate Temporary Release activity.
					String title= "Returned from Temporary Release";
					comments = new StringBuffer();
					comments.append("Comments: ")
								.append(form.getAdmissionComments())
								.append(". Juvenile was has been returned to "+form.getDetainedFacilityStr()+" after a temporary release for " + form.getTempReleaseReasonDesc()+ tempReleaseReasonOther +
										" on "+form.getReleaseDate());
					//pass JJS_DETENTION_ID while adding the activity  103972 and return date for 103956	
					//US 105411 adding time also with activity
					JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
							 ActivityConstants.RETURN_TEMP_RELEASE_FACILITY, title, comments.toString(),form.getDetentionId(),DateUtil.stringToDate(form.getReturnDate(), DateUtil.DATE_FMT_1),form.getReturnTime());
		        }
		        if(isObservationStatusChanged){
					//spl attention changed
					if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
						String title= "Facility Observation Status modification.";
						comments = new StringBuffer();
						comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
						if(form.getSaReason().contains("OT")){
							comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
						}
						JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
								 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
					}
					// SA comments changed
					if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
						String title= "Facility Observation modification.";
						comments = new StringBuffer();
						comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
						JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
								ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
					}
				}
		        form.setRetTempReleaseAction("confirm");
			}
		}
		form.setAction("confirm");
		return aMapping.findForward("success");
	}
    private void setCommonMessage(StringBuffer message,SendEmailEvent sendEmailEvent,Collection<JuvenileProfileReferralListResponseEvent> referralCollection ){
	   //referral History Details.
	
	    if(referralCollection!=null && !referralCollection.isEmpty())
	    {		
         	    message.append("<BR>");
         	    message.append("<BR>");
         	    message.append("<html><body>");
         	    message.append("<table border=\"1\" colspan=\"10\">");
         	    message.append("<tr><th colspan=\"10\">Referral Summary</th></tr>");
         	    message.append("<tr>");
         	    message.append("<td>Ref No </td>");
         	    message.append("<td>Referral Date</td>");
         	    message.append("<td>Offense/Petition Allegation</td>");
         	    message.append("<td>Intake Decision</td>");
         	    message.append("<td>Court Id</td>");         	    
         	    message.append("<td>Decision</td>");
         	    message.append("<td>Petition</td>");
         	    message.append("<td>Date Closed</td>");
         	    message.append("</tr>");
	    
			   Iterator<JuvenileProfileReferralListResponseEvent> juvProfCaseFileListIter = referralCollection.iterator();
			   while(juvProfCaseFileListIter.hasNext())
			   {
			       JuvenileProfileReferralListResponseEvent juvProfCaseFileListResp =juvProfCaseFileListIter.next();
				    message.append("<tr>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getReferralNumber()!=null)
				    	message.append(juvProfCaseFileListResp.getReferralNumber());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getReferralDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getReferralDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getOffense()!=null)
				    	message.append(juvProfCaseFileListResp.getOffense());
				    else
				    	message.append("");
				    message.append("/");
				    if(juvProfCaseFileListResp.getPetitionAllegation()!=null)
				    	message.append(juvProfCaseFileListResp.getPetitionAllegation());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getIntakeDecisionId()!=null)
				    	message.append(juvProfCaseFileListResp.getIntakeDecisionId());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCourtId()!=null)
				    	message.append(juvProfCaseFileListResp.getCourtId());
				    else
				    	message.append("");
				    message.append("</td>");				   
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getFinalDisposition()!=null)				
				    	message.append(juvProfCaseFileListResp.getFinalDisposition());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");				    
				    if(juvProfCaseFileListResp.getPetitionNumber()!=null)
				    	message.append(juvProfCaseFileListResp.getPetitionNumber());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCloseDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getCloseDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("</tr>");
			   }
	    }
	    else
		message.append("Please refer to Data Corrections for details.");
	    message.append("</table></body></html>");
	    sendEmailEvent.setMessage(message.toString());
}

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	AdmitReleaseForm form = (AdmitReleaseForm) aForm;

	if (form.getSpecialAttentionCd().equals("NO"))
	{
	    form.setSelectedSAReasons(null);
	    form.setSelectedSA("NO");
	}
	else
	{
	    form.setSelectedSA(form.getSpecialAttentionCd());
	    if (form.getSaReason() != null && !form.getSaReason().equals(""))
	    {
		form.setSelectedSAReasons(form.getSaReason().split(","));
	    }
	    form.setSaReasonStr(form.getSaReason());
	}
	if (form.getEscapeTime().contains(":"))
	{
	    form.setEscapeTime(JuvenileFacilityHelper.stripColonInDate(form.getEscapeTime()));
	}
	if (form.getReturnTime().contains(":"))
	{
	    form.setReturnTime(JuvenileFacilityHelper.stripColonInDate(form.getReturnTime()));
	}
	if (form.getReleaseTime().contains(":"))
	{
	    form.setReleaseTime(JuvenileFacilityHelper.stripColonInDate(form.getReleaseTime()));
	}

	if (form.getAction().equals("referralTransferView"))
	{
	    return (aMapping.findForward(UIConstants.BACK_REFERRAL_TRANSFER));
	}
	if (form.getAction().equals("tempReleaseDecisionView"))
	{
	    //		    form.setTemporaryReleaseAllowed(form.getTemporaryReleaseAllowed());
	    return (aMapping.findForward("backToTmpRelDecision"));
	}
	return (aMapping.findForward(UIConstants.BACK));

    }

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.finish", "finish");
    }
}
