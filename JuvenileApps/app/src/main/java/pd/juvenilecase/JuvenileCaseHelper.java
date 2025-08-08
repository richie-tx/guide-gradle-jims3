package pd.juvenilecase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.GetAllJuvenileCourtDecisionCodesEvent;
import messaging.codetable.GetJuvenileCourtDecisionCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDateRangeEvent;
import messaging.family.GetFamilyMembersByDLNumberEvent;
import messaging.family.GetFamilyMembersBySSNEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.family.UpdateFamilyMemberMatchesEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileCoactorsResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.SendCasefileGenerationFailureNoticeEvent;
import messaging.juvenilecase.SendNewJuvenileCasefileNotificationEvent;
import messaging.juvenilecase.SendNotificationToConductMAYSIEvent;
import messaging.juvenilecase.SendUpdateJuvenileCasefileNotificationEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryViewResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDNotificationConstants;
import naming.UIConstants;
import pd.address.Address;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.referral.JJSReferral;
import pd.km.util.Name;
import pd.notification.PDNotificationHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.MaysiDataReportBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;

public final class JuvenileCaseHelper
{
    private static final String DELIMITER = "^";

    private JuvenileCaseHelper()
    {
    }

    /**
     * @author asrvastava
     * 
     * This method is used to associate assignemnt records to casefiles. A new casefile is created
     * if one already doesn't exist for a combination of supervisionNum, juvenileNum and jpoUserId
     * and case status either pending/active.
     * 
     * @param jjsServices
     *            a collection of assignment records fetched from m204.
     * @throws Exception
     */
    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/
 // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
  /*  public static void associateAssignmentsToCaseFile(Iterator jjsServices) throws Exception
    {
        //TODO: Dawn - Remove the following before moving to production
        StringBuffer results = new StringBuffer("Assignment Records to be extracted from M204:");

        Map supTypeMap = new HashMap();
        Map casefileMap = new HashMap();
        Map juvenileMap = new HashMap();
        Set updatedCasefiles = new HashSet();
        Set newCasefiles = new HashSet();

        while (jjsServices.hasNext())
        {
            JJSService jjsService = (JJSService) jjsServices.next();

            // determine SupervisionType
            String assignmentLevelId = jjsService.getAssignmentLevelId();
            String serviceUnitId = jjsService.getUnitId();
            // compute key for the map. Map is used to avoid fetching data from db if it has already
            // been fetched
            // to determine supervisionType.
            String keySupType = assignmentLevelId + DELIMITER + serviceUnitId;

            Code supervisionType = (Code) supTypeMap.get(keySupType);
            if (supervisionType == null)
            {
                // determine supervisionType
                supervisionType = SupervisionTypeMap.determineSupervisionType(serviceUnitId, assignmentLevelId);
                if (supervisionType == null)
                {
                    // we need to skip this record if supervisiontype could not be found
                    continue;
                }
                supTypeMap.put(keySupType, supervisionType);
            }

            // Get Probation Officer
            String jpoUserId = jjsService.getProbationOfficerId();
            OfficerProfile officer = null;
            Iterator officers = OfficerProfile.findAll("logonId", jpoUserId);
            //Iterator officers = OfficerProfile.findAll( "JIMSLogonId", jpoUserId );
            if (officers.hasNext())
            {
                officer = (OfficerProfile) officers.next();
            }
            else
            {
                //TODO Probation Officer not found. Is an error needed here or does the profile get
                // created?
                continue;
            }
            String jpoId = officer.getOID().toString();

            // get JuvenileCasefile
            String juvNum = jjsService.getJuvenileNum();
            String supTypeId = supervisionType.getCode();
            // compute key for the map. Map is used to avoid fetching JuvenileCasefile from db if it
            // has already been fetched.
            String keyCasefile = jpoId + DELIMITER + juvNum + DELIMITER + supTypeId;
            JuvenileCasefile casefile = (JuvenileCasefile) casefileMap.get(keyCasefile);
            if (casefile == null)
            {
                // caseStatusId);

                casefile = JuvenileCasefile.find(jpoId, juvNum, supTypeId);
                if (casefile == null)
                {
                    // check if we have already traversed this juvNum
                    Juvenile juv = (Juvenile) juvenileMap.get(juvNum);
                    if (juv == null)
                    {
                        // check if Juvenile master record needs to be created
                        juv = Juvenile.find(juvNum);
                        if (juv == null)
                        {
                            // create juvenile master record
                            juv = createJuvenile(juvNum);
                            if (juv == null)
                            {
                                continue;
                            }
                            else
                            { // import family member info
                                JJSFamily juvfamily = JJSFamily.find(juvNum);
                                if (juvfamily != null)
                                {
                                    FamilyConstellation familyConstellation = createFamilyConstellation(juvfamily, juv
                                            .getJuvenileNum());
                                    // add constellation to juvenile
                                    if (familyConstellation != null)
                                    {
                                        juv.insertFamilyConstellationList(familyConstellation);
                                    }
                                }
                                IHome home = new Home();
                                home.bind(juv);
                            }

                        }
                        juvenileMap.put(juvNum, juv);
                    }

                    // new Casefile needs to be initiated
                    casefile = new JuvenileCasefile();
                    casefile.setJuvenileId(juvNum);

                    casefile.setProbationOfficer(officer);

                    casefile.setSupervisionTypeId(supTypeId);
                    casefile.setCaseStatusId(PDJuvenileCaseConstants.CASESTATUS_PENDING);
                    casefile.setActivationDate(new Date());
                    newCasefiles.add(casefile);

                    // casefile OID is needed for creating the activate casefile task
                    IHome home = new Home();
                    home.bind(casefile);

                    // added by JMF (3/3/2006)
                    String casefileId = casefile.getOID().toString();
                    StringBuffer juvNameBuffer = new StringBuffer(juv.getLastName());
                    juvNameBuffer = juvNameBuffer.append(", ").append(juv.getFirstName()).append(" ").append(
                            juv.getMiddleName());
                    String juvName = juvNameBuffer.toString();

                    //task creation
                    HashMap map = new HashMap();
                    map.put("submitAction", "Link");
                    map.put("casefileID", casefileId);
                    Task.createTask(jpoUserId, PDTaskConstants.CREATE_TASK_ACTIVATE_CASEFILE,
                            getTaskTitleForActivateCasefile(juvName, casefileId), map);
                }
                else
                {
                    updatedCasefiles.add(casefile);
                    // Casefile already exists
                    sendCaseFileGenerationFailureNotification(casefile, jjsService, officer);

                }
                casefileMap.put(keyCasefile, casefile);
            }
            Assignment assignment = new Assignment();
            populateAssignmentFromService(jjsService, assignment);
            casefile.insertAssignments(assignment);
            assignment.setCaseFileId(casefile.getOID().toString());

            // check if MAYSI is needed for this juvenile
            if (!maysiExists(juvNum, assignment.getReferralNumber()))
            {
                casefile.setIsMAYSINeeded(true);
                sendNotificationToConductMAYSI(assignment);
            }
            //TODO: Dawn - Remove the following before moving to production
            printServiceDataForQA(results, jjsService);

            // update the JJSService record to indicate this record has already been fetched
            // its m204 and it doesnt accept a boolean
            jjsService.setJims2ExtractInd("Y");

        }

        // send notifications for casefiles
        sendCasefileNotifications(newCasefiles, updatedCasefiles, juvenileMap);
    }*/

    private static Juvenile createJuvenile(String juvenileNum)
    {
        Juvenile juvenile = null;
        JJSJuvenile jjsJuvenile = JJSJuvenile.find(juvenileNum);
        if (jjsJuvenile != null)
        {
            juvenile = new Juvenile();
            populateJuvenileFromJJSJuvenile(jjsJuvenile, juvenile);
            IHome home = new Home();
            home.bind(juvenile);

        }
        return juvenile;
    }

    private static void populateJuvenileFromJJSJuvenile(JJSJuvenile jjsJuvenile, Juvenile juvenile)
    {
        juvenile.setDateOfBirth(jjsJuvenile.getBirthDate());
        juvenile.setFirstName(jjsJuvenile.getFirstName());
        juvenile.setMiddleName(jjsJuvenile.getMiddleName());
        juvenile.setLastName(jjsJuvenile.getLastName());
        juvenile.setJuvenileNum(jjsJuvenile.getJuvenileNum());
        juvenile.setRaceId(jjsJuvenile.getRaceId());
        juvenile.setHispanicInd(jjsJuvenile.getNcicEthnicity());
        if (jjsJuvenile.getNcicEthnicity()!= null && jjsJuvenile.getNcicEthnicity().equals("H")) {
            juvenile.setHispanic("Y"); //U.S 88526
        }
        juvenile.setSexId(jjsJuvenile.getSexId());
        juvenile.setEthnicityId(jjsJuvenile.getNcicEthnicity());
        juvenile.setSSN(jjsJuvenile.getJuvenileSsn());
    }

