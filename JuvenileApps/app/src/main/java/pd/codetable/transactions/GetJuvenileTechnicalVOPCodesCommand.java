/**
 * 
 */
package pd.codetable.transactions;

import java.util.Iterator;
import java.util.List;

import pd.codetable.criminal.JuvenileTechnicalVOPCodes;


import messaging.codetable.criminal.reply.JuvenileTechnicalVOPCodesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;

/**
 * @author cshimek
 *
 */
public class GetJuvenileTechnicalVOPCodesCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator vopCodesIter =  JuvenileTechnicalVOPCodes.findAll();
		
		List vopCodes = CollectionUtil.iteratorToList(vopCodesIter);
		JuvenileTechnicalVOPCodesResponseEvent response = new JuvenileTechnicalVOPCodesResponseEvent();
		if (vopCodes.size()  > 0 )
		{
			for ( int z=0; z < vopCodes.size(); z ++){
				
				JuvenileTechnicalVOPCodes codes = (JuvenileTechnicalVOPCodes) vopCodes.get(z);
				response = new JuvenileTechnicalVOPCodesResponseEvent();
				response.setCode(codes.getCode());
				response.setDescription(codes.getDescription());
				response.setLevel(codes.getLevelId());
				dispatch.postEvent(response);
			}
		}
	}
}
