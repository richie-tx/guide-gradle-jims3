package pd.supervision.legacyupdates.entities;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

/**
 * This attribute represents the legacy POI code.
 */
public class LevelOfSupervision extends PersistentObject {
	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, LevelOfSupervision.class);
	}

	private String action;

	private String buildT20Ind;
	private String caseNum;
	private String comments;
	/**
	 * This attribute represents the legacy POI code.
	 */
	private String los;
	private String olos;
	private String opId; 
	private String seqNumber;
	private String spn;
	private Date transactionDate;
	
	public String bind() {
		IHome home = new Home();
		home.bind(this);
		return this.getSeqNumber();
	}


	public String getAction() {
		fetch();
		return action;
	}


	public String getBuildT20Ind() {
		return buildT20Ind;
	}
	
	
	public String getCaseNum() {
		return caseNum;
	}

	public String getComments() {
		fetch();
		return comments;
	}

	public String getLos() {
		fetch();
		return los;
	}

	public String getOlos() {
		return olos;
	}

	
	public String getOpId() {
		return opId;
	}

	public String getSeqNumber() {
		fetch();
		return seqNumber;
	}

	public String getSpn() {
		return spn;
	}

	public Date getTransactionDate() {
		fetch();
		return transactionDate;
	}

	public void setAction(String action) {
		if (this.action == null || !this.action.equals(action)) {
			markModified();
		}
		this.action = action;
	}

	public void setBuildT20Ind(String buildT20Ind) {
		this.buildT20Ind = buildT20Ind;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public void setComments(String comments) {
		if (this.seqNumber == null || !this.seqNumber.equals(seqNumber)) {
			markModified();
		}
		this.comments = comments;
	}

	public void setLos(String los) {
		if (this.los == null || !this.los.equals(los)) {
			markModified();
		}
		this.los = los;
	}

	public void setOlos(String olos) {
		this.olos = olos;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public void setSeqNumber(String seqNumber) {
		if (this.seqNumber == null || !this.seqNumber.equals(seqNumber)) {
			markModified();
		}
		this.seqNumber = seqNumber;
	}

	public void setSpn(String spn) {
		this.spn = spn;
	}

	public void setTransactionDate(Date transactionDate) {
		if (this.transactionDate == null
				|| !this.transactionDate.equals(transactionDate)) {
			markModified();
		}
		this.transactionDate = transactionDate;
	}
}
