//Source file: C:\\views\\dev\\app\\src\\messaging\\contact\\GetPartyDataEvent.java

package messaging.party;

import mojo.km.messaging.RequestEvent;

public class GetPartyDataEvent extends RequestEvent
{
	private String agencyId;
	private String badgeNum;
	private String currentNameInd;
	private String OID;
	private String spn;
	private String payrollNum;
	private String name;
	private String namePtr;
	private String nameSeqNum;
	private boolean thinResponse;

	/**
	 * @roseuid 416D2E380333
	 */
	public GetPartyDataEvent()
	{

	}

	/**
	 * Access method for the agencyId property.
	 * 
	 * @return   the current value of the agencyId property
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * Access method for the badgeNum property.
	 * 
	 * @return   the current value of the badgeNum property
	 */
	public String getBadgeNum()
	{
		return badgeNum;
	}

	/**
	 * Sets the value of the agencyId property.
	 * 
	 * @param aAgencyId the new value of the agencyId property
	 */
	public void setAgencyId(String aAgencyId)
	{
		agencyId = aAgencyId;
	}

	/**
	 * Sets the value of the badgeNum property.
	 * 
	 * @param aBadgeNum the new value of the badgeNum property
	 */
	public void setBadgeNum(String aBadgeNum)
	{
		badgeNum = aBadgeNum;
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @param spn
	 */
	public void setSpn(String spn)
	{
		this.spn = spn;
	}
	/**
	 * @return
	 */
	public String getPayrollNum()
	{
		return payrollNum;
	}

	/**
	 * @param string
	 */
	public void setPayrollNum(String string)
	{
		payrollNum = string;
	}

	/**
	 * @return
	 */
	public String getCurrentNameInd()
	{
		return currentNameInd;
	}

	/**
	 * @return   the current value of the name property
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param aName the new value of the name property
	 */
	public void setName(String aName)
	{
		name = aName;
	}
	
	/**
	 * @return   the current value of the namePtr property
	 */
	public String getNamePtr()
	{
		return namePtr;
	}

	/**
	 * @param aNamePtr the new value of the namePtr property
	 */
	public void setNamePtr(String aNamePtr)
	{
		namePtr = aNamePtr;
	}
	
	/**
	 * @return  the current value of the OID property
	 */
	public String getOID()
	{
		return OID;
	}

	/**
	 * @param string
	 */
	public void setCurrentNameInd(String value)
	{
		currentNameInd = value;
	}

	/**
	 * @param string
	 */
	public void setOID(String string)
	{
		OID = string;
	}

	/**
	 * @return
	 */
	public boolean isThinResponse()
	{
		return thinResponse;
	}

	/**
	 * @param b
	 */
	public void setThinResponse(boolean b)
	{
		thinResponse = b;
	}

	public void setNameSeqNum(String nameSeqNum) {
		this.nameSeqNum = nameSeqNum;
	}

	public String getNameSeqNum() {
		return nameSeqNum;
	}

}