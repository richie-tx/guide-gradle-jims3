package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
/**
 * JDJUVJU TRACKING.CONTROL
 * JPJUV 9930
 * @author sthyagarajan
 *
 */
public class TrackingControlNumber  extends PersistentObject {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String courtDivision;
    private String nextTrackingNum;
    private String rectype;
    private Date lcDate;
    private Date lcTime;
    private String lcUser;
    /**
     * @return the courtDivision
     */
    public String getCourtDivision()
    {
	fetch();
        return courtDivision;
    }
    /**
     * @param courtDivision the courtDivision to set
     */
    public void setCourtDivision(String courtDivision)
    {
	if (this.courtDivision == null || !this.courtDivision.equals(courtDivision))
	{
		markModified();
	}
        this.courtDivision = courtDivision;
    }
    /**
     * @return the nextTrackingNum
     */
    public String getNextTrackingNum()
    {
	fetch();
        return nextTrackingNum;
    }
    /**
     * @param nextTrackingNum the nextTrackingNum to set
     */
    public void setNextTrackingNum(String nextTrackingNum)
    {
	if (this.nextTrackingNum == null || !this.nextTrackingNum.equals(nextTrackingNum))
	{
		markModified();
	}
        this.nextTrackingNum = nextTrackingNum;
    }
    /**
     * @return the rectype
     */
    public String getRectype()
    {
	fetch();
        return rectype;
    }
    /**
     * @param rectype the rectype to set
     */
    public void setRectype(String rectype)
    {
	if (this.rectype == null || !this.rectype.equals(rectype))
	{
		markModified();
	}
        this.rectype = rectype;
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
	if (this.lcDate == null || !this.lcDate.equals(lcDate))
	{
		markModified();
	}
        this.lcDate = lcDate;
    }
    /**
     * @return the lcTime
     */
    public Date getLcTime()
    {
	fetch();
        return lcTime;
    }
    /**
     * @param lcTime the lcTime to set
     */
    public void setLcTime(Date lcTime)
    {
	if (this.lcTime == null || !this.lcTime.equals(lcTime))
	{
		markModified();
	}
        this.lcTime = lcTime;
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
	if (this.lcUser == null || !this.lcUser.equals(lcUser))
	{
		markModified();
	}
        this.lcUser = lcUser;
    }
    
   
	
    /**
     * Finds juvenile trackingControlNumber list by an event
     * 
     * @return Iterator of trackingControlNumber list
     * @param event
     */
    static public Iterator<TrackingControlNumber> findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator<TrackingControlNumber> trackingControlNumber = home.findAll(event, TrackingControlNumber.class);
	return trackingControlNumber;
    }

    /**
     * @return Iterator trackingControlNumber
     * @param attrName
     *            name fo the attribute for where clause
     * @param attrValue
     *            value to be checked in the where clause
     */
    static public Iterator<TrackingControlNumber> findAll(String attrName,
	    String attrValue)
    {
	IHome home = new Home();
	Iterator<TrackingControlNumber> trackingControlNumber = home.findAll(attrName, attrValue, TrackingControlNumber.class);
	return trackingControlNumber;
    }

    /**
     * @return
     * 
     */
    static public Iterator<TrackingControlNumber> findAll()
    {
	IHome home = new Home();
	return home.findAll(TrackingControlNumber.class);
    }
}
