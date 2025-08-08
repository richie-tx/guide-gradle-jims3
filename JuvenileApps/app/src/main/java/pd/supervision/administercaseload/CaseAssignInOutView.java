package pd.supervision.administercaseload;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;
import java.util.Date;

/**
 * Retrieval Methods *******************************
 */
public class CaseAssignInOutView extends PersistentObject {
	private String caseAssignIoId;
	private String defendantId;
	private String criminalCaseId;
	private Date beginDate;
	private Date endDate;
	private String assignStaffPositionId;
	private String inOut;

	public String getCaseAssignIoId() {
		fetch();
		return caseAssignIoId;
	}

	public void setCaseAssignIoId(String caseAssignIoId) {
		if (this.caseAssignIoId == null
				|| !this.caseAssignIoId.equals(caseAssignIoId)) {
			markModified();
		}
		this.caseAssignIoId = caseAssignIoId;
	}

	public String getDefendantId() {
		fetch();
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		if (this.defendantId == null || !this.defendantId.equals(defendantId)) {
			markModified();
		}
		this.defendantId = defendantId;
	}

	public String getCriminalCaseId() {
		fetch();
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		if (this.criminalCaseId == null
				|| !this.criminalCaseId.equals(criminalCaseId)) {
			markModified();
		}
		this.criminalCaseId = criminalCaseId;
	}

	public Date getBeginDate() {
		fetch();
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		if (this.beginDate == null || !this.beginDate.equals(beginDate)) {
			markModified();
		}
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		fetch();
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (this.endDate == null || !this.endDate.equals(endDate)) {
			markModified();
		}
		this.endDate = endDate;
	}

	public String getAssignStaffPositionId() {
		fetch();
		return assignStaffPositionId;
	}

	public void setAssignStaffPositionId(String assignStaffPositionId) {
		if (this.assignStaffPositionId == null
				|| !this.assignStaffPositionId.equals(assignStaffPositionId)) {
			markModified();
		}
		this.assignStaffPositionId = assignStaffPositionId;
	}

	public String getInOut() {
		fetch();
		return inOut;
	}

	public void setInOut(String inOut) {
		if (this.inOut == null || !this.inOut.equals(inOut)) {
			markModified();
		}
		this.inOut = inOut;
	}

	/**
	 * Retrieval Methods *******************************
	 */
	static public CaseAssignInOutView find(String anOid) {
		IHome home = new Home();
		return (CaseAssignInOutView) home
				.find(anOid, CaseAssignInOutView.class);
	}

	static public Iterator findAll(IEvent anEvent) {
		IHome home = new Home();
		return home.findAll(anEvent, CaseAssignInOutView.class);
	}

	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, attrValue, CaseAssignInOutView.class);
	}
}
