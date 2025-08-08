/**
 * 
 */
package pd.codetable.transactions;

import java.util.Iterator;
import java.util.List;

import pd.codetable.criminal.JuvenileDSM5Code;

import messaging.codetable.criminal.reply.JuvenileDSM5CodesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;


/**
 * @author ugopinath
 *
 */
public class GetJuvenileDSM5CodesWithTJJDIDCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator dsmCodesIter =  JuvenileDSM5Code.findAll();		
		
		JuvenileDSM5CodesResponseEvent response = new JuvenileDSM5CodesResponseEvent();
		while (dsmCodesIter.hasNext() )
		{					
				JuvenileDSM5Code codes = (JuvenileDSM5Code) dsmCodesIter.next();
				response = new JuvenileDSM5CodesResponseEvent();
				// get only the active codes
				if(codes!=null && codes.getStatus()!=null && (!codes.getStatus().equalsIgnoreCase("INACTIVE") && !codes.getStatus().equalsIgnoreCase("I"))){ 
					response.setCode(codes.getCode());
					response.setDescription(codes.getDescription());
					response.setTjjdId(codes.getTjjdDSMId());
					response.setStatus(codes.getStatus());
					dispatch.postEvent(response);
				}
			
		}
	}
}
