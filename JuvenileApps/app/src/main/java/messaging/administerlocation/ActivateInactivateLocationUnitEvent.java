// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\ActivateInactivateLocationUnitEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class ActivateInactivateLocationUnitEvent extends RequestEvent {
	public String juvLocUnitId;

	public boolean isInactivate;

	public String action;

	/**
	 * @roseuid 4664668F0184
	 */
	public ActivateInactivateLocationUnitEvent() {

	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the isInactivate.
	 */
	public boolean isInactivate() {
		return isInactivate;
	}

	/**
	 * @param isInactivate
	 *            The isInactivate to set.
	 */
	public void setInactivate(boolean isInactivate) {
		this.isInactivate = isInactivate;
	}

	/**
	 * @return Returns the juvLocUnitId.
	 */
	public String getJuvLocUnitId() {
		return juvLocUnitId;
	}

	/**
	 * @param juvLocUnitId
	 *            The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(String juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}
}
