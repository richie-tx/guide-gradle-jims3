/*
 * Created on Nov 30, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCourtVariableElementsByConditionEvent extends RequestEvent{
	private String agencyId;
	private String conditionId;

    
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
     * @return Returns the conditionId.
     */
    public String getConditionId() {
        return conditionId;
    }
    /**
     * @param conditionId The conditionId to set.
     */
    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

}
