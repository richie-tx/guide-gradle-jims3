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
public class GetStaffPositionsForProgramUnitResponseEvent  extends ResponseEvent implements Serializable {

	
	
	Collection collection ; 
	
	/**
	 * @return Returns the collection.
	 */
	public Collection getCollection() {
		return collection;
	}
	/**
	 * @param collection The collection to set.
	 */
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
}
