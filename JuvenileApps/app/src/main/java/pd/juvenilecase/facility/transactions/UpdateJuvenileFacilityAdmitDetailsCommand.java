
package pd.juvenilecase.facility.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.facility.GetJuvenileFacilitySplAttnReasonCommentsEvent;
import messaging.facility.UpdateJuvenileFacilityAdmitDetailsEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.facility.reply.JuvenileFacilitySplAttnReasonResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
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
import naming.JuvenileFacilityControllerServiceNames;
import pd.codetable.criminal.JuvenileAdmitReasons;
import pd.codetable.criminal.JuvenileDates;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.security.PDSecurityHelper;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

/**
 * UpdateJuvenileFacilityAdmitDetailsCommand
 * @author sthyagarajan
 *
 */
public class UpdateJuvenileFacilityAdmitDetailsCommand implements ICommand {

	@Override
	public void execute(IEvent event) throws Exception {
		UpdateJuvenileFacilityAdmitDetailsEvent reqEvent = (UpdateJuvenileFacilityAdmitDetailsEvent) event;
		
		JJSFacility facility = null;
		JJSHeader header = null;
		JJSFacility newFacility = new JJSFacility();
		boolean isFacilityUpdated = false;
		boolean isNewFacilityRecCreated = false;
		
		//get the calendar detentionRecord.
		List<DocketEventResponseEvent> calendarDetentions = JuvenileFacilityHelper.getCalendarDetentions(reqEvent.getJuvNum());
		//sorts in descending order by seq num.
		Collections.sort((List<DocketEventResponseEvent>)calendarDetentions,Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
				return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
			}
		}));
		
		//Service call to header.
		Iterator<JJSHeader> jjsHeaderItr = JJSHeader.findAll("juvenileNumber",reqEvent.getJuvNum());
		while(jjsHeaderItr.hasNext()){
			header = jjsHeaderItr.next();
		}
			
		//Service to call JJS_FACILITY: Get the record that needs to be modified.
		JuvenileDetentionFacilitiesResponseEvent detentionResp;
		Collection<JuvenileDetentionFacilitiesResponseEvent> detentionRespEvent = JuvenileFacilityHelper.getNonPostFacilityDetails(reqEvent.getJuvNum()); // get all facilities for the juvenile.
		//sort by facilitySequence number.
		Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)detentionRespEvent,Collections.reverseOrder(JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator));
				
		Iterator<JuvenileDetentionFacilitiesResponseEvent> jjsFacilityItr = detentionRespEvent.iterator();
		while(jjsFacilityItr.hasNext()){
				detentionResp = jjsFacilityItr.next();
				if(detentionResp.getFacilitySequenceNumber().equals(reqEvent.getLastSeqNum())){
					GetJuvenileFacilityDetailsEvent facilityDetailsEvt =
								(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
							facilityDetailsEvt.setJuvenileNum(reqEvent.getJuvNum());
							facilityDetailsEvt.setFacilitySequenceNum(detentionResp.getFacilitySequenceNumber());
						
					//Service call to header.
					Iterator<JJSFacility> jjsFacility = JJSFacility.findAll(facilityDetailsEvt);
					while(jjsFacility.hasNext()){
						facility = jjsFacility.next();
						break;
					}
						
					if(facility!=null){
							//Get Old and new records
							JuvenileAdmitReasonsResponseEvent newadmitReasonRespEvent = reqEvent.getAdmitReasonResp(); //new admitReason.
							JuvenileAdmitReasons reason = JuvenileAdmitReasons.find("code",facility.getAdmitReason());  //old admit reason . Previous rec.
							JuvenileAdmitReasonsResponseEvent admitReasonRespEvent = PDCriminalCodeTableHelper.getJuvenileAdmitReasonsResponseEvent(reason);
							
							if(reqEvent.isObservationStatusChanged()){
								//Sa Reason change 
								if(facility.getSaReasonCode()!=null && facility.getSpecialAttention()!=null){
									if(!facility.getSaReasonCode().equals(reqEvent.getSaReason()) || !facility.getSpecialAttention().equals(reqEvent.getSpecialAttentionCd())){
										facility.setSaReasonCode(reqEvent.getSaReason());
										facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
									}
									if(reqEvent.isSecureIndicatorChangeFlag() || (reqEvent.isReasonChangeFlag())){ //the only time when the new recs are created is during secure status change and admit reason chnage.
										newFacility.setSaReasonCode(reqEvent.getSaReason());
										newFacility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
										isNewFacilityRecCreated = true;
									}
								}else{ //added for bug 40650
									facility.setSaReasonCode(reqEvent.getSaReason());
									facility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
									if(reqEvent.isSecureIndicatorChangeFlag() || (reqEvent.isReasonChangeFlag())){
										newFacility.setSaReasonCode(reqEvent.getSaReason());
										newFacility.setSpecialAttention(reqEvent.getSpecialAttentionCd());
										isNewFacilityRecCreated = true;
									}
								}
							}
							
							
							if(reqEvent.getFacilityStatus()!=null){
								if(reqEvent.getFacilityStatus().equals("R")){
									facility.setReturnDate(reqEvent.getReturnDate());
									facility.setReturnTime(reqEvent.getReturnTime());
									isFacilityUpdated=true;
								}
							}
							
							String admissionComments = JuvenileFacilityHelper.getFacilityAdmissionComments(reqEvent.getJuvNum(),facility.getOID());// #51737
							
							if(!reqEvent.isSecureIndicatorChangeFlag() && !reqEvent.isReasonChangeFlag()){
									if(admissionComments!=null){
										if(admissionComments.contains("[")){
											admissionComments = (StringUtils.split(admissionComments, '[')[0]).trim();
										}
									}
									//comments and change explanation.
									if(admissionComments==null){
										if(reqEvent.getComments()!=null && !reqEvent.getComments().isEmpty()){
											//facility.setComments(reqEvent.getComments()); #51737
											JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
											comments.setComments(reqEvent.getComments());
											comments.setJuvenileNum(reqEvent.getJuvNum());
											comments.setDetentionId(facility.getOID());
											isFacilityUpdated=true;
										}
									}else{
										if(reqEvent.getComments()!=null && !reqEvent.getComments().isEmpty()){
											if(!admissionComments.equals(reqEvent.getComments())){
												//facility.setComments(reqEvent.getComments()); #51737
												JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
												comments.setComments(reqEvent.getComments());
												comments.setJuvenileNum(reqEvent.getJuvNum());
												comments.setDetentionId(facility.getOID());
												isFacilityUpdated=true;
											}
										}
									}
								
							
								// for escape attempts.
								if(facility.getEscapeAttempts()==null){
									if(reqEvent.getEscapeAttempts()!=null && !reqEvent.getEscapeAttempts().isEmpty()){
										facility.setEscapeAttempts(reqEvent.getEscapeAttempts());
										facility.setEscapeAttemptComments(reqEvent.getEscapeAttemptComments());
										isFacilityUpdated=true;
									}
								}else{
									if(facility.getEscapeAttempts().contains("[")){
										facility.setEscapeAttempts(StringUtils.split(facility.getEscapeAttempts(), '[')[0]);
									}
									if(reqEvent.getEscapeAttempts().contains("[")){
										reqEvent.setEscapeAttempts(StringUtils.split(reqEvent.getEscapeAttempts(), '[')[0]);
									}
									if(reqEvent.getEscapeAttempts()!=null){
										if(!facility.getEscapeAttempts().trim().equals(reqEvent.getEscapeAttempts().trim())){
											facility.setEscapeAttempts(reqEvent.getEscapeAttempts());
											facility.setEscapeAttemptComments(reqEvent.getEscapeAttemptComments());
											isFacilityUpdated=true;
										}
									}
								}
							}
							
							
							if(reqEvent.isSecureIndicatorChangeFlag()){ // secure indicator change.
								isNewFacilityRecCreated=true;
								facility.setSecureIndicatorChangeFlag(reqEvent.isSecureIndicatorChangeFlag());
								facility.setReleaseDate(new Date());  //update it with the current date and time.
								facility.setReleaseTime(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)); //update it with the current date and time.
								
								//other flags should be false;
								facility.setOtherChangeFlag(reqEvent.isOtherChangeFlag());
								facility.setLocationChangeFlag(reqEvent.isLocationChangeFlag());
								facility.setReasonChangeFlag(reqEvent.isReasonChangeFlag());
								isFacilityUpdated= true;
								
								//Call save to add a new admit record
								newFacility.setSecureStatus(reqEvent.getSecureStatus());
								newFacility.setSecureIndicatorChangeFlag(reqEvent.isSecureIndicatorChangeFlag());
								
								JJSFacility facilityRec = JuvenileFacilityHelper.getOriginalAdmitRec(reqEvent.getJuvNum(), reqEvent.getDetResp().getReferralNumber());
							    if(facilityRec!=null){
							    	reqEvent.setOriginalAdmitDate(facilityRec.getAdmitDate());
							    	reqEvent.setOriginalAdmitTime(facilityRec.getAdmitTime());
							    }
								setOtherAdmitDetails(reqEvent,newFacility,facility,header); //setOtherAdmitDetails
							}else if(reqEvent.isReasonChangeFlag()){ //admit reason change.
								isNewFacilityRecCreated=true;
								//facility.setAdmitReason(reqEvent.getAdmitReason()); do not update.Bug #51932
								facility.setReasonChangeFlag(reqEvent.isReasonChangeFlag());
								//regina review comments should be populated only on the previous record and not on the new one on secure status change & admit reason change.
								facility.setReleaseDate(new Date());  //update it with the current date and time.
								facility.setReleaseTime(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)); //update it with the current date and time.
								facility.setDetentionStatus(newadmitReasonRespEvent.getDetentionType()); // update the admit reason code on change of admit reason.
								//other flags should be false;
								facility.setOtherChangeFlag(reqEvent.isOtherChangeFlag());
								facility.setLocationChangeFlag(reqEvent.isLocationChangeFlag());
								facility.setSecureIndicatorChangeFlag(reqEvent.isSecureIndicatorChangeFlag());
							
							
								JJSFacility facilityRec = JuvenileFacilityHelper.getOriginalAdmitRec(reqEvent.getJuvNum(), reqEvent.getDetResp().getReferralNumber());
							    if(facilityRec!=null){
							    	reqEvent.setOriginalAdmitDate(facilityRec.getAdmitDate());
							    	reqEvent.setOriginalAdmitTime(facilityRec.getAdmitTime());
							    }
								
								//Rule1: GenerateDetentionHearingChain
								if(newadmitReasonRespEvent!=null && admitReasonRespEvent!=null){
									String newDetnHearingChain = newadmitReasonRespEvent.getGenDetHearingChain();
									String detnHearingChain = admitReasonRespEvent.getGenDetHearingChain();
									
									if(newDetnHearingChain!=null && !newDetnHearingChain.isEmpty() &&detnHearingChain!=null &&!detnHearingChain.isEmpty()){
										if(newDetnHearingChain.equals(detnHearingChain)){
											if(facility.getDetainedDate()==null && newadmitReasonRespEvent.getGenDetHearingChain().equals("Y"))
											{
												//generated Calendar detention
												generateCalendarDetention(reqEvent,header,newadmitReasonRespEvent,calendarDetentions,false);
											}
										}else if(admitReasonRespEvent.getGenDetHearingChain().equals("N")&&  newadmitReasonRespEvent.getGenDetHearingChain().equals("Y")){
											//Rule 3: generated Calendar detention
											generateCalendarDetention(reqEvent,header,newadmitReasonRespEvent,calendarDetentions,true);
											if(facility.getDetainedDate()!=null){
												
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
												    	respEvt.setSubject("Juvenile has a new admission reason which requires a detention hearing.");
												    	respEvt.setIdentity(securityResEvent.getJIMSLogonId());
												    	respEvt.setJuvenileNum(reqEvent.getJuvNum());
												
												    	String notifMessage = "An admission record for "+reqEvent.getJuvenileName()+", Juvenile #"+reqEvent.getJuvNum()+", was assigned a different admission reason." +
														"The new admission reason "+ newadmitReasonRespEvent.getDescription()+" requires a probable cause (PC) hearing. " +
																"Please determine if a subsequent detention hearing needs to be scheduled.";
												    	respEvt.setNotificationMessage(notifMessage);
												    	
												    	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
												    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
												    	notificationEvent.setNotificationTopic("JC.FACILITY.ADMIT.REASON.CHANGE.NOTIFICATION");
												    	notificationEvent.setSubject(respEvt.getSubject());
												    	notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
												    	notificationEvent.addContentBean(respEvt);
												    	CompositeResponse response = MessageUtil.postRequest(notificationEvent);
									    		        MessageUtil.processReturnException( response ) ;
												    	//end send notification	
												
												    	//Send email to the current user
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
												    		message.append("An admission record for ");
												    		message.append(reqEvent.getJuvenileName()); 
												    		message.append(",Juvenile #");
												    		message.append(reqEvent.getJuvNum());
												    		message.append(", was assigned a different admission reason.");
												    		message.append("The new admission reason ");
												    		message.append(newadmitReasonRespEvent.getDescription());
												    		message.append(" requires a probable cause (PC) hearing.");
												    		message.append(" Please determine if a subsequent detention hearing needs to be scheduled.");
												    		sendEmailEvent.setMessage(message.toString());
												    		sendEmailEvent.setSubject("Juvenile has a new admission reason which requires a detention hearing.");
												    		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
												    		dispatch.postEvent(sendEmailEvent);
												    	}
												    }
												}
											}
					}
					else
					{
					    //rule 2:
					    if (admitReasonRespEvent.getGenDetHearingChain().equals("Y") && newadmitReasonRespEvent.getGenDetHearingChain().equals("N"))
					    {
						if (calendarDetentions != null)
						{
						    Iterator<DocketEventResponseEvent> calendarDetention = calendarDetentions.iterator();
						    while (calendarDetention.hasNext())
						    {
							DocketEventResponseEvent docketEventResp = (DocketEventResponseEvent) calendarDetention.next();
							//if (docketEventResp.getEventDate().before(facility.getAdmitDate()) && docketEventResp.getReferralNum().equals(facility.getReferralNumber()))
							    //docketEventResp.getEventDate().before(facility.getAdmitDate()) failing as court date is after admit date for the recently created record
							if(docketEventResp.getDetentionId()!=null)
							{
        							if (docketEventResp.getDetentionId().equals(facility.getOID()))//changed as part of 153582
        							{
        							    //81390
        							    /*DeleteJJSDetentionEvent deleteCalendarDetentionEvt =(DeleteJJSDetentionEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.DELETEJJSDETENTION);
        							    deleteCalendarDetentionEvt.setJuvenileNumber(reqEvent.getJuvNum());
        							    deleteCalendarDetentionEvt.setChainNumber(detention.getChainNumber());
        							    deleteCalendarDetentionEvt.setReferralNumber(detention.getReferralNum());
        							    deleteCalendarDetentionEvt.setSeqNumber(detention.getSeqNum());
        							    try{
        							    	JJSCLDetention.findAll(deleteCalendarDetentionEvt);
        							    }catch(M204Exception e){
        							    	e.printStackTrace();
        							    }*/
        							    //if (docketEventResp.getChainNumber() != null && !docketEventResp.getChainNumber().isEmpty())
        							    if (docketEventResp.getDetentionId() != null && !docketEventResp.getDetentionId().isEmpty())//changed as part of 153582
        							    {
        								//Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll("chainNumber", docketEventResp.getChainNumber()); // by chainnumber
        								Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll("detentionId", docketEventResp.getDetentionId());//by detention_id changed as part of 153582
        								if (detentionItr != null)
        								{
        								    while (detentionItr.hasNext())
        								    {
        									try
        									{
        									    JJSCLDetention det = detentionItr.next();
        									    //delete all future Detention setting records 
        									    if (det.getCourtDate().after(DateUtil.getCurrentDate())||det.getCourtDate().equals(DateUtil.getCurrentDate()))
        									    {
        										det.delete();
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
        							}//end of if (docketEventResp.getDetentionId().equals(facility.getOID()))
							}
						    }
						}
					    } // rule 2: complete
											}
									}
								}
								isFacilityUpdated = true;
								
								// Call save to add a new admit record
								newFacility.setAdmitReason(reqEvent.getAdmitReason());
								newFacility.setReasonChangeFlag(reqEvent.isReasonChangeFlag());
								if(reqEvent.getAdmitReason().equals("DIV")){
									newFacility.setSecureStatus("N");
									newFacility.setSecureIndicatorChangeFlag(true);
								}
								setOtherAdmitDetails(reqEvent,newFacility,facility,header);
							}else if(reqEvent.isLocationChangeFlag()){ //location change.
								facility.setFloorNum(reqEvent.getFloorNum());
								facility.setRoomNum(reqEvent.getRoomNum());
								facility.setUnit(reqEvent.getUnit());
								facility.setMultipleOccupyUnit(reqEvent.getMultipleOccupyUnit());
								// add code for new fields 104028
								facility.setLockerNumber(reqEvent.getLockerNumber());
								facility.setReceiptNumber(reqEvent.getReceiptNumber());
								//
								facility.setChangeExplanation(reqEvent.getChangeExplanation());
								facility.setLocationChangeFlag(reqEvent.isLocationChangeFlag());
								
								//other flags should be false;
								facility.setOtherChangeFlag(reqEvent.isOtherChangeFlag());
								facility.setSecureIndicatorChangeFlag(reqEvent.isSecureIndicatorChangeFlag());
								facility.setReasonChangeFlag(reqEvent.isReasonChangeFlag());
								
								isFacilityUpdated = true;
								
							}else if(reqEvent.isOtherChangeFlag()){ //other change flag.
								facility.setAdmittedByJPO(reqEvent.getAdmittedBy());
								facility.setOtherChangeFlag(reqEvent.isOtherChangeFlag());
							
								//other flags should be false;
								facility.setLocationChangeFlag(reqEvent.isLocationChangeFlag());
								facility.setSecureIndicatorChangeFlag(reqEvent.isSecureIndicatorChangeFlag());
								facility.setReasonChangeFlag(reqEvent.isReasonChangeFlag());
								facility.setChangeExplanation(reqEvent.getChangeExplanation());
								isFacilityUpdated = true;
							}
							//Release Update.
							if(reqEvent.getFacilityStatus()!=null && (reqEvent.getFacilityStatus().equals("N")||reqEvent.getFacilityStatus().equals("T"))){
								if(!facility.getReleaseDate().equals(reqEvent.getReleaseDate())|| !JuvenileFacilityHelper.getHoursMinsFromTime(facility.getReleaseTime()).equals(reqEvent.getReleaseTime())){
									facility.setReleaseDate(reqEvent.getReleaseDate());
									facility.setReleaseTime(reqEvent.getReleaseTime());
								}
								if(reqEvent.getReleaseReason()!=null && !reqEvent.getReleaseReason().isEmpty()){
									if(!facility.getReleaseBy().equals(reqEvent.getReleaseBy())||!facility.getReleaseTo().equals(reqEvent.getReleaseTo())||
											!facility.getReleaseAuthorizedBy().equals(reqEvent.getReleaseAuthorizedBy())){
										facility.setReleaseBy(reqEvent.getReleaseBy());
										facility.setReleaseTo(reqEvent.getReleaseTo());
										facility.setReleaseAuthorizedBy(reqEvent.getReleaseAuthorizedBy());
									}
								}
								isFacilityUpdated = true;
							}
							
							//Escape Update.
							//Update in header - relocation Date & relocation Time.
							if(reqEvent.getFacilityStatus()!=null && reqEvent.getFacilityStatus().equals("E")){
								if(header.getLastSequenceNumber().equals(reqEvent.getLastSeqNum())){
									if(!header.getRelocationDate().equals(reqEvent.getRelocationDate())||
											!JuvenileFacilityHelper.getHoursMinsFromTime(header.getRelocationTime()).equals(reqEvent.getRelocationTime())|| !facility.getEscapeCode().equals(reqEvent.getEscapeCode()))
										header.setRelocationDate(reqEvent.getRelocationDate()); 
										header.setRelocationTime(reqEvent.getRelocationTime()); 
										facility.setEscapeCode(reqEvent.getEscapeCode());
										isFacilityUpdated = true;
									}
								}	
							}
						}
					break;
				}
		
			if(isFacilityUpdated){
				if(!reqEvent.isReasonChangeFlag()){
					if(facility.getDetentionStatus()!=null && !facility.getDetentionStatus().equals(reqEvent.getFacilityStatus())){
						facility.setDetentionStatus(reqEvent.getFacilityStatus()); //reset is done in the admit reason change.
					}
				}
				
				IHome home= new Home();
				home.bind(facility);
				if(isNewFacilityRecCreated) {
					if(newFacility.getOriginalAdmitOID()==null){
						if(facility.getOriginalAdmitDate()==null)
							newFacility.setOriginalAdmitOID(facility.getOID());
						else
						{
							if(facility.getOriginalAdmitOID()!=null)
								newFacility.setOriginalAdmitOID(facility.getOriginalAdmitOID());
							else
							{
								JJSFacility facilityRec = JuvenileFacilityHelper.getOriginalAdmitRec(reqEvent.getJuvNum(), reqEvent.getDetResp().getReferralNumber());
							    if(facilityRec!=null){
							    	newFacility.setOriginalAdmitOID(facilityRec.getOID());
							    }
							}
								
						}
						
						String previousAdmitSeq = ( facility.getOriginaladmitSEQNUM() != null
										&& facility.getOriginaladmitSEQNUM().length() > 0 ) 
											? facility.getOriginaladmitSEQNUM() : "10";
						int admitSeq = Integer.parseInt( previousAdmitSeq ) + 10;
						newFacility.setOriginaladmitSEQNUM( String.valueOf( admitSeq ) );
						 if ( facility.getPostAdmitOID() != null
							    && facility.getPostAdmitOID().length() > 0 ){
							newFacility.setPostAdmitOID( facility.getPostAdmitOID() );
						    }
					} else {
					    	Iterator<JJSFacility> modifiedFacilitiesIter = JJSFacility.findAll( "originalAdmitOID", facility.getOriginalAdmitOID()  );
						List <JJSFacility> modifiedFacs = new ArrayList<>();
						
						while ( modifiedFacilitiesIter.hasNext() ){
						    JJSFacility modifiedFac = modifiedFacilitiesIter.next();
						    modifiedFacs.add( modifiedFac );
						}
						
						if ( modifiedFacs.size() > 0 ) {
						    Collections.sort(modifiedFacs, new Comparator<JJSFacility>(){
							    @Override
							    public int compare(JJSFacility h1, JJSFacility h2 ){
								return h2.getOID().compareTo(h1.getOID());
							    }
							});
						    
						   
						    String previousAdmitSeq = ( modifiedFacs.get(0).getOriginaladmitSEQNUM() != null 
							    				&& modifiedFacs.get(0).getOriginaladmitSEQNUM().length() > 0 ) 
							    					? modifiedFacs.get(0).getOriginaladmitSEQNUM() : "0";
						    int admitSeq = Integer.parseInt( previousAdmitSeq ) + 10;
						    newFacility.setOriginaladmitSEQNUM( String.valueOf( admitSeq ) );
						    if ( modifiedFacs.get(0).getPostAdmitOID() != null
							    && modifiedFacs.get(0).getPostAdmitOID().length() > 0 ){
							newFacility.setPostAdmitOID( modifiedFacs.get(0).getPostAdmitOID() );
						    }
						    
						}
					}
					home.bind(newFacility);
					// add facility comments for new recs.
					if(reqEvent.getComments()!=null && !reqEvent.getComments().isEmpty()){
						JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
						comments.setComments(reqEvent.getComments());
						comments.setJuvenileNum(reqEvent.getJuvNum());
						comments.setDetentionId(newFacility.getOID());
						//home= new Home();
						//home.bind(comments); //insert the comments.
					}
					if(reqEvent.getSaReasonOtherComments()!=null && !reqEvent.getSaReasonOtherComments().isEmpty()){
						JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
						splAttentionComments.setComments(reqEvent.getSaReasonOtherComments());
						splAttentionComments.setJuvenileNum(reqEvent.getJuvNum());
						splAttentionComments.setDetentionId(newFacility.getOID());
						//home= new Home();
						//home.bind(splAttentionComments); //insert the comments.
					}	
					/////////add an else here to copy the old comments to the new record
					else
					{
						List<JuvenileFacilitySplAttnReasonResponseEvent> respEvtList = new ArrayList<JuvenileFacilitySplAttnReasonResponseEvent>();
						GetJuvenileFacilitySplAttnReasonCommentsEvent commEvent = (GetJuvenileFacilitySplAttnReasonCommentsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYSPLATTNREASONCOMMENTS);
						commEvent.setJuvenileNumber(reqEvent.getJuvNum());
						commEvent.setDetentionId(facility.getOID());					
						Iterator<JJSSplAttnReasonComments> splAttentiontr = JJSSplAttnReasonComments.findAll(commEvent);
						List commList = IteratorUtils.toList(splAttentiontr);
						if (splAttentiontr.hasNext())
						{
							Collections.sort(commList);
							JJSSplAttnReasonComments copyComments = (JJSSplAttnReasonComments)commList.get(0);
							copyComments.setDetentionId(newFacility.getOID());
							//home= new Home();
							//home.bind(copyComments); //insert the comments.
						}
					}
				}
				//US 39468 - update the detention status in JJS
				JJSJuvenile juvenile = JJSJuvenile.find( reqEvent.getJuvNum() );
				if(juvenile!=null && juvenile.getDetentionStatusId()!=null && !juvenile.getDetentionStatusId().equals(header.getFacilityStatus()))
				{
					juvenile.setDetentionStatusId(header.getFacilityStatus());			
				}	
				//reset the flag.
				isFacilityUpdated=false;
			}
			// do not insert if not changed.
			if(reqEvent.isObservationStatusChanged()){
				//persist Spl Attention Comments //task #34820 bug fix:40650		
				String saReasonOtherComments = reqEvent.getSaReasonOtherComments();
				String juvenileNum = reqEvent.getJuvNum();
				String detentionId = facility.getOID();
				
				//add the same for new record.
				if(reqEvent.isSecureIndicatorChangeFlag() || reqEvent.isReasonChangeFlag()){
					if(saReasonOtherComments!=null && !saReasonOtherComments.isEmpty() && juvenileNum!=null && newFacility.getOID()!=null){
						JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
						splAttentionComments.setComments(saReasonOtherComments);
						splAttentionComments.setJuvenileNum(juvenileNum);
						splAttentionComments.setDetentionId(newFacility.getOID());
						//IHome home= new Home();
						//home.bind(splAttentionComments); //insert the comments.
					}
				}else{
					if(saReasonOtherComments!=null && !saReasonOtherComments.isEmpty() && juvenileNum!=null && detentionId!=null){
						JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
						splAttentionComments.setComments(saReasonOtherComments);
						splAttentionComments.setJuvenileNum(juvenileNum);
						splAttentionComments.setDetentionId(detentionId);
						//IHome home= new Home();
						//home.bind(splAttentionComments); //insert the comments.
					}	
				}
			}
	}
	
	/**
	 * set other facility data from the existing record.
	 * @param fac
	 * @param saveAdmit - existing facility rec
	 */
	private void setOtherAdmitDetails(UpdateJuvenileFacilityAdmitDetailsEvent reqEvent,JJSFacility fac, JJSFacility saveAdmit, JJSHeader header)
	{
		fac.setAdmittedByJPO(saveAdmit.getAdmittedByJPO());
		fac.setAdmitDate(new Date());
		fac.setAdmitTime(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1));
		fac.setAdmitAuthority(saveAdmit.getAdmitAuthority());
		if(!reqEvent.isReasonChangeFlag()){ //new value is set.
			fac.setAdmitReason(saveAdmit.getAdmitReason());
		}
		
		fac.setBookingSupervisionNum(saveAdmit.getBookingSupervisionNum());		
		fac.setChangeExplanation(reqEvent.getChangeExplanation());
		//fac.setComments(saveAdmit.getComments()); #51737
		fac.setCurrentOffense(saveAdmit.getCurrentOffense());	
		fac.setCurrentReferral(saveAdmit.getCurrentReferral());
		fac.setCurrentSupervisionNum(saveAdmit.getCurrentSupervisionNum());
		
		fac.setDetainedByInd(saveAdmit.getDetainedByInd());
		fac.setDetainedDate(saveAdmit.getDetainedDate());
		fac.setDetainedFacility(saveAdmit.getDetainedFacility());
		fac.setDetentionStatus(saveAdmit.getDetentionStatus());
		
		fac.setEscapeCode(saveAdmit.getEscapeCode());
		fac.setEscapeAttempts(reqEvent.getEscapeAttempts());
		fac.setEscapeAttemptComments(reqEvent.getEscapeAttemptComments());
		
		fac.setOutcome(saveAdmit.getOutcome());
		
		if(saveAdmit.getOriginalAdmitDate()!=null &&!saveAdmit.getOriginalAdmitDate().equals("") ){
			fac.setOriginalAdmitDate(saveAdmit.getOriginalAdmitDate());
			fac.setOriginalAdmitTime(saveAdmit.getOriginalAdmitTime());
		}else{
			fac.setOriginalAdmitDate(reqEvent.getOriginalAdmitDate());
			fac.setOriginalAdmitTime(reqEvent.getOriginalAdmitTime());
		}
		fac.setOriginalAdmitOID(saveAdmit.getOriginalAdmitOID()); // added for u.s #44549
		fac.setTempReleaseOtherComments(saveAdmit.getTempReleaseOtherComments());
		fac.setTempReleaseReasonCd(saveAdmit.getTempReleaseReasonCd());
		
		fac.setTjpcseqnum(saveAdmit.getTjpcseqnum());
		fac.setTransferToFacility(saveAdmit.getTransferToFacility());
	
		
	   if(!reqEvent.isSecureIndicatorChangeFlag()&& !reqEvent.getAdmitReason().equals("DIV")){ //new value is set.
			fac.setSecureStatus(saveAdmit.getSecureStatus());
		}
		fac.setReferralNumber(saveAdmit.getReferralNumber());		
		fac.setReceiptNumber(saveAdmit.getReceiptNumber());
		
		fac.setLockerNumber(saveAdmit.getLockerNumber());		
		fac.setFloorNum(saveAdmit.getFloorNum());
		fac.setUnit(saveAdmit.getUnit());
		fac.setRoomNum(saveAdmit.getRoomNum());
		fac.setMultipleOccupyUnit(saveAdmit.getMultipleOccupyUnit());
		
		fac.setFacilitySequenceNumber(String.valueOf(Integer.parseInt(saveAdmit.getFacilitySequenceNumber())+20)); //increase Facility Sequence number to 20.
		fac.setJuvenileNumber(saveAdmit.getJuvenileNumber());
		
		fac.setReleaseBy(saveAdmit.getReleaseBy());
		fac.setReleaseTo(saveAdmit.getReleaseTo());
		fac.setReleaseReason(saveAdmit.getReleaseReason());
		fac.setReleaseAuthorizedBy(saveAdmit.getReleaseAuthorizedBy());
		
		fac.setReturnDate(saveAdmit.getReturnDate());
		fac.setReturnTime(saveAdmit.getReturnTime());
		fac.setReturnStatus(saveAdmit.getReturnStatus());
		
		fac.setOtherChangeFlag(saveAdmit.isOtherChangeFlag());
		fac.setLocationChangeFlag(saveAdmit.isLocationChangeFlag());
		fac.setTjjdFacilityId(reqEvent.getTjjdFacilityId());
		fac.setTjjdFundingSrc(reqEvent.getTjjdFundingSrc());
		fac.setAvgCostPerDay(reqEvent.getAvgCostPerDay());
		
		fac.setRecType("DETENTION");
		
		//bug #56485
		fac.setSaReasonCode(saveAdmit.getSaReasonCode());
		fac.setSpecialAttention(saveAdmit.getSpecialAttention());
		String saReasonOtherComments = saveAdmit.getSaReasonOtherComments();
		
	
		//update header
		header.setFacilityCode(saveAdmit.getDetainedFacility()); //header facility
		header.setJuvenileNumber(saveAdmit.getJuvenileNumber());
		header.setReferralNumber(saveAdmit.getReferralNumber());
		header.setBookingSupervisionNum(saveAdmit.getBookingSupervisionNum());  
		header.setLastSequenceNumber(String.valueOf(Integer.parseInt(saveAdmit.getFacilitySequenceNumber())+20));
		header.setLcDate(DateUtil.getCurrentDate());
		header.setLcTime(Calendar.getInstance().getTime());
		header.setLcUser(PDSecurityHelper.getLogonId());
		header.setHeaderFacility("DET");
		header.setFacilityStatus(reqEvent.getAdmitReasonResp().getDetentionType());			
	}
	
	/**
	 * generateCalendarDetention
	 * @param jjsHeaderItr
	 * @param reqEvent
	 * @param header
	 * @param newadmitReasonRespEvent
	 * @param calendarDetentions
	 */
	private void generateCalendarDetention(
			UpdateJuvenileFacilityAdmitDetailsEvent reqEvent,JJSHeader header, JuvenileAdmitReasonsResponseEvent newadmitReasonRespEvent,List<DocketEventResponseEvent> calendarDetentions,boolean isChainNumIncremented){

		JJSCLDetention court = new JJSCLDetention();
		court.setRecType("DETENTION");
		court.setCourtTime("0900");
		if(header.getLastSequenceNumber().equals(reqEvent.getLastSeqNum())){
			court.setReferralNumber(header.getReferralNumber());
		}
		court.setJuvenileNumber(reqEvent.getJuvNum());
		//task 148846
		court.setPetitionNumber(reqEvent.getPetitionNum());
		//
		court.setLcDate(DateUtil.getCurrentDate());
		
		court.setLcTime(Calendar.getInstance().getTime());
		court.setLcUser(PDSecurityHelper.getLogonId());
		if(!"HLD".equalsIgnoreCase( reqEvent.getAdmitReason())) {
		    
		    court.setHearingType("PC");
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(reqEvent.getOriginalAdmitDate());
		c.add(Calendar.DATE, Integer.parseInt(newadmitReasonRespEvent.getProbCauseHearingDays()));
		
		//courtId
		//check if courtDate falls on saturday or sunday
		if(c.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY){
			court.setCourtId("300");
		}else{
			court.setCourtId(reqEvent.getFacRefereeCourt());
		}
		//adding the detentionid on creating court record incrementing to 1 as the next detention record creates after court record. task 154136	
		int nextdetId = Integer.parseInt(reqEvent.getDetResp().getDetentionId()) + 1;
		court.setDetentionId("" +nextdetId);
		//
		//courtDate
		String cDate = DateUtil.dateToString(c.getTime(), "yyyyMMdd");
		Date courtDate=DateUtil.stringToDate(cDate, "yyyyMMdd");
		if(court.getCourtId().equals("300"))
		{				
			court.setCourtDate(courtDate);				
		}
		else
		{
			String cDate1 = DateUtil.dateToString(c.getTime(), "MMddyyyy");				
			//check if the court date falls on a holiday
			Iterator<JuvenileDates> holiday = JuvenileDates.findAll("code", cDate1);
			if(holiday.hasNext())
			{
				JuvenileDates juvCourtDate = (JuvenileDates)holiday.next();				
					court.setCourtDate(DateUtil.stringToDate(juvCourtDate.getResetDate(), "yyyyMMdd"));					
			}
			else{
				court.setCourtDate(courtDate);
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(court.getCourtDate());
		cal.set(Calendar.HOUR_OF_DAY, 9);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);

		header.setNextHearingDate(cal.getTime());
		IHome home2= new Home();
		//set the chain number
		if(isChainNumIncremented){
			Iterator chainNumIter = JJSChainNumControl.findAll();
			if(chainNumIter.hasNext())
			{
				JJSChainNumControl chainNumControl = (JJSChainNumControl) chainNumIter.next();
				String nextChainNum = chainNumControl.getNextChainNum();
				if(nextChainNum!=null && !nextChainNum.equals(" "))
				{
					int num = Integer.parseInt(nextChainNum);
					court.setChainNumber(""+(num+1));
					chainNumControl.setNextChainNum(""+(num+1));
					home2.bind(chainNumControl);
				}
			}		
		}
		
			
		//set sequence number
		if(calendarDetentions!=null){
			Iterator<DocketEventResponseEvent> calDetenItr = calendarDetentions.iterator();
			if(calDetenItr.hasNext()){
				DocketEventResponseEvent detention = (DocketEventResponseEvent) calDetenItr.next();					
					if(!isChainNumIncremented){
					  //Increment the sequence no by 10.
						int seqNum = Integer.parseInt(detention.getSeqNum())+10;//use this only if chain not incremented bug fix for PC detention record created other than 10 bug 154845 
						court.setSeqNumber(String.valueOf(seqNum));
						court.setChainNumber(detention.getChainNumber()); //set chain number
					}
					else
					    court.setSeqNumber("10");
			}else{ //if no calendar detention record
				Iterator<JJSChainNumControl> chainNumIter = JJSChainNumControl.findAll();
				if(chainNumIter.hasNext())
				{
					JJSChainNumControl chainNumControl = chainNumIter.next();
					String nextChainNum = chainNumControl.getNextChainNum();
					if(nextChainNum!=null && !nextChainNum.equals(" "))
					{
						int num = Integer.parseInt(nextChainNum);
						court.setChainNumber(""+(num+1));
						chainNumControl.setNextChainNum(""+(num+1));
						court.setSeqNumber("10");
						home2.bind(chainNumControl);
					}
				}		
			}
		}
			home2.bind(court);
	}
}