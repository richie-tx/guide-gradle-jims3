package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @author sthyagarajan
 *
 */
public class JuvenileService  extends PersistentObject
{
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String caseNum; //petition number = casenum
    private Date appearanceDate;
    private Date requestDate;
    private String serviceType;
    private String trackingNum;
    private String serviceStatus;
    private String issueClerk;
    private Date issueDate;
    private Date lcDate;
    private String lcUser;
    
    
    /**
     * @return the caseNum
     */
    public String getCaseNum()
    {
	fetch();
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
    public Date getAppearanceDate()
    {
	fetch();
        return appearanceDate;
    }
    /**
     * @param appearanceDate the appearanceDate to set
     */
    public void setAppearanceDate(Date appearanceDate)
    {
        this.appearanceDate = appearanceDate;
    }
    /**
     * @return the requestDate
     */
    public Date getRequestDate()
    {
	fetch();
        return requestDate;
    }
    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate)
    {
        this.requestDate = requestDate;
    }
    /**
     * @return the serviceType
     */
    public String getServiceType()
    {
	fetch();
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
	fetch();
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
	fetch();
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
	fetch();
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
    public Date getIssueDate()
    {
	fetch();
        return issueDate;
    }
    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(Date issueDate)
    {
        this.issueDate = issueDate;
    }
    /**
     * @return the lcDate
     */
    public Date getLcDate()
    {
	fetch();
        return lcDate;
    }
    /**
     * @param lcDate the lcDate to set
     */
    public void setLcDate(Date lcDate)
    {
        this.lcDate = lcDate;
    }
    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
	fetch();
        return lcUser;
    }
    /**
     * @param lcUser the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
        this.lcUser = lcUser;
    }
    
    /**
     * @roseuid 433194DF00FF
     */
    public void find()
    {
	fetch();
    }

    /**
     * @roseuid 42A99B980107
     * @return iterator
     */
    public static Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	return home.findAll(event, JuvenileService.class);
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return Iterator
     */
    public static Iterator findAll(String attributeName, String attributeValue)
    {
	IHome home = new Home();
	return home.findAll(attributeName, attributeValue, JuvenileService.class);
    }
}
