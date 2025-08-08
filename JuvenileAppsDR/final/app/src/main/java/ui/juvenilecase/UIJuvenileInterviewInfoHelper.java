/*
 * Created on May 3, 2007
 * 
 */
package ui.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.interviewinfo.GetInterviewCreationDataEvent;
import messaging.interviewinfo.GetJuvenileBenefitsEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.interviewinfo.form.InterviewGuardian;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;

/**
 * @author awidjaja
 * 
 */
public class UIJuvenileInterviewInfoHelper {
	
	public static void populateInterviewForm(JuvenileInterviewForm form, HttpServletRequest aRequest)
	{
		
		//initiate JuvenilePhotoForm for displaying photo 
		JuvenilePhotoForm myPhotoForm=UIJuvenileHelper.getJuvPhotoForm(aRequest,true);
		
		//getting casefile Id from session
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
		if(casefileForm != null)
		{
		 	form.setCasefileId(casefileForm.getSupervisionNum());
		 	form.setJuvenileNum(casefileForm.getJuvenileNum());
		 	form.setJuvenileName(casefileForm.getJuvenileName());
		}
		else
		{
			//return failure?; cant proceed without casefileId
		}
		
		
	}
	
	public static JuvenileFamilyForm.Constellation populateFamilyFinancialInfo(String familyNumber) {
		JuvenileFamilyForm.Constellation theConstellation = null;
		
		GetFamilyConstellationDetailsEvent event= 
				   (GetFamilyConstellationDetailsEvent)EventFactory.getInstance(
				   JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONDETAILS);
		event.setConstellationNum(familyNumber);	

		// Getting PD Response Event	
		CompositeResponse response = postRequestEvent(event);
		Map dataMap = MessageUtil.groupByTopic(response);
		if (dataMap != null)
		{			
			Collection familiesMembers  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
			theConstellation.setFamilyNumber(familyNumber);
			UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(theConstellation,familiesMembers);
			
			Iterator iter =  theConstellation.getMemberList().iterator();
			while(iter.hasNext())
			{
				JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();			
				String famConstellationMemberNum = myMember.getFamilyConstellationMemberNum();
				String topic = famConstellationMemberNum
								+ "_"
								+ PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC;
	
				Collection financials  = (Collection) dataMap.get(topic);
				if(financials != null) //only guardian have financial info
				{
					Iterator finacialIter = financials.iterator();
					while(finacialIter.hasNext())
					{
						FamilyConstellationMemberFinancialResponseEvent financial = 
							(FamilyConstellationMemberFinancialResponseEvent) finacialIter.next();
						JuvenileFamilyForm.Guardian myGuardian=UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(financial);
						myGuardian.setMemberNumber(myMember.getMemberNumber());
						myGuardian.setConstellationMemberId(myMember.getFamilyConstellationMemberNum());
						myGuardian.setName(myMember.getMemberName());
						myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
						myGuardian.setDeceased(myMember.getDeceasedYesNo());
						UIJuvenileFamilyHelper.getEmploymentMemberInfo(myMember.getMemberNumber(),myGuardian);
						myGuardian.setInHomeStatus(myMember.isInHomeStatus());
						theConstellation.getGuardiansList().add(myGuardian);
					}
				}
				
			}
			theConstellation.setGuardiansList(UIJuvenileHelper.sortGuardianList((ArrayList)theConstellation.getGuardiansList()));
		}
		
		return theConstellation;
	}
	
	public static List getFamilyEmploymentInfo(String aMemberNum) {
		GetFamilyMemberEmploymentInfoEvent event = (GetFamilyMemberEmploymentInfoEvent) EventFactory
				.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBEREMPLOYMENTINFO);
		event.setMemberNum(aMemberNum);
		CompositeResponse response = postRequestEvent(event);
		
		Collection employmentInfo = 
			MessageUtil.compositeToCollection(
					response, FamilyMemberEmploymentInfoResponseEvent.class);
		
		List uiEmploymentList = new ArrayList();
		if(employmentInfo != null && employmentInfo.size() > 0) {
			for(Iterator emplIter = employmentInfo.iterator(); emplIter.hasNext();) {
				FamilyMemberEmploymentInfoResponseEvent empl = 
					(FamilyMemberEmploymentInfoResponseEvent)emplIter.next();
				uiEmploymentList.add( 
						UIJuvenileHelper.getJuvMemberFormMemberEmploymentInfoFROMEmploymentInfoRespEvt(empl));
			}	
		}
		
