package pd.notification;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.taskbrowser.TaskTypeURLCode;

/**
* @roseuid 43E236F40293
*/
public class Task extends PersistentObject {
	private String typeId;
	private String details;
	private String taskStatusId;
	private Date creationDate;
	private String taskUserId;
	private String juvenileName;
	
	/**
	* Properties for taskStatus
	* @contextKey TASK_STATUS
	*/
	public Code taskStatus = null;
	private String URL;
	/**
	* Properties for type
	* @detailerDoNotGenerate true
	*/
	private TaskTypeURLCode type = null;
	private String title;
	/**
	* Properties for paramList
	* @referencedType pd.notification.TaskParam
	* @detailerDoNotGenerate false
	*/
	private Collection paramList;
	private Date completionDate;
	/**
	* @roseuid 43E236F40293
	*/
	public Task() {
	}
	/**
	* @param logonId
	* @roseuid 43DE8D1403E7
	*/
//	public void find(String logonId) {
//		fetch();
//	}
	/**
	* Finds Tasks by a certain event
	* @return Iterator of Tasks
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, Task.class);
	}
	/**
	* @param taskID
	* @roseuid 43DE8D150009
	*/
	public void delete(int taskID) {
		markModified();
	}
	/**
	* @roseuid 43DE8D150013
	*/
	public void setStatusToCompleted() {
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setTaskStatusId(String taskStatusId) {
		if (this.taskStatusId == null || !this.taskStatusId.equals(taskStatusId)) {
			markModified();
		}
		taskStatus = null;
		this.taskStatusId = taskStatusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getTaskStatusId() {
		fetch();
		return taskStatusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initTaskStatus() {
		if (taskStatus == null) {
			taskStatus = (Code) new mojo.km.persistence.Reference(taskStatusId, Code.class, "TASK_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getTaskStatus() {
		fetch();
		initTaskStatus();
		return taskStatus;
	}
	/**
	* set the type reference for class member taskStatus
	*/
	public void setTaskStatus(Code taskStatus) {
		if (this.taskStatus == null || !this.taskStatus.equals(taskStatus)) {
			markModified();
		}
		if (taskStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(taskStatus);
		}
		setTaskStatusId("" + taskStatus.getOID());
		this.taskStatus = (Code) new mojo.km.persistence.Reference(taskStatus).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setTypeId(String typeId) {
		if (this.typeId == null || !this.typeId.equals(typeId)) {
			markModified();
		}
		type = null;
		this.typeId = typeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getTypeId() {
		fetch();
		return typeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initType() {
		if (type == null) {
			type = TaskTypeURLCode.find(typeId);
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public TaskTypeURLCode getType() {
		fetch();
		initType();
		return type;
	}
	/**
	* set the type reference for class member type
	*/
	public void setType(TaskTypeURLCode type) {
		if (this.type == null || !this.type.equals(type)) {
			markModified();
		}
		if (type.getOID() == null) {
			new mojo.km.persistence.Home().bind(type);
		}
		setTypeId("" + type.getOID());
		this.type = (TaskTypeURLCode) new mojo.km.persistence.Reference(type).getObject();
	}
	/**
	* @return 
	*/
	public Date getCompletionDate() {
		fetch();
		return completionDate;
	}
	/**
	* @return 
	*/
	public Date getCreationDate() {
		fetch();
		return creationDate;
	}
	/**
	* @return 
	*/
	public String getDetails() {
		fetch();
		return details;
	}
	/**
	* @return 
	*/
	public String getTitle() {
		fetch();
		return title;
	}
	/**
	* @return 
	*/
	public String getURL() {
		fetch();
		return URL;
	}
	/**
	* @return 
	*/
	public String getTaskUserId() {
		fetch();
		return taskUserId;
	}
	/**
	* @param date
	*/
	public void setCompletionDate(Date date) {
		if (this.completionDate == null || !this.completionDate.equals(date)) {
			markModified();
		}
		completionDate = date;
	}
	/**
	* @param date
	*/
	public void setCreationDate(Date date) {
		if (this.creationDate == null || !this.creationDate.equals(date)) {
			markModified();
		}
		creationDate = date;
	}
	/**
	* @param string
	*/
	public void setDetails(String string) {
		if (this.details == null || !this.details.equals(string)) {
			markModified();
		}
		details = string;
	}
	/**
	* @param string
	*/
	public void setTitle(String string) {
		if (this.title == null || !this.title.equals(string)) {
			markModified();
		}
		title = string;
	}
	/**
	* @param string
	*/
	public void setURL(String string) {
		if (this.URL == null || !this.URL.equals(string)) {
			markModified();
		}
		URL = string;
	}
	/**
	* @param string
	*/
	public void setTaskUserId(String string) {
		if (this.taskUserId == null || !this.taskUserId.equals(string)) {
			markModified();
		}
		taskUserId = string;
	}
	
	/**
	* @return 
	*/
	public String getJuvenileName() {
		fetch();
		return juvenileName;
	}
	
	/**
	* @param string
	*/
	public void setJuvenileName(String string) {
		if (this.juvenileName == null || !this.juvenileName.equals(string)) {
			markModified();
		}
		juvenileName = string;
	}

	/**
	* Initialize class relationship implementation for pd.notification.TaskParam
	*/
	private void initParamList() {
		if (paramList == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			paramList = new mojo.km.persistence.ArrayList(TaskParam.class, "taskId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.notification.TaskParam
	*/
	public Collection getParamList() {
		initParamList();
		return paramList;
	}
	/**
	* insert a pd.notification.TaskParam into class relationship collection.
	*/
	public void insertParamList(TaskParam anObject) {
		initParamList();
		paramList.add(anObject);
	}
	/**
	* Removes a pd.notification.TaskParam from class relationship collection.
	*/
	public void removeParamList(TaskParam anObject) {
		initParamList();
		paramList.remove(anObject);
	}
	/**
	* Clears all pd.notification.TaskParam from class relationship collection.
	*/
	public void clearParamList() {
		initParamList();
		paramList.clear();
	}
	
	static public Task find(String taskId)
	{
		Task task = null;
		IHome home = new Home();
		task = (Task) home.find(taskId, Task.class);
		return task;
	}
}
