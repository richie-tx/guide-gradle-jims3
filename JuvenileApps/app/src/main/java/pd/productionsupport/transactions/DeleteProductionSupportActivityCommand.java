package pd.productionsupport.transactions;

import messaging.productionsupport.DeleteProductionSupportActivityEvent;
import messaging.productionsupport.DeleteProductionSupportRiskAnalysisEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class DeleteProductionSupportActivityCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportActivityCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportActivityCommand");
    	DeleteProductionSupportActivityEvent deleteActivityEvent = (DeleteProductionSupportActivityEvent) event;   	
    	Activity activity = Activity.find(deleteActivityEvent.getActivityId());
    	if(activity != null){
    		activity.delete();
		}
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }
    
}
