//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 * @author ANPILLAI To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileByCasefileActivityEvent extends RequestEvent
{
    private String activityCategory;
    private String activityType;
    private String fromDate;
    private String toDate;
    private String activityCode;
    private String supervisionTypeId;
    private String juvLocationId;
    private String officerLastNameData;
    private String officerFirstNameData;
    private String systemActivities;
    private String closedCasefiles;  
    private String juvenileNumber;    

    /**
     * @roseuid 4278C82101F1
     */
    public GetJuvenileByCasefileActivityEvent()
    {

    }

    public String getOfficerFirstNameData()
    {
	return officerFirstNameData;
    }

    public void setOfficerFirstNameData(String officerFirstNameData)
    {
	this.officerFirstNameData = officerFirstNameData;
    }

    public String getOfficerLastNameData()
    {
	return officerLastNameData;
    }

    public void setOfficerLastNameData(String officerLastNameData)
    {
	this.officerLastNameData = officerLastNameData;
    }

    public String getJuvLocationId()
    {
	return juvLocationId;
    }

    public void setJuvLocationId(String juvLocationId)
    {
	this.juvLocationId = juvLocationId;
    }

    public String getSupervisionTypeId()
    {
	return supervisionTypeId;
    }

    public void setSupervisionTypeId(String supervisionTypeId)
    {
	this.supervisionTypeId = supervisionTypeId;
    }

    public String getActivityCode()
    {
	return activityCode;
    }

    public void setActivityCode(String activityCode)
    {
	this.activityCode = activityCode;
    }

    public String getToDate()
    {
	return toDate;
    }

    public void setToDate(String toDate)
    {
	this.toDate = toDate;
    }

    public String getFromDate()
    {
	return fromDate;
    }

    public void setFromDate(String fromDate)
    {
	this.fromDate = fromDate;
    }

    public String getActivityType()
    {
	return activityType;
    }

    public void setActivityType(String activityType)
    {
	this.activityType = activityType;
    }

    public String getActivityCategory()
    {
	return activityCategory;
    }

    public void setActivityCategory(String activityCategory)
    {
	this.activityCategory = activityCategory;
    }
    public String getSystemActivities()
    {
        return systemActivities;
    }

    public void setSystemActivities(String systemActivities)
    {
        this.systemActivities = systemActivities;
    }
    public String getClosedCasefiles()
    {
        return closedCasefiles;
    }

    public void setClosedCasefiles(String closedCasefiles)
    {
        this.closedCasefiles = closedCasefiles;
    }
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }

    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }

}
