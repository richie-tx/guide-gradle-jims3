/**
 * 
 */
package pd.codetable.transactions;

import java.util.Iterator;
import java.util.List;

import pd.codetable.criminal.JuvenileDSM5Code;
import pd.codetable.criminal.JuvenileRefAssignmentType;

import messaging.codetable.criminal.reply.JuvenileDSM5CodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileRefAssgnmtResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;


/**
 * @author ugopinath
 *
 */
public class GetJuvenileRefAssignmentTypeCommand implements ICommand{

	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator assmntTypesIter =  JuvenileRefAssignmentType.findAll();		
		
		JuvenileRefAssgnmtResponseEvent response = new JuvenileRefAssgnmtResponseEvent();
		while (assmntTypesIter.hasNext() )
		{					
		    JuvenileRefAssignmentType codes = (JuvenileRefAssignmentType) assmntTypesIter.next();
				response = new JuvenileRefAssgnmtResponseEvent();
				// get only the active codes
				if(codes!=null && codes.getStatus()!=null && (!codes.getStatus().equalsIgnoreCase("INACTIVE") && !codes.getStatus().equalsIgnoreCase("I"))){ 
					response.setCode(codes.getCode());
					response.setDescription(codes.getDescription());
					response.setCasefileGenerate(codes.getCasefileGenerate());
					response.setCodeWithDescription(codes.getCode() + "-" + codes.getDescription());
					dispatch.postEvent(response);
				}
			
		}
	}
}
