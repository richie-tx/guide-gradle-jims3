package pd.supervision.calendar;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Reference;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.common.calendar.CalendarEvent;
import pd.contact.agency.Department;
import pd.juvenilecase.interviewinfo.Interview;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.SP_Profile;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
//import org.apache.commons.collections.FastArrayList;

/**
 * Properties for eventStatus
 * 
 * @referencedType pd.codetable.Code
 * @contextKey SERVICEEVENT_STATUS
 * @detailerDoNotGenerate true
 */
public class ServiceEventContext extends CalendarEvent
{
    private String calendarEventContextId;

    private Integer calendarEventId;
    
    private String probationOfficerId;
    private String caseFileId ;
    private String juvenileId;
    private String eventComments;

    private int currentEnrollment;
    private int eventMaximum;
    private int eventMinimum;

    /**
     * Properties for eventStatus
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SERVICEEVENT_STATUS
     * @detailerDoNotGenerate true
     */
    private Code eventStatus = null;
    private String eventStatusId;

    /**
     * Properties for eventType
     * 
     * @referencedType pd.codetable.criminal.JuvenileEventTypeCode
     * @detailerDoNotGenerate true
     */
    private JuvenileEventTypeCode eventType = null;
    private String eventTypeId;

    /**
     * Properties for instructor
     * 
     * @referencedType pd.supervision.administerserviceprovider.SP_Profile
     * @detailerDoNotGenerate true
     */
    private SP_Profile instructor = null;
    private int instructorId;

    private Interview interview;
    private String interviewId;

    private int juvLocUnitId;

    private String memberAddressId;
    private String facilityCd;
    
    private String schoolCd;
    private JuvenileSchoolDistrictCode schoolName;

    private String serviceEventContextId;
    private Collection serviceEventContexts;
    private String serviceEventId;
    private int serviceId;

    private String contactFirstName;
    private String contactLastName;
    /**
     * Properties for serviceLocation
     * 
     * @referencedType pd.supervision.administerserviceprovider.ServiceLocation
     * @detailerDoNotGenerate true
     */
    private JuvLocationUnit serviceLocation = null;
    
    //<KISHORE>JIMS200059078 : Calendar: Add new event type Job Visit (PD) - KK
    private String memberEmployId;
    
