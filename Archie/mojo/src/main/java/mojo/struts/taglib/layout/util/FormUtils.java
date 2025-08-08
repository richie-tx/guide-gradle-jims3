/*
 * (c) Copyright 2001 Improve SA.
 * All Rights Reserved.
 */
package mojo.struts.taglib.layout.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts.action.ActionForm;
import org.apache.struts.taglib.html.Constants;

import mojo.struts.taglib.layout.field.AbstractModeFieldTag;

/**
 * Utility methods to deal with form and field.
 * 
 * @version 	1.0
 * @author
 */
public class FormUtils {
	/**
	 * Key of the map storing the form display modes.
	 */
	private static final String FORM_MODE_KEY = "mojo.struts.taglib.layout.util.FormUtils.FORM_MODE_KEY";

	public static final int CREATE_MODE = 0;
	public static final int EDIT_MODE = 1;	
	public static final int INSPECT_MODE = 2;
	
	/**
	 * Key of the map storing the field styleClasses.
	 */
	private static final String FIELD_STYLECLASS_KEY = "mojo.struts.taglib.layout.util.FormUtils.FIELD_STYLECLASS_KEY";

	/**
	 * Key of the map storing the field mode display.
	 */
	private static final String FIELD_MODE_KEY = "mojo.struts.taglib.layout.util.FormUtils.FIELD_MODE_KEY";

	/**
	 * Key of the map storing the label field styles.
	 */
	private static final String FIELD_STYLE_KEY = "mojo.struts.taglib.layout.util.FormUtils.FIELD_STYLE_KEY";
	
	/**
	 * Key of the list storing the value field styles.
	 */
	private static final String FIELD_VALUES_STYLE_KEY = "mojo.struts.taglib.layout.util.FormUtils.FIELD_VALUES_STYLE_KEY";

	/**
	 * Utility method to get the form display modes.
	 */	
	private static Map getFormModes(HttpSession in_session) {
		if (in_session==null) {
			return new HashMap();
		}
		Map lc_map = (Map) in_session.getAttribute(FORM_MODE_KEY);
		if (lc_map==null) {
			lc_map = new HashMap();
			in_session.setAttribute(FORM_MODE_KEY, lc_map);
		}
		return lc_map;
	}
	
	/**
	 * Utility method to get the field display modes.
	 */
	private static Map getFieldModes(HttpSession in_session, ActionForm in_form) {
		Map lc_mapForm = null;
		if (in_session!=null) {
			lc_mapForm = (Map) in_session.getAttribute(FIELD_MODE_KEY);
		}
		if (lc_mapForm == null) {
			lc_mapForm = new HashMap();
			if (in_session!=null) {
				in_session.setAttribute(FIELD_MODE_KEY, lc_mapForm);
			}
		}
		Map lc_mapField = (Map) lc_mapForm.get(in_form.getClass());
		if (lc_mapField == null) {
			lc_mapField = new HashMap();
			lc_mapForm.put(in_form.getClass(), lc_mapField);
		}
		return lc_mapField;
	}
	
	/**
	 * Set the form display mode.
	 * Used in user's Action class.
	 */
	public static void setFormDisplayMode(HttpServletRequest in_request, ActionForm in_form, int in_mode) {		
		if (in_form==null) {
			throw new IllegalArgumentException("in_form cannot be null");
		}
		Map lc_map = getFormModes(in_request.getSession());
		lc_map.put(in_form.getClass(), new Integer(in_mode));
		
		//in_request.getSession().setAttribute(FORM_MODE_KEY,new Integer(in_mode));
	}
	
	/**
	 * Set the field display mode.
	 * Used in user's Action class.
	 */
	public static void setFieldDisplayMode(HttpServletRequest in_request, ActionForm in_form, String in_fieldName, int in_mode) {		
		if (in_form == null) {
			throw new IllegalArgumentException("in_form cannot be null");
		}
		if (in_fieldName == null) {
			throw new IllegalArgumentException("in_fieldName cannot be null");
		}
		Map lc_field = getFieldModes(in_request.getSession(), in_form);
		lc_field.put(in_fieldName, new Integer(in_mode));
	}
	
	/**
	 * Get the form display mode
	 */	
	public static int getFormDisplayMode(PageContext in_page) {
		Object lc_form = in_page.findAttribute(Constants.BEAN_KEY);
		if (lc_form == null) {
			// defaut display mode is EDIT.
			return EDIT_MODE;	
		}
		return getFormDisplayMode(in_page.getSession(), (ActionForm) lc_form);
	}
	
	/**
	 * Get the field display mode
	 */	
	public static Integer getFieldDisplayMode(PageContext in_page, String in_fieldName) {
		Object lc_form = in_page.findAttribute(Constants.BEAN_KEY);
		if (lc_form == null) {
			// no default value
			return null;	
		}
		return getFieldDisplayMode(in_page.getSession(), (ActionForm) lc_form, in_fieldName);
	}
	
	/**
	 * Get the form display mode
	 */	
	public static int getFormDisplayMode(HttpServletRequest in_request, ActionForm in_form) {
		return getFormDisplayMode(in_request.getSession(), in_form);
	}
	
	/**
	 * Get the field display mode
	 */	
	public static Integer getFieldDisplayMode(HttpServletRequest in_request, ActionForm in_form, String in_fieldName) {
		return getFieldDisplayMode(in_request.getSession(), in_form, in_fieldName);
	}
	
