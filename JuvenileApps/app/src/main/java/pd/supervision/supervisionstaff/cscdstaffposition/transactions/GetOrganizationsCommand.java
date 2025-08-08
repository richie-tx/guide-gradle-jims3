// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\GetOrganizationsCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import pd.supervision.supervisionstaff.Organization;
import messaging.cscdstaffposition.GetOrganizationsEvent;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.cscdstaffposition.GetOrganizationDivisionManagersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

public class GetOrganizationsCommand implements ICommand {

	/**
	 * @roseuid 460BFAA203A7
	 */
	public GetOrganizationsCommand() {
	}

	/**
	 * @param event
	 * @roseuid 460BD54802FE
	 */
	public void execute(IEvent event) {
		GetOrganizationsEvent reqEvent = (GetOrganizationsEvent) event;
		GetOrganizationDivisionManagersEvent divMgrEvent = new GetOrganizationDivisionManagersEvent();
		divMgrEvent.setAgencyId(reqEvent.getAgencyId());
		Iterator orgIter = Organization.findAll(divMgrEvent);
		if (orgIter != null && orgIter.hasNext()){
		    Organization org = null;
		    OrganizationResponseEvent ore = null;
		    while (orgIter.hasNext()){
		        org = (Organization) orgIter.next();
		        ore = getResponseEvent(org);
		        this.processChildren(org, ore);
	            EventManager.getSharedInstance(EventManager.REPLY).postEvent(ore);
		    }
		}

	}

/**
     * @param parentOrg
     * @param parentOre
     */
    private void processChildren(Organization parentOrg, OrganizationResponseEvent parentOre) {
        if (parentOrg.getChildOrganizations() != null && parentOrg.getChildOrganizations().size() > 0){
            boolean childrenExist = true;
            Iterator iter = parentOrg.getChildOrganizations().iterator();
            Organization childOrg = null;
            OrganizationResponseEvent childOre = null;
            while (iter.hasNext()){
                childOrg = (Organization) iter.next();
                childOre = this.getResponseEvent(childOrg);
                parentOre.addChild(childOre);
                this.processChildren(childOrg, childOre);
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
