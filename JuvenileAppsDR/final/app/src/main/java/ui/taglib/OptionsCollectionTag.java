/*
 * SelectCodeTag.java
 *
 * Created on July 27, 2005, 7:40 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ui.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import ui.common.CodeHelper;

import messaging.codetable.reply.ICode;
import mojo.km.utilities.MessageUtil;

/**
 *
 * @author Jim Fisher
 */
public class OptionsCollectionTag extends TagSupport
{
	/**
	 * Set to false if you do NOT want the option labels filtered for sensitive 
	 * characters in HTML. By default, such values are filtered. [RT Expr]
	 */
	private String filter;

	/**
	 * The property of the bean within the collection which represents the label 
	 * to be rendered for each option. Defaults to "label". [RT Expr]
	 */
	private String label;

	/**
	 * The attribute name of the bean whose properties are consulted when rendering 
	 * the current value of this input field. If not specified, the bean associated 
	 * with the form tag we are nested within is utilized. [RT Expr]
	 */
	private String name;

	/**
	 * The property of the form bean, or the bean specified by the name attribute, 
	 * that will return the collection of objects to be rendered for these 
	 * options. [RT Expr]
	 */
	private String property;

	/**
	 * CSS styles to be applied to this HTML element. [RT Expr]
	 */
	private String style;

	/**
	 * CSS stylesheet class to be applied to this HTML 
	 * element (renders a "class" attribute). [RT Expr]
	 */
	private String styleClass;

	/**
	 * The property of the bean within the collection which represents the value 
	 * to be rendered for each option. Defaults to "value". [RT Expr]
	 */
	private String value;

	private String delim;

	/** 
	 * Creates a new instance of OptionsCollectionTag 
	 */
	public OptionsCollectionTag()
	{
	}

	public int doStartTag() throws JspException
	{
		try
		{
			Collection beanProperty = (Collection) this.getReadObject();

			String[] selectedValues = null;

			boolean delimit = (this.getDelim() != null);

			this.renderBody(beanProperty, selectedValues, delimit);
		}
		catch (Exception e)
		{
			// TODO Needs proper exception handling
			throw new JspException(e.getMessage());
		}
		return Tag.SKIP_BODY;
	}

	private Object getReadObject() throws Exception
	{
		// attempt to locate bean in the page context
		Object beanObj = pageContext.getAttribute(this.name);

		if (beanObj == null)
		{
			// attempt to locate bean in the request context
			beanObj = pageContext.getRequest().getAttribute(this.name);
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the session context
			beanObj = pageContext.getSession().getAttribute(this.name);
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the application context
			beanObj = pageContext.getServletContext().getAttribute(this.name);
		}

		StringBuffer buffer = new StringBuffer(this.property);
		String firstChar = String.valueOf(buffer.charAt(0)).toUpperCase();
		buffer.replace(0, 1, firstChar);
		buffer.insert(0, "get");
		
		String getterName = buffer.toString();
		
		Method method = beanObj.getClass().getMethod(getterName, (Class[]) null);

		return method.invoke(beanObj, (Object[]) null);
	}

	private void renderBody(Collection codes, String[] selectedValues, boolean delimit) throws Exception
	{
		if (codes != null)
		{
			Iterator i = codes.iterator();

			boolean selected;

			while (i.hasNext())
			{
				ICode code = (ICode) i.next();
				if (false)
				{
					selected = (Arrays.binarySearch(selectedValues, code.getCode()) >= 0);
				}
				else
				{
					selected = false;
				}

				StringBuffer buffer = new StringBuffer();
				buffer.append(code.getCode());
				if (delimit == true)
				{
					buffer.append(this.getDelim());
					buffer.append(code.getDescription());
				}
				this.renderOption(buffer.toString(), code.getDescription(), selected);
			}
		}
	}

	private void renderOption(String id, String description, boolean selected) throws IOException
	{
		JspWriter writer = pageContext.getOut();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<OPTION ");

		if (selected == true)
		{
			buffer.append("SELECTED");
		}

		buffer.append(" value=\"");
		buffer.append(id);
		buffer.append("\">");
		buffer.append(description);
		buffer.append("</OPTION>");
		writer.print(buffer.toString());
	}

	/**
	 * @return
	 */
	public String getDelim()
	{
		return delim;
	}

	/**
	 * @param string
	 */
	public void setDelim(String string)
	{
		delim = string;
	}

	/**
	 * @return
	 */
	public String getFilter()
	{
		return filter;
	}

	/**
	 * @return
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getStyle()
	{
		return style;
	}

	/**
	 * @return
	 */
	public String getStyleClass()
	{
		return styleClass;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param string
	 */
	public void setFilter(String string)
	{
		filter = string;
	}

	/**
	 * @param string
	 */
	public void setLabel(String string)
	{
		label = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setStyle(String string)
	{
		style = string;
	}

	/**
	 * @param string
	 */
	public void setStyleClass(String string)
	{
		styleClass = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		value = string;
	}

	/**
	 * @return
	 */
	public String getProperty()
	{
		return property;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string)
	{
		property = string;
	}

}
