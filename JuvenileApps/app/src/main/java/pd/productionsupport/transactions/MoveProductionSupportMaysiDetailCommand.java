package pd.productionsupport.transactions;

import messaging.productionsupport.MoveProductionSupportMaysiDetailEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;

public class MoveProductionSupportMaysiDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public MoveProductionSupportMaysiDetailCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) {
		
		MoveProductionSupportMaysiDetailEvent maysiDetailEvent = (MoveProductionSupportMaysiDetailEvent) event;
		JuvenileMAYSI maysiDetail = JuvenileMAYSI.find(maysiDetailEvent.maysiDetailId);
		maysiDetail.setJuvenileNumber(maysiDetailEvent.getJuvenileNumber());
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
