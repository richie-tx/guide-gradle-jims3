package mojo.struts.taglib;

import java.util.*;
import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

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
public class Widget extends BodyTagSupport {


    /**
     * Represents the <pre>event</pre> attribute on the <pre><msp:widget>
     * </pre> tag.  It's value should be the fully-qualified Java class name of
     * the event that the tag is "listening" for.
     */
    private String bean;

	/**
     * Represents the <pre>name</pre> attribute on the <pre><msp:widget>
     * </pre> tag.  If not specified, it's value will be "root".
     */
    private String name = "root";

    // This attribute holds the beams that are available to this tag.
    private Object availableBeans;

    // This attribute maintains a reference to the Class instance of the class
    // specified in the <i>event</i> property.
    private Class beanClass;

    /**
     * Executes when the <pre><msp:widget></pre> tag is first encountered.
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
    public int doStartTag() throws JspException {
        try {
            // get the responses
            Object lBean = pageContext.getRequest().getAttribute(Constants.BEAN_ATT_NAME);
            if (lBean == null) return SKIP_BODY;
            String lBeanStr = getBean();
            beanClass = Class.forName(lBeanStr);
            setAvailableBeans(lBean);
            // find instance of the event class
            if (nextValue()){
                return EVAL_BODY_AGAIN;
            } else {
                removeValue();
                return SKIP_BODY;
            }
        } catch (Throwable e) {
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
    public int doAfterBody() throws javax.servlet.jsp.JspTagException {
        BodyContent lBody = getBodyContent();
        // write out the previous body's content
        try {
            lBody.writeOut(getPreviousOut());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
		// clean the slate
        lBody.clearBody();
        // look for another instance
        if (nextValue()) {
            return BodyTagSupport.EVAL_BODY_AGAIN;
        } else {
            removeValue();
            return SKIP_BODY;
        }
    }

	/**
     * Executes after the body of the tag has finished being processed.  It is
     * only executed once for every instance of the tag in the MSP file.  This
     * implemenation removes the page-level attribute that held the event
     * instance.
     * @return int
     * @exception javax.servlet.jsp.JspException
     */
    public int doEndTag() throws JspException {
        pageContext.removeAttribute(getName());
        return EVAL_PAGE;
    }

	/**
     * Stores the given event as the list of replies available to this tag.  The
     * event can either be a single IEvent object or a
     * mojo.km.messaging.Composite.CompositeResponse object holding a list of
     * all of the responses.
     * @param IEvent the event(s) holding the replies available to this tag
     * @return void
     */
	private void setAvailableBeans(Object aBean){
        availableBeans = aBean;
        if (aBean instanceof List) {
            availableBeans = ((List) aBean).iterator();
        }
    }

	/**
     * This method searches for another instance of the event type in the list
     * of available events and returns true or false depending on whether or not
     * an instance was found.  If an instance was found, it stores that event in
     * the <pre>pageContext</pre> so that it is available when the body is
     * processed.
     * @return <pre>true</pre> if an instance of the event class was found <pre>false</pre> otherwise.
     */
    private boolean nextValue() {
        if (availableBeans == null) return false;
        // if there are multiple replies
        if (availableBeans instanceof Iterator) {
            Object lObject = null;
            // search the rest of the replies
            while (((Iterator)availableBeans).hasNext()){
                lObject = ((Iterator)availableBeans).next();
                // if we found one, set the value and exit
                if (beanClass.isInstance(lObject)){
                    pageContext.setAttribute(getName(), lObject, PageContext.REQUEST_SCOPE);
                    return true;
                }
            }
            // otherwise, clear the list of replies
            removeValue();
        } else {
            // if there's only one reply, just check that one
			if (beanClass.isInstance(availableBeans)){
				pageContext.setAttribute(getName(), availableBeans);
                removeValue();
				return true;
			}
		}
        return false;
    }

	/**
     * Clears this tag's reference to the replies sent to this page so that no
     * more processing can occur.
     * @return void
     */
    private void removeValue() {
        availableBeans = null;
    }

    public String getName() {
        return name;
    }

    public String getBean() {
        return bean;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setBean(String abean) {
        bean = abean;
    }
}