/*
 * Created on Mar 28, 2006
 *
 */
package pd.task.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import pd.task.TaskDef;

/**
 * @author Jim Fisher
 *
 */
public class LoadTaskDefinitions
{
	// Task Def Attributes
	private final String TOPIC = "topic";
	private final String ACTION = "action";
	private final String APPLICATION = "application";
	private final String DEFAULT_SEVERITY = "defaultSeverity";
	private final String SUBJECT = "subject";

	private final String TASK_DEF_CONFIG_FILE = "taskDefinition.xml";

	public LoadTaskDefinitions()
	{
	}

	private TaskDef createTaskDef(
		String topic,
		String action,
		String application,
		Integer severityLevel,
		String subject)
	{
		TaskDef taskDef = new TaskDef();
		taskDef.setTopic(topic);
		taskDef.setAction(action);
		taskDef.setApplication(application);
		taskDef.setSeverityLevel(severityLevel);
		taskDef.setSubject(subject);
		return taskDef;
	}

	private void parseTaskDef(Element element)
	{
		Attribute topicAttr = element.getAttribute(TOPIC);
		String topic = topicAttr.getValue();

		Attribute actionAttr = element.getAttribute(ACTION);
		String action = actionAttr.getValue();

		Attribute applicationAttr = element.getAttribute(APPLICATION);
		String application = applicationAttr.getValue();

		Attribute defaultSeverityAttr = element.getAttribute(DEFAULT_SEVERITY);
		Integer severityLevel = Integer.valueOf(defaultSeverityAttr.getValue());

		Attribute subjectAttr = element.getAttribute(SUBJECT);
		String subject = subjectAttr.getValue();

		TaskDef taskDef = this.createTaskDef(topic, action, application, severityLevel, subject);

		IHome home = new Home();
		home.bind(taskDef);
	}

	public void init() throws IOException, JDOMException
	{
		System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();

		String resourceName = TASK_DEF_CONFIG_FILE;

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(resourceName);

		if (url != null)
		{
			System.out.println("Loading " + resourceName + " from: " + url.getPath());
		}
		else
		{
			throw new FileNotFoundException(resourceName);
		}

		InputStream is = url.openStream();

		SAXBuilder saxReader = new SAXBuilder();
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
				this.parseTaskDef(element);
			}
		}
	}

	public static void main(String[] args)
	{
		LoadTaskDefinitions loader = new LoadTaskDefinitions();
		try
		{
			loader.init();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JDOMException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
