package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileReferralCommentEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileReferralEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralByReferralNumberEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;
import pd.juvenilecase.JuvenileCasefile;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.supervision.programreferral.JuvenileEventReferral;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralComment;

public class DeleteProductionSupportJuvenileReferralCommentCommand implements ICommand
{
    /**
     * @roseuid 4278CAAA00AA
     */
    public DeleteProductionSupportJuvenileReferralCommentCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4278C7B80346
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	DeleteProductionSupportJuvenileReferralCommentEvent deleteJuvenileReferralsCommentEvent = (DeleteProductionSupportJuvenileReferralCommentEvent) event;
	if (deleteJuvenileReferralsCommentEvent.getReferralcommentId() != null && deleteJuvenileReferralsCommentEvent.getReferralcommentId().length() > 0)
	{
	    // get the juvenile referral record
	    /*RetrieveJuvenileProgramReferralByReferralNumberEvent requestEvent = new RetrieveJuvenileProgramReferralByReferralNumberEvent();
	    requestEvent.setReferralNum(deleteJuvenileReferralsEvent.getReferralNum());
	    Iterator juvenileProgramReferralIter = JuvenileProgramReferral.findAll(requestEvent);*/
	    JuvenileProgramReferralComment juvenileProgramReferralRecord = null;
	    Iterator referralCommentIter = JuvenileProgramReferralComment.findAll("OID", deleteJuvenileReferralsCommentEvent.getReferralcommentId());
	    while (referralCommentIter!=null && referralCommentIter.hasNext())
	    {
		juvenileProgramReferralRecord = (JuvenileProgramReferralComment) referralCommentIter.next();
		juvenileProgramReferralRecord.delete();
		new Home().bind(juvenileProgramReferralRecord);
	    }

	    
	}

    }

    /**
     * @param event
     * @roseuid 4278C7B8034F
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4278C7B80359
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4278C7B80364
     */
    public void update(Object anObject)
    {

    }
}
