package mojo.struts.taglib.layout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import mojo.struts.taglib.layout.util.FormUtilsInterface;
import mojo.struts.taglib.layout.util.LayoutUtils;

/**
 * Display a form tag using the panel layout.<br>
 * This tag can specify a default action for the action using the DispatchAction class.<br>
 * 
 * @author: Eric Amundson
 */
public class FormTag extends PanelTag {
	protected String action = null;
	protected String enctype = null;
	
	protected String focus = null;
	protected String editFocus = null;
	protected String inspectFocus = null;
	protected String createFocus = null;
	
	
	protected String method = "POST";
	protected String onreset = null;
	protected String onsubmit = null;
	protected String scope = "session";
	protected String style = null;
	protected String target = null;
	protected String type = null;
	protected String reqCode = null;
	protected org.apache.struts.taglib.html.FormTag formTag = 
		new org.apache.struts.taglib.html.FormTag();

	protected int mode = FormUtilsInterface.EDIT_MODE;
	
	/**
	 * @deprecated
	 */
	public static final int CREATE_MODE = FormUtilsInterface.CREATE_MODE;
	
	/**
	 * @deprecated
	 */
	public static final int EDIT_MODE = FormUtilsInterface.EDIT_MODE;
	
	/**
	 * @deprecated
	 */
	public static final int INSPECT_MODE = FormUtilsInterface.INSPECT_MODE;

	public FormTag() {
		super();
	//	name = Constants.BEAN_KEY;
	}
	
	public int doEndLayoutTag() throws JspException {
	
		StringBuffer buffer = new StringBuffer();
		doPrintBlankLine(buffer);
		doAfterBody(buffer);
		TagUtils.getInstance().write(pageContext, buffer.toString());				
	
		buffer.setLength(0);
		doEndPanel(buffer);
		TagUtils.getInstance().write(pageContext, buffer.toString());
		doEndLayout();
		
		int ret = formTag.doEndTag();
		formTag.release();
		
		return ret;
	}
	
	public int doStartLayoutTag() throws JspException {
		//Initialize the struts form tag.
		LayoutUtils.copyProperties(formTag,this);
		
		//	Start the struts form tag.
		int ret = formTag.doStartTag();
		
		//Start the layout.
		doStartLayout();
		StringBuffer buffer = new StringBuffer("\n");
		doStartPanel(buffer);
		TagUtils.getInstance().write(pageContext,buffer.toString());
		
		
	
		// the mode must be calculated after the struts formTag is begun.
		mode = getSkin().getFormUtils().getFormDisplayMode(pageContext);
		computeDisplayMode();
		
		buffer.setLength(0);
		// if needed, add an hidden input field setting the reqCode.
		processRequestCode(buffer);
	
		// recalculate the key.
		processKey();
		
		// recalculate the focus.
		processFocus();
		
		doPrintTitle(buffer);
		doBeforeBody(buffer);
		doPrintBlankLine(buffer);
		TagUtils.getInstance().write(pageContext,buffer.toString());
		return ret;	
	}

	/**
	 * Specify the default action parameter if Action class is subclass of DispatchAction.
	 */ 
	protected void processRequestCode(StringBuffer buffer) throws JspException {
		if (reqCode!=null) {
			try {
				String parameter = computeActionParameter(pageContext, action);
			
				// set the default reqCode if we are posting to a dispatch action
				if (parameter!=null) {
					buffer.append("<input type=\"hidden\" name=\"");
					buffer.append(parameter);
					buffer.append("\" value=\"");
					buffer.append(reqCode);
					buffer.append("\">\n");
				}
			} catch (Exception e) {				
				JspException jspe = new JspException("BaseHandlerTag - cannot acces the DispatchAction parameter: " + e.getMessage() + "(" + e.getClass().getName() + ")");
				TagUtils.getInstance().saveException(pageContext, jspe);
				throw jspe;				
			}
		}
	}
	
	/**
	 * Look for the struts action parameter value defined in struts-config.	 
	 */
	public static String computeActionParameter(PageContext in_pageContext, String in_actionName) {
		String value = computeActionName(in_actionName);
		ModuleConfig lc_moduleConfig = TagUtils.getInstance().getModuleConfig(in_pageContext);
		ActionConfig lc_actionConfig = lc_moduleConfig.findActionConfig(value);		
		String parameter = lc_actionConfig.getParameter();
		return parameter;
	}

