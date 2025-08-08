/*
 * Created on May 4, 2004
 */
package ui.taglib;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class EmptyCollection extends BodyTagSupport {

	private String name ;
	/**
	@param aMapping
	@param aForm
	@param aRequest
	@return int startTag
	 */
	public int doStartTag() throws JspException {

		HttpServletRequest request =
			(HttpServletRequest) pageContext.getRequest();

		Object collectionObj = request.getAttribute(name);
		Collection collection = new java.util.ArrayList();
		
		if (collectionObj != null) {
			collection = (Collection) collectionObj;
		}

		if (collection.size() == 0) {
			return (EVAL_BODY_INCLUDE);
		} else {
			return (SKIP_BODY);

		}
	}

	/**
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String lname) {
		this.name = lname;
	}

}