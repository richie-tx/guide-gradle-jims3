/*
 * Created on Mar 14, 2006
 *
 */
package pd.task;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.task.CreateTaskEvent;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import messaging.task.to.TaskStateBean;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import naming.PDTaskConstants;

import org.apache.log4j.Level;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import pd.codetable.Code;
import pd.security.PDSecurityHelper;

/**
 * @author Jim Fisher
 *  
 */
public class Task extends PersistentObject
{
    public static String ACCEPTED = "A";

    public static String CLOSED = "C";

    public static String SUBMITTED = "S";

    // TODO Move to naming
    public static boolean WITH_STATE = true;

    public static boolean WITHOUT_STATE = false;

    /*
     * @param userId @param topic @param title @param map of task param
     */
    static public void createTask(String userId, String topic, String title, Map map) throws RuntimeException,
            Exception
    {
        CreateTaskEvent createTask = new CreateTaskEvent();
        createTask.setOwnerId(userId);
        createTask.setCreatorId(PDSecurityHelper.getLogonId());
        createTask.setStatusCode(PDTaskConstants.SUBMITTED_STATUS_ID);
        createTask.setTopic(topic);
        createTask.setTaskSubject(title);

        Iterator mapIter = map.keySet().iterator();
        while (mapIter.hasNext())
        {
            String key = (String) mapIter.next();
            String value = (String) map.get(key);
            createTask.addTaskStateItem(key, value);
        }

        TaskDef taskDef = TaskDef.find(topic);

        if (taskDef == null)
        {
            TaskDef.throwTaskDefinitionNotFound(topic);
        }
        taskDef.createTask(createTask);

        // free up the memory
        createTask = null;
        mapIter = null;
        taskDef = null;
    }

    /**
     * @param integer
     * @return
     */
    public static Task find(String taskId)
    {
        IHome home = new Home();
        return (Task) home.find(taskId, Task.class);
    }

