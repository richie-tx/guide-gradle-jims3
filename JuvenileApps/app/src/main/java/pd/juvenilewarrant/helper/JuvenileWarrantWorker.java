package pd.juvenilewarrant.helper;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import naming.JuvenileNotificationControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.NotificationControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;

import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilewarrant.ProcessReturnOfServiceNotificationEvent;
import messaging.juvenilewarrant.SendFailureToEnterReleaseDetailsNotificationEvent;
import messaging.juvenilewarrant.SendReleaseDecisionTimeExpiredNotificationEvent;
import messaging.juvenilewarrant.SendDirectiveToApprehendNotificationEvent;
import messaging.juvenilewarrant.SendJuvenileArrestWarrantNotificationEvent;
import messaging.juvenilewarrant.SendOICNotificationEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.scheduling.RegisterTaskEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.naming.CalendarConstants;
import pd.codetable.Code;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.juvenilewarrant.Charge;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantService;
import pd.notification.juvenilewarrant.PDJuvenileWarrantNotificationHelper;
import pd.pattern.IWorker;


/**
 * @author Jim Fisher
 *  
 */
public class JuvenileWarrantWorker implements IWorker
{

	private static final String EMAIL_TO = "RICHARD.YOUNG@US.HCTX.NET";
	private static final String EMAIL_FROM = "JIMS2NOTIFICATION@ITC.HCTX.NET";
	
    public JuvenileWarrantWorker()
    {
    }

    public void sendInvalidStageError(JuvenileWarrant warrant)
    {
        InvalidWarrantStageErrorEvent errorEvent = new InvalidWarrantStageErrorEvent();
        errorEvent.setWarrantNum(warrant.getWarrantNum());
        errorEvent.setWarrantAcknowledgeStatus(warrant.getWarrantAcknowledgeStatusId());
        errorEvent.setWarrantActivationStatus(warrant.getWarrantActivationStatusId());
        errorEvent.setWarrantSignatureStatus(warrant.getWarrantSignedStatusId());
        errorEvent.setWarrantStatus(warrant.getWarrantStatusId());
        EventManager.getSharedInstance(EventManager.REPLY).postEvent(errorEvent);
    }

