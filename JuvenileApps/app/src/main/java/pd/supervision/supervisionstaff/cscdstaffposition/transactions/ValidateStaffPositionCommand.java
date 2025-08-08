// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\ValidateStaffPositionCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.cscdstaffposition.GetCourtsStaffPositionEvent;
import messaging.cscdstaffposition.GetOrganizationPostionEvent;
import messaging.cscdstaffposition.GetStaffPositionEvent;
import messaging.cscdstaffposition.ValidateStaffPositionEvent;
import messaging.cscdstaffposition.VerifyProbationOfficerIndAndCjadEvent;
import messaging.cscdstaffposition.reply.CourtPreviouslyAssignedEvent;
import messaging.cscdstaffposition.reply.DuplicateCjadNumEvent;
import messaging.cscdstaffposition.reply.DuplicateDivisionManagerEvent;
import messaging.cscdstaffposition.reply.DuplicatePOIEvent;
import messaging.cscdstaffposition.reply.DuplicatePositionNameEvent;
import messaging.cscdstaffposition.reply.NoDivisionManagerEvent;
import messaging.cscdstaffposition.reply.NoSupervisorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.common.util.StringUtil;
import pd.security.PDSecurityHelper;
import pd.supervision.Court;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CourtStaffPosition;

public class ValidateStaffPositionCommand implements ICommand {
 
    /**
     * @roseuid 460BFAA302FB
     */
    public ValidateStaffPositionCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54602FE
     */
    public void execute(IEvent event) {
        ValidateStaffPositionEvent reqEvent = (ValidateStaffPositionEvent) event;
        
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
        reqEvent.setStatusId(aCode.getOID().toString());

        this.validateProbationOfficerInd(reqEvent);
        this.validateCjadNum(reqEvent);
        this.validatePosition(reqEvent);
    }

