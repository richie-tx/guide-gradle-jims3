/*
 * Created on May 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionEvent;
import messaging.juvenilecase.GetJuvenileCasefileReferralsEvent;
import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.GetJuvenileTraitsByTypeEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.GetSupervisionTypeTJJDMapEvent;
import messaging.juvenilecase.GetTraitChildByCategoryEvent;
import messaging.juvenilecase.GetTraitParentByCategoryEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingCoActorResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingComplainantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.SummaryOfFactsResponseEvent;
import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.mentalhealth.GetMAYSIDetailsEvent;
import messaging.mentalhealth.GetMentalHealthHospitalizationDataEvent;
import messaging.mentalhealth.GetMentalHealthHospitalizationListEvent;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.PetitionDetailsForm;

/**
 * @author dapte This is a utility class for common data-retrieval operations
 *         required by the Juvenile casework actions.
 */
public final class UIJuvenileCaseworkHelper
{
	private static final String	HEADER_FORM	= "juvenileCasefileForm";

	/**
     *  
     */
	private UIJuvenileCaseworkHelper()
	{
		super();
	}

	/**
	 * If casefileID is not null
	 * 
	 * @return Collection of Assignments (referrals)
	 */
	public static Collection<JuvenileCasefileReferralsResponseEvent> fetchJuvenileCasefileReferralsList(String casefileId, String juvNum)
	{
		Collection refs = new ArrayList();
		GetJuvenileCasefileReferralsEvent event = (GetJuvenileCasefileReferralsEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEREFERRALS);
		event.setSupervisionNum(casefileId);
		event.setJuvenileNum(juvNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) 
				MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException != null)
		{
			return null;
		}

		Map map = MessageUtil.groupByTopic(response);
		refs = (Collection) map.get(PDJuvenileCaseConstants.JUVENILE_CASEFILE_REFERRAL_TOPIC);

		// If null, return empty list.
		if (refs == null)
		{
			refs = new ArrayList();
		}
		
