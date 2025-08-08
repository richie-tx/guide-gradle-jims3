/*
 * Created on Aug 29, 2007
 *
 */
package pd.transferobjects.helper;

import messaging.transferobjects.AddressTO;
import messaging.transferobjects.TransferObjectInterface;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_mdsouza
 *
 */
public class AddressHelper {

	
	public AddressTO initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception 
	{
	  AddressTO addressTO = new AddressTO() ; 
	  try
	  {
	  	throw new UnsupportedOperationException() ; 
	  }
	  catch (Exception e)
	  {
	  	e.printStackTrace() ; 
	  	throw new Exception(e) ; 
	  }
//	  return addressTO ; 
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
