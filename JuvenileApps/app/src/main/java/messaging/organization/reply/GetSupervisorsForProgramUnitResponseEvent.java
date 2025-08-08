/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetSupervisorsForProgramUnitResponseEvent extends ResponseEvent implements Serializable  {

	Collection collectionOfSupervisors ; 
	
	
	
	/**
	 * @return Returns the collectionOfSupervisors.
	 */
	public Collection getCollectionOfSupervisors() {
		return collectionOfSupervisors;
	}
	/**
	 * @param collectionOfSupervisors The collectionOfSupervisors to set.
	 */
	public void setCollectionOfSupervisors(Collection collectionOfSupervisors) {
		this.collectionOfSupervisors = collectionOfSupervisors;
	}
}
