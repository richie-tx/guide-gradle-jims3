/*
 * Created on Jun 18, 2007
 *
 */
package pd.supervision.administercaseload;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.administercaseload.GetCaseloadByDefendantEvent;
import messaging.administercaseload.GetInOutActivityEvent;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.UpdateCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignInOutResponseEvent;
import messaging.administercaseload.reply.LightSupervisorResponseEvent;
import messaging.cscdstaffposition.GetStaffPositionsByPositionTypeCDEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.legacyupdates.UpdateAssignmentDataEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionConstants;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.cscdcalendar.CSEventsReportPDHelper;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffProfile;

/**
 * @author dgibler
 *
 */
public class CaseAssignmentHelper {

	/**
     * @param event
     */
	public static ICaseAssignment updateCaseAssignment(UpdateCaseAssignmentEvent event){
        ICaseAssignment assignmentBean = event.getCaseAssignment();
        String caseAssignmentId = assignmentBean.getCaseAssignmentId();
        CaseAssignment caseAssignment;
        if (caseAssignmentId == null) {
            caseAssignment = new CaseAssignment();
            //set the order on the assignment during the creation, after it does not need to be set
            //SupervisionOrderId and AcknowledgeStatusId are non-nullable columns in the database
            //so we need to set them before calling bind().
            caseAssignment.setSupervisionOrderId(assignmentBean.getSupervisionOrderId());
            caseAssignment.setAcknowledgeStatusId(assignmentBean.getAcknowledgeStatusCode());
            caseAssignment.bind();
            assignmentBean.setCaseAssignmentId(caseAssignment.getOID());
            
        } else {
            caseAssignment = CaseAssignment.find(caseAssignmentId);
        }
      
        caseAssignment.update(assignmentBean);      
       	createLegacyRecords(caseAssignment); 
       	

        
       	return assignmentBean;
    }
    
