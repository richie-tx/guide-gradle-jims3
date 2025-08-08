// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\AssignStaffPositionCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.PDConstants;

import pd.security.PDSecurityHelper;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile;
import messaging.cscdstaffposition.AssignStaffPositionEvent;
import messaging.cscdstaffposition.GetStaffByUserIdEvent;
import messaging.cscdstaffposition.VacateStaffPositionEvent;
import messaging.cscdstaffposition.VerifyCjadNumEvent;
import messaging.cscdstaffposition.reply.DuplicateCjadNumEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class AssignStaffPositionCommand implements ICommand {

    /**
     * @roseuid 460BFAA20211
     */
    public AssignStaffPositionCommand() {

    }

    /**
     * @param event
     * @roseuid 460BD54A003F
     */
    public void execute(IEvent event) {
        AssignStaffPositionEvent reqEvent = (AssignStaffPositionEvent) event;
        CSCDStaffPosition staffPosition = CSCDStaffPosition.find(reqEvent.getStaffPositionId());
        
        //CJAD is validated earlier in the workflow and is not needed here.
        /* boolean duplicateCjadNum = this.duplicateCjadNum(reqEvent);;
        
        if (duplicateCjadNum){
        	MessageUtil.postReply(new DuplicateCjadNumEvent());
        	return;
        }
        */
        staffPosition.setEffectiveDate(reqEvent.getEffectiveDate());
        
        CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
        //in the case of a position being assigned to someone that holds a position, he already has a staff profile.
        //we can't have a position assigned to more than person.  Vacate the postiion of a person that is
        //moved from one position to another.
        
        this.vacateCurrentPosition(reqEvent.getStaffLogonId(), reqEvent.getEffectiveDate());

        if (staffProfile == null){
            if (reqEvent.getCjadNum() != null && !reqEvent.getCjadNum().equals(PDConstants.BLANK)){
                	staffProfile = new CSCDStaffProfile();
                    staffProfile.setCjadNum(reqEvent.getCjadNum());
                    staffPosition.setStaffProfile(staffProfile);
            } else {
            	//No need to create a staffProfile if there is no CJAD
                }
        } else if (reqEvent.getCjadNum() != null && !reqEvent.getCjadNum().equals(PDConstants.BLANK)){
                staffProfile.setCjadNum(reqEvent.getCjadNum());  
        } else {
        	//Delete staff profile if no cjad on person being assigned position.
            CSCDStaffPositionHelper.deleteStaffProfile(staffProfile);
            staffPosition.setStaffProfileId(null);
        }
        
        staffPosition.setUserProfileId(reqEvent.getStaffLogonId());
        
      //ensure that data is proper state before processing caseload reassignment.
        staffPosition.bind(); 
        if (staffProfile != null){
        	staffProfile.bind();
        }
        
        if (staffPosition.getHasCaseload()){
            List caseList = CSCDStaffPositionHelper.processCaseloadReassignment(staffPosition, reqEvent.getEffectiveDate());
           	CaseAssignment caseAssignment = null;
           	String defendantId = null;
           	String agencyId = PDSecurityHelper.getUserAgencyId();
           	Map spnMap = new HashMap();
           	
           	//Create casenote for each person being supervised by position.
            for (int i = 0; i < caseList.size(); i++) {
        		caseAssignment = (CaseAssignment)caseList.get(i);
        		defendantId = (String) spnMap.get(caseAssignment.getDefendantId());
        		if (defendantId == null){
        			spnMap.put(caseAssignment.getDefendantId(), caseAssignment.getDefendantId());
        			CSCDStaffPositionHelper.createCasenote(agencyId, caseAssignment.getDefendantId(), reqEvent.getNotes());
        		}
        	}
        }
        
        CSCDStaffPosition.createHistory(staffPosition);

        //Create new CRM26 record.
        if (staffPosition.getHasCaseload()){
        	CSCDStaffPositionHelper.createLegacyStaffPosition(staffPosition, reqEvent.getEffectiveDate(), false);
        }
    }

    /**
     * @param staffLogonId
     */
    private void vacateCurrentPosition(String staffLogonId, Date effectiveDate) {
        GetStaffByUserIdEvent getByUserId = new GetStaffByUserIdEvent();
        getByUserId.setAgencyId(PDSecurityHelper.getUserAgencyId());
        getByUserId.setStaffLogonId(staffLogonId);
        
        Iterator iter = CSCDOrganizationStaffPosition.findAll(getByUserId);
        if (iter != null && iter.hasNext()){
            CSCDOrganizationStaffPosition staffPos =  null;
            VacateStaffPositionEvent vacateEvent = null;
            while (iter.hasNext()){
                staffPos = (CSCDOrganizationStaffPosition) iter.next();
                vacateEvent = new VacateStaffPositionEvent();
                vacateEvent.setSupervisionStaffId(staffPos.getStaffPositionId());
                vacateEvent.setEffectiveDate(effectiveDate);
                CSCDStaffPositionHelper.vacateStaffPosition(vacateEvent);
            }
        }
        
    }

    /**
     * @param reqEvent
     * @return
     */
    private boolean duplicateCjadNum(AssignStaffPositionEvent reqEvent) {
        
        VerifyCjadNumEvent cjadEvent = new VerifyCjadNumEvent();
        cjadEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
        cjadEvent.setCjadNum(reqEvent.getCjadNum());
        cjadEvent.setStaffPositionId(reqEvent.getStaffPositionId());
        cjadEvent.setUserProfileId(reqEvent.getStaffLogonId());

        return CSCDStaffPositionHelper.verifyCjadNum(cjadEvent);
    }

    /**
     * @param event
     * @roseuid 460BD54A0041
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD54A004E
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 460BD54A0050
     */
    public void update(Object anObject) {

    }
}