    private String sexoffenderInd;
    private String restrictionOther;
    private String weaponDescs;

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 4177C29D03A9
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return (Iterator) home.findAll(event, ServiceEventContext.class);
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
        return home.findAll(attrName, attrValue, ServiceEventContext.class);
    }

    /**
     * @roseuid 44805C4E0016
     */
    public ServiceEventContext()
    {
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

    public void createOID()
    {
        IHome home = new Home();
        home.bind(this);
    }

    public CalendarServiceEventResponseEvent getBasicCalendarServiceResponseEvent()
    {
        CalendarServiceEventResponseEvent resp = new CalendarServiceEventResponseEvent();
        resp.setEventId(this.getServiceEventId());
        resp.setCalendarEventId(this.getCalendarEventId());
        resp.setBodyText(this.getBodyText());
        resp.setCreatedBy(this.getCreatedBy());
        resp.setSubject(this.getSubject());
        resp.setEventComments(this.getEventComments());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date startDate = this.getStartDatetime();
        Date endDate = this.getEndDatetime();

        String eventTime = dateFormat.format(startDate);

        long eventLength = endDate.getTime() - startDate.getTime();
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String eventSessionLength = dateFormat.format(new Date(eventLength));
        resp.setStartDatetime(startDate);
        resp.setEndDatetime(endDate);
        resp.setEventDate(startDate);
        resp.setEventSessionLength(eventSessionLength);
        resp.setEventTime(eventTime);

        JuvenileEventTypeCode eventType = this.getEventType();
        if (eventType != null)
        {            
            resp.setEventTypeCode(eventType.getCode());
            resp.setEventType(eventType.getDescription());
            resp.setEventTypeCategory(eventType.getGroup());
        }

        Code eventStatus = this.getEventStatus();
        if (eventStatus != null)
        {
            resp.setEventStatusCode(eventStatus.getCode());
            resp.setEventStatus(eventStatus.getDescription());
        }
        return resp;
    }
    
    /**
     * @return Returns the calendarEventContextId.
     */
    public String getCalendarEventContextId()
    {
        return calendarEventContextId;
    }

    /**
     * @return calendarEventId
     */
    public Integer getCalendarEventId()
    {
        fetch();
        return this.calendarEventId;
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
     * @return
     */
    public String getEventComments()
    {
        fetch();
        return eventComments;
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
    public int getInstructorId()
    {
        fetch();
        return instructorId;
    }

    /**
     * @return Returns the interview.
     */
    public Interview getInterview()
    {
        initInterview();
        return interview;
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
     * @return
     */
    public int getJuvLocUnitId()
    {
        fetch();
        return juvLocUnitId;
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
     * Get the reference value to class :: pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @return String schoolCd
     */
    public String getSchoolCd()
    {
        fetch();
        return schoolCd;
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @return JuvenileSchoolDistrictCode schoolName
     */
    public JuvenileSchoolDistrictCode getSchoolName()
    {
        initSchoolName();
        return schoolName;
    }

    /**
     * @return
     */
    public String getServiceEventContextId()
    {
        fetch();
        return serviceEventContextId;
    }

    /**
     * @return
     */
    public String getServiceEventId()
    {
        return serviceEventId;
    }

    /**
     * @return
     */
    public int getServiceId()
    {
        fetch();
        return serviceId;
    }

    /**
     * Gets referenced type pd.supervision.administerserviceprovider.ServiceLocation
     */
    public JuvLocationUnit getServiceLocation()
    {
        initLocation();
        return serviceLocation;
    }

    /**
     * Get the reference value to class :: pd.supervision.administerserviceprovider.ServiceLocation
     */
    /*
     * public int getServiceLocationId() { fetch(); return serviceLocationId; }
     */
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initEventStatus()
    {
        if (eventStatus == null)
        {
            eventStatus = (Code) new Reference(eventStatusId, Code.class, "SERVICE_EVENT_STATUS")
                    .getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.criminal.JuvenileEventTypeCode
     */
    private void initEventType()
    {
        if (eventType == null)
        {
            IHome home = new Home();
            eventType = (JuvenileEventTypeCode) home.find("code", eventTypeId, JuvenileEventTypeCode.class);
        }
    }

    /**
     * Initialize class relationship to class pd.supervision.administerserviceprovider.SP_Profile
     */
    private void initInstructor()
    {
        if (instructor == null)
        {
            String instructorStr = String.valueOf(instructorId);
            instructor = (SP_Profile) new Reference(instructorStr, InhouseSP_Profile.class).getObject();
        }
    }

    public void initInterview()
    {
        if (interview == null)
        {
            String interviewIdStr = String.valueOf(interviewId);
            interview = (Interview) new Reference(interviewIdStr, Interview.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.supervision.administerserviceprovider.SP_Profile
     */
    private void initLocation()
    {
        if (serviceLocation == null)
        {
            String juvLocUnitIdStr = String.valueOf(juvLocUnitId);
            serviceLocation = (JuvLocationUnit) new Reference(juvLocUnitIdStr, JuvLocationUnit.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.person.JuvenileSchoolDistrictCode
     */
    private void initSchoolName()
    {
        if (schoolName == null)
        {
            if( schoolCd != null && schoolCd.length() > 5 ){
              
              String distCode = schoolCd.substring(0, 3);
              String schoolCode = schoolCd.substring(3);
              int dCode = Integer.parseInt(distCode);
    	      int sCode = Integer.parseInt(schoolCode);
    	      
    	      String derivedSchoolOID = String.valueOf(dCode) + "-" + String.valueOf(sCode);
                schoolName = (JuvenileSchoolDistrictCode) new Reference(derivedSchoolOID, JuvenileSchoolDistrictCode.class)
                        .getObject();
            }
        }
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
                serviceEventContexts = new mojo.km.persistence.ArrayList(Department.class, "serviceEventId", getOID());
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
     * @param calendarEventContextId The calendarEventContextId to set.
     */
    public void setCalendarEventContextId(String calendarEventContextId)
    {
        this.calendarEventContextId = calendarEventContextId;
    }

    /**
     * @param string
     */
    public void setCalendarEventId(Integer id)
    {
        if (this.calendarEventId != id)
        {
            markModified();
        }
        this.calendarEventId = id;
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
        setEventStatusId(eventStatus.getOID());
        eventStatus.setContext("SERVICE_EVENT_STATUS");
        this.eventStatus = (Code) new Reference(eventStatus).getObject();
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
        setEventTypeId(eventType.getOID());
        this.eventType = (JuvenileEventTypeCode) new Reference(eventType).getObject();
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
        setInstructorId(Integer.parseInt(instructor.getOID()));
        this.instructor = (SP_Profile) new Reference(instructor).getObject();
    }

    /**
     * Set the reference value to class :: pd.supervision.administerserviceprovider.SP_Profile
     */
    public void setInstructorId(int instructorId)
    {
        if (this.instructorId != instructorId)
        {
            markModified();
        }
        this.instructorId = instructorId;
    }

    /**
     * @param interview
     *            The interview to set.
     */
    public void setInterview(Interview interview)
    {
        this.interview = interview;
        if (interview.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(interview);
        }
        setInterviewId(interview.getOID());

    }

    /**
     * @param interviewId
     *            The interviewId to set.
     */
    public void setInterviewId(String interviewId)
    {
        if (this.interviewId != null && !this.interviewId.equals(interviewId))
        {
            markModified();
        }
        this.interviewId = interviewId;
    }

    /**
     * @param i
     */
    public void setJuvLocUnitId(int locationId)
    {
        if (this.juvLocUnitId != locationId)
        {
            markModified();
        }
        this.juvLocUnitId = locationId;
    }

    /**
     * @param memberAddressId
     *            The memberAddressId to set.
     */
    public void setMemberAddressId(String memberAddressId)
    {
        if (this.memberAddressId != null && !this.memberAddressId.equals(memberAddressId))
        {
            markModified();
        }
        this.memberAddressId = memberAddressId;
    }

    /**
     * Set the reference value to class :: pd.codetable.person.JuvenileSchoolDistrictCode
     * 
     * @param schoolCd
     */
    public void setSchoolCd(String lschoolCd)
    {
        if (this.schoolCd == null || !this.schoolCd.equals(lschoolCd))
        {
            markModified();
        }
        schoolName = null;
        this.schoolCd = lschoolCd;
    }

    /**
     * set the type reference for class member schoolName
     * 
     * @param schoolCode
     */
    public void setSchoolName(JuvenileSchoolDistrictCode lschoolCode)
    {
        if (this.schoolName == null || !this.schoolName.equals(lschoolCode))
        {
            markModified();
        }
        if (lschoolCode.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(lschoolCode);
        }
        setSchoolCd(lschoolCode.getOID());
        this.schoolName = (JuvenileSchoolDistrictCode) new Reference(lschoolCode).getObject();
    }

    /**
     * @param string
     */
    public void setServiceEventContextId(String string)
    {
        if (this.serviceEventContextId != string)
        {
            markModified();
        }
        setOID(string);
        this.serviceEventContextId = string;
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
        this.serviceEventId = string;
    }

    /**
     * @param i
     */
    public void setServiceId(int serviceId)
    {
        if (this.serviceId != serviceId)
        {
            markModified();
        }
        this.serviceId = serviceId;
    }

	/**
	 * @return
	 */
	public String getFacilityCd() {
		fetch();
		return facilityCd;
	}

	/**
	 * @param lfacilityCd
	 */
	public void setFacilityCd(String lfacilityCd) {
        if (this.facilityCd == null || !this.facilityCd.equals(lfacilityCd))
        {
            markModified();
        }
        this.facilityCd = lfacilityCd;		
	}
	
	/**
	 * @return probationOfficerId
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}

	/**
	 * @param probationOfficerId
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}

	/**
	 * @return caseFileId
	 */
	public String getCaseFileId() {
		return caseFileId;
	}

	/**
	 * @param caseFileId
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}

	/**
	 * @return juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return the memberEmployId
	 */
	public String getMemberEmployId() {
		return memberEmployId;
	}

	/**
	 * @param memberEmployId the memberEmployId to set
	 */
	public void setMemberEmployId(String memberEmployId) {
		this.memberEmployId = memberEmployId;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	/**
	 * @return the sexoffenderInd
	 */
	public String getSexoffenderInd() {
		return sexoffenderInd;
	}

	/**
	 * @param sexoffenderInd the sexoffenderInd to set
	 */
	public void setSexoffenderInd(String sexoffenderInd) {
		this.sexoffenderInd = sexoffenderInd;
	}

	/**
	 * @return the restrictionOther
	 */
	public String getRestrictionOther() {
		return restrictionOther;
	}

	/**
	 * @param restrictionOther the restrictionOther to set
	 */
	public void setRestrictionOther(String restrictionOther) {
		this.restrictionOther = restrictionOther;
	}

	/**
	 * @return the weaponDescs
	 */
	public String getWeaponDescs() {
		return weaponDescs;
	}

	/**
	 * @param weaponDescs the weaponDescs to set
	 */
	public void setWeaponDescs(String weaponDescs) {
		this.weaponDescs = weaponDescs;
	}
	
}
