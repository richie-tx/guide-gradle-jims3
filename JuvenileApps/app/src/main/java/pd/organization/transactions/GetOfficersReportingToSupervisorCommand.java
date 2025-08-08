/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import java.util.Collection;

import messaging.organization.GetOfficersReportingToSupervisorEvent;
import messaging.organization.reply.GetOfficersReportingToSupervisorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class GetOfficersReportingToSupervisorCommand 
implements ICommand 
{

	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetOfficersReportingToSupervisorEvent getOfficersReportingToSupervisorEvent = (GetOfficersReportingToSupervisorEvent) event ; 
		Collection collection  = OrganizationService.getOrganizationService().getOfficersReportingToSupervisor( getOfficersReportingToSupervisorEvent.getStaffPositionId() ); 
		GetOfficersReportingToSupervisorResponseEvent getOfficersReportingToSupervisorResponseEvent = new GetOfficersReportingToSupervisorResponseEvent() ; 
		getOfficersReportingToSupervisorResponseEvent.setOfficersReportingToSupervisor( collection ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getOfficersReportingToSupervisorResponseEvent) ; 

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
