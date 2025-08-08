package pd.supervision.managesupervisioncase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.managesupervisioncase.CloseOutOfCountyCaseEvent;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;
import naming.CasenoteConstants;
import naming.CloseCaseConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;
import pd.supervision.administercaseload.ActiveCaseAssignmentBuilder;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administersupervisee.SuperviseeHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;

public class CloseOutOfCountyCaseHelper
{
	/**
	 * 
	 * @param oocCase
	 * @param closeCaseEvent
	 */
	public static void closeOutOfCountyCase(OutOfCountyCase oocCase, CloseOutOfCountyCaseEvent closeCaseEvent)
	{
		if(oocCase instanceof OutOfCountyProbationCloseCase)
		{
			OutOfCountyProbationCloseCase oocProbationCase = (OutOfCountyProbationCloseCase)oocCase;
			
			oocProbationCase.setDispositionTypeId(closeCaseEvent.getDispositionTypeId());
			oocProbationCase.setDispositionDate(closeCaseEvent.getDispositionDate());
			oocProbationCase.setTransferInDate(closeCaseEvent.getDispositionDate());
			if (oocProbationCase.getOriginalCountyId() != null && !oocProbationCase.getOriginalCountyId().equals(PDConstants.BLANK)){
				StringBuffer countyCode = new StringBuffer(oocProbationCase.getOriginalCountyId());
				while (countyCode.length() < 3){
					countyCode.insert(0,0);
				}
				oocProbationCase.setReceivingCounty(countyCode.toString());
			}
			//Indicate Add of T30 and JZT
			oocProbationCase.setLegacyJZTActionIndicator("A");
			if (closeCaseEvent.isLastCaseForJurisdiction()){
				oocProbationCase.setLegacyT30ActionIndicator("A");
			}
		}
	}
	
	
	/**
	 * 
	 * @param oocCase
	 * @param closeCaseEvent
	 */
	public static void updateOutOfCountyCaseClosure(OutOfCountyCase oocCase, CloseOutOfCountyCaseEvent closeCaseEvent)
	{
		if(oocCase instanceof OutOfCountyProbationCloseCase)
		{
			OutOfCountyProbationCloseCase oocProbationCase = (OutOfCountyProbationCloseCase)oocCase;
			
			//Indicate Change JZT if transfer in date has changed.
			if (!oocProbationCase.getDispositionDate().equals(closeCaseEvent.getDispositionDate())){
				oocProbationCase.setLegacyJZTActionIndicator("C");
			} else {
				oocProbationCase.setLegacyJZTActionIndicator(PDConstants.BLANK);
			}
			oocProbationCase.createTaskToWorkgroup(oocProbationCase, closeCaseEvent);
			oocProbationCase.setTransferInDate(closeCaseEvent.getDispositionDate());
			oocProbationCase.setDispositionTypeId(closeCaseEvent.getDispositionTypeId());
			oocProbationCase.setDispositionDate(closeCaseEvent.getDispositionDate());
			oocProbationCase.setTransferInDate(closeCaseEvent.getDispositionDate());
			oocProbationCase.setReasonForUpdateId(closeCaseEvent.getReasonForUpdateId());
		}
	}

	
	
