/*
 * Created on Jan 22, 2008
 *
 */
package messaging.viewassignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.contact.domintf.IName;

/**
 * @author cc_rbhat
 *  
 */
public class SuperviseeTO implements Comparable{
	private String defendantId;

	private IName defendantName;

	private List activeCases = new ArrayList();
	
	public int compareTo(Object o1) {
		final int EQUAL = 0;
		int result = EQUAL;
		//check casting
		SuperviseeTO aThat = (SuperviseeTO) o1;
		//object address comparison
		if (this == aThat) return EQUAL;
		//attribute comparison
		int nameComparison = this.defendantName.getFormattedName().compareTo(aThat.getDefendantName().getFormattedName());
		if (nameComparison == EQUAL) {
			int idComparison = this.defendantId.compareTo(aThat.getDefendantId());
			result = idComparison;
		} else {
			result = nameComparison;
		}		
		return result;
	}
	
	public void sortCasesByProgramUnitAssignmentDate() {
		Collections.sort(activeCases, new Comparator() {
				public int compare(Object o1, Object o2) {
					if (!(o1 instanceof ICaseAssignment)) 
						throw new ClassCastException();
					if (!(o2 instanceof ICaseAssignment)) 
						throw new ClassCastException();
					final int EQUAL = 0;
					final int BEFORE = 1;
					final int AFTER = -1;
					Date programUnitAssignDate1 = ((ICaseAssignment) o1).getProgramUnitAssignDate();
					Date programUnitAssignDate2 = ((ICaseAssignment) o2).getProgramUnitAssignDate();
					int puDateComparison = programUnitAssignDate1.compareTo(programUnitAssignDate2);
					if (puDateComparison == EQUAL) {
						Date officerAssignDate1 = ((ICaseAssignment) o1).getOfficerAssignDate();
						Date officerAssignDate2 = ((ICaseAssignment) o2).getOfficerAssignDate();	
						if (officerAssignDate1 == null && officerAssignDate2 == null) {
							return EQUAL;
						} else if (officerAssignDate1 == null && officerAssignDate2 != null) {
							return AFTER; //date2 is after date1
						} else if (officerAssignDate1 != null && officerAssignDate2 == null) {
							return BEFORE; //date2 is before date1
						} else {
							return officerAssignDate1.compareTo(officerAssignDate2);
						}
					} else {
						return puDateComparison;
					}
				}
				public boolean equals(Object o1) {
					if ((o1 instanceof Comparator) && (this == o1)) 
						return true;
					else
						return false;
				}
			}
		);		
	}

	/**
	 * @return Returns the activeCases.
	 */
	public List getActiveCases() {
		return activeCases;
	}

	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @return Returns the defendantName.
	 */
	public IName getDefendantName() {
		return defendantName;
	}

	/**
	 * @param activeCases
	 *            The activeCases to set.
	 */
	public void setActiveCases(List activeCases) {
		this.activeCases = activeCases;
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
	public void setDefendantName(IName defendantName) {
		this.defendantName = defendantName;
	}
}
