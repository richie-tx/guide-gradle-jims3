package pd.juvenilecase.facility.transactions;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityEscapeEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
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
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCaseControllerServiceNames;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.security.PDSecurityHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class SaveJuvenileFacilityEscapeCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SaveJuvenileFacilityEscapeEvent reqEvent = (SaveJuvenileFacilityEscapeEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();
		
		//release logic goes here
		IHome home= new Home();
		
		//update header and facility.
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNum());
		JJSFacility facility  = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNum(),reqEvent.getFacilitySequenceNum());
		if(facility!=null && reqEvent.isObservationStatusChanged()){
			if(reqEvent.getSaReason()!=null || reqEvent.getSpecialAttentionCd()!=null || reqEvent.getSaReasonOtherComments()!=null){
				facility.setSaReasonCode(reqEvent.getSaReason());
				facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
				//persist Spl Attention Comments //task #34820
				String saReasonOtherComments = reqEvent.getSaReasonOtherComments();
				String juvenileNum = reqEvent.getJuvenileNum();
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
		
		if(facility!=null){
			facility.setDetentionStatus("E");
			facility.setOutcome(reqEvent.getOutcome());
			facility.setReleaseReason(reqEvent.getReleaseReason());
			facility.setEscapeCode(reqEvent.getEscapeCode());
			//facility.setComments(reqEvent.getComments()); //escape comments.
			if(reqEvent.getFacilityAdmissionComments()!=null && !reqEvent.getFacilityAdmissionComments().isEmpty()){
				JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
				comments.setComments(reqEvent.getFacilityAdmissionComments());
				comments.setJuvenileNum(reqEvent.getJuvenileNum());
				comments.setDetentionId(facility.getOID());
				home= new Home();
				home.bind(comments); //insert the comments.
			}
		}
		
		if(header!=null){
			header.setRelocationDate(reqEvent.getRelocationDate());
			header.setRelocationTime(reqEvent.getRelocationTime());
			header.setLcDate(DateUtil.getCurrentDate());
			header.setLcTime(Calendar.getInstance().getTime());
			header.setLcUser(PDSecurityHelper.getLogonId());
			header.setHeaderFacility(reqEvent.getHeaderFacility());
			header.setFacilityStatus("E");
		}
		
		try{
			home.bind(facility);
			home.bind(header);
		}catch(Exception ex){
			ErrorResponseEvent evt = new ErrorResponseEvent();
	 		evt.setException(ex);
	 		evt.setMessage("**** Exception while updating JJS_DETENTION / JJS_HEADER****");
	 		dispatch.postEvent(evt);
		}
		//US 39468 - update the detention status in JJS
		JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvenileNum() );
		if(juvenile!=null)
		{
			juvenile.setDetentionStatusId("E");			
		}	
		respEvent.setSaveable(true);
		
		//2.4.7. Activity: Determine Escape Detention Hearing status
		//get the calendar detentionRecord.
		List<DocketEventResponseEvent> calendarDetentions = JuvenileFacilityHelper.getCalendarDetentions(reqEvent.getJuvenileNum());
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
    				if(header.getNextHearingDate().equals(DateUtil.stringToDate(reqEvent.getEscapeDate(), DateUtil.DATE_FMT_1))
    						|| header.getNextHearingDate().after(DateUtil.stringToDate(reqEvent.getEscapeDate(), DateUtil.DATE_FMT_1)))
    				{
    					if(detention.getCourtResult().isEmpty()){
    						sendEscapeNotification(reqEvent,detention);
    						break;
    					}
					}
    			}
    		}
    	}

		//Record Trait.
		SaveJuvenileTraitsEvent saveEvent = (SaveJuvenileTraitsEvent)
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJUVENILETRAITS ) ;
		
		saveEvent.setJuvenileNum( reqEvent.getJuvenileNum()) ;
		saveEvent.setSupervisionNum( reqEvent.getCurrentSupervisionNum()) ;
		
		JuvenileTraitResponseEvent juvenileTraitResponseEvent = new JuvenileTraitResponseEvent();
		juvenileTraitResponseEvent.setSupervisionNum(reqEvent.getCurrentSupervisionNum());
		juvenileTraitResponseEvent.setJuvenileNum(reqEvent.getJuvenileNum());
		juvenileTraitResponseEvent.setEntryDate(DateUtil.getCurrentDate());
		juvenileTraitResponseEvent.setStatus("CURRENT");
		juvenileTraitResponseEvent.setStatusId("CU");
		juvenileTraitResponseEvent.setTraitTypeId("ESC");
		juvenileTraitResponseEvent.setFacilityAdmitOID(facility.getOriginalAdmitOID());
		saveEvent.addRequest( juvenileTraitResponseEvent ) ;
		
		IDispatch dispatch1 = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch1.postEvent( saveEvent ) ;
		
		dispatch.postEvent(respEvent);
		
	}
	
	/**
	 * Send Escape Notification
	 * @param form
	 * @param detention
	 */
	private void sendEscapeNotification(SaveJuvenileFacilityEscapeEvent escapeEvt ,DocketEventResponseEvent detention){
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
			   	respEvt.setSubject("Escaped juvenile has a pending detention hearing.");
			    respEvt.setIdentity(securityResEvent.getJIMSLogonId());
			    respEvt.setJuvenileNum(detention.getJuvenileNumber());
			
			    String notifMessage = detention.getJuvenileName()+", Juvenile #"+detention.getJuvenileNumber()+", escaped on " +
			    		escapeEvt.getEscapeDate()+" from the custody of " + escapeEvt.getDetentionFacilityDesc()+". The juvenile has a pending detention hearing on "
							+escapeEvt.getNextHearingDate() + " in court #"+detention.getCourt();
			    	respEvt.setNotificationMessage(notifMessage);
			    	
			    	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
			    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
			    	notificationEvent.setNotificationTopic("JC.FACILITY.ESCAPE.NOTIFICATION");
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
