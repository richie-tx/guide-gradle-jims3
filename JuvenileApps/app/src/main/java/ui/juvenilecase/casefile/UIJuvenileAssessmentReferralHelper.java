package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.casefile.CreateActivityEvent;
import messaging.casefile.SaveAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import pd.contact.officer.PDOfficerProfileHelper;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.AssessmentReferralForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

public class UIJuvenileAssessmentReferralHelper {
	
	/**
	 * Send notification for the gang unit officer
	 * @param event
	 * @param gangAssessmentRefForm
	 */
	public static void sendGangAssessmentNotificationForGangUnitOfficer(AssessmentReferralResponseEvent event,AssessmentReferralForm gangAssessmentRefForm){
		
		Collection<UserGroupResponseEvent> userGrpResEvent = SecurityUIHelper.getUserGroupByName("Gang Assessment Group");
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
		    	OfficerProfileResponseEvent	securityResEvent =	securityRespIter.next();
		    	
		    	//send notification for the Gang unit officers group.
				event.setSubject("Gang Referral has been submitted for "+gangAssessmentRefForm.getJuvenileName().getFormattedName()+ 
						", (" +gangAssessmentRefForm.getJuvenileNum()+") on "+gangAssessmentRefForm.getReferralDate());
			
				String mesg = "On "+gangAssessmentRefForm.getReferralDate()+" a Gang Referral was submitted by officer "+gangAssessmentRefForm.getPersonMakingRef()+" for "+gangAssessmentRefForm.getJuvenileName().getFormattedName()+ 
				", ("+gangAssessmentRefForm.getJuvenileNum()+"). Please be advised that a Gang Risk Assessment is needed. After completion, the risk assessment conclusion needs to be documented on " +
				"the Gang Referral by accessing the Assessment Referral tab."; 
		    	event.setIdentity(securityResEvent.getUserId());
		    	event.setNotificationMessage(mesg);
		    	event.setJuvenileNum(gangAssessmentRefForm.getJuvenileNum());
		    	
				CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		    	notificationEvent.setNotificationTopic("JC.GANG.ASSESSMENT.SUBMITTED");
		    	notificationEvent.setSubject(event.getSubject());
		    	notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, (IAddressable)event);
		    	notificationEvent.addContentBean(event);
		    	CompositeResponse response = MessageUtil.postRequest(notificationEvent);
		    	MessageUtil.processReturnException( response ) ;
		    	// reset event object
		    	event =  new AssessmentReferralResponseEvent();
		    	
		    	StringBuffer message = new StringBuffer(100);
				SendEmailEvent sendEmailEvent = new SendEmailEvent();
				
		    	//Send email to the gang unit officers group
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
			    	message.append("On ");
			    	message.append(gangAssessmentRefForm.getReferralDate());
			    	message.append(" a Gang Referral was submitted by officer ");
			    	message.append(gangAssessmentRefForm.getPersonMakingRef());
			    	message.append(" for ");
			    	message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
			    	message.append(", (");
			    	message.append(gangAssessmentRefForm.getJuvenileNum());
			    	message.append("). Please be advised that a Gang Risk Assessment is needed. After completion, the risk assessment conclusion needs to be documented on the Gang Referral by accessing the Assessment Referral tab.");
			    	sendEmailEvent.setMessage(message.toString());
		        
