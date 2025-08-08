package pd.supervision.posttrial.transactions.daos;

import java.util.Iterator;
import java.util.List;

import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.posttrial.CreateWorkflowTaskEvent;

import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.criminalcase.CriminalCase;
import pd.supervision.Court;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
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
public class WFAssignmentReassignmentDAO extends WorkflowUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public WFAssignmentReassignmentDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		CreateWorkflowTaskEvent cEvent = (CreateWorkflowTaskEvent) object;
	    String nextActionId = cEvent.getNextActionId();
	    		   
	    TaskDef taskDef = TaskDef.find(nextActionId);	   
	    
	    if(taskDef == null){
	    	
	    	ErrorResponseEvent re = new ErrorResponseEvent();
			   re.setMessage( "No task definition found for this Next Action" );
	  	   	   MessageUtil.postReply(re);
			   return;
	    }
	    
		   String caseId = new StringBuffer().append(cEvent.getCdi()).append(cEvent.getCaseNumber()).toString();
		   CriminalCase cCase = CriminalCase.find(caseId);   
		   if(cCase != null){		   
		       //create nttask
			   CreateCSTaskEvent createTaskEvent = prepareTaskEvent(cEvent);			   

			   String supervisionOrderId = "";
			   String caseAssignmentId = "";
			   String programUnitId = "";	
			   String courtId = "";
	    	   createTaskEvent.setCriminalCaseId( caseId );
			   
			   List list = CaseAssignmentOrder.findByCaseNumber(caseId);	       
			   SupervisionOrder sup = null;
	    	   if(list != null && !list.isEmpty()){
		    	   CaseAssignmentOrder caseOrder = (CaseAssignmentOrder) list.get(0);
		    	   supervisionOrderId = caseOrder.getSupervisionOrderId();
				   caseAssignmentId = caseOrder.getOID();
				   programUnitId = caseOrder.getProgramUnitId();
				   sup = SupervisionOrder.find(supervisionOrderId);
				   if(sup != null){
				       Court court = sup.getCurrentCourt();
				       if(court != null){
				    	   courtId = court.getCourtNumber();
				       }
					   //courtId = caseOrder.getSupervisionOrder().getCurrentCourt().getCourtNumber();
				   }
		       }else{
		    	   Iterator supIter = SupervisionOrder.findAll(CaseloadConstants.CRIMINAL_CASE_ID,caseId);
		    	   while(supIter.hasNext()){
		    		   sup = (SupervisionOrder) supIter.next();
		    		   courtId = sup.getCurrentCourt().getCourtNumber();
		    		   if(PDCodeTableConstants.STATUS_ACTIVE_ID.equalsIgnoreCase(sup.getOrderStatusId())){
		    			   break;
		    		   }
		    	   }
		    	   
		    	   if(sup == null){			    		   
					   ErrorResponseEvent re = new ErrorResponseEvent();
					   re.setMessage("Task invalid. No active supervision order for case # "+ cEvent.getCaseNumber() + " / " + cEvent.getCdi() );
					   re.setPageName("ASRE");
			  	   	   MessageUtil.postReply(re);
					   return;
		    	   }
		    	   supervisionOrderId = sup.getOID();
		       }
		       
		       //assignment
		       if(ViolationReportConstants.ASSIGNMENT.equalsIgnoreCase(cEvent.getNextActionParent())){
		    	   if(caseAssignmentId != null && !caseAssignmentId.equals("")){
		    		   createTaskEvent.setCaseAssignId( caseAssignmentId );
		    	   }
		    	   createTaskEvent.setSupervisionOrderId( supervisionOrderId );	
		    	   //createTaskEvent.setTopic( ViolationReportConstants.ASSIGNMENT );
		    	   //taskState.addItem(ViolationReportConstants.WORKFLOWVALIDATION, ViolationReportConstants.WORKFLOWVALIDATION);
		    	   
		    	   if(nextActionId.equalsIgnoreCase(CaseloadConstants.CSTASK_TOPIC_PROCESS_OFF_NEW_CASE_ASSIGN)){
					   
		    		   createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF);
					   createTaskEvent.setScenario( CaseloadConstants.OFFICER_ACCEPT_PAGEFLOW );
				   
		    	   }else if(nextActionId.equalsIgnoreCase(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF)){
					   
		    		   createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP);
		    		   createTaskEvent.setScenario( CaseloadConstants.ASSIGN_OFFICER_PAGEFLOW );
				   
		    	   }else if(nextActionId.equalsIgnoreCase(CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP)){
					   
		    		   createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);
		    		   createTaskEvent.setScenario( CaseloadConstants.ALLOCATE_SUPERVISOR_PAGEFLOW );
				   
		    	   }else if(nextActionId.equalsIgnoreCase(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU)){
					  
		    		   if(getOwnerId(cEvent).startsWith(CaseloadConstants.WORKGROUP_OID_PREPEND_STRING)){
						   
		    			   createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);
		    			   createTaskEvent.setScenario( CaseloadConstants.ASSIGN_PROGRAM_UNIT_PAGEFLOW );
					   
		    		   }else{
						   
		    			   createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU);
		    			   createTaskEvent.setScenario( CaseloadConstants.ASSIGN_PROGRAM_UNIT_PAGEFLOW );
					  
		    		   }
				   }
		       }else if(ViolationReportConstants.REASSIGNMENT.equalsIgnoreCase(cEvent.getNextActionParent())){
                   //reassignment
		    	   if("".equals(caseAssignmentId)){
					   ErrorResponseEvent re = new ErrorResponseEvent();
					   re.setMessage("You can not reassign a Supervisee who has never been assigned.");
					   MessageUtil.postReply(re);
					   return;
			       }  
		    	   
		    	   createTaskEvent.setCaseAssignId( caseAssignmentId );
		    	   createTaskEvent.setSupervisionOrderId( supervisionOrderId );

				   
				   if( nextActionId.equalsIgnoreCase( CaseloadConstants.CSTASK_TOPIC_PROCESS_OFF_NEW_CASE_ASSIGN ) ){
					   
					   createTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF );
					   createTaskEvent.setScenario( CaseloadConstants.REASSIGN_CASES_PAGEFLOW );
				   
				   }else if( nextActionId.equalsIgnoreCase( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF ) ){
					   
					   createTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );
					   createTaskEvent.setScenario( CaseloadConstants.REASSIGN_OFFICER_PAGEFLOW );
				   
				   }else if(nextActionId.equalsIgnoreCase( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP )){
					   
					   createTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU );
					   createTaskEvent.setScenario( CaseloadConstants.REALLOCATE_SUPERVISOR_PAGEFLOW );
				   
				   }else if(nextActionId.equalsIgnoreCase( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU )){
					   
					   createTaskEvent.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_PU );
					   createTaskEvent.setScenario( CaseloadConstants.REASSIGN_PROGRAM_UNIT_PAGEFLOW );//RRY changed to PU					   
				   }				   
		       }
		       
	    	   StringBuffer padCrt = new StringBuffer( courtId );
		    	
				while ( padCrt.length() < 3 ){
		    		padCrt.insert( 0, "0" );
		    	}
				
	    	   createTaskEvent.setCourtId( padCrt.toString() );
		       createTaskEvent.setSuperviseeName( cEvent.getSuperviseeName() );
		       
		       PDTaskHelper.createCSTask( createTaskEvent );
		    }
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {

	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void refresh(Object object) {

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
}
