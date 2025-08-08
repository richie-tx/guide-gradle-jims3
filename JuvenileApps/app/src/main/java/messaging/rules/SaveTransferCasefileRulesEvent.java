//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveJuvenileCasefileSupervisionRulesEvent.java

package messaging.rules;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveTransferCasefileRulesEvent extends CompositeRequest
{
	private String  supervisionNum;
	private String ruleID;

	/**
	 * @roseuid 43833C3E013A
	 */
	public SaveTransferCasefileRulesEvent()
	{

	}


	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}


	/**
	 * @param i
	 */
	public void setSupervisionNum(String i)
	{
		supervisionNum = i;
	}


	/**
	 * @return the ruleID
	 */
	public String getRuleID() {
		return ruleID;
	}


	/**
	 * @param ruleID the ruleID to set
	 */
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	

}
