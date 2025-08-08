// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionstaff\\cscdstaffposition\\transactions\\GetStaffPositionHistoryCommand.java

package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.GetStaffPositionHistoryEvent;
import messaging.cscdstaffposition.StaffPositionReportingEvent;
import messaging.cscdstaffposition.reply.StaffPositionHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.PDCodeTableConstants;
import naming.SupervisionStaffConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistoryView;

public class GetStaffPositionHistoryCommand implements ICommand {
    private static String BLANK = "";
    /**
     * @roseuid 460BFAA3004C
     */
    public GetStaffPositionHistoryCommand() {
    }

    /**
     * @param event
     * @roseuid 460BD5480030
     */
    public void execute(IEvent event) {
        GetStaffPositionHistoryEvent histEvent = (GetStaffPositionHistoryEvent) event;

        if (SupervisionStaffConstants.REPORT_TYPE_RETIRE.equals(histEvent.getReportType())) {
            this.processRetiredPositionsRpt(histEvent);
        } else if (SupervisionStaffConstants.REPORT_TYPE_PU.equals(histEvent.getReportType())) {
            this.processProgramUnitRpt(histEvent);
        } else if (SupervisionStaffConstants.REPORT_TYPE_POSITION.equals(histEvent.getReportType())) {
            this.processStaffPositionRpt(histEvent);
        } else if (SupervisionStaffConstants.REPORT_TYPE_STAFF.equals(histEvent.getReportType())) {
            this.processStaffHistoryRpt(histEvent);
        }
    }

    /**
     * @param hist
     * @return
     */
    private StaffPositionHistoryResponseEvent getHistoryResponseEvent(StaffPositionOrganizationHistoryView hist, Map nameMap, Map codeMap) {
        StaffPositionHistoryResponseEvent response = new StaffPositionHistoryResponseEvent();
        response.setCjadNum(hist.getCjadNum());
        response.setCstsOfficerTypeId(hist.getCstsOfficerTypeId());
        if (hist.getCstsOfficerTypeId() != null){
        	response.setOfficerTypeDesc(hist.getOfficerTypeDesc());
       }
        response.setEffectiveDate(hist.getEffectiveDate());
        response.setJobTitleCode(hist.getJobTitleCode());
        response.setJobTitleDesc(hist.getJobTitleDesc());
        response.setJobTitleId(hist.getJobTitleCode());
        response.setPositionName(hist.getPositionName());
        response.setProbationOfficerInd(hist.getProbationOfficerInd());
        response.setProgramUnitId(hist.getProgramUnitId());
        response.setUpdateDate(hist.getPositionChangeDate());
        response.setUpdateUserId(hist.getPositionChangeUserId());
        
        if (hist.getPositionChangeUserId() != null && !hist.getPositionChangeUserId().equalsIgnoreCase(BLANK)){
            if (nameMap.get(hist.getPositionChangeUserId()) == null){
                nameMap.put(hist.getPositionChangeUserId(), CSCDStaffPositionHelper.getUserName(hist.getPositionChangeUserId()));
            }
            response.setUpdateUserName((Name) nameMap.get(hist.getPositionChangeUserId()));
        }
        if (hist.getUserProfileId() != null && !hist.getUserProfileId().equals(BLANK)){
            if (nameMap.get(hist.getUserProfileId()) == null){
                nameMap.put(hist.getUserProfileId(), CSCDStaffPositionHelper.getUserName(hist.getUserProfileId()));
            }
            response.setAssignedName((Name) nameMap.get(hist.getUserProfileId()));            
        }
        response.setAssignedLogonId(hist.getUserProfileId());
        response.setStaffPositionId(hist.getPositionId());
        response.setProgramUnitId(hist.getProgramUnitId());
        response.setPositionStatusDesc(hist.getPositionStatusDesc());
        response.setPositionStatusId(hist.getPositionStatusCode());
        response.setPositionTypeId(hist.getPositionTypeCode());
        response.setPositionTypeDesc(hist.getPositionTypeDesc());
        
        if (hist.getProgramSectionId() != null && !hist.getProgramSectionId().equals("0")){
            response.setOrganizationId(hist.getProgramSectionId());
        } else if (hist.getProgramUnitId() != null && !hist.getProgramUnitId().equals("0")){
            response.setOrganizationId(hist.getProgramUnitId());
        } else {
            response.setOrganizationId(hist.getOrganizationId());
        }
        return response;
    }

