package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class UpdateTrackingControlNumberEvent extends RequestEvent

{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String courtDivision;
    String nextTrackingNum;
    
    
    /**
     * @return the courtDivision
     */
    public String getCourtDivision()
    {
        return courtDivision;
    }
    /**
     * @param courtDivision the courtDivision to set
     */
    public void setCourtDivision(String courtDivision)
    {
        this.courtDivision = courtDivision;
    }
    /**
     * @return the nextTrackingNum
     */
    public String getNextTrackingNum()
    {
        return nextTrackingNum;
    }
    /**
     * @param nextTrackingNum the nextTrackingNum to set
     */
    public void setNextTrackingNum(String nextTrackingNum)
    {
        this.nextTrackingNum = nextTrackingNum;
    }
}
