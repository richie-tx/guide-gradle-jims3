// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\AssignStaffPositionCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.cscdstaffposition.GetLightDivisionsForAgencyEvent;
import messaging.cscdstaffposition.GetOrganizationDivisionManagersEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionstaff.Organization;

public class GetLightDivisionsForAgencyCommand implements ICommand {

    /**
     * @roseuid 460BFAA20211
     */
    public GetLightDivisionsForAgencyCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54A003F
     */
    public void execute(IEvent event) {
        GetLightDivisionsForAgencyEvent reqEvent = (GetLightDivisionsForAgencyEvent) event;
        GetOrganizationDivisionManagersEvent gEvent = new GetOrganizationDivisionManagersEvent();
        gEvent.setAgencyId(reqEvent.getAgencyId());
        Iterator iterator = Organization.findAll(gEvent);
        SortedMap map = new TreeMap();
        while(iterator.hasNext()){
        	Organization organization = (Organization) iterator.next();  
        	
        	OrganizationTO organizationTO = new OrganizationTO(); 
    	    organizationTO.setOID(organization.getOID());
    	    organizationTO.agencyId = organization.getAgencyId() ; 
    	  	organizationTO.description = organization.getDescription() ; 
    	  	organizationTO.parentOrganizationId = organization.getParentOrganizationId() ; 
    	  	organizationTO.probationOfficerInd = organization.getProbationOfficerInd(); 
    	  	map.put(organization.getDescription(), organizationTO);
        } 
        
        if(!map.isEmpty()){
	        GetDivisionForAgencyResponseEvent getDivisionForAgencyResponseEvent = new GetDivisionForAgencyResponseEvent() ; 
			getDivisionForAgencyResponseEvent.setAgencyDivisionsCollection(map.values()) ;
	   		MessageUtil.postReply(getDivisionForAgencyResponseEvent); 
        }
	}

    /**
     * @param event
     * @roseuid 460BD54A0041
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD54A004E
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 460BD54A0050
     */
    public void update(Object anObject) {

    }
}