    public void sendFailureToEnterReleaseDetails(JuvenileWarrant warrant, String currentUserId)
    {

        SendFailureToEnterReleaseDetailsNotificationEvent notificationEvent = new SendFailureToEnterReleaseDetailsNotificationEvent();
        notificationEvent.setWarrantNum(warrant.getWarrantNum());

        UserProfile currentUser = UserProfile.find(currentUserId);
        notificationEvent.setUserFirstName(currentUser.getFirstName());
        notificationEvent.setUserLastName(currentUser.getLastName());

        if (currentUser.getPhoneExt() != null)
        {
            notificationEvent.setUserPhone(currentUser.getPhoneNum() + " " + currentUser.getPhoneExt());
        }
        else
        {
            notificationEvent.setUserPhone(currentUser.getPhoneNum());
        }

        notificationEvent.setUserEmail(currentUser.getEmail());

        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.HOURLY_SCHEDULE_CLASS);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);

        rtEvent.setFirstNotificationDate(cal.getTime());
        rtEvent.setNextNotificationDate(cal.getTime());
        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                notificationEvent.getWarrantNum(),
                PDNotificationConstants.NOTIFICATION_FAILURE_TO_ENTER_RELEASE_DETAILS);
        rtEvent.setTaskName(taskName);
        notificationEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

    /*
     * Arrest Warrant Notifications
     */

    private void sendARRNotification(JuvenileWarrant warrant, int notificationType)
    {
        // Used for when creating a warrant, and the following notifications
        // need to be sent.
        if (notificationType == PDNotificationConstants.CREATE_WARRANT)
        {
            this.sendNotificationARRInitiated(warrant);
            this.sendNotificationARRActivationFailure(warrant);
        }
        else if (notificationType == PDNotificationConstants.ARR_WANTED)
        {
            this.sendNotificationARRWanted(warrant);
            this.unregisterARRNotification(warrant);
        }
    }

    /**
     * @param warrant
     */
    private void unregisterARRNotification(JuvenileWarrant warrant)
    {
        SendJuvenileArrestWarrantNotificationEvent notificationEvent = (SendJuvenileArrestWarrantNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDJUVENILEARRESTWARRANTNOTIFICATION);

        String warrantNum = warrant.getWarrantNum();

        //Unregister the previous task
        String oldTaskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.ARR_ACTIVATION_FAILURE);

        this.unregisterTask(oldTaskName);

    }

    /**
     * @param warrant
     */
    public void unregisterReleaseDecisionTime(JuvenileWarrant warrant)
    {
        SendReleaseDecisionTimeExpiredNotificationEvent notificationEvent = (SendReleaseDecisionTimeExpiredNotificationEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.SENDRELEASEDECISIONTIMEEXPIREDNOTIFICATION);

        //Unregister the previous task
        String oldTaskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrant.getWarrantNum(), PDNotificationConstants.NOTIFICATION_RELEASE_DECISION_TIMER_EXPIRED);

        this.unregisterTask(oldTaskName);

    }

    /**
     * @param warrant
     */
    public void unregisterFailureToRelesaseDetails(JuvenileWarrant warrant)
    {
        SendFailureToEnterReleaseDetailsNotificationEvent notificationEvent = new SendFailureToEnterReleaseDetailsNotificationEvent();

        //Unregister the previous task
        String oldTaskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                notificationEvent.getWarrantNum(),
                PDNotificationConstants.NOTIFICATION_FAILURE_TO_ENTER_RELEASE_DETAILS);

        this.unregisterTask(oldTaskName);

    }

    private void sendNotificationARRWanted(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance(NotificationControllerServiceNames.CREATENOTIFICATION);

        UserProfile warrantOriginator = warrant.getWarrantOriginatorUser();
        UserProfile jpo = warrant.getProbationOfficerOfRecord();
        OfficerProfile officer = warrant.getOfficer();

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("WANTED");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
    
        notificationEvent.setSubject(buffer.toString());
        
        notificationEvent.setNotificationTopic("JW.ARR.WANTED");

        if(jpo !=null)
        {
          notificationEvent.addIdentity("jpo", (IAddressable) jpo);
        }
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addIdentity("officer", (IAddressable) officer);

        notificationEvent.addContentBean(warrant);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationARRInitiated(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("ACTIVATE");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
        
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.ARR.INITIATED");
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationARRActivationFailure(JuvenileWarrant warrant)
    {
        SendJuvenileArrestWarrantNotificationEvent notificationEvent = (SendJuvenileArrestWarrantNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDJUVENILEARRESTWARRANTNOTIFICATION);

        String warrantNum = warrant.getWarrantNum();
        UserProfile originator = null;
        String emailTo = "";
        if (warrant != null)
        {
            //Send email only to warrant originator
            originator = warrant.getWarrantOriginatorUser();
            emailTo = originator.getEmail();
        }

        notificationEvent.setWarrantNum(warrant.getWarrantNum());
        notificationEvent.setEmailTo(emailTo);

        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.ARR_ACTIVATION_FAILURE);
        //create RegisterTaskEvent and post it
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();

        Calendar calendar = Calendar.getInstance();
        rtEvent.setScheduleClassName(CalendarConstants.HALF_HOURLY_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(calendar.getTime());
        //set notification for 24 hours from current date/time
        calendar.add(Calendar.DATE, 1);
        rtEvent.setNextNotificationDate(calendar.getTime());
        rtEvent.setTaskName(taskName);
        notificationEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

    /*
     * Probable Cause Notifications
     */
    private void sendPCNotification(JuvenileWarrant warrant, int notificationType)
    {

        // Used for when creating a warrant, and the following notifications
        // need to be sent.
        if (notificationType == PDNotificationConstants.CREATE_WARRANT)
        {
            this.sendPCNotification(warrant, PDNotificationConstants.PC_WANTED);
        }
        switch (notificationType)
        {
        case PDNotificationConstants.PC_WANTED:
            this.sendNotificationPCInitiated(warrant);

        default:
            break;
        }
    }

    private void sendNotificationPCInitiated(JuvenileWarrant warrant)
    {
        UserProfile warrantOriginator = null;
        UserProfile jpo = null;
        OfficerProfile officer = null;
        if (warrant != null)
        {

            warrantOriginator = warrant.getWarrantOriginatorUser();
            jpo = warrant.getProbationOfficerOfRecord();
            officer = warrant.getOfficer();
        }

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("WANTED");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.PCW.INITIATED");
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addIdentity("warrantJPO", (IAddressable) jpo);
        notificationEvent.addIdentity("officer", (IAddressable) officer);

        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    /*
     * Violation of Probation Notifications
     */

    /**
     * @param notificationType
     */
    private void sendVOPNotification(JuvenileWarrant warrant, int notificationType)
    {
        // Used for when creating a warrant, and the following notifications
        // need to be sent.
        switch (notificationType)
        {
        case PDNotificationConstants.CREATE_WARRANT:
            this.sendNotificationVOPInitiated(warrant);
            break;

        case PDNotificationConstants.VOP_ACTIVATION:
            this.sendNotificationVOPActivated(warrant);
            break;

        case PDNotificationConstants.VOP_REJECTION:
            this.sendNotificationVOPRejected(warrant);
            break;

        case PDNotificationConstants.VOP_UNSEND:
            this.sendNotificationVOPUnsend(warrant);
            break;

        }
    }

    /**
     * @param warrant
     */
    private void sendNotificationVOPUnsend(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        UserProfile warrantOriginator = null;
        if (warrant != null)
        {
            warrantOriginator = warrant.getWarrantOriginatorUser();
        }
        
        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("UNSEND");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.VOP.UNSEND");
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationVOPActivated(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        UserProfile warrantOriginator = null;
        if (warrant != null)
        {
            warrantOriginator = warrant.getWarrantOriginatorUser();
        }
        
        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("ACTIVATED");        
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.VOP.ACTIVATED");
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationVOPInitiated(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("ACTIVATE");        
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.VOP.INITIATED");

        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationVOPRejected(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("REJECTED");        
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        UserProfile warrantOriginator = null;
        if (warrant != null)
        {
            warrantOriginator = warrant.getWarrantOriginatorUser();
        }
        notificationEvent.setNotificationTopic("JW.VOP.REJECTED");
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    /**
     * Warrant Recall Notifications
     */
    public void sendNotificationWarrantRecalled(JuvenileWarrant warrant)
    {

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("WARRENT RECALLED");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());
        
        notificationEvent.setNotificationTopic("JW.WARRANT.RECALLED");
        notificationEvent.addContentBean(warrant);
        Code code = warrant.getWarrantType();
        notificationEvent.addContentBean(code);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);

    }
    
    /**
     * Order of Immediate Custody Notifications
     */
    /**
     * Override the sendNotificaiton method on the base JuvenileWarrantClass for
     * the OIC specific implementation
     */
    private void sendOICNotification(JuvenileWarrant warrant, int notificationType)
    {

        if (notificationType == PDNotificationConstants.CREATE_WARRANT)
        {
            this.sendOICNotification(warrant, PDNotificationConstants.OIC_NEEDS_TO_BE_SIGNED);
        }
        else if (notificationType == PDNotificationConstants.UPDATE_WARRANT)
        {
            notificationType = PDNotificationConstants.OIC_DO_NOT_ISSUE;
            this.unregisterOICNotification(warrant, notificationType);
            this.sendNotificationOICInitiated(warrant);
            this.sendNotificationOICSignatureFailure(warrant);
        }
        else if (notificationType == PDNotificationConstants.OIC_NEEDS_TO_BE_SIGNED)
        {
            this.sendNotificationOICInitiated(warrant);
            this.sendNotificationOICSignatureFailure(warrant);
        }
        else if (notificationType == PDNotificationConstants.OIC_DO_NOT_ISSUE)
        {
            this.sendNotificationOICNotIssue(warrant);
            this.unregisterOICNotification(warrant, notificationType);
        }
        else if (notificationType == PDNotificationConstants.OIC_JUVENILE_IS_WANTED)
        {
            this.sendNotificationOICWanted(warrant);
            this.unregisterOICNotification(warrant, notificationType);
        }
        else if (notificationType == PDNotificationConstants.OIC_UPDATES_REQUIRED)
        {
            this.sendNotificationOICReturned(warrant);
            this.unregisterOICNotification(warrant, notificationType);
        }
    }

    /**
     * @param warrant
     */
    private void unregisterOICNotification(JuvenileWarrant warrant, int notificationType)
    {
        SendOICNotificationEvent notificationEvent = (SendOICNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDOICNOTIFICATION);

        String warrantNum = warrant.getWarrantNum();
        int notifType = notificationType;

        //Unregister the previous task
        String oldTaskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, notifType);

        this.unregisterTask(oldTaskName);
    }

    /**
     * @param warrant
     */
    private void sendNotificationOICSignatureFailure(JuvenileWarrant warrant)
    {

        SendOICNotificationEvent notificationEvent = (SendOICNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDOICNOTIFICATION);

        String warrantNum = warrant.getWarrantNum();

        //create RegisterTaskEvent and post it

        // Get the taskName and set it on the RegisterTaskEvent and the
        // notificationEvent
        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.OIC_DO_NOT_ISSUE);
        //create RegisterTaskEvent and post it
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();

        notificationEvent.setWarrantNum(warrantNum);

        Calendar calendar = Calendar.getInstance();
        rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
        //set notification for 24 hours from current date/time
        calendar.add(Calendar.DATE, 1);
        rtEvent.setFirstNotificationDate(calendar.getTime());
        rtEvent.setNextNotificationDate(calendar.getTime());
        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

    private void sendNotificationOICInitiated(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");
        
        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("NEEDS TO BE SIGNED");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        Charge juvCharges = null;
        if (warrant != null)
        {
            Iterator charges = warrant.getCharges().iterator();
            while (charges.hasNext())
            {

                juvCharges = (Charge) charges.next();
            }
        }
        notificationEvent.setNotificationTopic("JW.OIC.INITIATED");
        notificationEvent.addContentBean(warrant);
        notificationEvent.addContentBean(juvCharges);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void unregisterTask(String taskName)
    {
        UnregisterTaskEvent unregisterEvent = new UnregisterTaskEvent();
        unregisterEvent.setTaskName(taskName);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(unregisterEvent);
    }

    private void sendNotificationOICNotIssue(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("UNSEND");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.OIC.UNSEND");
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationOICWanted(JuvenileWarrant warrant)
    {

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");
        Charge juvCharges = null;
        UserProfile jpo = null;

        if (warrant != null)
        {
            jpo = warrant.getProbationOfficerOfRecord();
            Iterator charges = warrant.getCharges().iterator();
            while (charges.hasNext())
            {
                juvCharges = (Charge) charges.next();
            }
        }
        
        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("WANTED");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.OIC.WANTED");
        notificationEvent.addIdentity("warrantJPO", (IAddressable) jpo);
        notificationEvent.addContentBean(warrant);
        notificationEvent.addContentBean(juvCharges);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationOICReturned(JuvenileWarrant warrant)
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        Charge juvCharges = null;
        if (warrant != null)
        {
            Iterator charges = warrant.getCharges().iterator();
            while (charges.hasNext())
            {
                juvCharges = (Charge) charges.next();
            }
        }
        
        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("UPDATES REQUIRED");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.OIC.RETURNED");
        notificationEvent.addContentBean(warrant);
        notificationEvent.addContentBean(juvCharges);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    /*
     * Directive to Apprehend Notification
     */

    private void sendDTANotification(JuvenileWarrant warrant, int notificationType)
    {
        switch (notificationType)
        {
        case PDNotificationConstants.CREATE_WARRANT:
            this.sendNotificationDTAInitiated(warrant);
            this.sendNotificationDTAAcknowledgementFailure(warrant);
            break;

        case PDNotificationConstants.DTA_FILED:
            this.sendNotificationDTAAcknowledged(warrant);
            this.sendNotificationDTAActivationFailure(warrant);
            break;

        case PDNotificationConstants.DTA_WANTED:
            this.sendNotificationDTAWanted(warrant);
            this.unregisterDTANotification(warrant);
            break;
        }
    }

    /**
     * @param warrant
     */
    private void unregisterDTANotification(JuvenileWarrant warrant)
    {
        SendDirectiveToApprehendNotificationEvent notificationEvent = (SendDirectiveToApprehendNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDDIRECTIVETOAPPREHENDNOTIFICATION);

        String warrantNum = warrant.getWarrantNum();

        //Unregister the previous task
        String oldTaskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.DTA_ACTIVATION_FAILURE);

        this.unregisterTask(oldTaskName);
    }

    private void sendNotificationDTAInitiated(JuvenileWarrant warrant)
    {
        //JuvenileWarrant warrant =
        // JuvenileWarrant.find(requestEvent.getWarrantNum());
        OfficerProfile officer = null;

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");
        
        if (warrant != null)
        {
            officer = warrant.getOfficer();
        }
        
        StringBuffer buffer = new StringBuffer(100);
              buffer.append(warrant.getWarrantTypeId());
              buffer.append(" Warrant #");
              buffer.append(warrant.getWarrantNum());
              buffer.append(", ");
              buffer.append("INITIATED");              
              buffer.append(" for ");
              buffer.append(warrant.getNameLastFirstMiddleSuffix());
        
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.DTA.INITIATED");

        notificationEvent.addContentBean(officer);
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationDTAWanted(JuvenileWarrant warrant)
    {
        // This section utilizes the new notification framework.
        UserProfile warrantOriginator = null;
        UserProfile jpo = null;
        OfficerProfile officer = null;

        if (warrant != null)
        {

            warrantOriginator = warrant.getWarrantOriginatorUser();
            jpo = warrant.getProbationOfficerOfRecord();
            officer = warrant.getOfficer();
        }

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("APPREHEND");        
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.DTA.ACTIVATED");
        //the following line sets the warrant originator that was //referenced
        // in XML above
        notificationEvent.addIdentity("officer", (IAddressable) officer);
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addIdentity("warrantJPO", (IAddressable) jpo);
        notificationEvent.addContentBean(warrant);
        notificationEvent.addContentBean(officer);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    private void sendNotificationDTAAcknowledgementFailure(JuvenileWarrant warrant)
    {
        SendDirectiveToApprehendNotificationEvent notificationEvent = (SendDirectiveToApprehendNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDDIRECTIVETOAPPREHENDNOTIFICATION);

        //Register DTA acknowledgement failure message
        String warrantNum = warrant.getWarrantNum();

        // Send email only to warrant originator
        UserProfile originator = warrant.getWarrantOriginatorUser();
        String emailTo = originator.getEmail();

        notificationEvent.setWarrantNum(warrant.getWarrantNum());
        notificationEvent.setEmailTo(emailTo);
        notificationEvent.setNotificationType(PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE);
        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE);
        notificationEvent.setTaskName(taskName);

        // create RegisterTaskEvent and post it
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();

        Calendar calendar = Calendar.getInstance();
        rtEvent.setScheduleClassName(CalendarConstants.DAILY_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(calendar.getTime());
        //set notification for 24 hours from current date/time
        calendar.add(Calendar.DATE, 1);
        rtEvent.setNextNotificationDate(calendar.getTime());
        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

    private void sendNotificationDTAActivationFailure(JuvenileWarrant warrant)
    {
        SendDirectiveToApprehendNotificationEvent notificationEvent = (SendDirectiveToApprehendNotificationEvent) EventFactory
                .getInstance(JuvenileNotificationControllerServiceNames.SENDDIRECTIVETOAPPREHENDNOTIFICATION);

        String warrantNum = warrant.getWarrantNum();

        //Unregister the previous task
        String oldTaskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE);

        this.unregisterTask(oldTaskName);

        // Send email only to warrant originator

        notificationEvent.setWarrantNum(warrantNum);

        notificationEvent.setNotificationType(PDNotificationConstants.DTA_ACTIVATION_FAILURE);
        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrantNum, PDNotificationConstants.DTA_ACTIVATION_FAILURE);
        notificationEvent.setTaskName(taskName);

        RegisterTaskEvent rtEvent = new RegisterTaskEvent();

        Calendar calendar = Calendar.getInstance();
        rtEvent.setScheduleClassName(CalendarConstants.DAILY_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(calendar.getTime());

        // set notification for 24 hours from current date/time
        calendar.add(Calendar.DATE, 1);
        rtEvent.setNextNotificationDate(calendar.getTime());
        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

    private void sendNotificationDTAAcknowledged(JuvenileWarrant warrant)
    {

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("ACTIVATE");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
  
        notificationEvent.setSubject(buffer.toString());

        notificationEvent.setNotificationTopic("JW.DTA.ACKNOWLEDGED");

        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    /**
     * @param warrant
     * @param i
     */
    public void sendNotification(JuvenileWarrant warrant, int notificationType)
    {
        if (PDJuvenileWarrantConstants.WARRANT_TYPE_ARR.equals(warrant.getWarrantTypeId()))
        {
            this.sendARRNotification(warrant, notificationType);
        }
        else if (PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equals(warrant.getWarrantTypeId()))
        {
            this.sendDTANotification(warrant, notificationType);
        }
        else if (PDJuvenileWarrantConstants.WARRANT_TYPE_PCW.equals(warrant.getWarrantTypeId()))
        {
            this.sendPCNotification(warrant, notificationType);
        }
        else if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrant.getWarrantTypeId()))
        {
            this.sendOICNotification(warrant, notificationType);
        }
        else if (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(warrant.getWarrantTypeId()))
        {
            this.sendVOPNotification(warrant, notificationType);
        }
    }

    /**
     * @param warrant
     */
    public void setTransferFromReleaseDecisionTimer(JuvenileWarrant warrant)
    {
        SendReleaseDecisionTimeExpiredNotificationEvent notificationEvent = (SendReleaseDecisionTimeExpiredNotificationEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.SENDRELEASEDECISIONTIMEEXPIREDNOTIFICATION);

        notificationEvent.setWarrantNum(warrant.getWarrantNum());
        Code releaseDecisionCode = warrant.getReleaseDecision();
        String releaseDecision;
        if (releaseDecisionCode != null)
        {
            releaseDecision = releaseDecisionCode.getDescription();
        }
        else
        {
            releaseDecision = "[RELEASE DECISION]";
        }
        notificationEvent.setReleaseDecision(releaseDecision);
        notificationEvent
                .setReleaseDecisionDate(new java.sql.Timestamp(warrant.getReleaseDecisionTimeStamp().getTime()));
        notificationEvent.setJuvenileFirstName(warrant.getFirstName());
        notificationEvent.setJuvenileLastName(warrant.getLastName());

        Collection services = warrant.getServices();
        Iterator i = services.iterator();

        JuvenileWarrantService service = null;
        boolean done = false;
        while (i.hasNext() && done == false)
        {
            service = (JuvenileWarrantService) i.next();
            if (PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL.equals(service.getServiceStatusId()))
            {
                done = true;
            }
        }
        OfficerProfile officer = service.getExecutorOfficer();
        Department department = service.getExecutorOfficerDepartment();
        i = department.getContacts().iterator();
        DepartmentContact contact = null;
        if (i.hasNext())
        {
            contact = (DepartmentContact) i.next();
        }

        if (officer != null)
        {
            notificationEvent.setOfficerFirstName(officer.getFirstName());
            notificationEvent.setOfficerLastName(officer.getLastName());
            if (officer.getManagerFirstName() != null && officer.getManagerFirstName().equals("") == false
                    && officer.getManagerLastName() != null && officer.getManagerLastName().equals("") == false)
            {
                notificationEvent.setManagerFirstName(officer.getManagerFirstName());
                notificationEvent.setManagerLastName(officer.getManagerLastName());
            }
            else
            {
                if (department != null && contact != null)
                {
                    notificationEvent.setContactFirstName(contact.getFirstName());
                    notificationEvent.setContactLastName(contact.getLastName());
                }
            }
        }

        if (contact != null)
        {
            String phoneStr = contact.getPhoneNum();
            if (contact.getPhoneExt() != null && contact.getPhoneExt().equals("") == false)
            {
                phoneStr += " x" + contact.getPhoneExt();
            }

            notificationEvent.setContactPhone(phoneStr);
        }

        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                warrant.getWarrantNum(), PDNotificationConstants.NOTIFICATION_RELEASE_DECISION_TIMER_EXPIRED);

        //create RegisterTaskEvent and post it
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();

        // Send this notification 48 hours from now.
        Calendar cal = Calendar.getInstance();
        cal.setTime(warrant.getReleaseDecisionTimeStamp());
        cal.add(Calendar.HOUR, 48);
        Date futureDate = cal.getTime();

        rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(futureDate);
        rtEvent.setNextNotificationDate(futureDate);
        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
    }

    public void setupReminderSignReturnOfService(ProcessReturnOfServiceNotificationEvent notificationEvent,
            Date startDate, int hours)
    {
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.HOUR, hours);
        rtEvent.setFirstNotificationDate(cal.getTime());
        rtEvent.setNextNotificationDate(cal.getTime());
        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                notificationEvent.getWarrantNum(), PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);
        taskName += "_" + hours;

        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(rtEvent);
    }

    private String getExecutorPhoneNums(JuvenileWarrantService service)
    {
        StringBuffer buffer = new StringBuffer(60);

        OfficerProfile officer = service.getExecutorOfficer();

        boolean hasWork = officer.getWorkPhoneNum() != null && officer.getWorkPhoneNum().equals("") == false;
        boolean hasCell = officer.getCellPhone() != null && officer.getCellPhone().equals("") == false;

        if(hasWork == true)
	    {
	        String officerWorkPhoneNum = officer.getWorkPhoneNumAndExtnString();
            buffer.append("work phone: ");
            buffer.append(officerWorkPhoneNum);
	    }
        else
		{
			buffer.append("NO WORK PHONE");
		}
        if (hasWork && hasCell)
        {
            buffer.append(", ");
        }
	    if(hasCell == true)
	    {
	        String officerCellPhoneNumer = officer.getCellPhoneNumberString();
            buffer.append("cell phone: ");
            buffer.append(officerCellPhoneNumer);
	    }
	    else
		{
			buffer.append("NO CELL PHONE IN OFFICER PROFILE");
		}
        return buffer.toString();
    }

    /**
     * @param warrant
     */
    public void sendReminderSignReturnOfService(JuvenileWarrant warrant, JuvenileWarrantService service, Date startDate)
    {
    	
    	String emailTo = "";
    	// Send email only to warrant originator
        UserProfile originator = warrant.getWarrantOriginatorUser();
        if( originator != null ){
            
            emailTo = originator.getEmail();
            
            if ( emailTo == null || "".equals( emailTo )){
            	
            	// Send email to richard y if the email address is null
            	emailTo = EMAIL_TO;
            }
            
            ProcessReturnOfServiceNotificationEvent notificationEvent = new ProcessReturnOfServiceNotificationEvent();

            notificationEvent.setWarrantNum(warrant.getWarrantNum());
            notificationEvent.setNameFirstMiddleLastSuffix(warrant.getNameFirstMiddleLastSuffix());
            notificationEvent.setNameLastFirstMiddleSuffix(warrant.getNameLastFirstMiddleSuffix());
            
            notificationEvent.setExecutorPhoneNum(this.getExecutorPhoneNums(service));
            notificationEvent.setEmailTo( emailTo );
            notificationEvent.setEmailFrom( EMAIL_FROM );

            notificationEvent.setNotificationType(PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);

            this.setupReminderSignReturnOfService(notificationEvent, startDate, 24);
            this.setupReminderSignReturnOfService(notificationEvent, startDate, 48); 
        }

    }

    /**
     * @param juvWarrant
     */
    public void sendReturnServiceSignatureEvent(JuvenileWarrant juvWarrant)
    {
    	String emailTo = "";
    	// Send email only to warrant originator
        UserProfile originator = juvWarrant.getWarrantOriginatorUser();
        if( originator!= null){
            
            emailTo = originator.getEmail();
        	
            if ( emailTo == null || "".equals( emailTo )){
            	
            	// Send email to Leslie if the email address is null
            	emailTo = EMAIL_TO;
            }
            
            ProcessReturnOfServiceNotificationEvent notificationEvent = new ProcessReturnOfServiceNotificationEvent();

            notificationEvent.setWarrantNum(juvWarrant.getWarrantNum());
            notificationEvent.setNameFirstMiddleLastSuffix(juvWarrant.getNameFirstMiddleLastSuffix());
            notificationEvent.setNameLastFirstMiddleSuffix(juvWarrant.getNameLastFirstMiddleSuffix());
            notificationEvent.setEmailTo(emailTo);
            notificationEvent.setEmailFrom( EMAIL_FROM );
            notificationEvent.setNotificationType(PDNotificationConstants.PROCESS_RETURN_OF_SERVICE);

            RegisterTaskEvent rtEvent = new RegisterTaskEvent();
            rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.DAILY_SCHEDULE_CLASS);
            rtEvent.setFirstNotificationDate(DateUtil.getCurrentDate());
            rtEvent.setNextNotificationDate(DateUtil.getCurrentDate());
            String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                    notificationEvent.getWarrantNum(), PDNotificationConstants.PROCESS_RETURN_OF_SERVICE);
            rtEvent.setTaskName(taskName);
            rtEvent.setNotificationEvent(notificationEvent);
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            dispatch.postEvent(rtEvent);
        }
 
    }

    /**
     * @param juvWarrant
     */
    public void sendExpiredSignReturnServiceSignatureEvent(JuvenileWarrant juvWarrant, JuvenileWarrantService service,
            Date startDate)
    {
    	//Send an email to Leslie if there is a problem
    	String emailTo = "";
    	// Send email only to warrant originator
        UserProfile originator = juvWarrant.getWarrantOriginatorUser();
        if( originator != null ){
            
            emailTo = originator.getEmail();
            if ( emailTo == null || "".equals( emailTo )){
            	
            	// Send email to Leslie if the email address is null
            	emailTo = EMAIL_TO;
            }
        	
            ProcessReturnOfServiceNotificationEvent notificationEvent = new ProcessReturnOfServiceNotificationEvent();

            notificationEvent.setWarrantNum(juvWarrant.getWarrantNum());
            notificationEvent.setNameFirstMiddleLastSuffix(juvWarrant.getNameFirstMiddleLastSuffix());
            notificationEvent.setNameLastFirstMiddleSuffix(juvWarrant.getNameLastFirstMiddleSuffix());
            
            notificationEvent.setNotificationType(PDNotificationConstants.EXPIRED_RETUNRED_NOT_SIGNED);
            notificationEvent.setExecutorPhoneNum(this.getExecutorPhoneNums(service));
            notificationEvent.setEmailTo(emailTo);
            notificationEvent.setEmailFrom( EMAIL_FROM );

            RegisterTaskEvent rtEvent = new RegisterTaskEvent();
            rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.HOUR, 72);
            rtEvent.setFirstNotificationDate(cal.getTime());
            rtEvent.setNextNotificationDate(cal.getTime());
            String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                    notificationEvent.getWarrantNum(), PDNotificationConstants.EXPIRED_RETUNRED_NOT_SIGNED);
            rtEvent.setTaskName(taskName);
            rtEvent.setNotificationEvent(notificationEvent);
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            dispatch.postEvent(rtEvent);
        }

    }

    /**
     * @param requestEvent
     */
    public static void unregisterEvents(JuvenileWarrant warrant)
    {

        ProcessReturnOfServiceNotificationEvent requestEvent = new ProcessReturnOfServiceNotificationEvent();

        requestEvent.setWarrantNum(warrant.getWarrantNum());
        String taskName24 = PDJuvenileWarrantNotificationHelper.getTaskName(requestEvent.getClass().getName(),
                requestEvent.getWarrantNum(), PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);
        taskName24 += "_" + "24";

        String taskName48 = PDJuvenileWarrantNotificationHelper.getTaskName(requestEvent.getClass().getName(),
                requestEvent.getWarrantNum(), PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);
        taskName48 += "_" + "48";

        String taskName72 = PDJuvenileWarrantNotificationHelper.getTaskName(requestEvent.getClass().getName(),
                requestEvent.getWarrantNum(), PDNotificationConstants.EXPIRED_RETUNRED_NOT_SIGNED);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        UnregisterTaskEvent unregisterEvent = new UnregisterTaskEvent();
        unregisterEvent.setTaskName(taskName24);
        dispatch.postEvent(unregisterEvent);

        unregisterEvent.setTaskName(taskName48);
        dispatch.postEvent(unregisterEvent);

        unregisterEvent.setTaskName(taskName72);
        dispatch.postEvent(unregisterEvent);
    }
}