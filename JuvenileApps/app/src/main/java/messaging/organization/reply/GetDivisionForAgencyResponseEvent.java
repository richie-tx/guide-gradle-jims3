/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;

import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetDivisionForAgencyResponseEvent extends ResponseEvent implements Serializable {

	Collection agencyDivisionsCollection ; 
	
	
	
	/**
	 * @return Returns the agencyDivisionsCollection.
	 */
	public Collection getAgencyDivisionsCollection() {
		return agencyDivisionsCollection;
	}
	/**
	 * @param agencyDivisionsCollection The agencyDivisionsCollection to set.
	 */
	public void setAgencyDivisionsCollection(Collection agencyDivisionsCollection) {
		this.agencyDivisionsCollection = agencyDivisionsCollection;
	}
	
	public static Comparator divisionNameComparator = new Comparator( )
	{
		public int compare( Object division, Object other_division )
		{
			String division_name = ((OrganizationTO)division).getDescription();
			String other_division_name = ((OrganizationTO)other_division).getDescription();
			return division_name.compareTo( other_division_name ) ;
		}
	}; 	
}
