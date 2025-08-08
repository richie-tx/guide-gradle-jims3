//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\ActivateSupervisionOrderEvent.java

package messaging.supervisionorder;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class UpdateSupervisionOrderStatusEvent extends RequestEvent
{
	private String supervisionOrderId;
	private String status;
	private Date statusChangeDate;  
	
	private String defendantId;
	private String name;
	private boolean outOfCountyCase=false;
	private String courtId;
	private String killTaskId;
	private String currentUserId;
	
	private List orderConditionIdsList;
	
	
	/**
	 * @roseuid 43B2E402007D
	 */
	public UpdateSupervisionOrderStatusEvent()
	{

	}
	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @param aSupervisionOrderId
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		supervisionOrderId = aSupervisionOrderId;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}
	/**
	 * @return
	 */
	public Date getStatusChangeDate()
	{
		return statusChangeDate;
	}

	/**
	 * @param aStatus
	 */
	public void setStatus(String aStatus)
	{
		status = aStatus;
	}
	

	/**
	 * @param aStatus
	 */
	public void setStatusChangeDate(Date aDate)
	{
		statusChangeDate = aDate;
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the outOfCountyCase.
	 */
	public boolean isOutOfCountyCase() {
		return outOfCountyCase;
	}
	/**
	 * @param outOfCountyCase The outOfCountyCase to set.
	 */
	public void setOutOfCountyCase(boolean outOfCountyCase) {
		this.outOfCountyCase = outOfCountyCase;
	}
	/**
	 * @return Returns the killTaskId.
	 */
	public String getKillTaskId() {
		return killTaskId;
	}
	/**
	 * @param killTaskId The killTaskId to set.
	 */
	public void setKillTaskId(String killTaskId) {
		this.killTaskId = killTaskId;
	}
	/**
	 * @return Returns the currentUserId.
	 */
	public String getCurrentUserId() {
		return currentUserId;
	}
	/**
	 * @param currentUserId The currentUserId to set.
	 */
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	public List getOrderConditionIdsList() {
		return orderConditionIdsList;
	}
	public void setOrderConditionIdsList(List orderConditionIdsList) {
		this.orderConditionIdsList = orderConditionIdsList;
	}
}