    /**
     * @param event
     * @roseuid 460BD5480032
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 460BD548003E
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param iter
     */
    private void postHistoryResponseEvents(Iterator iter) {
        StaffPositionOrganizationHistoryView hist = null;
        StaffPositionHistoryResponseEvent response = null;
        HashMap nameMap = new HashMap();
        if (iter != null && iter.hasNext()){
        	Map codeMap = new HashMap();
            while (iter.hasNext()){
                hist = (StaffPositionOrganizationHistoryView) iter.next();
                if (hist.getUserProfileId() != null && hist.getUserProfileId().equals(BLANK) && nameMap.get(hist.getUserProfileId()) == null){
                    nameMap.put(hist.getUserProfileId(), null);
                }
                if (hist.getPositionChangeUserId() != null && !hist.getPositionChangeUserId().equals(BLANK) && nameMap.get(hist.getPositionChangeUserId()) == null){
                    nameMap.put(hist.getPositionChangeUserId(), null);
                }
                response = this.getHistoryResponseEvent(hist, nameMap, codeMap);
                MessageUtil.postReply(response);
            }
        }
    }


    /**
     * @param histEvent
     */
    private void processProgramUnitRpt(GetStaffPositionHistoryEvent histEvent) {
        StaffPositionReportingEvent reportEvent = new StaffPositionReportingEvent();
        reportEvent.setAgencyId(histEvent.getAgencyId());
        reportEvent.setBeginDate(histEvent.getBeginDate());
        reportEvent.setEndDate(histEvent.getEndDate());
        reportEvent.setPositionStatusId(histEvent.getPositionStatusId());
        reportEvent.setProgramUnitId(histEvent.getSearchkeyId());
        
        Iterator iter = StaffPositionOrganizationHistoryView.findAll(reportEvent);

        this.postHistoryResponseEvents(iter);
    }

