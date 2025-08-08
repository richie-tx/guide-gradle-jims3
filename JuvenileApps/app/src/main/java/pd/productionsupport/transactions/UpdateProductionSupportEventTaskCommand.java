package pd.productionsupport.transactions;


import messaging.productionsupport.UpdateProductionSupportEventTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;

public class UpdateProductionSupportEventTaskCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportEventTaskCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportEventTask");
	   UpdateProductionSupportEventTaskEvent updateEvent = (UpdateProductionSupportEventTaskEvent) event;
		JuvenileCasefile casefile = null;
		if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		//	casefile = .find(updateEvent.getCasefileId());
			if(updateEvent.getCasefileId() != null && updateEvent.getCasefileId().length() > 0){
				
			}
			if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
			
			}
		}
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02F0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44C8E0DB02FF
    */
   public void update(Object anObject) 
   {
    
   } 
}
