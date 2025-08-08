package pd.supervision.supervisionoptions;

import java.util.ArrayList;
import java.util.Iterator;

import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.PersistentObject;
import pd.contact.agency.Agency;

/**
* @roseuid 42F7C3FE035B
*/
public class SupervisionEventType extends PersistentObject {
	private String agencyId;
	private String name;
	private String description;
	/**
	* Properties for agency
	*/
	public Agency agency = null;
	/**
	* @roseuid 42F7C3FE035B
	*/
	public SupervisionEventType() {
	}
	/**
	* Access method for the name property.
	* @return the current value of the name property
	*/
	public String getName() {
		fetch();
		return name;
	}
	/**
	* Sets the value of the name property.
	* @param aName the new value of the name property
	*/
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}
	/**
	* Set the reference value to class :: pd.contact.agency.Agency
	*/
	public void setAgencyId(String agencyId) {
		if (this.agencyId == null || !this.agencyId.equals(agencyId)) {
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
	/**
	* Get the reference value to class :: pd.contact.agency.Agency
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initAgency() {
		if (agency == null) {
			try {
				agency = (Agency) new mojo.km.persistence.Reference(agencyId, Agency.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Agency getAgency() {
		initAgency();
		return agency;
	}
	/**
	* set the type reference for class member agency
	*/
	public void setAgency(Agency agency) {
		if (this.agency == null || !this.agency.equals(agency)) {
			markModified();
		}
		if (agency.getOID() == null) {
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}
	
	/**
	* 
	*/
	public static Iterator findAll( String agencyId )
	{
		//Home home = new Home();
		//return home.findAll( "agencyId", agencyId, SupervisionEventType.class );
		
		// Begin Mock code
		ArrayList list = new ArrayList();
		SupervisionEventType tmp;
		
		tmp = new SupervisionEventType();
		tmp.setOID( "1" );
		tmp.setAgencyId( "1" );
		tmp.setName( "CONSECUTIVE FAILURE TO REPORT" );
		tmp.setDescription( "" );
		list.add( tmp );
		
		tmp = new SupervisionEventType();
		tmp.setOID( "2" );
		tmp.setAgencyId( "1" );
		tmp.setName( "FAILURE TO REPORT" );
		tmp.setDescription( "" );
		list.add( tmp );
		
		tmp = new SupervisionEventType();
		tmp.setOID( "3" );
		tmp.setAgencyId( "1" );
		tmp.setName( "FAILURE TO REPORT" );
		tmp.setDescription( "" );
		list.add( tmp );
		
		tmp = new SupervisionEventType();
		tmp.setOID( "4" );
		tmp.setAgencyId( "1" );
		tmp.setName( "FAILURE TO REPORT" );
		tmp.setDescription( "" );
		list.add( tmp );
		
		tmp = new SupervisionEventType();
		tmp.setOID( "5" );
		tmp.setAgencyId( "1" );
		tmp.setName( "FAILURE TO REPORT" );
		tmp.setDescription( "" );
		list.add( tmp );
		
		return list.iterator();
		// End Mock code
	}

	/**
	* 
	*/
	public static SupervisionEventType find( String eventTypeId )
	{
		Home home = new Home();
		return (SupervisionEventType)home.find( eventTypeId, SupervisionEventType.class );
	}	
}
