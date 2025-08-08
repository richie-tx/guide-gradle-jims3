package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportFacilityHeaderEvent extends RequestEvent
{

    private String headerId;
    private String juvenileId;
    private String bookingReferralNum;
    private String bookingSupervisionNum;
    private String lastSequenceNum;
    private String highestSequenceNumInUse;
    private String facilityCode;
    private String facilityName;
    private String facilityStatusCode;
    private Date nextHearingDate;
    private Date probableCauseHearingDate;

    /**
     * @roseuid 45702FFC0393
     */
    public UpdateProductionSupportFacilityHeaderEvent()
    {

    }

    public String getHeaderId()
    {
        return headerId;
    }

    public void setHeaderId(String headerId)
    {
        this.headerId = headerId;
    }

    public String getJuvenileId()
    {
        return juvenileId;
    }

    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }

    public String getBookingReferralNum()
    {
        return bookingReferralNum;
    }

    public void setBookingReferralNum(String bookingReferralNum)
    {
        this.bookingReferralNum = bookingReferralNum;
    }

    public String getBookingSupervisionNum()
    {
        return bookingSupervisionNum;
    }

    public void setBookingSupervisionNum(String bookingSupervisionNum)
    {
        this.bookingSupervisionNum = bookingSupervisionNum;
    }

    public String getLastSequenceNum()
    {
        return lastSequenceNum;
    }

    public void setLastSequenceNum(String lastSequenceNum)
    {
        this.lastSequenceNum = lastSequenceNum;
    }

    public String getHighestSequenceNumInUse()
    {
        return highestSequenceNumInUse;
    }

    public void setHighestSequenceNumInUse(String highestSequenceNumInUse)
    {
        this.highestSequenceNumInUse = highestSequenceNumInUse;
    }

    public String getFacilityCode()
    {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode)
    {
        this.facilityCode = facilityCode;
    }

    public String getFacilityName()
    {
        return facilityName;
    }

    public void setFacilityName(String facilityName)
    {
        this.facilityName = facilityName;
    }

    public String getFacilityStatusCode()
    {
        return facilityStatusCode;
    }

    public void setFacilityStatusCode(String facilityStatusCode)
    {
        this.facilityStatusCode = facilityStatusCode;
    }

    public Date getNextHearingDate()
    {
        return nextHearingDate;
    }

    public void setNextHearingDate(Date nextHearingDate)
    {
        this.nextHearingDate = nextHearingDate;
    }

    public Date getProbableCauseHearingDate()
    {
        return probableCauseHearingDate;
    }

    public void setProbableCauseHearingDate(Date probableCauseHearingDate)
    {
        this.probableCauseHearingDate = probableCauseHearingDate;
    }

    
}
