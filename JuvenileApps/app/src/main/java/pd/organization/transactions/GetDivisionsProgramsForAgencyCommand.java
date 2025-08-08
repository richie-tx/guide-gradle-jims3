/*
 * Created on Aug 31, 2007
 *
 */
package pd.organization.transactions;

import java.util.Collection;

import messaging.organization.GetDivisionForAgencyEvent;
import messaging.organization.GetDivisionsProgramsForAgencyEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.organization.OrganizationHelper;
import pd.organization.OrganizationService;

/**
 * @author cc_mdsouza
 *
 */
public class GetDivisionsProgramsForAgencyCommand implements ICommand 
{

	
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetDivisionsProgramsForAgencyEvent getDivisionForAgencyEvent = (GetDivisionsProgramsForAgencyEvent) event ; 
		Collection collection = OrganizationHelper.getDivisionsProgramsForAgency(getDivisionForAgencyEvent.getAgencyId()); 
		GetDivisionForAgencyResponseEvent getDivisionForAgencyResponseEvent = new GetDivisionForAgencyResponseEvent() ; 
		getDivisionForAgencyResponseEvent.setAgencyDivisionsCollection( collection ) ;
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(getDivisionForAgencyResponseEvent) ; 

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
