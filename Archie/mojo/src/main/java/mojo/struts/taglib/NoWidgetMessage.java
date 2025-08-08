package mojo.struts.taglib;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import mojo.messaging.NoSearchResultsEvent;
import mojo.struts.Constants;

/**
 * WIDGET is the tag that associates a piece of HTML code with a particular event.  
 * A single request can spawn several replies and each chunk of UI code is associated with one 
 * of these replies through the WIDGET tag.  
 * 
 * This MSP extension tag allows a block of HTML, MSP or JSP code to "listen" for a particular event in the request attribute.  The code
 * in the body of this tag is executed once for every instance of the desired event in the response.  On each iteration, the code
 * in the body has access to the respective instance of the event.
 *
 * 
 * Common UI Pattern: This tag is used whenever multiple events of a fixed type are returned 
 * as replies and need to be displayed.
 *
 * @author Saleem Shafi
 */
public class NoWidgetMessage extends BodyTagSupport
{

	/**
	 * Executes when the <pre><msp:nodatamessage></pre> tag is first encountered.
	 * The only thing that happens before this method is executed is the bean
	 * properties of the attributes of the tag are initialized.  This
	 * implementation retrieves the list of replies that were sent to this page
	 * and searches for the first event that is an instance of the given
	 * classname.  If an instance is found, EVAL_BODY_TAG is returned and the
	 * body is processed with respect to that event.  Otherwise, SKIP_BODY is
	 * returned and the tag is ended.
	 * @return int
	 * @exception javax.servlet.jsp.JspException
	 */
	public int doStartTag() throws JspException
	{
		try
		{
			// get the responses
			Object lBean =
				pageContext.getRequest().getAttribute(Constants.BEAN_ATT_NAME);
				
			if(lBean instanceof NoSearchResultsEvent) {
				return EVAL_BODY_INCLUDE;
			} else {
				return SKIP_BODY;
			}

		} catch (Throwable e)
		{
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
	}	

	/**
	 * Executes after the body of the tag has been processed.  This
	 * implementation will write out the contents of the body and determine
	 * whether to perform another iteration by seearching for another instance
	 * of the event.  If it finds another instance, this method will return
	 * EVAL_BODY_TAG, which will re-evaluate the body with respect to the next
	 * instance of the event.  Otherwise, it will return SKIP_BODY and end this
	 * tag.
	 * @return int
	 * @exception javax.servlet.jsp.JspTagException
	 */
	public int doAfterBody() throws javax.servlet.jsp.JspTagException
	{
		return SKIP_BODY;
	}

	/**
	 * Executes after the body of the tag has finished being processed.  It is
	 * only executed once for every instance of the tag in the MSP file.  This
	 * implemenation removes the page-level attribute that held the event
	 * instance.
	 * @return int
	 * @exception javax.servlet.jsp.JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}

}