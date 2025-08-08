/*
 * Created on Sep 22, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetConditionCourtVariableElementsEvent extends RequestEvent{
	private String courtId;
	private String agencyId;
	public List conditionIds = new ArrayList(); 

    /**
     * @return Returns the conditionIds.
     */
    public List getConditionIds() {
        return conditionIds;
    }
    /**
     * @param conditionIds The conditionIds to set.
     */
    public void setConditionIds(List conditionIds) {
        this.conditionIds = conditionIds;
    }
//	private String conditionId;
	
//	/**
//	 * @return Returns the conditionId.
//	 */
//	public String getConditionId() {
//		return conditionId;
//	}
//	/**
//	 * @param conditionId The conditionId to set.
//	 */
//	public void setConditionId(String conditionId) {
//		this.conditionId = conditionId;
//	}
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
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
}
