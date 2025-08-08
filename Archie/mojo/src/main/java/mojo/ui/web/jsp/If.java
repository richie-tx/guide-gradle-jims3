package mojo.ui.web.jsp;

import java.util.*;
import java.io.IOException;
import java.lang.reflect.*;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
* This MSP extension tag allows the user to implement conditional logic within
* an MSP.  The body of the tag is only execute if the conditions specified in
* the attributes of the tag are met.  There are three required attributes:
* <pre>object</pre>, <code>value</code> and <pre>operator</pre>.
*
* The <pre>object</pre> and <pre>value</pre> attributes specify paths to
* objects in the available events.  If the <pre>value</pre> object cannot be
* found, the attribute is considered a constant value.  The <pre>operator
* </pre> value is one of the following:
* <ul>
* <li>equals</li> - evaluates to true iff the <code>object</pre> and
* <pre>value</pre> are identical.
* <li>notequals</li> - evaluates to true iff the <pre>object</pre> and
* <pre>value</pre> are not identical.
* <li>isempty</li> - evaluates to true iff the <pre>object</pre> is a list and
* has elements in it.
* <li>isnotempty</li> - evaluates to true iff the <pre>object</pre> is a list
* and does not has elements in it.
* <li>isodd</li> - evalutes to true iff the <pre>object</pre> is an odd number.
* <li>iseven</li> - evalutes to true iff the <pre>object</pre> is an even number.
* </ul>
* 
* @author Saleem Shafi
* @date 6/4/2002
* @modelguid {F4F355C5-4A5F-443C-B1BF-398287BF2E01}
*/
public class If extends BodyTagSupport {
	/**
     * Represents the <pre>object</pre> attribute on the <pre><msp:if></pre>
     * tag.
     * @modelguid {EFDBB511-14FB-4E4B-A3E8-2EAD5804BC6C}
     */
    private String object;
	/**
     * Represents the <pre>value</pre> attribute on the <pre><msp:if></pre>
     * tag.
     * @modelguid {5079A32A-1144-495B-98DC-3AA502C6B767}
     */
    private String value;
	/**
     * Represents the <pre>operator</pre> attribute on the <pre><msp:if>
     * </pre> tag.
     * @modelguid {BB135F0B-5123-4266-BCE8-914862805A8D}
     */
    private String operator;

    /**
     * Executes when the <pre><msp:if></pre> tag is first encountered.  The
     * only thing that happens before this method is executed is the bean
     * properties of the attributes of the tag are initialized.  This
     * implementation finds objects referenced by <pre>object</pre> and
     * <pre>value</pre> and deteremines whether or not they satisfy the given
     * <pre>operator</pre>.  If they do, EVAL_BODY_TAG is returned and the
     * body is processed.  Otherwise, SKIP_BODY is returned and the tag is
     * ended.
     * @return int
     * @exception javax.servlet.jsp.JspException
     * @modelguid {E7D9044B-438E-4406-B30A-F24CBA5E3B0B}
     */
    public int doStartTag() throws JspException {
        boolean lSuccess = false;
        try {
            // get object and value values
	        String lOperator = getOperator();
            Object result = MethodFactory.invokeGetter(pageContext, getObject());
            Object lValue = MethodFactory.invokeGetter(pageContext, getValue());
            if (lValue == null) {
                lValue = getValue();
            } else {
	            lValue = lValue.toString();
            }

            // check if equal
            if (lOperator.equals("equals")) {
                lSuccess = lValue.equals(result + "");
            // check if not equal
            } else if (lOperator.equals("notequals")) {
                lSuccess = !lValue.equals(result + "");
            // check is empty
            } else if (lOperator.equalsIgnoreCase("isodd")) {
                int lIntResult = Integer.parseInt(result.toString());
                boolean lBoolValue = new Boolean((String)lValue).booleanValue();
                lSuccess = (lIntResult % 2 == 1) == lBoolValue;
            } else if (lOperator.equalsIgnoreCase("iseven")) {
                int lIntResult = Integer.parseInt(result.toString());
                boolean lBoolValue = new Boolean((String)lValue).booleanValue();
                lSuccess = (lIntResult % 2 == 0) == lBoolValue;
            } else if (lOperator.equals("isempty")) {
                if (result instanceof Collection) {
                    lSuccess = !((Collection)result).iterator().hasNext();
                } else if (result instanceof Iterator) {
                    lSuccess = !((Iterator)result).hasNext();
                } else
                    lSuccess = false;
            // check if is not empty
            } else if (lOperator.equals("isnotempty")) {
                if (result instanceof Collection) {
                    lSuccess = ((Collection)result).iterator().hasNext();
                } else if (result instanceof Iterator) {
                    lSuccess = ((Iterator)result).hasNext();
                } else
                    lSuccess = false;
            }
        } catch (Throwable e) {
            throw new JspException(e.getMessage());
        }
        if (lSuccess) {
            return EVAL_BODY_TAG;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     * Executes after the body of the tag has been processed.  This
     * implementation will simply write out the contents of the body and end
     * the tag.
     * @return SKIP_BODY
     * @exception javax.servlet.jsp.JspTagException
     * @modelguid {9DCE2443-A49B-449C-AE0F-847B55EA6A09}
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
     * implemenation simply ends this tags and continues evaluating the page.
     * @return EVAL_PAGE
     * @exception javax.servlet.jsp.JspException
     * @modelguid {2E0AA945-964F-443D-AD1B-08AF78582F92}
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	/** @modelguid {D1DBA4B7-C1E9-42AC-817B-9AF82486EC27} */
    public String getObject() {
        return object;
    }

	/** @modelguid {67CF7ECC-758C-4D8E-AC3B-83C40595E8F4} */
    public String getOperator() {
        return operator;
    }

	/** @modelguid {20F52CBC-C823-410E-A987-3BCCF659F15D} */
    public String getValue() {
        return value;
    }

	/** @modelguid {02F21FFB-CCD1-4656-AEF8-DC1DA193399B} */
    public void setObject(String newObject) {
        object = newObject;
    }

	/** @modelguid {17D2F00E-5FCD-4617-9711-120519C1358B} */
    public void setOperator(String newOperator) {
        operator = newOperator;
    }

	/** @modelguid {870C2E0F-198B-464F-9117-633562DF67EB} */
    public void setValue(String newValue) {
        value = newValue;
    }
}
