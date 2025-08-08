/*
 * Created on Feb 8, 2008
 *
 */
package pd.supervision.administerassessments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pd.codetable.Code;
import pd.contact.party.Party;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import pd.supervision.csts.CSTSHelper;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;
import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.query.GetIsPendingWisconsinEvent;
import messaging.administerassessments.query.GetNewestForceFieldEvent;
import messaging.administerassessments.query.GetNewestSCSAssessmentEvent;
import messaging.administerassessments.query.GetNextVersionNumberEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.administerassessments.reply.CSCQuestionAnswerResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.manageworkgroup.GetWorkGroupByExactNameEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.PDConstants;

/**
 * @author dgibler
 *
 */
public class AssessmentHelper {
    private static String INITIAL="I";
    private static int ZERO = 0;
    private static String FELONY_CDI = "003";
    private static String MISDEMEANOR_CDI = "002";
    private static String OOC_CASE = "010";

    /**
     * @param assessmentTypeCd
     * @param isInitial
     * @return
     */
    public static String getAssessmentFileName(String assessmentTypeCd, boolean isInitial){
        StringBuffer modifiedAssessmentTypeCd = new StringBuffer(assessmentTypeCd);
        if (isInitial && CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN.equals(assessmentTypeCd)){
            modifiedAssessmentTypeCd.append(INITIAL);
        }
        String fileName = null;
        Code aCode = Code.find(CSCAssessmentConstants.ASSESSMENT_FILENAME, modifiedAssessmentTypeCd.toString());
        if (aCode != null){
            fileName = aCode.getDescription();
        }
        return fileName;
    }
    /**
     * @param assessmentTypeCd
     * @param isInitial
     * @return
     */
    public static CSCQuestionAnswerResponseEvent getQuestions(String assessmentTypeCd, boolean isInitial){
        
        String fileName = AssessmentHelper.getAssessmentFileName(assessmentTypeCd, isInitial);
        CSCQuestionAnswerResponseEvent qre = AssessmentHelper.getQuestions(fileName);
        
        return qre;
    }
    /**
     * @param assessmentFileName
     * @return
     */
    public static CSCQuestionAnswerResponseEvent getQuestions(String assessmentFileName){
        CSXMLQuestionAnswerPropertyAdapter xmlPropertyAdapter = new CSXMLQuestionAnswerPropertyAdapter();
        CSCQuestionAnswerResponseEvent qaRe = null;
        try {
            qaRe = xmlPropertyAdapter.loadXML(assessmentFileName);
        } catch (Exception e){
            ErrorResponseEvent re = new ErrorResponseEvent();
            re.setMessage(e.getClass().toString());
            re.setException(e);
            re.setTopic(ReturnException.RETURN_EXCEPTION_TOPIC);
            MessageUtil.postReply(re);
        }
        return qaRe;
    }
    /**
     * @param masterAssessmentId
     * @return
     */
    public static int getNextVersionNum(String masterAssessmentId){
        
        int versionNum = 1;
        
        if (masterAssessmentId != null){
            GetNextVersionNumberEvent getNextVersionNumberEvent = new GetNextVersionNumberEvent();
            getNextVersionNumberEvent.setMasterAssessmentId(masterAssessmentId);
            List aList = Assessment.findAll(getNextVersionNumberEvent);
            if (aList != null && aList.size() > ZERO){
                Assessment assessment = (Assessment) aList.get(ZERO);
                versionNum = assessment.getVersionNum() + 1;
            }
        }
        return versionNum;
    }
    /**
     * @param updateCSAssessmentEvent
     */
    public static void scheduleNextWisconsin(UpdateCSAssessmentEvent updateCSAssessmentEvent){
        
        GetIsPendingWisconsinEvent pendingWisconsinEvent = new GetIsPendingWisconsinEvent();
        pendingWisconsinEvent.setDefendantId(updateCSAssessmentEvent.getDefendantId());
        
        List aList = Wisconsin.findAll(pendingWisconsinEvent);
        Wisconsin isPendingWisconsin = null;
        Assessment assessment = null;
        
        if (aList != null && aList.size() > ZERO){
            isPendingWisconsin = (Wisconsin) aList.get(ZERO);
          //Update isPending assessment due date if existing isPending Wisconsin fell in previous supervision period.
            SupervisionPeriod activeSupPeriod = SupervisionPeriod.findActiveSupervisionPeriod(updateCSAssessmentEvent.getDefendantId(), PDSecurityHelper.getUserAgencyId());
            if (activeSupPeriod.getSupervisionBeginDate().after(isPendingWisconsin.getAssessmentDate())){
            	Assessment theAssessment = isPendingWisconsin.getAssessment();
            	theAssessment.setAssessmentDate(updateCSAssessmentEvent.getAssessmentDate());
            }
        } else {
            updateCSAssessmentEvent.setAssessmentTypeCd(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN);
            updateCSAssessmentEvent.setMasterAssessmentId(null);
            updateCSAssessmentEvent.setQuestionAnswers(null);
            assessment = Assessment.create(updateCSAssessmentEvent);
            isPendingWisconsin = new Wisconsin();
            isPendingWisconsin.setAssessmentId(assessment.getOID());
            isPendingWisconsin.setIsPending(true);
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(updateCSAssessmentEvent.getAssessmentDate());
        calendar.roll(Calendar.YEAR, true);
        isPendingWisconsin.setAssessmentDueDate(calendar.getTime());
        isPendingWisconsin.setIsReassessment(false);
        
        
    }
	 /**
	 * @param agencyId
	 * @param type
	 * @param name
	 * @return
	 */
	public static String fetchWorkgroupByName(String agencyId, String type, String name)
	    {

	        GetWorkGroupByExactNameEvent requestEvent = new GetWorkGroupByExactNameEvent();

	        requestEvent.setAgencyId(agencyId);
	        requestEvent.setName(name);
	        requestEvent.setType(type);
	        
			Iterator wgIter = WorkGroup.findAll( requestEvent );
			List aList = CollectionUtil.iteratorToList(wgIter);
			String wgOid = null;
			
			if (aList != null && aList.size() > 0){
				WorkGroup wg = (WorkGroup) aList.get(0);
				wgOid = wg.getOID();
			}
	        return wgOid;

	    }
	 
	private static final String CSTS_WORKGROUP_NAME = "CSTS WORKGROUP";
	private static final String SA = "SA";
	private static final String ASSESSMENT_DATE_MSG = "Assessment Date has been corrected from ";
	private static final String REASSESSMENT_SUBJ = " - Reassessment Date Correction";
	private static final String ASSESSMENT_SUBJ = " - Assessment Date Correction";
	private static final String STATUS_O = "O";
	private static final String FOR = " for ";
	private static final String TO = " to ";
	
    public static void createTaskToCSTSWorkgroup(
    		String defendantId, 
    		boolean isReassessment, 
    		String recType,
    		Date origAssessmentDate,
    		Date newAssessmentDate){
    	
      	String taskOwnerId = fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), SA, CSTS_WORKGROUP_NAME);
      	
