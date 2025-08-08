package mojo.struts.taglib.layout.field;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import mojo.struts.taglib.layout.FormTag;
import mojo.struts.taglib.layout.LabelledTag;
import mojo.struts.taglib.layout.el.Expression;
import mojo.struts.taglib.layout.policy.AbstractPolicy;
import mojo.struts.taglib.layout.util.LayoutUtils;
import mojo.struts.taglib.layout.util.NestedHelper;

/**
 * Base class for the tags dealing with form input.<br>
 * <br>
 * This class brings the support of display modes, which applies to the fields tag and the form tag.<br>
 * The form tag has 3 display modes: Create, Edit and Inspect.<br>
 * The field tag has also 3 display modes: Edit, Inspect, and Not displayed.<br>
 * One field display mode can be specified for each form mode.<br>
 * Have a look at the setMode methods to see how to specified the field display modes.<br>
 *
 * Moreover, the field display modes can be modified at run time by applying a display policy.
 *
 * @author: Jean-No?l Ribette
 */
public abstract class AbstractModeFieldTag extends LabelledTag {
	/**
	 * Display mode for this field in the different form editing mode (CREATE, EDIT, INSPECT)
	 * 2: the field will be editable, 1: the field will read-only, 0: the field will not be display at all
	 */
	private String mode = "E,E,I";
	/**
	 * the actual display mode, computed by computeDisplaymode()
	 */
	private short fieldDisplayMode = MODE_EDIT;

	/**
	 * The saved styleClass.
	 */
	private String jspStyleClass = null;
	
	/**
	 * The saved style.
	 */
	private String jspStyle = null;
	
	/**
	 * The saved property.
	 */
	private String jspProperty = null;
	
	protected boolean disabled = false;
	protected boolean readonly = false;
	
	protected Boolean jspDisabled;
	protected Boolean jspReadonly;
	

	/**
	 * Do not display the field at all. No hidden field generated.
	 */
	public static final short MODE_NODISPLAY = 0;
	
	/**
	 * Display the field read only. An hidden field is also genreated.
	 */
	public static final short MODE_INSPECT = 1;
	
	/**
	 * Display the field writable.
	 */
	public static final short MODE_EDIT = 2;
	
	/**
	 * Do not display the field at all, but generates an hidden field.
	 */
	public static final short MODE_HIDDEN = 3;
	
	/**
	 * Display the field value read only, no hidden field generated.
	 */
	public static final short MODE_INSPECT_ONLY = 4;

	/**
	 * Display the field value read only, only if the value is not null. No hidden field generated.
	 */
	public static final short MODE_INSPECT_PRESENT = 5;
	
	/**
	 * Display the field value read only.
	 */
	public static final short MODE_READONLY = 6;
	
	/**
	 * Display the field value disabled.
	 */
	public static final short MODE_DISABLED = 7;

	/**
	 * The name of the policy to apply.
	 */
	private String policy = null;
	protected String styleId;
			
