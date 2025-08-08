package mojo.struts.taglib;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.*;

/**
 * Returns that path for the current webapp.  If the webapp name is ProjectName, 
 * then this tag will return ‘projectname /’.  This tag allows us to be loosely coupled 
 * from our web app deployment without coupling to the Servlet API.
 * @author: Saleem Shafi
 */
public class WebApp extends TagSupport {
    //private static final String PROPERTY = "WebAppName";
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

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
