/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import messaging.organization.GetOrganizationStructureEvent;
import messaging.organization.reply.OrganizationStructureResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class RetrieveOrganizationStructureCommand 
implements ICommand 
{

	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetOrganizationStructureEvent getOrganizationStructureEvent = (GetOrganizationStructureEvent) event ; 
		OrganizationTO organizationTO = OrganizationService.getOrganizationService().getOrganization( getOrganizationStructureEvent.getOrganizationId() ); 
		OrganizationStructureResponseEvent getOrganizationStructureResponseEvent = new OrganizationStructureResponseEvent() ; 
		getOrganizationStructureResponseEvent.setOrganizationTO( organizationTO ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getOrganizationStructureResponseEvent) ; 

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
