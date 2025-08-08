//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\HandleTaskSearchAction.java

/*
 * Created on Mar 9, 2007
 */
package ui.supervision.managetasks.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.GetLightOrganizationStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import messaging.transferobjects.CSCDStaffPositionTO;
import messaging.transferobjects.UserProfileTO;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.managetasks.form.TasksSearchForm;
import ui.supervision.managetasks.helper.UIManagetasksHelper;
import ui.supervision.posttrial.form.CSCDTaskForm;

/**
 * @author hrodriguez
 */
public class HandleTaskSearchAction extends JIMSBaseAction
{

	
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.advancedSearch", "advancedSearch");
        keyMap.put("button.acceptTask", "acceptTask");
        keyMap.put("button.back", "back");
        keyMap.put("button.taskList", "basicSearch");
        keyMap.put("button.cancel", "basicSearch");
        keyMap.put("button.basicSearch", "basicSearch");
        keyMap.put("button.backToTasks", "basicSearch");
        keyMap.put("button.closeTask", "closeTask");
        keyMap.put("button.finish", "taskClose");
        keyMap.put("button.continueTask", "continueTask");
        keyMap.put("button.createTask", "createTask");
        keyMap.put("button.getOfficers", "getOfficers");
        keyMap.put("button.getSupervisors", "getSupervisors");
        keyMap.put("button.link", "link");
        keyMap.put("button.viewAssociatedTasks", "viewAssociatedTasks");
        keyMap.put("button.refresh", "refresh");
        keyMap.put("button.submit", "submit");
        keyMap.put("button.taskDetails", "taskDetails");
        keyMap.put("button.viewOrder", "viewOrder");
        keyMap.put("button.transferTask", "transferTask");
        keyMap.put("button.backToTaskSearchResults", "submit");

    }

    /**
     * 
     * @param staff
     * @return
     */
    public static CSCDSupervisionStaffResponseEvent getPositionObject(CSCDStaffPositionTO staff)
    {

        CSCDSupervisionStaffResponseEvent supStaff = new CSCDSupervisionStaffResponseEvent();

        UserProfileTO userProfile = (UserProfileTO) staff.getUserProfile();
        String userName = "NO OFFICER ASSIGNED";
        String positionName = "";
        if (userProfile != null)
        {
   
        	//Defect : JIMS200055217 Start
        	userName = (userProfile.getLastName()!= null ? userProfile.getLastName() : "" ) + ", ";
        	userName = userName + (userProfile.getFirstName()!= null ? userProfile.getFirstName() : "" ) + " ";
        	userName = userName + (userProfile.getMiddleName()!= null ? userProfile.getMiddleName() : "" );
        	//Defect : JIMS200055217 End
        	
            positionName = staff.getPositionName();
            if (positionName != null && !positionName.equals(""))
            {
                supStaff.setPositionName(userName + " | " + positionName);
            }
        }
        else
        {
            supStaff.setPositionName(userName + " |" + staff.getPositionName());
        }

        supStaff.setStaffPositionId(staff.getOID());
        supStaff.setParentPositionId(staff.getParentPositionId());
        supStaff.setDivisionId(staff.getOrganizationId());
        Collection children = staff.getChildStaffPositions();
        supStaff.setChildren(children);

        return supStaff;
    }

    //Future option
    public ActionForward acceptTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        UIManagetasksHelper helper = UIManagetasksHelper.getInstance();
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String userId = userInfo.getJIMSLogonId();

        String positionId = "";
        LightCSCDStaffResponseEvent respEvent = helper.fetchCSStaffposition(userId);

        if (respEvent != null)
        {
            positionId = respEvent.getStaffPositionId();
        }
        tsForm.setCurrentUserStaffPositionId( positionId );

        tsForm.setSelectedCSTask( null );
        
        CSTaskResponseEvent task = helper.getTasksByOID( tsForm.getTaskId() );
        tsForm.setSelectedCSTask( task );
        String fld = tsForm.getTasklistTypeId();
        helper.populateTaskForm( task, tsForm );
        tsForm.setTasklistTypeId(fld);
        fld = null;

        return aMapping.findForward(UIConstants.ACCEPT_SUCCESS);
    }

    //Pass to AdvancedSearch page
    //Display AdvancedSearch page
    public ActionForward advancedSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
        //Clears the Supervisors and officers when the page loads
   	    tsForm.setDivisionCollection( new ArrayList());
        tsForm.setSupervisors(new ArrayList());
        tsForm.setOfficerList(new ArrayList());

        tsForm.clearAll();
        tsForm.setWorkgroupIds(new String[0]);
        tsForm.setTaskStatusIds(new String[0]);
        tsForm.setSeverityLevelIds(new String[0]);
        
         
        GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
   		gEvent.setOfficerNameNeeded(true);
   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
   		
   		
   		if ( resp != null ){
   			
 		tsForm.setOrganizationId( resp.getDivisionId());
 		
 		if ( "SU".equals( resp.getStaffPositionType() )){
 			
 			tsForm.setStaffPositionId( resp.getStaffPositionId());
 			
 		}else {
 			
 			tsForm.setStaffPositionId( resp.getParentPositionId());
 	 		tsForm.setOfficerStaffId( resp.getStaffPositionId() );
 			
 		}
 
 		GetLightOrganizationStaffEvent divisionEvent = new GetLightOrganizationStaffEvent();
 		divisionEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
 		
 		List divisions = MessageUtil.postRequestListFilter(divisionEvent, CSCDSupervisionStaffResponseEvent.class);
 		
 		if (divisions != null && !divisions.isEmpty()){
    	   
    	   tsForm.setDivisionCollection( divisions);
 		}

 		// Set supervisor drop down
 		GetLightSupervisorsEvent glEvent = new GetLightSupervisorsEvent();
 		glEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		glEvent.setDivisionId( tsForm.getOrganizationId());
		glEvent.setOfficerInfoNeeded(true);
		
		List supervisorsInDivision = MessageUtil.postRequestListFilter(glEvent , CSCDSupervisionStaffResponseEvent.class);
		
		tsForm.setSupervisors( supervisorsInDivision );
		
		// Get staff under supervisor
		GetLightSupervisorsEvent officerEvent = new GetLightSupervisorsEvent();
		officerEvent.setSupervisorId( resp.getParentPositionId());
		officerEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		officerEvent.setSupportStaffNeeded(true);
		
		List staffUnderSupervisor = MessageUtil.postRequestListFilter(officerEvent , CSCDSupervisionStaffResponseEvent.class);
		
		tsForm.setOfficerList( staffUnderSupervisor );
		
   		}// end of user response
   		else{
   			
   		 this.sendToErrorPage(aRequest, "error.serviceProvider.invalidUser");
     			
   		}
        tsForm.setTasklistTypeId("AL");
        tsForm.setAction(UIConstants.ADVANCED);
        forward = aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
        return forward;
        
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    //Display the Basic search (default) page
    public ActionForward basicSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String userId = userInfo.getJIMSLogonId();
        String agencyId = userInfo.getAgencyId();

        TasksSearchForm tsForm = (TasksSearchForm) aForm;
      
        