    /**
     * @param getOrgPositionsEvent
     * @param validateEvent
     * @return
     */
    private boolean hasSupervisor(
            GetOrganizationPostionEvent getOrgPositionsEvent,
            ValidateStaffPositionEvent validateEvent) {
        boolean hasSupervisor = false;
        GetOrganizationPostionEvent reqEvent = new GetOrganizationPostionEvent();
        reqEvent.setAgencyId(validateEvent.getAgencyId());
        reqEvent.setDivisionId(validateEvent.getDivisionId());
        //A Supervisor can supervise people from different program units.
        //reqEvent.setProgramUnitId(validateEvent.getProgramUnitId());
        reqEvent.setStaffPositionId(validateEvent.getStaffPositionId());
        reqEvent.setStatusId(validateEvent.getStatusId());
        reqEvent.setPositionTypes(new ArrayList());
        SupervisionCode aCode = null;
        if (!validateEvent.getPositionTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP)
        		&& !validateEvent.getPositionTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR)){
        	aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(validateEvent.getAgencyId(), 
        			PDCodeTableConstants.STAFF_POSITION_TYPE, PDCodeTableConstants.STAFF_POSITION_TYPE_ASSISTANTSUP);
        	if (aCode != null){
        		reqEvent.addPositionTypeId(aCode.getOID());
        	}
        }
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(validateEvent.getAgencyId(), 
        		PDCodeTableConstants.STAFF_POSITION_TYPE, PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR);
        if (aCode != null){
            reqEvent.addPositionTypeId(aCode.getOID());
        }

        if (reqEvent.getPositionTypes().size() > 0){
        	CSCDOrganizationStaffPosition orgStaffPos = null;
            List aList = CollectionUtil.iteratorToList(CSCDOrganizationStaffPosition.findAll(reqEvent));
            for (int i = 0; i < aList.size(); i++)
			{
            	orgStaffPos = (CSCDOrganizationStaffPosition) aList.get(i);
            	if (orgStaffPos.getStaffPositionId().equals(validateEvent.getStaffPositionId())){
            		continue;
            	} else {
            		hasSupervisor = true;
            		break;
            	}
			}
        }

        return hasSupervisor;
    }

    /**
     * @param positionTypeId
     * @return
     */
    private boolean isDivisionManagerPositionRequest(String positionTypeId) {
        boolean isDivisionManager = false;
        if (PDCodeTableConstants.STAFF_POSITION_TYPE_DIVISIONMGR
                .equals(positionTypeId)) {
            isDivisionManager = true;
        }
        return isDivisionManager;
    }

    /**
     * @param event
     * @roseuid 460BD5460300
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD546030D
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA3030B
     */
    public void update(Object updateObject) {

    }

    /**
     * @param reqEvent
     */
    private void validateCjadNum(ValidateStaffPositionEvent reqEvent) {
        if (reqEvent.getCjadNum() != null
                && !reqEvent.getCjadNum().trim().equals(PDConstants.BLANK)) {
            VerifyProbationOfficerIndAndCjadEvent verifyEvent = new VerifyProbationOfficerIndAndCjadEvent();
            verifyEvent.setAgencyId(reqEvent.getAgencyId());
            verifyEvent.setCjadNum(reqEvent.getCjadNum());
            verifyEvent.setStatusId(reqEvent.getStatusId());
            verifyEvent.setStaffPositionId(reqEvent.getStaffPositionId());
            Iterator iter = CSCDOrganizationStaffPosition.findAll(verifyEvent);
            if (iter != null && iter.hasNext()) {
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(
                        new DuplicateCjadNumEvent());
            }

        }

    }

    /**
     * @param reqEvent
     */
    private void validatePosition(ValidateStaffPositionEvent validateEvent) {
        GetStaffPositionEvent getDivMgrEvent = new GetStaffPositionEvent();
        GetOrganizationPostionEvent getOrgPositionsEvent = new GetOrganizationPostionEvent();
        if (validateEvent.getAgencyId() == null
                || validateEvent.getAgencyId().equals(PDConstants.BLANK)) {
            String agencyId = PDSecurityHelper.getUserAgencyId();
            getOrgPositionsEvent.setAgencyId(agencyId);
            validateEvent.setAgencyId(agencyId);
            getDivMgrEvent.setAgencyId(agencyId);
        } else {
            getOrgPositionsEvent.setAgencyId(validateEvent.getAgencyId());
            getDivMgrEvent.setAgencyId(validateEvent.getAgencyId());
        }

        getDivMgrEvent.setOrganizationId(validateEvent.getDivisionId());
        getDivMgrEvent.setStatusId(validateEvent.getStatusId());

        CSCDStaffPosition divisionMgr = CSCDStaffPositionHelper.getDivisionManager(getDivMgrEvent);

        if (this.isDivisionManagerPositionRequest(validateEvent
                .getPositionTypeId())) {
            if (divisionMgr != null 
                    && (validateEvent.getStaffPositionId() == null 
                            || !validateEvent.getStaffPositionId().equals(divisionMgr.getOID()))) {
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(
                        new DuplicateDivisionManagerEvent());
            } else {
            }
        } else if (divisionMgr == null) {
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(
                    new NoDivisionManagerEvent());
        } else if (PDCodeTableConstants.STAFF_POSITION_TYPE_SUPERVISOR
                        .equals(validateEvent.getPositionTypeId())) {
            //Supervisors report to division manager which
            // was validated previously.
        } else if (!this.hasSupervisor(getOrgPositionsEvent, validateEvent)) {
        	EventManager.getSharedInstance(EventManager.REPLY).postEvent(
        			new NoSupervisorEvent());
        }
        if (PDCodeTableConstants.STAFF_JOB_TITLE_CLO.equals(validateEvent.getJobTitleId())){
            this.validateCLO(validateEvent);
        }
        this.validatePositionName(validateEvent);
    }
    private void validatePositionName(ValidateStaffPositionEvent reqEvent){
    	
    	if (reqEvent.isHasCaseLoad() && StringUtil.isEmpty(reqEvent.getPositionName()))
    	{
    			//skip remaining position name if caseload position and caseload is empty
    		return;
    	}
    	
    	reqEvent.getPositionName().toUpperCase();
    	Iterator iter = CSCDOrganizationStaffPosition.findAll(reqEvent);
    	CSCDOrganizationStaffPosition staffPos = null;
    	if (iter != null && iter.hasNext()){
    		while (iter.hasNext()){
    			staffPos = (CSCDOrganizationStaffPosition) iter.next();
    			if (reqEvent.getStaffPositionId() == null || 
    					(reqEvent.getStaffPositionId() != null && !staffPos.getStaffPositionId().equals(reqEvent.getStaffPositionId()))){
    				EventManager.getSharedInstance(EventManager.REPLY).postEvent(
    						new DuplicatePositionNameEvent());
    				break;
    			}
    		}
    	}
    }
    /**
     * @param validateEvent
     */
    private void validateCLO(ValidateStaffPositionEvent validateEvent) {
        
        GetCourtsStaffPositionEvent courtsStaffEvent = new GetCourtsStaffPositionEvent();
        
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(validateEvent.getAgencyId(), PDCodeTableConstants.STAFF_JOB_TITLE, PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
        courtsStaffEvent.setJobTitleId((String) aCode.getOID());
        
        aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(validateEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
        courtsStaffEvent.setStatusId((String) aCode.getOID());
        courtsStaffEvent.setAgencyId(validateEvent.getAgencyId());
        courtsStaffEvent.setCourts(validateEvent.getCourts());
        courtsStaffEvent.setStaffPositionId(validateEvent.getStaffPositionId());
        
        Iterator iter = CourtStaffPosition.findAll(courtsStaffEvent);    

		if (iter != null && iter.hasNext()){
		    CourtStaffPosition csp = null;
		    CourtPreviouslyAssignedEvent respEvent = null;
		    Map courtsMap = new HashMap();
		    Court court = null;
		    
		    while (iter.hasNext()){
		        csp = (CourtStaffPosition) iter.next();
		        respEvent = new CourtPreviouslyAssignedEvent();
		        respEvent.setCourtId(csp.getCourtId());
		        if (courtsMap.get(csp.getCourtId()) == null){
		            court = csp.getCourt();
		            courtsMap.put(csp.getCourtId(), court);
		            respEvent.setCourtNum(court.getCourtNumber());
		            EventManager.getSharedInstance(EventManager.REPLY).postEvent(respEvent);
		        }
		    }
		}
        
    }

    /**
     * @param reqEvent
     */
    private void validateProbationOfficerInd(ValidateStaffPositionEvent reqEvent) {

        if (reqEvent.getProbationOfficerInd() != null
                && !reqEvent.getProbationOfficerInd().trim().equals(PDConstants.BLANK)) {
            VerifyProbationOfficerIndAndCjadEvent verifyEvent = new VerifyProbationOfficerIndAndCjadEvent();
            verifyEvent.setAgencyId(reqEvent.getAgencyId());
            verifyEvent.setProbationOfficerInd(reqEvent
                    .getProbationOfficerInd().toUpperCase());
            verifyEvent.setStaffPositionId(reqEvent.getStaffPositionId());
            SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(reqEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_ACTIVE);
            verifyEvent.setStatusId(aCode.getOID());
            Iterator iter = CSCDOrganizationStaffPosition.findAll(verifyEvent);
            if (iter != null && iter.hasNext()) {
                EventManager.getSharedInstance(EventManager.REPLY).postEvent(
                        new DuplicatePOIEvent());
            } else {
                iter = Organization.findAll(verifyEvent);  
                if (iter != null && iter.hasNext()) {
                    EventManager.getSharedInstance(EventManager.REPLY).postEvent(
                            new DuplicatePOIEvent());
                }
            }
        }
    }
}
