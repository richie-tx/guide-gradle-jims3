// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\VacateStaffPositionCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pd.security.PDSecurityHelper;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentBuilder;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.cscdstaffposition.VacateStaffPositionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class VacateStaffPositionCommand implements ICommand {
	private final String NO_OFFICER_ASSIGNED_MSG = "No officer assigned to supervisee";
    /**
     * @roseuid 460BFAA3026E
     */
    public VacateStaffPositionCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54803C9
     */
    public void execute(IEvent event) {
        VacateStaffPositionEvent reqEvent = (VacateStaffPositionEvent) event;
        CSCDStaffPositionHelper.vacateStaffPosition(reqEvent);
        /* Moved the following code to CSCDStaffPositionHelper.vacateStaffPosition()
         * if (staffPosition.getHasCaseload()){
        	String agencyId = PDSecurityHelper.getUserAgencyId();
        	List caseList = CSCDStaffPositionHelper.getStaffPositionCaseload(staffPosition);
        	Map spnMap = new HashMap();
        	CaseAssignment caseAssignment = null;
        	String defendantId = null;
        	CaseAssignmentTO caseAssignmentTO = null;
        	
        	for (int i = 0; i < caseList.size(); i++) {
        		caseAssignment = (CaseAssignment)caseList.get(i);
                CaseAssignmentBuilder builder = new CaseAssignmentBuilder(caseAssignment);
                builder.build();
                caseAssignmentTO = (CaseAssignmentTO) builder.getResult();
                //Record acknowledgement data
                CSCDStaffPositionHelper.updateCaseAssignment(caseAssignmentTO, staffPosition, reqEvent.getEffectiveDate());

        		defendantId = (String) spnMap.get(caseAssignment.getDefendantId());
        		if (defendantId == null){
        			//Create one casenote for each defendant supervised by position being vacated.
        			spnMap.put(caseAssignment.getDefendantId(), caseAssignment.getDefendantId());
        			CSCDStaffPositionHelper.createCasenote(agencyId, caseAssignment.getDefendantId(), NO_OFFICER_ASSIGNED_MSG);
        		}
        	}
        }*/
    }
    
    /**
     * @param event
     * @roseuid 460BD54803CB
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD54803D8
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA3028E
     */
    public void update(Object updateObject) {

    }
}
