package pd.notification.juvenilewarrant.transactions;


import pd.juvenilewarrant.JuvenileWarrant;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import messaging.juvenilewarrant.SendOICNotificationEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;

/**
 * @author dnikolis
 *
 */
public class SendOICNotificationCommand implements ICommand
{	

	/**
	 * @roseuid 41BDFE9D0340
	 */
	public SendOICNotificationCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41BDFB4003B1
	 */
	public void execute(IEvent event)
	{
		SendOICNotificationEvent notificationEvent = (SendOICNotificationEvent) event;

		JuvenileWarrant warrant = JuvenileWarrant.find(notificationEvent.getWarrantNum());

		switch (notificationEvent.getNotificationType())
		{
			case PDNotificationConstants.OIC_NEEDS_TO_BE_SIGNED :
				sendNotificationNeedsToBeSigned(warrant, notificationEvent);
				break;
			case PDNotificationConstants.OIC_DO_NOT_ISSUE :
				sendNotificationDoNotIssue(warrant, notificationEvent);
				break;
			case PDNotificationConstants.OIC_JUVENILE_IS_WANTED :
				sendNotificationJuvenileIsWanted(warrant, notificationEvent);
				break;
			case PDNotificationConstants.OIC_UPDATES_REQUIRED :
				sendNotificationUpdatesRequired(warrant, notificationEvent);
				break;
			case PDNotificationConstants.OIC_WAS_NOT_SIGNED :
				sendNotificationWasNotSigned(warrant, notificationEvent);
				break;
			default :
				break;
		}
	}
	/**
	 * 
	 * @param warrant
	 * @param event
	 */
	private void sendNotificationDoNotIssue(JuvenileWarrant warrant, SendOICNotificationEvent event)
	{
				
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)EventFactory.getInstance("CreateNotification");
		
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", UNSENT for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
		notificationEvent.setNotificationTopic("JW.OIC.UNSEND");	
		notificationEvent.addContentBean(warrant);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}
	/**
	 * 
	 * @param warrant
	 * @param event
	 */
	private void sendNotificationNeedsToBeSigned(JuvenileWarrant warrant, SendOICNotificationEvent event)
	{
		
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)EventFactory.getInstance("CreateNotification");
		
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", INITIATED for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
		notificationEvent.setNotificationTopic("JW.OIC.INITIATED");	
		notificationEvent.addContentBean(warrant);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}
	/**
	 * 
	 * @param warrant
	 * @param event
	 */
	private void sendNotificationWasNotSigned(JuvenileWarrant warrant, SendOICNotificationEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		if (warrant.getWarrantSignedStatus() != null
			&& warrant.getWarrantSignedStatus().getCode().equals(PDJuvenileWarrantConstants.WARRANT_SIGNED))
		{
			String taskName = event.getTaskName();
			UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
			unRegTaskEvent.setTaskName(taskName);
			dispatch.postEvent(unRegTaskEvent);
		}
		else
		{
				
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)EventFactory.getInstance("CreateNotification");
		
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", NOT SIGNED for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
		notificationEvent.setNotificationTopic("JW.OIC.SIGNATURE.FAILED");	
		notificationEvent.addContentBean(warrant);
		dispatch.postEvent(notificationEvent);
		}
	}

	/**
	* @param warrant
	* @param event
	*/
	private void sendNotificationUpdatesRequired(JuvenileWarrant warrant, SendOICNotificationEvent event)
	{
				
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)EventFactory.getInstance("CreateNotification");
		
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", RETURNED for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
		notificationEvent.setNotificationTopic("JW.OIC.RETURNED");	
		notificationEvent.addContentBean(warrant);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}

	/**
	 * @param warrant
	 * @param event
	 */
	private void sendNotificationJuvenileIsWanted(JuvenileWarrant warrant, SendOICNotificationEvent event)
	{
				
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)EventFactory.getInstance("CreateNotification");
		
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", WANTED for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
		notificationEvent.setNotificationTopic("JW.OIC.WANTED");	
		notificationEvent.addContentBean(warrant);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}

	/**
	 * @param event
	 * @roseuid 41BDFB4003B3
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41BDFB4003BA
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 41BDFB4003BC
	 */
	public void update(Object anObject)
	{

	}
}
