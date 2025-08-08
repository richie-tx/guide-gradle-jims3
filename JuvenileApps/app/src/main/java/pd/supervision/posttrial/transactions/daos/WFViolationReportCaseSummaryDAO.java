package pd.supervision.posttrial.transactions.daos;

import java.util.Iterator;

import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.posttrial.CreateWorkflowTaskEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.managetask.PDTaskHelper;
import pd.task.TaskDef;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class WFViolationReportCaseSummaryDAO extends WorkflowUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public WFViolationReportCaseSummaryDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		CreateWorkflowTaskEvent cEvent = (CreateWorkflowTaskEvent) object;
	    String nextActionId = cEvent.getNextActionId();
	   
	    TaskDef taskDef = TaskDef.find(nextActionId);	   
	    if(taskDef != null){
		   String caseId = new StringBuffer().append(cEvent.getCdi()).append(cEvent.getCaseNumber()).toString();
		   CriminalCase cCase = CriminalCase.find(caseId);   
		   if(cCase != null){		   
		       // create nttask
			   CreateCSTaskEvent createTaskEvent = prepareTaskEvent(cEvent);			   
			   			   
			   createTaskEvent.setScenario( taskDef.getAction() );
			   createTaskEvent.setSuperviseeName( cEvent.getSuperviseeName() );
//			   taskState.addItem(ViolationReportConstants.PARAM_OFFICER_NAME, (cEvent.getOfficerName() != null)?cEvent.getOfficerName():"");
//			   taskState.addItem(ViolationReportConstants.PARAM_OFFENSE, (cEvent.getOffense() != null)?cEvent.getOffense():"");
//			   taskState.addItem(ViolationReportConstants.PARAM_LOS, (cEvent.getLos() != null)?cEvent.getLos():"");
//			   taskState.addItem(ViolationReportConstants.PARAM_PROGRAM_UNIT, (cEvent.getProgramUnit() != null)?cEvent.getProgramUnit():"");
			   createTaskEvent.setCourtId( cCase.getCourtId() );
			   createTaskEvent.setDefendantId( cCase.getDefendantId() );
			   createTaskEvent.setCriminalCaseId( cCase.getCriminalCaseId() );
			
			   Iterator vrIter = ViolationReport.findAll(ViolationReportConstants.CASE_ID, new StringBuffer().append(cEvent.getCdi()).append(cEvent.getCaseNumber()).toString());
			   String ncResponseId= "";
			   String reportType = "";
			   while(vrIter.hasNext()){
				   ViolationReport vr = (ViolationReport) vrIter.next();  
				   if(nextActionId.indexOf(ViolationReportConstants.VIOLATIONREPORT) >= 0){
					   reportType = ViolationReportConstants.REPORTTYPE_VIOLATION;
				   }else{
					   reportType = ViolationReportConstants.REPORTTYPE_CASESUMMARY;
				   }
		    	   if(!ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(vr.getStatusId()) && reportType.equalsIgnoreCase(vr.getReportType())){
		    		   ncResponseId = vr.getOID();
		    		   createTaskEvent.setNcResponseId(  ncResponseId );
			    	   break;	
		    	   }
		       }
			   
			   if(ncResponseId.equals("")){
				   ErrorResponseEvent re = new ErrorResponseEvent();
				   re.setMessage("There is no report for the case number in the correct status");
				   re.setPageName("VRCS");
				   if(ViolationReportConstants.REPORTTYPE_VIOLATION.equals(reportType)){
				       re.setUserId(ViolationReportConstants.VIOLATION_REPORT);
				   }else{
					   re.setUserId(ViolationReportConstants.CASE_SUMMARY);
				   }
				   MessageUtil.postReply(re);
				   return;
			   }
			   PDTaskHelper.createCSTask( createTaskEvent );
		   }
	   }
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {

	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {

	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {

	}
	

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {

	}
}