    public static boolean maysiExists(String juvenileNum, String referralNumber)
    {
        boolean result = false;
        GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
        queryEvent.setJuvenileNumber(juvenileNum);
        Iterator iter = JuvenileMAYSIComplete.findAll(queryEvent);
        while (iter.hasNext())
        {
            JuvenileMAYSIComplete myMaysi = (JuvenileMAYSIComplete) iter.next();
            if (myMaysi.getJuvenileNumber() != null && myMaysi.getReferralNumber().equals(referralNumber))
            {
                result = true;
                break;
            }

        }
        return result;
    }
    public static boolean maysiNeededforReferral(String juvenileNum, String referralNumber)
    {
        boolean result = false;
        GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
        queryEvent.setJuvenileNumber(juvenileNum);
        queryEvent.setReferralNumber(referralNumber);
        
             
        List<MAYSIDetailsResponseEvent> maysiList = new ArrayList<MAYSIDetailsResponseEvent>();
	Iterator<JuvenileMAYSIComplete> iter = JuvenileMAYSIComplete.findAll(queryEvent);
	 
	while (iter.hasNext())
	{
	    JuvenileMAYSIComplete juvMAYSI = iter.next();
	    MAYSIDetailsResponseEvent rep = juvMAYSI.getResponseEvent();
	    maysiList.add(rep);
	}

        Collections.sort((List<MAYSIDetailsResponseEvent>)maysiList,Collections.reverseOrder(new Comparator<MAYSIDetailsResponseEvent>() {
		@Override
		public int compare(MAYSIDetailsResponseEvent evt1, MAYSIDetailsResponseEvent evt2)
		{
		    if (evt1.getAssessmentDate()!= null && evt2.getAssessmentDate() != null)
			return evt1.getAssessmentDate().compareTo(evt2.getAssessmentDate());
		    else
			return -1;
		}
	}));
        Iterator maysiIter = maysiList.iterator();
        while (maysiIter.hasNext())
        {
            MAYSIDetailsResponseEvent myMaysi = (MAYSIDetailsResponseEvent) maysiIter.next();
            if (myMaysi.getReferralNumber() != null && myMaysi.getReferralNumber().equals(referralNumber)&&(myMaysi.getAssessmentOptionId().equals("A")||myMaysi.getAssessmentOptionId().equals("T")))
            {
                result = false; 
                break;
            }
            else if(myMaysi.getReferralNumber() != null && myMaysi.getReferralNumber().equals(referralNumber)&& !myMaysi.getAssessmentOptionId().equals("A") && !myMaysi.getAssessmentOptionId().equals("T"))
            {
        	result = true;
        	break;
            }

        }
        return result;
    }

    public static boolean maysiExists(String juvenileNum)
    {
        boolean result = false;
        GetAllMAYSIAssessmentsEvent queryEvent = new GetAllMAYSIAssessmentsEvent();
        queryEvent.setJuvenileNumber(juvenileNum);
        Iterator iter = JuvenileMAYSIComplete.findAll(queryEvent);
        if (iter.hasNext())
        {
            result = true;
        }
        return result;
    }

/*    private static void populateAssignmentFromService(JJSService jjsService, Assignment assignment)
    {
        assignment.setAssignmentAddDate(jjsService.getAddDate());
        assignment.setAssignmentLevelId(jjsService.getAssignmentLevelId());
        assignment.setReferralNumber(jjsService.getReferralNum());
        assignment.setServiceUnitId(jjsService.getUnitId());
        assignment.setAssignByUserId(jjsService.getAssignByUserId());
    }*/

    private static void sendCasefileNotifications(Set newCasefiles, Set updatedCasefiles, Map juvenileMap)
    {
        // process new ones
        Iterator newFiles = newCasefiles.iterator();
        while (newFiles.hasNext())
        {
            JuvenileCasefile juvenileCasefile = (JuvenileCasefile) newFiles.next();
            // get juvenile. try to fetch it from the map first.
            // Profile stripping fix task 97536
            //Juvenile juvenile = (Juvenile) juvenileMap.get(juvenileCasefile.getJuvenileNum());
            JuvenileCore juvenile = (JuvenileCore) juvenileMap.get(juvenileCasefile.getJuvenileNum());
            if (juvenile == null)
            {
                juvenile = juvenileCasefile.getJuvenile();
            }
            // casefile has been initiated
            sendNewJuvenileCasefileNotification(juvenile, juvenileCasefile);
        }

        // process updated ones
        Iterator updatedFiles = updatedCasefiles.iterator();
        while (updatedFiles.hasNext())
        {
            JuvenileCasefile juvenileCasefile = (JuvenileCasefile) updatedFiles.next();
            // get juvenile. try to fetch it from the map first.
            // Profile stripping fix task 97536
            //Juvenile juvenile = (Juvenile) juvenileMap.get(juvenileCasefile.getJuvenileNum());
            JuvenileCore juvenile = (JuvenileCore) juvenileMap.get(juvenileCasefile.getJuvenileNum());
            if (juvenile == null)
            {
                juvenile = juvenileCasefile.getJuvenile();
            }
            if (juvenile != null)
            {
                sendUpdateJuvenileCasefileNotification(juvenile, juvenileCasefile);
            }
            // assignments have been added in an existing casefile

        }
    }

    private static void sendNotificationToConductMAYSI(Assignment assignment)
    {
        String fromEmail = PDNotificationHelper.getNotificationEmail("Notification_EmailFrom");
        String toEmail = PDNotificationHelper.getNotificationEmail("Notification_Conduct_MAYSI");
        SendNotificationToConductMAYSIEvent notificationEvent = new SendNotificationToConductMAYSIEvent();
        notificationEvent.setEmailFrom(fromEmail);
        notificationEvent.setEmailTo(toEmail);
        notificationEvent.setAssignmentAddDate(assignment.getAssignmentAddDate());
        notificationEvent.setReferralNumber(assignment.getReferralNumber());

        // post RegisterTask event
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setNextNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setTaskName(getTaskName(notificationEvent.getClass().getName(),
                PDNotificationConstants.NOTIFICATION_CONDUCT_MAYSI));
        rtEvent.setNotificationEvent(notificationEvent);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(rtEvent);

    }
    // Profile stripping fix task 97536
    //private static void sendUpdateJuvenileCasefileNotification(Juvenile juvenile, JuvenileCasefile casefile)
    private static void sendUpdateJuvenileCasefileNotification(JuvenileCore juvenile, JuvenileCasefile casefile)
    {
        String fromEmail = PDNotificationHelper.getNotificationEmail("Notification_EmailFrom");
        String toEmail = PDNotificationHelper.getNotificationEmail("Notification_update_JuvenileCasefile");
        // create and populate notification event
        SendUpdateJuvenileCasefileNotificationEvent notificationEvent = new SendUpdateJuvenileCasefileNotificationEvent();
        notificationEvent.setEmailFrom(fromEmail);
        notificationEvent.setEmailTo(toEmail);
        notificationEvent.setJuvenileFirstName(juvenile.getFirstName());
        notificationEvent.setJuvenileLastName(juvenile.getLastName());
        notificationEvent.setJuvenileNumber(juvenile.getJuvenileNum());

        // get assignment data
        Iterator assignments = casefile.getAssignments().iterator();
        while (assignments.hasNext())
        {
            Assignment assignment = (Assignment) assignments.next();
            JuvenileCasefileReferralsResponseEvent referralEvent = getJuvenileCasefileReferralsResponse(assignment);
            notificationEvent.insertAssignment(referralEvent);
        }

        // post RegisterTask event
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setNextNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setTaskName(getTaskName(notificationEvent.getClass().getName(),
                PDNotificationConstants.NOTIFICATION_UPDATE_JUVENILECASEFILE));
        rtEvent.setNotificationEvent(notificationEvent);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(rtEvent);
    }

