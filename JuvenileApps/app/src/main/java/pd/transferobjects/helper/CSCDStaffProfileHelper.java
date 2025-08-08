/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.CSCDStaffProfileTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile;

/**
 * @author cc_mdsouza
 *
 */
public class CSCDStaffProfileHelper {

	public static CSCDStaffProfileTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
	  CSCDStaffProfileTO staffProfileTO = new CSCDStaffProfileTO() ; 
	  try
	  {
	  	CSCDStaffProfile staffProfile = (CSCDStaffProfile) persistentObject ; 
	  	
	  	
	  	staffProfileTO.setOID((String)staffProfile.getOID());
	    staffProfileTO.setSID( staffProfile.getSID());
	    staffProfileTO.setSTP( staffProfile.getSTP() ) ;
	    staffProfileTO.setUserID ( staffProfile.getUserID() );
	    staffProfileTO.setCreateUserID (staffProfile.getCreateUserID()) ;
	    staffProfileTO.setCreateTimestamp ( staffProfile.getCreateTimestamp());
	    staffProfileTO.setCreateJIMS2UserID ( staffProfile.getCreateJIMS2UserID());
	    staffProfileTO.setUpdateUserID ( staffProfile.getUpdateUserID() );
	    staffProfileTO.setUpdateTimestamp ( staffProfile.getUpdateTimestamp()) ;
	    staffProfileTO.setUpdateJIMS2UserID ( staffProfile.getUpdateJIMS2UserID());
	    staffProfileTO.setEXP ( staffProfile.getEXP() );
	    staffProfileTO.setWorkflowID (staffProfile.getWorkflowID());
	  	
	  	
	  	
	  	staffProfileTO.setCjadNum( staffProfile.getCjadNum() ); 
	  	staffProfileTO.setStaffPositionId ( staffProfile.getStaffPositionId() ); 
	  	
//	  	if (this.staffPosition == null && staffProfile.getStaffPosition() != null )
//	  	{
//	  		this.staffPositionTO = new CSCDStaffPositionTO() ; 
//	  		staffPositionTO.initializeTransferObjectFromPersistentObject( staffProfile.getStaffPosition()  ) ; 
//	  	}
	  	
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return staffProfileTO ; 
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
