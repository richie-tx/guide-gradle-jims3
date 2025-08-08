/*
 * Created on June 08, 2009
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * 
 *
 */
public class GetCurrentSupervisionOrderStartEndDatesEvent extends RequestEvent
{
	private String agencyId;	
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