    // Profile stripping fix task 97536
    //private static void sendNewJuvenileCasefileNotification(Juvenile juvenile, JuvenileCasefile casefile)
    private static void sendNewJuvenileCasefileNotification(JuvenileCore juvenile, JuvenileCasefile casefile)
    {
        String fromEmail = PDNotificationHelper.getNotificationEmail("Notification_EmailFrom");
        String toEmail = PDNotificationHelper.getNotificationEmail("Notification_New_JuvenileCasefile");
        // create and populate notification evenet
        SendNewJuvenileCasefileNotificationEvent notificationEvent = new SendNewJuvenileCasefileNotificationEvent();
        notificationEvent.setEmailFrom(fromEmail);
        notificationEvent.setEmailTo(toEmail);
        notificationEvent.setBenefitAssessmentNeeded(true);
        notificationEvent.setJuvenileFirstName(juvenile.getFirstName());
        notificationEvent.setJuvenileLastName(juvenile.getLastName());
        notificationEvent.setJuvenileNumber(juvenile.getJuvenileNum());
        notificationEvent.setMAYSINeeded(casefile.getIsMAYSINeeded());
        notificationEvent.setSupervisionNumber(casefile.getOID().toString());
        //retrieve jpo name
        OfficerProfile officerProfile = OfficerProfile.find(casefile.getProbationOfficerId());
        notificationEvent.setJpoFirstName(officerProfile.getFirstName());
        notificationEvent.setJpoLastName(officerProfile.getLastName());
        // get assignment data
        // get assignment data
        Iterator assignments = casefile.getAssignments().iterator();
        while (assignments.hasNext())
        {
            Assignment assignment = (Assignment) assignments.next();
            JuvenileCasefileReferralsResponseEvent referralEvent = getJuvenileCasefileReferralsResponse(assignment);
            notificationEvent.insertAssignment(referralEvent);
        }

        // post RegisterTask event
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setNextNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setTaskName(getTaskName(notificationEvent.getClass().getName(),
                PDNotificationConstants.NOTIFICATION_NEW_JUVENILECASEFILE));
        rtEvent.setNotificationEvent(notificationEvent);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(rtEvent);
    }

    /**
     * Method used to send the Notice for Failure of Generating casefile
     *  
     */

/*    private static void sendCaseFileGenerationFailureNotification(JuvenileCasefile casefile, JJSService jjsService,
            OfficerProfile officer)
    {

        //LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
        SendCasefileGenerationFailureNoticeEvent nevt = new SendCasefileGenerationFailureNoticeEvent();
        nevt.setSubject("send failure notice for case file generation");
        nevt.setIdentity(jjsService.getCaseLoadManager().getLogonId());
        //String juvName = casefile.getJuvenile().getFirstName() +
        // casefile.getJuvenile().getLastName() + casefile.getJuvenile().getMiddleName();
        String juvNum = jjsService.getJuvenileNum();
        String supervisionNum = casefile.getSupervisionTypeId();
        String jpoName = officer.getLastName() + " " + officer.getFirstName() + " " + officer.getMiddleName();

        String messagePart1 = "A new JIMS2 casefile was not created for Juvenile who is assigned to the Juvenile Number "
                + juvNum;
        String messagePart2 = ".The Juvenile has an existing casefile " + supervisionNum
                + " of the same supervision type,	which is assigned to JPO " + jpoName;

        String notificationMessage = "";
        notificationMessage = notificationMessage + messagePart1 + messagePart2;

        nevt.setNotificationMessage(notificationMessage);
        sendNotification(nevt, UIConstants.FAILURE_CASEFILE_NOTIFICATION);

    }*/

