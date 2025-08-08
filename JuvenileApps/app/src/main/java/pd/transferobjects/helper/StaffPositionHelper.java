/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import java.util.Iterator;

import messaging.transferobjects.CSCDStaffPositionTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @author cc_mdsouza
 *
 */
public class StaffPositionHelper {

	public static CSCDStaffPositionTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
		
	  CSCDStaffPositionTO cscdStaffPositionTO = new CSCDStaffPositionTO() ; 	
		
	  try
	  {
	  	CSCDStaffPosition staffPosition = (CSCDStaffPosition) persistentObject ;
	  	
	  	cscdStaffPositionTO.setStaffPositionId(staffPosition.getOID().toString());
	  	cscdStaffPositionTO.setOID((String)staffPosition.getOID());
	    cscdStaffPositionTO.setSID( staffPosition.getSID());
	    cscdStaffPositionTO.setSTP( staffPosition.getSTP() ) ;
	    cscdStaffPositionTO.setUserID ( staffPosition.getUserID() );
	    cscdStaffPositionTO.setCreateUserID (staffPosition.getCreateUserID()) ;
	    cscdStaffPositionTO.setCreateTimestamp ( staffPosition.getCreateTimestamp());
	    cscdStaffPositionTO.setCreateJIMS2UserID ( staffPosition.getCreateJIMS2UserID());
	    cscdStaffPositionTO.setUpdateUserID ( staffPosition.getUpdateUserID() );
	    cscdStaffPositionTO.setUpdateTimestamp ( staffPosition.getUpdateTimestamp()) ;
	    cscdStaffPositionTO.setUpdateJIMS2UserID ( staffPosition.getUpdateJIMS2UserID());
	    cscdStaffPositionTO.setEXP ( staffPosition.getEXP() );
	    cscdStaffPositionTO.setWorkflowID (staffPosition.getWorkflowID());
	  	
	  	cscdStaffPositionTO.setCstsOfficerTypeId ( staffPosition.getCstsOfficerTypeId() ); 
	  	cscdStaffPositionTO.setHasCaseload(staffPosition.getHasCaseload() ); 
	  	cscdStaffPositionTO.setJobTitleId(staffPosition.getJobTitleId() ); 
	  	cscdStaffPositionTO.setLocationDetails(staffPosition.getLocationDetails() );
	  	cscdStaffPositionTO.setLocationId( staffPosition.getLocationId() ); 
	  	cscdStaffPositionTO.setOrganizationId ( staffPosition.getOrganizationId() ) ; 
	  	cscdStaffPositionTO.setParentPositionId( staffPosition.getParentPositionId() ); 
	  	cscdStaffPositionTO.setPhoneNum( staffPosition.getPhoneNum() ); 
	  	cscdStaffPositionTO.setPositionName( staffPosition.getPositionName() ) ; 
	  	cscdStaffPositionTO.setPositionTypeId ( staffPosition.getPositionTypeId() ); 
	  	cscdStaffPositionTO.setProbationOfficerInd( staffPosition.getProbationOfficerInd() ); 
	  	cscdStaffPositionTO.setSID( staffPosition.getStaffProfileId() ); 
	  	cscdStaffPositionTO.setSID( staffPosition.getStatusId() ); 
	  	cscdStaffPositionTO.setUserProfileId ( staffPosition.getUserProfileId() ); 
//	  	if (this.cstsOfficerType == null && staffPosition.getCstsOfficerType() != null )
//	  	{
//	  		this.cstsOfficerType = new SupervisionCodeTO() ; 
//	  		cstsOfficerType.initializeTransferObjectFromPersistentObject( staffPosition.getCstsOfficerType() ) ; 
//	  	}
//	  	if (this.jobTitle == null && staffPosition.getJobTitle() != null )
//	  	{
//	  		this.jobTitle = new SupervisionCodeTO() ; 
//	  		jobTitle.initializeTransferObjectFromPersistentObject( staffPosition.getJobTitle() ) ; 
//	  	}
//	  	if (this.location == null && staffPosition.getLocation() != null )
//	  	{
//	  		this.location = new LocationTO() ; 
//	  		location.initializeTransferObjectFromPersistentObject( staffPosition.getLocation() ) ; 
//	  	}
//	  	if (this.organization == null && staffPosition.getOrganization() != null )
//	  	{
//	  		this.organization = new OrganizationTO() ; 
//	  		organization.initializeTransferObjectFromPersistentObject( staffPosition.getOrganization() ) ; 
//	  	}
//	  	if (this.parentPosition == null && staffPosition.getParentPosition() != null )
//	  	{
//	  		this.parentPosition = new CSCDStaffPositionTO() ; 
//	  		parentPosition.initializeTransferObjectFromPersistentObject( staffPosition.getParentPosition() ) ; 
//	  	}
	 /* 	if (this.positionType == null && staffPosition.getPositionType() != null )
	  	{
	  		this.positionType = new SupervisionCodeTO() ; 
	  		positionType.initializeTransferObjectFromPersistentObject( staffPosition.getPositionType() ) ; 
	  	}*/
	  	if (cscdStaffPositionTO.getStaffProfile() == null && staffPosition.getStaffProfile() != null )
	  	{
//	  		cscdStaffPositionTO.setStaffProfile ( new CSCDStaffProfileTO() ); 
//	  		cscdStaffPositionTO.getStaffProfile ( ).initializeTransferObjectFromPersistentObject( staffPosition.getStaffProfile() ) ;
	  		cscdStaffPositionTO.setStaffProfile( CSCDStaffProfileHelper.initializeTransferObjectFromPersistentObject( staffPosition.getStaffProfile())) ; 
	  	}
//	  	if (cscdStaffPositionTO.status == null && staffPosition.getStatus()!= null )
//	  	{
//	  		cscdStaffPositionTO.status = new SupervisionCodeTO() ; 
//	  		status.initializeTransferObjectFromPersistentObject( staffPosition.getStatus() ) ; 
//	  	}
	  	if (cscdStaffPositionTO.getUserProfile () == null && staffPosition.getUserProfile() != null )
	  	{
//	  		cscdStaffPositionTO.setUserProfile ( new UserProfileTO() ); 
//	  		cscdStaffPositionTO.getUserProfile ( ).initializeTransferObjectFromPersistentObject( staffPosition.getUserProfile() ) ;
	  		//cscdStaffPositionTO.setUserProfile( UserProfileHelper.initializeTransferObjectFromPersistentObject(staffPosition.getUserProfile() )) ; #87191 
	  	}
//
	  	if (staffPosition.getChildStaffPositions().size() > 0 && cscdStaffPositionTO.getChildStaffPositions().size() != staffPosition.getChildStaffPositions().size())
	  	{
	  		Iterator staffPositionsIterator = staffPosition.getChildStaffPositions().iterator() ;
	  		CSCDStaffPosition childStaffPosition = null ;
	  		CSCDStaffPositionTO childStaffPositionTO = null ; 
	  		while ( staffPositionsIterator.hasNext() ) 
	  		{
	  			childStaffPosition = (CSCDStaffPosition) staffPositionsIterator.next() ; 
//	  			childStaffPositionTO = new CSCDStaffPositionTO() ; 
//	  			childStaffPositionTO.initializeTransferObjectFromPersistentObject(childStaffPosition) ; 

	  			childStaffPositionTO = StaffPositionHelper.initializeTransferObjectFromPersistentObject(childStaffPosition) ; 
	  			cscdStaffPositionTO.getChildStaffPositions().add(childStaffPositionTO) ; 
	  		}
	  	}
//	  	
//	  	if (staffPosition.getCourts().size() > 0 && this.courts.size() != staffPosition.getCourts().size())
//	  	{
//	  		Iterator courtsIterator = staffPosition.getCourts().iterator() ;
//	  		Court court = null ;
//	  		CourtTO courtTO = null ; 
//	  		while ( courtsIterator.hasNext() ) 
//	  		{
//	  			court = (Court) courtsIterator.next() ; 
//	  			courtTO = new CourtTO() ; 
//	  			courtTO.initializeTransferObjectFromPersistentObject(court) ; 
//	  			this.courts.add(courtTO) ; 
//	  		}
//	  	}
//	  	if (staffPosition.getHistories().size() > 0 && this.histories.size() != staffPosition.getHistories().size())
//	  	{
//	  		// TODO : typecast list of histories and populate the relevant transfer object. 
//	  		// Right now the histories collection is just being set to an empty linked list 
//	  		// so no histories are available
//	  		this.histories = new LinkedList() ; 
//	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return cscdStaffPositionTO ; 
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
	
	
}
