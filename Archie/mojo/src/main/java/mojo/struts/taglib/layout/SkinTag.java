package mojo.struts.taglib.layout;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import mojo.struts.taglib.layout.skin.Skin;
import mojo.struts.taglib.layout.util.LayoutUtils;

/**
 * Insert the type's description here.
 * Creation date: (17/06/2001 18:01:46)
 * @author: Eric Amundson
 */
public class SkinTag extends javax.servlet.jsp.tagext.TagSupport {
	private boolean include = false;

	public int doEndTag() throws JspException {
		Skin lc_skin = LayoutUtils.getSkin(pageContext.getSession());
		
		StringBuffer sb = new StringBuffer();
		sb.append("<link rel=\"stylesheet\" href=\"");
		sb.append(lc_skin.getCssDirectory(pageContext.getRequest()));
		sb.append("/");
		sb.append(lc_skin.getCssFileName());
		sb.append("\" type=\"text/css\">\n");
		sb.append("<script>var imgsrc=\"");
		sb.append(lc_skin.getImageDirectory(pageContext.getRequest()));
		if (!lc_skin.getImageDirectory(pageContext.getRequest()).endsWith("/")) sb.append("/");
		sb.append("\"; var scriptsrc=\"");
		sb.append(lc_skin.getConfigDirectory(pageContext.getRequest()));
		if (!lc_skin.getConfigDirectory(pageContext.getRequest()).endsWith("/")) sb.append("/");		
		sb.append("\"; var langue=\"");
		sb.append(LayoutUtils.getLocale(pageContext).getLanguage());
		sb.append("\";</script>");
		
		if (include) {
			sb.append("<script src=\"");
			sb.append(lc_skin.getConfigDirectory(pageContext.getRequest()));
			if (!lc_skin.getConfigDirectory(pageContext.getRequest()).endsWith("/")) sb.append("/");		
			sb.append("javascript.js\"></script>");
		}
		
		TagUtils.getInstance().write(pageContext, sb.toString());
		return EVAL_PAGE;
	}
	
	public void release() {
		include = false;	
	}
	
	public void setIncludeScript(boolean in_include) {
		include = in_include;	
	}
}
