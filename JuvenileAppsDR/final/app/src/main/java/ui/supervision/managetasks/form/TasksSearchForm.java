/*
 * Created on Mar 9, 2007
 */
package ui.supervision.managetasks.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import org.apache.struts.action.ActionForm;


//import ui.supervision.managetask.UITasksLoadCodeTables;

/**
 * @author hrodriguez
 */
public class TasksSearchForm extends ActionForm {

//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";

	private Collection divisionCollection = new ArrayList() ;
	//	Fields
	private Date createDate;
	private Date createDate2;
	private String court;
	private String contextDesc;
	private String conditionId;
	private String contextId;  //Codetable Drop Down Lists
	private String currentUserStaffPositionId;
	private String currentUserlogonId;
	private String divisionDesc;
	private Date dueDate;
	private Date dueDate2;
	private Date lastTransferDate;
	private String lastTransferUser;
	private String lastTransferUserPos;
	private String nextAction;
	private String name;
	private String ntTaskId;
	private String officerId;
	private String officerName;
	private Collection officerList; // Drop down Workgroup
	private String organizationId;
	private String divisionId; //drop down of divisions
	private String staffPositionId;
	private String supervisorId; //drop down of supervisors
	private Collection supervisors;
	private List officerTempList = new ArrayList();
    
	private String officerStaffId; // drop down of officers and other staff
	private String partyOid;
	private String spn;
	private Object selectedTask;
	private String severityLevelDesc;
	private String severityLevelId;  //Codetable Drop Down Lists
	private String[] severityLevelIds; // selected values from drop down
	private List severityLevels;
	private String status;
	private Date statusChangeDate;
	private String statusChangeUser;
	private String statusChangeUserPos;
	private String statusId;
	private String subject;
	private String taskId;
	private String tasklistType;
	private String tasklistTypeDesc;
	private String tasklistTypeId;  //Codetable Drop Down Lists
	private String taskNearingDue;
	private boolean taskPastDueInd;
	private Collection taskResultList;  // Search result list
	private String taskResultListSize;
	private String taskStatusDesc;
	private String taskStatusId;  //Codetable Drop Down Lists
	private String[] taskStatusIds; // selected values from drop down
	private String taskSubject;
	private String taskText;
	private String taskTopic;
	private String transferTo;
	private String transferToId;
	private String transferToTypeId;
	
	private String workgroupId;
	private String[] workgroupIds;
	private String workgroupName;
	private Collection workgroupList; // Drop down Workgroup
	
	
	private String workgroupIdSelected;
	private String taskStatusIdSelected;
	private String severityLevelIdIdSelected;
	
	private CSTaskResponseEvent selectedCSTask;
	
	/**
     * @return Returns the supervisors.
     */
    public Collection getSupervisors()
    {
        return supervisors;
    }
    /**
     * @param supervisors The supervisors to set.
     */
    public void setSupervisors(Collection supervisors)
    {
        this.supervisors = supervisors;
    }
	/**
     * @return Returns the taskTopic.
     */
    public String getTaskTopic()
    {
        return taskTopic;
    }
    /**
     * @param taskTopic The taskTopic to set.
     */
    public void setTaskTopic(String taskTopic)
    {
        this.taskTopic = taskTopic;
    }

	public void clearDefaultFormValues() {
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
	}

	public void clear() {
		// Never clear the action
		//		action = "";
		createDate = null;
		createDate2 = null;
		court = "";
		dueDate = null;
		dueDate2 = null;
		name = "";
		partyOid = "";
		officerId = "";
		officerName = "";
		spn = "";
		severityLevelId = "";
		subject = "";
		taskId = "";
		tasklistTypeDesc = "";
		tasklistTypeId = "";
		taskNearingDue = "";
		taskPastDueInd = false;
		taskResultListSize = "";
		taskStatusId = "";
		taskSubject = "";
		taskText = "";
		workgroupId = "";
		workgroupName = "";

		// Collections and Drop Down Lists
		officerList = new ArrayList();
		//workgroupList = new ArrayList();
		taskResultList = new ArrayList();
	}
	
	public void clearForm() {
		createDate = null;
		createDate2 = null;
		court = "";
		dueDate = null;
		dueDate2 = null;
		name = "";
		partyOid = "";
		officerName = "";
		spn = "";
		severityLevelId = "";
		subject = "";
		tasklistTypeDesc = "";
		taskNearingDue = "";
		taskPastDueInd = false;
		taskResultListSize = "";
		taskStatusId = "";
		taskSubject = "";
		taskText = "";
		//workgroupId = "";
		workgroupName = "";
		// Collections and Drop Down Lists
		taskResultList = new ArrayList();
	}
	
