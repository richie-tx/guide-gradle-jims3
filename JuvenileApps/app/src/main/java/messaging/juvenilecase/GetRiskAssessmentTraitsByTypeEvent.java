//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSResultsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetRiskAssessmentTraitsByTypeEvent extends RequestEvent
{
	private String assessmentType;

	/**
	* @roseuid 41ACD56B01EF
	*/
	public GetRiskAssessmentTraitsByTypeEvent()
	{
	}

	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(String string)
	{
		assessmentType = string;
	}

}
