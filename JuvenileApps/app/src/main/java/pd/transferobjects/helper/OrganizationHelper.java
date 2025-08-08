/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import java.util.Iterator;

import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.transferobjects.CSCDStaffPositionTO;
import messaging.transferobjects.OrganizationTO;
import messaging.transferobjects.TransferObjectInterface;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @author cc_mdsouza
 *
 */
public class OrganizationHelper {

	public static OrganizationTO initializeTransferObjectFromPersistentObject( Organization organization) 
	throws Exception 
	{
	  OrganizationTO organizationTO = new OrganizationTO() ; 
	  
	  try
	  {

	    organizationTO.setOID((String)organization.getOID());
	    organizationTO.setSID( organization.getSID());
	    organizationTO.setSTP( organization.getSTP() ) ;
	    organizationTO.setUserID ( organization.getUserID() );
	    organizationTO.setCreateUserID (organization.getCreateUserID()) ;
	    organizationTO.setCreateTimestamp ( organization.getCreateTimestamp());
	    organizationTO.setCreateJIMS2UserID ( organization.getCreateJIMS2UserID());
	    organizationTO.setUpdateUserID ( organization.getUpdateUserID() );
	    organizationTO.setUpdateTimestamp ( organization.getUpdateTimestamp()) ;
	    organizationTO.setUpdateJIMS2UserID ( organization.getUpdateJIMS2UserID());
	    organizationTO.setEXP ( organization.getEXP() );
	    organizationTO.setWorkflowID (organization.getWorkflowID());
	  	
	  	organizationTO.agencyId = organization.getAgencyId() ; 
	  	organizationTO.description = organization.getDescription() ; 
	  	organizationTO.parentOrganizationId = organization.getParentOrganizationId() ; 
	  	organizationTO.probationOfficerInd = organization.getProbationOfficerInd() ; 
//	  	if (organization.getAgency() != null  && organizationTO.agency == null )
//	  	{
//	  		organizationTO.agency = new AgencyTO() ; 
//	  		organizationTO.agency.initializeTransferObjectFromPersistentObject( organization.getAgency() ) ; 
//	  	}
	  	if (organization.getManager() != null && organizationTO.manager == null )
	  	{
	  		organizationTO.manager = new CSCDStaffPositionTO() ; 
	  		//organizationTO.manager.initializeTransferObjectFromPersistentObject( organization.getManager() ) ;
	  		organizationTO.manager = StaffPositionHelper.initializeTransferObjectFromPersistentObject( organization.getManager() ) ; 
	  	}
//	  	if (organization.getParentOrganization() != null && organizationTO.parentOrganization == null )
//	  	{
//	  		organizationTO.parentOrganization = new OrganizationTO() ; 
//	  		organizationTO.parentorganization.initializeTransferObjectFromPersistentObject(organization.getParentOrganization()) ;
//	  	}
	  	if (organization.getChildOrganizationsFromService().size() > 0 && organizationTO.childOrganizations.size() != organization.getChildOrganizationsFromService().size())
	  	{
	  		Iterator organizationsIterator = organization.getChildOrganizationsFromService().iterator() ;
	  		Organization childOrganization = null ;
	  		OrganizationTO childOrganizationTO = null ; 
	  		while ( organizationsIterator.hasNext() ) 
	  		{
	  			childOrganization = (Organization)organizationsIterator.next() ; 
//	  			childOrganizationTO = new OrganizationTO() ; 
//	  			childOrganizationTO.initializeTransferObjectFromPersistentObject(childOrganization) ;
	  			childOrganizationTO = OrganizationHelper.initializeTransferObjectFromPersistentObject( childOrganization ) ; 
	  			organizationTO.childOrganizations.add(childOrganizationTO) ; 
	  		}
	  	}
	  	if (organization.getStaffPositions().size() > 0 && organizationTO.staffPositions.size() != organization.getStaffPositions().size())
	  	{
	  		Iterator staffPositionsIterator = organization.getStaffPositions().iterator() ;
	  		CSCDStaffPosition staffPosition= null ;
	  		CSCDStaffPositionTO staffPositionTO = null ; 
	  		while ( staffPositionsIterator.hasNext() ) 
	  		{
	  			staffPosition= (CSCDStaffPosition)staffPositionsIterator.next() ; 
	  			staffPositionTO = new CSCDStaffPositionTO() ; 
	  			//staffPositionTO.initializeTransferObjectFromPersistentObject(staffPosition) ; 
	  			organizationTO.staffPositions.add(staffPositionTO) ; 
	  		}
	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return organizationTO ; 
	} 
	
	public void putTransferObjectToPersistentObject ( TransferObjectInterface transferObjectInterface ) 
	throws Exception 
	{
	  try
	  {
	  	throw new UnsupportedOperationException() ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	}
	
	/**
	 * Convert program unit object to response event
	 * @return
	 * @throws Exception 
	 */
	public static GetProgramUnitResponseEvent 
					convertProgramUnitToResponseEvent(Organization program_unit) throws Exception
	{
		GetProgramUnitResponseEvent 
			program_unit_reponse = new GetProgramUnitResponseEvent();
		
		program_unit_reponse.setOrganizationTO(
				initializeTransferObjectFromPersistentObject(program_unit));	
			
		return program_unit_reponse;
	}
}