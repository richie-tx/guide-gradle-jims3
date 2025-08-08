package pd.supervision.calendar;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import naming.PDCalendarConstants;

import org.apache.commons.lang.StringUtils;
import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import pd.common.calendar.CalendarEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.km.util.Formatter;
import pd.supervision.administerserviceprovider.SP_Profile;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * Properties for eventStatus
 * 
 * @referencedType pd.codetable.Code
 * @contextKey SERVICEEVENT_STATUS
 * @detailerDoNotGenerate true
 */
public class ServiceEvent extends CalendarEvent
{
    private int serviceLocationId;

    private String serviceEventId;
    private Integer calendarEventId;
    private String serviceId;
    
    private String juvLocUnitId;
    private String facilityCd;
    private JuvLocationUnit juvLocUnit;

    private String interviewId ;
    private String schoolCd;
    private String memberAddressId;
    
    private Date startDateTime;
    private Date endDateTime;

    /**
     * Properties for eventType
     * 
     * @referencedType pd.codetable.criminal.JuvenileEventTypeCode
     * @detailerDoNotGenerate true
     */
    private JuvenileEventTypeCode eventType = null;
    private String eventTypeId;

    /**
     * Properties for serviceLocation
     * 
     * @referencedType pd.supervision.administerserviceprovider.ServiceLocation
     * @detailerDoNotGenerate true
     */
    private ServiceLocation serviceLocation = null;
    private String eventStatusId;

    /**
     * Properties for eventStatus
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SERVICEEVENT_STATUS
     * @detailerDoNotGenerate true
     */
    private Code eventStatus = null;

    /**
     * Properties for instructor
     * 
     * @referencedType pd.supervision.administerserviceprovider.SP_Profile
     * @detailerDoNotGenerate true
     */
    private SP_Profile instructor = null;

    private int eventMaximum;
    private int eventMinimum;
    private int currentEnrollment;

    private String instructorId;
    private String eventComments;
    private String serviceName;
    private String programReferralId;

    private Collection serviceEventContexts = null;
    
    //<KISHORE>JIMS200059078 : Calendar: Add new event type Job Visit (PD) - KK
    private String memberEmploymentId;
    
    private String contactLastName;
    private String contactFirstName;
    // values for School Adjudication 
    private String sexOffender;
    private String restrictOther;
    private String weaponDescs;
    
    /**
     * @roseuid 44805C4E0016
     */
    public ServiceEvent()
    {
    }
    
    public Date getStartDateTime(){
	fetch();
	return this.startDateTime;
    }
    
    public void setStartDateTime(Date startDateTime){
	this.startDateTime = startDateTime;
    }
    
    public Date getEndDateTime(){
	fetch();
	return this.endDateTime;
    }
    
    public void setEndDateTime(Date endDateTime){
	this.endDateTime = endDateTime;
    }

    /**
     * Access method for the eventMaximum property.
     * 
     * @return the current value of the eventMaximum property
     */
    public int getEventMaximum()
    {
        fetch();
        return eventMaximum;
    }

