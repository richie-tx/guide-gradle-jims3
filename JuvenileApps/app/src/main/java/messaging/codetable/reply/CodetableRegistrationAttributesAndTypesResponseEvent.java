/*
 * Created on Nov 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable.reply;

import java.util.Comparator;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodetableRegistrationAttributesAndTypesResponseEvent extends ResponseEvent implements Comparable{

	private String dbItemName;
	private String type;
	private String dipslayOrder;
	
	
	
	/**
	 * @return Returns the dbItemName.
	 */
	public String getDbItemName() {
		return dbItemName;
	}
	/**
	 * @param dbItemName The dbItemName to set.
	 */
	public void setDbItemName(String dbItemName) {
		this.dbItemName = dbItemName;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type; 
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return dbItemName;
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		CodetableRegistrationAttributesAndTypesResponseEvent evt = (CodetableRegistrationAttributesAndTypesResponseEvent) obj;
		
		if (evt.getDbItemName() == null){
			return -1;
		}		
		if (this.getDbItemName() == null){
			return 1;
		}
        return this.getDbItemName().compareToIgnoreCase(evt.getDbItemName().trim());
	}
	/**
	 * @return Returns the dipslayOrder.
	 */
	public String getDipslayOrder() {
		return dipslayOrder;
	}
	/**
	 * @param dipslayOrder The dipslayOrder to set.
	 */
	public void setDipslayOrder(String dipslayOrder) {
		this.dipslayOrder = dipslayOrder;
	}
}
