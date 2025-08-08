/*
 * Created on Feb 1, 2011
 */
package ui.supervision;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administercaseload.GetActiveSuperviseeCasesEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.UpdateCSTaskEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CSTaskControllerServiceNames;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.UIConstants;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author ryoung
 *  
 */
public class AssignReassignSuperviseeHelper {

	private static AssignReassignSuperviseeHelper service;

	private static boolean serviceInitialized = false;

	/**
	 *  
	 */
	private AssignReassignSuperviseeHelper() {
	}

	/**
	 * 
	 * @return
	 */
	public static AssignReassignSuperviseeHelper getInstance() {
		if (service == null) {
			synchronized (AssignReassignSuperviseeHelper.class) {
				if (!serviceInitialized) {
					service = new AssignReassignSuperviseeHelper();
					serviceInitialized = true;
				}
			}
		}
		return service;
	}

	/**
	 * 
	 * @param caseAssignmentForm
	 * @return
	 */
	public void recalculateWorkloadCredit( CaseAssignmentForm caseAssignmentForm ){
    	
		GetActiveCasesEvent event = (GetActiveCasesEvent) 
							EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASES);
		
		event.setDefendantId( caseAssignmentForm.getDefendantId() );
		
		List activeCases = new ArrayList();		
		List casesBySupervisee = MessageUtil.postRequestListFilter( event, CaseAssignmentResponseEvent.class );
		
		for ( int z =0; z < casesBySupervisee.size(); z++ ){
		
			CaseAssignmentResponseEvent caseResp = (CaseAssignmentResponseEvent) casesBySupervisee.get( z );
			activeCases = caseResp.getCaseAssignments();
		}

    	ICaseAssignment activeCaseAssignment = null;
		Map caseMap = new HashMap();
		
