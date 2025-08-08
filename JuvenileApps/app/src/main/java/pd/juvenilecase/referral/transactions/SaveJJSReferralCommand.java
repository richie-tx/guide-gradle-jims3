package pd.juvenilecase.referral.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import messaging.error.reply.ErrorResponseEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.juvenilecase.GetCasefilesForReferralsEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvReferralFamilyResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.notification.SendCasefileClosingNotificationEvent;
import messaging.notification.SendOverdueInterviewNotificationEvent;
import messaging.referral.GetJJSDecisionHistoryEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.SaveJJSReferralEvent;
import messaging.riskanalysis.GetRecentRiskAnalysisForJuvenileEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.PersistentEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.CalendarConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDTaskConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;
import pd.address.Address;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.person.ReasonNotDone;
import pd.common.util.NameUtil;
import pd.contact.officer.OfficerProfile;
import pd.exception.InvalidProbationOfficerException;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JJSFamily;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.JuvenileNotificationGenerator;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.referral.JJSDecisionHistory;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.JuvenileReferralOffenseBean;
import ui.security.SecurityUIHelper;

/**
 * @author ugopinath
 */
public class SaveJJSReferralCommand /*extends CasefileExtractionUtility*/implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	SaveJJSReferralEvent evt = (SaveJJSReferralEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	IHome home = new Home();
	String fromEmail = "jims2notification@itc.hctx.net";
	StringBuffer offenseCodes = new StringBuffer(100);
	String ageRestrict = "";
	boolean isError = false;
	boolean vopOffense = false;
	String casefileId = ""; //added for US 183149
	
	if (!(evt.getActionFlag() != null && (evt.getActionFlag().equalsIgnoreCase("fromOverRide") || evt.getActionFlag().equalsIgnoreCase("fromManageAssign"))))//BUG 87290
	{
	    JJSReferral ref = new JJSReferral();
	    if ( evt.getReferralNum() != null
		    && evt.getReferralNum() != ""
		    && Integer.parseInt(evt.getReferralNum()) >= 1000 ) {
    	    	ref.setReferralNum(evt.getReferralNum());
    	    	ref.setReferralDate(evt.getReferralDate());
    	    	ref.setIntakeDate(evt.getIntakeDate());
    	    	ref.setIntakeDecisionId(evt.getIntakeDecisionId());
    	    	ref.setTJPCDisp(evt.getTJPCDisp()); //US 179484
    	    	ref.setJuvenileNum(evt.getJuvenileNum());
    	    	ref.setReferralSource(evt.getReferralSource());
    	    	//default PIA status to P if ref source is 89 - task 169593
    	    	ref.setPIACode(evt.getPIAstatus());
    	    	ref.setOffenseTotal(evt.getOffenseTotal());
    	    	ref.setReferralTypeInd(evt.getReferralTypeInd());
    	    	//task 171521
    	    	if ( evt.getReferralTypeInd() != null)
    	    	{
    	    	    if(evt.getReferralTypeInd().equalsIgnoreCase("IC"))
    	    		ref.setCountyREFD("755");
    	    	    else if(evt.getReferralTypeInd().equalsIgnoreCase("CD"))
	    		ref.setCountyREFD("756");
    	    	    /*else if(evt.getReferralTypeInd().equalsIgnoreCase("TR"))
    	    	    {
    	    		String countyId="";
    	    		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
    	    		getEvent.setJuvenileNumber(evt.getJuvenileNum());
    	    		getEvent.setReferralNumber(evt.getReferralNum());
                	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);                	    
                	while(transOffenseReferralsIter.hasNext()) 
                	{
                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
                	    countyId=transOffenseReferral.getFromCountyCode();
                	    ref.setCountyREFD(countyId); 
                	}
                	  
    	    	    }*/
    	    	    else
    	    		ref.setCountyREFD("101");
    	    	}
    	    	//ref.setTJJDReferralDate(evt.getTJJDReferralDate());
    	    	ref.setTJJDReferralDate( evt.getReferralDate() );
    	    	ref.setJpoId(evt.getJpoID());
    	    	ref.setProbJPOId(evt.getProbJPOId()); //89887
    	    	ref.setCtAssignJPOId(evt.getCtAssignJPOId()); //89887
    	    	ref.setCreateUserID(SecurityUIHelper.getJIMSLogonId());
    	    	ref.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
    	    	ref.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
    	    	ref.setUpdateUserID("");
    	    	ref.setRecType("REFERRAL");
    	    	ref.setRefExcludedReporting(evt.getRefExcludedReporting());
    	    	ref.setsAbuse("U");
    	    	home.bind(ref);
    	    	
    	    	//call history
    	    	if( evt.getTJPCDisp() != null && StringUtils.isNotBlank(evt.getTJPCDisp())) {
    	    	    
        	    	this.updateJJSDecisionHistory(evt);
    	    	}

	    }
	    
	    //create each offense
	    Iterator offenseIter = evt.getOffenses().iterator();
	    while (offenseIter.hasNext())
	    {
		//ref.setIntakeDecision(new Code())
		JuvenileReferralOffenseBean offBean = (JuvenileReferralOffenseBean) offenseIter.next();
		JJSOffense offense = new JJSOffense();
		offense.setOffenseDate(offBean.getOffenseDate());
		offense.setOffenseCodeId(offBean.getOffenseCode());
		offense.setReferralNum(ref.getReferralNum());
		offense.setInvestigationNumber(offBean.getInvestigationNum());
		offense.setKeyMapLocation(offBean.getKeyMapLocation());
		offense.setSequenceNum(offBean.getSequenceNum());
		offense.setJuvenileNum(evt.getJuvenileNum());
		offense.setCreateUserID(SecurityUIHelper.getJIMSLogonId());
		offense.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		offense.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		offense.setLcDate(DateUtil.getCurrentDate());
		offense.setLcTime(Calendar.getInstance().getTime());
		offense.setLcUser(PDSecurityHelper.getLogonId());
		//Bug #81685 - get offense severity
		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", offBean.getOffenseCode());
		if (offenseCode != null)
		{
		    offense.setSeverity(offenseCode.getSeverity());
		    ageRestrict = offenseCode.getAgeRestrict();
		    if ("23".equals( offenseCode.getNumericCode() ) ) {
			vopOffense = true;
		    }
		}
		home = new Home();
		home.bind(offense);
		offenseCodes.append(offBean.getOffenseCode());
		if (offenseIter.hasNext())
		    offenseCodes.append(", ");
	    }
	}
	
	//User story 179507
	if ( "fromManageAssign".equalsIgnoreCase( evt.getActionFlag() )
		&& ( "AC".equalsIgnoreCase( evt.getSupervisionCat() )
			|| "AR".equalsIgnoreCase(  evt.getSupervisionCat() )
			|| "DA".equalsIgnoreCase(  evt.getSupervisionCat() ) )
	){
	    GetJJSReferralEvent JJSEvent = new GetJJSReferralEvent();
	    JJSEvent.setJuvenileNum( evt.getJuvenileNum() );	
	    JJSEvent.setReferralNum( evt.getReferralNum() );
	    Iterator<JJSReferral> referralIter = JJSReferral.findAll(JJSEvent);
	    while (referralIter.hasNext()) {
		    JJSReferral referral = (JJSReferral) referralIter.next();
		    if ( evt.getTJPCDisp() != null
			    && evt.getTJPCDisp() .length() > 0
			    && Integer.parseInt( evt.getTJPCDisp() ) > 99){
			referral.setTJPCDisp( evt.getTJPCDisp() );
			new Home().bind( referral );
		    }
	    };
	}
	// End of user story 179507 
	// moved Create Intake History to bottom rry
	JuvenileCasefileReferralResponseEvent casefileRefResp = new JuvenileCasefileReferralResponseEvent();
	//casefileRefResp.setReferralNumber(ref.getReferralNum()); Commented for override assignment
	casefileRefResp.setReferralNumber(evt.getReferralNum());
	casefileRefResp.setJuvenileNum(evt.getJuvenileNum());

	String jpoIdFromUI = "";
	if (evt.getJpoID() != null)
	{
	    jpoIdFromUI = evt.getJpoID();
	}
	else
	    if (evt.getCtAssignJPOId() != null)
	    {
		jpoIdFromUI = evt.getCtAssignJPOId();
	    }
	    else
		if (evt.getProbJPOId() != null)
		{
		    jpoIdFromUI = evt.getProbJPOId();
		}
	if (evt.getCasefileGenerate() != null && evt.getCasefileGenerate().equalsIgnoreCase("Y") && !(jpoIdFromUI.equalsIgnoreCase("UVANC") || jpoIdFromUI.equalsIgnoreCase("ANC")))
	{

	    OfficerProfile officer = null;
	    try
	    {
		officer = getOfficer(jpoIdFromUI);
	    }
	    catch (InvalidProbationOfficerException e)
	    {

	    }

	    String jpoId = officer.getOID().toString();
	    String juvNum = evt.getJuvenileNum();
	    String supTypeId = evt.getSupervisionType();
	    String referralNum = evt.getReferralNum();
	    Assignment assignment = null;
	    boolean assignmentCreated = false;
	    JuvenileCasefile casefile = JuvenileCasefile.find(jpoId, juvNum, supTypeId);

	    JJSJuvenile jjsJuv = JJSJuvenile.find(juvNum);
	    Date bDate = jjsJuv.getBirthDate();
	    GetJuvenileCasefileOffensesEvent jEvnt = new GetJuvenileCasefileOffensesEvent();
	    jEvnt.setJuvenileNum(juvNum);
	    jEvnt.setReferralNum(referralNum);
	    Iterator itr = JJSOffense.findAll(jEvnt);
	    JJSOffense jOffnse = new JJSOffense();
	    while (itr.hasNext())
	    {
		jOffnse = (JJSOffense) itr.next();
		break;

	    }
	    Boolean flg = true;
	    String offenseCd=jOffnse.getOffenseCodeId();
	    JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",offenseCd);

	    if (casefile == null)
	    {
		int ageAtOffense = getAgeAtOffense(bDate, jOffnse.getOffenseDate());
		if(( !evt.getAssignmentType().equalsIgnoreCase("SBQ")) 
			&& (ageAtOffense < 17 
				|| "23".equals(jOffnse.getOffenseNumericCode()) 
				|| ( ageAtOffense >= 17 
					&& ageAtOffense < 21 
					&& offenseCode.getSeveritySubtype().equals("T") ) ))
		{
		    flg = false;
		    casefile = JuvenileCasefile.find(juvNum, supTypeId);
		    //same casefile with a different jpo
		    if (casefile != null && !PDCodeTableConstants.STATUS_CLOSED.equalsIgnoreCase(casefile.getCaseStatusId()))
		    {
			if (this.doesReferralExists(casefile, referralNum))
			{
			    //TBD for Update. For create will never be true. Will fall true for Override
			    assignmentCreated = true;
			    casefile.setProbationOfficerId(jpoId);
			    casefile.setOfficerLastNameData(officer.getLastName());
			    casefile.setOfficerFirstNameData(officer.getFirstName());
			    casefile.setOfficerMiddleNameData(officer.getMiddleName());
			    casefile.setRectype("CASEFILE");

			    //89766 User-story
			    GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
			    searchEvent.setReferralNum(referralNum);
			    searchEvent.setJuvenileNum(evt.getJuvenileNum());

			    JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
			    resp.setReferralNumber(searchEvent.getReferralNum());

			    Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

			    Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
			    while (iter.hasNext())
			    {
				JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
				coll.add(caseRef);
			    }
			    resp.setCasefileReferrals(coll);
			    Collection<JuvenileCasefileReferral> casefilesResp = resp.getCasefileReferrals();
			    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
				@Override
				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
				{
				    int seq1 = Integer.parseInt(evt1.getRefSeqNum());
				    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
				    Integer seq1Int = new Integer(seq1);
				    Integer seq2Int = new Integer(seq2);
				    return seq1Int.compareTo(seq2Int);
				}
			    }));

			    Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResp.iterator();
			    //89766 User-story

			    Iterator<Assignment> assignmentIter = Assignment.findAll("caseFileId", casefile.getOID());
			    if (assignmentIter.hasNext())
			    {
				Assignment assgnmt = (Assignment) assignmentIter.next();
				if (assgnmt.getReferralNumber().equals(referralNum))
				{
				    Assignment newAssignment = new Assignment();
				    if (evt.getReferralDate() != null
						&& evt.getAssignmentDate() != null
						&& evt.getAssignmentDate().before( evt.getReferralDate() ) ){
					newAssignment.setAssignmentAddDate( evt.getReferralDate() );
				    } else {
					newAssignment.setAssignmentAddDate( evt.getAssignmentDate() );
				    }
				   // newAssignment.setAssignmentAddDate(assgnmt.getAssignmentAddDate());
				    newAssignment.setReferralNumber(assgnmt.getReferralNumber());
				    newAssignment.setAssignmentType(assgnmt.getAssignmentType());
				    // assgnmt.setAssignmentAddDate(evt.getAssignmentDate());
				    newAssignment.setOverrideReason(evt.getOverrideReason()); //US 71181
				    newAssignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181
				    if (casefilesRespItr.hasNext())
				    {
					JuvenileCasefileReferral casefileRef = casefilesRespItr.next();
					newAssignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRef.getRefSeqNum()) + 1));
				    }
				    casefile.insertAssignments(newAssignment);
				    home.bind(casefile);
				}
			    }
			}
			else
			{
			    //update the casefile with new jpo - Bug #83137
			    casefile.setProbationOfficerId(jpoId);
			    casefile.setOfficerLastNameData(officer.getLastName());
			    casefile.setOfficerFirstNameData(officer.getFirstName());
			    casefile.setOfficerMiddleNameData(officer.getMiddleName());
			    casefile.setRectype("CASEFILE");
			    // Send as a confirmation message 
			    evt.setExclMessage("Casefile already exists with this supervision type and a status not equal to Closed.  Assignment details added to Casefile# " + casefile.getOID());
			}
		    }//end if same casefile same supervision details different jpo
		    else
		    {
			try
			{
			    //Anytime a casefile is created a record is written by a DB trigger to JIMS2.JCJPOASSNMNTHIST. 
			    //This table is keeping a history of JPO assignments for a casefile.
			    casefile = generate(juvNum, jpoIdFromUI, supTypeId);
			    setRISKNEEDFlag(juvNum, evt.getSupervisionType(), evt.getSupervisionCat(), casefile);
			    home = new Home();
			    home.bind(casefile);
			    //retrieve the casefile again to get the seq num generated by the trigger.
			    casefile = JuvenileCasefile.find(jpoId, juvNum, supTypeId); //bug fix 22374 
			}
			catch (RuntimeException e)
			{
			    ErrorResponseEvent errorResp = new ErrorResponseEvent();
			    errorResp.setMessage("Error creating casefile." + e.getMessage());
			    dispatch.postEvent(errorResp);

			}
			catch (Exception e)
			{
			    ErrorResponseEvent errorResp = new ErrorResponseEvent();
			    errorResp.setMessage("Error creating casefile. " + e.getMessage());
			    dispatch.postEvent(errorResp);
			}
			try
			{
			    JuvenileNotificationGenerator.createTask(casefile, jpoId);
			}
			catch (RuntimeException e)
			{
			    ErrorResponseEvent errorResp = new ErrorResponseEvent();
			    errorResp.setMessage("Error occurred during the creation of Task after the successful generation of casefile. " + e.getMessage());
			    dispatch.postEvent(errorResp);

			}
			catch (Exception e)
			{
			    ErrorResponseEvent errorResp = new ErrorResponseEvent();
			    errorResp.setMessage("Error occurred during the creation of Task after the successful generation of casefile. " + e.getMessage());
			    dispatch.postEvent(errorResp);
			}
		    }//end else same casefile same supervision details different jpo   
		}
		else
		{
		    if (flg)
		    {
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("Juvenile's age exceeds assignment permissions. Casefile assignment is not permitted. Contact Data Corrections for assistance");
			dispatch.postEvent(errorResp);
		    }
		}

	    }//end if casefile not found with same jpo
	    else
	    {
		//casefile not equal null => casefile  found with same jpo, ref num and supTypeId
		if (!PDCodeTableConstants.STATUS_CLOSED.equalsIgnoreCase(casefile.getCaseStatusId()) && !"SBQ".equalsIgnoreCase(evt.getAssignmentType()))
		{
		    if (this.doesReferralExists(casefile, referralNum))//checks for assignment for the ref num in casefile
		    {
			 
			//in override
			assignmentCreated = true;
			casefile.setProbationOfficerId(jpoId);
			casefile.setOfficerLastNameData(officer.getLastName());
			casefile.setOfficerFirstNameData(officer.getFirstName());
			casefile.setOfficerMiddleNameData(officer.getMiddleName());
			casefile.setRectype("CASEFILE");
			home.bind(casefile);
			//the code below covers update of the assignment date
			//89766 User-story
			GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
			searchEvent.setReferralNum(referralNum);
			searchEvent.setJuvenileNum(evt.getJuvenileNum());

			JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
			resp.setReferralNumber(searchEvent.getReferralNum());

			Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

			Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
			while (iter.hasNext())
			{
			    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
			    coll.add(caseRef);
			}
			resp.setCasefileReferrals(coll);
			Collection<JuvenileCasefileReferral> casefilesResp = resp.getCasefileReferrals();
			if (casefileRefResp != null)
			{

			    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
				@Override
				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
				{
				    int seq1 = Integer.parseInt(evt1.getRefSeqNum());
				    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
				    Integer seq1Int = new Integer(seq1);
				    Integer seq2Int = new Integer(seq2);
				    return seq1Int.compareTo(seq2Int);

				}
			    }));

			    Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResp.iterator();
			    //89766 User-story
			    Iterator<Assignment> assignmentItr = Assignment.findAll("caseFileId", casefile.getOID());

			    while (assignmentItr.hasNext())
			    {
				Assignment assgnmt = (Assignment) assignmentItr.next();
				if (assgnmt.getReferralNumber().equals(referralNum))
				{

				    if (casefilesRespItr.hasNext())
				    {
					JuvenileCasefileReferral juvenileCasefileRef = casefilesRespItr.next();
					//need to talk to Uma on this change. RRY moved it to action for a confirmation message
					if (juvenileCasefileRef.getReferralNumber().equalsIgnoreCase(referralNum) && juvenileCasefileRef.getCaseFile().getProbationOfficerId().equalsIgnoreCase(jpoId) && juvenileCasefileRef.getCaseFile().getCasefileId().equalsIgnoreCase(casefile.getOID()) && juvenileCasefileRef.getSupervisionTypeCd().equalsIgnoreCase(supTypeId))
					{
					    evt.setExclMessage("Casefile " + casefile.getOID() + " already exists with this supervision type. No new assignment record created");
					}
					else
					{
					    Assignment newAssignment = new Assignment();
					    if (evt.getReferralDate() != null
							&& evt.getAssignmentDate() != null
							&& evt.getAssignmentDate().before( evt.getReferralDate() ) ){
						    newAssignment.setAssignmentAddDate( evt.getReferralDate() );
						} else {
						    newAssignment.setAssignmentAddDate(evt.getAssignmentDate());
						}
					    //newAssignment.setAssignmentAddDate(assgnmt.getAssignmentAddDate());
					    newAssignment.setReferralNumber(assgnmt.getReferralNumber());
					    newAssignment.setAssignmentType(assgnmt.getAssignmentType());
					    newAssignment.setOverrideReason(evt.getOverrideReason()); //US 71181
					    newAssignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181

					    if (juvenileCasefileRef != null)
					    {
						newAssignment.setSeqNum(String.valueOf(Integer.valueOf(juvenileCasefileRef.getRefSeqNum()) + 1));
					    }
					    casefile.insertAssignments(newAssignment);
					    home.bind(newAssignment);
					    
					}

				    }
				}
			    }
			}
		    }
		    else
		    {
			
			JJSJuvenile jjsJuvenile = JJSJuvenile.find(juvNum);
			Date birthDate = jjsJuvenile.getBirthDate();
			//  06/08/2012 - revised 7max age from 18 to 19 per ER 71590
			if ( getAgeInYears(birthDate) >= 17 && getAgeInYears(birthDate) < 22 && !"SBQ".equalsIgnoreCase(evt.getAssignmentType()))
			{
			   
			    GetJuvenileCasefileOffensesEvent jEvent = new GetJuvenileCasefileOffensesEvent();
			    jEvent.setJuvenileNum(juvNum);
			    jEvent.setReferralNum(referralNum);
			    Iterator iter = JJSOffense.findAll(jEvent);
			    boolean skipFlag = true;
			    StringBuffer referrals = new StringBuffer();
			    while (iter.hasNext())
			    {
				JJSOffense jOff = (JJSOffense) iter.next();
				int ageAtOffense = getAgeAtOffense(birthDate, jOff.getOffenseDate());
				 JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",jOff.getOffenseCodeId());
				
				if ( (ageAtOffense  < 17 
					|| "23".equals(jOffnse.getOffenseNumericCode()) 
					|| offCode.getSeveritySubtype().equals("T") ))
				{
				    assignment = this.createAssignment(evt, casefile);
				   
				    // Create Task for supervisionType = Deferred Adjudication, Prosecution
				    if (casefile.getSupervisionTypeId().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_ADJUDICATION_SUPERVISION) || casefile.getSupervisionTypeId().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION))
				    {
					String subject = "Schedule Overdue Deferred adjudication/prosecution for Juvenile # " + juvNum;
					this.scheduleOverdueInterviewTask(jpoIdFromUI, PDTaskConstants.MJCW_JPO_OVERDUE_DEFERREDADJUDICATION, subject, casefile, 240, evt.getAssignmentDate(), officer.getOfficerProfileId());
				    }
				    // End Task
				    // Send Casefile closing Notice to JPO
				    if (casefile.getSupervisionTypeId().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_STATUS_OFFENDER_SUPERVISION) || casefile.getSupervisionTypeId().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_PRE_ADJUDICATION_SUPERVISION) || casefile.getSupervisionTypeId().equalsIgnoreCase(UIConstants.CASEFILE_SUPERVISION_TYPE_INTAKE_SCREENING_SUPERVISION))
				    {
					this.sendPOStatusOffenderSupervisionNotification(evt, casefile, jjsJuvenile);
				    }
				    skipFlag = false;

				    referrals.append(assignment.getReferralNumber());
				    referrals.append(" on ");
				    referrals.append(assignment.getAssignmentAddDate().toString());
				    if (iter.hasNext())
				    {
					referrals.append(",");
				    }
				    assignmentCreated = true;
				    //.setUpdateTjjdRefDate(true);
				   // casefileRefResp.setAssignmentAddDate(assignment.getAssignmentAddDate());
				    assignment = null; 
				}
				jOff = null;
			    }
			    if (skipFlag)
			    {
				assignmentCreated = true;
				ErrorResponseEvent errorResp = new ErrorResponseEvent();
				errorResp.setMessage("Juvenile's age exceeds assignment permissions. Casefile assignment is not permitted. Contact Data Corrections for assistance");
				dispatch.postEvent(errorResp);
			    }
			    else
			    {
				try
				{
				    JuvenileNotificationGenerator.sendNewAssignmentUpdateJuvenileFor1718Notification(casefile, referrals.toString());
				}
				catch (RuntimeException e)
				{
				    ErrorResponseEvent errorResp = new ErrorResponseEvent();
				    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());
				    errorResp.setErrorLogId("notificationError"); //89877
				    dispatch.postEvent(errorResp);
				}
				catch (Exception e)
				{
				    ErrorResponseEvent errorResp = new ErrorResponseEvent();
				    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());
				    errorResp.setErrorLogId("notificationError"); //89877 Fixing nekhas bug as part this task
				    dispatch.postEvent(errorResp);
				}
			    }
			    jEvent = null;
			    iter = null;

			}//end if age >17 & <19

			else
			{
			    if (!"SBQ".equalsIgnoreCase(evt.getAssignmentType()))
			    {
				
				assignment = this.createAssignment(evt, casefile);
				// find all active cases for juvenile where current jpo is NOT same as the one assigned
				// to the subsequent referral and create notifications for jpos and clms, if any

				try
				{
				    JuvenileNotificationGenerator.sendNewAssignmentUpdateNotification(casefile, assignment.getReferralNumber());
				}
				catch (RuntimeException e)
				{
				    ErrorResponseEvent errorResp = new ErrorResponseEvent();
				    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());
				    errorResp.setErrorLogId("notificationError");
				    dispatch.postEvent(errorResp);
				}
				catch (Exception e)
				{
				    ErrorResponseEvent errorResp = new ErrorResponseEvent();
				    errorResp.setMessage("Error sending notification for new assignemnt. " + e.getMessage());
				    errorResp.setErrorLogId("notificationError");
				    dispatch.postEvent(errorResp);
				}
				assignmentCreated = true;
				//casefileRefResp.setUpdateTjjdRefDate(true);
				//casefileRefResp.setAssignmentAddDate(assignment.getAssignmentAddDate());
			    }
			    jjsJuvenile = null;
			    birthDate = null;
			}
