//Source file: C:\\views\\MSA\\app\\src\\pd\\contact\\user\\transactions\\UpdateUserProfileCommand.java

package pd.contact.user.transactions;

import java.util.Calendar;
import java.util.Date;

import messaging.scheduling.RegisterTaskEvent;
import messaging.user.ProcessUserProfileTransferEvent;
import messaging.user.TransferUserProfileEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.UserControllerServiceNames;
import messaging.user.UpdateUserProfileEvent;
import pd.contact.user.UserProfile;


public class TransferUserProfileCommand implements ICommand
{

	/**
	 * 
	 */
	public TransferUserProfileCommand()
	{

	}

	/**
	 * @param event
	 */
	public void execute(IEvent event)
	{
		// cast the event properly
		TransferUserProfileEvent transferEvent = (TransferUserProfileEvent) event;

		ProcessUserProfileTransferEvent processTransferEvent =
			(ProcessUserProfileTransferEvent) EventFactory.getInstance(
				UserControllerServiceNames.PROCESSUSERPROFILETRANSFER);
				
		processTransferEvent.setLogonId(transferEvent.getLogonId());
		processTransferEvent.setNewDepartmentId(transferEvent.getNewDepartmentId());
		
		// get the requested date and time for the transfer
		Date requestedTransferDate = transferEvent.getTransferDate();
		String requestedTransferTime = transferEvent.getTransferTime(); 

		// build a date/time from the passed-in date and time for comparison to
		// the current date/time
		Date transferDate = getTransferDate(requestedTransferDate, requestedTransferTime); 
		Date currentDate = DateUtil.getCurrentDate();
		// make sure that the the requested transfer date has not already passed
		if (transferDate.before(currentDate))
		{
			throw new RuntimeException("Invalid Transfer Date.");
		}
		// save the date / time that the transfer was requested
		processTransferEvent.setRequestDate(currentDate);
		processTransferEvent.setTransferDate(requestedTransferDate);
		processTransferEvent.setTransferTime(requestedTransferTime);

		/*dateUserProfileEvent updateEvent =
					(UpdateUserProfileEvent) EventFactory.getInstance(
						UserControllerServiceNames.UPDATEUSERPROFILE);
		updateEvent.setLogonId(transferEvent.getLogonId());
		updateEvent.setDeptTransferRequestDate(requestedTransferDate);
		updateEvent.setDeptTransferRequestTime(requestedTransferTime);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateEvent);*/
		UserProfile userProfile = UserProfile.find(transferEvent.getLogonId());
		userProfile.setDeptTransferRequestDate(requestedTransferDate);
		userProfile.setDeptTransferRequestTime(requestedTransferTime);
		// post RegisterTask event		
		RegisterTaskEvent rtEvent = new RegisterTaskEvent();
		rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);

		// now set the date to do the transfer
		rtEvent.setFirstNotificationDate(transferDate);
		rtEvent.setNextNotificationDate(transferDate);
		rtEvent.setTaskName(processTransferEvent.getClass().getName() + transferEvent.getLogonId() + transferDate + transferEvent.getNewDepartmentId());
		rtEvent.setNotificationEvent(processTransferEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rtEvent);

	}

	/**
	 * @param event
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 */
	public void update(Object updateObject)
	{

	}

	private Date getTransferDate(Date aDate, String aTime)
	{
		// the time provided will be in the format HH:MM AM/PM
		int hour = new Integer(aTime.substring(0, 2)).intValue();
		if (aTime.endsWith("PM"))
		{
			hour = hour + 12;
		}

		int minute = new Integer(aTime.substring(3, 5)).intValue();

		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(aDate);
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DATE), hour, minute);

		return tempCal.getTime();
	}
}
