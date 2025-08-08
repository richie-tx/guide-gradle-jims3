package mojo.struts.taglib;


import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * This tag signifies to the server that this page should not be cached.
 * @author: Saleem Shafi
 */
public class NoCache extends TagSupport {
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public int doStartTag() throws JspException {
        ((HttpServletResponse)pageContext.getResponse()).setHeader("Expires", "Tues, 01 Jan 1980 00:00:00 GMT");
        return SKIP_BODY;
    }
}
