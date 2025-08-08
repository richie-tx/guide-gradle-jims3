package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.GetLocationByJuvLocUnitIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

public class GetLocationByJuvLocUnitIdCommand implements ICommand
{

	public void execute(IEvent event) 
	{
	    GetLocationByJuvLocUnitIdEvent reqEvent = (GetLocationByJuvLocUnitIdEvent) event;
		Iterator iter = JuvLocationUnit.findAll("juvLocUnitId",reqEvent.getJuvLocUnitId());	
		
		while(iter.hasNext()){
		 
		    LocationResponseEvent replyEvent = null;
		    JuvLocationUnit locUnit = (JuvLocationUnit) iter.next();
		    
		    replyEvent = locUnit.getValueObject();		    
		    MessageUtil.postReply(replyEvent);
		
		}
		
	}

	public void onRegister(IEvent event)
	{
		

	}

	public void onUnregister(IEvent event)
	{
		

	}

	public void update(Object updateObject)
	{
		

	}

}
