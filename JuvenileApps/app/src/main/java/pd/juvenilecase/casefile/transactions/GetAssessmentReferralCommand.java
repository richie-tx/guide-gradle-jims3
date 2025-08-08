package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import messaging.casefile.GetAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.JuvenileAssessments;

public class GetAssessmentReferralCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		GetAssessmentReferralEvent requestEvent = (GetAssessmentReferralEvent)event;
		//Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(requestEvent.getAssessmentId()!=null){
			JuvenileAssessments gangAssessment = JuvenileAssessments.find(requestEvent.getAssessmentId());
			AssessmentReferralResponseEvent gangAssessRespEvent = gangAssessment.getResponseEvent();
			if (gangAssessRespEvent != null)
			{
				dispatch.postEvent(gangAssessRespEvent);
			}
		}else{
		
		//call the service to get from JCASSESSMENTS TABLE for the corresponding juvenileId.
		Iterator gangAssessments = JuvenileAssessments.findAll("juvenileId", requestEvent.getJuvenileId());
		
		//For each gangAssess, set the response.
		while (gangAssessments.hasNext())
		{
			JuvenileAssessments assessment = (JuvenileAssessments) gangAssessments.next();
			AssessmentReferralResponseEvent gangAssessRespEvent = assessment.getResponseEvent();
			if (gangAssessRespEvent != null)
			{
				dispatch.postEvent(gangAssessRespEvent);
			}
		}    
	}
	}

}
