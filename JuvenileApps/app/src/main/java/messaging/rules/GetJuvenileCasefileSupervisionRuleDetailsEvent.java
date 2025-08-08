//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileSupervisionRuleDetailsEvent.java

package messaging.rules;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefileSupervisionRuleDetailsEvent extends RequestEvent
{
	private String ruleId;
	/**
	 * @roseuid 43833B4C0135
	 */
	public GetJuvenileCasefileSupervisionRuleDetailsEvent()
	{

	}
	/**
	 * @return
	 */
	public String getRuleId()
	{
		return ruleId;
	}

	/**
	 * @param i
	 */
	public void setRuleId(String i)
	{
		ruleId = i;
	}

}
