/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import java.util.List;

/**
 * @author mchowdhury
 */
public class ComplianceConditionResponseEvent extends AComplianceConditionResponseEvent 
{
    private List courtPolicies; 
    private List departmentPolicies; 
    private String AMPMId;  

	/**
	 * @return Returns the courtPolicies.
	 */
	public List getCourtPolicies() {
		return courtPolicies;
	}
	/**
	 * @param courtPolicies The courtPolicies to set.
	 */
	public void setCourtPolicies(List courtPolicies) {
		this.courtPolicies = courtPolicies;
	}
	/**
	 * @return Returns the departmentPolicies.
	 */
	public List getDepartmentPolicies() {
		return departmentPolicies;
	}
	/**
	 * @param departmentPolicies The departmentPolicies to set.
	 */
	public void setDepartmentPolicies(List departmentPolicies) {
		this.departmentPolicies = departmentPolicies;
	}
	/**
	 * @return the aMPMId
	 */
	public String getAMPMId() {
		return AMPMId;
	}
	/**
	 * @param id the aMPMId to set
	 */
	public void setAMPMId(String id) {
		AMPMId = id;
	}
}