    /**
     * @param event
     * @return
     */
    public static Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, Task.class);
    }

    /**
     * @param getTasksEvent
     */
    public static Iterator findAllByStatus(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, Task.class);
    }

    private Date acceptedDate;

    private Date closedDate;

    private Date dueDate;

    /**
     * Properties for ownerIdentity
     * 
     * @referencedType mojo.km.identityaddress.IdentityAddress
     * @detailerDoNotGenerate true
     */
    private IdentityAddress ownerIdentity;

    private String ownerIdentityId;

    private Integer severityLevel;

    /**
     * Properties for sourceIdentity
     * 
     * @referencedType mojo.km.identityaddress.IdentityAddress
     * @detailerDoNotGenerate true
     */
    private IdentityAddress sourceIdentity;

    private String sourceIdentityId;

    /**
     * Properties for status
     * 
     * @referencedType pd.codetable.Code
     * @contextKey TASK_STATUS
     * @detailerDoNotGenerate true
     */
    private Code status;

    private String statusId;

    private Date submittedDate;

    /**
     * Properties for taskJournals
     * 
     * @referencedType pd.task.TaskJournal
     * @detailerDoNotGenerate true
     */
    private Collection taskJournals;

    /// @Deprecated
    private byte[] taskState;

    private String taskStateXml;

    private String taskSubject;

    private String topic;

    public Task()
    {
    }

    private void addItem(ITaskState bean, String type, String key, String stringValue)
    {
        if (stringValue.equals("null") == false)
        {
            if (ITaskState.STRING_CLASS.equals(type))
            {
                bean.addItem(key, stringValue);
            }
            else if (ITaskState.INTEGER_CLASS.equals(type))
            {
                Integer obj = new Integer(stringValue);
                bean.addItem(key, obj);
            }
            else if (ITaskState.DATE_CLASS.equals(type))
            {
                Date obj = DateUtil.stringToDate(stringValue, ITaskState.DATE_FMT);
                bean.addItem(key, obj);
            }
        }
    }

    /**
     * Clears all pd.juvenilewarrant.JuvenileAssociate from class relationship collection.
     */
    public void clearTaskJournals()
    {
        initTaskJournals();
        taskJournals.clear();
    }

    public void fill(ITask task, boolean withState)
    {
        if (task != null)
        {
            task.setTaskId(this.getTaskId());

            // TODO Create navigation from Task to its TaskDef parent
            TaskDef taskDef = TaskDef.find(this.getTopic());
            if (taskDef == null)
            {
                TaskDef.throwTaskDefinitionNotFound(task.getTopic());
            }

            // Set application, action, and scope
            taskDef.fill(task);

            task.setTopic(this.getTopic());
            task.setSeverityLevel(this.getSeverityLevel());
            task.setStatusCode(this.getStatusId());
            if (this.getStatusId() != null && !this.getStatusId().equals(""))
            {
                Code status = this.getStatus();
                if (status != null)
                {
                    task.setStatus(status.getDescription());
                }
            }
            task.setTaskSubject(this.getTaskSubject());
            task.setAcceptedDate(this.getAcceptedDate());
            task.setClosedDate(this.getClosedDate());
            task.setCreateDate(this.getCreateTimestamp());
            task.setDueDate(this.getDueDate());
            task.setSubmittedDate(this.getSubmittedDate());
            task.setSourceId(this.getSourceIdentityId());
            task.setCreateUserId(this.getCreateUserID());
            task.setOwnerId(this.getOwnerIdentityId());

            if (withState)
            {
                try
                {
                    ITaskState bean = this.parse(this.getTaskStateXml());
                    task.setUrl(this.getURL(task, bean));
                    task.setTaskState(bean);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return Date acceptedDate
     */
    public Date getAcceptedDate()
    {
        fetch();
        return acceptedDate;
    }

    /**
     * @return Date closedDate
     */
    public Date getClosedDate()
    {
        fetch();
        return closedDate;
    }

    /**
     * @return Date dueDate
     */
    public Date getDueDate()
    {
        fetch();
        return dueDate;
    }

    /**
     * Gets referenced type mojo.km.identityaddress.IdentityAddress
     * 
     * @return IdentityAdress ownerAddress
     */
    public IdentityAddress getOwnerIdentity()
    {
        fetch();
        initOwnerIdentity();
        return ownerIdentity;
    }

    /**
     * @return String ownerId
     */
    public String getOwnerIdentityId()
    {
        fetch();
        return ownerIdentityId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String severityLevelId
     */
    public Integer getSeverityLevel()
    {
        fetch();
        return severityLevel;
    }

    /**
     * @return String sourceId
     */
    public String getSourceIdentityId()
    {
        fetch();
        return sourceIdentityId;
    }

    /**
     * @return Returns the status.
     */
    public Code getStatus()
    {
        fetch();
        initStatus();
        return status;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String statusId
     */
    public String getStatusId()
    {
        fetch();
        return statusId;
    }

    /**
     * @return Date submittedDate
     */
    public Date getSubmittedDate()
    {
        fetch();
        return submittedDate;
    }

    /**
     * @return String taskId
     */
    public String getTaskId()
    {
        fetch();
        return super.getOID().toString();
    }

    /**
     * returns a collection of pd.task.TaskJournal
     * 
     * @return Collection juvenileAssociates
     */
    public Collection getTaskJournals()
    {
        fetch();
        initTaskJournals();
        return this.taskJournals;
    }

    /**
     * @return
     */
    public byte[] getTaskState()
    {
        fetch();
        return taskState;
    }

    /**
     * @return Returns the taskStateXml.
     */
    public String getTaskStateXml()
    {
        fetch();
        return taskStateXml;
    }

    /**
     * @return String taskSubject
     */
    public String getTaskSubject()
    {
        fetch();
        return taskSubject;
    }

    /**
     * @return String topic
     */
    public String getTopic()
    {
        fetch();
        return topic;
    }

    /**
     * @param task
     * @param bean
     * @return
     */
    private String getURL(ITask task, ITaskState bean)
    {
        StringBuffer forwardPath = new StringBuffer();
        forwardPath.append("/");
        forwardPath.append(task.getApplication());
        forwardPath.append("/");
        forwardPath.append(task.getAction());
        forwardPath.append(".do");

        if (this.taskStateXml != null)
        {
            forwardPath.append("?");
            boolean hasAction = false;

            for (Iterator keysIter = bean.getKeys().iterator(); keysIter.hasNext();)
            {
                String key = (String) keysIter.next();
                if (bean.get(key) instanceof Date)
                {
                    continue;
                }
                String value = (String) bean.get(key);
                if ("action".equalsIgnoreCase(key))
                {
                    hasAction = true;
                }
                forwardPath.append(key);
                forwardPath.append("=");
                forwardPath.append(value);
                if (keysIter.hasNext())
                    forwardPath.append("&");
            }

            if (!hasAction)
            {
                forwardPath.append("&");
                forwardPath.append("action");
                forwardPath.append("=default");
            }
        }
        return forwardPath.toString();
    }

    /**
     *  
     */
    private void initOwnerIdentity()
    {
        if (ownerIdentity == null)
        {
            ownerIdentity = (mojo.km.identityaddress.IdentityAddress) new mojo.km.persistence.Reference(
                    this.ownerIdentityId, mojo.km.identityaddress.IdentityAddress.class).getObject();
        }
    }

    /**
     *  
     */
    private void initSourceIdentity()
    {
        if (sourceIdentity == null)
        {
            sourceIdentity = (mojo.km.identityaddress.IdentityAddress) new mojo.km.persistence.Reference(
                    this.sourceIdentityId, mojo.km.identityaddress.IdentityAddress.class).getObject();
        }
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     * 
     * @roseuid 4107DFB6012A
     */
    private void initStatus()
    {
        if (status == null)
        {
            try
            {
                status = (Code) new mojo.km.persistence.Reference(statusId, Code.class,
                        "NOTIFICATION_STATUS").getObject();
            }
            catch (Throwable t)
            {
                status = null;
            }
        }
    }

    /**
     * Initialize class relationship implementation for pd.juvenilewarrant.JuvenileAssociate
     */
    private void initTaskJournals()
    {
        if (taskJournals == null)
        {
            if (this.getOID() == null)
            {
                new mojo.km.persistence.Home().bind(this);
            }

            taskJournals = new mojo.km.persistence.ArrayList(TaskJournal.class, "taskId", "" + getOID());
        }
    }

    /**
     * insert a pd.juvenilewarrant.JuvenileAssociate into class relationship collection.
     * 
     * @param anObject
     */
    public void insertTaskJournal(TaskJournal anObject)
    {
        initTaskJournals();
        taskJournals.add(anObject);
    }

    protected ITaskState parse(String xml) throws JDOMException, IOException
    {
        if (LogUtil.isTraceEnabled())
        // don't concat ("parse taskStateXml" + xml) unnecessarily 
        {
            LogUtil.log(Level.TRACE, "parse taskStateXml: " + xml);
        }
        ITaskState bean = new TaskStateBean();
        if (xml != null)
        {
            SAXBuilder saxReader = new SAXBuilder();
            ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
            InputStream is = new BufferedInputStream(bais);
            Document document = saxReader.build(is);
            Element root = document.getRootElement();

            // iterate through child elements of root
            List children = root.getChildren();
            if (children != null)
            {
                Iterator i = children.iterator();
                while (i.hasNext())
                {
                    Element element = (Element) i.next();
                    Attribute keyAttr = element.getAttribute("key");
                    String key = keyAttr.getValue();
                    Attribute typeAttr = element.getAttribute("type");
                    String type = typeAttr.getValue();
                    String content = element.getText();

                    this.addItem(bean, type, key, content);
                }
            }
        }

        return bean;
    }

    /**
     * @param date
     */
    public void setAcceptedDate(Date date)
    {
        if (this.acceptedDate == null || !this.acceptedDate.equals(date))
        {
            markModified();
        }
        this.acceptedDate = date;
    }

    /**
     * @param date
     */
    public void setClosedDate(Date date)
    {
        if (this.closedDate == null || !this.closedDate.equals(date))
        {
            markModified();
        }
        this.closedDate = date;
    }

    /**
     * @param date
     */
    public void setDueDate(Date date)
    {
        if (this.dueDate == null || !this.dueDate.equals(date))
        {
            markModified();
        }
        this.dueDate = date;
    }

    public void setOwnerIdentity(IdentityAddress identity)
    {
        this.ownerIdentity = identity;
        if (identity != null && identity.getOID() != null)
        {
            this.setOwnerIdentityId(identity.getOID().toString());
        }
        else
        {
            this.setOwnerIdentityId(null);
        }
    }

    /**
     * @param String
     *  ownerId
     */
    public void setOwnerIdentityId(String string)
    {
        if (this.ownerIdentityId == null || !this.ownerIdentityId.equals(string))
		{
			markModified();
		}
		this.ownerIdentityId = string;
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

    public void setSourceIdentity(IdentityAddress identity)
    {
        this.sourceIdentity = identity;
        if (identity != null && identity.getOID() != null)
        {
            this.setSourceIdentityId(identity.getOID().toString());
        }
        else
        {
            this.setSourceIdentityId(null);
        }
    }

    /**
     * @param string
     */
    public void setSourceIdentityId(String string)
    {
        sourceIdentityId = string;
    }

    /**
     * @param string
     */
    public void setStatusId(String string)
    {
        if (this.statusId == null || !this.statusId.equals(string))
        {
            markModified();
        }
        this.statusId = string;
    }

    /**
     * @param date
     */
    public void setSubmittedDate(Date date)
    {
        if (this.submittedDate == null || !this.submittedDate.equals(date))
        {
            markModified();
        }
        this.submittedDate = date;
    }

    /**
     * @param integer
     */
    public void setTaskId(String string)
    {
        this.setOID(string);
    }

    /**
     * @param bs
     */
    public void setTaskState(byte[] aTaskState)
    {
        markModified();
        this.taskState = aTaskState;
    }

    public void setTaskState(String taskStateString) throws IOException
    {
        this.setTaskStateXml(taskStateString);
    }

    /**
     * @param taskStateXml
     *            The taskStateXml to set.
     */
    public void setTaskStateXml(String taskStateXml)
    {
        markModified();
        this.taskStateXml = taskStateXml;
    }

    /**
     * @param string
     */
    public void setTaskSubject(String string)
    {
        if (this.taskSubject == null || !this.taskSubject.equals(string))
        {
            markModified();
        }
        this.taskSubject = string;
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
}
