package mojo.ui.web.jsp;

import java.util.*;
import java.io.IOException;
import java.lang.reflect.*;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
* This MSP extension tag allows the user to iterator over a list of events and
* execute the body of the tag once for each event.  This tag can also handle
* iterating over the body for a specified number of times.  There are two
* required attributes: <pre>list</pre> and <pre>alias</pre>; and an optional
* <pre>start</pre> attribute.
*
* The <pre>list</pre> attribute is the path to the object representing the
* list.  Valid types for this object include Collection objects, Iterator
* objects and Integer objects.  If the object is not a Collection or an
* Iterator, an attempt is made to convert it to an Integer type.  If successful,
* the body will be executed the number of times specified in the integer value.
*
* The <pre>alias</pre> attribute provides a temporary name to use to reference
* the current element in the list.  The system provides another reference,
* <pre>alias</pre>+"#", which returns the index of the object in the list.
* The optional <pre>start</pre> attribute acts as an offset for that system
* value.
* 
* Examples:
* <pre>
* <msp:loop list="tree.nodes" alias="node">
*   <msp:get object="node.nodeID" /><br>
* </msp:loop>
* </pre>
* 
* <pre>
* <msp:loop list="tree.nodes" alias="node">
*   Node #<msp:get object="node#"/>: <msp:get object="node.nodeID" /><br>
* </msp:loop>
* </pre>
* 
* <pre>
* <msp:loop list="tree.nodes" alias="node" start="1">
*   Node #<msp:get object="node#"/>: <msp:get object="node.nodeID" /><br>
* </msp:loop>
* </pre>
* 
* <pre>
* <msp:loop list="tree.numberOfNodes" alias="nodeNum">
*   This is Node #<msp:get object="nodeNum"/>.
* </msp:loop>
* </pre>
* 
* @author Saleem Shafi
* @date 6/4/2002
* @modelguid {16B986AB-43C3-4C3B-91F7-EBDFFC417862}
*/
public class Loop extends BodyTagSupport {
	/**
     * Represents the <pre>list</pre> attribute on the <pre><msp:loop></pre>
     * tag.
     * @modelguid {499AAC37-3BED-4327-B24F-7C47291513FB}
     */
    private String list;
	/**
     * Represents the <pre>start</pre> attribute on the <pre><msp:loop>
     * </pre> tag.
     * @modelguid {4714F1B3-6CDF-40E3-A894-88176FE3C15B}
     */
    private int start;
	/**
     * Represents the <pre>alias</pre> attribute on the <pre><msp:loop>
     * </pre> tag.
     * @modelguid {929B4630-C215-4FE6-8577-11E8BBECD76E}
     */
    private String alias;
	// Stores the current position of the list
	/** @modelguid {BEB106FF-5E5B-4DFB-9BD6-49F26B08DD16} */
    private Iterator i = null;
    // Stores the number of times to iterate
	/** @modelguid {ED75F5FC-896A-4A7A-91AA-DED74F159E9F} */
    private int num = 0;
    // Stores the number of times iterated
	/** @modelguid {99DCAF63-E5CD-4687-ADF2-0D8B3D721186} */
    private int index = 0;

