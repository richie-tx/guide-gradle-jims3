/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.CodeTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * @author cc_mdsouza
 *
 */
public class CodeHelper {

	public CodeTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
	  CodeTO codeTO = new CodeTO() ; 
	  try
	  {
	  	Code code  = (Code) persistentObject ;
	  	
	    codeTO.setOID((String)code.getOID());
	    codeTO.setSID( code.getSID());
	    codeTO.setSTP( code.getSTP() ) ;
	    codeTO.setUserID ( code.getUserID() );
	    codeTO.setCreateUserID (code.getCreateUserID()) ;
	    codeTO.setCreateTimestamp ( code.getCreateTimestamp());
	    codeTO.setCreateJIMS2UserID ( code.getCreateJIMS2UserID());
	    codeTO.setUpdateUserID ( code.getUpdateUserID() );
	    codeTO.setUpdateTimestamp ( code.getUpdateTimestamp()) ;
	    codeTO.setUpdateJIMS2UserID ( code.getUpdateJIMS2UserID());
	    codeTO.setEXP ( code.getEXP() );
	    codeTO.setWorkflowID (code.getWorkflowID());
	  	
	  	
	  	
	  	codeTO.setActiveDate ( code.getActiveDate() ); 
	  	codeTO.setCode( code.getCode() ) ; 
	  	codeTO.setCodeTableName ( code.getCodeTableName() ) ; 
	  	codeTO.setDescription ( code.getDescription() ); 
	  	codeTO.setInactiveDate( code.getInactiveDate() ); 
	  	codeTO.setInactiveDate(  code.getInactiveEffectiveDate() ); 
	  	codeTO.setSID( code.getStatus() );
	  	
//	  	if (code.getCodeTable() != null  && this.codeTable == null )
//	  	{
//	  		this.codeTable = new CodeTableTO() ; 
//	  		this.codeTable.initializeTransferObjectFromPersistentObject( code.getCodeTable()) ; 
//	  	}
//
//	  	if (code.getCodeHistories().size() > 0 && this.codeHistories.size() != code.getCodeHistories().size())
//	  	{
//	  		// TODO : add in code histories to the transfer objects
//	  		this.codeHistories = new ArrayList() ; 
//	  	}
	  	
	  	
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
	  return codeTO ; 
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
