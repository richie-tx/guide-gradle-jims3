package ui.juvenilecase.referral.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.GetJuvenileReferralDecisionCodeEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileReferralDecisionResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.SaveJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.km.util.Name;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.referral.form.JuvenileReferralForm;
import ui.security.SecurityUIHelper;

/**
 * @author nemathew
 */
public class DisplayManageAssignmentAction extends LookupDispatchAction
{

    /**
     * Manage Assignment
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addReferralAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.MANAGE_ASSIGNMENT);
	JuvenileReferralForm referralForm = (JuvenileReferralForm) aForm;
	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, referralForm);
	referralForm.setOffenseList(new ArrayList());
	referralForm.setReferralList(null);
	referralForm.setOperator(UIUtil.getCurrentUserID());
	referralForm.setOperatorDesc(UIUtil.getCurrentUserName());
	String juvNum = referralForm.getJuvenileNum();
	Map<String, JuvenileProfileReferralListResponseEvent> referralsMap = new HashMap<String, JuvenileProfileReferralListResponseEvent>();
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);
	ArrayList<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();

	    if (referral != null && referral.getCloseDate() == null)
	    {
		String sevSubType = "";
		//referral is not an administrative level offense, check the severitySubType of offense with SEQNUM = 1
		if (referral.getOffenses() != null)
		{
		    Iterator<JJSOffense> offenseItr2 = referral.getOffenses().iterator();
		    while (offenseItr2.hasNext())
		    {
			JJSOffense offense = offenseItr2.next();
			if (offense!= null && offense.getSequenceNum().equalsIgnoreCase("1"))
			{
			    if (offense.getOffenseCode() != null)
			    {
				sevSubType = offense.getOffenseCode().getSeveritySubtype();
				break;
			    }
			}
		    }
		    if (sevSubType != null && !sevSubType.equalsIgnoreCase("R") && !sevSubType.equalsIgnoreCase("Z")) //US 86851
		    {
			//locate the petition record with the highest sequence number (JUVENILE_PETITION. PetitionSequenceNumber) associated to the Juvenile Number and Referral Number from the setting record.
			//If there is an associated PETITION record, display the petition allegation. //of the highest seq Num 
			List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral(juvNum, referral.getReferralNumber());
			if (petitionResponses != null && !petitionResponses.isEmpty())
			{
			    Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
				@Override
				public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
				{
				    if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
					return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
				    else
					return -1;
				}
			    }));
			    Iterator<PetitionResponseEvent> petitionRespItr = petitionResponses.iterator();
			    while (petitionRespItr.hasNext())
			    {
				PetitionResponseEvent petitionResp = petitionRespItr.next();
				if (petitionResp != null)
				{
				    referral.setPetitionNumber(petitionResp.getPetitionNum());
				    referral.setOffense(petitionResp.getOffenseCodeId());
				    referral.setOffenseDesc(petitionResp.getOffenseShortDesc());
				    if ( "TRNDSP".equals(petitionResp.getOffenseCodeId()) 
						   || "TRNSIN".equals(petitionResp.getOffenseCodeId())
						   || "REGION".equals(petitionResp.getOffenseCodeId())
					           || "ISCOIN".equals(petitionResp.getOffenseCodeId())
					       ){
					       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
						   if ( referral.getReferralNumber().equals(transferredOffense.getReferralNum()) ) {
						       referral.setOffenseDesc( transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
						   }
					       }
					  
					   } 
				    if (petitionResp.getOffenseCodeId() != null)
				    {
					// Code copied from HandleJuvenileProfileTransferredOffensesSelectionAction
					GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
					jocEvent.setAlphaCode(petitionResp.getOffenseCodeId());
					List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
					if (events != null & !events.isEmpty())
					{
					    for (int x = 0; x < events.size(); x++)
					    {
						JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);

						//if (!"Y".equals(respEvent.getInactiveInd()) && petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
						if (petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()))
						{
						    referral.setOffenseCategory(respEvent.getCategory());
						    break;
						}
					    }
					}
				    }
				    break;
				}
			    }
			}
			else
			// if there isn�t an associated PETITION record, display the short description of the offense associated to the referral.
			// if multiple offenses, take offense with seqNum = 1 (oldest offense)
			{
			    Collection<JJSOffense> offenses = referral.getOffenses();
			    if (offenses != null)
			    {
				Iterator<JJSOffense> offenseItr = offenses.iterator();
				while (offenseItr.hasNext())
				{
				    JJSOffense offense = offenseItr.next();
				    if (offense.getSequenceNum().equalsIgnoreCase("1"))
				    {
					referral.setOffense(offense.getOffenseCodeId());
					referral.setOffenseCategory(offense.getCatagory());
					if ( "TRNDSP".equals(offense.getOffenseCodeId()) 
						   || "TRNSIN".equals(offense.getOffenseCodeId())
						   || "REGION".equals(offense.getOffenseCodeId())
					           || "ISCOIN".equals(offense.getOffenseCodeId())
					       ){
					       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
						   if ( referral.getReferralNumber().equals(transferredOffense.getReferralNum()) ) {
						       referral.setOffenseDesc( transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
						   }
					       }
					  
					   } else {
					       referral.setOffenseDesc(offense.getOffenseDescription());
					   }
					
					break;
				    }
				}
			    }
			}
			//referral.setProbationStartDateStr(DateUtil.dateToString(referral.getProbationStartDate(), DateUtil.DATE_FMT_1));
			//referral.setProbationEndDateStr(DateUtil.dateToString(referral.getProbationEndDate(), DateUtil.DATE_FMT_1));
			JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, referral.getReferralNumber());
			Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();
			//Cassandra confirmed that when there are multiple casefiles associated with a JUVNUM and REFNUM, then the casefile with the HIGHEST SEQNUM will determine the current supervision for the referral num. 
			// BUG 86562 
			/*Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			    @Override
			    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			    {
				if (evt1.getJuvSeqNum() != null && evt2.getJuvSeqNum() != null)
				return Integer.valueOf(evt1.getJuvSeqNum()).compareTo(Integer.valueOf(evt2.getJuvSeqNum()));
				else
				    return -1;
			    }
			}));*/
			//Bug #91705
			
