/*
 * Created on Jun 20, 2007
 *
 */
package pd.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.GetCasefileByJuvNumRefNum;
import messaging.juvenilecase.GetJuvenileAlertDetailsEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.ProcessJuvenileCasefileAssignmentsEvent;
import messaging.juvenilecase.reply.JIMS2AlertDetailsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.notification.SendCasefileClosingNotificationEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.PersistentEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.scheduling.Process;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.CalendarConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.PDConstants;
import naming.UIConstants;

/**
 * @author mchowdhury
 *
 */

public class JuvenileCasefileAssignmentScheduler
{
	private boolean done = false;
	private final long SLEEP_TIME = 300000;	// 5 minutes

	/**
	 * Private Constructor
	 */
	private JuvenileCasefileAssignmentScheduler() 
	{		
	}

	private void execute() throws InterruptedException
	{
        Process process = null;
		try{
			Iterator iter = Process.findAll("name", PDConstants.PROCESS_SCHEDULER);
			while(iter.hasNext()){
				process = (Process) iter.next();
				break;
			}
			if(process == null || !process.isActive()){
				System.out.println("There is no process in the data store or the process is inactive.");
				return;			
		    }
		}catch(Exception e){
			e.printStackTrace();
			return;
		}		
            
		while(done == false)
		{			
			this.processCasefileAssignments(process);
			this.processCasefileAlerts();
			Thread.sleep(SLEEP_TIME);
		}
	}
	
	private void processCasefileAssignments(Process process)
	{
		ProcessJuvenileCasefileAssignmentsEvent requestEvent =
        	(ProcessJuvenileCasefileAssignmentsEvent) EventFactory.getInstance(
        JuvenileCaseControllerServiceNames.PROCESSJUVENILECASEFILEASSIGNMENTS);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		requestEvent.setProcessId(process.getOID().toString());
		dispatch.postEvent(requestEvent);
        

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

        Object obj = MessageUtil.filterComposite(compositeResponse, ReturnException.class);
	}

	/**
	 * TODO
	 * method used to fetch the details from M204 file JDJJSDT and to send the notices based upon supervision types.
	 */
	private void processCasefileAlerts() {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileAlertDetailsEvent requestEvent = (GetJuvenileAlertDetailsEvent) EventFactory
				.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEALERTDETAILS);

		Iterator iter = null;
		try{
			iter = JIMS2Alert.findAll(requestEvent);
		}catch(RuntimeException e){
			System.out.println("RunTime Exception Occured while running Alerts " + e.getMessage());
			iter = null;
			return;
		}catch(Exception e){
			System.out.println("Exception Occured while running Alerts " + e.getMessage());
			iter = null;
			return;
		}
		
