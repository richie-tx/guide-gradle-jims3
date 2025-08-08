package messaging.supervisionorder.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class SuperviseeCaseOrderResponseEvent extends ResponseEvent
{
    private String defendantId;
    private String cdi;
    private String caseNumber;
    private String offenseCodeId;
    private String offenseCodeDesc;
    private String supervisionOrderId;  // this is the oid
    private Date caseFileDate;
    private String orderStatus;
    private String version;
    private String currentCourtId;
    private String supervisionPeriodId;
	/**
	 * @return Returns the orderStatus.
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus The orderStatus to set.
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
    private Date orderFiledDate;
    private String courtId;
    private String displayCourtId;
    
	/**
	 * @return Returns the supervisionOrderId.
	 */
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}
	/**
	 * @param supervisionOrderId The supervisionOrderId to set.
	 */
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
	/**
	 * @return Returns the offenseCodeDesc.
	 */
	public String getOffenseCodeDesc() {
		return offenseCodeDesc;
	}
	/**
	 * @param offenseCodeDesc The offenseCodeDesc to set.
	 */
	public void setOffenseCodeDesc(String offenseCodeDesc) {
		this.offenseCodeDesc = offenseCodeDesc;
	}
    
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @return the displayCourtId
	 */
	public String getDisplayCourtId() {
		return displayCourtId;
	}
	/**
	 * @param displayCourtId the displayCourtId to set
	 */
	public void setDisplayCourtId(String displayCourtId) {
		this.displayCourtId = displayCourtId;
	}
	/**
	 * @return Returns the caseFileDate.
	 */
	public Date getCaseFileDate() {
		return caseFileDate;
	}
	/**
	 * @param caseFileDate The caseFileDate to set.
	 */
	public void setCaseFileDate(Date caseFileDate) {
		this.caseFileDate = caseFileDate;
	}
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * @return Returns the cdi.
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi The cdi to set.
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the offenseCodeId.
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}
	/**
	 * @param offenseCodeId The offenseCodeId to set.
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}
	/**
	 * @return Returns the orderFiledDate.
	 */
	public Date getOrderFiledDate() {
		return orderFiledDate;
	}
	/**
	 * @param orderFiledDate The orderFiledDate to set.
	 */
	public void setOrderFiledDate(Date orderFiledDate) {
		this.orderFiledDate = orderFiledDate;
	}
	/**
	 * @return the currentCourtId
	 */
	public String getCurrentCourtId() {
		return currentCourtId;
	}
	/**
	 * @param currentCourtId the currentCourtId to set
	 */
	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
	
}