    /**
     * @param histEvent
     */
    private void processRetiredPositionsRpt(GetStaffPositionHistoryEvent histEvent) {
        GetCSCDSupervisionStaffEvent getRetiredStaffEvent = new GetCSCDSupervisionStaffEvent();
        getRetiredStaffEvent.setAgencyId(histEvent.getAgencyId());
        
        SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(histEvent.getAgencyId(), PDCodeTableConstants.STAFF_STATUS, PDCodeTableConstants.STAFF_STATUS_RETIRED);
        if (aCode != null){
            getRetiredStaffEvent.setStatusId(aCode.getOID());
        }

        Iterator iter = CSCDOrganizationStaffPosition.findAll(getRetiredStaffEvent);
        Map nameMap = new HashMap();
        if (iter != null && iter.hasNext()){
            StaffPositionHistoryResponseEvent response = null;
            CSCDOrganizationStaffPosition position = null;
            while (iter.hasNext()){
                position = (CSCDOrganizationStaffPosition) iter.next();
                response = new StaffPositionHistoryResponseEvent();
                response.setRetirementDate(position.getRetirementDate());
                
                response.setStaffPositionId(position.getStaffPositionId());
                response.setAssignedLogonId(position.getUserProfileId());
                if (position.getUserProfileId() != null && !position.getUserProfileId().equals(BLANK) && nameMap.get(position.getUserProfileId()) == null){
                    nameMap.put(position.getUserProfileId(), CSCDStaffPositionHelper.getUserName(position.getUserProfileId()));
                }
                //Set organizationId as lowest level in hierarchy
                if (position.getProgramSectionId() != null && !position.getProgramSectionId().equals("0")){
                    response.setOrganizationId(position.getProgramSectionId());
                } else if (position.getProgramUnitId() != null && !position.getProgramUnitId().equals("0")){
                    response.setOrganizationId(position.getProgramUnitId());
                } else {
                    response.setOrganizationId(position.getDivisionId());
                }
                response.setAssignedName((Name) nameMap.get(position.getUserProfileId()));
                response.setCjadNum(position.getCjadNum());
                response.setCstsOfficerTypeId(position.getCstsOfficerTypeId());
                if (position.getCstsOfficerTypeId() != null){
                	response.setOfficerTypeDesc(position.getOfficerTypeDesc());
                }
                response.setJobTitleDesc(position.getJobTitleDesc());
                response.setJobTitleCode(position.getJobTitleCode());
                response.setPositionName(position.getPositionName());
                response.setPositionTypeId(position.getPositionTypeCode());
                response.setPositionTypeDesc(position.getPositionTypeDesc());
                response.setPositionStatusDesc(position.getPositionStatusDesc());
                response.setPositionStatusId(position.getPositionStatusCode());
                response.setProbationOfficerInd(position.getProbationOfficerInd());
                response.setProgramUnitId(position.getProgramUnitId());
                response.setUpdateDate(position.getUpdateDate());
                response.setUpdateUserId(position.getUpdateUser());
                if (position.getUpdateUser() != null && !position.getUpdateUser().equals(BLANK)) {
                    if (nameMap.get(position.getUpdateUser()) == null){
                        nameMap.put(position.getUpdateUser(), CSCDStaffPositionHelper.getUserName(position.getUpdateUser()));
                    }
                    response.setUpdateUserName((Name) nameMap.get(position.getUpdateUser()));
                } else if (position.getCreateUser() != null && !position.getCreateUser().equals(BLANK)){
                    if (nameMap.get(position.getCreateUser()) == null){
                        nameMap.put(position.getCreateUser(), CSCDStaffPositionHelper.getUserName(position.getCreateUser()));
                    }
                    response.setUpdateUserName((Name) nameMap.get(position.getCreateUser()));
                    response.setUpdateDate(position.getCreateDate());
                }
                
                MessageUtil.postReply(response);
            }
        }
    }
	/**
     * @param histEvent
     */
    private void processStaffHistoryRpt(GetStaffPositionHistoryEvent histEvent) {
        StaffPositionReportingEvent reportEvent = new StaffPositionReportingEvent();
        reportEvent.setAgencyId(histEvent.getAgencyId());
        reportEvent.setBeginDate(histEvent.getBeginDate());
        reportEvent.setEndDate(histEvent.getEndDate());
        reportEvent.setStaffLogonId(histEvent.getSearchkeyId());
        
        Iterator iter = StaffPositionOrganizationHistoryView.findAll(reportEvent);
        this.postHistoryResponseEvents(iter);    }

    /**
     * @param histEvent
     */
    private void processStaffPositionRpt(GetStaffPositionHistoryEvent histEvent) {
        StaffPositionReportingEvent reportEvent = new StaffPositionReportingEvent();
        reportEvent.setAgencyId(histEvent.getAgencyId());
        reportEvent.setBeginDate(histEvent.getBeginDate());
        reportEvent.setEndDate(histEvent.getEndDate());
        reportEvent.setPositionStatusId(histEvent.getPositionStatusId());
        reportEvent.setStaffPositionId(histEvent.getSearchkeyId());
        
        Iterator iter = StaffPositionOrganizationHistoryView.findAll(reportEvent);
        this.postHistoryResponseEvents(iter);

    }

    /**
     * @param updateObject
     * @roseuid 460BFAA3005B
     */
    public void update(Object updateObject) {

    }
}