		JIMS2Alert jims2Alert = null;
		JIMS2AlertDetailsResponseEvent alertRespEvent = null;
		while (iter.hasNext()) {
			jims2Alert = (JIMS2Alert) iter.next();
			if (jims2Alert != null) {
				alertRespEvent = jims2Alert.getValueObject();
				//fetching the casefile numbers based upon juvenileNum and
				// referralNum.
				GetCasefileByJuvNumRefNum searchEvent = new GetCasefileByJuvNumRefNum();
				searchEvent.setJuvenileNum(alertRespEvent.getJuvenileNum());
				searchEvent.setReferralNum(alertRespEvent.getReferralNum());
				jims2Alert.setJims2ExtractInd("S");
				Iterator caseIter = null;
				try{
					caseIter = JuvenileCasefileReferral.findAll(searchEvent);
					JuvenileCasefileReferral caseRef = null;
					while (caseIter.hasNext()) {
						caseRef = (JuvenileCasefileReferral) caseIter.next();
						
						if (caseRef.getCaseFileId() != null && !caseRef.getCaseFileId().equals("") && "A".equals(caseRef.getCaseStatusCd())) {
	
							//Populating Casefile Details using casefileId which has been fetched above.
							GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
							getCasefileEvent.setSupervisionNumber(caseRef.getCaseFileId());
							dispatch.postEvent(getCasefileEvent);
							CompositeResponse response = (CompositeResponse) dispatch.getReply();
							JuvenileCasefileResponseEvent casefileResp = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);
	
							String supervisionTypeId = casefileResp.getSupervisionTypeId();
							if (UIConstants.CASEFILE_SUPERVISION_TYPE_RESIDENTIAL_SUPERVISION.equalsIgnoreCase(supervisionTypeId)
									&& alertRespEvent.getAlertType().equalsIgnoreCase("R")) {
	
								// set the extract indicator to 'Y'
								jims2Alert.setJims2ExtractInd("Y");
								//Sending 24hours delayed Notice.
								sendCasefileClosing24HoursNotification(casefileResp, alertRespEvent);
							}
							else if (UIConstants.CASEFILE_SUPERVISION_TYPE_RESIDENTIAL_SUPERVISION.equalsIgnoreCase(supervisionTypeId)
									&& alertRespEvent.getAlertType().equalsIgnoreCase("C")) {
	
								// set the extract indicator to 'Y'
								jims2Alert.setJims2ExtractInd("Y");
								//Sending Release Cancelled Notice.
								sendCasefileClosingReleaseCancelledNotification(casefileResp, alertRespEvent);
							}
							else if (UIConstants.CASEFILE_SUPERVISION_TYPE_COURT_SUPERVISION.equalsIgnoreCase(supervisionTypeId)
									&& alertRespEvent.getAlertType().equalsIgnoreCase("D")) {
	
								//set the extract indicator to 'Y'
								jims2Alert.setJims2ExtractInd("Y");
								//Sending 7Days delayed Notice.
								sendCasefileClosing7DaysNotification(casefileResp, alertRespEvent);
							} else if (UIConstants.CASEFILE_SUPERVISION_TYPE_DEFERRED_ADJUDICATION_SUPERVISION.equalsIgnoreCase(supervisionTypeId)
									&& alertRespEvent.getAlertType().equalsIgnoreCase("N")) {
	
								//set the extract indicator to 'Y'
								jims2Alert.setJims2ExtractInd("Y");
								//Sending 6months delayed Notice.
								sendCasefileClosing6MonthsNotification(casefileResp);
							}else{
								jims2Alert.setJims2ExtractInd("S");
							}
							getCasefileEvent = null;
							response = null;
							casefileResp = null;
						}
						caseRef = null;
					}
				}catch(RuntimeException e){
					e.printStackTrace();
					continue;
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
				alertRespEvent = null;
				searchEvent = null;
				caseIter = null;
			}
			jims2Alert = null;
		}
	}
	
	/**
	 * @param casefileResp
	 * @param alertRespEvent
	 *            24Hours Overdue Notification
	 */
	public void sendCasefileClosing24HoursNotification(JuvenileCasefileResponseEvent casefileResp, JIMS2AlertDetailsResponseEvent alertRespEvent) throws Exception{
	SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
    .getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
	String subject = "Juvenile has been released. Casefile is still active.";
	casefileNotifEvent.setSubject(subject);
	StringBuffer sb = new StringBuffer("");
	String releaseDateStr = "";
	if(alertRespEvent.getReleaseDate()!=null && !(alertRespEvent.getReleaseDate().equals(""))){
		if(alertRespEvent.getReleaseDate().length() == 8){
		    releaseDateStr = DateUtil.dateToString(DateUtil.stringToDate(alertRespEvent.getReleaseDate(),"yyyyMMdd"),"MM/dd/yyyy");
		    releaseDateStr = releaseDateStr + " "+stringToTime(alertRespEvent.getReleaseTime());
		}		
	}
	sb.append(casefileResp.getJuvenileFirstName()+""+casefileResp.getJuvenileLastName() +", Supervision # " + casefileResp.getSupervisionNum());
	sb.append(", was released on "+ releaseDateStr +" and the case remains open.");
	sb.append("\n");
	casefileNotifEvent.setNotificationMessage(sb.toString());
	String taskName = casefileNotifEvent.getClass().getName();
	casefileNotifEvent.setNoticeTopic("MJCW.M204.JPO.CASEFILE.CLOSING.NOTIFICATION");
	casefileNotifEvent.setSupervisionNumber(casefileResp.getSupervisionNum());
	//To JPO
	casefileNotifEvent.setIdentityType("jpo");
    casefileNotifEvent.setIdentity(casefileResp.getProbationOfficerLogonId());
    this.scheduleNotification(casefileNotifEvent, taskName, 24);	
    //To CLM
    casefileNotifEvent.setIdentityType("clm");
    casefileNotifEvent.setNoticeTopic("MJCW.M204.CLM.CASEFILE.CLOSING.NOTIFICATION");
    casefileNotifEvent.setIdentity(casefileResp.getCaseloadManagerId());
    this.scheduleNotification(casefileNotifEvent, taskName, 24);
}
	
	/**
	 * @param casefileResp
	 * @param alertRespEvent
	 * Release Cancelled Notification
	 * as per #JIMS200046868
	 */
	public void sendCasefileClosingReleaseCancelledNotification(JuvenileCasefileResponseEvent casefileResp, JIMS2AlertDetailsResponseEvent alertRespEvent) throws Exception{
	SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
    .getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
	String subject = "Juvenile release is cancelled. Casefile is still active.";
	casefileNotifEvent.setSubject(subject);
	StringBuffer sb = new StringBuffer("");
	Date cancelledDate = new Date();
	sb.append(casefileResp.getJuvenileFirstName()+""+casefileResp.getJuvenileLastName() +", Supervision # " + casefileResp.getSupervisionNum());
	sb.append(",  Release was cancelled on "+ dateToString(cancelledDate) +" and the case remains open.");
	sb.append("\n");
	casefileNotifEvent.setNotificationMessage(sb.toString());
	String taskName = casefileNotifEvent.getClass().getName();
	casefileNotifEvent.setNoticeTopic("MJCW.M204.JPO.RELEASECANCELLED.NOTIFICATION");
	casefileNotifEvent.setSupervisionNumber(casefileResp.getSupervisionNum());
	//To JPO
	casefileNotifEvent.setIdentityType("jpo");
    casefileNotifEvent.setIdentity(casefileResp.getProbationOfficerLogonId());
    this.scheduleNotification(casefileNotifEvent, taskName, 24);	
    //To CLM
    casefileNotifEvent.setIdentityType("clm");
    casefileNotifEvent.setNoticeTopic("MJCW.M204.CLM.RELEASECANCELLED.NOTIFICATION");
    casefileNotifEvent.setIdentity(casefileResp.getCaseloadManagerId());
    this.scheduleNotification(casefileNotifEvent, taskName, 24);
}
	
	
	/**
	 * @param casefileResp
	 * @param alertRespEvent
	 * 7Days Overdue Notification
	 */
	public void sendCasefileClosing7DaysNotification(JuvenileCasefileResponseEvent casefileResp, JIMS2AlertDetailsResponseEvent alertRespEvent) throws Exception{
	SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
    .getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
	String subject = "Case has been disposed. Supervision is still active.";
	casefileNotifEvent.setSubject(subject);
	StringBuffer sb = new StringBuffer("");
	Date dispositionDate = null;
	if(alertRespEvent.getDispositionDate()!=null && !(alertRespEvent.getDispositionDate().equals(""))){
		if(alertRespEvent.getDispositionDate().length() == 8){
			dispositionDate = DateUtil.stringToDate(alertRespEvent.getDispositionDate(),"yyyyMMdd");			
		}
	}
	sb.append("Seven days have expired since a final disposition was entered by the Court on "+dispositionDate+" for "+
			casefileResp.getJuvenileFirstName()+" "+casefileResp.getJuvenileLastName()+" Juvenile# "+casefileResp.getJuvenileNum()+
			". the casefile, Supervision # " + casefileResp.getSupervisionNum()+" needs to be closed.");
	casefileNotifEvent.setNotificationMessage(sb.toString());
	String taskName = casefileNotifEvent.getClass().getName();
	casefileNotifEvent.setSupervisionNumber(casefileResp.getSupervisionNum());
	casefileNotifEvent.setNoticeTopic("MJCW.M204.JPO.CASEFILE.CLOSING.7DAYS.NOTIFICATION");
	//To JPO
	casefileNotifEvent.setIdentityType("jpo");
    casefileNotifEvent.setIdentity(casefileResp.getProbationOfficerLogonId());
    this.scheduleNotification(casefileNotifEvent, taskName, 168);	
    //To CLM
    casefileNotifEvent.setIdentityType("clm");
    casefileNotifEvent.setNoticeTopic("MJCW.M204.CLM.CASEFILE.CLOSING.7DAYS.NOTIFICATION");
    casefileNotifEvent.setIdentity(casefileResp.getCaseloadManagerId());
    this.scheduleNotification(casefileNotifEvent, taskName, 168);
}
	
	
	/**
	 * @param casefileResp
	 * 6months Overdue Notification
	 */
	public void sendCasefileClosing6MonthsNotification(JuvenileCasefileResponseEvent casefileResp) throws Exception{
	SendCasefileClosingNotificationEvent casefileNotifEvent = (SendCasefileClosingNotificationEvent) EventFactory
    .getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDCASEFILECLOSINGNOTIFICATION);
	String subject = "A non-suit decision is needed.";
	casefileNotifEvent.setSubject(subject);
	StringBuffer sb = new StringBuffer("");
	sb.append(casefileResp.getJuvenileFirstName()+""+casefileResp.getJuvenileLastName() +", Supervision # " + casefileResp.getSupervisionNum());
	sb.append(", assigned to "+casefileResp.getProbationOfficerFirstName()+" "+casefileResp.getProbationOfficerLastName());
	sb.append(" completed his six months of deferred prosecution on "+dateToString(casefileResp.getSupervisionEndDate()));
	sb.append(" A non suit has not been entered by the Court.");
	casefileNotifEvent.setNotificationMessage(sb.toString());
	String taskName = casefileNotifEvent.getClass().getName();
	casefileNotifEvent.setSupervisionNumber(casefileResp.getSupervisionNum());
	casefileNotifEvent.setNoticeTopic("MJCW.M204.CASEFILE.CLOSING.NOTIFICATION");
	//To JPO
	casefileNotifEvent.setIdentityType("jpo");
    casefileNotifEvent.setIdentity(casefileResp.getProbationOfficerLogonId());
    this.scheduleNotification(casefileNotifEvent, taskName, 4320);	
    //To CLM
    casefileNotifEvent.setIdentityType("clm");
    casefileNotifEvent.setIdentity(casefileResp.getCaseloadManagerId());
    this.scheduleNotification(casefileNotifEvent, taskName, 4320);
}
	

	/**
	 * @param event
	 * @param taskName
	 * @param date
	 * @param hours
	 * ScheduleNotification
	 */
	public void scheduleNotification(PersistentEvent event, String taskName, int hours) throws Exception {
        //create RegisterTaskEvent and post it
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
        Date date = new Date();
 		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        Date futureDate = cal.getTime();
        taskName += "_" + Math.random();
        rtEvent.setFirstNotificationDate(futureDate);
        rtEvent.setNextNotificationDate(futureDate);
        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(event);
        try{
        	dispatch.postEvent(rtEvent);
            CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
            ReturnException r = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
            if(r != null){
            	System.out.println(r.getMessage());
            	throw new Exception(r.getMessage());
            }
            
            Exception exp = (Exception) MessageUtil.filterComposite(compositeResponse, Exception.class);
            if(exp != null){
            	System.out.println(exp.getMessage());
            	throw new Exception(exp.getMessage());
            }
        }catch(Exception e){
        	e.printStackTrace();
        	throw new Exception(e.getMessage());
        }
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date)
    {
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String dateStr = "";
		try{
			if (date != null) {
			dateStr = dfmt.format(date);
		}
    }
		catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		}
		return dateStr;
    }
	
	/**
	 * @param timeStr
	 * @return 
	 * this method returns formatted time string as "04:55 PM" when given "1655".  
	 */
	public static String stringToTime(String timeStr) {
        String formattedTimeStr = "";
        if (timeStr != null && timeStr.length() == 4) {
            int hour = Integer.parseInt(timeStr.substring(0, 2));
            String minutes = timeStr.substring(2);
            if (hour >= 12 && hour < 24) {
                hour = hour - 12;
                if (hour < 10 && hour != 0)
                    formattedTimeStr = "0" + hour + ":" + minutes + " PM";
                else if (hour == 0)
                    formattedTimeStr = "12" + ":" + minutes + " PM";
                else
                    formattedTimeStr = hour + ":" + minutes + " PM";
            } else if (hour < 12 && hour != 0) {
                if (hour < 10)
                    formattedTimeStr = "0" + hour + ":" + minutes + " AM";
                else
                    formattedTimeStr = hour + ":" + minutes + " AM";
            } else if (hour == 24 || hour == 0) {
                formattedTimeStr = 12 + ":" + minutes + " AM";
            }
        }
        return formattedTimeStr;
    }
	
	public static void main(String[] args)
	{
	    
		mojo.km.context.ContextManager.currentContext();
        
        JuvenileCasefileAssignmentScheduler sch = new JuvenileCasefileAssignmentScheduler();
		try
		{
			sch.execute();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
