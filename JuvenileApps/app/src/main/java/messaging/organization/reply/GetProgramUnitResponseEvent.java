/*
 * Created on Aug 31, 2007
 *
 */
package messaging.organization.reply;

import java.io.Serializable;
import java.util.Comparator;

import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_mdsouza
 *
 */
public class GetProgramUnitResponseEvent extends ResponseEvent implements Serializable  {

	OrganizationTO organizationTO ; 
	
	
	
	/**
	 * @return Returns the organizationTO.
	 */
	public OrganizationTO getOrganizationTO() {
		return organizationTO;
	}
	/**
	 * @param organizationTO The organizationTO to set.
	 */
	public void setOrganizationTO(OrganizationTO organizationTO) {
		this.organizationTO = organizationTO;
	}
	
	public static Comparator programUnitNameComparator = new Comparator( )
	{
		public int compare( Object program_unit, Object other_program_unit )
		{
			String program_unit_name = ((GetProgramUnitResponseEvent)program_unit).getOrganizationTO().getDescription();
			String other_program_unit_name = ((GetProgramUnitResponseEvent)other_program_unit).getOrganizationTO().getDescription();
			return program_unit_name.compareTo( other_program_unit_name ) ;
		}
	}; 	
	
}
