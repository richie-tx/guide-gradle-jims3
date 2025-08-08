package pd.contact.officer.transactions;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import pd.contact.officer.OfficerTraining;
import pd.contact.officer.TrainingTopics;
import messaging.contact.officer.reply.OfficerTrainingResponseEvent;
import messaging.officer.GetOfficerTrainingEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class GetOfficerTrainingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetOfficerTrainingEvent searchEvent = (GetOfficerTrainingEvent) event;
	
	Iterator iter = OfficerTraining.findAll("officerProfileId", searchEvent.getOfficerProfileId());
	
	while(iter.hasNext()){
	    
	    OfficerTraining training = (OfficerTraining) iter.next();
	    OfficerTrainingResponseEvent response = new OfficerTrainingResponseEvent();
	    
	    response.setTrainingTopicsId( training.getTrainingTopicsId());
	    
	    Iterator topics = TrainingTopics.findAll("OID", String.valueOf(training.getTrainingTopicsId()) );
	    if(topics.hasNext()){
		
		TrainingTopics topic = (TrainingTopics) topics.next();
		 response.setTrainingTopicCd(topic.getCode());
		 response.setTrainingDescription(topic.getDescription());
	    }
	    response.setTrainingBeginDate(training.getTrainingBeginDate());
	   // SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    response.setSortOrder(training.getOID());
	    response.setTrainingEndDate(training.getTrainingEndDate());
	    response.setTrainingHours(training.getTrainingHours());
	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    dispatch.postEvent(response);
		    
	}	
	
    }

}
