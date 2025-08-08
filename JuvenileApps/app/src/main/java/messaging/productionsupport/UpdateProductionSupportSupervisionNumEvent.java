package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportSupervisionNumEvent extends RequestEvent
{

    private String casefileId;
    private String mergeToCasefileId;

    /**
     * @roseuid 45702FFC0393
     */
    public UpdateProductionSupportSupervisionNumEvent()
    {

    }

    /**
     * @return the casefileId
     */
    public String getCasefileId()
    {
	return casefileId;
    }

    /**
     * @param casefileId
     *            the casefileId to set
     */
    public void setCasefileId(String casefileId)
    {
	this.casefileId = casefileId;
    }

    /**
     * @return the mergeToCasefileId
     */
    public String getMergeToCasefileId()
    {
	return mergeToCasefileId;
    }

    /**
     * @param mergeToCasefileId
     *            the mergeToCasefileId to set
     */
    public void setMergeToCasefileId(String mergeToCasefileId)
    {
	this.mergeToCasefileId = mergeToCasefileId;
    }

}
