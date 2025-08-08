package pd.organization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.transferobjects.CSCDStaffPositionTO;
import messaging.transferobjects.OrganizationTO;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.transferobjects.helper.OrganizationHelper;
import pd.transferobjects.helper.StaffPositionHelper;

public class OrganizationService 
{
	
	private static OrganizationService organizationService  ; 
	
	
	// used to store all organizations in flat map structure for oid based search
	private HashMap oidOrganizationSearchMap ; 
	
	// keyed on oid of staff position in flat map structure for oid based search of staff position
	private HashMap staffPositionSearchMap ; 
	
	private List divisionList ;
	
	
	private OrganizationService() 
	{
		oidOrganizationSearchMap = new HashMap() ;
		staffPositionSearchMap = new HashMap() ;
		divisionList = new LinkedList() ; 
	}
	
	
	public static OrganizationService getOrganizationService()
	{
	  try
	  {
	  	if(OrganizationService.organizationService == null )
	  	{
	  		OrganizationService.organizationService = new OrganizationService() ; 
	  		OrganizationService.organizationService.initializeOrganizationTree() ; 
	  	}

	  	// to be removed when the refresh functionality is added in 
//	  	OrganizationService.organizationService.initializeOrganizationTree() ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  finally
	  {}
	  return OrganizationService.organizationService ; 
	}
	
	private void initializeOrganizationTree()
	{
	  try
	  {
	  	// initialize everything until the refresh method call has been implemented 
	  	this.oidOrganizationSearchMap.clear()  ; 
	  	this.oidOrganizationSearchMap = new HashMap() ; 
		this.staffPositionSearchMap.clear() ; 
		this.staffPositionSearchMap = new HashMap() ; 
		this.divisionList.removeAll(divisionList);
		this.divisionList = new LinkedList() ; 

	  	
	  	IHome home = new Home() ;
	  	// obtain all the organizations 
	  	Iterator organizationIterator = home.findAll(Organization.class) ;
	  	Organization organization = null ;
	  	String organizationId = null ; 
	  	String parentOrganizationId = null ;
	  	List listOfChildOrganizations = null ;
	  	List newListOfChildOrganizations = null ; 
	  	while (organizationIterator.hasNext() )
	  	{
	  		organization = (Organization) organizationIterator.next() ;
	  		organizationId = (String)organization.getOID() ; 
	  		this.oidOrganizationSearchMap.put(organizationId , organization ) ; 
	  	}
	  	// Build org tree 
	  	Collection organizationValues = this.oidOrganizationSearchMap.values();
	  	Iterator valuesIterator = organizationValues.iterator() ;
	  	Organization currentOrganization = null ;
	  	Organization parentOrganization = null ; 
	  	String parentId = null ; 
	  	while(valuesIterator.hasNext() )
	  	{
	  	  currentOrganization = (Organization)valuesIterator.next() ;
	  	  parentId = currentOrganization.getParentOrganizationId() ;
	  	  if (parentId != null )
	  	  { 
	  	  	parentOrganization = (Organization)this.oidOrganizationSearchMap.get(parentId) ;
	  	  	parentOrganization.addChildOrganization(currentOrganization) ;
	  	  	//currentOrganization.setParentOrganization(parentOrganization) ; 
	  	  }
	  	  if (parentId == null )
	  	  {
	  	  	this.divisionList.add(currentOrganization) ;
	  	  }
	  	}
	  	// obtain all the staff positions
	  	Iterator staffPositionIterator = home.findAll(CSCDStaffPosition.class) ;
	  	CSCDStaffPosition staffPosition = null ;
	  	String staffPositionOrganizationId = null ; 
	  	String staffPositionId = null ; 
	  	while (staffPositionIterator.hasNext() )
	  	{
	  		staffPosition = (CSCDStaffPosition) staffPositionIterator.next() ;
	  		staffPositionId = (String)staffPosition.getOID() ; 
	  		this.staffPositionSearchMap.put(staffPositionId , staffPosition) ; 
	  	}
	  	Iterator staffPositionValueIterator = staffPositionSearchMap.values().iterator() ;
	  	Organization staffPositionParentOrganization = null ;
	  	String parentStaffPositionId = null ;
	  	String parentOrganizationIdForDisplay = null ; 
	  	CSCDStaffPosition parentStaffPosition = null ; 
	  	while (staffPositionValueIterator.hasNext())
	  	{
	  	  staffPosition = (CSCDStaffPosition) staffPositionValueIterator.next() ;
	  	  // add staff position to division
	  	  staffPositionOrganizationId = staffPosition.getOrganizationId() ; 
	  	  staffPositionParentOrganization = (Organization) this.oidOrganizationSearchMap.get(staffPositionOrganizationId) ;
	  	  //System.out.println("Adding staff position : " + staffPosition.getOID() + " to : " +  staffPositionOrganizationId );
	  	  staffPositionParentOrganization.addStaffPosition(staffPosition) ; 
	  	  // add staff position to parent staff position if required 
	  	  parentStaffPositionId = staffPosition.getParentPositionId() ;
	  	  parentStaffPosition = (CSCDStaffPosition) this.staffPositionSearchMap.get(parentStaffPositionId) ;
	  	  if (parentStaffPosition != null )
	  	  { 
		  	  parentStaffPosition.addChildStaffPositionFromService(staffPosition) ;
		  	  //staffPosition.setParentPosition(parentStaffPosition) ; 
	  	  }
	  	}
	  
		organizationIterator = this.oidOrganizationSearchMap.values().iterator() ; 
		String orgOid = null ; 
		String agencyId = PDSecurityHelper.getUserAgencyId();
		while (organizationIterator.hasNext())
	  	{
	  	  	try 
			{
				organization = (Organization) organizationIterator.next() ; 
				orgOid = (String)organization.getOID() ;
				//System.out.println("Organization ID : " + orgOid) ; 
				organization.findOrganizationManager(agencyId) ;
			} 
	  	  	catch (Exception e) 
			{
				ErrorResponseEvent re = new ErrorResponseEvent();
				re.setMessage(re.getMessage());
				MessageUtil.postReply(re);
			}
	  	}
	  }
	  catch (Exception e)
	  {
			ErrorResponseEvent re = new ErrorResponseEvent();
			re.setMessage(re.getMessage());
			MessageUtil.postReply(re);
	  }
	}
	

	
	
	private void refreshOrganizationTree() 
	{
	  try
	  {
	  	this.initializeOrganizationTree() ; 
	  }
	  catch (Exception e)
	  {
			ErrorResponseEvent re = new ErrorResponseEvent();
			re.setMessage(re.getMessage());
			MessageUtil.postReply(re);
	  }
	}
	
//	public Organization getParentOrganization( String organizationId )
//	throws Exception 
//	{
//	  Organization organization = null ;
//	  try
//	  {
//	  	organization = (Organization) oidOrganizationSearchMap.get(organizationId) ; 
//	  	String parentOrganizationId = organization.getParentOrganizationId() ;
//	  	if (parentOrganizationId == null ) 
//	  	{ 
//	  		throw new Exception("Organization : " + organizationId + " does not have parent.") ; 
//	  	}
//	  	else
//	  	{
//	  		IHome home = new Home() ;
//	  		organization = (Organization)home.find(parentOrganizationId , Organization.class ) ;
//	  	}
//	  	
//	  	
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  }
//	  
//	  
//	  return organization ; 
//	}

	
	
