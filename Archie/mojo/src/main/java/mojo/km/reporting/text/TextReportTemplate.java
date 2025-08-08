/*
 * Created on Mar 28, 2006
 *
 */
package mojo.km.reporting.text;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *
 */
public class TextReportTemplate extends PersistentObject
{

	static public TextReportTemplate find(String textReportTemplateId)
	{
		IHome home = new Home();
		TextReportTemplate textReportTemplate =
			(TextReportTemplate) home.find(textReportTemplateId, TextReportTemplate.class);
		return textReportTemplate;
	}

	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, TextReportTemplate.class);
	}

	public static TextReportTemplate findByName(String name)
	{
		TextReportTemplate template = null;
		IHome home = new Home();
		Iterator i = home.findAll("name", name, TextReportTemplate.class);
		if (i.hasNext())
		{
			template = (TextReportTemplate) i.next();
		}
		return template;
	}

	private String name;
	private String template;

	/**
	 * 
	 */
	public TextReportTemplate()
	{
	}

	public void findAll()
	{
		fetch();
	}

	/**
	 * @return
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * @return
	 */
	public String getTemplate()
	{
		fetch();
		return template;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		if (this.name == null || !this.name.equals(string))
		{
			markModified();
		}
		name = string;
	}

	/**
	 * @param cs
	 */
	public void setTemplate(String aTemplate)
	{
	    if (this.template == null || !this.template.equals(aTemplate))
		{
			markModified();
		}
		this.template = aTemplate;
	}
}
