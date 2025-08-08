package pd.productionsupport.transactions;

import naming.UIConstants;
import ui.common.Name;
import pd.juvenilecase.referral.JJSOffense;
import ui.security.SecurityUIHelper;
import messaging.productionsupport.DeleteReferralOffenseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

public class DeleteReferralOffenseCommand implements ICommand 
{
    public DeleteReferralOffenseCommand(){}
    public void execute(IEvent event) 
    {
	DeleteReferralOffenseEvent deleteEvent = (DeleteReferralOffenseEvent) event;
	
	if (deleteEvent.getOffenseId() != null
		&& deleteEvent.getOffenseId().length() > 0 ) {
	    JJSOffense referralOffense = JJSOffense.find(deleteEvent.getOffenseId());
	    
	    if(deleteEvent.getDelComments() != null)
	    {
		referralOffense.setDelComments(deleteEvent.getDelComments());
		new Home().bind(referralOffense);
	    }
	    
	    if (referralOffense != null){
		referralOffense.delete();
		new Home().bind(referralOffense);
		
		StringBuffer message = new StringBuffer();
		
		 IUserInfo user = SecurityUIHelper.getUser();
		 Name userName = new Name( user.getFirstName(), UIConstants.EMPTY_STRING, user.getLastName() );
		 message.append("<p>DELETE BY: " + userName.getFormattedName() + "</p>");
		 message.append("<p>DATE: " + DateUtil.getCurrentDateString( UIConstants.DATETIME24_FMT_1 ) + "</p>");		
		 message.append("<p>REFERRAL OFFENSE ID#:" + deleteEvent.getOffenseId() + "</p>");
		 message.append("<p>COMMENTS #:" + deleteEvent.getDelComments() + "</p>");
		 message.append("<hr>");
		
		
		sendNotification(message.toString());
	    }
	}
	
    }
    
    private void sendNotification(String message){
   	String fromEmail = "jims2notification@itc.hctx.net";
   	SendEmailEvent sendEmailEvent = new SendEmailEvent();
   	sendEmailEvent.setContentType("text/html; charset=utf-8");
   	sendEmailEvent.setSubject("Deleted Referral Offense Records via Production Support");
   	sendEmailEvent.setFromAddress(fromEmail);
   	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
   	//sendEmailEvent.addToAddress("sravanthi.nunna@harriscountytx.gov");
   	//sendEmailEvent.addToAddress("dustin.nguyen@us.hctx.net");
   	sendEmailEvent.setMessage(message);
   	IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
   	dispatch1.postEvent(sendEmailEvent);
   	
       }
}


