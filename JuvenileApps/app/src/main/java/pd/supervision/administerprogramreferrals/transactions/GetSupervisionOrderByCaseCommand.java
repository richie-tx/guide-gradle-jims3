package pd.supervision.administerprogramreferrals.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pd.common.util.StringUtil;
import pd.supervision.supervisionorder.SupervisionOrderCase;
import messaging.administerprogramreferrals.GetSupervisionOrderByCaseEvent;
import messaging.administerprogramreferrals.reply.SupervisionOrderByCaseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * 
 * @author cc_bjangay
 *
 */
public class GetSupervisionOrderByCaseCommand implements ICommand
{

	/**
	 * 
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetSupervisionOrderByCaseEvent reqEvent = (GetSupervisionOrderByCaseEvent)event;
		String criminalCaseId = reqEvent.getCriminalCaseId();
		if(!StringUtil.isEmpty(criminalCaseId))
		{
			Iterator iterator = SupervisionOrderCase.findAll("criminalCaseId", criminalCaseId);
	        Map supOrderMap = new HashMap();   
	        SupervisionOrderCase so = null;
	        String statusId = null;
	        
	        while(iterator.hasNext())
	        {
	        	so = (SupervisionOrderCase) iterator.next();            
	            if(so != null)
	            {
	                statusId = so.getOrderStatusId();
	            	if(statusId != null && ("A".equalsIgnoreCase(statusId) || "I".equalsIgnoreCase(statusId)))
	            	{
		            	if(supOrderMap.containsKey(so.getCriminalCaseId()))
		            	{
		            		if("A".equalsIgnoreCase(statusId))
		            		{
		            			supOrderMap.put(so.getCriminalCaseId(), so);
		            		}
		            		else
		            		{
		            			SupervisionOrderCase existingOrder  =  (SupervisionOrderCase) supOrderMap.get(so.getCriminalCaseId());
		            			if(!"A".equalsIgnoreCase(existingOrder.getOrderStatusId()) && existingOrder.getCreateTimestamp().before(so.getCreateTimestamp()))
		            			{
		            				supOrderMap.put(so.getCriminalCaseId(), so);
		            			}
		            		existingOrder = null;
		            		}
		            	}
		            	else
		            	{
		            		supOrderMap.put(so.getCriminalCaseId(),so);
		            	}  
	            	}
	            }
	        }
	        
	        SupervisionOrderByCaseResponseEvent responseEvt = new SupervisionOrderByCaseResponseEvent();
	        responseEvt.setSupervisionOrderId(((SupervisionOrderCase)supOrderMap.get(criminalCaseId)).getOID());
	        MessageUtil.postReply(responseEvt);
		}
	}

}
