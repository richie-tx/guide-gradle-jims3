package messaging.juvenile;


import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileServiceEvent extends RequestEvent
{

    private String caseNum; //petition number = casenum
    private int appearanceDate;
    private int requestDate;
    private String serviceType;
    private String trackingNum;
    private String serviceStatus;
    private String issueClerk;
    private int issueDate;
    private int lcDate;
    private String lcUser;
    /**
     * @return the caseNum
     */
    public String getCaseNum()
    {
        return caseNum;
    }
    /**
     * @param caseNum the caseNum to set
     */
    public void setCaseNum(String caseNum)
    {
        this.caseNum = caseNum;
    }
    /**
     * @return the appearanceDate
     */
    public int getAppearanceDate()
    {
        return appearanceDate;
    }
    /**
     * @param appearanceDate the appearanceDate to set
     */
    public void setAppearanceDate(int appearanceDate)
    {
        this.appearanceDate = appearanceDate;
    }
    /**
     * @return the requestDate
     */
    public int getRequestDate()
    {
        return requestDate;
    }
    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(int requestDate)
    {
        this.requestDate = requestDate;
    }
    /**
     * @return the serviceType
     */
    public String getServiceType()
    {
        return serviceType;
    }
    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }
    /**
     * @return the trackingNum
     */
    public String getTrackingNum()
    {
        return trackingNum;
    }
    /**
     * @param trackingNum the trackingNum to set
     */
    public void setTrackingNum(String trackingNum)
    {
        this.trackingNum = trackingNum;
    }
    /**
     * @return the serviceStatus
     */
    public String getServiceStatus()
    {
        return serviceStatus;
    }
    /**
     * @param serviceStatus the serviceStatus to set
     */
    public void setServiceStatus(String serviceStatus)
    {
        this.serviceStatus = serviceStatus;
    }
    /**
     * @return the issueClerk
     */
    public String getIssueClerk()
    {
        return issueClerk;
    }
    /**
     * @param issueClerk the issueClerk to set
     */
    public void setIssueClerk(String issueClerk)
    {
        this.issueClerk = issueClerk;
    }
    /**
     * @return the issueDate
     */
    public int getIssueDate()
    {
        return issueDate;
    }
    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(int issueDate)
    {
        this.issueDate = issueDate;
    }
    /**
     * @return the lcDate
     */
    public int getLcDate()
    {
        return lcDate;
    }
    /**
     * @param lcDate the lcDate to set
     */
    public void setLcDate(int lcDate)
    {
        this.lcDate = lcDate;
    }
    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
        return lcUser;
    }
    /**
     * @param lcUser the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }
    
}