    /**
	 * When a case is assigned to a program unit, JZP and FAS1CASE record types are to be updated. Similarly 
	 * when a case is assigned to an officer, T24, JZP and FAS1CASE record types are to be updated. When legacy
	 * updater creates a T24 type of record, it transparently creates other two record types (JZP & FAS1CASE) also. 
	 * However, during assignment to an officer, if the officer is not having a CJAD number, T24 will not be
	 * created only JZP and FAS1CASE record types will be created.
	 */
	public static void createLegacyRecords(CaseAssignment caseAssignment) {
		if (caseAssignment.getCaseState().equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)) {
			legacyUpdateForProgramUnitAssignment(caseAssignment, null);
		} else if (caseAssignment.getCaseState().equals(CaseloadConstants.OFFICER_ASSIGNED)) {
			if (caseAssignment.getAcknowledgeUserId() != null){
				legacyUpdateForOfficerAssignment(caseAssignment, null);
			}
		} else if (caseAssignment.getCaseState().equals(CaseloadConstants.CASE_CLOSED)){
			legacyUpdateForOfficerAssignment(caseAssignment, null);
		}
	}
	public static void createLegacyRecordsForReinstatement(CaseAssignment caseAssignment, Date reinstatementDate) {
		if (caseAssignment.getCaseState().equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)) {
			legacyUpdateForProgramUnitAssignment(caseAssignment, reinstatementDate);
		} else if (caseAssignment.getCaseState().equals(CaseloadConstants.OFFICER_ASSIGNED)) {
			if (caseAssignment.getAcknowledgeUserId() != null){
				legacyUpdateForOfficerAssignment(caseAssignment, reinstatementDate);
			}
		}  else if (caseAssignment.getCaseState().equals(CaseloadConstants.SUPERVISOR_ALLOCATED)){
			legacyUpdateForProgramUnitAssignment(caseAssignment, reinstatementDate);
		}
	}

    private static void legacyUpdateForProgramUnitAssignment(CaseAssignment caseAssignment, Date reinstatementDate) {

    	ILegacyUpdates icsts = new LegacyUpdatesImpl();
    	UpdateAssignmentDataEvent reqEvent = new UpdateAssignmentDataEvent();    	    	
    	String recordType = "JZP";        	
    	reqEvent.setJims2SourceId(caseAssignment.getOID());
    	reqEvent.setRecType(recordType);
    	//reqEvent.setLogonId(caseAssignment.getAcknowledgeUserId());
    	String logonId = PDSecurityHelper.getLogonId();
    	if (logonId != null){
    		reqEvent.setLogonId(logonId);
    	} else {
    		reqEvent.setLogonId(PDConstants.BLANK);
    	}
    	reqEvent.setSpn(caseAssignment.getDefendantId());        	
    	reqEvent.setCriminalCaseId(caseAssignment.getCriminalCaseId());
		Organization organization = Organization.find(caseAssignment.getProgramUnitId());
    	reqEvent.setProbationOfficerInd(organization.getProbationOfficerInd());//POI from organization    
    	if (reinstatementDate != null){
    		reqEvent.setTransactionDate(reinstatementDate);
    	} else if (caseAssignment.getProgramUnitAssignDate() != null){
    		reqEvent.setTransactionDate(caseAssignment.getProgramUnitAssignDate());
    	} else {
    		reqEvent.setTransactionDate(DateUtil.getCurrentDate());
    	}
    	
    	try {
    		icsts.create(reqEvent, recordType);
		} catch (Exception e) {		
			throw new RuntimeException("Failed to create JZP type of record for program unit assignment.", e);
		}
    	if (reinstatementDate == null){//not reinstatement
    		recordType = "CAS1";        	
    		reqEvent.setRecType(recordType);
    		reqEvent.setTransactionDate(null);//do not need this field for FAS1CASE
    		try {
    			icsts.create(reqEvent, recordType);
    		} catch (Exception e) {		
				throw new RuntimeException("Failed to create FAS1CASE type of record for program unit assignment.", e);
    		}    
    	}
    }
    
    private static void legacyUpdateForOfficerAssignment(CaseAssignment caseAssignment, Date reinstatementDate) {
    	ILegacyUpdates icsts = new LegacyUpdatesImpl();
    	UpdateAssignmentDataEvent reqEvent = new UpdateAssignmentDataEvent();
    	String recordType = "JZP";        	
    	reqEvent.setJims2SourceId(caseAssignment.getOID());
    	reqEvent.setRecType(recordType);
    	//reqEvent.setLogonId(caseAssignment.getAcknowledgeUserId());
    	String logonId = PDSecurityHelper.getLogonId();
    	if (logonId != null){
    		reqEvent.setLogonId(logonId);
    	} else {
    		reqEvent.setLogonId(PDConstants.BLANK);
    	}
    	reqEvent.setSpn(caseAssignment.getDefendantId());        	
    	reqEvent.setCriminalCaseId(caseAssignment.getCriminalCaseId());	    	
		CSCDStaffPosition staffPosition = caseAssignment.getAssignedStaffPosition();

    	if (caseAssignment.getCaseState().equals(CaseloadConstants.CASE_CLOSED)){
    		reqEvent.setProbationOfficerInd("**");
    		reqEvent.setTransactionDate(caseAssignment.getTerminationDate());
    	} else {
    		reqEvent.setProbationOfficerInd(staffPosition.getProbationOfficerInd());//POI from staff position 
    		if (reinstatementDate != null){
    			reqEvent.setTransactionDate(reinstatementDate);
    		} else if (caseAssignment.getOfficerAssignDate() != null){
    			reqEvent.setTransactionDate(caseAssignment.getOfficerAssignDate());
    		} else {
    			reqEvent.setTransactionDate(DateUtil.getCurrentDate());
    		}
    	}
    	
    	try {
			icsts.create(reqEvent, recordType);
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException("Failed to create JZP type of record for officer assignment.", e);
		}

		if (!caseAssignment.getCaseState().equals(CaseloadConstants.CASE_CLOSED)){
			boolean createT24 = false;
			CSCDStaffProfile staffProfile = staffPosition.getStaffProfile();
			if (staffProfile != null) {
				String cjadNumber = staffProfile.getCjadNum();
				if (cjadNumber != null && cjadNumber.trim().length() > 0) {
					createT24 = true;
				}
			}
			if (createT24) {
				if (CSCDStaffPositionHelper.isCLO(staffPosition.getJobTitleId())){
					if (!createT24ForCLO(caseAssignment,staffPosition,reqEvent)){
						createT24 = false;
					}
				}
			}
			if (createT24) {
				recordType = "T24";	
				reqEvent.setRecType(recordType);
				reqEvent.setCjadNum(staffProfile.getCjadNum());
				if (reinstatementDate != null){
					reqEvent.setAssignmentDate(reinstatementDate);
				} else {
					reqEvent.setAssignmentDate(caseAssignment.getOfficerAssignDate());
				}
				try {
					icsts.create(reqEvent, recordType);
				} catch (Exception e) {		
					e.printStackTrace();
					throw new RuntimeException("Failed to create T24/FAS1CASE types of records for officer assignment.", e);
				}   
			}
		} 
	    recordType = "CAS1";        	
	    reqEvent.setRecType(recordType);
	    reqEvent.setTransactionDate(null);//do not need this field for FAS1CASE
	    try {
			icsts.create(reqEvent, recordType);
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException("Failed to create FAS1CASE type of record for officer assignment.", e);
		}    	    	    					
    }

	private static boolean createT24ForCLO(CaseAssignment caseBeingAssigned,CSCDStaffPosition officer,UpdateAssignmentDataEvent reqEvent) {
		List activeCases = getActiveCaseAssignments(caseBeingAssigned.getDefendantId());
		
		if (activeCases.size() == 1){//The case being assigned is the only active case for spn.
			return true;
		}	 

		CaseAssignmentOrder thisCase = null;
		boolean createT24 = false;
		int thisCLOCaseCount = 0;
		int otherCLOCaseCount = 0;
		int otherCSOCaseCount = 0;
		boolean caseBeingAssignedIsFelony = false;
		String felonyCLOCaseNum = null;
		CSCDStaffPosition felonyCLOStaffPos = null;
		Date felonyCLOAssignmentDate = null;
		
		for (int i = 0; i < activeCases.size(); i++) {
		
			thisCase = (CaseAssignmentOrder) activeCases.get(i);

			if (thisCase.getCriminalCaseId().equals(caseBeingAssigned.getCriminalCaseId())){
				if (isFelonyCase(thisCase.getCriminalCaseId(), thisCase.getCourtId())){
					caseBeingAssignedIsFelony = true;
				}
			} else  {
				if (thisCase.getAssignedStaffPositionId() != null 
						&& thisCase.getAssignedStaffPositionId().equals(caseBeingAssigned.getAssignedStaffPositionId())){
					//This case is already assigned to the CLO of case being assigned.
					thisCLOCaseCount++;
				} else if (thisCase.getAssignedStaffPositionId() != null) {
					CSCDStaffPosition thisOfficer = CSCDStaffPosition.find(thisCase.getAssignedStaffPositionId());
					if (CSCDStaffPositionHelper.isCLO(thisOfficer.getJobTitleId())
							&& thisOfficer.getStaffProfileId() !=  null){
						if (isFelonyCase(thisCase.getCriminalCaseId(), thisCase.getCourtId())){
							if (felonyCLOAssignmentDate != null 
									&& thisCase.getOfficerAssignDate() != null
									&& thisCase.getOfficerAssignDate().after(felonyCLOAssignmentDate)){
								felonyCLOStaffPos = thisOfficer;
								felonyCLOAssignmentDate = thisCase.getOfficerAssignDate();
								felonyCLOCaseNum = thisCase.getCriminalCaseId();
							} else if (felonyCLOAssignmentDate == null){
								felonyCLOStaffPos = thisOfficer;
								felonyCLOAssignmentDate = thisCase.getOfficerAssignDate();
								felonyCLOCaseNum = thisCase.getCriminalCaseId();
							}
						}
						otherCLOCaseCount++;
					} else {
						otherCSOCaseCount++;
					}
				}
			}
		}
		int otherActiveCasesCount = activeCases.size()-1;
		
		if (otherCSOCaseCount > 0){//There are other cases assigned to CSO(s).
			createT24 = false;
		} else if (thisCLOCaseCount == otherActiveCasesCount){
			//All cases are with CLO of new case and this is the last case to be assigned.
			createT24 = true;
		} else if (caseBeingAssignedIsFelony){
			createT24 = true;
		} else if (felonyCLOStaffPos != null
				&& felonyCLOAssignmentDate != null){//Create T24 for officer assigned to felony case.
			CSCDStaffProfile staffProfile = felonyCLOStaffPos.getStaffProfile();
			if (staffProfile != null) {
				String cjadNumber = staffProfile.getCjadNum();
				if (cjadNumber != null && cjadNumber.trim().length() > 0) {
					createT24 = false;
					reqEvent.setRecType("T24");
					reqEvent.setCjadNum(staffProfile.getCjadNum());
					reqEvent.setAssignmentDate(felonyCLOAssignmentDate);
					reqEvent.setProbationOfficerInd(felonyCLOStaffPos.getProbationOfficerInd());
					reqEvent.setCriminalCaseId(felonyCLOCaseNum);
					try {
						ILegacyUpdates icsts = new LegacyUpdatesImpl();
						icsts.create(reqEvent, reqEvent.getRecType());
					} catch (Exception e) {		
						e.printStackTrace();
						throw new RuntimeException("Failed to create T24/FAS1CASE types of records for officer assignment.", e);
					}   
				}
			}
		}
		return createT24;
	}

	public static boolean isFelonyCase(String criminalCaseId, String courtId) {
		
		boolean isFelony = false;
		
		if (criminalCaseId != null && criminalCaseId.length() == 15){
			if (criminalCaseId.startsWith("003")){
				isFelony = true;
			} else if (criminalCaseId.startsWith("010")){
				if (courtId != null && courtId.endsWith("F")){
					isFelony = true;
				}
			}
		}
		
		return isFelony;
	}

	public static boolean isMisdemeanorCase(String criminalCaseId, String courtId) {
		boolean isMisdemeanor = false;
		if (criminalCaseId != null && criminalCaseId.length() == 15){
			if (criminalCaseId.startsWith("002")){
				isMisdemeanor = true;
			} else if (criminalCaseId.startsWith("010")){
				if (courtId != null && courtId.endsWith("M")){
					isMisdemeanor = true;
				}
			}
		}
		return isMisdemeanor;
	}

	private static List getActiveCaseAssignments(String defendantId){
    	GetCaseloadByDefendantEvent defEvent = new GetCaseloadByDefendantEvent();

    	if( defendantId != null && defendantId.length() < 8 ){
    		while( defendantId.length() < 8 ){
    			defendantId = "0" + defendantId;
    		}
    	}
    	defEvent.setDefendantId( defendantId );
    	Iterator iter = CaseAssignmentOrder.findAllByOfficerEvent(defEvent);
    	List activeCaseAssignmentList = CollectionUtil.iteratorToList(iter);
    	return activeCaseAssignmentList;
   	
    }
    /**
     * 
     * @param defendantId
     */
    public static void closeSupervisee(String defendantId)
    {
    	Supervisee supervisee = Supervisee.findByDefendantId(defendantId);
    	if (supervisee != null){
    		supervisee.setCurrentlySupervised(false);
    		supervisee.setAssignedProgramUnitId(null);
    		supervisee.setAssignedStaffPositionId(null);
    		supervisee.setCaseloadCreditStaffPositionId(null);
    		supervisee.setProgramUnitAssignmentDate(null);
    		String historyType = CaseloadConstants.SUPERVISEE_HISTORY_CLOSE_SUPERVISION;
    		SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
    		supervisee.insertHistory(history);
    	}
    }
 
    public static Supervisee recalculateWorkloadCredit(Supervisee supervisee){
    	
    	List activeCases = getActiveCaseAssignments(supervisee.getDefendantId());
		CaseAssignmentOrder activeCaseAssignment = null;
		List cloCases = new ArrayList();
		List csoCases = new ArrayList();
		Map staffMap = new HashMap();
		CSCDStaffPosition officer = null;
		Map caseMap = new HashMap();
		String newestCaseId = null;
		
		if (activeCases.size() > 0){
			for (int i = 0; i < activeCases.size(); i++) {
				activeCaseAssignment = (CaseAssignmentOrder) activeCases.get(i);
				caseMap.put(activeCaseAssignment.getCriminalCaseId(), activeCaseAssignment);
				if (activeCaseAssignment.getAssignedStaffPositionId() != null){
					officer = (CSCDStaffPosition) staffMap.get(activeCaseAssignment.getAssignedStaffPositionId());
					if (officer == null){
						officer = CSCDStaffPosition.find(activeCaseAssignment.getAssignedStaffPositionId());
						staffMap.put(activeCaseAssignment.getAssignedStaffPositionId(), officer);
					}
					if (CSCDStaffPositionHelper.isCLO(officer.getJobTitleId())){
						cloCases.add(activeCaseAssignment);
					} else {
						csoCases.add(activeCaseAssignment);
					}
				}
			}

			if (csoCases.size() > 0){
				newestCaseId = getMostRecentCSOCase(csoCases);
			} else {
				newestCaseId = getMostRecentCLOCase(cloCases);
			}
			activeCaseAssignment = (CaseAssignmentOrder) caseMap.get(newestCaseId);

			supervisee.setAssignedProgramUnitId(activeCaseAssignment.getProgramUnitId());
			supervisee.setAssignedStaffPositionId(activeCaseAssignment.getAssignedStaffPositionId());
			supervisee.setCaseloadCreditStaffPositionId(activeCaseAssignment.getAssignedStaffPositionId());
			supervisee.setProgramUnitAssignmentDate(activeCaseAssignment.getProgramUnitAssignDate());
			String updateType=null;
			if (supervisee.getAssignedStaffPositionId() != null){
				updateType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_STAFF;
			} else if (supervisee.getAssignedProgramUnitId() != null){
				updateType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PU;
			}
			if (updateType != null){
				updateSuperviseeHistory(supervisee,updateType);
			}
		}     	
		return supervisee;		
    }

    private static String getMostRecentCSOCase(List csoCases){
    	
		String newestCaseId = null;
		Date newestProgramUnitAssignDate = null;
		Date newestOfficerAssignDate = null;
		CaseAssignmentOrder activeCaseAssignment = null;

		for (int i = 0; i < csoCases.size(); i++) {
			activeCaseAssignment = (CaseAssignmentOrder) csoCases.get(i);
			if (newestCaseId == null){
				newestCaseId = activeCaseAssignment.getCriminalCaseId();
				newestProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
				newestOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
			} else {
				if (activeCaseAssignment.getProgramUnitAssignDate().after(newestProgramUnitAssignDate)){
					newestCaseId = activeCaseAssignment.getCriminalCaseId();
					newestProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
					newestOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
					continue;
				}
				if (activeCaseAssignment.getOfficerAssignDate() != null){
					if (newestOfficerAssignDate != null){
						if (activeCaseAssignment.getOfficerAssignDate().after(newestOfficerAssignDate)){
							newestCaseId = activeCaseAssignment.getCriminalCaseId();
							newestProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
							newestOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
							continue;
						}
					}
				}
			}
		}
		return newestCaseId;
    }
    private static String getMostRecentCLOCase(List cloCases){
    	
    	CaseAssignmentOrder activeCaseAssignment = null;
		String newestFelonyCaseId = null;
		Date newestFelonyProgramUnitAssignDate = null;
		Date newestFelonyOfficerAssignDate = null;
		String newestMisdemeanorCaseId = null;
		Date newestMisdemeanorProgramUnitAssignDate = null;
		Date newestMisdemeanorOfficerAssignDate = null;
    	
		for (int i = 0; i < cloCases.size(); i++) {
			
			activeCaseAssignment = (CaseAssignmentOrder) cloCases.get(i);
			if (isFelonyCase(activeCaseAssignment.getCriminalCaseId(), activeCaseAssignment.getCourtId())){
				if (newestFelonyCaseId == null){
					newestFelonyCaseId = activeCaseAssignment.getCriminalCaseId();
					newestFelonyProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
					newestFelonyOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
				}else {
					if (activeCaseAssignment.getProgramUnitAssignDate().after(newestFelonyProgramUnitAssignDate)){
						newestFelonyCaseId = activeCaseAssignment.getCriminalCaseId();
						newestFelonyProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
						newestFelonyOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
						continue;
					}
					if (activeCaseAssignment.getOfficerAssignDate() != null){
						if (newestFelonyOfficerAssignDate != null){
							if (activeCaseAssignment.getOfficerAssignDate().after(newestFelonyOfficerAssignDate)){
								newestFelonyCaseId = activeCaseAssignment.getCriminalCaseId();
								newestFelonyProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
								newestFelonyOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
								continue;
							}
						}
					}
				}
			} else {//case assignment is a misdemeanor case
				if (newestMisdemeanorCaseId == null){
					newestMisdemeanorCaseId = activeCaseAssignment.getCriminalCaseId();
					newestMisdemeanorProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
					newestMisdemeanorOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
				}else {
					if (activeCaseAssignment.getProgramUnitAssignDate().after(newestMisdemeanorProgramUnitAssignDate)){
						newestMisdemeanorCaseId = activeCaseAssignment.getCriminalCaseId();
						newestMisdemeanorProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
						newestMisdemeanorOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
						continue;
					}
					if (activeCaseAssignment.getOfficerAssignDate() != null){
						if (newestMisdemeanorOfficerAssignDate != null){
							if (activeCaseAssignment.getOfficerAssignDate().after(newestMisdemeanorOfficerAssignDate)){
								newestMisdemeanorCaseId = activeCaseAssignment.getCriminalCaseId();
								newestMisdemeanorProgramUnitAssignDate = activeCaseAssignment.getProgramUnitAssignDate();
								newestMisdemeanorOfficerAssignDate = activeCaseAssignment.getOfficerAssignDate();
								continue;
							}
						}
					}
				}
			}
		}
		String mostRecentCaseId = null;
		if (newestFelonyCaseId != null){
			mostRecentCaseId = newestFelonyCaseId;
		} else {
			mostRecentCaseId = newestMisdemeanorCaseId;
		}
		
		return mostRecentCaseId;
    }
    public static void reactivateSupervisee(String defendantId)
    {
    	Supervisee supervisee = Supervisee.findByDefendantId(defendantId);

    	if (!supervisee.isCurrentlySupervised()){
    		supervisee.setCurrentlySupervised(true);
    	} 
    	
    	supervisee = recalculateWorkloadCredit(supervisee);

    }
    
    
    /**
     * 
     * @param supervisee
     * @param historyType
     * @return
     */
    public static SuperviseeHistory updateSuperviseeHistory(Supervisee supervisee, String historyType) {
		SuperviseeHistory history = new SuperviseeHistory();
		history.setType(historyType);
		history.setSuperviseeId(supervisee.getOID().toString());
		history.setLosEffectiveDate(supervisee.getLosEffectiveDate());
		history.setSupervisionLevelId(supervisee.getSupervisionLevelId());
		history.setAssignedProgramUnitId(supervisee.getAssignedProgramUnitId());
		history.setCaseloadCreditStaffPositionId(supervisee.getCaseloadCreditStaffPositionId());
		history.setAssignedStaffPositionId(supervisee.getAssignedStaffPositionId());
		history.setCurrentlySupervised(supervisee.isCurrentlySupervised());
		history.setZipCode(supervisee.getZipCode());
		history.setProgramUnitAssignmentDate(supervisee.getProgramUnitAssignmentDate());
		history.setProgramTrackerEffectiveDate(supervisee.getProgramTrackerEffectiveDate());
		history.setProgramTrackerEndDate(supervisee.getProgramTrackerEndDate());
		history.setProgramTrackerId(supervisee.getProgramTrackerId());
		
		return history;
	}
    
    /**
     * 
     * @param inOutActivityEvent
     */
    public static List<CaseloadInOutActivity> getInOutActivity(GetInOutActivityEvent inOutActivityEvent)
    {
    	return CollectionUtil.iteratorToList(CaseloadInOutActivity.findAll(inOutActivityEvent));
    }//end of getInOutActivity()
    
    /**
     * Return caseload in/out activity responses
     * @param inOutActivity
     * @return
     */
    public static List<CaseAssignInOutResponseEvent> 
    					getInOutActivityResponses(List<CaseloadInOutActivity> inOutActivity)
    {
    	int num_responses = inOutActivity.size();
    	List<CaseAssignInOutResponseEvent> in_out_responses = 
    		new ArrayList<CaseAssignInOutResponseEvent>(num_responses);
    	for (int i=0;i<num_responses;i++)
    	{
    			//retrieve in/out activity
    		CaseloadInOutActivity in_out_activity = inOutActivity.get(i);
    		
    			//create response object and set properties
    		CaseAssignInOutResponseEvent in_out_response = new CaseAssignInOutResponseEvent();    		
    		in_out_response.setAssignStaffPositionId(in_out_activity.getAssignStaffPositionId());
    		in_out_response.setBeginDate(in_out_activity.getBeginDate());
    		in_out_response.setEndDate(in_out_activity.getEndDate());
    		in_out_response.setCriminalCaseId(in_out_activity.getCriminalCaseId());
    		in_out_response.setDefendantId(in_out_activity.getDefendantId());
    		in_out_response.setDefendantName(
    				CSEventsReportPDHelper.getDefendantName(
    						in_out_activity.getDefendantId()));    		
    		in_out_response.setInOut(in_out_activity.getInOut());
    		in_out_response.setSupervisionOrderId(in_out_activity.getSupervisionOrderId());    		
    		in_out_response.setCourtId(in_out_activity.getCourtId());    		    		

    			//add response to return list
    		in_out_responses.add(in_out_response);
    	}
    	
    	return in_out_responses;
    }//end of getInOutActivityResponses()
    
    /**
     * Creates an IN record
     * @param assignmentBean
     */
    public static void createCaseAssignInOutView ( ICaseAssignment assignmentBean ){
    	
			String newStaffPos = assignmentBean.getAssignedStaffPositionId();
			Date newStaffPosDate = assignmentBean.getOfficerAssignDate();
		
			CaseAssignInOutView inOutView = new CaseAssignInOutView();
	    	
	 		inOutView.setDefendantId( assignmentBean.getDefendantId() );
	 		inOutView.setCriminalCaseId( assignmentBean.getCriminalCaseId() );
	 		inOutView.setBeginDate( newStaffPosDate );
	    	inOutView.setAssignStaffPositionId( newStaffPos );
			inOutView.setInOut( "IN" );
     }
    
       
 	public static void postSupervisorsInDivision(GetLightSupervisorsEvent event, String organizationId){
   		GetStaffPositionsByPositionTypeCDEvent gEvent = new GetStaffPositionsByPositionTypeCDEvent();
   		gEvent.setAgencyId(event.getAgencyId());
   		gEvent.setOrganizationId(organizationId);
   		gEvent.setStatusId(PDCodeTableConstants.STATUS_ACTIVE);
    	Iterator iterator = CSCDOrganizationStaffPosition.findAll(gEvent);
    		
    	CSCDSupervisionStaffResponseEvent sre = null; 
    	SortedMap map = new TreeMap();
    	while(iterator.hasNext()){
    		CSCDOrganizationStaffPosition csOrg = (CSCDOrganizationStaffPosition) iterator.next();
    		if (csOrg.getRetirementDate() != null){
    			continue;
    		}
    		sre = new CSCDSupervisionStaffResponseEvent();
			if(csOrg.getUserProfileId() != null && !"".equals(csOrg.getUserProfileId())){
			    UserProfile user = UserProfile.find(csOrg.getUserProfileId());	
			    if(user != null){
			        Name name = new Name();
			        name.setFirstName(user.getFirstName());
			        name.setLastName(user.getLastName());
			        sre.setAssignedName(name);
			        sre.setSupervisorLogonId(csOrg.getUserProfileId());
			    }else{
			    	Name name = new Name();
			    	name.setFirstName(PDConstants.BLANK);
	                name.setMiddleName(PDConstants.BLANK);
	                //name.setLastName("NO USER PROFILE FOUND");
	                name.setLastName(NO_OFFICER_ASSIGNED);
	                sre.setAssignedName(name);
			        sre.setSupervisorLogonId(csOrg.getUserProfileId());
	            }
			}else{
		    	Name name = new Name();
		    	name.setFirstName(PDConstants.BLANK);
                name.setMiddleName(PDConstants.BLANK);
                //name.setLastName("NO USER PROFILE FOUND");
                name.setLastName(NO_OFFICER_ASSIGNED);
                sre.setAssignedName(name);
		        sre.setSupervisorLogonId(csOrg.getUserProfileId());
            }
 			sre.setDivisionId(csOrg.getDivisionId());
			sre.setPositionName(csOrg.getPositionName());
			sre.setSupervisorLogonId(csOrg.getUserProfileId());
			sre.setSupervisorPoi(csOrg.getProbationOfficerInd());
			sre.setStaffPositionId(csOrg.getStaffPositionId());
			sre.setParentPositionId(csOrg.getParentPositionId());
			sre.setPositionTypeId(csOrg.getPositionTypeCode());
			sre.setProgramUnitId(csOrg.getProgramUnitId());
			sre.setProgramUnitName(csOrg.getProgramUnitName());
			map.put(new StringBuffer(sre.getAssignedName().getLastName()).append(sre.getAssignedName().getFirstName()).append(csOrg.getPositionName()).toString(), sre);
		}
    	
    	Iterator iter = map.values().iterator();
//    	while(iter.hasNext()){
//    		CSCDSupervisionStaffResponseEvent resp = (CSCDSupervisionStaffResponseEvent) iter.next();
//			EventManager.getSharedInstance(EventManager.REPLY).postEvent(resp);
//    	}
    	if (iter != null && iter.hasNext()){
    		List respList = CollectionUtil.iteratorToList(iter);
    		MessageUtil.postReplies(respList);
    	}
	}
	private static final String NO_OFFICER_ASSIGNED = "No Officer Assigned";
	
	public static void postOfficersUnderSupervisor(String parentPositionId) {
		CSCDSupervisionStaffResponseEvent subordinate = null;
		Iterator officerIter = CSCDOrganizationStaffPosition.findAll("parentPositionId", parentPositionId);
		SortedMap map = new TreeMap();
		while(officerIter.hasNext()){
			CSCDOrganizationStaffPosition officer = (CSCDOrganizationStaffPosition) officerIter.next();
			if (officer.getRetirementDate() != null
					|| officer.getPositionStatusCode().equals(PDCodeTableConstants.STAFF_STATUS_RETIRED)){
				continue;
			}
			if(PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equalsIgnoreCase(officer.getPositionTypeCode())){
				subordinate = new CSCDSupervisionStaffResponseEvent();
				if(officer.getUserProfileId() != null && !"".equals(officer.getUserProfileId())){
				    UserProfile user = UserProfile.find(officer.getUserProfileId());	
				    if(user != null){
				        Name name = new Name();
				        name.setFirstName(user.getFirstName());
				        name.setLastName(user.getLastName());
				        name.setMiddleName(user.getMiddleName());
				        subordinate.setAssignedName(name);	        			        
				    }else{
				    	Name name = new Name();
				    	name.setFirstName(PDConstants.BLANK);
		                name.setMiddleName(PDConstants.BLANK);
		                name.setLastName(NO_OFFICER_ASSIGNED);
		                subordinate.setAssignedName(name);
		            }
				}else{
			    	Name name = new Name();
			    	name.setFirstName(PDConstants.BLANK);
	                name.setMiddleName(PDConstants.BLANK);
	                name.setLastName(NO_OFFICER_ASSIGNED);
	                subordinate.setAssignedName(name);
	            }
				subordinate.setDivisionId(officer.getDivisionId());
				subordinate.setPositionName(officer.getPositionName());
				subordinate.setStaffPositionId(officer.getStaffPositionId());
				subordinate.setPositionTypeId(officer.getPositionTypeCode());
				subordinate.setAssignedLogonId(officer.getUserProfileId());
				subordinate.setOrganizationId(officer.getOrganizationId());
				subordinate.setParentPositionId(officer.getParentPositionId());
				subordinate.setProbationOfficerInd(officer.getProbationOfficerInd());
				subordinate.setSupervisorId(parentPositionId);
				
				map.put(new StringBuffer(subordinate.getAssignedName().getLastName()).append(subordinate.getAssignedName().getFirstName()).append(officer.getPositionName()).toString() , subordinate);
			}
		}
		
    	Iterator iter = map.values().iterator();
//    	while(iter.hasNext()){
//		CSCDSupervisionStaffResponseEvent resp = (CSCDSupervisionStaffResponseEvent) iter.next();
//		EventManager.getSharedInstance(EventManager.REPLY).postEvent(resp);
    	if (iter != null && iter.hasNext()){
    		List respList = CollectionUtil.iteratorToList(iter);
    		MessageUtil.postReplies(respList);
    	}
	}

	/**
	 * 
	 * @param parentPositionId
	 */
	public static void postStaffUnderSupervisor( String parentPositionId ) {
		CSCDSupervisionStaffResponseEvent subordinate = null;
		Iterator officerIter = CSCDOrganizationStaffPosition.findAll("parentPositionId", parentPositionId);
		SortedMap map = new TreeMap();
		while(officerIter.hasNext()){
			CSCDOrganizationStaffPosition officer = (CSCDOrganizationStaffPosition) officerIter.next();
			if (officer.getRetirementDate() != null
					|| officer.getPositionStatusCode().equals(PDCodeTableConstants.STAFF_STATUS_RETIRED)){
				continue;
			}
			if(PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equalsIgnoreCase(officer.getPositionTypeCode())
					|| "OS".equalsIgnoreCase(officer.getPositionTypeCode())){
				subordinate = new CSCDSupervisionStaffResponseEvent();
				if(officer.getUserProfileId() != null && !"".equals(officer.getUserProfileId())){
				    UserProfile user = UserProfile.find(officer.getUserProfileId());	
				    if(user != null){
				        Name name = new Name();
				        name.setFirstName(user.getFirstName());
				        name.setLastName(user.getLastName());
				        name.setMiddleName(user.getMiddleName());
				        subordinate.setAssignedName(name);	        			        
				    }else{
				    	Name name = new Name();
				    	name.setFirstName(PDConstants.BLANK);
		                name.setMiddleName(PDConstants.BLANK);
		                name.setLastName(NO_OFFICER_ASSIGNED);
		                subordinate.setAssignedName(name);
		            }
				}else{
			    	Name name = new Name();
			    	name.setFirstName(PDConstants.BLANK);
	                name.setMiddleName(PDConstants.BLANK);
	                name.setLastName(NO_OFFICER_ASSIGNED);
	                subordinate.setAssignedName(name);
	            }
				subordinate.setDivisionId(officer.getDivisionId());
				subordinate.setPositionName(officer.getPositionName());
				subordinate.setStaffPositionId(officer.getStaffPositionId());
				subordinate.setPositionTypeId(officer.getPositionTypeCode());
				subordinate.setAssignedLogonId(officer.getUserProfileId());
				subordinate.setOrganizationId(officer.getOrganizationId());
				subordinate.setParentPositionId(officer.getParentPositionId());
				subordinate.setProbationOfficerInd(officer.getProbationOfficerInd());
				subordinate.setSupervisorId(parentPositionId);
				
				map.put(new StringBuffer(subordinate.getAssignedName().getLastName()).append(subordinate.getAssignedName().getFirstName()).append(officer.getPositionName()).toString() , subordinate);
			}
		}
		
    	Iterator iter = map.values().iterator();
    	if (iter != null && iter.hasNext()){
    		List respList = CollectionUtil.iteratorToList(iter);
    		MessageUtil.postReplies(respList);
    	}
	}
	
	public static LightSupervisorResponseEvent postLightSupervisorResponse(GetLightSupervisorsEvent event){
		Iterator orgIter = null;
		LightSupervisorResponseEvent resp = null;		
		if(event.getStaffPositionId() != null && !"".equals(event.getStaffPositionId())){
			orgIter = CSCDOrganizationStaffPosition.findAll(SupervisionConstants.STAFFPOSITION_ID, event.getStaffPositionId());
		}else if(event.getLogonId() != null && !"".equals(event.getLogonId())){
			orgIter = CSCDOrganizationStaffPosition.findAll(SupervisionConstants.USERPROFILE_ID, event.getLogonId());
		}
		if(orgIter != null){
			while(orgIter.hasNext()){
				CSCDOrganizationStaffPosition org = (CSCDOrganizationStaffPosition) orgIter.next();
				//Dawn commented the following code since staffUserProfile is not needed.
				//UserProfile staffUserProfile = org.getUserProfile();
				if (org.getRetirementDate() == null &&
						!org.getPositionStatusCode().equals(PDCodeTableConstants.STAFF_STATUS_RETIRED)){
					resp = new LightSupervisorResponseEvent();
					resp.setDivisionId(org.getDivisionId());
					resp.setSupervisorPositionId(org.getParentPositionId());
					resp.setStaffPositionId(org.getStaffPositionId());
					resp.setPositionTypeCode(org.getPositionTypeCode());
					EventManager.getSharedInstance(EventManager.REPLY).postEvent(resp);
				}
			}
		}
		return resp;
	}
}
