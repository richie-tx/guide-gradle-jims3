/*
 * Created on May 15, 2007
 *
 */
package ui.juvenilecase.programreferral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralSearchForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.programreferral.GetProgramReferralListEvent;
import messaging.programreferral.ProgramReferralRetrieverAttribute;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

/**
 */
public class UIProgramReferralHelper {

	/**
	 * @param casefileId
	 * @param programId
	 */
	public static UIProgramReferralBean getActiveProgramReferral(
			String casefileId, String programId) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute casefileAttribute = ProgramReferralAttributeFactory
				.getCasefileAttribute();
		casefileAttribute.setAttributeValue(casefileId);

		attributeList.add(casefileAttribute);

		ProgramReferralRetrieverAttribute programAttribute = ProgramReferralAttributeFactory
				.getProgramAttribute();
		programAttribute.setAttributeValue(programId);

		attributeList.add(programAttribute);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);

		attributeList.add(statusAttribute);

		statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(true);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) MessageUtil
				.filterComposite(compositeResponse,
						ProgramReferralResponseEvent.class);

		UIProgramReferralBean programReferralBean = null;

		if (resp != null) {
			programReferralBean = new UIProgramReferralBean(resp);
		}
		return programReferralBean;
	}
	
	/**
	 * @param casefileId
	 * @param programId
	 */
	public static UIProgramReferralBean getActiveJuvenileProgramReferral(
			String juvenileNum, String programId) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
		.getJuvenileAttribute();

		juvenileAttr.setAttributeValue(juvenileNum);

		attributeList.add(juvenileAttr);

		ProgramReferralRetrieverAttribute programAttribute = ProgramReferralAttributeFactory
				.getProgramAttribute();
		programAttribute.setAttributeValue(programId);

		attributeList.add(programAttribute);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);

		attributeList.add(statusAttribute);

		statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(true);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) MessageUtil
				.filterComposite(compositeResponse,
						ProgramReferralResponseEvent.class);

		UIProgramReferralBean programReferralBean = null;

		if (resp != null) {
			programReferralBean = new UIProgramReferralBean(resp);
		}
		return programReferralBean;
	}

	public static List getActiveCasefileProgramReferrals(String casefileId) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute casefileAttr = ProgramReferralAttributeFactory
				.getCasefileAttribute();

		casefileAttr.setAttributeValue(casefileId);

		attributeList.add(casefileAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);

		attributeList.add(statusAttribute);

		statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		for (int i = 0; i < respList.size(); i++) {
			ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) respList
					.get(i);
			resp.setOutComeDesc(SimpleCodeTableHelper.getDescrByCode(
					PDCodeTableConstants.PROGRAM_REFERRAL_OUTCOME, resp
							.getOutComeCd()));

		}

		return respList;
	}

	public static List getActiveJuvenileProgramReferrals(String juvenileNum) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
				.getJuvenileAttribute();

		juvenileAttr.setAttributeValue(juvenileNum);

		attributeList.add(juvenileAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);

		attributeList.add(statusAttribute);

		statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		return respList;
	}
	
	
	/**
	 * 
	 * @param juvenileNum
	 * @return
	 */
	public static List getOnlyActiveJuvenileProgramReferrals(String juvenileNum) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
				.getJuvenileAttribute();

		juvenileAttr.setAttributeValue(juvenileNum);

		attributeList.add(juvenileAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);

		attributeList.add(statusAttribute);

		/*statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);

		attributeList.add(statusAttribute);*/

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		List respList = (List) MessageUtil.postRequestListFilter(gpre, ProgramReferralResponseEvent.class);

		return respList;
	}
	
	public static List getJuvenileProgramReferrals(String juvenileNum) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
				.getJuvenileAttribute();

		juvenileAttr.setAttributeValue(juvenileNum);
		attributeList.add(juvenileAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);
		attributeList.add(statusAttribute);

		statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);
		attributeList.add(statusAttribute);
		
		statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.CLOSED);
		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		return respList;
	}

	public static List getClosedCasefileProgramReferrals(String casefileId) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute casefileAttr = ProgramReferralAttributeFactory
				.getCasefileAttribute();

		casefileAttr.setAttributeValue(casefileId);

		attributeList.add(casefileAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.CLOSED);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		for (int i = 0; i < respList.size(); i++) {
			ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) respList
					.get(i);
			resp.setOutComeDesc(SimpleCodeTableHelper.getDescrByCode(
					PDCodeTableConstants.PROGRAM_REFERRAL_OUTCOME, resp
							.getOutComeCd()));

		}

		return respList;
	}

	public static List getClosedJuvenileProgramReferrals(String juvenileNum) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
				.getJuvenileAttribute();

		juvenileAttr.setAttributeValue(juvenileNum);

		attributeList.add(juvenileAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.CLOSED);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		return respList;
	}

	/**
	 * A list of attributes (Query Parameters) are accpeted or created to return the active
	 * program referrals
	 * @param serviceProviderId
	 */
	public static CompositeResponse getComprehensiveSPProgramReferrals(String serviceProviderId, ProgramReferralSearchForm form) {

		//List of Attributes (Query parameters)
		List attributeList = new ArrayList();
		
		//Set Service Provider Attribute(s)
		ProgramReferralRetrieverAttribute serviceProviderAttr = ProgramReferralAttributeFactory
				.getServiceProviderAttribute();
		serviceProviderAttr.setAttributeValue(serviceProviderId);
		attributeList.add(serviceProviderAttr);
		
		//Set Service Provider Attribute(s)
		if (form.getProviderProgramName() != null && form.getProviderProgramName().length() > 0) {
			ProgramReferralRetrieverAttribute programAttr = ProgramReferralAttributeFactory
					.getProgramAttribute();
			programAttr.setAttributeValue(form.getProviderProgramName());
			attributeList.add(programAttr);
		}
		
		//Set Juvenile Attributes
		if (form.getJuvenileNum() != null && form.getJuvenileNum().length() > 0) {
			
			ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
				.getJuvenileAttribute();
			juvenileAttr.setAttributeValue(form.getJuvenileNum());
			attributeList.add(juvenileAttr);
			
		} 
		else 
		{
			//Code Checking for Juvenile Name
			if (form.getJpoJuvenileNameInd() != null && form.getJpoJuvenileNameInd().equalsIgnoreCase(PDCalendarConstants.JUVENILE_NAME_SEARCH)) 
			{
				
				SearchJuvenileProfilesEvent juvProfileEvent = (SearchJuvenileProfilesEvent)
				EventFactory.getInstance( JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES ) ;
				
				//If the code gets to this point, last name should never be null
				juvProfileEvent.setLastName( form.getLastName() ) ;
								
				if (form.getFirstName() != null && form.getFirstName().length() > 0) {
					juvProfileEvent.setFirstName( form.getFirstName() ) ;
				}
				
				if (form.getMiddleName() != null && form.getMiddleName().length() > 0) {
					juvProfileEvent.setMiddleName( form.getMiddleName() ) ;
				}
				
				// empty string: dont include alias records  
				juvProfileEvent.setSearchType( UIConstants.EMPTY_STRING ) ; 
				
				List juveniles = MessageUtil.postRequestListFilter(juvProfileEvent, JuvenileProfileDetailResponseEvent.class);
				
				Iterator juvenilesIter= juveniles.iterator();
				while(juvenilesIter.hasNext())
				{
					//Set Juvenile Attributes
					JuvenileProfileDetailResponseEvent juvenilesResEvent = (JuvenileProfileDetailResponseEvent)juvenilesIter.next();
					ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
						.getJuvenileAttribute();
					juvenileAttr.setAttributeValue(juvenilesResEvent.getJuvenileNum());
					attributeList.add(juvenileAttr);
				
				}
			}
		}
		
		//Include JPO name for search if...
		if (form.getJpoJuvenileNameInd() != null && form.getJpoJuvenileNameInd().equalsIgnoreCase(PDCalendarConstants.JPO_NAME_SEARCH)) {
			
			//Set Last name Attribute -If the code gets to this point, last name should never be null
			ProgramReferralRetrieverAttribute officerLastNameAttr = ProgramReferralAttributeFactory
					.getOfficerLastNameProviderAttribute();
			officerLastNameAttr.setAttributeValue(form.getLastName().toUpperCase());
			attributeList.add(officerLastNameAttr);
			
			//Set First name Attribute
			if (form.getFirstName() != null && form.getFirstName().length() > 0) {
				ProgramReferralRetrieverAttribute officerFirstNameAttr = ProgramReferralAttributeFactory
						.getOfficerFirstNameProviderAttribute();
				officerFirstNameAttr.setAttributeValue(form.getFirstName().toUpperCase());
				attributeList.add(officerFirstNameAttr);
			}
			
			//Set Middle name Attribute
			if (form.getMiddleName() != null && form.getMiddleName().length() > 0) {
				ProgramReferralRetrieverAttribute officerMiddleNameAttr = ProgramReferralAttributeFactory
						.getOfficerMiddleNameProviderAttribute();
				officerMiddleNameAttr.setAttributeValue(form.getMiddleName().toUpperCase());
				attributeList.add(officerMiddleNameAttr);
			}
		}
		
		//Set Status
		if (form.getReferralStatusDescription() != null && form.getReferralStatusDescription().length() > 0) {
			//Specific Status or Sub Status combination has been choosen
			ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
			statusAttribute.setAttributeValue(form.getReferralStatusDescription());
			attributeList.add(statusAttribute); 
			
		} else {
			//Return ALL THREE Major Types
			ProgramReferralRetrieverAttribute statusAcceptedAttribute = ProgramReferralAttributeFactory
					.getStateAttribute();
			statusAcceptedAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);
			attributeList.add(statusAcceptedAttribute); 
	
			ProgramReferralRetrieverAttribute statusTentativeAttribute = ProgramReferralAttributeFactory.getStateAttribute();
			statusTentativeAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);
			attributeList.add(statusTentativeAttribute);
			
			ProgramReferralRetrieverAttribute statusClosedAttribute = ProgramReferralAttributeFactory.getStateAttribute();
			statusClosedAttribute.setAttributeValue(ProgramReferralConstants.CLOSED);
			attributeList.add(statusClosedAttribute);
		
		}
		
		//Set From Begin Date Attribute(s)
		if (form.getFromBeginDate() != null && form.getFromBeginDate().length() > 0) {
			ProgramReferralRetrieverAttribute fromBeginDateAttr = ProgramReferralAttributeFactory
					.getBeginDateProviderAttribute();
			fromBeginDateAttr.setAttributeValue(form.getFromBeginDate());
			attributeList.add(fromBeginDateAttr);
		}
		
		//Set To End Date Attribute(s)
		if (form.getToBeginDate() != null && form.getToBeginDate().length() > 0) {
			ProgramReferralRetrieverAttribute toEndDateAttr = ProgramReferralAttributeFactory
					.getEndDateProviderAttribute();
			toEndDateAttr.setAttributeValue(form.getToBeginDate());
			attributeList.add(toEndDateAttr);
		}
		

		//Set attrubutes (parameters) on event and run
		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);
		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);
		//Return results
		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);
		
		

		//Return List of Program Referrals based on Query parameters
		return compositeResponse;

	}

	/**
	 * @param serviceProviderId
	 */
	public static List getRejectedSPProgramReferrals(String serviceProviderId) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute serviceProviderAttr = ProgramReferralAttributeFactory
				.getServiceProviderAttribute();

		serviceProviderAttr.setAttributeValue(serviceProviderId);

		attributeList.add(serviceProviderAttr);

		ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory
				.getStateAttribute();
		statusAttribute.setAttributeValue(ProgramReferralConstants.CLOSED);

		attributeList.add(statusAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		List rejectedReferrals = new ArrayList();
		if (!respList.isEmpty() && respList.size() > 0 && respList != null) {
			Iterator iter = respList.iterator();
			while (iter.hasNext()) {
				ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) iter
						.next();
				if (!resp.getReferralSubStatusCd().equals("")
						&& resp.getReferralSubStatusCd() != null
						&& resp.getReferralSubStatusCd().equalsIgnoreCase(
								ProgramReferralConstants.REJECTED)) {
					rejectedReferrals.add(resp);
				}
			}
		}

		return rejectedReferrals;

	}

	/**
	 * @param juvenileId
	 * @return
	 */
	public static List getJuvenileReferralHistory(String juvenileId) {
		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttribute = ProgramReferralAttributeFactory
				.getJuvenileAttribute();

		juvenileAttribute.setAttributeValue(juvenileId);

		attributeList.add(juvenileAttribute);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		return respList;
	}

	/**
	 * @return
	 */
	public static String getSPAdminContactId(String serviceProviderId) {

		GetServiceProviderEvent spEvent = (GetServiceProviderEvent) EventFactory
				.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);
		spEvent.setServiceProviderId(serviceProviderId);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(spEvent);

		JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent) MessageUtil
				.filterComposite(compositeResponse,
						JuvenileServiceProviderResponseEvent.class);
		String adminContactId = null;

		if (resp != null) {
			adminContactId = resp.getAdminUserProfileId();
		}
		return adminContactId;
	}

	/**
	 * @return
	 */
	public static String getSPContactUserProfId(String serviceProviderId) {

		GetServiceProviderEvent spEvent = (GetServiceProviderEvent) EventFactory
				.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);
		spEvent.setServiceProviderId(serviceProviderId);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(spEvent);

		JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent) MessageUtil
				.filterComposite(compositeResponse,
						JuvenileServiceProviderResponseEvent.class);
		String contactUserProfId = null;

		if (resp != null) {
			contactUserProfId = resp.getContactUserProfileId();
		}
		return contactUserProfId;
	}

	/**
	 * @param officerId
	 * @return
	 */
	public static String getOfficerUserId(String officerId) {
		GetOfficerProfileEvent event = (GetOfficerProfileEvent) EventFactory
				.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
		event.setOfficerProfileId(officerId);
		CompositeResponse response = MessageUtil.postRequest(event);
		OfficerProfileResponseEvent officerProfileResponse = (OfficerProfileResponseEvent) MessageUtil
				.filterComposite(response, OfficerProfileResponseEvent.class);
		String userId = null;
		if (officerProfileResponse != null) {
			userId = officerProfileResponse.getUserId();
		}

		return userId;
	}

	/**
	 * @param juvenileNum
	 * @param casefileId
	 * @return
	 */

	public static List getJuvenileProgramReferrals(String juvenileNum,
			String casefileId) {

		List attributeList = new ArrayList();
		ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory
				.getJuvenileAttribute();

		juvenileAttr.setAttributeValue(juvenileNum);

		attributeList.add(juvenileAttr);

		ProgramReferralRetrieverAttribute casefileAttr = ProgramReferralAttributeFactory
				.getCasefileAttribute();

		casefileAttr.setAttributeValue(casefileId);

		attributeList.add(casefileAttr);

		GetProgramReferralListEvent gpre = (GetProgramReferralListEvent) EventFactory
				.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALLIST);

		gpre.setReferralAttributes(attributeList);
		gpre.setDetailsNeeded(false);

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gpre);

		List respList = (List) MessageUtil.compositeToCollection(
				compositeResponse, ProgramReferralResponseEvent.class);

		return respList;
	}
	
	
	public static String getControllingRefNumber(String casefileId)
	{
		String controllingReferralNum = UIConstants.EMPTY_STRING;
		GetJuvenileCasefileEvent getCasefileEvent =
			(GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
	
		getCasefileEvent.setSupervisionNumber(casefileId);
	
		CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
		JuvenileCasefileResponseEvent casefileInfo =
			(JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class); 
		if (casefileInfo != null)
		{
//			String spvrnTypeId = casefileInfo.getSupervisionTypeId();
//			String sprvnCatId = UIConstants.EMPTY_STRING;
//			if (spvrnTypeId != null) {
//				sprvnCatId  = UIJuvenileCaseworkHelper.getSupCatFromType(spvrnTypeId);
//				if (sprvnCatId.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) || 
//					sprvnCatId.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES) ||
//					sprvnCatId.equals(UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ) )
//				{
					if (casefileInfo.getControllingReferralId() != null && !UIConstants.EMPTY_STRING.equals(casefileInfo.getControllingReferralId()))
					{
						controllingReferralNum = casefileInfo.getControllingReferralId();
					}
//				}
//			}
//			spvrnTypeId = null;
//			sprvnCatId = null; 
		}
		// if value blank, find assignment record and use lowest referral number
		if (UIConstants.EMPTY_STRING.equals(controllingReferralNum))
		{
			controllingReferralNum  = getLowestCtrlngRefNbr(casefileId);
		}
		return controllingReferralNum;
	}
	
	/**
	 *Get the lowest controlling referral number from JCASSIGNMENT table. 
	 * @param casefileId
	 * @return controllingReferralNum
	 */
	public static String getLowestCtrlngRefNbr(String casefileId){
		
		String controllingReferralNum = UIConstants.EMPTY_STRING;
		
		GetAssignmentsByCasefileIdEvent asmntByCasefileEvent =
			(GetAssignmentsByCasefileIdEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		
		asmntByCasefileEvent.setCasefileId(casefileId);

		CompositeResponse refResponse = MessageUtil.postRequest(asmntByCasefileEvent);

		List assignments = MessageUtil.compositeToList(refResponse, AssignmentResponseEvent.class);
		
		if (assignments != null)
		{
			String refNum = UIConstants.EMPTY_STRING;
			if (assignments.size() == 1)
			{
				AssignmentResponseEvent arEvent = (AssignmentResponseEvent) assignments.get(0);
				refNum = arEvent.getReferralNum();
				if (refNum != null && refNum.length() == 4)
				{
					controllingReferralNum = arEvent.getReferralNum();
				}	
			}
			if (assignments.size() > 1)
			{
				Iterator iter = assignments.iterator();
				SortedMap map = new TreeMap();
				while(iter.hasNext()){
					AssignmentResponseEvent arEvent = (AssignmentResponseEvent) iter.next();	
					if(arEvent.getCaseFileId().equals(casefileId)) //added for user-story #34655
						map.put(arEvent.getReferralNum(), arEvent);
				}
				List asmnts = new ArrayList(map.values());
				if (asmnts != null)
				{	
					for (int x=0; x<asmnts.size(); x++)
					{
						AssignmentResponseEvent arEvent = (AssignmentResponseEvent) asmnts.get(x);
						refNum = arEvent.getReferralNum();
						if (refNum != null && refNum.length() == 4 )
						{
							controllingReferralNum = arEvent.getReferralNum();
							break;
						}
					}
				}
				refNum = null;
				asmnts = null;
			}
		}
		assignments = null;
		return controllingReferralNum;
	}
	
	public static float calculateTimeDiff(Date dateStart, Date dateEnd){
	    long diff = 0;
	    long diffSeconds = 0;  
	    long diffMinutes = 0;	    
	    long diffHours = 0;
	    long diffDays = 0; 
	    float result = 0;
	     
	     if(dateStart != null && dateEnd != null){
		 
		 diff = dateEnd.getTime() - dateStart.getTime(); //in milliseconds
		 
		 if(diff > 0)
		 {
			    diffSeconds = diff / 1000; 
			    diffMinutes = diff / (60 * 1000);
			    diffHours = diff / (60 * 60 * 1000);
			    diffDays = diff / (60 * 60 * 1000 * 24);
		 }
		 
		 if (diffHours < 1) {
		       result = Float.valueOf(diffMinutes) / 60;
		    } else if (diffDays < 1) {
		       result = diffHours;
		    } 
	     	}		
	    
	    return result;
	}
	
	
	public static List<ServiceEventAttendanceResponseEvent> getAttendaceEventRecords(List<ServiceEventAttendanceResponseEvent> existingReferrals, CalendarServiceEventResponseEvent event)
	{	    
	    float creditHours = 0;
	    float totalCreditHours = 0;
	    List<ServiceEventAttendanceResponseEvent> eventsAttended = new ArrayList<ServiceEventAttendanceResponseEvent>();
	    
	    if(existingReferrals != null && existingReferrals.size() > 0){			
		
		Iterator<ServiceEventAttendanceResponseEvent> iterExistingRef = existingReferrals.iterator();
			
		while(iterExistingRef.hasNext()){
			
			ServiceEventAttendanceResponseEvent attendanceEvent = (ServiceEventAttendanceResponseEvent)iterExistingRef.next();
			if(attendanceEvent != null){
			    
			    if((attendanceEvent.getAttendanceStatusCd() != null && attendanceEvent.getAttendanceStatusCd().equals("AT")) && 
				(attendanceEvent.getProgramReferralId() != null && attendanceEvent.getProgramReferralId().equals(event.getProgramReferralId()) && 
					(attendanceEvent.getServiceEventId() != null && attendanceEvent.getServiceEventId().equals(event.getEventId())))){
				
				creditHours = (float) UIProgramReferralHelper.calculateTimeDiff(attendanceEvent.getStartDateTime(), attendanceEvent.getEndDateTime());
				attendanceEvent.setCreditHours(creditHours);
    				totalCreditHours += creditHours; 
    				
				eventsAttended.add(attendanceEvent);
    				
    				//break;
			    } 
			}
												
		    }					
	    }
	    
	    return eventsAttended;
	}
	
	public static ProgramReferralResponseEvent getProgramInfoFacilitiesCaseworker(String juvNum)
	{
	    List<ProgramReferralResponseEvent> programList = new ArrayList();
	    final String programIdResidential1 = "1111";
	    final String programIdResidential2 = "1112";
	    
	    List<ProgramReferralResponseEvent> referrals = UIProgramReferralHelper.getJuvenileProgramReferrals(juvNum);
	    
	    Iterator<ProgramReferralResponseEvent> Iter = referrals.iterator();
	    while(Iter.hasNext()){
		ProgramReferralResponseEvent program = (ProgramReferralResponseEvent)Iter.next();
		
		if(program != null && program.getJuvServiceProviderId() != null && program.getReferralStatusCd() != null){
		    
		    if(program.getReferralStatusCd().equalsIgnoreCase("AC") &&
			    (program.getJuvServiceProviderId().equalsIgnoreCase(programIdResidential1) || program.getJuvServiceProviderId().equalsIgnoreCase(programIdResidential2))) {
			
				programList.add(program);		    }
		}
		
	    }
	    
	    
	    ProgramReferralResponseEvent latestProgram = null;
	    if(programList != null && programList.size() > 0){
		
		Collections.sort(programList, new Comparator<ProgramReferralResponseEvent>() {
			@Override
			public int compare(ProgramReferralResponseEvent p1, ProgramReferralResponseEvent p2)
			{
			    if(p2.getLastActionDate() != null && p1.getLastActionDate() != null){
				return p2.getLastActionDate().compareTo(p1.getLastActionDate());
			    } else {
				return p2.getProvProgramId().compareTo(p1.getProvProgramId());
			    }
			    
			}
		    });
		
		 //return 1st item - the one with latest begin date
		 latestProgram = programList.get(0);
	    }
	    
	    if(latestProgram != null && latestProgram.getJuvServiceProviderId() != null && !"".equals(latestProgram.getJuvServiceProviderId()))
	    {
		Collection<ProviderProgramResponseEvent> programs = UIServiceProviderHelper.getActivePrograms(latestProgram.getJuvServiceProviderId());
		    Iterator<ProviderProgramResponseEvent> iterPrograms = programs.iterator();
		    while(iterPrograms.hasNext()){
			ProviderProgramResponseEvent spProgram = (ProviderProgramResponseEvent)iterPrograms.next();
			
			if(spProgram.getProviderProgramId() != null && !spProgram.getProviderProgramId().equals("") &&  
				latestProgram.getProvProgramId() != null && !latestProgram.getProvProgramId().equals("")){
			    
	        		if(spProgram.getProviderProgramId().equalsIgnoreCase(latestProgram.getProvProgramId()) ){
	        		    
	        		    latestProgram.setProvProgramCode(spProgram.getProgramCodeId());
	        		}
			}
			
		    }
	    }    
	    
	    
	    return latestProgram;
	}
}
