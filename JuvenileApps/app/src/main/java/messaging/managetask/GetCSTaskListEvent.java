// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\managetask\\GetCSTaskListEvent.java

package messaging.managetask;

import mojo.km.messaging.RequestEvent;

public class GetCSTaskListEvent extends RequestEvent {


	public static final String TASKS_FOR_WORKGROUP_DISCRIMINANT = "getTasksForWorkgroup";

	public static final String TASKS_FOR_POSITION_DISCRIMINANT = "getTasksForPosition";

	public static final String TASKS_FOR_DEFENDANT_DISCRIMINANT = "getTasksForDefendantId";

	public String ownerId;

	public Object[] arrayOfWorkgroupIds;

	public String discriminant;

	public String positionId;

	private String defendantId;

	/**
	 * @roseuid 463F3016006C
	 */
	public GetCSTaskListEvent() {

	}

	/**
	 * @param ownerId
	 * @return
	 * @roseuid 463F171F01A4
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return String
	 * @roseuid 463F171F01A6
	 */
	public String getOwnerId() {
		return this.ownerId;
	}

	/**
	 * @return Returns the arrayOfWorkgroupIds.
	 */
	public Object[] getArrayOfWorkgroupIds() {
		return arrayOfWorkgroupIds;
	}

	/**
	 * @param arrayOfWorkgroupIds
	 *            The arrayOfWorkgroupIds to set.
	 */
	public void setArrayOfWorkgroupIds(Object[] arrayOfWorkgroupIds) {
		this.arrayOfWorkgroupIds = arrayOfWorkgroupIds;
	}

	/**
	 * @return Returns the discriminant.
	 */
	public String getDiscriminant() {
		return discriminant;
	}

	/**
	 * @param discriminant
	 *            The discriminant to set.
	 */
	public void setDiscriminant(String discriminant) {
		this.discriminant = discriminant;
	}

	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId
	 *            The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @param defendantId
	 *            The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}
