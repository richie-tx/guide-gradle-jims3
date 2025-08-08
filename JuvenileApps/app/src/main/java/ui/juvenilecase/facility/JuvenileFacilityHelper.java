package ui.juvenilecase.facility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.casefile.CreateActivityEvent;
import messaging.codetable.GetJuvenileAdmitReasonsEvent;
import messaging.codetable.GetJuvenileFacilitiesEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.GetJuvenileReferralSourceEvent;
import messaging.codetable.GetServiceDeliveryWithoutFeeCodeEvent;
import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileReferralSourceResponseEvent;
import messaging.codetable.criminal.reply.ServiceDeliveryWithoutFeeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDateRangeEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.GetAllJuvFacAdmissionCommentsEvent;
import messaging.facility.GetAllJuvFacSplAttnReasonCommentsEvent;
import messaging.facility.GetJuvenileCurrentFacilityDetailsEvent;
import messaging.facility.GetJuvenileFacilityAdmissionCommentsEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.facility.GetJuvenileFacilityHeaderEvent;
import messaging.facility.GetJuvenileFacilitySplAttnReasonCommentsEvent;
import messaging.facility.reply.JuvenileFacilityAdmissionCommentsResponseEvent;
import messaging.facility.reply.JuvenileFacilitySplAttnReasonResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetAllCasefilesForJuvenileEvent;
import messaging.juvenilecase.GetAllCasefilesForReferralsEvent;
import messaging.juvenilecase.GetCasefilesForReferralsEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.GetReferralsByCasefileIdEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJJSReferralWithoutFilterEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import messaging.referral.GetJuvenileProfileReferralListPetCJISEvent;
import messaging.referral.GetReferralListwithDALognumEvent;
import messaging.user.GetUserEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.commons.lang.StringUtils;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.security.SecurityUIHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class JuvenileFacilityHelper {
	
	
	/**
	 * Get Detention Traits 
	 * @param juvNum
	 * @return Collection of JuvenileTraitResponseEvent
	 */
	public static Collection<JuvenileTraitResponseEvent> getDetentionTraits(String juvNum, JuvenileBriefingDetailsForm form,JuvenileDetentionFacilitiesResponseEvent detentionResp){
		GetJuvenileTraitsEvent event =
			(GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
		event.setJuvenileNum(juvNum);
		//Added for U.S. #42660
		if(detentionResp.getOriginalAdmitOID()!=null){
			event.setFacilityAdmitOID(detentionResp.getOriginalAdmitOID());
		}else{
			event.setFacilityAdmitOID(detentionResp.getDetentionId());
		}
		event.setUIFacility(true);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
		
		if (juvenileTraits == null) {
			juvenileTraits = new ArrayList<JuvenileTraitResponseEvent>();
		} else {
			Collections.sort((ArrayList) (juvenileTraits));
		}
		
		Collection<JuvenileTraitResponseEvent>  detentionTrait = new ArrayList<JuvenileTraitResponseEvent>();
		
		//iterating the trait details to get trait desc Officer safety /security alert and show the alert in JSP ER JIMS200076601
		Iterator<JuvenileTraitResponseEvent> traitItr = juvenileTraits.iterator();
		while(traitItr.hasNext()){
			JuvenileTraitResponseEvent traitRespEvent = traitItr.next();			
			
			if(traitRespEvent.getFacilityAdmitOID()!=null){
				detentionTrait.add(traitRespEvent);
			}
		}
		return detentionTrait;
	}
	
	

	/**
	 * Get Detention Traits 
	 * @param juvNum
	 * @return Collection of JuvenileTraitResponseEvent
	 */
	public static Collection<JuvenileTraitResponseEvent> getDetentionTraits(String juvNum){
		GetJuvenileTraitsEvent event =
			(GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
		event.setJuvenileNum(juvNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
		
		if (juvenileTraits == null) {
			juvenileTraits = new ArrayList<JuvenileTraitResponseEvent>();
		} else {
			Collections.sort((ArrayList) (juvenileTraits));
		}
		
		Collection<JuvenileTraitResponseEvent>  detentionTrait = new ArrayList<JuvenileTraitResponseEvent>();
		
		//iterating the trait details to get trait desc Officer safety /security alert and show the alert in JSP ER JIMS200076601
		Iterator<JuvenileTraitResponseEvent> traitItr = juvenileTraits.iterator();
		while(traitItr.hasNext()){
			JuvenileTraitResponseEvent traitRespEvent = traitItr.next();
			if(traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_DETENTION)){
				detentionTrait.add(traitRespEvent);
			}
		}
		return detentionTrait;
	}
	
	
	
	
	
	/**
	 * Get Admission Information in the briefing page
	 * @param form
	 * @return JuvenileDetentionFacilitiesResponseEvent
	 */
	public static JuvenileDetentionFacilitiesResponseEvent getInFacilityDetails(String juvNum)
	{
		JuvenileDetentionFacilitiesResponseEvent juvFacDetails = new JuvenileDetentionFacilitiesResponseEvent();
		// call the header first and retrieve the sequence num .
		JuvenileFacilityHeaderResponseEvent facilityHeaderRespEvent =getJuvFacilityHeaderDetails(juvNum);
		if(facilityHeaderRespEvent != null)
		{	
			String lastSeqNum = facilityHeaderRespEvent.getLastSeqNum();
			JuvenileDetentionFacilitiesResponseEvent detentionFacility = null;
			// call the jjs detention record with the sequence no. One single fetch.
			GetJuvenileFacilityDetailsEvent facilityDetailsEvt =(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
			facilityDetailsEvt.setJuvenileNum(juvNum);
			facilityDetailsEvt.setFacilitySequenceNum(lastSeqNum);
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(facilityDetailsEvt);
			IEvent replyEvent = dispatch.getReply();
			CompositeResponse responses = (CompositeResponse) replyEvent;

			detentionFacility= (JuvenileDetentionFacilitiesResponseEvent)MessageUtil.filterComposite(responses,JuvenileDetentionFacilitiesResponseEvent.class);
			// now check for the seq num match for safety.
			if(detentionFacility != null && facilityHeaderRespEvent.getLastSeqNum()!=null && facilityHeaderRespEvent.getLastSeqNum().equals(detentionFacility.getFacilitySequenceNumber())){
				juvFacDetails=detentionFacility;
			}
		}
		return juvFacDetails;
	}
	
	public static JuvenileDetentionFacilitiesResponseEvent getReferralDetail(JuvenileDetentionFacilitiesResponseEvent admissionInfo, String juvNum, String referralNo){
	    
	    if(admissionInfo != null && juvNum != null && referralNo != null){
		
		Collection<JuvenileProfileReferralListResponseEvent> juvProfResEvt = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);
		
		Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = juvProfResEvt.iterator();
		while (juvProfResItr.hasNext())
		{
		    JuvenileProfileReferralListResponseEvent profileRespEvent = juvProfResItr.next();
		    	if (profileRespEvent != null && profileRespEvent.getReferralNumber().equalsIgnoreCase(referralNo))
		    	{
		    	    admissionInfo.setReferralOfficer(profileRespEvent.getReferralOfficer());
		    	    admissionInfo.setReferralSource(profileRespEvent.getReferralSource());
		    	    admissionInfo.setReferralSourceDesc(profileRespEvent.getReferralSourceDesc());
		    	    admissionInfo.setPetitionNum(profileRespEvent.getPetitionNumber());
		    	    break;
			}		    	
		}		
		
	    }
	    
	    return admissionInfo;
	}
	
	/**
	 * Get Most recent detention Record.
	 * @param juvenileNum
	 * @return facility
	 */
	public static JJSFacility getCurrentFacilityRecord(String juvenileNum){
		JJSFacility  facility =null;
		JJSHeader header = null;
		GetJuvenileFacilityDetailsEvent facilityDetailsEvent = new GetJuvenileFacilityDetailsEvent();
		facilityDetailsEvent.setJuvenileNum(juvenileNum);
		Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityDetailsEvent);
		
		Iterator<JJSHeader> jjsHeaderItr = JJSHeader.findAll("juvenileNumber",juvenileNum);
		while(jjsHeaderItr.hasNext()){
			header = jjsHeaderItr.next();
		}
			
		while(facilityItr.hasNext()){
			JJSFacility detentionRec = facilityItr.next();
			if(header!=null && detentionRec!=null){
				if(detentionRec.getFacilitySequenceNumber().equals(header.getLastSequenceNumber())){
					facility= detentionRec;
					break;
				}
			}
		}
		return facility;
	}
	
	/**
	 * Get Most recent detention Record.
	 * @param juvenileNum
	 * @return facility Response event
	 */
	public JuvenileDetentionFacilitiesResponseEvent getCurrentJuvenileFacilityRecord(String juvenileNum){
	    
	    JuvenileDetentionFacilitiesResponseEvent  facility = null;
		
	    GetJuvenileCurrentFacilityDetailsEvent reqEvent = 
			(GetJuvenileCurrentFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILECURRENTFACILITYDETAILS);
	    reqEvent.setJuvenileNum(juvenileNum);
		
	    facility = (JuvenileDetentionFacilitiesResponseEvent) MessageUtil.postRequest(reqEvent, JuvenileDetentionFacilitiesResponseEvent.class);
	    return facility;
	}
			
	/**
	 * getFacility
	 * @param codes
	 * @param facilityCode
	 * @return JuvenileFacilityResponseEvent
	 */
	public static JuvenileFacilityResponseEvent getFacility(Collection<JuvenileFacilityResponseEvent> codes, String facilityCode)
	{
		if(codes==null){
			return null;
		}
		
		Iterator<JuvenileFacilityResponseEvent> iter = codes.iterator();
		while(iter.hasNext())
		{
			JuvenileFacilityResponseEvent resp = (JuvenileFacilityResponseEvent)iter.next();
			if(resp.getCode().equals(facilityCode)){
				return resp;
			}
		}
		return null;
	}
	
	/**
	 * Get JJS_Facility details. Pass null to seq num if needed to retrieve all the details for the juvenile alone. 
	 * @param juvNum
	 * @return Collection<JuvenileDetentionFacilitiesResponseEvent> 
	 */
	public static Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvFacilityDetails(String juvNum,String seqNum){
		//Service to call JJS_FACILITY
		GetJuvenileFacilityDetailsEvent event =
			(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
		event.setJuvenileNum(juvNum);
		event.setFacilitySequenceNum(seqNum);
		event.setDetentionRecordId(null);
		event.setReferralNum(null);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacilityDetails =
			MessageUtil.compositeToCollection(responses, JuvenileDetentionFacilitiesResponseEvent.class);
		
		return juvFacilityDetails;
	}
	/**
	 * getJuvFacilityDetailsByDetentionID
	 * @param juvNum
	 * @param detentionId
	 * @param seqNum
	 * @return
	 */
	public static Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvFacilityDetailsByDetentionID (String detentionId){
		//Service to call JJS_FACILITY
		GetJuvenileFacilityDetailsEvent event =
			(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
		event.setJuvenileNum(null);
		event.setFacilitySequenceNum(null);
		event.setDetentionRecordId(detentionId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacilityDetails =
			MessageUtil.compositeToCollection(responses, JuvenileDetentionFacilitiesResponseEvent.class);
		
		return juvFacilityDetails;
	}
	
	/**
	 * getJuvFacilityDetailsByDetentionID
	 * @param juvNum
	 * @param detentionId
	 * @param seqNum
	 * @return
	 */
	public static Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvFacilityDetailsByReferral(String referralNum){
		//Service to call JJS_FACILITY
		GetJuvenileFacilityDetailsEvent event =
			(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
		event.setJuvenileNum(null);
		event.setFacilitySequenceNum(null);
		event.setDetentionRecordId(null);
		event.setReferralNum(referralNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacilityDetails =
			MessageUtil.compositeToCollection(responses, JuvenileDetentionFacilitiesResponseEvent.class);
		
		return juvFacilityDetails;
	}
	
	/**
	 * checkIfJJSFacilityHasMoreThanOneRecord
	 * @param juvNum
	 * @return boolean
	 */
	public static boolean checkIfJJSFacilityHasMoreThanOneRecord(String juvNum){
		boolean hasMoreRecs = false;
		GetJuvenileFacilityDetailsEvent facilityDetailsEvt =(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
		facilityDetailsEvt.setJuvenileNum(juvNum);
		facilityDetailsEvt.setFacilitySequenceNum(null);
		//Service call to jjsdetention.
		Iterator<JJSFacility> jjsFacility = JJSFacility.findAll(facilityDetailsEvt);
		while (jjsFacility.hasNext()){
			hasMoreRecs = true;
			break;
		}
		return hasMoreRecs;
	}
	
	/**
	 * Get admit form from session.
	 * @return Collection<JuvenileDetentionFacilitiesResponseEvent>
	 */
	public static  Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvFacilityDetailsFromSession(HttpServletRequest aRequest,String juvNum){
		HttpSession session = aRequest.getSession();
		Collection<JuvenileDetentionFacilitiesResponseEvent> facilities = (Collection<JuvenileDetentionFacilitiesResponseEvent>) session.getAttribute("JJS_FACILITY");
		if(facilities==null || (facilities!=null && !facilities.isEmpty())){
			facilities = getJuvFacilityDetails(juvNum,null);
		}
		return facilities;
	}
	
	/**
	 * pure post. no event and command. Used in modify admit, as it was affecting other class objects.
	 * getNonPostFacilityDetails
	 * @param juvNum
	 * @return 
	 */
	public static Collection<JuvenileDetentionFacilitiesResponseEvent> getNonPostFacilityDetails(String juvNum){
		Collection<JuvenileDetentionFacilitiesResponseEvent> detentionFacilitiesList = new ArrayList<JuvenileDetentionFacilitiesResponseEvent>();
		GetJuvenileFacilityDetailsEvent facilityDetailsEvent = new GetJuvenileFacilityDetailsEvent();
		facilityDetailsEvent.setJuvenileNum(juvNum);
		Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityDetailsEvent);
		while (facilityItr.hasNext()) {
			JJSFacility fac = (JJSFacility)facilityItr.next();
			JuvenileDetentionFacilitiesResponseEvent resp = JuvenileFacilityHelper.buildDetentionResponseEventForModifyAdmit(fac);
			detentionFacilitiesList.add(resp);
		}
		return detentionFacilitiesList;
	}
	
	/**
	 * getOriginalAdmitRec
	 * @param juvNum
	 * @param bookingRefNum
	 * @return
	 * @deprecated
	 */
	public static JJSFacility getOriginalAdmitRec(String juvNum,String bookingRef){
		JJSFacility tempFac = new JJSFacility();
		GetJuvenileFacilityDetailsEvent facilityDetailsEvent = new GetJuvenileFacilityDetailsEvent();
		facilityDetailsEvent.setJuvenileNum(juvNum);
		Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityDetailsEvent);
		List<JJSFacility> juvFacList = new ArrayList<JJSFacility>();
		while (facilityItr.hasNext()) {
			JJSFacility fac = (JJSFacility)facilityItr.next();
			juvFacList.add(fac);
		}
		//sort in descending order by seq num.
		Collections.sort((List<JJSFacility>)juvFacList,Collections.reverseOrder(new Comparator<JJSFacility>() {
			@Override
			public int compare(JJSFacility evt1, JJSFacility evt2) {
				return Integer.valueOf(evt1.getFacilitySequenceNumber()).compareTo(Integer.valueOf(evt2.getFacilitySequenceNumber()));
			}
		}));

		Iterator sortedFacList = juvFacList.iterator();
		while (sortedFacList.hasNext()) {
			JJSFacility fac = (JJSFacility)sortedFacList.next();
			//get the very first detention record 					
			if(fac.getOriginalAdmitDate()==null)  
			{
				tempFac=fac;
				break;
			}
			
		}
		return tempFac;
	}
	
	/**
	 * Get JJS_Header details
	 * @param juvNum
	 * @return JuvenileFacilityHeaderResponseEvent
	 */
	public static JuvenileFacilityHeaderResponseEvent getJuvFacilityHeaderDetails(String juvNum){
		//Service to call JJS_HEADER
		GetJuvenileFacilityHeaderEvent headerEvent =
			(GetJuvenileFacilityHeaderEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYHEADER);
		
		headerEvent.setJuvenileNum(juvNum);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(headerEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse response = (CompositeResponse)replyEvent;

		JuvenileFacilityHeaderResponseEvent juvFacHeaderRespEvent =(JuvenileFacilityHeaderResponseEvent)MessageUtil.filterComposite(response,JuvenileFacilityHeaderResponseEvent.class);
		return juvFacHeaderRespEvent;
	}
	public static JuvenileFacilityHeaderResponseEvent getJuvFacilityHeaderDetailsforTempRelease(String juvNum,String court){
		//Service to call JJS_HEADER
		GetJuvenileFacilityHeaderEvent headerEvent =
			(GetJuvenileFacilityHeaderEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYHEADER);
		
		headerEvent.setJuvenileNum(juvNum);
		headerEvent.setCourtType(court);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(headerEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse response = (CompositeResponse)replyEvent;

		JuvenileFacilityHeaderResponseEvent juvFacHeaderRespEvent =(JuvenileFacilityHeaderResponseEvent)MessageUtil.filterComposite(response,JuvenileFacilityHeaderResponseEvent.class);
		return juvFacHeaderRespEvent;
	}
	
	/**
	 * Get raw header object
	 * @param juvNum
	 * @return JJSHeader
	 * @deprecated
	 */
	public static JJSHeader getJJSHeader(String juvNum){
		JJSHeader header = null;
		//Service call to header.
		Iterator<JJSHeader> jjsHeaderItr = JJSHeader.findAll("juvenileNumber",juvNum);
		while(jjsHeaderItr.hasNext()){
			header = jjsHeaderItr.next();
		}
		return	header;
	}
	
	/**
	 * Get raw header object
	 * @param juvNum
	 * @return JJSHeader
	 */
	public JuvenileFacilityHeaderResponseEvent getJJSHeaderResp(String juvNum){
	    GetJuvenileFacilityHeaderEvent headerEvent =
			(GetJuvenileFacilityHeaderEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYHEADER);
		
		headerEvent.setJuvenileNum(juvNum);

		JuvenileFacilityHeaderResponseEvent responseEvent =
					(JuvenileFacilityHeaderResponseEvent)MessageUtil.postRequest( headerEvent,JuvenileFacilityHeaderResponseEvent.class );
		
		return responseEvent;
	}
	
	//GetJuvenileFacilityHeaderEvent
	
	/**
	 * get raw latest jjsDetention object
	 * @param juvNum
	 * @param lastSeqNum
	 * @return JJSFacility
	 */
	public static JJSFacility getJJSDetention(String juvNum,String lastSeqNum){
		JJSFacility facility = null;
		//Service to call JJS_FACILITY: Get the record that needs to be modified.

		JuvenileFacilityHeaderResponseEvent detentionRespEvent = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvNum);
		if(detentionRespEvent.getLastSeqNum().equals(lastSeqNum)){
			GetJuvenileFacilityDetailsEvent facilityDetailsEvt =(GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
			facilityDetailsEvt.setJuvenileNum(juvNum);
			facilityDetailsEvt.setFacilitySequenceNum(lastSeqNum);
			//Service call to jjsdetention.
			Iterator<JJSFacility> jjsFacility = JJSFacility.findAll(facilityDetailsEvt);
			while(jjsFacility.hasNext()){
				facility = jjsFacility.next();
				break;
			}
		}
		return facility;
	}
	
	/**
	 * get any jjsDetention object for juvNum, seqNum
	 * @param juvNum
	 * @param seqNum
	 * @return JJSFacility
	 */
    public static JJSFacility getJJSDetentionJuvNumSeqNum(String juvNum, String seqNum)
    {
	JJSFacility facility = null;
	//Service to call JJS_FACILITY: Get the record that needs to be modified.

	GetJuvenileFacilityDetailsEvent facilityDetailsEvt = (GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
	facilityDetailsEvt.setJuvenileNum(juvNum);
	facilityDetailsEvt.setFacilitySequenceNum(seqNum);
	//Service call to jjsdetention.
	Iterator<JJSFacility> jjsFacility = JJSFacility.findAll(facilityDetailsEvt);
	while (jjsFacility.hasNext())
	{
	    facility = jjsFacility.next();
	    break;
	}
	return facility;
    }
	
	
	/**
	 * Get Juvenile ReferralDetails
	 * @param juvenileNum
	 * @return JuvenileProfileReferralListResponseEvent
	 */
	public static Collection<JuvenileProfileReferralListResponseEvent> getJuvReferralDetails(String juvenileNum){
		Object juvRefLstObj = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Send PD Request Event
		GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
		event.setJuvenileNum(juvenileNum);
		dispatch.postEvent(event);
		// Get PD Response Event
		CompositeResponse responses = (CompositeResponse)dispatch.getReply();
		Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt =
				MessageUtil.compositeToCollection(responses, JuvenileProfileReferralListResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(responses);
				
		return juvProfRefListEvt;
	}
	/**
	 * Get Juvenile ReferralDetails
	 * @param juvenileNum
	 * @return JuvenileProfileReferralListResponseEvent
	 */
	public static Collection<JuvenileProfileReferralListResponseEvent> getReferralDetails(String dalogNum){
		Object juvRefLstObj = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Send PD Request Event
		GetReferralListwithDALognumEvent event = (GetReferralListwithDALognumEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETREFERRALLISTWITHDALOGNUM);
		event.setDalogNum(dalogNum);
		dispatch.postEvent(event);
		// Get PD Response Event
		CompositeResponse responses = (CompositeResponse)dispatch.getReply();
		Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt =
				MessageUtil.compositeToCollection(responses, JuvenileProfileReferralListResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(responses);
				
		return juvProfRefListEvt;
	}
	
	/**
	 * Get Juvenile ReferralDetails
	 * @param juvenileNum
	 * @return JuvenileProfileReferralListResponseEvent
	 */
	public static Collection<PetitionResponseEvent> getJuvReferralDetailsforPetCJISSearch(String juvenileNum,String typeCode){
		//Object juvRefLstObj = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		//Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt=new ArrayList<JuvenileProfileReferralListResponseEvent>();
		Collection<PetitionResponseEvent> juvProfRefListEvt = new ArrayList<PetitionResponseEvent>();
		
		// Send PD Request Event
		if(typeCode.isEmpty())
		{
		    	List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsForJuv(juvenileNum);//get ALL petitions for juv
			Iterator<PetitionResponseEvent> petition = petitions.iterator();
			while (petition.hasNext())
			{
			    PetitionResponseEvent petRespEvt = petition.next();
			    PetitionResponseEvent refResp=new PetitionResponseEvent();
			    if(petRespEvt!=null)
			    {		
                		GetJuvenileProfileReferralListPetCJISEvent event = (GetJuvenileProfileReferralListPetCJISEvent)
                				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLISTPETCJIS);
                		event.setJuvenileNum(juvenileNum);
                		event.setRefNum(petRespEvt.getReferralNum());
                		dispatch.postEvent(event);                		
                		CompositeResponse response=MessageUtil.postRequest(event);
                		PetitionResponseEvent respReffEvt = (PetitionResponseEvent) MessageUtil.filterComposite(response,
                			PetitionResponseEvent.class); 
                		refResp.setPetitionAllegation(petRespEvt.getPetitionAllegation());
    			    	refResp.setPetitionNum(petRespEvt.getPetitionNum());
    			    	refResp.setCJISNum(petRespEvt.getPetCJISNum());
    			    	refResp.setTermination_Date(DateUtil.dateToString(petRespEvt.getTerminationDate(), DateUtil.DATE_FMT_1));
    			    	refResp.setPetitionAllegation(petRespEvt.getOffenseCodeId());
    			    	//refResp.setPetitionAllegationDescr(petRespEvt.getOffenseCode().getDescription());
    			    	refResp.setReferralNum(respReffEvt.getReferralNum());
    			    	refResp.setReferralDate(respReffEvt.getReferralDate());
    			    	refResp.setCourtId(respReffEvt.getCourtId());
    			    	refResp.setCourtDate(respReffEvt.getCourtDate());
    			    	refResp.setCourtResult(respReffEvt.getCourtResult());
    			    	refResp.setCourtResultDesc(respReffEvt.getCourtResultDesc());
    			    	refResp.setFinalDisposition(respReffEvt.getFinalDisposition());
			    	refResp.setFinalDispositionDescription(respReffEvt.getFinalDispositionDescription());
			    	
			    	Collection<JJSOffense> offenses = respReffEvt.getOffenses();
                        	    if (offenses != null)
                        	    {
                        		Iterator<JJSOffense> offenseItr = offenses.iterator();
                        		while (offenseItr.hasNext())
                        		{
                        		    JJSOffense offense = offenseItr.next();
                        		    if (offense.getSequenceNum().equalsIgnoreCase("1"))
                        		       	refResp.setOffense(offense.getOffenseCodeId());                        		    
 
                        		}
                        	     }
			    }
			    juvProfRefListEvt.add(refResp);
			}
		}
		else if(typeCode.equalsIgnoreCase("pet"))
		{
		    List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsForpetNum(juvenileNum);//get ALL petitions for petition num
			Iterator<PetitionResponseEvent> petition = petitions.iterator();
			while (petition.hasNext())
			{
			    PetitionResponseEvent petRespEvt = petition.next();
			    PetitionResponseEvent refResp=new PetitionResponseEvent();
			    if(petRespEvt!=null)
			    {		
                		GetJuvenileProfileReferralListPetCJISEvent event = (GetJuvenileProfileReferralListPetCJISEvent)
                				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLISTPETCJIS);
                		event.setJuvenileNum(petRespEvt.getJuvenileNum());
                		event.setRefNum(petRespEvt.getReferralNum());
                		dispatch.postEvent(event);                		
                		CompositeResponse response=MessageUtil.postRequest(event);
                		PetitionResponseEvent respReffEvt = (PetitionResponseEvent) MessageUtil.filterComposite(response,
                			PetitionResponseEvent.class); 
                		refResp.setPetitionAllegation(petRespEvt.getPetitionAllegation());
    			    	refResp.setPetitionNum(petRespEvt.getPetitionNum());
    			    	refResp.setCJISNum(petRespEvt.getPetCJISNum());
    			    	refResp.setTermination_Date(DateUtil.dateToString(petRespEvt.getTerminationDate(), DateUtil.DATE_FMT_1));
    			    	refResp.setPetitionAllegation(petRespEvt.getOffenseCodeId());
    			    	//refResp.setPetitionAllegationDescr(petRespEvt.getOffenseCode().getDescription());
    			    	refResp.setReferralNum(respReffEvt.getReferralNum());
    			    	refResp.setReferralDate(respReffEvt.getReferralDate());
    			    	refResp.setCourtId(respReffEvt.getCourtId());
    			    	refResp.setCourtDate(respReffEvt.getCourtDate());
    			    	refResp.setCourtResult(respReffEvt.getCourtResult());
    			    	refResp.setCourtResultDesc(respReffEvt.getCourtResultDesc());
    			    	refResp.setFinalDisposition(respReffEvt.getFinalDisposition());
    			    	refResp.setFinalDispositionDescription(respReffEvt.getFinalDispositionDescription());
    			    	
    			    	Collection<JJSOffense> offenses = respReffEvt.getOffenses();
                        	    if (offenses != null)
                        	    {
                        		Iterator<JJSOffense> offenseItr = offenses.iterator();
                        		while (offenseItr.hasNext())
                        		{
                        		    JJSOffense offense = offenseItr.next();
                        		    if (offense.getSequenceNum().equalsIgnoreCase("1"))
                        		       	refResp.setOffense(offense.getOffenseCodeId());                        		    
    
                        		}
                        	     }
			    }
			    juvProfRefListEvt.add(refResp);
			}
		}
		else if(typeCode.equalsIgnoreCase("cjis"))
		{
		    List<PetitionResponseEvent> petitions = InterviewHelper.getPetitionsForCJIS(juvenileNum);//call like search event for cjis
			Iterator<PetitionResponseEvent> petition = petitions.iterator();
			while (petition.hasNext())
			{
			    PetitionResponseEvent petRespEvt = petition.next();
			    PetitionResponseEvent refResp=new PetitionResponseEvent();
			    if(petRespEvt!=null)
			    {		
                		GetJuvenileProfileReferralListPetCJISEvent event = (GetJuvenileProfileReferralListPetCJISEvent)
                				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLISTPETCJIS);
                		event.setJuvenileNum(petRespEvt.getJuvenileNum());
                		event.setRefNum(petRespEvt.getReferralNum());
                		dispatch.postEvent(event);                		
                		CompositeResponse response=MessageUtil.postRequest(event);
                		PetitionResponseEvent respReffEvt = (PetitionResponseEvent) MessageUtil.filterComposite(response,
                			PetitionResponseEvent.class); 
                		refResp.setPetitionAllegation(petRespEvt.getPetitionAllegation());
    			    	refResp.setPetitionNum(petRespEvt.getPetitionNum());
    			    	refResp.setCJISNum(petRespEvt.getPetCJISNum());
    			    	refResp.setTermination_Date(DateUtil.dateToString(petRespEvt.getTerminationDate(), DateUtil.DATE_FMT_1));
    			    	refResp.setPetitionAllegation(petRespEvt.getOffenseCodeId());
    			    	//refResp.setPetitionAllegationDescr(petRespEvt.getOffenseCode().getDescription());
    			    	refResp.setReferralNum(respReffEvt.getReferralNum());
    			    	refResp.setReferralDate(respReffEvt.getReferralDate());
    			    	refResp.setCourtId(respReffEvt.getCourtId());
    			    	refResp.setCourtDate(respReffEvt.getCourtDate());
    			    	refResp.setCourtResult(respReffEvt.getCourtResult());
    			    	refResp.setCourtResultDesc(respReffEvt.getCourtResultDesc());
    			    	refResp.setFinalDisposition(respReffEvt.getFinalDisposition());
    			    	refResp.setFinalDispositionDescription(respReffEvt.getFinalDispositionDescription());
    			    	
    			    	Collection<JJSOffense> offenses = respReffEvt.getOffenses();
                        	    if (offenses != null)
                        	    {
                        		Iterator<JJSOffense> offenseItr = offenses.iterator();
                        		while (offenseItr.hasNext())
                        		{
                        		    JJSOffense offense = offenseItr.next();
                        		    if (offense.getSequenceNum().equalsIgnoreCase("1"))
                        		       	refResp.setOffense(offense.getOffenseCodeId());                        		    
    
                        		}
                        	     }
			    }
			    juvProfRefListEvt.add(refResp);
			}
		}
		return juvProfRefListEvt;
	}
	/*public static Collection<JuvenileProfileReferralListResponseEvent> getpetitionreferralDetails(String juvenileNum)
	{
	
	}*/
	public static Collection<JuvenileProfileReferralListResponseEvent> getJuvReferralDetailsfiltered(String juvenileNum){
		Object juvRefLstObj = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		Collection<JuvenileProfileReferralListResponseEvent> filteredReferrals = new ArrayList();
		// Send PD Request Event
		GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
		event.setJuvenileNum(juvenileNum);
		dispatch.postEvent(event);
		// Get PD Response Event
		CompositeResponse responses = (CompositeResponse)dispatch.getReply();
		Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt =
				MessageUtil.compositeToCollection(responses, JuvenileProfileReferralListResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(responses);
		Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = juvProfRefListEvt.iterator();
		    while (referralIterator.hasNext())
		    { 
			JuvenileProfileReferralListResponseEvent refRec = referralIterator.next();
			if (refRec != null)
			{
			    if (refRec.getProbationEndDate() != null)
			    {
				filteredReferrals.add(refRec);
			    }
			}
		    }//
				
		return filteredReferrals;
	}
	
	/**
	 * get active Facility Code
	 * @param facilityCode
	 * @return JuvenileFacilityResponseEvent
	 */
	public static JuvenileFacilityResponseEvent getFacilityByCode(String facilityCode)
	{	
		ArrayList<JuvenileFacilityResponseEvent> activeFacilitiesList = (ArrayList<JuvenileFacilityResponseEvent>) loadActiveFacilities();
		Iterator<JuvenileFacilityResponseEvent> iter = activeFacilitiesList.iterator();
		while(iter.hasNext())
		{
			JuvenileFacilityResponseEvent resp = (JuvenileFacilityResponseEvent)iter.next();
			if(resp.getCode().equals(facilityCode)){
				return resp;
			}
		}
		return null;
	}
	
	/**
	 * loadActiveFacilities
	 * @param isHasPostAdjCatCasefile
	 * @param newAdmit
	 * @return Collection<JuvenileFacilityResponseEvent>
	 */
	public static Collection<JuvenileFacilityResponseEvent> loadActiveFacilities()
	{		
		GetJuvenileFacilitiesEvent facilityEvent = new GetJuvenileFacilitiesEvent();      
        CompositeResponse response = MessageUtil.postRequest(facilityEvent);
        List<JuvenileFacilityResponseEvent> facilityColl = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);	 
        List<JuvenileFacilityResponseEvent> activeFacilities = new ArrayList<JuvenileFacilityResponseEvent>();
        //go through the list and remove inactive facilities
        Iterator<JuvenileFacilityResponseEvent> iter = facilityColl.iterator();
	    while(iter.hasNext())
	    {
	    	JuvenileFacilityResponseEvent resp = iter.next();
	    	if( !"Y".equalsIgnoreCase(resp.getInactiveInd()) )
	    	{
	    		resp.setDescriptionWithCode(resp.getCode() +  " - " + resp.getDescription() );
	    		resp.setCodeWithJuvTJPCPlacementType(resp.getCode() + "|" + resp.getJuvTJPCPlacementType() + "|" + resp.getSecureStatus());
    			activeFacilities.add(resp);	
	    	}
	    }
	    Collections.sort(activeFacilities,JuvenileFacilityResponseEvent.CodeComparator );
	  
	    return activeFacilities;
	}
	
	/**
	 * loadNonPostAdjFacilities
	 * 
	 * 
	 * @return Collection<JuvenileFacilityResponseEvent>
	 */
	public static Collection<JuvenileFacilityResponseEvent> loadNonPostAdjFacilities()
	{
		//Load the facility list
        //New Admission - check all the casefile supervision categories 
        //any active caseile of Post-Adj Res - display all facilities
        //no active casefile of Post-Adj res - display facilities with D, E, H placement type

        List<JuvenileFacilityResponseEvent> facilityColl = (List<JuvenileFacilityResponseEvent>) loadActiveFacilities(); 
        List<JuvenileFacilityResponseEvent> activeFacilities = new ArrayList<JuvenileFacilityResponseEvent>();
        //go through the list and remove inactive facilities
        Iterator<JuvenileFacilityResponseEvent> iter = facilityColl.iterator();
	    while(iter.hasNext())
	    {
	    	JuvenileFacilityResponseEvent resp = iter.next();
	    	if(resp!=null){
		    	if(resp.getJuvTJPCPlacementType()!=null &&( resp.getJuvTJPCPlacementType().equalsIgnoreCase("D") || resp.getJuvTJPCPlacementType().equalsIgnoreCase("E") || resp.getJuvTJPCPlacementType().equalsIgnoreCase("H"))){
	 	    	 activeFacilities.add(resp);	 
		    	}
	    	}
	    }
	    Collections.sort(activeFacilities,JuvenileFacilityResponseEvent.CodeComparator );
	    return activeFacilities;
	}
	/**
	 * loadActiveFacilities
	 * @param form
	 * @return Collection<JuvenileFacilityResponseEvent> 
	 */
	public static Collection<JuvenileFacilityResponseEvent> loadAllFacilities()
	{		
		GetJuvenileFacilitiesEvent facilityEvent = new GetJuvenileFacilitiesEvent();      
        CompositeResponse response = MessageUtil.postRequest(facilityEvent);
        List<JuvenileFacilityResponseEvent> facilityColl = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);	 
        Collections.sort(facilityColl,JuvenileFacilityResponseEvent.CodeComparator );  	  
	    return facilityColl;
	}
	
	/**
	 * Get Description for the escape code.
	 * @param code
	 * @return ServiceDeliveryWithoutFeeResponseEvent
	 */
	public static ServiceDeliveryWithoutFeeResponseEvent getServicesDelivered(String code){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetServiceDeliveryWithoutFeeCodeEvent servicesDeliveredEvent = (GetServiceDeliveryWithoutFeeCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSERVICEDELIVERYWITHOUTFEECODE);
		servicesDeliveredEvent.setCode(code);
		//servicesDeliveredEvent.setCategory("E");
		
		dispatch.postEvent(servicesDeliveredEvent);
		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		ServiceDeliveryWithoutFeeResponseEvent serviceDeliveryWithoutFeeResp =(ServiceDeliveryWithoutFeeResponseEvent)MessageUtil.filterComposite(response,ServiceDeliveryWithoutFeeResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(response);
		return serviceDeliveryWithoutFeeResp;
	}
	
	/**
	 * Load active escape codes with category E
	 * @return  Collection<ServiceDeliveryWithoutFeeResponseEvent>
	 */
	public static Collection<ServiceDeliveryWithoutFeeResponseEvent> loadActiveEscapeCodes()
	{		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetServiceDeliveryWithoutFeeCodeEvent servicesDeliveredEvent = (GetServiceDeliveryWithoutFeeCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSERVICEDELIVERYWITHOUTFEECODE);
		
		// Get PD Response Event
		 CompositeResponse response = MessageUtil.postRequest(servicesDeliveredEvent);
		 List<ServiceDeliveryWithoutFeeResponseEvent> servicesDeliveredRespList = MessageUtil.compositeToList(response, ServiceDeliveryWithoutFeeResponseEvent.class);	
		 // Perform Error handling
		 MessageUtil.processReturnException(response);
   
		 List<ServiceDeliveryWithoutFeeResponseEvent>  serviceDeliveryWithoutFeeCodes= new ArrayList<ServiceDeliveryWithoutFeeResponseEvent>();
		 //go through the list and remove inactive codes.
		 Iterator<ServiceDeliveryWithoutFeeResponseEvent> servicesDeliveredWithoutFeeCodeItr = servicesDeliveredRespList.iterator();
		 while(servicesDeliveredWithoutFeeCodeItr.hasNext())
		  {
			 ServiceDeliveryWithoutFeeResponseEvent resp = servicesDeliveredWithoutFeeCodeItr.next();
		   		if(!"Y".equalsIgnoreCase(resp.getInactiveInd()) && "E".equalsIgnoreCase( resp.getCategory()))
		   		{
		   			serviceDeliveryWithoutFeeCodes.add(resp);	    		
		   		}
		   }
		 Collections.sort(serviceDeliveryWithoutFeeCodes);
		return serviceDeliveryWithoutFeeCodes;
	}
	
	
	/**
	 * written as pass through for facilities for Prod Support. Calls normal UIFacilityPopulationHelper that all facilties calls
	 * gets all admit reasons already formatted as 'code - description'
	 * @param code
	 * @return description
	 */
	public static ArrayList<JuvenileAdmitReasonsResponseEvent> getAdmitReasons(){
		ArrayList<JuvenileAdmitReasonsResponseEvent> allAdmitReasons = (ArrayList)UIFacilityPopulationHelper.loadAdmitReasons();
		return allAdmitReasons;
	}
	
	/**
	 * Get admitReason description
	 * @param code
	 * @return description
	 */
	public static JuvenileAdmitReasonsResponseEvent getAdmitReasonByCode(String code){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileAdmitReasonsEvent admitReasonsEvent = (GetJuvenileAdmitReasonsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEADMITREASONS);
		admitReasonsEvent.setCode(code);
		dispatch.postEvent(admitReasonsEvent);
		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		JuvenileAdmitReasonsResponseEvent juvAdmitReasonResp =(JuvenileAdmitReasonsResponseEvent)MessageUtil.filterComposite(response,JuvenileAdmitReasonsResponseEvent.class);
		// Perform Error handling
		MessageUtil.processReturnException(response);
		return juvAdmitReasonResp;
	}
	
	/**
	 * Get AdmitReason
	 * @param code
	 * @param codes
	 * @return JuvenileAdmitReasonsResponseEvent
	 */
	public static JuvenileAdmitReasonsResponseEvent getAdmitReason(String code, Collection<JuvenileAdmitReasonsResponseEvent> codes){		
		Iterator<JuvenileAdmitReasonsResponseEvent> iter = codes.iterator();
		while(iter.hasNext())
		{
			JuvenileAdmitReasonsResponseEvent juvAdmitReasonResp = iter.next();
			if(juvAdmitReasonResp.getCode().equalsIgnoreCase(code))
				return juvAdmitReasonResp;	
		}
		return null;
	}

	/**
	 * Get admit form from session.
	 * @return AdmitReleaseForm
	 */
	public static AdmitReleaseForm getAdmitForm(HttpServletRequest aRequest){
		HttpSession session = aRequest.getSession();
		AdmitReleaseForm admitForm = (AdmitReleaseForm)session.getAttribute("admitReleaseForm");
		return admitForm;
	}
	
	/**
	 * Get Original Admit Date
	 * @param form
	 * @return JuvenileDetentionFacilitiesResponseEvent
	 */
	public static JuvenileDetentionFacilitiesResponseEvent getOriginalAdmitRec(Collection juvFacList, String bookingRef){
		//sort in descending order by seq num.
		Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)juvFacList,Collections.reverseOrder(new Comparator<JuvenileDetentionFacilitiesResponseEvent>() {
			@Override
			public int compare(JuvenileDetentionFacilitiesResponseEvent evt1, JuvenileDetentionFacilitiesResponseEvent evt2) {
				return Integer.valueOf(evt1.getFacilitySequenceNumber()).compareTo(Integer.valueOf(evt2.getFacilitySequenceNumber()));
			}
		}));
		Iterator<JuvenileDetentionFacilitiesResponseEvent> iter = juvFacList.iterator();
		JuvenileDetentionFacilitiesResponseEvent tempResp = new JuvenileDetentionFacilitiesResponseEvent();
		while(iter.hasNext())
		{
			JuvenileDetentionFacilitiesResponseEvent resp = iter.next();
			//get the very first detention record 					
			if(resp.getOriginalAdmitDate()==null)  
			{
				tempResp=resp;
				break;
			}		
		
		}
		return tempResp;
	}
	
	
	/**
	 * validReferralSource
	 * @param source
	 * @return boolean
	 */
	public static boolean validReferralSource(String source){
		//strip the preceding 0 if there is any
		String[] ref = source.split("");
		if(ref.length>1)
		{
        		if(ref[0].equals("0"))
        			source=ref[1];
		}
		GetJuvenileReferralSourceEvent refEvent = (GetJuvenileReferralSourceEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEREFERRALSOURCE);
		refEvent.setCode(source);
		IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
		disp.postEvent(refEvent);
		CompositeResponse resp = (CompositeResponse)disp.getReply();
		JuvenileReferralSourceResponseEvent refResp = (JuvenileReferralSourceResponseEvent)MessageUtil.filterComposite(resp, JuvenileReferralSourceResponseEvent.class);
		if(refResp==null || refResp.getCode()==null)
			return false;
		return true;
	}
	
	/**
	 * Get Admit code with detention Type
	 * @param reasons
	 * @param reasonCode
	 * @return String
	 */
    public static String getAdmitCodeWithDetType(Collection<JuvenileAdmitReasonsResponseEvent> reasons, String reasonCode)
    {    	
    	Iterator<JuvenileAdmitReasonsResponseEvent> reasonsIter = reasons.iterator();
    	while(reasonsIter.hasNext())
    	{
    		JuvenileAdmitReasonsResponseEvent reasonResp = reasonsIter.next();
    		if(reasonResp.getCode().equalsIgnoreCase(reasonCode))
    			return reasonResp.getCodeWithDetType();
    	}
       	return null; 	
    }
    
    /**
     * getFacilityCodeWithJuvTJPCPlacementType
     * @param facilities
     * @param transferTo
     * @return String
     */
    public static String getFacilityCodeWithJuvTJPCPlacementType(Collection<JuvenileFacilityResponseEvent> facilities, String transferTo)
    {    	
    	Iterator<JuvenileFacilityResponseEvent> facilitiesIter = facilities.iterator();
    	while(facilitiesIter.hasNext())
    	{
    		JuvenileFacilityResponseEvent facResp = facilitiesIter.next();
    		if(facResp.getCode().equalsIgnoreCase(transferTo))
    			return facResp.getCodeWithJuvTJPCPlacementType();
    	}
       	return null; 	
    }
    
    /** method added for US 11094. Task#41503 get Only FacilityJuvPlacementType
     * getOnlyJuvPlacementType
     * @param facilities
     * @param transferTo
     * @return String 
     */
    public static String getFacilityJuvPlacementType(Collection<JuvenileFacilityResponseEvent> facilities, String transferTo)
    {    	
    	Iterator<JuvenileFacilityResponseEvent> facilitiesIter = facilities.iterator();
    	while(facilitiesIter.hasNext())
    	{
    		JuvenileFacilityResponseEvent facResp = facilitiesIter.next();
    		if(facResp.getCode().equalsIgnoreCase(transferTo))
    		    return facResp.getJuvPlacementType();
    	}
       	return null; 	 
    }
    
    /** method added for US 11094. Task#41503 get Only Facility Juv TJPC PlacementType
     * getOnlyJuvTJPCPlacementType
     * @param facilities
     * @param transferTo
     * @return String
     */
    public static String getFacilityJuvTJPCPlacementType(Collection<JuvenileFacilityResponseEvent> facilities, String transferTo)
    {    	
    	Iterator<JuvenileFacilityResponseEvent> facilitiesIter = facilities.iterator();
    	while(facilitiesIter.hasNext())
    	{
    		JuvenileFacilityResponseEvent facResp = facilitiesIter.next();
    		if(facResp.getCode().equalsIgnoreCase(transferTo))
    		    return facResp.getJuvTJPCPlacementType();
    	}
       	return null; 	
    }
    
    /**
     * getFacilityLabels
     * @param facilities
     * @param transferTo
     * @return JuvenileFacilityResponseEvent
     */
    public static JuvenileFacilityResponseEvent getFacilityResp(Collection<JuvenileFacilityResponseEvent> facilities, String transferTo)
    {    	
    	Iterator<JuvenileFacilityResponseEvent> facilitiesIter = facilities.iterator();
    	while(facilitiesIter.hasNext())
    	{
    		JuvenileFacilityResponseEvent facResp = facilitiesIter.next();
    		if(facResp.getCode().equalsIgnoreCase(transferTo))
    			return facResp;
    	}
       	return null; 	
    }
    
    /**
     * Swap Other to the end of the list with the last element in the list.
     * @return swapped list 
     */
    public static List<CodeResponseEvent> getSortedSpecialAttnCodes(){
    	//load special attention reasons              
		List<CodeResponseEvent> splAttnList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SPECIAL_ATTENTION_REASON);
		int lastElementIndx =splAttnList.size()-1;
		int othrIndex=0;
		for(int i=0;i<splAttnList.size()-1;i++){
			CodeResponseEvent resp = splAttnList.get(i);
			if(resp.getCode()!=null && resp.getCode().equals("OT")){
				othrIndex=i;
				break;
			}
		}
		Collections.swap(splAttnList,othrIndex,lastElementIndx);//other and last element in the list
		return splAttnList;
    }
    
    /**
     * getJJSDetentionByAttribute
     * @param juvNum
     * @param refNum
     * @param courtDate
     * @return DocketEventResponseEvent
     */
    public static DocketEventResponseEvent getCalendarDetention(String juvNum,String refNum,String courtDateStr){
    	GetJJSCLDetentionByDateRangeEvent refEvent = (GetJJSCLDetentionByDateRangeEvent)EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYDATERANGE);
    	refEvent.setJuvenileNumber(juvNum);
    	
    	Date currentDate = DateUtil.getCurrentDate();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date courtDate = new Date();
    	try {
    			courtDate = format.parse(courtDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	if(courtDate.before(currentDate))
    	{
	    	refEvent.setStartDate(courtDateStr);
	    	refEvent.setEndDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
    	}
    	else
    	{
    		refEvent.setStartDate(DateUtil.getCurrentDateString("yyyy-MM-dd"));
	    	refEvent.setEndDate(courtDateStr);
    	}
		IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
		disp.postEvent(refEvent);
		CompositeResponse resp = (CompositeResponse)disp.getReply();
		DocketEventResponseEvent respEvt = (DocketEventResponseEvent)MessageUtil.filterComposite(resp, DocketEventResponseEvent.class);
		return respEvt;
    }
    
    /**
     * getCalendarDetentions
     * @param juvNum
     * @return List<DocketEventResponseEvent>
//81390
     */
    public static List<DocketEventResponseEvent> getCalendarDetentions(String juvNum){
    	List<DocketEventResponseEvent> detentionRespEvts = new ArrayList<DocketEventResponseEvent>();
    	Iterator<JJSCLDetention> iter = JJSCLDetention.findAll("juvenileNumber", juvNum);
		while (iter.hasNext())
		{
		    JJSCLDetention detention = (JJSCLDetention) iter.next();
			if(detention!=null){
				DocketEventResponseEvent resp = detention.valueObject();
				detentionRespEvts.add(resp);
			}
		}
		return detentionRespEvts;
    }
    
    /**
	 * SetSaReasoncode
	 * 
	 * @param reasonForReferralsCodes
	 * @param gangAssessmentRefForm
	 */
	public static void setSAReasonDesc(List<String> saReasonDesc,AdmitReleaseForm admitReleaseForm) {

		StringBuffer saReasonDescStr = new StringBuffer();
		StringBuffer saReasonCodeStr = new StringBuffer();
		
		if (saReasonDesc != null) {
			for (int i = 0; i < saReasonDesc.size(); i++) {
				saReasonDescStr.append(saReasonDesc.get(i));
				saReasonCodeStr.append(admitReleaseForm.getSelectedSAReasons()[i]);
				if (i != saReasonDesc.size() - 1) {
					saReasonDescStr.append(",");
					saReasonCodeStr.append(",");
				}
			}
			admitReleaseForm.setSaReasonStr(saReasonDescStr.toString());
			admitReleaseForm.setSaReason(saReasonCodeStr.toString());
		} else {
			admitReleaseForm.setSaReasonStr("");
			admitReleaseForm.setSaReason("");
		}
	}
	
	/**
	 * Get the header information from the briefing details
	 * @param briefingDetailsForm
	 * @return JuvenileFacilityHeaderResponseEvent
	 */
	public static JuvenileFacilityHeaderResponseEvent getHeaderDetails( JuvenileBriefingDetailsForm briefingDetailsForm){
		return  briefingDetailsForm.getHeaderInfo();
	}
	
	/**
	 * Get detention details from the briefing details
	 * @param briefingDetailsForm
	 * @return JuvenileDetentionFacilitiesResponseEvent
	 */
	public static JuvenileDetentionFacilitiesResponseEvent getDetentionDetails( JuvenileBriefingDetailsForm briefingDetailsForm){
		return  briefingDetailsForm.getAdmissionInfo();
	}
	
	/**
	 * populate admit release form from briefing details.
	 * @param form
	 * @param briefingDetailsForm
	 */
	public static void populateAdmitReleaseForm(AdmitReleaseForm form, JuvenileBriefingDetailsForm briefingDetailsForm){
		
		//Header information
		form.setJuvenileNum(briefingDetailsForm.getJuvenileNum());
		form.setProfileDetail(briefingDetailsForm.getProfileDetail());
		form.setEthnicity(briefingDetailsForm.getEthnicity());
		// the if check below is to list only valid Facilities in the 'Transfer To Facility' field for 'Facility Transfer' US 40769, Task# 42674
		if (form.getAction()!= null && form.getAction().equalsIgnoreCase("inTransferView")){
			if(!briefingDetailsForm.isHasPostAdjCatCasefile()){ //if it is a non post adj casefile.Restrict the facility to fewer ones with tjpc placement type D,E,H.
				form.setFacilities(JuvenileFacilityHelper.loadNonPostAdjFacilities());
			}else{
				form.setFacilities(JuvenileFacilityHelper.loadActiveFacilities());
			}
		}else{
			form.setFacilities(JuvenileFacilityHelper.loadActiveFacilities());
		}
		
		form.setCasefiles(briefingDetailsForm.getCasefiles());

		//Primary language for the header 
		String primaryLanguageId = null;
		if(briefingDetailsForm.getProfileDetail()!=null){
			primaryLanguageId= briefingDetailsForm.getProfileDetail().getPrimaryLanguage();
		}
		if(primaryLanguageId!=null && !primaryLanguageId.isEmpty()){
			form.setPrimaryLanguageDesc(CodeHelper.getFastCodeDescription(PDCodeTableConstants.LANGUAGE,primaryLanguageId));
		}
		
		//booking referral
        form.setBookingOffense(briefingDetailsForm.getBookingOffenseCodeDesc()); 
        form.setBookingOffenseCd(briefingDetailsForm.getBookingOffenseCode());
        form.setBookingOffenseLevel(briefingDetailsForm.getBookingOffenseLevel());
        form.setBookingSupervisionNum(briefingDetailsForm.getBookingSupervisionNum()); //hot fix.Bug no:51682
        form.setReferrals(briefingDetailsForm.getReferrals());
        
        //current offense
        form.setCurrentOffense(briefingDetailsForm.getAdmissionInfo().getCurrentOffenseDesc());
        form.setCurrentOffenseCd(briefingDetailsForm.getAdmissionInfo().getCurrentOffense());
        if (briefingDetailsForm.isTempRelDecisionEnabled())
            form.setTempRelDecisionEnabled(true);
        else
            form.setTempRelDecisionEnabled(false);
        if (briefingDetailsForm.isTempRelDecisionDistEnabled())
            form.setTempRelDecisionDistEnabled(true);
        else
            form.setTempRelDecisionDistEnabled(false);
        
        //get header details:
        JuvenileFacilityHeaderResponseEvent  headerInfo = briefingDetailsForm.getHeaderInfo();
        populateAdmitReleaseFormFromHeader(form,headerInfo);
      
      	//get Admission details.
		JuvenileDetentionFacilitiesResponseEvent detResp=briefingDetailsForm.getAdmissionInfo();
      	populateAdmitReleaseFormFromDetention(form,detResp);
        
	}
	
	/**
	 * Populate header information to the admit release form.
	 * @param form
	 * @param headerInfo
	 */
	public static void populateAdmitReleaseFormFromHeader(AdmitReleaseForm form,JuvenileFacilityHeaderResponseEvent  headerInfo){
		form.setHeaderInfo(headerInfo);
		
	 	if(headerInfo!=null){
      		form.setFacilityStatus(headerInfo.getFacilityStatus()); //set facility status.
      		form.setFacilityStatusDesc(SimpleCodeTableHelper.getDescrByCode("FACILITY_STATUS", headerInfo.getFacilityStatus())); //set facility status desc.
      	}
	 	
		
			if(form.getAction().equals("modifyAdmitView")){
				if(headerInfo.getRelocationDate()!=null){
					form.setEscapeDate(DateUtil.dateToString(headerInfo.getRelocationDate(),DateUtil.DATE_FMT_1));
				}else{
					form.setEscapeDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
				}
			}else{
				if(headerInfo.getRelocationDate()!=null){
					form.setEscapeDate(DateUtil.dateToString(headerInfo.getRelocationDate(),DateUtil.DATE_FMT_1));
				}else{
					form.setEscapeDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
				}
			}
	 	
	
	 	if(headerInfo.getRelocationTime()!=null && !headerInfo.getRelocationTime().isEmpty()&& headerInfo.getRelocationTime().equals("00:00")){
	 		form.setEscapeTime(JuvenileFacilityHelper.stripColonInDate(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)));
	 	}else{
			if(form.getAction().equals("modifyAdmitView")){
				if(headerInfo.getRelocationTime()!=null && !headerInfo.getRelocationTime().isEmpty()){
					form.setEscapeTime(JuvenileFacilityHelper.stripColonInDate(headerInfo.getRelocationTime()));
				}else{
			 		form.setEscapeTime(JuvenileFacilityHelper.stripColonInDate(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)));
				}
			}else{
				if(headerInfo.getRelocationTime()!=null && !headerInfo.getRelocationTime().isEmpty()){
					form.setEscapeTime(headerInfo.getRelocationTime());
				}else{
					form.setEscapeTime(JuvenileFacilityHelper.stripColonInDate(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)));
				}
			}
	 	}
		form.setNextHearingDate(headerInfo.getNextHearingDate()); //part of detention in facility but pulled from header.
	}
	
	/**
	 * Populate detention information to the admit release form.
	 */
	public static void  populateAdmitReleaseFormFromDetention(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		form.setDetResp(detResp);
		
		if(detResp!=null)
		{
			form.setDetainedFacility(detResp.getDetainedFacility());
			form.setDetainedFacilityStr(detResp.getDetainedFacilityDesc());
			
			populateFacilityObservationStatus(form,detResp);
			populateFacilityRelease(form,detResp);
			populateReturnInformation(form,detResp);
			populateAdmissionInformation(form,detResp);
			populateEscapeInformation(form,detResp);
			populateDetentionHearing(form,detResp);
		}
	}
	
	/**
	 * Populate Special attention to the admit release form 
	 * @param form
	 * @param detResp
	 */
	public static void populateFacilityObservationStatus(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		//Observation status details starts
		form.setSpecialAttentions(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SPECIAL_ATTENTION)); //spl atten reason code
		form.setSpecialAttentionReasons(JuvenileFacilityHelper.getSortedSpecialAttnCodes()); //spl atten reason code
		form.setSaReasonStr(detResp.getSaReason());
		
		
		if(detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().isEmpty()){
			form.setSpecialAttentionCd(detResp.getSpecialAttention());
			form.setSelectedSA(detResp.getSpecialAttention());
		}else{
			form.setSpecialAttentionCd("NO"); //default it to none
			form.setSelectedSA("NO");
		}
		//task #34820
		//if the saReason is there, check if other is selected. If it is, then get saReasonComments
		if(detResp.getSaReason()!= null && !detResp.getSaReason().equals(""))
		{
			String[] saReasonCodes = StringUtils.split(detResp.getSaReason(), ",");
			for (int i = 0; i < saReasonCodes.length; i++) {
				if(saReasonCodes[i]!=null && !saReasonCodes[i].isEmpty() && saReasonCodes[i].equalsIgnoreCase("OT"))
				{
					form.setSaReasonOtherComments(JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId()));
				}
				else
					form.setSaReasonOtherComments("");
			}
		}
	}
	/**
	 * Populate Release Information to the admit release form
	 * @param form
	 * @param detResp
	 */
	public static void populateFacilityRelease(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		//release Information starts
		form.setReleaseReason(detResp.getReleaseReason());
		form.setReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASE_REASON, detResp.getReleaseReason()));
		
		if(detResp.getReleaseDate()!=null && (form.getAction().equals("modifyAdmitView")|| form.getAction().equals("retTempReleaseView"))){
			form.setReleaseDate(DateUtil.dateToString(detResp.getReleaseDate(),DateUtil.DATE_FMT_1));
		}else{ 
			form.setReleaseDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));//default to current Date
		}
		
		if(detResp.getReleaseTime()!=null && !detResp.getReleaseTime().isEmpty()&&!detResp.getReleaseTime().equals("00:00") && (form.getAction().equals("modifyAdmitView")|| form.getAction().equals("retTempReleaseView"))){
			form.setReleaseTime(JuvenileFacilityHelper.stripColonInDate(detResp.getReleaseTime()));
		}else{ 
			form.setReleaseTime(JuvenileFacilityHelper.stripColonInDate(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)));//default to current Time
		}
		if(form.getAction().equals("modifyAdmitView")|| form.getAction().equals("retTempReleaseView")){
			form.setReleasedBy(detResp.getReleaseBy());
		}else{
			if(detResp.getReleaseBy()==null ||(detResp.getReleaseBy()!=null &&detResp.getReleaseBy().isEmpty())){
				form.setReleasedBy(SecurityUIHelper.getLogonId());
			}
		}
		form.setReleaseAuthorities(CodeHelper.getActiveCodesInM204(PDCodeTableConstants.RELEASED_BY_AUTHORITY,true)); //release authority drop down
		
		if(form.getAction().equals("modifyAdmitView")||form.getAction().equals("inTransferView")|| form.getAction().equals("retTempReleaseView")){
			if(detResp.getReleaseAuthorizedBy()!=null && !detResp.getReleaseAuthorizedBy().isEmpty()){ //#51735 changes.
				form.setReleaseAuthority(detResp.getReleaseAuthorizedBy());
				if(detResp.getReleaseAuthorizedBy().trim().length()<4){
					IName name =SecurityUIHelper.getUserName("UV"+detResp.getReleaseAuthorizedBy());
					if(name!=null){
					    if( (name.getFirstName()==null || name.getFirstName().equals("")) 
							&& (name.getLastName()==null || name.getLastName().equals(""))
							&& (name.getMiddleName()==null || name.getMiddleName().equals(""))
							)
						form.setReleaseAuthorityDesc("NOT AVAILABLE");
					    else
						form.setReleaseAuthorityDesc(name.getFormattedName());
					}
				}
			}else{
				form.setReleaseAuthority("");
			}
		}
		form.setReleaseTo(ComplexCodeTableHelper.getActiveReleasedFromDetentionCodes()); //release To drop down
		if(form.getAction().equals("modifyAdmitView")|| form.getAction().equals("retTempReleaseView")){
			form.setReleasedTo(detResp.getReleaseTo());
		}else{
			form.setReleasedTo("");
		}
		
		form.setTransferToFacilities(CodeHelper.getActiveCodesInM204(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, true));
		if(form.getAction().equals("modifyAdmitView")){
			form.setTransferToFacility(detResp.getTransferToFacility());
			form.setTransferToFacilityDesc(detResp.getTransferToFacilityDesc());
		}else{
			form.setTransferToFacility("");
			form.setTransferToFacilityDesc("");
		}
		//TemporaryRelease from SQL
		form.setTempReleaseReasons(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.TEMP_RELEASE_REASON, true));
		if(form.getAction().equals("modifyAdmitView") || form.getAction().equals("retTempReleaseView")){
			form.setTempReleaseReason(detResp.getTempReleaseReasonCd());
			form.setTempReleaseReasonDesc(detResp.getTempReleaseReasonCdDesc());
		}else{
			form.setTempReleaseReason("");
			form.setTempReleaseReasonDesc("");
		}
		form.setOutcomes(CodeHelper.getActiveCodesInM204(PDCodeTableConstants.FACILITY_OUTCOME,true));
		if(form.getAction().equals("modifyAdmitView") || form.getAction().equals("returnEscapeView")){
			form.setOutcome(detResp.getOutcome());
			form.setOutcomeDesc(detResp.getOutcomeDesc());
		}else{
			form.setOutcome("");
			form.setOutcomeDesc("");
		}
	}
	
	/**
	 * Populate Return Information to the admit release form.
	 * @param form
	 * @param detResp
	 */
	public static void populateReturnInformation(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		//return Information details
		if(detResp.getReturnDate()!=null && form.getAction().equals("modifyAdmitView")){
			form.setReturnDate(DateUtil.dateToString(detResp.getReturnDate(),DateUtil.DATE_FMT_1));
		}else{ //default to current Date
			form.setReturnDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1)); 
		}
		
		if(detResp.getReturnTime()!=null && !detResp.getReturnTime().isEmpty()&& !detResp.getReturnTime().equals("00:00") && form.getAction().equals("modifyAdmitView")){
			form.setReturnTime(JuvenileFacilityHelper.stripColonInDate(detResp.getReturnTime()));
		}else{//default to current Time
			form.setReturnTime(JuvenileFacilityHelper.stripColonInDate(DateUtil.dateToString(Calendar.getInstance().getTime(),DateUtil.TIME24_FMT_1)));//return time, need to add a new column in jjs_detention
		}
		
		form.setReturnReasons(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.RETURN_REASON,true));
		if(form.getAction().equals("modifyAdmitView")){ //default only in modify admit screen
			form.setReturnReason(detResp.getReturnReason());
			form.setReturnReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RETURN_REASON, detResp.getReturnReason())); //Release Reason and return reason are same.
		}else{
			form.setReturnReason("");
		}
		form.setReturnStatus(detResp.getReturnStatus());
	}
	
	
	/**
	 * Populate the admission information to the admit release form
	 */
	public static void populateAdmissionInformation(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		//Admission information starts
	    //If the JUVENILE_FACILITY_ADMISSION_RELEASE TransfertoFacility attribute is populated, it should be used to supply the facility value for the Facility field.
        if(detResp.getTransferToFacility()!=null && !detResp.getTransferToFacility().equals("")){        
	        	form.setDetainedFacility(JuvenileFacilityHelper.getFacility(form.getFacilities(), detResp.getTransferToFacility()).getCode());
        }
        	
        //admit details
        //admit reasons drop down.
		form.setAdmitReasons(UIFacilityPopulationHelper.loadAdmitReasons());
		
		//go through admit reasons and get codeWithDetType
        if(detResp.getAdmitReason()!=null && !detResp.getAdmitReason().equals("")){
        	form.setReasonCode(JuvenileFacilityHelper.getAdmitCodeWithDetType(form.getAdmitReasons(),detResp.getAdmitReason()));
        }
        
        if(form.getReasonCode()!=null && !form.getReasonCode().isEmpty()){
        	JuvenileAdmitReasonsResponseEvent admitReasonByCode = JuvenileFacilityHelper.getAdmitReasonByCode(StringUtils.split(form.getReasonCode(), '|')[0]);
			form.setAdmitReasonStr(admitReasonByCode.getDescription());
			form.setAdmitReasonCd(admitReasonByCode.getCode());
        }
        
        form.setAdmitAuthority(detResp.getAdmitAuthority());
        form.setAdmitAuthorityDesc(detResp.getAdmitAuthorityDesc());
        form.setAdmitDateStr(DateUtil.dateToString(detResp.getAdmitDate(),DateUtil.DATE_FMT_1));
        form.setAdmitTime(detResp.getAdmitTime());
        form.setAdmitBy(detResp.getAdmittedByJPO());
        form.setAdmitByDesc(detResp.getAdmittedByJPODesc());
        	
        //current booking referral
        if(detResp.getCurrentReferral()!=null &&!detResp.getCurrentReferral().isEmpty()){
        	form.setCurrentReferral(detResp.getCurrentReferral());
        }
        
        //current supervision //hot fix bug : 51682
        if(detResp.getCurrentSupervisionNum()==null || (detResp.getCurrentSupervisionNum()!=null && detResp.getCurrentSupervisionNum().isEmpty())){ 
        	form.setCurrentSupervisionNum(JuvenileFacilityHelper.getMostRecentActiveCasefile(form.getReferrals(), form.getCasefiles(), form.getCurrentReferral()));
		}else{
			form.setCurrentSupervisionNum(detResp.getCurrentSupervisionNum());
		}
        
        //booking referral
        form.setBookingReferral(detResp.getReferralNumber());
        if(detResp.getBookingSupervisionNum()!=null && !detResp.getBookingSupervisionNum().isEmpty()){
        	form.setBookingSupervisionNum(detResp.getBookingSupervisionNum());
        }
        
        form.setTransferFromReferral(detResp.getReferralNumber());
        
	      //get TJPC placement Type.
	     if(form.getDetainedFacility()!=null && !form.getDetainedFacility().isEmpty()&& form.getFacilities()!=null && !form.getFacilities().isEmpty()){
	      	JuvenileFacilityResponseEvent facilityRespEvent =JuvenileFacilityHelper.getFacility(form.getFacilities(), form.getDetainedFacility());
	      	if(facilityRespEvent!=null){
	      		form.setPlacementType(facilityRespEvent.getJuvTJPCPlacementType());
	      		form.setDetainedFacilityStr(facilityRespEvent.getDescription());
	      	   	form.setDetainedFacility(form.getDetainedFacility());
	      	   	form.setLocationOneLabel(facilityRespEvent.getLocationOneLabel());
	      	   	form.setLocationTwoLabel(facilityRespEvent.getLocationTwoLabel());
	      	   	form.setLocationThreeLabel(facilityRespEvent.getLocationThreeLabel());
	      	   	 	}
	    }
        
        //location information
        form.setFloorLocation(detResp.getFloorNum());
	    form.setUnitLocation(detResp.getUnit());
	    form.setRoomLocation(detResp.getRoomNum());
	    form.setMultipleOccupancyUnit(detResp.getMultipleOccupyUnit());
	    form.setLockerLocation(detResp.getLockerNumber());
	    form.setValuablesReceipt(detResp.getReceiptNumber());
	    form.setSecureStatus(detResp.getSecureStatus());
	    form.setAdmitBy(detResp.getAdmittedByJPO());
	    form.setReasonForChange(detResp.getChangeExplanation());
	    
	    if(detResp.getSecureStatus()==null || (detResp.getSecureStatus()!=null && detResp.getSecureStatus().isEmpty())){
	    	form.setSecureStatus("S");
        	form.setSecureStatusDesc("Secure");
        }
	    
	    //ChangeFlags
	    form.setSecureStatusChangeFlag(detResp.isSecureIndicatorChangeFlag());
	    form.setOtherChangeFlag(detResp.isOtherChangeFlag());
	    
	    form.setAdmitReasonChangeFlag(detResp.isReasonChangeFlag());
	    form.setLocationChangeFlag(detResp.isLocationChangeFlag());
	    form.setTjjdFacilityId(detResp.getTjjdFacilityId());
	    form.setTjjdFundingSrc(detResp.getTjjdFundingSrc());
		
		//form.setAdmissionComments(detResp.getComments()); #51737 changes
		// adding detention ID to the form special attention - history changes.
		form.setDetentionId(detResp.getDetentionId());
	}
	
	/**
	 * Populate the escape information to the admit release form
	 * @param form
	 * @param detResp
	 */
	public static void populateEscapeInformation(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		form.setEscapeCodes(JuvenileFacilityHelper.loadActiveEscapeCodes());
		form.setEscapeStatus(form.getFacilityStatus());
		form.setEscapeFrom(detResp.getEscapeCode());
    	form.setNumOfEscapeAttempts(detResp.getEscapeAttempts());
    	form.setEscapeComments(detResp.getEscapeAttemptComments());
    	form.setEscapeFromDesc(detResp.getEscapeCodeDesc());
	}
	
	/**
	 * Populates the detention hearing information 
	 * @param form
	 * @param detResp
	 */
	public static void populateDetentionHearing(AdmitReleaseForm form,JuvenileDetentionFacilitiesResponseEvent detResp){
		form.setDetainedDate(DateUtil.dateToString(detResp.getDetainedDate(),DateUtil.DATE_FMT_1));
		//Bug #69605 - detained date was null, changed to admit date
		//Bug #69605 - changed detained date to next hearing date as per requirements
		if(form.getNextHearingDate()!=null && !form.getNextHearingDate().equals(""))
		{
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			Date nextHearingDate = new Date();
			try {
				nextHearingDate = format1.parse(form.getNextHearingDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	DocketEventResponseEvent docRespEvent = JuvenileFacilityHelper.getCalendarDetention(detResp.getJuvNum(),detResp.getReferralNumber(),(format2.format(nextHearingDate)).toString());
	    	if(docRespEvent!=null){
	    		form.setCourtId(docRespEvent.getCourt()); //setCourt
	    	}
		}
    	form.setFacilitySeqNum(String.valueOf(detResp.getFacilitySequenceNumber()));
    	
	}
	
	/**
	 * Populate admit release information from profile.
	 * @param form
	 */
	public static void populateProfileReferralDetails(AdmitReleaseForm form){
		//set the booking referral PIA status
        Iterator<JuvenileProfileReferralListResponseEvent>  profileRespItr =    JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNum()).iterator();
        while(profileRespItr.hasNext()){
        	JuvenileProfileReferralListResponseEvent profileRespEvent = profileRespItr.next();
	    	 if(profileRespEvent!=null && profileRespEvent.getReferralNumber().equalsIgnoreCase(form.getBookingReferral()))
	    	 {
	    		   String PIAStatus = profileRespEvent.getPiaStatus();
	    		   form.setBookingRefPIAStatus(PIAStatus);
	    		   form.setReferralOfficers(profileRespEvent.getReferralOfficer());
	    		   form.setReferralSource(profileRespEvent.getReferralSource());
	    		   form.setPetition(profileRespEvent.getPetitionNumber());
	    		   break;
	    	 }
        }
	}
	
	/**
	 * Create activity for facility admission changes.
	 * @param currentSupervisionNum
	 * @param categoryId
	 * @param typeId
	 * @param codeId
	 * @param title
	 * @param comments
	 */
	public static void createActivity(String currentSupervisionNum,String categoryId,String typeId,String codeId,String title,String comments,String detentionId,Date activityDate,String time){
		CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);
		reqEvent.setSupervisionNumber(currentSupervisionNum);
		reqEvent.setActivityDate(activityDate);
		reqEvent.setActivityCategoryId(categoryId);
		reqEvent.setActivityTypeId(typeId);
		reqEvent.setActivityCodeId(codeId);
		reqEvent.setActivityTitle(title);
		reqEvent.setComments(comments);
		//add the code for 103972
		reqEvent.setDetentionId(detentionId);
		//add activity time
		reqEvent.setTime(time);
		MessageUtil.postRequest(reqEvent);
	}
	
	/**
	 * referralHasCasefiles
	 * @param referrals
	 * @param referralNum
	 * @return boolean
	 */
	public static boolean referralHasCasefiles(Collection<JuvenileProfileReferralListResponseEvent> referrals, String referralNum)
	{
		Iterator<JuvenileProfileReferralListResponseEvent> iter = referrals.iterator();
		while(iter.hasNext())
	    {
	       JuvenileProfileReferralListResponseEvent resp =  iter.next();
	       return resp.isHasCasefiles();
	    }
		return false;
	}
	
	/**
	 * getMostRecentActiveCasefile
	 * @param casefiles
	 * @return Strings
	 */
	public static String getMostRecentActiveCasefile( Collection<JuvenileProfileReferralListResponseEvent> referrals, Collection<JuvenileCasefileSearchResponseEvent> casefiles, String currentReferral)
	{
		String recentCasefileId="";
		boolean found=false;
		if(currentReferral!=null && !currentReferral.equals(""))
		{
			if(referrals!=null){
				Iterator<JuvenileProfileReferralListResponseEvent> iter = referrals.iterator();
				Date checkDate= null;
				int i=0;
				while(iter.hasNext())
				{
					JuvenileProfileReferralListResponseEvent resp = iter.next();
					if(resp.getReferralNumber().equals(currentReferral))
					{
						Iterator casefilesIter = resp.getCasefiles().iterator();
						while(casefilesIter.hasNext())
						{							
							JuvenileCasefileResponseEvent casefileResp = (JuvenileCasefileResponseEvent) casefilesIter.next();
							if(casefileResp.getCaseStatus()!=null && casefileResp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
							{
								found=true;
								if(i==0 || ( casefileResp.getActivationDate()!=null && casefileResp.getActivationDate().after(checkDate)))
								{
									checkDate=casefileResp.getActivationDate();
									recentCasefileId=casefileResp.getSupervisionNum();
								}	
								i++;
							}
						}
					}
				}
			}
		}
			
		if(!found)
		{		
			if(casefiles!=null){
				Iterator<JuvenileCasefileSearchResponseEvent> iter = casefiles.iterator();
				Date checkDate= null;
				int i=0;
				while(iter.hasNext())
				{
					JuvenileCasefileSearchResponseEvent resp = iter.next();
					if(resp.getCaseStatus()!=null && resp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
					{
						if(i==0 || ( resp.getActivationDate()!=null && resp.getActivationDate().after(checkDate)))
						{
							checkDate=resp.getActivationDate();
							recentCasefileId=resp.getSupervisionNum();
						}	
						i++;
					}
				}
			}
		}
		
		return recentCasefileId;
	}
	
	/**
	 * SetSAReason
	 * @param saRreasonCodes
	 * @param admitForm
	 */
	public static String setSAReasonId(String[] saRreasonCodes, Collection<CodeResponseEvent> saReasons) {

		StringBuffer saRreasonId = new StringBuffer();
		if (saRreasonCodes != null) {
			for (int i = 0; i < saRreasonCodes.length; i++) {
				 Iterator<CodeResponseEvent> saReasonsIter = saReasons.iterator();
				 while(saReasonsIter.hasNext())
				 {
					 CodeResponseEvent codeResp = saReasonsIter.next();
					 if(saRreasonCodes[i].equalsIgnoreCase(codeResp.getDescription()))
					 {		
						 saRreasonId.append(codeResp.getCode());
					 }
				 }
				if (i != saRreasonCodes.length - 1) {
					saRreasonId.append(",");
				}
			}
		} 
		return saRreasonId.toString();
	}
	
	/**
	 * DB format sample // HH:MM:00.0000000
	 * Get HH:MM for release,return and admit time .
	 * @param time
	 * @return hh:mm
	 */
	public static String getHoursMinsFromTime(String time){
		if(time!=null && !time.equals("")){
			int end =0;
			String[] getTime = StringUtils.split(time, '.');
			if(getTime!=null && getTime.length>0){
				end = getTime[0].lastIndexOf(":");
			}
			if(end!=-1){
				time = time.substring(0, end);
			}
			return time;
		}
		return null;
	}
	
	/**
	 * fetchOffenseResp
	 * @param offense
	 * @param juvenileNum
	 * @return JJSOffenseResponseEvent
	 */
	 public static JJSOffenseResponseEvent fetchOffenseResp(JJSOffense offense,String juvenileNum,String refNum)
	    {
	       	JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();
	    	 //U.S.32320
	       	boolean isTransferredOffense=false;
			List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseResp = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
			 if(offense.getOffenseCode()!=null && offense.getOffenseCode().getSeveritySubtype()!=null){ //null check added as part of 88723
			  if(offense.getOffenseCode().getSeveritySubtype().equals("T")){
				  if(transferredOffenseResp!=null && !transferredOffenseResp.isEmpty()){ // if there is a tranferred offense added to the table.
					Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
						while (transferredOffenseIter.hasNext())
						{
							JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
							//check for the referral number too bug fix for 147175
							if(transferredOffense!=null&&transferredOffense.getReferralNum().equalsIgnoreCase(refNum))
						    {
							   	resp.setOffenseDescription(transferredOffense.getOffenseShortDesc());
							    JuvenileOffenseCode juvOffenseCode = JuvenileOffenseCode.find("offenseCode",transferredOffense.getOffenseCode());
							    if(offense.getOffenseCode()!=null){
							    	resp.setSeveritySubtype(juvOffenseCode.getSeveritySubtype());
							    	resp.setOffenseLevelId(transferredOffense.getCategory());
							    }
								resp.setOffenseCodeId(transferredOffense.getOffenseCode());
								isTransferredOffense = true;
								break;
						    }
							
						}
					}
				  	if(!isTransferredOffense){
						resp.setOffenseLevelId(offense.getCatagory());
						if(offense.getOffenseCode()!=null){
							resp.setOffenseDescription(offense.getOffenseCode().getShortDescription());
					    	resp.setSeveritySubtype(offense.getOffenseCode().getSeveritySubtype());
						}
					    resp.setOffenseCodeId(offense.getOffenseCodeId());
					}
				}
			  	else
				{
					resp.setOffenseLevelId(offense.getCatagory());
					resp.setOffenseCodeId(offense.getOffenseCodeId());
					if(offense.getOffenseCode()!=null)
				    {
					   	resp.setOffenseDescription(offense.getOffenseCode().getShortDescription());
					   	resp.setSeveritySubtype(offense.getOffenseCode().getSeveritySubtype());
				    }
				}
			 }
			if(resp.getSeveritySubtype()==null){
				resp.setSeveritySubtype("");
			}
	    	return resp;
	    }

	/**
	 * 	getOffenseResp
	 * @param offense
	 * @return JJSOffenseResponseEvent
	 */
   public static JJSOffenseResponseEvent getOffenseResp(JJSOffense offense) {
		
		JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();
		resp.setOffenseLevelId(offense.getCatagory());
		
		if (offense.getOffenseCode() != null) {
			resp.setOffenseDescription(offense.getOffenseCode().getShortDescription());
			resp.setSeveritySubtype(offense.getOffenseCode().getSeveritySubtype());
		}
		
		if (offense.getOffenseCodeId() != null){
			resp.setOffenseCodeId(offense.getOffenseCodeId());
		}
		return resp;
	}		
   
	/**
	 * getCurrentReferrals
	 * @param form
	 * @param briefingDetailsForm
	 * @return List<JuvenileProfileReferralListResponseEvent>
	 */
	public static List<JuvenileProfileReferralListResponseEvent> getCurrentReferrals(JuvenileBriefingDetailsForm form,AdmitReleaseForm aForm) {
		ArrayList<JuvenileProfileReferralListResponseEvent> referralList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
		List<JuvenileProfileReferralListResponseEvent>  profileRespListDetails = (List<JuvenileProfileReferralListResponseEvent>) JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNum());
		
		 JuvenileDetentionFacilitiesResponseEvent previousAdmissionInfo =null;
		 boolean hasMoreRecs = JuvenileFacilityHelper.checkIfJJSFacilityHasMoreThanOneRecord(form.getJuvenileNum());
		 if(hasMoreRecs && form.getAdmissionInfo().getFacilitySequenceNumber()!=null){
			Collection<JuvenileDetentionFacilitiesResponseEvent> JuvenileDetentionFacilitiesResponseEvents =  JuvenileFacilityHelper.getJuvFacilityDetails(form.getJuvenileNum(),String.valueOf(Integer.parseInt(form.getAdmissionInfo().getFacilitySequenceNumber())-20));
			if(JuvenileDetentionFacilitiesResponseEvents!=null){
				Iterator<JuvenileDetentionFacilitiesResponseEvent> JuvenileDetentionFacilitiesResponseEventItr = JuvenileDetentionFacilitiesResponseEvents.iterator();
				while(JuvenileDetentionFacilitiesResponseEventItr.hasNext()){
					previousAdmissionInfo = JuvenileDetentionFacilitiesResponseEventItr.next();
					break;
				}
			}
		 }
		 
		
		if(profileRespListDetails!=null){
			Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = profileRespListDetails.iterator();
			while (juvProfResItr.hasNext()){
				JuvenileProfileReferralListResponseEvent profileRespEvent = juvProfResItr.next();
				if(profileRespEvent!=null){
					 if(profileRespEvent.getReferralNumber()!=null && profileRespEvent.getCloseDate()==null ){
						 if(!profileRespEvent.getMostSevereOffense().getSeveritySubtype().equals("R")){
							 	GetCasefilesForReferralsEvent referralWithCasefiles = (GetCasefilesForReferralsEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILESFORREFERRALS);
							   	referralWithCasefiles.setJuvenileNum(form.getJuvenileNum());
							   	referralWithCasefiles.setReferralNum(profileRespEvent.getReferralNumber());

						 		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
						 		dispatch.postEvent( referralWithCasefiles );
						 		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
						 		MessageUtil.processReturnException( compositeResponse );
						 		
						 		JuvenileProfileReferralListResponseEvent casefileProfileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
						 		if(casefileProfileRespEvent.getCasefileReferrals()!=null)
						 		{
							  		Iterator<JuvenileCasefileReferral> casefilesIter = casefileProfileRespEvent.getCasefileReferrals().iterator();
									while(casefilesIter.hasNext())
							  	    {
							  	    	JuvenileCasefileReferral casefileReferral = (JuvenileCasefileReferral)casefilesIter.next();
							  	    	if(casefileReferral!=null && casefileReferral.getCaseFileId()!=null){ 
						    	    	    JuvenileCasefile casefile = casefileReferral.getCaseFile();
							  	    		if(casefile!=null){
							  	    			if(casefile.getCaseStatusId().equalsIgnoreCase(UIConstants.ACTIVE) ||casefile.getCaseStatusId().equalsIgnoreCase("A") ){
							  	    				form.setHasActiveCasefiles(true);
							  	    			}
							  	    			profileRespEvent.setHasCasefiles(true);
							  	    			JuvenileCasefileResponseEvent casefileRespEvt= JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
							  	    			profileRespEvent.getCasefiles().add(casefileRespEvt);
							  	    		}
							  	    	}
							  	    }
									
						 		}
						 		if(previousAdmissionInfo!=null && previousAdmissionInfo.getReleaseReason()!=null && previousAdmissionInfo.getReleaseReason().equals("N") 
										 && previousAdmissionInfo.getReleaseTo()!=null && previousAdmissionInfo.getReleaseTo().equals("NTS")){
										 aForm.setCurrentReferral(profileRespEvent.getReferralNumber()+ "-" +profileRespEvent.getMostSevereOffense().getSeveritySubtype());
										 referralList.add( profileRespEvent);
										 return referralList;
						 		 }
						 	}
					 }
					 referralList.add(profileRespEvent);
				}
			}
		}
		 Collections.sort((List<JuvenileProfileReferralListResponseEvent>)referralList, Collections.reverseOrder());
		 return referralList;
	}
	
	/**
	 * buildReferralTransferRecord
	 * @param form
	 * @return List<JuvenileProfileReferralListResponseEvent>
	 */
	public static List<JuvenileProfileReferralListResponseEvent> buildReferralTransferRecord(JuvenileBriefingDetailsForm form){
		int activeReferralCounter = 0;
	   	
		Date currentDetentionAdmitDate = form.getAdmissionInfo().getAdmitDate();
		String bookingSupervisionNum = form.getAdmissionInfo().getBookingSupervisionNum();
		String bookingReferral = form.getAdmissionInfo().getReferralNumber();
		ArrayList<JuvenileProfileReferralListResponseEvent> referralList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
		
		List<JuvenileProfileReferralListResponseEvent>  juvReferralList =    (List<JuvenileProfileReferralListResponseEvent>) JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNum());
		if(juvReferralList!=null){
			Iterator<JuvenileProfileReferralListResponseEvent> referralListItr = juvReferralList.iterator();
			while(referralListItr.hasNext()){
				JuvenileProfileReferralListResponseEvent referralResp = referralListItr.next();
				if(referralResp!=null && referralResp.getReferralNumber().equalsIgnoreCase(bookingReferral))
		    	{
					if(referralResp.getCloseDate()!=null){
						 form.setRefTransferMesg("Booking Referral is closed.Detention record cannot be transferred to a different referral.");
						 return null;						 
					}
					break;
				}
			}
		}
		
		
		/**
		 * If {BookingSupervisionNumber} is populated, display the other active referrals {where JUVENILE_JJS_REFERRAL.CloseDate is null} in the same casefile as the Booking Referral.  
		 */
		if(bookingSupervisionNum!=null && !bookingSupervisionNum.isEmpty()){
			List<JuvenileCasefileReferralResponseEvent> casefileRespEvts = getReferralsByCasefileId(form.getJuvenileNum(), bookingSupervisionNum); // get all referrals for that casefile
			
			List<JuvenileCasefileReferralResponseEvent> updatedCasefileRespEvts = new ArrayList<JuvenileCasefileReferralResponseEvent>();
			HashMap<String,JuvenileCasefileReferralResponseEvent> uniqueCasefileRespEvts = new HashMap<String, JuvenileCasefileReferralResponseEvent>();
			
			for(int i=0;i<casefileRespEvts.size();i++)
			{
			    if(!updatedCasefileRespEvts.contains(casefileRespEvts.get(i).getReferralNumber()))
			    {
				uniqueCasefileRespEvts.put(casefileRespEvts.get(i).getReferralNumber(), casefileRespEvts.get(i));
			    }
			}
			
			Iterator<Map.Entry<String,JuvenileCasefileReferralResponseEvent>> itr = uniqueCasefileRespEvts.entrySet().iterator();
			
			while(itr.hasNext())
			{
			    Map.Entry<String,JuvenileCasefileReferralResponseEvent> entry = itr.next();
			    updatedCasefileRespEvts.add(entry.getValue());
			    
			}
			
			casefileRespEvts = updatedCasefileRespEvts;
				

			
			if(casefileRespEvts!=null){
				 Iterator<JuvenileCasefileReferralResponseEvent> casefilesIter = casefileRespEvts.iterator();
				 while(casefilesIter.hasNext()) //for each referral get the referral details.
				 {
					 JuvenileCasefileReferralResponseEvent casefileReferralResp = casefilesIter.next();
					 if(casefileReferralResp!=null)
			    	 {
						JuvenileProfileReferralListResponseEvent referralResponse = getJuvReferralDetails(form.getJuvenileNum(),casefileReferralResp.getReferralNumber());//getReferral details.
						if(referralResponse!=null){
				  	   		if(referralResponse.getReferralNumber()!=null && referralResponse.getCloseDate()==null )
				  	   		{
				  	   			activeReferralCounter++;
				  	   			if(!referralResponse.getReferralNumber().equalsIgnoreCase(bookingReferral))//excluding the current booking referral.
				  	   			{
				  	   				if(referralResponse.getReferralDate().equals(currentDetentionAdmitDate) ||  referralResponse.getReferralDate().before(currentDetentionAdmitDate))
				  	   				{
				  	   					 JuvenileCasefileResponseEvent casefileRespEvt= JuvenileCaseHelper.getJuvenileCasefileResponse(casefileReferralResp.getCaseFile());
				  	   					 referralResponse.getCasefiles().add(casefileRespEvt);
										 referralList.add( referralResponse); //add it to the referral list.
									}
								}
				  	   		 }
					    }
					}
			    } //end of while(1)
			}
		}else{ //If BookingSupervisionNumber is not populated,Display all of the juvenile�s referral numbers in descending order, excluding the current BookingReferral
			if(juvReferralList!=null){
				Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = juvReferralList.iterator();
					while(juvProfResItr.hasNext()){
						JuvenileProfileReferralListResponseEvent referralResp = juvProfResItr.next();
						if(referralResp!=null){
							if(!referralResp.getReferralNumber().equalsIgnoreCase(bookingReferral)){//excluding the current booking referral.
								GetCasefilesForReferralsEvent referralWithCasefiles = (GetCasefilesForReferralsEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILESFORREFERRALS);
							   	referralWithCasefiles.setJuvenileNum(form.getJuvenileNum());
							   	referralWithCasefiles.setReferralNum(referralResp.getReferralNumber());

						 		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
						 		dispatch.postEvent( referralWithCasefiles );
						 		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
						 		MessageUtil.processReturnException( compositeResponse );
						 		JuvenileProfileReferralListResponseEvent casefileProfileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
						 		if(casefileProfileRespEvent.getCasefileReferrals()!=null)
						 		{
							  		Iterator<JuvenileCasefileReferral> casefilesIter = casefileProfileRespEvent.getCasefileReferrals().iterator();
									while(casefilesIter.hasNext())
							  	    {
							  	    	JuvenileCasefileReferral casefileReferral = (JuvenileCasefileReferral)casefilesIter.next();
							  	    	if(casefileReferral!=null && casefileReferral.getCaseFileId()!=null){ 
						    	    	    JuvenileCasefile casefile = casefileReferral.getCaseFile();
							  	    		if(casefile!=null){
							  	    			referralResp.setHasCasefiles(true);
							  	    			if(casefile.getCaseStatusId().equalsIgnoreCase(UIConstants.ACTIVE) ||casefile.getCaseStatusId().equalsIgnoreCase("A") ){
							  	    				form.setHasActiveCasefiles(true);
							  	    			}
							  	    			JuvenileCasefileResponseEvent casefileRespEvt= JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
							  	    			referralResp.getCasefiles().add(casefileRespEvt);
							  	    		}
							  	    	}
							  	    }
						 		}
						 		if(!referralResp.getCasefiles().isEmpty()){
						 			Collections.sort((List<JuvenileCasefileResponseEvent>)casefileProfileRespEvent.getCasefiles(), Collections.reverseOrder());
						 			referralList.add(referralResp); 
						 		}
							}
					    }
					}
				}
			}

		if(bookingSupervisionNum!=null && !bookingSupervisionNum.isEmpty() && activeReferralCounter==0){
			form.setRefTransferMesg("Referrals have been closed, facility record cannot be transferred to a different referral.");
		}else if(bookingSupervisionNum!=null && !bookingSupervisionNum.isEmpty() && activeReferralCounter<2){
			form.setRefTransferMesg("At least two active referrals (no close date) should be associated to the juvenile casefile.");
		}
		if(referralList.size()==0){
				form.setRefTransferMesg("Referral Transfer cannot be completed. No valid referrals available.");
		}
		Collections.sort((List<JuvenileProfileReferralListResponseEvent>)referralList, Collections.reverseOrder());
		return referralList;
	}
  
  /**
   * getJuvReferralDetails
   * @param juvNum
   * @param referralNum
   * @return JuvenileProfileReferralListResponseEvent
   */
  public static JuvenileProfileReferralListResponseEvent getJuvReferralDetails(String juvNum,String referralNum){
	  GetJJSReferralEvent referralEvent = (GetJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSREFERRAL);
	  referralEvent.setJuvenileNum(juvNum);
	  referralEvent.setReferralNum(referralNum);
	  
	  IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
	  dispatch.postEvent(referralEvent);
	  CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
	  MessageUtil.processReturnException( compositeResponse );
	  JuvenileProfileReferralListResponseEvent profileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
	  return profileRespEvent;
  }
  /**
   * getJuvReferralDetails without filter for purge seal task 152221
   * @param juvNum
   * @param referralNum
   * @return JuvenileProfileReferralListResponseEvent
   */
  public static JuvenileProfileReferralListResponseEvent getJuvReferralDetailsWithoutFilter(String juvNum,String referralNum){
      	  GetJJSReferralWithoutFilterEvent referralEvent = (GetJJSReferralWithoutFilterEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSREFERRALWITHOUTFILTER);
	  referralEvent.setJuvenileNum(juvNum);
	  referralEvent.setReferralNum(referralNum);
	  
	  IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
	  dispatch.postEvent(referralEvent);
	  CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
	  MessageUtil.processReturnException( compositeResponse );
	  JuvenileProfileReferralListResponseEvent profileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
	  return profileRespEvent;
  }
  
  /**
   * Get the spl attn values for that juvenile and detention id.
   * @param juvenileNumber
   * @param detentionId
   * @return Collection<JuvenileFacilitySplAttnReasonResponseEvent> 
   */
  public static String getMostRecentSplAttnReasonComments(String juvenileNumber,String detentionId){
		List<JuvenileFacilitySplAttnReasonResponseEvent> respEvtList = new ArrayList<JuvenileFacilitySplAttnReasonResponseEvent>();
	  //get the Special Attention Reason Other Comments from the new table	
		GetJuvenileFacilitySplAttnReasonCommentsEvent commEvent = (GetJuvenileFacilitySplAttnReasonCommentsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYSPLATTNREASONCOMMENTS);
		commEvent.setJuvenileNumber(juvenileNumber);
		commEvent.setDetentionId(detentionId);
		String commentsWithoutTimestamp="";
		Iterator<JJSSplAttnReasonComments> splAttentiontr = JJSSplAttnReasonComments.findAll(commEvent);
		while (splAttentiontr.hasNext()) {
			JJSSplAttnReasonComments splAttnReasonComments = (JJSSplAttnReasonComments)splAttentiontr.next();	
			if(splAttnReasonComments.getComments()!=null && !splAttnReasonComments.getComments().isEmpty())
				commentsWithoutTimestamp = (StringUtils.split(splAttnReasonComments.getComments(), '[')[0]).trim();
			JuvenileFacilitySplAttnReasonResponseEvent responseEvent = new JuvenileFacilitySplAttnReasonResponseEvent();
			responseEvent.setEntryDate( DateUtil.dateToString(splAttnReasonComments.getCreateDate(),DateUtil.DEFAULT_DATE_FMT));
			responseEvent.setEntryTime(DateUtil.getHHMMSSWithColonFromDate(splAttnReasonComments.getCreateDate()));
			//responseEvent.setComments(splAttnReasonComments.getComments());
			responseEvent.setComments(commentsWithoutTimestamp);
			responseEvent.setDetentionId(splAttnReasonComments.getDetentionId());
			responseEvent.setCreateUser(splAttnReasonComments.getCreateUserID());
			responseEvent.setCreateDate(splAttnReasonComments.getCreateDate());
			respEvtList.add(responseEvent);
		}
		Collections.sort((List<JuvenileFacilitySplAttnReasonResponseEvent>)respEvtList,Collections.reverseOrder(new Comparator<JuvenileFacilitySplAttnReasonResponseEvent>() {
			@Override
			public int compare(JuvenileFacilitySplAttnReasonResponseEvent evt1, JuvenileFacilitySplAttnReasonResponseEvent evt2) {
				return evt1.getCreateDate().compareTo(evt2.getCreateDate());
			}
		}));
		if(!respEvtList.isEmpty()){
			return respEvtList.get(0).getComments(); // return the last comment always.
		}
	
		return "";
  }
  
  /**
   * Get the spl attn values for all juveniles.
   * @param juvenileNumber
   * @param detentionId
   * @return Collection<JuvenileFacilitySplAttnReasonResponseEvent> 
   */
  public static Collection<JuvenileFacilitySplAttnReasonResponseEvent> getAllJuvFacSplAttnReasonComments(String juvenileNumber){
		//get the Special Attention Reason Other Comments from the new table	
		GetAllJuvFacSplAttnReasonCommentsEvent commEvent = (GetAllJuvFacSplAttnReasonCommentsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETALLJUVFACSPLATTNREASONCOMMENTS);
		commEvent.setJuvenileNumber(juvenileNumber);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(commEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileFacilitySplAttnReasonResponseEvent> juvFacilitySplAttnReasonRespEvt =
			MessageUtil.compositeToCollection(responses, JuvenileFacilitySplAttnReasonResponseEvent.class);
		return juvFacilitySplAttnReasonRespEvt;
  }
  
  /**
   * Get the Facility Admission values for all juveniles.
   * @param juvenileNumber
   * @param detentionId
   * @return Collection<JuvenileFacilityAdmissionCommentsResponseEvent> 
   */
  public static Collection<JuvenileFacilityAdmissionCommentsResponseEvent> getAllJuvFacAdmissionComments(String juvenileNumber){
		//get the facility Admission Comments from the new table	
	  	GetAllJuvFacAdmissionCommentsEvent commEvent = (GetAllJuvFacAdmissionCommentsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETALLJUVFACADMISSIONCOMMENTS);
		commEvent.setJuvenileNumber(juvenileNumber);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(commEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileFacilityAdmissionCommentsResponseEvent> juvFacilityAdmissionRespEvt =
			MessageUtil.compositeToCollection(responses, JuvenileFacilityAdmissionCommentsResponseEvent.class);
		return juvFacilityAdmissionRespEvt;
  }
  
	/**
	 * getFacilityAdmissionComments
	 * @param juvenileNum
	 * @param detentionId
	 * @return String
	 */
	public static String getFacilityAdmissionComments(String juvenileNum,String detentionId){
		List<JuvenileFacilityAdmissionCommentsResponseEvent> respEvtList = new ArrayList<JuvenileFacilityAdmissionCommentsResponseEvent>();
		if(detentionId!=null && !detentionId.isEmpty()){
			GetJuvenileFacilityAdmissionCommentsEvent facilityAdmissionCommentsEvt = new GetJuvenileFacilityAdmissionCommentsEvent();
			facilityAdmissionCommentsEvt.setJuvenileNumber(juvenileNum);
			facilityAdmissionCommentsEvt.setDetentionId(detentionId);
			
			Iterator<JJSFacilityAdmissionComments> facAdmissionCommentsItr = JJSFacilityAdmissionComments.findAll(facilityAdmissionCommentsEvt);
			while (facAdmissionCommentsItr.hasNext()) {
				JJSFacilityAdmissionComments admissionComments = facAdmissionCommentsItr.next();	
				JuvenileFacilityAdmissionCommentsResponseEvent responseEvent = new JuvenileFacilityAdmissionCommentsResponseEvent();
				responseEvent.setEntryDate( DateUtil.dateToString(admissionComments.getCreateDate(),DateUtil.DEFAULT_DATE_FMT));
				responseEvent.setEntryTime(DateUtil.getHHMMSSWithColonFromDate(admissionComments.getCreateDate()));
				responseEvent.setComments(admissionComments.getComments());
				responseEvent.setDetentionId(admissionComments.getDetentionId());
				respEvtList.add(responseEvent);
			}
		//	Collections.sort(respEvtList,Collections.reverseOrder()); doesnt work when comments entered multiple times on the same day.
			if(!respEvtList.isEmpty()){
				return respEvtList.get(respEvtList.size()-1).getComments(); // return the last comment always.
			}
		}
		return "";
	}
	
  
  	/**
  	 * getOfficerProfiles
  	 * @param attrName
  	 * @param attrValue
  	 * @return
  	 */
	public static List<OfficerProfileResponseEvent> getOfficerProfiles(String attrName,String attrValue) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		event.setAttributeName(attrName);
		event.setAttributeValue(attrValue);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		List<OfficerProfileResponseEvent> officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
		
		if (officerprofiles != null && officerprofiles.size() > 0)
		{
			Collections.sort(officerprofiles);
			
		}
		return officerprofiles;
	}
	
	/**
	 * getAdmitDateWithColon
	 * @param admitTime
	 * @return
	 */
	public static String getDateWithColon(String time){
		String timeWithColon = "";
		if(time!=null && !time.isEmpty()){
			String hour = time.substring(0, 2);
			String sec = time.substring(2, 4);
			//16:57:00.0000000 sample
			timeWithColon=hour+":"+sec;
		}
		return timeWithColon;
	}
	
	/**
	 * stripColonInAdmitDate
	 * @param admitTime
	 * @return
	 */
	public static String stripColonInDate(String time){
		String timeWithColon = "";
		if(time!=null && !time.isEmpty()){
			String[]dayTime = time.split(":");
			if(dayTime.length >1){
				timeWithColon=dayTime[0]+dayTime[1];
			}
		}
		return timeWithColon;
	}
	
	/**
	 * Brings only active and pending casefiles.
	 * @param juvNum
	 * @param refNum
	 * @return
	 */
	public static JuvenileProfileReferralListResponseEvent getCasefilesFromReferral(String juvNum, String refNum)
	{
		 GetCasefilesForReferralsEvent referralWithCasefiles = (GetCasefilesForReferralsEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILESFORREFERRALS);
		 referralWithCasefiles.setJuvenileNum(juvNum);
		 referralWithCasefiles.setReferralNum(refNum);

	 	 IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
	 	 dispatch.postEvent( referralWithCasefiles );
	 	 CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
	 	 MessageUtil.processReturnException( compositeResponse );
	 	 JuvenileProfileReferralListResponseEvent casefileProfileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
	 	 return casefileProfileRespEvent;
	}
	
	/** getAllCasefilesForJuvNumRefNum
	 * @param juvNum
	 * @param refNum
	 * @return
	 */
	public static JuvenileProfileReferralListResponseEvent getAllCasefilesForJuvNumRefNum(String juvNum, String refNum)
	{
	    GetAllCasefilesForReferralsEvent referralWithCasefiles = (GetAllCasefilesForReferralsEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETALLCASEFILESFORREFERRALS);
		 referralWithCasefiles.setJuvenileNum(juvNum);
		 referralWithCasefiles.setReferralNum(refNum);

	 	 IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
	 	 dispatch.postEvent( referralWithCasefiles );
	 	 CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
	 	 MessageUtil.processReturnException( compositeResponse );
	 	 JuvenileProfileReferralListResponseEvent casefileProfileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
	 	 return casefileProfileRespEvent;
	}
	/** getAllCasefilesForJuvNumRefNum
	 * @param juvNum
	 * @param refNum
	 * @return
	 */
	public static JuvenileProfileReferralListResponseEvent getAllCasefilesForJuvNum(String juvNum)
	{
	    GetAllCasefilesForJuvenileEvent referralWithCasefiles = (GetAllCasefilesForJuvenileEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETALLCASEFILESFORJUVENILE);
		 referralWithCasefiles.setJuvenileNum(juvNum);		 

	 	 IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
	 	 dispatch.postEvent( referralWithCasefiles );
	 	 CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
	 	 MessageUtil.processReturnException( compositeResponse );
	 	 JuvenileProfileReferralListResponseEvent casefileProfileRespEvent = (JuvenileProfileReferralListResponseEvent)MessageUtil.filterComposite( compositeResponse, JuvenileProfileReferralListResponseEvent.class );
	 	 return casefileProfileRespEvent;
	}
	/**
	 * getReferralsByCasefileId
	 * @param juvNum
	 * @param refNum
	 * @return
	 */
	public static  List<JuvenileCasefileReferralResponseEvent> getReferralsByCasefileId(String juvNum, String casefileId)
	{
		 GetReferralsByCasefileIdEvent casefileEvt = (GetReferralsByCasefileIdEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETREFERRALSBYCASEFILEID);
		 casefileEvt.setJuvenileNum(juvNum);
		 casefileEvt.setCaseFileId(casefileId);
		 CompositeResponse response = MessageUtil.postRequest(casefileEvt);
			
		 List<JuvenileCasefileReferralResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileReferralResponseEvent.class);
	 	 return casefiles;
	}
	/**
	 * 
	 * @param casefiles
	 * @param refDate
	 * @return
	 */
	public static JuvenileCasefileSearchResponseEvent getCasefileCreatedClosestToRefDt(Collection casefiles, Date refDate)
	{
		Iterator<JuvenileCasefileSearchResponseEvent> casefilesIter = casefiles.iterator();
		JuvenileCasefileSearchResponseEvent tempResp = new JuvenileCasefileSearchResponseEvent();
		while(casefilesIter.hasNext())
		{
			JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) casefilesIter.next();
			if(resp.getCaseStatus()!=null && (resp.getCaseStatus().equalsIgnoreCase("A") || resp.getCaseStatus().equalsIgnoreCase("ACTIVE")))
			{
				if(tempResp.getCasefileCreateDate()==null)
				{					
						tempResp=resp;
				}
				else if(tempResp.getCasefileCreateDate().before(resp.getCasefileCreateDate()) && (resp.getCasefileCreateDate().before(refDate) || resp.getCasefileCreateDate().equals(refDate)))
				{
					tempResp=resp;
				}
			}
			
		}
		return tempResp;
		
	}
	
	public static Collection getCasefiles(String juvenileNum)
	{
		SearchJuvenileCasefilesEvent searchForCasefiles = (SearchJuvenileCasefilesEvent) EventFactory
				.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
		searchForCasefiles.setSearchType("JNUM");
		searchForCasefiles.setJuvenileNum(juvenileNum);
		CompositeResponse response = MessageUtil.postRequest(searchForCasefiles);
		
		Collection retCasefile;
		ErrorResponseEvent error = 
				(ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if (error == null) {
			List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
			return casefiles;
			
		}
		return null;
	}
	
	
	public static void hasActiveOrPendingCasefiles(JuvenileBriefingDetailsForm form)
	{
		SearchJuvenileCasefilesEvent searchForCasefiles = (SearchJuvenileCasefilesEvent) EventFactory
				.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
		searchForCasefiles.setSearchType("JNUM");
		searchForCasefiles.setJuvenileNum(form.getJuvenileNum());
		CompositeResponse response = MessageUtil.postRequest(searchForCasefiles);
		
		List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
		
		Iterator<JuvenileCasefileSearchResponseEvent> casefilesIter = casefiles.iterator();
		
		while(casefilesIter.hasNext())
		{
			JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) casefilesIter.next();
			if(resp.getCaseStatus()!=null && (resp.getCaseStatus().equalsIgnoreCase("ACTIVE") || resp.getCaseStatus().equalsIgnoreCase("A")))
				form.setHasActiveCasefiles(true);
			
			if(resp.getCaseStatus()!=null && (resp.getCaseStatus().equalsIgnoreCase("PENDING") || resp.getCaseStatus().equalsIgnoreCase("P")))
				form.setHasPendingCasefiles(true);
		}		
	}
	//Removed buildDetentionResponseEvent--RRY
	/**
	 * buildDetentionResponseEvent
	 * @param fac
	 * @return JuvenileDetentionFacilitiesResponseEvent
	 * Uma,nekha,sri, brainstrom - Descriptions are not needed as it was causing issue when code.find used. Need to be a pure post modify admit.6/14/2017.
	 */
	public static JuvenileDetentionFacilitiesResponseEvent buildDetentionResponseEventForModifyAdmit(JJSFacility fac){
		JuvenileDetentionFacilitiesResponseEvent resp = new JuvenileDetentionFacilitiesResponseEvent();
		resp.setJuvNum(fac.getJuvenileNumber());
		resp.setReferralNumber(fac.getReferralNumber());
		resp.setAdmitDate(fac.getAdmitDate());
		resp.setAdmitTime(JuvenileFacilityHelper.getHoursMinsFromTime(fac.getAdmitTime()));
		resp.setFacilityStatus(fac.getDetentionStatus());
		
		resp.setDetentionStatus(fac.getDetentionStatus());
		// added the Admit Reason and Admit Reason Desc below for Task#42493, US 38986
		resp.setAdmitReason(fac.getAdmitReason());
	
		resp.setOriginalAdmitDate(fac.getOriginalAdmitDate());
		resp.setOriginalAdmitOID(fac.getOriginalAdmitOID()); //added for user-story44549
		resp.setSecureStatus(fac.getSecureStatus());
		resp.setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(fac.getReleaseTime()));
		resp.setReleaseDate(fac.getReleaseDate());
		resp.setReleaseAuthorizedBy(fac.getReleaseAuthorizedBy());
	
		resp.setReleaseTo(fac.getReleaseTo());
		
		resp.setReleaseBy(fac.getReleaseBy());
	
		if(fac.getReleaseReason()!=null){
			resp.setReleaseReason(fac.getReleaseReason());
		
		}
		if (fac.getFacilitySequenceNumber() == null || fac.getFacilitySequenceNumber().equals("")) {
			resp.setFacilitySequenceNumber("0");
		} else {
			resp.setFacilitySequenceNumber(fac.getFacilitySequenceNumber());
		}
		
		resp.setLockerNumber(fac.getLockerNumber());
		resp.setReceiptNumber(fac.getReceiptNumber());
		resp.setSpecialAttention(fac.getSpecialAttention());
		resp.setSaReason(fac.getSaReasonCode());
		
		if(resp.getSpecialAttention()!=null && !resp.getSpecialAttention().isEmpty()){
			resp.setSpecialAttentionDesc(getCodeTableDescription(PDCodeTableConstants.SPECIAL_ATTENTION, resp.getSpecialAttention()));
		}else{
			resp.setSpecialAttention("NO");
			resp.setSpecialAttentionDesc(getCodeTableDescription(PDCodeTableConstants.SPECIAL_ATTENTION, resp.getSpecialAttention()));
		}

		//get the Special Attention Reason Other Comments from the new table
		if(fac.getOID()!=null && !fac.getOID().isEmpty()){
			GetJuvenileFacilitySplAttnReasonCommentsEvent splAttentionEvt = new GetJuvenileFacilitySplAttnReasonCommentsEvent();
			splAttentionEvt.setJuvenileNumber(fac.getJuvenileNumber());
			splAttentionEvt.setDetentionId(fac.getOID());
			
			Iterator<JJSSplAttnReasonComments> splAttentiontr = JJSSplAttnReasonComments.findAll(splAttentionEvt);
			if (splAttentiontr.hasNext()) {
				JJSSplAttnReasonComments splAttnReasonComments = splAttentiontr.next();	
				resp.setSaReasonOtherComments(splAttnReasonComments.getComments());
			}
		}
		resp.setAdmitAuthority(fac.getAdmitAuthority());
		
		
		resp.setAdmittedByJPO(fac.getAdmittedByJPO());
		
		resp.setDetainedFacility(fac.getDetainedFacility());
		
		
		resp.setBookingSupervisionNum(fac.getBookingSupervisionNum());
		resp.setReferralNumber(fac.getReferralNumber());
		
		resp.setCurrentOffense(fac.getCurrentOffense());
		if(fac.getCurrentOffense()!=null && !fac.getCurrentOffense().isEmpty()){
			resp.setCurrentOffenseDesc(JuvenileOffenseCode.find("offenseCode",fac.getCurrentOffense()).getLongDescription());
		}
		resp.setCurrentReferral(fac.getCurrentReferral()); 
		resp.setCurrentSupervisionNum(fac.getCurrentSupervisionNum());
		resp.setDetainedDate(fac.getDetainedDate());
		resp.setEscapeCode(fac.getEscapeCode());
	
		
		if(fac.getEscapeAttempts()!=null&&fac.getEscapeAttempts().equals("0")){
			resp.setEscapeAttempts("");
		}else{
			resp.setEscapeAttempts(fac.getEscapeAttempts());
		}
		resp.setEscapeAttemptComments(fac.getEscapeAttemptComments());
		resp.setDetainedByInd(fac.getDetainedByInd());
	//	resp.setComments(fac.getComments()); U.S #51737
		resp.setReturnDate(fac.getReturnDate());
		resp.setReturnStatus(fac.getReturnStatus());
		resp.setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(fac.getReturnTime()));
		resp.setReturnReason(fac.getReturnReason());
		resp.setOtherChangeFlag(fac.isOtherChangeFlag());
		resp.setReasonChangeFlag(fac.isReasonChangeFlag());
		resp.setSecureIndicatorChangeFlag(fac.isSecureIndicatorChangeFlag());
		resp.setLocationChangeFlag(fac.isLocationChangeFlag());
		resp.setTempReleaseReasonCd(fac.getTempReleaseReasonCd());
		
		resp.setTempReleaseOtherComments(fac.getTempReleaseOtherComments());
		resp.setChangeExplanation(fac.getChangeExplanation());
		resp.setTransferToFacility(fac.getTransferToFacility());
		
		resp.setOutcome(fac.getOutcome());
		
		resp.setRoomNum(fac.getRoomNum());
		resp.setUnit(fac.getUnit());
		resp.setFloorNum(fac.getFloorNum());
		resp.setMultipleOccupyUnit(fac.getMultipleOccupyUnit()); 
		resp.setDetentionId(fac.getOID()); //task #34820
		resp.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
		return resp;
	}
	

	
	
	 /**
	     * getCodeTableDescription
	     * 
	     * @param codetableName
	     * @param code
	     * @return String
	     */
	    public static String getCodeTableDescription(String codetableName,String codeId)
	    {
		String description="";
		Collection<Code> codes = Code.findAll(codetableName);
		Iterator<Code> i = codes.iterator();
		while (i.hasNext())
		{
		    Code code = (Code) i.next();
		    if(code!=null){
			if (code.getCode() != null && code.getCode().equalsIgnoreCase(codeId))
			{
			    description = code.getDescription();
			    break;
			}
		    }
		}
		return description;
	    }
	    
	    public static void populateTransferTo(String juvNum, Collection<JuvenileProfileReferralListResponseEvent> referralList)
	    {
		for (JuvenileProfileReferralListResponseEvent event : referralList) {
		        
		        IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
			GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
			courtEvt.setJuvenileNumber(juvNum);
			courtEvt.setReferralNumber(event.getReferralNumber());
			disp.postEvent(courtEvt);
			CompositeResponse response1 = (CompositeResponse) disp.getReply();
			//Court Response.
			List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(response1, DocketEventResponseEvent.class);
					
			String cCourt = event.getCourtId();
			
			DocketEventResponseEvent matchingEvent = null;

			// Using a for loop to find the first matching record
		        for (DocketEventResponseEvent e1 : crtdktRespEvts) {
		            if (cCourt.equals(e1.getTransferTo())) {
		                matchingEvent = e1;
		                break;
		            }
		        }
		        
		        if(matchingEvent != null && matchingEvent.getCourt()!= null)
		        {
		            event.setTransferredFrom("Transferred-" + matchingEvent.getCourt());
		        }		
	        }
	    }
	    
		
		/**
		 * setBookingOffense
		 * @param evt
		 */
		public static void setBookingOffense(JuvenileDetentionFacilitiesResponseEvent evt){
			GetJuvenileCasefileOffensesEvent jcoEvent = new GetJuvenileCasefileOffensesEvent();
			jcoEvent.setJuvenileNum(evt.getJuvNum());
			List<JJSOffenseResponseEvent> offenses = MessageUtil.postRequestListFilter(jcoEvent, JJSOffenseResponseEvent.class);
			
			//U.S #32320
			boolean isTransferredOffense=false;
			List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseResp = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(evt.getJuvNum());
			//sorts in descending order.
			Collections.sort((List<JJSOffenseResponseEvent>)offenses,Collections.reverseOrder(new Comparator<JJSOffenseResponseEvent>() {
				@Override
				public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2) {
					return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
				}
			}));
			
			Iterator<JJSOffenseResponseEvent> offensesItr = offenses.iterator();
			while (offensesItr.hasNext())
			{
				JJSOffenseResponseEvent offenseResp= offensesItr.next();
				if(offenseResp !=null && JuvenileFacilityHelper.checkIfJJSFacilityHasMoreThanOneRecord(evt.getJuvNum()) && evt!=null 
						&& evt.getReferralNumber()!=null && !evt.getReferralNumber().isEmpty())
				{
					if(offenseResp.getReferralNum().equals(evt.getReferralNumber()))
					{
						//if a transferred offense
						if(offenseResp.getSeveritySubtype()!=null && offenseResp.getSeveritySubtype().equals("T")){
							//if there is a transferred offense added to the cart in SQL.
							if(transferredOffenseResp!=null && !transferredOffenseResp.isEmpty()){
								Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
								while (transferredOffenseIter.hasNext())
								{
									JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
									if(transferredOffense.getReferralNum().equals(evt.getReferralNumber())){
										evt.setBookingOffenseCd(transferredOffense.getOffenseCode());
										evt.setBookingOffenseDesc(transferredOffense.getOffenseShortDesc());
										isTransferredOffense = true;
										break;
									}
								}
								
							} //if there is not a transferred offense added to the cart in SQL.Retain the offense from m204 jjsoffense.
							if(!isTransferredOffense){
								evt.setBookingOffenseCd(offenseResp.getOffenseCodeId());
								
								GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
								jocEvent.setAlphaCode(offenseResp.getOffenseCodeId());
								IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
								dispatch.postEvent(jocEvent);
								CompositeResponse response = (CompositeResponse)dispatch.getReply();
								
								JuvenileOffenseCodeResponseEvent juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
								if(juvOffenseCode!=null){
									evt.setBookingOffenseDesc( juvOffenseCode.getLongDescription());
								}	
								   break;
							}
						}
						else // not a transferred offense.
						{
							evt.setBookingOffenseCd(offenseResp.getOffenseCodeId());
							
							GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
							jocEvent.setAlphaCode(offenseResp.getOffenseCodeId());
							IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
							dispatch.postEvent(jocEvent);
							CompositeResponse response = (CompositeResponse)dispatch.getReply();
							
							JuvenileOffenseCodeResponseEvent juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
							if(juvOffenseCode!=null){
								evt.setBookingOffenseDesc( juvOffenseCode.getLongDescription());
							}	
							   break;
						}
					} //end of if(2)			
				}//end of if(1)
			} //end of while(1)
		}
		  private static IName getUserName(String userId)
		    {
		        UserResponseEvent user = getUser(userId);
		        if(user!=null){
			        String firstName = user.getFirstName();
			        String lastName = user.getLastName();
			        String middleName = user.getMiddleName();
			        if (firstName == null)
			        {
			            firstName = "";
			        }
			        if (lastName == null)
			        {
			            lastName = "";
			        }
			        if (middleName == null)
			        {
			            middleName = "";
			        }
			        	return new Name(firstName, middleName, lastName);
			    }
		        return new Name("", "", "");
		    }
		 private static UserResponseEvent getUser(String userId)
		    {
		        UserResponseEvent userResponse = null;
		        if (userId != null)
		        {
		            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		            GetUserEvent requestEvent = (GetUserEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSER);
		            requestEvent.setLogonId(userId);
		            dispatch.postEvent(requestEvent);
		            IEvent replyEvent = dispatch.getReply();
		            CompositeResponse compositeResponse = (CompositeResponse) replyEvent;
		            MessageUtil.processReturnException(compositeResponse);

		            // if multiple requests the response is cached so there can be be multiple results
		            //userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
		            Collection userResponses = MessageUtil.compositeToCollection(compositeResponse,UserResponseEvent.class);
		            Iterator iter = userResponses.iterator();
		            while(iter.hasNext())
		            {
		            	   userResponse = (UserResponseEvent) iter.next();
		            }
		        }

		        return userResponse;
		    }
	
}
