package pd.organization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.cscdstaffposition.GetOrganizationDivisionManagersEvent;
import messaging.organization.GetProgramUnitsByDivisionIdsEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.transferobjects.OrganizationTO;
import pd.supervision.supervisionstaff.Organization;


public class OrganizationHelper 
{
	public static Collection getDivisionsProgramsForAgency(String agencyCode ) 
	{
		Map parentMap = new HashMap();
        try{
	  	    GetOrganizationDivisionManagersEvent gEvent = new GetOrganizationDivisionManagersEvent();
	        gEvent.setAgencyId(agencyCode);
	        Iterator iterator = Organization.findAll(gEvent);
	        StringBuffer buffer = new StringBuffer();
	        while(iterator.hasNext()){
	        	Organization organization = (Organization) iterator.next();  
	        	
	        	OrganizationTO organizationTO = new OrganizationTO(); 
	    	    organizationTO.setOID(organization.getOID());
	    	    organizationTO.agencyId = organization.getAgencyId() ; 
	    	  	organizationTO.description = organization.getDescription() ; 
	    	  	organizationTO.parentOrganizationId = organization.getParentOrganizationId() ; 
	    	  	organizationTO.probationOfficerInd = organization.getProbationOfficerInd(); 
	    	  	buffer.append(organization.getOID());
	    	  	if(iterator.hasNext()){
	    	  		buffer.append(",");
	    	  	}
	    	  	parentMap.put(organization.getOID(), organizationTO);
	        }   
	        
		    GetProgramUnitsByDivisionIdsEvent pEvent = new GetProgramUnitsByDivisionIdsEvent();
		    pEvent.setDivisionIds(buffer.toString());
		    
		    if(buffer.toString().length() < 1){
		    	return new ArrayList();
		    }
		    
		    Iterator programUnits = Organization.findAll(pEvent);
		    
		    //Iterator divisionIterator = map.values().iterator() ;
		  	Organization organization = null ;
		  	GetProgramUnitResponseEvent organizationTO = null ; 
		  	Map childMaps = new HashMap();
		  	while(programUnits.hasNext())
		  	{
		  		organization = (Organization) programUnits.next() ; 
		  		organizationTO = new GetProgramUnitResponseEvent();
		  		OrganizationTO orgTO = new OrganizationTO(); 
	    	    orgTO.setOID(organization.getOID());
	    	    orgTO.agencyId = organization.getAgencyId() ; 
	    	  	orgTO.description = organization.getDescription() ; 
	    	  	orgTO.parentOrganizationId = organization.getParentOrganizationId() ; 
	    	  	orgTO.probationOfficerInd = organization.getProbationOfficerInd(); 
	    	  	organizationTO.setOrganizationTO(orgTO);
	    	  	OrganizationTO parentOrg = (OrganizationTO) parentMap.get(organization.getParentOrganizationId());
	    	  	if(parentOrg != null){
		    	  	Collection coll = parentOrg.getChildOrganizations();
		    	  	if(coll == null){
		    	  		coll = new ArrayList();
		    	  	}
		    	  	coll.add(organizationTO);
	    	  	}
		  	}
	    }
	    catch (Exception e)
	    {
	  	    e.printStackTrace() ; 
	    }
	    return new ArrayList(parentMap.values()); 
	}
}
