package pd.supervision.administerprogramreferrals;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class LegacyDischargeReason extends PersistentObject {
	private String spn;
	private String caseNum;
	private String legacyDischargeReasonCode;
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getLegacyDischargeReasonCode() {
		return legacyDischargeReasonCode;
	}
	public void setLegacyDischargeReasonCode(String legacyDischargeReasonCode) {
		this.legacyDischargeReasonCode = legacyDischargeReasonCode;
	}
	public static LegacyDischargeReason find(String spn, String criminalCaseId){
		StringBuffer compKey = new StringBuffer(spn);
		compKey.append(criminalCaseId.substring(3));
		IHome home = new Home();
		LegacyDischargeReason obj =  (LegacyDischargeReason) home.find(compKey.toString(), LegacyDischargeReason.class);
		return obj;
	}
}
