/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.organization.GetBaseStaffResponseEventEvent;
import messaging.organization.reply.GetBaseStaffResponseEventResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class GetBaseStaffResponseEventCommand implements ICommand 
{

	
	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
	
		GetBaseStaffResponseEventEvent baseStaffResponseEvent = (GetBaseStaffResponseEventEvent)event ; 
		CSCDSupervisionStaffResponseEvent staffResponseEvent = OrganizationService.getOrganizationService().getBaseStaffResponseEvent( baseStaffResponseEvent.getStaffPositionTO()) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		GetBaseStaffResponseEventResponseEvent responseEvent = new GetBaseStaffResponseEventResponseEvent() ; 
   		responseEvent.setCscdSupervisionStaffResponseEvent(staffResponseEvent) ; 
   		dispatch.postEvent(responseEvent) ; 
		
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
