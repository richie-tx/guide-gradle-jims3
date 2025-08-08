package mojo.struts.taglib.layout.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

import com.fgm.web.menu.MenuComponent;
import com.fgm.web.menu.MenuRepository;

import mojo.struts.taglib.layout.datagrid.DatagridImpl;
import mojo.struts.taglib.layout.skin.Skin;
/**
 * Useful methods.
 * 
 * @author: Jean-Noel Ribette
 * @version 0.1
 */
public class LayoutUtils {
	protected static String bundle = Globals.MESSAGES_KEY;
	protected static String localeKey = Globals.LOCALE_KEY;
	protected static MessageResources messages =
		MessageResources.getMessageResources(Constants.Package + ".LocalStrings");
	protected static String name = Constants.BEAN_KEY;

	// special mode for jsp creation without forms and actions
	protected static boolean noErrorMode = false;

	// compatiblity mode with struts-layout 0.3 to avoid bad form title key if not using the form display modes.
	protected static boolean useFormDisplayMode = true;
		
	// old panel nesting compatibility
	protected static boolean panelNesting = false;
	
	/**
	 * Java 1.4 encode method to use instead of deprecated 1.3 version.
	 */
	private static Method encode = null;

	/**
	 * Initialize the encode variable with the 1.4 method if available.
	 */
	static {
		try {
			// get version of encode method with two String args 
			Class[] args = new Class[] { String.class, String.class };
			encode = URLEncoder.class.getMethod("encode", args);
		} catch (NoSuchMethodException e) {
			// log.debug("Could not find Java 1.4 encode method.  Using deprecated version.", e);
		}
	}

	public static void addMenu(PageContext pageContext, MenuComponent menu)
		throws JspException {
		MenuRepository repository =
			(MenuRepository) pageContext.findAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		if (repository == null)
			throw new JspException("Menu repository not found");
		repository.addMenu(menu);
	}
	public static void addMenu(ServletContext servletContext, MenuComponent menu)
		throws ServletException {
		MenuRepository repository =
			(MenuRepository) servletContext.getAttribute(
				MenuRepository.MENU_REPOSITORY_KEY);
		if (repository == null)
			throw new ServletException("Menu repository not found");
		repository.addMenu(menu);
	}
	public static void copyProperties(Object dest, Object orig)
		throws JspException {
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (InvocationTargetException e) {
			Throwable t = e.getTargetException();
			if (t == null)
				t = e;
			System.err.println("LayoutUtils.copyProperties: ");
			System.err.println(t);
			throw new JspException("LayoutUtils.copyProperties: " + t.getMessage());
		} catch (Throwable t) {
			System.err.println("LayoutUtils.copyProperties: ");
			System.err.println(t);
			throw new JspException("LayoutUtils.copyProperties: " + t.getMessage());
		}
	}
	/**
	 * Get the property 'property' of the bean named Constants.BEAN_KEY in the given page context
	 * Handle classic exception
	 **/
	public static Object getBeanFromPageContext(
		PageContext pageContext,
		String property)
		throws JspException {
		Object bean = pageContext.findAttribute(name);
		if (bean == null)
			throw new JspException(messages.getMessage("getter.bean", name));
		Object object = bean;
		if (property != null) {
			try {
				object = PropertyUtils.getProperty(bean, property);
			} catch (IllegalAccessException e) {
				throw new JspException(messages.getMessage("getter.access", property, name));
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				throw new JspException(
					messages.getMessage("getter.result", property, t.toString()));
			} catch (NoSuchMethodException e) {
				throw new JspException(messages.getMessage("getter.method", property, name));
			}
		}
		return object;
	}
	/**
	 * Get the property 'property' of the bean named 'name' in the given pageContext.
	 * Handle classic Exception
	 **/
	public static Object getBeanFromPageContext(
		PageContext pageContext,
		String name,
		String property)
		throws JspException {
		Object bean = pageContext.findAttribute(name);
		if (bean == null) {
			throw new JspException(messages.getMessage("getter.bean", name));
		}
		Object object = bean;

		if (property != null) {
			try {
				object = PropertyUtils.getProperty(bean, property);
			} catch (IllegalAccessException e) {
				throw new JspException(messages.getMessage("getter.access", property, name));
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				throw new JspException(
					messages.getMessage("getter.result", property, t.toString()));
			} catch (NoSuchMethodException e) {
				throw new JspException(messages.getMessage("getter.method", property, name));
			}
		}
		return object;
	}
	public static Collection getCollection(Object in_bean) {
		return getCollection(in_bean, true);
	}
	/**
	 * Returns a collection if the bean if a collection.<br>
	 * @param in_wrap if true and the bean is not a collection return a collection containing the bean
	 */
	public static Collection getCollection(Object in_bean, boolean in_wrap) {
		if (in_bean == null) {
			return null;
		}
		if (in_bean.getClass().isArray()) {
			return Arrays.asList((Object[]) in_bean);
		}
		if (in_bean instanceof Collection) {
			return (Collection) in_bean;
		}
		if (in_bean instanceof Map) {
			return ((Map) in_bean).entrySet();
		}
		if (in_bean instanceof DatagridImpl) {
			return ((DatagridImpl) in_bean).getData();
		}
		if (in_wrap) {
			Collection lc_collection = new ArrayList(1);
			lc_collection.add(in_bean);
			return lc_collection;
		}
		return null;
	}
				
