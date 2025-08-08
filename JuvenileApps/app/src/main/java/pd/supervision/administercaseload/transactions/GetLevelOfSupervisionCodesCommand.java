/**
 * 
 */
package pd.supervision.administercaseload.transactions;

import java.util.Iterator;

import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
//import messaging.administercaseload.GetLevelOfSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetLevelOfSupervisionCodesCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		
		//GetLevelOfSupervisionCodesEvent reqEvent = (GetLevelOfSupervisionCodesEvent) event;
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		SupervisionLevelOfServiceCode supervisionLevelOfServiceCode = new SupervisionLevelOfServiceCode();
		
		Iterator iter = supervisionLevelOfServiceCode.findAll();
		while (iter.hasNext())
		{
			SupervisionLevelOfServiceCode supLevOfservCode = (SupervisionLevelOfServiceCode) iter.next();
			CodeResponseEvent codeResponseEvent = new CodeResponseEvent();
			codeResponseEvent.setCode(supLevOfservCode.getOID());
			codeResponseEvent.setDescription(supLevOfservCode.getDescription().toUpperCase());
			dispatch.postEvent(codeResponseEvent);
			
		}

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