    /**
     * Executes when the <pre><msp:loop></pre> tag is first encountered.  The
     * only thing that happens before this method is executed is the bean
     * properties of the attributes of the tag are initialized.  This
     * implementation finds the list identified in the <pre>list</pre>
     * attribute and begins iterating over the list.  If the list is not empty,
     * EVAL_BODY_TAG is returned and the body is processed once for each element
     * in the list.  Otherwise, SKIP_BODY is returned and the tag is ended.
     * @return int
     * @exception javax.servlet.jsp.JspException
     * @modelguid {594F56C8-8D7F-433B-811E-D621E18A37EE}
     */
    public int doStartTag() throws JspException {
        try {
			// get the list
            Object result = MethodFactory.invokeGetter(pageContext, getList());
            if (result == null) return SKIP_BODY;
            storeValue(result);
            // try to iterator over the list
            if (nextValue()) {
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
     * whether to perform another iteration.  If the list has more elements,
     * EVAL_BODY_TAG is returned and the body will be processed again with
     * respect to the next event.  Otherwise, it will return SKIP_BODY and end
     * this tag.
     * @return int
     * @exception javax.servlet.jsp.JspTagException
     * @modelguid {082705E5-19FF-4E74-8EB1-D3482DA0E9B4}
     */
    public int doAfterBody() throws javax.servlet.jsp.JspTagException {
        BodyContent lBody = getBodyContent();
        // write out the previous body's content
        try {
            lBody.writeOut(getPreviousOut());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        lBody.clearBody();
        // look for more elements
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
     * implemenation simply ends this tags and continues evaluating the page.
     * @return EVAL_PAGE
     * @exception javax.servlet.jsp.JspException
     * @modelguid {B77CF419-66BD-419F-A355-A7123DA6C0DF}
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	/**
     * Stores the given Object as the list to be iterated over.  If the object
     * is a Java List or Iterator, the Iterator is stored.  If it is a number,
     * the numeric value is stored.
     * @param Object the object representing the list
     * @return void
     * @modelguid {36D6EC03-79C0-47D6-A2A6-481F5554713D}
     */
    private void storeValue(Object aValue) {
        if (aValue instanceof Iterator) {
            i = (Iterator)aValue;
        } else if (aValue instanceof Collection) {
            i = ((Collection)aValue).iterator();
        } else {
            try {
                num = Integer.parseInt(aValue.toString());
            } catch (Exception e) {
            }
        }
    }

	/**
     * This method determines whether or not there are more elements in the
     * list.  If another element is found, it is store in the <pre>
     * pageContext</pre> and true is returned.  Otherwise, it returns false.
     * @return <pre>true</pre> if another element is found in the list, <pre>false</pre> otherwise.
     * @modelguid {3339CAB6-8E60-4E79-806A-42803F2D37B9}
     */
    private boolean nextValue() {
        // if we're dealing with an Iterator
        if (i != null) {
            // check if the Iterator is empty
            if (i.hasNext()) {
                // if not, store the next value, and the index of the list
                pageContext.setAttribute(getAlias(), i.next());
                pageContext.setAttribute(getAlias() + "#", "" + (start + index++));
                return true;
            } else {
                return false;
            }
        // if we're dealing with a numeric list
        } else {
            // check if we've hit the end
            if (num > index) {
                // if not, store the next number and increment
                index++;
                pageContext.setAttribute(getAlias(), "" + (start + index));
                return true;
            } else {
                return false;
            }
        }
    }

	/**
     * Clears this tag's reference to the list so that no more processing can
     * occur.
     * @return void
     * @modelguid {AF8B0618-1FC5-4B8D-BCE0-47328DC4141D}
     */
    private void removeValue() {
        i = null;
        num = -1;
        index = 0;
    }

	/** @modelguid {17398EFC-2DEB-4D96-B1AF-4723FC2738EE} */
    public String getAlias() {
        return alias;
    }

	/** @modelguid {6894E832-B383-48A8-A32B-855A3CB69329} */
    public String getList() {
        return list;
    }

	/** @modelguid {D4B57EDB-ED29-42F0-97E9-FD04BCA98535} */
    public int getStart() {
        return start;
    }

	/** @modelguid {9129EF8C-DD04-423F-B811-C1F57B1865CC} */
    public void setAlias(String newAlias) {
        alias = newAlias;
    }

	/** @modelguid {FE0363AE-BA41-4AFE-A1F0-FF927BF8C1A3} */
    public void setList(String newList) {
        list = newList;
    }

	/** @modelguid {494F185C-8809-440A-A178-80076C281FFC} */
    public void setStart(int newStart) {
        start = newStart;
    }
}
