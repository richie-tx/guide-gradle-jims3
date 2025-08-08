package ui.supervision.administercaseload.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.administercaseload.GetActiveSuperviseeCasesEvent;
import messaging.administercaseload.GetCaseAssignmentEvent;
import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.GetCaseloadSummaryEvent;
import messaging.administercaseload.UpdateCaseAssignmentEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.CaseloadSummaryResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.managetask.UpdateCSTaskEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSTaskControllerServiceNames;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.CasenoteControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.TaskControllerServiceNames;
import naming.UIConstants;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

/**
 * @author Jim Fisher
 */
public class CaseAssignmentDirector
{
    private CaseAssignmentForm form;

    public CaseAssignmentDirector(CaseAssignmentForm aForm)
    {
        this.form = aForm;
    }

    public void reviewAssignmentActiveCases()
    {
        //GET CURRENT CASE TO BE ACKNOWLEDGED
        GetCaseAssignmentEvent getCaseAssignmentEvent = (GetCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.GETCASEASSIGNMENT);

        getCaseAssignmentEvent.setSupervisionOrderId(form.getSupervisionOrderId());
        getCaseAssignmentEvent.setCaseAssignmentId(form.getCaseAssignmentId());

        CaseAssignmentResponseEvent caseAssignResponse = (CaseAssignmentResponseEvent) MessageUtil.postRequest(
                getCaseAssignmentEvent, CaseAssignmentResponseEvent.class);

        List assignments = caseAssignResponse.getCaseAssignments();

        if (assignments.size() == 1)
        {
            // expecting only one caseAssignment in the list
            ICaseAssignment currentAssignment = (ICaseAssignment) assignments.get(0);

            form.setCaseToAcknowledge(currentAssignment);
            form.setDefendantId(currentAssignment.getDefendantId());
            form.setSuperviseeName(currentAssignment.getDefendantName());
            form.setCaseNum(currentAssignment.getCriminalCaseId());
            // Set these values based on the acknowledgeRoleCode
            // form.setSupervisorName(currentAssignment.getSupervisorName());
            // form.setOfficerName(currentAssignment.getOfficerName());
            form.setDefendantId(currentAssignment.getDefendantId());
            form.setProgramUnitId(currentAssignment.getProgramUnitId());
            form.setProgramUnitName(currentAssignment.getProgramUnitName());

            //GET ACTIVE ASSIGNED CASES
            GetActiveSuperviseeCasesEvent event = (GetActiveSuperviseeCasesEvent) EventFactory
                    .getInstance(CaseloadControllerServiceNames.GETACTIVESUPERVISEECASES);

            event.setDefendantId(form.getDefendantId());
            event.setFilteredByCaseStatus(true);
            event.setQueryName(true);

            CompositeResponse response = MessageUtil.postRequest(event);

            CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) MessageUtil.filterComposite(
                    response, CaseAssignmentResponseEvent.class);

            form.setActiveCases(assignmentResponse.getCaseAssignments());

        }
    }
    public List getActiveCaseAssignmentsForSupervisee(){
        GetActiveSuperviseeCasesEvent event = (GetActiveSuperviseeCasesEvent) EventFactory
        .getInstance(CaseloadControllerServiceNames.GETACTIVESUPERVISEECASES);

        event.setDefendantId(form.getDefendantId());
        event.setFilteredByCaseStatus(true);
        event.setQueryName(true);

        CompositeResponse response = MessageUtil.postRequest(event);

        CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) MessageUtil.filterComposite(
        		response, CaseAssignmentResponseEvent.class);
        
        List activeCaseAssignments = null;
        if (assignmentResponse != null){
        	activeCaseAssignments = assignmentResponse.getCaseAssignments();
        }
        return activeCaseAssignments;
    }
    public void updateWorkgroupPaperFileReceived()
    {
        ICaseAssignment bean = new CaseAssignmentTO();

        // TODO Validate
        bean.setCaseAssignmentId(form.getCaseAssignmentId());
        bean.setAcknowledgeUserId(UIUtil.getCurrentUserID());
        bean.setAcknowledgeRoleCode(CaseloadConstants.OP_ACKNOWLEDGE_WORKGROUP_ASSIGNMENT);
        bean.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_VERIFIED);
        
        UpdateCaseAssignmentEvent updateEvent = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
        updateEvent.setCaseAssignmentTransactionCode(CaseloadConstants.WF_PAPERFILE_RECEIVED);

        updateEvent.setCaseAssignment(bean);

        MessageUtil.postRequest(updateEvent);
    }

    public void updateSupervisorPaperFileReceived()
    {
        ICaseAssignment bean = new CaseAssignmentTO();
        bean.setCaseAssignmentId(form.getCaseAssignmentId());
        bean.setAcknowledgePositionId(form.getSupervisorPositionId());
        bean.setAcknowledgeRoleCode(CaseloadConstants.OP_ACKNOWLEDGE_SUPERVISOR_ASSIGNMENT);
        bean.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_VERIFIED);

        UpdateCaseAssignmentEvent updateEvent = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
        updateEvent.setCaseAssignmentTransactionCode(CaseloadConstants.WF_PAPERFILE_RECEIVED);
        updateEvent.setCaseAssignment(bean);

        MessageUtil.postRequest(updateEvent);
    }

    public void updateOfficerPaperFileReceived()
    {
        ICaseAssignment bean = new CaseAssignmentTO();
        bean.setAcknowledgePositionId(form.getOfficerPositionId());
        bean.setAcknowledgeRoleCode(CaseloadConstants.OP_ACKNOWLEDGE_SUPERVISOR_ASSIGNMENT);
        bean.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_VERIFIED);
        
        UpdateCaseAssignmentEvent updateEvent = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
        updateEvent.setCaseAssignmentTransactionCode(CaseloadConstants.WF_PAPERFILE_RECEIVED);
        updateEvent.setCaseAssignment(bean);

        MessageUtil.postRequest(updateEvent);
    }

    /**
     * Queries for a list of caseloads. The caseloads are typed with a CaseAssignmentResponseEvent.
     * 
     * This response event contains the header information (e.g. SPN, defendantName, etc...)
     * 
     * Contained within each response, is a list of ICaseAssignment records. These case assignments
     * represent a case that has been assigned to an offier.
     * 
     * i.e. Officer Caseload Supervisee A
     *        
     */
    public void setCaseloadSupervisees()
    {
        GetCaseloadEvent event = (GetCaseloadEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETCASELOAD);
        event.setWorkflowInd(form.getSearchCaseloadBySelected());
        if (CaseloadConstants.SEARCH_CASELOAD_BY_NAME.equals(form.getSearchCaseloadBySelected()))
        { 
            event.setSupervisorFirstName(form.getSupervisorFirstName());
            event.setSupervisorLastName(form.getSupervisorLastName());
        }
        else if (CaseloadConstants.SEARCH_CASELOAD_BY_SPN.equals(form.getSearchCaseloadBySelected()))
        {
            event.setDefendantId(form.getDefendantId());
        }
        else if (CaseloadConstants.SEARCH_CASELOAD_BY_OFFICER.equals(form.getSearchCaseloadBySelected()))
        {
            event.setOfficerPositionId(form.getOfficerPositionId());
        }

        List caseloads = MessageUtil.postRequestListFilter(event, CaseAssignmentResponseEvent.class);
        
        form.setCaseloads(caseloads);
    }

    /**
     *  
     */
    public void assignSuperviseeToCSO()
    {
        // TODO validate
        UpdateCaseAssignmentEvent updateCaseAssignment = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);
        updateCaseAssignment.setCaseAssignmentTransactionCode(CaseloadConstants.WF_PAPERFILE_RECEIVED);
        
        ICaseAssignment caseAssignment = new CaseAssignmentTO();
        caseAssignment.setSupervisionOrderId(form.getSupervisionOrderId());
        caseAssignment.setAcknowledgePositionId(form.getOfficerPositionId());
        caseAssignment.setAcknowledgeStatusCode(CaseloadConstants.ACKNOWLEDGMENT_STATUS_VERIFIED);

        updateCaseAssignment.setCaseAssignment(caseAssignment);
        MessageUtil.postRequest(updateCaseAssignment);
    }

    /**
     *  
     */
    public void createCasenote()
    {
        UpdateCasenoteEvent updateCasenote = (UpdateCasenoteEvent) EventFactory
                .getInstance(CasenoteControllerServiceNames.UPDATECASENOTE);
        updateCasenote.setNotes(form.getCasenoteText());
        updateCasenote.setEntryDate(new Date());
        updateCasenote.setSupervisionOrderId(form.getSupervisionOrderId());

        String assignmentCodeId = getCasenoteSubjectCodeId();
        Collection subjects = new ArrayList();
        subjects.add(assignmentCodeId);

        updateCasenote.setSubjects(subjects);

        updateCasenote.setContactMethodId(CaseloadConstants.NONE_OTHER_CONTACTMETHOD);

        //   updateCasenote.setContextType(CaseloadConstants.SUPERVISEE_CONTEXT);
        //updateCasenote.setContextType(CasenoteContext.SUPERVISEE);
        updateCasenote.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);

        MessageUtil.postRequest(updateCasenote);
    }

    /**
     *  
     */
    private String getCasenoteSubjectCodeId()
    {
        GetSupervisionCodesEvent request = (GetSupervisionCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETSUPERVISIONCODES);
        request.setCodeTableName(CaseloadConstants.CASENOTE_SUBJECT_CODE_TABLE_NAME);
        request.setCode(CaseloadConstants.ASSIGNMENT_SUBJECT_CD);
        request.setAgencyId(SecurityUIHelper.getUserAgencyId());

        CodeResponseEvent codeResponse = (CodeResponseEvent) MessageUtil.postRequest(request, CodeResponseEvent.class);

        return codeResponse.getCodeId();
    }

    /**
     *  
     */
    public void updateTaskStatusToClosed()
    {
        UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.UPDATECSTASK);

        updateTaskEvent.setCsTaskId( form.getTaskId() );
        updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
        updateTaskEvent.setCloseTask( true );

        MessageUtil.postRequest(updateTaskEvent);
    }

    /**
     *  
     */
    public void updateTaskStatusToAccepted()
    {
    	UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory
        .getInstance(CSTaskControllerServiceNames.UPDATECSTASK);

        updateTaskEvent.setCsTaskId( form.getTaskId() );
        updateTaskEvent.setStatusId(UIConstants.ACCEPTED_STATUS_ID);
        updateTaskEvent.setAcceptTask( true );

        MessageUtil.postRequest(updateTaskEvent);
    }

    /**
     *  
     */
    public void createNewProcessOfficerNewCaseAssignmentTask()
    {
        CreateCSTaskEvent createTask = (CreateCSTaskEvent) 
        								EventFactory.getInstance(CSTaskControllerServiceNames.CREATECSTASK);

        /*
         * // TODO Convert topic String literal to constant
         * createTask.setTopic("CS_PROCESS_OFFICER_NEW_CASE_ASSIGNMENT"); // TaskAction: Process
         * officer new case assignment // TODO confirm TO formula // TO: <Caseload position
         * selected> createTask.setOwnerId("CSPOS_" + form.getOfficerPositionId()); // TODO Check
         * subject vs. subject2: createTask.setSubject(CaseloadConstants.NEW_ORDER_FOR_SUPERVISION);
         * createTask.setTaskSubject("Process officer new case assignment"); // Text: New Order for
         * Supervision // <SuperviseeLastName>, <SuperviseeFirstName>, <SuperviseeMiddleName>, //
         * <SuperviseeSPN>, <Case#>, <Court#>
         */

        String ownerId = form.getWorkGroupId();
        String taskSubject = CaseloadConstants.NEW_ORDER_FOR_SUPERVISION;

        //      TODO retrieve from message bundle
        StringBuffer buffer = new StringBuffer();
        buffer.append(CaseloadConstants.NEW_ORDER_FOR_SUPERVISION);
        buffer.append("\n");
        if( StringUtils.isNotEmpty(form.getSuperviseeName().getLastName()) ) {
	        buffer.append(form.getSuperviseeName().getLastName());
	        buffer.append(", ");
        }
        if( StringUtils.isNotEmpty(form.getSuperviseeName().getFirstName()) ) {
	        buffer.append(form.getSuperviseeName().getFirstName());
	        buffer.append(", ");
        }
        if( StringUtils.isNotEmpty(form.getSuperviseeName().getMiddleName()) ) {
	        buffer.append(form.getSuperviseeName().getMiddleName());
	        buffer.append(", ");
        }
        buffer.append("SPN ");
        buffer.append(form.getDefendantId());
        buffer.append(", CASE ");
        buffer.append(form.getCaseNum());
        buffer.append(", CRT ");
        buffer.append(form.getCourtNumber());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);

        createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP );
        createTask.setWorkGroupId( ownerId );
        createTask.setTaskSubject(taskSubject);
        createTask.setDueDate(cal.getTime());
        createTask.setStatusId("O");

        createTask.setTastText( buffer.toString() );
        createTask.setScenario( CaseloadConstants.SC_ASSIGN_NEW_CASE );
        //createTask.addTaskStateItem(CaseloadConstants.ACTIVITY_IND, CaseloadConstants.WF_PROCESS_OFFICER_NEW_CASE_ASSIGNMENT);
        createTask.setSupervisionOrderId( form.getSupervisionOrderId() );
        createTask.setCaseAssignId( form.getCaseAssignmentId() );
        createTask.setDefendantId( form.getDefendantId() );
        createTask.setSuperviseeName( form.getSuperviseeNameStr() );

        MessageUtil.postRequest( createTask );
        createTask = null;
        ownerId = null;
        taskSubject = null;
        buffer = null;
    }

    /**
     *  
     */
    public void setCaseloads()
    {
        GetCaseloadSummaryEvent event = (GetCaseloadSummaryEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.GETCASELOADSUMMARY);

        event.setSupervisorPositionId(form.getSupervisorPositionId());

        CaseloadSummaryResponseEvent response = (CaseloadSummaryResponseEvent) MessageUtil.postRequest(event,
                CaseloadSummaryResponseEvent.class);

        form.setCaseloads(response.getCaseloads());
    }

    /**
     *  
     */
    public void setCasenotePrefillAssignOfficer()
    {
        // TODO get this text from a message bundle

        // casenote prefill
        // "<Case#>, <Court> has been assigned to <SupervisingOfficer> on
        // [CreateDate], [CreateTime]";

        StringBuffer buffer = new StringBuffer();
        buffer.append("CASE ");
        buffer.append(form.getCaseNum());
        buffer.append(", CRT ");
        buffer.append(form.getCourtNumber());
        buffer.append(" has been assigned to ");
        buffer.append(form.getOfficerName());
        buffer.append(" on ");

        Date rightNow = new Date();
        buffer.append(DateUtil.dateToString(rightNow, CaseloadConstants.CASENOTE_DATE_FMT));

        form.setCasenoteText(buffer.toString());
        buffer = null;
    }

    /**
     * @param myForm
     */
    public void createNewAllocateSuperviseeToSupTask()
    {
        CreateCSTaskEvent createTask = ( CreateCSTaskEvent ) 
        								EventFactory.getInstance(TaskControllerServiceNames.CREATETASK);

        String ownerId = form.getWorkGroupId();
        String taskSubject = CaseloadConstants.NEW_ORDER_FOR_SUPERVISION;
        StringBuffer text = new StringBuffer();
        text.append(CaseloadConstants.NEW_ORDER_FOR_SUPERVISION);
        text.append(" ");
        text.append(form.getSuperviseeName().getFormattedName());
        text.append(", SPN ");
        text.append(form.getDefendantId());
        text.append(", CASE ");
        text.append(form.getCaseNum());
        text.append(", CRT ");
        text.append(form.getCourtNumber());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);

        createTask.setTopic(CaseloadConstants.CSTASK_TOPIC_ALLOCATE_SUPERVISEE_TO_SUP);
        createTask.setWorkGroupId( ownerId );
        createTask.setTaskSubject(taskSubject);
        createTask.setDueDate(cal.getTime());
        createTask.setStatusId("O");

        createTask.setTastText( text.toString() );
        createTask.setScenario( CaseloadConstants.SC_ASSIGN_NEW_CASE );
        //createTask.addTaskStateItem(CaseloadConstants.ACTIVITY_IND, CaseloadConstants.WF_ALLOCATE_SUPERVISEE_TO_SUPERVISOR);
        createTask.setSupervisionOrderId( form.getSupervisionOrderId() );
        createTask.setCaseAssignId( form.getCaseAssignmentId() );
        createTask.setDefendantId( form.getDefendantId() );
        createTask.setSuperviseeName( form.getSuperviseeNameStr() );

        MessageUtil.postRequest(createTask);
        createTask = null;
        ownerId = null;
        taskSubject = null;
        text = null;
    }

    /**
     * @param myForm
     */
    public void assignSuperviseeToProgramUnit()
    {
        UpdateCaseAssignmentEvent updateCaseAssignmentEvent = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);

        form.getCaseToAcknowledge().setProgramUnitId(form.getProgramUnitId());
        form.getCaseToAcknowledge().setProgramUnitName(form.getProgramUnitName());
        form.getCaseToAcknowledge().setSupervisionOrderId(form.getSupervisionOrderId());
        form.getCaseToAcknowledge().setSupervisionStyleCode(CaseloadConstants.SUPERVISION_STYLE_DIRECT);
        updateCaseAssignmentEvent.setCaseAssignmentTransactionCode(CaseloadConstants.WF_ASSIGN_SUPERVISEE_TO_PROGRAMUNIT);

        updateCaseAssignmentEvent.setCaseAssignment(form.getCaseToAcknowledge());

        CompositeResponse response = MessageUtil.postRequest(updateCaseAssignmentEvent);
        CaseAssignmentResponseEvent caseAssignmentResponseEvent = (CaseAssignmentResponseEvent) MessageUtil.filterComposite(
                response, CaseAssignmentResponseEvent.class);

        CaseAssignmentTO caseAssignment = (CaseAssignmentTO) caseAssignmentResponseEvent.getCaseAssignments().get(0);

        form.setCaseAssignmentId(caseAssignment.getCaseAssignmentId());
    }

     /**
     *  
     */
    public void handleSupervisorSelectList()
    {

    }

    /**
     * @param myForm
     */
    public void createNewAssignSuperviseeToOfficerTask()
    {
        CreateCSTaskEvent createTask = ( CreateCSTaskEvent ) EventFactory.getInstance(TaskControllerServiceNames.CREATETASK);

        String ownerId = form.getSupervisorPositionId();
        String taskSubject = CaseloadConstants.NEW_ORDER_FOR_SUPERVISION;
        StringBuffer text = new StringBuffer();
        text.append("New Order for Supervision ");
        text.append(form.getSuperviseeName().getFormattedName());
        text.append(", SPN ");
        text.append(form.getDefendantId());
        text.append(", CASE ");
        text.append(form.getCaseNum());
        text.append(", CRT ");
        text.append(form.getCourtNumber());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);

        createTask.setTopic( CaseloadConstants.CSTASK_TOPIC_ASSIGN_SUPERVISEE_TO_OFF );
        createTask.setStaffPositionId( ownerId );
        createTask.setTaskSubject(taskSubject);
        createTask.setDueDate(cal.getTime());
        createTask.setStatusId("O");

        createTask.setTastText( text.toString() );
        createTask.setScenario( CaseloadConstants.SC_ASSIGN_NEW_CASE );
        //createTask.addTaskStateItem(CaseloadConstants.ACTIVITY_IND, CaseloadConstants.WF_ASSIGN_SUPERVISEE_TO_OFFICER);
        createTask.setSupervisionOrderId( form.getSupervisionOrderId() );
        createTask.setCaseAssignId( form.getCaseAssignmentId() );
        createTask.setDefendantId( form.getDefendantId() );
        createTask.setSuperviseeName( form.getSuperviseeNameStr() );
        //createTask.addTaskStateItem(CaseloadConstants.SUPERVISOR_POSITION_ID, form.getSupervisorPositionId());
        //createTask.addTaskStateItem(CaseloadConstants.SUPERVISOR_FIRST_NAME, form.getSupervisorName().getFirstName());
        //createTask.addTaskStateItem(CaseloadConstants.SUPERVISOR_MIDDLE_NAME, form.getSupervisorName().getMiddleName());
        //createTask.addTaskStateItem(CaseloadConstants.SUPERVISOR_LAST_NAME, form.getSupervisorName().getLastName());

        MessageUtil.postRequest( createTask );
        createTask = null;
        ownerId = null;
        taskSubject = null;
        text = null;
    }

    /**
     * @param myForm
     */
    public void allocateSuperviseeToSupervisor()
    {
        UpdateCaseAssignmentEvent updateCaseAssignmentEvent = (UpdateCaseAssignmentEvent) EventFactory
                .getInstance(CaseloadControllerServiceNames.UPDATECASEASSIGNMENT);

        updateCaseAssignmentEvent.setCaseAssignmentTransactionCode(CaseloadConstants.WF_PAPERFILE_RECEIVED);

        updateCaseAssignmentEvent.setCaseAssignment(form.getCaseToAcknowledge());

        MessageUtil.postRequest(updateCaseAssignmentEvent);

        //        CompositeResponse response = MessageUtil.postRequest(updateCaseAssignmentEvent);
        //        
        //        CaseAssignmentResponseEvent caseAssignmentResponseEvent = (CaseAssignmentResponseEvent)
        // MessageUtil
        //                .filterComposite(response, CaseAssignmentResponseEvent.class);
        //
        //        CaseAssignmentTO caseAssignment = (CaseAssignmentTO)
        // caseAssignmentResponseEvent.getCaseAssignments().get(0);
        //
        //        form.setCaseAssignmentId(caseAssignment.getCaseAssignmentId());
    }
}
