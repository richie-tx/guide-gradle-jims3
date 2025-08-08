package mojo.km.scheduling.transactions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Level;

import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.CreateNotificationEvent;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.PropertyBundleProperties;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.naming.SchedulerConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.scheduling.ISchedule;
import mojo.km.scheduling.Job;
import mojo.km.scheduling.Log;
import mojo.km.scheduling.Process;
import mojo.km.scheduling.SchedulerErrorReortingBean;
import mojo.km.scheduling.Task;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.PrintUtilities;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.CalendarConstants;
import mojo.naming.NotificationControllerSerivceNames;

public class ExecuteTasksCommand implements ICommand
{
	/**
	 * Provides behavior for application requirements. It is executed when event is posted from
	 * requesting context.
	 * 
	 * @param event -
	 *            houses data for command operation.
	 * @exception thrown
	 *                if error in application behavior
	 */
	public void execute(IEvent event) throws RuntimeException, Exception {
		
		Process process = null;
		Timestamp startTime = new Timestamp(DateUtil.getCurrentDate().getTime());
		
		HashMap errorMap = new HashMap();
		Iterator iter = Process.findAll("name", SchedulerConstants.PROCESS_SCHEDULER);
		
		while (iter.hasNext()) {
			process = (Process) iter.next();
			break;
		}
		
		if (process == null || !process.isActive()) {
			LogUtil.log(Level.ERROR, "There is no process in the data store or the process is inactive.");
			System.exit(0);
		}

		Job job = Job.createJob(0, 0, 0, Integer.parseInt(process.getOID()), startTime, null,
				SchedulerConstants.SCHEDULER_CREATOR, null);

		IHome home = new Home();
		Iterator i = null;

		try {
			
			i = home.findAll(event, Task.class);
			
		} catch (Exception e) {
			
			sendErrorEmail(PrintUtilities.getStackTrace(e, null), job);
			return;
			
		}

		if (!i.hasNext())
		{
			return;
		}

		Task task = null;
		int processedCount = 0;
		int errorCount = 0;
		String oid = null;
		String eventName = null;

		while (i.hasNext())
		{
			task = (Task) i.next();
			try
			{
				if (task != null)
				{
					eventName = task.getNotificationEvent().getClass().getName();
					oid = task.getOID();
					LogUtil.log(Level.INFO, "Executing task: " + eventName + ". Task ID: " + oid);
					this.executeTask(task);
					processedCount++;
				}
			}
			catch (mojo.km.context.multidatasource.GenericEventBroadcaster.InternalException e)
			{
				Throwable t = e.getActual();
				String stackTrace = PrintUtilities.getStackTrace(null, t);
				LogUtil.log(Level.ERROR, t);
				Log.createLog(Integer.parseInt(job.getOID()), new Timestamp(DateUtil.getCurrentDate().getTime()), stackTrace,
						"E", SchedulerConstants.SCHEDULER_CREATOR);
				errorCount++;

				if (task != null)
				{
					SchedulerErrorReortingBean bean = new SchedulerErrorReortingBean(task, stackTrace);
					errorMap.put(oid, bean);
					if(!task.getScheduleClassName().equals(CalendarConstants.ALWAYS_SCHEDULE_CLASS)){
						task.setExecuted(Task.ERROR);
					}
					TransactionManager.getInstance().addUpdated(task);
					LogUtil.log(Level.ERROR, "Error executing task: " + eventName + ". Task ID: " + oid);
				}
			}
			catch (Exception e)
			{
				errorCount++;
				String stackTrace = PrintUtilities.getStackTrace(e, null);
				Log.createLog(Integer.parseInt(job.getOID()), new Timestamp(DateUtil.getCurrentDate().getTime()), stackTrace,
						"E", SchedulerConstants.SCHEDULER_CREATOR);
				
				
				if (task != null)
				{
					
					SchedulerErrorReortingBean bean = new SchedulerErrorReortingBean(task, stackTrace);
					errorMap.put(task.getOID(), bean);
					if(!task.getScheduleClassName().equals(CalendarConstants.ALWAYS_SCHEDULE_CLASS)){
						task.setExecuted(Task.ERROR);
					}
					TransactionManager.getInstance().addUpdated(task);
					LogUtil.log(Level.ERROR, "Error executing task: " + eventName + ". Task ID: " + oid);
				}
			}
		}

		if (errorCount != 0)
		{
			Iterator errorMapIter = errorMap.keySet().iterator();
			StringBuilder buffer = new StringBuilder();
			int j = 0;
			while (errorMapIter.hasNext())
			{
				j++;
				String key = (String) errorMapIter.next();
				SchedulerErrorReortingBean bean = (SchedulerErrorReortingBean) errorMap.get(key);
				if (bean != null)
				{
					buffer.append("\n");
					buffer.append("Notice=");
					buffer.append(j);
					buffer.append(" Task Id=");
					buffer.append(bean.getTask().getOID());
					buffer.append(" TaskName=");
					buffer.append(bean.getTask().getTaskName());
					buffer.append("\n");
					buffer.append("ErrorMessage=");
					buffer.append(bean.getErrorMessage());
				}
			}

			// send notification for the TeamLead
			try
			{
				//sendSchedulerNotification(buffer.toString());
				SendEmailEvent sendEmailEvent = new SendEmailEvent();
				StringBuilder subject = new StringBuilder("JIMS2 Scheduler Message  from ");
				subject.append(this.getRegionInfo());
				subject.append(" at ");
				subject.append(new Timestamp(DateUtil.getCurrentDate().getTime()));

				sendEmailEvent.setSubject(subject.toString());
				sendEmailEvent.setMessage(buffer.toString());
				sendEmailEvent.setFromAddress(getPropertyValue("Notification_EmailFrom"));
				sendEmailEvent.addToAddress(getPropertyValue("TeamLead_Email"));

				 CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
	                .getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
	
				 notificationEvent.setNotificationTopic( "SCHEDULER.ERROR" );
				 notificationEvent.setSubject(subject.toString());
				 notificationEvent.addIdentity("identity", (IAddressable) sendEmailEvent);
				 notificationEvent.addContentBean(sendEmailEvent);
				 IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				 dispatch.postEvent(notificationEvent);
			}
			catch (RuntimeException e)
			{
				Log.createLog(Integer.parseInt(job.getOID()), new Timestamp(DateUtil.getCurrentDate().getTime()),
						"Error occured during the Error Notification.", "E", SchedulerConstants.SCHEDULER_CREATOR);
				LogUtil.log(Level.ERROR, e);
			}
			catch (Exception e)
			{
				Log.createLog(Integer.parseInt(job.getOID()), new Timestamp(DateUtil.getCurrentDate().getTime()), e
						.getMessage(), "E", SchedulerConstants.SCHEDULER_CREATOR);
				LogUtil.log(Level.ERROR, e);
			}
		}
		job.setJob(0, processedCount, errorCount, new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * @return
	 */
	private String getRegionInfo()
	{
		String region = "UNKNOWN SERVER";
		ConnectionProperties cProp = MojoProperties.getInstance().getConnectionProperties("M204");
		String url = cProp.getURL();
		if (SchedulerConstants.PRODUCTION_URL.equalsIgnoreCase(url))
		{
			region = "PRODUCTION";
		}
		else if (SchedulerConstants.TEST_URL.equalsIgnoreCase(url))
		{
			region = "TEST";
		}
		else if (SchedulerConstants.QA_URL.equalsIgnoreCase(url))
		{
			region = "QA";
		}
		else if (SchedulerConstants.UAT_URL.equalsIgnoreCase(url))
		{
			region = "UAT";
		}
		else if (SchedulerConstants.TRAINING_URL.equalsIgnoreCase(url))
		{
			region = "TRAINING";
		}
		return region;
	}

	private void sendSchedulerNotification(String message)
	{
		SendEmailEvent sendEmailEvent = new SendEmailEvent();
		StringBuilder subject = new StringBuilder("JIMS2 Scheduler Message from ");
		subject.append(this.getRegionInfo());
		subject.append(" at ");
		subject.append(new Timestamp(DateUtil.getCurrentDate().getTime()));

		sendEmailEvent.setSubject(subject.toString());

		sendEmailEvent.setMessage(message);
		sendEmailEvent.setFromAddress(getPropertyValue("Notification_EmailFrom"));
		sendEmailEvent.addToAddress(getPropertyValue("TeamLead_Email"));

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);
	}

	/**
	 * Use the task notification event state to execute corresponding service.
	 */
	private void executeTask(Task aTask) throws Exception
	{
		Date rightNow = new Date();
		Date nextDate = aTask.getNextNotificationDate();

		if (nextDate.before(rightNow))
		{
			ISchedule taskSchedule = (ISchedule) Class.forName(aTask.getScheduleClassName()).newInstance();

			boolean notify = taskSchedule.isRunDateStillValid(aTask.getNextNotificationDate());

			Date nextRunDate = taskSchedule.getNextRunDate(rightNow);

			if (nextRunDate == null)
			{
				aTask.markCompleted();
			}
			else
			{
				aTask.setNextNotificationDate(nextRunDate);
			}

			if (notify)
			{
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(aTask.getNotificationEvent());
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				try
				{
					MessageUtil.processReturnException(response);
				}
				catch (Exception e)
				{
					LogUtil.log(Level.ERROR, e);
				}
			}
		}
	}

	public static String getPropertyValue(String key)
	{
		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		return propBundle.getProperty(key);
	}

	/**
	 * Upon command registration with context this method will get executed
	 * 
	 * @param event -
	 *            sample event data.
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * Upon command unregistration from context this method will get executed
	 * 
	 * @param event -
	 *            sample event
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * If command requires data before execute is called context will place the in command
	 * 
	 * @param object -
	 *            housing input data
	 */
	public void update(Object object)
	{
	}

	
	private void sendErrorEmail(String error, Job job)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n");
		buffer.append(" The following error occered during the scheduler run for Job Id: ");
		buffer.append(job.getOID());
		buffer.append(" ");
		buffer.append("\n");
		buffer.append(error);

		// send notification for the TeamLead
		try
		{
			sendSchedulerNotification(buffer.toString());
		}
		catch (Exception e)
		{
			LogUtil.log(Level.ERROR, e);
			try
			{
				Log.createLog(Integer.parseInt(job.getOID()), new Timestamp(DateUtil.getCurrentDate().getTime()),
						"Error occured during the Error Notification.", "E", SchedulerConstants.SCHEDULER_CREATOR);
			}
			catch (Exception e1)
			{
				LogUtil.log(Level.ERROR, e1);
			}
		}
	}
	




}
