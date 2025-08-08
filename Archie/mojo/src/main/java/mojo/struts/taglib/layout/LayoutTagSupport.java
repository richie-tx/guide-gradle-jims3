package mojo.struts.taglib.layout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import mojo.struts.taglib.layout.util.ParentFinder;

/**
 * Abstract LayoutTag implementation.
 */
public abstract class LayoutTagSupport extends TagSupport implements LayoutTag {
  public final PageContext getPageContext() {
    return pageContext;
  }
  
  public final int doStartTag() throws JspException {
    ParentFinder.registerTag(pageContext, this);
    initDynamicValues();
    return doStartLayoutTag();
  }   
  
  public final int doEndTag() throws JspException {
    try {
      return doEndLayoutTag();
    } finally {
      reset();
      ParentFinder.deregisterTag(pageContext);
    }
  }
  
  public int doStartLayoutTag() throws JspException {
    return super.doStartTag();
  }
  public int doEndLayoutTag() throws JspException {
    return super.doEndTag();
  }
  
  protected void initDynamicValues() {
    // Do nothing.
  }
  
  protected void reset() {
    // DO NOTHING, to be override.
  }
}
