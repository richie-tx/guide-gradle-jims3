//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckReferralPreconditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JuvenileDeliquencyHistoryEvent;
import messaging.juvenilecase.reply.ReferralPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.ReferralPrefillResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.riskanalysis.CheckReferralPreconditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.riskanalysis.AdmonishmentHearingDockett;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskFormula;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.UIJuvenileHelper;

public class CheckReferralPreconditionsCommand implements ICommand
{
    static final long SECONDS_IN_A_DAY = (24 * 60 * 60);
    static final int NOT_FOUND = (-1);
    static final String Yes_STR = "Yes";
    static final String No_STR = "No";

    /**
     * @roseuid 4342C2CD0315
     */
    public CheckReferralPreconditionsCommand()
    {
    }

    /**
     * @param event
     * @roseuid 433C3D3D0212
     */
    public void execute(IEvent event)
    {
	/*
	 * <pre-condition> Casefile case status = active; supervision type=intake;
	 * status offender supervision; court supervison;deferred
	 * prosecution;deferred adjudication </pre-condition>
	 */
	//IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY );

	CheckReferralPreconditionsEvent preCondEvent = (CheckReferralPreconditionsEvent) event;
	JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCasefileID());
	boolean allowBypass = false;
	String assessmentType = "";

	//		if( juvCaseFile != null )
	//		{
	if (juvCaseFile.getCaseStatusId().equals("A"))
	{
	    if (preCondEvent.isNewReferral())
	    {
		assessmentType = RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL;
	    }
	    else
	    {
		assessmentType = RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL;
		allowBypass = true;
	    }

	    if ((juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) || 
		    juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES) || 
		    juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ)))
	    {

		//Check to see if a Risk Analysis Custody or Non-Custody exist, if one does, set it a Risk Analysis Object
		RiskAnalysis latestReferralRiskAnalysis = null;
		String juvenileNum = juvCaseFile.getJuvenileNum();
		RiskAnalysis latestCustodyeferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(juvenileNum, RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL);
		RiskAnalysis latestNonCustodyReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(juvenileNum, RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL);

		if (latestCustodyeferralRiskAnalysis != null && latestNonCustodyReferralRiskAnalysis != null)
		{
		    if (latestCustodyeferralRiskAnalysis.getEnteredDate().compareTo(latestNonCustodyReferralRiskAnalysis.getEnteredDate()) > 0)
		    {
			latestReferralRiskAnalysis = latestCustodyeferralRiskAnalysis;
		    }
		    else
		    {
			latestReferralRiskAnalysis = latestNonCustodyReferralRiskAnalysis;
		    }
		}
		else
		    if (latestCustodyeferralRiskAnalysis != null && latestNonCustodyReferralRiskAnalysis == null)
		    {
			latestReferralRiskAnalysis = latestCustodyeferralRiskAnalysis;
		    }
		    else
			if (latestNonCustodyReferralRiskAnalysis != null && latestCustodyeferralRiskAnalysis == null)
			{
			    latestReferralRiskAnalysis = latestNonCustodyReferralRiskAnalysis;
			}

		//If a Custody or Non-Custody  exist, compare entry date with assignment date
		if (latestReferralRiskAnalysis != null)
		{

		    /** Get's the latest assignment if there is one **/
		    List<AssignmentResponseEvent> assignments = new ArrayList();
		    Iterator<Assignment> assignmentIter = Assignment.findAll("caseFileId", juvCaseFile.getOID());
		    if (assignmentIter.hasNext())
		    {

			while (assignmentIter.hasNext())
			{
			    Assignment assignment = assignmentIter.next();
			    AssignmentResponseEvent resp = new AssignmentResponseEvent();
			    resp.setReferralNum(assignment.getReferralNumber());
			    resp.setAssignmentDate(assignment.getAssignmentAddDate());
			    assignments.add(resp);
			}
			Collections.sort(assignments);

			int sizeOfList = assignments.size();
			AssignmentResponseEvent resp = assignments.get(sizeOfList - 1);

			//The entered date time has to be set to zero because 
			//assignment has no time coming from m204.
			Calendar cal = Calendar.getInstance();
			cal.setTime(latestReferralRiskAnalysis.getEnteredDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date dateWithTimeRemoved = cal.getTime();
			latestReferralRiskAnalysis.setEnteredDate(dateWithTimeRemoved);
			latestReferralRiskAnalysis.setModified(false);

			int dateCompareResult = resp.getAssignmentDate().compareTo(latestReferralRiskAnalysis.getEnteredDate());
			if (dateCompareResult < 0 && !allowBypass) //True if Assignment is before entered date
			{
			    this.postErrorEvent(RiskAnalysisConstants.NEW_REFERAL_NEEDED);
			}

		    }

		}
	    }

	    boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), assessmentType);
	    if ( riskAnalysisExist && !allowBypass )
	    {
		this.postErrorEvent(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
	    }

	    ReferralPrefillResponseEvent refPrefillResp = new ReferralPrefillResponseEvent();

	    // get court status and petition allegation from JJS_Dockett on m204
	    AdmonishmentHearingDockett dockett = AdmonishmentHearingDockett.findByJuvenileNum(juvCaseFile.getJuvenileNum());
	    if (dockett == null)
	    {
		refPrefillResp.setCourtStatus(No_STR);
		refPrefillResp.setPetitionAllegation(No_STR);
	    }
	    else
	    {
		refPrefillResp.setCourtStatus(Yes_STR);
		if (dockett.getPetitionAllegation().toUpperCase().indexOf("VOP") == NOT_FOUND)
		{
		    refPrefillResp.setPetitionAllegation(No_STR);
		}
		else
		{
		    refPrefillResp.setPetitionAllegation(Yes_STR);
		}
	    }

	    // if this youth [JUVENILE_CASEFILE.JuvenileNumber] has any
	    // casefiles with casefile status=active and casefile supervision
	    // type = community, intensive, enhanced, or residential supervision
	    // then currently on probation.
	    boolean probationStatus = PDRiskAnalysisHelper.getProbationStatus(preCondEvent.getJuvenileNum());
	    refPrefillResp.setProbationStatus((probationStatus) ? Yes_STR : No_STR);

	    int numOfOffenses = 0;
	    List<String> referralNumList = new ArrayList();

	    // get number of offenses
	    for (Iterator<Assignment> referrals = Assignment.findAll("caseFileId", preCondEvent.getCasefileID()); referrals.hasNext(); /*empty*/)
	    {
		Assignment ref = referrals.next();

		//this.retrieveDetentionRecords(preCondEvent.getJuvenileNum(), ref.getReferralNumber());

		GetJuvenileCasefileOffensesEvent casefileOffensesEvent = (GetJuvenileCasefileOffensesEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEOFFENSES);
		casefileOffensesEvent.setJuvenileNum(preCondEvent.getJuvenileNum());
		casefileOffensesEvent.setReferralNum(ref.getReferralNumber());

		for (Iterator i = JJSOffense.findAll(casefileOffensesEvent); i.hasNext(); /*empty*/)
		{
		    i.next();
		    numOfOffenses++;
		}
		referralNumList.add(ref.getReferralNumber());
	    }

	    refPrefillResp.setNumofCharges(numOfOffenses);

	    if (referralNumList.size() > 0)
	    {
		Collections.sort(referralNumList);
		LinkedHashSet<String> hashSet = new LinkedHashSet<String>(referralNumList);
		referralNumList = new ArrayList<String>(hashSet);
		StringBuffer referralsInfo = new StringBuffer();
		for (int i =0; i<  referralNumList.size(); i++){
		    String referralNum = referralNumList.get(i);
		    if ( i > 0 ){
			referralsInfo.append(", ");
		    }
		    referralsInfo.append(referralNum);
		}
		refPrefillResp.setReferrals( referralsInfo.toString() );
	    }

	     MessageUtil.postReply(refPrefillResp);

	    JuvenileDeliquencyHistoryEvent juvDeliqHistEvent = PDRiskAnalysisHelper.getDelinquencyHistory(preCondEvent.getJuvenileNum(), numOfOffenses);
	    if (juvDeliqHistEvent != null)
	    { // BTW ... "juvDeliqHistEvent" will always be non-null
		MessageUtil.postReply(juvDeliqHistEvent);
	    }

	    //PDRiskAnalysisHelper.retrieveRiskQuestions( assessementType, dispatch);
	    this.getRiskQuestions(preCondEvent.isNewReferral());

	    //GET SCHOOL DETAILS:
	    Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(preCondEvent.getJuvenileNum());
	    if (schoolHistories.size() > 0)
	    {
		Collections.sort((List) schoolHistories, new SchoolHistoryComparator());

		JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp = (JuvenileSchoolHistoryResponseEvent) ((ArrayList) schoolHistories).get(0);

		if (mySchoolHistoryResp != null)
		{
		    refPrefillResp.setSchool(mySchoolHistoryResp.getSchool());
		    refPrefillResp.setGrade(mySchoolHistoryResp.getGradeLevelDescription());
		}
	    }

	}
	else
	{
	    this.postErrorEvent(UIConstants.EMPTY_STRING);
	}
	//		}
	//		else
	//		{
	//			ReferralPreConditionFailedResponseEvent preCondFailedEvent = new ReferralPreConditionFailedResponseEvent();
	//			preCondFailedEvent.setMessage( "CaseFile " + preCondEvent.getCasefileID() 
	//					+ " not found for the Juvenile Number " + preCondEvent.getJuvenileNum() );
	//			dispatch.postEvent( preCondFailedEvent );
	//		}
    }

    /*	public void retrieveDetentionRecords(String juvenileNum, String referralNum) {
    		
    		GetJuvenileDetentionFacilitiesEvent reqEvent = new GetJuvenileDetentionFacilitiesEvent();
    		reqEvent.setJuvenileNum(juvenileNum);
    		reqEvent.setReferralNum(referralNum);
    		
    		List <JJSFacility> jjsFacilities = CollectionUtil.iteratorToList(JJSFacility.findAll(reqEvent));
    	
    		JuvenileDetentionFacilitiesResponseEvent resp = null;
    		
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(new Date());
    		cal.roll(Calendar.DAY_OF_YEAR, -3);
    		cal.set(Calendar.HOUR, 0);
    		cal.set(Calendar.HOUR_OF_DAY, 0);
    		cal.set(Calendar.MILLISECOND, 0);
    		cal.set(Calendar.MINUTE, 0);
    		cal.set(Calendar.SECOND, 0);
    		
    		Date threeDaysAgo = cal.getTime();
    		
    		JJSFacility fac =  null;
    		for (int j = 0; j < jjsFacilities.size(); j++) {

    			fac = jjsFacilities.get(j);
    			if (fac.getAdmitDate().before(threeDaysAgo)){
    				continue;
    			}
    			resp = new JuvenileDetentionFacilitiesResponseEvent();
    			resp.setAdmitDate(fac.getAdmitDate());
    			resp.setReleaseDate(fac.getReleaseDate());
    			resp.setAdmitTime(fac.getAdmitTime());
    			resp.setReleaseTime(fac.getReleaseTime());
    			resp.setFacilityCode(fac.getFacilityCode());
    			resp.setAdmitReason(fac.getAdmitReason());
    			resp.setOriginalAdmitDate(fac.getOriginalAdmitDate());
    			MessageUtil.postReply(resp);
    		}

    		
    	}
    */
    private void postErrorEvent(String msg)
    {
	ReferralPreConditionFailedResponseEvent preCondFailedEvent = new ReferralPreConditionFailedResponseEvent();
	preCondFailedEvent.setMessage(msg);
	MessageUtil.postReply(preCondFailedEvent);

    }

    private void getRiskQuestions(boolean isNewReferral)
    {
	String assessmentType = null;
	if (isNewReferral)
	{
	    assessmentType = RiskAnalysisConstants.RISK_TYPE_DETENTION;
	}
	else
	{
	    assessmentType = RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL;
	}
	RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(assessmentType);
	if (activeFormula != null)
	{
	    PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
	}
	else
	{
	    this.postErrorEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
	}

    }

    /**
     * @param event
     * @roseuid 433C3D3D0214
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 433C3D3D0216
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param anObject
     * @roseuid 433C3D3D0218
     */
    public void update(Object anObject)
    {
    }
}
