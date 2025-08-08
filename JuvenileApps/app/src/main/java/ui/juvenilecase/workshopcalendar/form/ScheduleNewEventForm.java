/*
 * Created on Jun 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.workshopcalendar.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.contact.to.Address;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberAddressViewResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SchoolDistrictComparator;
import ui.common.SimpleCodeTableHelper;
import ui.common.form.AddressValidationForm;
import ui.juvenilecase.programreferral.UIProgramReferralBean;

/**
 * @author awidjaja
 */
public class ScheduleNewEventForm extends AddressValidationForm
{
    private static Collection emptyColl = new ArrayList();

    private String action = UIConstants.EMPTY_STRING;
    private String secondaryAction = UIConstants.EMPTY_STRING;

    private Service currentService;

    private Collection allEvents;
    private Collection preScheduledEvents;

    private Date currentDate;

    private String juvenileNum;
    private String juvenileName;
    private String juvenileFullName;

    private String juvenileFirstName; //bug 26711
    private String juvenileLastName;//bug 26711
    private String juvenileMiddleName;//bug 26711

    private String birthDate;

    private String caseFileId;

    private String officerId;
    private String officerName;

    private String officerFirstName; //bug 26711
    private String officerLastName;//bug 26711

    //added for User Story 11109
    private String officerPhone;
    private String officerPhoneAreaCode;
    private String officerPhonePrefix;
    private String officerPhoneMain;
    private String officerPhoneExtn;
    private String officerFax;
    private String faxAreaCode;
    private String faxPrefix;
    private String faxMain;
    private String officerHours;

    //12533 user story changes
    private String guardianFirstName;
    private String guardianLastName;

    private String serviceProviderId;
    private String selectedServiceProviderId;

    private String providerName;

    private String addressStatus = UIConstants.EMPTY_STRING;

    private String calendarType;

    private String programId;
    private String programName;
    private String juvPgmReferralId;

    private String saveEventDate;
    private Date saveEventDateYYMMDD;

    private UIProgramReferralBean programReferral;

    private boolean programReferralNew;
    private String maxyouthLimit;   

    

    private String hasProgramReferral;

    private boolean expandProviders;

    // begin value added for SP events
    private String eventFromDate;
    private String eventToDate;
    private String eventType;
    private List serviceProviderList;
    private List eventList;
    private String serviceName;
    private String serviceProviderName;
    private String[] selectedEventIds;
    private List programList;
    private String selectedServiceId;
    private List selectedEventsList;
    private boolean referralFound;
    private String fromPage;
    private String pageState;
    private String confirmationMsg;
    private boolean allowUpdates = false;
    private boolean allowPrePetitionUpdates = true;
    private List<JuvenileCoreLightResponseEvent> juveniles = new ArrayList<>();
    private Collection juvenileProfiles;   

    private String searchType;

    private List activeFacilityList;

    // begin values for SAN involved weapons
    private List offenseInvolvedWeaponList;
    private List typeOfWeapons;
    private String[] offenseCategoryDescription;
    private String[] offenseDescription;
    private String[] typeOfWeaponDescription;
    private String[] typeOfWeaponId;
    private String[] weaponDescription;
    private String[] weaponInvolvedStr;

    private String eventId;
    private String eventDate;
    private String referralAction;
    // end values for SAN involved weapons

    

    public ScheduleNewEventForm()
    {
	programReferral = null;
	currentService = new Service();
	currentDate = new Date();

    }

    public void clear()
    {
	currentService = new Service();
	preScheduledEvents = emptyColl;
	action = UIConstants.EMPTY_STRING;
	officerHours = UIConstants.EMPTY_STRING;
	officerPhone = UIConstants.EMPTY_STRING;
	officerPhoneAreaCode = UIConstants.EMPTY_STRING;
	officerPhonePrefix = UIConstants.EMPTY_STRING;
	officerPhoneMain = UIConstants.EMPTY_STRING;
	officerPhoneExtn = UIConstants.EMPTY_STRING;
	officerFax = UIConstants.EMPTY_STRING;
	faxAreaCode = UIConstants.EMPTY_STRING;
	faxPrefix = UIConstants.EMPTY_STRING;
	faxMain = UIConstants.EMPTY_STRING;
	officerHours = UIConstants.EMPTY_STRING;
    }

    public void spClear()
    {
	eventFromDate = UIConstants.EMPTY_STRING;
	eventToDate = UIConstants.EMPTY_STRING;
	eventType = UIConstants.EMPTY_STRING;
	serviceProviderList = new ArrayList();
	;
	eventList = new ArrayList();
	;
	serviceName = UIConstants.EMPTY_STRING;
	serviceProviderName = UIConstants.EMPTY_STRING;
	selectedEventIds = new String[0];
	programList = new ArrayList();
	;
	selectedServiceId = UIConstants.EMPTY_STRING;
	selectedEventsList = new ArrayList();
	referralFound = false;
	fromPage = UIConstants.EMPTY_STRING;
	pageState = UIConstants.EMPTY_STRING;
	confirmationMsg = UIConstants.EMPTY_STRING;
	offenseInvolvedWeaponList = new ArrayList();
	juvenileProfiles=new ArrayList();;
    }

    public String getEventId()
    {
	return eventId;
    }

    public void setEventId(String eventId)
    {
	this.eventId = eventId;
    }

    public String getEventDate()
    {
	return eventDate;
    }

    public void setEventDate(String eventDate)
    {
	this.eventDate = eventDate;
    }
    public String getReferralAction()
    {
        return referralAction;
    }

    public void setReferralAction(String referralAction)
    {
        this.referralAction = referralAction;
    }

    /**
     * @return the officerPhone
     */
    public String getOfficerPhone()
    {
	return officerPhone;
    }

    /**
     * @param officerPhone
     *            the officerPhone to set
     */
    public void setOfficerPhone(String officerPhone)
    {
	this.officerPhone = officerPhone;
    }

    /**
     * @return the officerPhoneAreaCode
     */
    public String getOfficerPhoneAreaCode()
    {
	return officerPhoneAreaCode;
    }

    /**
     * @param officerPhoneAreaCode
     *            the officerPhoneAreaCode to set
     */
    public void setOfficerPhoneAreaCode(String officerPhoneAreaCode)
    {
	this.officerPhoneAreaCode = officerPhoneAreaCode;
    }

    /**
     * @return the officerPhonePrefix
     */
    public String getOfficerPhonePrefix()
    {
	return officerPhonePrefix;
    }

    /**
     * @param officerPhonePrefix
     *            the officerPhonePrefix to set
     */
    public void setOfficerPhonePrefix(String officerPhonePrefix)
    {
	this.officerPhonePrefix = officerPhonePrefix;
    }

    /**
     * @return the officerPhoneMain
     */
    public String getOfficerPhoneMain()
    {
	return officerPhoneMain;
    }

    /**
     * @param officerPhoneMain
     *            the officerPhoneMain to set
     */
    public void setOfficerPhoneMain(String officerPhoneMain)
    {
	this.officerPhoneMain = officerPhoneMain;
    }

    /**
     * @return the officerFax
     */
    public String getOfficerFax()
    {
	return officerFax;
    }

    /**
     * @param officerFax
     *            the officerFax to set
     */
    public void setOfficerFax(String officerFax)
    {
	this.officerFax = officerFax;
    }

    /**
     * @return the faxAreaCode
     */
    public String getFaxAreaCode()
    {
	return faxAreaCode;
    }

    /**
     * @param faxAreaCode
     *            the faxAreaCode to set
     */
    public void setFaxAreaCode(String faxAreaCode)
    {
	this.faxAreaCode = faxAreaCode;
    }

    /**
     * @return the faxPrefix
     */
    public String getFaxPrefix()
    {
	return faxPrefix;
    }

    /**
     * @param faxPrefix
     *            the faxPrefix to set
     */
    public void setFaxPrefix(String faxPrefix)
    {
	this.faxPrefix = faxPrefix;
    }

    /**
     * @return the faxMain
     */
    public String getFaxMain()
    {
	return faxMain;
    }

    /**
     * @param faxMain
     *            the faxMain to set
     */
    public void setFaxMain(String faxMain)
    {
	this.faxMain = faxMain;
    }

    public String getOfficerHours()
    {
	return officerHours;
    }

    public void setOfficerHours(String officerHours)
    {
	this.officerHours = officerHours;
    }

    public String getJuvenileLastName()
    {
	return juvenileLastName;
    }

    public void setJuvenileLastName(String juvenileLastName)
    {
	this.juvenileLastName = juvenileLastName;
    }

