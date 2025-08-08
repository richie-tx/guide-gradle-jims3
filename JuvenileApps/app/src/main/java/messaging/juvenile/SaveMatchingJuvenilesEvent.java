/*
 * Created on Jul 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveMatchingJuvenilesEvent extends RequestEvent {
	private String juvA;
	private String juvB;
	private String status;
	private String notes;
	private String createUser;
	/**
	 * @return Returns the createUser.
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * @param createUser The createUser to set.
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * @return Returns the juvA.
	 */
	public String getJuvA() {
		return juvA;
	}
	/**
	 * @param juvA The juvA to set.
	 */
	public void setJuvA(String juvA) {
		this.juvA = juvA;
	}
	/**
	 * @return Returns the juvB.
	 */
	public String getJuvB() {
		return juvB;
	}
	/**
	 * @param juvB The juvB to set.
	 */
	public void setJuvB(String juvB) {
		this.juvB = juvB;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
