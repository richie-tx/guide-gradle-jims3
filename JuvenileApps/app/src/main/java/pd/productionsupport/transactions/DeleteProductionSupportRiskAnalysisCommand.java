package pd.productionsupport.transactions;

import messaging.productionsupport.DeleteProductionSupportRiskAnalysisEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class DeleteProductionSupportRiskAnalysisCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportRiskAnalysisCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportRiskAnalysisCommand");
    	DeleteProductionSupportRiskAnalysisEvent deleteRiskResponseEvent = (DeleteProductionSupportRiskAnalysisEvent) event;   	
    	RiskAnalysis riskAnalysis = RiskAnalysis.find(deleteRiskResponseEvent.getRiskAnalysisId());
    	if(riskAnalysis != null){
    		riskAnalysis.delete();
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
