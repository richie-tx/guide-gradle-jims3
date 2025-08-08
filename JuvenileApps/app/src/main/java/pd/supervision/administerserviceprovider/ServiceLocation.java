//01/11/07 UGopinath modified to add location unit

package pd.supervision.administerserviceprovider;

import java.util.Iterator;

import messaging.administerserviceprovider.CreateServiceLocationRequestEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
/**
* Properties for location
*/
public class ServiceLocation extends PersistentObject {
	/**
	* Properties for service
	*/
	private Service service = null;
	private String serviceId;	
	private String juvLocUnitId;
	/**
	* Properties for location
	*/
	private Location location = null;
	private JuvLocationUnit locationUnit = null;
	public ServiceLocation() {
	}
	
	
	

	
	/**
	* Set the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	*/
	public void setJuvLocUnitId(String juvUnitId) {
		if (this.juvLocUnitId == null || !this.juvLocUnitId.equals(juvUnitId)) {
			markModified();
		}
		locationUnit = null;
		this.juvLocUnitId = juvUnitId;
	}
	/**
	* Get the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	*/
	public String getJuvLocUnitId() {
		fetch();
		return juvLocUnitId;
	}
	/**
	* Initialize class relationship to class pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	*/
	private void initLocationUnit() {
		if (locationUnit == null) {
			locationUnit =
				(JuvLocationUnit) new mojo
					.km
					.persistence
					.Reference(juvLocUnitId, JuvLocationUnit.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	*/
	public JuvLocationUnit getLocationUnit() {
		fetch();
		initLocationUnit();
		return locationUnit;
	}
	/**
	* set the type reference for class member location unit
	*/
	public void setLocationUnit(JuvLocationUnit locUnit) {
		if (this.locationUnit == null || !this.locationUnit.equals(locUnit)) {
			markModified();
		}
		if (locationUnit.getOID() == null) {
			new mojo.km.persistence.Home().bind(locationUnit);
		}
		setJuvLocUnitId("" + locationUnit.getOID());
		this.locationUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(locationUnit).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.administerserviceprovider.Service
	*/
	public void setServiceId(String serviceId) {
		if (this.serviceId == null || !this.serviceId.equals(serviceId)) {
			markModified();
		}
		this.serviceId = serviceId;
	}
	/**
	* Get the reference value to class :: pd.supervision.administerserviceprovider.Service
	*/
	public String getServiceId() {
		fetch();
		return serviceId;
	}
	/**
	* Initialize class relationship to class pd.supervision.administerserviceprovider.Service
	*/
	private void initService() {
		if (service == null) {
			service =
				(Service) new mojo
					.km
					.persistence
					.Reference(serviceId, Service.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.administerserviceprovider.Service
	*/
	public Service getService() {
		initService();
		return service;
	}
	/**
	* set the type reference for class member service
	*/
	public void setService(Service service) {
		if (this.service == null || !this.service.equals(service)) {
			markModified();
		}
		if (service.getOID() == null) {
			new mojo.km.persistence.Home().bind(service);
		}
		setServiceId("" + service.getOID());
		this.service = (Service) new mojo.km.persistence.Reference(service).getObject();
	}
	
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */

	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,ServiceLocation.class);
	}
	
	/**
	 * @return Iterator
	 */

	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(ServiceLocation.class);
		
		
	}
	
	/**
		 * @param event
		 * @return
		 */

		public static Iterator findAll(IEvent event)
		{
			IHome home = new Home();
			return home.findAll(event,ServiceLocation.class);
			
		}

	
	public void setServiceLocation(CreateServiceLocationRequestEvent createEvent) {
		
		this.setJuvLocUnitId(createEvent.getJuvLocUnitId());
		this.setServiceId(createEvent.getServiceId());
	}	

	/**
	 * @param serviceLocationId
	 * @return
	 */
	public static ServiceLocation find(String serviceLocationId)
	{
		IHome home = new Home();
		return (ServiceLocation) home.find(serviceLocationId,ServiceLocation.class);
	}
	
	public void createOID() {
		new Home().bind(this);
	}
	
}