	/**
	 * Build an iterator
	 */
	public static Iterator getIterator(Object collection) throws JspException {
		Iterator iterator;
		if (collection.getClass().isArray())
			collection = Arrays.asList((Object[]) collection);
		if (collection instanceof Collection)
			iterator = ((Collection) collection).iterator();
		else if (collection instanceof Iterator)
			iterator = ((Iterator) collection);
		else if (collection instanceof Map)
			iterator = ((Map) collection).entrySet().iterator();
		else if (collection instanceof DatagridImpl)
			iterator = ((DatagridImpl) collection).getData().iterator();
		else
			throw new JspException(
				messages.getMessage("optionsTag.iterator", collection.toString()));
		return iterator;
	}
	public static Iterator getIterator(
		PageContext pageContext,
		String name,
		String property)
		throws JspException {
		Iterator iterator;
		Object collection = null;

		if (noErrorMode) {
			Vector v = new Vector();
			v.add("element 1");
			v.add("element 2");
			v.add("element 3");
			v.add("element 4");
			v.add("element 5");
			v.add("element 6");
			collection = v;
		} else {
			collection = getBeanFromPageContext(pageContext, name, property);
		}
		return getIterator(collection);
	}
	public static String getLabel(
		PageContext pageContext,
		String key,
		Object[] args)
		throws JspException {
		return getLabel(pageContext, bundle, key, args, false);
	}
	/**
	 * Get the label with the key 'key' from the pageContext messageRessources.
	 * Handle classic exception
	 **/
	public static String getLabel(
		PageContext pageContext,
		String key,
		Object[] args,
		boolean returnNull)
		throws JspException {
		return getLabel(pageContext, bundle, key, args, returnNull);
	}
	/**
	 * Get the label with the key 'key' from the pageContext messageRessources.
	 * Handle classic exception
	 **/
	public static String getLabel(
		PageContext pageContext,
		String bundle,
		String key,
		Object[] args,
		boolean returnNull)
		throws JspException {
			return getLabel((HttpServletRequest)pageContext.getRequest(), pageContext.getServletContext(), bundle, key, args, returnNull);
		}
	public static String getLabel(
		HttpServletRequest request,
		ServletContext servletContext,
		String bundle,
		String key,
		Object[] args,
		boolean returnNull)
		throws JspException {			
		// Acquire the resources object containing our messages
		MessageResources resources =
			(MessageResources) request.getAttribute(bundle);
		if (resources == null) {
			resources = (MessageResources) servletContext.getAttribute(bundle);
		}
		if (resources == null) {
			throw new JspException(messages.getMessage("messageTag.resources", bundle));
		}

		// Calculate the Locale we will be using
		Locale locale = null;
		try {
			locale =
				(Locale) request.getSession(false).getAttribute(localeKey);
		} catch (IllegalStateException e) { // Invalidated session
			locale = null;
		} catch (NullPointerException npe) {
			// no session yet.
			locale = request.getLocale();	
		}
		if (locale == null)
			locale = Locale.getDefault();

		// Retrieve the message string we are looking for
		String message = null;
		if (key != null) {
			message = resources.getMessage(locale, key, args);
		}
		if (message == null) {
			if (returnNull)
				return null;
			else
				return key;
		}
		return message;
	}
	// return the locale
	public static Locale getLocale(HttpServletRequest in_request) {
		// first look in the LOCALE_KEY
		Object l_object = in_request.getSession().getAttribute(Globals.LOCALE_KEY);
		if (l_object != null && l_object instanceof Locale)
			return (Locale) l_object;

		// else return the request Locale
		return in_request.getLocale();
	}
	// return the locale
	public static Locale getLocale(PageContext in_pageContext) {
		// first look in the LOCALE_KEY
		Object l_object = in_pageContext.findAttribute(Globals.LOCALE_KEY);
		if (l_object != null && l_object instanceof Locale)
			return (Locale) l_object;

		// else return the request Locale
		return in_pageContext.getRequest().getLocale();
	}
	public static MenuComponent getMenu(PageContext pageContext, String menuName)
		throws JspException {
		MenuRepository repository =
			(MenuRepository) pageContext.findAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		if (repository == null)
			throw new JspException("Menu repository not found");
		return repository.getMenu(menuName);
	}
	public static boolean getNoErrorMode() {
		return noErrorMode;
	}

