package pd.supervision.legacyupdates.entities;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;

/**
 * This attribute represents the legacy POI code.
 */
public class ReferralSubmit extends PersistentObject {
	/**
	 * This attribute represents the legacy POI code.
	 */

	private String spn;
	private String recordType;
	private String action;
	private String seqNumber;
	private String contractProg;
	private String placementReason;
	private String confineDays;
	private String confineMonths;
	private String confineYears;
	private String isCommJusticePlan;
	private String ctsCode;
	private String pfsCode;
	private String progBeginDate;
	private String designator;
	private String progEnDate;
	private String dischargeReason;
	private String filler;
	private String cdi;
	private String cas;
	private String opId;
	private String msg;
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCdi() {
		return cdi;
	}

	public void setCdi(String cdi) {
		this.cdi = cdi;
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}
	
	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getProgEnDate() {
		fetch();
		return progEnDate;
	}

	public void setProgEnDate(String progEnDate) {
		if (this.progEnDate == null || !this.progEnDate.equals(progEnDate)) {
			markModified();
		}
		this.progEnDate = progEnDate;
	}

	public String getDischargeReason() {
		fetch();
		return dischargeReason;
	}

	public void setDischargeReason(String dischargeReason) {
		if (this.dischargeReason == null
				|| !this.dischargeReason.equals(dischargeReason)) {
			markModified();
		}
		this.dischargeReason = dischargeReason;
	}

	public String getPlacementReason() {
		fetch();
		return placementReason;
	}

	public void setPlacementReason(String placementReason) {
		if (this.placementReason == null
				|| !this.placementReason.equals(placementReason)) {
			markModified();
		}
		this.placementReason = placementReason;
	}

	public String getPfsCode() {
		
		fetch();
		return pfsCode;
	}

	public void setPfsCode(String s_pfsCode) {
		
		this.pfsCode = s_pfsCode;
	}

	public String getConfineDays() {
		fetch();
		return confineDays;
	}

	public void setConfineDays(String confineDays) {
		if (this.confineDays == null || !this.confineDays.equals(confineDays)) {
			markModified();
		}
		this.confineDays = confineDays;
	}

	public String getConfineMonths() {
		fetch();
		return confineMonths;
	}

	public void setConfineMonths(String confineMonths) {
		if (this.confineMonths == null
				|| !this.confineMonths.equals(confineMonths)) {
			markModified();
		}
		this.confineMonths = confineMonths;
	}

	public String getConfineYears() {
		fetch();
		return confineYears;
	}

	public void setConfineYears(String confineYears) {
		if (this.confineYears == null
				|| !this.confineYears.equals(confineYears)) {
			markModified();
		}
		this.confineYears = confineYears;
	}

	public String getSpn() {
		fetch();
		return spn;
	}

	public void setSpn(String spn) {
		if (this.spn == null || !this.spn.equals(spn)) {
			markModified();
		}
		this.spn = spn;
	}

	public String getRecordType() {
		fetch();
		return recordType;
	}

	public void setRecordType(String recordType) {
		if (this.recordType == null || !this.recordType.equals(recordType)) {
			markModified();
		}
		this.recordType = recordType;
	}

	public String getAction() {
		fetch();
		return action;
	}

	public void setAction(String action) {
		if (this.action == null || !this.action.equals(action)) {
			markModified();
		}
		this.action = action;
	}

	public String getSeqNumber() {
		fetch();
		return seqNumber;
	}

	public void setSeqNumber(String seqNumber) {
		if (this.seqNumber == null || !this.seqNumber.equals(seqNumber)) {
			markModified();
		}
		this.seqNumber = seqNumber;
	}

	/**
	 * Replaced DPT
	 * @return
	 */
	public String getIsCommJusticePlan() {
		fetch();
		return isCommJusticePlan;
	}

	/**
	 * 
	 * @param b_commJPlan
	 */
	public void setIsCommJusticePlan(boolean b_commJPlan) {
		String comJustPlan = "N";
		if( b_commJPlan ){
			comJustPlan = "Y";
		}
		if (this.isCommJusticePlan != (comJustPlan)) {
			markModified();
		}
		this.isCommJusticePlan = comJustPlan;
	}

	public String getCtsCode() {
		fetch();
		return ctsCode;
	}

	public String getContractProg() {
		return contractProg;
	}

	public void setContractProg(boolean b_contractProg) {
		String contractProg = "N";
		if(b_contractProg){
			contractProg = "Y";
		}
		if (this.contractProg != (contractProg)) {
			markModified();
		}
		this.contractProg = contractProg;
	}

	public void setCtsCode(String ctsCode) {
		if (this.ctsCode == null || !this.ctsCode.equals(ctsCode)) {
			markModified();
		}
		this.ctsCode = ctsCode;
	}

	public String getProgBeginDate() {
		fetch();
		return progBeginDate;
	}

	public void setProgBeginDate(String progBeginDate) {
		if (this.progBeginDate == null
				|| !this.progBeginDate.equals(progBeginDate)) {
			markModified();
		}
		this.progBeginDate = progBeginDate;
	}

	public String getDesignator() {
		fetch();
		return designator;
	}

	public void setDesignator(String designator) {
		if (this.designator == null || !this.designator.equals(designator)) {
			markModified();
		}
		this.designator = designator;
	}

	public String bind() {
		IHome home = new Home();
		home.bind(this);
		return this.getSeqNumber();
	}

	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, ReferralSubmit.class);
	}

}