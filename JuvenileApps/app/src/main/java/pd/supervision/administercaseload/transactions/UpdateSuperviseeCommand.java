/*
 * Created on Dec 4, 2007
 *
 */
package pd.supervision.administercaseload.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.UpdateSuperviseeEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.legacyupdates.UpdateLevelOfSupervisionDataEvent;
import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionConstants;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administercaseload.SearchCaseloadUtil;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.managesupervisioncase.OutOfCountyCase;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderPrintTemplate;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;

/**
 * This command ensures following business rules:
 * 1) When the supervisee has an existing active order and the supervisee's level of supervision is currently 
 *    set to less than 3 the system will not update it. e.g. LOS 2 (Maximum) will NOT be changed.
 * 2) When the supervisee has no active order (or there is an active order and the supervisee's level of supervision 
 *    is greater than 3 including Indirect LOS), the system will set the supervisee's level of supervision to 3.
 * 3) When there is a change to the Level Of Supervision, the effective date will be set to the order activation date.
 * 4) The system will maintain a history of changes made to the level of supervision.
 * 
 * Note: This activity is for new case assignments only and not reassignments.
 * 
 * @author cc_rbhat
 */
public class UpdateSuperviseeCommand implements ICommand {

	public UpdateSuperviseeCommand() {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception { 
		UpdateSuperviseeEvent requestEvent = (UpdateSuperviseeEvent) event;
		String updateType = requestEvent.getUpdateType();
		if (updateType.equals(CaseloadConstants.SUPERVISEE_ASSIGNED_TO_PU)) {
			assignToProgramUnit(requestEvent);
		} else if (updateType.equals(CaseloadConstants.SUPERVISEE_ASSIGNED_TO_STAFF)) {
			assignToStaffPosition(requestEvent);
		} else if (updateType.equals(CaseloadConstants.SUPERVISEE_REASSIGNED_TO_PU)) {
			reassignToProgramUnit(requestEvent);
		} else if (updateType.equals(CaseloadConstants.SUPERVISEE_REASSIGNED_TO_STAFF)) {
			reassignToStaffPosition(requestEvent);
		} else if (updateType.equals(CaseloadConstants.SUPERVISEE_ALLOCATE_TO_SUPERVISOR)) {
			reassignToProgramUnit(requestEvent);
		} else if (updateType.equals(CaseloadConstants.SUPERVISEE_UPDATE_CREDIT)) {
			updateSuperviseeCredit(requestEvent);
		}
		
		updateLegacySystem(requestEvent);
	}

	/**
	 * To ensure JIMS1 monthly reporting (CJAD monthly report and CSTS
	 * electronic reporting) continues, this activity establishes the Level Of
	 * Supervision and the Program Unit. The LOS 2nd Character gets updated with
	 * the Program Unit based on the mapping code with legacy.
	 * 
	 * @param event
	 */
	private void updateLegacySystem(UpdateSuperviseeEvent event) {
		return;
	}

	private void assignToProgramUnit(UpdateSuperviseeEvent requestEvent) {
		Supervisee supervisee = Supervisee.findByDefendantId(requestEvent.getDefendantId());
		if (supervisee == null) {
			initializeSupervisee(requestEvent);
		} else {
			updateLevelOfSupervision(requestEvent, supervisee);
			updateProgramUnit(requestEvent, supervisee);
		}
	}

	private void assignToStaffPosition(UpdateSuperviseeEvent requestEvent) {
		Supervisee supervisee = Supervisee.findByDefendantId(requestEvent.getDefendantId());
		updateStaffPosition(requestEvent, supervisee);
	}

	private void reassignToProgramUnit(UpdateSuperviseeEvent requestEvent) {
		Supervisee supervisee = Supervisee.findByDefendantId(requestEvent.getDefendantId());
		updateProgramUnit(requestEvent, supervisee);
	}

	private void reassignToStaffPosition(UpdateSuperviseeEvent requestEvent) {
		LightCSCDStaffResponseEvent staffResponseEvent = getStaff(requestEvent.getAssignedStaffPositionId());
		String staffJobTitle = staffResponseEvent.getJobTitleCD();
		if (staffJobTitle.equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLO)
				|| staffJobTitle.equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER)) {
			reassignToCLO(requestEvent);
		} else {
			assignToStaffPosition(requestEvent);
		}
	}

	/**
	 * 1) If at least one case of supervisee remains with CSO, CLO doesnot get caseload credit for the supervisee.
	 * 2) If all cases of supervisee are assigned to a CLO, then that CLO get caseload credit for the supervisee.
	 * 3) If all the cases of a supervisee are either assigned to misdemeanor CLO or felony CLO, then only the 
	 *    feloncy CLO will get the caseload credit. 
	 */
	private void reassignToCLO(UpdateSuperviseeEvent requestEvent) {
		List casesToReassign = requestEvent.getActiveCases();
		String defendantId = requestEvent.getDefendantId();
		Supervisee supervisee = Supervisee.findByDefendantId(requestEvent.getDefendantId());

		GetCaseloadEvent caseloadEvent = new GetCaseloadEvent();
		caseloadEvent.setWorkflowInd(CaseloadConstants.SEARCH_CASELOAD_BY_SPN);
		caseloadEvent.setDefendantId(defendantId);
		List superviseeCases = SearchCaseloadUtil.getCaseload(caseloadEvent);

		if (casesToReassign.size() == superviseeCases.size()) {//All cases are being reassigned to CLO.
			updateProgramUnit(requestEvent, supervisee);
			updateStaffPosition(requestEvent, supervisee);
		} else if (casesToReassign.size() < superviseeCases.size()) {//Selected cases are being reassigned to CLO.
			//Check if there is an existing case assigned to a CSO.
			Map superviseeCasesMap = new HashMap();
			for (Iterator iterator = superviseeCases.iterator(); iterator.hasNext();) {
				CaseAssignmentOrder caseAssignOrder = (CaseAssignmentOrder) iterator.next();
	            ICaseAssignment caseAssignment = caseAssignOrder.detailsValueObject();				
				//ICaseAssignment caseAssignment = (ICaseAssignment) iterator.next();
				superviseeCasesMap.put(caseAssignment.getCriminalCaseId(), caseAssignment);
			}
			Map casesToReassignMap = new HashMap();
			for (Iterator iterator = superviseeCases.iterator(); iterator.hasNext();) {
				CaseAssignmentOrder caseAssignOrder = (CaseAssignmentOrder) iterator.next();
	            ICaseAssignment caseAssignment = caseAssignOrder.detailsValueObject();
				casesToReassignMap.put(caseAssignment.getCriminalCaseId(), caseAssignment);
			}
			List nonSelectedCases = new ArrayList();
			for (Iterator iterator = superviseeCasesMap.keySet().iterator(); iterator.hasNext();) {
				String criminalCaseId = (String) iterator.next();
				if (!casesToReassignMap.containsKey(criminalCaseId)) {
					nonSelectedCases.add(superviseeCasesMap.get(criminalCaseId));
				}
			}
			boolean updateSupervisee = true;
			for (Iterator iterator = nonSelectedCases.iterator(); iterator.hasNext();) {
				CaseAssignmentOrder caseAssignOrder = (CaseAssignmentOrder) iterator.next();
	            ICaseAssignment caseAssignment = caseAssignOrder.detailsValueObject();
				String existingStaffPositionId = caseAssignment.getAssignedStaffPositionId();

				LightCSCDStaffResponseEvent staffResponseEvent = getStaff(existingStaffPositionId);
				String staffPositionType = staffResponseEvent.getStaffPositionType();
				String staffJobTitle = staffResponseEvent.getJobTitleCD();
				
				if (PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER.equals(staffPositionType) && 
						!PDCodeTableConstants.STAFF_JOB_TITLE_CLO.equals(staffJobTitle)) {
					//Supervisee has a case assigned to a CSO, no update needed for Supervisee entity.
					updateSupervisee = false;
					break;					
				} else {
					List courts = (ArrayList) staffResponseEvent.getCourts();
					for (Iterator courtItr = courts.iterator(); courtItr.hasNext();) {
						String courtId = (String) courtItr.next();
						String courtType = courtId.substring(0, 2);
						if (courtType.equalsIgnoreCase("CR")) { //Felony court
							//Supervisee has a case assigned to a felony CLO, no update needed for Supervisee entity.
							updateSupervisee = false;
							break;
						}
					}
				}
			}
			if (updateSupervisee) {
				updateProgramUnit(requestEvent, supervisee);
				updateStaffPosition(requestEvent, supervisee);
			}
		}
	}

	private void initializeSupervisee(UpdateSuperviseeEvent requestEvent) {
		SupervisionLevelOfServiceCode supervisionLevelCode = null;
		supervisionLevelCode = SupervisionLevelOfServiceCode.findByLevelOfService(CaseloadConstants.LOS3);
		Supervisee supervisee = new Supervisee();
		supervisee.setDefendantId(requestEvent.getDefendantId());
		supervisee.setSupervisionLevelId(supervisionLevelCode.getOID().toString());

		ICaseAssignment caseToAcknowledge = requestEvent.getNewCaseToAcknowledge();
		SupervisionOrder order = SupervisionOrder.find(caseToAcknowledge.getSupervisionOrderId());
		
		String courtCd = order.getCurrentCourtId();
		String courtCat = "";
		if ( courtCd.length() > 2 ){
			
			courtCat = courtCd.substring( 0, 2 );
		}
		if ("OC".equals(courtCat) ){
			
			supervisee.setLosEffectiveDate( order.getTransferInDate() );
		}else{
			// For Harris County cases
			supervisee.setLosEffectiveDate(order.getCaseSupervisionBeginDate());
		}
		
		supervisee.setAssignedProgramUnitId(requestEvent.getAssignedProgramUnitId());
		supervisee.setProgramUnitAssignmentDate(requestEvent.getProgramUnitAssignmentDate());
		supervisee.setCurrentlySupervised(true);
		new Home().bind(supervisee); //get the new oid.
		String historyType = CaseloadConstants.SUPERVISEE_HISTORY_CREATE;
		SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
		supervisee.insertHistory(history);
	}
	private static final String OUT_OF_COUNTY = "010";
	private static final String PRETRIAL_INTERVENTION = "PTIN";
	/**
	 * Business Rule: When a supervisee has an existing order and the supervisee's level of supervision
	 * is currently set to less than 3 (including alpha characters), the system will not update
	 * the supervisee's level of supervision.
	 */
	private void updateLevelOfSupervision(UpdateSuperviseeEvent requestEvent, Supervisee supervisee) {
		
		boolean currentlySupervised = supervisee.isCurrentlySupervised();
		SupervisionLevelOfServiceCode supervisionLevelCode = null;
		ICaseAssignment caseToAcknowledge = requestEvent.getNewCaseToAcknowledge();
		String cdi = caseToAcknowledge.getCriminalCaseId().substring(0,3);
		String caseNum = caseToAcknowledge.getCriminalCaseId().substring(3);
		SupervisionOrder order = SupervisionOrder.find(caseToAcknowledge.getSupervisionOrderId());
		
		if (!currentlySupervised) {
			String legacyLOSCode = null;
			if (cdi.equals(OUT_OF_COUNTY)){
				GetOutOfCountyCaseEvent oocEvt = new GetOutOfCountyCaseEvent();
				oocEvt.setCourtDivisionId(cdi);
				oocEvt.setCaseNum(caseNum);
				List caseList = CollectionUtil.iteratorToList(new Home().findAll(oocEvt, OutOfCountyProbationCase.class));

				if (caseList != null && caseList.size() > 0){
					OutOfCountyCase oocCase = (OutOfCountyCase) caseList.get(0);
					legacyLOSCode = null;
					if ("CSR".equals(oocCase.getOutOfCountyCaseTypeId())){
						legacyLOSCode = CaseloadConstants.LEGACY_LOS_CSR;
					} else {
						legacyLOSCode = CaseloadConstants.LEGACY_LOS_NEW_COMPACT_CASES;
					}
				}
				supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(legacyLOSCode);
				supervisee.setSupervisionLevelId(supervisionLevelCode.getOID());
				supervisee.setLosEffectiveDate( order.getTransferInDate() );
			} else if (PRETRIAL_INTERVENTION.equals(order.getDispositionTypeId())){
				if (this.isPretrialBondCase(order.getOrderTitleId())){
					legacyLOSCode = CaseloadConstants.LEGACY_LOS_PRETRIAL_BOND;
				} else if (this.isDWIDivertCase(order.getOrderTitleId())
						&& !cdi.equals(SupervisionConstants.FELONY_CDI)){
					//this is a pretrial DWI Divert misdemeanor case
					legacyLOSCode = CaseloadConstants.LEGACY_LOS_PRETRIAL_DIRECT;
				} else if (cdi.equals(SupervisionConstants.FELONY_CDI)){
					legacyLOSCode = CaseloadConstants.LEGACY_LOS_PRETRIAL_DIRECT;
				} else {
					legacyLOSCode = CaseloadConstants.LEGACY_LOS_PRETRIAL_INDIRECT;
				}
				supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(legacyLOSCode);
				supervisee.setSupervisionLevelId(supervisionLevelCode.getOID());
				supervisee.setLosEffectiveDate( order.getCaseSupervisionBeginDate() );
			}  else {
				supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(CaseloadConstants.LEGACY_LOS_DIRECT_MEDIUM);
				supervisee.setSupervisionLevelId(supervisionLevelCode.getOID());
				supervisee.setLosEffectiveDate( order.getCaseSupervisionBeginDate() );
			}

			supervisee.setCurrentlySupervised(true);

			String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS;
			SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
			supervisee.insertHistory(history);	
			if ((cdi.equals(OUT_OF_COUNTY) && CaseloadConstants.LEGACY_LOS_NEW_COMPACT_CASES.equals(legacyLOSCode))){
					legacyUpdateForLevelOfSupervision(history, caseToAcknowledge, supervisionLevelCode,"A");	
			} else if (!cdi.equals(OUT_OF_COUNTY)
					&& (!PRETRIAL_INTERVENTION.equals(order.getDispositionTypeId()))){
				legacyUpdateForLevelOfSupervision(history, caseToAcknowledge, supervisionLevelCode,"A");	
			}
		} else if (currentlySupervised) {
			if(StringUtils.isNotEmpty(supervisee.getSupervisionLevelId())){
				supervisionLevelCode = SupervisionLevelOfServiceCode.find(supervisee.getSupervisionLevelId());
				String currentLevelOfSupervision = supervisionLevelCode.getLevelOfServiceCode();
				String legacyLevelOfSupervision = supervisionLevelCode.getLevelOfServiceLegacyCode();
				if (currentLevelOfSupervision.equals(CaseloadConstants.IND)
						|| currentLevelOfSupervision.equals(CaseloadConstants.LOS4)){
					if (!cdi.equals(OUT_OF_COUNTY)){
						//this is a harris county case
						if (!PRETRIAL_INTERVENTION.equals(order.getDispositionTypeId())){
							supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(CaseloadConstants.LEGACY_LOS_DIRECT_MEDIUM);
							supervisee.setSupervisionLevelId(supervisionLevelCode.getOID()); 
							supervisee.setLosEffectiveDate(order.getCaseSupervisionBeginDate());
							String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS;
							SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
							supervisee.insertHistory(history);							
							legacyUpdateForLevelOfSupervision(history, caseToAcknowledge, supervisionLevelCode,"A");			

						} else if (currentLevelOfSupervision.equals(CaseloadConstants.IND)){
							//Harris County pretrial case.  
							supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(CaseloadConstants.LEGACY_LOS_PRETRIAL_DIRECT);
							supervisee.setSupervisionLevelId(supervisionLevelCode.getOID());	
						}
					}  else {
							//New case is OOC (Out of County)  defect JIMS200074622
							// Add check for OOC case - If IND (Not "E" legacy) or LOS 4 OOC type, create LOS History and set effectiveDate to transferInDate -RJC
							boolean isOOCLOSUpdated = false;
							if (currentLevelOfSupervision.equals(CaseloadConstants.IND) && (legacyLevelOfSupervision != null) &&				
									!legacyLevelOfSupervision.equalsIgnoreCase(CaseloadConstants.LEGACY_LOS_NEW_COMPACT_CASES)){
								
									supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(CaseloadConstants.LEGACY_LOS_NEW_COMPACT_CASES);
									supervisee.setSupervisionLevelId(supervisionLevelCode.getOID());
									supervisee.setLosEffectiveDate( order.getTransferInDate() );
									isOOCLOSUpdated = true;
							} else if (currentLevelOfSupervision.equals(CaseloadConstants.LOS4)){
								
									supervisionLevelCode = SupervisionLevelOfServiceCode.findByLegacyLevelOfService(CaseloadConstants.LEGACY_LOS_DIRECT_MEDIUM);
									supervisee.setSupervisionLevelId(supervisionLevelCode.getOID());
									supervisee.setLosEffectiveDate( order.getTransferInDate() );
									isOOCLOSUpdated = true;
							}
							// if LOS changed, then create history and set the legacy information
							if(isOOCLOSUpdated){
								String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS;
								SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
								supervisee.insertHistory(history);							
								legacyUpdateForLevelOfSupervision(history, caseToAcknowledge, supervisionLevelCode,"A");	
							}
						}
				}
			}
		}
	}
	private static String PRETRIAL_BOND="PRETRIAL BOND";
	private static String DWI_DIVERT="DWI DIVERT";

	private boolean isDWIDivertCase(String orderTitleId) {
		boolean isDWIDivert = false;
		IHome home = new Home();
		SupervisionOrderPrintTemplate printTemplate = (SupervisionOrderPrintTemplate) home.find(orderTitleId, SupervisionOrderPrintTemplate.class);
		if (printTemplate.getOrderTitle().equals(DWI_DIVERT)){
			isDWIDivert = true;
		}
		return isDWIDivert;
	}
	
	private boolean isPretrialBondCase(String orderTitleId) {
		boolean isPretrialBondCase = false;
		IHome home = new Home();
		SupervisionOrderPrintTemplate printTemplate = (SupervisionOrderPrintTemplate) home.find(orderTitleId, SupervisionOrderPrintTemplate.class);
		if (printTemplate.getOrderTitle().equals(PRETRIAL_BOND)){
			isPretrialBondCase = true;
		}
		return isPretrialBondCase;
	}

	private void legacyUpdateForLevelOfSupervision(SuperviseeHistory history, ICaseAssignment caseAssignment, 
			SupervisionLevelOfServiceCode supervisionLevelCode,
			String action) {
		
	   	//LOS H (Pretrial Intervention, J (Transfer Within Texas) and K (Transfer Out of Texas) 
    	//are not state reported and will not have a CTS code.
		if (supervisionLevelCode.getCstsCode() == null || supervisionLevelCode.getCstsCode().equals(PDConstants.BLANK)){
			return;
		}
    	ILegacyUpdates icsts = new LegacyUpdatesImpl();
    	UpdateLevelOfSupervisionDataEvent reqEvent = new UpdateLevelOfSupervisionDataEvent();    	    	
    	String recordType = "T20";        	
    	reqEvent.setJims2SourceId(history.getOID());
    	reqEvent.setRecType(recordType);
    	reqEvent.setLogonId(caseAssignment.getAcknowledgeUserId());
    	reqEvent.setSpn(caseAssignment.getDefendantId());
    	reqEvent.setCriminalCaseId(caseAssignment.getCriminalCaseId());
    	reqEvent.setAction(action);
    	if (action.equals("A")){
    		//ER JIMS200062461
    		//A new T20 should be build when a person not currently under supervision is put under supervision
    		reqEvent.setBuildT20Ind("Y");
    	}
    	reqEvent.setTransactionDate(history.getLosEffectiveDate());
    	String los = supervisionLevelCode.getCstsCode();
     	reqEvent.setLos(los);
    	reqEvent.setComments("");
    	try {
    		icsts.create(reqEvent, recordType);
		} catch (Exception e) {		
			e.printStackTrace();
			throw new RuntimeException("Failed to create T20 type of record for program unit assignment.", e);
		}
	}

	private void updateProgramUnit(UpdateSuperviseeEvent requestEvent, Supervisee supervisee) {
		//If program unit changes, no office gets caseload credit till the current
		//case is assigned to a CSO.
		String currentProgramUnitId = supervisee.getAssignedProgramUnitId();
		Date currentProgramUnitAssignmentDate = supervisee.getProgramUnitAssignmentDate();
		String caseProgramUnitId = requestEvent.getAssignedProgramUnitId();
		Date caseProgramUnitAssignmentDate = requestEvent.getProgramUnitAssignmentDate();
		
		if (!caseProgramUnitId.equals(currentProgramUnitId)) {
			supervisee.setAssignedProgramUnitId(requestEvent.getAssignedProgramUnitId());
			supervisee.setCaseloadCreditStaffPositionId(null);
			supervisee.setProgramUnitAssignmentDate(requestEvent.getProgramUnitAssignmentDate());				
			
			String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PU;
			SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
			supervisee.insertHistory(history);
		} else if (currentProgramUnitAssignmentDate != null &&
				   caseProgramUnitId.equals(currentProgramUnitId) && 
				 (caseProgramUnitAssignmentDate.getTime() < currentProgramUnitAssignmentDate.getTime())) {
			supervisee.setCaseloadCreditStaffPositionId(null);
			supervisee.setProgramUnitAssignmentDate(requestEvent.getProgramUnitAssignmentDate());				

			String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PU;
			SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
			supervisee.insertHistory(history);			
		}
	}

	private void updateStaffPosition(UpdateSuperviseeEvent requestEvent, Supervisee supervisee) {
		String staffPositionId = requestEvent.getAssignedStaffPositionId();
		supervisee.setCaseloadCreditStaffPositionId(staffPositionId);
		supervisee.setAssignedStaffPositionId(staffPositionId);
		String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_STAFF;
		SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
		supervisee.insertHistory(history);
	}

	private void updateSuperviseeCredit(UpdateSuperviseeEvent requestEvent) 
	{
		Supervisee supervisee = Supervisee.findByDefendantId(requestEvent.getDefendantId());
		
		if (supervisee != null)
		{
			supervisee.setCaseloadCreditStaffPositionId(
					requestEvent.getCaseloadCreditStaffPositionId());
			supervisee.setProgramUnitAssignmentDate(
							requestEvent.getProgramUnitAssignmentDate());
			supervisee.setAssignedProgramUnitId(requestEvent.getAssignedProgramUnitId());
					
			String historyType = CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_CREDIT;
			SuperviseeHistory history = updateSuperviseeHistory(supervisee, historyType);
			supervisee.insertHistory(history);			
		}
	}
	
	private SuperviseeHistory updateSuperviseeHistory(Supervisee supervisee, String historyType) {
		SuperviseeHistory history = new SuperviseeHistory();
		history.setType(historyType);
		history.setSuperviseeId(supervisee.getOID());
		history.setLosEffectiveDate(supervisee.getLosEffectiveDate());
		history.setSupervisionLevelId(supervisee.getSupervisionLevelId());
		history.setAssignedProgramUnitId(supervisee.getAssignedProgramUnitId());
		history.setProgramUnitAssignmentDate(supervisee.getProgramUnitAssignmentDate());
		if (supervisee.getCaseloadCreditStaffPositionId() != null && 
				supervisee.getCaseloadCreditStaffPositionId().equals(PDConstants.BLANK)){
			history.setCaseloadCreditStaffPositionId(null);
		} else {
			history.setCaseloadCreditStaffPositionId(supervisee.getCaseloadCreditStaffPositionId());
		}
		history.setAssignedStaffPositionId(supervisee.getAssignedStaffPositionId());
		history.setCurrentlySupervised(supervisee.isCurrentlySupervised());
		history.setZipCode(supervisee.getZipCode());
		new Home().bind(history);  //need oid returned for further processing.
		return history;
	}

	private LightCSCDStaffResponseEvent getStaff(String staffPositionId) {
		GetLightCSCDStaffForUserEvent event = new GetLightCSCDStaffForUserEvent();
    	event.setStaffPositionId( staffPositionId );
    	
    	LightCSCDStaffResponseEvent resp = CSCDStaffPositionHelper.getLightCSCDStaffForUser(event);
    	if (resp != null){
    		MessageUtil.postReply( resp );
    	}
		return resp;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}
}
