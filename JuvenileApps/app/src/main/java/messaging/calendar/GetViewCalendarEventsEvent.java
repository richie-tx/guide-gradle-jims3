// Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetViewCalendarEventsEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class GetViewCalendarEventsEvent extends RequestEvent
{
    private Date eventStartDate;

    private Date eventEndDate;

    private String eventTypeId;

    private String juvUnitCd;

    private String eventStatusCd;

    private String juvenileLastName;

    private String juvenileFirstName;

    private String juvenileMiddleName;

    private String juvenileNum;

    private String serviceProviderName;

    private boolean caseLoadManager;

    private String officerId;

    private String officerLastName;

    private String officerMiddleName;

    private String officerFirstName;

    /**
     * @roseuid 45F1B0F6004C
     */
    public GetViewCalendarEventsEvent()
    {

    }

    /**
     * @return Returns the caseLoadManager.
     */
    public boolean isCaseLoadManager()
    {
        return caseLoadManager;
    }

    /**
     * @param caseLoadManager
     *            The caseLoadManager to set.
     */
    public void setCaseLoadManager(boolean caseLoadManager)
    {
        this.caseLoadManager = caseLoadManager;
    }

    /**
     * @return Returns the eventEndDate.
     */
    public Date getEventEndDate()
    {
        return eventEndDate;
    }

    /**
     * @param eventEndDate
     *            The eventEndDate to set.
     */
    public void setEventEndDate(Date eventEndDate)
    {
        this.eventEndDate = eventEndDate;
    }

    /**
     * @return Returns the eventStartDate.
     */
    public Date getEventStartDate()
    {
        return eventStartDate;
    }

    /**
     * @param eventStartDate
     *            The eventStartDate to set.
     */
    public void setEventStartDate(Date eventStartDate)
    {
        this.eventStartDate = eventStartDate;
    }

    /**
     * @return Returns the eventStatusCd.
     */
    public String getEventStatusCd()
    {
        return eventStatusCd;
    }

    /**
     * @param eventStatusCd
     *            The eventStatusCd to set.
     */
    public void setEventStatusCd(String eventStatusCd)
    {
        this.eventStatusCd = eventStatusCd;
    }

    /**
     * @return Returns the eventTypeId.
     */
    public String getEventTypeId()
    {
        return eventTypeId;
    }

    /**
     * @param eventTypeId
     *            The eventTypeId to set.
     */
    public void setEventTypeId(String eventTypeId)
    {
        this.eventTypeId = eventTypeId;
    }

    /**
     * @return Returns the juvenileFirstName.
     */
    public String getJuvenileFirstName()
    {
        return juvenileFirstName;
    }

    /**
     * @param juvenileFirstName
     *            The juvenileFirstName to set.
     */
    public void setJuvenileFirstName(String juvenileFirstName)
    {
        this.juvenileFirstName = juvenileFirstName;
    }

    /**
     * @return Returns the juvenileLastName.
     */
    public String getJuvenileLastName()
    {
        return juvenileLastName;
    }

    /**
     * @param juvenileLastName
     *            The juvenileLastName to set.
     */
    public void setJuvenileLastName(String juvenileLastName)
    {
        this.juvenileLastName = juvenileLastName;
    }

    /**
     * @return Returns the juvenileMiddleName.
     */
    public String getJuvenileMiddleName()
    {
        return juvenileMiddleName;
    }

    /**
     * @param juvenileMiddleName
     *            The juvenileMiddleName to set.
     */
    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
        this.juvenileMiddleName = juvenileMiddleName;
    }

    /**
     * @return Returns the juvUnitCd.
     */
    public String getJuvUnitCd()
    {
        return juvUnitCd;
    }

    /**
     * @param juvUnitCd
     *            The juvUnitCd to set.
     */
    public void setJuvUnitCd(String juvUnitCd)
    {
        this.juvUnitCd = juvUnitCd;
    }

    /**
     * @return Returns the officerFirstName.
     */
    public String getOfficerFirstName()
    {
        return officerFirstName;
    }

    /**
     * @param officerFirstName
     *            The officerFirstName to set.
     */
    public void setOfficerFirstName(String officerFirstName)
    {
        this.officerFirstName = officerFirstName;
    }

    /**
     * @return Returns the officerLastName.
     */
    public String getOfficerLastName()
    {
        return officerLastName;
    }

    /**
     * @param officerLastName
     *            The officerLastName to set.
     */
    public void setOfficerLastName(String officerLastName)
    {
        this.officerLastName = officerLastName;
    }

    /**
     * @return Returns the officerMiddleName.
     */
    public String getOfficerMiddleName()
    {
        return officerMiddleName;
    }

    /**
     * @param officerMiddleName
     *            The officerMiddleName to set.
     */
    public void setOfficerMiddleName(String officerMiddleName)
    {
        this.officerMiddleName = officerMiddleName;
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
     * @return Returns the juvenileNum.
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            The juvenileNum to set.
     */
    public void setJuvenileNum(String juvenileNum)
    {
        this.juvenileNum = juvenileNum;
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
}
