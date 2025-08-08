//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\interviewinfo\\transactions\\GetJuvenileInsuranceCommand.java

package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileHelper;
import pd.juvenile.JuvenileInsurance;

import messaging.interviewinfo.GetJuvenileInsuranceEvent;
import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileInsuranceCommand implements ICommand 
{
   
   /**
    * @roseuid 43F37ACE011B
    */
   public GetJuvenileInsuranceCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 43F371BF03E8
    */
   public void execute(IEvent event) 
   {
		GetJuvenileInsuranceEvent request = (GetJuvenileInsuranceEvent)event;
		Iterator iterator = JuvenileInsurance.findAll("juvenileNum", request.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iterator.hasNext())
		{
			JuvenileInsurance insurance = (JuvenileInsurance) iterator.next();
			if(insurance != null)
			{
				JuvenileInsuranceResponseEvent reply = JuvenileHelper.getJuvenileInsuranceResponseEvent(insurance);
				dispatch.postEvent(reply);
			}
		}		
   }
   
   /**
    * @param event
    * @roseuid 43F371C0000B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371C0000D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 43F371C0000F
    */
   public void update(Object anObject) 
   {
    
   }
   
}
