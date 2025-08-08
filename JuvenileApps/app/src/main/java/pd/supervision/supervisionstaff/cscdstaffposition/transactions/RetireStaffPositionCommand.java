// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\RetireStaffPositionCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Date;

import naming.PDCodeTableConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import messaging.cscdstaffposition.RetireStaffPositionEvent;
import messaging.cscdstaffposition.VacateStaffPositionEvent;
import messaging.cscdstaffposition.reply.ActiveCaseloadEvent;
import messaging.cscdstaffposition.reply.ActiveSubordinatesEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

public class RetireStaffPositionCommand implements ICommand {

    /**
     * @roseuid 460BFAA30165
     */
    public RetireStaffPositionCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54900CB
     */
    public void execute(IEvent event) {
        RetireStaffPositionEvent reqEvent = (RetireStaffPositionEvent) event;
        CSCDStaffPosition staffPosition = CSCDStaffPosition.find(reqEvent.getSupervisionStaffId());
        
        boolean positionCanBeRetired = true;
        if (staffPosition != null) {
            if (staffPosition.hasActiveCaseload()) {
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ActiveCaseloadEvent());
                positionCanBeRetired = false;
            }
            if (staffPosition.hasActiveSubordinates()){
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ActiveSubordinatesEvent());
                positionCanBeRetired = false;
            } else if (positionCanBeRetired){
            	
            	//persist retirement date before position is vacated and retirement date is lost
                staffPosition.setRetirementDate(reqEvent.getRetirementDate());
                staffPosition.bind();
                            	
                SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDSecurityHelper.getUserAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_RETIRED);
                if (aCode != null){
                	staffPosition = this.retirePosition(staffPosition.getOID(), reqEvent.getRetirementDate());
                    staffPosition.setStatusId(aCode.getOID());
                    CSCDStaffPosition.createHistory(staffPosition);
                } else {
                    EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ErrorResponseEvent());
                }
            }
        } else {
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ErrorResponseEvent());
        }
    }

    private CSCDStaffPosition retirePosition(String staffPositionId, Date retirementDate)
	{
    	VacateStaffPositionEvent vacateEvent = new VacateStaffPositionEvent();
        vacateEvent.setSupervisionStaffId(staffPositionId);
        vacateEvent.setEffectiveDate(retirementDate);
        CSCDStaffPosition staffPosition = CSCDStaffPositionHelper.vacateStaffPosition(vacateEvent);
        return staffPosition;
	}

	/**
     * @param event
     * @roseuid 460BD54900CD
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD54900CF
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA30174
     */
    public void update(Object updateObject) {

    }
}