	    if(taskOwnerId != null){
		    CreateCSTaskEvent createTask = new CreateCSTaskEvent();
		    //Set due date to 5 days from current date.
		    Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 5);
			createTask.setDueDate(cal.getTime());
			createTask.setStatusId( STATUS_O );
			StringBuffer subject = new StringBuffer(recType);
			if (isReassessment){
				subject.append(REASSESSMENT_SUBJ);
			} else {
				subject.append(ASSESSMENT_SUBJ);
			}
			createTask.setTaskSubject(subject.toString());
			StringBuffer text = new StringBuffer(ASSESSMENT_DATE_MSG);
			text.append(DateUtil.dateToString(origAssessmentDate, DateUtil.DATE_FMT_1));
			text.append(TO);
			text.append( DateUtil.dateToString( newAssessmentDate, DateUtil.DATE_FMT_1) );
			text.append(FOR);
			text.append( defendantId );
			
			createTask.setWorkGroupId( taskOwnerId );
			createTask.setTastText( text.toString() );
			createTask.setDefendantId( defendantId );
			
			Party defendant = Party.find( defendantId );
	        Name aName = new Name();
	        if (defendant != null){
	        	if (defendant.getFirstName() != null && !defendant.getFirstName().equals(PDConstants.BLANK)){
	        		aName.setFirstName(defendant.getFirstName());
	        	} else {
	        		aName.setFirstName(PDConstants.BLANK);
	        	}
	        	if (defendant.getLastName() != null && !defendant.getLastName().equals(PDConstants.BLANK)){
	        		aName.setLastName(defendant.getLastName());
	        	} else {
	        		aName.setLastName(PDConstants.BLANK);
	        	}
	        } else {
	        	aName.setFirstName(PDConstants.BLANK);
	        	aName.setLastName(PDConstants.BLANK);
	        }
			
