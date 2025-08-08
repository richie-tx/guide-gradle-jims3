/*
 * Created on Oct 27, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan;

import mojo.km.messaging.RequestEvent;

/**
 * @author dapte
 */
public class PersonResponsibleEvent extends RequestEvent {

	private String name;
	private String type;
	private String goalID;
	
	
	/**
	 * @return Returns the goalID.
	 */
	public String getGoalID() {
		return goalID;
	}
	/**
	 * @param goalID The goalID to set.
	 */
	public void setGoalID(String goalID) {
		this.goalID = goalID;
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
