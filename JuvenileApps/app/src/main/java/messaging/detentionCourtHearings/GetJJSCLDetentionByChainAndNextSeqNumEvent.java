package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class GetJJSCLDetentionByChainAndNextSeqNumEvent extends RequestEvent
{
    private static final long serialVersionUID = 1L;
    
    String chainNumber;
    String seqNumber;

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
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }
}
