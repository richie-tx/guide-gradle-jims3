package pd.supervision.administercaseload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.CasenoteConstants;
import naming.CloseCaseConstants;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.CSDischargeReasonCode;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.security.PDSecurityHelper;
import pd.supervision.Factory.OutOfCountyCaseFactory;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerprogramreferrals.LegacyDischargeReason;
import pd.supervision.cscdcalendar.CSEventHelper;
import pd.supervision.managesupervisioncase.OutOfCountyCase;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionPeriod;
import messaging.administercaseload.CloseCaseResponseEvent;
import messaging.administercaseload.UpdateCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.codetable.GetRelativeCodeEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.DateUtil;

public class CloseCaseHelper
{
	/**
	 * 
	 * @param closeCaseAssignment
	 */
	public static CloseCaseResponseEvent closeCase(ICaseAssignment closeCaseAssignment)
	{
		String defendantId = closeCaseAssignment.getDefendantId();
		String criminalCaseId = closeCaseAssignment.getCriminalCaseId();
		Date terminationDate = closeCaseAssignment.getTerminationDate();
		
		ArrayList failureReasonsList = new ArrayList();
		
		try
		{
			updateCaseAssignment(closeCaseAssignment);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.CASEASSIGNMENT_FAILURE);
		}
		
		try
		{
			createCasenotesOnCaseClosure(closeCaseAssignment);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.CASE_CLOSURE_CASENOTES_FAILURE);
		}
		
		try
		{
			inactivateOrder(defendantId, criminalCaseId, terminationDate);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.SUPERVISION_ORDER_FAILURE);
		}
		
		CloseCaseResponseEvent responseEvent = new CloseCaseResponseEvent();
		responseEvent.setDefendantId(defendantId);
		responseEvent.setCriminalCaseId(criminalCaseId);
		responseEvent.setFailureReasonsList(failureReasonsList);
		if(failureReasonsList.size() > 0)
		{
			responseEvent.setResult(CloseCaseConstants.FAILURE);
		}
		else
		{
			responseEvent.setResult(CloseCaseConstants.SUCCESS);
		}
		
		return responseEvent;
	}//end of closeCase()
	
	
	/**
	 * 
	 * @param defendantId
	 * @param terminationDate
	 */
	public static CloseCaseResponseEvent processLastCase(String defendantId)
	{
		ArrayList failureReasonsList = new ArrayList();
		
		try
		{
			exitProgramReferrals(defendantId);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.PROGRAM_REFERRAL_FAILURE);
		}
		
		try
		{
			Date terminationDate = new Date();
			String terminationDateStr = DateUtil.dateToString(terminationDate, DateUtil.DATE_FMT_1);
			terminationDate = DateUtil.stringToDate(terminationDateStr, DateUtil.DATE_FMT_1);
			
			deleteFutureCalendarEvents(defendantId, terminationDate);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.CALENDAR_FAILURE);
		}
		
		try
		{
			endSupervisionPeriod(defendantId);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.SUPERVISION_PERIOD_FAILURE);
		}
		
		try
		{
			closeSupervisee(defendantId);
			TransactionManager.getInstance().commitClear();
		}
		catch(Exception ex)
		{
			failureReasonsList.add(CloseCaseConstants.SUPERVISEE_FAILURE);
		}
		
		CloseCaseResponseEvent responseEvent = new CloseCaseResponseEvent();
		responseEvent.setDefendantId(defendantId);
		responseEvent.setCriminalCaseId("");
		responseEvent.setFailureReasonsList(failureReasonsList);
		if(failureReasonsList.size() > 0)
		{
			responseEvent.setResult(CloseCaseConstants.FAILURE);
		}
		else
		{
			responseEvent.setResult(CloseCaseConstants.SUCCESS);
		}
		
		return responseEvent;
	}//end of processLastCase()
	
	
	/**
	 * 
	 * @param closeCaseAssignment
	 */
	public static String inactivateOrder(String defendantId, String criminalCaseId, Date terminationDate)
	{
		/*set the following columns in SUPERVISIONORDER record
		ORDERSTATUSCD = "I" 
		INACTIVATEDATE = case closure date*/
		return SupervisionOrderHelper.inactivateOrder(defendantId, criminalCaseId, terminationDate);
	}
	
	
	/**
	 * 
	 * @param closeCaseAssignment
	 */
	public static void updateCaseAssignment(ICaseAssignment closeCaseAssignment)
	{
		/*set the following columns in CASEASSIGN record
		TERMINATIONDATE = case closure date
		CASESTATE = "CASE_CLOSED"
		ACKNOWLEDGESTATUSCD = "A"
		PAPERFILERECPOS_ID = staff position ID
		PAPERFILERECUSR_ID = logon ID
		PAPERFILERECDATE = current date*/
		closeCaseAssignment.setCaseAssignmentState(CaseloadConstants.CASE_CLOSED);
		
		UpdateCaseAssignmentEvent updateCaseAssignmentEvent = (UpdateCaseAssignmentEvent) EventFactory
		.getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
		updateCaseAssignmentEvent.setCaseAssignment(closeCaseAssignment);
		
		CaseAssignmentHelper.updateCaseAssignment(updateCaseAssignmentEvent);
		
	}
	
	
	/**
	 * 
	 * @param closeCaseAssignment
	 */
	public static void createCasenotesOnCaseClosure(ICaseAssignment closeCaseAssignment)
	{
		String agencyId = PDSecurityHelper.getUserAgencyId();
		String defendantId = closeCaseAssignment.getDefendantId();
    	SupervisionPeriod supPer = SupervisionPeriod.findActiveSupervisionPeriod(defendantId, agencyId);
    	
    	if(supPer != null)
    	{
			 String notes = getNotes(closeCaseAssignment);
			 
			 Collection subjects = new ArrayList();
			 SupervisionCode aCode = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CASENOTE_SUBJECT,CaseloadConstants.ASSIGNMENT_SUBJECT_CD);
			 subjects.add((String) aCode.getOID());
				
			 UpdateCasenoteEvent updateCaseNoteEvent = new UpdateCasenoteEvent();
			 updateCaseNoteEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
			 updateCaseNoteEvent.setUserID(PDSecurityHelper.getLogonId());
			 updateCaseNoteEvent.setSuperviseeId(defendantId);
			 updateCaseNoteEvent.setSupervisionPeriodId(supPer.getOID().toString());
			 
	//         casenote is associated to the order (Context = order)
			 updateCaseNoteEvent.setSupervisionOrderId(closeCaseAssignment.getSupervisionOrderId());
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
	
	
	/**
	 * 
	 * @param closeCaseAssignment
	 * @return
	 */
	private static String getNotes(ICaseAssignment closeCaseAssignment)
	{
		String criminalCaseId = closeCaseAssignment.getCriminalCaseId();
		if (criminalCaseId != null & criminalCaseId.length() > 3){
			criminalCaseId = criminalCaseId.substring(0,3) + " " + criminalCaseId.substring(3,criminalCaseId.length());
		}
    	String courtId = closeCaseAssignment.getCourtId();
    	Date inactivationDate = closeCaseAssignment.getTerminationDate();
    	String inactivationDateStr = DateUtil.dateToString(inactivationDate, DateUtil.DATE_FMT_1);
    	
    	StringBuffer notes = new StringBuffer("Case Closure for ");
    	notes.append(criminalCaseId + ", ");
    	notes.append(courtId);
    	notes.append(" on ");
    	notes.append(inactivationDateStr);
    	notes.append(". The Paper Casefile has been prepared for transfer to the Records Unit.");
    	
    	return notes.toString();
	}
	
	
	/**
	 * 
	 * @param defendantId
	 * @return
	 */
	public static boolean isLastCase(String defendantId)
	{
		return SupervisionOrderHelper.isLastCase(defendantId);
	}
	
	
	/**
	 * 
	 * @param defendantId
	 * @param terminationDate
	 */
	public static void exitProgramReferrals(String defendantId)
	{
		/*set the following fields in CSPROGRAMREFERAL record
	 	DISCHARGEREASON = Jims2 Reason for discharge
	 	PROGRAMENDDATE = case closure date */
		CSProgramReferralHelper.autoExitReferrals(defendantId);
	}
	
	
	/**
	 * 
	 * @param defendantId
	 * @param terminationDate
	 */
	public static void deleteFutureCalendarEvents(String defendantId, Date terminationDate)
	{
		CSEventHelper.deleteFutureCalendarEvents(defendantId, terminationDate);
	}
	
	
	/**
	 * 
	 * @param defendantId
	 * @param terminationDate
	 */
	public static void endSupervisionPeriod(String defendantId)
	{
		SupervisionOrderHelper.endSupervisionPeriod(defendantId);
	}
	
	
	/**
	 * 
	 * @param defendantId
	 */
	public static void closeSupervisee(String defendantId)
	{
		CaseAssignmentHelper.closeSupervisee(defendantId);
	}
	public static String getDischargeReasonForCase(String defendantId, String caseId){
		String dischargeReason = PDCodeTableConstants.JIMS2_DISCHRG_REAS_INAPPROPRIATE;
		GetRelativeCodeEvent event = new GetRelativeCodeEvent();
		
		if (caseId.startsWith("010")){
			event.setConversionType(PDCodeTableConstants.CONVERSION_TYPE_OOC_JIMS2_AUTO_EXIT);
			OutOfCountyCase oocCase = (OutOfCountyCase)OutOfCountyCaseFactory.find(caseId.substring(3), caseId.substring(0,3));
			if (oocCase != null){
				event.setCode(oocCase.getDispositionTypeId());
			}
		} else {
			LegacyDischargeReason ldr = LegacyDischargeReason.find(defendantId, caseId);
			if (ldr != null){
				event.setConversionType(PDCodeTableConstants.CONVERSION_TYPE_HC_AUTO_EXIT);
				event.setCode(ldr.getLegacyDischargeReasonCode());
			}
		}
		if (event.getCode() != null){
			Iterator iterator = CSDischargeReasonCode.findAll(event);
			if(iterator != null && iterator.hasNext()){
				CSDischargeReasonCode dischargeReasonRecord = (CSDischargeReasonCode)iterator.next();
				dischargeReason = dischargeReasonRecord.getRelativeCode();
			}
		}
		return dischargeReason;
	}

}//end of CloseCaseHelper class