		Collections.sort(uiEmploymentList);
		
		
		return uiEmploymentList;

	}
	
	public static JuvenileFamilyForm.Constellation getJuvenileConstellationDetails(String juvenileNum) {
		JuvenileFamilyForm.Constellation currentConstellation = null;
		
		GetActiveFamilyConstellationEvent event =  (GetActiveFamilyConstellationEvent)EventFactory.getInstance(
				   JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
		 
		event.setJuvenileNum(juvenileNum);
		
		CompositeResponse response = postRequestEvent(event);
		Map dataMap = MessageUtil.groupByTopic(response);
		
		
		
		if (dataMap != null)
		{
			Collection families  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
			if(families!=null && families.size()>0){
				Iterator myIter=families.iterator();
				while(myIter.hasNext()){
					FamilyConstellationListResponseEvent myFamily=(FamilyConstellationListResponseEvent)myIter.next();
					if(myFamily.isActive()){
						currentConstellation=new JuvenileFamilyForm.Constellation();
						currentConstellation.setFamilyNumber(myFamily.getFamilyNum());
						currentConstellation.setActive(myFamily.isActive());
						
						Collection currentFamMembers = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
						UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(currentConstellation,currentFamMembers);
						break;
					}
				}
			}
		}
		return currentConstellation;
	}
	
	/**
	 * Only retain family members who are:
	 * - Guardian
	 * - Influential Adult, Step Mother or Step Father if they are in home (Other known Parent/Guardian)
	 * @param constellation
	 */
	public static void filterFamilyMembersForRequestAttyAppt(JuvenileFamilyForm.Constellation constellation) {
		
		List qualifiedMemberList = new ArrayList();
		
		for(Iterator memberIter = constellation.getMemberList().iterator();memberIter.hasNext();) {
			JuvenileFamilyForm.MemberList member = 
				(JuvenileFamilyForm.MemberList)memberIter.next();
			if(member.isGuardian() || 
				(member.getRelationshipToJuvId().equalsIgnoreCase("IA") ||
				member.getRelationshipToJuvId().equalsIgnoreCase("SF") ||
				member.getRelationshipToJuvId().equalsIgnoreCase("SM"))	) {
				qualifiedMemberList.add(member);
			}
		}
		
		constellation.setMemberList(qualifiedMemberList);
	}
	
	public static List getJuvenileGuardians(String juvenileNum){
		List guardians = new ArrayList();
		JuvenileFamilyForm.Constellation currentConstellation = 
			getJuvenileConstellationDetails(juvenileNum);
		
		if(currentConstellation != null)
		{			
			for(Iterator iter = currentConstellation.getMemberList().iterator();iter.hasNext();)
			{
				JuvenileFamilyForm.MemberList member = (JuvenileFamilyForm.MemberList)iter.next();
				if(member.isGuardian())
				{
					InterviewGuardian guardian = new InterviewGuardian();
					guardian.setGuardianInformation(member);
					guardians.add(guardian);
				}
			}
		}
		return guardians;
	}
	
	/**
	 * Ideally, interview creation data should be called once throughout the whole session.
	 * To avoid making multiple database request, it will need to compare juvenileNum & casefileId 
	 * in session.
	 * @param form
	 * @param sessionJuvenileNum
	 */
	public static void setInterviewCreationData(JuvenileInterviewForm form, String juvenileNum, String casefileNum)
	{
		GetInterviewCreationDataEvent getInterviewCreationDataEvent = new GetInterviewCreationDataEvent();
		if(form.getJuvenileNum() != null && form.getJuvenileNum().length() > 0) {
			getInterviewCreationDataEvent.setJuvenileId(form.getJuvenileNum());
		}
		else {
			getInterviewCreationDataEvent.setCasefileId( form.getCasefileId() );
		}

		CompositeResponse compositeResponse = postRequestEvent(getInterviewCreationDataEvent);
		Collection persons = MessageUtil.compositeToCollection( compositeResponse, InterviewPersonResponseEvent.class );
		
		for(Iterator iter = persons.iterator(); iter.hasNext();)
		{
			InterviewPersonResponseEvent re = (InterviewPersonResponseEvent)iter.next();
			if(re.getFormattedName() == null || re.getFormattedName().trim().length() < 1)
			{
				iter.remove();
			}
		}
//		form.getPersonsInterviewedList().clear();
//		Collections.sort((List) persons);
//		form.getPersonsInterviewedList().addAll(persons);
		Collection newlySorted=form.specialSortJuvFamMembersContacts(persons);
		form.getPersonsInterviewedList().clear();
		form.getPersonsInterviewedList().addAll(newlySorted);
		
	
		//since location is always for JUV agency, it can be loaded as static data
		Collection locations = MessageUtil.compositeToCollection( compositeResponse, LocationResponseEvent.class );
		Collections.sort((List)locations,LocationResponseEvent.JuvUnitNameComparator);
		form.getInterviewLocationList().clear();
		form.getInterviewLocationList().addAll(locations);
	
	}
	
	/**
	 * Utility method to help post method to the PD. Takes in a completed set event, posts it to the PD and runs it through MessageUtil.processReturnException
	 * before returning the composite response to the user
	 * @param event -- the RequestEvent to post to the PD
	 * @return -- the Composite Resposnse, can be null
	 */
	private static CompositeResponse postRequestEvent(RequestEvent event) {
		if(event==null){
			return null;
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);	
		return response;
	}
	
	public static Collection getBenefitsInfoList(String juvNumber)
	{
		//get benefits and insurance details
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
  		/*GetFamilyMemberBenefitsEvent event= 
			 (GetFamilyMemberBenefitsEvent)EventFactory.getInstance(
			 JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERBENEFITS);*/
		GetJuvenileBenefitsEvent event = new GetJuvenileBenefitsEvent();
		event.setJuvenileNum(juvNumber);
  		dispatch.postEvent(event);	   



  		// Getting PD Response Event	
  		CompositeResponse response = (CompositeResponse) dispatch.getReply();
  		// Perform Error handling
  		MessageUtil.processReturnException(response); 
		
		Collection benefits = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), JuvenileBenefitResponseEvent.class );
		
		JuvenileBenefitResponseEvent responseEvt;
		JuvenileMemberForm.MemberBenefit myBenefit;
		ArrayList myNewList=new ArrayList();
		
		if(benefits!=null && benefits.size()>0)
		{
			Iterator iter=benefits.iterator();
			while(iter.hasNext()){
				responseEvt=(JuvenileBenefitResponseEvent)iter.next();
				if(responseEvt!=null)
				{
					//myBenefit=UIJuvenileHelper.getJuvMemberFormMemberBenefitFROMBenefitRespEvt(responseEvt);
					myBenefit = new JuvenileMemberForm.MemberBenefit();
					myBenefit.setMemberBenefitId(responseEvt.getBenefitId());
					myBenefit.setEligibilityTypeId(responseEvt.getEligibilityTypeId());
					
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

					Date entryDate = responseEvt.getEntryDate();
					if(entryDate != null)
					{
						myBenefit.setEntryDate(format.format(entryDate));
					}
							
					//myBenefit.setEntryDate(responseEvt.getEntryDate());
					myBenefit.setEligibleForBenefits(responseEvt.isEligibleForBenefits());
					myBenefit.setReceivingBenefits(responseEvt.isReceivingBenefits());
					myBenefit.setReceivedByFirstName(responseEvt.getReceivedBy().getFirstName());
					myBenefit.setReceivedByMiddleName(responseEvt.getReceivedBy().getMiddleName());
					myBenefit.setReceivedByLastName(responseEvt.getReceivedBy().getLastName());
					myBenefit.setIdNumber(responseEvt.getIdNumber());
					myBenefit.setReceivedAmt(responseEvt.getReceivedAmt());
					myBenefit.setBenefitStatus(responseEvt.getBenefitStatus());
					if(myBenefit!=null)
						myNewList.add(myBenefit);
				}
			}
		}
		
		Collections.sort(myNewList);
		//return UIJuvenileHelper.sortMemberBenefitsList(myNewList);
		return myNewList;
		
	}
	
	public static List<CodeResponseEvent> loadUpdateBenefitStatuses()
	{
		List<CodeResponseEvent> statusCodes = SimpleCodeTableHelper.getCodesSortedByDesc("JUVENILE_BENEFIT_STATUS");
		List<CodeResponseEvent> returnStatusCodes = new ArrayList<CodeResponseEvent>();
    	for (int y=0; y<statusCodes.size(); y++)
    	{
    		CodeResponseEvent cre = (CodeResponseEvent) statusCodes.get(y);
    		if (!"CU".equalsIgnoreCase(cre.getCode() ) ) {
    			returnStatusCodes.add(cre);
    		}
    	}
    	statusCodes = null;
		return returnStatusCodes;
	}
	
	public static List<CodeResponseEvent> loadBenefitStatuses()
	{
	    List<CodeResponseEvent> statusCodes = SimpleCodeTableHelper.getCodesSortedByDesc("JUVENILE_BENEFIT_STATUS");
	    
	    return statusCodes;
	}
	
	
}
