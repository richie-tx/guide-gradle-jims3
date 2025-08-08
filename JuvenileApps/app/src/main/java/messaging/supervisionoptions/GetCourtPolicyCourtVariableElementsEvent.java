/*
 * Created on Oct 27, 2006
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
public class GetCourtPolicyCourtVariableElementsEvent extends RequestEvent {
	private String courtId;
	private String agencyId;
	private String courtPolicyId;

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
     * @return Returns the courtPolicyId.
     */
    public String getCourtPolicyId() {
        return courtPolicyId;
    }
    /**
     * @param courtPolicyId The courtPolicyId to set.
     */
    public void setCourtPolicyId(String courtPolicyId) {
        this.courtPolicyId = courtPolicyId;
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
