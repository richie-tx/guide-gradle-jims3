package pd.contact.officer.transactions;

import java.util.Iterator;

import pd.contact.officer.TrainingTopics;
import messaging.contact.officer.reply.TrainingTopicsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetAllTrainingTopicsCommand implements ICommand
{

    @Override
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<TrainingTopics> iter = TrainingTopics.findAll();
	
	while(iter.hasNext()){
	    
	    TrainingTopics topics = (TrainingTopics) iter.next();
	    TrainingTopicsResponseEvent resp = new TrainingTopicsResponseEvent();
	    
	    resp.setTrainingTopicId(Integer.parseInt(topics.getOID()));
	    resp.setCode(topics.getCode());
	    resp.setDescription(topics.getDescription());
	    resp.setStatus(topics.getStatus());
	    resp.setTrainingHours(topics.getTrainingHours()); 
	  
	    dispatch.postEvent(resp);
	    
	}	
	
    }

}
