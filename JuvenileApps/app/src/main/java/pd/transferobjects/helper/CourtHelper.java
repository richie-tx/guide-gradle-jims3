/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.CourtTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.supervision.Court;

/**
 * @author cc_mdsouza
 *
 */
public class CourtHelper {

	public CourtTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
		
	  CourtTO courtTO = new CourtTO() ; 
		
	  try
	  {
	  	Court court = (Court) persistentObject ; 
	    
	  	courtTO.setOID((String)court.getOID());
	    courtTO.setSID( court.getSID());
	    courtTO.setSTP( court.getSTP() ) ;
	    courtTO.setUserID ( court.getUserID() );
	    courtTO.setCreateUserID (court.getCreateUserID()) ;
	    courtTO.setCreateTimestamp ( court.getCreateTimestamp());
	    courtTO.setCreateJIMS2UserID ( court.getCreateJIMS2UserID());
	    courtTO.setUpdateUserID ( court.getUpdateUserID() );
	    courtTO.setUpdateTimestamp ( court.getUpdateTimestamp()) ;
	    courtTO.setUpdateJIMS2UserID ( court.getUpdateJIMS2UserID());
	    courtTO.setEXP ( court.getEXP() );
	    courtTO.setWorkflowID (court.getWorkflowID());

	    
	    courtTO.setCourtNumber ( court.getCourtNumber() ); 
	  	courtTO.setCourtCategory ( court.getCourtCategory() ); 
	  	courtTO.setJudgeFirstName ( court.getJudgeFirstName() ); 
	  	courtTO.setJudgeLastName ( court.getJudgeLastName() ); 
	  	courtTO.setDescription ( court.getDescription() ); 
	  	courtTO.setAddress(court.getAddress());
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return courtTO ; 
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
