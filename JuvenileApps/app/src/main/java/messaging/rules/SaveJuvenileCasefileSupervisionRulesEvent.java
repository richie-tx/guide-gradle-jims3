//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveJuvenileCasefileSupervisionRulesEvent.java

package messaging.rules;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveJuvenileCasefileSupervisionRulesEvent extends CompositeRequest
{
	private String  supervisionNum;
	private boolean standard;

	/**
	 * @roseuid 43833C3E013A
	 */
	public SaveJuvenileCasefileSupervisionRulesEvent()
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
	 * @return
	 */
	public boolean isStandard()
	{
		return standard;
	}

	/**
	 * @param b
	 */
	public void setStandard(boolean b)
	{
		standard = b;
	}

}
