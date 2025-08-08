package pd.notification.juvenilewarrant.transactions;

import naming.UIConstants;
import pd.notification.PDNotificationHelper;
import messaging.juvenilewarrant.SendSuccessfulWarrantServiceNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author asrvastava
 *
 * This command is the handler for the succeful warrant service notification
 */
public class SendSuccessfulWarrantServiceNotificationCommand implements ICommand 
{
   
   /**
    * @roseuid 420BB1B7009C
    */
   public SendSuccessfulWarrantServiceNotificationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 420BAF010217
    */
	public void execute(IEvent event) 
	{		
		SendSuccessfulWarrantServiceNotificationEvent requestEvent = (SendSuccessfulWarrantServiceNotificationEvent)event;						
		SendEmailEvent sendEmailEvent = new SendEmailEvent();
		sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());
		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, requestEvent.getEmailTo());
		
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(requestEvent.getWarrantTypeID());
    	buffer.append(" Warrant #");
    	buffer.append(requestEvent.getWarrantNum());
    	buffer.append(", ");
    	buffer.append("EXECUTED SUCCESSFULLY");
    	buffer.append(" for ");
    	buffer.append(requestEvent.getJuvenileNameLastFirstMiddleSuffix());
    	sendEmailEvent.setSubject(buffer.toString());
//		sendEmailEvent.setSubject("Warrant executed successfully");
						
		// Message - invalid address message
		StringBuffer message = new StringBuffer();
		message.append(requestEvent.getWarrantType());
		message.append(" " + requestEvent.getWarrantNum());
		message.append(" has been executed by " + requestEvent.getOfficerFirstName());
		message.append(" " + requestEvent.getOfficerLastName());
		message.append(" " + requestEvent.getAgencyName());
		message.append(" " + requestEvent.getOfficerWorkPhoneString());
		message.append(" regarding juvenile " +	requestEvent.getJuvenileNameFirstMiddleLastSuffix());
//		message.append(" " + requestEvent.getJuvenileLastName());
		message.append(".");
		String arrestDateStamp = DateUtil.dateToString(requestEvent.getArrestTimeStamp(), UIConstants.DATE_FMT_1);
		String arrestTimeStamp = DateUtil.dateToString(requestEvent.getArrestTimeStamp(), UIConstants.TIME24_FMT_1);
		
		message.append(" The juvenile was apprehended on ");
		message.append(arrestDateStamp);
		message.append(" ");
		message.append(arrestTimeStamp);
		message.append(".");
		
		sendEmailEvent.setMessage(message.toString());							
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);    		
	}
   
   /**
    * @param event
    * @roseuid 420BAF010223
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 420BAF010225
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 420BAF010227
    */
   public void update(Object anObject) 
   {
    
   }
   
}
