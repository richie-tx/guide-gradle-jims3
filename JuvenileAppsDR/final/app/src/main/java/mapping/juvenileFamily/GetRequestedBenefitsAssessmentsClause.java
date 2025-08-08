package mapping.juvenileFamily;

import mojo.km.messaging.IEvent;

/**
 *
 */
public class GetRequestedBenefitsAssessmentsClause
{
	/**
	 * 
	 */
	public GetRequestedBenefitsAssessmentsClause()
	{
		super();
	}

	public String getClause(IEvent anEvent)
	{
		return "ENTRYDATE is NULL";
	}

}
