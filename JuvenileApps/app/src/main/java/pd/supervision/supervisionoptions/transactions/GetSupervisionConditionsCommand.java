//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetSupervisionConditionsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.List;

import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;


public class GetSupervisionConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C447033C
    */
   public GetSupervisionConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A39013A
    */
   public void execute(IEvent event) 
   {
		GetSupervisionConditionsEvent evt = (GetSupervisionConditionsEvent)event;

		List responses = CommonSupervisionHelper.getSupervisionConditions(evt);
		MessageUtil.postReplies(responses);

   }
   
   /**
    * @param event
    * @roseuid 42F79A39013C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A39013E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A390149
    */
   public void update(Object anObject) 
   {
    
   }   
   
   
}
