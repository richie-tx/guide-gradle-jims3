//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\GetJuvenileDrugsCommand.java

package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileDrugs;
import pd.juvenile.JuvenileHelper;

import messaging.juvenile.GetJuvenileDrugsEvent;
import messaging.juvenile.reply.JuvenileDrugsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileDrugsCommand implements ICommand 
{
   
   /**
    * @roseuid 42B18DC3004E
    */
   public GetJuvenileDrugsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830701D8
    */
   public void execute(IEvent event) 
   {
		GetJuvenileDrugsEvent requestEvent = (GetJuvenileDrugsEvent)event;
		Iterator drugs = JuvenileDrugs.findAll("juvenileId", requestEvent.getJuvenileNum());
		
		//	Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		// Iterate through the warrants and post the JuvenileWarrantServiceResponseEvent for each
		while (drugs.hasNext())
		{
			JuvenileDrugs drug = (JuvenileDrugs) drugs.next();
			JuvenileDrugsResponseEvent drugRespEvent = JuvenileHelper.getJuvenileDrugsResponseEvent(drug);
			if (drugRespEvent != null)
			{
				dispatch.postEvent(drugRespEvent);
			}
		}    
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830701DA
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830701E5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42B1830701E7
    */
   public void update(Object anObject) 
   {
    
   }
   
}
