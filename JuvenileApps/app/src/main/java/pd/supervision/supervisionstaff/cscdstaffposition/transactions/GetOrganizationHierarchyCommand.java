// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\GetOrganizationsCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;
import java.util.List;

import pd.supervision.supervisionstaff.Organization;
import messaging.cscdstaffposition.GetOrganizationHierarchyEvent;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.cscdstaffposition.GetOrganizationDivisionManagersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;

public class GetOrganizationHierarchyCommand implements ICommand {

	/**
	 * @roseuid 460BFAA203A7
	 */
	public GetOrganizationHierarchyCommand() {
	}

	/**
	 * @param event
	 * @roseuid 460BD54802FE
	 */
	public void execute(IEvent event) {
		GetOrganizationHierarchyEvent reqEvent = ( GetOrganizationHierarchyEvent ) event;
		
		List childList = CollectionUtil.iteratorToList( Organization.findAll( reqEvent ));
		
		GetOrganizationDivisionManagersEvent divMgrEvent = new GetOrganizationDivisionManagersEvent();
		divMgrEvent.setAgencyId(reqEvent.getAgencyId());
		Iterator orgIter = Organization.findAll(divMgrEvent);
		
		if (orgIter != null && orgIter.hasNext()){
		    Organization org = null;
		    OrganizationResponseEvent ore = null;
		    while (orgIter.hasNext()){
		        org = (Organization) orgIter.next();
		        ore = getResponseEvent(org);
		        this.buildChildren(ore, childList);
	            EventManager.getSharedInstance(EventManager.REPLY).postEvent(ore);
		    }
		}
	}

   /**
    * 
    * @param parentOre
    * @param children
    */
    private void buildChildren( OrganizationResponseEvent parentOre, List children) {

        String parentOID = parentOre.getOrganizationId();
        Organization childOrg = null;
        OrganizationResponseEvent childOre = null;
        
        for ( int ctr=0; ctr < children.size();ctr++ ){
        	
        	 childOrg = (Organization) children.get(ctr);
        	 childOre = this.getResponseEvent(childOrg);
        	 if ( parentOID.equals(childOre.getParentId())){
        		 
        		 parentOre.addChild(childOre);
        	 }

        }
    }

/**
     * @param org
     */
    private OrganizationResponseEvent getResponseEvent(Organization org) {
        OrganizationResponseEvent ore = new OrganizationResponseEvent();
        ore.setDescription(org.getDescription());
        ore.setOrganizationId(org.getOID().toString());
        ore.setParentId(org.getParentOrganizationId());
        return ore;
       
    }



	/**
	 * @param event
	 * @roseuid 460BD5480300
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 460BD548030C
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 460BFAA203C6
	 */
	public void update(Object updateObject) {

	}
}
