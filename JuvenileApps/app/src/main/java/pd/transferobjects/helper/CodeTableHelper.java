/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.CodeTableTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;
import pd.codetable.CodeTable;

/**
 * @author cc_mdsouza
 *
 */
public class CodeTableHelper {

	public void initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
	  CodeTableTO codeTableTO = new CodeTableTO() ; 
	  try
	  {
	  	CodeTable codeTable = (CodeTable) persistentObject ;
	  	
	    codeTableTO.setOID((String)codeTable.getOID());
	    codeTableTO.setSID( codeTable.getSID());
	    codeTableTO.setSTP( codeTable.getSTP() ) ;
	    codeTableTO.setUserID ( codeTable.getUserID() );
	    codeTableTO.setCreateUserID (codeTable.getCreateUserID()) ;
	    codeTableTO.setCreateTimestamp ( codeTable.getCreateTimestamp());
	    codeTableTO.setCreateJIMS2UserID ( codeTable.getCreateJIMS2UserID());
	    codeTableTO.setUpdateUserID ( codeTable.getUpdateUserID() );
	    codeTableTO.setUpdateTimestamp ( codeTable.getUpdateTimestamp()) ;
	    codeTableTO.setUpdateJIMS2UserID ( codeTable.getUpdateJIMS2UserID());
	    codeTableTO.setEXP ( codeTable.getEXP() );
	    codeTableTO.setWorkflowID (codeTable.getWorkflowID());
	  	
	  	
	  	codeTableTO.setDataSource ( codeTable.getDataSource() );
	  	codeTableTO.setFileName ( codeTable.getFileName() ); 
	  	codeTableTO.setRecordType ( codeTable.getRecordType() ); 
	  	
//	  	if (codeTable.getCodes().size() > 0 && this.codes.size() != codeTable.getCodes().size())
//	  	{
//	  		// TODO : codes collection needs to be initialize to the type of object stored in the collection 
//	  		// returning an empty collection to eliminate a null pointer exception
//	  		this.codes = new ArrayList() ; 
//	  	}
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
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
