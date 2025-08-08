package pd.juvenilewarrant;

import java.util.Date;
import java.util.Iterator;

import messaging.contact.user.to.UserBean;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilewarrant.GetJuvenileWarrantServiceInfoEvent;
import messaging.juvenilewarrant.SendInvalidAddressNotificationEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import naming.PDOfficerProfileConstants;
import pd.codetable.Code;
import pd.contact.agency.Department;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.notification.PDNotificationHelper;
import pd.notification.juvenilewarrant.PDJuvenileWarrantNotificationHelper;

/**
 * @author asrvastava
 */
public class JuvenileWarrantService extends PersistentObject
{
    /**
     * Properties for executorOfficerDepartment
     * 
     * @referencedType pd.contact.agency.Department
     * @detailerDoNotGenerate true
     */
    private Department executorOfficerDepartment;

    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey ADDRESS_TYPE
     * @detailerDoNotGenerate true
     */
    private Code addressType = null;

    /**
     * Properties for state
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STATE_ABBR
     * @detailerDoNotGenerate true
     */
    private Code state = null;

    private String executorOfficerId;

    private String executorOfficerDepartmentId;

    private String apartmentNum;

    private Date serviceTimeStamp;

    private Date expirationTimeStamp;

    private String comments;

    private String zipCode;

    private String streetName;

    /**
     * Properties for serviceStatus
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SERVICE_STATUS
     * @detailerDoNotGenerate true
     */
    private Code serviceStatus = null;

    private boolean isBadAddress;

    private String juvenileWarrantId;

    private String stateId;

    private String addressTypeId;

    private Double mileage;

    /**
     * Properties for streetType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STREET_TYPE
     * @detailerDoNotGenerate true
     */
    private Code streetType = null;

    private String streetNum;

    private Double airFare;

    private String streetTypeId;

    private String additionalZipCode;

    private String serviceStatusId;

    private String city;

    private Double perDiem;

    /**
     * Properties for county
     * 
     * @referencedType pd.codetable.Code
     * @contextKey COUNTY
     * @detailerDoNotGenerate true
     */
    private Code county = null;

    /**
     * Properties for juvenileWarrant
     * 
     * @referencedType pd.juvenilewarrant.JuvenileWarrant
     * @detailerDoNotGenerate true
     */
    private JuvenileWarrant juvenileWarrant = null;

    /**
     * Properties for executorOfficer
     * 
     * @referencedType pd.contact.officer.OfficerProfile
     * @detailerDoNotGenerate true
     */
    private OfficerProfile executorOfficer = null;

    private String countyId;

    /**
     * @roseuid 41FA7D58005D
     */
    public JuvenileWarrantService()
    {
    }

    /**
     * @return warrantService
     * @param serviceId
     */
    static public JuvenileWarrantService find(String serviceId)
    {
        JuvenileWarrantService warrantService = null;
        IHome home = new Home();
        warrantService = (JuvenileWarrantService) home.find(serviceId, JuvenileWarrantService.class);
        return warrantService;
    }

    /**
     * returns the successful JuvenileWarrantService
     * 
     * @return warrantService
     * @param serviceId
     */
    static public JuvenileWarrantService findSuccessfulService(GetJuvenileWarrantServiceInfoEvent requestEvent)
    {
        Iterator services = JuvenileWarrantService.findAll(requestEvent);

        // There will be only one successful Service
        JuvenileWarrantService service = null;
        if (services.hasNext())
        {
            service = (JuvenileWarrantService) services.next();
        }
        return service;
    }

    /**
     * returns the successful JuvenileWarrantService
     * 
     * @return JuvenileWarrantService
     * @param warrantNum
     */
    static public JuvenileWarrantService findSuccessfulService(String warrantNum)
    {
        GetJuvenileWarrantServiceInfoEvent requestEvent = new GetJuvenileWarrantServiceInfoEvent();
        requestEvent.setWarrantNum(warrantNum);
        JuvenileWarrantService service = findSuccessfulService(requestEvent);
        return service;
    }

