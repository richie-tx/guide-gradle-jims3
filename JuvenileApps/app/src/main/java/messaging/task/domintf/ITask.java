/*
 * Created on Mar 14, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.task.domintf;

import java.io.Serializable;
import java.util.Date;

import messaging.task.domintf.ITaskState;

/**
 * @author jfisher
 *
 */
public interface ITask extends Serializable
{	
	Date getAcceptedDate();
	String getAction();
	String getApplication();
	Date getClosedDate();
	Date getCreateDate();
	String getCreateUserId();
	Date getDueDate();
	String getOwnerId();
	Integer getSeverityLevel();
	String getSourceId();
	String getStatusCode();
	String getStatus();
	String getSubject();
	Date getSubmittedDate();
	String getTaskId();
	ITaskState getTaskState();
	String getTaskSubject();
	String getUrl();
	String getTopic();
	void setAcceptedDate(Date date);
	void setAction(String string);
	void setApplication(String string);
	void setClosedDate(Date date);
	void setCreateDate(Date date);
	void setCreateUserId(String string);
	void setDueDate(Date date);
	void setOwnerId(String string);
	void setSeverityLevel(Integer integer);
	void setSourceId(String string);
	void setStatusCode(String string);
	void setStatus(String status);
	void setSubject(String string);
	void setSubmittedDate(Date date);
	void setTaskId(String string);
	void setTaskState(ITaskState taskState);
	void setTaskSubject(String taskSubject);
	void setTopic(String string);
	void setUrl(String url);
}
