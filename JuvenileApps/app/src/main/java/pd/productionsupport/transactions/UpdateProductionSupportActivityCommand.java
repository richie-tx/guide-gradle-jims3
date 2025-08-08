package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.casefile.GetActivitiesEvent;
import messaging.productionsupport.UpdateProductionSupportActivityEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

public class UpdateProductionSupportActivityCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportActivityCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportActivity");
	   UpdateProductionSupportActivityEvent updateEvent = (UpdateProductionSupportActivityEvent) event;
	   GetActivitiesEvent getActivityEvents  = new GetActivitiesEvent();
	   getActivityEvents.setCasefileID(updateEvent.getCasefileId());
	   Iterator<Activity> activityIter = Activity.findAll(getActivityEvents);
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {		   
		   while(activityIter.hasNext()){
			   Activity activity = (Activity)activityIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   activity.setSupervisionNumber(updateEvent.getMergeToCasefileId());
			   }
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
