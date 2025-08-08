/*
 * Created on Jan 25, 2006
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetMostCurrentSupervisionOrderForCaseEvent extends RequestEvent
{
	private String agencyId;
	private String spn;
	private String orderStatusId;
	private String caseId;
	

    /**
     * @return Returns the caseId.
     */
    public String getCaseId() {
        return caseId;
    }
    /**
     * @param caseId The caseId to set.
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    /**
     * @return Returns the spn.
     */
    public String getSpn() {
        return spn;
    }
    /**
     * @param spn The spn to set.
     */
    public void setSpn(String spn) {
        this.spn = spn;
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
	public String getOrderStatusId()
	{
		return orderStatusId;
	}

	/**
	 * @param anAgencyId
	 */
	public void setAgencyId(String anAgencyId)
	{
		agencyId = anAgencyId;
	}

	/**
	 * @param anOrderStatusId
	 */
	public void setOrderStatusId(String anOrderStatusId)
	{
		orderStatusId = anOrderStatusId;
	}

}
