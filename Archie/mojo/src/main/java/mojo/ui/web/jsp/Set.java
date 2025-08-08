package mojo.ui.web.jsp;

import java.lang.reflect.Method;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
* This MSP extension tag allows the user to set a constant value to a variable
* accessible by other MSP tags.  Since the <pre>value</pre> attribute is
* considered to be constant, the only use for this tag is when the rendering
* responsibility is divided over multiple pages.  That way, one page can set
* a value and another, re-usable page can read that value and adjust it's
* processing accordingly.
*
* For example, if you wanted to put all of your address rendering logic in one
* external MSP file and wanted to reference that file in both your read-only and
* edit pages, you could use the <pre><msp:set></pre> tag to tell the external
* MSP file whether or not to make the value editable.
*
* View Page:
* <pre>
* <msp:set variable="editable" value="false"/>
* <%@ include file="/jsp/address.widget" %>
* </pre>
*
* Edit Page:
* <pre>
* <msp:set variable="editable" value="true"/>
* <%@ include file="/jsp/address.widget" %>
* </pre>
*
* address.widget
* <pre>
* <msp:if object="editable" operator="equals" value="true">
* Street: <input type="text" name="street" value="<msp:get
* object="user.address.street">" /><br>
* City: <input type="text" name="city" value="<msp:get
* object="user.address.city">"/><br>
* State: <input type="text" name="state" value="<msp:get
* object="user.address.state">" /> &nbsp; &nbsp; Zip pre: <input type="text"
* name="zippre" value="<msp:get object="user.address.zippre">" /><br>
* </msp:if>
*
* <msp:if object="editable" operator="notequals" value="true">
* Street: <msp:get object="user.address.street" /><br>
* City: <msp:get object="user.address.city"/><br>
* State: <msp:get object="user.address.state" /> &nbsp; &nbsp; Zip pre:
* <msp:get object="user.address.zippre" /><br>
* </msp:if>
* </pre>
*
* Obviously, this tag does not help in reducing the amount of pre, but it can
* help keep related pre blocks together by allowing pages to parameterize
* included MSP pages.
* 
* @author Saleem Shafi
* @date 6/4/2002
* @modelguid {FC9A8505-4C6C-4FFB-96C7-4D156C533C9B}
*/
public class Set extends TagSupport {
	/**
     * Represents the <pre>variable</pre> attribute on the <pre><msp:set>
     * </pre> tag.
     * @modelguid {D939EFAE-1E37-4151-AEA9-CE298EADA2C0}
     */
    private String variable;
	/**
     * Represents the <pre>value</pre> attribute on the <pre><msp:set></pre>
     * tag.  Note:  This is assumed to be a constant value, not a path to an
     * event value.  If you are trying to provide a new name to an event value,
     * use the <msp:use> tag instead.
     * @modelguid {199AF76C-6D3B-446D-86CB-FE46C400E392}
     */
    private String value;

    /**
     * Executes when the <pre><msp:set></pre> tag is first encountered. The
     * only thing that happens before this method is executed is the bean
     * properties of the attributes of the tag are initialized.  This
     * implementation stores the given value on <i>pageContext</i> with the
     * <i>variable</i> as the key.
     * @return SKIP_BODY
     * @exception javax.servlet.jsp.JspException
     * @modelguid {5414DC15-FA7E-42D1-91DF-7C6019562250}
     */
    public int doStartTag() throws JspException {
        try {
            pageContext.setAttribute(getVariable(), getValue());
        } catch (Throwable e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

	/**
     * Executes after the body of the tag has finished being processed.  It is
     * only executed once for every instance of the tag in the MSP file.  This
     * implemenation simply ends this tags and continues evaluating the page.
     * @return EVAL_PAGE
     * @exception javax.servlet.jsp.JspException
     * @modelguid {93AE41FC-0504-40C5-B2EF-9F6DCA40DFE7}
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	/** @modelguid {18F7BD4F-7AAA-4291-A03E-89F0B2909BB5} */
    public String getVariable() {
        return variable;
    }

	/** @modelguid {0A0ABBA3-8912-4110-A871-2F1DE7E3C931} */
    public void setVariable(String newVariable) {
        variable = newVariable;
    }

	/** @modelguid {C7E0097C-8574-4B06-BE40-DC0FA26DF397} */
    public String getValue() { return value; }

	/** @modelguid {6C8BBB20-02D6-4554-A788-9A18F078DBEC} */
    public void setValue(String value) { this.value = value; }
}