		//US 71184 - go through all the referrals and getmost recent assignment details
		Iterator refsIter = refs.iterator();
		ArrayList repeatRefNums = new ArrayList();
		Collection returnRefs = new ArrayList();
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
		while(refsIter.hasNext())
		{
		    JuvenileCasefileReferralsResponseEvent resp = (JuvenileCasefileReferralsResponseEvent)refsIter.next();
		    Collection<JJSOffense> offenses =  resp.getOffenses();
		    if ( offenses != null
			    && offenses.size() > 0) {
			Iterator<JJSOffense> offenseItr = offenses.iterator();
			while (offenseItr.hasNext())
			{
			    JJSOffense offense = offenseItr.next();
			    JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
        		    if (transferredOffenses != null && transferredOffenses.size() > 0 && offenseCode != null)
        		    {
        			if ("TRNDSP".equals(offenseCode.getOffenseCode()) || "TRNSIN".equals(offenseCode.getOffenseCode()) || "REGION".equals(offenseCode.getOffenseCode()) || "ISCOIN".equals(offenseCode.getOffenseCode()))
        			{
        			    for (JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses)
        			    {
        				if (resp.getReferralNumber().equals(transferredOffense.getReferralNum()))
        				{
        				    offenseCode.setLongDescription(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
        				    offenseCode.setShortDescription(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
        				}
        			    }
        			}
        		    }		    			    
			    
			    //US 179141
			    if(offenseCode != null && offenseCode.getOffenseCode() != null && resp != null && resp.getOffense() != null)
			    {				
				if(resp.getOffense().equalsIgnoreCase(offenseCode.getOffenseCode())){
				    
				    if(offenseCode.getCategory() != null && !"".equals(offenseCode.getCategory())){
					if(resp.getReferralTypeInd()!=null&&(resp.getReferralTypeInd().equalsIgnoreCase("TR")||resp.getReferralTypeInd().equalsIgnoreCase("IC")))
					{
					  //change for US180514
					    GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
				    	    getEvent.setJuvenileNumber(juvNum);
				    	    getEvent.setReferralNumber(resp.getReferralNumber());
			                	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);   
			                	
			                	if(transOffenseReferralsIter.hasNext()) 
			                	{
			                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();                	    
			                	    if (transOffenseReferral.getOffenseCode() != null)
			                	    {
			                		JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());
			                		if (offCode != null)
			                		{
			                		    resp.setPetitionAllegDscr(offCode.getLongDescription()+ ", " + offCode.getCategory());                		    
			                		}
			                	    }
			                	    
			                	}
					    
					}
					else
					    resp.setPetitionAllegDscr(resp.getPetitionAllegDscr() + ", " + offenseCode.getCategory());
					offenseCode.setLongDescription(offenseCode.getLongDescription() + ", " + offenseCode.getCategory());
				    }				    
				}
			    }
			    
			    resp.getOffenseCodes().add(offenseCode);
			    
			}
		    }
		    
		    if(!repeatRefNums.contains(resp.getReferralNumber()))
		    {
        		    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, resp.getReferralNumber());
        		    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();
        		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
        			@Override
        			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
        			{
        			    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
        				//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());/// sorting was wrong if same date
        				return evt1.getOID().compareTo(evt2.getOID());///sorted with OID to get the latest assignment details bug 126238
        			    else
        				return -1;
        			}
        		    }));
        		    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
        		    //while (caseFileRefItr.hasNext())
        		    if (caseFileRefItr.hasNext())
        		    {
        			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
        			resp.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
        			resp.setSupervisionType(caseFileReferral.getSupervisionType());
        			
        			
        			resp.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
        			resp.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, resp.getSupervisionCategoryId()));
        			// referral.setJpoId(caseFileReferral.getOfficerID());
        			OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
        			if (officerProfileResponse != null)
        			{
        			    resp.setJpoId(officerProfileResponse.getUserId());
        			    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
        			    resp.setJpo(officerFullName);
        			}
        			repeatRefNums.add(resp.getReferralNumber());
        			if( caseFileReferral.getSupervisionTypeCd() != null){
        			    
        			    GetSupervisionTypeTJJDMapEvent request = new GetSupervisionTypeTJJDMapEvent();
        			    request.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
        			    
        			    CompositeResponse replyEvent = MessageUtil.postRequest(request);
        			    SupervisionTypeMapResponseEvent tjjdMap = (SupervisionTypeMapResponseEvent) MessageUtil.filterComposite(replyEvent,SupervisionTypeMapResponseEvent.class);
        			    
        			    if( tjjdMap != null ){
        				
        				resp.setSpecialCatagoryCd( tjjdMap.getSplCategoryId() );
        				if(casefileId.equalsIgnoreCase(caseFileReferral.getCaseFileId()))
        				{
        				    resp.setTJJDCode(tjjdMap.getSupTJJDTypeId());
        				    resp.setSupervisioncategoryPact(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, caseFileReferral.getSupervisionCat()));
        	        			
        				}
        				else
        				{
        				    while (caseFileRefItr.hasNext())
        				    {
        					JuvenileCasefileReferral caseFileReferral1 = caseFileRefItr.next();
        					if(casefileId.equalsIgnoreCase(caseFileReferral1.getCaseFileId()))
        					{
        					    if( caseFileReferral1.getSupervisionTypeCd() != null)
        					    {
        		        			    
        		        			    GetSupervisionTypeTJJDMapEvent req = new GetSupervisionTypeTJJDMapEvent();
        		        			    req.setSupervisionTypeId(caseFileReferral1.getSupervisionTypeCd());
        		        			    
        		        			    CompositeResponse reply = MessageUtil.postRequest(req);
        		        			    SupervisionTypeMapResponseEvent tjjd = (SupervisionTypeMapResponseEvent) MessageUtil.filterComposite(reply,SupervisionTypeMapResponseEvent.class);
        		        			    
        		        			    if( tjjd != null )
        		        			    {
        		        				resp.setTJJDCode(tjjd.getSupTJJDTypeId());
        		        				resp.setSupervisioncategoryPact(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, caseFileReferral1.getSupervisionCat()));
        		        	        		break;
        		        			    }
        		        			    
        					    }
        					}
        					
        				    }
        				
        				}
        			    }
        			    
        			}
        			returnRefs.add(resp);
        			//break;
        		    }
		    	}
		}
		Collections.sort((List) returnRefs);

		return returnRefs;
	}
	
	

	/**
	 * JuvenileProfile Referral Number
	 * 
	 * @return Collection of Assignments (referrals)
	 */
	public static Collection fetchJuvenileProfileReferralsList(String juvNum)
	{
		Collection refs = new ArrayList();

		GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) 
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
		event.setJuvenileNum(juvNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		Map dataMap = MessageUtil.groupByTopic(response);
		if (dataMap != null)
		{
			refs = (Collection) dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
		}

		// If null, return empty list.
		if (refs == null)
		{
			refs = new ArrayList();
		}

		return refs;
	}

	/**
	 * @param sequenceNum
	 * @return MAYSIDetailsResponseEvent detailEvent
	 */
	public static MAYSIDetailsResponseEvent fetchMAYSIDetails(String assessmentId, String subAssessId, String maysiDetailId)
	{
		GetMAYSIDetailsEvent requestEvent = (GetMAYSIDetailsEvent) 
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMAYSIDETAILS);

		// maysiID
		requestEvent.setAssessmentId(assessmentId);
		requestEvent.setSubAssessId(subAssessId);
		requestEvent.setMaysiDetailId(maysiDetailId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(replyEvent, ReturnException.class);
		if (returnException != null)
		{
			return null;
		}

		MAYSIDetailsResponseEvent detail = (MAYSIDetailsResponseEvent) 
				MessageUtil.filterComposite(replyEvent, MAYSIDetailsResponseEvent.class);

		return detail;
	}

	/**
	 * @param juvenileNum
	 * @return Collection cautions
	 */
	public static List fetchPreviousMAYSIAssessments(String juvenileNum)
	{
		GetAllMAYSIAssessmentsEvent requestEvent = (GetAllMAYSIAssessmentsEvent) 
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETALLMAYSIASSESSMENTS);
		requestEvent.setJuvenileNumber(juvenileNum);
		requestEvent.setReferralNumber("%");

		List assessments = MessageUtil.postRequestListFilter(requestEvent, MAYSISearchResultResponseEvent.class);
		return assessments;
	}

	/**
	 * @param juvenileNum
	 * @return List activityResults
	 */
	public static List fetchCleActivities(String supervisionNum)
	{
		GetActivitiesEvent reqEvent = (GetActivitiesEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);

		reqEvent.setCasefileID(supervisionNum);
		reqEvent.setCategoryId(ActivityConstants.ACTIVITY_CATEGORY_ADMINISTRATIVE);
		reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT);
		reqEvent.setActivityCodeId(ActivityConstants.CASEFILE_CLOSING_OVERRIDE_EXCEPTIONS);

		List activityResults = MessageUtil.postRequestListFilter(reqEvent, ActivityResponseEvent.class);

		return activityResults;
	}

	/**
	 * @param juvenileNum
	 *          Method to fetch the Hospitalization List.
	 */

	public static List fetchHospitalizationListbyJuvenile(String juvenileNum)
	{
		GetMentalHealthHospitalizationListEvent requestEvent = (GetMentalHealthHospitalizationListEvent) 
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHHOSPITALIZATIONLIST);
		requestEvent.setJuvenileNum(juvenileNum);

		List hospitalizationList = MessageUtil.postRequestListFilter(requestEvent, HospitalizationResponseEvent.class);
		return hospitalizationList;
	}

	/**
	 * @param hospitalizationId
	 *          Method to fetch the Hospitalization List.
	 */
	public static HospitalizationResponseEvent fetchHospitalizationListbyHospID(String hospitalizationId)
	{
		GetMentalHealthHospitalizationDataEvent requestEvent = (GetMentalHealthHospitalizationDataEvent) 
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHHOSPITALIZATIONDATA);
		requestEvent.setHospitalizationId(hospitalizationId);

		HospitalizationResponseEvent hospDetails = (HospitalizationResponseEvent) MessageUtil.postRequest(requestEvent, HospitalizationResponseEvent.class);

		return hospDetails;
	}

	/*
	 * 
	 */
	private static Map fetchByType(String juvenileNum, String traitTypeId)
	{
		GetJuvenileTraitsByTypeEvent event = (GetJuvenileTraitsByTypeEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYTYPE);

		event.setJuvenileNum(juvenileNum);
		event.setTraitType(traitTypeId);

		CompositeResponse composite = MessageUtil.postRequest(event);
		Map traits = MessageUtil.groupByTopic(composite);

		// Remove the TerminationEvent
		traits.remove(UIConstants.EMPTY_STRING);

		return traits;
	}

	public static Map fetchTraits(String juvenileNum)
	{
		GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);

		event.setJuvenileNum(juvenileNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse composite = (CompositeResponse) replyEvent;
		Map traitMap = MessageUtil.groupByTopic(composite);

		// Remove the TerminationEvent
		traitMap.remove(UIConstants.EMPTY_STRING);

		return traitMap;
	}

	/*
	 * //Added for ER changes 11032 to reduce iteration. 
	 * Returns a collection.
	 */
	public static Collection fetchJuvTraits(String juvenileNum)
	{
		GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);

		event.setJuvenileNum(juvenileNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse response = (CompositeResponse) replyEvent;

		Collection<JuvenileTraitResponseEvent> juvTraitRespEvent = MessageUtil.compositeToCollection(response, JuvenileTraitResponseEvent.class);
		Collections.sort((ArrayList) (juvTraitRespEvent));

		if (juvTraitRespEvent == null){
			juvTraitRespEvent = new ArrayList();
		}
		else{
			Collections.sort((List) juvTraitRespEvent);
		}

		return juvTraitRespEvent;
	}
	
	/**
	 * Added for U.S. #42660
	 * fetchFacilityTraits
	 * @param juvenileNum
	 * @param facilityAdmitOID
	 * @return Map<String,JuvenileTraitResponseEvent>
	 */
	public static Map<String,JuvenileTraitResponseEvent> fetchFacilityTraits(String juvenileNum,String facilityAdmitOID)
	{
		GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);

		event.setJuvenileNum(juvenileNum);
		event.setFacilityAdmitOID(facilityAdmitOID);
		event.setUIFacility(true);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse composite = (CompositeResponse) replyEvent;
		Map<String,JuvenileTraitResponseEvent>  traitMap = MessageUtil.groupByTopic(composite);

		// Remove the TerminationEvent
		traitMap.remove(UIConstants.EMPTY_STRING);

		return traitMap;
	}
	
	
	public static Collection fetchParentTraitTypes(String parentTraitTypeId)
	{
		GetJuvenileTraitTypesEvent event = (GetJuvenileTraitTypesEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES);

		event.setTraitType(parentTraitTypeId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<TraitTypeResponseEvent> traitTypes = MessageUtil.compositeToCollection(responses, TraitTypeResponseEvent.class);
		Collections.sort((ArrayList) (traitTypes));

		if (traitTypes == null)
		{
			traitTypes = new ArrayList();
		}
		else
		{
			Collections.sort((List) traitTypes);
		}

		return traitTypes;
	}

	/*
     * 
     */
	public static Collection fetchParentChildTraitTypesForCategory(String aCategoryName, boolean isParent)
	{
		IEvent event = null;
		if (isParent)
		{
			event = (GetTraitParentByCategoryEvent) 
					EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETTRAITPARENTBYCATEGORY);
			((GetTraitParentByCategoryEvent) event).setTraitCategoryName(aCategoryName);
		}
		else
		{
			event = (GetTraitChildByCategoryEvent) 
					EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETTRAITCHILDBYCATEGORY);
			((GetTraitChildByCategoryEvent) event).setTraitCategoryName(aCategoryName);
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;
		Collection traitTypes = MessageUtil.compositeToCollection(responses, TraitTypeResponseEvent.class);
		
		//filter only for SCHOOL parent trait
		if("SCHOOL".equalsIgnoreCase(aCategoryName)){
		    traitTypes = filterTrait(traitTypes);
		};				

		if (traitTypes == null)
		{
			traitTypes = new ArrayList();
		}
		else
		{
			Collections.sort((List) traitTypes);
		}
		

		return traitTypes;
	}
	
	
	public static Collection filterTrait(Collection traitTypesCollection) {
	    
	    List<TraitTypeResponseEvent> activeTraits = new ArrayList<TraitTypeResponseEvent>();
	    
	    int itemCount = traitTypesCollection.size();
	    
	    for (int i = 0; i < itemCount; i++) {	
		    
		    TraitTypeResponseEvent event = ((ArrayList<TraitTypeResponseEvent>) traitTypesCollection).get(i); 
		    String status = event.getStatus();
			
		  //add active traits
		    if ("A".equalsIgnoreCase(status)){
			activeTraits.add(event);
		    }			
	    }
	    
	    return activeTraits;
	}
	

	/*
     * 
     */
	public static Map fetchAllTraitTypes()
	{
		GetJuvenileTraitTypesEvent event = (GetJuvenileTraitTypesEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES);

		event.setTraitType(null);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;
		Map traitMap = MessageUtil.groupByTopic(responses);

		// Remove the TerminationEvent
		traitMap.remove(UIConstants.EMPTY_STRING);

		if (traitMap != null)
		{
			Iterator iter = traitMap.values().iterator();
			while (iter.hasNext())
			{
				ArrayList myTempList = (ArrayList) iter.next();
				if (myTempList != null && myTempList.size() > 1)
					Collections.sort((List) myTempList);
			}
		}

		return traitMap;
	}

	/**
	 * @return Collection of unique referrals
	 */
	public static Collection fetchUniqueJuvenileCasefileReferralsList(String casefileId, String juvNum)
	{
		HashMap referralMap = new HashMap();
		Collection refs = fetchJuvenileProfileReferralsList(juvNum);
		Iterator<JuvenileProfileReferralListResponseEvent> refIterator = refs.iterator();
		JuvenileProfileReferralListResponseEvent evt;
		JJSOffense offse;
		String offnse= null;		
	
		while (refIterator.hasNext())
			{ 
			evt = refIterator.next();
			//38453 do not display refrrals of severity subtype of "R" (some referrals have multiple offenses)
			if(evt.getOffenses()!=null){
			Iterator<JJSOffense> offItr = evt.getOffenses().iterator();
			while (offItr.hasNext()){
				offse = offItr.next();
				offnse = offse.getOffenseCodeId();
				JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",offnse);
				//not attaching referrals with offense severity sub type of  "R"
				//richards changes added.
				if (!"R".equalsIgnoreCase(offCode.getSeveritySubtype()))
	                        {
	                              referralMap.put(evt.getReferralNumber(), evt);}
	                        }
			   }
			}
				
		
		
		Iterator ite2 = referralMap.keySet().iterator();
		Collection codeRefs = new ArrayList();
		CodeResponseEvent codeRef;
		String refNum;
		while (ite2.hasNext())
		{
			codeRef = new CodeResponseEvent();
			refNum = ite2.next().toString();
			codeRef.setCode(refNum);
			codeRef.setDescription(refNum);
			codeRefs.add(codeRef);
		}
		Collections.sort((List) codeRefs);

		return codeRefs;
	}

	/**
	 * Populates the juvenileCasefile form if the casefileId param is different
	 * from the existing form or the supervision(aka casefileid) is null
	 * 
	 * @param form
	 * @param casefileId
	 */
	public static void populateJuvenileCasefileForm(JuvenileCasefileForm form, String casefileId)
	{
		if (form == null)
		{
			return;
		}

		form.populateJuvenileCasefileForm(casefileId);
	}

	/**
	 * It's expected that only one of the boolean parameters will be true. If both
	 * are true both headers will be searched for a valid casefile number, note
	 * this is dangerous and may lead to a contamination of headers and should
	 * only be used when absolutely necessary.
	 * 
	 * @param aRequest
	 * @param isSearchJuvHeader
	 * @param isSearchCasefileHeader
	 * @return
	 */
	public static String getCasefileNumber(HttpServletRequest aRequest, boolean isSearchJuvHeader, boolean isSearchCasefileHeader)
	{
		// Get Casefile Number from Header Form
		String casefileNum = UIConstants.EMPTY_STRING;
		if (isSearchCasefileHeader)
		{
			JuvenileCasefileForm headerForm = UIJuvenileCaseworkHelper.getHeaderForm(aRequest);
			if (headerForm != null)
			{
				casefileNum = headerForm.getSupervisionNum();
			}
		}

		// look for "casefileId" on request
		if (nullOrEmptyString(casefileNum))
		{
			casefileNum = aRequest.getParameter(PDJuvenileCaseConstants.CASEFILEID_PARAM);
		}

		// look for "supervisionNum" on request
		if (nullOrEmptyString(casefileNum))
		{
			casefileNum = aRequest.getParameter(PDJuvenileCaseConstants.SUPERVISIONNUM_PARAM);
		}

		return casefileNum;
	}

	/*
     * 
     */
	public static JuvenileCasefileForm getHeaderForm(HttpServletRequest aRequest, boolean isCreate)
	{
		JuvenileCasefileForm myForm = getHeaderForm(aRequest);
		if (myForm == null)
		{
			HttpSession session = aRequest.getSession();
			myForm = new JuvenileCasefileForm();
			session.setAttribute(HEADER_FORM, myForm);
		}

		return myForm;
	}

	/**
	 * Added to get the header form for juvenile profile
	 * 
	 * @return HttpServletRequest
	 */
	public static JuvenileCasefileForm getHeaderForm(HttpServletRequest aRequest)
	{

		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm headerForm = (JuvenileCasefileForm) session.getAttribute(HEADER_FORM);

		return headerForm;
	}

	/**
	 * This method checks if a given supervision type is part of the specified
	 * supervison categor and returns true if so and false if the type is not part
	 * of the category.
	 */
	public static boolean isSupTypeInCat(String aSupTypeId, String aSupTypeCatId)
	{
		if (nullOrEmptyString(aSupTypeId) || nullOrEmptyString(aSupTypeCatId))
		{
			return false;
		}

		List myList = ComplexCodeTableHelper.getAllSupervisionCatTypes();
		if (myList != null)
		{
			Iterator<SupervisionTypeMapResponseEvent> iter = myList.iterator();
			while (iter.hasNext())
			{
				SupervisionTypeMapResponseEvent myRespEvt = iter.next();
				if (myRespEvt != null && myRespEvt.getSupervisionTypeId() != null)
				{
					if (aSupTypeId.equals(myRespEvt.getSupervisionTypeId()))
					{
						if (aSupTypeCatId.equals(myRespEvt.getSupervisionCatId()))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/*
     * 
     */
	public static List getAllSupTypesInCategory(String aSupCategoryId)
	{
		HashMap myTypes = new HashMap();
		ArrayList myTypesList = new ArrayList();
		if (aSupCategoryId != null && !aSupCategoryId.equals(UIConstants.EMPTY_STRING))
		{
			List myList = ComplexCodeTableHelper.getAllSupervisionCatTypes(); 

			if (myList != null)
			{
				Iterator<SupervisionTypeMapResponseEvent> iter = myList.iterator();
				while (iter.hasNext())
				{
					SupervisionTypeMapResponseEvent myRespEvt = iter.next();
					if (myRespEvt != null && myRespEvt.getSupervisionCatId() != null)
					{
						if (aSupCategoryId.equals(myRespEvt.getSupervisionCatId())&& 
								(myRespEvt.getSupervisionTypeStatus()!=null && (myRespEvt.getSupervisionTypeStatus().equals("ACTIVE")|| myRespEvt.getSupervisionTypeStatus().equals("A"))))
						{
							myTypes.put(myRespEvt.getSupervisionTypeId(), myRespEvt);
						}
					}
				}
			}
		}

		Collection myColl = myTypes.values();
		if (myColl != null && myColl.size() > 0)
		{
			Iterator myIter = myColl.iterator();
			while (myIter.hasNext())
			{
				myTypesList.add(myIter.next());
			}
		}

		return myTypesList;
	}
	
	public static List getAllSupTypesInSupervisionCategory(String aSupCategoryId)
	{
		HashMap myTypes = new HashMap();
		ArrayList myTypesList = new ArrayList();
		if (aSupCategoryId != null && !aSupCategoryId.equals(UIConstants.EMPTY_STRING))
		{
			List myList = ComplexCodeTableHelper.getAllSupervisionCategoryTypes();

			if (myList != null)
			{
				Iterator<SupervisionTypeMapResponseEvent> iter = myList.iterator();
				while (iter.hasNext())
				{
					SupervisionTypeMapResponseEvent myRespEvt = iter.next();
					if (myRespEvt != null && myRespEvt.getSupervisionCatId() != null)
					{
						if (aSupCategoryId.equals(myRespEvt.getSupervisionCatId()))
						{
							myTypes.put(myRespEvt.getSupervisionTypeId(), myRespEvt);
						}
					}
				}
			}
		}

		Collection myColl = myTypes.values();
		if (myColl != null && myColl.size() > 0)
		{
			Iterator myIter = myColl.iterator();
			while (myIter.hasNext())
			{
				myTypesList.add(myIter.next());
			}
		}

		return myTypesList;
	}

	/**
	 * Get supervision category for given sup type. Returns "" if not found;
	 * 
	 * @param aSupTypeId
	 * @returns the supTypeCatId
	 * @return
	 */
	public static String getSupCatFromType(String aSupTypeId)
	{

		if (nullOrEmptyString(aSupTypeId))
		{
			return (UIConstants.EMPTY_STRING);
		}

		List myList = ComplexCodeTableHelper.getAllSupervisionCategoryTypes(); //previous code: ComplexCodeTableHelper.getAllSupervisionCatTypes(); - returns only active types
		if (myList != null)
		{
			Iterator<SupervisionTypeMapResponseEvent> iter = myList.iterator();
			while (iter.hasNext())
			{
				SupervisionTypeMapResponseEvent myRespEvt = iter.next();
				if (myRespEvt != null && myRespEvt.getSupervisionTypeId() != null)
				{
					if (aSupTypeId.equals(myRespEvt.getSupervisionTypeId()))
					{
						return myRespEvt.getSupervisionCatId();
					}
				}
			}
		}

		return (UIConstants.EMPTY_STRING);
	}
	

	//<KISHORE>JIMS200059455 : Prog. Referral: Do not display canc. events(UI)-KK
	// There can be multiple charges per daLogNum where as only one charge per petition num
	// Fetch JOT charges for a petition or a daLogNumber based on the input
	public static void getJOTChargesForReferral(String petitionNum,String daLogNum,PetitionDetailsForm form){
		GetJuvenileCasefilePetitionEvent eve = new GetJuvenileCasefilePetitionEvent();
		if(StringUtils.isNotEmpty(daLogNum)){
			eve.setDaLogNum(daLogNum);
		}else if(StringUtils.isNotEmpty(petitionNum)){
			eve.setPetitionNum(petitionNum);
			form.setPetitionNum(petitionNum);
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(eve);
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		
		if(StringUtils.isNotEmpty(daLogNum)){
			Collection events = MessageUtil.compositeToCollection(response,JuvenileOffenderTrackingChargeResponseEvent.class);
			form.setJotCharges((ArrayList)events);
		}else if(StringUtils.isNotEmpty(petitionNum)){
			Object refObj = MessageUtil.filterComposite(response,JuvenileOffenderTrackingChargeResponseEvent.class);
			if( refObj != null )
			{
				JuvenileOffenderTrackingChargeResponseEvent resp = (JuvenileOffenderTrackingChargeResponseEvent)refObj;
				form.setCharge(resp.getCharge());
				form.setCJISNum(resp.getCJISNum());
				form.setCJISSuffixNum(resp.getCJISSuffixNum());
				form.setLawEnforcementAgency(resp.getLawEnforcementAgency());
				form.setPetitionNum(resp.getPetitionNum());
				form.setTotalPropertyLossAmount(resp.getTotalPropertyLossAmount());
				form.setGangRelated(resp.isGangRelated());
				form.setUnderTheInfluence(resp.getAlcoholOrDrugInfluence());
				form.setWeaponUsed(resp.isWeaponUsed());
				form.setOffenseDate(resp.getOffenseDate());
				form.setOffenseDegree(resp.getOffenseDegree());
				form.setLevelOfOffense(resp.getLevelOfOffense());
				form.setWeaponType(resp.getWeaponType());
				form.setDaLogNum(resp.getDaLogNum());
				//task 149503
				form.setProstInd(resp.getProstIndicator());
				//
				// form.setSummaryText(getSummaryText(petitionNum));
			}
		}
		
		
		form.resetPetitionDetails();
		
		// Summary of Facts
		Object factObj = MessageUtil.filterComposite(response,SummaryOfFactsResponseEvent.class);
		if( factObj != null )
		{
			SummaryOfFactsResponseEvent sum = (SummaryOfFactsResponseEvent)factObj;
			form.setSummaryOfFacts(sum.getSummaryOfFacts());
		}
		
		// Defendant details
		Object defendantObj = MessageUtil.filterComposite(response,JuvenileOffenderTrackingResponseEvent.class);		
		if( defendantObj != null )
		{
			JuvenileOffenderTrackingResponseEvent defResp = (JuvenileOffenderTrackingResponseEvent)defendantObj;
			form.setArrestDate(defResp.getArrestDate());
			form.setArrestTime(defResp.getArrestTime());
			form.setArrestingAgency( defResp.getFilingAgencyDesc() );
			form.setJuvenileCoActor(defResp.getCoDefendants());
			form.setIdentificationType(defResp.getIdentificationType());
		}
		
		// Adult Co-Actors
		Object coActorObj = MessageUtil.filterComposite(response,JuvenileOffenderTrackingCoActorResponseEvent.class);		
		if( coActorObj != null )
		{
			JuvenileOffenderTrackingCoActorResponseEvent actor =(JuvenileOffenderTrackingCoActorResponseEvent)coActorObj;
			Collection coll = actor.getCoActors();
			Iterator iter1 = coll.iterator();
			StringBuffer buff = new StringBuffer();
			while( iter1.hasNext() )
			{
				buff.append((String)iter1.next());
				if( iter1.hasNext() )
					buff.append(", ");
			}
			form.setAdultCoActor(buff.toString());
		}

		// getDispositions(form, petitionNum);
		Collection victims = MessageUtil.compositeToCollection(response,
				JuvenileOffenderTrackingComplainantResponseEvent.class);		
		form.setVictimOrWitness(victims);
	}
	
	
	/*
	 * given a String, return true if the String is null or empty
	 */
	private static boolean nullOrEmptyString(String str)
	{
		return (str == null || (str.trim().length() == 0));
	}
	
	//US 40492
	
	public static CodeResponseEvent setJPOOfRecord(Collection casefiles)
	{
		Iterator<JuvenileCasefileSearchResponseEvent> iter = casefiles.iterator();
		HashMap<String, JuvenileCasefileSearchResponseEvent> casefileMap = new HashMap<String, JuvenileCasefileSearchResponseEvent>();
		String jpoOfRecId="";
		String jpoOfRec="";
		while(iter.hasNext())
		{
			JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent)iter.next();
			
			if(resp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
			{
				//US 40492 check if there are any open referrals - if yes, then figure out JPO of record
	    		//get the referrals for the casefile 
	    		Collection<JuvenileCasefileReferralsResponseEvent> casefileRefs = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(resp.getSupervisionNum(), resp.getJuvenileNum());
	    		//check if any of them is open
	    		Iterator refsIter = casefileRefs.iterator();
	    		JuvenileCasefileReferralsResponseEvent refresp = null;
	    		boolean hasOpenRefs=false;
	    		
	    		while(refsIter.hasNext())
	    		{
	    			refresp = (JuvenileCasefileReferralsResponseEvent) refsIter.next();
	    			if(refresp.getRefCloseDate()==null)
	    			{
	    				hasOpenRefs=true;
	    				break;
	    			}
	    		}
	    		//if has open refs then check for JPO of record	
	    		if(hasOpenRefs)
	    		{
	    			if(!casefileMap.containsKey(resp.getSupervisionCategory()))
	    			{
	    				casefileMap.put(resp.getSupervisionCategory(), resp);
	    			}
	    			else
	    			{
	    				//replace with one that has been recently created
	    				JuvenileCasefileSearchResponseEvent respFromMap = (JuvenileCasefileSearchResponseEvent)casefileMap.get(resp.getSupervisionCategory());
	    				if(resp.getAssignmentDate()!=null && respFromMap.getAssignmentDate()!=null && (resp.getAssignmentDate().after(respFromMap.getAssignmentDate())))
	    					casefileMap.put(resp.getSupervisionCategory(), resp);
	    			}
	    		}//end hasOpenRefs				    	
	    		
			}//end if active
		}//end while
	
	
		//Now go through the HashMap and find the JPO according to rules
		if((JuvenileCasefileSearchResponseEvent)casefileMap.get("AR") != null)
		{		    			
			JuvenileCasefileSearchResponseEvent casefileAR = (JuvenileCasefileSearchResponseEvent)casefileMap.get("AR");
			//check if there is another rec with AC
			if((JuvenileCasefileSearchResponseEvent)casefileMap.get("AC") != null)
			{
				JuvenileCasefileSearchResponseEvent casefileAC = (JuvenileCasefileSearchResponseEvent)casefileMap.get("AC");
				if(casefileAC.getAssignmentDate().after(casefileAR.getAssignmentDate()))
				{
					//jpo will be the one on casefileAC
					jpoOfRecId = casefileAC.getOfficerLoginId();
					jpoOfRec = casefileAC.getProbationOfficerFullName();
					
				}//end if assessmentDate
				else
				{
					//jpo will be the one on casefileAR
					jpoOfRecId = casefileAR.getOfficerLoginId();
					jpoOfRec = casefileAR.getProbationOfficerFullName();
				}
				
			}//end if AC
			else
			{
				//jpo of rec will be the one with AC
				jpoOfRecId = casefileAR.getOfficerLoginId();	
				jpoOfRec = casefileAR.getProbationOfficerFullName();
			}//end else AC
		}//end else if AR
		else if((JuvenileCasefileSearchResponseEvent)casefileMap.get("AC") != null)
		{
			JuvenileCasefileSearchResponseEvent casefileAC = (JuvenileCasefileSearchResponseEvent)casefileMap.get("AC");
			//check if there is another rec with AR
			if((JuvenileCasefileSearchResponseEvent)casefileMap.get("AR") != null)
			{
				
				JuvenileCasefileSearchResponseEvent casefileAR = (JuvenileCasefileSearchResponseEvent)casefileMap.get("AR");
				if(casefileAC.getAssignmentDate().after(casefileAR.getAssignmentDate()))
				{
					//jpo will be the one on casefileAC
					jpoOfRecId = casefileAC.getOfficerLoginId();	
					jpoOfRec = casefileAC.getProbationOfficerFullName();
				}//end if assessmentDate
				else
				{
					//jpo will be the one on casefileAR
					jpoOfRecId = casefileAR.getOfficerLoginId();
					jpoOfRec = casefileAR.getProbationOfficerFullName();
				}
			}//end if AR
			else
			{
				//jpo will be one on AC rec
				jpoOfRecId = casefileAC.getOfficerLoginId();
				jpoOfRec = casefileAC.getProbationOfficerFullName();
			}
		}//end else if AC
		//task 162557 
		else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("ID") != null) //US 87430 
		{
		    JuvenileCasefileSearchResponseEvent casefileAD = (JuvenileCasefileSearchResponseEvent) casefileMap.get("ID");
		    jpoOfRecId = casefileAD.getOfficerLoginId();
		    jpoOfRec = casefileAD.getProbationOfficerFullName();
		}//
		else if((JuvenileCasefileSearchResponseEvent)casefileMap.get("DA") != null)
		{
			JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent)casefileMap.get("DA");
			//get briefing form from session and populate
			jpoOfRecId= casefileResp.getOfficerLoginId();
			jpoOfRec = casefileResp.getProbationOfficerFullName();
			
		}//end if DA //moved the code down for BUG 87437
		else if((JuvenileCasefileSearchResponseEvent)casefileMap.get("AD") != null)
		{
			JuvenileCasefileSearchResponseEvent casefileAD = (JuvenileCasefileSearchResponseEvent)casefileMap.get("AD");
			jpoOfRecId = casefileAD.getOfficerLoginId();
			jpoOfRec = casefileAD.getProbationOfficerFullName();
			
		}//end else if AD
		else if((JuvenileCasefileSearchResponseEvent)casefileMap.get("PP") != null)
		{
			JuvenileCasefileSearchResponseEvent casefilePP = (JuvenileCasefileSearchResponseEvent)casefileMap.get("PP");
			jpoOfRecId = casefilePP.getOfficerLoginId();
			jpoOfRec = casefilePP.getProbationOfficerFullName();
			
		}//end else if PP	
		CodeResponseEvent codeResp = new CodeResponseEvent();
		codeResp.setCode(jpoOfRecId);
		codeResp.setDescription(jpoOfRec);
		return codeResp;
	}
	
	public static OfficerProfile getCaseloadManager(String caseloadManagerId){	    
		
		OfficerProfile caseloadManagerProfile = OfficerProfile.findByLogonId(caseloadManagerId);
		
		return caseloadManagerProfile;
		
	}
	
}
