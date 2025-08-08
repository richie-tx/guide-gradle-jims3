//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\GetOutOfCountyCaseCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.administercaseload.CloseCaseResponseEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.managesupervisioncase.CloseOutOfCountyCaseEvent;
import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.util.DateUtil;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.CloseCaseConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.supervision.administercaseload.CloseCaseHelper;
import pd.supervision.managesupervisioncase.CloseOutOfCountyCaseHelper;
import pd.supervision.managesupervisioncase.OutOfCountyCase;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCloseCase;
import pd.supervision.managetask.CSTask;
import pd.supervision.managetask.PDTaskHelper;
import pd.task.helper.TaskHelper;

public class CloseOutOfCountyCaseCommand implements ICommand
{
	private static final String ZERO = "0";
	private static final String SUPERVISEE = "S";
	
	/**
	 * @roseuid 4447C72F02A4
	 */
	public CloseOutOfCountyCaseCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4447C36603E3
	 */
	public void execute(IEvent event)
	{
		CloseOutOfCountyCaseEvent closeCaseEvent = (CloseOutOfCountyCaseEvent)event;
		
		// lookup the OOC case.  
		GetOutOfCountyCaseEvent findEvent = new GetOutOfCountyCaseEvent();
		findEvent.setCaseNum(closeCaseEvent.getCaseNum());
		findEvent.setCourtDivisionId(closeCaseEvent.getCourtDivisionId());
		
		Iterator cases = null;
		if (closeCaseEvent.getCourtDivisionId().equals(PDCodeTableConstants.CSCD))
		{
			cases = new Home().findAll(findEvent, OutOfCountyProbationCloseCase.class);
		}
		else
		{
			// Invalid Court Division for Out of County case
			System.out.println("Invalid Court Division <" + closeCaseEvent.getCourtDivisionId() + "> for Out of County case.");
		}
		
		OutOfCountyCase oocCase = null;
		if (cases.hasNext())
		{
			oocCase = (OutOfCountyCase)cases.next();
		}
		
		String defendantId = oocCase.getSpn();
		while(defendantId.length() < 8)
		{
			defendantId = ZERO + defendantId;
		}
		
		String criminalCaseId = oocCase.getCriminalCaseId();
		Date dispositionDate = closeCaseEvent.getDispositionDate();
		
		if (oocCase != null)
		{
//			Close OOC Case
			if(closeCaseEvent.getReasonForUpdateId() == null)
			{
				ArrayList failureReasonsList = new ArrayList();
				
				String jurisdictionCode = getJurisdictionCode(oocCase);	    		
//				close Out Of County Case record in JIMS1
				try
				{
					if (jurisdictionCode !=  null && !jurisdictionCode.equals(PDConstants.BLANK)){
						boolean lastCaseForJurisdiction = isLastOOCCaseForOrigJuris(defendantId, oocCase.getCaseNum(), jurisdictionCode);
						if ( lastCaseForJurisdiction ){
							System.out.println( " T30-3 built! " + jurisdictionCode  );
						}
						closeCaseEvent.setLastCaseForJurisdiction(lastCaseForJurisdiction);
						CloseOutOfCountyCaseHelper.closeOutOfCountyCase(oocCase, closeCaseEvent);
						TransactionManager.getInstance().commitClear();
					} else {
						failureReasonsList.add(CloseCaseConstants.OOC_CASE_CLOSE_FAILURE+" - Supervision Information missing from OOC case. Update case information before closing.");
						CloseCaseResponseEvent responseEvent = new CloseCaseResponseEvent();
						responseEvent.setDefendantId(defendantId);
						responseEvent.setCriminalCaseId(criminalCaseId);
						responseEvent.setFailureReasonsList(failureReasonsList);
						responseEvent.setResult(CloseCaseConstants.FAILURE);
						MessageUtil.postReply(responseEvent);
						return;
					}
				}
				catch(Exception ex)
				{
					TaskHelper.createCsTaskToJIMSWorkgroup("CLOSE OOC CASE", defendantId, criminalCaseId, ex);
					failureReasonsList.add(CloseCaseConstants.OOC_CASE_CLOSE_FAILURE);
					CloseCaseResponseEvent responseEvent = new CloseCaseResponseEvent();
					responseEvent.setDefendantId(defendantId);
					responseEvent.setCriminalCaseId(criminalCaseId);
					responseEvent.setFailureReasonsList(failureReasonsList);
					responseEvent.setResult(CloseCaseConstants.FAILURE);
					MessageUtil.postReply(responseEvent);
					return;
				}
				
//				get CaseAssignmentOrder record, if exists
				ICaseAssignment activeCaseAssignment = CloseOutOfCountyCaseHelper.getActiveCaseAssignment(criminalCaseId);
				
				if(activeCaseAssignment != null)
				{
					CloseOutOfCountyCaseHelper.setPaperfileAcknowledgement(activeCaseAssignment);
					activeCaseAssignment.setTerminationDate(dispositionDate);
					
					CloseCaseResponseEvent responseEvent = CloseCaseHelper.closeCase(activeCaseAssignment);
					
					List reasonsList = responseEvent.getFailureReasonsList();
					reasonsList.addAll(failureReasonsList);
					
					if(reasonsList.size() > 0)
					{
						responseEvent.setResult(CloseCaseConstants.FAILURE);
					}
					else
					{
						responseEvent.setResult(CloseCaseConstants.SUCCESS);
					}
					
					MessageUtil.postReply(responseEvent);
				} else {
					//Inactivate supervision order for case that does not have an active case assignment
					//The order for case with an active case assignment will be done above in CloseCaseHelper.closeCase(activeCaseAssignment)
					String supervisionOrderId = null;					
					
					try
					{
						supervisionOrderId = CloseCaseHelper.inactivateOrder(defendantId, criminalCaseId, dispositionDate);
						TransactionManager.getInstance().commitClear();
					}
					catch(Exception ex)
					{
						failureReasonsList.add(CloseCaseConstants.SUPERVISION_ORDER_FAILURE);
					}
				
//					create Casenotes on Case closure; 
//					casenote should be created even if CaseASsignment record is not present
					try
					{
						if (supervisionOrderId != null){
							String courtId = oocCase.getOutOfCountyCaseTypeId();
							CloseOutOfCountyCaseHelper.createCasenotesOnCaseClosure(defendantId, criminalCaseId, courtId, dispositionDate, supervisionOrderId);
							TransactionManager.getInstance().commitClear();
						}
					}
					catch(Exception ex)
					{
						failureReasonsList.add(CloseCaseConstants.CASE_CLOSURE_CASENOTES_FAILURE);
					}
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
					// Close all tasks for case rry
					PDTaskHelper taskHelper = new PDTaskHelper();
					taskHelper.closeAllCSTasks( defendantId, criminalCaseId );
				}
				
				MessageUtil.postReply(responseEvent);
				
//				check if it is the last case of the defendant
				if(CloseCaseHelper.isLastCase(defendantId))
				{
					responseEvent = CloseCaseHelper.processLastCase(defendantId);
					closeAllCSTasksForSPN( defendantId );					
					MessageUtil.postReply(responseEvent);
				}
			
			}
//			Update OOC Case Closure
			else
			{
				String jurisdictionCode = getJurisdictionCode(oocCase);

				boolean lastCaseForJurisdiction = isLastOOCCaseForOrigJuris(defendantId, oocCase.getCaseNum(), jurisdictionCode);
				closeCaseEvent.setLastCaseForJurisdiction(lastCaseForJurisdiction);

				CloseOutOfCountyCaseHelper.updateOutOfCountyCaseClosure(oocCase, closeCaseEvent);
				TransactionManager.getInstance().commitClear();
			}
		}
	}
	
