//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetEventTypesCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.SupervisionEventType;

import messaging.supervisionoptions.GetEventTypesEvent;
import messaging.supervisionoptions.reply.EventTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetEventTypesCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44500CB
    */
   public GetEventTypesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C006F
    */
   public void execute(IEvent event) 
   {
		GetEventTypesEvent groupsEvent = (GetEventTypesEvent)event;
		String agencyId = groupsEvent.getAgencyId();
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator iter = SupervisionEventType.findAll( agencyId );
		while ( iter.hasNext() )
		{
			SupervisionEventType type = (SupervisionEventType)iter.next();
			EventTypeResponseEvent evt = new EventTypeResponseEvent();
				
			evt.setEventTypeId( type.getOID().toString() );
			evt.setAgencyId( type.getAgencyId() );
			evt.setName( type.getName() );
			evt.setDescription( type.getDescription() );
				
			dispatch.postEvent( evt );
		}
   }
   
   /**
    * @param event
    * @roseuid 42F7997C0071
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C0073
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997C007D
    */
   public void update(Object anObject) 
   {
    
   }
   
}
