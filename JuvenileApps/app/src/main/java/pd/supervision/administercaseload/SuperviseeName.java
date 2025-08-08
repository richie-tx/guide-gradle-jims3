/*
 * Created on Mar 6, 2008
 */
package pd.supervision.administercaseload;

import java.util.Iterator;

import messaging.viewassignment.GetSuperviseeNameEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_rbhat
 */
public class SuperviseeName extends PersistentObject {
	private String defendantId;

	private String firstName;

	private String middleName;

	private String lastName;

	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		fetch();
		return defendantId;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		fetch();
		return firstName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		fetch();
		return lastName;
	}

	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		fetch();
		return middleName;
	}

	/**
	 * @param defendantId
	 *            The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		if (this.defendantId == null || !this.defendantId.equals(defendantId)) {
			markModified();
		}
		this.defendantId = defendantId;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		if (this.firstName == null || !this.firstName.equals(firstName)) {
			markModified();
		}
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		if (this.lastName == null || !this.lastName.equals(lastName)) {
			markModified();
		}
		this.lastName = lastName;
	}

	/**
	 * @param middleName
	 *            The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		if (this.middleName == null || !this.middleName.equals(middleName)) {
			markModified();
		}
		this.middleName = middleName;
	}

	public static SuperviseeName findByDefendantId(String defendantId) {
		GetSuperviseeNameEvent requestEvent = new GetSuperviseeNameEvent();
		requestEvent.setDefendantId(defendantId);
		IHome home = new Home();
		SuperviseeName superviseeName = null;
		Iterator iterator = home.findAll(requestEvent, SuperviseeName.class);
		for (; iterator.hasNext();) {
			superviseeName = (SuperviseeName) iterator.next();
		}
		return superviseeName;
	}
}
