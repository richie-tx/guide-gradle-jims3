package mojo.struts.taglib.layout.field;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import mojo.struts.taglib.layout.el.Expression;
import mojo.struts.taglib.layout.event.EndLayoutEvent;
import mojo.struts.taglib.layout.event.ErrorEvent;
import mojo.struts.taglib.layout.event.LayoutEventListener;
import mojo.struts.taglib.layout.event.StartLayoutEvent;
import mojo.struts.taglib.layout.util.LayoutUtils;
/**
 * Abstract class defining the layout of the field tags.<br>
 * The tag renders html code looking like: &lt;tr&gt;&lt;th&gt; input field title &lt;/th&gt;&lt;td&gt; input field &lt;/td&gt;&lt;/tr&gt;.<br>
 * <br>
 * This class brings the support of an help message to the field that will be displayed under the field with the default css class "HELP",<br>
 * the notion of mandatory field, and displays the error messages associated with the field.
 *
 * @author: Eric Amundson
 */
public abstract class AbstractLayoutFieldTag extends AbstractModeFieldTag implements LayoutEventListener {
	/**
	 * Help message key.
	 */
	private String help = null;

	/**
	 * Help message styleClass
	 */
	private String helpStyleClass = "HELP";

	/**
	 * Mandatory value. If true, display a red start after the tag.
	 */
	private String isRequired = "false";
	
	/**
	 * Saved jsp isRequired value.
	 */
	private String jspIsRequired;

	/**
	 * Error styleClass
	 */
	private String errorStyleClass = "ERROR";
	
	/**
	 * Hint message
	 */
	private String hint = null;
	
	/**
	 * Generate the tag layout: default is true.
	 */
	private boolean layout = true;
	/**
	 * Tooltip for this field
	 */
	private String tooltip = null;
	
	/**
	 * Model
	 */
	private String model = null;
	
/**
 * Prepare to display the errors.
 */
protected void beginFieldError(List out_errors) throws JspException {
    // Get the error if any and print the html tags
    out_errors.addAll(retrieveErrors());
    if (out_errors.size()!=0 && getSkin().getDisplayErrorMessage()) {
		StringBuffer lc_buffer = new StringBuffer();
		getSkin().getFieldInterface(model).doStartErrors(lc_buffer, this, out_errors);
		TagUtils.getInstance().write(pageContext, lc_buffer.toString());
    }
}
/**
 * Start to print the field: display the field title.
 */
protected final void beginFieldLayout() throws JspException {
	StringBuffer buffer = new StringBuffer();
	
	if (Boolean.FALSE.equals(new StartLayoutEvent(this, null).send())) {
		// For compatibiliy reason, print a <tr> tag if the tag is not nested in a layout tag.
		buffer.append("<tr>");	
	}
	
	// Start to display the field.
	getSkin().getFieldInterface(model).doStartField(buffer, this, getLabel(), getFieldValue());
		
	// Write the buffer.
	TagUtils.getInstance().write(pageContext, buffer.toString());
}

