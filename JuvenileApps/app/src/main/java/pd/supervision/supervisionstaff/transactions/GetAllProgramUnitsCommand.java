package pd.supervision.supervisionstaff.transactions;

import java.util.Iterator;

import pd.supervision.supervisionstaff.Organization;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.supervisionstaff.GetAllProgramUnitsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

public class GetAllProgramUnitsCommand implements ICommand{

	/*
	 * (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
				
		GetAllProgramUnitsEvent request = (GetAllProgramUnitsEvent) event;		
		
		// Find the program Unit
		Iterator programUnitIter = Organization.findAll(request);
		
		while ( programUnitIter.hasNext() ){
			
			Organization org = (Organization) programUnitIter.next(); 
						
			OrganizationResponseEvent response = getResponseEvent( org );
			
			EventManager.getSharedInstance(EventManager.REPLY).postEvent( response );
			
		}
	}
	
	/**
	 * 
	 * @param org
	 * @return
	 */
    private OrganizationResponseEvent getResponseEvent(Organization org) {
	        OrganizationResponseEvent ore = new OrganizationResponseEvent();
	        ore.setDescription(org.getDescription());
	        ore.setOrganizationId(org.getOID().toString());
	        ore.setParentId(org.getParentOrganizationId());
	        return ore;
	       
	 }

}
