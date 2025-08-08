//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\ValidateOutOfCountyCaseEvent.java

package messaging.managesupervisioncase;

import mojo.km.messaging.RequestEvent;

public class ValidateOutOfCountyCaseEvent extends RequestEvent
{
	private String cJISNum;
	private String caseNum;
	/**
	 * @roseuid 4443FBA60125
	 */
	public ValidateOutOfCountyCaseEvent()
	{

	}

	/**
	 * Access method for the cJISNum property.
	 * 
	 * @return   the current value of the cJISNum property
	 */
	public String getCJISNum()
	{
		return cJISNum;
	}

	/**
	 * Sets the value of the cJISNum property.
	 * 
	 * @param aCJISNum the new value of the cJISNum property
	 */
	public void setCJISNum(String aCJISNum)
	{
		cJISNum = aCJISNum;
	}

	/**
	 * Access method for the outOfCountyCase property.
	 * 
	 * @return
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * Sets the value of the outOfCountyCase property.
	 * 
	 * @param aCaseNum
	 */
	public void setCaseNum(String aCaseNum)
	{
		caseNum = aCaseNum;
	}

}
