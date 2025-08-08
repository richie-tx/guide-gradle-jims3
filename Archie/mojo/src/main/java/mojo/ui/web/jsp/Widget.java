package mojo.ui.web.jsp;

import java.util.*;
import java.io.IOException;
import java.lang.reflect.*;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import mojo.km.messaging.Composite.CompositeResponse;

/**
 * This MSP extension tag allows a block of HTML, MSP or JSP code to "listen" for a particular event in the response.  The code
 * in the body of this tag is executed once for every instance of the desired event in the response.  On each iteration, the code
 * in the body has access to the respective instance of the event.
 *
 * For example, if you wanted to display an error message on the screen for every error that was encountered while trying to create
 * a user object, the code in your MSP file might look like:
 * <pre>
 * <msp:widget event="messaging.userevents.CreateUserErrorEvent" >
 *   <p class="error"><msp:get object="root.errorMessage" /></p>
 * </msp:widget>
 * </pre>
 * 
 * In this example, the paragraph that displays the error message will be executed once for every instance of messaging.userevents.CreateUserErrorEvent
 * in the replies generated from the request.  Each instance will be used as the "root" event on its respective iteration.  The optional
 * <pre>alias</pre> property allows you to use an alternate reference to "root".  The use of the <pre>alias</pre> attribute becomes
 * required if the <pre><msp:widget></pre> tag is nested within another <pre><msp:widget></pre> tag.  If that were the case, the above
 * example might look more like:
 * <pre>
 * <msp:widget event="messaging.userevents.CreateUserErrorEvent" alias="error">
 *   <p class="error"><msp:get object="error.errorMessage" /></p>
 * </msp:widget>
 * </pre>
 * 
 * @author Saleem Shafi
 * @modelguid {4912B49A-CACD-4A76-85A6-E06613DA02D5}
 */
public class Widget extends BodyTagSupport {
    /**
     * Represents the <pre>event</pre> attribute on the <pre><msp:widget>
     * </pre> tag.  It's value should be the fully-qualified Java class name of
     * the event that the tag is "listening" for.
     * @modelguid {73754186-74AD-492D-837F-4F0549E33C57}
     */
    private String event;

	/**
     * Represents the <pre>alias</pre> attribute on the <pre><msp:widget>
     * </pre> tag.  If not specified, it's value will be "root".
     * @modelguid {77C1EA58-C11D-42D6-A1A7-437AA1F98371}
     */
    private String alias = "root";

    // This attribute holds the replies that are available to this tag.
	/** @modelguid {C3F9F351-1F11-4C0A-A116-9B670F69CF10} */
    private Object replies;
    // This attribute maintains a reference to the Class instance of the class
    // specified in the <i>event</i> property.
	/** @modelguid {FA9DF434-F01C-4233-8085-FF6AA33138E1} */
    private Class eventClass;

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
     * @modelguid {E25929DA-B124-4420-A1DD-A66926626561}
     */
    public int doStartTag() throws JspException {
        try {
            // get the responses
            IEvent lEvent = (IEvent)pageContext.getRequest().getAttribute("Event");
            if (lEvent == null) return SKIP_BODY;
            eventClass = Class.forName(getEvent());
            storeValue(lEvent);
            // find instance of the event class
            if (nextValue()){
                return EVAL_BODY_TAG;
            } else {
                removeValue();
                return SKIP_BODY;
            }
        } catch (Throwable e) {
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
     * @modelguid {71617801-8CB9-4499-AFC7-40A20CA1CE15}
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
            return EVAL_BODY_TAG;
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
     * @modelguid {9D0301E7-FA82-45C4-9279-0ADF5A89EF40}
     */
    public int doEndTag() throws JspException {
        pageContext.removeAttribute(getAlias());
        return EVAL_PAGE;
    }

	/**
     * Stores the given event as the list of replies available to this tag.  The
     * event can either be a single IEvent object or a
     * mojo.km.messaging.Composite.CompositeResponse object holding a list of
     * all of the responses.
     * @param IEvent the event(s) holding the replies available to this tag
     * @return void
     * @modelguid {AAF43B97-A1AF-4E63-98EC-E9756D88E62D}
     */
	private void storeValue(IEvent anEvent){
        replies = anEvent;
        // if the parameter is a CompositeResponse, store the list of responses.
        if (replies instanceof CompositeResponse) {
			replies = ((CompositeResponse)replies).getResponses();
        }
    }

	/**
     * This method searches for another instance of the event type in the list
     * of available events and returns true or false depending on whether or not
     * an instance was found.  If an instance was found, it stores that event in
     * the <pre>pageContext</pre> so that it is available when the body is
     * processed.
     * @return <pre>true</pre> if an instance of the event class was found <pre>false</pre> otherwise.
     * @modelguid {54BD0908-A103-49AE-A7FC-753E89B49F25}
     */
    private boolean nextValue() {
        if (replies == null) return false;
        // if there are multiple replies
        if (replies instanceof Enumeration) {
            Object lObject = null;
            // search the rest of the replies
            while (((Enumeration)replies).hasMoreElements()){
                lObject = ((Enumeration)replies).nextElement();
                // if we found one, set the value and exit
                if (eventClass.isInstance(lObject)){
                    pageContext.setAttribute(getAlias(), lObject);
                    return true;
                }
            }
            // otherwise, clear the list of replies
            removeValue();
        } else {
            // if there's only one reply, just check that one
			if (eventClass.isInstance(replies)){
				pageContext.setAttribute(getAlias(), replies);
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
     * @modelguid {996415EB-494A-4B51-8B9E-D168A2DDEF01}
     */
    private void removeValue() {
        replies = null;
    }

	/** @modelguid {E0A6C829-3EC8-4C43-8DEE-F07DBF3FC6C3} */
    public String getAlias() {
        return alias;
    }

	/** @modelguid {3A8060CE-063D-42E0-8578-B7BCCB1B20BE} */
    public String getEvent() {
        return event;
    }

	/** @modelguid {E3799198-E825-458E-883F-2308F1639D80} */
    public void setAlias(String newAlias) {
        alias = newAlias;
    }

	/** @modelguid {B6667E71-97C6-4E9A-B2BD-160B6078CABC} */
    public void setEvent(String anEvent) {
        event = anEvent;
    }
}