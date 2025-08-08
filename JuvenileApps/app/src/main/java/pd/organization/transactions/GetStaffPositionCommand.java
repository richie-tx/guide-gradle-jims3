/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import messaging.organization.GetStaffPositionEvent;
import messaging.organization.reply.GetStaffPositionResponseEvent;
import messaging.transferobjects.CSCDStaffPositionTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class GetStaffPositionCommand 
implements ICommand 
{

	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetStaffPositionEvent getStaffPositionEvent = (GetStaffPositionEvent) event ; 
		CSCDStaffPositionTO cscdStaffPositionTO  = OrganizationService.getOrganizationService().getStaffPosition( getStaffPositionEvent.getStaffPositionId() ); 
		GetStaffPositionResponseEvent getStaffPositionResponseEvent = new GetStaffPositionResponseEvent() ; 
		getStaffPositionResponseEvent.setStaffPosition( cscdStaffPositionTO ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getStaffPositionResponseEvent) ; 

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
