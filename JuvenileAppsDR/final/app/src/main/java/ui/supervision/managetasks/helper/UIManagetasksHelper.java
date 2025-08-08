/*
 * Created on March 2, 2007
 *
 */
package ui.supervision.managetasks.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdstaffposition.GetStaffPositionsByUserIdEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.GetCSTaskByOIDEvent;
import messaging.managetask.GetCSTaskListAdvancedSearchEvent;
import messaging.managetask.GetCSTaskListEvent;
import messaging.managetask.GetCSTasksEvent;
import messaging.managetask.UpdateCSTaskEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import messaging.manageworkgroup.GetWorkGroupsByUserEvent;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.organization.GetDivisionForAgencyEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import messaging.task.TransferTaskEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CSTaskControllerServiceNames;
import naming.OrganizationControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.TaskControllerServiceNames;
import naming.UIConstants;
import naming.WorkGroupControllerServiceNames;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managetasks.form.TasksSearchForm;

/**
 * @author Ryoung
 * 
 * This is a utility class for common operations required in Manage Tasks - UI classes.
 *  
 */
public class UIManagetasksHelper
{

	private static final ThreadLocal<UIManagetasksHelper> localTaskHelper =
		new ThreadLocal(){
		
			public UIManagetasksHelper initialValue(){

				System.out.println("Creating UIManageTaskHelper for Thread : " + Thread.currentThread().getName());
				return new UIManagetasksHelper();
			}
	};	
	
	public static UIManagetasksHelper getInstance() {
		
		return localTaskHelper.get();
	}
	
	/**
	 * 
	 * @param agencyId
	 * @return
	 */
    public List fetchDivisionForAgency(String agencyId)
    {
        GetDivisionForAgencyEvent divisionEvent = (GetDivisionForAgencyEvent) EventFactory
                .getInstance(OrganizationControllerServiceNames.GETDIVISIONFORAGENCY);
 
        divisionEvent.setAgencyCode(agencyId);
 
        List divisions = MessageUtil.postRequestListFilter(divisionEvent, GetDivisionForAgencyResponseEvent.class);
        
        List divisionList = new ArrayList();
        Iterator divIter = divisions.iterator();
        while (divIter.hasNext()){
            
            GetDivisionForAgencyResponseEvent  divResponse = (GetDivisionForAgencyResponseEvent)divIter.next();
            divisionList = (List) divResponse.getAgencyDivisionsCollection();
        }
 
        return divisionList;
 
    }
    
    /**
     * 
     * @param tsForm
     * @param agencyId
     * @param logonId
     * @return
     */
    public List getUsersPosition(TasksSearchForm tsForm, String agencyId, String logonId){
        
        
        List idlist = new ArrayList();
        
	    GetStaffPositionsByUserIdEvent getStaffEvent = (GetStaffPositionsByUserIdEvent) 
	    	EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONSBYUSERID);
	    
	    getStaffEvent.setAgencyId(agencyId);
	    idlist.add(logonId);
	    getStaffEvent.setLogonIds(idlist);
	    
	    List aList = MessageUtil.postRequestListFilter(getStaffEvent, CSCDSupervisionStaffResponseEvent.class);
//	    if (aList != null && aList.size() > 0){
//	        
//	    }
	   
