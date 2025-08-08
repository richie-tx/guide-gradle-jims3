/*
 * Created on Feb 20, 2008
 */
package ui.supervision.viewassignment.form;

import java.util.Iterator;
import java.util.List;

import messaging.contact.domintf.IName;
import messaging.viewassignment.SuperviseeTO;

/**
 * @author cc_rbhat
 */
public class SuperviseeReport extends BaseCaseAssignmentReport {
	private String defendantId;

	private String defendantName;

	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @return Returns the defendantName.
	 */
	public String getDefendantName() {
		List searchResults = this.getSearchResults();
		if (searchResults != null) {
			for (Iterator iterator = searchResults.iterator(); iterator.hasNext();) {
				//for supervisee search, there will be only one entry in results.
				SuperviseeTO supervisee = (SuperviseeTO) iterator.next();
				IName name = supervisee.getDefendantName();
				if (name != null) {
					defendantName = name.getFormattedName();
				} else {
					defendantName = "No Name Available";
				}
			}			
		}
		return defendantName;
	}

	/**
	 * @param defendantId
	 *            The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	/**
	 * @param defendantName
	 *            The defendantName to set.
	 */
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
}
