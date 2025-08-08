//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetJuvenileCasefilePropertyLossDetailsCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Iterator;

import pd.juvenilewarrant.JuvenileOffenderTrackingProperty;


import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingPropertyResponseEvent;
import messaging.juvenilewarrant.GetJuvenileCasefilePropertyLossDetailsEvent;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefilePropertyLossDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 467FB2520216
    */
   public GetJuvenileCasefilePropertyLossDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467FAF3A038E
    */
   public void execute(IEvent event) 
   {
   	GetJuvenileCasefilePropertyLossDetailsEvent loss = (GetJuvenileCasefilePropertyLossDetailsEvent) event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	Iterator properties = JuvenileOffenderTrackingProperty.findAllByDaLogNum(loss.getDaLogNum());
   	while(properties.hasNext())
   	{
   		JuvenileOffenderTrackingProperty property = (JuvenileOffenderTrackingProperty)properties.next();
   		JuvenileOffenderTrackingPropertyResponseEvent resp = new JuvenileOffenderTrackingPropertyResponseEvent(); 
   		resp.setDescription(property.getDescription());
   		String val = property.getValue();
   		Double valWithDecimal = new Double(val);
   	
   		resp.setValue(new Double(valWithDecimal.doubleValue()/100));
   		resp.setDaLogNum(property.getDaLogNum());
   		dispatch.postEvent(resp);
   	}
   }
   
   /**
    * @param event
    * @roseuid 467FAF3A0390
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467FAF3A03AD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 467FAF3A03AF
    */
   public void update(Object anObject) 
   {
    
   }  
 
}
