package pd.supervision.supervisionorder.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionorder.SupervisionOrderCase;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSuperviseeCaseOrdersCommand implements ICommand
{

    /**
     * @roseuid 4643602E010A
     */
    public GetSuperviseeCaseOrdersCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464342310396
     */
    public void execute(IEvent anEvent)
    {
    	GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) anEvent;  
        Iterator iter = SupervisionOrderCase.findAll(event);
        Map supOrderMap = new HashMap();       
        SupervisionOrderCase so = null;
        while(iter.hasNext()){
        	so = (SupervisionOrderCase) iter.next();            
        	if(!supOrderMap.containsKey(so.getCriminalCaseId() + so.getOID())){
        		supOrderMap.put(so.getCriminalCaseId() + so.getOID(), so);
        	}
        } 
        
        iter = supOrderMap.values().iterator();
        supOrderMap = new HashMap();  
        String statusId = null;
        while(iter.hasNext()){
        	so = (SupervisionOrderCase) iter.next();            
            if(so != null){
                statusId = so.getOrderStatusId();
            	if(statusId != null && ("A".equalsIgnoreCase(statusId) || "I".equalsIgnoreCase(statusId))){
	            	if(supOrderMap.containsKey(so.getCriminalCaseId())){
	            		if("A".equalsIgnoreCase(statusId)){
	            			supOrderMap.put(so.getCriminalCaseId(), so);
	            		}else{
	            			SupervisionOrderCase existingOrder  =  (SupervisionOrderCase) supOrderMap.get(so.getCriminalCaseId());
	            			if(!"A".equalsIgnoreCase(existingOrder.getOrderStatusId()) && existingOrder.getCreateTimestamp().before(so.getCreateTimestamp())){
	            				supOrderMap.put(so.getCriminalCaseId(), so);
	            			}
	            			existingOrder = null;
	            		}
	            	}else{
	            		supOrderMap.put(so.getCriminalCaseId(),so);
	            	}  
            	}
            }
        } 
        
/*        Iterator iterator = supOrderMap.values().iterator();
        Map map = new HashMap();
        List list = new ArrayList();
        while(iterator.hasNext()){
        	so = (SupervisionOrder) iterator.next();
        	if(!map.containsKey(so.getCriminalCaseId()) && (so.getCriminalCaseId() != null && !"".equals(so.getCriminalCaseId()))){
				map.put(so.getCriminalCaseId(), so.getCriminalCaseId());
			}
        	list.add(so);
        }       
        
        Iterator mapIter = map.values().iterator();
		StringBuffer crm = new StringBuffer();
		while(mapIter.hasNext()){
			String criminalCaseId = (String) map.get(mapIter.next());
			crm.append("'");
			crm.append(criminalCaseId);
			crm.append("'");
			if(mapIter.hasNext()){
				crm.append(",");
			}
		}	
		
		if(crm.toString().length() < 1){
			return;
		}
		
		Map casesMap = CriminalCase.getCriminalCasesByIds(crm.toString());*/
        
        iter = supOrderMap.values().iterator();
        SuperviseeCaseOrderResponseEvent resp = null;
		while(iter.hasNext()){
        	so = (SupervisionOrderCase) iter.next();
        	resp = so.getSuperviseeCaseOrder();                                    
            MessageUtil.postReply(resp);
        }         
        
        // garbage collection
        supOrderMap = null;
        iter = null;
        event = null;
        so = null;
        statusId = null;
        resp = null;
    }

    /**
     * @param event
     * @roseuid 4643423103C3
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4643423103D2
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4643423103D4
     */
    public void update(Object anObject)
    {

    }
}