	private String getJurisdictionCode(OutOfCountyCase oocCase){
		String jurisdictionCode = oocCase.getOriginalCountyId();
		if(jurisdictionCode==null || jurisdictionCode.trim().equalsIgnoreCase(""))
		{
			jurisdictionCode = oocCase.getStateId();
		}
		else
		{
//			append zeros only for numeric County code
			while(jurisdictionCode.length() < 3)
			{
				jurisdictionCode = ZERO + jurisdictionCode;
			}
		}
		return jurisdictionCode;
	}
	
	 private boolean isLastOOCCaseForOrigJuris(String defendantId, String caseNumber, String jurisdictionCode)
	{
		 List outOfCountyCases = CloseOutOfCountyCaseHelper.getOutOfCountyTransferCases(defendantId, SUPERVISEE);
		
		Iterator iter = outOfCountyCases.iterator();
		while(iter.hasNext())
		{
			TransferResponseEvent responseEvent = (TransferResponseEvent)iter.next();
			String respEvtOrigJurisCode = responseEvent.getTransferringCountyState();
			String respEvtCaseNumber = responseEvent.getCaseNum();
			Date transOutDate = DateUtil.stringToDate( responseEvent.getOutOfCountyTransferOutDate(), DateUtil.DATE_FMT_1 );
			
			if((!caseNumber.equalsIgnoreCase(respEvtCaseNumber)) && (jurisdictionCode.equalsIgnoreCase(respEvtOrigJurisCode)))
			{
				if ( transOutDate == null ){
					
					return false;
				}
			}
		}
		return true;
	}
	
	 /**
	     * 
	     * @param toSpn
	     * @param fromSpn
	     */
	    private void closeAllCSTasksForSPN( String defendantId ) {
	       
	    	CSTask task = null;
	    	 CSTask csTask = new CSTask();
	    	 
	        List< CSTask > tasks = 
	        	CollectionUtil.iteratorToList(
	        			csTask.findAll( "defendantId" , defendantId ));
	        
		     for ( int x =0; x < tasks.size(); x ++ ){
		    	 
		    	 task = (CSTask) tasks.get( x );
		    	 // Close tasks here...
				 if( task != null && !task.getStatusId().equals("C")){
					 task.setStatusId( "C" );
				 }
		     }
		     tasks.clear();
		     tasks = null;
		     task = null;
		     csTask = null;
		     
	    }
	
	/**
	 * @param event
	 * @roseuid 4447C3670004
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4447C3670006
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 4447C3670008
	 */
	public void update(Object updateObject)
	{

	}

}
