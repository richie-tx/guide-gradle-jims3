package pd.supervision.administerserviceprovider;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;

import messaging.administerserviceprovider.CreateServiceEvent;
import messaging.calendar.ICalendarContext;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileEventTypeCode;

public class Service extends PersistentObject implements ICalendarContext {
	private double cost;
	private int maxEnrollment;
	private String providerProgramId;
	/**
	* Properties for rate
	* @referencedType pd.codetable.Code
	* @contextKey RATE
	* @detailerDoNotGenerate true
	*/
	private Code rate = null;
	private String rateId;
	private String serviceCode;
	/**
	* Properties for serviceEvents
	* @referencedType pd.supervision.calendar.ServiceEvent
	* @detailerDoNotGenerate true
	*/
	private java.util.Collection serviceEvents = null;
	private String serviceId;
	/**
	* Properties for serviceLocations
	* @referencedType pd.supervision.administerserviceprovider.ServiceLocation
	* @detailerDoNotGenerate true
	*/
	private java.util.Collection serviceLocations = null;
	private String serviceName;
	/**
	* Properties for serviceType
	* @referencedType pd.codetable.Code
	* @contextKey SERVICE_TYPE
	* @detailerDoNotGenerate true
	*/
	private JuvenileEventTypeCode serviceType = null;
	private String serviceTypeId;

	/**
	* Properties for rate
	* @referencedType pd.codetable.Code
	* @contextKey SERVICE_STATUS
	* @detailerDoNotGenerate true
	*/
	private Code status = null;
	private String statusId;
	
