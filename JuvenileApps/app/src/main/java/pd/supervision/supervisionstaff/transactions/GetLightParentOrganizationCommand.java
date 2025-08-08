package pd.supervision.supervisionstaff.transactions;

import pd.supervision.supervisionstaff.Organization;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.supervisionstaff.GetLightParentOrganizationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

public class GetLightParentOrganizationCommand implements ICommand{

	/**
	 * 
	 */
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
		
		GetLightParentOrganizationEvent request = (GetLightParentOrganizationEvent) event;
		
		String organizationId = request.getProgramUnitId();
		
		// Find the program Unit
		Organization programUnit = Organization.find(organizationId);
		
		if ( programUnit != null ){
			
			OrganizationResponseEvent response = getResponseEvent( programUnit );
			
			String parentId = response.getParentId();
			// Retrieve the parent org
			Organization parentOrg = Organization.find( parentId );
			
			if ( parentOrg != null ){
				
				OrganizationResponseEvent parentRes = getResponseEvent( parentOrg );
				
				EventManager.getSharedInstance(EventManager.REPLY).postEvent( parentRes );
			}

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
