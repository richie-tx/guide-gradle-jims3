package messaging.districtCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateJJSHeaderFromDistrictCourtEvent extends RequestEvent
{
    private String juvenileNumber;
    private String facilityCode;
    private String referralNo;
    private String lastSeqNum;
    private String facilityStatus;
    private Date relocationDate;
    private String relocationTime;
    private Date nextHearingDate;
    private Date probableCauseDate;
    private String bookingSupervision;

    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
	return juvenileNumber;
    }

    /**
     * @param juvenileNumber
     *            the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	this.juvenileNumber = juvenileNumber;
    }

    /**
     * @return the facilityCode
     */
    public String getFacilityCode()
    {
	return facilityCode;
    }

    /**
     * @param facilityCode
     *            the facilityCode to set
     */
    public void setFacilityCode(String facilityCode)
    {
	this.facilityCode = facilityCode;
    }

    /**
     * @return the referralNo
     */
    public String getReferralNo()
    {
	return referralNo;
    }

    /**
     * @param referralNo
     *            the referralNo to set
     */
    public void setReferralNo(String referralNo)
    {
	this.referralNo = referralNo;
    }

    /**
     * @return the lastSqlNum
     */
    public String getLastSeqNum()
    {
	return lastSeqNum;
    }

    /**
     * @param lastSqlNum
     *            the lastSqlNum to set
     */
    public void setLastSqlNum(String lastSeqNum)
    {
	this.lastSeqNum = lastSeqNum;
    }

    /**
     * @return the facilityStatus
     */
    public String getFacilityStatus()
    {
	return facilityStatus;
    }

    /**
     * @param facilityStatus
     *            the facilityStatus to set
     */
    public void setFacilityStatus(String facilityStatus)
    {
	this.facilityStatus = facilityStatus;
    }

    /**
     * @return the relocationDate
     */
    public Date getRelocationDate()
    {
	return relocationDate;
    }

    /**
     * @param relocationDate
     *            the relocationDate to set
     */
    public void setRelocationDate(Date relocationDate)
    {
	this.relocationDate = relocationDate;
    }

    /**
     * @return the relocationTime
     */
    public String getRelocationTime()
    {
	return relocationTime;
    }

    /**
     * @param relocationTime
     *            the relocationTime to set
     */
    public void setRelocationTime(String relocationTime)
    {
	this.relocationTime = relocationTime;
    }

    /**
     * @return the nextHearingDate
     */
    public Date getNextHearingDate()
    {
	return nextHearingDate;
    }

    /**
     * @param nextHearingDate
     *            the nextHearingDate to set
     */
    public void setNextHearingDate(Date nextHearingDate)
    {
	this.nextHearingDate = nextHearingDate;
    }

    /**
     * @return the probableCauseOfDeath
     */
    public Date getProbableCauseDate()
    {
	return probableCauseDate;
    }

    /**
     * @param probableCauseOfDeath
     *            the probableCauseOfDeath to set
     */
    public void setProbableCauseDate(Date probableCauseDate)
    {
	this.probableCauseDate = probableCauseDate;
    }


    /**
     * @return the bookingSupervision
     */
    public String getBookingSupervision()
    {
	return bookingSupervision;
    }

    /**
     * @param bookingSupervision
     *            the bookingSupervision to set
     */
    public void setBookingSupervision(String bookingSupervision)
    {
	this.bookingSupervision = bookingSupervision;
    }
}