	/**
	 * Set fieldDisplayMode to the correct display mode (create, edit or inspect).
	 */
	protected void computeDisplayMode(Integer in_overrideFieldDisplayMode) {
		if (in_overrideFieldDisplayMode == null) {
			fieldDisplayMode = getSkin().getFormUtils().computeFieldDisplayMode(this);
		} else {
			fieldDisplayMode = in_overrideFieldDisplayMode.shortValue();
		}
		switch (fieldDisplayMode) {
			case MODE_EDIT :
				fieldDisplayMode = getEditAuthorizedMode();
				break;
			case MODE_INSPECT :
			case MODE_INSPECT_ONLY:
			case MODE_INSPECT_PRESENT:
				fieldDisplayMode = getInspectAuthorizedMode(fieldDisplayMode);
				break;
			case MODE_READONLY:
				fieldDisplayMode = getEditAuthorizedMode();
				jspReadonly = readonly ? Boolean.TRUE : Boolean.FALSE; 
				readonly = true;
				break;				
			case MODE_DISABLED:				
				fieldDisplayMode = getEditAuthorizedMode();
				jspDisabled = disabled ? Boolean.TRUE : Boolean.FALSE;
				disabled = true;
				break;
		}
	}
	/**
	 * End a in edit field.
	 */
	protected abstract int doEndEditMode() throws JspException;
	/**
	 * End a field in inspect mode.
	 */
	protected abstract int doEndInspectMode() throws JspException;
	/**
	 * End the tag.
	 */
	public int doEndLayoutTag() throws JspException {
		int lc_ret = EVAL_PAGE;
		switch (fieldDisplayMode) {
			case MODE_NODISPLAY :
			case MODE_HIDDEN :
				break;
			case MODE_INSPECT_ONLY:
			case MODE_INSPECT :
			case MODE_INSPECT_PRESENT:
				lc_ret = doEndInspectMode();
				break;
			case MODE_EDIT :
				lc_ret = doEndEditMode();
				break;
		}		
		return lc_ret;
	}
	/**
	 * Start a field in edit mode.
	 */
	protected abstract int doStartEditMode() throws JspException;
	/**
	 * Start a field in inspect mode.
	 */
	protected abstract int doStartInspectMode() throws JspException;
	/**
	 * Start the tag.
	 */
	public int doStartLayoutTag() throws JspException {
		// Get the value to display.
		Object lc_value = getFieldValue();

		// Print the value in a hidden field if we are in hidden mode. In inspect mode AbstractLayoutFieldTag is doing this.
		if (fieldDisplayMode == MODE_HIDDEN) {
			printHiddenValue(lc_value);
		}

		// Skip the tag if the value is null and we are in inspect mode and we don't display null values, or if we are in mode_nodisplay.
		if (!isFill(lc_value)
			&& 
			(fieldDisplayMode == MODE_INSPECT && !getSkin().getDisplayNullFields() 
			||
			fieldDisplayMode == MODE_INSPECT_PRESENT
			)) {
			fieldDisplayMode = MODE_NODISPLAY;
		}

		// Start to display something for the user.
		switch (fieldDisplayMode) {
			case MODE_INSPECT :
			case MODE_INSPECT_ONLY :
			case MODE_INSPECT_PRESENT :
				return doStartInspectMode();
			case MODE_EDIT :
				return doStartEditMode();
			case MODE_NODISPLAY :
			case MODE_HIDDEN :
				return SKIP_BODY;
		}

		return EVAL_BODY_INCLUDE;
	}
	
	protected void initDynamicValues() {
		// Compute the property.
		jspProperty = property;
		property = Expression.evaluate(property, pageContext);
		property = NestedHelper.getAdjustedProperty(property, pageContext);
		
		// Compute the display mode.
		Integer lc_userModeDisplay = getSkin().getFormUtils().getFieldDisplayMode(pageContext, property);
		computeDisplayMode(lc_userModeDisplay);
		
		// Save the style and styleClass to avoid tag pooling problems.
		jspStyle = style;
		jspStyleClass = styleClass;
		
		// Set the styleClass.
		String lc_userStyleClass = getSkin().getFormUtils().getFieldStyleClass(this);
		if (lc_userStyleClass!=null) {
			styleClass = lc_userStyleClass;	
		}
		
		// Set the style.
		String lc_userStyle = getSkin().getFormUtils().getFieldStyle(pageContext, property);
		if (lc_userStyle!=null) {
			style = lc_userStyle;
		}
	}
	
	protected boolean isFill(Object lc_value) throws JspException {
		return lc_value != null && lc_value.toString().length()!=0;
	}
	public short getFieldDisplayMode() {
		return fieldDisplayMode;
	}
	/**
	 * Return the value(s) that will be displayed.
	 */
	protected abstract Object getFieldValue() throws JspException;
  
	/**
	 * Use by subclasses to know in which mode (create, edit, inspect) the form is.
	 */
	protected int getFormMode() {
		FormTag form = (FormTag) findAncestorWithClass(this, FormTag.class);
		return form.getDisplayMode();
  }
  
	/**
	 * Insert the method's description here.
	 * Creation date: (22/11/2001 15:22:08)
	 * @return java.lang.String
	 */
	public String getPolicy() {
		return policy;
	}
	/**
	 * Print the value in a hidden input field.
	 */
	protected final void printHiddenValue(Object in_value) throws JspException {		
		Collection lc_collection = LayoutUtils.getCollection(in_value, false);
		if (lc_collection != null) {			
			printIndexedHiddenValue(lc_collection);
		} else {
			printSimpleHiddenValue(in_value);
		}		
	}
	protected void printSimpleHiddenValue(Object in_value) throws JspException {
		StringBuffer lc_buffer = new StringBuffer();
		lc_buffer.append("<input type=\"hidden\" name=\"");
		lc_buffer.append(property);
		lc_buffer.append("\" value=\"");
		lc_buffer.append(in_value == null ? "" : in_value.toString());
		if (styleId!=null) {
			lc_buffer.append("\" id=\"");
			lc_buffer.append(styleId);
		}
		lc_buffer.append("\">");
		TagUtils.getInstance().write(pageContext, lc_buffer.toString());
	}
	