	public OrganizationTO getParentOrganization( String organizationId )
	throws Exception 
	{
	  Organization organization = null ; 
	  OrganizationTO organizationTO  = null ; 
	  try
	  {
	  	organization = (Organization) oidOrganizationSearchMap.get(organizationId) ; 
	  	String parentOrganizationId = organization.getParentOrganizationId() ;
	  	if (parentOrganizationId == null ) 
	  	{ 
	  		throw new Exception("Organization : " + organizationId + " does not have parent.") ; 
	  	}
	  	else
	  	{
	  		IHome home = new Home() ;
	  		organization = (Organization)home.find(parentOrganizationId , Organization.class ) ;
	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  if( organization != null  ) 
	  {
//	  	organizationTO = new OrganizationTO() ;
	  	
	  	organizationTO = OrganizationHelper.initializeTransferObjectFromPersistentObject(organization) ; 
	  	
	  	
//		organizationTO.initializeTransferObjectFromPersistentObject(organization) ; 
	  }
	  return organizationTO ; 
	}
	
	
//	public Organization getOrganization(String organizationId ) 
//	{
//		  Organization organization = null ;
//		  try
//		  {
//		  	organization = (Organization) oidOrganizationSearchMap.get(organizationId) ; 
//		  }
//		  catch (Exception e)
//		  {
//		  	e.printStackTrace() ; 
//		  }
//		  return organization ; 
//	}

	
	
	public OrganizationTO getOrganization(String organizationId ) 
	{
		  OrganizationTO organizationTO = null ; 
		  Organization organization = null ; 
		  try
		  {
		  	organization = (Organization) oidOrganizationSearchMap.get(organizationId) ; 
		  	if (organization != null )
			{
//		  		organizationTO = new OrganizationTO() ; 
//				organizationTO.initializeTransferObjectFromPersistentObject(organization) ; 
			  	organizationTO = OrganizationHelper.initializeTransferObjectFromPersistentObject(organization) ; 
			}
		  }
		  catch (Exception e)
		  {
		  	e.printStackTrace() ; 
		  }
		  return organizationTO ; 
	}
	
	
	
//	public Organization getProgramUnit(String programUnitId )
//	{
//	  Organization organization = null ; 
//	  try
//	  {
//	  	organization = this.getOrganization(programUnitId) ;
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  }
//	  return organization ; 
//	}

	
	public OrganizationTO getProgramUnit(String programUnitId )
	{
	  OrganizationTO organizationTO = null ; 
	  try
	  {
	  	organizationTO = this.getOrganization(programUnitId) ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  return organizationTO ; 
	}

	
	
//	public Organization getDivisionForProgramUnit(String programUnitId )
//	{
//	  
//	 Organization organization  = null ; 
//	  try
//	  {
//	  	organization = this.getParentOrganization(programUnitId) ;
//	  	
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  }
//	  return organization ; 
//	}
	

	public OrganizationTO getDivisionForProgramUnit(String programUnitId )
	{
	  OrganizationTO organizationTO = null ; 
	  try
	  {
	  	organizationTO = this.getParentOrganization(programUnitId) ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  return organizationTO ; 
	}
	
	
	
//	public Organization getDivision(String divisionId )
//	{
//	  Organization organization = null ; 
//	  try
//	  {
//	  	organization = this.getOrganization(divisionId) ; 
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  }
//	  return organization ; 
//	}
	

	public OrganizationTO getDivision(String divisionId )
	{
	  OrganizationTO organizationTO = null ; 
	  try
	  {
	  	organizationTO = this.getOrganization(divisionId) ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  return organizationTO ; 
	}
	
	
	
	
//	public Collection getStaffPositionsForProgramUnit( String programUnitId )
//	{
//	  Collection staffPositionCollection = null ; 
//	  try
//	  {
//	  	Organization organization = (Organization)this.oidOrganizationSearchMap.get(programUnitId) ;
//	  	staffPositionCollection= organization.getStaffPositions() ;
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ;
//	  }
//	  return staffPositionCollection ; 
//	}


	
	public Collection getStaffPositionsForProgramUnit( String programUnitId )
	{
	  Collection staffPositionCollection = null ; 
	  Collection staffPositionTOCollection = new ArrayList() ;  
	  try
	  {
	  	Organization organization = (Organization)this.oidOrganizationSearchMap.get(programUnitId) ;
	  	staffPositionCollection= organization.getStaffPositions() ;
	  	
	  	Iterator staffPositionIterator = staffPositionCollection.iterator() ;
	  	CSCDStaffPosition staffPosition = null ;
	  	CSCDStaffPositionTO staffPositionTO = null ; 
	  	while(staffPositionIterator.hasNext())
	  	{
	  		staffPosition = (CSCDStaffPosition)staffPositionIterator.next() ; 
//	  		staffPositionTO = new CSCDStaffPositionTO() ; 
//	  		staffPositionTO.initializeTransferObjectFromPersistentObject(staffPosition) ; 

	  		staffPositionTO = StaffPositionHelper.initializeTransferObjectFromPersistentObject(staffPosition) ; 
	  		staffPositionTOCollection.add(staffPositionTO) ;
	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ;
	  }
//	  return staffPositionCollection ; 
	  return staffPositionTOCollection ; 
	}
	
	
	// returns a list of user profiles of supervisors for program unit
	
//	public Collection getDivisionManagerForDivision( String divisionId )
//	{
//	  Collection supervisorCollection = new ArrayList() ; 
//	  try
//	  {
//	  	Organization organization = (Organization)this.oidOrganizationSearchMap.get(divisionId) ;
//	  	Collection staffCollection= organization.getStaffPositions() ;
//	  	Iterator staffCollectionIterator = staffCollection.iterator() ;
//	  	CSCDStaffPosition iteratedStaffPosition= null ;
//	  	UserProfile userProfile = null ;
//	  	SupervisionCode supervisionCodeToGetPositionType = null ;
//	  	String cstsOfficerTypeId = null ;
//	  	String referenceCstsOfficerTypeId = null ;
//	  	SupervisionCode divisionManagerCode = null ; 
//	  	while(staffCollectionIterator.hasNext() )
//	  	{
//	  	  iteratedStaffPosition = (CSCDStaffPosition) staffCollectionIterator.next() ;
//	  	  cstsOfficerTypeId = iteratedStaffPosition.getCstsOfficerTypeId() ; 	
//	  	  
////	  	  divisionManagerCode = CSCDStaffPositionHelper.getSupervisionCodeByValue(
////	  	  		PDCodeTableConstants.CSCD_AGENCY, 
////				PDCodeTableConstants.STAFF_POSITION_TYPE, 
////				PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR);
//	  	  divisionManagerCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(
//		  	  		PDCodeTableConstants.CSCD_AGENCY, 
//					PDCodeTableConstants.STAFF_POSITION_TYPE, 
//					PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR);
//	  	  
//	  	  referenceCstsOfficerTypeId = divisionManagerCode.getCodeId() ; 
//	  	  
//	  	  if(cstsOfficerTypeId.equals(referenceCstsOfficerTypeId))
//	  	  {	  
//		  	supervisorCollection.add(iteratedStaffPosition) ; 
//	  	  }
//	  	}
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ;
//	  }
//	  return supervisorCollection ; 
//	}

	
	// returns a list of user profiles of supervisors for program unit
	
	public Collection getDivisionManagerForDivision( String divisionId )
	{
	  Collection supervisorCollection = new ArrayList() ; 
	  Collection staffPositionTOCollection = new ArrayList() ;  
	  try
	  {
	  	Organization organization = (Organization)this.oidOrganizationSearchMap.get(divisionId) ;
	  	Collection staffCollection= organization.getStaffPositions() ;
	  	Iterator staffCollectionIterator = staffCollection.iterator() ;
	  	CSCDStaffPosition iteratedStaffPosition= null ;
	  	UserProfile userProfile = null ;
	  	SupervisionCode supervisionCodeToGetPositionType = null ;
	  	String cstsOfficerTypeId = null ;
	  	String referenceCstsOfficerTypeId = null ;
	  	SupervisionCode divisionManagerCode = null ; 
	  	while(staffCollectionIterator.hasNext() )
	  	{
	  	  iteratedStaffPosition = (CSCDStaffPosition) staffCollectionIterator.next() ;
	  	  cstsOfficerTypeId = iteratedStaffPosition.getCstsOfficerTypeId() ; 	
	  	  
	  	  divisionManagerCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(
	  	  		PDCodeTableConstants.CSCD_AGENCY, 
				PDCodeTableConstants.STAFF_POSITION_TYPE, 
				PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR);
	  	  
	  	  referenceCstsOfficerTypeId = divisionManagerCode.getCodeId() ; 
	  	  
	  	  if(cstsOfficerTypeId.equals(referenceCstsOfficerTypeId))
	  	  {	  
		  	supervisorCollection.add(iteratedStaffPosition) ; 
	  	  }

	  	}
	  	
	  	Iterator staffPositionIterator = supervisorCollection.iterator() ;
	  	CSCDStaffPosition staffPosition = null ;
	  	CSCDStaffPositionTO staffPositionTO = null ; 
	  	while(staffPositionIterator.hasNext())
	  	{
	  		staffPosition = (CSCDStaffPosition) staffPositionIterator.next() ; 
//	  		staffPositionTO = new CSCDStaffPositionTO() ; 
//	  		staffPositionTO.initializeTransferObjectFromPersistentObject(staffPosition) ; 

	  		staffPositionTO = StaffPositionHelper.initializeTransferObjectFromPersistentObject(staffPosition) ; 
	  		staffPositionTOCollection.add(staffPositionTO) ;
	  	}
	  	
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ;
	  }
	  return supervisorCollection ; 
	}
	
//	// returns a list of user profiles of supervisors for program unit
//	
//	public Collection getSupervisorsForProgramUnit( String programUnitId )
//	{
//	  Collection supervisorCollection = new ArrayList() ; 
//	  try
//	  {
//	  	Organization organization = (Organization)this.oidOrganizationSearchMap.get(programUnitId) ;
//	  	Collection staffCollection= organization.getStaffPositions() ;
//	  	Iterator staffCollectionIterator = staffCollection.iterator() ;
//	  	CSCDStaffPosition iteratedStaffPosition= null ;
//	  	UserProfile userProfile = null ;
//	  	SupervisionCode supervisionCodeToGetPositionType = null ; 
//	  	String code = null ; 
//	  	while(staffCollectionIterator.hasNext() )
//	  	{
//	  	  iteratedStaffPosition = (CSCDStaffPosition) staffCollectionIterator.next() ;
//	  	  supervisionCodeToGetPositionType = iteratedStaffPosition.getPositionType() ; 
//	  	  code = supervisionCodeToGetPositionType.getCode() ; 
//	  	  if(
//	  	  		code.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR) || 
//	  	  		code.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP) 
//		  )
//	  	  {	  
//		  	supervisorCollection.add(iteratedStaffPosition) ; 
//	  	  }
//	  	}
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ;
//	  }
//	  return supervisorCollection ; 
//	}

	
	// returns a list of user profiles of supervisors for program unit
	
	public Collection getSupervisorsForProgramUnit( String programUnitId )
	{
	  Collection supervisorCollection = new ArrayList() ; 
	  Collection staffPositionTOCollection = new ArrayList() ;  
	  try
	  {
	  	Organization organization = (Organization)this.oidOrganizationSearchMap.get(programUnitId) ;
	  	Collection staffCollection= organization.getStaffPositions() ;
	  	Iterator staffCollectionIterator = staffCollection.iterator() ;
	  	CSCDStaffPosition iteratedStaffPosition= null ;
	  	UserProfile userProfile = null ;
	  	SupervisionCode supervisionCodeToGetPositionType = null ; 
	  	String code = null ; 
	  	while(staffCollectionIterator.hasNext() )
	  	{
	  	  iteratedStaffPosition = (CSCDStaffPosition) staffCollectionIterator.next() ;
	  	  supervisionCodeToGetPositionType = iteratedStaffPosition.getPositionType() ; 
	  	  code = supervisionCodeToGetPositionType.getCode() ; 
	  	  if(
	  	  		code.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR) || 
	  	  		code.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP) 
		  )
	  	  {	  
		  	supervisorCollection.add(iteratedStaffPosition) ; 
	  	  }
	  	}

	  
	  	Iterator staffPositionIterator = supervisorCollection.iterator() ;
	  	CSCDStaffPosition staffPosition = null ;
	  	CSCDStaffPositionTO staffPositionTO = null ; 
	  	while(staffPositionIterator.hasNext())
	  	{
	  		staffPosition = (CSCDStaffPosition) staffPositionIterator.next() ; 
	  		staffPositionTO = StaffPositionHelper.initializeTransferObjectFromPersistentObject(staffPosition) ; 
	  		staffPositionTOCollection.add(staffPositionTO) ;
	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ;
	  }
	  return staffPositionTOCollection ; 
	}
	
	
//	public Collection getOfficersReportingToSupervisor(String staffPositionId )
//	{
//		  Collection reporteesCollection = null ; 
//		  try
//		  {
//		  	CSCDStaffPosition supervisorStaffPosition = (CSCDStaffPosition)this.staffPositionSearchMap.get(staffPositionId) ;
//		  	Collection staffCollection= supervisorStaffPosition.getChildStaffPositions() ;
//		  	Iterator staffCollectionIterator = staffCollection.iterator() ;
//		  	CSCDStaffPosition iteratedStaffPosition= null ;
//		  	UserProfile userProfile = null ;
//		  	SupervisionCode supervisionCodeToGetPositionType = null ; 
//		  	while(staffCollectionIterator.hasNext() )
//		  	{
//		  	  iteratedStaffPosition = (CSCDStaffPosition) staffCollectionIterator.next() ;
//		  	  supervisionCodeToGetPositionType = iteratedStaffPosition.getCstsOfficerType() ;
//		  	  if(
//		  	  		supervisionCodeToGetPositionType.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER) || 
//		  	  		supervisionCodeToGetPositionType.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP) 
//			  )
//		  	  {	  
//			  	reporteesCollection.add(iteratedStaffPosition) ; 
//		  	  }
//		  	}
//		  }
//		  catch (Exception e)
//		  {
//		  	e.printStackTrace() ;
//		  }
//		  return reporteesCollection ; 
//	}

	
	
	public Collection getOfficersReportingToSupervisor(String staffPositionId )
	{
		  Collection reporteesCollection = null ; 
		  Collection staffPositionTOCollection = null ; 
		  try
		  {
		  	CSCDStaffPosition supervisorStaffPosition = (CSCDStaffPosition)this.staffPositionSearchMap.get(staffPositionId) ;
		  	Collection staffCollection= supervisorStaffPosition.getChildStaffPositions() ;
		  	Iterator staffCollectionIterator = staffCollection.iterator() ;
		  	CSCDStaffPosition iteratedStaffPosition= null ;
		  	UserProfile userProfile = null ;
		  	SupervisionCode supervisionCodeToGetPositionType = null ; 
		  	while(staffCollectionIterator.hasNext() )
		  	{
		  	  iteratedStaffPosition = (CSCDStaffPosition) staffCollectionIterator.next() ;
		  	  supervisionCodeToGetPositionType = iteratedStaffPosition.getCstsOfficerType() ;
		  	  if(
		  	  		supervisionCodeToGetPositionType.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER) || 
		  	  		supervisionCodeToGetPositionType.equals(PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP) 
			  )
		  	  {	  
			  	reporteesCollection.add(iteratedStaffPosition) ; 
		  	  }
		  	}

		  	Iterator staffPositionIterator = reporteesCollection.iterator() ;
		  	CSCDStaffPosition staffPosition = null ;
		  	CSCDStaffPositionTO staffPositionTO = null ; 
		  	while(staffPositionIterator.hasNext())
		  	{
		  		staffPosition = (CSCDStaffPosition) staffPositionIterator.next() ; 
//		  		staffPositionTO = new CSCDStaffPositionTO() ; 
//		  		staffPositionTO.initializeTransferObjectFromPersistentObject(staffPosition) ; 

		  		staffPositionTO = StaffPositionHelper.initializeTransferObjectFromPersistentObject(staffPosition) ; 
		  		staffPositionTOCollection.add(staffPositionTO) ;
		  	}
		  
		  
		  }
		  catch (Exception e)
		  {
		  	e.printStackTrace() ;
		  }
		  return reporteesCollection ; 
	}
	
	
//	public CSCDStaffPosition getStaffPosition(String staffPositionId ) 
//	{
//	  CSCDStaffPosition staffPosition = null ;
//	  try
//	  {
//	    staffPosition = (CSCDStaffPosition) this.staffPositionSearchMap.get(staffPositionId) ;
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  }
//	  return staffPosition ; 
//	}
	

	
	public CSCDStaffPositionTO getStaffPosition(String staffPositionId ) 
	{
	  CSCDStaffPosition staffPosition = null ;
	  CSCDStaffPositionTO staffPositionTO = null ; 
	  try
	  {
	    staffPosition = (CSCDStaffPosition) this.staffPositionSearchMap.get(staffPositionId) ;
	    if (staffPosition != null )
	    {
//	    	staffPositionTO = new CSCDStaffPositionTO() ; 
//	    	staffPositionTO.initializeTransferObjectFromPersistentObject(staffPosition) ; 

	  		staffPositionTO = StaffPositionHelper.initializeTransferObjectFromPersistentObject(staffPosition) ; 
	    
	    }
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  return staffPositionTO ; 
	}
	
	
	
	
	/**
	 * @return Returns the divisionList.
	 */
	public List getDivisionList() {
		return divisionList;
	}
	/**
	 * @param divisionList The divisionList to set.
	 */
	public void setDivisionList(List divisionList) {
		this.divisionList = divisionList;
	}
	
	
//	public Collection getDivisionForAgency(String agencyCode ) 
//	{
//	  Collection divisionsInAgency = new LinkedList() 	;
//	  try
//	  {
//	  	Iterator allOrganizationsIterator = this.oidOrganizationSearchMap.values().iterator() ;
//	  	Organization division = null ; 
//	  	while (allOrganizationsIterator.hasNext() )
//	  	{
//	  	  division = (Organization)allOrganizationsIterator.next() ;
//	  	  // check to see if organization belongs to agency 
//	  	  if ( division.getAgencyId().equals(agencyCode) )
//	  	  {
//	  	  	// check to see if the organization has a parent organization
//	  	  	// divisions do not have a parent organization
//	  	  	if (division.getParentOrganizationId() == null ||division.getParentOrganizationId().equals("") )
//	  	  	{
//	  	  	  divisionsInAgency.add(division) ; 
//	  	  	}
//	  	  }
//	  	}
//	  	
//	  }
//	  catch (Exception e)
//	  {
//	  	e.printStackTrace() ; 
//	  }
//	  return divisionsInAgency ; 
//	}


	public Collection getDivisionForAgency(String agencyCode ) 
	{
	  Collection divisionsInAgency = new LinkedList() 	;
	  Collection organizationTOCollection = new ArrayList() ; 
	  try
	  {
	  	Iterator allOrganizationsIterator = this.oidOrganizationSearchMap.values().iterator() ;
	  	Organization division = null ; 
	  	while (allOrganizationsIterator.hasNext() )
	  	{
	  	  division = (Organization)allOrganizationsIterator.next() ;
	  	  // check to see if organization belongs to agency 
	  	  if ( division.getAgencyId().equals(agencyCode) )
	  	  {
	  	  	// check to see if the organization has a parent organization
	  	  	// divisions do not have a parent organization
	  	  	if (division.getParentOrganizationId() == null ||division.getParentOrganizationId().equals("") )
	  	  	{
	  	  	  divisionsInAgency.add(division) ; 
	  	  	}
	  	  }
	  	}
	  	Iterator divisionIterator = divisionsInAgency.iterator() ;
	  	Organization organization = null ;
	  	OrganizationTO organizationTO = null ; 
	  	while(divisionIterator.hasNext())
	  	{
	  		organization = (Organization) divisionIterator.next() ; 
//	  		organizationTO = new OrganizationTO() ; 
//	  		organizationTO.initializeTransferObjectFromPersistentObject(organization) ; 

	  		organizationTO = OrganizationHelper.initializeTransferObjectFromPersistentObject(organization) ; 
	  		organizationTOCollection.add(organizationTO) ;
	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  return organizationTOCollection ; 
	}
	
	public Collection getDivisionForAgency(String agencyCode, String divisionId ) 
	{
	    Collection divisionsInAgency = new LinkedList() 	;
	    Collection organizationTOCollection = new ArrayList() ; 
	    try
	    {
	    	Organization division = null ; 
	    	Iterator allOrganizationsIterator = this.oidOrganizationSearchMap.values().iterator() ;
	  	    while (allOrganizationsIterator.hasNext() )
	     	{
	  	        division = (Organization)allOrganizationsIterator.next() ;
	  	        // check to see if organization belongs to agency 
	  	        if ( division.getAgencyId().equals(agencyCode) )
	  	        {
	  	  	        // check to see if the organization has a parent organization
	  	  	        // divisions do not have a parent organization
			    	if(divisionId != null && !"".equals(divisionId) && divisionId.equals(division.getOID())){
			  	    	if (division.getParentOrganizationId() == null ||division.getParentOrganizationId().equals("") )
			  	  	    {
			  	  	        divisionsInAgency.add(division);
			  	  	    }
			  	    }else{ 	
		  	        	if (division.getParentOrganizationId() == null ||division.getParentOrganizationId().equals("") )
		  	  	        {
		  	  	            divisionsInAgency.add(division) ; 
		  	  	        }
			  	    }
	  	        }
	  	    }	    	
	    	
	  	    Iterator divisionIterator = divisionsInAgency.iterator() ;
	  	    Organization organization = null ;
	  	    OrganizationTO organizationTO = null ; 
	  	    while(divisionIterator.hasNext())
	  	    {
	  		    organization = (Organization) divisionIterator.next() ; 
	  		    organizationTO = OrganizationHelper.initializeTransferObjectFromPersistentObject(organization) ; 
	  		    organizationTOCollection.add(organizationTO) ;
	  	    }
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  }
	  return organizationTOCollection ; 
	}
	
	
    public CSCDSupervisionStaffResponseEvent getBaseStaffResponseEvent(CSCDStaffPositionTO staffPositionTO)
    {
    	String oid = staffPositionTO.getOID() ;
    	IHome home = new Home() ; 
    	CSCDStaffPosition staffPosition = (CSCDStaffPosition)home.find( oid , CSCDStaffPosition.class ) ; 
    	CSCDSupervisionStaffResponseEvent event = CSCDStaffPositionHelper.getBaseStaffResponseEvent(staffPosition, null);
    	return event ; 
    } 

    public Collection getProgramUnitsForDivision(String divisionId )
    {
    	Collection programUnitList = new LinkedList() ;
    	try
		{
    		Organization division = (Organization)this.oidOrganizationSearchMap.get( divisionId ) ;
    		programUnitList = division.getChildOrganizations() ; 
		}
    	catch (Exception e)
		{
    		e.printStackTrace() ; 
		}
    	return programUnitList ; 
    }


}
