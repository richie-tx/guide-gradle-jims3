package mapping.juvenileFamily;

import messaging.family.GetRequestedBenefitsAssessmentByJuvenileEvent;
import mojo.km.messaging.IEvent;

/**
 *
 */
public class GetRequestedBenefitsAssessmentByJuvenileClause
{
	/**
	 * 
	 */
	public GetRequestedBenefitsAssessmentByJuvenileClause()
	{
		super();
	}

	public String getClause(IEvent anEvent)
	{
		return "JUVENILE_ID = '" + ((GetRequestedBenefitsAssessmentByJuvenileEvent)anEvent).getJuvenileNum() +  "' and ENTRYDATE is NULL";
	}

}
