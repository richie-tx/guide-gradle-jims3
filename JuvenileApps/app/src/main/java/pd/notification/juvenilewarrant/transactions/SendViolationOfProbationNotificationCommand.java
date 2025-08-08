/*
 * Created on Feb 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.notification.juvenilewarrant.transactions;

import naming.PDNotificationConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import messaging.juvenilewarrant.SendViolationOfProbationNotificationEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class SendViolationOfProbationNotificationCommand implements ICommand
{
	private static final char BLANK = ' ';
	private static final char PERIOD = '.';

	/**
	 * @param event
	 */
	public void execute(IEvent event) throws Exception
	{
		SendViolationOfProbationNotificationEvent notificationEvent = (SendViolationOfProbationNotificationEvent) event;

		String warrantNum = notificationEvent.getWarrantNum();

		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);

		this.processEvent(warrant, notificationEvent);
	}
	/**
	 * 
	 * @param notificationEvent
	 */
	private void processEvent(JuvenileWarrant warrant, SendViolationOfProbationNotificationEvent notificationEvent)
	{
		switch (notificationEvent.getNotificationType())
		{
			case PDNotificationConstants.VOP_FILED :
				this.processVOPFiled(warrant, notificationEvent);
				break;
			case PDNotificationConstants.VOP_ACTIVATION :
				this.processVOPActivation(warrant, notificationEvent);
				break;
			case PDNotificationConstants.VOP_REJECTION :
				this.processVOPRejection(warrant, notificationEvent);
				break;
			case PDNotificationConstants.VOP_UNSEND :
				this.processVOPRejection(warrant, notificationEvent);
			default :
				break;
		}
	}

	/**
	 * @param notificationEvent
	 */
	private void processVOPActivation(JuvenileWarrant warrant, SendViolationOfProbationNotificationEvent notificationEvent)
	{		
		this.sendEmail(warrant, notificationEvent);	
	}

	/**
	 * @param notificationEvent
	 */
	private void processVOPFiled(JuvenileWarrant warrant, SendViolationOfProbationNotificationEvent notificationEvent)
	{
		//Unregister VOP filed if warrant has been activated or rejected
		if ((warrant.getWarrantActivationDate() != null)
			|| ((warrant.getUnsendNotSignedReason() != null) && (!warrant.getUnsendNotSignedReason().equals(""))))
		{
			this.unregisterEvent(notificationEvent.getTaskName());
		}
		else
		{
			this.sendEmail(warrant, notificationEvent);
		}
	}

	/**
	 * @param notificationEvent
	 */
	private void processVOPRejection(JuvenileWarrant warrant, SendViolationOfProbationNotificationEvent notificationEvent)
	{
		//Unregister Rejection if VOP has been activated and has not been inactivated
		if (warrant.getWarrantActivationDate() != null)
		{
			this.unregisterEvent(notificationEvent.getTaskName());
		}
		else
		{
			this.sendEmail(warrant, notificationEvent);
		}

	}

	/**
	 * @param taskName
	 */
	private void unregisterEvent(String taskName)
	{
		IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
		unRegTaskEvent.setTaskName(taskName);
		requestDispatch.postEvent(unRegTaskEvent);
	}

	/**
	 * @param notificationEvent
	 */
	private void sendEmail(JuvenileWarrant warrant, SendViolationOfProbationNotificationEvent notificationEvent)
	{
		SendEmailEvent sendEmailEvent = new SendEmailEvent();

		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, notificationEvent.getEmailTo());

		String message = createEmailMessage(warrant, notificationEvent);

		sendEmailEvent.setMessage(message);

		StringBuffer buffer = new StringBuffer(100);
     	buffer.append(warrant.getWarrantTypeId());
     	buffer.append(" Warrant #");
     	buffer.append(warrant.getWarrantNum());
     	buffer.append(", ");
     	buffer.append(notificationEvent.getNotificationType());
     	buffer.append(" for ");
     	buffer.append(warrant.getNameLastFirstMiddleSuffix());
     	sendEmailEvent.setSubject(buffer.toString());
		sendEmailEvent.setFromAddress(notificationEvent.getEmailFrom());		
		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, notificationEvent.getEmailTo());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);
	}

	/**
	 * @param notificationEvent
	 * @return message
	 */
	public String createEmailMessage(JuvenileWarrant warrant, SendViolationOfProbationNotificationEvent notificationEvent)
	{
		String message = null;
		switch (notificationEvent.getNotificationType())
		{
			case PDNotificationConstants.VOP_FILED :
				message = this.createVOPFiledMessage(warrant);
				break;
				
			case PDNotificationConstants.VOP_ACTIVATION :
				message = this.createVOPActivateMessage(warrant);
				break;
				
			case PDNotificationConstants.VOP_REJECTION :
				message = this.createVOPRejectionMessage(warrant);
				break;
				
			case PDNotificationConstants.VOP_UNSEND :
				message = this.createVOPUnsendMessage(warrant);
				break; 
		}
		return message;
	}
	/**
	 * @param warrant
	 * @return
	 */
	private String createVOPUnsendMessage(JuvenileWarrant warrant)
	{
		//The Violation of Probation warrant [warrant number] has been rejected for [juvenile first name] [juvenile middle name] 
		//[juvenile last name] [juvenile suffix] [(juvenile number)] because [usendnotsignreason].
		StringBuffer message = new StringBuffer("The Violation of Probation warrant ");
		message.append(warrant.getWarrantNum());
		message.append(" has been returned for ");
		message.append(warrant.getFirstName());
		if ((warrant.getMiddleName() != null) && (!warrant.getMiddleName().equals("")))
		{
			message.append(BLANK);
			message.append(warrant.getMiddleName());
		}
		message.append(BLANK);
		message.append(warrant.getLastName());
		message.append("(");
		message.append(warrant.getJuvenileNum());
		message.append(") because ");
		message.append(warrant.getUnsendNotSignedReason());
		
		if(warrant.getUnsendNotSignedReason() != null && warrant.getUnsendNotSignedReason().length() > 0)
		{
			char lastChar = warrant.getUnsendNotSignedReason().charAt(warrant.getUnsendNotSignedReason().length()-1);
			
			if(lastChar != PERIOD)
			{
				message.append(PERIOD);
			}
		}
		return message.toString();
	}
	/**
	 * @param notificationEvent
	 * @return createVOPActivateMessage
	 */
	private String createVOPActivateMessage(JuvenileWarrant warrant)
	{
		//The Violation of Probation warrant [warrant number] has been activated [warrantactivationdate] [warrantactivationtime] 
		//for [juvenile first name] [juvenile middle name] [juvenile last name] [juvenile suffix] [(juvenile number)].
		StringBuffer message = new StringBuffer();
		
		message.append("The Violation of Probation warrant ");
		message.append(warrant.getWarrantNum());
		message.append(" has been activated on ");
		if (warrant.getWarrantActivationDate() != null)
		{
			message.append(DateUtil.dateToString(warrant.getWarrantActivationDate(), "MM/dd/yyyy"));
			message.append(" at ");
			message.append(DateUtil.dateToString(warrant.getWarrantActivationDate(), "hh:mm:ss"));
		}
		message.append(" for ");
		message.append(warrant.getFirstName());
		if ((warrant.getMiddleName() != null) && (!warrant.getMiddleName().equals("")))
		{
			message.append(BLANK);
			message.append(warrant.getMiddleName());
		}
		message.append(BLANK);
		message.append(warrant.getLastName());
		message.append("(");
		message.append(warrant.getJuvenileNum());
		message.append(").");
		return message.toString();
	}
	/**
	 * @param notificationEvent
	 * @return message
	 */
	private String createVOPFiledMessage(JuvenileWarrant warrant)
	{
		//A Violation of Probation warrant [Warrant number] is now ready and needs to be activated.
		//The Violation of Probation warrant was created on [filestamp date] at [filestamp time] for [Juvenile first name] [juvenile middle name] 
		//[juvenile last name] [juvenile suffix] [(juvenile number)].   Please review VOP documents for activation.
		StringBuffer message = new StringBuffer("A Violation of Probation warrant ");

		message.append(warrant.getWarrantNum());
		message.append(" is now ready and needs to be activated.  ");
		message.append("The Violation of Probation warrant was created on ");
		if (warrant.getFileStampDate() != null)
		{
			message.append(DateUtil.dateToString(warrant.getFileStampDate(), "MM/dd/yyyy"));
			message.append(" at ");
			message.append(DateUtil.dateToString(warrant.getFileStampDate(), "hh:mm:ss"));
		}
		message.append(" for ");
		message.append(warrant.getFirstName());
		if ((warrant.getMiddleName() != null) && (!warrant.getMiddleName().equals("")))
		{
			message.append(BLANK);
			message.append(warrant.getMiddleName());
		}
		message.append(BLANK);
		message.append(warrant.getLastName());
		message.append("(");
		message.append(warrant.getJuvenileNum());
		message.append(")");
		message.append(PERIOD);
		message.append(BLANK);
		message.append("Please review VOP documents for activation.");

		return message.toString();
	}
	/**
	 * @param notificationEvent
	 * @return message
	 */
	private String createVOPRejectionMessage(JuvenileWarrant warrant)
	{
		//The Violation of Probation warrant [warrant number] has been rejected for [juvenile first name] [juvenile middle name] 
		//[juvenile last name] [juvenile suffix] [(juvenile number)] because [usendnotsignreason].
		StringBuffer message = new StringBuffer("The Violation of Probation warrant ");
		message.append(warrant.getWarrantNum());
		message.append(" has been rejected for ");
		message.append(warrant.getFirstName());
		if ((warrant.getMiddleName() != null) && (!warrant.getMiddleName().equals("")))
		{
			message.append(BLANK);
			message.append(warrant.getMiddleName());
		}
		message.append(BLANK);
		message.append(warrant.getLastName());
		message.append("(");
		message.append(warrant.getJuvenileNum());
		message.append(") because ");
		message.append(warrant.getUnsendNotSignedReason());
		message.append(PERIOD);

		return message.toString();
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
	 * 
	 */

	/**
	 * @param event
	 */
	public void update(Object updateObject)
	{
	}

}