//        tsForm.setCurrentUserStaffPositionId("248");
        Collection taskList = new ArrayList();
        UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
        Collection listOfWorkgroups = taskHelper.fetchWorkgroupsByUser(userId, agencyId);
        if (listOfWorkgroups != null)
        {

            tsForm.setWorkgroupList(listOfWorkgroups);
        }
        else
        {

            this.sendToErrorPage(aRequest, "error.noWorkgroupsfound");
            forward = aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
            return forward;
        }

        // Get tasks for userId and Position
        String userPosition = "";
		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
		gEvent.setLogonId(SecurityUIHelper.getLogonId());
		CompositeResponse resp = MessageUtil.postRequest(gEvent);
		
		LightCSCDStaffResponseEvent response = (LightCSCDStaffResponseEvent) MessageUtil.filterComposite(resp, LightCSCDStaffResponseEvent.class);

        if (response != null)
        {
            userPosition = response.getStaffPositionId();
        } else {
        	
        	sendToErrorPage(aRequest, "error.administerCaseload.userNotAssignedToStaffPosition");		    
		    return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
        	
        }

        List tasks = taskHelper.getTasksByDiscriminant(userPosition);
        taskList.addAll(tasks);

        if (tasks.size() == 0)
        {
            this.sendToErrorPage(aRequest, "error.noRecords");
        }

