/*
 * Created on Aug 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.administerserviceprovider.SendJuvenileServiceProviderDocumentAttendanceNotificationEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.casefile.UpdateJuvenileCasefileEvent;
import messaging.casefile.reply.CasefileForActivationResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.mentalhealth.reply.MAYSIAssessResponseEvent;
import messaging.notification.SendCasefileClosingNotificationEvent;
import messaging.notification.SendCaseplanNotificationEventEvent;
import messaging.notification.SendMentalHealthNotificationEvent;
import messaging.notification.SendOverdueAssessmentNotificationEvent;
import messaging.notification.SendOverdueInterviewNotificationEvent;
import messaging.notification.SendPendingAppointmentNotificationEvent;
import messaging.notification.SendProgramReferralNotificationEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.scheduling.RegisterTaskEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.PersistentEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.CalendarConstants;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.time.FastDateFormat;

import pd.contact.officer.PDOfficerProfileHelper;
import pd.supervision.administerserviceprovider.SP_Profile;
import ui.common.Name;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.casefile.form.CasefileActivationForm;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class JuvenileCaseworkAlertsHelper {
	
	private static FastDateFormat FULL_DATE_FORMAT= FastDateFormat.getInstance("MM/dd/yyyy h:mm a");
	private static FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("MM/dd/yyyy");
	private static FastDateFormat TIME_FORMAT = FastDateFormat.getInstance("h:mm a");
	
	private static String MJCW_JPO_PRECOURT_INTERVIEWNOTIFICATION = "MJCW.JPO.PRECOURT.INTERVIEWNOTIFICATION";
	private static String MJCW_JPO_SUPERVISION_DUE30_NOTIFICATION = "MJCW.JPO.SUPERVISION.DUE30.NOTIFICATION";
	private static String MJCW_JPO_SUPERVISION_DUE60_NOTIFICATION = "MJCW.JPO.SUPERVISION.DUE60.NOTIFICATION";
	private static String MJCW_JPO_SUPERVISION_OVERDUE_NOTIFICATION = "MJCW.JPO.SUPERVISION.OVERDUE.NOTIFICATION";
	private static String MJCW_CLM_SUPERVISION_OVERDUE_NOTIFICATION = "MJCW.CLM.SUPERVISION.OVERDUE.NOTIFICATION";
	
	private static String MJCW_JPO_PENDING_CASEPLAN_NOTIFICATION = "MJCW.JPO.PENDING.CASEPLAN.NOTIFICATION";
	private static String MJCW_MAYSIREQUESTOR_ACTION_ALERT = "MJCW.MAYSIREQUESTOR.ACTION.ALERT";
	private static String MJCW_JPO_POSTCOURT_INTERVIEWNOTIFICATION = "MJCW.JPO.POSTCOURT.INTERVIEWNOTIFICATION";
	private static String MJCW_JPO_MAYSI14DAYS_OVERDUE_NOTIFICATION = "MJCW.JPO.MAYSI14DAYS.OVERDUE.NOTIFICATION";
	private static String MJCW_JPO_MAYSI29DAYS_OVERDUE_NOTIFICATION = "MJCW.JPO.MAYSI29DAYS.OVERDUE.NOTIFICATION";
	private static String MJCW_JPO_RISK14DAYS_OVERDUE_NOTIFICATION = "MJCW.JPO.RISK14DAYS.OVERDUE.NOTIFICATION";
	private static String MJCW_JPO_RISK29DAYS_OVERDUE_NOTIFICATION = "MJCW.JPO.RISK29DAYS.OVERDUE.NOTIFICATION";
	private static String MJCW_JPO_TITLEIVEASSESS_OVERDUE_NOTIFICATION = "MJCW.JPO.TITLEIVEASSESS.OVERDUE.NOTIFICATION";
	private static String MJCW_JPO_OVERDUE20DAYS_CASEPLAN_NOTIFICATION = "MJCW.JPO.OVERDUE20DAYS.CASEPLAN.NOTIFICATION";
		
	private static String NOTICE = "Notice";
	private static String JPO = "jpo";
	private static String ONE_WEEK_PRIOR_NOTIF_SUBJECT = "Appointments are scheduled to occur in one week."; 
	private static String TWO_WEEK_PRIOR_NOTIF_SUBJECT = "Appointments scheduled to occur in two weeks."; 
	private static String TODAYS_APPOINTMENT = "Today's appointments."; 
	private static String SUPEVERSION_EXPIRE_30 = "Supervision due to expire in 30 days.";
	private static String SUPEVERSION_EXPIRE_60 = "Supervision due to expire in 60 days.";
	private static String CASEFILE_CLOSING = "Casefile closing is overdue.";
	private static String CASEFILE_PENDING = "Caseplan is still pending.";
	private static String PARTIES_SIGN_CASEPLAN = "Parties need to sign caseplan.";
	private static String MAYSIREQTASK = "MaysiReqTask";
	private static String POST_COURT_INTERVIEW = "Post Court Interview has not been attended.";
	private static String MAYSI_14_DAYS = "MAYSI is 14 days overdue.";
	private static String MAYSI_29_DAYS = "MAYSI is 29 days overdue.";
	private static String RISK_ANALYSIS_14 = "Risk Analysis is 14 days overdue.";
	private static String RISK_ANALYSIS_29  = "Risk Analysis is 29 days overdue.";
	private static String TITLE_IV  = "Title IVe assessment is overdue.";
	
	public JuvenileCaseworkAlertsHelper() {
	}
	
	public void sendMAYSIRequestorActionAlert(MAYSIAssessResponseEvent resp, String juvenileNum, String juvName, String maysiId){
		
		Date date = new Date();
		StringBuffer sb = new StringBuffer();
		String subject = "MAYSI not administered on " + DateUtil.dateToString(resp.getAssessmentDate(), DateUtil.DATE_FMT_1) + ", information needed for Juvenile # " + juvenileNum;
		
		SendMentalHealthNotificationEvent notificationEvent = (SendMentalHealthNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDMENTALHEALTHNOTIFICATION);
    	
    	notificationEvent.setSubject(subject);
    	notificationEvent.setMaysiId(maysiId);
    	notificationEvent.setJuvenileNum(juvenileNum);
    	//Added
    	notificationEvent.setLocationUnitId(resp.getLocationUnitId());
    	notificationEvent.setFacilityTypeId(resp.getFacilityTypeId());
    	notificationEvent.setLengthOfStayId(resp.getLengthOfStayId());
    	
		sb.append("A MAYSI request was recorded on " + DateUtil.dateToString(resp.getAssessmentDate(), DateUtil.DATE_FMT_1) + " for ");
		sb.append(juvName + ", Juvenile#(" + juvenileNum + "). 24 hours have expired and there are no associated test results.");
		sb.append(" Please indicate the reason the MAYSI was not administered.");
		sb.append("\n");
		
		notificationEvent.setNotificationMessage(sb.toString());
		notificationEvent.setAlertType(MAYSIREQTASK);
		notificationEvent.setTaskTopic(MJCW_MAYSIREQUESTOR_ACTION_ALERT);
		
        String taskName = notificationEvent.getClass().getName() + "-" + juvenileNum;	

		notificationEvent.setIdentity(resp.getRequestingOfficerId());        
        this.scheduleNotification(notificationEvent, taskName, date, 24);
        
        sb = null;
        notificationEvent = null;
        taskName = null;
        date = null;
        
	}
	
	/**
	 * @param event
	 * @param taskName
	 * @param date
	 * @param hours
	 */
	public void scheduleNotification(PersistentEvent event, String taskName, Date date, int hours){
        //create RegisterTaskEvent and post it
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

        MessageUtil.postRequest(rtEvent);        
	}
	
	public void sendSPProgramReferralActionAlert(ProgramReferralResponseEvent resp, JuvenileCasefileForm juvenileCasefileForm) {
		
		StringBuffer sb = new StringBuffer();
		String subject = "Program Referral Action Alert for Juvenile " + resp.getJuvenileId() + " , Casefile " + resp.getCasefileId();
		Date date = new Date();
		
    	SendProgramReferralNotificationEvent notificationEvent = (SendProgramReferralNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDPROGRAMREFERRALNOTIFICATION);
    	
    	notificationEvent.setSubject(subject);
    	
    	Name name = new Name(resp.getJuvenileFirstName(),resp.getJuvenileMiddleName(),resp.getJuvenileLastName());
    	if(name.getFormattedName().equals("")){
    		name = juvenileCasefileForm.getJuvenileName();
    	}
		
		sb.append("A request to " + resp.getProviderProgramName() + " program has been received on ");
		sb.append(dateToString(resp.getLastActionDate()) + " for ");
		sb.append(name.getFormattedName() + " and no action has been taken.");
		sb.append("\n");
		
		notificationEvent.setNotificationMessage(sb.toString());
		notificationEvent.setJuvenileNum(juvenileCasefileForm.getJuvenileNum());
		notificationEvent.setSupervisionNumber(juvenileCasefileForm.getSupervisionNum());
		
        String taskName = notificationEvent.getClass().getName() + "-" + resp.getCasefileId();	

		String adminContactId = UIProgramReferralHelper.getSPAdminContactId(resp.getJuvServiceProviderId());
		notificationEvent.setIdentity(adminContactId);        
        this.scheduleNotification(notificationEvent, taskName, date, 72);
        String contactUserId = UIProgramReferralHelper.getSPContactUserProfId(resp.getJuvServiceProviderId());
		
		if(!adminContactId.equalsIgnoreCase(contactUserId)) {
			notificationEvent.setIdentity(contactUserId);        
			this.scheduleNotification(notificationEvent, taskName, date, 72);
		}
		//To JPO
		notificationEvent.setIdentity(juvenileCasefileForm.getProbationOfficerLogonId());
        this.scheduleNotification(notificationEvent, taskName, date, 72);
        
		//to notify contacts in CSJVSRVPRVPROF added by Sruti ER JIMS200075727
        ArrayList contactUserList =  (ArrayList) UIServiceProviderHelper.getContactsFromSecurityManager(adminContactId); //88615
        Iterator contactUsersItr = contactUserList.iterator();
		while(contactUsersItr.hasNext()){
			ServiceProviderContactResponseEvent contactResp = (ServiceProviderContactResponseEvent) contactUsersItr.next();
			String contactUser = contactResp.getEmail();//86318
			if(contactUser != null){
				notificationEvent.setIdentity(contactUser);
				notificationEvent.setSubject(subject);
				this.scheduleNotification(notificationEvent, taskName, date, 72);
				
			}
		}        
        sb = null;
        notificationEvent = null;
        taskName = null;
        date = null;
        
	}
	
	// following methods added by Vijay
	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending notice to Assigned JPO for supervisionType = Intensive, Community Supervision & Deferred Adjudication
	 */
	public void sendPOPostCourtInterviewNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueInterviewNotificationEvent notificationEvent = (SendOverdueInterviewNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEINTERVIEWNOTIFICATION);
		
    	notificationEvent.setSubject(POST_COURT_INTERVIEW);
    	
		sb.append("Please be advised that "+casefileForm.getJuvenileName() +" " + casefileForm.getJuvenileNum()+ " who was assigned to you on ");
		sb.append(dateToString(casefileForm.getAssignmentDate()) +", has not attended their post-court interview");
		sb.append("\n");
		
		notificationEvent.setNotificationMessage(sb.toString());
		notificationEvent.setNoticeTopic(MJCW_JPO_POSTCOURT_INTERVIEWNOTIFICATION);
		
        String taskName = notificationEvent.getClass().getName() + "-" + resp.getSupervisionNum();
        notificationEvent.setSupervisionNumber(casefileForm.getSupervisionNum());
		//To JPO
        notificationEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        notificationEvent.setIdentityType(JPO);
        notificationEvent.setAlertType(NOTICE);
        
        this.scheduleNotification(notificationEvent, taskName, resp.getActivationDate(), 168);	
        
        sb = null;
        notificationEvent = null;
        taskName = null;
	}
	
	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending MAYSI 14 daysover due notice to Assigned JPO for all kinds of supervisionTypes
	 */
	public void sendPOMaysi14daysNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueAssessmentNotificationEvent assessNotifEvent = (SendOverdueAssessmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEASSESSMENTNOTIFICATION);
    	
    	assessNotifEvent.setSubject(MAYSI_14_DAYS);
    	
    	sb.append(casefileForm.getJuvenileName() +" Juvenile # " + casefileForm.getJuvenileNum()+ " and Supervision# " +casefileForm.getSupervisionNum());
		sb.append(" has not completed a mental health assessment since "+ dateToString(resp.getActivationDate()));
		sb.append("\n");
		assessNotifEvent.setNotificationMessage(sb.toString());

        String taskName = assessNotifEvent.getClass().getName() + "-" + resp.getSupervisionNum();	

        assessNotifEvent.setNoticeTopic(MJCW_JPO_MAYSI14DAYS_OVERDUE_NOTIFICATION);
        assessNotifEvent.setSupervisionNumber(resp.getSupervisionNum());
        assessNotifEvent.setIdentityType(JPO);
		//To JPO
        assessNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        //EventManager.getSharedInstance(EventManager.REQUEST).postEvent(assessNotifEvent);
        this.scheduleNotification(assessNotifEvent, taskName, resp.getActivationDate(), 336);	
        
        sb = null;
        assessNotifEvent = null;
        taskName = null;
		
	}

	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending MAYSI 29 days overdue notice to Assigned JPO for all kinds of supervisionTypes
	 */
	public void sendPOMaysi29daysNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueAssessmentNotificationEvent assessNotifEvent = (SendOverdueAssessmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEASSESSMENTNOTIFICATION);
		
    	assessNotifEvent.setSubject(MAYSI_29_DAYS);
    	
		sb.append(casefileForm.getJuvenileName() +" Juvenile # " + casefileForm.getJuvenileNum()+ " and Supervision# " +casefileForm.getSupervisionNum());
		sb.append(" has not completed a mental health assessment since "+dateToString(resp.getActivationDate()));
		sb.append("\n");
		assessNotifEvent.setNotificationMessage(sb.toString());
		
        String taskName = assessNotifEvent.getClass().getName() + "-" + resp.getSupervisionNum();
        assessNotifEvent.setNoticeTopic(MJCW_JPO_MAYSI29DAYS_OVERDUE_NOTIFICATION);
        assessNotifEvent.setSupervisionNumber(resp.getSupervisionNum());
        assessNotifEvent.setIdentityType(JPO);
		//To JPO
        assessNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        this.scheduleNotification(assessNotifEvent, taskName, resp.getActivationDate(), 696);	
        
        sb = null;
        assessNotifEvent = null;
        taskName = null;
        
	}

	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending RiskAnalysis 14 days overdue notice to Assigned JPO for all kinds of supervisionTypes
	 */
	public void sendPORiskAnalysis14daysNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueAssessmentNotificationEvent assessNotifEvent = (SendOverdueAssessmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEASSESSMENTNOTIFICATION);
 
    	assessNotifEvent.setSubject(RISK_ANALYSIS_14);
    	
		sb.append(casefileForm.getJuvenileName() +" Juvenile # " + casefileForm.getJuvenileNum()+ " and Supervision # " +casefileForm.getSupervisionNum());
		sb.append(" has not completed a risk needs assessment since "+dateToString(resp.getActivationDate()));
		sb.append("\n");
		assessNotifEvent.setNotificationMessage(sb.toString());
		
        String taskName = assessNotifEvent.getClass().getName() + "-" + resp.getSupervisionNum();
        assessNotifEvent.setNoticeTopic(MJCW_JPO_RISK14DAYS_OVERDUE_NOTIFICATION);
        assessNotifEvent.setSupervisionNumber(resp.getSupervisionNum());
        assessNotifEvent.setIdentityType(JPO);
		//To JPO
        assessNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());   
        this.scheduleNotification(assessNotifEvent, taskName, resp.getActivationDate(), 336);	
		
	}

	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending RiskAnalysis 29 days overdue notice to Assigned JPO for all kinds of supervisionTypes
	 */
	public void sendPORiskAnalysis29daysNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueAssessmentNotificationEvent assessNotifEvent = (SendOverdueAssessmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEASSESSMENTNOTIFICATION);
    	
    	assessNotifEvent.setSubject(RISK_ANALYSIS_29);
    	
		sb.append(casefileForm.getJuvenileName() +" Juvenile # " + casefileForm.getJuvenileNum()+ " and Supervision # " +casefileForm.getSupervisionNum());
		sb.append(" has not completed a risk needs assessment since "+dateToString(casefileForm.getAssignmentDate()));
		sb.append("\n");
		assessNotifEvent.setNotificationMessage(sb.toString());
		
        String taskName = assessNotifEvent.getClass().getName() + "-" + resp.getSupervisionNum();
        assessNotifEvent.setNoticeTopic(MJCW_JPO_RISK29DAYS_OVERDUE_NOTIFICATION);
        assessNotifEvent.setSupervisionNumber(resp.getSupervisionNum());
        assessNotifEvent.setIdentityType(JPO);
		//To JPO
        assessNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());        
        this.scheduleNotification(assessNotifEvent, taskName, resp.getActivationDate(), 696);
        
        sb = null;
        assessNotifEvent = null;
        taskName = null;
	}

	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending Title IVe Assessment overdue notice to Assigned JPO for all kinds of supervisionTypes
	 */
	public void sendPOTitleIVAssesmentOverdueNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueAssessmentNotificationEvent assessNotifEvent = (SendOverdueAssessmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEASSESSMENTNOTIFICATION);
    	
    	assessNotifEvent.setSubject(TITLE_IV);
    	
		sb.append(casefileForm.getJuvenileName() +" Juvenile # " + casefileForm.getJuvenileNum()+ " and Supervision# " +casefileForm.getSupervisionNum());
		sb.append(" has not completed Title IVe assessment since "+dateToString(resp.getActivationDate()));
		sb.append("\n");
		assessNotifEvent.setNotificationMessage(sb.toString());
		
        String taskName = assessNotifEvent.getClass().getName() + "-" + resp.getSupervisionNum();
        assessNotifEvent.setNoticeTopic(MJCW_JPO_TITLEIVEASSESS_OVERDUE_NOTIFICATION);
        assessNotifEvent.setSupervisionNumber(resp.getSupervisionNum());
        assessNotifEvent.setIdentityType(JPO);
		//To JPO
        assessNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());        
        this.scheduleNotification(assessNotifEvent, taskName, resp.getActivationDate(), 24);
        
        sb = null;
        assessNotifEvent = null;
        taskName = null;
        
	}
	
	// Pending Appointment notifications
	
	/**
	 * @param respEvent
	 * @param casefileForm
	 * Sending Pending Appointment 2Weeksprior Notice to Assigned JPO for Interview Event
	 */
	public void sendPOPendingAppointment2WeekspriorNotification(ScheduleNewEventForm eventForm, String serviceEventTypeCode, CalendarServiceEventResponseEvent respEvent, String jpoId) {
		
		Date eventDate = respEvent.getStartDatetime();
		StringBuffer sb = new StringBuffer();
		
		SendPendingAppointmentNotificationEvent pendingNotifEvent = (SendPendingAppointmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDPENDINGAPPOINTMENTNOTIFICATION);
		
    	pendingNotifEvent.setSubject(TWO_WEEK_PRIOR_NOTIF_SUBJECT);
    	
		sb.append(eventForm.getJuvenileName() +" Juvenile # " + eventForm.getJuvenileNum()+ ", is scheduled for "+eventForm.getCurrentService().getServiceType());
		sb.append(" on "+DATE_FORMAT.format(eventDate)+ " at "+TIME_FORMAT.format(eventDate));
		sb.append("\n");
		
		pendingNotifEvent.setNotificationMessage(sb.toString());
		
        String taskName = pendingNotifEvent.getClass().getName() + "-" + eventForm.getCaseFileId();	
        pendingNotifEvent.setSupervisionNumber(eventForm.getCaseFileId());
		//To JPO
        pendingNotifEvent.setIdentity(jpoId); 
        
        this.scheduleNotification(pendingNotifEvent, taskName, eventDate, -336);	
        
        sb = null;
        pendingNotifEvent = null;
        taskName = null;
        eventDate = null;
		
	}
	
	/**
	 * @param form
	 * @param serviceEventTypeCode
	 * @param respEvent
	 * @param jpoId
	 * Sending Pending Appointment 1Week prior Notice to Assigned JPO for Interview Event
	 */
	public void sendPOPendingAppointment1WeekpriorNotification(ScheduleNewEventForm eventForm, String serviceEventTypeCode, CalendarServiceEventResponseEvent respEvent, String jpoId) {
		
		Date eventDate = respEvent.getStartDatetime();
		StringBuffer sb = new StringBuffer();
		
		SendPendingAppointmentNotificationEvent pendingNotifEvent = (SendPendingAppointmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDPENDINGAPPOINTMENTNOTIFICATION);
		
    	pendingNotifEvent.setSubject(ONE_WEEK_PRIOR_NOTIF_SUBJECT);
    	
		sb.append(eventForm.getJuvenileName() +", Supervision # " + eventForm.getCaseFileId()+ " is scheduled for  "+ eventForm.getCurrentService().getServiceType());
		sb.append(" on "+DATE_FORMAT.format(eventDate)+ " at "+TIME_FORMAT.format(eventDate));
		sb.append("\n");
		
		pendingNotifEvent.setNotificationMessage(sb.toString());

        String taskName = pendingNotifEvent.getClass().getName() + "-" + eventForm.getCaseFileId();
        pendingNotifEvent.setSupervisionNumber(eventForm.getCaseFileId());
		//To JPO
        pendingNotifEvent.setIdentity(jpoId); 
        
        this.scheduleNotification(pendingNotifEvent, taskName, eventDate, -168);
        
        sb = null;
        pendingNotifEvent = null;
        taskName = null;
        eventDate = null;

	}

	/**
	 * @param form
	 * @param serviceEventTypeCode
	 * @param respEvent
	 * @param jpoId
	 * Sending Pending Appointment Notice on the same Day to Assigned JPO for Interview Event
	 */
	public void sendPOPendingAppointmentDayBeforepriorNotification(ScheduleNewEventForm eventForm, String serviceEventTypeCode, CalendarServiceEventResponseEvent respEvent, String jpoId) {
		
		Date eventDate = respEvent.getStartDatetime();
		StringBuffer sb = new StringBuffer();
		
		SendPendingAppointmentNotificationEvent pendingNotifEvent = (SendPendingAppointmentNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDPENDINGAPPOINTMENTNOTIFICATION);
  
    	pendingNotifEvent.setSubject(TODAYS_APPOINTMENT);
    	
		sb.append( eventForm.getJuvenileNum()+" "+ eventForm.getJuvenileName()+" has an appointment today at  "+TIME_FORMAT.format(eventDate));
		sb.append(" for "+ eventForm.getCurrentService().getServiceType());
		sb.append("\n");
		
		pendingNotifEvent.setNotificationMessage(sb.toString());
		
        String taskName = SendPendingAppointmentNotificationEvent.class.getName() + "-" + eventForm.getCaseFileId();	
        pendingNotifEvent.setSupervisionNumber(eventForm.getCaseFileId());
		//To JPO
        pendingNotifEvent.setIdentity(jpoId);     
        
        this.scheduleNotification(pendingNotifEvent, taskName, eventDate, 0);	
        
        eventDate = null;
        sb = null;
        pendingNotifEvent = null;
        taskName = null;
	}
	
	//End PendingAppointment Notices.
	
	// Begin CasefileClosing Notifications
	/**
	 * @param updateEvent
	 * @param form
	 */
	public void sendPOSupervisionDueExpirePrior30daysNotification(UpdateJuvenileCasefileEvent updateEvent, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		Date eventDate = updateEvent.getSupervisionEndDate();
		
		SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
		
		casefileNotifEvent.setSupervisionExpectedEndDate(updateEvent.getSupervisionEndDate());
    	casefileNotifEvent.setSubject(SUPEVERSION_EXPIRE_30);
    	
		sb.append(casefileForm.getJuvenileName() +" Supervision # " + updateEvent.getSupervisionNumber());
		sb.append(" is scheduled for closing in 30 days on "+FULL_DATE_FORMAT.format(eventDate));
		sb.append("\n");
		
		casefileNotifEvent.setNotificationMessage(sb.toString());
		
		String taskName = SendCasefileClosingNotificationEvent.class.getName() + "-" + updateEvent.getSupervisionNumber();	
		casefileNotifEvent.setSupervisionNumber(updateEvent.getSupervisionNumber());
		//To JPO
		casefileNotifEvent.setIdentityType(JPO);
        casefileNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        casefileNotifEvent.setNoticeTopic(MJCW_JPO_SUPERVISION_DUE30_NOTIFICATION);// Added as the command is used for both DB2 and M204 notices.
        
        this.scheduleNotification(casefileNotifEvent, taskName, eventDate, -720);
        
        eventDate = null;
        casefileNotifEvent = null;
        sb = null;
        taskName = null;
        
	}
	/**
	 * #U.S 24194 30 day notice = Post Adjudication Residential
	 * sendPostAdjudicationResidential30daysEmailNotification
	 * @param updateEvent
	 * @param casefileForm
	 */
	public void sendPostAdjudicationResidential30daysEmailNotification(UpdateJuvenileCasefileEvent updateEvent, JuvenileCasefileForm casefileForm) {
		Collection<UserGroupResponseEvent> userGrpResEvent = SecurityUIHelper.getUserGroupByName("TJJD MEDICAID TRACKER");
		Collection<OfficerProfileResponseEvent>  securityRespEvent =null;
		if(userGrpResEvent!=null){
			 Iterator<UserGroupResponseEvent> userGrpResIter = userGrpResEvent.iterator();
			 while(userGrpResIter.hasNext())
			 {
				 UserGroupResponseEvent response = userGrpResIter.next();
				 securityRespEvent = PDOfficerProfileHelper.getOfficerProfilesInUserGroup(response.getUserGroupId());
			 }
		}
		if(securityRespEvent!=null)
		{
			Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
		
			while(securityRespIter.hasNext())
		    {
				SendEmailEvent sendEmailEvent = new SendEmailEvent();
				StringBuffer message = new StringBuffer(100);
				OfficerProfileResponseEvent	securityResEvent =	 securityRespIter.next();
		    	
		    	//send email to the group
				String officerEmailId = "";
		    	List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("logonId", UIUtil.getCurrentUserID());
				Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
				if(events.hasNext()){
					OfficerProfileResponseEvent resp = events.next();
						officerEmailId=resp.getEmail();
				}
		    	if((officerEmailId!=null && !officerEmailId.equals(""))&& (securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals("")))
		    	{
			    	sendEmailEvent.setFromAddress(officerEmailId);
			    	UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, securityResEvent.getEmail());
			    	
			    	message.append("Juvenile# ");
			    	if(casefileForm.getJuvenileFullName()!=null)
			    		message.append(casefileForm.getJuvenileFullName());
			    	else
			    		message.append("");
			    		
			    	message.append(" ");
			    	if(casefileForm.getJuvenileNum()!=null)
			    		message.append(casefileForm.getJuvenileNum());
			    	else
			    		message.append("");
			    	message.append(" is pending release from ");
			    	if(casefileForm.getDetentionFacility()!=null)
			    		message.append(casefileForm.getDetentionFacility());
			    	else
			    		message.append("");
			    	message.append(". Please Complete a TJJD Medicaid Tracker.");
			    	sendEmailEvent.setMessage(message.toString()); //set mesg.
		        
				    StringBuffer subMesg = new StringBuffer(100);	    	
				    subMesg.append("Youth Pending Release , TJJD Medicaid Tracker Requires Completion.");
			    	sendEmailEvent.setSubject(subMesg.toString()); //set Subj.
		
			    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			    	dispatch.postEvent(sendEmailEvent);
		    	}
		    }
	    }
	}

	/**
	 * @param updateEvent
	 * @param form
	 */
	public void sendPOSupervisionDueExpirePrior60daysNotification(UpdateJuvenileCasefileEvent updateEvent, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		Date eventDate = updateEvent.getSupervisionEndDate();
		
		SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
		
    	casefileNotifEvent.setSubject(SUPEVERSION_EXPIRE_60);
    	casefileNotifEvent.setSupervisionExpectedEndDate(updateEvent.getSupervisionEndDate());
    	
		sb.append(casefileForm.getJuvenileName() +" Supervision # " + updateEvent.getSupervisionNumber());
		sb.append(" is scheduled for closing in 60 days on "+ FULL_DATE_FORMAT.format(eventDate));
		sb.append("\n");
		casefileNotifEvent.setNotificationMessage(sb.toString());
		
		String taskName = SendCasefileClosingNotificationEvent.class.getName() + "-" + updateEvent.getSupervisionNumber();
		casefileNotifEvent.setSupervisionNumber(updateEvent.getSupervisionNumber());
		//To JPO
		casefileNotifEvent.setIdentityType(JPO);
        casefileNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        casefileNotifEvent.setNoticeTopic(MJCW_JPO_SUPERVISION_DUE60_NOTIFICATION);// Added as the command is used for both DB2 and M204 notices.
        
        this.scheduleNotification(casefileNotifEvent, taskName, eventDate, -1440);
        
        casefileNotifEvent = null;
        sb = null;
        taskName = null;
        
	}
	
	
	/**
	 * @param updateEvent
	 * @param casefileForm
	 * To JPO
	 */
	public void sendPOSupervisionDueExpireAfter24hoursNotification(UpdateJuvenileCasefileEvent updateEvent, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
		
    	casefileNotifEvent.setSubject(CASEFILE_CLOSING);
    	casefileNotifEvent.setSupervisionExpectedEndDate(updateEvent.getSupervisionEndDate());
    	
		sb.append(casefileForm.getJuvenileName() +" Supervision # " + updateEvent.getSupervisionNumber());
		sb.append(" for  "+casefileForm.getSupervisionType()+", ");
		sb.append(" was scheduled for closing yesterday and the case remains open. The assigned probation officer is "+casefileForm.getProbationOfficerFullName());
		sb.append("\n");
		
		casefileNotifEvent.setNotificationMessage(sb.toString());
		
		String taskName = SendCasefileClosingNotificationEvent.class.getName() + "-" + updateEvent.getSupervisionNumber();
		casefileNotifEvent.setSupervisionNumber(updateEvent.getSupervisionNumber());
		//To JPO
		casefileNotifEvent.setIdentityType(JPO);
        casefileNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        casefileNotifEvent.setNoticeTopic(MJCW_JPO_SUPERVISION_OVERDUE_NOTIFICATION);// Added as the command is used for both DB2 and M204 notices.
        this.scheduleNotification(casefileNotifEvent, taskName, updateEvent.getSupervisionEndDate(), 24);
        
        casefileNotifEvent = null;
        sb = null;
        taskName = null;
        
	}
	
	/**
	 * @param updateEvent
	 * @param casefileForm
	 * To CLM
	 */
	public void sendCLMSupervisionDueExpireAfter24hoursNotification(UpdateJuvenileCasefileEvent updateEvent, JuvenileCasefileForm casefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
		
    	casefileNotifEvent.setSubject(CASEFILE_CLOSING);
    	casefileNotifEvent.setSupervisionExpectedEndDate(updateEvent.getSupervisionEndDate());
    	
		sb.append(casefileForm.getJuvenileName() +" Supervision # " + updateEvent.getSupervisionNumber());
		sb.append(" for  "+casefileForm.getSupervisionType()+", ");
		sb.append(" was scheduled for closing yesterday and the case remains open. The assigned probation officer is "+casefileForm.getProbationOfficerFullName());
		sb.append("\n");
		
		casefileNotifEvent.setNotificationMessage(sb.toString());
        String taskName = SendCasefileClosingNotificationEvent.class.getName() + "-" + updateEvent.getSupervisionNumber();
        casefileNotifEvent.setSupervisionNumber(updateEvent.getSupervisionNumber());
		
        //To CLM
        casefileNotifEvent.setIdentityType("clm");
        casefileNotifEvent.setIdentity(casefileForm.getCaseloadManagerId());
        casefileNotifEvent.setNoticeTopic(MJCW_CLM_SUPERVISION_OVERDUE_NOTIFICATION);// Added as the command is used for both DB2 and M204 notices.
        
        this.scheduleNotification(casefileNotifEvent, taskName, updateEvent.getSupervisionEndDate(), 24);
        
        casefileNotifEvent = null;
        sb = null;
        taskName = null;
	}
	
	//	End CasefileClosing Notifications
	
	//Caseplan Pending Notices
	
	/**
	 * @param respEvent
	 * @param casefileForm
	 */
	public void sendPOCaseplanPendingNotification(CasefileForActivationResponseEvent resp, JuvenileCasefileForm casefileForm) {

		StringBuffer sb = new StringBuffer();
		
		SendCaseplanNotificationEventEvent caseplanNotifEvent = (SendCaseplanNotificationEventEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEPLANNOTIFICATIONEVENT);

    	caseplanNotifEvent.setSubject(CASEFILE_PENDING);
    	
		sb.append("The caseplan for the following juveniles is still pending and 20 days have elapsed since casefile activation.\n");
		sb.append("Juvenile # "+casefileForm.getJuvenileNum()+" "+casefileForm.getJuvenileFullName()+" Supervision # "+casefileForm.getSupervisionNum());
		sb.append("\n");
		
		caseplanNotifEvent.setNotificationMessage(sb.toString());
		caseplanNotifEvent.setNoticeTopic(MJCW_JPO_OVERDUE20DAYS_CASEPLAN_NOTIFICATION);
		Date activationDate = resp.getActivationDate();
        String taskName = SendCaseplanNotificationEventEvent.class.getName() + "-" + resp.getSupervisionNum();	
        
		//To JPO
        caseplanNotifEvent.setIdentityType(JPO);
        caseplanNotifEvent.setCasefileId(resp.getSupervisionNum());
        caseplanNotifEvent.setIdentity(casefileForm.getProbationOfficerLogonId());
        caseplanNotifEvent.setAlertType(NOTICE);
        
        this.scheduleNotification(caseplanNotifEvent, taskName, activationDate, 480);
        
        caseplanNotifEvent = null;
        sb = null;
        taskName = null;
	}
	
	/**
	 * @param respEvent
	 * @param casefileForm
	 * caseplan not signed notice
	 */
	public void sendPOCaseplanNotSignedNotification(CaseplanForm casePlanForm, String officerId, String juvName ) {
	
		StringBuffer sb = new StringBuffer();
		
		SendCaseplanNotificationEventEvent caseplanNotifEvent = (SendCaseplanNotificationEventEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEPLANNOTIFICATIONEVENT);
    	Date activationDate = new Date();
    	
    	caseplanNotifEvent.setSubject(PARTIES_SIGN_CASEPLAN);
    	
    	sb.append("The CLM accepted the caseplan for the following juvenile# "+ casePlanForm.getJuvenileNum()+" "+juvName);
		sb.append(" and casefile# "+casePlanForm.getCasefileId());
		sb.append( " on "+ FULL_DATE_FORMAT.format(activationDate));
		sb.append(". Please have the appropriate parties sign the caseplan.");
		sb.append("\n");
		
		caseplanNotifEvent.setNotificationMessage(sb.toString());
		caseplanNotifEvent.setNoticeTopic(MJCW_JPO_PENDING_CASEPLAN_NOTIFICATION);
		String taskName = SendCaseplanNotificationEventEvent.class.getName() + "-" + casePlanForm.getCasefileId();
		
		//To JPO
		caseplanNotifEvent.setIdentityType(JPO);
		caseplanNotifEvent.setCasefileId(casePlanForm.getCasefileId());
        caseplanNotifEvent.setIdentity(officerId);
        caseplanNotifEvent.setAlertType(NOTICE);
        caseplanNotifEvent.setJuvenileNum(casePlanForm.getJuvenileNum());
        
        this.scheduleNotification(caseplanNotifEvent, taskName, activationDate, 72);
        
        caseplanNotifEvent = null;
        sb = null;
        taskName = null;
        
	}
	
	public void clmAcceptNotification(CaseplanForm casePlanForm, String officerId, String juvName ) {
		
		StringBuffer sb = new StringBuffer();
		
		SendCaseplanNotificationEventEvent caseplanNotifEvent = (SendCaseplanNotificationEventEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEPLANNOTIFICATIONEVENT);
    	String subject = ("CLM Accepted Caseplan for Supervision#: "+ casePlanForm.getCasefileId());
    	
    	caseplanNotifEvent.setSubject(subject);
    	
    	sb.append("The caseplan for"+" "+juvName+","+" "+ "Supervision#"+ casePlanForm.getCasefileId()+" ");
    	sb.append("has been reviewed and accepted");
		sb.append("\n");
		
		caseplanNotifEvent.setNotificationMessage(sb.toString());
		caseplanNotifEvent.setNoticeTopic(MJCW_JPO_PENDING_CASEPLAN_NOTIFICATION);
		//String taskName = caseplanNotifEvent.getClass().getName() + "-" + casePlanForm.getCasefileId();
		//To JPO
		caseplanNotifEvent.setIdentityType(JPO);
		caseplanNotifEvent.setCasefileId(casePlanForm.getCasefileId());
        caseplanNotifEvent.setIdentity(officerId);
        caseplanNotifEvent.setAlertType(NOTICE);
        caseplanNotifEvent.setJuvenileNum(casePlanForm.getJuvenileNum());
        IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchNotification.postEvent(caseplanNotifEvent);
        	
	}
	
	
	public void clmRejectNotification(CaseplanForm casePlanForm, String officerId, Name name, String juvName ) {
		
		StringBuffer sb = new StringBuffer();
		
		SendCaseplanNotificationEventEvent caseplanNotifEvent = (SendCaseplanNotificationEventEvent) EventFactory
        	.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEPLANNOTIFICATIONEVENT);
    	
    	caseplanNotifEvent.setSubject("CLM Rejected Caseplan for Supervision#: " + casePlanForm.getCasefileId());
    	
    	sb.append("The caseplan for"+" "+juvName+","+" "+ "Supervision#"+ casePlanForm.getCasefileId()+" ");
    	sb.append("has been rejected by"+" "+name+" "+"for the following reason:"+casePlanForm.getComments());
		sb.append("\n");
		
		caseplanNotifEvent.setNotificationMessage(sb.toString());
		caseplanNotifEvent.setNoticeTopic(MJCW_JPO_PENDING_CASEPLAN_NOTIFICATION);
		
		//To JPO
		caseplanNotifEvent.setIdentityType(JPO);
		caseplanNotifEvent.setCasefileId(casePlanForm.getCasefileId());
        	caseplanNotifEvent.setIdentity(officerId);
        	caseplanNotifEvent.setAlertType(NOTICE);        
		caseplanNotifEvent.setCasefileId(casePlanForm.getCasefileId());
		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		
		dispatchNotification.postEvent(caseplanNotifEvent);

		
	}
	
	
	/**
	 * @param ownerId
	 * @param topic
	 * @param subject
	 * @param form
	 * @param hours
	 * Non-Residential/Residential Supervision Type Task to the Assigned JPO.
	 */
	public void scheduleCasePlanReviewTask(String ownerId, String topic, String subject, CaseplanForm form, int hours, String scheduleClassName, String action, String identityType) {

		SendCaseplanNotificationEventEvent caseplanTask = (SendCaseplanNotificationEventEvent) EventFactory
				.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEPLANNOTIFICATIONEVENT);
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
		Date dueDate = cal.getTime();
		
		caseplanTask.setDueDate(dueDate);
		caseplanTask.setOwnerId(ownerId);
		caseplanTask.setCasefileId(form.getCasefileId());
		caseplanTask.setCaseplanId(form.getCurrentCaseplan().getCaseplanId());
		caseplanTask.setJuvenileNum(form.getJuvenileNum());
		caseplanTask.setSubmitAction(action);
		caseplanTask.setTaskTopic(topic);
		caseplanTask.setTaskSubject(subject);
		caseplanTask.setAlertType("Task");
		caseplanTask.setIdentityType(identityType);
		//Registering the task with the scheduler
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
		rtEvent.setScheduleClassName(scheduleClassName);
		rtEvent.setFirstNotificationDate(dueDate);
		rtEvent.setNextNotificationDate(dueDate);
		String taskName = caseplanTask.getClass().getName() +"-"+ Math.random();
		rtEvent.setTaskName(taskName);
		rtEvent.setNotificationEvent(caseplanTask);
		
		dueDate = null;
		cal = null;
		date = null;
		
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);

	}
	
	
	/**
	 * @param ownerId
	 * @param topic
	 * @param subject
	 * @param form
	 * @param hours
	 * Task to the Assigned JPO for Supervision Types Intensive/Enhanced/Community Supervision/Deferred Adjudication/Prosecution  .
	 */
	public void scheduleOverdueInterviewTask(String ownerId, String topic, String subject, CasefileActivationForm form, int hours, String  juvenileNum , Date date, String officerId, String identityType) {

		SendOverdueInterviewNotificationEvent interviewTask = (SendOverdueInterviewNotificationEvent) EventFactory
				.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEINTERVIEWNOTIFICATION);
		
		Calendar cal = Calendar.getInstance();
		Date testDate = new Date();
		cal.setTime(testDate);
		cal.add(Calendar.HOUR, hours);
		Date dueDate = cal.getTime();
		
		interviewTask.setDueDate(dueDate);
		interviewTask.setOwnerId(ownerId);
		interviewTask.setCasefileId(form.getCasefileID());
		interviewTask.setJuvenileNum(juvenileNum);
		interviewTask.setOfficerId(officerId);
		
		interviewTask.setSubmitAction("Link Create");
		interviewTask.setTaskTopic(topic);
		interviewTask.setTaskSubject(subject);
		interviewTask.setAlertType("Task");
		interviewTask.setIdentityType(identityType);
		//dispatch.postEvent(interviewTask);

		//Registering the task with the scheduler
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
		rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
		rtEvent.setFirstNotificationDate(dueDate);
		rtEvent.setNextNotificationDate(dueDate);
		String taskName = interviewTask.getClass().getName() +"-"+ Math.random();
		rtEvent.setTaskName(taskName);
		rtEvent.setNotificationEvent(interviewTask);
		
		dueDate = null;
		cal = null;
		date = null;
		
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);

	}
	
	
	/**
	 * @param ownerId
	 * @param subject
	 * @param hours
	 * @param date
	 * @param form
	 * @param juvenileCasefileForm
	 */
	public void schedulePreCourtInterviewNotification(String ownerId, String subject, int hours, Date date,
		ScheduleNewEventForm form, JuvenileCasefileForm juvenileCasefileForm) {
		
		StringBuffer sb = new StringBuffer();
		
		SendOverdueInterviewNotificationEvent notificationEvent = (SendOverdueInterviewNotificationEvent) EventFactory
				.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDOVERDUEINTERVIEWNOTIFICATION);
		
		notificationEvent.setSubject(subject);
		
		sb.append("Schedule Juvenile for Pre-court interview. " + juvenileCasefileForm.getJuvenileName()
				+ ", Juvenile# " + form.getJuvenileNum() + " who was assigned to you on ");
		sb.append(dateToString(juvenileCasefileForm.getActivationDate())+ ", has a pending court date and has not been interviewed.");
		sb.append("\n");
		
		notificationEvent.setNotificationMessage(sb.toString());
		notificationEvent.setNoticeTopic(MJCW_JPO_PRECOURT_INTERVIEWNOTIFICATION);
		String taskName = SendOverdueInterviewNotificationEvent.class.getName() + "-" + form.getCaseFileId();
		notificationEvent.setAlertType(NOTICE);
		notificationEvent.setSupervisionNumber(juvenileCasefileForm.getSupervisionNum());
		//To JPO
		notificationEvent.setIdentity(ownerId);
		notificationEvent.setIdentityType(JPO);
		this.scheduleNotification(notificationEvent, taskName, date, hours);
		//To CLM
		notificationEvent.setIdentity(juvenileCasefileForm.getCaseloadManagerId());
		notificationEvent.setIdentityType("clm");
		this.scheduleNotification(notificationEvent, taskName, date, hours);
	}

	/**
	 * Sending 5 days over due task to service provider for event attendance documentation.
	 */
	public void sendServiceProvider5DayDocumentAttendanceNotification(CalendarServiceEventResponseEvent cser) {
		
		SendJuvenileServiceProviderDocumentAttendanceNotificationEvent reqEvent = (SendJuvenileServiceProviderDocumentAttendanceNotificationEvent) EventFactory
        	.getInstance(ServiceProviderControllerServiceNames.SENDJUVENILESERVICEPROVIDERDOCUMENTATTENDANCENOTIFICATION);
    	
    	reqEvent.setServiceEventId(cser.getServiceEventId());
    	reqEvent.setServiceProviderId(Integer.toString(cser.getServiceProviderId()));
    	reqEvent.setEventDate(cser.getStartDatetime());
    	reqEvent.setProgramId(cser.getProgramId());
    	reqEvent.setServiceId(cser.getServiceId());
    	
        String taskName = reqEvent.getClass().getSimpleName() + "-" + cser.getServiceEventId();	

        this.scheduleNotification(reqEvent, taskName, cser.getStartDatetime(), 120);	
        
        reqEvent = null;
        taskName = null;
	}

	
	/**
	 * @param date
	 * @return
	 */
	public String dateToString(Date date) 
    {
		String formattedDate = (date != null)? FULL_DATE_FORMAT.format(date): "INVALID ARGUMENT";
		System.out.println("Date returned: "+formattedDate);
		return formattedDate;
    }
	// End Added by Vijay
	
}
