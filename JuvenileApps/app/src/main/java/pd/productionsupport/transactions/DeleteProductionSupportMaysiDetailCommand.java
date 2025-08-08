package pd.productionsupport.transactions;

import messaging.productionsupport.DeleteProductionSupportMaysiDetailEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;

public class DeleteProductionSupportMaysiDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public DeleteProductionSupportMaysiDetailCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		DeleteProductionSupportMaysiDetailEvent maysiDetailEvent = (DeleteProductionSupportMaysiDetailEvent) event;
		JuvenileMAYSI maysiDetail = JuvenileMAYSI.find(maysiDetailEvent.maysiDetailId);
		maysiDetail.delete();
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
