/*
 * Created on Jan 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.criminalcase;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCaseEvent extends RequestEvent
{
	private String courtDivisionId;
	private String caseNum;
	private String agencyId;
	/**
	 * @return
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @return
	 */
	public String getCourtDivisionId()
	{
		return courtDivisionId;
	}

	/**
	 * @param aCaseNum
	 */
	public void setCaseNum(String aCaseNum)
	{
		caseNum = aCaseNum;
	}

	/**
	 * @param aCdi
	 */
	public void setCourtDivisionId(String aCdi)
	{
		courtDivisionId = aCdi;
	}

    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
