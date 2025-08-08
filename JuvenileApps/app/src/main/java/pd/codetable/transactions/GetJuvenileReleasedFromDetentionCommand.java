package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.JuvenileReleasedFromDetention;
import messaging.codetable.GetJuvenileReleasedFromDetentionEvent;
import messaging.codetable.criminal.reply.JuvenileReleasedFromResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class GetJuvenileReleasedFromDetentionCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	IHome home = new Home();
	GetJuvenileReleasedFromDetentionEvent request = (GetJuvenileReleasedFromDetentionEvent) event;
	Iterator<JuvenileReleasedFromDetention> i = home.findAll(JuvenileReleasedFromDetention.class);
	
	while(i.hasNext()){
	   
	    JuvenileReleasedFromDetention code = i.next();
	    
	    JuvenileReleasedFromResponseEvent resp = code.valueObject();			
	    dispatch.postEvent(resp);
	}	
    }

}