	private String description;	
	private Date statusChangeDate;
	private Date endDate;
	/**
	* @roseuid 447357EA0318
	*/
	public Service() {
	}
	/**
	* Clears all pd.supervision.calendar.ServiceEvent from class relationship collection.
	*/
	public void clearServiceEvents() {
		initServiceEvents();
		serviceEvents.clear();
	}
	/**
	* Clears all pd.supervision.administerserviceprovider.ServiceLocation from class relationship collection.
	*/
	public void clearServiceLocations() {
		initServiceLocations();
		serviceLocations.clear();
	}
	public Integer getCalendarEventContextId()  {
		fetch();
		return null;
	}
	public String getProbationOfficerId() {
		fetch();
		return null;
	}
	public String getCaseFileId() {
		fetch();
		return null;
	}
	public String getJuvenileId() {
		fetch();
		return null;
	}
	public Integer getCalendarEventId() {
		fetch();
		return null;
	}
	/**
	* Access method for the cost property.
	* @return the current value of the cost property
	*/
	public double getCost() {
		fetch();
		return cost;
	}
	/**
	* Access method for the maxEnrollment property.
	* @return the current value of the maxEnrollment property
	*/
	public int getMaxEnrollment() {
		fetch();
		return maxEnrollment;
	}
	/**
	* Get the reference value to class :: pd.supervision.administerserviceprovider.ProviderProgram
	*/
	public String getProviderProgramId() {
		fetch();
		return providerProgramId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getRate() {
		fetch();
		initRate();
		return rate;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getRateId() {
		fetch();
		return rateId;
	}
	/**
	* Access method for the serviceCode property.
	* @return the current value of the serviceCode property
	*/
	public String getServiceCode() {
		fetch();
		return serviceCode;
	}
	/**
	* returns a collection of pd.supervision.calendar.ServiceEvent
	*/
	public java.util.Collection getServiceEvents() {
		initServiceEvents();
		return serviceEvents;
	}
	/**
	* Get the reference value to serviceId
	*/
	public String getServiceId() {
		return "" + getOID();
	}
	/**
	* returns a collection of pd.supervision.administerserviceprovider.ServiceLocation
	*/
	public java.util.Collection getServiceLocations() {
		fetch();
		initServiceLocations();
		return serviceLocations;
	}
	/**
	* Access method for the serviceName property.
	* @return the current value of the serviceName property
	*/
	public String getServiceName() {
		fetch();
		return serviceName;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public JuvenileEventTypeCode getServiceType() {
		fetch();
		initServiceType();
		return serviceType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getServiceTypeId() {
		fetch();
		return serviceTypeId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRate() {
		if (rate == null) {
			rate = (Code) new mojo.km.persistence.Reference(rateId, Code.class, "RATE").getObject();
		}
	}
	/**
	* Initialize class relationship implementation for pd.supervision.calendar.ServiceEvent
	*/
	private void initServiceEvents() {
		if (serviceEvents == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			serviceEvents = new mojo.km.persistence.ArrayList(pd.supervision.calendar.ServiceEvent.class, "serviceId", "" + getOID());
		}
	}
	/**
	* Initialize class relationship implementation for pd.supervision.administerserviceprovider.ServiceLocation
	*/
	private void initServiceLocations() {
		if (serviceLocations == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			serviceLocations = new mojo.km.persistence.ArrayList(ServiceLocation.class, "serviceId", "" + getOID());
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initServiceType() {
		if (serviceType == null) {
			serviceType = (JuvenileEventTypeCode) new mojo.km.persistence.Reference(serviceTypeId, JuvenileEventTypeCode.class).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "SERVICE_STATUS").getObject();
		}
	}
	/**
	* insert a pd.supervision.calendar.ServiceEvent into class relationship collection.
	*/
	public void insertServiceEvents(pd.supervision.calendar.ServiceEvent anObject) {
		initServiceEvents();
		serviceEvents.add(anObject);
	}
	/**
	* insert a pd.supervision.administerserviceprovider.ServiceLocation into class relationship collection.
	*/
	public void insertServiceLocations(ServiceLocation anObject) {
		initServiceLocations();
		serviceLocations.add(anObject);
	}
	/**
	* Removes a pd.supervision.calendar.ServiceEvent from class relationship collection.
	*/
	public void removeServiceEvents(pd.supervision.calendar.ServiceEvent anObject) {
		initServiceEvents();
		serviceEvents.remove(anObject);
	}
	/**
	* Removes a pd.supervision.administerserviceprovider.ServiceLocation from class relationship collection.
	*/
	public void removeServiceLocations(ServiceLocation anObject) {
		initServiceLocations();
		serviceLocations.remove(anObject);
	}
	/**
	* Sets the value of the cost property.
	* @param aCost the new value of the cost property
	*/
	public void setCost(double aCost) {
		if (this.cost != aCost) {
			markModified();
		}
		cost = aCost;
	}
	/**
	* Sets the value of the maxEnrollment property.
	* @param aMaxEnrollment the new value of the maxEnrollment property
	*/
	public void setMaxEnrollment(int aMaxEnrollment) {
		if (this.maxEnrollment != aMaxEnrollment) {
			markModified();
		}
		maxEnrollment = aMaxEnrollment;
	}
	/**
	* Set the reference value to class :: pd.supervision.administerserviceprovider.ProviderProgram
	*/
	public void setProviderProgramId(String providerProgramId) {
		if (this.providerProgramId == null || !this.providerProgramId.equals(providerProgramId)) {
			markModified();
		}
		this.providerProgramId = providerProgramId;
	}
	/**
	* set the type reference for class member rate
	*/
	public void setRate(Code rate) {
		if (this.rate == null || !this.rate.equals(rate)) {
			markModified();
		}
		if (rate.getOID() == null) {
			new mojo.km.persistence.Home().bind(rate);
		}
		setRateId("" + rate.getOID());
		rate.setContext("RATE");
		this.rate = (Code) new mojo.km.persistence.Reference(rate).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setRateId(String rateId) {
		if (this.rateId == null || !this.rateId.equals(rateId)) {
			markModified();
		}
		rate = null;
		this.rateId = rateId;
	}
	/**
	* Sets the value of the serviceCode property.
	* @param aServiceCode the new value of the serviceCode property
	*/
	public void setServiceCode(String aServiceCode) {
		if (this.serviceCode == null || !this.serviceCode.equals(aServiceCode)) {
			markModified();
		}
		serviceCode = aServiceCode;
	}
	/**
	* Set the reference value to serviceId
	*/
	public void setServiceId(String serviceId) {
		if (this.serviceId == null || !this.serviceId.equals(serviceId)) {
			markModified();
		}
		this.serviceId = serviceId;
	}
	/**
	* Sets the value of the serviceName property.
	* @param aServiceName the new value of the serviceName property
	*/
	public void setServiceName(String aServiceName) {
		if (this.serviceName == null || !this.serviceName.equals(aServiceName)) {
			markModified();
		}
		serviceName = aServiceName;
	}
	/**
	* set the type reference for class member serviceType
	*/
	public void setServiceType(JuvenileEventTypeCode serviceType) {
		if (this.serviceType == null || !this.serviceType.equals(serviceType)) {
			markModified();
		}
		if (serviceType.getOID() == null) {
			new mojo.km.persistence.Home().bind(serviceType);
		}
		setServiceTypeId("" + serviceType.getOID());		
		this.serviceType = (JuvenileEventTypeCode) new mojo.km.persistence.Reference(serviceType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setServiceTypeId(String serviceTypeId) {
		if (this.serviceTypeId == null || !this.serviceTypeId.equals(serviceTypeId)) {
			markModified();
		}
		serviceType = null;
		this.serviceTypeId = serviceTypeId;
	}
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		status.setContext("SERVICE_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,Service.class);
	}
	
	/**
	 * @return Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(Service.class);
	}
	
	/**
	 * @param event
	 * @return Iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event,Service.class);
	}
	
	/**
	* @return pd.supervision.administerserviceprovider.Service
	* @param serviceCode
	* @roseuid 4107B06D01B5
	*/
	static public Service find(String serviceId) {
		IHome home = new Home();
		return (Service) home.find(serviceId, Service.class);
	}
	public void setService(CreateServiceEvent createEvent) {
		
		this.setServiceName(createEvent.getServiceName());
		this.setServiceCode(createEvent.getServiceCode());
		this.setServiceTypeId(createEvent.getType());
		
		this.setProviderProgramId(createEvent.getProviderProgramId());
		this.setRateId(createEvent.getRateId());
		
		this.setMaxEnrollment((new Integer(createEvent.getMaxErollment())).intValue());

		this.setDescription(createEvent.getDescription());
		this.setStatusId(createEvent.getStatusId());
		if(createEvent.getServiceCost()!= null) {	
			if(!createEvent.getServiceCost().equals(""))
			{
				Double d = new Double(createEvent.getServiceCost());
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMinimumFractionDigits(2); 
				String a = df.format(d);
				double AA = Double.parseDouble(a);
				this.setCost(AA);
	//			this.setCost((new Integer(createEvent.getServiceCost())).intValue());
			}
			else
				this.setCost(0.00);			
		}
		Date endDate = null;
		if(createEvent.getProviderProgramId() != null && !(createEvent.getProviderProgramId().equals(""))) {
			ProviderProgram providerProgram = ProviderProgram.find(createEvent.getProviderProgramId());
			endDate = providerProgram.getEndDate();
		}	
		if(endDate != null) {
			this.setEndDate(endDate);
		}
	}	
	public void createOID() {
		new Home().bind(this);
	}
	public void updateServiceStatus(CreateServiceEvent createEvent) {
		this.setStatusId(createEvent.getStatusId());
		this.setStatusChangeDate(DateUtil.getCurrentDate());		
	}
	
	public String getDescription() {
		fetch();
		return description;
	}	
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}		
	public Date getStatusChangeDate() {
		fetch();
		return statusChangeDate;
	}		
	public void setStatusChangeDate(Date aStatusChangeDate) {
		if (this.statusChangeDate == null || !this.statusChangeDate.equals(aStatusChangeDate)) {
			markModified();
		}
		statusChangeDate = aStatusChangeDate;
	}	
	/**
	* Access method for the endDate property.
	* @return the current value of the endDate property
	*/
	public Date getEndDate() {
		fetch();
		return endDate;
	}
	/**
	* Sets the value of the endDate property.
	* @param aEndDate the new value of the endDate property
	*/
	public void setEndDate(Date aEndDate) {
		if (this.endDate == null || !this.endDate.equals(aEndDate)) {
			markModified();
		}
		endDate = aEndDate;
	}
	
}
