package mojo.ui.web.jsp;

import java.lang.reflect.Method;
import mojo.km.messaging.IEvent;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.*;

/**
 * Insert the type's description here. Creation date: (11/8/00 11:46:16 AM)
 * @author:
 * @modelguid {604F70F0-9B9D-496B-96AA-D4D48595C151}
 */
public class WebApp extends TagSupport {
    //private static final String PROPERTY = "WebAppName";
	/** @modelguid {D6C20460-1CA4-4BD3-8113-C1D2C188A5FD} */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

	/** @modelguid {0551B7E0-41BF-4ED8-979D-B179B63FFB5C} */
    public int doStartTag() throws JspException {
        try {
            //String webapp = mojo.km.configuration.SessionProperties.getInstance().getProperty(PROPERTY);
            HttpServletRequest lRequest = (HttpServletRequest)pageContext.getRequest();
            String contextPath =lRequest.getContextPath();
			String webapp = "";
            if (!contextPath.equals("")) {
				webapp = contextPath.substring(1) + "/";
            }

            if (webapp != null)
                pageContext.getOut().print(webapp);
        } catch (Throwable e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
