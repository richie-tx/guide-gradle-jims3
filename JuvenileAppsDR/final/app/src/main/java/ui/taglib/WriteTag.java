package ui.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import messaging.codetable.reply.ICode;
import naming.UIConstants;

import org.apache.struts.taglib.TagUtils;

import ui.common.CodeHelper;

/**
 * @author Jim Fisher
 *
 */
public class WriteTag extends TagSupport
{
	private static String PAGE_BREAK = "<BR>";
	private final String TRUE = "true";
	private String delim;
	private String name;
	private String noValueMsg;
	private String noValueMsgKey;
	private String property;

	public int doStartTag() throws JspException
	{
		try
		{
			Object bean = this.getReadObject();
			if (bean instanceof String[])
			{
				List codes = CodeHelper.extractCodes((String[]) bean, UIConstants.CODE_DELIM);
				this.writeValues(codes);
			}
		}
		catch (Exception e)
		{
			throw new JspException(e.getMessage());
		}

		return TagSupport.SKIP_BODY;
	}

	/**
	 * @param propertyBean
	 */
	private void writeValues(Object aPropertyBean) throws IOException, JspException
	{
		String description = null;
		JspWriter writer = this.pageContext.getOut();

		boolean exists = true; // this.valueExists(aPropertyBean);

		if (exists == true && aPropertyBean instanceof List)
		{
			List propertyBean = (List) aPropertyBean;
			description = this.formatDescription(propertyBean);
		}
		else if (exists == true && aPropertyBean instanceof ICode)
		{
			description = ((ICode) aPropertyBean).getDescription();
		}
		else if (exists == true && aPropertyBean instanceof String)
		{
			description = (String) aPropertyBean;
		}
		else
		{
			description = this.getNoValueMessage();
		}
		writer.print(description);
	}

	private String formatDescription(List descriptions) throws JspException
	{
		String description = null;
		if (descriptions == null || descriptions.isEmpty())
		{
			description = this.getNoValueMessage();
		}
		else
		{
			if (this.delim == null)
			{
				description = CodeHelper.formatCodeDescription(descriptions, PAGE_BREAK);
			}
			else
			{
				description = CodeHelper.formatCodeDescription(descriptions, this.delim);
			}
		}
		return description;
	}

	private Object getReadObject() throws Exception
	{
		Object obj = null;

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

		if (this.property != null)
		{
			StringBuffer buffer = new StringBuffer(this.property);
			String firstChar = String.valueOf(buffer.charAt(0)).toUpperCase();
			buffer.replace(0, 1, firstChar);
			buffer.insert(0, "get");

			String getterName = buffer.toString();

			Method method = beanObj.getClass().getMethod(getterName, (Class[]) null);
			obj = method.invoke(beanObj, (Object[]) null);
		}
		else
		{
			obj = beanObj;
		}

		return obj;
	}

	/**
	 * @param aSelectedPropertyBean
	 * @return
	 */
	private boolean valueExists(Object aSelectedPropertyBean)
	{
		boolean valueExists = true;

		if (aSelectedPropertyBean == null)
		{
			valueExists = false;
		}
		else if (aSelectedPropertyBean instanceof String)
		{
			String value = (String) aSelectedPropertyBean;
			if (value == null || value.equals(""))
			{
				valueExists = false;
			}
		}
		else if (aSelectedPropertyBean instanceof String[])
		{
			String[] values = (String[]) aSelectedPropertyBean;
			if (values == null || values.length == 0)
			{
				valueExists = false;
			}
		}

		return valueExists;
	}

	private String getNoValueMessage() throws JspException
	{
		String message = null;
		if (this.noValueMsgKey == null || this.noValueMsgKey.equals(""))
		{
			message = this.getNoValueMsg();
		}
		else
		{
			message = TagUtils.getInstance().message(pageContext, null, null, this.noValueMsgKey, null);
		}
		return message;
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
	public String getDelim()
	{
		return delim;
	}

	/**
	 * @return
	 */
	public String getNoValueMsg()
	{
		return noValueMsg;
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
	public void setDelim(String string)
	{
		delim = string;
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
	public void setNoValueMsg(String string)
	{
		noValueMsg = string;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string)
	{
		property = string;
	}

	/**
	 * @return
	 */
	public String getNoValueMsgKey()
	{
		return noValueMsgKey;
	}

	/**
	 * @param string
	 */
	public void setNoValueMsgKey(String string)
	{
		noValueMsgKey = string;
	}

}