	protected void printIndexedHiddenValue(Collection lc_collection) throws JspException {
		// Print all the bean in this collection.
		Iterator lc_iterator = lc_collection.iterator();
		int i = 0;
		StringBuffer lc_buffer = new StringBuffer();
		while (lc_iterator.hasNext()) {
			Object lc_bean = lc_iterator.next();
			lc_buffer.append("<input type=\"hidden\" name=\"");
			lc_buffer.append(property);
			lc_buffer.append("[");
			lc_buffer.append(i);
			lc_buffer.append("]");
			lc_buffer.append("\" value=\"");
			lc_buffer.append(lc_bean == null ? "" : lc_bean.toString());
			lc_buffer.append("\">");
			i++;
		}
		TagUtils.getInstance().write(pageContext, lc_buffer.toString());
	}
	
	/**
	 * Reset the tag after is has been processed.
	 * All variables modified during the evaluation must be reset to their initial value.
	 */
	protected void reset() {
		fieldDisplayMode = MODE_EDIT;
		if (jspDisabled!=null) {
			disabled = jspDisabled.booleanValue();
			jspDisabled = null;	
		}
		if (jspReadonly!=null) {
			readonly = jspReadonly.booleanValue();
			jspReadonly = null;
		}
		styleClass = jspStyleClass;
		style = jspStyle;
		property = jspProperty;
		jspStyleClass = null;
		jspStyle = null;
		jspProperty = null;
	}
	
	/**
	 * Release the tag.
	 */
	public void release() {
		super.release();
		policy = null;
		mode = "E,E,I";
		styleId = null;
		disabled = false;
		readonly = false;		
	}
	protected short getEditAuthorizedMode() {
		if (policy == null)
			return MODE_EDIT;

		AbstractPolicy lc_policy = getSkin().getPolicy();
		return lc_policy.getAuthorizedDisplayMode(policy, name, property, pageContext);
	}
	protected short getInspectAuthorizedMode(short in_inspectOnly) {
		if (policy == null) {
			return in_inspectOnly;
			}
		AbstractPolicy lc_policy = getSkin().getPolicy();
		short lc_allowedDisplayMode;
		switch (lc_policy.getAuthorizedDisplayMode(policy, name, property, pageContext)) {
			case MODE_EDIT:
				lc_allowedDisplayMode = MODE_EDIT; 
			case MODE_INSPECT :
				lc_allowedDisplayMode = MODE_INSPECT;
				break;
			case MODE_INSPECT_ONLY:
				lc_allowedDisplayMode = MODE_INSPECT_ONLY;
				break;
			case MODE_INSPECT_PRESENT:
				lc_allowedDisplayMode = MODE_INSPECT_PRESENT;
				break;
			case MODE_HIDDEN:
				lc_allowedDisplayMode = MODE_HIDDEN;
				break;
			case MODE_NODISPLAY:
				lc_allowedDisplayMode = MODE_NODISPLAY;
				break;
			default:
				throw new IllegalStateException(lc_policy.getClass().getName() + " returns an illegal value");
			
		}
		return lc_allowedDisplayMode;
	}
	/**
	 * Set the display mode
	 * format is XX,XX,XX where XX can be H (hidden), N (not displayed), E (editable), I (inspectable), S(inspect / no hidden). Order is create mode, edit mode, inspect mode
	 */
	public void setMode(String in_mode) {
		mode = in_mode;
	}
	public String getMode() {
		return mode;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (22/11/2001 15:22:08)
	 * @param newPolicy java.lang.String
	 */
	public void setPolicy(String newPolicy) {
		policy = newPolicy;
	}
	public void setProperty(String property) {
		if (LayoutUtils.getNoErrorMode()) {
			this.property = "property";
		} else {
			this.property = property;
	}
	}
	
	/**
	 * Returns the styleId.
	 * @return String
	 */
	public String getStyleId() {
		return styleId;
	}

	/**
	 * Sets the styleId.
	 * @param styleId The styleId to set
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
		
	/**
	 * @return
	 */
	public boolean isDisabled() {
		return disabled;
	}
	/**
	 * @param b
	 */
	public void setDisabled(boolean b) {
		disabled = b;		
	}

	/**
	 * @return
	 */
	public boolean isReadonly() {
		return readonly;
	}

	/**
	 * @param b
	 */
	public void setReadonly(boolean b) {
		readonly = b;		
	}

}