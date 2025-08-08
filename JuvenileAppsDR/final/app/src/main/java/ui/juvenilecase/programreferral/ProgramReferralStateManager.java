/*
 * Created on May 17, 2007
 *
 */
package ui.juvenilecase.programreferral;

import naming.ProgramReferralConstants;
import ui.juvenilecase.programreferral.ProgramReferralState;
import ui.juvenilecase.programreferral.ProgramReferralTentativeState;

/**
 */
public class ProgramReferralStateManager {

	public static ProgramReferralState getReferralState(String referralStatusCd,String referralSubStatusCd){
		ProgramReferralState refState = null;
		if (referralStatusCd.equals(ProgramReferralConstants.TENTATIVE)){
			refState = new ProgramReferralTentativeState();
			refState.setSubStatus(referralSubStatusCd);
		}else if (referralStatusCd.equals(ProgramReferralConstants.CLOSED)){
			refState = new ProgramReferralClosedState();
			refState.setSubStatus(referralSubStatusCd);
		}else if (referralStatusCd.equals(ProgramReferralConstants.ACCEPTED)){
			refState = new ProgramReferralAcceptedState();
			refState.setSubStatus("");
		}
		return refState;
	}
	
	
	/**
	 * 
	 */
	private ProgramReferralStateManager() {

	}
}
