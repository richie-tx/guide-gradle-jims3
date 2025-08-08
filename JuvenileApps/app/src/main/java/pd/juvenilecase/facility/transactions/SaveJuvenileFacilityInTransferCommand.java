/**
 * 
 */
package pd.juvenilecase.facility.transactions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityInTransferEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.CreateNotificationEvent;
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
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.JuvenileCasefile;
import pd.security.PDSecurityHelper;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

/**
 * @author sthyagarajan
 * SaveJuvenileFacilityInTransferCommand
 */
public class SaveJuvenileFacilityInTransferCommand implements ICommand{
	
	@Override
	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		SaveJuvenileFacilityInTransferEvent reqEvent = (SaveJuvenileFacilityInTransferEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();
		
		//update header and facility.
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNumber());
		JJSFacility facility  = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNumber(),reqEvent.getFacilitySequenceNum());
		
		IHome home= new Home();
		if(reqEvent.isObservationStatusChanged()){
			//special attention update
			if(facility!=null && reqEvent!=null && (reqEvent.getSaReason()!=null || reqEvent.getSpecialAttentionCd()!=null)){
				facility.setSaReasonCode(reqEvent.getSaReason());
				facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
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
				}	
			}
		}
		
		//release logic goes here
		if(facility!=null && reqEvent!=null){
			facility.setReleaseDate(reqEvent.getReleaseDate());
			facility.setReleaseTime(reqEvent.getReleaseTime());
			facility.setReleaseBy(reqEvent.getReleasedBy());
			facility.setReleaseAuthorizedBy(reqEvent.getReleaseAuthorizedBy());
			facility.setReleaseTo(reqEvent.getReleaseTo());
			facility.setReleaseReason(reqEvent.getReleaseReason());
			facility.setTransferToFacility(reqEvent.getTransferToFacility());
			facility.setOutcome(reqEvent.getOutcome());
			//facility.setComments(reqEvent.getFacilityAdmissionComments());
			if(reqEvent.getFacilityAdmissionComments()!=null && !reqEvent.getFacilityAdmissionComments().isEmpty()){
				JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
				comments.setComments(reqEvent.getFacilityAdmissionComments());
				comments.setJuvenileNum(reqEvent.getJuvenileNumber());
				comments.setDetentionId(facility.getOID());
				home= new Home();
				home.bind(comments); //insert the comments.
			}
			facility.setDetentionStatus(reqEvent.getFacilityStatus());
			//do not create  a new admission record.
			//setOtherAdmitDetails(reqEvent,newFacility,facility,header); U.S 39182
		}
		
		try{
			home.bind(facility);
		}catch(Exception ex){
			ErrorResponseEvent evt = new ErrorResponseEvent();
 			evt.setException(ex);
 			evt.setMessage("****Exception while updating JJS_DETENTION ****");
 			dispatch.postEvent(evt);
		}
		
		try{
			//update header U.S 39182
			//header.setLastSequenceNumber(String.valueOf(Integer.parseInt(saveAdmit.getFacilitySequenceNumber())+20));
			header.setLcDate(DateUtil.getCurrentDate());
			header.setLcTime(Calendar.getInstance().getTime());
			header.setLcUser(PDSecurityHelper.getLogonId());
			header.setHeaderFacility("DET");
			header.setFacilityStatus(reqEvent.getFacilityStatus());	
			header.setFacilityCode(reqEvent.getTransferToFacility()); //TODO U.S confirm with regina one more time. not confident.
			home.bind(header);
		}catch(Exception ex){
			ErrorResponseEvent evt = new ErrorResponseEvent();
 			evt.setException(ex);
 			evt.setMessage("****Exception while updating JJS HEADER****");
 			dispatch.postEvent(evt);
		}
		
		//US 39468 - update the detention status in JJS
		JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvenileNumber() );
		if(juvenile!=null)
		{
			juvenile.setDetentionStatusId(reqEvent.getFacilityStatus());			
		}	
		//In transfer notice JPO
		sendInTransferNotificationToJPO(reqEvent);
		//sendInTransferNotificationToCLM
		sendInTransferNotificationToCLMS(reqEvent);
		
		List<DocketEventResponseEvent> calendarDetentions = JuvenileFacilityHelper.getCalendarDetentions(reqEvent.getJuvenileNumber());
		//sorts in descending order by seq num.
		Collections.sort((List<DocketEventResponseEvent>)calendarDetentions,Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
				return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
			}
		}));

		//2.9.1. Activity: Determine �In Transfer� TransferToFacility
		if(reqEvent.getTransferToFacility().equals("HCP")){
			//get the calendar detentionRecord.
			 if(calendarDetentions!=null){
		    		Iterator<DocketEventResponseEvent>  calendarDetention = calendarDetentions.iterator();
		    		while(calendarDetention.hasNext()){
		    			DocketEventResponseEvent detention = (DocketEventResponseEvent) calendarDetention.next();
		    			if(detention.getEventDate().equals(DateUtil.getCurrentDate())|| detention.getEventDate().after(DateUtil.getCurrentDate()) && detention.getCourtResult().isEmpty()){
		    				//send notification to DH group.
		    				sendInTransferNotification(reqEvent,detention);
		    				break;
		    			}
		    		}
			 	}
			}
		respEvent.setSaveable(true);
		dispatch.postEvent(respEvent);
	}
	
	/**
	 * Send Final Release Notification
	 * @param form
	 * @param detention
	 */
	private void sendInTransferNotificationToCLMS(SaveJuvenileFacilityInTransferEvent releaseReqEvt){
		/**
		 * Bug no #40795
		 * The CLMs located at the receiving facility (TransferToFacility)
		 * should receive a notice that a juvenile has been transferred to their
		 * facility: OFFICER.DepartmentCode = JUV; OFFICER.JuvLocation =
		 * TransferToFacility; and, OFFICER.OfficerSubType = Caseload Manager
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String transferToFacility = releaseReqEvt.getTransferToFacility();
		String officerEmailId = "";
		if(transferToFacility!=null && !transferToFacility.isEmpty())
		{
		   Iterator<JuvLocationUnit> juvLocUnitsItr = JuvLocationUnit.findAll("juvUnitCd", transferToFacility);
			
			while (juvLocUnitsItr.hasNext())
			{
				JuvLocationUnit locationUnit = juvLocUnitsItr.next();
				if(locationUnit!=null && locationUnit.getStatus()!=null && locationUnit.getStatus().equals("A")){
					List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("juvUnitId",locationUnit.getJuvLocUnitId());
					if(officerprofiles!=null){
						Iterator<OfficerProfileResponseEvent> officers = officerprofiles.iterator();
						while (officers.hasNext())
						{
							OfficerProfileResponseEvent officerResp = officers.next();
							officerEmailId=officerResp.getEmail();
							if(officerResp.getStatusId()!=null && officerResp.getStatusId().equals("A") && (officerEmailId!=null && !officerEmailId.equals("")) && officerResp.getOfficerSubTypeId().equals("CLM"))
							{
								JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
								respEvt.setSubject("Juvenile was transferred on "+ sdf.format(releaseReqEvt.getReleaseDate())+" to your "+releaseReqEvt.getTransferToFacilityDesc());
								respEvt.setIdentity(officerResp.getUserId());
								respEvt.setJuvenileNum(releaseReqEvt.getJuvenileNumber());
								
								String notifMessage = releaseReqEvt.getJuvenileName()+", Juvenile#: "+releaseReqEvt.getJuvenileNumber()+", under Supervision#: " +
								releaseReqEvt.getCurrentSupervisionNum()+" and Referral#: "+releaseReqEvt.getBookingReferralNumber()+" was TRANSFERRED on "+sdf.format(releaseReqEvt.getReleaseDate())+" to your " + releaseReqEvt.getTransferToFacilityDesc();
												
								respEvt.setNotificationMessage(notifMessage);
								    	
								CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								notificationEvent.setNotificationTopic("JC.FACILITY.TRANSFER.NOTIFICATION");
								notificationEvent.setSubject(respEvt.getSubject());
								notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
								notificationEvent.addContentBean(respEvt);
								CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								MessageUtil.processReturnException( response ) ;
					    		    
					    		//Send email to the Detention hearing group
							    SendEmailEvent sendEmailEvent = new SendEmailEvent();
							    StringBuffer message = new StringBuffer(100);
								
								
							    sendEmailEvent.setFromAddress(officerEmailId);
							    UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,officerEmailId);
							    message.append(releaseReqEvt.getJuvenileName());
							    message.append(",Juvenile#: ");
							    message.append(releaseReqEvt.getJuvenileNumber());
							    message.append(", under supervision#: ");
							    message.append(releaseReqEvt.getCurrentSupervisionNum());
							    message.append(" and Referral#: ");
							    message.append(releaseReqEvt.getBookingReferralNumber());
							    message.append(" was TRANSFERRED on ");
							    message.append(sdf.format(releaseReqEvt.getReleaseDate()));
							    message.append(" to your ");
							    message.append(releaseReqEvt.getTransferToFacilityDesc());
							    sendEmailEvent.setMessage(message.toString());
							    sendEmailEvent.setSubject("Juvenile was transferred on "+ sdf.format(releaseReqEvt.getReleaseDate())+" to your "+releaseReqEvt.getTransferToFacilityDesc()+".");
							    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
						   		dispatch.postEvent(sendEmailEvent);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Send Final Release Notification
	 * @param form
	 * @param detention
	 */
	private void sendInTransferNotificationToJPO(SaveJuvenileFacilityInTransferEvent releaseReqEvt){
		//send notification
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Iterator<JuvenileCasefile> iter = JuvenileCasefile.findAll("juvenileId", releaseReqEvt.getJuvenileNumber());
		while (iter.hasNext())
		{
			JuvenileCasefile juvenileCasefile = iter.next();
			//Populating Casefile Details using casefileId which has been fetched above.
			if(juvenileCasefile.getCaseStatusId()!=null && (juvenileCasefile.getCaseStatusId().equalsIgnoreCase("A") || juvenileCasefile.getCaseStatusId().equalsIgnoreCase("P")))
			{
				OfficerProfile officer = juvenileCasefile.getProbationOfficer();				
				
				if(officer!=null && officer.getLogonId()!=null && !officer.getLogonId().isEmpty()){
						   	
					JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
					respEvt.setSubject("Juvenile was transferred on "+ sdf.format(releaseReqEvt.getReleaseDate())+" to "+releaseReqEvt.getTransferToFacilityDesc());
					respEvt.setIdentity(officer.getLogonId());
					respEvt.setJuvenileNum(releaseReqEvt.getJuvenileNumber());
			
			  
				    String notifMessage = releaseReqEvt.getJuvenileName()+", Juvenile#: "+releaseReqEvt.getJuvenileNumber()+", under supervision#: " +
				    releaseReqEvt.getCurrentSupervisionNum()+" and Referral#: "+releaseReqEvt.getBookingReferralNumber()+" was TRANSFERRED on "+sdf.format(releaseReqEvt.getReleaseDate())+" to " + releaseReqEvt.getTransferToFacilityDesc();
								
				    respEvt.setNotificationMessage(notifMessage);
				    	
				    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
				    		EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
				    notificationEvent.setNotificationTopic("JC.FACILITY.TRANSFER.NOTIFICATION");
				    notificationEvent.setSubject(respEvt.getSubject());
				    notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
				    notificationEvent.addContentBean(respEvt);
				    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
	    		    MessageUtil.processReturnException( response ) ;
	    		    
	    		    //Send email to the Detention hearing group
			    	SendEmailEvent sendEmailEvent = new SendEmailEvent();
			    	StringBuffer message = new StringBuffer(100);
			    	 
			         String officerEmailId = "";
					 List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("logonId", officer.getLogonId());
					 Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
					 if(events.hasNext()){
						OfficerProfileResponseEvent resp = events.next();
						officerEmailId=resp.getEmail();
						if((officerEmailId!=null && !officerEmailId.equals("")))
				    	{
				    		sendEmailEvent.setFromAddress(officerEmailId);
				    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,officerEmailId);
				    		message.append(releaseReqEvt.getJuvenileName());
				    		message.append(",Juvenile#: ");
				    		message.append(releaseReqEvt.getJuvenileNumber());
				    		message.append(", under supervision#: ");
				    		message.append(releaseReqEvt.getCurrentSupervisionNum());
				    		message.append(" and Referral#: ");
				    		message.append(releaseReqEvt.getBookingReferralNumber());
				    		message.append(" was TRANSFERRED on ");
				    		message.append(sdf.format(releaseReqEvt.getReleaseDate()));
				    		message.append(" to ");
				    		message.append(releaseReqEvt.getTransferToFacilityDesc());
				    		sendEmailEvent.setMessage(message.toString());
				    		sendEmailEvent.setSubject("Juvenile was transferred on "+ sdf.format(releaseReqEvt.getReleaseDate())+" to "+releaseReqEvt.getTransferToFacilityDesc()+".");
				    		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					   		dispatch.postEvent(sendEmailEvent);
				    	}
					 }
				}
			}
		}
	}
	
	/**
	 * Send Final Transfer Release Notification
	 * @param form
	 * @param detention
	 */
	private void sendInTransferNotification(SaveJuvenileFacilityInTransferEvent releaseReqEvt,DocketEventResponseEvent detention){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//send notification
		Collection<UserGroupResponseEvent> userGrpResEvent = SecurityUIHelper.getUserGroupByName("DETENTION HEARING");
		Collection<OfficerProfileResponseEvent>  securityRespEvent =null;
		if(userGrpResEvent!=null){
			 Iterator<UserGroupResponseEvent> userGrpResIter = userGrpResEvent.iterator();
			 while(userGrpResIter.hasNext())
			 {
				 UserGroupResponseEvent response = userGrpResIter.next();
				 securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup(response.getUserGroupId());
			 }
		}	
		if(securityRespEvent!=null){
			Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
		
		    while(securityRespIter.hasNext())
		    {
		    	OfficerProfileResponseEvent securityResEvent = securityRespIter.next();
			   	//start send notification for the detention hearing group.
			   	JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
			  	respEvt.setSubject("Juvenile transferred to HCPC has a pending detention hearing.");
			    respEvt.setIdentity(securityResEvent.getJIMSLogonId());
			    respEvt.setJuvenileNum(releaseReqEvt.getJuvenileNumber());
			
			    String notifMessage = releaseReqEvt.getJuvenileName()+", Juvenile#: "+releaseReqEvt.getJuvenileNumber()+", was transferred to the Harris County Psychiatric Center on " +
			    		sdf.format(releaseReqEvt.getReleaseDate())+". The juvenile has a pending detention hearing on to " + sdf.format(detention.getEventDate()) +" in court#: "+ detention.getCourt();
									
				respEvt.setNotificationMessage(notifMessage);
				CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
					    		EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
				   notificationEvent.setNotificationTopic("JC.FACILITY.TRANSFER.NOTIFICATION");
				   notificationEvent.setSubject(respEvt.getSubject());
				   notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
				   notificationEvent.addContentBean(respEvt);
				   CompositeResponse response = MessageUtil.postRequest(notificationEvent);
		    	   MessageUtil.processReturnException( response ) ;
		    	  //end send notification	
		    		    
		    	   //Send email to the Detention hearing group
				   SendEmailEvent sendEmailEvent = new SendEmailEvent();
				   StringBuffer message = new StringBuffer(100);
				  
				   //String currentUserEmail = SecurityUIHelper.getUser(UIUtil.getCurrentUserID()).getEmail();
				   String officerEmailId = "";
				   List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("logonId", UIUtil.getCurrentUserID());
				   Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
				   if(events.hasNext()){
						OfficerProfileResponseEvent resp = events.next();
						officerEmailId=resp.getEmail();
						if((officerEmailId!=null && !officerEmailId.equals(""))&& (securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals("")))
						{
					   		sendEmailEvent.setFromAddress(officerEmailId);
					   		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,officerEmailId);
					   		message.append(releaseReqEvt.getJuvenileName());
					   		message.append(",Juvenile#: ");
					   		message.append(releaseReqEvt.getJuvenileNumber());
					   		message.append(", was transferred to the Harris County Psychiatric Center on ");
					   		message.append(" was TRANSFERRED on ");
					   		message.append(sdf.format(releaseReqEvt.getReleaseDate()));
					   		message.append(". The juvenile has a pending detention hearing on to ");
					   		if(releaseReqEvt.getDetainedDate()!=null)
					   		{
					   		    message.append(sdf.format(releaseReqEvt.getDetainedDate()));
					   		}
					   		message.append(" in court#: ");
					   		message.append(detention.getCourt());
					   		sendEmailEvent.setMessage(message.toString());
					   		sendEmailEvent.setSubject("Juvenile transferred to HCPC has a pending detention hearing.");
					   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					   		dispatch.postEvent(sendEmailEvent);
						}	
					}
		    	}
			}
		}
	
	/**
	 * Not Used anymore.
	 * Create new Facility Record.
	 * set other facility data from the existing record.
	 * @param fac
	 * @param saveAdmit
	 * U.S 39182
	 */
	@SuppressWarnings("unused")
	private void setOtherAdmitDetails(SaveJuvenileFacilityInTransferEvent reqEvent,JJSFacility fac, JJSFacility saveAdmit, JJSHeader header)
	{
		fac.setAdmittedByJPO(saveAdmit.getAdmittedByJPO());
		//fac.setAdmitDate(saveAdmit.getAdmitDate());
		//fac.setAdmitTime(saveAdmit.getAdmitTime());
		fac.setAdmitAuthority(saveAdmit.getAdmitAuthority());
		fac.setAdmitReason(saveAdmit.getAdmitReason());
		fac.setReasonChangeFlag(saveAdmit.isReasonChangeFlag());
		
		fac.setBookingSupervisionNum(saveAdmit.getBookingSupervisionNum());		
		fac.setChangeExplanation(saveAdmit.getChangeExplanation());
		fac.setComments(saveAdmit.getComments());
		
		fac.setCurrentOffense(saveAdmit.getCurrentOffense());	
		fac.setCurrentReferral(saveAdmit.getCurrentReferral());
		fac.setCurrentSupervisionNum(saveAdmit.getCurrentSupervisionNum());
		
		fac.setDetainedByInd(saveAdmit.getDetainedByInd());
		fac.setDetainedDate(saveAdmit.getDetainedDate());
		fac.setDetainedFacility(saveAdmit.getDetainedFacility());
		fac.setDetentionStatus(saveAdmit.getDetentionStatus());
		
		fac.setEscapeCode(saveAdmit.getEscapeCode());
		fac.setEscapeAttempts(saveAdmit.getEscapeAttempts());
		fac.setEscapeAttemptComments(saveAdmit.getEscapeAttemptComments());
		
		fac.setOutcome(saveAdmit.getOutcome());
		
		if(saveAdmit.getOriginalAdmitDate()!=null &&!saveAdmit.getOriginalAdmitDate().equals("") ){
			fac.setOriginalAdmitDate(saveAdmit.getOriginalAdmitDate());
			fac.setOriginalAdmitTime(saveAdmit.getOriginalAdmitTime());
		}else{
			fac.setOriginalAdmitDate(saveAdmit.getAdmitDate());
			fac.setOriginalAdmitTime(saveAdmit.getAdmitTime());
		}
		
		if(saveAdmit.getOriginalAdmitDate()!=null){
			fac.setOriginalAdmitDate(saveAdmit.getOriginalAdmitDate());
			fac.setOriginalAdmitTime(saveAdmit.getOriginalAdmitTime());
		}else{
			//Collection<JuvenileDetentionFacilitiesResponseEvent> detentionFacilities = JuvenileFacilityHelper.getNonPostFacilityDetails(reqEvent.getJuvenileNumber()); // get all facilities for the juvenile.
			JJSFacility origDetRec = null;
		    if(reqEvent.getBookingReferralNumber()!=null){
		    	origDetRec = JuvenileFacilityHelper.getOriginalAdmitRec(reqEvent.getJuvenileNumber(), reqEvent.getBookingReferralNumber());
		    }
		    fac.setOriginalAdmitDate(origDetRec.getAdmitDate());
		    fac.setOriginalAdmitTime(origDetRec.getAdmitTime());
		}
		
		fac.setTempReleaseOtherComments(saveAdmit.getTempReleaseOtherComments());
		fac.setTempReleaseReasonCd(saveAdmit.getTempReleaseReasonCd());
		
		fac.setTjpcseqnum(saveAdmit.getTjpcseqnum());
		fac.setTransferToFacility(reqEvent.getTransferToFacility());
		
		fac.setSaReasonCode(reqEvent.getSaReason());
		fac.setSpecialAttention(reqEvent.getSpecialAttentionCd());
		fac.setSaReasonOtherComments(reqEvent.getSaReasonOtherComments());
		
		fac.setSecureStatus(saveAdmit.getSecureStatus());
		fac.setSecureIndicatorChangeFlag(saveAdmit.isSecureIndicatorChangeFlag());
		
		fac.setReferralNumber(saveAdmit.getReferralNumber());		
		fac.setReceiptNumber(saveAdmit.getReceiptNumber());
		
		fac.setLockerNumber(saveAdmit.getLockerNumber());		
		fac.setFloorNum(saveAdmit.getFloorNum());
		fac.setUnit(saveAdmit.getUnit());
		fac.setRoomNum(saveAdmit.getRoomNum());
		fac.setMultipleOccupyUnit(saveAdmit.getMultipleOccupyUnit());
		
		fac.setFacilitySequenceNumber(String.valueOf(Integer.parseInt(saveAdmit.getFacilitySequenceNumber())+20)); //increase Facility Sequence number to 20.
		fac.setJuvenileNumber(saveAdmit.getJuvenileNumber());
		
	
		fac.setReturnDate(saveAdmit.getReturnDate());
		fac.setReturnTime(saveAdmit.getReturnTime());
		fac.setReturnStatus(saveAdmit.getReturnStatus());
		
		fac.setReasonChangeFlag(saveAdmit.isReasonChangeFlag());
		fac.setOtherChangeFlag(saveAdmit.isOtherChangeFlag());
		fac.setLocationChangeFlag(saveAdmit.isLocationChangeFlag());
		fac.setRecType("DETENTION");
		
		//update header
		header.setLcDate(DateUtil.getCurrentDate());
		header.setLcTime(Calendar.getInstance().getTime());
		header.setLcUser(PDSecurityHelper.getLogonId());
		header.setHeaderFacility("DET");
		header.setFacilityStatus(reqEvent.getFacilityStatus());	
	}
}