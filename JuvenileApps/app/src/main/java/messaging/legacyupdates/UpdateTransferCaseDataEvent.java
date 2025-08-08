package messaging.legacyupdates;

import java.util.Date;

public class UpdateTransferCaseDataEvent extends LegacyUpdatesRequestEvent
{
	private String action;
	private Date transferDate;
	private String transfrCntyJurisCode;
	private String receivngCntyJurisCode;
    private String transferTypeCode;
    private String oriCntyPersonId;
    private String suprvsngCntyPersonId;
    private boolean jurisCodeChanged;
    private boolean supervisionRejected;
    
    
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the transferDate
	 */
	public Date getTransferDate() {
		return transferDate;
	}
	/**
	 * @param transferDate the transferDate to set
	 */
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	/**
	 * @return the transfrCntyJurisCode
	 */
	public String getTransfrCntyJurisCode() {
		return transfrCntyJurisCode;
	}
	/**
	 * @param transfrCntyJurisCode the transfrCntyJurisCode to set
	 */
	public void setTransfrCntyJurisCode(String transfrCntyJurisCode) {
		this.transfrCntyJurisCode = transfrCntyJurisCode;
	}
	/**
	 * @return the receivngCntyJurisCode
	 */
	public String getReceivngCntyJurisCode() {
		return receivngCntyJurisCode;
	}
	/**
	 * @param receivngCntyJurisCode the receivngCntyJurisCode to set
	 */
	public void setReceivngCntyJurisCode(String receivngCntyJurisCode) {
		this.receivngCntyJurisCode = receivngCntyJurisCode;
	}
	/**
	 * @return the transferTypeCode
	 */
	public String getTransferTypeCode() {
		return transferTypeCode;
	}
	/**
	 * @param transferTypeCode the transferTypeCode to set
	 */
	public void setTransferTypeCode(String transferTypeCode) {
		this.transferTypeCode = transferTypeCode;
	}
	/**
	 * @return the oriCntyPersonId
	 */
	public String getOriCntyPersonId() {
		return oriCntyPersonId;
	}
	/**
	 * @param oriCntyPersonId the oriCntyPersonId to set
	 */
	public void setOriCntyPersonId(String oriCntyPersonId) {
		this.oriCntyPersonId = oriCntyPersonId;
	}
	/**
	 * @return the suprvsngCntyPersonId
	 */
	public String getSuprvsngCntyPersonId() {
		return suprvsngCntyPersonId;
	}
	/**
	 * @param suprvsngCntyPersonId the suprvsngCntyPersonId to set
	 */
	public void setSuprvsngCntyPersonId(String suprvsngCntyPersonId) {
		this.suprvsngCntyPersonId = suprvsngCntyPersonId;
	}
	/**
	 * @return the jurisCodeChanged
	 */
	public boolean isJurisCodeChanged() {
		return jurisCodeChanged;
	}
	/**
	 * @param jurisCodeChanged the jurisCodeChanged to set
	 */
	public void setJurisCodeChanged(boolean jurisCodeChanged) {
		this.jurisCodeChanged = jurisCodeChanged;
	}
	/**
	 * @return the supervisionRejected
	 */
	public boolean isSupervisionRejected() {
		return supervisionRejected;
	}
	/**
	 * @param supervisionRejected the supervisionRejected to set
	 */
	public void setSupervisionRejected(boolean supervisionRejected) {
		this.supervisionRejected = supervisionRejected;
	}
}