	/**
	 * Get the form display mode
	 */	
	protected static int getFormDisplayMode(HttpSession in_session, ActionForm in_form) {
		Map lc_map = getFormModes(in_session);
		if (in_form==null) {
			// defaut display mode is EDIT.
			return EDIT_MODE;	
		}
		Integer lc_mode = (Integer) lc_map.get(in_form.getClass());
		if (lc_mode==null) {
			// defaut display mode is EDIT.
			return EDIT_MODE;	
		}
		return lc_mode.intValue();
	}

	/**
	 * Get the field display mode
	 */	
	protected static Integer getFieldDisplayMode(HttpSession in_session, ActionForm in_form, String in_fieldName) {
		if (in_form == null) {
			// no default value
			return null;	
		}
		if (in_fieldName == null) {
			// no default value
			return null;	
		}
		Map lc_map = getFieldModes(in_session, in_form);
		Integer lc_mode = (Integer) lc_map.get(in_fieldName);
		if (lc_mode == null) {
			// no default value
			return null;	
		}
		return lc_mode;
	}
	/**
	 * Compute a field display mode.
	 * Used in custom tags
	 */
	public static short computeFieldDisplayMode(PageContext in_pageContext, String in_mode) {
		int lc_mode = getFormDisplayMode(in_pageContext);
		int lc_modePosition = lc_mode*2;
		if (in_mode==null || in_mode.length()<lc_modePosition) {
			throw new IllegalArgumentException("Bad form / field display mode (" + lc_mode + ";" + in_mode);
		}
		char lc_char = in_mode.charAt(lc_modePosition);
		switch (Character.toUpperCase(lc_char)) {
			case 'E': return AbstractModeFieldTag.MODE_EDIT;
			case 'I': return AbstractModeFieldTag.MODE_INSPECT;
			case 'H': return AbstractModeFieldTag.MODE_HIDDEN;
			case 'N': return AbstractModeFieldTag.MODE_NODISPLAY;
			case 'S': return AbstractModeFieldTag.MODE_INSPECT_ONLY;
			case 'P': return AbstractModeFieldTag.MODE_INSPECT_PRESENT;
			case 'R': return AbstractModeFieldTag.MODE_READONLY;
			case 'D': return AbstractModeFieldTag.MODE_DISABLED;
		}
		throw new IllegalArgumentException("Bad field display mode " + lc_char);
	}
	
	private static Map getFieldStyleClass(HttpServletRequest in_request) {
		Map lc_map = (Map) in_request.getAttribute(FIELD_STYLECLASS_KEY);
		if (lc_map==null) {
			lc_map = new HashMap();
			in_request.setAttribute(FIELD_STYLECLASS_KEY, lc_map);
		}
		return lc_map;
	}
	
	
	private static Map getFieldStyle(HttpServletRequest in_request) {
		Map lc_map = (Map) in_request.getAttribute(FIELD_STYLE_KEY);
		if (lc_map==null) {
			lc_map = new HashMap();
			in_request.setAttribute(FIELD_STYLE_KEY, lc_map);
		}
		return lc_map;
	}	
	
	/**
	 * Override a field styleClass.
	 * Used in user's Action class.
	 */
	public static void setFieldStyleClass(HttpServletRequest in_request, String in_fieldName, String in_styleClass) {
		getFieldStyleClass(in_request).put(in_fieldName, in_styleClass);
	}
	
	/**
	 * Override a field style.
	 * Used in user's Action class.
	 */
	public static void setFieldStyle(HttpServletRequest in_request, String in_fieldName, String in_style) {
		getFieldStyle(in_request).put(in_fieldName, in_style);
	}	

	/**
	 * Get a field styleClass.
	 * Used in custom tags.
	 */
	public static String getFieldStyleClass(PageContext in_pageContext, String in_fieldName) {
		return (String) getFieldStyleClass((HttpServletRequest)in_pageContext.getRequest()).get(in_fieldName);
	}
	
	/**
	 * Get a field style.
	 * Used in custom tags.
	 */
	public static String getFieldStyle(PageContext in_pageContext, String in_fieldName) {
		return (String) getFieldStyle((HttpServletRequest)in_pageContext.getRequest()).get(in_fieldName);
	}
	
	/**
	 * Get the style for the current field value.
	 */
	public static String getFieldValueStyle(PageContext in_pageContext) {
		List lc_list = (List) in_pageContext.getRequest().getAttribute(FIELD_VALUES_STYLE_KEY);
		if (lc_list==null || lc_list.isEmpty()) {
			return ""; 	
		} else {
			StringBuffer lc_style = new StringBuffer();
			for (int i=0;i< lc_list.size();i++) {
					lc_style.append(lc_list.get(i));
			}
			return lc_style.toString();
		}
	}
	
	public static void addFieldValueStyle(PageContext in_pageContext, String in_style) {
		List lc_list = (List) in_pageContext.getRequest().getAttribute(FIELD_VALUES_STYLE_KEY);
		if (lc_list==null) {
			lc_list = new ArrayList();	
			in_pageContext.getRequest().setAttribute(FIELD_VALUES_STYLE_KEY, lc_list);
		}
		lc_list.add(in_style);
	}
	public static void removeFieldValueStyle(PageContext in_pageContext) {
		List lc_list = (List) in_pageContext.getRequest().getAttribute(FIELD_VALUES_STYLE_KEY);
		lc_list.remove(lc_list.size()-1);
	}

}