	public static ICaseAssignment getActiveCaseAssignment(String caseNumber)
	{
		List caseAssignList = CaseAssignmentOrder.findByCaseNumber(caseNumber);
		if((caseAssignList != null) && (caseAssignList.size() > 0))
		{
			CaseAssignmentOrder activeCaseAssignment = (CaseAssignmentOrder)caseAssignList.get(0);
			
			ActiveCaseAssignmentBuilder builder = new ActiveCaseAssignmentBuilder();
			builder.setActiveCaseAssignment(activeCaseAssignment);
            builder.build();
            ICaseAssignment caseAssignmentBean = (ICaseAssignment) builder.getResult();
            
            return caseAssignmentBean;
		}
		return null;
	}
	
	
	public static void setPaperfileAcknowledgement(ICaseAssignment caseAssignment)
	{
		if(caseAssignment != null){
			caseAssignment.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_ASSUMED);
			
			LightCSCDStaffResponseEvent cscdStaff = getCSCDStaff();
			if(cscdStaff != null){
				caseAssignment.setAcknowledgePositionId(cscdStaff.getStaffPositionId());
				
			}
			caseAssignment.setAcknowledgeUserId(PDSecurityHelper.getLogonId());
			caseAssignment.setAcknowledgeDate(DateUtil.getCurrentDate());
		}
	}

	
	
	private static LightCSCDStaffResponseEvent getCSCDStaff()
	{
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(PDSecurityHelper.getLogonId());		
    	
    	LightCSCDStaffResponseEvent staffResponseEvent = CSCDStaffPositionHelper.getLightCSCDStaffForUser(ev);
		return staffResponseEvent;
	} 
	
	
	
	
	public static void createCasenotesOnCaseClosure(String defendantId, String criminalCaseId, String courtId, Date dispositionDate, String supervisionOrderId)
	{
		String agencyId = PDSecurityHelper.getUserAgencyId();
    	SupervisionPeriod supPer = SupervisionPeriod.findActiveSupervisionPeriod(defendantId, agencyId);
    	
    	if(supPer != null)
    	{
			 String notes = getNotes(criminalCaseId, courtId, dispositionDate);
			 
			 Collection subjects = new ArrayList();
			 SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,CaseloadConstants.ASSIGNMENT_SUBJECT_CD);
			 subjects.add((String) aCode.getOID());
				
			 UpdateCasenoteEvent updateCaseNoteEvent = new UpdateCasenoteEvent();
			 updateCaseNoteEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
			 updateCaseNoteEvent.setUserID(PDSecurityHelper.getLogonId());
			 updateCaseNoteEvent.setSuperviseeId(defendantId);
			 updateCaseNoteEvent.setSupervisionPeriodId(supPer.getOID().toString());
			 
	//         casenote is associated to the order (Context = order)
			 updateCaseNoteEvent.setSupervisionOrderId(supervisionOrderId);
			 updateCaseNoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_ORDER_MODIFICATION);
			 updateCaseNoteEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_ORDER_MODIFICATION);
			 updateCaseNoteEvent.setCasenoteStatusId(CasenoteConstants.STATUS_COMPLETE);
			 updateCaseNoteEvent.setSaveAsDraft(false);
			 updateCaseNoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
			 updateCaseNoteEvent.setEntryDate(new Date());
			 updateCaseNoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
	         
			 updateCaseNoteEvent.setNotes(notes);            
			 updateCaseNoteEvent.setSubjects(subjects);
			 
	         Casenote casenote = new Casenote();
	         casenote.updateCasenote(updateCaseNoteEvent);
    	}
	}
	
	
	
	public static String getNotes(String criminalCaseId, String courtId, Date dispositionDate)
	{
		String dispositionDateStr = DateUtil.dateToString(dispositionDate, DateUtil.DATE_FMT_1);
		if (criminalCaseId != null & criminalCaseId.length() > 3){
			criminalCaseId = criminalCaseId.substring(0,3) + " " + criminalCaseId.substring(3,criminalCaseId.length());
		}
    	StringBuffer notes = new StringBuffer("Case Closure for ");
    	notes.append(criminalCaseId + ", ");
    	notes.append(courtId);
    	notes.append(" on ");
    	notes.append(dispositionDateStr);
    	notes.append(". The Paper Casefile has been prepared for transfer to the Records Unit.");
    	
    	return notes.toString();
	}
	
	
	public static List getOutOfCountyTransferCases(String superviseeId, String historyType)
	{
		List outOfCountyCasesList = new ArrayList();	
		
		GetTransfersEvent event = new GetTransfersEvent();
		event.setSuperviseeId(superviseeId);
		event.setSearchType(historyType); 
		
		List transfers = SuperviseeHelper.getTransfers(event);
		TransferResponseEvent resp = null;
		
		for (int i = 0; i < transfers.size(); i++) {
			resp = (TransferResponseEvent) transfers.get(i);
			if (resp.getCdi().equals(CloseCaseConstants.OUT_OF_COUNTY)) 
			{
				outOfCountyCasesList.add(resp);
			}
		}
	    return outOfCountyCasesList;
	}
	    
	
	public static boolean isT30RecordToBeProcessed(OutOfCountyProbationCase outOfCountyCase, String previousDispositionTypeId)
	{
		if((previousDispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.DEFERRED_ADJUDICATION)) ||
				(previousDispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.STRAIGHT_PROBATION)))
		{
			String caseTypeId = outOfCountyCase.getOutOfCountyCaseTypeId();
			
			if((caseTypeId.equalsIgnoreCase(PDCodeTableConstants.IN_STATE_FELONY)) || (caseTypeId.equalsIgnoreCase(PDCodeTableConstants.IN_STATE_MISD)) || 
					(caseTypeId.equalsIgnoreCase(PDCodeTableConstants.OUT_OF_STATE_FELONY)) || (caseTypeId.equalsIgnoreCase(PDCodeTableConstants.OUT_OF_STATE_MISD)))
			{
				return true;
			}
		}
		return false;
	}
	
 }
