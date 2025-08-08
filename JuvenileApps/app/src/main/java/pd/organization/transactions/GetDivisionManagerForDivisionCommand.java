/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import java.util.Collection;

import messaging.organization.GetDivisionManagerForDivisionEvent;
import messaging.organization.reply.GetDivisionManagerForDivisionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class GetDivisionManagerForDivisionCommand 
implements ICommand 
{

	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetDivisionManagerForDivisionEvent getDivisionManagerForDivisionEvent = (GetDivisionManagerForDivisionEvent) event ; 
		Collection collection  = OrganizationService.getOrganizationService().getDivisionManagerForDivision( getDivisionManagerForDivisionEvent.getDivisionId() ); 
		GetDivisionManagerForDivisionResponseEvent getDivisionManagerForDivisionResponseEvent = new GetDivisionManagerForDivisionResponseEvent() ; 
		getDivisionManagerForDivisionResponseEvent.setDivisionManagerCollection( collection ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getDivisionManagerForDivisionResponseEvent) ; 

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
