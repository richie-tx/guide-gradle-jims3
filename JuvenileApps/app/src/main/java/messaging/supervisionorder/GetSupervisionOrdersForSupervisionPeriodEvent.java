package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author jmcnabb
 *
 */
public class GetSupervisionOrdersForSupervisionPeriodEvent extends RequestEvent
{
	private String supervisionPeriodId;

	/**
	 * @roseuid 43B2E4250186
	 */
	public GetSupervisionOrdersForSupervisionPeriodEvent()
	{

	}

	/**
	 * @return
	 */
	public String getSupervisionPeriodId()
	{
		return supervisionPeriodId;
	}

	/**
	 * @param aPeriodId
	 */
	public void setSupervisionPeriodId(String aPeriodId)
	{
		supervisionPeriodId = aPeriodId;
	}

}
