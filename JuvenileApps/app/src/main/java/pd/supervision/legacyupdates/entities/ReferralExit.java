package pd.supervision.legacyupdates.entities;

import java.util.Date;

import mojo.km.persistence.PersistentObject;

/**
 * This attribute represents the legacy POI code.
 */
public class ReferralExit extends PersistentObject {
	/**
	 * This attribute represents the legacy POI code.
	 */
	private Date programEndDate;

	public Date getProgramEndDate() {
		fetch();
		return programEndDate;
	}

	public void setProgramEndDate(Date programEndDate) {
		if (this.programEndDate == null
				|| !this.programEndDate.equals(programEndDate)) {
			markModified();
		}
		this.programEndDate = programEndDate;
	}

	public String getDischargeReasonId() {
		fetch();
		return dischargeReasonId;
	}

	public void setDischargeReasonId(String dischargeReasonId) {
		if (this.dischargeReasonId == null
				|| !this.dischargeReasonId.equals(dischargeReasonId)) {
			markModified();
		}
		this.dischargeReasonId = dischargeReasonId;
	}

	private String dischargeReasonId;
}
