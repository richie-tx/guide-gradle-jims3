// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\UpdateStaffPositionCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Date;

import naming.PDConstants;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.legacycscdstaff.LegacySupervisionStaff;
import messaging.cscdstaffposition.UpdateStaffPositionEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.legacycscdstaff.UpdateLegacyStaffAssignmentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

public class UpdateStaffPositionCommand implements ICommand {
    /**
     * @roseuid 460BFAA301F1
     */
    public UpdateStaffPositionCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54701C6
     */
    public void execute(IEvent event) {
        UpdateStaffPositionEvent updateEvent = (UpdateStaffPositionEvent) event;
        
        CSCDStaffPosition staffPos = null;
        boolean isCreate = false;
        if (updateEvent.getPositionId() != null
                && !updateEvent.getPositionId().trim().equals("")) {
            staffPos = CSCDStaffPosition.update(updateEvent);
        } else {
        	isCreate = true;
            staffPos = CSCDStaffPosition.create(updateEvent);
        }
        
        if (staffPos.getHasCaseload() && staffPos.getProbationOfficerInd() != null 
                && !staffPos.getProbationOfficerInd().equals(PDConstants.BLANK)){
            this.updateLegacyProbationOfficer(staffPos, updateEvent.getCjadNum(), updateEvent.getEffectiveDate(), isCreate);
        }
        
        if (staffPos != null) {
            CSCDSupervisionStaffResponseEvent sre = CSCDStaffPositionHelper.getStaffResponseEvent(staffPos, null);
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(sre);
        }        
    }

    /**
     * @param staffPos
     * @param cjadNum
     */
    private void updateLegacyProbationOfficer(CSCDStaffPosition staffPos, String cjadNum, Date effectiveDate, boolean isCreate) {
        UpdateLegacyStaffAssignmentEvent reqEvent = CSCDStaffPositionHelper.getLegacyStaffUpdateEvent(staffPos, cjadNum, false);
        reqEvent.setEffectiveDate(effectiveDate);
        if (isCreate){
        	LegacySupervisionStaff.create(reqEvent);
        } else {
        	LegacySupervisionStaff.update(reqEvent);
        }
    }
 
    /**
     * @param event
     * @roseuid 460BD54701D4
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD54701D6
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA30201
     */
    public void update(Object updateObject) {

    }
}
