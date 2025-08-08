//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetTraitParentByCategoryCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetTraitParentByCategoryEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.TraitType;

public class GetTraitParentByCategoryCommand implements ICommand 
{
   
   /**
    * @roseuid 442AE8D10399
    */
   public GetTraitParentByCategoryCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442AE641012A
    */
   public void execute(IEvent event) 
   {
			GetTraitParentByCategoryEvent traitEvent = (GetTraitParentByCategoryEvent) event;
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
    * @roseuid 442AE641012C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442AE6410138
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 442AE641013A
    */
   public void update(Object anObject) 
   {
    
   }
   
}
