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
public class GetDivisionManagerForDivisionResponseEvent extends ResponseEvent implements Serializable {

	Collection divisionManagerCollection ; 
	
	
	/**
	 * @return Returns the divisionManagerCollection.
	 */
	public Collection getDivisionManagerCollection() {
		return divisionManagerCollection;
	}
	/**
	 * @param divisionManagerCollection The divisionManagerCollection to set.
	 */
	public void setDivisionManagerCollection(Collection divisionManagerCollection) {
		this.divisionManagerCollection = divisionManagerCollection;
	}
}
