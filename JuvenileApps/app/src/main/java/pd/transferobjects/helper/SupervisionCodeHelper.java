/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.SupervisionCodeTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.codetable.supervision.SupervisionCode;

/**
 * @author cc_mdsouza
 *
 */
public class SupervisionCodeHelper {

	public SupervisionCodeTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
		
	  SupervisionCodeTO supervisionCodeTO = new SupervisionCodeTO() ; 
		
	  try
	  {
	  	SupervisionCode supervisionCode = (SupervisionCode) persistentObject ;
	  	
	  	
	    supervisionCodeTO.setOID((String)supervisionCode.getOID());
	    supervisionCodeTO.setSID( supervisionCode.getSID());
	    supervisionCodeTO.setSTP( supervisionCode.getSTP() ) ;
	    supervisionCodeTO.setUserID ( supervisionCode.getUserID() );
	    supervisionCodeTO.setCreateUserID (supervisionCode.getCreateUserID()) ;
	    supervisionCodeTO.setCreateTimestamp ( supervisionCode.getCreateTimestamp());
	    supervisionCodeTO.setCreateJIMS2UserID ( supervisionCode.getCreateJIMS2UserID());
	    supervisionCodeTO.setUpdateUserID ( supervisionCode.getUpdateUserID() );
	    supervisionCodeTO.setUpdateTimestamp ( supervisionCode.getUpdateTimestamp()) ;
	    supervisionCodeTO.setUpdateJIMS2UserID ( supervisionCode.getUpdateJIMS2UserID());
	    supervisionCodeTO.setEXP ( supervisionCode.getEXP() );
	    supervisionCodeTO.setWorkflowID (supervisionCode.getWorkflowID());
	  	
	  	
	  	supervisionCodeTO.setCodeTableName (  supervisionCode.getCodeTableName() ) ; 
	  	supervisionCodeTO.setAgencyId ( supervisionCode.getAgencyId() ); 
	  	supervisionCodeTO.setCode ( supervisionCode.getCode() ); 
	  	supervisionCodeTO.setDescription ( supervisionCode.getDescription() ); 
	  	supervisionCodeTO.setCodeId ( supervisionCode.getCodeId() ); 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return supervisionCodeTO ; 
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
