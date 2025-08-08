package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

public class DeleteJJSCLAncillarySettingEvent extends RequestEvent 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String juvenileNumber;
    String chainNumber;
    String referralNumber;
    String seqNumber;
    String docketEventId;
    String docketType;

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
     * @return the chainNumber
     */
    public String getChainNumber()
    {
	return chainNumber;
    }

    /**
     * @param chainNumber
     *            the chainNumber to set
     */
    public void setChainNumber(String chainNumber)
    {
	this.chainNumber = chainNumber;
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
     * @return the seqNumber
     */
    public String getSeqNumber()
    {
	return seqNumber;
    }

    /**
     * @param seqNumber
     *            the seqNumber to set
     */
    public void setSeqNumber(String seqNumber)
    {
	this.seqNumber = seqNumber;
    }

    /**
     * @return the docketEventId
     */
    public String getDocketEventId()
    {
	return docketEventId;
    }

    /**
     * @param docketEventId
     *            the docketEventId to set
     */
    public void setDocketEventId(String docketEventId)
    {
	this.docketEventId = docketEventId;
    }

    /**
     * @return the docketType
     */
    public String getDocketType()
    {
	return docketType;
    }

    /**
     * @param docketType
     *            the docketType to set
     */
    public void setDocketType(String docketType)
    {
	this.docketType = docketType;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }
}