//        Collection userTasks = UIManagetasksHelper.getTasksByUser(userId);
//        if (!(userTasks.size() == 0))
//        {
//            taskList.addAll(userTasks);
//        }

        tsForm.setTasklistTypeId("AL");
        tsForm.setTaskResultList(taskList);
        tsForm.setAction(UIConstants.BASIC);
        return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * From the end of the flow return back to search results
     */
    public ActionForward backToSearchResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
        ActionForward forward = new ActionForward();       
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
       // Trying to restore previous search results.
        
             
        return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
    }

    //Forward to close task summary
    public ActionForward closeTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        UIManagetasksHelper helper = UIManagetasksHelper.getInstance();
        CSTaskResponseEvent task = 
        	helper.fetchTaskFromList(tsForm.getTaskResultList(), tsForm.getTaskId());
        helper.populateTaskForm(task, tsForm);

        return aMapping.findForward(UIConstants.CLOSE_SUCCESS);
    }
    
   
    public ActionForward continueTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.setSelectedCSTask( null );
        UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
        
        CSTaskResponseEvent task = taskHelper.getTasksByOID( tsForm.getTaskId() );
        tsForm.setSelectedCSTask( task );
        taskHelper.populateTaskForm( task, tsForm );
        
        task = null;
        return aMapping.findForward(UIConstants.CONTINUE_SUCCESS);
    }

    //Advanced search options
    public ActionForward getSupervisors(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        tsForm.setOfficerList(new ArrayList());
        tsForm.setSupervisors(new ArrayList());
        List supervisors = new ArrayList();
        
        GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
		gEvent.setDivisionId(tsForm.getOrganizationId());
		gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		
		supervisors = MessageUtil.postRequestListFilter(gEvent, CSCDSupervisionStaffResponseEvent.class);
		
		if (supervisors.size() < 1) {
		    sendToErrorPage(aRequest, "error.administerCaseload.supervisorsInDivisionNotFound");
		    
		    return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
	    }
    
		tsForm.setTasklistTypeId("AL");
        tsForm.setSupervisors(supervisors);
        return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
    }

    public ActionForward getOfficers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
        tsForm.setOfficerList(new ArrayList());
        List officerStaff = new ArrayList();
        
        GetLightSupervisorsEvent gEvent = new GetLightSupervisorsEvent();
		gEvent.setSupervisorId(tsForm.getStaffPositionId());
		gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		gEvent.setSupportStaffNeeded(true);
		
		officerStaff = MessageUtil.postRequestListFilter(gEvent, CSCDSupervisionStaffResponseEvent.class);
        
		if (officerStaff.size() < 1) {
		    sendToErrorPage(aRequest, "error.administerCaseload.officersUnderSupervisiorNotFound");
		    
		    return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
	    }
		
        tsForm.setTasklistTypeId("AL");
        tsForm.setOfficerList(officerStaff);
        return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 45DB5B2501CD
     */
    //Called from NavigationMenu, TasksTab and other usecases
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        // get current user's positionId for advanced search display
        tsForm.setCurrentUserStaffPositionId("");
		tsForm.setCurrentUserlogonId(SecurityUIHelper.getLogonId());		
		GetCSCDSupervisionStaffEvent staffEvent = 
			(GetCSCDSupervisionStaffEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);
		staffEvent.setStaffLogonId(SecurityUIHelper.getLogonId());
		CompositeResponse myResp=postRequestEvent(staffEvent);
		List staffPositions = MessageUtil.compositeToList(myResp, CSCDSupervisionStaffResponseEvent.class);
		if (staffPositions!=null && !staffPositions.isEmpty())
		{
			for (int x=0; x < staffPositions.size(); x++){
			CSCDSupervisionStaffResponseEvent staffPosRespEvt = (CSCDSupervisionStaffResponseEvent)staffPositions.get(x); //iter.next();
				tsForm.setCurrentUserStaffPositionId(staffPosRespEvt.getStaffPositionId());
			}
		}

        return basicSearch(aMapping, aForm, aRequest, aResponse);
    }
    
    public ActionForward viewAssociatedTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String userId = userInfo.getJIMSLogonId();
        String agencyId = userInfo.getAgencyId();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
        UIManagetasksHelper helper = UIManagetasksHelper.getInstance();
        //Fetch workgroups
        Collection listOfWorkgroups = helper.fetchWorkgroupsByUser(userId, agencyId);
        if (listOfWorkgroups != null)
        {

            tsForm.setWorkgroupList(listOfWorkgroups);
        }
        else
        {

            this.sendToErrorPage(aRequest, "error.noWorkgroupsfound");
            return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
        }
        
        String workgroupId = aRequest.getParameter("workgroupId");
        List workGroupList = new ArrayList();
        workGroupList.add(workgroupId);
        List wgTasks = helper.getTasksByDiscriminant(workGroupList);

        if (wgTasks.size() == 0)
        {
            this.sendToErrorPage(aRequest, "error.noRecords");
        }
        tsForm.setWorkgroupId(workgroupId);
        tsForm.setTasklistTypeId(UIConstants.TASK_LIST_TYPE_WORKGROUP);
        tsForm.setTaskResultList(wgTasks);

        return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     * @throws GeneralFeedbackMessageException
     */
    public ActionForward createTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.clearForm();
         
        CSCDTaskForm ctForm = (CSCDTaskForm) getSessionForm(aMapping, aRequest, "cscdTaskForm", true);
        ctForm.clearDefaultFormValues();
        
        return aMapping.findForward(UIConstants.CREATE_TASK_SUCCESS);
    }
    
    //Reset previous search criteria
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        String action = tsForm.getAction();

        if (UIConstants.BASIC.equals(action))
        {
            tsForm.refreshSearch();
        }
        else
        {
            tsForm.refreshAdvancedSearch();
            return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
        }

        return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
    }
    
   /* private int getStaffPositionCode(String officerType) {
    	
			GetSupervisionCodesEvent request = (GetSupervisionCodesEvent) EventFactory
					.getInstance(CodeTableControllerServiceNames.GETSUPERVISIONCODES);
			
			request.setCodeTableName(PDCodeTableConstants.STAFF_POSITION_TYPE);
			request.setCode(officerType);
			request.setAgencyId(SecurityUIHelper.getUserAgencyId());

			CodeResponseEvent codeResponse = (CodeResponseEvent) MessageUtil.postRequest(request, CodeResponseEvent.class);

		return Integer.parseInt(codeResponse.getCodeId());
	}*/

    //Process the Basic search
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//long startTime = System.currentTimeMillis();		
    	// Gets the logoId from the session
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String logonId = userInfo.getJIMSLogonId();
                
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        UIManagetasksHelper tskHelper = UIManagetasksHelper.getInstance();
        
        Collection taskList = new ArrayList();
        tsForm.setTaskResultList(taskList);

        String taskListType = tsForm.getTasklistTypeId();
        
 		// Check advanced search first
        String action = tsForm.getAction();
        if (UIConstants.ADVANCED.equalsIgnoreCase(action))
        {
        	if ( "SP".equalsIgnoreCase(taskListType) && "".equals( tsForm.getSpn()) ){
        		
        		this.sendToErrorPage(aRequest, "error.noRecords");
        		return aMapping.findForward( UIConstants.ADVANCED_SEARCH_SUCCESS );
        	}
        	// ryoung moved all advanced search out
            forward = this.findAdvancedSearchTasks(aMapping, aForm, aRequest, aResponse, mgr);
         }
        else
        {
            // Using basic search
            if (taskListType.equalsIgnoreCase("WG"))
            {
                if (tsForm.getWorkgroupIds().length >= 1)
                {
                    List wgIdList = new ArrayList();
                    String[] wgIds = tsForm.getWorkgroupIds();

                    int len = wgIds.length;
                    for (int i = 0; i < len; i++)
                    {
                        String wgroupId = wgIds[i];
                        wgIdList.add(wgroupId);
                    }
                    List wgTasks = tskHelper.getTasksByDiscriminant(wgIdList);

                    if (wgTasks.size() == 0)
                    {
                        this.sendToErrorPage(aRequest, "error.noRecords");
                    }
                    tsForm.setTasklistTypeId(UIConstants.TASK_LIST_TYPE_WORKGROUP);
                    tsForm.setTaskResultList(wgTasks);

                }
                forward = aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);

            }
            else
            {
                // Get tasks for userId and Position
                String userPosition = "";
                GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
        		gEvent.setLogonId(SecurityUIHelper.getLogonId());
        		LightCSCDStaffResponseEvent response = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);

                if (response != null)
                {
                    userPosition = response.getStaffPositionId();
                }

                List tasks = tskHelper.getTasksByDiscriminant(userPosition);
                taskList.addAll(tasks);

                if (tasks.size() == 0)
                {
                    this.sendToErrorPage(aRequest, "error.noRecords");
                }

                tsForm.setTasklistTypeId("AL");
                tsForm.setTaskResultList(taskList);
                tsForm.setAction(UIConstants.BASIC);
                forward = aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);

            }

        }
        //System.out.println("Elapsed Time= " + (System.currentTimeMillis()-startTime)/1000F);		
        return forward;
    }
    
   
    //Display Task Detail page
    public ActionForward taskDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.setSelectedCSTask( null );
        
        UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
        
        CSTaskResponseEvent task = taskHelper.getTasksByOID( tsForm.getTaskId() );
        tsForm.setSelectedCSTask( task );
        taskHelper.populateTaskForm( task, tsForm );
        
        task = null;

        return aMapping.findForward(UIConstants.VIEW_SUCCESS);
    }
    
    //Submit advanced searches
    public ActionForward findAdvancedSearchTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse, ISecurityManager mgr)
    {
        
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        String officerStaffId = tsForm.getOfficerStaffId();
        String staffPositionId = tsForm.getStaffPositionId();
        String ownerId = "";
        UIManagetasksHelper helper = UIManagetasksHelper.getInstance();
        /*
         * these checks needed becuase STRUTS is not updating the multiple select correctly, 
         * value set in js in jsp 
         */
               
        if ("N".equalsIgnoreCase(tsForm.getWorkgroupIdSelected())){
        	 tsForm.setWorkgroupIds(null);
        }
        if ("N".equalsIgnoreCase(tsForm.getTaskStatusIdSelected())){
       	 	tsForm.setTaskStatusIds(null);
        }
        if ("N".equalsIgnoreCase(tsForm.getSeverityLevelIdIdSelected())){
       	 	tsForm.setSeverityLevelIds(null);
        }
        

        if (officerStaffId != null && officerStaffId.length() > 0)
        {
            ownerId =  officerStaffId;
        }
        else if (staffPositionId != null && staffPositionId.length() > 0)
        {
            ownerId =  staffPositionId;
        }
	        
	   Collection tasks = new ArrayList();
	        
        try {
        	
			tasks = helper.getAdvancedSearchTasks(tsForm, ownerId, null);
			
        	} catch (GeneralFeedbackMessageException ex){
	   			ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ex.getMessage()));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
        	}

        if ( tasks.size() == 0 )
        {
        	this.sendToErrorPage(aRequest, "error.noRecords");
        }
        tsForm.setTaskResultList( tasks );
         
        return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
    }

    
    //  Future option
    public ActionForward transferTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        UIManagetasksHelper helper = UIManagetasksHelper.getInstance();
        CSTaskResponseEvent task =
        	helper.fetchTaskFromList(tsForm.getTaskResultList(), tsForm.getNtTaskId());
        helper.populateTaskForm(task, tsForm);

        return aMapping.findForward(UIConstants.TRANSFER_SUCCESS);
    }

    //Future option
    public ActionForward viewOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
//        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        return aMapping.findForward(UIConstants.VIEW_ORDER_SUCCESS);
    }

}
