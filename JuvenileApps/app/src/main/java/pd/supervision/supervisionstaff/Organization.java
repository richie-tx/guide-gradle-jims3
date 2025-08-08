package pd.supervision.supervisionstaff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.agency.Agency;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @roseuid 460C064B008E
 */
public class Organization extends PersistentObject
{
	
	/**
	 * @roseuid 46096B6901BB
	 */
	static public Iterator findAll(IEvent anEvent)
	{
		IHome home = new Home();
		return home.findAll(anEvent, Organization.class);
	}
	static public Iterator findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    return home.findAll(attrName, attrValue, Organization.class);
	}
	static public Organization find(String anOid){
	    IHome home = new Home();
	    return (Organization) home.find(anOid, Organization.class);
	}
	/**
	 * Properties for agency
	 */
	private Agency agency = null;
	private String agencyId;
	/**
	 * Properties for childOrganizations
	 * @referencedType pd.supervision.supervisionstaff.Organization
	 * @detailerDoNotGenerate true
	 * @associationType simple
	 */
	private Collection childOrganizations = null;
	/**
	 * Properties for childOrganizationsFromService
	 * @referencedType pd.supervision.supervisionstaff.Organization
	 * @detailerDoNotGenerate true
	 * @associationType simple
	 */
	private Collection childOrganizationsFromService = null;
	private String description;
	private String legacyProgramUnit;
	private CSCDStaffPosition manager = null ; 
	/**
	 * Properties for parentOrganization
	 */
	private Organization parentOrganization = null;
	private String parentOrganizationId;
	private String probationOfficerInd;
    // transient collection - built on initialize of Organization service and depends on link CSStaffPosition.organizationId
	/**
	 * Properties for staffPositions
	 * @referencedType pd.supervision.supervisionstaff.Organization
	 * @detailerDoNotGenerate true
	 * @associationType simple
	 */
    private Collection staffPositions = null ; 
    
    private String stateReporting;

	/**
	 * @roseuid 460C064B008E
	 */
	public Organization()
	{
		this.childOrganizations = new ArrayList() ; 
		this.staffPositions = new ArrayList() ; 
		this.childOrganizationsFromService = new ArrayList() ; 
	}
	
	/** 
	 * Used to build hierarchy of organizations within organization service with no persistence 
	 * @author cc_mdsouza
	 *
	 */
	public void addChildOrganization(Organization organization )
	{
	  	this.childOrganizationsFromService.add(organization) ; 
	}
	
	/** 
	 * Used to build hierarchy of organizations within organization service with no persistence 
	 * @author cc_mdsouza
	 *
	 */
	public void addStaffPosition(CSCDStaffPosition staffPosition )
	{
	  	this.staffPositions.add(staffPosition) ; 
	}

	/**
	 * Clears all pd.supervision.supervisionstaff.Organization from class relationship collection.
	 */
	public void clearChildOrganizations()
	{
		initChildOrganizations();
		childOrganizations.clear();
	}
	
	
	// used by Organization Service to set the organization manager object from staff positions assigned to organization unit
	public void findOrganizationManager(String agencyId)
	{
//        SupervisionCode code = PDSupervisionCodeHelper.getSupervisionCodeByValue(
//        		PDSecurityHelper.getUserAgencyId(), 
//				PDCodeTableConstants.STAFF_POSITION_TYPE, 
//				PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR
//				);
        SupervisionCode code = PDSupervisionCodeHelper.getSupervisionCodeByValue(
        		agencyId, 
				PDCodeTableConstants.STAFF_POSITION_TYPE, 
				PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR
				);

        String divisionManagerCodeAsString = (String)code.getOID() ; 
	  	
	  	List consolidatedStaffPositionList = new ArrayList() ;
	  	consolidatedStaffPositionList.addAll(staffPositions) ; 
	  	List childOrganizationList = (ArrayList)this.getChildOrganizationsFromService() ;
	  	Iterator childIterator = childOrganizationList.iterator() ; 
	  	Organization organization = null ; 
	  	while (childIterator.hasNext() )
	  	{
	  		organization = (Organization)childIterator.next() ;
		  	consolidatedStaffPositionList.addAll(organization.getStaffPositions() ) ; 
	  	}
	  	Iterator staffPositionIterator = consolidatedStaffPositionList.iterator() ;
	  	CSCDStaffPosition iteratedStaffPosition = null ;
	  	String cstsOfficerTypeIdString = null ;
	  	String positionId = null ; 
//	  	System.out.println("Staff Positions : ");
	  	while (staffPositionIterator.hasNext() )
	  	{
	  		iteratedStaffPosition = (CSCDStaffPosition)staffPositionIterator.next() ;
	  		cstsOfficerTypeIdString = iteratedStaffPosition.getCstsOfficerTypeId() ; 
	  		positionId = iteratedStaffPosition.getPositionTypeId() ; 
//	  		System.out.println("Officer Type : " + cstsOfficerTypeIdString);
//	  		System.out.println("Position Type : " + positionId );
//		  	if(iteratedStaffPosition.getCstsOfficerTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR))
//		  	{	  
//		  		this.manager = iteratedStaffPosition ;
//		  		break ; 
//		  	}
//		  	if(iteratedStaffPosition.getPositionTypeId().equals("280"))
		  	if(iteratedStaffPosition.getPositionTypeId().equals(divisionManagerCodeAsString))
	  		{	  
		  		this.manager = iteratedStaffPosition ;
//		  		System.out.println("Added manager !"  );
		  		break ; 
		  	}
	  	}

	}

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 */
	public Agency getAgency()
	{
		fetch();
		initAgency();
		return agency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * returns a collection of pd.supervision.supervisionstaff.Organization
	 */
	public Collection getChildOrganizations()
	{
		initChildOrganizations();
		ArrayList retVal = new ArrayList();
		Iterator i = childOrganizations.iterator();
		while (i.hasNext())
		{
//			pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization actual = (pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization) i
//					.next();
//			retVal.add(actual.getChild());
			
		    Organization actual = (Organization) i
			.next();
			
			retVal.add(actual);
		}
		return retVal;
	}
	
	public Collection getChildOrganizationsFromService()
	{
	  return this.childOrganizationsFromService ; 
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}
	
	
	
	/**
	 * @return Returns the manager.
	 * 
	 * 
	 */
	public CSCDStaffPosition getManager() {
		return manager;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.Organization
	 */
	public Organization getParentOrganization()
	{
		fetch();
		initParentOrganization();
		return parentOrganization;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionstaff.Organization
	 */
	public String getParentOrganizationId()
	{
		fetch();
		return parentOrganizationId;
	}
	
    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd() {
		fetch();        
		return probationOfficerInd;
    }
	
	
	
	
	/**
	 * @return Returns the staffPositions.
	 */
	public Collection getStaffPositions() {
		return staffPositions;
	}

	/**
	 * 
	 * @return
	 */
	public String getStateReporting() {
		
    	fetch();
    	return stateReporting;
	}

	/**
	 * 
	 * @param aStateReporting
	 */
	public void setStateReporting(String aStateReporting) {
		
		if (this.stateReporting == null || !this.stateReporting.equals(aStateReporting))
		{
			markModified();
		}
		
		this.stateReporting = aStateReporting;
	}
	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initAgency()
	{
		if (agency == null)
		{
			agency = (Agency) new mojo.km.persistence.Reference(agencyId,
					Agency.class).getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.supervisionstaff.Organization
	 */
	private void initChildOrganizations()
	{
		if (childOrganizations == null || childOrganizations.size() == 0 )
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
//			childOrganizations = new mojo.km.persistence.ArrayList(
//					pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization.class, "parentId", ""
//							+ getOID());
			childOrganizations = new mojo.km.persistence.ArrayList(
					Organization.class, "parentOrganizationId", ""
							+ getOID());
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionstaff.Organization
	 */
	private void initParentOrganization()
	{
		if (parentOrganization == null)
		{
			parentOrganization = (Organization) new mojo.km.persistence.Reference(
					parentOrganizationId, Organization.class).getObject();
		}
	}

	/**
	 * insert a pd.supervision.supervisionstaff.Organization into class relationship collection.
	 */
//	public void insertChildOrganizations(pd.supervision.supervisionstaff.Organization anObject)
//	{
//		initChildOrganizations();
//		//pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization actual = new pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization();
//		if (this.getOID() == null)
//		{
//			new Home().bind(this);
//		}
//		if (anObject.getOID() == null)
//		{
//			new Home().bind(anObject);
//		}
//		actual.setChild(anObject);
//		actual.setParent(this);
//
//		childOrganizations.add(anObject);
//	}

	
	public void removeChildOrganization(Organization organization )
	{
	  try
	  {
	  	this.childOrganizationsFromService.remove(organization) ;  
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ;
	  }
	  finally
	  {}
	}

	/**
	 * Removes a pd.supervision.supervisionstaff.Organization from class relationship collection.
	 */
//	public void removeChildOrganizations(pd.supervision.supervisionstaff.Organization anObject)
//	{
//		initChildOrganizations();
//		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
//		assocEvent.setChildId((String) anObject.getOID());
//		assocEvent.setParentId((String) this.getOID());
//		pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization actual = (pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization) new mojo.km.persistence.Reference(
//				assocEvent, pd.supervision.supervisionstaff.OrganizationChildOrganizationsOrganization.class)
//				.getObject();
//		childOrganizations.remove(actual);
//	}
	public void removeStaffPosition(CSCDStaffPosition staffPosition )
	{
	  try
	  {
	  	this.staffPositions.remove(staffPosition) ;  
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ;
	  }
	  finally
	  {}
	}

	/**
	 * set the type reference for class member agency
	 */
	public void setAgency(Agency agency)
	{
		if (this.agency == null || !this.agency.equals(agency))
		{
			markModified();
		}
		if (agency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 */
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
	
	/**
	 * @param childOrganizations The childOrganizations to set.
	 */
//	public void setChildOrganizations(java.util.Collection childOrganizations) {
//		this.childOrganizations = childOrganizations;
//	}
//
//	
	/**
	 * @param childOrganizationsFromService The childOrganizationsFromService to set.
	 */
	public void setChildOrganizationsFromService(Collection childOrganizationsFromService) {
		this.childOrganizationsFromService = childOrganizationsFromService;
	}

	/**
	 * @param description
	 The description to set.
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}
	/**
	 * @param manager The manager to set.
	 */
	public void setManager(CSCDStaffPosition manager) {
		this.manager = manager;
	}

	/**
	 * set the type reference for class member parentOrganization
	 */
	public void setParentOrganization(Organization parentOrganization)
	{
		if (this.parentOrganization == null || !this.parentOrganization.equals(parentOrganization))
		{
			markModified();
		}
		if (parentOrganization.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parentOrganization);
		}
		setParentOrganizationId("" + parentOrganization.getOID());
		this.parentOrganization = (Organization) new mojo.km.persistence.Reference(
				parentOrganization).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionstaff.Organization
	 */
	public void setParentOrganizationId(String parentOrganizationId)
	{
		if (this.parentOrganizationId == null || !this.parentOrganizationId.equals(parentOrganizationId))
		{
			markModified();
		}
		parentOrganization = null;
		this.parentOrganizationId = parentOrganizationId;
	}
    /**
     * @param probationOfficerInd The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd) {
		if (this.probationOfficerInd == null || !this.probationOfficerInd.equals(probationOfficerInd))
		{
			markModified();
		}
        this.probationOfficerInd = probationOfficerInd;
    }
	/**
	 * @param staffPositions The staffPositions to set.
	 */
	public void setStaffPositions(Collection staffPositions) {
		this.staffPositions = staffPositions;
	}
    /**
     * @return Returns the legacyProgramUnit.
     */
    public String getLegacyProgramUnit() {
        fetch();
        return legacyProgramUnit;
    }
    /**
     * @param legacyProgramUnit The legacyProgramUnit to set.
     */
    public void setLegacyProgramUnit(String legacyProgramUnit) {
		if (this.legacyProgramUnit == null || !this.legacyProgramUnit.equals(legacyProgramUnit))
		{
			markModified();
		}
        this.legacyProgramUnit = legacyProgramUnit;
    }
}