    /**
     * Access method for the eventMinimum property.
     * 
     * @return the current value of the eventMinimum property
     */
    public int getEventMinimum()
    {
        fetch();
        return eventMinimum;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getEventStatus()
    {
        initEventStatus();
        return eventStatus;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getEventStatusId()
    {
        fetch();
        return eventStatusId;
    }

    /**
     * Gets referenced type pd.codetable.criminal.JuvenileEventTypeCode
     */
    public JuvenileEventTypeCode getEventType()
    {
        initEventType();
        return eventType;
    }

    /**
     * Get the reference value to class :: pd.codetable.criminal.JuvenileEventTypeCode
     */
    public String getEventTypeId()
    {
        fetch();
        return eventTypeId;
    }

    /**
     * Gets referenced type pd.supervision.administerserviceprovider.SP_Profile
     */
    public SP_Profile getInstructor()
    {
        initInstructor();
        return instructor;
    }

    /**
     * Get the reference value to class :: pd.supervision.administerserviceprovider.SP_Profile
     */
    public String getInstructorId()
    {
        fetch();
        return instructorId;
    }

    /**
     * Gets referenced type pd.supervision.administerserviceprovider.ServiceLocation
     */
    public ServiceLocation getServiceLocation()
    {
        initServiceLocation();
        return serviceLocation;
    }

    /**
     * Get the reference value to class :: pd.supervision.administerserviceprovider.ServiceLocation
     */
    public int getServiceLocationId()
    {
        fetch();
        return serviceLocationId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initEventStatus()
    {
        if (eventStatus == null)
        {
            eventStatus = (Code)
            		new mojo.km.persistence.Reference(eventStatusId, Code.class, "SERVICE_EVENT_STATUS").getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.criminal.JuvenileEventTypeCode
     */
    private void initEventType()
    {
        if (eventType == null)
        {
            eventType = (JuvenileEventTypeCode) new mojo.km.persistence.Reference(eventTypeId,
                    JuvenileEventTypeCode.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.supervision.administerserviceprovider.SP_Profile
     */
    private void initInstructor()
    {
        if (instructor == null)
        {
            instructor = (SP_Profile) new mojo.km.persistence.Reference(""
                    + instructorId, SP_Profile.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class
     * pd.supervision.administerserviceprovider.ServiceLocation
     */
    private void initServiceLocation()
    {
        if (serviceLocation == null)
        {
            serviceLocation = (ServiceLocation)
            		new mojo.km.persistence.Reference( 
            				"" + serviceLocationId, ServiceLocation.class).getObject();
        }
    }

    /**
     * Sets the value of the eventMaximum property.
     * 
     * @param aEventMaximum
     *            the new value of the eventMaximum property
     */
    public void setEventMaximum(int aEventMaximum)
    {
        if (this.eventMaximum != aEventMaximum)
        {
            markModified();
        }
        eventMaximum = aEventMaximum;
    }

    /**
     * Sets the value of the eventMinimum property.
     * 
     * @param aEventMinimum
     *            the new value of the eventMinimum property
     */
    public void setEventMinimum(int aEventMinimum)
    {
        if (this.eventMinimum != aEventMinimum)
        {
            markModified();
        }
        eventMinimum = aEventMinimum;
    }

    /**
     * set the type reference for class member eventStatus
     */
    public void setEventStatus(Code eventStatus)
    {
        if (this.eventStatus == null || !this.eventStatus.equals(eventStatus))
        {
            markModified();
        }

        if (eventStatus.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(eventStatus);
        }
        setEventStatusId("" + eventStatus.getOID());
        eventStatus.setContext("SERVICE_EVENT_STATUS");
        this.eventStatus = (Code) new mojo.km.persistence.Reference(eventStatus).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setEventStatusId(String eventStatusId)
    {
        if (this.eventStatusId == null || !this.eventStatusId.equals(eventStatusId))
        {
            markModified();
        }
        eventStatus = null;
        this.eventStatusId = eventStatusId;
    }

    /**
     * set the type reference for class member eventType
     */
    public void setEventType(JuvenileEventTypeCode eventType)
    {
        if (this.eventType == null || !this.eventType.equals(eventType))
        {
            markModified();
        }
        if (eventType.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(eventType);
        }
        setEventTypeId("" + eventType.getOID());
        this.eventType = (JuvenileEventTypeCode)
        		new mojo.km.persistence.Reference(eventType).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.criminal.JuvenileEventTypeCode
     */
    public void setEventTypeId(String eventTypeId)
    {
        if (this.eventTypeId == null || !this.eventTypeId.equals(eventTypeId))
        {
            markModified();
        }
        eventType = null;
        this.eventTypeId = eventTypeId;
    }

    /**
     * set the type reference for class member instructor
     */
    public void setInstructor(SP_Profile instructor)
    {
        if (this.instructor == null || !this.instructor.equals(instructor))
        {
            markModified();
        }
        if (instructor.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(instructor);
        }
        setInstructorId(instructor.getOID());
        this.instructor = (SP_Profile)
        		new mojo.km.persistence.Reference(instructor).getObject();
    }

    /**
     * Set the reference value to class :: pd.supervision.administerserviceprovider.SP_Profile
     */
    public void setInstructorId(String instructorId)
    {
        if (this.instructorId != instructorId)
        {
            markModified();
        }
        this.instructorId = instructorId;
    }

    /**
     * set the type reference for class member serviceLocation
     */
    public void setServiceLocation(ServiceLocation serviceLocation)
    {
        if (this.serviceLocation == null || !this.serviceLocation.equals(serviceLocation))
        {
            markModified();
        }
        if (serviceLocation.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(serviceLocation);
        }
        setServiceLocationId(Integer.parseInt(serviceLocation.getOID()));
        this.serviceLocation = (ServiceLocation)
        		new mojo.km.persistence.Reference(serviceLocation).getObject();
    }

    /**
     * Set the reference value to class :: pd.supervision.administerserviceprovider.ServiceLocation
     */
    public void setServiceLocationId(int serviceLocationId)
    {
        if (this.serviceLocationId != serviceLocationId)
        {
            markModified();
        }
        this.serviceLocationId = serviceLocationId;
    }

    /**
     * @return java.util.Iterator
     * @param attrName,
     *            attrValue
     * @roseuid 4177C29D03A9
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, ServiceEvent.class);

    }

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 4177C29D03A9
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return (Iterator) home.findAll(event, ServiceEvent.class);
    }

    /**
     * @return JuvenileServiceProvider
     * @param serviceEventId
     * @roseuid 4177C29D03A9
     */
    static public CalendarEvent find(String serviceEventId)
    {
        IHome home = new Home();
        return (ServiceEvent) home.find(serviceEventId, ServiceEvent.class);
    }
    
    static public ServiceEvent findByServiceEventId(String serviceEventId)
    {
        IHome home = new Home();
        return (ServiceEvent) home.find(serviceEventId, ServiceEvent.class);
    }

    /**
     * Clears all pd.supervision.calendar.ServiceEventContext from class relationship collection.
     * 
     * @roseuid 4107DFB603E7
     */
    public void clearServiceEventContexts()
    {
        initServiceEventContexts();
        serviceEventContexts.clear();
    }

    /**
     * Initialize class relationship implementation for pd.supervision.calendar.ServiceEventContext
     * 
     * @roseuid 4107DFB60397
     */
    private void initServiceEventContexts()
    {
        if (serviceEventContexts == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }
            try
            {
                serviceEventContexts = new mojo.km.persistence.ArrayList(pd.contact.agency.Department.class,
                        "serviceEventId", getOID());
            }
            catch (Throwable t)
            {
                serviceEventContexts = new java.util.ArrayList();
            }
        }
    }

    /**
     * insert a pd.supervision.calendar.ServiceEventContext into class relationship collection.
     * 
     * @param anObject
     * @roseuid 4107DFB603B5
     */
    public void insertServiceEventContexts(ServiceEventContext anObject)
    {
        initServiceEventContexts();
        serviceEventContexts.add(anObject);
    }

    /**
     * Removes a pd.supervision.calendar.ServiceEventContext from class relationship collection.
     * 
     * @param anObject
     * @roseuid 4107DFB603C9
     */
    public void removeServiceEventContexts(ServiceEventContext anObject)
    {
        initServiceEventContexts();
        serviceEventContexts.remove(anObject);
    }

    /**
     * @return
     */
    public String getServiceEventId()
    {
        return serviceEventId ;
    }

    /**
     * @return
     */
    public String getOIDId()
    {
        return "" + getOID();
    }

    /**
     * @param string
     */
    public void setServiceEventId(String string)
    {
        if (this.serviceEventId == null || !this.serviceEventId.equals(string))
        {
            markModified();
        }
        setOID(string);
        this.serviceEventId = string;
    }

    public void createOID()
    {
        IHome home = new Home();
        home.bind(this);
    }

    /**
     * @return
     */
    public String getServiceId()
    {
        fetch();
        return serviceId;
    }

    /**
     * @param i
     */
    public void setServiceId(String i)
    {
        if (this.serviceId != serviceId)
        {
            markModified();
        }
        this.serviceId = i;
    }

    
    /**
     * @param createEvent
     * @param logonId
     * @param calEventId
     */
    public void setServiceEvent(CreateCalendarServiceEventEvent createEvent, String logonId, Integer calEventId)
    {
        if (createEvent.getEventMaximum() != 0)
        {
            this.setEventMaximum(createEvent.getEventMaximum());
        }
        
        this.setEventMinimum(createEvent.getEventMinimum());
        this.setEventComments(createEvent.getEventComments());
        this.setEventTypeId(createEvent.getEventTypeId());
        this.setEventStatusId(createEvent.getEventStatusId());
        if (createEvent.getEventTypeId().equals(PDCalendarConstants.SCHOOL_ADJUDICATION)) {
        	this.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED);
		}
    	this.setSexOffender(createEvent.getSexOffender());
    	this.setRestrictOther(createEvent.getRestrictOther());
    	this.setWeaponDescs(createEvent.getWeaponDescs());
        
        if (createEvent.getInstructorId() != null && !createEvent.getInstructorId().equals(""))
        {
            this.setInstructorId(createEvent.getInstructorId());
        }
        
        if (createEvent.getLocationId() != null && !createEvent.getLocationId().equals(""))
        {
            this.setJuvLocUnitId(createEvent.getLocationId());
        }
        
        if (createEvent.getFacilityId() != null && !createEvent.getFacilityId().equals(""))
        {
            this.setFacilityCd(createEvent.getFacilityId());
        }
        
        if (createEvent.getServiceId() != null && !createEvent.getServiceId().equals(""))
        {
            this.setServiceId(createEvent.getServiceId());
        }

        this.setCreateUserID(logonId);
        this.setCalendarEventId(calEventId);
        this.setStartDatetime(createEvent.getStartDatetime());
        this.setEndDatetime(createEvent.getEndDatetime());
        this.setCalendarEventType(createEvent.getCalendarEventType());
        
        if (createEvent.getMemberAddressId() != null && !createEvent.getMemberAddressId().equals(""))
        {
            this.setmemberAddressId(createEvent.getMemberAddressId());
        }
        
        if (createEvent.getInterviewId() != null && !createEvent.getInterviewId().equals(""))
        {
            this.setInterviewId(createEvent.getInterviewId());
        }

        if (createEvent.getSchoolCd() != null && !createEvent.getSchoolCd().equals(""))
        {
            String derivedOid = Formatter.pad(createEvent.getSchoolDistrictId(), 3, '0', true);
            String padSchoolCode = Formatter.pad(createEvent.getSchoolCd(), 3, '0', true);
            this.setSchoolCd(derivedOid + padSchoolCode);
        }
        if (StringUtils.isNotEmpty(createEvent.getMemberEmploymentId()))
        {
            this.setMemberEmploymentId(createEvent.getMemberEmploymentId());
        }
        if (createEvent.getContactLastName() != null && !createEvent.getContactLastName().equals(""))
        {
        	this.setContactLastName(createEvent.getContactLastName());
        }
        if (createEvent.getContactFirstName() != null && !createEvent.getContactFirstName().equals(""))
        {
        	this.setContactFirstName(createEvent.getContactFirstName());
        }
    }

    /**
     * @return
     */
    public String getJuvLocUnitId()
    {
        fetch();
        return this.juvLocUnitId;
    }

    /**
     * @param i
     */
    public void setJuvLocUnitId(String i)
    {
        if (this.juvLocUnitId != i)
        {
            markModified();
        }
        this.juvLocUnitId = i;
    }

    /**
     * @return
     */
    public String getEventComments()
    {
        fetch();
        return eventComments;
    }

    /**
     * @param string
     */
    public void setEventComments(String eventComments)
    {
        if (this.eventComments == null || !this.eventComments.equals(eventComments))
        {
            markModified();
        }
        this.eventComments = eventComments;
    }

    /**
     * @return Returns the interviewId.
     */
    public String getInterviewId()
    {
        fetch();
        return interviewId;
    }

    /**
     * @param interviewId
     *            The interviewId to set.
     */
    public void setInterviewId(String interviewId)
    {
        if (this.interviewId != interviewId)
        {
            markModified();
        }
        this.interviewId = interviewId;
    }

    /**
     * @return Returns the memberAddressId.
     */
    public String getmemberAddressId()
    {
        fetch();
        return memberAddressId;
    }

    /**
     * @param memberAddressId
     *            The memberAddressId to set.
     */
    public void setmemberAddressId(String memberAddressId)
    {
        if (this.memberAddressId != memberAddressId)
        {
            markModified();
        }
        this.memberAddressId = memberAddressId;
    }

    /**
     * @return Returns the schoolCd.
     */
    public String getSchoolCd()
    {
        fetch();
        return schoolCd;
    }

    /**
     * @param schoolCd
     *            The schoolCd to set.
     */
    public void setSchoolCd(String schoolCd)
    {
        if (this.schoolCd == null || !this.schoolCd.equals(schoolCd))
        {
            markModified();
        }
        this.schoolCd = schoolCd;
    }

    /**
     * @return Returns the currentEnrollment.
     */
    public int getCurrentEnrollment()
    {
        fetch();
        return currentEnrollment;
    }

    /**
     * @param currentEnrollment
     *            The currentEnrollment to set.
     */
    public void setCurrentEnrollment(int currentEnrollment)
    {
    	if( this.currentEnrollment < 0 && currentEnrollment < 0 )
    	{ // in the rare case where they are both invalid
    		this.currentEnrollment = currentEnrollment = 0 ;
    		markModified();
    	}

    	if( currentEnrollment < 0 )
    	{
    		currentEnrollment = 0 ;
    	}
    	
    	if( this.currentEnrollment < 0 )
    	{
    		this.currentEnrollment = 0 ;
    	}
    	
      if( this.currentEnrollment != currentEnrollment )
      {
          markModified();
      }

      this.currentEnrollment = currentEnrollment;
    }

    /**
     * @return Returns the calendarEventId.
     */
    public Integer getCalendarEventId()
    {
        fetch();
        return calendarEventId;
    }

    /**
     * @param calendarEventId
     *            The calendarEventId to set.
     */
    public void setCalendarEventId(Integer calendarEventId)
    {
        if (this.calendarEventId != calendarEventId)
        {
            markModified();
        }
        this.calendarEventId = calendarEventId;
    }

    /**
     * @return Returns the memberAddressId.
     */
    public String getMemberAddressId()
    {
        fetch();
        return memberAddressId;
    }

    /**
     * @param memberAddressId
     *            The memberAddressId to set.
     */
    public void setMemberAddressId(String memberAddressId)
    {
        if (this.memberAddressId != memberAddressId)
        {
            markModified();
        }
        this.memberAddressId = memberAddressId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initJuvLocUnit()
    {
        if (juvLocUnit == null)
        {
            juvLocUnit = (JuvLocationUnit) 
            		new mojo.km.persistence.Reference(juvLocUnitId, JuvLocationUnit.class).getObject();
        }
    }

    /**
     * @return Returns the juvLocUnit.
     */
    public JuvLocationUnit getJuvLocUnit()
    {
        initJuvLocUnit();
        return juvLocUnit;
    }

    /**
     * @param juvLocUnit
     *            The juvLocUnit to set.
     */
    public void setJuvLocUnit(JuvLocationUnit juvLocUnit)
    {
        this.juvLocUnit = juvLocUnit;
    }

    /**
     * @return Returns the serviceName.
     */
    public String getServiceName()
    {
        fetch();
        return serviceName;
    }

    /**
     * @param serviceName
     *            The serviceName to set.
     */
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

	public String getFacilityCd() {
		return facilityCd;
	}

	public void setFacilityCd(String facilityCd) {
		this.facilityCd = facilityCd;
	}

	/**
	 * @return the memberEmploymentId
	 */
	public String getMemberEmploymentId() {
		return memberEmploymentId;
	}

	/**
	 * @param memberEmploymentId the memberEmploymentId to set
	 */
	public void setMemberEmploymentId(String memberEmploymentId) {
		this.memberEmploymentId = memberEmploymentId;
	}

	public String getProgramReferralId() {
		return programReferralId;
	}

	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}

	public String getContactLastName() {
        fetch();
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
	  if (this.contactLastName != contactLastName)
	       {
	           markModified();
	       }
	  this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
        fetch();
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		if (this.contactFirstName != contactFirstName)
	       {
	           markModified();
	       }
		this.contactFirstName = contactFirstName;
	}

	public String getSexOffender() {
		fetch();
		return sexOffender;
	}

	public void setSexOffender(String sexOffender) {
		if (this.sexOffender != sexOffender)
	       {
	           markModified();
	       }
		this.sexOffender = sexOffender;
	}

	public String getRestrictOther() {
		fetch();
		return restrictOther;
	}

	public void setRestrictOther(String restrictOther) {
		if (this.restrictOther != restrictOther)
	       {
	           markModified();
	       }		
		this.restrictOther = restrictOther;
	}

	public String getWeaponDescs() {
		fetch();
		return weaponDescs;
	}

	public void setWeaponDescs(String weaponDescs) {
		if (this.weaponDescs != weaponDescs)
	       {
	           markModified();
	       }
		this.weaponDescs = weaponDescs;
	}
	
	/**
	 * 
	 */
	public CalendarServiceEventResponseEvent getCalendarResponseEvent() {
		CalendarServiceEventResponseEvent servResp = new CalendarServiceEventResponseEvent();
		
		servResp.setServiceEventId(this.getOID());
		servResp.setCalendarEventId(this.getCalendarEventId());
		servResp.setMaxAttendance(Integer.toString(this.getEventMaximum()));
		servResp.setMinAttendance(Integer.toString(this.getEventMinimum()));
		servResp.setEventStatusCode(this.getEventStatusId());
		servResp.setEventTypeCode(this.getEventTypeId());
		if(this.getInstructorId() != null){
			servResp.setInstructorId(new Integer(this.getInstructorId()));
		}
		servResp.setJuvUnitCd(this.getJuvLocUnitId());
		servResp.setJuvUnitName(this.getJuvLocUnit().getLocationUnitName());
		if(this.getServiceId() != null){
			servResp.setServiceId(this.getServiceId());
		}else{
			servResp.setServiceId("0");
		}
		servResp.setEventComments(this.getEventComments());
		servResp.setSchoolCd(this.getSchoolCd());
		servResp.setInterviewId(this.getInterviewId());
		if(this.getMemberAddressId() != null){
			servResp.setMemberAddressId(new Integer(this.getMemberAddressId()));
		}		
		servResp.setCurrentEnrollment(Integer.toString(this.getCurrentEnrollment()));
		servResp.setFacilityCd(this.getFacilityCd());
		if(this.getMemberEmploymentId() != null){
			servResp.setMemberEmploymentId(new Integer(this.getMemberEmploymentId()));
		}
		servResp.setContactFirstName(this.getContactFirstName());
		servResp.setContactLastName(this.getContactLastName());
		servResp.setSexOffenderRegistrantStr(this.getSexOffender());
		servResp.setRestrictionsOther(this.getRestrictOther());

		if(this.getCreateUserID() != null){
			servResp.setCreateUser(this.getCreateUserID());
		}
		if(this.getCreateTimestamp() != null){
			servResp.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
		}
		if(this.getUpdateUserID() != null){
			servResp.setUpdateUser(this.getUpdateUserID());
		}
		if(this.getUpdateTimestamp() != null){
			servResp.setUpdateDate(new Date(this.getUpdateTimestamp().getTime()));
		}
		if(this.getCreateJIMS2UserID() != null){
			servResp.setCreateJims2User(this.getCreateJIMS2UserID());
		}
		if(this.getUpdateJIMS2UserID() != null){
			servResp.setUpdateJims2User(this.getUpdateJIMS2UserID());
		}
		
		return servResp;
		
	}
	
	
}
