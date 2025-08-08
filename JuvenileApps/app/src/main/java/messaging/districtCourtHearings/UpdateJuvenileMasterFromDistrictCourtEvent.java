package messaging.districtCourtHearings;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileMasterFromDistrictCourtEvent extends RequestEvent
{

    private String juvenileNumber;
    private String disposition;
    private String courtResult;
    private String originalDisposition;
    private String originalCourtResult;
    private String courtDate;
    private String originalActionDate;
    private String actionDate;

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
     * @return the disposition
     */
    public String getDisposition()
    {
	return disposition;
    }

    /**
     * @param disposition
     *            the disposition to set
     */
    public void setDisposition(String disposition)
    {
	this.disposition = disposition;
    }

    /**
     * @return the courtResult
     */
    public String getCourtResult()
    {
	return courtResult;
    }

    /**
     * @param courtResult
     *            the courtResult to set
     */
    public void setCourtResult(String courtResult)
    {
	this.courtResult = courtResult;
    }

    /**
     * @return the originalDisposition
     */
    public String getOriginalDisposition()
    {
	return originalDisposition;
    }

    /**
     * @param originalDisposition
     *            the originalDisposition to set
     */
    public void setOriginalDisposition(String originalDisposition)
    {
	this.originalDisposition = originalDisposition;
    }

    /**
     * @return the originalCourtResult
     */
    public String getOriginalCourtResult()
    {
	return originalCourtResult;
    }

    /**
     * @param originalCourtResult
     *            the originalCourtResult to set
     */
    public void setOriginalCourtResult(String originalCourtResult)
    {
	this.originalCourtResult = originalCourtResult;
    }

    /**
     * @return the courtDate
     */
    public String getCourtDate()
    {
	return courtDate;
    }

    /**
     * @param courtDate
     *            the courtDate to set
     */
    public void setCourtDate(String courtDate)
    {
	this.courtDate = courtDate;
    }

    /**
     * @return the originalActionDate
     */
    public String getOriginalActionDate()
    {
	return originalActionDate;
    }

    /**
     * @param originalActionDate
     *            the originalActionDate to set
     */
    public void setOriginalActionDate(String originalActionDate)
    {
	this.originalActionDate = originalActionDate;
    }

    /**
     * @return the actionDate
     */
    public String getActionDate()
    {
	return actionDate;
    }

    /**
     * @param actionDate
     *            the actionDate to set
     */
    public void setActionDate(String actionDate)
    {
	this.actionDate = actionDate;
    }

}
