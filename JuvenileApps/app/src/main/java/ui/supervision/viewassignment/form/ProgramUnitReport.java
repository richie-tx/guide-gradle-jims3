/*
 * Created on Feb 20, 2008
 */
package ui.supervision.viewassignment.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.cscdstaffposition.reply.OrganizationResponseEvent;


/**
 * @author cc_rbhat
 */
public class ProgramUnitReport extends BaseCaseAssignmentReport { 
	private List programUnits;

	private String[] selectedProgramUnits;

	private List selectedProgramUnitsNames = new ArrayList();

	/**
	 * @return Returns the programUnits.
	 */
	public List getProgramUnits() {
		return programUnits;
	}
	/**
	 * @return Returns the selectedProgramUnits.
	 */
	public String[] getSelectedProgramUnits() {
		return selectedProgramUnits;
	}
	/**
	 * @return Returns the selectedProgramUnitsNames.
	 */
	public List getSelectedProgramUnitsNames() {
		if ( selectedProgramUnits != null ) {
			selectedProgramUnitsNames = new ArrayList();
			for ( int i = 0; i < this.selectedProgramUnits.length; i++ ) {
				String selectedProgramUnit = selectedProgramUnits[i];
				for ( Iterator iterator = programUnits.iterator(); iterator.hasNext(); ) {
					OrganizationResponseEvent programUnit = ( OrganizationResponseEvent ) iterator.next();	
					if (programUnit.getOrganizationId().equals( selectedProgramUnit )) {
						selectedProgramUnitsNames.add( programUnit.getDescription() );
					}
				}
			}			
		}
		return selectedProgramUnitsNames;
	}
		/**
	 * @param programUnits The programUnits to set.
	 */
	public void setProgramUnits(List programUnits) {
		this.programUnits = programUnits;
	}
	/**
	 * @param selectedProgramUnits The selectedProgramUnits to set.
	 */
	public void setSelectedProgramUnits(String[] selectedProgramUnits) {
		this.selectedProgramUnits = selectedProgramUnits;
	}
	/**
	 * @param selectedProgramUnitsNames The selectedProgramUnitsNames to set.
	 */
	public void setSelectedProgramUnitsNames(List selectedProgramUnitsNames) {
		this.selectedProgramUnitsNames = selectedProgramUnitsNames;
	}
}
