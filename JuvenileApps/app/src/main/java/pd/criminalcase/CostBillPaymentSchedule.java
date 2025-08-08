package pd.criminalcase;

import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
* cm-pa4-jtp
*/
public class CostBillPaymentSchedule extends PersistentObject {
	/**
	* CM-PA4-CND
	*/
	private double courtCostsTotal;
	/**
	* Properties for JailTimePeriod
	* @referencedType pd.codetable.Code
	* @contextKey NEW_CONTEXT_KEY_NEEDED
	* @detailerDoNotGenerate true
	*/
	private Code jailTimePeriod = null;
	/**
	* cm-pa4-jtp
	*/
	private String jailTime;
	private String costBillSummaryId;
	private String jailTimePeriodId;
	/**
	* CM-PA4-FRC
	*/
	private double fineCostsPaymentRate;
	/**
	* @roseuid 43B2F0800271
	*/
	public CostBillPaymentSchedule() {
	}
	/**
	* @roseuid 43B2EF40030D
	*/
	public void findAll() {
		fetch();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setJailTimePeriodId(String jailTimePeriodId) {
		if (this.jailTimePeriodId == null || !this.jailTimePeriodId.equals(jailTimePeriodId)) {
			markModified();
		}
		jailTimePeriod = null;
		this.jailTimePeriodId = jailTimePeriodId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getJailTimePeriodId() {
		fetch();
		return jailTimePeriodId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initJailTimePeriod() {
		if (jailTimePeriod == null) {
			jailTimePeriod = (Code) new mojo.km.persistence.Reference(jailTimePeriodId, Code.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getJailTimePeriod() {
		initJailTimePeriod();
		return jailTimePeriod;
	}
	/**
	* set the type reference for class member JailTimePeriod
	*/
	public void setJailTimePeriod(Code JailTimePeriod) {
		if (this.jailTimePeriod == null || !this.jailTimePeriod.equals(JailTimePeriod)) {
			markModified();
		}
		if (JailTimePeriod.getOID() == null) {
			new mojo.km.persistence.Home().bind(JailTimePeriod);
		}
		setJailTimePeriodId("" + JailTimePeriod.getOID());
		this.jailTimePeriod = (Code) new mojo.km.persistence.Reference(JailTimePeriod).getObject();
	}
	/**
	* Set the reference value to class :: pd.criminalcase.CostBillSummary
	*/
	public void setCostBillSummaryId(String costBillSummaryId) {
		this.costBillSummaryId = costBillSummaryId;
	}
	/**
	* Get the reference value to class :: pd.criminalcase.CostBillSummary
	*/
	public String getCostBillSummaryId() {
		return costBillSummaryId;
	}
}
