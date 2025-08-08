package mojo.ui.web.jsp;

import java.lang.reflect.Method;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
* This MSP extension tag allows the user to retrieve data from the events made
* available to this tag.  This is a simple substitution tag and will most likely
* be the most common tag in your MSP pages.  The required attribute,
* <pre>object</pre>, specifies the path to the object to be displayed.  If
* at any point in the traversal of the event structure an error occurs or a
* value is null, the result will be a non-value.
*
* @author Saleem Shafi
* @date 6/4/2002
* @modelguid {748716F5-B04F-46ED-8992-5A13B69EB49C}
*/
public class Get extends TagSupport {
	/**
     * Represents the <pre>object</pre> attribute on the <pre><msp:get></pre>
     * tag.
     * @modelguid {CA5B2EF2-0EED-4E24-92F4-2D92543BC88D}
     */
    private String object;

	/**
     * Represents the <pre>format</pre> attribute on the <pre><msp:get></pre>
     * tag.
     * @modelguid {312FADB6-BDA9-4B3D-991A-D5B69BF0B24E}
     */
    private String format;

    /**
     * Executes when the <pre><msp:get></pre> tag is first encountered.  The
     * only thing that happens before this method is executed is the bean
     * properties of the attributes of the tag are initialized.  This
     * implementation finds object referenced by <pre>object</pre> and writes
     * out the value.
     * @return int
     * @exception javax.servlet.jsp.JspException
     * @modelguid {86310121-5F8D-4C24-8360-FCEBD280F706}
     */
    public int doStartTag() throws JspException {
        try {
            Object result = MethodFactory.invokeGetter(pageContext, getObject());
            if (result != null) {
                if (getFormat() != null) {
                    try { 
                        result = new java.text.DecimalFormat(getFormat()).format(result);
                    } catch (IllegalArgumentException e) {
                        System.out.println("'format' attribute used on a non-numberic field '"+getObject()+"'.");
                    }
                }
                pageContext.getOut().print(result.toString());
            }
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
     * @modelguid {0317214A-502E-41D0-A5CD-C152EE6C9725}
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	/** @modelguid {A8DD7FC6-AB6B-4D28-B944-6D180B7049F4} */
    public String getObject() {
        return object;
    }

	/** @modelguid {AC5C67C4-05E7-453B-AA5A-F42AC329963E} */
    public void setObject(String newObject) {
        object = newObject;
    }

	/** @modelguid {5B860F13-627C-4D6E-A171-80432E87AA17} */
    public String getFormat() {
        return format;
    }

	/** @modelguid {6F60080A-A1D4-4773-B20B-E48FDA74A6D0} */
    public void setFormat(String newFormat) {
        format = newFormat;
    }

}
