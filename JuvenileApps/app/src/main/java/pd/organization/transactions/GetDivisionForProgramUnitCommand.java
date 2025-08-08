/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import messaging.organization.GetDivisionForProgramUnitEvent;
import messaging.organization.reply.GetDivisionForProgramUnitResponseEvent;
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
public class GetDivisionForProgramUnitCommand 
implements ICommand 
{

	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetDivisionForProgramUnitEvent getDivisionForProgramUnitEvent = (GetDivisionForProgramUnitEvent) event ; 
		OrganizationTO organizationTO  = OrganizationService.getOrganizationService().getDivisionForProgramUnit( getDivisionForProgramUnitEvent.getProgramUnitId() ); 
		GetDivisionForProgramUnitResponseEvent getDivisionForProgramUnitResponseEvent = new GetDivisionForProgramUnitResponseEvent() ; 
		getDivisionForProgramUnitResponseEvent.setOrganizationTO( organizationTO ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getDivisionForProgramUnitResponseEvent) ; 

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
