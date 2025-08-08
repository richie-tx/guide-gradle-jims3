package pd.productionsupport.transactions;

import pd.juvenile.DrugTesting;
import pd.juvenilecase.casefile.Activity;
import messaging.productionsupport.DeleteProductionSupportDrugTestingInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class DeleteProductionSupportDrugTestingInfoCommand implements ICommand
{
    public DeleteProductionSupportDrugTestingInfoCommand(){}
    
    public void execute(IEvent event)
    {
	System.out.println("DeleteProductionSupportDrugTestingInfo");
	DeleteProductionSupportDrugTestingInfoEvent deleteEvent = (DeleteProductionSupportDrugTestingInfoEvent) event;
	String drugTestingId = deleteEvent.getDrugTestingId();
	if ( drugTestingId != null
		&& drugTestingId.length() > 0 ){
	    DrugTesting drugTestingInfo = DrugTesting.find(drugTestingId );
	    if ( drugTestingInfo != null ) {
		String activityId = drugTestingInfo.getActivityId();
		if ( activityId != null
			&& activityId.length() > 0 ){
		    Activity activity = Activity.find( activityId );
		    if ( activity != null) {
			drugTestingInfo.delete();
			new mojo.km.persistence.Home().bind(drugTestingInfo);
			activity.delete();
		    }		    
		}
		else{
		drugTestingInfo.delete();
		}
	    }
	}
    }
}
