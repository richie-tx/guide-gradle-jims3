package mojo.ui.web.jsp;

import java.lang.reflect.Method;
import mojo.km.messaging.IEvent;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * Insert the type's description here. Creation date: (11/8/00 11:46:16 AM)
 * @author:
 * @modelguid {BC019807-C820-4F94-A44C-28859A636A5E}
 */
public class NoCache extends TagSupport {
	/** @modelguid {355A6AA9-DCB8-453B-8098-5169F25FE38C} */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	/** @modelguid {D2C95E23-5C21-49C6-9608-C8D262601B03} */
    public int doStartTag() throws JspException {
        ((HttpServletResponse)pageContext.getResponse()).setHeader("Expires", "Tues, 01 Jan 1980 00:00:00 GMT");
        return SKIP_BODY;
    }
}
