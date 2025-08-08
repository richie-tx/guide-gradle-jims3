/**
 * 
 */
package pd.codetable.transactions;

import java.util.Iterator;
import java.util.List;

import pd.codetable.criminal.JuvenileVOPSanctionCodes;

import messaging.codetable.criminal.reply.JuvenileVOPSanctionCodesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;


/**
 * @author cshimek
 *
 */
public class GetJuvenileVOPSanctionCodesCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator sanctionCodesIter =  JuvenileVOPSanctionCodes.findAll();
		
		List sanctionCodes = CollectionUtil.iteratorToList(sanctionCodesIter);
		JuvenileVOPSanctionCodesResponseEvent response = new JuvenileVOPSanctionCodesResponseEvent();
		if (sanctionCodes.size()  > 0 )
		{
			for ( int z=0; z < sanctionCodes.size(); z ++){
				
				JuvenileVOPSanctionCodes codes = (JuvenileVOPSanctionCodes) sanctionCodes.get(z);
				response = new JuvenileVOPSanctionCodesResponseEvent();
				// get only the active codes. bug fix;21770 part 2
				if(codes!=null && codes.getStatus()!=null && (!codes.getStatus().equalsIgnoreCase("INACTIVE") && !codes.getStatus().equalsIgnoreCase("I"))){ 
					response.setCode(codes.getCode());
					response.setDescription(codes.getDescription());
					response.setLevel(codes.getLevelId());
					response.setSanctionLevel(codes.getSanctionLevelId());
					dispatch.postEvent(response);
				}
			}
		}
	}
}