    /**
     * @return JuvenileWarrantService
     * @param event
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, JuvenileWarrantService.class);
    }

    /**
     * Used to send notification for the bad address
     */
    public void sendBadAddressNotification()
    {
        markModified();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        String fromEmail = PDNotificationHelper.getNotificationEmail("Notification_EmailFrom");
        String toEmail = PDNotificationHelper.getNotificationEmail("Notification_Bad_Address");
        SendInvalidAddressNotificationEvent notificationEvent = createInvalidAddressNotificationEvent(fromEmail,
                toEmail);
        notificationEvent.setWarrantNum(this.getJuvenileWarrantId());
        notificationEvent.setOfficerId(this.getExecutorOfficerId());
        //Agency executorAgency =
        // Agency.find(this.getExecutorOfficer().getAgencyId());
        //notificationEvent.setAgencyName(executorAgency.getAgencyName());
        notificationEvent.setServiceCity(this.getCity());
        notificationEvent.setServiceStreetName(this.getStreetName());
        notificationEvent.setServiceStreetNum(this.getStreetNum());
        String azipCode = this.getZipCode();
        String ladditionalZipCode = this.getAdditionalZipCode();
        if (ladditionalZipCode != null)
        {
            azipCode = azipCode + "-" + ladditionalZipCode;
        }
        notificationEvent.setServiceZipCode(azipCode);
        RegisterTaskEvent rtEvent = new RegisterTaskEvent();
        rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
        rtEvent.setFirstNotificationDate(DateUtil.getCurrentDate());
        rtEvent.setNextNotificationDate(DateUtil.getCurrentDate());
        String taskName = PDJuvenileWarrantNotificationHelper.getTaskName(notificationEvent.getClass().getName(),
                notificationEvent.getWarrantNum(), PDNotificationConstants.SERVICE_INVALID_ADDRESS);
        rtEvent.setTaskName(taskName);
        rtEvent.setNotificationEvent(notificationEvent);
        dispatch.postEvent(rtEvent);
    }

    /**
     * @return event
     * @param from
     * @param to
     */
    private SendInvalidAddressNotificationEvent createInvalidAddressNotificationEvent(String from, String to)
    {
        markModified();
        SendInvalidAddressNotificationEvent event = new SendInvalidAddressNotificationEvent();
        event.setEmailFrom(from);
        event.setEmailTo(to);
        return event;
    }

