package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.casefile.GetActivitiesEvent;
import messaging.productionsupport.SaveProductionSupportJuvenileSchoolHistoryEvent;
import messaging.productionsupport.UpdateProductionSupportActivityEvent;
import messaging.productionsupport.UpdateProductionSupportActivityRecordEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenile.JuvenileSchoolHistory;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;

public class UpdateProductionSupportActivityRecordCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportActivityRecordCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("UpdateProductionSupportActivityRecordCommand");
	   UpdateProductionSupportActivityRecordEvent updateEvent = (UpdateProductionSupportActivityRecordEvent) event;
	   Activity act = Activity.find(updateEvent.getActivityId());
	   saveActivity(act, updateEvent);
	   
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
	private void saveActivity(Activity act, UpdateProductionSupportActivityRecordEvent updateEvent){
		if(act != null){			
		    		
			if( !nvl(act.getSupervisionNumber()).trim().equalsIgnoreCase(nvl(updateEvent.getCasefileId()).trim())
				&& updateEvent.getCasefileId() != null ){
			    		act.setSupervisionNumber(updateEvent.getCasefileId());					
			}
			if( !nvl(act.getActivityCodeId()).trim().equalsIgnoreCase(nvl(updateEvent.getActivityCd()).trim())
				&& updateEvent.getActivityCd() != null ){
			    		act.setActivityCodeId(updateEvent.getActivityCd());					
			}
			if( !nvl(act.getComments()).trim().equalsIgnoreCase(nvl(updateEvent.getComments()).trim())
				&& updateEvent.getComments() != null ){
			    		act.setComments(updateEvent.getComments());					
			}
			if( !nvl(act.getUpdateComments()).trim().equalsIgnoreCase(nvl(updateEvent.getUpdateComments()).trim())
				&& updateEvent.getUpdateComments() != null ){
			    		act.setUpdateComments(updateEvent.getUpdateComments());					
			}
			if( !act.getActivityDate().equals(updateEvent.getActivityDate())
				&& updateEvent.getActivityDate() != null ){
			    		act.setActivityDate(updateEvent.getActivityDate());					
			}
			if( !nvl(act.getDetentionId()).trim().equalsIgnoreCase(nvl(updateEvent.getDetentionId()).trim())
				&& updateEvent.getDetentionId() != null ){
			    		act.setDetentionId(updateEvent.getDetentionId());					
			}
			if( !nvl(act.getTime()).trim().equalsIgnoreCase(nvl(updateEvent.getDetentionTime()).trim())
				&& updateEvent.getDetentionTime() != null ){
			    		act.setTime(updateEvent.getDetentionTime());					
			}
			if( !nvl(act.getActivityTime()).trim().equalsIgnoreCase(nvl(updateEvent.getActivityTime()).trim())
				&& updateEvent.getActivityTime() != null ){
			    		act.setActivityTime(updateEvent.getActivityTime());					
			}
			if( !nvl(act.getActivityEndTime()).trim().equalsIgnoreCase(nvl(updateEvent.getActivityendTime()).trim())
				&& updateEvent.getActivityendTime() != null ){
			    		act.setActivityEndTime(updateEvent.getActivityendTime());					
			}
			new Home().bind(act);
	}
}

    String nvl(String value) {
    return (value != null && value.length() > 0) ? value : "";
    }

}
