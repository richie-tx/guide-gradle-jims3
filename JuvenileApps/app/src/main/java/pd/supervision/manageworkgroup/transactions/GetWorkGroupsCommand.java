//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageworkgroup\\transactions\\GetWorkGroupsCommand.java

package pd.supervision.manageworkgroup.transactions;

import java.util.Iterator;

import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.manageworkgroup.WorkGroupHelper;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetWorkGroupsCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB612902C9
    */
   public GetWorkGroupsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250248
    */
   public void execute(IEvent event) 
   {
        GetWorkGroupsEvent evt = (GetWorkGroupsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		Iterator wgIter = WorkGroup.findAll( evt );
		while ( wgIter.hasNext() )
		{
		    WorkGroup workGroup = (WorkGroup)wgIter.next();
			WorkGroupResponseEvent reply = WorkGroupHelper.getWorkGroupResponseEvent(workGroup); 
			dispatch.postEvent(reply);
		}
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250256
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250258
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB5B25025A
    */
   public void update(Object anObject) 
   {
    
   }
   
}