//			Moved up to right before the clear			
//			casefileRefResp.setUpdateTjjdRefDate(true);
//			casefileRefResp.setAssignmentAddDate(assignment.getAssignmentAddDate());
			
		    }//end of else !age >17 & <19
		} //end of casefile  found with same jpo, ref num and supTypeId and casefile status is not closed
	    }//end else casefile found
	    if (!assignmentCreated && casefile != null && casefile.getOID() != null && !"SBQ".equalsIgnoreCase(evt.getAssignmentType()))
	    {
		//89766 User-story
		GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
		searchEvent.setReferralNum(evt.getReferralNum());
		searchEvent.setJuvenileNum(evt.getJuvenileNum());

		JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
		resp.setReferralNumber(searchEvent.getReferralNum());

		Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

		Iterator<JuvenileCasefileReferral> casefileIter = JuvenileCasefileReferral.findAll(searchEvent);
		while (casefileIter.hasNext())
		{
		    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) casefileIter.next();
		    coll.add(caseRef);
		}
		resp.setCasefileReferrals(coll);
		Collection<JuvenileCasefileReferral> casefilesResps = resp.getCasefileReferrals();
		Collections.sort((List<JuvenileCasefileReferral>) casefilesResps, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		    @Override
		    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		    {
			int seq1 = Integer.parseInt(evt1.getRefSeqNum());
			int seq2 = Integer.parseInt(evt2.getRefSeqNum());
			Integer seq1Int = new Integer(seq1);
			Integer seq2Int = new Integer(seq2);
			return seq1Int.compareTo(seq2Int);
		    }
		}));
		Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResps.iterator();
		//89766 User-story

		Iterator<Assignment> assignmentItr = Assignment.findAll("caseFileId", casefile.getOID());
		if (assignmentItr.hasNext())
		{
		    Assignment assgnmt = (Assignment) assignmentItr.next();
		    if (assgnmt.getReferralNumber().equals(referralNum))
		    {
			Assignment newAssignment = new Assignment();
			if (evt.getReferralDate() != null
				&& evt.getAssignmentDate() != null
				&& evt.getAssignmentDate().before( evt.getReferralDate() ) ){
			    newAssignment.setAssignmentAddDate( evt.getReferralDate() );
			} else {
			    newAssignment.setAssignmentAddDate(evt.getAssignmentDate());
			}
			//newAssignment.setAssignmentAddDate(assgnmt.getAssignmentAddDate());
			newAssignment.setReferralNumber(assgnmt.getReferralNumber());
			newAssignment.setAssignmentType(assgnmt.getAssignmentType());
			// assgnmt.setAssignmentAddDate(evt.getAssignmentDate());
			newAssignment.setOverrideReason(evt.getOverrideReason()); //US 71181
			newAssignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181
			if (casefilesRespItr.hasNext())
			{
			    JuvenileCasefileReferral juvCasefileRef = casefilesRespItr.next();
			    if (juvCasefileRef != null)
			    {
				newAssignment.setSeqNum(String.valueOf(Integer.valueOf(juvCasefileRef.getRefSeqNum()) + 1));
			    }
			}
			casefile.insertAssignments(newAssignment);
			home.bind(newAssignment);
			assignmentCreated = true;
		    }
		}
		if (!assignmentCreated)
		{
		    if (casefilesRespItr.hasNext())
		    {
			JuvenileCasefileReferral juvCasefileRef = casefilesRespItr.next();
			if (juvCasefileRef != null)
			{
			    assignment = new Assignment();
			    populateAssignment(evt, assignment);
			    if (assignment != null && juvCasefileRef.getRefSeqNum() != null)
			    {
				assignment.setSeqNum(String.valueOf(Integer.valueOf(juvCasefileRef.getRefSeqNum()) + 1));

			    }
			    casefile.insertAssignments(assignment);
			}
		    }
		    else
		    {
			assignment = new Assignment();
			populateAssignment(evt, assignment);
			assignment.setSeqNum("1");
			casefile.insertAssignments(assignment);
		    }
		}
		assignment.setCaseFileId(casefile.getOID().toString());
		home.bind(assignment);
	    }
	    if (assignment != null && casefile != null && casefile.getOID() != null)
	    {
		setMAYSIFlag(juvNum, assignment.getReferralNumber(), jpoId, assignment, casefile);
		setRiskAnalysisFlag(juvNum, evt.getSupervisionType(), evt.getSupervisionCat(), assignment.getAssignmentAddDate(), casefile);
		setRISKNEEDFlag(juvNum, evt.getSupervisionType(), evt.getSupervisionCat(), casefile);
		// send notification
		JuvenileNotificationGenerator.sendNewJuvenileCasefileNotification(casefile, officer);
	    }
	    //end assignment edits		
	    if (casefile != null && casefile.getOID() != null)
		casefileRefResp.setCaseFileId(casefile.getOID());
	    	casefileId = casefile.getOID(); //added for US 183149
	}

	//check for subsequent referral with level codes = B, N or J and send notice to JPO and CLM			
	if (!"fromOverRide".equalsIgnoreCase(evt.getActionFlag()) && "SBQ".equalsIgnoreCase(evt.getAssignmentType()))
	{ // as per CL, no Subsequent assignments happen via override 2/12/2019

	    OfficerProfile officer = null;
	    try
	    {
		officer = getOfficer(evt.getJpoID());
	    }
	    catch (InvalidProbationOfficerException e)
	    {

	    }
	    String jpoId = officer.getOID().toString();
	    String juvNum = evt.getJuvenileNum();
	    String supTypeId = evt.getSupervisionType();
	    String referralNum = evt.getReferralNum();
	    IHome ihome = new Home();
	    Assignment assignment = null;
	    JuvenileCasefile casefile = null;

	    if (evt.getSubsequentCasefileId() != null)
	    {
		casefile = JuvenileCasefile.find(evt.getSubsequentCasefileId());
		casefileId = casefile.getOID(); //added for US 183149
		if ( vopOffense ){
		    casefile.setVop(true);
		    ihome.bind(casefile);
		}
	    }
	    else
	    {
		casefile = JuvenileCasefile.find(jpoId, juvNum, supTypeId);
		casefileId = casefile.getOID(); //added for US 183149
	    }

	    //89766 User-story
	    GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
	    searchEvent.setReferralNum(referralNum);
	    searchEvent.setJuvenileNum(evt.getJuvenileNum());

	    JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	    resp.setReferralNumber(searchEvent.getReferralNum());

	    Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

	    Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
	    while (iter.hasNext())
	    {
		JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
		coll.add(caseRef);
	    }
	    resp.setCasefileReferrals(coll);

	    List<JuvenileCasefileReferral> casefileRefList = new ArrayList<JuvenileCasefileReferral>(resp.getCasefileReferrals());
	    List<JuvenileCasefileReferral> activeRefList = new ArrayList<JuvenileCasefileReferral>();
	    for (int x = 0; x < casefileRefList.size(); x++)
	    {

		JuvenileCasefileReferral refObj = casefileRefList.get(x);
		if ("A".equalsIgnoreCase(refObj.getCaseStatusCd()))
		{
		    activeRefList.add(refObj);
		}
	    }
	    Collection<JuvenileCasefileReferral> casefilesResp = activeRefList;//resp.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    int seq1 = Integer.parseInt(evt1.getRefSeqNum());
		    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
		    Integer seq1Int = new Integer(seq1);
		    Integer seq2Int = new Integer(seq2);
		    return seq1Int.compareTo(seq2Int);
		}
	    }));

	    Iterator<JuvenileCasefileReferral> casefilesRespItr = casefilesResp.iterator();
	    //89766 User-story
	    /*			Iterator<Assignment> assignmentItr = Assignment.findAll("caseFileId", casefile.getOID());
	    			if ( assignmentItr.hasNext() )
	    			{
	    				Assignment assgnmt = (Assignment) assignmentItr.next();
	    				if (assgnmt.getReferralNumber().equals(referralNum)) {
	    				    Assignment newAssignment = new Assignment();
	    				    newAssignment.setAssignmentAddDate( assgnmt.getAssignmentAddDate());		
	    				    newAssignment.setReferralNumber( assgnmt.getReferralNumber() );
	    				    newAssignment.setAssignmentType(evt.getAssignmentType());
	    				    // assgnmt.setAssignmentAddDate(evt.getAssignmentDate());
	    				    newAssignment.setOverrideReason(evt.getOverrideReason()); //US 71181
	    				    newAssignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181
	    				    if(casefilesRespItr.hasNext()){
	    					JuvenileCasefileReferral casefileRefferal = casefilesRespItr.next();
	    					 newAssignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRefferal.getRefSeqNum()) + 1));
	    				    }
	    				    newAssignment.setCaseFileId( casefile.getOID() );
	    				    ihome.bind( newAssignment );
	    				    casefile.insertAssignments(newAssignment);
	    				}
	    			}else{*/
	    if (casefilesResp != null)
	    {
		casefilesRespItr = casefilesResp.iterator();
		if (casefilesRespItr.hasNext())
		{
		    JuvenileCasefileReferral casefileRef = casefilesRespItr.next();
		    if (casefileRef.getReferralNumber().equals(referralNum) && !evt.getAssignmentType().equalsIgnoreCase(casefileRef.getAssignmentType()))
		    {
			assignment = new Assignment();
			if (evt.getReferralDate() != null
				&& evt.getAssignmentDate() != null
				&& evt.getAssignmentDate().before( evt.getReferralDate() ) ){
			    assignment.setAssignmentAddDate( evt.getReferralDate() );
			} else {
			    assignment.setAssignmentAddDate(evt.getAssignmentDate());
			}
			//assignment.setAssignmentAddDate(evt.getAssignmentDate());
			assignment.setReferralNumber(evt.getReferralNum());
			assignment.setAssignmentType(evt.getAssignmentType());
			assignment.setOverrideReason(evt.getOverrideReason()); //US 71181
			assignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181
			assignment.setSeqNum(String.valueOf(Integer.valueOf(casefileRef.getRefSeqNum()) + 1));
			assignment.setCaseFileId(casefile.getOID());
			ihome.bind(assignment);
			casefile.insertAssignments(assignment);
		    }
		}
		else
		{
		    assignment = new Assignment();
		    if (evt.getReferralDate() != null
				&& evt.getAssignmentDate() != null
				&& evt.getAssignmentDate().before( evt.getReferralDate() ) ){
			    assignment.setAssignmentAddDate( evt.getReferralDate() );
			} else {
			    assignment.setAssignmentAddDate(evt.getAssignmentDate());
			}
		    //assignment.setAssignmentAddDate(evt.getAssignmentDate());
		    assignment.setReferralNumber(evt.getReferralNum());
		    assignment.setAssignmentType(evt.getAssignmentType());
		    assignment.setOverrideReason(evt.getOverrideReason()); //US 71181
		    assignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181
		    assignment.setSeqNum("1");
		    assignment.setCaseFileId(casefile.getOID());
		    ihome.bind(assignment);
		    casefile.insertAssignments(assignment);
		}
	    }
	    
	    //Bug 184993 set assignment date for tjjdReferralDate(assignment type is subsequent, new referral is assigned to an existing active casefile )
	   /*if ( evt.getJuvenileNum() != null
		    && evt.getJuvenileNum().length() > 0
		    && evt.getReferralNum() != null
		    && evt.getReferralNum().length() > 0 ){
		GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
		refEvent.setJuvenileNum( evt.getJuvenileNum() );
		refEvent.setReferralNum( evt.getReferralNum() );

		Iterator referralRespItr  =  JJSReferral.findAll(refEvent);
		
		while ( referralRespItr.hasNext() ) {
		    JJSReferral referral = ( JJSReferral ) referralRespItr.next();
		    referral.setTJJDReferralDate(evt.getAssignmentDate());
		    home.bind(referral);
		}
	    }*/
	    //}
	    // Profile stripping fix task 97536	
	    //Juvenile juvenile = casefile.getJuvenile();
	    JuvenileCore juvenile = casefile.getJuvenile();
	    //
	    String juvenileName = juvenile.getFirstName() + " " + juvenile.getLastName();
	    String juvenileNum = juvenile.getJuvenileNum();
	    String officerJIMSLogonId = casefile.getProbationOfficer().getLogonId();
	    String referralDate = DateUtil.dateToString(evt.getAssignmentDate(), DateUtil.DATE_FMT_1);
	    JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, officerJIMSLogonId);

	    OfficerProfile officerProfile = OfficerProfile.find(jpoId);
	    officerJIMSLogonId = officerProfile.getManagerId();
	    //create CLM notification 
	    if (officerJIMSLogonId != null && !"".equals(officerJIMSLogonId))
	    {
		JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, officerJIMSLogonId);

		//send email notification as well
		StringBuffer message = new StringBuffer(100);
		SendEmailEvent sendEmailEvent = new SendEmailEvent();
		sendEmailEvent.setSubject("Subsequent Referral Assignment for Juvenile: " + juvenileName + " " + juvenileNum);
		sendEmailEvent.setMessage(message.toString());
		sendEmailEvent.setFromAddress(fromEmail);
		sendEmailEvent.addToAddress(officer.getEmail());
		sendEmailEvent.setMessage(juvenileName + " " + juvenileNum + " received subsequent Referral #: " + evt.getReferralNum() + " under Supervision# " + casefile.getOID() + ", for the following offense(s): " + offenseCodes);
		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(sendEmailEvent);
	    }

	    if (evt.getAssignmentType() != null && evt.getAssignmentType().equalsIgnoreCase("SBQ"))
	    {
		sendJPOSubsequentNotifications(evt, officer, casefile);
	    }

	    if (casefile != null && casefile.getOID() != null)
		casefileRefResp.setCaseFileId(casefile.getOID());
	}

	//added 3/5/2019 as per Cassandra, any changes in the Assignment should make an entry in intake history table, for override and manage as well...
	//Create Intake History
	if( !isError ){
	   
		JJSSVIntakeHistory intakeHistory = new JJSSVIntakeHistory();
		intakeHistory.setAssignmentType(evt.getAssignmentType());
		intakeHistory.setAssignmentDate(evt.getAssignmentDate());
		intakeHistory.setIntakeDate(evt.getIntakeDate());
		intakeHistory.setIntakeDecisionId(evt.getIntakeDecisionId());
		intakeHistory.setJpoId(jpoIdFromUI);
		intakeHistory.setJuvenileNum(evt.getJuvenileNum());
		intakeHistory.setReferralNumber(evt.getReferralNum());
		intakeHistory.setCreateUserID(UIUtil.getCurrentUserID());
		intakeHistory.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		intakeHistory.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		intakeHistory.setSupervisionTypeId(evt.getSupervisionType());
		intakeHistory.setCasefileId(casefileId); //added for US 183149
		home = new Home();
		home.bind(intakeHistory);
	}


	casefileRefResp.setOffenseCodes(offenseCodes + "");
	dispatch.postEvent(casefileRefResp);

    }

    /**
     * @param juvNum
     * @return
     */
    private boolean maysiExists(String juvNum) throws RuntimeException, Exception
    {
	GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
	queryEvent.setJuvenileNumber(juvNum);
	//		queryEvent.setReferralNumber( referralNumber );
	Iterator iter = JuvenileMAYSIComplete.findAll(queryEvent);
	SortedMap map = new TreeMap();
	// 08/30/2013
	// Revised to find first referral and check for existing MAYSI 
	//		if( iter.hasNext() )
	//		{
	//			return true;
	//		}		

	while (iter.hasNext())
	{
	    JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) iter.next();
	    map.put(jmc.getReferralNumber(), jmc);
	}
	List temp = new ArrayList(map.values());
	if (temp.size() > 0)
	{
	    JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) temp.get(0);
	    if ("1010".equals(jmc.getReferralNumber()))
	    {
		return true;
	    }
	}
	temp = null;

	return false;
    }

    /**
     * @param juvNum
     * @param referralNumber
     * @return
     */
    private boolean maysiExists(String juvNum, String referralNumber) throws RuntimeException, Exception
    {
	GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
	queryEvent.setJuvenileNumber(juvNum);
	queryEvent.setReferralNumber(referralNumber);
	Iterator iter = JuvenileMAYSIComplete.findAll(queryEvent);
	ArrayList<JuvenileMAYSIComplete> maysiList = new ArrayList<JuvenileMAYSIComplete>();
	while (iter.hasNext())
	{
	    JuvenileMAYSIComplete maysi =(JuvenileMAYSIComplete) iter.next();
	    if (maysi != null)
	    {
		maysiList.add(maysi);
	    }
	}
	//SortedMap map = new TreeMap();
	// 08/30/2013
	// Revised to find first referral and check for existing MAYSI 
	//		if( iter.hasNext() )
	//		{
	//			return true;
	//		}		

	/*while (iter.hasNext())
	{
	    JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) iter.next();
	    map.put(jmc.getReferralNumber(), jmc);
	}
	List temp = new ArrayList(map.values());*/
	/*if (temp.size() > 0)
	{
	    JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) temp.get(0);
	    if (referralNumber.equals(jmc.getReferralNumber()))
	    {
		return true;
	    }
	}*/
	//task 172536
	//if (temp.size() > 0)
	//sort iterator desc order of assessmentid	
	if(maysiList.size()>1)
	{
        	Collections.sort((List<JuvenileMAYSIComplete>) maysiList, Collections.reverseOrder(new Comparator<JuvenileMAYSIComplete>() {
        	@Override
                	public int compare(JuvenileMAYSIComplete evt1, JuvenileMAYSIComplete evt2)
                	{
                	    if (evt1.getJuvenileMAYSIAssessId() != null && evt2.getJuvenileMAYSIAssessId() != null)
                		return Integer.valueOf(evt1.getJuvenileMAYSIAssessId()).compareTo(Integer.valueOf(evt2.getJuvenileMAYSIAssessId()));
                	    else
                		return -1;
                	}
        	}));
	}
	Iterator<JuvenileMAYSIComplete> maysiItr = maysiList.iterator();
	if (maysiItr.hasNext())
	{		    
	    //JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) temp.get(0);
	    JuvenileMAYSIComplete jmc =(JuvenileMAYSIComplete)maysiItr.next();
	    if (jmc.getAssessmentOptionId() != null && jmc.getAssessmentOptionId().equals("T"))
	    {
        	    //JuvenileMAYSIComplete jmc = (JuvenileMAYSIComplete) iter.next();
        	    ReasonNotDone rnd =null;
        	    if(jmc.getReasonNotDoneId()!=null &&!jmc.getReasonNotDoneId().isEmpty())
        	    {
        		Iterator<ReasonNotDone> it = ReasonNotDone.findAll("description", jmc.getReasonNotDoneId());
        		while (it.hasNext()) 
        		{
        		    rnd = (ReasonNotDone) it.next();
        		    break;
        		}
        		if(rnd.getFinalReason()!=null)
        		{
                	    if (referralNumber.equals(jmc.getReferralNumber())&& rnd.getFinalReason().equalsIgnoreCase("YES"))//and finalreason yes and loop through all 
                	    {
                		return true;
                	    }
        		}
        	    }
	    }
	    /*else if(getAssessmentOptionId() != null && getAssessmentOptionId().equals("A"))
	    		return false;*/
	    else
	    		return true;    
	     	
	}
	
	//temp = null;

	return false;
    }

    /**
     * @param evt
     * @param casefile
     * @return
     */
    private Assignment createAssignment(SaveJJSReferralEvent evt, JuvenileCasefile casefile)
    {
	Assignment assignment = new Assignment();
	populateAssignment(evt, assignment);
	//89766 User-story
	GetCasefilesForReferralsEvent searchEvent = new GetCasefilesForReferralsEvent();
	searchEvent.setReferralNum(evt.getReferralNum());
	searchEvent.setJuvenileNum(evt.getJuvenileNum());

	JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	resp.setReferralNumber(searchEvent.getReferralNum());

	Collection<JuvenileCasefileReferral> coll = new ArrayList<JuvenileCasefileReferral>();

	Iterator<JuvenileCasefileReferral> juvCasefileRefItr = JuvenileCasefileReferral.findAll(searchEvent);
	while (juvCasefileRefItr.hasNext())
	{
	    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) juvCasefileRefItr.next();
	    coll.add(caseRef);
	}
	resp.setCasefileReferrals(coll);
	Collection<JuvenileCasefileReferral> casefilesResps = resp.getCasefileReferrals();
	//if (casefilesResps != null)
	//	{
	Collections.sort((List<JuvenileCasefileReferral>) casefilesResps, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
	    @Override
	    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
	    {
		int seq1 = Integer.parseInt(evt1.getRefSeqNum());
		int seq2 = Integer.parseInt(evt2.getRefSeqNum());
		Integer seq1Int = new Integer(seq1);
		Integer seq2Int = new Integer(seq2);
		return seq1Int.compareTo(seq2Int);
	    }
	}));
	//89766 User-story
	Iterator<JuvenileCasefileReferral> casefileRespItr = casefilesResps.iterator();
	if (casefileRespItr.hasNext())
	{
	    JuvenileCasefileReferral juvCasefileRef = casefileRespItr.next();
	    if (juvCasefileRef != null)
	    {
		assignment.setSeqNum(String.valueOf(Integer.valueOf(juvCasefileRef.getRefSeqNum()) + 1));
	    }
	}
	else
	{
	    assignment.setSeqNum("1");
	}
	casefile.insertAssignments(assignment);
	assignment.setCaseFileId(casefile.getOID().toString());
	assignment.setCreateUserID(UIUtil.getCurrentUserID());//assignment.setCreateUserID( PDJuvenileCaseConstants.CASEFILE_CREATOR );
	
	//User story 188618:  update tjjdReferralDate as assignment date when referral is assigned to the casefile
	if ( "1".equals( assignment.getSeqNum()  ) ) {
	    GetJJSReferralEvent JJSEvent = new GetJJSReferralEvent();
	    JJSEvent.setJuvenileNum( evt.getJuvenileNum() );
	    JJSEvent.setReferralNum( evt.getReferralNum() );
	    Iterator<JJSReferral> i = JJSReferral.findAll(JJSEvent);
        	while (i.hasNext())
        	{
        	    JJSReferral ref = (JJSReferral) i.next();
        	    ref.setTJJDReferralDate( assignment.getAssignmentAddDate() );
        	    new Home().bind( ref );
        	};
	}
	
	//}
	return assignment;
    }

    /**
     * @param jjsService
     * @param assignment
     */
    private void populateAssignment(SaveJJSReferralEvent evt, Assignment assignment)
    {
	if (evt.getReferralDate() != null
		&& evt.getAssignmentDate() != null
		&& evt.getAssignmentDate().before( evt.getReferralDate() ) ){
	    assignment.setAssignmentAddDate( evt.getReferralDate() );
	} else {
	    assignment.setAssignmentAddDate(evt.getAssignmentDate());
	}
	assignment.setReferralNumber(evt.getReferralNum());
	assignment.setAssignmentType(evt.getAssignmentType());
	assignment.setOverrideReason(evt.getOverrideReason()); //US 71181
	assignment.setOverrideOtherComments(evt.getOverrideOtherComment()); //US 71181
	//assignment.setAssignByUserId(jjsService.getAssignByUserId());
    }

    /**
     * @param casefile
     * @param referralNum
     * @return
     */
    private JuvenileCasefile generate(String juvNum, String officer, String supTypeId) throws RuntimeException, Exception
    {
	Juvenile juv = Juvenile.findJCJuvenile(juvNum);

	if (juv == null)
	{
	    // create juvenile master record
	    juv = createJuvenile(juvNum);
	    if (juv != null)
	    {
		// import family member info

		Collection<JuvReferralFamilyResponseEvent> juvRefFamily = UIJuvenileHelper.getFamilyInfoForMigratedRecordsWithoutCasefile(juvNum);
		Iterator familyIter = juvRefFamily.iterator();
		if (juvRefFamily.size() != 0)
		{
		    JuvReferralFamilyResponseEvent familyResp = (JuvReferralFamilyResponseEvent) familyIter.next();
		    if (familyResp.getJuvRefFamId() != null)
		    {
			JJSFamily juvfamily = populateJJSFamilyFromResp(familyResp, juvNum);
			FamilyConstellation familyConstellation = createFamilyConstellation(juvfamily, juv.getJuvenileNum());
			// add constellation to juvenile
			if (familyConstellation != null)
			{
			    juv.insertFamilyConstellationList(familyConstellation);
			}
			else
			{
			    //JuvenileNotificationGenerator.sendMissingConstellationNotification(juvNum, officer, jjsService.getAddDate());
			}
		    }
		}
	    }
	}

	// new Casefile needs to be initiated
	JuvenileCasefile casefile = new JuvenileCasefile();
	casefile.setJuvenileId(juvNum);

	OfficerProfile officerProf = getOfficer(officer);
	casefile.setProbationOfficer(officerProf);
	casefile.setSupervisionTypeId(supTypeId);
	casefile.setSequenceNumber("0"); //bug fix 22374 
	casefile.setRectype("CASEFILE");
	casefile.setCaseStatusId(PDJuvenileCaseConstants.CASESTATUS_PENDING);
	casefile.setSubabuse(true);
	casefile.setVop(false);
	casefile.setCreateTimestamp( new Timestamp(new Date().getTime()));
	//  casefile.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	casefile.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
	casefile.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);

	return casefile;
    }

    /**
     * @param juvenileNum
     */
    private static Juvenile createJuvenile(String juvenileNum) throws RuntimeException, Exception
    {
	Juvenile juvenile = null;
	JJSJuvenile jjsJuvenile = JJSJuvenile.find(juvenileNum);
	if (jjsJuvenile != null)
	{
	    Date birthDate = jjsJuvenile.getBirthDate();
	    //  06/08/2012 - revised max age from 17 to 19 per ER 71590
	    if (getAgeInYears(birthDate) < 10 || getAgeInYears(birthDate) > 19)
	    {
		throw new Exception("Juvenile can not be created as Juvenile age is not in between 10-19");
	    }

	    juvenile = new Juvenile();
	    populateJuvenileFromJJSJuvenile(jjsJuvenile, juvenile);
	    IHome home = new Home();
	    home.bind(juvenile);
	}
	return juvenile;
    }

    /**
     * @param jjsJuvenile
     * @param juvenile
     * @throws Exception
     * @throws
     */
    private static void populateJuvenileFromJJSJuvenile(JJSJuvenile jjsJuvenile, Juvenile juvenile)
    {
	juvenile.setJuvenileNum(jjsJuvenile.getJuvenileNum());
	//ER Fix:JIMS200077276 defaulting student id to the juvenileNum.
	juvenile.setStudentId(jjsJuvenile.getJuvenileNum());
	//juvenile.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	juvenile.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
	juvenile.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	juvenile.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
    }

    /**
     * @param jpoUserId
     * @return
     */
    private static OfficerProfile getOfficer(String jpoUserId) throws InvalidProbationOfficerException, RuntimeException, Exception
    {
	OfficerProfile officer = null;

	Iterator<OfficerProfile> officers = OfficerProfile.findAll("logonId", jpoUserId);
	if (officers.hasNext())
	{
	    while (officers.hasNext())
	    {
		officer = officers.next();
		String status = officer.getStatusId();
		if (status != null && status.equalsIgnoreCase("A"))
		{
		    return officer;
		}
	    }
	}
	else
	{
	    throw new InvalidProbationOfficerException("Invalid Probation Officer Exception");
	}

	return officer;
    }

    /**
     * @param casefile
     * @param referralNum
     * @return
     */
    private boolean doesReferralExists(JuvenileCasefile casefile, String referralNum) throws RuntimeException, Exception
    {
	Iterator<Assignment> refIter = casefile.getAssignments().iterator();
	while (refIter.hasNext())
	{
	    Assignment assignment = refIter.next();
	    if (referralNum.equalsIgnoreCase(assignment.getReferralNumber()))
	    {
		assignment = null;
		return true;
	    }
	    assignment = null;
	}
	refIter = null;

	return false;
    }

    /**
     * Method for creating the task to JPO for overdue Deferred
     * Adjudication/Prosecution Interview
     * 
     * @param probationOfficerId
     * @param topic
     * @param subject
     * @param casefile
     * @param hours
     * @param juvenileNum
     * @param addDate
     */

    private void scheduleOverdueInterviewTask(String ownerId, String topic, String subject, JuvenileCasefile casefile, int hours, Date addDate, String officerId) throws RuntimeException, Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	SendOverdueInterviewNotificationEvent interviewTask = (SendOverdueInterviewNotificationEvent) EventFactory.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEINTERVIEWNOTIFICATION);

	Calendar cal = Calendar.getInstance();
	Date testDate = new Date();

	cal.setTime(testDate);
	cal.add(Calendar.HOUR, hours);

	Date dueDate = cal.getTime();

	interviewTask.setDueDate(dueDate);
	interviewTask.setOwnerId(ownerId);
	interviewTask.setCasefileId(casefile.getOID().toString());
	interviewTask.setJuvenileNum(casefile.getJuvenileNum());
	interviewTask.setOfficerId(officerId);
	interviewTask.setSubmitAction("Link Create");
	interviewTask.setTaskTopic(topic);
	interviewTask.setTaskSubject(subject);
	interviewTask.setAlertType("Task");
	interviewTask.setIdentityType("jpo");

	// Register the task with the scheduler
	RegisterTaskEvent rtEvent = new RegisterTaskEvent();
	rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
	rtEvent.setFirstNotificationDate(dueDate);
	rtEvent.setNextNotificationDate(dueDate);
	String taskName = interviewTask.getClass().getName() + "-" + Math.random();
	rtEvent.setTaskName(taskName);
	rtEvent.setNotificationEvent(interviewTask);
	dispatch.postEvent(rtEvent);

	dispatch = null;
	interviewTask = null;
	cal = null;
	testDate = null;
	dueDate = null;
	rtEvent = null;
    }

    /**
     * @param updateEvent
     * @param casefileForm
     *            To JPO CasefileClosingNotification.
     */
    private void sendPOStatusOffenderSupervisionNotification(SaveJJSReferralEvent evt, JuvenileCasefile casefile, JJSJuvenile juvenile) throws RuntimeException, Exception
    {
	//added for 89887
	String jpoIdFromUI = "";
	if (evt.getJpoID() != null)
	{
	    jpoIdFromUI = evt.getJpoID();
	}
	else
	    if (evt.getCtAssignJPOId() != null)
	    {
		jpoIdFromUI = evt.getCtAssignJPOId();
	    }
	    else
		if (evt.getProbJPOId() != null)
		{
		    jpoIdFromUI = evt.getProbJPOId();
		}
	//added for 89887
	SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);

	String subject = "Status Offender Supervision has been open 30 days.";
	casefileNotifEvent.setSubject(subject);

	StringBuffer sb = new StringBuffer(juvenile.getLastName());
	sb.append(" ").append(juvenile.getFirstName()).append(" Supervision # ").append(casefile.getOID().toString()).append(" was assigned to you on ").append(DateUtil.dateToString(evt.getAssignmentDate(), DateUtil.DATE_FMT_1)).append(" and the case remains active. It has been 30 days since assignment.").append("\n");

	casefileNotifEvent.setNotificationMessage(sb.toString());
	String taskName = casefileNotifEvent.getClass().getName();
	// To JPO
	casefileNotifEvent.setIdentityType("jpo");
	casefileNotifEvent.setIdentity(jpoIdFromUI);
	casefileNotifEvent.setNoticeTopic("MJCW.M204.CASEFILE.ASSIGNMENT.NOTIFICATION");
	// Added as the command is used for both DB2 and M204 notices.
	casefileNotifEvent.setSupervisionNumber(casefile.getOID().toString());
	this.scheduleNotification(casefileNotifEvent, taskName, evt.getAssignmentDate(), 720);
	casefileNotifEvent = null;
    }

    /**
     * @param event
     * @param taskName
     * @param date
     * @param hours
     */
    public void scheduleNotification(PersistentEvent event, String taskName, Date date, int hours) throws RuntimeException, Exception
    {
	// create RegisterTaskEvent and post it
	RegisterTaskEvent rtEvent = new RegisterTaskEvent();
	rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);

	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.HOUR, hours);
	Date futureDate = cal.getTime();
	taskName += "_" + Math.random();

	rtEvent.setFirstNotificationDate(futureDate);
	rtEvent.setNextNotificationDate(futureDate);

	rtEvent.setTaskName(taskName);
	rtEvent.setNotificationEvent(event);
	// EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent );
	MessageUtil.postRequest(rtEvent);
	rtEvent = null;
	cal = null;
	futureDate = null;
    }

    private void sendJPOSubsequentNotifications(SaveJJSReferralEvent evt, OfficerProfile officer, JuvenileCasefile casefile) throws RuntimeException, Exception
    {
	//		String currentJPOID = officer.getOID().toString();
	// Profile stripping fix task 97536
	//Juvenile juvenile = casefile.getJuvenile();
	JuvenileCore juvenile = casefile.getJuvenile();
	//
	String juvenileName = juvenile.getFirstName() + " " + juvenile.getLastName();
	String juvenileNum = juvenile.getJuvenileNum();
	//		String officerJIMSLogonId = casefile.getProbationOfficer().getLogonId();
	String referralDate = DateUtil.dateToString(evt.getReferralDate(), DateUtil.DATE_FMT_1);
	String referralNum = evt.getReferralNum();
	SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
	search.setSearchType("JNUM");
	search.setJuvenileNum(juvenileNum);
	CompositeResponse responses = MessageUtil.postRequest(search);
	List casefiles = MessageUtil.compositeToList(responses, JuvenileCasefileSearchResponseEvent.class);
	List jpoList = new ArrayList();
	SortedMap jpoMap = new TreeMap();

	if (casefiles != null)
	{
	    for (int x = 0; x < casefiles.size(); x++)
	    {
		JuvenileCasefileSearchResponseEvent event = (JuvenileCasefileSearchResponseEvent) casefiles.get(x);
		if ("ACTIVE".equals(event.getCaseStatus()))
		{
		    jpoMap.put(event.getJpoId(), event);
		}
	    }
	    jpoList = new ArrayList(jpoMap.values());
	    String activeCaseCLMLogonId = "";
	    for (int y = 0; y < jpoList.size(); y++)
	    {
		JuvenileCasefileSearchResponseEvent event = (JuvenileCasefileSearchResponseEvent) jpoList.get(y);
		//create JPO notification
		try
		{
		    JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, event.getOfficerLoginId());
		}
		catch (RuntimeException e)
		{

		}
		catch (Exception e)
		{

		}
		//create CLM notification
		OfficerProfile officerProfile = OfficerProfile.find(event.getJpoId());
		activeCaseCLMLogonId = officerProfile.getManagerId();
		if (activeCaseCLMLogonId != null && !"".equals(activeCaseCLMLogonId))
		{
		    try
		    {
			JuvenileNotificationGenerator.sendSubsequentReferralNotification(juvenileName, juvenileNum, referralNum, referralDate, activeCaseCLMLogonId);
		    }
		    catch (RuntimeException e)
		    {

		    }
		    catch (Exception e)
		    {

		    }
		}
	    }
	    activeCaseCLMLogonId = null;
	}
	jpoList = null;
	jpoMap = null;

    }

    /**
     * @param juvNum
     * @param referralNumber
     * @param casefile
     * @throws Exception
     * @throws RuntimeException
     */
    private void setMAYSIFlag(String juvNum, String referralNumber, String supervisionTypeId, Assignment assignment, JuvenileCasefile casefile) throws RuntimeException, Exception
    {
	//for Deferred Adjudication Casefiles, the maysi flag is set to false in the setRiskAnalysisFlag method no matter what this method returns
	if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase(supervisionTypeId))
	{
	    casefile.setIsMAYSINeeded(false); 
	    return;
	}
	//add a check to see if current casefile maysi flag bug 104412
	if(casefile.getIsMAYSINeeded())
	    casefile.setIsMAYSINeeded(true);
	else
	    casefile.setIsMAYSINeeded(false);
	//
	if (!maysiExists(juvNum, referralNumber))//&& casefile.getCaseStatus().equals("A")
	{	   
	    casefile.setIsMAYSINeeded(true);
	    try
	    {
		OfficerProfile officer = casefile.getProbationOfficer();
		String officerJIMSLogonId = officer.getLogonId();
		casefile.getSequenceNumber();

		JuvenileNotificationGenerator.sendNotificationToConductMAYSI(juvNum, officerJIMSLogonId, assignment);
	    }
	    catch (RuntimeException e)
	    {
		e.printStackTrace();
		throw new Exception("Error Occurred during the MAYSI Notification");
	    }
	    catch (Exception e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}
    }

    /**
     * @param juvenileNum
     * @param supervisionType
     * @param assignmentAddDate
     * @param casefile
     */
    private void setRiskAnalysisFlag(String juvenileNum, String supervisionTypeId, String supervisionCategory, Date assignmentAddDate, JuvenileCasefile casefile) throws RuntimeException, Exception
    {

	// added this edit for activity 74021  supervisiontype DP90 aka DPS
	if (PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_PROSECUTION_SUPERVISION.equalsIgnoreCase(supervisionTypeId))
	{
	    casefile.setIsMAYSINeeded(false);
	}

	if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ.equalsIgnoreCase(supervisionCategory))
	{
	    casefile.setIsProgressRiskNeeded(false);
	    casefile.setIsResProgressRiskNeeded(false);
	    casefile.setIsResidentialRiskNeeded(false);
	    casefile.setIsCommunityRiskNeeded(false);
	    casefile.setIsMAYSINeeded(false);
	}
	else
	    if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM.equalsIgnoreCase(supervisionCategory))
	    {
		casefile.setIsResidentialRiskNeeded(false);
	    }
	    else
		if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES.equalsIgnoreCase(supervisionCategory))
		{
		    casefile.setIsResProgressRiskNeeded(true);
		    if (!isRiskAnalysisAssessmentCompleted(casefile, juvenileNum, RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL))
		    {
			casefile.setIsResidentialRiskNeeded(false);
		    }
		}
	return;

    }
    
    private void setRISKNEEDFlag(String juvenileNum, String supervisionTypeId, String supervisionCategory, JuvenileCasefile casefile) throws RuntimeException, Exception
    {

	if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ.equalsIgnoreCase(supervisionCategory))
	{
	    casefile.setRiskNeed(true);
	}
	else
	{
	    casefile.setRiskNeed(false);
	}

	return;
    }

    /**
     * RiskAnalysisForJuvenile within 30 days
     * 
     * @param casefile
     * @param juvenileNum
     * @param assessmentType
     * @return
     */
    private boolean isRiskAnalysisAssessmentCompleted(JuvenileCasefile casefile, String juvenileNum, String assessmentType) throws RuntimeException, Exception
    {
	GetRecentRiskAnalysisForJuvenileEvent event = new GetRecentRiskAnalysisForJuvenileEvent();
	event.setJuvenileNumber(juvenileNum);
	event.setAssessmentType(assessmentType);
	Iterator riskAnalysis = RiskAnalysis.findAll(event);
	if (riskAnalysis.hasNext())
	{
	    return true;
	}

	return false;
    }

    /**
     * Calculates the age in years given a date.
     * 
     * @param ageDate
     * @return age in years, 0 if age parameter is null
     */
    private static int getAgeAtOffense(Date dob, Date offenseDate)
    {
	if (dob == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(dob);
	Calendar dateOfOffense = Calendar.getInstance();
	dateOfOffense.setTime(offenseDate);
	int age = 0;
	age = dateOfOffense.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (dateOfOffense.before(birthdate))
	{
	    age--;
	}
	return age;
    }

    private static JJSFamily populateJJSFamilyFromResp(JuvReferralFamilyResponseEvent resp, String juvNum)
    {
	JJSFamily fam = new JJSFamily();
	fam.setFatherPhone(resp.getFathersPhone());
	fam.setMotherPhone(resp.getMothersPhone());
	fam.setOtherPhone(resp.getOtherPhone());
	if (resp.getFathersName() != null)
	{
	    Name fatherName = NameUtil.getNameFromString(resp.getFathersName());
	    fam.setFatherFirstName(fatherName.getFirstName());
	    fam.setFatherLastName(fatherName.getLastName());
	    fam.setFatherMiddleName(fatherName.getMiddleName());
	}
	JJSJuvenile jjsJuvenile = JJSJuvenile.find(juvNum);
	if (jjsJuvenile.getSsnRelation1Id() != null)
	{
	    if (jjsJuvenile.getSsnRelation1Id().equalsIgnoreCase("BF"))
		fam.setFatherSsn(jjsJuvenile.getSsn1());
	    else
		if (jjsJuvenile.getSsnRelation1Id().equalsIgnoreCase("BM"))
		    fam.setMotherSsn(jjsJuvenile.getSsn1());
		else
		{
		    fam.setOtherSsn(jjsJuvenile.getSsn1());
		    fam.setRelationshipId(jjsJuvenile.getSsnRelation1Id());
		}
	}
	if (jjsJuvenile.getSsnRelation2Id() != null)
	{
	    if (jjsJuvenile.getSsnRelation2Id().equalsIgnoreCase("BF"))
		fam.setFatherSsn(jjsJuvenile.getSsn2());
	    else
		if (jjsJuvenile.getSsnRelation2Id().equalsIgnoreCase("BM"))
		    fam.setMotherSsn(jjsJuvenile.getSsn2());
		else
		{
		    fam.setOtherSsn(jjsJuvenile.getSsn2());
		    fam.setRelationshipId(jjsJuvenile.getSsnRelation2Id());
		}
	}
	if (resp.getMothersName() != null)
	{
	    Name motherName = NameUtil.getNameFromString(resp.getMothersName());
	    fam.setMotherFirstName(motherName.getFirstName());
	    fam.setMotherLastName(motherName.getLastName());
	    fam.setMotherMiddleName(motherName.getMiddleName());
	}
	if (resp.getOtherName() != null)
	{
	    Name otherName = NameUtil.getNameFromString(resp.getOtherName());
	    fam.setOtherFirstName(otherName.getFirstName());
	    fam.setOtherLastName(otherName.getLastName());
	    fam.setOtherMiddleName(otherName.getMiddleName());
	}

	fam.setFatherAddressId(resp.getFathersAddress());
	fam.setMotherAddressId(resp.getMothersAddress());
	fam.setOtherAddressId(resp.getOtherAddress());

	return fam;
    }

    /**
     * @param juvfamily
     * @param juvNum
     */

    private static FamilyConstellation createFamilyConstellation(JJSFamily juvfamily, String juvNum) throws RuntimeException, Exception
    {
	FamilyConstellation familyConstellation = null;
	ArrayList<JuvenileReferralMemberDetailsBean> juvFamilyList = new ArrayList<JuvenileReferralMemberDetailsBean>();
	JuvenileReferralMemberDetailsBean memberBean1 = populateFamilyMemBeanFromJJSFamily(juvfamily, "BM");
	juvFamilyList.add(memberBean1);
	JuvenileReferralMemberDetailsBean memberBean2 = populateFamilyMemBeanFromJJSFamily(juvfamily, "BF");
	juvFamilyList.add(memberBean2);
	JuvenileReferralMemberDetailsBean memberBean3 = populateFamilyMemBeanFromJJSFamily(juvfamily, "OR");
	juvFamilyList.add(memberBean3);
	Iterator<JuvenileReferralMemberDetailsBean> familyMemberList = juvFamilyList.iterator();
	while (familyMemberList.hasNext())
	{
	    FamilyMember member = null;
	    JuvenileReferralMemberDetailsBean memberBean = (JuvenileReferralMemberDetailsBean) familyMemberList.next();
	    // check if member exists
	    FamilyMember mother = null;
	    ArrayList suspMemberIds = new ArrayList();
	    if ((memberBean.getFirstName() != null && memberBean.getFirstName().trim().length() > 0) || (memberBean.getMiddleName() != null && memberBean.getMiddleName().trim().length() > 0) || (memberBean.getLastName() != null && memberBean.getLastName().trim().length() > 0))
	    {
		if (memberBean.getSSN() != null && !memberBean.getSSN().getSSN().equals("") && !memberBean.getSSN().getSSN().equals("666666666") && !memberBean.getSSN().getSSN().equals("777777777") && !memberBean.getSSN().equals("888888888") && !memberBean.getSSN().getSSN().equals("999999999"))
		{
		    //check if any other family member has the same ssn
		    Iterator<FamilyMember> familyMembersSSN = JuvenileCaseHelper.checkFamilyMemberSSN(memberBean.getSSN().getSSN());
		    while (familyMembersSSN.hasNext())
		    {
			FamilyMember memberSSN = (FamilyMember) familyMembersSSN.next();
			//check if they have the same name
			if (memberSSN != null && memberSSN.getFirstName() != null && !memberSSN.getFirstName().equalsIgnoreCase(memberBean.getFirstName()) && memberSSN.getLastName() != null && !memberSSN.getLastName().equalsIgnoreCase(memberBean.getLastName()))
			{
			    suspMemberIds.add(memberSSN.getFamilyMemberId());
			}
		    }
		}
	    }
	    boolean isGuardianAvailable = false;
	    Address address = new Address();
	    PhoneNumber phoneNumber = null;
	    address.setStreetName(memberBean.getMemberAddress().getStreetName());
	    address.setCreateUserID(SecurityUIHelper.getLogonId());
	    address.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
	    address.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
	    address.setValidated("N");
	    phoneNumber = memberBean.getContactPhoneNumber();
	    try
	    {
		member = getFamilyMember(memberBean, address, phoneNumber);
	    }
	    catch (RuntimeException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    catch (Exception e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    //suspicious
	    if (suspMemberIds != null && !suspMemberIds.isEmpty())
	    {
		Iterator<String> iter = suspMemberIds.iterator();
		while (iter.hasNext())
		{
		    String memberId = (String) iter.next();
		    JuvenileCaseHelper.markMembersSuspicious(memberId, member.getFamilyMemberId());
		}
	    }
	    if (memberBean.getRelationshipId().equalsIgnoreCase("BM"))
	    {
		if (familyConstellation == null)
		    familyConstellation = createFamilyConstellation(juvNum);
		FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BM");
		if (familyConstellationMember != null)
		{
		    familyConstellationMember.setGuardian(true);
		    isGuardianAvailable = true;
		    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
		    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
		    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
		    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
		    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
		}
	    }
	    // add member to constellation
	    if (memberBean.getRelationshipId().equalsIgnoreCase("BF"))
	    {
		if (familyConstellation == null)
		    familyConstellation = createFamilyConstellation(juvNum);
		FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "BF");
		if (familyConstellationMember != null)
		{
		    familyConstellationMember.setGuardian(true);
		    isGuardianAvailable = true;
		    familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
		    SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
		    aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
		    aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
		    JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
		}
	    }
	    // add member to constellation
	    if (memberBean.getRelationshipId().equalsIgnoreCase("OR"))
	    {
		FamilyConstellationMember familyConstellationMember = createCostellationMember(member, "OR");
		if (isGuardianAvailable == false)
		{
		    if (familyConstellation == null)
			familyConstellation = createFamilyConstellation(juvNum);
		    if (familyConstellationMember != null)
		    {
			familyConstellationMember.setGuardian(true);
			familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
			SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
			aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
			aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
			JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
		    }
		}
	    }

	}

	return familyConstellation;
    }

    /**
     * @param firstName
     * @param middleName
     * @param lastName
     * @param memberAddress
     * @param memberPhoneNum
     * @param ssn
     * @return
     */
    private static JuvenileReferralMemberDetailsBean populateFamilyMemBeanFromJJSFamily(JJSFamily resp, String relationshipType)
    {
	JuvenileReferralMemberDetailsBean memberBean = new JuvenileReferralMemberDetailsBean();
	memberBean.setRelationshipId(relationshipType);
	if (relationshipType != null && relationshipType.equalsIgnoreCase("BM"))
	{
	    memberBean.setFirstName(resp.getMotherFirstName());
	    memberBean.setLastName(resp.getMotherLastName());
	    memberBean.setMiddleName(resp.getMotherMiddleName());
	    memberBean.setCompleteSSN(resp.getMotherSsn());
	    memberBean.setContactPhoneNumber(new PhoneNumber(resp.getMotherPhone()));
	    memberBean.setMemberAddress(new ui.common.Address());
	    memberBean.getMemberAddress().setStreetName(resp.getMotherAddressId());

	}
	else
	    if (relationshipType != null && relationshipType.equalsIgnoreCase("BF"))
	    {
		memberBean.setFirstName(resp.getFatherFirstName());
		memberBean.setLastName(resp.getFatherLastName());
		memberBean.setMiddleName(resp.getFatherMiddleName());
		memberBean.setContactPhoneNumber(new PhoneNumber(resp.getFatherPhone()));
		memberBean.setCompleteSSN(resp.getFatherSsn());
		memberBean.setMemberAddress(new ui.common.Address());
		memberBean.getMemberAddress().setStreetName(resp.getFatherAddressId());
	    }
	    else
	    {
		memberBean.setFirstName(resp.getOtherFirstName());
		memberBean.setLastName(resp.getOtherLastName());
		memberBean.setMiddleName(resp.getOtherMiddleName());
		memberBean.setContactPhoneNumber(new PhoneNumber(resp.getOtherPhone()));
		memberBean.setCompleteSSN(resp.getOtherSsn());
		memberBean.setMemberAddress(new ui.common.Address());
		memberBean.getMemberAddress().setStreetName(resp.getOtherAddressId());
	    }

	return memberBean;
    }

    /**
     * @param firstName
     * @param middleName
     * @param lastName
     * @param memberAddress
     * @param memberPhoneNum
     * @param ssn
     * @return
     */
    private static FamilyMember getFamilyMember(JuvenileReferralMemberDetailsBean memberBean, Address memberAddress, PhoneNumber contactPhoneNumber) throws RuntimeException, Exception
    {
	if (memberBean != null)
	{
	    String firstName = memberBean.getFirstName();
	    String middleName = memberBean.getMiddleName();
	    String lastName = memberBean.getLastName();
	    String ssn = memberBean.getSSN().getSSN();

	    FamilyMember member = null;
	    if ((firstName != null && firstName.trim().length() > 0) || (middleName != null && middleName.trim().length() > 0) || (lastName != null && lastName.trim().length() > 0))
	    {
		//TODO need to check if member exists
		member = new FamilyMember();
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setMiddleName(middleName);
		if (ssn == null || ssn.trim().equals(""))
		{
		    ssn = "666666666";
		}
		member.setSsn(ssn);
		//member.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
		member.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
		// create address
		if (memberAddress != null && !memberAddress.getStreetName().equals(""))
		{
		    Address address = null;
		    address = memberAddress;
		    member.insertAddresses(address);
		}

		FamilyMemberPhone memberPhone = null;
		if (contactPhoneNumber != null && contactPhoneNumber.getPhoneNumber() != null && contactPhoneNumber.getPhoneNumber().trim().length() > 0)
		{
		    memberPhone = JuvenileFamilyHelper.createMemberPhone(contactPhoneNumber.getPhoneNumber(), contactPhoneNumber.getExt(), memberBean.getPhoneType(), (String) member.getOID());
		}

		//add phonenum to member
		if (memberPhone != null)
		{
		    member.insertPhoneNumbers(memberPhone);
		}

		if (member.getOID() == null)
		{
		    IHome home = new Home();
		    home.bind(member);
		}
	    }
	    return member;
	}
	return null;
    }

    /*
     * @param juvNum
     */
    private static FamilyConstellation createFamilyConstellation(String juvNum)
    {
	FamilyConstellation familyConstellation = new FamilyConstellation();
	familyConstellation.setEntryDate(new Date());
	familyConstellation.setJuvenileId(juvNum);
	familyConstellation.setActive(true);
	familyConstellation.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	//familyConstellation.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	familyConstellation.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
	return familyConstellation;
    }

    /**
     * @param mother
     * @param relationshipId
     * @return FamilyConstellationMember
     */
    private static FamilyConstellationMember createCostellationMember(FamilyMember member, String relationshipId)
    {
	FamilyConstellationMember familyConstellationMember = null;
	if (member != null)
	{
	    familyConstellationMember = new FamilyConstellationMember();
	    familyConstellationMember.setTheFamilyMemberId((String) member.getOID());
	    familyConstellationMember.setRelationshipToJuvenileId(relationshipId);
	    familyConstellationMember.setInHomeStatus(false); // default to No as per requrement
	    familyConstellationMember.setInvolvementLevelId(PDJuvenileCaseConstants.FAMILY_MEMBER_INVOLVEMENT_LVL_LOW); // default to LOW as per requirements
	    familyConstellationMember.setCreateJIMS2UserID(PDJuvenileCaseConstants.JIMS2_CASEFILE_CREATOR);
	    // familyConstellationMember.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
	    familyConstellationMember.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
	}
	return familyConstellationMember;
    }

    /**
     * Moved from casefile extract utility
     * 
     * @param ageDate
     * @return
     */
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
     *
     * @param saveEvent
     */    
    private void updateJJSDecisionHistory(SaveJJSReferralEvent saveEvent) {
	
	    JJSDecisionHistory history = new JJSDecisionHistory();
	    
	    history.setJuvenileNum( saveEvent.getJuvenileNum() );
	    history.setReferralNum(saveEvent.getReferralNum());
	    history.setInDecisionId(saveEvent.getIntakeDecisionId());
	    history.setCourtDecisionId(null);
	    history.setDecisionDate(saveEvent.getIntakeDate());
	    history.setTjpcDisp( saveEvent.getTJPCDisp());
	    
	    GetJJSDecisionHistoryEvent req = new GetJJSDecisionHistoryEvent();
	    req.setJuvenileNum( saveEvent.getJuvenileNum() );
	    req.setReferralNum(saveEvent.getReferralNum());
	    
	    Iterator<JJSDecisionHistory> historyIter = JJSDecisionHistory.findAll(req);
	    
	    if(!historyIter.hasNext()) {
		
		 history.setTjpcDispSeqNum("1");
	    }else {
		
		int ctr =0;
		while( historyIter.hasNext()) {
		    
		    historyIter.next();				    
		    ctr++;
		}				
		history.setTjpcDispSeqNum(String.valueOf( ctr+ 1 ));				
	    }	
	
    }

}
