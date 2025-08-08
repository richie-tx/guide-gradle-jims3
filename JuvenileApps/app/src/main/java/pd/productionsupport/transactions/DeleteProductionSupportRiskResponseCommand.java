package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.productionsupport.DeleteProductionSupportRiskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.RiskResponse;

public class DeleteProductionSupportRiskResponseCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportRiskResponseCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportRiskResponseCommand");
    	DeleteProductionSupportRiskResponseEvent deleteRiskResponseEvent = (DeleteProductionSupportRiskResponseEvent) event;   	
    	Iterator<RiskResponse> riskResponseIter = RiskResponse.findAll("riskAnalysisId",deleteRiskResponseEvent.getRiskAnalysisId());
    	while(riskResponseIter.hasNext()){
    		RiskResponse response = (RiskResponse)riskResponseIter.next();
    		response.delete();
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
