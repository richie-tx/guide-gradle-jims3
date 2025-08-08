package pd.juvenilecase.facility.transactions;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.facility.SaveJuvenileFacilityReleaseEvent;
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
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.PDCodeTableConstants;
import pd.codetable.SimpleCodeTableHelper;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSReferral;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class SaveJuvenileFacilityReleaseCommand implements ICommand{
	
	@Override
	public void execute(IEvent event) throws Exception {
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SaveJuvenileFacilityReleaseEvent reqEvent = (SaveJuvenileFacilityReleaseEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();
		
		//update header and facility.
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNumber());
		JJSFacility facility  = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNumber(),reqEvent.getFacilitySequenceNum());
		
		IHome home= new Home();
			if(facility!=null){
				if(reqEvent.isObservationStatusChanged()){
					//special attention update
					if(reqEvent.getSaReason()!=null){
						facility.setSaReasonCode(reqEvent.getSaReason());
						facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
					}
					
					String saReasonOtherComments = reqEvent.getSaReasonOtherComments();
					String juvenileNum = reqEvent.getJuvenileNumber();
					String detentionId = facility.getOID();
					
					if(saReasonOtherComments!=null && !saReasonOtherComments.isEmpty() && juvenileNum!=null && detentionId!=null){
						JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
						splAttentionComments.setComments(saReasonOtherComments);
						splAttentionComments.setJuvenileNum(juvenileNum);
						splAttentionComments.setDetentionId(detentionId);
						home.bind(splAttentionComments); //insert the comments.
					}
				}
			}
		
		//release logic goes here
		if(facility!=null){
			facility.setReleaseDate(reqEvent.getReleaseDate());
			facility.setReleaseTime(reqEvent.getReleaseTime());
			facility.setReleaseBy(reqEvent.getReleasedBy());
			facility.setReleaseAuthorizedBy(reqEvent.getReleaseAuthorizedBy());
			//facility.setComments(reqEvent.getFacilityAdmissionComments());
			if(reqEvent.getFacilityAdmissionComments()!=null && !reqEvent.getFacilityAdmissionComments().isEmpty()){
				JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
				comments.setComments(reqEvent.getFacilityAdmissionComments());
				comments.setJuvenileNum(reqEvent.getJuvenileNumber());
				comments.setDetentionId(facility.getOID());
				home= new Home();
				home.bind(comments); //insert the comments.
			}
		}
		
		if(facility!=null && !reqEvent.getReleaseTo().equals("TRN")){
				facility.setReleaseTo(reqEvent.getReleaseTo());
				facility.setOutcome(reqEvent.getOutcome());
				facility.setReleaseReason(reqEvent.getReleaseReason());
				facility.setCustodylastName(reqEvent.getCustodylastName());
				facility.setCustodyfirstName(reqEvent.getCustodyfirstName());
				
		 		//bind facility
		 		home.bind(facility);
				//1)If juvenile is released prior to the probable causing hearing or any calendar detention hearing, 
				//all  future/pending Calendar Detention record hearings (JUVENILE_JJS_DETENTION.HEARING) need to be deleted:  
				
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
			    		while(calendarDetention.hasNext()){
			    			DocketEventResponseEvent docketEvtResp = (DocketEventResponseEvent) calendarDetention.next();

			    			if( docketEvtResp.getEventDate() != null && docketEvtResp.getEventDate().after(reqEvent.getReleaseDate()) 
			    					|| (docketEvtResp.getEventDate() == reqEvent.getReleaseDate() && DateUtil.stringToDate(reqEvent.getReleaseTime(),DateUtil.TIME_FMT_1).before(DateUtil.stringToDate(docketEvtResp.getCourtTime(),DateUtil.TIME_FMT_1)))  
			    					&& docketEvtResp.getReferralNum().equals(facility.getReferralNumber()) && docketEvtResp.getCourtResult().isEmpty()){
			    				//81390
			    				/*DeleteJJSDetentionEvent deleteCalendarDetentionEvt =
										(DeleteJJSDetentionEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.DELETEJJSDETENTION);
			    				deleteCalendarDetentionEvt.setJuvenileNumber(reqEvent.getJuvenileNumber());
			    				deleteCalendarDetentionEvt.setChainNumber(detention.getChainNumber());
			    				deleteCalendarDetentionEvt.setReferralNumber(detention.getReferralNum());
			    				deleteCalendarDetentionEvt.setSeqNumber(detention.getSeqNum());*/
			    				
			    				if(docketEvtResp.getChainNumber()!=null && !docketEvtResp.getChainNumber().isEmpty())
			    				{
			    				    Iterator<JJSCLDetention> detentionItr  = JJSCLDetention.findAll("chainNumber", docketEvtResp.getChainNumber()); // by chainnumber
			    				    if (detentionItr != null)
			    				    {
			    					while (detentionItr.hasNext())
			    					{
			    					    try
			    					    {
			    						JJSCLDetention det = detentionItr.next();
			    						//delete all future Detention setting records 
			    						if (det.getCourtDate().after(DateUtil.getCurrentDate()))
			    						{
			    						    det.setDeleted();
			    						    new Home().bind(det);
			    						}
			    					    }
			    					    catch (Exception e)
			    					    {
			    						e.printStackTrace();
			    					    }
			    					}
			    				    }
			    				}
			    				
			    				//Delete the current value in JUVENILE_MASTER.FacilityStatus; juvenile is no longer in the legal custody of the facility.  
			    			 	facility.setDetentionStatus("");
			    				
			    				//Delete JUVENILE_FACILITY_ADMISSION_HEADER.NextHearingDate.  NOTE:  Once a juvenile has been �hard� released from a facility, there is no legal reason to have a detention hearing related to this facility stay.
			    			 	Date date = null;
			    				header.setNextHearingDate(date);
			    				//bind facility
			    				home.bind(facility);
			    				//home.bind(header);
			    			}
			    		}
				 	}
			
			 		respEvent.setSaveable(true);
			 		//FINAL RELEASE NOTICE.
				 	sendFinalReleaseNotification(reqEvent);
		}
		else
		{
			if(facility!=null){
				facility.setReleaseTo(reqEvent.getReleaseTo());
				facility.setOutcome("C");
				facility.setReleaseReason(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_RELEASON_REASON, "T"));
				home.bind(facility);
			}
			
		 	respEvent.setSaveable(true);
		 	//FINAL TRANSFER NOTICE.
			sendTransferReleaseNotification(reqEvent);
		}
			//header
		if(header!=null){
			header.setFacilityStatus(null);
			home.bind(header);
		}
			//legacy update.
			//Set JUVENILE_JJS_MASTER.DetentionFacilityStatus to the value in JUVENILE_ADMISSION_HEADER.FacilityStatus.
			JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvenileNumber() );
			if(juvenile!=null)
			{
				juvenile.setDetentionStatusId("");
				home.bind(juvenile);
				respEvent.setSaveable(true);
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
				JJSReferral jjsReferral = (JJSReferral)referralItr.next();
				if(jjsReferral.getFinalReleaseDate()==null || (jjsReferral.getFinalReleaseDate()!=null && !jjsReferral.getFinalReleaseDate().equals(reqEvent.getReleaseDate()))){
					jjsReferral.setFinalReleaseDate(reqEvent.getReleaseDate());
					home.bind(jjsReferral);
					respEvent.setSaveable(true);
				}
			}
			/**
			 * Dont worry about it 
			 * HOURS Calculation:
			   Stop calculation of �hours in facility� associated to the booking referral (JDJJSMS0.REFERRAL.MS.NON.SECURE.HOURS and JDJJSMS0.REFERRAL.MS.SECURE.HOURS)
			 */
		dispatch.postEvent(respEvent);
	}
	
	
	/**
	 * Send Final Release Notification
	 * @param form
	 * @param detention
	 */
	private void sendFinalReleaseNotification(SaveJuvenileFacilityReleaseEvent releaseReqEvt){
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
			   	respEvt.setSubject("Juvenile has been released from "+ releaseReqEvt.getDetainedFacility());
			    respEvt.setIdentity(securityResEvent.getJIMSLogonId());
			    respEvt.setJuvenileNum(releaseReqEvt.getJuvenileNumber());
			
			    String notifMessage = releaseReqEvt.getJuvenileName()+", Juvenile #"+releaseReqEvt.getJuvenileNumber()+", under supervision# " +
			    		releaseReqEvt.getCurrentSupervisionNum()+" was released from " + releaseReqEvt.getDetainedFacility()+" on "
							+releaseReqEvt.getReleaseDate() + ".";
			    	respEvt.setNotificationMessage(notifMessage);
			    	
			    	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
			    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
			    	notificationEvent.setNotificationTopic("JC.FACILITY.RELEASE.NOTIFICATION");
			    	notificationEvent.setSubject(respEvt.getSubject());
			    	notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
			    	notificationEvent.addContentBean(respEvt);
			    	CompositeResponse response = MessageUtil.postRequest(notificationEvent);
    		        MessageUtil.processReturnException( response ) ;
			    	//end send notification	
			 }
		}
	}
	

	/**
	 * Send Final Transfer Release Notification
	 * @param form
	 * @param detention
	 */
	private void sendTransferReleaseNotification(SaveJuvenileFacilityReleaseEvent releaseReqEvt){
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
			   	respEvt.setSubject("Juvenile has been released and is being transferred from "+ releaseReqEvt.getDetainedFacility());
			    respEvt.setIdentity(securityResEvent.getJIMSLogonId());
			    respEvt.setJuvenileNum(releaseReqEvt.getJuvenileNumber());
			
			    String notifMessage = releaseReqEvt.getJuvenileName()+", Juvenile #"+releaseReqEvt.getJuvenileNumber()+", under supervision# " +
			    		releaseReqEvt.getCurrentSupervisionNum()+" was released from " + releaseReqEvt.getDetainedFacility()+" on "
							+releaseReqEvt.getReleaseDate() + ". Juvenile is being transferred to another facility.";
			    	respEvt.setNotificationMessage(notifMessage);
			    	
			    	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
			    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
			    	notificationEvent.setNotificationTopic("JC.FACILITY.RELEASE.NOTIFICATION");
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
