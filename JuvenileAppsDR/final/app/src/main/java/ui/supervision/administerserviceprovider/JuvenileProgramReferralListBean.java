package ui.supervision.administerserviceprovider;

import java.util.List;

public class JuvenileProgramReferralListBean {
	private String juvServiceProviderName;
	private List referralList;
		
	public String getJuvServiceProviderName() {
		return juvServiceProviderName;
	}
	public void setJuvServiceProviderName(String juvServiceProviderName) {
		this.juvServiceProviderName = juvServiceProviderName;
	}
	
	/**
	 * @return Returns the activeReferralList.
	 */
	public List getReferralList() {
		return referralList;
	}
	/**
	 * @param activeReferralList The activeReferralList to set.
	 */
	public void setReferralList(List referralList) {
		this.referralList = referralList;
	}
}
