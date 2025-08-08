/*
 * Created on Mar 1, 2006
 *
 */
package ui.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.domintf.ISocialSecurity;

import ui.common.format.IFormat;
import ui.common.format.NameFormatter;
import ui.common.format.PhoneNumberFormatter;
import ui.common.format.SocialSecurityFormatter;

/**
 * @author Jim Fisher
 *
 */
public class FormatTag extends TagSupport
{
	private String name;
	private String property;
	private String format;
	private String formatKey;
	private String notAvailable;
	private String notAvailableKey;

	public int doStartTag() throws JspException
	{
		try
		{
			Object obj = TagUtil.getReadObject(super.pageContext, name, property);
			if (obj == null)
			{
				if (this.getNotAvailable() == null)
				{
					this.setNotAvailable("");
				}
				this.writeObject(this.getNotAvailable());
			}
			else
			{
				this.writeObject(obj);
			}
		}
		catch (Exception e)
		{
			throw new JspException(e.getMessage());
		}

		return TagSupport.SKIP_BODY;
	}

	private void writeObject(Object obj) throws IOException
	{
		String str = null;
		IFormat formatter = null;
		if (obj instanceof IName)
		{
			formatter = new NameFormatter(format);
		}
		else if (obj instanceof ISocialSecurity)
		{
			formatter = new SocialSecurityFormatter(format);
		}
		else if (obj instanceof IPhoneNumber)
		{
			formatter = new PhoneNumberFormatter(format);
		}
		else if (obj instanceof String)
		{
			JspWriter writer = this.pageContext.getOut();
			writer.print(str);
		}

		// TODO Properly handle this exception
		if (formatter != null)
		{
			str = formatter.format(obj);
			JspWriter writer = this.pageContext.getOut();
			writer.print(str);
		}
	}

	/**
	 * @return
	 */
	public String getFormat()
	{
		return format;
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
	public String getProperty()
	{
		return property;
	}

	/**
	 * @param string
	 */
	public void setFormat(String string)
	{
		format = string;
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
	public void setProperty(String string)
	{
		property = string;
	}

	/**
	 * @return
	 */
	public String getNotAvailable()
	{
		return notAvailable;
	}

	/**
	 * @return
	 */
	public String getNotAvailableKey()
	{
		return notAvailableKey;
	}

	/**
	 * @param string
	 */
	public void setNotAvailable(String string)
	{
		notAvailable = string;
	}

	/**
	 * @param string
	 */
	public void setNotAvailableKey(String string)
	{
		notAvailableKey = string;
	}

	/**
	 * @return
	 */
	public String getFormatKey()
	{
		return formatKey;
	}

	/**
	 * @param string
	 */
	public void setFormatKey(String string)
	{
		formatKey = string;
	}

}
