/*
 * Created on Aug 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.transferobjects;

import mojo.km.persistence.PersistentObject;

/**
 * @author cc_mdsouza
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TransferObjectInterface 
{
	
	public void initializeTransferObjectFromPersistentObject( PersistentObject persistentObject ) 
	throws Exception ; 
	
	public void putTransferObjectToPersistentObject ( TransferObjectInterface transferObjectInterface ) 
	throws Exception ; 

}
