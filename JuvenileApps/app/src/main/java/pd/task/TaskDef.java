/*
 * Created on Mar 14, 2006
 *
 */
package pd.task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import pd.task.exception.TaskDefinitionNotFoundException;

import messaging.task.domintf.ICreateTask;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import mojo.naming.IdentityAddressConstants;

/**
 * @author Jim Fisher
 *
 */
public class TaskDef extends PersistentObject
{
	private String topic;
	private String subject;

	private Integer severityLevel;
	private String action;
	private String application;

	public TaskDef()
	{
	}

	public static void throwTaskDefinitionNotFound(String topic)
	{
		// TODO Configure this exception
		throw new TaskDefinitionNotFoundException("Task topic: " + topic + " was not found in the system.");
	}

	private void addItem(Element root, String key, Object valueObj)
	{
		Element item = new Element("TaskItem");
		item.setAttribute("key", key);
		String type = valueObj.getClass().getName();
		item.setAttribute("type", type);
		String stringValue = this.serialize(type, valueObj);
		item.setText(stringValue);
		root.addContent(item);
	}

	private String serialize(String type, Object value)
	{
		String stringValue = null;
		if (value != null)
		{
			if (ITaskState.STRING_CLASS.equals(type))
			{
				stringValue = (String) value;
			}
			else if (ITaskState.INTEGER_CLASS.equals(type))
			{
				stringValue = value.toString();
			}
			else if (ITaskState.DATE_CLASS.equals(type))
			{
				stringValue = DateUtil.dateToString((Date) value, ITaskState.DATE_FMT);
			}
		}
		return stringValue;
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public Integer getSeverityLevel()
	{
		fetch();
		return severityLevel;
	}

	/**
	 * @return
	 */
	public String getSubject()
	{
		fetch();
		return subject;
	}

	/**
	 * @return
	 */
	public String getTopic()
	{
		fetch();
		return topic;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		if (this.action == null || !this.action.equals(string))
		{
			markModified();
		}
		this.action = string;
	}

	/**
	 * @param string
	 */
	public void setSeverityLevel(Integer integer)
	{
		if (this.severityLevel == null || !this.severityLevel.equals(integer))
		{
			markModified();
		}
		this.severityLevel = integer;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string)
	{
		if (this.subject == null || !this.subject.equals(string))
		{
			markModified();
		}
		this.subject = string;
	}

	/**
	 * @param string
	 */
	public void setTopic(String string)
	{
		if (this.topic == null || !this.topic.equals(string))
		{
			markModified();
		}
		this.topic = string;
	}

	/**
	 * @param string
	 */
	public Task createTask(ICreateTask createTask) throws IOException
	{
		Task task = new Task();
		task.setTopic(this.getTopic());
		//DAG 11/10/09 - Severity level is not required and should not default.
//		if (createTask.getSeverityLevel() == null)
//		{
//			task.setSeverityLevel(this.getSeverityLevel());
//		}
//		else
//		{
//			task.setSeverityLevel(createTask.getSeverityLevel());
//		}
		if (createTask.getSeverityLevel() != null){
			task.setSeverityLevel(createTask.getSeverityLevel());
		}
		task.setStatusId(Task.SUBMITTED);
		task.setSubmittedDate(new Date());
		task.setDueDate(createTask.getDueDate());
		task.setTaskSubject(createTask.getTaskSubject());
	
		ITaskState taskState = createTask.getTaskState();
		if (taskState != null)
		{
			String taskStateString = this.serializeTaskState(taskState);
			task.setTaskState(taskStateString);
		}

		// Use factory method to create SOURCE identity or use Identity instance if exists
		IdentityAddress sourceIdentity =
			IdentityAddress.identityFactory(createTask.getCreatorId(), IdentityAddressConstants.IDENTITY_INDIVIDUAL);
		task.setSourceIdentity(sourceIdentity);

		// Use factory method to create DESTINATION identity or use Identity instance if exists
		IdentityAddress ownerIdentity =
			IdentityAddress.identityFactory(createTask.getOwnerId(), IdentityAddressConstants.IDENTITY_INDIVIDUAL);
		task.setOwnerIdentity(ownerIdentity);
		
		 IHome home = new Home();
	     home.bind(task);

		return task;
	}

	private String serializeTaskState(ITaskState taskState)
	{
		// Generate XML document
		// TODO Convert string literals to constants
		Element root = new Element("TaskState");
		Document document = new Document(root);

		Iterator i = taskState.getKeys().iterator();

		while (i.hasNext())
		{
			String key = (String) i.next();
			Object obj = taskState.get(key);
			this.addItem(root, key, obj);
		}

		XMLOutputter outputter = new XMLOutputter();

		OutputStream out = new ByteArrayOutputStream();

		try
		{
			outputter.output(document, out);
		}
		catch (IOException e)
		{
			// TODO Handle appropriately
			e.printStackTrace();
		}

		return out.toString();
	}

	/**
	 * @param topic
	 * @return
	 */
	public static TaskDef find(String topic)
	{
		TaskDef taskDef = null;
		IHome home = new Home();
		Iterator i = home.findAll("topic", topic, TaskDef.class);
		if (i.hasNext())
		{
			taskDef = (TaskDef) i.next();
		}
		return taskDef;
	}

	/**
	 * @return
	 */
	public String getApplication()
	{
		fetch();
		return application;
	}

	/**
	 * @param string
	 */
	public void setApplication(String string)
	{
		if (this.application == null || !this.application.equals(string))
		{
			markModified();
		}
		this.application = string;
	}

	/**
	 * @param taskBean
	 */
	public void fill(ITask task)
	{
		task.setApplication(this.getApplication());
		task.setAction(this.getAction());
		task.setSubject(this.getSubject());
	}

}
