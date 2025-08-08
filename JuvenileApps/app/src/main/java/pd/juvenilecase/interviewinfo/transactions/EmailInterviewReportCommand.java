package pd.juvenilecase.interviewinfo.transactions;

import pd.juvenilecase.interviewinfo.InterviewDocument;
import messaging.interviewinfo.EmailInterviewReportEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.to.EmailAttachmentBean;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.naming.NotificationControllerSerivceNames;

public class EmailInterviewReportCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public EmailInterviewReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		EmailInterviewReportEvent request = (EmailInterviewReportEvent)event;

		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(
			NotificationControllerSerivceNames.CREATENOTIFICATION );
		
		InterviewDocument doc = InterviewDocument.find( request.getReportId() );
		
		EmailAttachmentBean attachment = new EmailAttachmentBean();
		attachment.setContent( (byte[])doc.getDocument() );
		attachment.setName( doc.getDocumentTypeCode().getDescription() + ".pdf" );
		attachment.setContentType( "application/pdf" );    
		notificationEvent.setAttachment( attachment );

		notificationEvent.setNotificationTopic( "JC.INTERVIEW.SEND" );
		notificationEvent.setSubject( request.getSubject() );
		notificationEvent.addIdentity( "originator", request );
		
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
	}
	
   /**
    * @param event
    * @roseuid 448D7EEE03B4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03BB
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EEE03BD
    */
   public void update(Object anObject) 
   {
    
   }
   
}