			createTask.setSuperviseeName(aName.getFormattedName() );

			createTask.setTopic( CSCAssessmentConstants.CSTASK_TOPIC_NOTIFY_CSTS_CHANGE );
			
			PDTaskHelper.createCSTask( createTask );

	    }
    }
    /**
     * @param assessmentId
     * @return
     */
    public static AssessmentResponseEvent getAssessmentResponseEvent(String masterAssessmentId, String assessmentId){
        AssessmentResponseEvent are = new AssessmentResponseEvent(masterAssessmentId, assessmentId);
        return are;
    }
    /**
     * @param assessment
     * @return
     */
    public static boolean hasCSTSRecordBeenSentToState(Assessment assessment){
        
        boolean sentToState = false;
        
        if (isAssessmentTypeStateReported(assessment.getAssessmentTypeId())){
            sentToState = CSTSHelper.hasCSTSRecordBeenSentToState(assessment);
        }

        return sentToState;
    }
    /**
     * @param assessmentTypeId
     * @return
     */
    private static boolean isAssessmentTypeStateReported(String assessmentTypeId) {
        
        boolean reportedToState = false;
        
        if (assessmentTypeId.equals(CSCAssessmentConstants.ASSESSMENTTYPE_SCS)
                || assessmentTypeId.equals(CSCAssessmentConstants.ASSESSMENTTYPE_LSIR)
                || assessmentTypeId.equals(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN)){
            reportedToState = true;
        }
        
        return reportedToState;
    }
    /**
     * @param defendantId
     * @return
     */
    public static String getCaseNumberForCSTSReporting(String defendantId){
        
        List activeOrderList = SupervisionOrderHelper.getActiveSupervisionOrders(defendantId);
        //T21 - In the event that there are active felony and misdemeanor cases, the system will select the felony case.
        //When there are multiple felony cases, select the most current HC Felony case.  If non exist then select
        //the most current HC misdemeanor case.  If none exist select the most current OOC felony case (based on court).
        //If none exist then select the most current OOC misdemeanor case (based on court), if none exist, the most current
        //OOC JP case.
        SupervisionOrder supervisionOrder = null;
        String mostRecentCaseId = null;
        List hcFelonyCases = new ArrayList();
        List hcMisdCases = new ArrayList();
        List oocCases = new ArrayList();
        
        for (int i = 0; i < activeOrderList.size(); i++) {
            supervisionOrder = (SupervisionOrder) activeOrderList.get(i);
            if (supervisionOrder.getCriminalCaseId().startsWith(FELONY_CDI)){
                hcFelonyCases.add(supervisionOrder);
            } else if (supervisionOrder.getCriminalCaseId().startsWith(MISDEMEANOR_CDI)){
                hcMisdCases.add(supervisionOrder);
            } else if (supervisionOrder.getCriminalCaseId().startsWith(OOC_CASE)){
                oocCases.add(supervisionOrder);
            } 
        }
        
        if (hcFelonyCases.size() > ZERO){
            mostRecentCaseId = getMostRecentCaseId(hcFelonyCases);
        } else if (hcMisdCases.size() > ZERO){
            mostRecentCaseId = getMostRecentCaseId(hcMisdCases);
        } else if (oocCases.size() > ZERO){
            mostRecentCaseId = getMostRecentOutOfCountyCase(oocCases);
        } 
        
        return mostRecentCaseId;
    }
    /**
     * @param oocOrders
     * @return
     */
    public static String getMostRecentOutOfCountyCase(List oocOrders) {
        
        List felonyCases = new ArrayList();
        List misdCases = new ArrayList ();
        List jpCases = new ArrayList();
        String mostRecentCaseId = null;
        SupervisionOrder so = null;
        String courtId = null;
        String FELONY = "F";
        String JP_COURT = "JP";
       
        for (int i = 0; i < oocOrders.size(); i++) {
            so = (SupervisionOrder) oocOrders.get(i);
            courtId = so.getCurrentCourtId();
            if (courtId.startsWith(JP_COURT)){
                jpCases.add(so);
            } else if (courtId.endsWith(FELONY)){
                felonyCases.add(so);
            } else {
                misdCases.add(so);
            }
        }
        
        if (felonyCases.size() > ZERO){
            mostRecentCaseId = getMostRecentCaseId(felonyCases);
        } else if (misdCases.size() > ZERO){
            mostRecentCaseId = getMostRecentCaseId(misdCases);
        } else if (jpCases.size() > ZERO){
            mostRecentCaseId = getMostRecentCaseId(jpCases);
        }
        
        return mostRecentCaseId;
    }
    /**
     * @param activeOrders
     */
    private static String getMostRecentCaseId(List activeOrders) {
        
        SupervisionOrder so = null;
        SupervisionOrder mostRecentOrder = null;
        
        for (int i = 0; i < activeOrders.size(); i++) {
            so = (SupervisionOrder) activeOrders.get(i);
            if (mostRecentOrder == null || so.getActivationDate().after(mostRecentOrder.getActivationDate())){
                mostRecentOrder = so;
            }
        }
        
        return mostRecentOrder.getCriminalCaseId();
    }
    
    
    /**
     * @param defendantId
     * @return
     */
    public static ForceFieldAnalysis getMostRecentForceField(String defendantId) 
    {
 	   GetNewestForceFieldEvent queryEvt = new GetNewestForceFieldEvent();
 	   queryEvt.setDefendantId(defendantId);
 	   ForceFieldAnalysis newestForceField = null;
 	   List aList = ForceFieldAnalysis.findAll(queryEvt);
 	   if(aList!= null && aList.size()>0)
 	   {
 		   newestForceField = (ForceFieldAnalysis)aList.get(0);
 	   }
        return newestForceField;
    }
    
    
    /**
     * @param defendantId
     * @return
     */
    public static StrategiesForCaseSupervision getMostRecentSCS(String defendantId) {
     
        GetNewestSCSAssessmentEvent anEvent = new GetNewestSCSAssessmentEvent();
        anEvent.setDefendantId(defendantId);
        StrategiesForCaseSupervision newestSCS = null;
        List aList = StrategiesForCaseSupervision.findAll(anEvent);
        if (aList != null && aList.size() > 0){
            newestSCS = (StrategiesForCaseSupervision) aList.get(0);
        }
        
        return newestSCS;
    }
    
    
    /**
     * @param defendantId
     * @return
     */
    public static SCSInterview getMostRecentSCSInterview(String defendantId) {
     
        GetNewestSCSAssessmentEvent anEvent = new GetNewestSCSAssessmentEvent();
        anEvent.setDefendantId(defendantId);
        SCSInterview newestSCSInterview = null;
        List aList = SCSInterview.findAll(anEvent);
        if (aList != null && aList.size() > 0){
     	   newestSCSInterview = (SCSInterview) aList.get(0);
        }
        return newestSCSInterview;
    }
    
 }
