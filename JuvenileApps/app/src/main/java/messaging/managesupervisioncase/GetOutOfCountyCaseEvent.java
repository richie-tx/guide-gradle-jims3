//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\GetOutOfCountyCaseEvent.java

package messaging.managesupervisioncase;

import mojo.km.messaging.RequestEvent;

public class GetOutOfCountyCaseEvent extends RequestEvent
{
	public String caseNum;
	public String courtDivisionId;
	public boolean reactivate;

	/**
	 * @roseuid 4447D37C0108
	 */
	public GetOutOfCountyCaseEvent()
	{

	}

	/**
	 * @param aCaseNum
	 * @roseuid 4447C367002D
	 */
	public void setCaseNum(String aCaseNum)
	{
		caseNum = aCaseNum;
	}

	/**
	 * @return String
	 * @roseuid 4447C3670036
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @param aCourtDivisionId
	 * @roseuid 4447C3670038
	 */
	public void setCourtDivisionId(String aCourtDivisionId)
	{
		courtDivisionId = aCourtDivisionId;
	}

	/**
	 * @return String
	 * @roseuid 4447C3670041
	 */
	public String getCourtDivisionId()
	{
		return courtDivisionId;
	}

	/**
	 * @param aValue
	 */
	public void setReactivate(boolean aValue)
	{
		reactivate = aValue;
	}

	/**
	 * @return String
	 */
	public boolean isReactivate()
	{
		return reactivate;
	}
}