			 // Needed to group by assignment date first RRY
			    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
				@Override
				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
				{
				    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
					    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
				    else
					return -1;
				    }
			    }));
			    
			  Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
				@Override
				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
				{				 
				 //89766 User-story
				    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
				    {
					  int seq1 = Integer.parseInt(evt1.getRefSeqNum()); //Bug #91871
					    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
					    Integer seq1Int = new Integer(seq1);
					    Integer seq2Int = new Integer(seq2);
					return seq1Int.compareTo(seq2Int);
				    }
				    else
					return -1;
				}
			    }));
			referral.setCasefileReferrals(casefileReferrals); //added the history of casefile referrals //87861
			Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
			 if(!caseFileRefItr.hasNext())
			    {
				referral.setAssignmentType(refCasefileList.getAssignmentType());
				referral.setAssignmentDate(refCasefileList.getAssignmentDate());
				referral.setSupervisionCategoryId(refCasefileList.getSupervisionCategoryId());
				referral.setSupervisionTypeId(refCasefileList.getSupervisionTypeId());
				referral.setSupervisionCategory(refCasefileList.getSupervisionCategory());
				referral.setSupervisionType(refCasefileList.getSupervisionType());
				referral.setJpoId(refCasefileList.getJpo());
			    }
			while (caseFileRefItr.hasNext())
			{
			    JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
			    referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
			    referral.setSupervisionType(caseFileReferral.getSupervisionType());
			    referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
			    referral.setAssignmentType(caseFileReferral.getAssignmentType());
			    referral.setAssignmentDate(refCasefileList.getAssignmentDate());
			    referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
			    OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
			    if (officerProfileResponse != null)
			    {
				referral.setJpoId(officerProfileResponse.getUserId());
				String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
				referral.setJpo(officerFullName);
			    }
			    break;
			}
			if (referral.getFinalDisposition() == null)
			{
			    referral.setCourtDate(null); //court date is same as the final disposition date, but only when there is a final disposition 
			}
			openReferralsOnlyList.add(referral);
			referralsMap.put(referral.getReferralNumber(), referral);//putting all the referrals that can be assigned via Manage in a Map
		    }
		}
	    } //end of if referral is OPEN (closed date != null)
	}//end of while

	if (openReferralsOnlyList.size() > 0)
	{
	    //sort referrals by ref num desc to display
	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) openReferralsOnlyList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return Integer.valueOf(evt1.getReferralNumber()).compareTo(Integer.valueOf(evt2.getReferralNumber()));
		    else
			return -1;
		}
	    }));
	    referralForm.setReferralListMap(referralsMap);
	    referralForm.setReferralList(openReferralsOnlyList);
	    referralForm.setIntakeDecisions(JuvenileCaseHelper.getReferralDecisions());
	    Collections.sort((List) referralForm.getIntakeDecisions(), JuvenileReferralDispositionCode.CodeComparator);
	    referralForm.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	    referralForm.setRefSources(JuvenileReferralHelper.getAllReferralSources());
	    referralForm.setAction("manageAssignmentfromRefBrief");
	    //clearing out the below fields for the REFRESH button 
	    referralForm.setAssignmentType("");
	    referralForm.setSupervisionCat("");
	    referralForm.setSupervisionType("");
	    referralForm.setJpo("");
	    referralForm.setDisbleAssignment("N");
	    referralForm.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	    return forward;
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "There are no open referrals for assignment"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("briefing");
	}
    }
    
    private static int getAgeInYears(Date ageDate)
    {
	if (ageDate == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(ageDate);
	Calendar now = Calendar.getInstance();

	int age = 0;
	age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (now.before(birthdate))
	{
	    age--;
	}
	return age;
    }

  
    /**
     * Submit (previously Next button to go to Controlling Ref section)
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("manageAssignment");
	String addAssignmntEligible = "logical";
	form.setUpdateMessage("");
	form.setSubsequentMessage("");

	OfficerProfileResponseEvent officerResp = new OfficerProfileResponseEvent();
	if (form.getJpo() != null && !(form.getJpo().equalsIgnoreCase("UVANC") && form.getJpo().equalsIgnoreCase("UVREC")))
	{
	    officerResp = JuvenileReferralHelper.validateJPOEntered(form);
	    if (officerResp == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Probation Officer is invalid. Please modify"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }else
	    {
		String officerFullName = officerResp.getLastName() + ", " + officerResp.getFirstName();
		form.setJpoName(officerFullName);
	    }
	}
	
	JJSJuvenile jjsJuv = JJSJuvenile.find(form.getJuvenileNum());
	Date bDate = jjsJuv.getBirthDate();
	int getAgeInYears=getAgeInYears(bDate);
	if(getAgeInYears > 21 || getAgeInYears < 10)
	    {
		ActionErrors errors = new ActionErrors();
		 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age exceeds assignment permissions.  Casefile assignment is not permitted. Contact Data Corrections for assistance"));
		 saveErrors(aRequest, errors);
		 return aMapping.findForward(UIConstants.FAILURE);
	    }
	
	
	//To get the list of referrals selected to do the manage assign
	String refNums[] = form.getSelectedRefToOverride();
	List<String> refListToAddAssgn = Arrays.asList(refNums[0].split(","));
	ArrayList<JuvenileProfileReferralListResponseEvent> addAssignmntRefrlsList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	if (refListToAddAssgn != null && refListToAddAssgn.size() > 0)
	{
	    for (int i = 0; i < refListToAddAssgn.size(); i++)
	    {
		String refNum = refListToAddAssgn.get(i);
		JuvenileProfileReferralListResponseEvent referralRespEvt = form.getReferralListMap().get(refNum);
		if (referralRespEvt != null)
		{
		    addAssignmntRefrlsList.add(referralRespEvt);
		}
	    }
	}//ends: get the list of referrals selected to do the assignment
	form.setSelectedReferrals(addAssignmntRefrlsList);
	
	//US 86648
	Iterator addAssignRefsItr = addAssignmntRefrlsList.iterator();
	while (addAssignRefsItr.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent overrideRefRespEvt = (JuvenileProfileReferralListResponseEvent) addAssignRefsItr.next();
	    if(overrideRefRespEvt != null && overrideRefRespEvt.getIntakeDecisionId()== null || overrideRefRespEvt.getIntakeDecDate() == null || overrideRefRespEvt.getIntakeDecisionId().equalsIgnoreCase("") || overrideRefRespEvt.getIntakeDecDate().equalsIgnoreCase("")){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "One or more referrals has missing referral details.  Please update required details before assignment"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	
	// Run a check to determine if the selected assignment entry has followed an acceptable sequence.  4.7.10
	String selectedSupervisionCat = form.getSupervisionCat();//
	Iterator selectedRefsItr = addAssignmntRefrlsList.iterator();
	String selectedAssignType = form.getAssignmentType();
	Map<Date, List<JuvenileProfileReferralListResponseEvent>> groupedReferrals = new HashMap<Date, List<JuvenileProfileReferralListResponseEvent>>();
	Map<Date, List<PetitionResponseEvent>> groupedPetitions = new HashMap<Date, List<PetitionResponseEvent>>();
	
	if (!selectedAssignType.equalsIgnoreCase("TRN"))
	{ //if TRN the assignment process associated to the referral will not follow a �logical sequence� of assignments
	    while (selectedRefsItr.hasNext())
	    {
		//if one selected referral doesn't qualify for manage, can't do manage on any of the selected referrals
		if(!addAssignmntEligible.equalsIgnoreCase("logical")){
		    break;
		}
		JuvenileProfileReferralListResponseEvent refRespEvt = (JuvenileProfileReferralListResponseEvent) selectedRefsItr.next();
		//87861
		if (selectedSupervisionCat.equalsIgnoreCase("PC"))
		{
		    if (refRespEvt.getSupervisionCategoryId() != null && refRespEvt.getSupervisionCategoryId().equalsIgnoreCase("RC"))
		    {
			addAssignmntEligible = "illogical";
			break;
		    }
		    else
		    {
			addAssignmntEligible = "logical"; //91085
		    }
		    
		    Collection<JuvenileCasefileReferral> casefileReferrals = refRespEvt.getCasefileReferrals();
		    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
		    while (caseFileRefItr.hasNext())
		    {
			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
			if (caseFileReferral != null)
			{

			    /**
			     * 4) [US 87861] If the entry supervision Assignment
			     * Type = Post Court, and the selected referral(s)
			     * does/do not include history of supervision
			     * assignment type or supervision category =
			     * Pre=Adjudication, then do not permit assignment.
			     * This is considered an illogical sequence. A
			     * Pre-Adjudication (court) level supervision should
			     * precede a Post-Court level supervision category.
			     */

			    if (caseFileReferral.getSupervisionCat() != null && caseFileReferral.getSupervisionCat().equalsIgnoreCase("AD"))
			    {
				addAssignmntEligible = "logical";
				break;
			    }
			    else
			    {
				addAssignmntEligible = "illogical"; //91085
			    }
			}
		    }
		    
		    //87861
		    /**
		     * 5) [US 87861] If the entry Assignment Type = Post Court,
		     * and the selected referral(s) includes a court
		     * disposition, {JUVENILE_REFERRAL.CourtDisposition} verify
		     * the court decision option code. If the court
		     * decision/disposition is considered a final decision code
		     * (Disposition has Option = �F� based on Court Decisions,
		     * JJS90.2), then do no permit referral assignment. Display
		     * on screen message: A final Court Decision is associated
		     * to the selected referral(s). Please create the correct
		     * Post-Adjudicated level assignment.
		     */
		    if (addAssignmntEligible!=null && !addAssignmntEligible.equalsIgnoreCase("illogical") && refRespEvt.getCourtDisposition() != null && !refRespEvt.getCourtDisposition().equalsIgnoreCase(""))
		    {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetJuvenileDispositionCodeEvent courtDispEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			courtDispEvt.setCode(refRespEvt.getCourtDisposition());
			dispatch.postEvent(courtDispEvt);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			List<JuvenileDispositionCodeResponseEvent> courtDispResps = (List<JuvenileDispositionCodeResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, JuvenileDispositionCodeResponseEvent.class);

			if (courtDispResps != null)
			{
			    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = courtDispResps.iterator();
			    if (juvCodeIter.hasNext())
			    {
				JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				/*if (dispResp != null && dispResp.getOptionCode() != null && dispResp.getOptionCode().equalsIgnoreCase("F"))
				{*/
				if (dispResp != null && dispResp.getCodeNum()!= null && (Integer.valueOf(dispResp.getCodeNum())>=1))
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A final Court Decision is associated to the selected referral(s).  Please create the correct Post-Adjudicated level assignment"));
				    saveErrors(aRequest, errors);
				    return aMapping.findForward(UIConstants.FAILURE);
				}
			    }
			}
		    }
		}
		//87861
		if (selectedSupervisionCat.equalsIgnoreCase("AD"))
		{
		    if (refRespEvt.getSupervisionCategoryId() != null && refRespEvt.getSupervisionCategoryId().equalsIgnoreCase("AR") || refRespEvt.getSupervisionCategoryId().equalsIgnoreCase("AC"))
		    {
			if (refRespEvt.getAssignmentType() != null && refRespEvt.getAssignmentType().equalsIgnoreCase("SBQ"))// US 87227
			{
			    addAssignmntEligible = "logical";
			    break;
			}
			else
			{
			    addAssignmntEligible = "illogical";
			    break;
			}
		    }
		    //if (refRespEvt.getIntakeDecisionId().equalsIgnoreCase("PIP") || refRespEvt.getIntakeDecisionId().equalsIgnoreCase("PET"))
		    if ( refRespEvt.getFund().equals("AD") )
		    {
			addAssignmntEligible = "logical";
			break;
		    }
		    else
		    {
			GetJuvenileReferralDecisionCodeEvent refDecisionEvt = (GetJuvenileReferralDecisionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEREFERRALDECISIONCODE);
			refDecisionEvt.setCode(refRespEvt.getIntakeDecisionId());
			List<JuvenileReferralDecisionResponseEvent> responses = MessageUtil.postRequestListFilter(refDecisionEvt, JuvenileReferralDecisionResponseEvent.class);
			
			for( int x=0;x< responses.size();x++){
			    
			    JuvenileReferralDecisionResponseEvent disp = responses.get(x);
			    if (disp != null)
				{
				    String tjpcCode = disp.getTJPCCode();
				  //  if (tjpcCode != null && tjpcCode.equalsIgnoreCase("030")) //commented for BUG 173077
				    if (tjpcCode != null && tjpcCode.equalsIgnoreCase("070") || tjpcCode.equalsIgnoreCase("000"))//added for BUG 173077
				    {
					addAssignmntEligible = "logical";
					break;
				    }
				    else{
					addAssignmntEligible = "illogicalAD";
					break;
				    }
			    }
			}

		    } //BUG 86351
		}// entry sup = AD , ENDS
		if (selectedSupervisionCat.equalsIgnoreCase("AR") || selectedSupervisionCat.equalsIgnoreCase("AC") || selectedSupervisionCat.equalsIgnoreCase("DA") || selectedSupervisionCat.equalsIgnoreCase("ID"))
		{
		    JuvenileDispositionCode juvResultCode = null;
		  
		    
		    if (refRespEvt.getAssignmentType() != null && refRespEvt.getAssignmentType().equalsIgnoreCase("REC"))// US 87340, Task:87347
		    {
			if (form.getAssignmentType().equalsIgnoreCase("SBQ"))
			{
			    addAssignmntEligible = "logical";
			    break;
			} //BUG 87487 
		    }
		    if (selectedSupervisionCat.equalsIgnoreCase("DA"))
		    {
			if ( !groupedReferrals.containsKey( refRespEvt.getPdaDate() ) ){
			    groupedReferrals.put(refRespEvt.getPdaDate(), new ArrayList<JuvenileProfileReferralListResponseEvent>() );
			}
			    
			groupedReferrals.get(refRespEvt.getPdaDate() ).add( refRespEvt );
			
			if (refRespEvt.getSupervisionCategoryId().equalsIgnoreCase("AR") || refRespEvt.getSupervisionCategoryId().equalsIgnoreCase("AC"))
			{
			    addAssignmntEligible = "illogical";
			    break;
			}
			if (refRespEvt.getFinalDisposition() == null || refRespEvt.getFinalDisposition().isEmpty()){ //BUG 86316
			    addAssignmntEligible = "illogicalDA";
			    break;
			}
			else{
			    juvResultCode = JuvenileDispositionCode.find("codeAlpha",refRespEvt.getFinalDisposition());
			    if (juvResultCode.getSubGroupInd() != null && !"I".equalsIgnoreCase(juvResultCode.getSubGroupInd()))
			    {
				addAssignmntEligible = "illogicalDA";
				break;
			    }
			}
		    } // entry sup = DA , ENDS
		    if (selectedSupervisionCat.equalsIgnoreCase("AR"))
		    {
			if ( !groupedReferrals.containsKey( refRespEvt.getDispositionDate() ) ){
			    groupedReferrals.put(refRespEvt.getDispositionDate(), new ArrayList<JuvenileProfileReferralListResponseEvent>() );
			}
			    
			groupedReferrals.get(refRespEvt.getDispositionDate()).add( refRespEvt );
			    
			if (refRespEvt.getFinalDisposition() == null || refRespEvt.getFinalDisposition().isEmpty()){ //BUG 86300
			    addAssignmntEligible = "illogicalAR";
			    break;
			}
			else
			{
			    juvResultCode = JuvenileDispositionCode.find("codeAlpha",refRespEvt.getFinalDisposition());
			    if (juvResultCode != null && !"F".equalsIgnoreCase(juvResultCode.getSubGroupInd()))
			    {
				addAssignmntEligible = "illogicalAR";
				break;
			    }
			}
		    }
		    if (selectedSupervisionCat.equalsIgnoreCase("AC"))
		    {
			if ( !groupedReferrals.containsKey( refRespEvt.getDispositionDate() ) ){
			    groupedReferrals.put(refRespEvt.getDispositionDate(), new ArrayList<JuvenileProfileReferralListResponseEvent>() );
			}
			    
			groupedReferrals.get(refRespEvt.getDispositionDate()).add( refRespEvt );
			
			if (refRespEvt.getFinalDisposition() == null || refRespEvt.getFinalDisposition().isEmpty()) //BUG 86342
			    {
				addAssignmntEligible = "illogicalAC";
				break;
			    } //BUG 86342 ENDS
			if (refRespEvt.getFinalDisposition() != null)
			{
			    juvResultCode = JuvenileDispositionCode.find("codeAlpha",refRespEvt.getFinalDisposition());
			    if (juvResultCode != null)
			    {
				if (!(juvResultCode.getSubGroupInd() != null && (juvResultCode.getSubGroupInd().equalsIgnoreCase("E") || juvResultCode.getSubGroupInd().equalsIgnoreCase("F"))))
				{
				    addAssignmntEligible = "illogicalAC";
				    break;
				}
			    }
			}

		    } // entry sup = AC , ENDS
		    
		    if (selectedSupervisionCat.equalsIgnoreCase("AR") || selectedSupervisionCat.equalsIgnoreCase("AC") || selectedSupervisionCat.equalsIgnoreCase("DA") ||  selectedSupervisionCat.equalsIgnoreCase("PC")) //87861
		    {
			if ("REC".equalsIgnoreCase(refRespEvt.getAssignmentType()))
			{
			    addAssignmntEligible = "illogical";
			    break;
			} //If the entry supervision category = Deferred Adjudication (DA), Post Adjudicated Residential (AR) or Post Adjudicated Community (AC) and the current supervision assignment type = Receiving, the sequence is illogical.
		    }

		}
	    } //for each selected referral ends
	} //if Assignment Type not TRN ENDS
	else
	{
	    
	    while (selectedRefsItr.hasNext())
	    {
		JuvenileProfileReferralListResponseEvent refRespEvt = (JuvenileProfileReferralListResponseEvent) selectedRefsItr.next();

		JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",refRespEvt.getOffense());
		if(offCode.getSeveritySubtype()!=null && !offCode.getSeveritySubtype().equalsIgnoreCase("T"))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This assignment type requires an out of state or out of county level offense: TRNSIN, TRNSDP, ISCOIN OR REGION. Please check assignment type"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		
	    }
	    
	}
	
	for (Map.Entry< Date, List<JuvenileProfileReferralListResponseEvent> > referrals : groupedReferrals.entrySet() ) {
	    List<PetitionResponseEvent> petitions = new ArrayList<PetitionResponseEvent>();
	    for ( JuvenileProfileReferralListResponseEvent referral : referrals.getValue() ){
		  List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral( form.getJuvenileNum() , referral.getReferralNumber() );
		  if ( petitionResponses !=null
			    && petitionResponses.size() > 0 ) {
			petitions.addAll( petitionResponses );
		  }
	    }
	    
	    if ( petitions != null
			&& petitions.size() > 0 ) {
		    
		    Collections.sort(petitions, new Comparator<PetitionResponseEvent>() {
		            @Override
		            public int compare(PetitionResponseEvent p1, PetitionResponseEvent p2) {
		                int severityComparison = p2.getSeverity().compareTo(p1.getSeverity());
		                if (severityComparison != 0) {
		                    return severityComparison;
		                }
		                return p1.getReferralNum().compareTo( p2.getReferralNum() );
		            }
		    });
	    }
	    
	    groupedPetitions.put(referrals.getKey(), petitions);
	    
	}
	

	
	    
	    
	    
	
	if (addAssignmntEligible.equalsIgnoreCase("illogical"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The supervision assignment is an illogical sequence"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	} 
	if (addAssignmntEligible.equalsIgnoreCase("illogical2"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This supervision is an illogical sequence"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	if (addAssignmntEligible.equalsIgnoreCase("illogicalAD"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The selected referral(s) does/do not qualify for Pre-Adjudication level assignment.   Please verify the referral(s) Intake Decision"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	if (addAssignmntEligible.equalsIgnoreCase("illogicalDA"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The selected referral(s) does/do not qualify for Deferred Adjudication level assignment"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	if (addAssignmntEligible.equalsIgnoreCase("illogicalAR"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The selected referral(s) does/do not qualify for Post- Adjudicated Residential level assignment.  Verify Court Decision"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	if (addAssignmntEligible.equalsIgnoreCase("illogicalAC"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The selected referral(s) does/do not qualify for Post- Adjudicated Community level assignment.  Verify Court Decision"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	  /* if (!selectedSupervisionCat.equalsIgnoreCase("AR") || selectedSupervisionCat.equalsIgnoreCase("AC") || selectedSupervisionCat.equalsIgnoreCase("DA") || selectedSupervisionCat.equalsIgnoreCase("ID")){
	       //then skip the controlling ref page and go directly to summary page
	       return aMapping.findForward("summary");
	   }*/
	//return aMapping.findForward(UIConstants.NEXT);
	boolean isExclError = false;
	Iterator selectedRefsItr2 = addAssignmntRefrlsList.iterator();
	while (selectedRefsItr2.hasNext())
	{

	    JuvenileProfileReferralListResponseEvent refRespEvt = (JuvenileProfileReferralListResponseEvent) selectedRefsItr2.next();
	      
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    SaveJJSReferralEvent saveEvent = (SaveJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.SAVEJJSREFERRAL);
	    saveEvent.setActionFlag("fromManageAssign"); //BUG 87290
	    saveEvent.setExclMessage("");
	    saveEvent = JuvenileReferralHelper.setSaveDetails(saveEvent, form, refRespEvt);
	    if ( "AR".equalsIgnoreCase( selectedSupervisionCat )
		    || "AC".equalsIgnoreCase( selectedSupervisionCat )
		    || "DA".equalsIgnoreCase( selectedSupervisionCat ) ){
		String tjpcDisp = "";
		if ( refRespEvt.getCourts() != null
			&& refRespEvt.getCourts().size() > 0 ) {
		    List<JJSCourt> courtList = new ArrayList<>();
		    for (JJSCourt court : ( List<JJSCourt>) refRespEvt.getCourts()  ) {
			
			if ( court.getReferralNumber().equalsIgnoreCase( refRespEvt.getReferralNumber() ) ) {
			    if ( ! "DA".equalsIgnoreCase( selectedSupervisionCat ) ){
				courtList.add(court);
			    } else {
				if ( !"AB".equalsIgnoreCase( court.getHearingType() ) ){
				    courtList.add(court);
				}
			    }
			}
			
		    }
		    
		    if ( courtList != null
			    && courtList.size() > 0 ) {
			Collections.sort(courtList, new Comparator<JJSCourt>(){
			    @Override
			    public int compare ( JJSCourt c1, JJSCourt c2 ) {
				return Integer.parseInt(c2.getSeqNumber()) - Integer.parseInt(c1.getSeqNumber() );
			    }
			});
		    }
		    
		    tjpcDisp = courtList.get(0).getTJPCDisp();
		}
		
		if ( refRespEvt.getTjpcDisp() == null
			||  (refRespEvt.getTjpcDisp() != null
				&& Integer.parseInt( refRespEvt.getTjpcDisp() ) < 99 ) ){
		    Date dateKey = null;
		    if (  "AR".equalsIgnoreCase( selectedSupervisionCat )
			    ||  "AC".equalsIgnoreCase( selectedSupervisionCat ) ){
			dateKey = refRespEvt.getDispositionDate();
		    }
		    if ( "DA".equalsIgnoreCase( selectedSupervisionCat ) ) {
			dateKey = refRespEvt.getPdaDate();
		    }
		    if ( ( groupedReferrals.get( dateKey ).size() >  1
			    && groupedPetitions.get( dateKey ).size() > 0
			    && groupedPetitions.get( dateKey ).get (0).getReferralNum().equals( refRespEvt.getReferralNumber() ) )
			    ||(  groupedReferrals.get( dateKey  ).size() == 1 ) ){
			saveEvent.setTJPCDisp( tjpcDisp );
		    } else {
			saveEvent.setTJPCDisp("910");
		    }
		}
			
	    }
	    dispatch.postEvent(saveEvent);
	    CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	    if (error != null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", error.getMessage()));
		 //89766 User-story
		if(error.getErrorLogId()!=null && error.getErrorLogId().equalsIgnoreCase("notificationError")){
		 // do nothing
	    	}
		else
	    	{ 
	    	    saveErrors(aRequest, errors);
		   return aMapping.findForward(UIConstants.FAILURE); // as per Cassandra any failure in sending notifications or MAYSI should still continue with assignment creation / update
	    	}
	    	 //89766 User-story
	    }
	    
	    if(StringUtils.isNotEmpty( saveEvent.getExclMessage())){
		isExclError = true; 
		 form.setMessage(saveEvent.getExclMessage());
	    }
	    
	    JuvenileCasefileReferralResponseEvent casefileRefResp = (JuvenileCasefileReferralResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileReferralResponseEvent.class);
	    
	    //update JJSREFERRAL FOR #89887
	    GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
	    jjsEvt.setJuvenileNum(saveEvent.getJuvenileNum());
	    jjsEvt.setReferralNum(saveEvent.getReferralNum());
	    Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

	    if (refIter.hasNext())
	    {
		JJSReferral ref = refIter.next();
		if (ref != null)
		{
		    if(saveEvent.getJpoID()!=null && !saveEvent.getJpoID().equalsIgnoreCase("")){
			ref.setJpoId(saveEvent.getJpoID());
		    }
		    if(saveEvent.getCtAssignJPOId()!=null && !saveEvent.getCtAssignJPOId().equalsIgnoreCase("")){
			ref.setCtAssignJPOId(saveEvent.getCtAssignJPOId());
		    }
		    if(saveEvent.getProbJPOId()!=null && !saveEvent.getProbJPOId().equalsIgnoreCase("")){
			ref.setProbJPOId(saveEvent.getProbJPOId());
		    }
		    
		    /*
		    if ( casefileRefResp.isUpdateTjjdRefDate() ) {
			ref.setTJJDReferralDate( casefileRefResp.getAssignmentAddDate() );
		    }
		    */
		    IHome home = new Home();
		    home.bind(ref);
		}
	    }
	  //update JJSREFERRAL FOR #89887
	    
	    //generate Manage Assignment Activity 
	   
	    String logonId = SecurityUIHelper.getLogonId();//get the user logged in
	    if (casefileRefResp != null)
	    {
		if (casefileRefResp.getCaseFileId() != null)
		{
		    Name fullName = new Name(form.getFirstName(), form.getMiddleName(), form.getLastName(), form.getNameSuffix());
		    StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
		    String comments = "Referral " + casefileRefResp.getReferralNumber() +  " details have changed to the following . Assignment " + casefileRefResp.getCaseFileId() + " has been generated.  Operator " + logonId + "' " + DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
		    UIJuvenileHelper.createActivity(casefileRefResp.getCaseFileId(), ActivityConstants.ASSIGNMENT_GENERATED, comments);
		}
	    }
	
	}
	
	if(isExclError){
	   //display duplicate message from above
	    form.setAction("manageAssignSuccess");
	}else{
	    
	    if(form.getJpo().equalsIgnoreCase("UVANC") || form.getJpo().equalsIgnoreCase("ANC"))
	    {
		  form.setMessage("Assignment details recorded.  JPO code UVANC will not create a new casefile or supervision record");
	    }
	    else if ((form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("SBQ"))  && (form.getAgeAtOffense() >= 10 && form.getAgeAtOffense() < 21))
		{
			form.setMessage("Assignment record for Juvenile: "+ form.getJuvenileNum()+" has been created.");
			form.setSubsequentMessage("The record has been assigned for record creation and SUBSEQUENT assignment to: " + form.getSubsequentCasefileId());
		    
		}
	    else
	    {
		form.setMessage("Assignment record for Juvenile: "+ form.getJuvenileNum()+" has been created.");
	    }
	    
	    form.setAction("manageAssignSuccess");
	}

	return refreshPage(aMapping, aForm, aRequest, aResponse);
    }

    /**
     * Back
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.BACK);
	return forward;
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward refreshPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm referralForm = (JuvenileReferralForm) aForm;
	Map<String, JuvenileProfileReferralListResponseEvent> referralsMap = new HashMap<String, JuvenileProfileReferralListResponseEvent>();
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(referralForm.getJuvenileNum());
	ArrayList<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    if (referral != null && referral.getCloseDate() == null)
	    {
		String sevSubType="";
		//if (referral.getMostSevereOffense() != null && !("R").equalsIgnoreCase(referral.getMostSevereOffense().getSeveritySubtype()))
		//referral is not an administrative level offense, check the severitySubType of offense with SEQNUM = 1
		if (referral.getOffenses() != null)
		{ 
		    Iterator<JJSOffense> offenseItr2 = referral.getOffenses().iterator();
		    while (offenseItr2.hasNext())
		    {
			JJSOffense offense = offenseItr2.next();
			if (offense.getSequenceNum().equalsIgnoreCase("1"))
			{
			    sevSubType = offense.getOffenseCode().getSeveritySubtype();
			    break;
			}
			
		    }
		    if (! "R".equalsIgnoreCase(sevSubType)){
		 
		    //locate the petition record with the highest sequence number (JUVENILE_PETITION. PetitionSequenceNumber) associated to the Juvenile Number and Referral Number from the setting record.
		    //If there is an associated PETITION record, display the petition allegation. //of the highest seq Num 
		    List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral(referralForm.getJuvenileNum(), referral.getReferralNumber());
		    if (petitionResponses != null && !petitionResponses.isEmpty())
		    {
			Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
			    @Override
			    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
			    {
				if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
				    return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
				else
				    return -1;
			    }
			}));	
			Iterator<PetitionResponseEvent> petitionRespItr = petitionResponses.iterator();
			while (petitionRespItr.hasNext())
			{
			    PetitionResponseEvent petitionResp = petitionRespItr.next();
			    if (petitionResp != null)
			    {
				referral.setPetitionNumber(petitionResp.getPetitionNum());
				referral.setOffense(petitionResp.getOffenseCodeId());
				referral.setOffenseDesc(petitionResp.getOffenseShortDesc());
				if (petitionResp.getOffenseCodeId() != null)
				{
				   // Code copied from HandleJuvenileProfileTransferredOffensesSelectionAction
				    GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
				    jocEvent.setAlphaCode(petitionResp.getOffenseCodeId());
				    List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
				    if (events != null & !events.isEmpty())
				    {
					for (int x = 0; x < events.size(); x++)
					{
					    JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);					   
					    
					    if (petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()))
					    {
						referral.setOffenseCategory(respEvent.getCategory());
						break;
					    }
					}
				    }
				}
				break;
			    }
			}
		    }
		    else
			// if there isn�t an associated PETITION record, display the short description of the offense associated to the referral.
			// if multiple offenses, take offense with seqNum = 1 (oldest offense)
		    {
			Collection<JJSOffense> offenses = referral.getOffenses();
			if (offenses != null)
			{
			    Iterator<JJSOffense> offenseItr = offenses.iterator();
			    while (offenseItr.hasNext())
			    {
				JJSOffense offense = offenseItr.next();
				if (offense.getSequenceNum().equalsIgnoreCase("1"))
				{
				    referral.setOffense(offense.getOffenseCodeId());
				    referral.setOffenseDesc(offense.getOffenseDescription());
				    referral.setOffenseCategory(offense.getCatagory());
				    break;
				}
			    }
			}
		    }

		    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(referralForm.getJuvenileNum(), referral.getReferralNumber());
		    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();
		    
		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			@Override
			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			{
			 //89766 User-story
			    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
			    {
				  int seq1 = Integer.parseInt(evt1.getRefSeqNum());
				    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
				    Integer seq1Int = new Integer(seq1);
				    Integer seq2Int = new Integer(seq2);
				return seq1Int.compareTo(seq2Int);
			    }
			    else
				return -1;
			}
		    }));
		    
		    //RRY-- Needed to group by assignment date first-- RRY
		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			@Override
			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			{
			    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
				    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
			    else
				return -1;
			    }
		    }));
		    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
		    if (!caseFileRefItr.hasNext())
		    {
			referral.setAssignmentType(refCasefileList.getAssignmentType());
			referral.setAssignmentDate(refCasefileList.getAssignmentDate());
			referral.setSupervisionCategoryId(refCasefileList.getSupervisionCategoryId());
			referral.setSupervisionTypeId(refCasefileList.getSupervisionTypeId());
			referral.setSupervisionCategory(refCasefileList.getSupervisionCategory());
			referral.setSupervisionType(refCasefileList.getSupervisionType());
			referral.setJpoId(refCasefileList.getJpo());
		    }
		    while (caseFileRefItr.hasNext())
		    {
			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
			referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
			referral.setSupervisionType(caseFileReferral.getSupervisionType());
			referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
			referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
			OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
			if (officerProfileResponse != null)
			{
			    referral.setJpoId(officerProfileResponse.getUserId());
			    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
			    referral.setJpo(officerFullName);
			}
			break;
		    }
		    if (referral.getFinalDisposition() == null)
		    {
			referral.setCourtDate(null); //court date is same as the final disposition date, but only when there is a final disposition 
		    }
		    openReferralsOnlyList.add(referral);
		    referralsMap.put(referral.getReferralNumber(), referral);//putting all the referrals that can be overridden in a Map
		}
	    }
	}
	}
	if (openReferralsOnlyList.size() > 0)
	{
	    //sort referrals by ref num desc
	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) openReferralsOnlyList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return Integer.valueOf(evt1.getReferralNumber()).compareTo(Integer.valueOf(evt2.getReferralNumber()));
		    else
			return -1;
		}
	    }));
	    referralForm.setReferralListMap(referralsMap);
	    referralForm.setReferralList(openReferralsOnlyList);
	}
	return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward newAssignCreate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("manageAssignment");
	form.setAssignmentType(""); 
	form.setSupervisionCat("");
	form.setSupervisionType("");
	form.setJpo("");
	form.setDisbleAssignment("N");
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    public ActionForward selectOfficerCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("manageAssignment");
    }
    
    public ActionForward changeSupervisionType(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("manageAssignment");
    }

    @Override
    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.manageAssignment", "addReferralAssignment");
	keyMap.put("button.submit", "next");
	keyMap.put("button.back", "back");
	keyMap.put("button.createNextAssignment", "newAssignCreate");
	keyMap.put("button.select", "selectOfficerCode");
	keyMap.put("button.changeSuprType", "changeSupervisionType");
	return keyMap;
    }
}
