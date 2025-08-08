package pd.productionsupport.transactions;

import java.util.Calendar;

import messaging.productionsupport.UpdateProductionSupportCasefileEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;

public class UpdateProductionSupportCasefileCommand implements ICommand 
{
   
   /**
    * @roseuid 44CF77170194
    */
   public UpdateProductionSupportCasefileCommand()   
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02EE
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportCasefile");
	   UpdateProductionSupportCasefileEvent updateEvent = (UpdateProductionSupportCasefileEvent) event;
	   JuvenileCasefile casefile = JuvenileCasefile.find(updateEvent.getSupervisionId());
		if(updateEvent.getSupervisionId() != null && !(updateEvent.getSupervisionId().equals(""))) {
			if(updateEvent.getJpoOfficerId() != null && updateEvent.getJpoOfficerId().length() > 0){
				casefile.setProbationOfficerId(updateEvent.getJpoOfficerId());
			}
		}
		casefile.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()) );
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
