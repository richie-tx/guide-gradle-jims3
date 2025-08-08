/**
 * 
 */
package pd.juvenilecase.riskanalysis.transactions;

import java.util.Iterator;
import java.util.List;

import pd.juvenilecase.riskanalysis.RiskResultGroup;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;


/**
 * @author cshimek
 *
 */
public class GetRiskResultGroupCodesCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator riskResultGroupCodesIter =  RiskResultGroup.findAll();
		
		List riskResultGroupCodes = CollectionUtil.iteratorToList(riskResultGroupCodesIter);
		RiskResultGroupCodesResponseEvent response = new RiskResultGroupCodesResponseEvent();
		if (riskResultGroupCodes.size()  > 0 )
		{
			for ( int z=0; z < riskResultGroupCodes.size(); z ++){
				
				RiskResultGroup codes = (RiskResultGroup) riskResultGroupCodes.get(z);
				response = new RiskResultGroupCodesResponseEvent();
				response.setCode(codes.getOID());
				response.setDescription(codes.getDisplayDescription());
				dispatch.postEvent(response);
			}
		}
	}
}
