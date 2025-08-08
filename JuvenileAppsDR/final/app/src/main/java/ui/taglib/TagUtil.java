/*
 * Created on Mar 1, 2006
 *
 */
package ui.taglib;

import java.lang.reflect.Method;

import javax.servlet.jsp.PageContext;

/**
 * @author Jim Fisher
 *
 */
public class TagUtil
{
	public static Object getReadObject(PageContext pageContext, String name, String property) throws Exception
	{
		Object obj = null;

		// attempt to locate bean in the page context
		Object beanObj = pageContext.getAttribute(name);

		if (beanObj == null)
		{
			// attempt to locate bean in the request context
			beanObj = pageContext.getRequest().getAttribute(name);
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the session context
			beanObj = pageContext.getSession().getAttribute(name);
		}

		if (beanObj == null)
		{
			// attempt to locate bean in the application context
			beanObj = pageContext.getServletContext().getAttribute(name);
		}

		if (property != null)
		{
			

			String getterName = appendGetter(property, "get");
			
			Method method = null;
			
			try
			{
				method = beanObj.getClass().getMethod(getterName, (Class[]) null);
			}
			catch(NoSuchMethodException e)
			{
				//if getXXX doesn't exist, try isXXX (for boolean)
				getterName = appendGetter(property, "is");
				method = beanObj.getClass().getMethod(getterName, (Class[]) null);
			}
			
			obj = method.invoke(beanObj, (Object[]) null);
		}
		else
		{
			obj = beanObj;
		}

		return obj;
	}
	
	private static String appendGetter(String property, String getter)
	{
		StringBuffer buffer = new StringBuffer(property);
		String firstChar = String.valueOf(buffer.charAt(0)).toUpperCase();
		buffer.replace(0, 1, firstChar);
		buffer.insert(0, getter);
		
		return buffer.toString();
	}
}
