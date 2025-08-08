/**
 * 
 */
package pd.codetable.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.codetable.GetJuvenileCodeTableChildCodesEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import pd.codetable.criminal.JuvenileCodeTableChildCodes;

/**
 * @author cshimek
 *
 */
public class GetJuvenileCodeTableChildCodesCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetJuvenileCodeTableChildCodesEvent jtce = (GetJuvenileCodeTableChildCodesEvent) event;
		Iterator subCodesIter =  JuvenileCodeTableChildCodes.findAll("codeTableName", jtce.getCodeTableName());
		
		List subCodes = CollectionUtil.iteratorToList(subCodesIter);
		JuvenileCodeTableChildCodesResponseEvent response = new JuvenileCodeTableChildCodesResponseEvent();
		if (subCodes.size()  > 0 )
		{
			for ( int z=0; z < subCodes.size(); z ++){
				
				JuvenileCodeTableChildCodes codes = (JuvenileCodeTableChildCodes) subCodes.get(z);
				response = new JuvenileCodeTableChildCodesResponseEvent();
				response.setCode(codes.getCode());
				response.setDescription(codes.getDescription());
				response.setParentId(codes.getParentId());
				response.setStatus( codes.getStatus() );
				dispatch.postEvent(response);
			}
		}
	}
	
}