			    	message = new StringBuffer(100);	    	
			    	message.append("Gang Referral submitted for ");
			    	message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
			    	message.append(", (");
			    	message.append(gangAssessmentRefForm.getJuvenileNum());
			    	message.append(")");
			    	message.append(" on ");
			    	message.append(gangAssessmentRefForm.getReferralDate());
			    	sendEmailEvent.setSubject(message.toString());
			    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			    	dispatch.postEvent(sendEmailEvent);
		    	}
		    }	
	    }
	}
	
	/**
	 * Send Notification for the probation officer.
	 * @param event
	 * @param gangAssessmentRefForm
	 */
    public static void sendGangAssessmentNotificationForProbationOfficer(AssessmentReferralResponseEvent event, AssessmentReferralForm gangAssessmentRefForm)
    {
	Collection<UserGroupResponseEvent> userGrpResEvent = SecurityUIHelper.getUserGroupByName("Gang Assessment Group");
	Collection<OfficerProfileResponseEvent> securityRespEvent = null;
	if (userGrpResEvent != null)
	{
	    Iterator<UserGroupResponseEvent> userGrpResIter = userGrpResEvent.iterator();
	    while (userGrpResIter.hasNext())
	    {
		UserGroupResponseEvent response = userGrpResIter.next();
		securityRespEvent = PDOfficerProfileHelper.getOfficerProfilesInUserGroup(response.getUserGroupId());
	    }
	}
	String officerEmailId = "";
	List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("logonId", UIUtil.getCurrentUserID());
	Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
	if (events.hasNext())
	{
	    OfficerProfileResponseEvent resp = events.next();
	    officerEmailId = resp.getEmail();
	}
	if (securityRespEvent != null)
	{
	    Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();

	    while (securityRespIter.hasNext())
	    {
		OfficerProfileResponseEvent securityResEvent = securityRespIter.next();

		//start send notification
		event.setSubject("Gang Offender Risk Assessment was Completed for " + gangAssessmentRefForm.getJuvenileName().getFormattedName() + ", (" + gangAssessmentRefForm.getJuvenileNum() + ") on " + DateUtil.getCurrentDateString(UIConstants.DATE_FMT_1));

		String mesg = "Gang Offender Risk Assessment was completed for " + gangAssessmentRefForm.getJuvenileName().getFormattedName() + ", (" + gangAssessmentRefForm.getJuvenileNum() + ") on " + DateUtil.getCurrentDateString(UIConstants.DATE_FMT_1) + ". Status: " + gangAssessmentRefForm.getJuvenileName().getFormattedName() + " has been " + gangAssessmentRefForm.getAcceptedStatus() + " to the gang unit. Please view referral for additional conclusion information.";

		//send notification to the probation officer.
		event.setIdentity(gangAssessmentRefForm.getCreateUserId());
		event.setNotificationMessage(mesg);

		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);

		notificationEvent.setNotificationTopic("JC.GANG.ASSESSMENT.COMPLETED");
		notificationEvent.setSubject(event.getSubject());
		notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, (IAddressable) event);
		notificationEvent.addContentBean(event);
		dispatchNotification.postEvent(notificationEvent);

		StringBuffer message = new StringBuffer(100);
		SendEmailEvent sendEmailEvent = new SendEmailEvent();

		//send email to the group

		if ((officerEmailId != null && !officerEmailId.equals("")) && (securityResEvent.getEmail() != null && !securityResEvent.getEmail().equals("")))
		{
		    sendEmailEvent.setFromAddress(officerEmailId);
		    UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, securityResEvent.getEmail());

		    message.append("Gang Offender Risk Assessment was completed for ");
		    message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
		    message.append(", ");
		    message.append(gangAssessmentRefForm.getJuvenileNum());
		    message.append(" on ");
		    message.append(DateUtil.getCurrentDateString(UIConstants.DATE_FMT_1));
		    message.append(". Status: ");
		    message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
		    message.append(" has been ");
		    message.append(gangAssessmentRefForm.getAcceptedStatus());
		    message.append(" to the gang unit. Please view the referral for additional conclusion information.");
		    sendEmailEvent.setMessage(message.toString());

		    message = new StringBuffer(100);
		    message.append("Gang Offender Risk Assessment was completed for ");
		    message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
		    message.append(", (");
		    message.append(gangAssessmentRefForm.getJuvenileNum());
		    message.append(") on ");
		    message.append(DateUtil.getCurrentDateString(UIConstants.DATE_FMT_1));
		    sendEmailEvent.setSubject(message.toString());

		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    dispatch.postEvent(sendEmailEvent);
		}
	    }
	    ///email to original officer who requested. task 164367
	    StringBuffer message = new StringBuffer(100);
	    SendEmailEvent sendEmailEvent = new SendEmailEvent();
	    String userRequested = gangAssessmentRefForm.getCreateUserId();
	    String userRequestedEmailId = "";
	    if (userRequested != null && !userRequested.isEmpty())
	    {
		List<OfficerProfileResponseEvent> officerprof = JuvenileFacilityHelper.getOfficerProfiles("logonId", userRequested);
		Iterator<OfficerProfileResponseEvent> evts = officerprof.iterator();
		if (evts.hasNext())
		{
		    OfficerProfileResponseEvent resp = evts.next();
		    userRequestedEmailId = resp.getEmail();
		}
		sendEmailEvent.setFromAddress(officerEmailId);
		sendEmailEvent.addToAddress(userRequestedEmailId);
		message.append("Gang Offender Risk Assessment was completed for ");
		message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
		message.append(", ");
		message.append(gangAssessmentRefForm.getJuvenileNum());
		message.append(" on ");
		message.append(DateUtil.getCurrentDateString(UIConstants.DATE_FMT_1));
		message.append(". Status: ");
		message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
		message.append(" has been ");
		message.append(gangAssessmentRefForm.getAcceptedStatus());
		message.append(" to the gang unit. Please view the referral for additional conclusion information.");
		sendEmailEvent.setMessage(message.toString());
		message = new StringBuffer(100);
		message.append("Gang Offender Risk Assessment was completed for ");
		message.append(gangAssessmentRefForm.getJuvenileName().getFormattedName());
		message.append(", (");
		message.append(gangAssessmentRefForm.getJuvenileNum());
		message.append(") on ");
		message.append(DateUtil.getCurrentDateString(UIConstants.DATE_FMT_1));
		sendEmailEvent.setSubject(message.toString());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);
	    }

	}
    }
	
	/**
	 * Create activity for the Assessment creation.
	 * @param gangAssessmentForm
	 * @param activityCode
	 * @param comments
	 */
	public static void createActivityForAssessmentCreation(AssessmentReferralForm gangAssessmentForm, String activityCode,String comments) {
		SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
		search.setSearchType("JNUM");
		search.setJuvenileNum(gangAssessmentForm.getJuvenileNum());
		CompositeResponse responses = MessageUtil.postRequest(search);
		String caseFileId = null;
		List casefiles = MessageUtil.compositeToList(responses,JuvenileCasefileSearchResponseEvent.class);
		if (casefiles.size() > 0) {
			Collections.sort(casefiles);
			caseFileId = ((JuvenileCasefileSearchResponseEvent) casefiles.get(0)).getSupervisionNum();
		}
		CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);
		if(caseFileId!=null){
			
			reqEvent.setSupervisionNumber(caseFileId);
			reqEvent.setFromAction(true);
			reqEvent.setActivityDate(DateUtil.getCurrentDate());
			reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_CATEGORY_ASSESSMENT_REFERRAL);
			reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_GANG_ASSESSMENT_REFERRAL);
			reqEvent.setActivityCodeId(activityCode);
			reqEvent.setComments(comments);
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    dispatch.postEvent(reqEvent);
		
		    CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		    MessageUtil.processReturnException( compositeResponse );
		}
	}

	/**
	 * Create gang assessment referral
	 * @param gangAssesmentForm
	 * @return SaveAssessmentReferralEvent
	 */
	public static SaveAssessmentReferralEvent createGangAssessmentReferral(AssessmentReferralForm gangAssesmentForm) {

		SaveAssessmentReferralEvent reqEvent = new SaveAssessmentReferralEvent();

		// Create assessment
		reqEvent.setReferralDate(DateUtil.stringToDate(gangAssesmentForm.getReferralDate(), UIConstants.DATE_FMT_1));
		reqEvent.setPlacementFacilityId(gangAssesmentForm.getPlacementFacilityId());
		reqEvent.setGangNameId(gangAssesmentForm.getGangNameId());
		reqEvent.setCliqueSetId(gangAssesmentForm.getCliqueSetId());
		reqEvent.setRecommendationsId(gangAssesmentForm.getRecommendationsId());
		reqEvent.setAssessmentTypeId("GNG");
		reqEvent.setOtherGangName(gangAssesmentForm.getOtherGangName());
		reqEvent.setOtherCliqueSet(gangAssesmentForm.getOtherCliqueSet());
		reqEvent.setDescHybrid(gangAssesmentForm.getDescHybrid());
		reqEvent.setLvlOfGangInvolvementId(gangAssesmentForm.getLvlOfGangInvolvementId());
		reqEvent.setOtherReasonForReferral(gangAssesmentForm.getOtherReasonForReferralTxt());
		reqEvent.setComments(gangAssesmentForm.getComments());
		reqEvent.setPersonMakingReferral(gangAssesmentForm.getPersonMakingRef());
		if (gangAssesmentForm.getOtherReasonForReferral().equalsIgnoreCase("OTHER")) {
			reqEvent.setOtherReasonForReferral(gangAssesmentForm.getOtherReasonForReferralTxt());
			if (!gangAssesmentForm.getReasonForReferralId().equalsIgnoreCase("")) {
				reqEvent.setReasonForReferralId(gangAssesmentForm.getReasonForReferralId() + "," + "OTHR");
			} else {
				reqEvent.setReasonForReferralId("OTHR");
			}
		} else {
			reqEvent.setReasonForReferralId(gangAssesmentForm.getReasonForReferralId());
		}
		reqEvent.setJuvenileNum(gangAssesmentForm.getJuvenileNum());
		
		//parent notified
		if(gangAssesmentForm.getParentNotified().equalsIgnoreCase("Yes"))
		    reqEvent.setParentNotified("1");
		else if(gangAssesmentForm.getParentNotified().equalsIgnoreCase("No"))
		    reqEvent.setParentNotified("0");
		else
		    reqEvent.setParentNotified(null);
				
		//parent notified gang assessment request
		if(gangAssesmentForm.getParentNotifiedGangAssReq().equalsIgnoreCase("Yes"))
		    reqEvent.setParentNotifiedGangAssReq("1");
		else if(gangAssesmentForm.getParentNotifiedGangAssReq().equalsIgnoreCase("No"))
		    reqEvent.setParentNotifiedGangAssReq("0");
		else
		    reqEvent.setParentNotifiedGangAssReq(null);
		
		reqEvent.setStatus("PENDING");

		return reqEvent;
	}

	/**
	 * updateGangAssessmentReferral
	 * 
	 * @param caseFileAssesform
	 * @return SaveAssessmentReferralEvent
	 */
	public static SaveAssessmentReferralEvent updateGangAssessmentReferral(AssessmentReferralForm gangAssessmentForm) {
		
		SaveAssessmentReferralEvent reqEvent = new SaveAssessmentReferralEvent();
		reqEvent.setAssessmentIDNumber(gangAssessmentForm.getAssessmentIDNumber());
		reqEvent.setAcceptedStatus(gangAssessmentForm.getAcceptedStatus());
		reqEvent.setRejectionReason(gangAssessmentForm.getRejectionReason());
		reqEvent.setRecommendationsId(gangAssessmentForm.getRecommendationsId());
		reqEvent.setConclusion(gangAssessmentForm.getConclusion());
		if(gangAssessmentForm.getParentNotified()!=null)
		{
        		if(gangAssessmentForm.getParentNotified().equalsIgnoreCase("yes"))
        		    reqEvent.setParentNotified("1");
        		else if(gangAssessmentForm.getParentNotified().equalsIgnoreCase("no"))
        		    reqEvent.setParentNotified("0");
		}
		
		//parent notified gang assessment request
		if(gangAssessmentForm.getParentNotifiedGangAssReq()!=null){
		    if(gangAssessmentForm.getParentNotifiedGangAssReq().equalsIgnoreCase("Yes"))
			    reqEvent.setParentNotifiedGangAssReq("1");
		    else if(gangAssessmentForm.getParentNotifiedGangAssReq().equalsIgnoreCase("No"))
			    reqEvent.setParentNotifiedGangAssReq("0");
		}		

		reqEvent.setStatus("COMPLETED");

		return reqEvent;
	}

	/**
	 * Populate profile details.
	 * 
	 * @param gangAssessmentRefForm
	 * @param aRequest
	 */
	public static void populateProfileDetails(AssessmentReferralForm gangAssessmentRefForm,HttpServletRequest aRequest) {
		// Prefill profile details
		JuvenileProfileDetailResponseEvent profileDetail = UIJuvenileHelper.getJuvenileDetailForm(aRequest);

		gangAssessmentRefForm.setJuvenileNum(profileDetail.getJuvenileNum());
		gangAssessmentRefForm.setPersonMakingRef(UIUtil.getCurrentUserName());
		gangAssessmentRefForm.setJuvenileName(new Name(profileDetail.getFirstName(), profileDetail.getMiddleName(), profileDetail.getLastName()));
		gangAssessmentRefForm.setDateOfBirth(DateUtil.dateToString(profileDetail.getDateOfBirth(), UIConstants.DATE_FMT_1));
		gangAssessmentRefForm.setGender(profileDetail.getSex());
		String raceOrEthnicity = profileDetail.getRace();
		if (profileDetail.getEthnicity() != null&& !profileDetail.getEthnicity().equalsIgnoreCase("")) {
			raceOrEthnicity += "-" +CodeHelper.getFastCodeDescription(
					PDCodeTableConstants.JUVENILE_ETHNICITY,profileDetail.getEthnicity());;
		}
		gangAssessmentRefForm.setRaceOrEthinicity(raceOrEthnicity);
		gangAssessmentRefForm.setLanguage(CodeHelper.getFastCodeDescription(PDCodeTableConstants.LANGUAGE,profileDetail.getPrimaryLanguage()));
		gangAssessmentRefForm.setReferralDate(DateUtil.dateToString(new Date(),UIConstants.DATE_FMT_1));
		//gangAssessmentRefForm.setParentNotifiedGangAssReq("No"); //set default value of parent notified gang assessment req

		String facilityCode = null;
		// Send PD Request Event
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
		event.setJuvenileNum(gangAssessmentRefForm.getJuvenileNum());
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response); // Perform Error handling

		Map dataMap = MessageUtil.groupByTopic(response);
		if (dataMap != null) {
			Collection facilityHistory = (Collection) dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
			if (facilityHistory != null && facilityHistory.size() > 1) {
				List theList = new ArrayList(facilityHistory);

				ArrayList sortFields = new ArrayList();
				sortFields.add(new ReverseComparator(new BeanComparator("admitDate")));
				sortFields.add(new ReverseComparator(new BeanComparator("admitTime")));
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort(theList, multiSort);

				JuvenileDetentionFacilitiesResponseEvent jdfEvent = (JuvenileDetentionFacilitiesResponseEvent) theList.get(0);
				// if Juvenile is placed in facility already,default placement facility.
				if (jdfEvent != null&& (jdfEvent.getReleaseDate() == null || jdfEvent.getReleaseDate().equals(""))) {
					facilityCode = jdfEvent.getDetainedFacility();
					if(facilityCode!=null && !facilityCode.equals("")){
						gangAssessmentRefForm.setPlacementFacilityId(facilityCode);
					}
				}
			}
		}
	}

	/**
	 * SetReasonForReferralId
	 * 
	 * @param reasonForReferralsCodes
	 * @param gangAssessmentRefForm
	 */
	public static void setReasonForReferralId(String[] reasonForReferralsCodes,
			AssessmentReferralForm gangAssessmentRefForm) {

		StringBuffer reasonForReferralId = new StringBuffer();
		if (reasonForReferralsCodes != null) {
			for (int i = 0; i < reasonForReferralsCodes.length; i++) {
				reasonForReferralId.append(reasonForReferralsCodes[i]);
				if (i != reasonForReferralsCodes.length - 1) {
					reasonForReferralId.append(",");
				}
			}
			gangAssessmentRefForm.setReasonForReferralId(reasonForReferralId.toString());
		} else {
			gangAssessmentRefForm.setReasonForReferralId("");
		}
	}
}