    public String getJuvenileMiddleName()
    {
	return juvenileMiddleName;
    }

    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
	this.juvenileMiddleName = juvenileMiddleName;
    }

    public String getOfficerFirstName()
    {
	return officerFirstName;
    }

    public void setOfficerFirstName(String officerFirstName)
    {
	this.officerFirstName = officerFirstName;
    }

    public String getOfficerLastName()
    {
	return officerLastName;
    }

    public void setOfficerLastName(String officerLastName)
    {
	this.officerLastName = officerLastName;
    }

    /**
     * @return the officerPhoneExtn
     */
    public String getOfficerPhoneExtn()
    {
	return officerPhoneExtn;
    }

    /**
     * @param officerPhoneExtn
     *            the officerPhoneExtn to set
     */
    public void setOfficerPhoneExtn(String officerPhoneExtn)
    {
	this.officerPhoneExtn = officerPhoneExtn;
    }

    public String getGuardianLastName()
    {
	return guardianLastName;
    }

    public void setGuardianLastName(String guardianLastName)
    {
	this.guardianLastName = guardianLastName;
    }

    public String getGuardianFirstName()
    {
	return guardianFirstName;
    }

    public void setGuardianFirstName(String guardianFirstName)
    {
	this.guardianFirstName = guardianFirstName;
    }

    /**
     * @return
     */
    public Service getCurrentService()
    {
	return currentService;
    }

    /**
     * @param service
     */
    public void setCurrentService(Service service)
    {
	currentService = service;
    }

    /**
     * @return Returns the allEvents.
     */
    public Collection getAllEvents()
    {
	return allEvents;
    }

    /**
     * @param allEvents
     *            The allEvents to set.
     */
    public void setAllEvents(Collection allEvents)
    {
	this.allEvents = allEvents;
    }

    /**
     * @return Returns the caseFileId.
     */
    public String getCaseFileId()
    {
	return caseFileId;
    }

    /**
     * @param caseFileId
     *            The caseFileId to set.
     */
    public void setCaseFileId(String caseFileId)
    {
	this.caseFileId = caseFileId;
    }

    /**
     * @return Returns the juvenileNumber.
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @param juvenileNumber
     *            The juvenileNumber to set.
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }

    /**
     * @return Returns the preScheduledEvents.
     */
    public Collection getPreScheduledEvents()
    {
	return preScheduledEvents;
    }

    /**
     * @param preScheduledEvents
     *            The preScheduledEvents to set.
     */
    public void setPreScheduledEvents(Collection<CalendarServiceEventResponseEvent> preScheduledEvents)
    {
	HashMap serviceProvidersMap = new HashMap();

	for (CalendarServiceEventResponseEvent serviceEvent : preScheduledEvents)
	{
	    if (serviceProvidersMap.get(serviceEvent.getServiceProviderId() + UIConstants.EMPTY_STRING) != null)
	    {
		ServiceProvider sp = (ServiceProvider) serviceProvidersMap.get(serviceEvent.getServiceProviderId() + UIConstants.EMPTY_STRING);
		sp.setResponseEvents(serviceEvent);
	    }
	    else
	    {
		ServiceProvider sp = new ServiceProvider();
		sp.setServiceProviderId(serviceEvent.getServiceProviderId() + UIConstants.EMPTY_STRING);
		sp.setServiceProviderName(serviceEvent.getServiceProviderName());
		sp.setResponseEvents(serviceEvent);
		serviceProvidersMap.put(serviceEvent.getServiceProviderId() + UIConstants.EMPTY_STRING, sp);
	    }
	}

	ArrayList serviceProviders = new ArrayList();
	Collection<ServiceProvider> spList = serviceProvidersMap.values();
	serviceProviders.ensureCapacity(spList.size());

	for (ServiceProvider sp : spList)
	{
	    serviceProviders.add(sp);
	}

	Collections.sort((List) serviceProviders);
	this.preScheduledEvents = serviceProviders;
    }

    /**
     * @return Returns the officerId.
     */
    public String getOfficerId()
    {
	return officerId;
    }

    /**
     * @param officerId
     *            The officerId to set.
     */
    public void setOfficerId(String officerId)
    {
	this.officerId = officerId;
    }

    /**
     * @return Returns the juvenileName.
     */
    public String getJuvenileName()
    {
	return juvenileName;
    }

    /**
     * @param juvenileName
     *            The juvenileName to set.
     */
    public void setJuvenileName(String juvenileName)
    {
	this.juvenileName = juvenileName;
    }

    /**
     * @return Returns the officerName.
     */
    public String getOfficerName()
    {
	return officerName;
    }

    /**
     * @param officerName
     *            The officerName to set.
     */
    public void setOfficerName(String officerName)
    {
	this.officerName = officerName;
    }

    /**
     * @return Returns the action.
     */
    public String getAction()
    {
	return action;
    }

    /**
     * @param action
     *            The action to set.
     */
    public void setAction(String action)
    {
	this.action = action;
    }

    /**
     * @return Returns the calendarType.
     */
    public String getCalendarType()
    {
	return calendarType;
    }

    /**
     * @param calendarType
     *            The calendarType to set.
     */
    public void setCalendarType(String calendarType)
    {
	this.calendarType = calendarType;
    }

    /**
     * @return Returns the serviceProviderId.
     */
    public String getServiceProviderId()
    {
	return serviceProviderId;
    }

    /**
     * @param serviceProviderId
     *            The serviceProviderId to set.
     */
    public void setServiceProviderId(String serviceProviderId)
    {
	this.serviceProviderId = serviceProviderId;
    }

    public String getSelectedServiceProviderId()
    {
	return this.selectedServiceProviderId;
    }

    /**
     * @param serviceProviderId
     *            The serviceProviderId to set.
     */
    public void setSelectedServiceProviderId(String selectedServiceProviderId)
    {
	this.selectedServiceProviderId = selectedServiceProviderId;
    }

    /**
     * @return Returns the programReferral.
     */
    public UIProgramReferralBean getProgramReferral()
    {
	return programReferral;
    }

    /**
     * @param programReferral
     *            The programReferral to set.
     */
    public void setProgramReferral(UIProgramReferralBean programReferral)
    {
	this.programReferral = programReferral;
    }

    /**
     * @return Returns the programReferralNew.
     */
    public boolean isProgramReferralNew()
    {
	return programReferralNew;
    }

    /**
     * @param programReferralNew
     *            The programReferralNew to set.
     */
    public void setProgramReferralNew(boolean programReferralNew)
    {
	this.programReferralNew = programReferralNew;
    }
    
    public String getMaxyouthLimit()
    {
        return maxyouthLimit;
    }

    public void setMaxyouthLimit(String maxyouthLimit)
    {
        this.maxyouthLimit = maxyouthLimit;
    }

    /**
     * @return Returns the programId.
     */
    public String getProgramId()
    {
	return programId;
    }

    /**
     * @param programId
     *            The programId to set.
     */
    public void setProgramId(String programId)
    {
	this.programId = programId;
    }

    /**
     * @return Returns the programName.
     */
    public String getProgramName()
    {
	return programName;
    }

    /**
     * @param programName
     *            The programName to set.
     */
    public void setProgramName(String programName)
    {
	this.programName = programName;
    }

    /**
     * @return Returns the providerName.
     */
    public String getProviderName()
    {
	return providerName;
    }

    /**
     * @param providerName
     *            The providerName to set.
     */
    public void setProviderName(String providerName)
    {
	this.providerName = providerName;
    }

    public boolean isExpandProviders()
    {
	return expandProviders;
    }

    public void setExpandProviders(boolean expandProviders)
    {
	this.expandProviders = expandProviders;
    }

    public String getSecondaryAction()
    {
	return secondaryAction;
    }

    public void setSecondaryAction(String secondaryAction)
    {
	this.secondaryAction = secondaryAction;
    }

    public Date getCurrentDate()
    {
	return currentDate;
    }

    public void setCurrentDate(Date currentDate)
    {
	this.currentDate = currentDate;
    }

    public String getSaveEventDate()
    {
	return saveEventDate;
    }

    public void setSaveEventDate(String saveEventDate)
    {
	this.saveEventDate = saveEventDate;
    }

    public Date getSaveEventDateYYMMDD()
    {
	return saveEventDateYYMMDD;
    }

    public void setSaveEventDateYYMMDD(Date saveEventDateYYMMDD)
    {
	this.saveEventDateYYMMDD = saveEventDateYYMMDD;
    }

    public String getBirthDate()
    {
	return birthDate;
    }

    public void setBirthDate(String birthDate)
    {
	this.birthDate = birthDate;
    }

    public String getJuvenileFullName()
    {
	return juvenileFullName;
    }

    public void setJuvenileFullName(String juvenileFullName)
    {
	this.juvenileFullName = juvenileFullName;
    }

    /**
     * @return the eventFromDate
     */
    public String getEventFromDate()
    {
	return eventFromDate;
    }

    /**
     * @param eventFromDate
     *            the eventFromDate to set
     */
    public void setEventFromDate(String eventFromDate)
    {
	this.eventFromDate = eventFromDate;
    }

    /**
     * @return the eventToDate
     */
    public String getEventToDate()
    {
	return eventToDate;
    }

    /**
     * @param eventToDate
     *            the eventToDate to set
     */
    public void setEventToDate(String eventToDate)
    {
	this.eventToDate = eventToDate;
    }

    /**
     * @return the serviceProviderList
     */
    public List getServiceProviderList()
    {
	return serviceProviderList;
    }

    /**
     * @param serviceProviderList
     *            the serviceProviderList to set
     */
    public void setServiceProviderList(List serviceProviderList)
    {
	this.serviceProviderList = serviceProviderList;
    }

    /**
     * @return the eventList
     */
    public List getEventList()
    {
	return eventList;
    }

    /**
     * @param eventList
     *            the eventList to set
     */
    public void setEventList(List eventList)
    {
	this.eventList = eventList;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName()
    {
	return serviceName;
    }

    /**
     * @param serviceName
     *            the serviceName to set
     */
    public void setServiceName(String serviceName)
    {
	this.serviceName = serviceName;
    }

    /**
     * @return the serviceProviderName
     */
    public String getServiceProviderName()
    {
	return serviceProviderName;
    }

    /**
     * @param serviceProviderName
     *            the serviceProviderName to set
     */
    public void setServiceProviderName(String serviceProviderName)
    {
	this.serviceProviderName = serviceProviderName;
    }

    /**
     * @return the selectedEventIds
     */
    public String[] getSelectedEventIds()
    {
	return selectedEventIds;
    }

    /**
     * @param selectedEventIds
     *            the selectedEventIds to set
     */
    public void setSelectedEventIds(String[] selectedEventIds)
    {
	this.selectedEventIds = selectedEventIds;
    }

    /**
     * @return the programList
     */
    public List getProgramList()
    {
	return programList;
    }

    /**
     * @param programList
     *            the programList to set
     */
    public void setProgramList(List programList)
    {
	this.programList = programList;
    }

    /**
     * @return the selectedServiceId
     */
    public String getSelectedServiceId()
    {
	return selectedServiceId;
    }

    /**
     * @param selectedServiceId
     *            the selectedServiceId to set
     */
    public void setSelectedServiceId(String selectedServiceId)
    {
	this.selectedServiceId = selectedServiceId;
    }

    /**
     * @return the selectedEventsList
     */
    public List getSelectedEventsList()
    {
	return selectedEventsList;
    }

    /**
     * @param selectedEventsList
     *            the selectedEventsList to set
     */
    public void setSelectedEventsList(List selectedEventsList)
    {
	this.selectedEventsList = selectedEventsList;
    }

    /**
     * @return the eventType
     */
    public String getEventType()
    {
	return eventType;
    }

    /**
     * @param eventType
     *            the eventType to set
     */
    public void setEventType(String eventType)
    {
	this.eventType = eventType;
    }

    /**
     * @return the referralFound
     */
    public boolean isReferralFound()
    {
	return referralFound;
    }

    /**
     * @param referralFound
     *            the referralFound to set
     */
    public void setReferralFound(boolean referralFound)
    {
	this.referralFound = referralFound;
    }

    /**
     * @return the fromPage
     */
    public String getFromPage()
    {
	return fromPage;
    }

    /**
     * @param fromPage
     *            the fromPage to set
     */
    public void setFromPage(String fromPage)
    {
	this.fromPage = fromPage;
    }

    /**
     * @return the pageState
     */
    public String getPageState()
    {
	return pageState;
    }

    /**
     * @param pageState
     *            the pageState to set
     */
    public void setPageState(String pageState)
    {
	this.pageState = pageState;
    }

    /**
     * @return the confirmationMsg
     */
    public String getConfirmationMsg()
    {
	return confirmationMsg;
    }

    /**
     * @param confirmationMsg
     *            the confirmationMsg to set
     */
    public void setConfirmationMsg(String confirmationMsg)
    {
	this.confirmationMsg = confirmationMsg;
    }

    public List<JuvenileCoreLightResponseEvent> getJuveniles()
    {
	return juveniles;
    }

    public void setJuveniles(List<JuvenileCoreLightResponseEvent> juveniles)
    {
	this.juveniles = juveniles;
    }

    public Collection getJuvenileProfiles()
    {
	return juvenileProfiles;
    }

    public void setJuvenileProfiles(Collection juvenileProfiles)
    {
	this.juvenileProfiles = juvenileProfiles;
    }
    // end value added for SP events

    public String getSearchType()
    {
	return searchType;
    }

    public void setSearchType(String searchType)
    {
	this.searchType = searchType;
    }

    /**
     * @return the allowUpdates
     */
    public boolean isAllowUpdates()
    {
	return allowUpdates;
    }

    /**
     * @param allowUpdates
     *            the allowUpdates to set
     */
    public void setAllowUpdates(boolean allowUpdates)
    {
	this.allowUpdates = allowUpdates;
    }

    /**
     * @return the allowPrePetitionUpdates
     */
    public boolean isAllowPrePetitionUpdates()
    {
	return allowPrePetitionUpdates;
    }

    /**
     * @param allowPrePetitionUpdates
     *            the allowPrePetitionUpdates to set
     */
    public void setAllowPrePetitionUpdates(boolean allowPrePetitionUpdates)
    {
	this.allowPrePetitionUpdates = allowPrePetitionUpdates;
    }

    public String getHasProgramReferral()
    {
	return hasProgramReferral;
    }

    public void setHasProgramReferral(String hasProgramReferral)
    {
	this.hasProgramReferral = hasProgramReferral;
    }

    /**
     * @return the juvPgmReferralId
     */
    public String getJuvPgmReferralId()
    {
	return juvPgmReferralId;
    }

    /**
     * @param juvPgmReferralId
     *            the juvPgmReferralId to set
     */
    public void setJuvPgmReferralId(String juvPgmReferralId)
    {
	this.juvPgmReferralId = juvPgmReferralId;
    }

    public List getActiveFacilityList()
    {
	return activeFacilityList;
    }

    public void setActiveFacilityList(List activeFacilityList)
    {
	this.activeFacilityList = activeFacilityList;
    }

    public List getOffenseInvolvedWeaponList()
    {
	return offenseInvolvedWeaponList;
    }

    public void setOffenseInvolvedWeaponList(List offenseInvolvedWeaponList)
    {
	this.offenseInvolvedWeaponList = offenseInvolvedWeaponList;
    }

    public List getTypeOfWeapons()
    {
	return typeOfWeapons;
    }

    public void setTypeOfWeapons(List typeOfWeapons)
    {
	this.typeOfWeapons = typeOfWeapons;
    }

    // begin getter and setters for SAN involved weapons
    public void involvedWeaponClear()
    {
	offenseCategoryDescription = null;
	offenseDescription = null;
	typeOfWeaponDescription = null;
	typeOfWeaponId = null;
	weaponDescription = null;
	weaponInvolvedStr = null;
    }

    /**
     * @return the offenseCategoryDescription
     */
    public String[] getOffenseCategoryDescription()
    {
	return offenseCategoryDescription;
    }

    /**
     * @param offenseCategoryDescription
     *            the offenseCategoryDescription to set
     */
    public void setOffenseCategoryDescription(String[] offenseCategoryDescription)
    {
	this.offenseCategoryDescription = offenseCategoryDescription;
    }

    /**
     * @return the offenseDescription
     */
    public String[] getOffenseDescription()
    {
	return offenseDescription;
    }

    /**
     * @param offenseDescription
     *            the offenseDescription to set
     */
    public void setOffenseDescription(String[] offenseDescription)
    {
	this.offenseDescription = offenseDescription;
    }

    /**
     * @return the typeOfWeaponDescription
     */
    public String[] getTypeOfWeaponDescription()
    {
	return typeOfWeaponDescription;
    }

    /**
     * @param typeOfWeaponDescription
     *            the typeOfWeaponDescription to set
     */
    public void setTypeOfWeaponDescription(String[] typeOfWeaponDescription)
    {
	this.typeOfWeaponDescription = typeOfWeaponDescription;
    }

    /**
     * @return the typeOfWeaponId
     */
    public String[] getTypeOfWeaponId()
    {
	return typeOfWeaponId;
    }

    /**
     * @param typeOfWeaponId
     *            the typeOfWeaponId to set
     */
    public void setTypeOfWeaponId(String[] typeOfWeaponId)
    {
	this.typeOfWeaponId = typeOfWeaponId;
    }

    /**
     * @return the weaponDescription
     */
    public String[] getWeaponDescription()
    {
	return weaponDescription;
    }

    /**
     * @param weaponDescription
     *            the weaponDescription to set
     */
    public void setWeaponDescription(String[] weaponDescription)
    {
	this.weaponDescription = weaponDescription;
    }

    /**
     * @return the weaponInvolvedStr
     */
    public String[] getWeaponInvolvedStr()
    {
	return weaponInvolvedStr;
    }

    /**
     * @param weaponInvolvedStr
     *            the weaponInvolvedStr to set
     */
    public void setWeaponInvolvedStr(String[] weaponInvolvedStr)
    {
	this.weaponInvolvedStr = weaponInvolvedStr;
    }

    // end getter and setters for SAN involved weapons
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1)
    {
	if (this.getPreScheduledEvents() != null)
	{
	    Collection<ServiceProvider> pse = this.getPreScheduledEvents();
	    List<CalendarServiceEventResponseEvent> justList = new ArrayList();

	    ((ArrayList) justList).ensureCapacity(pse.size());

	    for (ServiceProvider sp : pse)
	    {
		justList.addAll(sp.getServiceEventResponseEvents());
	    }

	    if (justList.size() > 0)
	    {
		for (CalendarServiceEventResponseEvent csere : justList)
		{
		    csere.setPreScheduledSelected(false);
		}
	    }
	}
    }

    public String getJuvenileFirstName()
    {
	return juvenileFirstName;
    }

    public void setJuvenileFirstName(String juvenileFirstName)
    {
	this.juvenileFirstName = juvenileFirstName;
    }

    /***********************************************************************
     * BEGIN CLASS DEFINITIONS
     ***********************************************************************/
    public static class ServiceProvider implements Comparable
    {
	private String serviceProviderName;
	private String serviceProviderId;
	private Collection preScheduledServices;
	private Map preScheduledServicesMap;

	/**
	 * 
	 */
	public ServiceProvider()
	{
	    preScheduledServicesMap = new HashMap();
	}

	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId()
	{
	    return serviceProviderId;
	}

	/**
	 * @param serviceProviderId
	 *            The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId)
	{
	    this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName()
	{
	    return serviceProviderName;
	}

	/**
	 * @param serviceProviderName
	 *            The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName)
	{
	    this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return Returns the serviceResponseEvents.
	 */
	public Collection getServiceEventResponseEvents()
	{
	    Collection<Service> pss = getPreScheduledServices();
	    ArrayList serviceResponseEvents = new ArrayList();
	    if (pss != null)
	    {
		serviceResponseEvents.ensureCapacity(pss.size());
		for (Service service : pss)
		{
		    serviceResponseEvents.addAll(service.getEventList());
		}
	    }

	    return serviceResponseEvents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
	    ServiceProvider serviceProvider = (ServiceProvider) o;
	    return this.getServiceProviderName().compareTo(serviceProvider.getServiceProviderName());
	}

	/**
	 * @return Returns the preScheduledServices.
	 */
	public Collection getPreScheduledServices()
	{
	    if (preScheduledServicesMap == null)
	    {
		return null;
	    }

	    Collection<Service> svcList = preScheduledServicesMap.values();
	    preScheduledServices = new ArrayList();
	    ((ArrayList) preScheduledServices).ensureCapacity(svcList.size());

	    for (Service service : svcList)
	    {
		preScheduledServices.add(service);
	    }

	    Collections.sort((ArrayList) preScheduledServices);

	    return preScheduledServices;
	}

	/**
	 * @param preScheduledServices
	 *            The preScheduledServices to set.
	 */
	public void setPreScheduledServices(Collection preScheduledServices)
	{
	    this.preScheduledServices = preScheduledServices;
	}

	public void setResponseEvents(CalendarServiceEventResponseEvent event)
	{
	    if (preScheduledServicesMap.get(event.getProgramId()) == null)
	    {
		Service service = new Service();

		service.setService(event.getServiceName());
		service.setServiceId(event.getServiceId());
		service.setProgramName(event.getProgramName());
		service.setProgramId(event.getProgramId());
		service.setHasProgramReferral(event.getHasProgramReferral());
		List evtList = new ArrayList();
		evtList.add(event);
		service.setEventList(evtList);
		preScheduledServicesMap.put(event.getProgramId() + UIConstants.EMPTY_STRING, service);
	    }
	    else
	    {
		Service service = (Service) preScheduledServicesMap.get(event.getProgramId());
		List evtList = (ArrayList) service.getEventList();
		evtList.add(event);
	    }
	}
    } // end class ServiceProvider

    public static class Service implements Comparable
    {
	private Collection locationsList;
	private Collection facilitiesList;
	private Collection serviceTypeList;
	private Collection serviceProviders;
	private Collection eventList;
	private Collection instructorList;
	private Collection sessionLengthIntList;

	Event currentEvent;

	private String memberAddressId;
	private String memberLocation;

	private String locationId;
	private String facilityId;
	private String facility;

	private String location;

	private String serviceTypeId;
	private String serviceType;
	private String serviceTypeCode;
	private String serviceTypeCategory = UIConstants.EMPTY_STRING;
	private String serviceProviderId;
	private String serviceProvider;

	private String serviceId;
	private String service;

	private String programId;
	private String programName;
	private Date programStartDate;
	private Date programEndDate;
	private String maxEnrollment;

	private Collection familyMemberLocations;
	private Collection intervieweeList;
	private Collection selectedIntervieweeList;
	private String[] selectedIntervieweeArray;
	private Collection interviewTypeList;
	private String interviewTypeId;
	private String interviewType;

	private Address newAddress;

	private Collection stateList;
	private String stateName;
	private Collection countyList;
	private String countyName;
	private Collection streetNumSuffixList;
	private String streetNumSuffixName;
	private Collection streetTypeList;
	private String streetTypeName;
	private Collection addressTypeList;
	private String addressTypeName;

	private boolean isInterviewListSet;

	private String schoolDistrictId;
	private String schoolDistrictName;
	private String schoolId;
	private String schoolName;
	private String schoolStreet;
	private String schoolCity;
	private String schoolState;
	private String schoolZip;
	private String schoolPhone;
	private Collection schoolDistricts;
	private Map schoolMap;
	private Collection schools;
	private Collection offenses;
	private String contactFirstName;
	private String contactLastName;

	private boolean inHouse;

	private String hasProgramReferral;

	private boolean sexOffenderRegistrant;
	private String sexOffenderRegistrantStr;
	private String restrictionsOther;
	private boolean weaponInvolved;
	private String weaponInvolvedStr;
	private String typeOfWeaponId;
	private List typeOfWeapons;
	private String typeOfWeaponDescription;
	private String weaponDescription;

	//User Story 11109
	private Collection referrals;
	private String controllingReferral;
	private String letterType;
	private String petitionNumber;

	/**
	 * @return the referrals
	 */
	public Collection getReferrals()
	{
	    return referrals;
	}

	/**
	 * @param referrals
	 *            the referrals to set
	 */
	public void setReferrals(Collection referrals)
	{
	    this.referrals = referrals;
	}

	/**
	 * @return the controllingReferral
	 */
	public String getControllingReferral()
	{
	    return controllingReferral;
	}

	/**
	 * @param controllingReferral
	 *            the controllingReferral to set
	 */
	public void setControllingReferral(String controllingReferral)
	{
	    this.controllingReferral = controllingReferral;
	}

	public Service()
	{
	    currentEvent = new Event();

	    locationId = UIConstants.EMPTY_STRING;
	    facilityId = UIConstants.EMPTY_STRING;
	    location = UIConstants.EMPTY_STRING;
	    facility = UIConstants.EMPTY_STRING;
	    serviceTypeId = UIConstants.EMPTY_STRING;
	    serviceTypeCode = UIConstants.EMPTY_STRING;
	    serviceTypeCategory = UIConstants.EMPTY_STRING;
	    serviceType = UIConstants.EMPTY_STRING;
	    serviceProviderId = UIConstants.EMPTY_STRING;
	    serviceProvider = UIConstants.EMPTY_STRING;
	    serviceId = UIConstants.EMPTY_STRING;
	    service = UIConstants.EMPTY_STRING;
	    schoolDistrictId = UIConstants.EMPTY_STRING;
	    schoolId = UIConstants.EMPTY_STRING;
	    schoolName = UIConstants.EMPTY_STRING;
	    schoolStreet = UIConstants.EMPTY_STRING;
	    schoolCity = UIConstants.EMPTY_STRING;
	    schoolState = UIConstants.EMPTY_STRING;
	    schoolZip = UIConstants.EMPTY_STRING;
	    schoolPhone = UIConstants.EMPTY_STRING;
	    schoolDistrictName = UIConstants.EMPTY_STRING;
	    interviewType = UIConstants.EMPTY_STRING;
	    interviewTypeId = UIConstants.EMPTY_STRING;
	    memberAddressId = UIConstants.EMPTY_STRING;
	    memberLocation = UIConstants.EMPTY_STRING;

	    locationsList = emptyColl;
	    facilitiesList = emptyColl;
	    serviceTypeList = emptyColl;
	    serviceProviders = emptyColl;
	    eventList = emptyColl;
	    instructorList = emptyColl;
	    familyMemberLocations = emptyColl;
	    intervieweeList = emptyColl;
	    sessionLengthIntList = emptyColl;

	    selectedIntervieweeArray = new String[0];
	    selectedIntervieweeList = emptyColl;
	    interviewTypeList = emptyColl;
	    schoolDistricts = emptyColl;
	    schools = emptyColl;
	    schoolMap = new HashMap();
	    newAddress = new Address();
	    stateList = emptyColl;
	    countyList = emptyColl;
	    streetTypeList = emptyColl;
	    addressTypeList = emptyColl;
	    isInterviewListSet = false;
	    streetNumSuffixList = emptyColl;
	}

	public void clear()
	{
	    locationId = UIConstants.EMPTY_STRING;
	    facilityId = UIConstants.EMPTY_STRING;
	    location = UIConstants.EMPTY_STRING;
	    facility = UIConstants.EMPTY_STRING;
	    serviceProviderId = UIConstants.EMPTY_STRING;
	    serviceProvider = UIConstants.EMPTY_STRING;
	    serviceId = UIConstants.EMPTY_STRING;
	    service = UIConstants.EMPTY_STRING;
	    schoolDistrictId = UIConstants.EMPTY_STRING;
	    schoolId = UIConstants.EMPTY_STRING;
	    schoolName = UIConstants.EMPTY_STRING;
	    schoolStreet = UIConstants.EMPTY_STRING;
	    schoolCity = UIConstants.EMPTY_STRING;
	    schoolState = UIConstants.EMPTY_STRING;
	    schoolZip = UIConstants.EMPTY_STRING;
	    schoolDistrictName = UIConstants.EMPTY_STRING;
	    interviewType = UIConstants.EMPTY_STRING;
	    interviewTypeId = UIConstants.EMPTY_STRING;
	    memberAddressId = UIConstants.EMPTY_STRING;
	    memberLocation = UIConstants.EMPTY_STRING;
	    contactLastName = UIConstants.EMPTY_STRING;
	    contactFirstName = UIConstants.EMPTY_STRING;
	    sexOffenderRegistrant = false;
	    sexOffenderRegistrantStr = UIConstants.EMPTY_STRING;
	    restrictionsOther = UIConstants.EMPTY_STRING;
	    weaponInvolved = false;
	    weaponInvolvedStr = UIConstants.EMPTY_STRING;
	    typeOfWeaponId = UIConstants.EMPTY_STRING;
	    typeOfWeaponDescription = UIConstants.EMPTY_STRING;
	    weaponDescription = UIConstants.EMPTY_STRING;
	    currentEvent.clear();
	}

	/**
	 * @return Returns the maxEnrollment.
	 */
	public String getMaxEnrollment()
	{
	    return maxEnrollment;
	}

	/**
	 * @param maxEnrollment
	 *            The maxEnrollment to set.
	 */
	public void setMaxEnrollment(String maxEnrollment)
	{
	    this.maxEnrollment = maxEnrollment;
	}

	/**
	 * @return Returns the programEndDate.
	 */
	public Date getProgramEndDate()
	{
	    return programEndDate;
	}

	/**
	 * @param programEndDate
	 *            The programEndDate to set.
	 */
	public void setProgramEndDate(Date programEndDate)
	{
	    this.programEndDate = programEndDate;
	}

	/**
	 * @return Returns the programStartDate.
	 */
	public Date getProgramStartDate()
	{
	    return programStartDate;
	}

	/**
	 * @param programStartDate
	 *            The programStartDate to set.
	 */
	public void setProgramStartDate(Date programStartDate)
	{
	    this.programStartDate = programStartDate;
	}

	/**
	 * @return
	 */
	public String getLocation()
	{
	    return location;
	}

	public String getFacility()
	{
	    return facility;
	}

	/**
	 * @return
	 */
	public String getLocationId()
	{
	    return locationId;
	}

	public String getFacilityId()
	{
	    return facilityId;
	}

	public void setFacilityId(String string)
	{
	    facilityId = string;
	}

	/**
	 * @return
	 */
	public String getService()
	{
	    return service;
	}

	/**
	 * @return
	 */
	public String getServiceId()
	{
	    return serviceId;
	}

	/**
	 * @return
	 */
	public String getServiceProvider()
	{
	    return serviceProvider;
	}

	/**
	 * @return
	 */
	public String getServiceProviderId()
	{
	    return serviceProviderId;
	}

	/**
	 * @return
	 */
	public String getServiceType()
	{
	    return serviceType;
	}

	/**
	 * @return
	 */
	public String getServiceTypeId()
	{
	    return serviceTypeId;
	}

	/**
	 * @param string
	 */
	public void setLocationId(String string)
	{
	    locationId = string;

	    if (locationsList != null && !locationId.equals(UIConstants.USER_ENTERED_CUSTOM_ADDRESS))
	    {
		Collection<LocationResponseEvent> locList = locationsList;
		for (LocationResponseEvent sre : locList)
		{
		    if (sre.getJuvLocationUnitId().equals(locationId))
		    {
			location = sre.getLocationUnitName();
			break;
		    }
		}
	    }
	}

	/**
	 * @param string
	 */
	public void setServiceId(String string)
	{
	    serviceId = string;
	}

	/**
	 * @param string
	 */
	public void setServiceProvider(String string)
	{
	    serviceProvider = string;
	}

	/**
	 * @param string
	 */
	public void setServiceProviderId(String string)
	{
	    serviceProviderId = string;
	}

	/**
	 * @return
	 */
	public Event getCurrentEvent()
	{
	    return currentEvent;
	}

	/**
	 * @param event
	 */
	public void setCurrentEvent(Event event)
	{
	    currentEvent = event;
	}

	/**
	 * @return
	 */
	public Collection getServiceTypeList()
	{
	    return serviceTypeList;
	}

	/**
	 * @param collection
	 */
	public void setServiceTypeList(Collection collection)
	{
	    serviceTypeList = collection;
	}

	/**
	 * @return
	 */
	public Collection getEventList()
	{
	    return eventList;
	}

	/**
	 * @param collection
	 */
	public void setEventList(Collection collection)
	{
	    eventList = collection;
	}

	/**
	 * @return
	 */
	public Collection getServiceProviders()
	{
	    return serviceProviders;
	}

	/**
	 * @param collection
	 */
	public void setServiceProviders(Collection collection)
	{
	    serviceProviders = collection;
	}

	/**
	 * @return
	 */
	public Collection getInstructorList()
	{
	    Collections.sort((List) instructorList);
	    return instructorList;
	}

	/**
	 * @param collection
	 */
	public void setInstructorList(Collection collection)
	{
	    instructorList = collection;
	}

	/**
	 * @return
	 */
	public Collection getLocationsList()
	{
	    return locationsList;
	}

	/**
	 * @param collection
	 */
	public void setLocationsList(Collection collection)
	{
	    locationsList = collection;
	}

	/**
	 * @param collection
	 */
	public void setFacilitiesList(Collection collection)
	{
	    facilitiesList = collection;
	}

	/**
	 * @return
	 */
	public Collection getFacilitiesList()
	{
	    return facilitiesList;
	}

	/**
	 * @param string
	 */
	public void setLocation(String string)
	{
	    location = string;
	    //<KISHORE>JIMS200059076 : Calendar: Add new event type Job Visit (UI) - KK
	    // For Job_Visit events, locations list will be a list of LocationResponseEvents
	    // with only the Location name && Location id populated as needed
	    if (locationsList != null)
	    {
		Collection<LocationResponseEvent> locList = locationsList;
		for (LocationResponseEvent sre : locList)
		{
		    if (sre.getLocationId().equals(location))
		    {
			setMemberLocation(sre.getLocationName());
			break;
		    }
		}
	    }
	}

	public void setFacility(String string)
	{
	    facility = string;
	}

	/**
	 * @param string
	 */
	public void setService(String string)
	{
	    service = string;
	}

	/**
	 * @param string
	 */
	public void setServiceType(String string)
	{
	    serviceType = string;
	}

	/**
	 * @return
	 */
	public boolean isInHouse()
	{
	    return inHouse;
	}

	/**
	 * @param b
	 */
	public void setInHouse(boolean b)
	{
	    inHouse = b;
	}

	/**
	 * @return Returns the serviceTypeCategory.
	 */
	public String getServiceTypeCategory()
	{
	    return serviceTypeCategory;
	}

	/**
	 * @param serviceTypeCategory
	 *            The serviceTypeCategory to set.
	 */
	public void setServiceTypeCategory(String serviceTypeCategory)
	{
	    this.serviceTypeCategory = serviceTypeCategory;
	}

	/**
	 * @return Returns the familyMemberLocations.
	 */
	public Collection getFamilyMemberLocations()
	{
	    return familyMemberLocations;
	}

	/**
	 * @param familyMemberLocations
	 *            The familyMemberLocations to set.
	 */
	public void setFamilyMemberLocations(Collection familyMemberLocations)
	{
	    this.familyMemberLocations = familyMemberLocations;
	}

	/**
	 * @return Returns the interviewTypeList.
	 */
	public Collection getInterviewTypeList()
	{
	    return interviewTypeList;
	}

	/**
	 * @param interviewTypeList
	 *            The interviewTypeList to set.
	 */
	public void setInterviewTypeList(Collection interviewTypeList)
	{
	    this.interviewTypeList = new ArrayList();
	    if (interviewTypeList != null && !interviewTypeList.isEmpty())
	    {
		this.interviewTypeList = interviewTypeList;
	    }
	}

	/**
	 * @return Returns the schoolDistricts.
	 */
	public Collection getSchoolDistricts()
	{
	    return schoolDistricts;
	}

	public void setSchoolDistricts(Collection aSchoolDistricts)
	{
	    HashMap districtMap = new HashMap();
	    schoolMap = new HashMap();

	    Collection<JuvenileSchoolDistrictCodeResponseEvent> districtList = aSchoolDistricts;
	    for (JuvenileSchoolDistrictCodeResponseEvent school : districtList)
	    {
		String districtKey = school.getDistrictCode();
		if (!districtMap.containsKey(districtKey))
		{
		    districtMap.put(districtKey, school);
		}

		// Add school
		Collection schools = (Collection) schoolMap.get(districtKey);
		if (schools == null)
		{
		    schools = new ArrayList();
		    schools.add(school);
		    schoolMap.put(districtKey, schools);
		}

	    }

	    ArrayList schoolDistrictList = new ArrayList();
	    schoolDistrictList.ensureCapacity(districtMap.size());

	    Iterator iter = districtMap.entrySet().iterator();
	    while (iter.hasNext())
	    {
		Map.Entry entry = (Map.Entry) iter.next();
		schoolDistrictList.add(entry.getValue());
	    }

	    Collections.sort((List) schoolDistrictList, new SchoolDistrictComparator());
	    this.schoolDistricts = schoolDistrictList;
	    setSchoolMap(schoolMap);
	    schoolMap.clear();
	}

	/**
	 * @return Returns the schools.
	 */
	public Collection getSchools()
	{
	    return schools;
	}

	/**
	 * @param schools
	 *            The schools to set.
	 */
	public void setSchools(Collection schools)
	{
	    this.schools = schools;
	}

	public Collection getOffenses()
	{
	    return offenses;
	}

	public void setOffenses(Collection offenses)
	{
	    this.offenses = offenses;
	}

	/**
	 * @return Returns the serviceTypeCode.
	 */
	public String getServiceTypeCode()
	{
	    return serviceTypeCode;
	}

	/**
	 * @param serviceTypeCode
	 *            The serviceTypeCode to set.
	 */
	public void setServiceTypeCode(String serviceTypeCode)
	{
	    this.serviceTypeCode = serviceTypeCode;
	    if (serviceTypeList != null && (serviceTypeCode.length() > 0))
	    {
		Collection<ServiceTypeCdResponseEvent> svcList = serviceTypeList;
		for (ServiceTypeCdResponseEvent sre : svcList)
		{
		    if (sre.getServiceTypeCode().equals(serviceTypeCode))
		    {
			setServiceTypeId(sre.getServiceTypeId());
			setServiceType(sre.getDescription());
			setServiceTypeCategory(sre.getCategory());
		    }
		}
	    }
	    else
	    {
		setServiceTypeId(UIConstants.EMPTY_STRING);
		setServiceType(UIConstants.EMPTY_STRING);
		setServiceTypeCategory(UIConstants.EMPTY_STRING);
	    }
	}

	/**
	 * @param string
	 */
	public void setServiceTypeId(String string)
	{
	    serviceTypeId = string;

	}
	/*public void setServiceTypeId( String string )
	{
		this.serviceTypeId = string ;
		if( string != null && (string.length() > 0) )
		{
			Collection<ServiceTypeCdResponseEvent> svcList = serviceTypeList ;
			for( ServiceTypeCdResponseEvent sre : svcList )
			{
				if( sre.getServiceTypeId().equals( string ) )
				{
					setServiceTypeCode( sre.getServiceTypeCode() ) ;
					setServiceType( sre.getDescription() ) ;
					setServiceTypeCategory( sre.getCategory() ) ;
				}
			}
		}
		else
		{
		    	setServiceTypeCode( UIConstants.EMPTY_STRING ) ;
			setServiceType( UIConstants.EMPTY_STRING ) ;
			setServiceTypeCategory( UIConstants.EMPTY_STRING ) ;
		}
	}*/

	/**
	 * @return Returns the memberAddressId.
	 */
	public String getMemberAddressId()
	{
	    return memberAddressId;
	}

	/**
	 * @return Returns the memberLocation.
	 */
	public String getMemberLocation()
	{
	    return memberLocation;
	}

	/**
	 * @param memberLocation
	 *            The memberLocation to set.
	 */
	public void setMemberLocation(String memberLocation)
	{
	    this.memberLocation = memberLocation;
	}

	/**
	 * @return Returns the schoolMap.
	 */
	public Map getSchoolMap()
	{
	    return schoolMap;
	}

	/**
	 * @param schoolMap
	 *            The schoolMap to set.
	 */
	public void setSchoolMap(Map schoolMap)
	{
	    this.schoolMap = schoolMap;
	}

	/**
	 * @return Returns the schoolDistrictId.
	 */
	public String getSchoolDistrictId()
	{
	    return schoolDistrictId;
	}

	/**
	 * @param schoolDistrictId
	 *            The schoolDistrictId to set.
	 */
	public void setSchoolDistrictId(String schoolDistrictId)
	{
	    this.schoolDistrictId = schoolDistrictId;
	    if (getSchoolDistricts() != null)
	    {
		Collection<JuvenileSchoolDistrictCodeResponseEvent> districtList = getSchoolDistricts();
		for (JuvenileSchoolDistrictCodeResponseEvent district : districtList)
		{
		    if (district.getDistrictCode().equals(schoolDistrictId))
		    {
			setSchoolDistrictName(district.getDistrictDescription());
			break;
		    }
		}
	    }
	}

	/**
	 * @return Returns the schoolId.
	 */
	public String getSchoolId()
	{
	    return schoolId;
	}

	/**
	 * @param schoolId
	 *            The schoolId to set.
	 */
	public void setSchoolId(String schoolId)
	{
	    //<KISHORE>JIMS200058872 : Calendar:  Modify School Visit Location (UI) - KK
	    if (schoolId != null && schoolId.length() > 0)
	    {
		int sindex = schoolId.indexOf("/");
		this.schoolDistrictId = schoolId.substring(0, sindex);
		this.schoolId = schoolId.substring(sindex + 1, schoolId.length());
		if (getSchools() != null)
		{
		    Collection<JuvenileSchoolHistoryResponseEvent> schoolList = getSchools();
		    for (JuvenileSchoolHistoryResponseEvent school : schoolList)
		    {
			if (school.getSchoolId().equals(schoolId))
			{
			    String nameStr = school.getSchool();
			    int nindex = nameStr.indexOf("/");
			    setSchoolDistrictName(nameStr.substring(0, nindex));
			    setSchoolName(nameStr.substring(nindex + 1, nameStr.length()));
			    setSchoolStreet(school.getSchoolStreet());
			    setSchoolCity(school.getSchoolCity());
			    setSchoolState(school.getSchoolState());
			    setSchoolZip(school.getSchoolZip());
			    setSchoolPhone(school.getSchoolPhone());
			    break;
			}
		    }
		}
	    }
	}

	/**
	 * @return Returns the intervieweeList.
	 */
	public Collection getIntervieweeList()
	{
	    return intervieweeList;
	}

	/**
	 * @param intervieweeList
	 *            The intervieweeList to set.
	 */
	public void setIntervieweeList(Collection intervieweeList)
	{
	    this.intervieweeList = intervieweeList;
	}

	/**
	 * @return Returns the selectedIntervieweeList.
	 */
	public Collection getSelectedIntervieweeList()
	{
	    return selectedIntervieweeList;
	}

	/**
	 * @param selectedIntervieweeList
	 *            The selectedIntervieweeList to set.
	 */
	public void setSelectedIntervieweeList(Collection selectedIntervieweeList)
	{
	    this.selectedIntervieweeList = selectedIntervieweeList;
	}

	/**
	 * @return Returns the selectedIntervieweeArray.
	 */
	public String[] getSelectedIntervieweeArray()
	{
	    return selectedIntervieweeArray;
	}

	/**
	 * @param selectedIntervieweeArray
	 *            The selectedIntervieweeArray to set.
	 */
	public void setSelectedIntervieweeArray(String[] selectedIntervieweeArray)
	{
	    this.selectedIntervieweeArray = selectedIntervieweeArray;
	    Collection persons = new ArrayList();

	    Collection<InterviewPersonResponseEvent> interviewList = getIntervieweeList();
	    ((ArrayList) persons).ensureCapacity(interviewList.size());

	    for (String str : selectedIntervieweeArray)
	    {
		for (InterviewPersonResponseEvent person : interviewList)
		{
		    if (str.equalsIgnoreCase(person.getFormattedName()))
		    {
			persons.add(person);
			break;
		    }
		}
	    }
	    setSelectedIntervieweeList(persons);
	}

	/**
	 * @return Returns the interviewTypeId.
	 */
	public String getInterviewTypeId()
	{
	    return interviewTypeId;
	}

	/**
	 * @param interviewTypeId
	 *            The interviewTypeId to set.
	 */
	public void setInterviewTypeId(String interviewTypeId)
	{
	    this.interviewTypeId = interviewTypeId;
	    if (interviewTypeId == null || interviewTypeId.equals(UIConstants.EMPTY_STRING))
	    {
		this.interviewTypeId = UIConstants.EMPTY_STRING;
		return;
	    }

	    if (interviewTypeList != null && interviewTypeList.size() > 0)
	    {
		interviewType = CodeHelper.getCodeDescriptionByCode(interviewTypeList, interviewTypeId);
	    }
	}

	/**
	 * @return Returns the schoolDistrictName.
	 */
	public String getSchoolDistrictName()
	{
	    return schoolDistrictName;
	}

	/**
	 * @param schoolDistrictName
	 *            The schoolDistrictName to set.
	 */
	public void setSchoolDistrictName(String schoolDistrictName)
	{
	    this.schoolDistrictName = schoolDistrictName;
	}

	/**
	 * @return Returns the schoolName.
	 */
	public String getSchoolName()
	{
	    return schoolName;
	}

	/**
	 * @param schoolName
	 *            The schoolName to set.
	 */
	public void setSchoolName(String schoolName)
	{
	    this.schoolName = schoolName;
	}

	/**
	 * @return the schoolStreet
	 */
	public String getSchoolStreet()
	{
	    return schoolStreet;
	}

	/**
	 * @param schoolStreet
	 *            the schoolStreet to set
	 */
	public void setSchoolStreet(String schoolStreet)
	{
	    this.schoolStreet = schoolStreet;
	}

	/**
	 * @return the schoolCity
	 */
	public String getSchoolCity()
	{
	    return schoolCity;
	}

	/**
	 * @param schoolCity
	 *            the schoolCity to set
	 */
	public void setSchoolCity(String schoolCity)
	{
	    this.schoolCity = schoolCity;
	}

	/**
	 * @return the schoolState
	 */
	public String getSchoolState()
	{
	    return schoolState;
	}

	/**
	 * @param schoolState
	 *            the schoolState to set
	 */
	public void setSchoolState(String schoolState)
	{
	    this.schoolState = schoolState;
	}

	/**
	 * @return the schoolZip
	 */
	public String getSchoolZip()
	{
	    return schoolZip;
	}

	/**
	 * @param schoolZip
	 *            the schoolZip to set
	 */
	public void setSchoolZip(String schoolZip)
	{
	    this.schoolZip = schoolZip;
	}

	/**
	 * @return the schoolPhone
	 */
	public String getSchoolPhone()
	{
	    return schoolPhone;
	}

	/**
	 * @param schoolPhone
	 *            the schoolPhone to set
	 */
	public void setSchoolPhone(String schoolPhone)
	{
	    this.schoolPhone = schoolPhone;
	}

	/**
	 * @return Returns the interviewType.
	 */
	public String getInterviewType()
	{
	    return interviewType;
	}

	/**
	 * @param interviewType
	 *            The interviewType to set.
	 */
	public void setInterviewType(String interviewType)
	{
	    this.interviewType = interviewType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object objIn)
	{
	    if (!(objIn instanceof Service))
		return 0;

	    Service service = (Service) objIn;
	    if (this.getService() == null || (this.getService().length() == 0))
		return -1;

	    if (service.getService() == null || (service.getService().length() == 0))
		return 1;

	    return this.getService().compareTo(service.getService());
	}

	/**
	 * @return Returns the addressTypeList.
	 */
	public Collection getAddressTypeList()
	{
	    return addressTypeList;
	}

	/**
	 * @param addressTypeList
	 *            The addressTypeList to set.
	 */
	public void setAddressTypeList(Collection addressTypeList)
	{
	    this.addressTypeList = addressTypeList;
	}

	/**
	 * @return Returns the countyList.
	 */
	public Collection getCountyList()
	{
	    return countyList;
	}

	/**
	 * @param countyList
	 *            The countyList to set.
	 */
	public void setCountyList(Collection countyList)
	{
	    this.countyList = countyList;
	}

	/**
	 * @return Returns the newAddress.
	 */
	public Address getNewAddress()
	{
	    return newAddress;
	}

	/**
	 * @param newAddress
	 *            The newAddress to set.
	 */
	public void setNewAddress(Address newAddress)
	{
	    this.newAddress = newAddress;
	}

	/**
	 * @return Returns the stateList.
	 */
	public Collection getStateList()
	{
	    return stateList;
	}

	/**
	 * @param stateList
	 *            The stateList to set.
	 */
	public void setStateList(Collection stateList)
	{
	    this.stateList = stateList;
	}

	/**
	 * @return Returns the streetTypeList.
	 */
	public Collection getStreetTypeList()
	{
	    return streetTypeList;
	}

	/**
	 * @param streetTypeList
	 *            The streetTypeList to set.
	 */
	public void setStreetTypeList(Collection streetTypeList)
	{
	    this.streetTypeList = streetTypeList;
	}

	/**
	 * @param streetNumSuffixList
	 *            The streetNumSuffixList to set.
	 */
	public void setStreetNumSuffixList(Collection streetNumSuffixList)
	{
	    this.streetNumSuffixList = streetNumSuffixList;
	}

	/**
	 * @return Returns the streetNumSuffixList.
	 */
	public Collection getStreetNumSuffixList()
	{
	    return streetNumSuffixList;
	}

	/**
	 * @return Returns the isInterviewListSet.
	 */
	public boolean isInterviewListSet()
	{
	    return isInterviewListSet;
	}

	/**
	 * @param isInterviewListSet
	 *            The isInterviewListSet to set.
	 */
	public void setInterviewListSet(boolean isInterviewListSet)
	{
	    this.isInterviewListSet = isInterviewListSet;
	}

	/**
	 * @return Returns the addressTypeName.
	 */
	public String getAddressTypeName()
	{
	    return addressTypeName;
	}

	/**
	 * @param addressTypeName
	 *            The addressTypeName to set.
	 */
	public void setAddressTypeName(String addressTypeName)
	{
	    this.addressTypeName = addressTypeName;
	}

	/**
	 * @return Returns the countyName.
	 */
	public String getCountyName()
	{
	    return countyName;
	}

	/**
	 * @param countyName
	 *            The countyName to set.
	 */
	public void setCountyName(String countyName)
	{
	    this.countyName = countyName;
	}

	/**
	 * @return Returns the countyName.
	 */
	public String getStreetNumSuffixName()
	{
	    return streetNumSuffixName;
	}

	/**
	 * @param streetNumSuffixName
	 *            The streetNumSuffixName to set.
	 */
	public void setStreetNumSuffixName(String streetNumSuffixName)
	{
	    this.streetNumSuffixName = streetNumSuffixName;
	}

	/**
	 * @return Returns the stateName.
	 */
	public String getStateName()
	{
	    return stateName;
	}

	/**
	 * @param stateName
	 *            The stateName to set.
	 */
	public void setStateName(String stateName)
	{
	    this.stateName = stateName;
	}

	/**
	 * @return Returns the streetTypeName.
	 */
	public String getStreetTypeName()
	{
	    return streetTypeName;
	}

	/**
	 * @param streetTypeName
	 *            The streetTypeName to set.
	 */
	public void setStreetTypeName(String streetTypeName)
	{
	    this.streetTypeName = streetTypeName;
	}

	/**
	 * @param memberAddressId
	 *            The memberAddressId to set.
	 */
	public void setMemberAddressId(String memberAddressId)
	{
	    this.memberAddressId = memberAddressId;
	    if (getFamilyMemberLocations() != null)
	    {
		Collection<FamilyMemberAddressViewResponseEvent> fmaList = getFamilyMemberLocations();
		for (FamilyMemberAddressViewResponseEvent fma : fmaList)
		{
		    if (fma.getMemberAddressId().equals(memberAddressId))
		    {
			setMemberLocation(fma.getMemberNameAddress());
			break;
		    }
		}
	    }
	}

	/**
	 * @return Returns the programName.
	 */
	public String getProgramName()
	{
	    return programName;
	}

	/**
	 * @param programName
	 *            The programName to set.
	 */
	public void setProgramName(String programName)
	{
	    this.programName = programName;
	}

	/**
	 * @return Returns the programId.
	 */
	public String getProgramId()
	{
	    return programId;
	}

	/**
	 * @param programId
	 *            The programId to set.
	 */
	public void setProgramId(String programId)
	{
	    this.programId = programId;
	}

	/**
	 * @return Returns the sessionLengthIntList.
	 */
	public Collection getSessionLengthIntList()
	{
	    return sessionLengthIntList;
	}

	/**
	 * @param sessionLengthIntList
	 *            The sessionLengthIntList to set.
	 */
	public void setSessionLengthIntList(Collection sessionLengthIntList)
	{
	    this.sessionLengthIntList = sessionLengthIntList;
	}

	public String getHasProgramReferral()
	{
	    return hasProgramReferral;
	}

	public void setHasProgramReferral(String hasProgramReferral)
	{
	    this.hasProgramReferral = hasProgramReferral;
	}

	public String getContactFirstName()
	{
	    return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName)
	{
	    this.contactFirstName = contactFirstName;
	}

	public String getContactLastName()
	{
	    return contactLastName;
	}

	public void setContactLastName(String contactLastName)
	{
	    this.contactLastName = contactLastName;
	}

	public boolean isSexOffenderRegistrant()
	{
	    return sexOffenderRegistrant;
	}

	public void setSexOffenderRegistrant(boolean sexOffenderRegistrant)
	{
	    this.sexOffenderRegistrant = sexOffenderRegistrant;
	}

	public String getSexOffenderRegistrantStr()
	{
	    return sexOffenderRegistrantStr;
	}

	public void setSexOffenderRegistrantStr(String sexOffenderRegistrantStr)
	{
	    this.sexOffenderRegistrantStr = sexOffenderRegistrantStr;
	}

	public String getRestrictionsOther()
	{
	    return restrictionsOther;
	}

	public void setRestrictionsOther(String restrictionsOther)
	{
	    this.restrictionsOther = restrictionsOther;
	}

	public boolean isWeaponInvolved()
	{
	    return weaponInvolved;
	}

	public void setWeaponInvolved(boolean weaponInvolved)
	{
	    this.weaponInvolved = weaponInvolved;
	}

	public String getWeaponInvolvedStr()
	{
	    return weaponInvolvedStr;
	}

	public void setWeaponInvolvedStr(String weaponInvolvedStr)
	{
	    this.weaponInvolvedStr = weaponInvolvedStr;
	}

	public String getTypeOfWeaponId()
	{
	    return typeOfWeaponId;
	}

	public void setTypeOfWeaponId(String typeOfWeaponId)
	{
	    this.typeOfWeaponId = typeOfWeaponId;
	}

	public String getTypeOfWeaponDescription()
	{
	    return typeOfWeaponDescription;
	}

	public void setTypeOfWeaponDescription(String typeOfWeaponDescription)
	{
	    this.typeOfWeaponDescription = typeOfWeaponDescription;
	}

	public List getTypeOfWeapons()
	{
	    return typeOfWeapons;
	}

	public void setTypeOfWeapons(List typeOfWeapons)
	{
	    this.typeOfWeapons = typeOfWeapons;
	}

	public String getWeaponDescription()
	{
	    return weaponDescription;
	}

	public void setWeaponDescription(String weaponDescription)
	{
	    this.weaponDescription = weaponDescription;
	}

	public String getLetterType()
	{
	    return letterType;
	}

	public void setLetterType(String letterType)
	{
	    this.letterType = letterType;
	}

	public String getPetitionNumber()
	{
	    return petitionNumber;
	}

	public void setPetitionNumber(String petitionNumber)
	{
	    this.petitionNumber = petitionNumber;
	}

    } // End class Service 

    public static class Event
    {
	private String proposedEventId; //# U.S.30339 changes

	private Collection conflictingEvents;
	private Collection jpoConflictingEvents;
	private Collection juvenileConflictingEvents;

	private String eventDateStr;
	private String eventTime;

	private String sessionLength;
	private String sessionLengthDesc;

	private Collection workDays;
	private String maxAttendance;
	private String status;

	// boolean recurringEvent;
	private String instructorId;
	private String instructorName;

	private String comments;

	private Date startDate;
	private Date endDate;

	private boolean recurringEvent;
	private String recurrencePattern;
	private String recurrenceStartDate;
	private String recurrenceEndDate;
	private String dailyRecurrenceInterval;
	private String weeklyRecurrenceInterval;
	private String monthlyRecurrenceInterval1;
	private String monthlyRecurrenceInterval2;
	private String recurrenceFrequency;

	private boolean dailyRadio;
	private boolean monthlyRadio;
	private boolean yearlyRadio;
	private boolean frequencyRadio;

	private String monthlyDay;
	private String weeklyDay[];
	private String monthlyWeekNumber;
	private String monthlyWeekDay;
	private String yearlyMonthNumber;
	private String yearlyDay;
	private String yearlyWeekDay;
	private String yearlyWeekNumber;
	private String yearlyMonthNumber1;

	public Event()
	{
	    eventDateStr = UIConstants.EMPTY_STRING;
	    sessionLength = UIConstants.EMPTY_STRING;
	    eventTime = UIConstants.EMPTY_STRING;
	    maxAttendance = UIConstants.EMPTY_STRING;
	    status = UIConstants.EMPTY_STRING;
	    instructorId = UIConstants.EMPTY_STRING;
	    instructorName = UIConstants.EMPTY_STRING;
	    comments = UIConstants.EMPTY_STRING;
	    recurringEvent = false;
	    recurrenceStartDate = UIConstants.EMPTY_STRING;
	    dailyRecurrenceInterval = UIConstants.EMPTY_STRING;
	    weeklyRecurrenceInterval = UIConstants.EMPTY_STRING;
	    monthlyRecurrenceInterval1 = UIConstants.EMPTY_STRING;
	    monthlyRecurrenceInterval2 = UIConstants.EMPTY_STRING;
	    dailyRadio = false;
	    monthlyRadio = false;
	    yearlyRadio = false;
	    frequencyRadio = false;
	    monthlyDay = UIConstants.EMPTY_STRING;
	    recurrenceEndDate = UIConstants.EMPTY_STRING;
	    recurrenceFrequency = UIConstants.EMPTY_STRING;
	    conflictingEvents = emptyColl;
	    jpoConflictingEvents = emptyColl;
	    juvenileConflictingEvents = emptyColl;
	    weeklyDay = new String[0];
	    monthlyWeekNumber = UIConstants.EMPTY_STRING;
	    monthlyWeekDay = UIConstants.EMPTY_STRING;
	    yearlyMonthNumber = UIConstants.EMPTY_STRING;
	    yearlyDay = UIConstants.EMPTY_STRING;
	    yearlyWeekDay = UIConstants.EMPTY_STRING;
	    yearlyMonthNumber1 = UIConstants.EMPTY_STRING;
	    yearlyWeekNumber = UIConstants.EMPTY_STRING;
	}

	public void clear()
	{
	    eventDateStr = UIConstants.EMPTY_STRING;
	    sessionLength = UIConstants.EMPTY_STRING;
	    eventTime = UIConstants.EMPTY_STRING;
	    maxAttendance = UIConstants.EMPTY_STRING;
	    status = UIConstants.EMPTY_STRING;
	    recurringEvent = false;
	    instructorId = UIConstants.EMPTY_STRING;
	    instructorName = UIConstants.EMPTY_STRING;
	    comments = UIConstants.EMPTY_STRING;
	    recurrenceStartDate = UIConstants.EMPTY_STRING;
	    dailyRecurrenceInterval = UIConstants.EMPTY_STRING;
	    weeklyRecurrenceInterval = UIConstants.EMPTY_STRING;
	    monthlyRecurrenceInterval1 = UIConstants.EMPTY_STRING;
	    monthlyRecurrenceInterval2 = UIConstants.EMPTY_STRING;
	    dailyRadio = false;
	    monthlyRadio = false;
	    yearlyRadio = false;
	    frequencyRadio = false;
	    monthlyDay = UIConstants.EMPTY_STRING;
	    recurrenceEndDate = UIConstants.EMPTY_STRING;
	    recurrenceFrequency = UIConstants.EMPTY_STRING;
	    weeklyDay = new String[0];
	    monthlyWeekNumber = UIConstants.EMPTY_STRING;
	    monthlyWeekDay = UIConstants.EMPTY_STRING;
	    yearlyMonthNumber = UIConstants.EMPTY_STRING;
	    yearlyDay = UIConstants.EMPTY_STRING;
	    yearlyWeekDay = UIConstants.EMPTY_STRING;
	    yearlyWeekNumber = UIConstants.EMPTY_STRING;
	    yearlyMonthNumber1 = UIConstants.EMPTY_STRING;
	    conflictingEvents = emptyColl;
	    jpoConflictingEvents = emptyColl;
	    juvenileConflictingEvents = emptyColl;
	}

	public String getSessionLengthVal()
	{
	    int sessionHr = 0;
	    int sessionMin = 0;
	    // The sessionLength code table values are
	    // 5,10,15,20,25,30,35,40,45,50...80 which stands for session length 0.5
	    // hr, 1 hr, 1.5 hrs....8hrs.
	    // Parse out the hour by dividing the value by 10. Parse out minutes by
	    // doing a mod on 10..minutes value is always 0 or 30.
	    if (sessionLength != null && (sessionLength.length() > 0))
	    {
		int sessionLengthInt = 0;
		try
		{
		    sessionLengthInt = Integer.parseInt(sessionLength);
		}
		catch (NumberFormatException nfe)
		{ /*empty*/
		}
		sessionHr = sessionLengthInt / 10;
		if (sessionLengthInt % 10 != 0)
		{
		    sessionMin = 30;
		}

		return (sessionHr + ":" + sessionMin);
	    }

	    return (UIConstants.EMPTY_STRING);
	}

	/**
	 * @return
	 */
	public String getSessionLength()
	{
	    return sessionLength;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
	    return status;
	}

	/**
	 * @param string
	 */
	public void setSessionLength(String string)
	{
	    sessionLength = string;
	    sessionLengthDesc = UIConstants.EMPTY_STRING;
	    sessionLengthDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SESSION_LENGTH_INTERVAL, sessionLength);
	}

	/**
	 * @param string
	 */
	public void setStatus(String string)
	{
	    status = string;
	}

	/**
	 * @return
	 */
	public String getInstructorId()
	{
	    return instructorId;
	}

	/**
	 * @return
	 */
	public String getInstructorName()
	{
	    return instructorName;
	}

	/**
	 * @param string
	 */
	public void setInstructorId(String string)
	{
	    instructorId = string;
	}

	/**
	 * @param string
	 */
	public void setInstructorName(String string)
	{
	    instructorName = string;
	}

	/**
	 * @return
	 */
	public String getEventTime()
	{
	    return eventTime;
	}

	/**
	 * @param string
	 */
	public void setEventTime(String string)
	{
	    eventTime = string;
	}

	/**
	 * @return
	 */
	public Collection getWorkDays()
	{
	    return workDays;
	}

	/**
	 * @param collection
	 */
	public void setWorkDays(Collection collection)
	{
	    workDays = collection;
	}

	/**
	 * @return
	 */
	public String getComments()
	{
	    return comments;
	}

	/**
	 * @return
	 */
	public String getEventDateStr()
	{
	    return eventDateStr;
	}

	/**
	 * @return
	 */
	public String getMaxAttendance()
	{
	    return maxAttendance;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
	    comments = string;
	}

	/**
	 * @param string
	 */
	public void setEventDateStr(String string)
	{
	    eventDateStr = string;
	}

	/**
	 * @param string
	 */
	public void setMaxAttendance(String string)
	{
	    maxAttendance = string;
	}

	/**
	 * @return
	 */
	public Collection getConflictingEvents()
	{
	    return conflictingEvents;
	}

	/**
	 * @param collection
	 */
	public void setConflictingEvents(Collection collection)
	{
	    conflictingEvents = collection;
	}

	/**
	 * @return
	 */
	public Date getEndDate()
	{
	    return endDate;
	}

	/**
	 * @return
	 */
	public Date getStartDate()
	{
	    return startDate;
	}

	/**
	 * @param date
	 */
	public void setEndDate(Date date)
	{
	    endDate = date;
	}

	/**
	 * @param date
	 */
	public void setStartDate(Date date)
	{
	    startDate = date;
	}

	/**
	 * @return Returns the recurringEvent.
	 */
	public boolean isRecurringEvent()
	{
	    return recurringEvent;
	}

	/**
	 * @param recurringEvent
	 *            The recurringEvent to set.
	 */
	public void setRecurringEvent(boolean recurringEvent)
	{
	    this.recurringEvent = recurringEvent;
	}

	/**
	 * @return Returns the recurrencePattern.
	 */
	public String getRecurrencePattern()
	{
	    return recurrencePattern;
	}

	/**
	 * @param recurrencePattern
	 *            The recurrencePattern to set.
	 */
	public void setRecurrencePattern(String recurrencePattern)
	{
	    this.recurrencePattern = recurrencePattern;
	}

	/**
	 * @return Returns the recurrenceStartDate.
	 */
	public String getRecurrenceStartDate()
	{
	    return recurrenceStartDate;
	}

	/**
	 * @param recurrenceStartDate
	 *            The recurrenceStartDate to set.
	 */
	public void setRecurrenceStartDate(String recurrenceStartDate)
	{
	    this.recurrenceStartDate = recurrenceStartDate;
	}

	/**
	 * @return Returns the dailyRadio.
	 */
	public boolean isDailyRadio()
	{
	    return dailyRadio;
	}

	/**
	 * @param dailyRadio
	 *            The dailyRadio to set.
	 */
	public void setDailyRadio(boolean dailyRadio)
	{
	    this.dailyRadio = dailyRadio;
	}

	/**
	 * @return Returns the monthlyRadio.
	 */
	public boolean isMonthlyRadio()
	{
	    return monthlyRadio;
	}

	/**
	 * @param monthlyRadio
	 *            The monthlyRadio to set.
	 */
	public void setMonthlyRadio(boolean monthlyRadio)
	{
	    this.monthlyRadio = monthlyRadio;
	}

	/**
	 * @return Returns the yearlyRadio.
	 */
	public boolean isYearlyRadio()
	{
	    return yearlyRadio;
	}

	/**
	 * @param yearlyRadio
	 *            The yearlyRadio to set.
	 */
	public void setYearlyRadio(boolean yearlyRadio)
	{
	    this.yearlyRadio = yearlyRadio;
	}

	/**
	 * @return Returns the monthlyDay.
	 */
	public String getMonthlyDay()
	{
	    return monthlyDay;
	}

	/**
	 * @param monthlyDay
	 *            The monthlyDay to set.
	 */
	public void setMonthlyDay(String monthlyDay)
	{
	    this.monthlyDay = monthlyDay;
	}

	/**
	 * @return Returns the frequencyRadio.
	 */
	public boolean isFrequencyRadio()
	{
	    return frequencyRadio;
	}

	/**
	 * @param frequencyRadio
	 *            The frequencyRadio to set.
	 */
	public void setFrequencyRadio(boolean frequencyRadio)
	{
	    this.frequencyRadio = frequencyRadio;
	}

	/**
	 * @return Returns the recurrenceEndDate.
	 */
	public String getRecurrenceEndDate()
	{
	    return recurrenceEndDate;
	}

	/**
	 * @param recurrenceEndDate
	 *            The recurrenceEndDate to set.
	 */
	public void setRecurrenceEndDate(String recurrenceEndDate)
	{
	    this.recurrenceEndDate = recurrenceEndDate;
	}

	/**
	 * @return Returns the recurrenceFrequency.
	 */
	public String getRecurrenceFrequency()
	{
	    return recurrenceFrequency;
	}

	/**
	 * @param recurrenceFrequency
	 *            The recurrenceFrequency to set.
	 */
	public void setRecurrenceFrequency(String recurrenceFrequency)
	{
	    this.recurrenceFrequency = recurrenceFrequency;
	}

	/**
	 * @return Returns the dailyRecurrenceInterval.
	 */
	public String getDailyRecurrenceInterval()
	{
	    return dailyRecurrenceInterval;
	}

	/**
	 * @param dailyRecurrenceInterval
	 *            The dailyRecurrenceInterval to set.
	 */
	public void setDailyRecurrenceInterval(String dailyRecurrenceInterval)
	{
	    this.dailyRecurrenceInterval = dailyRecurrenceInterval;
	}

	/**
	 * @return Returns the monthlyRecurrenceInterval1.
	 */
	public String getMonthlyRecurrenceInterval1()
	{
	    return monthlyRecurrenceInterval1;
	}

	/**
	 * @param monthlyRecurrenceInterval1
	 *            The monthlyRecurrenceInterval1 to set.
	 */
	public void setMonthlyRecurrenceInterval1(String monthlyRecurrenceInterval1)
	{
	    this.monthlyRecurrenceInterval1 = monthlyRecurrenceInterval1;
	}

	/**
	 * @return Returns the monthlyRecurrenceInterval2.
	 */
	public String getMonthlyRecurrenceInterval2()
	{
	    return monthlyRecurrenceInterval2;
	}

	/**
	 * @param monthlyRecurrenceInterval2
	 *            The monthlyRecurrenceInterval2 to set.
	 */
	public void setMonthlyRecurrenceInterval2(String monthlyRecurrenceInterval2)
	{
	    this.monthlyRecurrenceInterval2 = monthlyRecurrenceInterval2;
	}

	/**
	 * @return Returns the weeklyRecurrenceInterval.
	 */
	public String getWeeklyRecurrenceInterval()
	{
	    return weeklyRecurrenceInterval;
	}

	/**
	 * @param weeklyRecurrenceInterval
	 *            The weeklyRecurrenceInterval to set.
	 */
	public void setWeeklyRecurrenceInterval(String weeklyRecurrenceInterval)
	{
	    this.weeklyRecurrenceInterval = weeklyRecurrenceInterval;
	}

	/**
	 * @return Returns the weeklyDay.
	 */
	public String[] getWeeklyDay()
	{
	    return weeklyDay;
	}

	/**
	 * @param weeklyDay
	 *            The weeklyDay to set.
	 */
	public void setWeeklyDay(String[] weeklyDay)
	{
	    this.weeklyDay = weeklyDay;
	}

	/**
	 * @return Returns the monthlyWeekNumber.
	 */
	public String getMonthlyWeekNumber()
	{
	    return monthlyWeekNumber;
	}

	/**
	 * @param monthlyWeekNumber
	 *            The monthlyWeekNumber to set.
	 */
	public void setMonthlyWeekNumber(String monthlyWeekNumber)
	{
	    this.monthlyWeekNumber = monthlyWeekNumber;
	}

	/**
	 * @return Returns the yearlyDay.
	 */
	public String getYearlyDay()
	{
	    return yearlyDay;
	}

	/**
	 * @param yearlyDay
	 *            The yearlyDay to set.
	 */
	public void setYearlyDay(String yearlyDay)
	{
	    this.yearlyDay = yearlyDay;
	}

	/**
	 * @return Returns the yearlyMonthNumber.
	 */
	public String getYearlyMonthNumber()
	{
	    return yearlyMonthNumber;
	}

	/**
	 * @param yearlyMonthNumber
	 *            The yearlyMonthNumber to set.
	 */
	public void setYearlyMonthNumber(String yearlyMonthNumber)
	{
	    this.yearlyMonthNumber = yearlyMonthNumber;
	}

	/**
	 * @return Returns the yearlyMonthNumber1.
	 */
	public String getYearlyMonthNumber1()
	{
	    return yearlyMonthNumber1;
	}

	/**
	 * @param yearlyMonthNumber1
	 *            The yearlyMonthNumber1 to set.
	 */
	public void setYearlyMonthNumber1(String yearlyMonthNumber1)
	{
	    this.yearlyMonthNumber1 = yearlyMonthNumber1;
	}

	/**
	 * @return Returns the yearlyWeekDay.
	 */
	public String getYearlyWeekDay()
	{
	    return yearlyWeekDay;
	}

	/**
	 * @param yearlyWeekDay
	 *            The yearlyWeekDay to set.
	 */
	public void setYearlyWeekDay(String yearlyWeekDay)
	{
	    this.yearlyWeekDay = yearlyWeekDay;
	}

	/**
	 * @return Returns the monthlyWeekDay.
	 */
	public String getMonthlyWeekDay()
	{
	    return monthlyWeekDay;
	}

	/**
	 * @param monthlyWeekDay
	 *            The monthlyWeekDay to set.
	 */
	public void setMonthlyWeekDay(String monthlyWeekDay)
	{
	    this.monthlyWeekDay = monthlyWeekDay;
	}

	/**
	 * @return Returns the yearlyWeekNumber.
	 */
	public String getYearlyWeekNumber()
	{
	    return yearlyWeekNumber;
	}

	/**
	 * @param yearlyWeekNumber
	 *            The yearlyWeekNumber to set.
	 */
	public void setYearlyWeekNumber(String yearlyWeekNumber)
	{
	    this.yearlyWeekNumber = yearlyWeekNumber;
	}

	/**
	 * @return Returns the jpoConflictingEvents.
	 */
	public Collection getJpoConflictingEvents()
	{
	    return jpoConflictingEvents;
	}

	/**
	 * @param jpoConflictingEvents
	 *            The jpoConflictingEvents to set.
	 */
	public void setJpoConflictingEvents(Collection jpoConflictingEvents)
	{
	    this.jpoConflictingEvents = jpoConflictingEvents;
	}

	/**
	 * @return Returns the juvenileConflictingEvents.
	 */
	public Collection getJuvenileConflictingEvents()
	{
	    return juvenileConflictingEvents;
	}

	/**
	 * @param juvenileConflictingEvents
	 *            The juvenileConflictingEvents to set.
	 */
	public void setJuvenileConflictingEvents(Collection juvenileConflictingEvents)
	{
	    this.juvenileConflictingEvents = juvenileConflictingEvents;
	}

	/**
	 * @return Returns the sessionLengthDesc.
	 */
	public String getSessionLengthDesc()
	{
	    return sessionLengthDesc;
	}

	/**
	 * @param sessionLengthDesc
	 *            The sessionLengthDesc to set.
	 */
	public void setSessionLengthDesc(String sessionLengthDesc)
	{
	    this.sessionLengthDesc = sessionLengthDesc;
	}

	/**
	 * @return the proposedEventId
	 */
	public String getProposedEventId()
	{
	    return proposedEventId;
	}

	/**
	 * @param proposedEventId
	 *            the proposedEventId to set
	 */
	public void setProposedEventId(String proposedEventId)
	{
	    this.proposedEventId = proposedEventId;
	}

    } // End class Event
}