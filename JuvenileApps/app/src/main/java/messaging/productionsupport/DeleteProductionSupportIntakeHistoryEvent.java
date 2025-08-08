package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

/**
 * @author rcarter
 */

public class DeleteProductionSupportIntakeHistoryEvent extends RequestEvent
{
    private String intakeHistoryId;

    /**
     * @return the traitId
     */
    public String getIntakeHistoryId()
    {
	return intakeHistoryId;
    }

    /**
     * @param traitId
     *            the traitId to set
     */
    public void setIntakeHistoryId(String intakeHistoryId)
    {
	this.intakeHistoryId = intakeHistoryId;
    }

}
