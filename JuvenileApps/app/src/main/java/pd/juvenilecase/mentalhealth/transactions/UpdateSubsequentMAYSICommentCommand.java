package pd.juvenilecase.mentalhealth.transactions;

import java.util.Date;
import java.util.Iterator;

import pd.juvenilecase.mentalhealth.JuvenileSubMAYSI;
import ui.security.SecurityUIHelper;
import messaging.mentalhealth.UpdateSubsequentMAYSICommentEvent;
import messaging.mentalhealth.reply.MAYSISubAssessResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;

public class UpdateSubsequentMAYSICommentCommand implements ICommand
{
    public UpdateSubsequentMAYSICommentCommand(){}
    
    public void execute(IEvent event)
    {
	UpdateSubsequentMAYSICommentEvent updateEvent = (UpdateSubsequentMAYSICommentEvent) event;
	System.out.println("Sub assessment id: " + updateEvent.getMaysiSubAssessmentId() );
	String maysiSubAssessmentId = updateEvent.getMaysiSubAssessmentId();
	IUserInfo user = SecurityUIHelper.getUser( ) ;
	StringBuilder comments = new StringBuilder();
	comments.append(updateEvent.existingComments);
	comments.append("\n");
	comments.append(updateEvent.newComments);
	comments.append("[" + DateUtil.getCurrentDateString(DateUtil.DATETIME_FMT_1) + " - " + user.getJIMSLogonId() + "]" );
	if ( maysiSubAssessmentId != null 
		&& maysiSubAssessmentId.length() > 0 ) {
	    Iterator<JuvenileSubMAYSI> subMAYSIter = JuvenileSubMAYSI.findAll("OID", maysiSubAssessmentId );
	    while( subMAYSIter.hasNext()) {
		JuvenileSubMAYSI subMaysi = subMAYSIter.next();
		subMaysi.setReviewComments(comments.toString());
		if( subMaysi != null ) {
		    IHome home = new Home();
		    home.bind(subMaysi);
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		    MAYSISubAssessResponseEvent maysiEvent = subMaysi.getResponseEvent();
		    dispatch.postEvent(maysiEvent);
			
		}
		
		
	    }
	}
	
    }

}