    private static void sendNotification(SendCasefileGenerationFailureNoticeEvent nevt, String topic)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
        notificationEvent.setNotificationTopic(topic);
        notificationEvent.setSubject(nevt.getSubject());
        notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, (IAddressable) nevt);
        notificationEvent.addContentBean(nevt);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    // End

    /**
     * Takes a JuvenileCasefile and returns a JuvenileCasefileResponseEvent based off the values
     * within the casefile
     * 
     * @param file
     * @return JuvenileCasefileResponseEvent
     */
    public static JuvenileCasefileResponseEvent getJuvenileCasefileResponse(JuvenileCasefile file)
    {
        JuvenileCasefileResponseEvent resp = new JuvenileCasefileResponseEvent();
        resp.setTopic(PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);
        resp.setJuvenileNum(file.getJuvenileNum());
        resp.setSupervisionNum((String) file.getOID());
        resp.setSequenceNum(file.getSequenceNumber());
        resp.setActivationDate(file.getActivationDate());
        resp.setSupervisionEndDate(file.getSupervisionEndDate());
        resp.setCourtOrderedProbationStartDate(file.getCourtOrderedProbationStartDate());
        resp.setIsMAYSINeeded(file.getIsMAYSINeeded());
        //taken out for US 14459
       // resp.setBenefitsAssessmentNeeded(file.getIsBenefitsAssessmentNeeded());
        // set the Risk Assessment Attributes
        resp.setReferralRiskNeeded(file.getIsReferralRiskNeeded());
		//commented out for US 14459
       /* resp.setInterviewRiskNeeded(file.getIsInterviewRiskNeeded());
        resp.setTestingRiskNeeded(file.getIsTestingRiskNeeded());*/
        resp.setCommunityRiskNeeded(file.getIsCommunityRiskNeeded());
        resp.setResidentialRiskNeeded(file.getIsResidentialRiskNeeded());
        resp.setProgressRiskNeeded(file.getIsProgressRiskNeeded());
        resp.setResProgressRiskNeeded(file.getIsResProgressRiskNeeded());
        resp.setCaseplanId(file.getCaseplanId());
        resp.setCreateUserID(file.getCreateUserID());
        if(file.getCreateTimestamp()!=null)
        	resp.setCreateDate(new Date(file.getCreateTimestamp().getTime()));
        resp.setUpdateUser(file.getUpdateUserID());
        if(file.getUpdateTimestamp()!=null)
        	resp.setUpdateDate(new Date(file.getUpdateTimestamp().getTime()));
        resp.setCreateJIMS2UserID(file.getCreateJIMS2UserID());
        resp.setUpdateJIMS2UserID(file.getUpdateJIMS2UserID());
        resp.setProbationFlag(file.getProbationFlag());
        // Set Juvenile attributes on response event
        //Juvenile juvenile = file.getJuvenile();
        JuvenileCore juvenile = file.getJuvenile();
        if (juvenile != null)
        {
            resp.setJuvenileFirstName(juvenile.getFirstName());
            resp.setJuvenileLastName(juvenile.getLastName());
            resp.setJuvenileMiddleName(juvenile.getMiddleName());
            resp.setJuvenileNameSuffix(juvenile.getNameSuffix());
            if ( juvenile.getPreferredFirstName() != null 
        	    && juvenile.getPreferredFirstName().length() > 0 ) {
        	resp.setJuvenilePreferredFirstName(juvenile.getPreferredFirstName());
            } else {
        	resp.setJuvenilePreferredFirstName("");
            }
            // bug fix for 98584
            resp.setJuvenileRaceId(juvenile.getRaceId());
            resp.setJuvenileOriginalRaceId(juvenile.getOriginalRaceId());
            //
            if( juvenile.getRace() != null ){
        	
                resp.setJuvenileRaceDescription(juvenile.getRace().getDescription());
            }
            resp.setJuvenileSexId(juvenile.getSexId());
            resp.setJuvenileCurrentAge(String.valueOf(JuvenileCaseHelper.getAgeInYears(juvenile.getDateOfBirth())));
            resp.setDobStr(DateUtil.dateToString(juvenile.getDateOfBirth(), DateUtil.DATE_FMT_1));
        }

        resp.setCaseStatusId(file.getCaseStatusId());
        if(file.getCaseStatusId()!=null){
        	resp.setCaseStatus(SimpleCodeTableHelper.getDescrByCode("JUV_CASE_STATUS",file.getCaseStatusId()));
        }
        resp.setSupervisionCategoryId(file.getSupervisionCategoryId());
        resp.setSupervisionTypeId(file.getSupervisionTypeId());
        if(file.getSupervisionTypeId()!=null){
            resp.setSupervisionType( SimpleCodeTableHelper.getDescrByCode("SUPERVISION_TYPE", file.getSupervisionTypeId()));
        }
        resp.setSupervisionOutcome(file.getSupervisionOutcome());
        resp.setSupervisionOutcomeDescriptionId(file.getSupervisionOutcomeDescriptionId());
        resp.setClosingEvaluation(file.getClosingEvaluation());
        resp.setClosingDate(file.getClosingDate());
        
        //Bug:12703 First checking the controlling ref from closing, If value not found, checking controlling ref from activation casefile
        if(file.getControllingReferral()!=null && !file.getControllingReferral().isEmpty()){
        	resp.setControllingReferral(file.getControllingReferral());
        }else if(file.getCasefileControllingReferralId()!=null && !file.getCasefileControllingReferralId().isEmpty()){
        	resp.setControllingReferralId(file.getCasefileControllingReferralId());
        }else{
        	if(file.getCasefileId()!=null && !file.getCasefileId().isEmpty()){
        		resp.setControllingReferral(UIProgramReferralHelper.getLowestCtrlngRefNbr((String) file.getCasefileId()));
        		resp.setControllingReferralId(UIProgramReferralHelper.getLowestCtrlngRefNbr((String) file.getCasefileId()));
        	}else{
        		resp.setControllingReferral(UIProgramReferralHelper.getLowestCtrlngRefNbr((String) file.getOID()));
        		resp.setControllingReferralId(UIProgramReferralHelper.getLowestCtrlngRefNbr((String) file.getOID()));
        	}
        }
        
        OfficerProfile officer = file.getProbationOfficer();
        if (officer != null)
        {
            resp.setProbationOfficeId(officer.getOfficerProfileId());
            resp.setProbationOfficerFirstName(officer.getFirstName());
            resp.setProbationOfficerMiddleName(officer.getMiddleName());
            resp.setProbationOfficerLastName(officer.getLastName());
            resp.setProbationOfficerLogonId(officer.getLogonId());
            resp.setCaseloadManagerId(officer.getManagerLogonId());
            resp.setCaseloadManagerFirstName(officer.getManagerFirstName());
            resp.setCaseloadManagerMiddleName(officer.getManagerMiddleName());
            resp.setCaseloadManagerLastName(officer.getManagerLastName());
        }

        Collection assignmentList = file.getAssignments();

        if (assignmentList != null)
        {
            Iterator iter;
            iter = assignmentList.iterator();
            Date oldestAssignmentDate = null;
            List<String> referrals = new ArrayList<>();
            StringBuffer associatedReferrals = new StringBuffer();
            int loop = 0;
            while (iter.hasNext())
            {
                Assignment assignment = (Assignment) iter.next();
                if (oldestAssignmentDate == null)
                {
                    oldestAssignmentDate = assignment.getAssignmentAddDate();
                }
                else if (assignment.getAssignmentAddDate().before(oldestAssignmentDate))
                {
                    oldestAssignmentDate = assignment.getAssignmentAddDate();
                }
                if ( ! referrals.contains( assignment.getReferralNumber() ) ){
                    referrals.add(assignment.getReferralNumber());
                    if ( loop == 0 ){
                	associatedReferrals.append( assignment.getReferralNumber() );
                    } else {
                	associatedReferrals.append(", " +  assignment.getReferralNumber() );
                    }
                }
                loop++;
                
            }
            
            resp.setAssignedReferrals(associatedReferrals.toString());
            resp.setAssignmentDate(oldestAssignmentDate);
        }
        
        resp.setSubabuse(file.getSubabuse());
        resp.setHispanic(file.getHispanic());
        resp.setVop(file.getVop());
        resp.setSchool(file.getSchool());
        resp.setRiskNeed(file.getRiskNeed());
      
        return resp;
    }

    /**
     * Takes an Assignment and returns a JuvenileCasefileReferralsResponseEvent based off the values
     * within the assignment
     * 
     * @param assignment
     * @return JuvenileCasefileReferralsResponseEvent
     */
    public static JuvenileCasefileReferralsResponseEvent getJuvenileCasefileReferralsResponse(Assignment assignment)
    {
        JuvenileCasefileReferralsResponseEvent responseEvent = new JuvenileCasefileReferralsResponseEvent();
        responseEvent.setAssignmentDate(assignment.getAssignmentAddDate());
        responseEvent.setReferralNumber(assignment.getReferralNumber());
        // assignment level
        JuvenileLevelUnitCode assignmentLevel = assignment.getAssignmentLevel();
        if (assignmentLevel != null)
        {
            responseEvent.setAssignmentLevel(assignmentLevel.getLevel());
        }
        // unit
        JuvenileLevelUnitCode unit = assignment.getServiceUnit();
        if (unit != null)
        {
            responseEvent.setServiceUnit(unit.getUnit());
        }
        return responseEvent;
    }
    
    /**
     * Takes an JPOAssignmentHistory and returns a JPOAssignmentHistoryResponseEvent based off the values
     * within the jpoAssignmentHistory
     * 
     * @param jpoAssignmentHistory
     * @return JPOAssignmentHistoryResponseEvent
     */
    public static JPOAssignmentHistoryResponseEvent getJPOAssignmentHistoryResponseEvent(JPOAssignmentHistory jpoAssignmentHistory)
    {
    	JPOAssignmentHistoryResponseEvent responseEvent = new JPOAssignmentHistoryResponseEvent();
    	responseEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);
        responseEvent.setJpoAssignmentDate(jpoAssignmentHistory.getJpoAssignmentDate());
        responseEvent.setCasefileId(jpoAssignmentHistory.getCasefileId());
        responseEvent.setOfficerProfileId(jpoAssignmentHistory.getOfficerProfileId());
        OfficerProfile officerProfile = jpoAssignmentHistory.getOfficerProfile();
        if (officerProfile != null)
        {
            responseEvent.setOfficerFirstName(officerProfile.getFirstName());
            responseEvent.setOfficerLastName(officerProfile.getLastName());
            responseEvent.setOfficerMiddleName(officerProfile.getMiddleName());
        }
       return responseEvent;
    }
    
    /**
     * Takes an JPOAssignmentHistory and returns a JPOAssignmentHistoryResponseEvent based off the values
     * within the jpoAssignmentHistory
     * 
     * @param jpoAssignmentHistory
     * @return JPOAssignmentHistoryResponseEvent
     */
    public static JPOAssignmentHistoryViewResponseEvent getJPOAssignmentHistoryViewResponseEvent(JPOAssignmentHistoryView jpoAssignmentHistory)
    {
    	JPOAssignmentHistoryViewResponseEvent responseEvent = new JPOAssignmentHistoryViewResponseEvent();
    	responseEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);
        responseEvent.setJpoAssignmentDate(jpoAssignmentHistory.getJpoAssignmentDate());
        responseEvent.setCaseFileId(jpoAssignmentHistory.getCaseFileId());
        responseEvent.setOfficerProfileId(jpoAssignmentHistory.getOfficerProfileId());
        responseEvent.setAssignmentAddDate(jpoAssignmentHistory.getAssignmentAddDate());
        responseEvent.setAssignmentLevelId(jpoAssignmentHistory.getAssignmentLevelId());
        responseEvent.setReferralNumber(jpoAssignmentHistory.getReferralNumber());
        responseEvent.setServiceUnitId(jpoAssignmentHistory.getServiceUnitId());
        responseEvent.setAssignmentType(jpoAssignmentHistory.getAssignmentType());
        responseEvent.setAssignmentTypeDescr(ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getRefAssmntTypeCodes(), jpoAssignmentHistory.getAssignmentType()));        
        OfficerProfile officerProfile = OfficerProfile.find(jpoAssignmentHistory.getOfficerProfileId());
        if (officerProfile != null) {
        	Name fullName = new Name(officerProfile.getFirstName(),officerProfile.getMiddleName(),officerProfile.getLastName());
        	String officerProfileName = fullName.getFormattedName();
            responseEvent.setJpoName(officerProfileName);
            responseEvent.setJpoLogonId(officerProfile.getLogonId());
		}
        if (jpoAssignmentHistory.getAssignedByUserId() != null) {
        	UserProfile assignedByUserProfile = UserProfile.find(jpoAssignmentHistory.getAssignedByUserId());
        	if (assignedByUserProfile != null) {
        		Name assignFullName = new Name(assignedByUserProfile.getFirstName(),assignedByUserProfile.getMiddleName(),assignedByUserProfile.getLastName());
        		String assignByFullName = assignFullName.getFormattedName();
        		responseEvent.setAssignedByName(assignByFullName);
        		responseEvent.setAssignedByLogonId(jpoAssignmentHistory.getAssignedByUserId());
        	}
       	} else {
       		responseEvent.setAssignedByName("CASEFILE EXTRACT");
       		responseEvent.setAssignedByLogonId("CFEXTR");
        }
        
       return responseEvent;
    }

    /**
     * Calculates the age in years given a date.
     * 
     * @param ageDate
     * @return age in years, 0 if age parameter is null
     */
    public static int getAgeInYears(Date ageDate)
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
     * @param className
     * @param notificationType
     * @return taskName
     */
    public static String getTaskName(String className, int notificationType)
    {
        String taskName = className + "-" + System.currentTimeMillis() + "-" + notificationType;
        return taskName;
    }

