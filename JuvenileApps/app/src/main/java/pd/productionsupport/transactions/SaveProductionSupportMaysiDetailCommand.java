package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.productionsupport.SaveProductionSupportMaysiDetailEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;

public class SaveProductionSupportMaysiDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public SaveProductionSupportMaysiDetailCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		SaveProductionSupportMaysiDetailEvent maysiDetailEvent = (SaveProductionSupportMaysiDetailEvent) event;
		JuvenileMAYSI maysiDetail = JuvenileMAYSI.find(maysiDetailEvent.maysiDetailId);
		maysiDetail.setReferralNum(maysiDetailEvent.getReferralNumber());
	}
	
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}