	    return aList;
    }
    
    /**
     * 
     * @param listOfWorkgroups
     * @return
     */
    public List getTasksByDiscriminant(List listOfWorkgroups)
    {

        GetCSTaskListEvent taskEvent = (GetCSTaskListEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.GETCSTASKLIST);

        taskEvent.setArrayOfWorkgroupIds(listOfWorkgroups.toArray());
        taskEvent.setDiscriminant(GetCSTaskListEvent.TASKS_FOR_WORKGROUP_DISCRIMINANT);

        List tasks = MessageUtil.postRequestListFilter(taskEvent, CSTaskResponseEvent.class);

        List newTasks = new ArrayList();

        if (tasks != null)
        {
            int len = tasks.size();
            for (int i = 0; i < len; i++)
            {

                CSTaskResponseEvent resEvent = (CSTaskResponseEvent) tasks.get(i);
                resEvent.setStatus( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASKSTATUS, resEvent.getStatusId()));
                newTasks.add(resEvent);
            
            }
        }

        taskEvent = null;
        
        tasks.clear();
        tasks = null;
        
        return newTasks;
       
    }

    /**
     * 
     * @param tsForm
     * @param owner
     * @param deptDesc
     * @return
     * @throws GeneralFeedbackMessageException 
     */
    public List getAdvancedSearchTasks(TasksSearchForm tsForm, String positionId, String deptDesc) throws GeneralFeedbackMessageException
    {

        GetCSTaskListAdvancedSearchEvent taskEvent = (GetCSTaskListAdvancedSearchEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.GETCSTASKLISTADVANCEDSEARCH);

        
        taskEvent.setTaskListTypeId(tsForm.getTasklistTypeId());
        taskEvent.setStaffPositionId( positionId );
		int ctr = 0;       
        List workGroupList = new ArrayList();
       	if (tsForm.getWorkgroupIds() != null){
        	for (int w = 0; w<tsForm.getWorkgroupIds().length; w++){
        		if (tsForm.getWorkgroupIds()[w] != "" && tsForm.getWorkgroupIds()[w].length() > 0){
        			workGroupList.add(ctr, tsForm.getWorkgroupIds()[w]);
        			ctr++;
        		}
        	}
       	}	
       	taskEvent.setWorkgroupIds(workGroupList);
       	

       	List taskStatusList = new ArrayList();
       	if (tsForm.getTaskStatusIds() != null){
       		ctr = 0;
        	for (int t = 0; t<tsForm.getTaskStatusIds().length; t++){
        		if (tsForm.getTaskStatusIds()[t] != "" && tsForm.getTaskStatusIds()[t].length() > 0){
        			taskStatusList.add(ctr, tsForm.getTaskStatusIds()[t]);
        			ctr++;
        		}
        	}
    	}
        taskEvent.setTaskStatusIds(taskStatusList);
        
        Calendar cal = Calendar.getInstance();

        Date xdate = tsForm.getCreateDate();
		if (xdate!=null){
			cal.setTime(xdate);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.MILLISECOND,1);
			taskEvent.setBeginCreateDate(cal.getTime());
		}

		xdate = tsForm.getCreateDate2();
		if (xdate!=null){
			cal.setTime(xdate);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			taskEvent.setEndCreateDate(cal.getTime());
		}
        
		xdate = tsForm.getDueDate();
		if (xdate!=null){
			cal.setTime(xdate);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.MILLISECOND,1);
			taskEvent.setBeginDueDate(cal.getTime());
		}
		
		xdate = tsForm.getDueDate2();
		if (xdate!=null){
			cal.setTime(xdate);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			taskEvent.setEndDueDate(cal.getTime());
		}
		if ( StringUtils.isNotEmpty( tsForm.getCourt() ) ) {
			taskEvent.setCourtId(tsForm.getCourt().toUpperCase());
		}
        taskEvent.setUserDepartmentDesc(deptDesc);
        taskEvent.setDefendantId( tsForm.getSpn());
        
        CompositeResponse response = MessageUtil.postRequest( taskEvent ) ;


        ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if(error != null) 
		{
			throw new GeneralFeedbackMessageException(error.getMessage());
		}
        
		List tasks = MessageUtil.compositeToList( response, CSTaskResponseEvent.class ) ;

		taskEvent = null;
		
        return tasks;
    }

    /**
     * 
     * @param resp
     * @param tsForm
     */
    public void populateTaskForm(CSTaskResponseEvent resp, TasksSearchForm tsForm)
    {
        if (resp != null)
        {
        	tsForm.clearTaskDetails();
        	
            tsForm.setName(resp.getSuperviseeName());
            tsForm.setSpn(resp.getDefendantId());
            tsForm.setCreateDate(resp.getCreateDate());
            tsForm.setDueDate(resp.getDueDate());
            tsForm.setCourt(resp.getCourtId());
            tsForm.setNextAction(resp.getScenario());
            tsForm.setStatusChangeDate(resp.getStatusChangeDate());
            String statusChangeUserId = "";
            String statusChangePosition = "";
            statusChangeUserId = resp.getStatusChangeUserId();
            if (statusChangeUserId != null && !"".equals(statusChangeUserId)){            	
            	LightCSCDStaffResponseEvent respEvent = fetchCSStaffposition( statusChangeUserId );
            	if ( respEvent != null){
            		tsForm.setStatusChangeUser(respEvent.getOfficerName());
            		statusChangePosition = " /  " + respEvent.getStaffPositionName();
            		tsForm.setStatusChangeUserPos(statusChangePosition);
            	}
            }
            tsForm.setLastTransferDate(resp.getLastTransferDate());

            String lastTransferUserId = "";
            String lastTransferPosition = "";
            lastTransferUserId = resp.getLastTransferUserId();
            if ( lastTransferUserId != null && !"".equals(lastTransferUserId)){            	
            	LightCSCDStaffResponseEvent respEvent = fetchCSStaffposition( lastTransferUserId );
            	if ( respEvent != null){
         			tsForm.setLastTransferUser(respEvent.getOfficerName());
        			lastTransferPosition = " /  " + respEvent.getStaffPositionName();
            		tsForm.setLastTransferUserPos( lastTransferPosition );
        		}
            }
            tsForm.setTaskId(resp.getTaskId());
            tsForm.setTaskTopic(resp.getTopic());
           
           if (resp.getStatusId() != null)
            {
                
                Iterator codeIter = CodeHelper.getCodes(PDCodeTableConstants.TASKSTATUS).iterator();
				while(codeIter.hasNext())
				{
					CodeResponseEvent codeResp = (CodeResponseEvent) codeIter.next();
					if(codeResp.getCode().equals(resp.getStatusId()))
					{
					    tsForm.setStatus(codeResp.getDescription());
						
					}
				}  
            }

            tsForm.setSubject(resp.getTaskSubject());
            tsForm.setTaskSubject( resp.getSubject2() );
            tsForm.setTaskText( resp.getTaskText() );


        }
    }

    /**
     * 
     * @param positionId
     * @return
     */
    public List getTasksByDiscriminant( String positionId )
    {

        GetCSTaskListEvent taskEvent = (GetCSTaskListEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.GETCSTASKLIST);

        taskEvent.setPositionId( positionId );
        taskEvent.setDiscriminant(GetCSTaskListEvent.TASKS_FOR_POSITION_DISCRIMINANT);

        List tasks = MessageUtil.postRequestListFilter(taskEvent, CSTaskResponseEvent.class);

        List newTasks = new ArrayList();

        if (tasks != null)
        {
            int len = tasks.size();
            for (int i = 0; i < len; i++)
            {

                CSTaskResponseEvent resEvent = (CSTaskResponseEvent) tasks.get(i);
                String statusId = resEvent.getStatusId();
                if (!"C".equals(statusId) ){
                	
                    resEvent.setStatus( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASKSTATUS, resEvent.getStatusId()));
                    newTasks.add(resEvent);
                	
                }

            }
        }

        taskEvent = null;
        tasks = null;
        
        return newTasks;
    }

    /**
     * 
     * @param logonId
     * @return
     */
    public List getTasksByUser(String logonId)
    {

        GetCSTaskListEvent taskEvent = (GetCSTaskListEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.GETCSTASKLIST);

        taskEvent.setOwnerId(logonId);
        taskEvent.setDiscriminant(GetCSTaskListEvent.TASKS_FOR_POSITION_DISCRIMINANT);

        List tasks = MessageUtil.postRequestListFilter(taskEvent, CSTaskResponseEvent.class);

        List newTasks = new ArrayList();

        if (tasks != null)
        {
            int len = tasks.size();
            for (int i = 0; i < len; i++)
            {

                CSTaskResponseEvent resEvent = (CSTaskResponseEvent) tasks.get(i);
                resEvent.setStatus( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASKSTATUS, resEvent.getStatusId()));
                newTasks.add(resEvent);
            
            }
        }

        taskEvent = null;
        tasks = null;
        
        return newTasks;
    }

    /**
     * 
     * @param taskId
     * @return
     */
    public  CSTaskResponseEvent getTasksByOID( String taskId )
    {

    	CSTaskResponseEvent tasksResponse = null;
        GetCSTaskByOIDEvent oidEvent = (GetCSTaskByOIDEvent) EventFactory
                .getInstance(CSTaskControllerServiceNames.GETCSTASKBYOID);

        oidEvent.setTaskId( taskId );
        
        tasksResponse = (CSTaskResponseEvent) MessageUtil.postRequest( oidEvent, CSTaskResponseEvent.class );

        oidEvent = null;
        return tasksResponse;
    }

   
    
    /**
     * 
     * @param agencyId
     * @return
     */
    public List fetchWorkgroups(String agencyId)
    {

        GetWorkGroupsEvent requestEvent = (GetWorkGroupsEvent) EventFactory
                .getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);

        requestEvent.setAgencyId(agencyId);
         
        List workgroupList = MessageUtil.postRequestListFilter(requestEvent, WorkGroupResponseEvent.class);

        return workgroupList;

    }

     /**
      * 
      * @param userId
      * @param agencyId
      * @return
      */   
    public List fetchWorkgroupsByUser(String userId, String agencyId)
    {

        GetWorkGroupsByUserEvent requestEvent = (GetWorkGroupsByUserEvent) EventFactory
                .getInstance(WorkGroupControllerServiceNames.GETWORKGROUPSBYUSER);

        requestEvent.setAgencyId(agencyId);
        requestEvent.setLogonId(userId);

        
        List workgroupList = MessageUtil.postRequestListFilter(requestEvent, WorkGroupResponseEvent.class);
        
        Collections.sort(workgroupList, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (!(o1 instanceof WorkGroupResponseEvent)) 
					throw new ClassCastException();
				if (!(o2 instanceof WorkGroupResponseEvent)) 
					throw new ClassCastException();
				String name1 = ((WorkGroupResponseEvent) o1).getWorkgroupName();
				String name2 = ((WorkGroupResponseEvent) o2).getWorkgroupName();
				if (name1 == null) {
					name1 = "";
				}
				if (name2 == null) {
					name2 = "";
				}
				return name1.compareTo(name2);
			}
			public boolean equals(Object o1) {
				if ((o1 instanceof Comparator) && (this == o1)) 
					return true;
				else
					return false;
			}
		}
        );

        return workgroupList;

    }

    /**
     * 
     * @param agencyId
     * @param type
     * @param name
     * @return
     */
    public List fetchWorkgroupByName(String agencyId, String type, String name)
    {

        GetWorkGroupsEvent requestEvent = (GetWorkGroupsEvent) EventFactory
                .getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);

        requestEvent.setAgencyId(agencyId);
        requestEvent.setName(name);
        requestEvent.setType(type);

        List workgroupList = MessageUtil.postRequestListFilter(requestEvent, WorkGroupResponseEvent.class);
        

        return workgroupList;

    }

    /**
     * 
     * @param logonId
     * @return
     */
    public LightCSCDStaffResponseEvent fetchCSStaffposition(String logonId)
    {
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(logonId);	
		ev.setOfficerNameNeeded(true);
		return (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
    }
  
    /**
     * 
     * @param tasks
     * @param taskId
     * @return
     */
    public CSTaskResponseEvent fetchTaskFromList(Collection tasks, String taskId)
    {

        if (taskId != null && !taskId.equals(""))
        {
            Iterator iter = tasks.iterator();
            while (iter.hasNext())
            {
                CSTaskResponseEvent taskEvent = (CSTaskResponseEvent) iter.next();
                if (taskEvent.getTaskId().equalsIgnoreCase(taskId))
                {
                	tasks.remove( taskEvent );
                    return taskEvent;
                }
            }
        }
        return null;
     }
    
    /**
     * 
     * @param event
     * @return
     */
    protected CompositeResponse postRequestEvent(RequestEvent event)
    {
        if (event == null)
        {
            return null;
        }
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(event);
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        MessageUtil.processReturnException(response);
        return response;
    }
    
    /**
     * 
     * @param tsForm
     * @param statusId
     */
    public void updateTaskStatus( TasksSearchForm tsForm, String statusId, String flow ) {
        
		String taskId = tsForm.getTaskId();
		
		UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory
				.getInstance( CSTaskControllerServiceNames.UPDATECSTASK );
		
		updateTaskEvent.setCsTaskId( taskId );
		updateTaskEvent.setStatusId(statusId);
		updateTaskEvent.setStaffPositionId( tsForm.getCurrentUserStaffPositionId() );
		
		if ( "ACCEPT".equals( flow )){
			
			updateTaskEvent.setAcceptTask(true);
			updateTaskEvent.setCloseTask(false);
			
		}else {
			
		if ( "CLOSE".equals(flow)){
				
				updateTaskEvent.setAcceptTask(false);
				updateTaskEvent.setCloseTask(true);
		 } else {
			 
			 updateTaskEvent.setAcceptTask(false);
			 updateTaskEvent.setCloseTask(false);
		 }
		} 

		MessageUtil.postRequest(updateTaskEvent);
	}
    
  public void updateCSTaskStatus(TasksSearchForm tsForm) {
        
	    ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String userId = userInfo.getJIMSLogonId();
		String csTaskId = tsForm.getTaskId();
		
		UpdateCSTaskEvent updateCSTaskEvent = (UpdateCSTaskEvent) EventFactory
				.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
		
		long now = System.currentTimeMillis();
		java.sql.Date transferDate = new java.sql.Date(now);
		
		updateCSTaskEvent.setCsTaskId(csTaskId);
		updateCSTaskEvent.setLastTransferDate(transferDate);
		updateCSTaskEvent.setLastTransferUser(userId);
		MessageUtil.postRequest(updateCSTaskEvent);
	}
    
    /**
     * 
     * @param ntTaskId
     * @param taskOwnerId
     */
    public void transferTask(String ntTaskId, String taskOwnerId) {
        
        TransferTaskEvent transTaskEvent = (TransferTaskEvent) EventFactory
        .getInstance(TaskControllerServiceNames.TRANSFERTASK);
        
        transTaskEvent.setTaskId(ntTaskId);
        transTaskEvent.setOwnerId(taskOwnerId);

        MessageUtil.postRequest(transTaskEvent);
	}

    /**
     * 
     * @param TasksSearchForm
     * @return
     */
    public static List getTasksByWorkGroupIds(TasksSearchForm tsForm)
    {

        GetCSTaskListEvent taskEvent = (GetCSTaskListEvent) EventFactory.getInstance(CSTaskControllerServiceNames.GETCSTASKLIST);

        List workGroupList = new ArrayList();
        List taskStatusList = new ArrayList();
        List severityLevelList = new ArrayList();
       
        if (UIConstants.TASK_LIST_TYPE_WORKGROUP.equals(tsForm.getTaskId() )){ 
        	workGroupList.add(tsForm.getWorkgroupIds());
        	taskEvent.setArrayOfWorkgroupIds(workGroupList.toArray());
        }
        if (UIConstants.TASK_LIST_TYPE_ACTIONLIST.equals(tsForm.getTasklistTypeId()) ){
 // add coding to load Division, Supervisor and Officer/Staff        	
        }
        
        taskStatusList.add(tsForm.getTaskStatusIds());
//        taskEvent.setArrayOfTasksStatusIds(taskStatusList.toArray());
        
        Calendar cal = Calendar.getInstance();
        Date date1 = tsForm.getCreateDate();
        Date date2 = tsForm.getCreateDate2();
        
		if (date1!=null){
			cal.setTime(date1);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.MILLISECOND,1);
		}
		if (date2!=null){
			cal.setTime(date2);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
		}
        
		date1 = tsForm.getDueDate();
		date2 = tsForm.getDueDate2();
		if (date1!=null){
			cal.setTime(date1);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.MILLISECOND,1);
		}
		if (date2!=null){
			cal.setTime(date2);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
		}
		
        severityLevelList.add(tsForm.getSeverityLevelIds());
        
        taskEvent.setDiscriminant(GetCSTaskListEvent.TASKS_FOR_WORKGROUP_DISCRIMINANT);

        List tasks = MessageUtil.postRequestListFilter(taskEvent, CSTaskResponseEvent.class);
        List newTasks = new ArrayList();

        if (tasks != null)
        {
            for (int i = 0; i < tasks.size(); i++)
            {
                CSTaskResponseEvent resEvent = (CSTaskResponseEvent) tasks.get(i);
                String taskStatusDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASK_STATUS, resEvent.getStatusId());
                resEvent.setStatus(taskStatusDesc);
                newTasks.add(resEvent);
            }
        }

        return newTasks;
    }
    
    /**
	 * 
	 * @param spn
	 * @param crimCaseId
	 * @return
	 */
	public boolean hasCurrentTasks( String spn , String crimCaseId ){
		
		boolean hasTasks = false;
		
		GetCSTasksEvent request = (GetCSTasksEvent) EventFactory
        							.getInstance(CSTaskControllerServiceNames.GETCSTASKS );
		
		request.setDefendantId( spn );
		request.setCriminalCaseId( crimCaseId );
		
		CSTaskResponseEvent taskResponse = (CSTaskResponseEvent) 
									MessageUtil.postRequest( request, CSTaskResponseEvent.class );
		if ( taskResponse != null ){
			
			hasTasks = true;
		}
		return hasTasks;
	}
}// END CLASS