    /**
     * @author asrvastava This method is used to send successful warrant service
     *         notification.
     */
    public void sendSuccessfulServiceNotification(JuvenileWarrant warrant)
    {

        OfficerProfile officer = null;
        UserProfile warrantOriginator = null;
        Code code = null;
        Department department = null;

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
                .getInstance("CreateNotification");

        if (warrant != null)
        {
            officer = warrant.getOfficer();
            warrantOriginator = warrant.getWarrantOriginatorUser();
            code = warrant.getWarrantType();
            department = officer.getDepartment();
        }
        UserBean userBean = new UserBean();

        userBean.setFirstName(officer.getFirstName());
        userBean.setLastName(officer.getLastName());

        if (department == null)
        {
            userBean.setDepartmentName("Department not Found");
        }
        else
        {
            userBean.setDepartmentName(department.getDepartmentName());
        }

        StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrant.getWarrantNum());
    	buffer.append(", ");
    	buffer.append("SERVED");
    	buffer.append(" for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());

    	notificationEvent.setSubject(buffer.toString());
        //notificationEvent.setSubject("Send notification that warrant has been served");

        notificationEvent.setNotificationTopic("JW.WARRANT.SERVED");
        notificationEvent.addIdentity("warrantOriginator", (IAddressable) warrantOriginator);
        notificationEvent.addContentBean(userBean);
        // This bean sends the warrantType Code.
        notificationEvent.addContentBean(code);
        notificationEvent.addContentBean(officer);
        notificationEvent.addContentBean(warrant);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);

    }

    /**
     * Access method for the airFare property.
     * 
     * @return the current value of the airFare property
     */
    public Double getAirFare()
    {
        fetch();
        return airFare;
    }

    /**
     * Sets the value of the airFare property.
     * 
     * @param aAirFare
     *            the new value of the airFare property
     */
    public void setAirFare(Double aAirFare)
    {
        if (this.airFare == null || !this.airFare.equals(aAirFare))
        {
            markModified();
        }
        airFare = aAirFare;
    }

    /**
     * Access method for the perDiem property.
     * 
     * @return the current value of the perDiem property
     */
    public Double getPerDiem()
    {
        fetch();
        return perDiem;
    }

    /**
     * Sets the value of the perDiem property.
     * 
     * @param aPerDiem
     *            the new value of the perDiem property
     */
    public void setPerDiem(Double aPerDiem)
    {
        if (this.perDiem == null || !this.perDiem.equals(aPerDiem))
        {
            markModified();
        }
        perDiem = aPerDiem;
    }

    /**
     * Access method for the mileage property.
     * 
     * @return the current value of the mileage property
     */
    public Double getMileage()
    {
        fetch();
        return mileage;
    }

    /**
     * Sets the value of the mileage property.
     * 
     * @param aMileage
     *            the new value of the mileage property
     */
    public void setMileage(Double aMileage)
    {
        if (this.mileage == null || !this.mileage.equals(aMileage))
        {
            markModified();
        }
        mileage = aMileage;
    }

    /**
     * Access method for the comments property.
     * 
     * @return the current value of the comments property
     */
    public String getComments()
    {
        fetch();
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param aComments
     *            the new value of the comments property
     */
    public void setComments(String aComments)
    {
        if (this.comments == null || !this.comments.equals(aComments))
        {
            markModified();
        }
        comments = aComments;
    }

    /**
     * Access method for the serviceTimeStamp property.
     * 
     * @return the current value of the serviceTimeStamp property
     */
    public Date getServiceTimeStamp()
    {
        fetch();
        return serviceTimeStamp;
    }

    /**
     * Sets the value of the serviceTimeStamp property.
     * 
     * @param aServiceTimeStamp
     *            the new value of the serviceTimeStamp property
     */
    public void setServiceTimeStamp(Date aServiceTimeStamp)
    {
        if (this.serviceTimeStamp == null || !this.serviceTimeStamp.equals(aServiceTimeStamp))
        {
            markModified();
        }
        serviceTimeStamp = aServiceTimeStamp;
    }

    /**
     * Access method for the serviceTimeStamp property.
     * 
     * @return the current value of the serviceTimeStamp property
     */
    public Date getExpirationTimeStamp()
    {
        fetch();
        return this.expirationTimeStamp;
    }

    /**
     * Sets the value of the serviceTimeStamp property.
     * 
     * @param aServiceTimeStamp
     *            the new value of the serviceTimeStamp property
     */
    public void setExpirationTimeStamp(Date anExpirationTimeStamp)
    {
        if (this.expirationTimeStamp == null || !this.expirationTimeStamp.equals(anExpirationTimeStamp))
        {
            markModified();
        }
        this.expirationTimeStamp = anExpirationTimeStamp;
    }

    /**
     * Access method for the streetNum property.
     * 
     * @return the current value of the streetNum property
     */
    public String getStreetNum()
    {
        fetch();
        return streetNum;
    }

    /**
     * Sets the value of the streetNum property.
     * 
     * @param aStreetNum
     *            the new value of the streetNum property
     */
    public void setStreetNum(String aStreetNum)
    {
        if (this.streetNum == null || !this.streetNum.equals(aStreetNum))
        {
            markModified();
        }
        streetNum = aStreetNum;
    }

    /**
     * Access method for the streetName property.
     * 
     * @return the current value of the streetName property
     */
    public String getStreetName()
    {
        fetch();
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param aStreetName
     *            the new value of the streetName property
     */
    public void setStreetName(String aStreetName)
    {
        if (this.streetName == null || !this.streetName.equals(aStreetName))
        {
            markModified();
        }
        streetName = aStreetName;
    }

    /**
     * Access method for the city property.
     * 
     * @return the current value of the city property
     */
    public String getCity()
    {
        fetch();
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param aCity
     *            the new value of the city property
     */
    public void setCity(String aCity)
    {
        if (this.city == null || !this.city.equals(aCity))
        {
            markModified();
        }
        city = aCity;
    }

    /**
     * Access method for the state property.
     * 
     * @return the current value of the state property
     */
    public Code getState()
    {
        fetch();
        initState();
        return state;
    }

    /**
     * Access method for the zipCode property.
     * 
     * @return the current value of the zipCode property
     */
    public String getZipCode()
    {
        fetch();
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param aZipCode
     *            the new value of the zipCode property
     */
    public void setZipCode(String aZipCode)
    {
        if (this.zipCode == null || !this.zipCode.equals(aZipCode))
        {
            markModified();
        }
        zipCode = aZipCode;
    }

    /**
     * Determines if the isBadAddress property is true.
     * 
     * @return <code>true</code> if the isBadAddress property is true
     */
    public boolean getIsBadAddress()
    {
        fetch();
        return isBadAddress;
    }

    /**
     * Sets the value of the isBadAddress property.
     * 
     * @param aIsBadAddress
     *            the new value of the isBadAddress property
     */
    public void setIsBadAddress(boolean aIsBadAddress)
    {
        if (this.isBadAddress != aIsBadAddress)
        {
            markModified();
        }
        isBadAddress = aIsBadAddress;
    }

    /**
     * Access method for the serviceStatus property.
     * 
     * @return the current value of the serviceStatus property
     */
    public Code getServiceStatus()
    {
        fetch();
        initServiceStatus();
        return serviceStatus;
    }

    /**
     * Access method for the executorOfficer property.
     * 
     * @return the current value of the executorOfficer property
     */
    public OfficerProfile getExecutorOfficer()
    {
        fetch();
        initExecutorOfficer();
        return executorOfficer;
    }

    /**
     * Access method for the executorOfficer property.
     * 
     * @return the current value of the executorOfficer property
     */
    public Department getExecutorOfficerDepartment()
    {
        fetch();
        initExecutorOfficerDepartment();
        return executorOfficerDepartment;
    }

    /**
     * Initialize class relationship to class pd.contact.agency.Department
     */
    private void initExecutorOfficerDepartment()
    {
        if (executorOfficerDepartment == null)
        {
            executorOfficerDepartment = Department.find(executorOfficerDepartmentId); //87191//(pd.contact.agency.Department) new mojo.km.persistence.Reference(
                   // executorOfficerDepartmentId, pd.contact.agency.Department.class).getObject();
        }
    }

    /**
     * @param serviceZipCode
     * @param serviceStreetNum
     * @param serviceStreetName
     * @param serviceID
     * @param serviceDate
     * @param serviceCity
     * @param perDiem
     * @param mileage
     * @param executorId
     * @param airFare
     * @roseuid 41F1732B002E
     */
    public void save()
    {
        markModified();
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String serviceStatusId
     */
    public String getServiceStatusId()
    {
        fetch();
        return serviceStatusId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initServiceStatus()
    {
        if (serviceStatus == null)
        {
            serviceStatus = (Code) new mojo.km.persistence.Reference(serviceStatusId,
                    Code.class, "SERVICE_STATUS").getObject();
        }
    }

    /**
     * set the type reference for class member serviceStatus
     * 
     * @param serviceStatus
     */
    public void setServiceStatus(Code aserviceStatus)
    {
        if (this.serviceStatus == null || !this.serviceStatus.equals(aserviceStatus))
        {
            markModified();
        }
        if (aserviceStatus.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(aserviceStatus);
        }
        setServiceStatusId("" + aserviceStatus.getOID());
        this.serviceStatus = (Code) new mojo.km.persistence.Reference(aserviceStatus).getObject();
    }

    /**
     * Set the reference value to class :: pd.contact.officer.OfficerProfile
     * 
     * @param executorOfficerId
     */
    public void setExecutorOfficerId(String aexecutorOfficerId)
    {
        if (this.executorOfficerId == null || !this.executorOfficerId.equals(aexecutorOfficerId))
        {
            markModified();
        }
        executorOfficer = null;
        this.executorOfficerId = aexecutorOfficerId;
    }

    /**
     * Get the reference value to class :: pd.contact.contact.OfficerProfile
     * 
     * @return String executorOfficerId
     */
    public String getExecutorOfficerId()
    {
        fetch();
        return executorOfficerId;
    }

    /**
     * Initialize class relationship to class pd.contact.officer.OfficerProfile
     */
    private void initExecutorOfficer()
    {
        if (executorOfficer == null)
        {
            executorOfficer = (OfficerProfile) new mojo.km.persistence.Reference(executorOfficerId,
                    OfficerProfile.class).getObject();
        }
    }

    /**
     * set the type reference for class member executorOfficer
     * 
     * @param executorOfficer
     */
    public void setExecutorOfficer(OfficerProfile aexecutorOfficer)
    {
        if (this.executorOfficer == null || !this.executorOfficer.equals(aexecutorOfficer))
        {
            markModified();
        }
        if (aexecutorOfficer.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(aexecutorOfficer);
        }
        setExecutorOfficerId("" + aexecutorOfficer.getOID());
        this.executorOfficer = (OfficerProfile) new mojo.km.persistence.Reference(aexecutorOfficer)
                .getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @param serviceStatusId
     */
    public void setServiceStatusId(String aserviceStatusId)
    {
        if (this.serviceStatusId == null || !this.serviceStatusId.equals(aserviceStatusId))
        {
            markModified();
        }
        serviceStatus = null;
        this.serviceStatusId = aserviceStatusId;
    }

    /**
     * Set the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
     * 
     * @param juvenileWarrantId
     */
    public void setJuvenileWarrantId(String ajuvenileWarrantId)
    {
        if (this.juvenileWarrantId == null || !this.juvenileWarrantId.equals(ajuvenileWarrantId))
        {
            markModified();
        }
        juvenileWarrant = null;
        this.juvenileWarrantId = ajuvenileWarrantId;
    }

    /**
     * Get the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
     * 
     * @return String juvenileWarrantId
     */
    public String getJuvenileWarrantId()
    {
        fetch();
        return juvenileWarrantId;
    }

    /**
     * Initialize class relationship to class pd.juvenilewarrant.JuvenileWarrant
     */
    private void initJuvenileWarrant()
    {
        if (juvenileWarrant == null)
        {
            juvenileWarrant = (JuvenileWarrant) new mojo.km.persistence.Reference(juvenileWarrantId,
                    JuvenileWarrant.class).getObject();
        }
    }

    /**
     * Gets referenced type pd.juvenilewarrant.JuvenileWarrant
     * 
     * @return JuvenileWarrant juvenileWarrant
     */
    public JuvenileWarrant getJuvenileWarrant()
    {
        fetch();
        initJuvenileWarrant();
        return juvenileWarrant;
    }

    /**
     * set the type reference for class member juvenileWarrant
     * 
     * @param juvenileWarrant
     */
    public void setJuvenileWarrant(JuvenileWarrant ajuvenileWarrant)
    {
        if (this.juvenileWarrant == null || !this.juvenileWarrant.equals(ajuvenileWarrant))
        {
            markModified();
        }
        if (ajuvenileWarrant.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(ajuvenileWarrant);
        }
        setJuvenileWarrantId("" + ajuvenileWarrant.getOID());
        this.juvenileWarrant = (JuvenileWarrant) new mojo.km.persistence.Reference(ajuvenileWarrant)
                .getObject();
    }

    /**
     * @return Code addressType
     */
    public Code getAddressType()
    {
        fetch();
        initAddressType();
        return addressType;
    }

    /**
     * @return Code streetType
     */
    public Code getStreetType()
    {
        fetch();
        initStreetType();
        return streetType;
    }

    /**
     * @return String apartmentNum
     */
    public String getApartmentNum()
    {
        fetch();
        return apartmentNum;
    }

    /**
     * @param string
     */
    public void setApartmentNum(String string)
    {
        if (this.apartmentNum == null || !this.apartmentNum.equals(string))
        {
            markModified();
        }
        apartmentNum = string;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setAddressTypeId(String laddressTypeId)
    {
        if (this.addressTypeId == null || !this.addressTypeId.equals(laddressTypeId))
        {
            markModified();
        }
        addressType = null;
        this.addressTypeId = laddressTypeId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String addressTypeId
     */
    public String getAddressTypeId()
    {
        fetch();
        return addressTypeId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initAddressType()
    {
        if (addressType == null)
        {
            addressType = (Code) new mojo.km.persistence.Reference(addressTypeId, Code.class,
        	    PDCodeTableConstants.ADDRESS_TYPE).getObject();
        }
    }

    /**
     * set the type reference for class member addressType
     */
    public void setAddressType(Code laddressType)
    {
        if (this.addressType == null || !this.addressType.equals(laddressType))
        {
            markModified();
        }
        if (laddressType.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(laddressType);
        }
        setAddressTypeId("" + laddressType.getOID());
        this.addressType = (Code) new mojo.km.persistence.Reference(laddressType).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStreetTypeId(String lstreetTypeId)
    {
        if (this.streetTypeId == null || !this.streetTypeId.equals(lstreetTypeId))
        {
            markModified();
        }
        streetType = null;
        this.streetTypeId = lstreetTypeId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String streetTypeId
     */
    public String getStreetTypeId()
    {
        fetch();
        return streetTypeId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStreetType()
    {
        if (streetType == null)
        {
            streetType = (Code) new mojo.km.persistence.Reference(streetTypeId, Code.class,
        	    PDCodeTableConstants.STREET_TYPE).getObject();
        }
    }

    /**
     * set the type reference for class member streetType
     */
    public void setStreetType(Code lstreetType)
    {
        if (this.streetType == null || !this.streetType.equals(lstreetType))
        {
            markModified();
        }
        if (lstreetType.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lstreetType);
        }
        setStreetTypeId("" + lstreetType.getOID());
        this.streetType = (Code) new mojo.km.persistence.Reference(lstreetType).getObject();
    }

    /**
     * @return Iterator services
     * @param attrName
     * @param attrValue
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        Iterator services = null;
        services = home.findAll(attrName, attrValue, JuvenileWarrantService.class);
        return services;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStateId(String lstateId)
    {
        if (this.stateId == null || !this.stateId.equals(lstateId))
        {
            markModified();
        }
        state = null;
        this.stateId = lstateId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String stateId
     */
    public String getStateId()
    {
        fetch();
        return stateId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initState()
    {
        if (state == null)
        {
            state = (Code) new mojo.km.persistence.Reference(stateId, Code.class,
        	    PDCodeTableConstants.STATE_ABBR).getObject();
        }
    }

    /**
     * set the type reference for class member state
     */
    public void setState(Code lstate)
    {
        if (this.state == null || !this.state.equals(lstate))
        {
            markModified();
        }
        if (lstate.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lstate);
        }
        setStateId("" + lstate.getOID());
        this.state = (Code) new mojo.km.persistence.Reference(lstate).getObject();
    }

    /**
     * @return String additionalZipCode
     */
    public String getAdditionalZipCode()
    {
        fetch();
        return additionalZipCode;
    }

    /**
     * @param string
     */
    public void setAdditionalZipCode(String lstring)
    {
        if (this.additionalZipCode == null || !this.additionalZipCode.equals(lstring))
        {
            markModified();
        }
        additionalZipCode = lstring;
    }

    /**
     * @return Code county
     */
    public Code getCounty()
    {
        fetch();
        initCounty();
        return county;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setCountyId(String acountyId)
    {
        if (this.countyId == null || !this.countyId.equals(acountyId))
        {
            markModified();
        }
        county = null;
        this.countyId = acountyId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String countyId
     */
    public String getCountyId()
    {
        fetch();
        return countyId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initCounty()
    {
        if (county == null)
        {
            county = (Code) new mojo.km.persistence.Reference(countyId, Code.class, PDCodeTableConstants.JUVENILE_COUNTY)
                    .getObject();
        }
    }

    /**
     * set the type reference for class member county
     */
    public void setCounty(Code acounty)
    {
        if (this.county == null || !this.county.equals(acounty))
        {
            markModified();
        }
        if (acounty.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(acounty);
        }
        setCountyId("" + acounty.getOID());
        this.county = (Code) new mojo.km.persistence.Reference(acounty).getObject();
    }

    /**
     * @return
     */
    public String getExecutorOfficerDepartmentId()
    {
        fetch();
        return executorOfficerDepartmentId;
    }

    /**
     * @param string
     */
    public void setExecutorOfficerDepartmentId(String string)
    {
        if (this.executorOfficerDepartmentId == null || !this.executorOfficerDepartmentId.equals(string))
        {
            markModified();
        }
        executorOfficerDepartment = null;
        this.executorOfficerDepartmentId = string;
    }

    public JuvenileWarrantServiceResponseEvent valueObject()
    {
        JuvenileWarrantServiceResponseEvent warrantServiceEvent = new JuvenileWarrantServiceResponseEvent();

        warrantServiceEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_SERVICE_EVENT_TOPIC);

        if (this.getAirFare() != null)
        {
            warrantServiceEvent.setAirFare(this.getAirFare().toString());
        }
        warrantServiceEvent.setBadAddress(this.getIsBadAddress());
        warrantServiceEvent.setCity(this.getCity());
        warrantServiceEvent.setComments(this.getComments());
        if (this.getMileage() != null)
        {
            warrantServiceEvent.setMileage(this.getMileage().toString());
        }
        if (this.getPerDiem() != null)
        {
            warrantServiceEvent.setPerDiem(this.getPerDiem().toString());
        }
        warrantServiceEvent.setServiceTimeStamp(this.getServiceTimeStamp());
        warrantServiceEvent.setServiceID(this.getOID().toString());
        warrantServiceEvent.setStreetName(this.getStreetName());
        warrantServiceEvent.setStreetNum(this.getStreetNum());
        warrantServiceEvent.setZipCode(this.getZipCode());
        warrantServiceEvent.setAdditionalZipCode(this.getAdditionalZipCode());
        warrantServiceEvent.setAptNumber(this.getApartmentNum());

        // officer info
        if (this.executorOfficerId != null)
        {
            OfficerProfile officer = this.getExecutorOfficer();

            if (officer.getDepartmentId() != null && officer.getDepartmentId().equals("") == false)
            {
                Department officerDept = officer.getDepartment();
                warrantServiceEvent.setExecutorAgencyName(officerDept.getDepartmentName());
            }

            warrantServiceEvent.setExecutorCellNum(officer.getCellPhone());
            warrantServiceEvent.setExecutorEmail(officer.getEmail());
            warrantServiceEvent.setExecutorFirstName(officer.getFirstName());
            warrantServiceEvent.setExecutorMiddleName(officer.getMiddleName());
            warrantServiceEvent.setExecutorLastName(officer.getLastName());
            warrantServiceEvent.setExecutorPager(officer.getPager());
            warrantServiceEvent.setExecutorPhoneNum(officer.getWorkPhoneNum());
            if (officer.getBadgeNum() != null && !officer.getBadgeNum().equals(""))
            {
                warrantServiceEvent.setExecutorOfficerId(officer.getBadgeNum());
                warrantServiceEvent.setExecutorIdType(PDOfficerProfileConstants.BADGE_NUM);
            }
            else
            {
                warrantServiceEvent.setExecutorOfficerId(officer.getOtherIdNum());
                warrantServiceEvent.setExecutorIdType(PDOfficerProfileConstants.ID_NUM);
            }
        }

        // service status
        String codeId = this.getServiceStatusId();
        if (codeId != null && !codeId.equals(""))
        {
            Code serviceCode = this.getServiceStatus();
            if (serviceCode != null)
            {
                warrantServiceEvent.setServiceStatusId(serviceCode.getCode());
                warrantServiceEvent.setServiceStatus(serviceCode.getDescription());
            }
        }

        // state
        codeId = this.getStateId();
        if (codeId != null && !codeId.equals(""))
        {
            Code stateCode = this.getState();
            if (stateCode != null)
            {
                warrantServiceEvent.setState(stateCode.getDescription());
            }
        }

        // address type
        codeId = this.getAddressTypeId();
        if (codeId != null && !codeId.equals(""))
        {
            Code addTypeCode = this.getAddressType();
            if (addTypeCode != null)
            {
                warrantServiceEvent.setAddressTypeId(addTypeCode.getCode());
                warrantServiceEvent.setAddressType(addTypeCode.getDescription());
            }
        }
        // streeet type
        codeId = this.getStreetTypeId();
        if (codeId != null && !codeId.equals(""))
        {
            Code stTypeCode = this.getStreetType();
            if (stTypeCode != null)
            {
                warrantServiceEvent.setStreetTypeId(stTypeCode.getCode());
                warrantServiceEvent.setStreetType(stTypeCode.getDescription());
            }
        }
        // county
        codeId = this.getCountyId();
        if (codeId != null && !codeId.equals(""))
        {
            Code cntCode = this.getAddressType();
            if (cntCode != null)
            {
                warrantServiceEvent.setCountyId(cntCode.getCode());
                warrantServiceEvent.setCounty(cntCode.getDescription());
            }
        }

        return warrantServiceEvent;
    }

}
