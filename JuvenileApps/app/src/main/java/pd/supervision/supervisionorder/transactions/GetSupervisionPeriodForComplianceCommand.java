//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\GetSupervisionOrdersCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import messaging.supervisionorder.GetSupervisionPeriodForComplianceEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionorder.SupervisionPeriod;

/**
 * @author mchowdhury
 */
public class GetSupervisionPeriodForComplianceCommand implements ICommand {
	
	/**
	 * @roseuid 43B2E782006D
	 */
	public GetSupervisionPeriodForComplianceCommand() {

	}

	/**
	 * @param event
	 * @roseuid 438F22CC0066
	 */
	public void execute(IEvent event) 
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetSupervisionPeriodForComplianceEvent gEvent = (GetSupervisionPeriodForComplianceEvent) event;
        Iterator iter = SupervisionPeriod.findAll(gEvent);
        SupervisionPeriod sp = null;
        while(iter.hasNext()){
            sp = (SupervisionPeriod) iter.next();	
        }
        
        if(sp != null){
        	SupervisionPeriodResponseEvent resp = sp.getResponseEvent();
        	dispatch.postEvent(resp);
        }
	}

	/**
	 * @param event
	 * @roseuid 438F22CC0068
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 438F22CC006A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 43B2E782007D
	 */
	public void update(Object updateObject) {

	}
}