	/**
	 * Get the property 'property' of the bean 'bean'
	 * Handle classic exception
	 **/
	public static Object getProperty(Object bean, String property)
		throws JspException {
		Object object = bean;

		if (noErrorMode)
			return object;

		if (property != null) {
			try {
				object = PropertyUtils.getProperty(bean, property);
			} catch (IllegalAccessException e) {
				throw new JspException("IllegalAccessException while trying to access property " + property + " of a " + bean.getClass().getName());
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				throw new JspException("Invocation target exception (" + (t==null ? "" : t.getClass().getName() + " " + t.getMessage()) + ") while trying to access property " + property + " of a " + bean.getClass().getName());				
			} catch (NoSuchMethodException e) {
				throw new JspException("No method to get the property " + property + " of " + bean.toString() + " (" + bean.getClass().getName() + ")");
			} catch (IllegalArgumentException iae) {
				// one of the nested property is null.	
				object = null;
			}
		}
		return object;
	}
	public static String getPropertyToChoose(ServletRequest request) {
		String property =
			request.getParameter("frImproveStrutsTaglibLayoutPROPERTY_TO_CHOOSE");
		if (property != null && property.length() > 0)
			return property;
		return null;
	}
	public static Skin getSkin(HttpSession session) {
		if (session == null)
			return Skin.getSkin("default","");
		Skin skin = (Skin) session.getAttribute("LIGHT_SKIN");
		if (skin == null) {
			return Skin.getSkin("default","");
		}
		return skin;
	}
	public static void init(ServletContext context)
		throws javax.servlet.UnavailableException {
		if ("noerror".equals(context.getInitParameter("struts-layout-mode")))
			noErrorMode = true;
	
		String l_string = context.getInitParameter("struts-layout-form-display-mode");
		if ("false".equals(l_string)) {
			setUseFormDisplayMode(false);
		}
		l_string = context.getInitParameter("struts-layout-old-panel-nesting");
		if ("false".equals(l_string)) {
			setPanelNesting(false);
		}
		
		// initialize an empty menu repository
		if (context.getInitParameter("struts-layout-menu") != null)
			context.setAttribute(MenuRepository.MENU_REPOSITORY_KEY, new MenuRepository());
	}

	/**
	 * Returns the errors associated with an input field.
	 */
	public static List retrieveErrors(PageContext pageContext, String property)
		throws JspException {
		ActionErrors errors =
			(ActionErrors) pageContext.getAttribute(
				Globals.ERROR_KEY,
				PageContext.REQUEST_SCOPE);
		List localizedErrors = new ArrayList();
		if (errors != null && !errors.isEmpty()) {
			Iterator iterator = errors.get(property);
			while (iterator != null && iterator.hasNext()) {
				ActionMessage report = (ActionMessage) iterator.next();
				localizedErrors.add(
					LayoutUtils.getLabel(pageContext, report.getKey(), report.getValues()));
			}
		}
		return localizedErrors;
	}

