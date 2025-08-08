package messaging.districtCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateJJSDetentionFromDistrictCourtEvent extends RequestEvent

{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String admitReason;
    private Date admitDate;
    private String admitTime;
    private String facilityCode;
    private String juvenileNumber;
    private String referralNumber;
    private String detentionStatus;

    private Date detainedDate;

    private Date lastChangeDate;
    private String lcTime;
    private Date nextHearingDate;
    private String headerStatus;

    /**
     * @return the admitReason
     */
    public String getAdmitReason()
    {
	return admitReason;
    }

    /**
     * @param admitReason
     *            the admitReason to set
     */
    public void setAdmitReason(String admitReason)
    {
	this.admitReason = admitReason;
    }

    /**
     * @return the admitDate
     */
    public Date getAdmitDate()
    {
	return admitDate;
    }

    /**
     * @param admitDate
     *            the admitDate to set
     */
    public void setAdmitDate(Date admitDate)
    {
	this.admitDate = admitDate;
    }

    /**
     * @return the admitTime
     */
    public String getAdmitTime()
    {
	return admitTime;
    }

    /**
     * @param admitTime
     *            the admitTime to set
     */
    public void setAdmitTime(String admitTime)
    {
	this.admitTime = admitTime;
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
     * @return the referralNumber
     */
    public String getReferralNumber()
    {
	return referralNumber;
    }

    /**
     * @param referralNumber
     *            the referralNumber to set
     */
    public void setReferralNumber(String referralNumber)
    {
	this.referralNumber = referralNumber;
    }

    /**
     * @return the detentionStatus
     */
    public String getDetentionStatus()
    {
	return detentionStatus;
    }

    /**
     * @param detentionStatus
     *            the detentionStatus to set
     */
    public void setDetentionStatus(String detentionStatus)
    {
	this.detentionStatus = detentionStatus;
    }

    /**
     * @return the detainedDate
     */
    public Date getDetainedDate()
    {
	return detainedDate;
    }

    /**
     * @param detainedDate
     *            the detainedDate to set
     */
    public void setDetainedDate(Date detainedDate)
    {
	this.detainedDate = detainedDate;
    }

    /**
     * @return the lastChangeDate
     */
    public Date getLastChangeDate()
    {
	return lastChangeDate;
    }

    /**
     * @param lastChangeDate
     *            the lastChangeDate to set
     */
    public void setLastChangeDate(Date lastChangeDate)
    {
	this.lastChangeDate = lastChangeDate;
    }

    /**
     * @return the lcTime
     */
    public String getLcTime()
    {
	return lcTime;
    }

    /**
     * @param lcTime
     *            the lcTime to set
     */
    public void setLcTime(String lcTime)
    {
	this.lcTime = lcTime;
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
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }

    /**
     * @return the headerStatus
     */
    public String getHeaderStatus()
    {
	return headerStatus;
    }

    /**
     * @param headerStatus
     *            the headerStatus to set
     */
    public void setHeaderStatus(String headerStatus)
    {
	this.headerStatus = headerStatus;
    }
}