		if ( activeCases.size() > 0 ){
			for ( int i = 0; i < activeCases.size(); i++ ) {
				
				activeCaseAssignment = ( ICaseAssignment ) activeCases.get(i);
				if ( activeCaseAssignment.getAssignedStaffPositionId() != null ){

					if ("CSO".equalsIgnoreCase(findUserPosition( activeCaseAssignment.getAssignedStaffPositionId() ))){
						
						// sort by date ascending order            
		                caseMap.put( activeCaseAssignment.getOfficerAssignDate() , activeCaseAssignment );
		                break;
					}else if ("CLO".equalsIgnoreCase(findUserPosition( activeCaseAssignment.getAssignedStaffPositionId() ))){
						// sort by date ascending order            
		                caseMap.put( activeCaseAssignment.getOfficerAssignDate() , activeCaseAssignment );
		                break;
						
					}
				}
			}
			
			Map sortedMap = sortByValue( caseMap );

            List temp = new ArrayList( sortedMap.values() );
            if ( temp.size() > 0 ){
            	
            	activeCaseAssignment = ( ICaseAssignment ) temp.get( 0 );
    			caseAssignmentForm.setOfficerPositionId( activeCaseAssignment.getAssignedStaffPositionId());
            }
		} else {
        	caseAssignmentForm.setOfficerPositionId( "" );
        }    	
    }

	/**
	 * 
	 * @param caseAssignmentForm
	 * @return
	 */
	public boolean isLastCaseAssigned( CaseAssignmentForm caseAssignmentForm )
	{

		boolean lastCase = true;
		
		GetActiveCasesEvent event = (GetActiveCasesEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASES);
       event.setDefendantId( caseAssignmentForm.getDefendantId() );

       CaseAssignmentResponseEvent assignmentResponse = ( CaseAssignmentResponseEvent ) 
       MessageUtil.postRequest(event, CaseAssignmentResponseEvent.class);
       
       List activeCases = assignmentResponse.getCaseAssignments();

       for ( int x = 0; x <activeCases.size(); x++ ){
       	
       	CaseAssignmentTO cato = ( CaseAssignmentTO ) activeCases.get( x );
       	
       	if ( cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals( caseAssignmentForm.getCriminalCaseId() )){
       		
       		if ( !caseAssignmentForm.getOfficerPositionId().equals( cato.getAssignedStaffPositionId() )){
       			
       			lastCase = false;
       		}
       	} 
       }
		return lastCase;
	}//end of isLastCase()
	
	/**
	 * 
	 * @param taskId
	 */
	public void closePreviousTask( String taskId ) 
	{
		if ( !"".equals( taskId ) ){
			
			UpdateCSTaskEvent updateTaskEvent = 
				(UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
			
			updateTaskEvent.setCsTaskId( taskId );
			updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
			updateTaskEvent.setCloseTask(true);
			MessageUtil.postRequest(updateTaskEvent);
			
		}
	}
	
    /**
	 * 
	 * @param positionId
	 * @return
	 */
	public String findUserPosition( String positionId ){
		
		String positionCode = "";
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setStaffPositionId( positionId );
		
		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);

		if(staffResponseEvent != null){
			positionCode = staffResponseEvent.getJobTitleCD();
		}
		return positionCode;	
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	private static Map sortByValue( Map map ) {
		
		List list = new LinkedList( map.entrySet());
		Collections.sort(list, new Comparator() {
			
			public int compare(Object o1, Object o2) { 
				
				return ((Comparable) ((Map.Entry) (o1)).getValue())
				.compareTo(((Map.Entry) (o2)).getValue()); } });
		
		    Map result = new LinkedHashMap();
		    
		    for (Iterator it = list.iterator(); it.hasNext();) { 
		    	Map.Entry entry = (Map.Entry)it.next();
		    	result.put(entry.getKey(), entry.getValue());}
		    
	  return result; 
	  }
	
	/**
	 * If the supervisee has existing cases assigned to officer (CSO/CLO) in different program unit than
	 * the currently selected by user then send notifications to those officers & their supervisors. Whereas, 
	 * in case, if previous cases have been assigned to a different program unit or allocated
	 * to a supervisor but not assigned to an officer (CSO/CLO) then notifications are not send.    
	 * @param caseAssignmentForm
	 */
	public void checkProgramUnitChange(CaseAssignmentForm caseAssignmentForm) {
		//sort supervisee active cases by staff postions.
		Map superviseeCasesByStaffPositions = new HashMap();
		String currentProgramUnitId = caseAssignmentForm.getProgramUnitId();
		String currentProgramUnitName = caseAssignmentForm.getProgramUnitName();
		List activeCases = new ArrayList();
		List superivseeCases = new ArrayList();
		//GET ACTIVE ASSIGNED CASES
        GetActiveSuperviseeCasesEvent event = (GetActiveSuperviseeCasesEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.GETACTIVESUPERVISEECASES);

        event.setDefendantId( caseAssignmentForm.getDefendantId() );
        event.setFilteredByCaseStatus(true);
        event.setQueryName(true);

        CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
        							MessageUtil.postRequest( event, CaseAssignmentResponseEvent.class);
		
        if ( assignmentResponse != null ){
        	
        	List CSOActiveCases = new ArrayList();
        	activeCases = assignmentResponse.getCaseAssignments();
        	
        	for ( int z=0; z< activeCases.size(); z ++ ){
        		
        		ICaseAssignment activeCase = (ICaseAssignment) activeCases.get( z );
        		if ( !"CLO".equals( this.findUserPosition( activeCase.getAssignedStaffPositionId() ))){
        			
        			CSOActiveCases.add( activeCase );
        		}
        	}
        	activeCases = new ArrayList( CSOActiveCases );
        }

			for (Iterator iterator = activeCases.iterator(); iterator.hasNext();) {
				ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
				String previousProgramUnitId = activeCase.getProgramUnitId();
				String assignedStaffPosition = activeCase.getAssignedStaffPositionId();
				caseAssignmentForm.setCourtNumber( activeCase.getCourtId());
				caseAssignmentForm.setCriminalCaseId( activeCase.getCriminalCaseId() );
				if (!currentProgramUnitId.equals(previousProgramUnitId) &&
						assignedStaffPosition != null) {
					superivseeCases = (ArrayList) superviseeCasesByStaffPositions.get(assignedStaffPosition);
					if (superivseeCases == null) {
						superivseeCases = new ArrayList();
						superviseeCasesByStaffPositions.put(assignedStaffPosition, superivseeCases);
					}
					superivseeCases.add(activeCase);
				}
			}
			if ( activeCases.size() >= 1 ){
				
				//Send notifications to officers and their supervisors. 
				for (Iterator iterator = superviseeCasesByStaffPositions.keySet().iterator(); iterator.hasNext();) {
					String staffPositionId = (String) iterator.next();
					LightCSCDStaffResponseEvent staffPosition = this.getCSCDStaffPosition(staffPositionId);
					String parentPostionId = staffPosition.getParentPositionId();
		
					List superviseeCases = (ArrayList) superviseeCasesByStaffPositions.get(staffPositionId);
					
					StringBuffer taskTextForOfficer = new StringBuffer();
					taskTextForOfficer.append("Transfer casefile to " + currentProgramUnitName + ": ");
					taskTextForOfficer.append(caseAssignmentForm.getSuperviseeName().getFormattedName() + ", ");
					taskTextForOfficer.append(caseAssignmentForm.getDefendantId() + ", ");
		
					StringBuffer taskTextForSupervisor = new StringBuffer();
					taskTextForSupervisor.append("Transfer casefile from " + staffPosition.getOfficerName());
					taskTextForSupervisor.append(" to " + currentProgramUnitName + ": ");
					taskTextForSupervisor.append(caseAssignmentForm.getSuperviseeName().getFormattedName() + ", ");
					taskTextForSupervisor.append(caseAssignmentForm.getDefendantId() + ", ");
					
					for (Iterator itr = superviseeCases.iterator(); itr.hasNext();) {
						ICaseAssignment activeCase = (ICaseAssignment) itr.next();
						taskTextForOfficer.append(activeCase.getCriminalCaseId() + ", ");
						taskTextForOfficer.append(activeCase.getCourtId() + ", ");					
		
						taskTextForSupervisor.append(activeCase.getCriminalCaseId() + ", ");
						taskTextForSupervisor.append(activeCase.getCourtId() + ", ");
						
						StringBuffer additionalText = new StringBuffer();
						additionalText.append(" Initial Closure on Courtesy case(s) when the target Program Unit is Transfer Unit. ");
						additionalText.append(" Please delete any future scheduled events for the supervisee and verify IVR association.");
						
						if ( parentPostionId != null && parentPostionId.length() != 0 ) {
							
							createReassignCasefileNotification( activeCase, caseAssignmentForm.getWorkGroupName(), parentPostionId, taskTextForSupervisor + additionalText.toString());
							createReassignCasefileNotification( activeCase, caseAssignmentForm.getWorkGroupName(), staffPositionId, taskTextForOfficer + additionalText.toString());
						
						}else {
							
							createReassignCasefileNotification( activeCase, caseAssignmentForm.getWorkGroupName(), staffPositionId, taskTextForOfficer + additionalText.toString());
						}
					}
					
					
				}
		}
	}
	
	/**
	 * 
	 * @param staffPositionId
	 * @return
	 */
	public LightCSCDStaffResponseEvent getCSCDStaffPosition(String staffPositionId) {
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setStaffPositionId(staffPositionId);		
		ev.setOfficerNameNeeded( true );
		return (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
	} 
	
	/**
	 * 
	 * @param caseAssignmentForm
	 * @param defendantId
	 * @param staffPositionId
	 * @param taskText
	 */
	private void createReassignCasefileNotification(ICaseAssignment activeCase, String workGroupName, String staffPositionId, String taskText) {

		//create cs task
		CreateCSTaskEvent createTaskEvent = new CreateCSTaskEvent();

		//due date
		Calendar calendar = Calendar.getInstance();
		int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		
			switch ( weekday) {
				
			case 5:
				calendar.add(Calendar.DATE, 4);
				break;
				
			case 6:
				calendar.add(Calendar.DATE, 4);
				break;
				
			default:
				calendar.add(Calendar.DATE, 2);
				break;
				
			}

		StringBuffer subject2 = new StringBuffer();
		subject2.append("Transfer casefile to ")
		.append( workGroupName );

		createTaskEvent.setCaseAssignId( activeCase.getCaseAssignmentId() );
		createTaskEvent.setSupervisionOrderId( activeCase.getSupervisionOrderId() );
		
		createTaskEvent.setCourtId( activeCase.getCourtId() );
		createTaskEvent.setDefendantId( activeCase.getDefendantId() );
		createTaskEvent.setCriminalCaseId( activeCase.getCriminalCaseId() );
		createTaskEvent.setSuperviseeName( activeCase.getSuperviseeName() );
		createTaskEvent.setSubject2( subject2.toString() );
		createTaskEvent.setTaskSubject( "Transfer casefile" );
		createTaskEvent.setTastText( taskText );
		createTaskEvent.setStaffPositionId( staffPositionId );
		createTaskEvent.setTopic(CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP);
		createTaskEvent.setDueDate(calendar.getTime());
		
		MessageUtil.postRequest( createTaskEvent );
	}
	
 }// End of class