		/**
	 * Set the Struts light skin to use
	 * The file in config/'skin'.css will be use.
	 **/
	public static void setSkin(HttpSession session, String skin) {
		if (skin != null && !skin.equals("")) {
			String theSkin = skin;
			if (!theSkin.endsWith(".css")) {
				session.setAttribute("LIGHT_SKIN", Skin.getSkin(theSkin, ""));
			} else {
				session.setAttribute("LIGHT_SKIN", Skin.getSkin(theSkin.substring(0, theSkin.lastIndexOf('.')), null));				
			}
		} else
			session.removeAttribute("LIGHT_SKIN");
	}
	
	/**
	 * Gets the useFormDisplayMode.
	 * @return Returns a boolean
	 */
	public static boolean getUseFormDisplayMode() {
		return useFormDisplayMode;
	}

	/**
	 * Sets the useFormDisplayMode.
	 * @param useFormDisplayMode The useFormDisplayMode to set
	 */
	public static void setUseFormDisplayMode(boolean useFormDisplayMode) {
		LayoutUtils.useFormDisplayMode = useFormDisplayMode;
	}

	/**
	 * Returns the panelNesting.
	 * @return boolean
	 */
	public static boolean isPanelNesting() {
		return panelNesting;
	}

	/**
	 * Sets the panelNesting.
	 * @param panelNesting The panelNesting to set
	 */
	public static void setPanelNesting(boolean panelNesting) {
		LayoutUtils.panelNesting = panelNesting;
	}	
	
	/**
	 * Compute an url, using RequestUtils.computeURL from Struts.
	 * If links should not be followed if there are unsaved form changes, 
	 * includes call to the required javascript code.
	 */
	public static String computeURL(PageContext in_pageContext, String in_forward, String in_url, String in_page, Map in_params, String in_anchor, boolean in_redirect, String in_target) throws JspException {
		String lc_computedURL = null;
		
		Skin lc_skin = getSkin(in_pageContext.getSession()); 
		
		if (lc_skin.isLinkTokenRequired()) {
			String lc_token = (String) in_pageContext.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
			if (lc_token != null) {
				if (in_params==null) {
					in_params = new HashMap();	
				}
				in_params.put(Constants.TOKEN_KEY, lc_token);
			}
		}

		try {
			lc_computedURL =  TagUtils.getInstance().computeURL(in_pageContext, in_forward, in_url, in_page, null, lc_computedURL, in_params, in_anchor, in_redirect);
		} catch (MalformedURLException e) {
			TagUtils.getInstance().saveException(in_pageContext, e);
			throw new JspException(e.getMessage());			
		}
		if (in_target==null && !lc_skin.getFollowLinkIfFormChanged()) {
			StringBuffer lc_buffer = new StringBuffer("javascript:checkFormChange('");
			lc_buffer.append(lc_computedURL);
			lc_buffer.append("','");
			lc_buffer.append(getLabel(in_pageContext, "layout.dataLost", null));
			lc_buffer.append("')");
			lc_computedURL = lc_buffer.toString();
		}

		return lc_computedURL;
	}
	
	/**
	 * Use the new URLEncoder.encode() method from java 1.4 if available, else
	 * use the old deprecated version.  This method uses reflection to find the appropriate
	 * method; if the reflection operations throw exceptions, this will return the url
	 * encoded with the old URLEncoder.encode() method.
	 * @return String - the encoded url.
	 * 
	 * Copy from struts.
	 */
	public static String encodeURL(String in_url) {
		try {
			// encode url with new 1.4 method and UTF-8 encoding if possible.
			if (encode != null) {
				return (String) encode.invoke(null, new Object[] { in_url, "UTF-8" });
			}
		} catch (IllegalAccessException e) {
			// nothing to do.
		} catch (InvocationTargetException e) {
			// nothing to do.
		}

		return URLEncoder.encode(in_url);
	}
}