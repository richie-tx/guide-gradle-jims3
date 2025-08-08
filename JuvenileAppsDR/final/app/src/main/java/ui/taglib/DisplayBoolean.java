/*
 * Created on August 17, 2006
 */
package ui.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author dapte
 */

public class DisplayBoolean extends BodyTagSupport {

	private String name;
	private String property;
	private String trueValue;
	private String falseValue;
	
	/**
	 @return int EVAL_BODY_INCLUDE
	 */
	public int doStartTag() throws JspException {

		HttpServletRequest request =
			(HttpServletRequest) pageContext.getRequest();

		try {
			Object paramObj = TagUtil.getReadObject(super.pageContext, name, property);
			if(paramObj != null) {
				Boolean actual = (Boolean)paramObj;
				if(actual.booleanValue() == true) {
					pageContext.getOut().write(trueValue);
				} else {
					pageContext.getOut().write(falseValue);
				}
			}
		}catch (Exception e) {
			throw new JspException("IO Error: " + e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @return Returns the falseValue.
	 */
	public String getFalseValue() {
		return falseValue;
	}
	/**
	 * @param falseValue The falseValue to set.
	 */
	public void setFalseValue(String falseValue) {
		this.falseValue = falseValue;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the property.
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property The property to set.
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return Returns the trueValue.
	 */
	public String getTrueValue() {
		return trueValue;
	}
	/**
	 * @param trueValue The trueValue to set.
	 */
	public void setTrueValue(String trueValue) {
		this.trueValue = trueValue;
	}
}