	public void clearAll() {
		clearDefaultFormValues();
		clear();
	}
	
	public void refreshSearch(){
		tasklistTypeId = "";
		//workgroupList = new ArrayList();
	}
	
	public void clearTaskDetails() {
		
		statusChangeDate = null;
		lastTransferDate = null;
		statusChangeUser = "";
		statusChangeUserPos = "";
		lastTransferUser = null;
		lastTransferUserPos = "";
		createDate = null;
		dueDate = null;
		tasklistTypeId = "";
		severityLevelId = "";
		status = "";
		subject = "";
		taskSubject = "";
		taskText = "";
	}
	
	public void refreshAdvancedSearch(){
	    taskResultList = new ArrayList();
	    supervisors = new ArrayList();
		officerList = new ArrayList();
		createDate = null;
		createDate2 = null;
		spn = "";
		court = "";
		dueDate = null;
		dueDate2 = null;
		divisionId = "";
		organizationId = "";
		workgroupIds = new String[0];
		severityLevelIds = new String[0];
		taskStatusIds = new String[0];
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the court.
	 */
	public String getCourt() {
		return court;
	}
	/**
	 * @param court The court to set.
	 */
	public void setCourt(String court) {
		this.court = court;
	}
	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return Returns the dueDate.
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate The dueDate to set.
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the partyOid.
	 */
	public String getPartyOid() {
		return partyOid;
	}
	/**
	 * @param partyOid The partyOid to set.
	 */
	public void setPartyOid(String partyOid) {
		this.partyOid = partyOid;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the severityLevelId.
	 */
	public String getSeverityLevelId() {
		return severityLevelId;
	}
	/**
	 * @param severityLevelId The severityLevelId to set.
	 */
	public void setSeverityLevelId(String severityLevelId) {
		this.severityLevelId = severityLevelId;
		//severityLevelDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEVERITY_LEVEL, severityLevelId);
	//	List severityLevels =  ComplexCodeTableHelper.getSeverityLevels("CSC");
		//severityLevelDesc = ComplexCodeTableHelper.getSeverityLevels("CSC");
	}
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * 
	 * @return
	 */
	public List getOfficerTempList() {
		return officerTempList;
	}
	/**
	 * 
	 * @param officerTempList
	 */
	public void setOfficerTempList(List officerTempList) {
		this.officerTempList = officerTempList;
	}
	/**
	 * @return Returns the taskId.
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return Returns the tasklistTypeDesc.
	 */
	public String getTasklistTypeDesc() {
		return tasklistTypeDesc;
	}
	/**
	 * @param tasklistType The tasklistTypeDesc to set.
	 */
	public void setTasklistTypeDesc(String tasklistTypeDesc) {
		this.tasklistTypeDesc = tasklistTypeDesc;
	}
	/**
	 * @return Returns the tasklistTypeId.
	 */
	public String getTasklistTypeId() {
		return tasklistTypeId;
	}
	/**
	 * @param tasklistTypeId The tasklistTypeId to set.
	 */
	public void setTasklistTypeId(String tasklistTypeId) {
		this.tasklistTypeId = tasklistTypeId;
		tasklistTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TASK_LIST_TYPES, tasklistTypeId);
	}
	
	/**
	 * @return Returns the taskNearingDue.
	 */
	public String getTaskNearingDue() {
		return taskNearingDue;
	}
	/**
	 * @param taskNearingDue The taskNearingDue to set.
	 */
	public void setTaskNearingDue(String taskNearingDue) {
		this.taskNearingDue = taskNearingDue;
	}
	/**
	 * @return Returns the taskPastDueInd.
	 */
	public boolean isTaskPastDueInd() {
		return taskPastDueInd;
	}
	/**
	 * @param taskPastDueInd The taskPastDueInd to set.
	 */
	public void setTaskPastDueInd(boolean taskPastDueInd) {
		this.taskPastDueInd = taskPastDueInd;
	}
	
	/**
	 * @return Returns the taskResultListSize.
	 */
	public String getTaskResultListSize() {
		return taskResultListSize;
	}
	/**
	 * @param taskResultListSize The taskResultListSize to set.
	 */
	public void setTaskResultListSize(String taskResultListSize) {
		this.taskResultListSize = taskResultListSize;
	}
	/**
	 * @return Returns the workgroupId.
	 */
	public String getWorkgroupId() {
		return workgroupId;
	}
	/**
	 * @param workgroupId The workgroupId to set.
	 */
	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}
	/**
	 * @return Returns the workgroupList.
	 */
	public Collection getWorkgroupList() {
		return workgroupList;
	}
	/**
	 * @param workgroupList The workgroupList to set.
	 */
	public void setWorkgroupList(Collection workgroupList) {
		this.workgroupList = workgroupList;
	}
	/**
	 * @return Returns the workgroupName.
	 */
	public String getWorkgroupName() {
		return workgroupName;
	}
	/**
	 * @param workgroupName The workgroupName to set.
	 */
	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}
	
	
	/**
	 * @return Returns the createDate2.
	 */
	public Date getCreateDate2() {
		return createDate2;
	}
	/**
	 * @param createDate2 The createDate2 to set.
	 */
	public void setCreateDate2(Date createDate2) {
		this.createDate2 = createDate2;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the dueDate2.
	 */
	public Date getDueDate2() {
		return dueDate2;
	}
	/**
	 * @param dueDate2 The dueDate2 to set.
	 */
	public void setDueDate2(Date dueDate2) {
		this.dueDate2 = dueDate2;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
     * @return
     */
    public String getCreateDateAsString() {
        if (createDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(createDate, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @param string
     */
    public void setCreateDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            createDate = null;
        } else {
            createDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @return
     */
    public String getCreateDate2AsString() {
        if (createDate2 == null) {
            return "";
        } else {
            return DateUtil.dateToString(createDate2, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @param string
     */
    public void setCreateDate2AsString(String string) {
        if (string == null || string.trim().equals("")) {
            createDate2 = null;
        } else {
            createDate2 = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @return
     */
    public String getDueDateAsString() {
        if (dueDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(dueDate, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @param string
     */
    public void setDueDateAsString(String string) {
        if (string == null || string.trim().equals("")) {
            dueDate = null;
        } else {
            dueDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @return
     */
    public String getDueDate2AsString() {
        if (dueDate2 == null) {
            return "";
        } else {
            return DateUtil.dateToString(dueDate2, UIConstants.DATE_FMT_1);
        }
    }
    /**
     * @param string
     */
    public void setDueDate2AsString(String string) {
        if (string == null || string.trim().equals("")) {
            dueDate2 = null;
        } else {
            dueDate2 = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
        }
    }
    /**
	 * @return Returns the severityLevelDesc.
	 */
	public String getSeverityLevelDesc() {
		return severityLevelDesc;
	}
	/**
	 * @param severityLevelDesc The severityLevelDesc to set.
	 */
	public void setSeverityLevelDesc(String severityLevelDesc) {
		this.severityLevelDesc = severityLevelDesc;
	}
	/**
	 * @return Returns the tasklistType.
	 */
	public String getTasklistType() {
		return tasklistType;
	}
	/**
	 * @param tasklistType The tasklistType to set.
	 */
	public void setTasklistType(String tasklistType) {
		this.tasklistType = tasklistType;
	}
	/**
	 * @return Returns the taskResultList.
	 */
	public Collection getTaskResultList() {
		return taskResultList;
	}
	/**
	 * @param taskResultList The taskResultList to set.
	 */
	public void setTaskResultList(Collection taskResultList) {
		this.taskResultList = taskResultList;
	}

	/**
	 * @return Returns the taskStatusDesc.
	 */
	public String getTaskStatusDesc() {
		return taskStatusDesc;
	}
	/**
	 * @param taskStatusDesc The taskStatusDesc to set.
	 */
	public void setTaskStatusDesc(String taskStatusDesc) {
		this.taskStatusDesc = taskStatusDesc;
	}
	/**
	 * @return Returns the taskStatusId.
	 */
	public String getTaskStatusId() {
		return taskStatusId;
	}
	/**
	 * @param taskStatusId The taskStatusId to set.
	 */
	public void setTaskStatusId(String taskStatusId) {
		this.taskStatusId = taskStatusId;
	}
	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId() {
		return officerId;
	}
	/**
	 * @param officerId The officerId to set.
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	/**
	 * @return Returns the officerList.
	 */
	public Collection getOfficerList() {
		return officerList;
	}
	/**
	 * @param officerList The officerList to set.
	 */
	public void setOfficerList(Collection officerList) {
		this.officerList = officerList;
	}
	/**
	 * @return Returns the officerName.
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName The officerName to set.
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return Returns the taskSubject.
	 */
	public String getTaskSubject() {
		return taskSubject;
	}
	/**
	 * @param taskSubject The taskSubject to set.
	 */
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
	/**
	 * @return Returns the taskText.
	 */
	public String getTaskText() {
		return taskText;
	}
	/**
	 * @param taskText The taskText to set.
	 */
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	/**
	 * @return Returns the contextDesc.
	 */
	public String getContextDesc() {
		return contextDesc;
	}
	/**
	 * @param contextDesc The contextDesc to set.
	 */
	public void setContextDesc(String contextDesc) {
		this.contextDesc = contextDesc;
	}
	/**
	 * @return Returns the contextId.
	 */
	public String getContextId() {
		return contextId;
	}
	/**
	 * @param contextId The contextId to set.
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}
	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return Returns the officerStaffId.
	 */
	public String getOfficerStaffId() {
		return officerStaffId;
	}
	/**
	 * @param officerStaffId The officerStaffId to set.
	 */
	public void setOfficerStaffId(String officerStaffId) {
		this.officerStaffId = officerStaffId;
	}
	/**
	 * @return Returns the supervisorId.
	 */
	public String getSupervisorId() {
		return supervisorId;
	}
	/**
	 * @param supervisorId The supervisorId to set.
	 */
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
    /**
     * @return Returns the selectedTask.
     */
    public Object getSelectedTask()
    {
        return selectedTask;
    }
    /**
     * @param selectedTask The selectedTask to set.
     */
    public void setSelectedTask(Object selectedTask)
    {
        this.selectedTask = selectedTask;
    }
    /**
     * @return Returns the workgroupIds.
     */
    public String[] getWorkgroupIds()
    {
        return workgroupIds;
    }
    /**
     * @param workgroupIds The workgroupIds to set.
     */
    public void setWorkgroupIds(String[] workgroupIds)
    {
        this.workgroupIds = workgroupIds;
    }
    /**
     * @return Returns the conditionId.
     */
    public String getConditionId()
    {
        return conditionId;
    }
    /**
     * @param conditionId The conditionId to set.
     */
    public void setConditionId(String conditionId)
    {
        this.conditionId = conditionId;
    }
    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId()
    {
        return organizationId;
    }
    /**
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
        
    }
    /**
     * @return Returns the divisionDesc.
     */
    public String getDivisionDesc()
    {
        return divisionDesc;
    }
    /**
     * @param divisionDesc The divisionDesc to set.
     */
    public void setDivisionDesc(String divisionDesc)
    {
        this.divisionDesc = divisionDesc;
    }
    
    
    /**
     * @return Returns the divisionCollection.
     */
    public Collection getDivisionCollection()
    {
        return divisionCollection;
    }
    /**
     * @param divisionCollection The divisionCollection to set.
     */
    public void setDivisionCollection(Collection divisionCollection)
    {
        this.divisionCollection = divisionCollection;
    }
    /**
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId()
    {
        return staffPositionId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId)
    {
        this.staffPositionId = staffPositionId;
    }
    /**
     * @return Returns the severityLevels.
     */
    public List getSeverityLevels()
    {
        return severityLevels = ComplexCodeTableHelper.getSeverityLevels("CSC");
    }
    /**
     * @param severityLevels The severityLevels to set.
     */
    public void setSeverityLevels(List severityLevels)
    {
        this.severityLevels = severityLevels;
    }
    /**
     * @return Returns the ntTaskId.
     */
    public String getNtTaskId()
    {
        return ntTaskId;
    }
    /**
     * @param ntTaskId The ntTaskId to set.
     */
    public void setNtTaskId(String ntTaskId)
    {
        this.ntTaskId = ntTaskId;
    }
    /**
     * @return Returns the statusId.
     */
    public String getStatusId()
    {
        return statusId;
    }
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId)
    {
        this.statusId = statusId;
    }
    /**
     * @return Returns the transferTo.
     */
    public String getTransferTo()
    {
        return transferTo;
    }
    /**
     * @param transferTo The transferTo to set.
     */
    public void setTransferTo(String transferTo)
    {
        this.transferTo = transferTo;
    }
    /**
     * @return Returns the transferToTypeId.
     */
    public String getTransferToTypeId()
    {
        return transferToTypeId;
    }
    /**
     * @param transferToTypeId The transferToTypeId to set.
     */
    public void setTransferToTypeId(String transferToTypeId)
    {
        this.transferToTypeId = transferToTypeId;
    }
    /**
     * @return Returns the transferToId.
     */
    public String getTransferToId()
    {
        return transferToId;
    }
    /**
     * @param transferToId The transferToId to set.
     */
    public void setTransferToId(String transferToId)
    {
        this.transferToId = transferToId;
    }
	public Date getLastTransferDate() {
		return lastTransferDate;
	}
	public void setLastTransferDate(Date lastTransferDate) {
		this.lastTransferDate = lastTransferDate;
	}
	public String getLastTransferUser() {
		return lastTransferUser;
	}
	public void setLastTransferUser(String lastTransferUser) {
		this.lastTransferUser = lastTransferUser;
	}
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}
	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}
	public String getStatusChangeUser() {
		return statusChangeUser;
	}
	public void setStatusChangeUser(String statusChangeUser) {
		this.statusChangeUser = statusChangeUser;
	}
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	public String getLastTransferUserPos() {
		return lastTransferUserPos;
	}
	public void setLastTransferUserPos(String lastTransferUserPos) {
		this.lastTransferUserPos = lastTransferUserPos;
	}
	public String getStatusChangeUserPos() {
		return statusChangeUserPos;
	}
	public void setStatusChangeUserPos(String statusChangeUserPos) {
		this.statusChangeUserPos = statusChangeUserPos;
	}
	/**
	 * @return the severityLevelIds
	 */
	public String[] getSeverityLevelIds() {
		return severityLevelIds;
	}
	/**
	 * @param severityLevelIds the severityLevelIds to set
	 */
	public void setSeverityLevelIds(String[] severityLevelIds) {
		this.severityLevelIds = severityLevelIds;
	}
	/**
	 * @return the taskStatusIds
	 */
	public String[] getTaskStatusIds() {
		return taskStatusIds;
	}
	/**
	 * @param taskStatusIds the taskStatusIds to set
	 */
	public void setTaskStatusIds(String[] taskStatusIds) {
		this.taskStatusIds = taskStatusIds;
	}
	/**
	 * @return the currentUserStaffPositionId
	 */
	public String getCurrentUserStaffPositionId() {
		return currentUserStaffPositionId;
	}
	/**
	 * @param currentUserStaffPositionId the currentUserStaffPositionId to set
	 */
	public void setCurrentUserStaffPositionId(String currentUserStaffPositionId) {
		this.currentUserStaffPositionId = currentUserStaffPositionId;
	}
	/**
	 * @return the currentUserlogonId
	 */
	public String getCurrentUserlogonId() {
		return currentUserlogonId;
	}
	/**
	 * @param currentUserlogonId the currentUserlogonId to set
	 */
	public void setCurrentUserlogonId(String currentUserlogonId) {
		this.currentUserlogonId = currentUserlogonId;
	}
	/**
	 * @return the workgroupIdSelected
	 */
	public String getWorkgroupIdSelected() {
		return workgroupIdSelected;
	}
	/**
	 * @param workgroupIdSelected the workgroupIdSelected to set
	 */
	public void setWorkgroupIdSelected(String workgroupIdSelected) {
		this.workgroupIdSelected = workgroupIdSelected;
	}
	/**
	 * @return the taskStatusIdSelected
	 */
	public String getTaskStatusIdSelected() {
		return taskStatusIdSelected;
	}
	/**
	 * @param taskStatusIdSelected the taskStatusIdSelected to set
	 */
	public void setTaskStatusIdSelected(String taskStatusIdSelected) {
		this.taskStatusIdSelected = taskStatusIdSelected;
	}
	/**
	 * @return the severityLevelIdIdSelected
	 */
	public String getSeverityLevelIdIdSelected() {
		return severityLevelIdIdSelected;
	}
	/**
	 * @param severityLevelIdIdSelected the severityLevelIdIdSelected to set
	 */
	public void setSeverityLevelIdIdSelected(String severityLevelIdIdSelected) {
		this.severityLevelIdIdSelected = severityLevelIdIdSelected;
	}
	/**
	 * 
	 * @return
	 */
	public CSTaskResponseEvent getSelectedCSTask() {
		return selectedCSTask;
	}
	/**
	 * 
	 * @param selectedCSTAsk
	 */
	public void setSelectedCSTask(CSTaskResponseEvent selectedCSTAsk) {
		this.selectedCSTask = selectedCSTAsk;
	}
	
}
