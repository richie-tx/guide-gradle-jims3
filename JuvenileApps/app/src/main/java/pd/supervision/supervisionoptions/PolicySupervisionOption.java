package pd.supervision.supervisionoptions;

import pd.supervision.supervisionoptions.CourtPolicy;

/**
* @roseuid 42F7C4120251
*/
public class PolicySupervisionOption extends CourtSupervisionOption {
	private String policyId;
	/**
	* Properties for policy
	*/
	public CourtPolicy policy = null;
	/**
	* @roseuid 42F7C4120251
	*/
	public PolicySupervisionOption() {
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.CourtPolicy
	*/
	public void setPolicyId(String policyId) {
		if (this.policyId == null || !this.policyId.equals(policyId)) {
			markModified();
		}
		policy = null;
		this.policyId = policyId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.CourtPolicy
	*/
	public String getPolicyId() {
		fetch();
		return policyId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.CourtPolicy
	*/
	private void initPolicy() {
		if (policy == null) {
			try {
				policy =
					(CourtPolicy) new mojo.km.persistence.Reference(policyId, CourtPolicy.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.CourtPolicy
	*/
	public CourtPolicy getPolicy() {
		initPolicy();
		return policy;
	}
	/**
	* set the type reference for class member policy
	*/
	public void setPolicy(CourtPolicy policy) {
		if (this.policy == null || !this.policy.equals(policy)) {
			markModified();
		}
		if (policy.getOID() == null) {
			new mojo.km.persistence.Home().bind(policy);
		}
		setPolicyId("" + policy.getOID());
		this.policy = (CourtPolicy) new mojo.km.persistence.Reference(policy).getObject();
	}
}