	protected abstract int doEndEditField() throws JspException;
protected int doEndEditMode() throws JspException {
	int lc_result = doEndEditField();
	if (layout) {
	    endFieldLayout();
	}
    return lc_result;
}
	protected abstract int doEndInspectField() throws JspException;
protected final int doEndInspectMode() throws JspException {
	int lc_result = doEndInspectField();
	if (layout) {
	    endFieldLayout();
	}
    return lc_result;
}
	protected abstract int doStartEditField() throws JspException;
protected int doStartEditMode() throws JspException {
	List lc_errors = new ArrayList();	
	if (layout) {
	    beginFieldLayout();
	    beginFieldError(lc_errors);
	}
    int lc_result = doStartEditField();
    if (layout) {
    	endFieldError(lc_errors);
    }
    return lc_result;
}
	protected abstract int doStartInspectField() throws JspException;
protected final int doStartInspectMode() throws JspException {
	if (layout) {
	    beginFieldLayout();
	}
    if (MODE_INSPECT==getFieldDisplayMode()) {
	    printHiddenValue(getFieldValue());
    }
    return doStartInspectField();
}

protected void endFieldError(List in_errors) throws JspException {
	if (in_errors.size()!=0) {
		new ErrorEvent(this, in_errors).send();
		if (getSkin().getDisplayErrorMessage()) {
			StringBuffer lc_buffer = new StringBuffer();
			getSkin().getFieldInterface(model).doEndErrors(lc_buffer, this, in_errors);
			TagUtils.getInstance().write(pageContext, lc_buffer.toString());			
		}
	}
}
/**
 * End the field (close the html tags)
 */
protected final void endFieldLayout() throws JspException {
	StringBuffer buffer = new StringBuffer();
	
	getSkin().getFieldInterface(model).doEndField(buffer, this, getFieldValue());

	// Write the buffer.
	TagUtils.getInstance().write(pageContext, buffer.toString());
	
	// End the layout.
	if (Boolean.FALSE.equals(new EndLayoutEvent(this, null).send())) {
		// For compatiblity reason write a </tr> if the tag is not nested in a layout tag.
		TagUtils.getInstance().write(pageContext, "</tr>");	
	}
	

	if (help!=null) {
		buffer.setLength(0);
		// PENDING the text is displayed on the right if the tag is in a line tag.
		if (Boolean.FALSE.equals(new StartLayoutEvent(this, null).send())) {
			buffer.append("<tr>");
		}		
		buffer.append("<td colspan=\"").append(getSkin().getFieldInterface(model).getColumnNumber()).append("\" class=\"");
		buffer.append(helpStyleClass);
		buffer.append("\">");
		buffer.append(LayoutUtils.getLabel(pageContext, help, null));
		buffer.append("</td>");
		TagUtils.getInstance().write(pageContext, buffer.toString());
		if (Boolean.FALSE.equals(new EndLayoutEvent(this, null).send())) {
			TagUtils.getInstance().write(pageContext, "</tr>");
		}
	}
}

/**
 * Insert the method's description here.
 * Creation date: (01/12/2001 00:52:02)
 * @return java.lang.String
 */
public String getErrorStyleClass() {
	return errorStyleClass;
}
public String getHelp() {
    return help;
}
/**
 * Insert the method's description here.
 * Creation date: (01/12/2001 00:04:23)
 * @return java.lang.String
 */
public String getHelpStyleClass() {
	return helpStyleClass;
}
/**
 * Insert the method's description here.
 * Creation date: (01/12/2001 01:26:01)
 * @return boolean
 */

public void release() {
	super.release();
	help = null;
	hint = null;
	tooltip = null;
	helpStyleClass = "HELP";
	isRequired = "false";
	errorStyleClass = "ERROR";
	layout = true;
	model = null;
}

	protected void reset() {
		super.reset();
		isRequired = jspIsRequired;
		jspIsRequired = null;
	}

public Object processStartLayoutEvent(StartLayoutEvent in_event) throws JspException {
	return Boolean.FALSE;
}
public Object processEndLayoutEvent(EndLayoutEvent in_event) {
	return Boolean.FALSE;	
}
/**
 * Retrieve the errors associated with the current property if there are any.
 */
private List retrieveErrors() throws JspException {
	return LayoutUtils.retrieveErrors(pageContext, property);
}
/**
 * Insert the method's description here.
 * Creation date: (01/12/2001 00:52:02)
 * @param newErrorStyleClass java.lang.String
 */
public void setErrorStyleClass(String newErrorStyleClass) {
	errorStyleClass = newErrorStyleClass;
}

	protected void initDynamicValues() {		
		super.initDynamicValues();
		jspIsRequired = isRequired;
		isRequired = Expression.evaluate(jspIsRequired, pageContext);
	}

/**
 * Set the help message key.
 */
public void setHelp(String help) {
    this.help = help;
}
/**
 * Set the tooltip
 */
public void setTooltip(String tooltip) {
    this.tooltip = tooltip;
}
/**
 * Get the tooltip
 */
public String getTooltip() {
    return tooltip;
}
/**
 * Insert the method's description here.
 * Creation date: (01/12/2001 00:04:23)
 * @param newHelpStyleClass java.lang.String
 */
public void setHelpStyleClass(String newHelpStyleClass) {
	helpStyleClass = newHelpStyleClass;
}
/**
 * If set to "TRUE" a red star is added after the field when the value is null
 * A piece of Javascript adds or removes the start when the value changes
 */
public void setIsRequired(String newIsRequired) {
	isRequired = newIsRequired;
}

public String getIsRequired() {
	return isRequired;
}

	public boolean isRequired() {
		return "true".equalsIgnoreCase(isRequired);
	}


	public void setHint(String in_hint) {
		hint = in_hint;
	}
	public String getHint() {
		return hint;
	}

	/**
	 * Returns the layout.
	 * @return boolean
	 */
	public boolean isLayout() {
		return layout;
	}

	/**
	 * Sets the layout.
	 * @param layout The layout to set
	 */
	public void setLayout(boolean layout) {
		this.layout = layout;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String in_string) {
		model = in_string;
	}

	/**
	 * Returns an empty label if layout is set to false. 
	 */
	protected String getLabel() throws JspException {
		if (isLayout()) {
			return super.getLabel();
		} else {
			return "";
		}
	}

}
