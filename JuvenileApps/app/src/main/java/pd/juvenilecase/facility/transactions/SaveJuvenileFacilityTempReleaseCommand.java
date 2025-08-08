/**
 * 
 * @author sthyagarajan
 *
 */
package pd.juvenilecase.facility.transactions;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityTempReleaseEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.CreateNotificationEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.PDCodeTableConstants;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSReferral;
import ui.common.SimpleCodeTableHelper;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

/**
 * 
 * 
 *
 */
public class SaveJuvenileFacilityTempReleaseCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		SaveJuvenileFacilityTempReleaseEvent reqEvent = (SaveJuvenileFacilityTempReleaseEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();
		
		
		boolean saveTempRelease=false;
		//update header and facility.
		
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNumber());
		JJSFacility facility  = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNumber(),reqEvent.getFacilitySequenceNum());
		
		IHome home= new Home();
		
		if(reqEvent.isObservationStatusChanged()){
			if(facility!=null && reqEvent.getSaReason()!=null || reqEvent.getSpecialAttentionCd()!=null){
					facility.setSaReasonCode(reqEvent.getSaReason());
					facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
					//facility.setSaReasonOtherComments(reqEvent.getSaReasonOtherComments());
			
					//persist Spl Attention Comments //task #34820
					String saReasonOtherComments = reqEvent.getSaReasonOtherComments();
					String juvenileNum = reqEvent.getJuvenileNumber();
					String detentionId = facility.getOID();
					if(saReasonOtherComments!=null && !saReasonOtherComments.isEmpty() && juvenileNum!=null && detentionId!=null){
						JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
						splAttentionComments.setComments(saReasonOtherComments);
						splAttentionComments.setJuvenileNum(juvenileNum);
						splAttentionComments.setDetentionId(detentionId);
						home.bind(splAttentionComments); //insert the comments.
						saveTempRelease=true;
					}	
			}
		}
				
		//release logic goes here
		if(facility!=null && reqEvent.getReleaseDate()!=null &&  reqEvent.getReleaseTime()!=null 
				&& reqEvent.getReleasedBy()!=null && reqEvent.getReleaseAuthorizedBy()!=null 
				   && reqEvent.getReleaseTo()!=null && reqEvent.getReleaseReason()!=null && reqEvent.getTempReleaseReason()!=null){
				facility.setReleaseDate(reqEvent.getReleaseDate());
				facility.setReleaseTime(reqEvent.getReleaseTime());
				facility.setReleaseBy(reqEvent.getReleasedBy());
				facility.setReleaseAuthorizedBy(reqEvent.getReleaseAuthorizedBy());
				facility.setReleaseTo(reqEvent.getReleaseTo());
				facility.setReleaseReason(reqEvent.getReleaseReason());
				facility.setTempReleaseReasonCd(reqEvent.getTempReleaseReason());
				facility.setTempReleaseOtherComments(reqEvent.getTempReleaseReasonOtherComments());
				//facility.setComments(reqEvent.getFacilityAdmissionComments());
				// add facility comments for new recs.added for #51737
				if(reqEvent.getFacilityAdmissionComments()!=null && !reqEvent.getFacilityAdmissionComments().isEmpty()){
					JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
					comments.setComments(reqEvent.getFacilityAdmissionComments());
					comments.setJuvenileNum(reqEvent.getJuvenileNumber());
					comments.setDetentionId(facility.getOID());
					home= new Home();
					home.bind(comments); //insert the comments.
				}
				saveTempRelease=true;
		}
		//update status in header and the JJSfacility as well other fields.
	 	if(saveTempRelease){	
	 		try{
		 		facility.setDetentionStatus("T");
		 		header.setFacilityStatus("T");
		 		home.bind(facility);
	 		}catch(Exception ex){
	 			ErrorResponseEvent evt = new ErrorResponseEvent();
	 			evt.setException(ex);
	 			evt.setMessage("**** Exception while updating JJS_DETENTION / JJS HEADER****");
	 			dispatch.postEvent(evt);
	 		}
	 		
	 		//US 39468 - update the detention status in JJS
			JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvenileNumber() );
			if(juvenile!=null)
			{
				juvenile.setDetentionStatusId("T");			
			}	
	 		
	 		/** 
			 * M204 Legacy Update:  
			   Add �ReleaseDate� (JUVENILE_JJS_REFERRAL.FinalReleaseDate) to the referral record identified by the JUVENILE_FACILITY_ADMISSION_RELEASE.BookingReferral.
			 */
			GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
			jjsEvt.setJuvenileNum(reqEvent.getJuvenileNumber());
			jjsEvt.setReferralNum(reqEvent.getBookingReferralNumber());
			
			Iterator<JJSReferral> referralItr = JJSReferral.findAll(jjsEvt);
			if(referralItr.hasNext()) {
				JJSReferral jjsReferral = referralItr.next();
				if(jjsReferral.getFinalReleaseDate()==null || (jjsReferral.getFinalReleaseDate()!=null && !jjsReferral.getFinalReleaseDate().equals(reqEvent.getReleaseDate()))){
					jjsReferral.setFinalReleaseDate(reqEvent.getReleaseDate());
					home.bind(jjsReferral);
					respEvent.setSaveable(true);
				}
			}
			saveTempRelease=false;
	 	
		 	//NOTIFICATION
			//Send Notification.
			sendTempReleaseNotification(reqEvent);
			
			
			//get the calendar detentionRecord.
			List<DocketEventResponseEvent> calendarDetentions = JuvenileFacilityHelper.getCalendarDetentions(reqEvent.getJuvenileNumber());
			//sorts in descending order by seq num.
			Collections.sort((List<DocketEventResponseEvent>)calendarDetentions,Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
					@Override
					public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
						return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					}
			}));
			
			if(calendarDetentions!=null){
	    		Iterator<DocketEventResponseEvent>  calendarDetention = calendarDetentions.iterator();
	    		while(calendarDetention.hasNext())
	    		{
	    			DocketEventResponseEvent detention = (DocketEventResponseEvent) calendarDetention.next();
	    			if(header.getNextHearingDate()!=null)
	    			{
	    				Date hearingDate = header.getNextHearingDate();
	    				if(hearingDate == reqEvent.getReleaseDate() || hearingDate.after(reqEvent.getReleaseDate()))
	    				{
	    					if(detention.getCourtResult().isEmpty()){
	    						//send Notification and email
	    						sendPendingDetentionHearingNotification(reqEvent,detention);
	    						break;
	    					}
						}
	    			}
	    		}
			}
    	}
	 	dispatch.postEvent(respEvent);
	}
	
	/**
	 * Send Final Release Notification
	 * @param form
	 * @param detention
	 */
	private void sendTempReleaseNotification(SaveJuvenileFacilityTempReleaseEvent releaseReqEvt){
		//JUVENILE_NOTIFICATION.To:  The JPOs currently assigned to the juvenile�s active and/or pending casefiles should receive a notice that a juvenile on their caseload has been temporarily released from a facility.  
		//send notification
		
		
		//Bug #41634
		Iterator iter = JuvenileCasefile.findAll("juvenileId", releaseReqEvt.getJuvenileNumber());
		while (iter.hasNext())
		{
			JuvenileCasefile juvenileCasefile = (JuvenileCasefile) iter.next();
			//Populating Casefile Details using casefileId which has been fetched above.
			if(juvenileCasefile.getCaseStatusId()!=null && (juvenileCasefile.getCaseStatusId().equalsIgnoreCase("A") || juvenileCasefile.getCaseStatusId().equalsIgnoreCase("P")))
			{
				OfficerProfile officer = juvenileCasefile.getProbationOfficer();				
			
				if(officer!=null && officer.getLogonId()!=null && !officer.getLogonId().isEmpty())
				{
					   	JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
					   	respEvt.setSubject("Juvenile has temporarily been released from "+ releaseReqEvt.getDetainedFacility());
					    respEvt.setIdentity(officer.getLogonId());
					    respEvt.setJuvenileNum(releaseReqEvt.getJuvenileNumber());
					
					    String otherComments="";
					    if(releaseReqEvt.getTempReleaseReason().equals("OT")){
					    	otherComments = releaseReqEvt.getTempReleaseReasonOtherComments();
					    }
					    
					    StringBuffer notifMessage = new StringBuffer(100);
					    
					    notifMessage.append( releaseReqEvt.getJuvenileName());
					    notifMessage.append(", Juvenile #");
					    notifMessage.append(releaseReqEvt.getJuvenileNumber());
					    notifMessage.append(", under supervision# ");
					    notifMessage.append(releaseReqEvt.getCurrentSupervisionNum());
					    notifMessage.append(" was temporarily released from ");
					    notifMessage.append(releaseReqEvt.getDetainedFacility());
					    notifMessage.append(" for the following reason: ");
					    notifMessage.append(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TEMP_RELEASE_REASON, releaseReqEvt.getTempReleaseReason()));
					    if(otherComments!=null && !otherComments.isEmpty()){
					    	notifMessage.append("-");
					    	notifMessage.append(otherComments+".");
					    }
					    respEvt.setNotificationMessage(notifMessage.toString());
					    	
					    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
					    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
					    notificationEvent.setNotificationTopic("JC.FACILITY.TEMPRELEASE.NOTIFICATION");
					    notificationEvent.setSubject(respEvt.getSubject());
					    notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
					    notificationEvent.addContentBean(respEvt);
					    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
		    		    MessageUtil.processReturnException( response ) ;
					    	//end send notification	
				}
			}
		}
	}
	
	/**
	 * send PendingDetentionHearing Notification to the JPO
	 * @param tempReleaseEvt
	 * @param detention
	 */
	private void sendPendingDetentionHearingNotification(SaveJuvenileFacilityTempReleaseEvent tempReleaseEvt ,DocketEventResponseEvent detention){
		//send notification
				Collection<UserGroupResponseEvent> userGrpResEvent = SecurityUIHelper.getUserGroupByName("DETENTION HEARING");
				Collection<OfficerProfileResponseEvent>  securityRespEvent =null;
				if(userGrpResEvent!=null){
					 Iterator<UserGroupResponseEvent> userGrpResIter = userGrpResEvent.iterator();
					 while(userGrpResIter.hasNext())
					 {
						 UserGroupResponseEvent response = (UserGroupResponseEvent) userGrpResIter.next();
						 securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup(response.getUserGroupId());
					 }
				}	
				if(securityRespEvent!=null){
					Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
				
				    while(securityRespIter.hasNext())
				    {
				    	OfficerProfileResponseEvent	securityResEvent =	securityRespIter.next();
					   	//start send notification for the detention hearing group.
					   	JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
					   	respEvt.setSubject("Juvenile with a pending detention hearing has been temporarily released.");
					    respEvt.setIdentity(securityResEvent.getJIMSLogonId());
					    respEvt.setJuvenileNum(detention.getJuvenileNumber());
					
					    String notifMessage = detention.getJuvenileName()+", Juvenile #"+detention.getJuvenileNumber()+", was temporarily released on  " +
					    		tempReleaseEvt.getReleaseDate()+". The juvenile has a pending detention hearing on "
									+tempReleaseEvt.getNextHearingDate() + " in court #"+detention.getCourt();
					    	respEvt.setNotificationMessage(notifMessage);
					    	
					    	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
					    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
					    	notificationEvent.setNotificationTopic("JC.FACILITY.TEMPRELEASE.NOTIFICATION");
					    	notificationEvent.setSubject(respEvt.getSubject());
					    	notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
					    	notificationEvent.addContentBean(respEvt);
					    	CompositeResponse response = MessageUtil.postRequest(notificationEvent);
		    		        MessageUtil.processReturnException( response ) ;
					    	//end send notification	
		    		        
		    		        
		    		    	//Send email to the Detention hearing group
					    	SendEmailEvent sendEmailEvent = new SendEmailEvent();
					    	StringBuffer message = new StringBuffer(100);
					    	String officerEmailId = "";
							List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("logonId", UIUtil.getCurrentUserID());
							Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
							if(events.hasNext()){
								OfficerProfileResponseEvent resp = events.next();
									officerEmailId=resp.getEmail();
							}
							   

					    	if((officerEmailId!=null && !officerEmailId.equals(""))&& (securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals("")))
					    	{
					    		sendEmailEvent.setFromAddress(officerEmailId);
					    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
					    		message.append("Juvenile with a pending detention hearing has been temporarily released ");
					    		message.append(tempReleaseEvt.getJuvenileName()); 
					    		message.append(",Juvenile #");
					    		message.append(tempReleaseEvt.getJuvenileNumber());
					    		message.append(", was temporarily released on ");
					    		message.append(tempReleaseEvt.getReleaseDate());
					    		message.append(". The juvenile has a pending detention hearing on ");
					    		message.append(tempReleaseEvt.getNextHearingDate());
					    		message.append(" in court #");
					    		message.append(detention.getCourt());
					    		sendEmailEvent.setMessage(message.toString());
					    		sendEmailEvent.setSubject("Juvenile with a pending detention hearing has been temporarily released.");
					    		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					    		dispatch.postEvent(sendEmailEvent);
					    	}
				 	}
				}
			}
}
