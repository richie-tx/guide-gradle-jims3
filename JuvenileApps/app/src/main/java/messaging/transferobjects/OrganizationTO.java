/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author cc_mdsouza
 *
 */
public class OrganizationTO
extends PersistentObjectTO 
{

	public AgencyTO agency = null;
	public String agencyId = null ; 
	public Collection childOrganizations = null;
	public String description = null ;
	public CSCDStaffPositionTO manager = null ; 
	public OrganizationTO parentOrganization = null;
	public String parentOrganizationId = null ;
	public String probationOfficerInd = null ;
	
    public Collection staffPositions = null ; 

    public OrganizationTO()
	{
		this.childOrganizations = new ArrayList() ; 
		this.staffPositions = new ArrayList() ; 
	}

    
//	public void initializeTransferObjectFromPersistentObject( PersistentObject persistentObject , boolean getChildren ) 
//	throws Exception 
//	{
//		  try
//		  {
//		  	Organization organization = (Organization)  persistentObject ; 
//		  	this.agencyId = organization.getAgencyId() ; 
//		  	this.description = organization.getDescription() ; 
//		  	this.parentOrganizationId = organization.getParentOrganizationId() ; 
//		  	this.probationOfficerInd = organization.getProbationOfficerInd() ; 
////		  	if (organization.getAgency() != null  && this.agency == null )
////		  	{
////		  		this.agency = new AgencyTO() ; 
////		  		this.agency.initializeTransferObjectFromPersistentObject( organization.getAgency() ) ; 
////		  	}
//		  	if (organization.getManager() != null && this.manager == null )
//		  	{
//		  		this.manager = new CSCDStaffPositionTO() ; 
//		  		this.manager.initializeTransferObjectFromPersistentObject( organization.getManager() ) ;
//		  	}
////		  	if (organization.getParentOrganization() != null && this.parentOrganization == null )
////		  	{
////		  		this.parentOrganization = new OrganizationTO() ; 
////		  		this.parentOrganization.initializeTransferObjectFromPersistentObject(organization.getParentOrganization()) ;
////		  	}
//		  	if (organization.getChildOrganizationsFromService().size() > 0 && this.childOrganizations.size() != organization.getChildOrganizationsFromService().size())
//		  	{
//		  		Iterator organizationsIterator = organization.getChildOrganizationsFromService().iterator() ;
//		  		Organization childOrganization = null ;
//		  		OrganizationTO childOrganizationTO = null ; 
//		  		while ( organizationsIterator.hasNext() ) 
//		  		{
//		  			childOrganization = (Organization)organizationsIterator.next() ; 
//		  			childOrganizationTO = new OrganizationTO() ; 
//	  				childOrganizationTO.initializeTransferObjectFromPersistentObject(organization ) ;
//		  			this.childOrganizations.add(childOrganizationTO) ; 
//		  		}
//		  	}
//		  	if (organization.getStaffPositions().size() > 0 && this.staffPositions.size() != organization.getStaffPositions().size())
//		  	{
//		  		Iterator staffPositionsIterator = organization.getStaffPositions().iterator() ;
//		  		CSCDStaffPosition staffPosition= null ;
//		  		CSCDStaffPositionTO staffPositionTO = null ; 
//		  		while ( staffPositionsIterator.hasNext() ) 
//		  		{
//		  			staffPosition= (CSCDStaffPosition)staffPositionsIterator.next() ; 
//		  			staffPositionTO = new CSCDStaffPositionTO() ; 
//		  			//staffPositionTO.initializeTransferObjectFromPersistentObject(staffPosition) ; 
//		  			this.staffPositions.add(staffPositionTO) ; 
//		  		}
//		  	}
//		  }
//		  catch (Exception e)
//		  {
//		  	e.printStackTrace() ; 
//		  	throw new Exception(e) ; 
//		  }
//	}
//	
//	public void initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
//	throws Exception 
//	{
//	  try
//	  {
//	  	this.initializeTransferObjectFromPersistentObject(persistentObject , true ) ; 
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  	throw new Exception(e) ; 
//	  }
//	} 
	
    
    
	
	
	
	/**
	 * @return Returns the agency.
	 */
	public AgencyTO getAgency() {
		return agency;
	}
	/**
	 * @param agency The agency to set.
	 */
	public void setAgency(AgencyTO agency) {
		this.agency = agency;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the childOrganizations.
	 */
	public Collection getChildOrganizations() {
		return childOrganizations;
	}
	/**
	 * @param childOrganizations The childOrganizations to set.
	 */
	public void setChildOrganizations(Collection childOrganizations) {
		this.childOrganizations = childOrganizations;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the manager.
	 */
	public CSCDStaffPositionTO getManager() {
		return manager;
	}
	/**
	 * @param manager The manager to set.
	 */
	public void setManager(CSCDStaffPositionTO manager) {
		this.manager = manager;
	}
	/**
	 * @return Returns the parentOrganization.
	 */
	public OrganizationTO getParentOrganization() {
		return parentOrganization;
	}
	/**
	 * @param parentOrganization The parentOrganization to set.
	 */
	public void setParentOrganization(OrganizationTO parentOrganization) {
		this.parentOrganization = parentOrganization;
	}
	/**
	 * @return Returns the parentOrganizationId.
	 */
	public String getParentOrganizationId() {
		return parentOrganizationId;
	}
	/**
	 * @param parentOrganizationId The parentOrganizationId to set.
	 */
	public void setParentOrganizationId(String parentOrganizationId) {
		this.parentOrganizationId = parentOrganizationId;
	}
	/**
	 * @return Returns the probationOfficerInd.
	 */
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}
	/**
	 * @param probationOfficerInd The probationOfficerInd to set.
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
	/**
	 * @return Returns the staffPositions.
	 */
	public Collection getStaffPositions() {
		return staffPositions;
	}
	/**
	 * @param staffPositions The staffPositions to set.
	 */
	public void setStaffPositions(Collection staffPositions) {
		this.staffPositions = staffPositions;
	}
}
