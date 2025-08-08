package pd.juvenilecase.casefile.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.GetGangAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.casefile.JuvenileAssessments;

public class GetGangAssessmentReferralCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetGangAssessmentReferralEvent requestEvent = (GetGangAssessmentReferralEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Date fromDate = DateUtil.stringToDate(requestEvent.getFromDateStr(), "MM/dd/yyyy");
	Date toDate = DateUtil.stringToDate(requestEvent.getToDateStr(), "MM/dd/yyyy");
	Iterator gangAssessmentIter = JuvenileAssessments.findAll("assessmentStatus", requestEvent.getAssessmentStatus());
	while (gangAssessmentIter.hasNext())
	{
	    JuvenileAssessments gangAssessment = (JuvenileAssessments) gangAssessmentIter.next();
	    
	    if (requestEvent.getAssessmentStatus() != null && requestEvent.getAssessmentStatus().equalsIgnoreCase("COMPLETED"))
	    {
		List<AssessmentReferralResponseEvent> editedGangAssessmentRespEvtList = new ArrayList<AssessmentReferralResponseEvent>();
		if (gangAssessment.getEntryDate() != null)
		{
		    if (gangAssessment.getEntryDate().compareTo(fromDate) >= 0 && gangAssessment.getEntryDate().compareTo(toDate) <= 0)
		    {
			JJSJuvenile jjsJuvenile = JJSJuvenile.find(gangAssessment.getJuvenileId());
			String juvName = jjsJuvenile.getLastName() + ", " + jjsJuvenile.getFirstName();
			gangAssessment.setJuvenileName(juvName);
			AssessmentReferralResponseEvent gangAssessRespEvent = gangAssessment.getResponseEvent();
			if (gangAssessRespEvent != null)
			{
			    dispatch.postEvent(gangAssessRespEvent);
			}
		    }
		}

	    }
	    else
	    {
		JJSJuvenile jjsJuvenile = JJSJuvenile.find(gangAssessment.getJuvenileId());
		String juvName = jjsJuvenile.getLastName() + ", " + jjsJuvenile.getFirstName();
		gangAssessment.setJuvenileName(juvName);
		AssessmentReferralResponseEvent gangAssessRespEvent = gangAssessment.getResponseEvent();
		if (gangAssessRespEvent != null)
		{
		    dispatch.postEvent(gangAssessRespEvent);
		}
	    }

	}

    }
}


