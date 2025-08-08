/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import java.util.Collection;

import messaging.organization.GetSupervisorsForProgramUnitEvent;
import messaging.organization.reply.GetSupervisorsForProgramUnitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class GetSupervisorsForProgramUnitCommand implements ICommand {

	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetSupervisorsForProgramUnitEvent getSupervisorsForProgramUnitEvent = (GetSupervisorsForProgramUnitEvent) event ; 
		Collection collection  = OrganizationService.getOrganizationService().getSupervisorsForProgramUnit( getSupervisorsForProgramUnitEvent.getProgramUnitId() ); 
		GetSupervisorsForProgramUnitResponseEvent getSupervisorsForProgramUnitResponseEvent = new GetSupervisorsForProgramUnitResponseEvent() ; 
		getSupervisorsForProgramUnitResponseEvent.setCollectionOfSupervisors( collection ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getSupervisorsForProgramUnitResponseEvent) ; 

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
