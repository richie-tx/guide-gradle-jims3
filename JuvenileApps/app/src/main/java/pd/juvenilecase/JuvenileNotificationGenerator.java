/*
 * Created on June 06, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilecase.JuvenileCasefileNotificationEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.naming.NotificationControllerSerivceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDTaskConstants;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.task.Task;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class JuvenileNotificationGenerator /*extends CasefileExtractionUtility*/
{
		
	/**
	 * Method to send the Notices to CaseLoadManagers about the exceptions during the generating casefile  
     * @param errorMap
     * @param t
     * // no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.opic
	 */	
    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/
/*	protected static void sendCaseFileGenerationFailureNotifications(Map errorMap, String topic) throws RuntimeException, Exception{
		Iterator caseManagerIds = errorMap.keySet().iterator();
		while(caseManagerIds.hasNext()){
			String caseManagerId = (String) caseManagerIds.next();
			List errorList = (List) errorMap.get(caseManagerId);
			Iterator iterator = errorList.iterator();
			StringBuffer errorMessageBuffer = new StringBuffer();
			int i=0;
			while(iterator.hasNext()){
				JuvenileCasefileExtractionErrorBean bean = (JuvenileCasefileExtractionErrorBean) iterator.next();
				errorMessageBuffer.append("Notice=");
				errorMessageBuffer.append(i++);
				errorMessageBuffer.append(logMessage(bean.getJjsService(), bean.getErrorMessage()));
				errorMessageBuffer.append("<BR>");
			}
			sendCaseFileExtractionNotification(caseManagerId, "Send Failure Notice for Casefile Generation", errorMessageBuffer.toString(), UIConstants.FAILURE_CASEFILE_NOTIFICATION);
		}
		caseManagerIds = null;
	}*/				
	
	/*
	 * @param casefile
	 * @param officer
	 */
	public static void sendNewJuvenileCasefileNotification(JuvenileCasefile casefile, OfficerProfile officer) throws RuntimeException, Exception
	{
	    	// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
	    	JuvenileCore juvenile = casefile.getJuvenile();
	    	//
		String juvenileFirstName = juvenile.getFirstName();
		String juvenileLastName = juvenile.getLastName();
		String probationOfficerFirstName = officer.getFirstName();
		String probationOfficerLastName = officer.getLastName();
		
		StringBuffer subject = new StringBuffer("Casefile (");
		subject.append(casefile.getOID().toString());
		subject.append(") has been assigned to ");
		subject.append(probationOfficerFirstName);
		subject.append(" ");
		subject.append(probationOfficerLastName);
		subject.append(".");

		StringBuffer messageText = new StringBuffer();
		messageText.append(juvenileFirstName);
		messageText.append(" ");
		messageText.append(juvenileLastName);
		messageText.append(" (");
		messageText.append(juvenile.getJuvenileNum());
		messageText.append("), Supervision#:");
		messageText.append(casefile.getOID().toString());
		messageText.append(" has been assigned to ");
		messageText.append(probationOfficerFirstName);
		messageText.append(" ");
		messageText.append(probationOfficerLastName);
		messageText.append("."); 
		
		Iterator assignments = casefile.getAssignments().iterator();
		if(assignments.hasNext()){
			messageText.append(" The casefile includes the following referrals:");
		}
		while (assignments.hasNext())
		{
			Assignment assignment = (Assignment) assignments.next();
			messageText.append(assignment.getReferralNumber());
			
			if(assignments.hasNext()){
				messageText.append(",");
			}
			assignment = null;
		}
		messageText.append("<BR>");
		messageText.append("The MAYSI indicator is ");
		messageText.append(casefile.getIsMAYSINeeded());
		//taken out for US 14459
		/*messageText.append(". The Title 4E assessment indicator is ");
		messageText.append(casefile.getIsBenefitsAssessmentNeeded());*/
		messageText.append(". The Risk Analysis indicator is ");
		messageText.append(casefile.getIsRiskAssessmentNeeded());
		messageText.append(".");
		
		sendCaseFileExtractionNotification(officer.getLogonId(), subject.toString(), messageText.toString(), PDJuvenileCaseConstants.MJCW_JPO_NEWCASEFILEGENERATION);
		juvenile = null;
		assignments = null;
	}

	/*
	 * @juvenileNum
	 * @jpoId
	 * @param assignment
	 */
	public static void sendNotificationToConductMAYSI(String juvenileNum, String jpoId, Assignment assignment) throws RuntimeException, Exception 
	{
		StringBuffer buf = new StringBuffer("You have received a new referral ");
		buf.append(assignment.getReferralNumber());
		buf.append(" on ");
		buf.append(dateToString(assignment.getAssignmentAddDate()));
		buf.append(" for Juvenile Number ");
		buf.append(juvenileNum);
		buf.append(" which does not have an associated mental health assessment.");
		sendCaseFileExtractionNotification(jpoId, "MAYSI is Needed", buf.toString(), PDJuvenileCaseConstants.MJCW_JPO_CONDUCTMAYSI);
	}
	
	/**
	 * @param date
	 * @return
	 * Added as per #JIMS200048238
	 */
	public static String dateToString(Date date)
    {
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String dateStr = "";
		try {
			if (date != null) {
				dateStr = dfmt.format(date);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		}
		return dateStr;		
    }
	
	/**
	 * @param casefile
	 * @param probationOfficerId
	 * @throws Exception
	 */
	public static void createTask(JuvenileCasefile casefile, String probationOfficerId) throws RuntimeException, Exception {
		String casefileId = casefile.getOID().toString();
						
		HashMap map = new HashMap();
		map.put("submitAction", "Link");
		map.put("casefileID", casefileId);
		Task.createTask(probationOfficerId, PDTaskConstants.CREATE_TASK_ACTIVATE_CASEFILE, getTaskTitleForActivateCasefile(casefile.getJuvenileNum(),casefileId), map);
		map = null;
	}

	/*
	 * @param casefile
	 * @param referralNumber
	 */
	public static void sendJPOUpdateNotification(JuvenileCasefile casefile, String referralNumber, String oldJPOName) throws RuntimeException, Exception
	{
	    	// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
	    	JuvenileCore juvenile = casefile.getJuvenile();
		//
		String juvenileFirstName = juvenile.getFirstName();
		String juvenileLastName = juvenile.getLastName();
		
		StringBuffer subject = new StringBuffer("New casefile was not created for ");
		subject.append(juvenileFirstName);
		subject.append(" ");
		subject.append(juvenileLastName);
		subject.append(".");

		StringBuffer messageText = new StringBuffer("Casefile already exists for ");
		messageText.append(juvenileFirstName);
		messageText.append(" ");
		messageText.append(juvenileLastName);
		messageText.append(" (");
		messageText.append(juvenile.getJuvenileNum());
		messageText.append("), Supervision#:");
		messageText.append(casefile.getOID().toString());
		messageText.append(" with a status not equal to 'Closed' and Referral Number ");
		messageText.append(referralNumber);
		messageText.append(" assigned to JPO ");
		messageText.append(oldJPOName);
		messageText.append(".");
		messageText.append("<BR>");
		messageText.append("The existing casefile has been reassigned to JPO ");
		messageText.append(casefile.getOfficerFirstName());
		messageText.append(" ");
		messageText.append(casefile.getOfficerLastName());
		messageText.append("."); 
		
		OfficerProfile officer = casefile.getProbationOfficer();
		String officerJIMSLogonId = officer.getLogonId();
		sendCaseFileExtractionNotification(officerJIMSLogonId, subject.toString(), messageText.toString(), PDJuvenileCaseConstants.MJCW_JPO_UPDATECASEFILE);
		juvenile = null;
	}
	/*
	 * @param casefile
	 * @param referralNumber
	 */
	protected static void sendMissingConstellationNotification(String juvNum, OfficerProfile officer, Date assignmentDate) throws RuntimeException, Exception
	{
		//Juvenile juvenile = Juvenile.find(juvNum);
		JuvenileCore juvenile = JuvenileCore.findCore(juvNum);
		String juvenileFirstName = juvenile.getFirstName();
		String juvenileLastName = juvenile.getLastName();
		String juvenileMiddleName = juvenile.getMiddleName();
		
		StringBuffer subject = new StringBuffer("Guardian is Missing from Juvenile Profile");

		StringBuffer messageText = new StringBuffer("Guardian is needed for ");
		messageText.append(juvenileFirstName);
		messageText.append(" ");
		messageText.append(juvenileMiddleName);
		messageText.append(" ");
		messageText.append(juvenileLastName);
		messageText.append(", Juvenile #");
		messageText.append(juvenile.getJuvenileNum());
		messageText.append(".");
		messageText.append("You assigned this juvenile to ");
		messageText.append(officer.getFirstName());
		messageText.append(" ");
		messageText.append(officer.getLastName());
		messageText.append(" on ");
		messageText.append(dateToString(assignmentDate));
		messageText.append(" prior to creation of family record.");
		messageText.append("Please enter the family/guardian information in JJS and in JIMS2.");
		
		String officerJIMSLogonId = officer.getLogonId();
		sendCaseFileExtractionNotification(officerJIMSLogonId, subject.toString(), messageText.toString(), PDJuvenileCaseConstants.MJCW_JPO_MISSINGCONSTELLATION);
		juvenile = null;
	}
	
	/**
	 * @param casefile
	 * @param referralNumber
	 */
	public static void sendNewAssignmentUpdateJuvenileFor1718Notification(JuvenileCasefile casefile, String referralNumber) throws RuntimeException, Exception{
	    	// Profile stripping fix task 97536
	    	//Juvenile juvenile = casefile.getJuvenile();
	    	JuvenileCore juvenile = casefile.getJuvenile();
	    	//
		String juvenileFirstName = juvenile.getFirstName();
		String juvenileLastName = juvenile.getLastName();
		
		StringBuffer subject = new StringBuffer("Referral(s)");
		subject.append(" has/have been added to ");
		subject.append(juvenileFirstName);
		subject.append(" ");
		subject.append(juvenileLastName);
		subject.append("'s supervision casefile");

		StringBuffer messageText = new StringBuffer("The following referral(s) ");
		messageText.append(referralNumber);
		messageText.append(" have been added to ");
		messageText.append(juvenileFirstName);
		messageText.append(" ");
		messageText.append(juvenileLastName);
		messageText.append("'s supervision casefile.");
		messageText.append("<BR>");
		
		messageText.append("The reason is that the juvenile is between 17-18 years old and a casefile with status not equal to 'Closed' already exists ");
		messageText.append("<BR>");
		messageText.append("and a violation of probation offense occurred or other offense has occurred before the juvenile's 17th birthday.");

		OfficerProfile officer = casefile.getProbationOfficer();
		String officerJIMSLogonId = officer.getLogonId();
		sendCaseFileExtractionNotification(officerJIMSLogonId, subject.toString(), messageText.toString(), PDJuvenileCaseConstants.MJCW_JPO_UPDATECASEFILE);
		juvenile = null;
	}
	
	
	/**
	 * @param casefile
	 * @param referralNumber
	 */
	public static void sendNewAssignmentUpdateNotification(JuvenileCasefile casefile, String referralNumber) throws RuntimeException, Exception{
	    	// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
	    	JuvenileCore juvenile = casefile.getJuvenile();
		//
		String juvenileFirstName = juvenile.getFirstName();
		String juvenileLastName = juvenile.getLastName();
		StringBuffer subject = new StringBuffer("Referral");
		subject.append(" has been added to ");
		subject.append(juvenileFirstName);
		subject.append(" ");
		subject.append(juvenileLastName);
		subject.append("'s supervision casefile");

		StringBuffer messageText = new StringBuffer("The following referral ");
		messageText.append(referralNumber);
		messageText.append(" has been added to ");
		messageText.append(juvenileFirstName);
		messageText.append(" ");
		messageText.append(juvenileLastName);
		messageText.append("'s supervision casefile.");
		OfficerProfile officer = casefile.getProbationOfficer();
		String officerJIMSLogonId = officer.getLogonId();
	
		sendCaseFileExtractionNotification(officerJIMSLogonId, subject.toString(), messageText.toString(), PDJuvenileCaseConstants.MJCW_JPO_UPDATECASEFILE);
		juvenile = null;
	}
	
	
	/*
	 * @param casefile
	 * @param referralNumber
	 */
	public static void sendSubsequentReferralNotification(String juvenileName, String juvenileNum, String referralNumber, String referralDate, String officerJIMSLogonId) throws RuntimeException, Exception
	{
	
		StringBuffer subject = new StringBuffer("A Subsequent Referral has been created for ");
		subject.append(juvenileName);
		subject.append(".");

		StringBuffer messageText = new StringBuffer("Subsequent Referral for ");
		messageText.append(juvenileName);
		messageText.append(" (");
		messageText.append(juvenileNum);
		messageText.append("), Referral#:");
		messageText.append(referralNumber);
		messageText.append(", was created on ");
		messageText.append(referralDate);
		messageText.append(".");

		sendCaseFileExtractionNotification(officerJIMSLogonId, subject.toString(), messageText.toString(), PDJuvenileCaseConstants.MJCW_JPO_UPDATECASEFILE);
	}
	
	/**
	 * Method used to send the Notice for Generating casefile  
     * @param identityId
     * @param subject
     * @param errorMessage
     * @param topic
	 */	
	private static void sendCaseFileExtractionNotification(String identityId, String subject, String errorMessage, String topic) throws RuntimeException, Exception{
		
		JuvenileCasefileNotificationEvent nevt = new JuvenileCasefileNotificationEvent();
		nevt.setSubject(subject);
		nevt.setIdentity(identityId);
		nevt.setNotificationMessage(errorMessage);
		sendNotification(nevt,topic);
		nevt = null;
	}	
		
	/*
	 * @param nevt
	 * @param topic
	 */
	private static void sendNotification(JuvenileCasefileNotificationEvent nevt, String topic) throws RuntimeException, Exception{			
		CreateNotificationEvent notificationEvent = 
			(CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic(topic);			
		notificationEvent.setSubject(nevt.getSubject());
		notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, (IAddressable)nevt);
		notificationEvent.addContentBean(nevt);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}

	/**
	 * 
	 * @param className
	 * @param notificationType
	 * @return taskName
	 */
	protected static String getTaskName(String className, int notificationType)
	{
		String taskName = className + "-" + System.currentTimeMillis() + "-" + notificationType;
		return taskName;
	}

	/*
	 * @param juvenileNumber
	 * @param casefileId
	 */
	private static String getTaskTitleForActivateCasefile(String juvenileNumber, String casefileId){
		StringBuffer title = new StringBuffer("Activate Casefile Assignment for Juvenile Number ");
		title.append(juvenileNumber);
		title.append(" and Supervision Number: ");
		title.append(casefileId);
		return title.toString();		
	}
}
