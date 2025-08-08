//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\GetSupervisionOrdersEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSupervisionOrdersEvent extends RequestEvent
{
	private String agencyId;
	private String caseId;
	private String caseNum;
	private String courtDivision;
	private boolean orderInProgress;
	private String orderStatusId;
	private String spn;

	/**
	 * @roseuid 43B2E4250186
	 */
	public GetSupervisionOrdersEvent()
	{

	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	/**
	 * @return
	 */
	public String getCaseId()
	{
		return caseId;
	}

	/**
	 * @return String
	 * @roseuid 438F22CC00A5
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @return String
	 * @roseuid 438F22CC0095
	 */
	public String getCourtDivision()
	{
		return courtDivision;
	}

	/**
	 * @return
	 */
	public boolean getOrderInProgress()
	{
		return orderInProgress;
	}

	/**
	 * @return
	 */
	public String getOrderStatusId()
	{
		return orderStatusId;
	}

	/**
	 * @return String
	 * @roseuid 438F22CC00B4
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @param anAgencyId
	 */
	public void setAgencyId(String anAgencyId)
	{
		agencyId = anAgencyId;
	}

	/**
	 * @param aCaseId
	 */
	public void setCaseId(String aCaseId)
	{
		caseId = aCaseId;
	}

	/**
	 * @param aCaseNum
	 * @roseuid 438F22CC00A3
	 */
	public void setCaseNum(String aCaseNum)
	{
		this.caseNum = aCaseNum;
	}

	/**
	 * @param aCourtDivision
	 * @roseuid 438F22CC0093
	 */
	public void setCourtDivision(String aCourtDivision)
	{
		this.courtDivision = aCourtDivision;
	}

	/**
	 * @param b
	 */
	public void setOrderInProgress(boolean b)
	{
		orderInProgress = b;
	}

	/**
	 * @param string
	 */
	public void setOrderStatusId(String string)
	{
		orderStatusId = string;
	}

	/**
	 * @param aSpn
	 * @roseuid 438F22CC00B2
	 */
	public void setSpn(String aSpn)
	{
		this.spn = aSpn;
	}

}
