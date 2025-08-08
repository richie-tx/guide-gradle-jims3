//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetTraitChildByCategoryCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.TraitType;

import messaging.juvenilecase.GetTraitChildByCategoryEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetTraitChildByCategoryCommand implements ICommand 
{
   
   /**
    * @roseuid 442AE8D0035B
    */
   public GetTraitChildByCategoryCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442AE642037C
    */
   public void execute(IEvent event) 
   {
			GetTraitChildByCategoryEvent traitEvent = (GetTraitChildByCategoryEvent) event;
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
			Iterator i = null;
	
			if(traitEvent.getTraitCategoryName() != null)
			{
				i = TraitType.findByType(traitEvent);
					if(i!=null){
						while (i.hasNext())
						{
							TraitType traitType = (TraitType) i.next();
							TraitTypeResponseEvent replyEvent = traitType.getValueObject();
							replyEvent.setTopic(traitType.getParentTypeId());
							dispatch.postEvent(replyEvent);
						}
						return;
					}
			}

			
			return;
   }
   
   /**
    * @param event
    * @roseuid 442AE642038A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442AE642038C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 442AE642038E
    */
   public void update(Object anObject) 
   {
    
   }
   
}