	/**
	 * Look for the struts action name as defined in struts-config.
	 */
	protected static String computeActionName(String in_actionName) {
		return TagUtils.getInstance().getActionMappingName(in_actionName);
	}

	/**
	 * Add .edit /.create or .inspect to the end of the key if needed.
	 */
	protected void processKey() {
		if (key!=null && LayoutUtils.getUseFormDisplayMode()) {
			switch (mode) {
				case FormUtilsInterface.EDIT_MODE:
					key = key + ".edit";
					break;
				case FormUtilsInterface.CREATE_MODE:
					key = key + ".create";
					break;
				case FormUtilsInterface.INSPECT_MODE:
					key = key + ".inspect";
			}
		}
	}
	
	/**
	 * Set the focus according to the form display mode.
	 */
	protected void processFocus() {		
		switch (mode) {
			case FormUtilsInterface.EDIT_MODE:
				if (editFocus!=null) {
					formTag.setFocus(editFocus);
				}
				break;
			case FormUtilsInterface.INSPECT_MODE:
				if (inspectFocus!=null) {
					formTag.setFocus(inspectFocus);				
				}
				break;
			case FormUtilsInterface.CREATE_MODE:
				if (createFocus!=null) {
					formTag.setFocus(createFocus);
				}
				break;
		}
	}

public String getAction() {
	return action;
}
	public int getDisplayMode() { return mode; }
public String getEnctype() {
	return enctype;
}
public String getFocus() {
	return focus;
}
public String getMethod() {
	return method;
}
public String getOnreset() {
	return onreset;
}
public String getOnsubmit() {
	return onsubmit;
}
public String getScope() {
	return scope;
}
public String getReqCode() {
	return reqCode;
}
public String getStyle() {
	return style;
}
public String getTarget() {
	return target;
}
public String getType() {
	return type;
}
public void release() {
	super.release();
	action = null;
	method = null;
	scope = null;
	//name = Constants.BEAN_KEY;
	mode = FormUtilsInterface.EDIT_MODE;
	reqCode = null;
	
	focus = null;
	editFocus = null;
	createFocus = null;
	inspectFocus = null;
	
	formTag.release();
}
/**
 * Set the action for the form tag. <br>
 * If the init-parameter 'struts-layout-mode' is equal to 'noerror' the action is set to '/default'.
 */
public void setAction(String action) {
	this.action = action;
	if (LayoutUtils.getNoErrorMode()) this.action = "/default";

}
public void setEnctype(String enctype) {
	this.enctype = enctype;
}
public void setFocus(String focus) {
	this.focus = focus;
}
public void setMethod(String method) {
	this.method = method;
}
public void setOnreset(String onreset) {
	this.onreset = onreset;
}
public void setOnsubmit(String onsubmit) {
	this.onsubmit = onsubmit;
}
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
public void setScope(String scope) {
	this.scope = scope;
}
public void setStyle(String style) {
	this.style = style;
}
public void setTarget(String target) {
	this.target = target;
}
public void setType(String type) {
	this.type = type;
}
	/**
	 * Returns the createFocus.
	 * @return String
	 */
	public String getCreateFocus() {
		return createFocus;
	}

	/**
	 * Returns the editFocus.
	 * @return String
	 */
	public String getEditFocus() {
		return editFocus;
	}

	/**
	 * Returns the inspectFocus.
	 * @return String
	 */
	public String getInspectFocus() {
		return inspectFocus;
	}

	/**
	 * Sets the createFocus.
	 * @param createFocus The createFocus to set
	 */
	public void setCreateFocus(String createFocus) {
		this.createFocus = createFocus;
	}

	/**
	 * Sets the editFocus.
	 * @param editFocus The editFocus to set
	 */
	public void setEditFocus(String editFocus) {
		this.editFocus = editFocus;
	}

	/**
	 * Sets the inspectFocus.
	 * @param inspectFocus The inspectFocus to set
	 */
	public void setInspectFocus(String inspectFocus) {
		this.inspectFocus = inspectFocus;
	}

}