/*    //TODO: Dawn - Remove the following before moving to production
    private static StringBuffer printServiceDataForQA(StringBuffer results, JJSService assignment)
    {
        results.append('\n');
        results.append("juvenileNum=");
        results.append(assignment.getJuvenileNum());
        results.append(" referralNum=");
        results.append(assignment.getReferralNum());
        results.append(" seqNum=");
        results.append(assignment.getSeqNum());
        results.append(" assignmentLevelId=");
        results.append(assignment.getAssignmentLevelId());
        results.append(" unitId=");
        results.append(assignment.getUnitId());
        results.append(" serviceType=");
        results.append(assignment.getServiceTypeId());
        results.append(" addDate=");
        results.append(DateUtil.dateToString(assignment.getAddDate(), "MM/dd/yyyy"));
        results.append(" serviceDate=");
        results.append(DateUtil.dateToString(assignment.getServiceDate(), "MM/dd/yyyy"));
        return results;
    }*/

    /**
     * @param juvfamily
     */
    private static FamilyConstellation createFamilyConstellation(JJSFamily juvfamily, String juvNum)
    {
        // TODO Auto-generated method stub
        FamilyConstellation familyConstellation = null;

        // mother
        // check if member exists
        FamilyMember mother = getFamilyMember(juvfamily.getMotherFirstName(), juvfamily.getMotherMiddleName(),
                juvfamily.getMotherLastName(), juvfamily.getMotherAddress(), juvfamily.getMotherPhone(), juvfamily
                        .getMotherSsn());

        boolean isGaurdianAvailable = false;

        // add member to constellation
        if (mother != null)
        {
            if (familyConstellation == null)
                familyConstellation = createFamilyConstellation(juvNum);
            FamilyConstellationMember familyConstellationMember = createCostellationMember(mother, "BM");
            familyConstellationMember.setGuardian(true);
            isGaurdianAvailable = true;

            familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
            SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
            aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
            aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
            JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);

        }

        // father
        FamilyMember father = getFamilyMember(juvfamily.getFatherFirstName(), juvfamily.getFatherMiddleName(),
                juvfamily.getFatherLastName(), juvfamily.getFatherAddress(), juvfamily.getFatherPhone(), juvfamily
                        .getFatherSsn());
        // add member to constellation
        if (father != null)
        {
            if (familyConstellation == null)
                familyConstellation = createFamilyConstellation(juvNum);
            FamilyConstellationMember familyConstellationMember = createCostellationMember(father, "BF");
            familyConstellationMember.setGuardian(true);
            isGaurdianAvailable = true;
            familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
            SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
            aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
            aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
            JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
        }

        //		// other
        FamilyMember other = getFamilyMember(juvfamily.getOtherFirstName(), juvfamily.getOtherMiddleName(), juvfamily
                .getOtherLastName(), juvfamily.getOtherAddress(), juvfamily.getOtherPhone(), juvfamily.getOtherSsn());
        // add member to constellation
        if (other != null)
        {
            if (familyConstellation == null)
                familyConstellation = createFamilyConstellation(juvNum);

            FamilyConstellationMember familyConstellationMember = createCostellationMember(other, "OR");
            familyConstellation.insertFamilyConstellationMembers(familyConstellationMember);
            if (isGaurdianAvailable == false)
            {
                familyConstellationMember.setGuardian(true);
                SaveFamilyMemberFinancialEvent aEvent = new SaveFamilyMemberFinancialEvent();
                aEvent.setConstellationMemberId(Integer.parseInt(familyConstellationMember.getTheFamilyMemberId()));
                aEvent.setConstellationNum(familyConstellationMember.getFamilyConstellationId());
                JuvenileFamilyHelper.saveConstellationMemberFinancial(aEvent, familyConstellationMember);
            }
        }

        return familyConstellation;

    }

    /*
     * 
     *  
     */
    private static FamilyConstellation createFamilyConstellation(String juvNum)
    {
        FamilyConstellation familyConstellation = new FamilyConstellation();
        familyConstellation.setEntryDate(new Date());
        familyConstellation.setJuvenileId(juvNum);
        familyConstellation.setActive(true);
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
        }
        return familyConstellationMember;
    }

    /**
     * @param string
     * @param string2
     * @param string3
     * @return
     */
    private static FamilyMember getFamilyMember(String firstName, String middleName, String lastName,
            Address memberAddress, String memberPhoneNum, String Ssn)
    {
        /*
         * create member
         *  
         */
        // TODO Auto-generated method stub
        FamilyMember member = null;
        if ((firstName != null && firstName.trim().length() > 0)
                || (middleName != null && middleName.trim().length() > 0)
                || (lastName != null && lastName.trim().length() > 0))
        {
            //TODO need to check if member exists
            member = new FamilyMember();
            member.setFirstName(firstName);
            member.setLastName(lastName);
            member.setMiddleName(middleName);
            member.setSsn(Ssn);
            // create address
            if (memberAddress != null)
            {
                Address address = null;
                // check if address exists
                Iterator iter = Address.findAll("streetName", memberAddress.getStreetName());
                if (iter.hasNext())
                {
                    address = (Address) iter.next();
                }
                else
                {
                    address = new Address();
                    address.setStreetName(memberAddress.getStreetName());
                    address.setStreetNum(memberAddress.getStreetNum());
                    address.setStreetTypeId(memberAddress.getStreetTypeId());
                    address.setAptNum(memberAddress.getAptNum());
                    address.setAddressTypeId(memberAddress.getAddressTypeId());
                    address.setStateId(memberAddress.getStateId());
                    address.setCity(memberAddress.getCity());
                    address.setZipCode(memberAddress.getZipCode());
                }

                member.insertAddresses(address);
            }

            FamilyMemberPhone memberPhone = null;
            if (memberPhoneNum != null && memberPhoneNum.trim().length() > 0)
                memberPhone = JuvenileFamilyHelper.createMemberPhone(memberPhoneNum, "", "RES", (String) member
                        .getOID());
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

    /*
     * @param juvenileName @param casefileId
     */
    private static String getTaskTitleForActivateCasefile(String juvenileName, String casefileId)
    {
        StringBuffer title = new StringBuffer("Activate Casefile Assignment for Juvenile ");
        title.append(juvenileName);
        title.append(" and Supervision Number: ");
        title.append(casefileId);
        return title.toString();
    }

    public static Iterator<FamilyMember> checkFamilyMemberSSN(String memSSN)
    {
//    	check if any other family member has the same ssn
		GetFamilyMembersBySSNEvent ssnEvent = new GetFamilyMembersBySSNEvent();
		ssnEvent.setSsn(memSSN);
		Iterator<FamilyMember> familyMembers = FamilyMember.findAll(ssnEvent);
		return familyMembers;
    	
    }
    
    public static Iterator<FamilyMember> checkFamilyMemberDriverLicenseNum(String memDriverLicenseNum)
    {
//    	check if any other family member has the same ssn
	        GetFamilyMembersByDLNumberEvent DLEvent = new GetFamilyMembersByDLNumberEvent();
	        DLEvent.setDriverLicenseNum(memDriverLicenseNum);
		Iterator<FamilyMember> familyMembers = FamilyMember.findAll(DLEvent);
		return familyMembers;
    	
    }
    
    public static void markMembersSuspicious(String memberA, String memberB)
    {
    	UpdateFamilyMemberMatchesEvent event = new UpdateFamilyMemberMatchesEvent();
        event.setMemberA(memberA);
        event.setMemberB(memberB);	          
        event.setStatus("UNRESOLVED");
        event.setNotes("System Generated Notes: Match found when member number " + memberB+" was being created during Extraction process.");
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        try
        {
            dispatch.postEvent(event);
        }
        catch (Exception e)
        {
            // ignore any exception as this is not visible to the user
        }
    	
    }
    
    public static CasefileClosingInfo getCasefileClosingDetails(String supervisionNum)
    {

		List <CasefileClosingInfo> closingInfoList = CollectionUtil.iteratorToList(CasefileClosingInfo.findAll(PDJuvenileCaseConstants.SUPERVISION_NUMBER, supervisionNum));
		CasefileClosingInfo casefileClosing = null;

		if (closingInfoList.size() > 0){
			casefileClosing = closingInfoList.get(0);
		}
        return casefileClosing;
    }
    
	/**
	 * Referral Decisions Codes 
	 * @return list of codes
	 */
	public static  List<JuvenileReferralDispositionCode> getReferralDecisions(){
		List<JuvenileReferralDispositionCode> juvReferralDispList = new ArrayList<JuvenileReferralDispositionCode>();

		Iterator<JuvenileReferralDispositionCode> juvReferralDispItr = JuvenileReferralDispositionCode.findAll();
		while (juvReferralDispItr.hasNext()) 
		{
		    JuvenileReferralDispositionCode disp = (JuvenileReferralDispositionCode)juvReferralDispItr.next();
		    disp.setCodeWithDescription(disp.getCode() + " - " + disp.getDescription());
		    if(disp.getInactiveInd()==null)
			juvReferralDispList.add(disp);
		    else if(! "Y".equalsIgnoreCase(disp.getInactiveInd()))
			juvReferralDispList.add(disp);		    
		}
		return juvReferralDispList;
	}
	/**
	 * Referral Decisions Codes 
	 * @return list of codes
	 */
	public static  List<JuvenileReferralDispositionCode> getAllReferralDecisions(){
		List<JuvenileReferralDispositionCode> juvReferralDispList = new ArrayList<JuvenileReferralDispositionCode>();

		Iterator<JuvenileReferralDispositionCode> juvReferralDispItr = JuvenileReferralDispositionCode.findAll();
		while (juvReferralDispItr.hasNext()) 
		{
		    JuvenileReferralDispositionCode disp = (JuvenileReferralDispositionCode)juvReferralDispItr.next();
		    disp.setCodeWithDescription(disp.getCode() + " - " + disp.getDescription());		    
		    juvReferralDispList.add(disp);
		}
		return juvReferralDispList;
	}
	
	/**
	 * Get Court Decisions Codes
	 * @return list of codes
	 */
	public static List<JuvenileDispositionCode> getCourtDecisions(){
		List<JuvenileDispositionCode> juvDispList = new ArrayList<JuvenileDispositionCode>();
		
		Iterator<JuvenileDispositionCode> juvDispItr = JuvenileDispositionCode.findAll();
		while (juvDispItr!=null && juvDispItr.hasNext()) 
		{
			juvDispList.add(juvDispItr.next());
		}
		return juvDispList;
	}
	
	/**
	 * Get Court Decisions Codes
	 * @return list of codes
	 */
	public static List<JuvenileCourtDecisionCodeResponseEvent> getCourtDecisionsNew(){
		
	    GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) 
			  				EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
	  
	    Collection<JuvenileCourtDecisionCodeResponseEvent> juvDisp = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
	    
	    Collections.sort((List<JuvenileCourtDecisionCodeResponseEvent>) juvDisp, Collections.reverseOrder(new Comparator<JuvenileCourtDecisionCodeResponseEvent>() {
		    @Override
		    public int compare(JuvenileCourtDecisionCodeResponseEvent evt1, JuvenileCourtDecisionCodeResponseEvent evt2)
		    {
			if (evt1.getCode() != null && evt2.getCode() != null)
			    return evt2.getCode().compareTo(evt1.getCode());
			else
			    return -1;
		    }
		}));
	    
	    return (List<JuvenileCourtDecisionCodeResponseEvent>) juvDisp;
	}
	/**
	 * Get Court Decisions Codes
	 * @return list of codes
	 */
	public static List<JuvenileCourtDecisionCodeResponseEvent> getAllCourtDecisions(){
		
	    GetAllJuvenileCourtDecisionCodesEvent courtDisp = (GetAllJuvenileCourtDecisionCodesEvent) 
			  				EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILECOURTDECISIONCODES);
	  
	    Collection<JuvenileCourtDecisionCodeResponseEvent> juvDisp = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
	    
	    Collections.sort((List<JuvenileCourtDecisionCodeResponseEvent>) juvDisp, Collections.reverseOrder(new Comparator<JuvenileCourtDecisionCodeResponseEvent>() {
		    @Override
		    public int compare(JuvenileCourtDecisionCodeResponseEvent evt1, JuvenileCourtDecisionCodeResponseEvent evt2)
		    {
			if (evt1.getCode() != null && evt2.getCode() != null)
			    return evt2.getCode().compareTo(evt1.getCode());
			else
			    return -1;
		    }
		}));
	    
	    return (List<JuvenileCourtDecisionCodeResponseEvent>) juvDisp;
	}
	/**
	 * Get coactors
	 * @return list of codes
	 */
	//added for task 151689
	public static List<JuvenileCoactorsResponseEvent> getjuvenileCoactors(String juvNum,String refNum){
	    
	  //add code to get coactor juvnum and attorney of that in response event
	  //add code to get coactor juvnum and attorney of that in response event
		
	     Collection<JuvenileCoactorsResponseEvent> juvCoactors = new ArrayList<JuvenileCoactorsResponseEvent>();
	     GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	     refEvent.setJuvenileNum(juvNum);
	     refEvent.setReferralNum(refNum);
	     JJSReferral  referralResp =getJuvenileReferralDetails(refEvent);
	     if(referralResp!=null )
	     {	
		if(referralResp.getTransactionNum()!=null&&!referralResp.getTransactionNum().isEmpty()) 
		{
		Iterator<JJSReferral> refIter = JJSReferral.findAll("transactionNum", referralResp.getTransactionNum() );
		String uniqueJuvnum="";
		List refNums = new ArrayList();
		while(refIter.hasNext())
		{
			JJSReferral aRefferal = refIter.next();
			//if(aRefferal.getCloseDate()==null) bug no #89976
			    refNums.add(aRefferal);
			    
		}
		Collections.sort((List<JJSReferral>)refNums,Collections.reverseOrder(new Comparator<JJSReferral>() {
			@Override
			public int compare(JJSReferral evt1, JJSReferral evt2) {
				return Integer.valueOf(evt1.getJuvenileNum()).compareTo(Integer.valueOf(evt2.getJuvenileNum()));
			}
		}));
		
		Iterator<JJSReferral> referralIter =refNums.iterator();
		while (referralIter.hasNext())
		{
		    JuvenileCoactorsResponseEvent coactorResp = new JuvenileCoactorsResponseEvent();
		    JJSReferral ref = (JJSReferral)referralIter.next();
		    
		    if(!ref.getJuvenileNum().equalsIgnoreCase(juvNum)&&!ref.getJuvenileNum().equalsIgnoreCase(uniqueJuvnum))//
		    {
			 	//get attorney from last attorney table for each coactors			
        			Iterator it = JJSLastAttorney.findAll("juvenileNum",ref.getJuvenileNum());        			
        			if(it.hasNext())
        			{                         		 		    
                            		  	JJSLastAttorney rec = (JJSLastAttorney) it.next();                    		  	
                            		  	coactorResp.setAttorneyName(rec.getAtyName());                    		  	   		   
                        	}
        			coactorResp.setJuvenileNum(ref.getJuvenileNum());
        			juvCoactors.add(coactorResp);
        			uniqueJuvnum=ref.getJuvenileNum();			
		    }
		      
		}
	     }
	     }
	    return (List<JuvenileCoactorsResponseEvent>) juvCoactors;
	}
	/**
	 * Added for user-story 34655
	 * @param controllingRefNum
	 * @param casefileId
	 * @param juvNum
	 * @return dispositionDate
	 */
	public static String getDispositionDate(JuvenileCasefile casefile){
		
		String dispositionDate="";
		GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
		if(casefile!=null && casefile.getCasefileControllingReferralId()!=null && !casefile.getCasefileControllingReferralId().isEmpty()){
		 refEvent.setJuvenileNum(casefile.getJuvenileNum());
		 refEvent.setReferralNum(casefile.getCasefileControllingReferralId());
		 JJSReferral  referralResp =getJuvenileReferralDetails(refEvent);
		  
	       if(referralResp != null && referralResp.getCourtDate()!=null){
		      	if(casefile.getSupervisionCategoryId()!=null && (casefile.getSupervisionCategoryId().equals(UIConstants.POST_ADJUDICATION_COMMUNITY)||casefile.getSupervisionCategoryId().equals(UIConstants.DEFERRED_ADJUDICATION)||
					casefile.getSupervisionCategoryId().equals(UIConstants.POST_ADJUDICATION_RESIDENTIAL))){
		      				dispositionDate = DateUtil.dateToString(referralResp.getCourtDate(),DateUtil.DATE_FMT_1);
				}
	       }else{
	       		dispositionDate= "";
	       }
		}
	        
	    if(casefile.getSupervisionCategoryId()!=null && (casefile.getSupervisionCategoryId().equals(UIConstants.PRE_ADJUDICATION)|| casefile.getSupervisionCategoryId().equals(UIConstants.PRE_PETITION))){
			String lowestCtrlRefNum = UIProgramReferralHelper.getLowestCtrlngRefNbr((String) casefile.getCasefileId());
			if(lowestCtrlRefNum!=null && !lowestCtrlRefNum.isEmpty() ){
				refEvent.setJuvenileNum(casefile.getJuvenileNum());
				refEvent.setReferralNum(lowestCtrlRefNum);
				JJSReferral lowestReferralResp = getJuvenileReferralDetails(refEvent);
		        	if(lowestReferralResp!=null && lowestReferralResp.getCourtDate()!=null){
		        		dispositionDate = DateUtil.dateToString(lowestReferralResp.getCourtDate(),DateUtil.DATE_FMT_1);
		        	}else{
			        	dispositionDate="";
		        	}
				}
			}
		return dispositionDate;
	}
	
	/**
 	 * Added for user-story 34655
	 * GetJuvenileCasefileReferralDetailsEvent
	 * @param refEvent
	 * @param controllingRefNum
	 */
	private static JJSReferral getJuvenileReferralDetails(GetJJSReferralEvent refEvent ){
		JJSReferral referralResp = null;
	        //List<JJSReferralResponseEvent> referralResps = MessageUtil.postRequestListFilter(refEvent, JJSReferralResponseEvent.class);
			Iterator<JJSReferral>referralRespItr = JJSReferral.findAll(refEvent);
		      if(referralRespItr.hasNext()){
		    	  referralResp = referralRespItr.next();
		       }
	       return referralResp;
		}
	
	
	public static List<JJSCLDetention> getSequenceNumbers(String juvNumber){
	    List<JJSCLDetention> listSeqNumbers = new ArrayList<JJSCLDetention>();

	    String startDate = "1900-01-01";
	    String endDate = "2100-12-31";
	    
	    GetJJSCLDetentionByDateRangeEvent det = new GetJJSCLDetentionByDateRangeEvent();
	    det.setJuvenileNumber(juvNumber);
	    det.setStartDate(startDate);
	    det.setEndDate(endDate);
	   
	    Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(det);

	    List<String> seqNumbers = new ArrayList<String>();
	    
	    while (detentionItr.hasNext())
		{
			JJSCLDetention detention = (JJSCLDetention) detentionItr.next();
			
			String currentSeqNumber = detention.getSeqNumber();			
			
			if(currentSeqNumber != null && !currentSeqNumber.equals("")){
			    
			    if(!seqNumbers.contains(currentSeqNumber)){
				    listSeqNumbers.add(detention);
				    seqNumbers.add(currentSeqNumber);
			    }
			    
			}		
			
	    	}
	    
	    if (listSeqNumbers.size() > 1)
		{
		    Collections.sort((List<JJSCLDetention>) listSeqNumbers, new Comparator<JJSCLDetention>() {
			@Override
			public int compare(JJSCLDetention seq1, JJSCLDetention seq2)
			{	
			    if(seq1.getSeqNumber() != null && seq2.getSeqNumber() != null){
				return Integer.parseInt(seq1.getSeqNumber()) - Integer.parseInt(seq2.getSeqNumber());
			    } else {
				return -1;
			    }
			}
		    });

		}
	    
	    return listSeqNumbers;
	}
	
	
	/**
	 * 
	 * @param juvNumber
	 * @param jjsDetentionId
	 * @return
	 */
	public static boolean jjsDetentionIdExists(String juvNumber, String jjsDetentionId){

	    /*String startDate = "1900-01-01";
	    String endDate = "2100-12-31";*/
	    boolean detentionIdExists = false;
	    
	    GetJJSCLDetentionByDateRangeEvent det = new GetJJSCLDetentionByDateRangeEvent();
	    det.setJuvenileNumber(juvNumber);
/*	    det.setStartDate(startDate);
	    det.setEndDate(endDate);*/
	   
	    Iterator<JJSFacility> facilityItr = JJSFacility.findAll("OID", jjsDetentionId);
	    
	    if ( facilityItr.hasNext() )
		{
		JJSFacility facility = (JJSFacility) facilityItr.next();
			
			if( facility.getOID() != null ){
			    
			    detentionIdExists = true;
			    
			}
			
			/*if((jjsDetentionId == null || (jjsDetentionId != null && jjsDetentionId.equals(""))) 
				&& (detentionId == null ||(detentionId != null && detentionId.equals("")))){
			    detentionIdExists = true;
			}*/		
			
			
	    	}
	    
	    return detentionIdExists;
	}
	
	
	public static boolean sequenceNumberExists(String juvNumber, String seqNmbr){

	    String startDate = "1900-01-01";
	    String endDate = "2100-12-31";
	    boolean seqNumExists = false;
	    
	    GetJJSCLDetentionByDateRangeEvent det = new GetJJSCLDetentionByDateRangeEvent();
	    det.setJuvenileNumber(juvNumber);
	    det.setStartDate(startDate);
	    det.setEndDate(endDate);
	   
	    Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(det);
	    
	    while (detentionItr.hasNext())
		{
			JJSCLDetention detention = (JJSCLDetention) detentionItr.next();
			
			String seqNumber = detention.getSeqNumber();			
			
			if(seqNumber != null){
			    
			    if(seqNumber.equals(seqNmbr)){
				seqNumExists = true;
				break;
			    }	
			}
			
			if((seqNmbr == null || (seqNmbr != null && seqNmbr.equals(""))) 
				&& (seqNumber == null ||(seqNumber != null && seqNumber.equals("")))){
			    seqNumExists = true;
			}		
			
			
	    	}
	    
	    return seqNumExists;
	}
	
	public static MaysiDataReportBean populateMAYSIReportDetails(MAYSIDetailsResponseEvent detail)
	{
	    if(detail != null)
	    {	
		 MaysiDataReportBean reportBean = new MaysiDataReportBean();	
		 
		String juvName = null;
		String juvStatus = null;
		
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
		reqProfileMain.setJuvenileNum(detail.getJuvenileNum());
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		
		JuvenileCore juvCore = JuvenileCore.findCore(detail.getJuvenileNum());		

		if(juvProfileRE != null)
		{
		    if(juvProfileRE.getMiddleName() != null && !"".equals(juvProfileRE.getMiddleName()))
		    {
			juvName = juvProfileRE.getLastName() + ", " + juvProfileRE.getFirstName() + " " + juvProfileRE.getMiddleName();
		    }
		    else
		    {
			juvName = juvProfileRE.getLastName() + ", " + juvProfileRE.getFirstName() + " " + juvProfileRE.getMiddleName();
		    }
		    
		    if(juvProfileRE.getStatus() != null && !"".equals(juvProfileRE.getStatus()))
		    {
			juvStatus = juvProfileRE.getStatus();
		    }
		    
		    reportBean.setJuvenileName(juvName);
		    reportBean.setJuvenileStatus(juvStatus);
		    reportBean.setJuvenileAge(juvCore.getAgeInYears(juvCore.getDateOfBirth()));
		}
	       
	    	
	    	reportBean.setJuvenileNum(detail.getJuvenileNum());
	    	reportBean.setReferralNumber(String.valueOf(detail.getReferralNumber()));
	    	reportBean.setMaysiId(detail.getAssessmentId());
	    	reportBean.setSexId(detail.getSexId());
	    	reportBean.setSex(detail.getSex());
	    	reportBean.setRaceId(detail.getRaceId());
	    	reportBean.setTestAge(detail.getTestAge());
	    	reportBean.setRace(detail.getRace());
	    	
	    	if(detail.getAssessmentDate() == null)
	    	{
	    	    reportBean.setAssessmentDate("");
	    	}
	    	else {
	    	    reportBean.setAssessmentDate(DateUtil.dateToString(detail.getAssessmentDate(), DateUtil.DATE_FMT_1));
	    	}
	    	
	    	if(detail.getAssessmentTime() == null){
	    	    reportBean.setAssessmentTime("");
	    	}
	    	else {
	    	    reportBean.setAssessmentTime(detail.getAssessmentTime());
	    	}
	    	
	    	
	    	if(detail.getAssessmentReviewdate() == null)
	    	{
	    	    reportBean.setAssessmentReviewDate("");
	    	}
	    	else 
	    	{
	    	    reportBean.setAssessmentReviewDate(DateUtil.dateToString(detail.getAssessmentReviewdate(), DateUtil.DATE_FMT_1));
	    	}
	    	
	    	if(detail.getAssessmentReviewtime() == null || detail.getAssessmentReviewtime() == "null")
	    	{
	    	    reportBean.setAssessmentReviewTime("");
	    	}
	    	else
	    	{
	    	    reportBean.setAssessmentReviewTime(detail.getAssessmentReviewtime());
	    	}
	    	

		Name officerName = new Name(detail.getAssessOfficerName().getFirstName(),
				detail.getAssessOfficerName().getMiddleName(), detail.getAssessOfficerName().getLastName());
		
		reportBean.setAssessmentOfficerName(officerName.getFormattedName());
		reportBean.setAssessmentOption(detail.getAssessmentOption());
		if(detail.isHasPreviousMAYSI())
		{
		    reportBean.setHasPreviousMAYSI("YES");
		}
		else 
		{
		    reportBean.setHasPreviousMAYSI("NO");
		}
		
		reportBean.setHispanic(detail.getHispanic());
		if(detail.isAdministered())
		{
		    reportBean.setAdminister("YES");
		}
		else 
		{
		    reportBean.setAdminister("NO");
		}
		reportBean.setOtherReasonNotDone(detail.getOtherReasonNotDone());
		reportBean.setReasonNotDone(detail.getReasonNotDone());
		reportBean.setScheduledOffIntDateStr("");
		if (detail.getScheduledOffIntDate() != null){
		    reportBean.setScheduledOffIntDateStr(DateUtil.dateToString(detail.getScheduledOffIntDate(), DateUtil.DATE_FMT_1));
		} 		
		
		if( detail.getReasonNotDoneId() == null )
		{
		    reportBean.setReasonNotDoneId("");
		}

		String loc = detail.getLocationUnit();
		if( loc == null || loc.equals("null") )
		{
		    reportBean.setLocationUnit("");
		}
		else
		{
		    reportBean.setLocationUnit(loc);
		}

		reportBean.setLengthOfStay(detail.getLengthOfStay());

		reportBean.setFacilityType(detail.getFacilityType());
		reportBean.setHasDetails(false);
		if( detail.haveMAYSIDetails() )
		{
		    reportBean.setHasDetails(true);
		    //reportBean.setCautionsNWarnings(detail);
		    //set Cautions and Warnings
		    reportBean.setAlcoholDrugCaution(false);
		    reportBean.setAlcoholDrugWarning(false);
			if( detail.getAlcoholDrug() >= 7 )
			{
			    reportBean.setAlcoholDrugWarning(true);
			}
			else if( detail.getAlcoholDrug() >= 4 )
			{
			    reportBean.setAlcoholDrugCaution(true);
			}
			reportBean.setAngryIrritableCaution(false);
			reportBean.setAngryIrritableWarning(false);
			if( detail.getAngryIrritable() >= 8 )
			{
			    reportBean.setAngryIrritableWarning(true);
			}
			else if( detail.getAngryIrritable() >= 5 )
			{
			    reportBean.setAngryIrritableCaution(true);
			}
			reportBean.setDepressionAnxietyCaution(false);
			reportBean.setDepressionAnxietyWarning(false);
			if( detail.getDepressionAnxiety() >= 6 )
			{
			    reportBean.setDepressionAnxietyWarning(true);
			}
			else if( detail.getDepressionAnxiety() >= 3 )
			{
			    reportBean.setDepressionAnxietyCaution(true);
			}
			reportBean.setSomaticComplaintCaution(false);
			reportBean.setSomaticComplaintWarning(false);
			if( detail.getSomaticComplaint() >= 6 )
			{
			    reportBean.setSomaticComplaintWarning(true);
			}
			else if( detail.getSomaticComplaint() >= 3 )
			{
			    reportBean.setSomaticComplaintCaution(true);
			}
			reportBean.setThoughtDisturbanceCaution(false);
			reportBean.setThoughtDisturbanceWarning(false);
			if( detail.getThoughtDisturbance() >= 2 )
			{
			    reportBean.setThoughtDisturbanceWarning(true);
			}
			else if( detail.getThoughtDisturbance() >= 1 )
			{
			    reportBean.setThoughtDisturbanceCaution(true);
			}
			//<KISHORE>JIMS200060170 : MJCW - MAYSI Isn't Displaying the Warnings Correctly
			// Add logic here for suicide/Ideation 
			// Once requirements document has been updated
			//maysiDetail.jsp has already been updated
			reportBean.setSuicideIdeationCaution(false);
			reportBean.setSuicideIdeationWarning(false);
			// 08/13/2013 #75944 revised values for Warning from >= 3 to > 1 and Caution from >= 2 to == 1 
			if( detail.getSuicideIdetaion() > 1 )
			{
			    reportBean.setSuicideIdeationWarning(true);
			}
			else if( detail.getSuicideIdetaion() == 1 )
			{
			    reportBean.setSuicideIdeationCaution(true);
			}
			//===================================================
		    reportBean.setDetailSexId(detail.getDetailSexId());
		    reportBean.setDetailRaceId(detail.getDetailRaceId());

		    reportBean.setScreenDate(DateUtil.dateToString(detail.getScreenDate(), DateUtil.DATE_FMT_1));
		    reportBean.setAlcoholDrug(String.valueOf(detail.getAlcoholDrug()));
		    reportBean.setAngryIrritable(String.valueOf(detail.getAngryIrritable()));
		    reportBean.setDepressionAnxiety(String.valueOf(detail.getDepressionAnxiety()));
		    reportBean.setSomaticComplaint(String.valueOf(detail.getSomaticComplaint()));
		    reportBean.setSuicideIdeation(String.valueOf(detail.getSuicideIdetaion()));
		    reportBean.setThoughtDisturbance(String.valueOf(detail.getThoughtDisturbance()));
		    reportBean.setTraumaticExpression(String.valueOf(detail.getTraumaticExpression()));

		}
		reportBean.setHasSubAssessment(false);
		if( detail.haveMAYSISubAssessDetails() )
		{
		    reportBean.setHasSubAssessment(true);
		    if(detail.getAssessmentReviewdate() == null)
		    {
			reportBean.setAssessmentReviewDate("");
		    }
		    else
		    {
			reportBean.setAssessmentReviewDate(DateUtil.dateToString(detail.getAssessmentReviewdate(), DateUtil.DATE_FMT_1));
		    }
		    
		    if(detail.getAssessmentReviewtime() == null)
		    {
			reportBean.setAssessmentReviewTime("");
		    }
		    else 
		    {
			reportBean.setAssessmentReviewTime(detail.getAssessmentReviewtime());
		    }		    
		    
		    if(detail.isSubReferral())
		    {
			reportBean.setSubsAssessmentReferral("YES");
		    }
		    else 
		    {
			reportBean.setSubsAssessmentReferral("NO");
		    }

		    reportBean.setProviderReferredType(detail.getProviderType());
		    
		    if(detail.isAssessComplete())
		    {
			reportBean.setWasSubsAssessmentCompleted("YES");
		    }
		    else 
		    {
			reportBean.setWasSubsAssessmentCompleted("NO");
		    }

		    reportBean.setSubsAssessmentComments(detail.getReviewComments());
		}
		
		return reportBean;
	    }
	    
	    
	    return null;
	    
	}
	
    //	/**
    //	 * Returns a JJSServiceResponseEvent based on values in JJSService.
    //	 * @param jjsService
    //	 * @return
    //	 */
    //	public static JJSServiceResponseEvent getJJSServiceResponseEvent(JJSService jjsService)
    //	{
    //		JJSServiceResponseEvent re = new JJSServiceResponseEvent();
    //		re.setAddDate(jjsService.getAddDate());
    //		re.setAmount(jjsService.getAmount());
    //		re.setAssignmentLevelId(jjsService.getAssignmentLevelId());
    //		re.setCaseLoadManagerId(jjsService.getCaseLoadManagerId());
    //		re.setCategoryId(jjsService.getCategoryId());
    //		re.setDeptId(jjsService.getDeptId());
    //		re.setFundingSourceId(jjsService.getFundingSourceId());
    //		re.setJims2ExtractInd(jjsService.getJims2ExtractInd());
    //		re.setJuvenileNum(jjsService.getJuvenileNum());
    //		re.setProbationOfficerId(jjsService.getProbationOfficerId());
    //		re.setReferralNum(jjsService.getReferralNum());
    //		re.setSchoolProgramId(jjsService.getSchoolProgramId());
    //		re.setSeqNum(jjsService.getSeqNum());
    //		re.setServiceDate(jjsService.getServiceDate());
    //		re.setServiceTypeId(jjsService.getServiceTypeId());
    //		re.setUnitId(jjsService.getUnitId());
    //		re.setVendorId(jjsService.getVendorId());
    //		return re;
    //	}

}
