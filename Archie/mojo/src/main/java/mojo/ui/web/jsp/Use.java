package mojo.ui.web.jsp;

import java.util.*;
import java.io.IOException;
import java.lang.reflect.*;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
* This MSP extension tag allows the user to create a new reference to an already
* accessible event on this page.  The <pre><msp:use></pre> tag has two
* required attributes: <pre>object</pre> and <pre>alias</pre>.  The <pre>
* object</pre> attribute specifies the path to the desired event, while the
* <pre>alias</pre> attribute provides the new name that the event can be
* referenced by.
*
* This tag is particular useful when the event desired is deep in the event
* structure, or when an event appears several times in the application and is
* rendered the same way each time, but shows up inside of different event
* structures.
*
* Example:
* <pre>
* <msp:use object="user.address" alias="address">
* Street: <msp:get object="address.street" /><br>
* City: <msp:get object="address.city"/><br>
* State: <msp:get object="address.state" /> &nbsp; &nbsp; Zip pre: <msp:get
* object="address.zippre" /><br>
* </msp:use>
* </pre>
*
* Without the <pre><msp:use></pre> tag, the above example might look more
* like:
* <pre>
* Street: <msp:get object="user.address.street" /><br>
* City: <msp:get object="user.address.city"/><br>
* State: <msp:get object="user.address.state" /> &nbsp; &nbsp; Zip pre:
* <msp:get object="user.address.zippre" /><br>
* </pre>
*
* If the block of pre were much longer the extra depth of the "address" event
* could become quite tedious, and the deeper the event, the worse it gets.
* 
* Also, if we imagine that we have an AddressEvent that exists inside of both
* the CreateUserEvent and the EditUserEvent and we want to render this event the
* same way on both the Create User page and the Edit User page, we might setup
* an <pre><msp:use></pre> tag to alias the AddressEvent in each page and then
* defer to rendering pre to an included MSP file.
*
* This might look like:
* <pre>
* <msp:use object="createUser.address" as="address">
*    <%@ include file="/jsp/addressUpdate.widget" %>
* </msp:use>
* </pre>
* and
* <pre>
* <msp:use object="editUser.address" as="address">
*    <%@ include file="/jsp/addressUpdate.widget" %>
* </msp:use>
* </pre>
* while "/jsp/addressUpdate.widget" would look like:
* <pre>
* Street: <input type="text" name="street" value="<msp:get
* object="address.street">" /><br>
* City: <input type="text" name="city" value="<msp:get object="address.city">"/>
* <br>
* State: <input type="text" name="state" value="<msp:get
* object="address.state">" /> &nbsp; &nbsp; Zip pre: <input type="text"
* name="zippre" value="<msp:get object="address.zippre">" /><br>
* </pre>
*
* @author Saleem Shafi
* @date 6/4/2002
* @modelguid {BFDEF789-0051-450B-B3A2-9058B3D76740}
*/
public class Use extends BodyTagSupport {
	/**
     * Represents the <pre>object</pre> attribute on the <pre><msp:use>
     * </pre> tag.
     * @modelguid {7D0FB46A-901C-4896-BAAA-CFA7F21E2FD6}
     */
    private String object;
	/**
     * Represents the <pre>alias</pre> attribute on the <pre><msp:use></pre>
     * tag.
     * @modelguid {35221DC0-7548-409C-899F-DA4BDD0E193F}
     */
    private String alias;

    /**
     * Executes when the <pre><msp:use></pre> tag is first encountered. The
     * only thing that happens before this method is executed is the bean
     * properties of the attributes of the tag are initialized.  This
     * implementation retrieves the list of replies that were sent to this page,
     * tries to locate the one described in the <i>object</i> attribute, stores
     * it in the <i>pageContext</i> with the <i>alias</i> as the key and then
     * processes the body of the tag.
     * @return EVAL_BODY_TAG
     * @exception javax.servlet.jsp.JspException
     * @modelguid {C5BB7C71-ED7A-4B5C-948F-1F660C02E8A9}
     */
    public int doStartTag() throws JspException {
        try {
            Object lResult = MethodFactory.invokeGetter(pageContext, getObject());
            if (lResult != null)
	            pageContext.setAttribute(getAlias(), lResult);
            return EVAL_BODY_TAG;
        } catch (Throwable e) {
            throw new JspException(e.getMessage());
        }
    }

    /**
     * Executes after the body of the tag has been processed.  This
     * implementation will simply write out the contents of the body and end
     * the tag.
     * @return SKIP_BODY
     * @exception javax.servlet.jsp.JspTagException
     * @modelguid {9938AE34-BD95-4B82-BF6D-A390D5A8667F}
     */
    public int doAfterBody() throws javax.servlet.jsp.JspTagException {
        BodyContent lBody = getBodyContent();
        try {
            lBody.writeOut(getPreviousOut());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

	/**
     * Executes after the body of the tag has finished being processed.  It is
     * only executed once for every instance of the tag in the MSP file.  This
     * implemenation removes the page-level attribute that held the event
     * reference.
     * @return int
     * @exception javax.servlet.jsp.JspException
     * @modelguid {B2B9254A-FF45-46C9-8783-A60E966BDC3D}
     */
    public int doEndTag() throws JspException {
        pageContext.removeAttribute(getAlias());
        return EVAL_PAGE;
    }

	/** @modelguid {C2B62F47-5693-4595-AE06-DB2122C693B5} */
    public String getAlias() {
        return alias;
    }

	/** @modelguid {A375DE31-7AD2-4EF6-A620-405ED0F11174} */
    public String getObject() {
        return object;
    }

	/** @modelguid {89911958-C33E-476B-B34B-9EAE99527B2C} */
    public void setAlias(String newAlias) {
        alias = newAlias;
    }

	/** @modelguid {948ABAAD-6F2C-458E-B0D6-8CF4DDF7060D} */
    public void setObject(String newObject) {
        object = newObject;
    }
}
