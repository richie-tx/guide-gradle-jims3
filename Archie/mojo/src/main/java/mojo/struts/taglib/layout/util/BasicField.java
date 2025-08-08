package mojo.struts.taglib.layout.util;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import mojo.struts.taglib.layout.field.AbstractLayoutFieldTag;
import mojo.struts.taglib.layout.skin.Skin;

/**
 * Default implementation of the FieldInterface interface.
 * @author jer80876
 */
public class BasicField implements FieldInterface {
	/**
	 * Default implementation uses 2 columns: one for the label, one for the value.
	 */
	public int getColumnNumber() {
		return 2;
	}
	
	/**
	 * Start to display the field.
	 */
	public void doStartField(StringBuffer in_buffer, AbstractLayoutFieldTag in_tag, String in_label, Object in_value) throws JspException {
		// Start the label cell.
		in_buffer.append("<th valign=\"top\" class=\"");
		in_buffer.append(in_tag.getStyleClass());
		
		// Set the layout id of the cell.
		String lc_layoutId = in_tag.getLayoutId();
		if (lc_layoutId!=null) {
			in_buffer.append("\" id=\"");
			in_buffer.append(lc_layoutId);
			in_buffer.append("L");
		}
		
		// Render a span in_tag as some browser don't support to set the style in the <td> in_tag.
		in_buffer.append("\"><span class=\"");
		in_buffer.append(in_tag.getStyleClass());
		
		// Render a tooltip.
		if (in_tag.getHint()!=null) {
			in_buffer.append("\" title=\"");
			in_buffer.append(LayoutUtils.getLabel(in_tag.getPageContext(), in_tag.getBundle(), in_tag.getHint(), null, false));
		}
		
		// Maybe use a specific style for the label
		if (in_tag.getStyle()!=null) {
			in_buffer.append("\" style=\"");
			in_buffer.append(in_tag.getStyle());
		}
		in_buffer.append("\">");
		
		// Render the label and end of the label cell.
		if (in_label!=null) {
			StringBuffer lc_label = new StringBuffer(in_label);
			for (int i=0;i<lc_label.length();i++) {
				if (lc_label.charAt(i)==' ') lc_label.replace(i,i+1,"&nbsp;");
			}
			in_buffer.append(lc_label.toString());
		} else {
			in_buffer.append("&nbsp;");
		}
		in_buffer.append("</span></th>");

		// Prepare to print the value: start the value cell.
		in_buffer.append("<td valign=\"top\" class=\"");
		in_buffer.append(in_tag.getStyleClass());
		
		// Set the layoutId of the cell.
		if (lc_layoutId!=null) {
			in_buffer.append("\" id=\"");
			in_buffer.append(lc_layoutId);
			in_buffer.append("F");	
		}
		
		// Maybe use extra style information.
		String lc_style = LayoutUtils.getSkin(in_tag.getPageContext().getSession()).getFormUtils().getFieldValueStyle(in_tag.getPageContext());
		if (lc_style!=null) {
			in_buffer.append("\" style=\"");
			in_buffer.append(lc_style);
		}
		in_buffer.append("\">");		
	}
	
	public void doEndField(StringBuffer in_buffer, AbstractLayoutFieldTag in_tag, Object in_value) {
		// Display an image if is Required is set.
		String lc_property = in_tag.getProperty();
		PageContext lc_pageContext = in_tag.getPageContext();
		Skin lc_skin = LayoutUtils.getSkin(lc_pageContext.getSession());
				
		if (in_tag.isRequired() && (in_value==null || in_value.toString().length()==0)) {
			in_buffer.append("<img name=\"" + lc_property + "required\" src=\"" + lc_skin.getImageDirectory(lc_pageContext.getRequest()) + "/ast.gif\">");
		} else {
			in_buffer.append("<img name=\"" + lc_property + "required\" src=\"" + lc_skin.getImageDirectory(lc_pageContext.getRequest()) + "/clearpixel.gif\">");
		}
		
		// end the field
		in_buffer.append("</td>");
		in_tag = null;
	}

	public void doStartErrors(StringBuffer in_buffer, AbstractLayoutFieldTag in_tag, List in_errors) {
        in_buffer.append("<table><tr><td class=\"");
		in_buffer.append(in_tag.getErrorStyleClass());
		in_buffer.append("\">");		
	}

	public void doEndErrors(StringBuffer in_buffer, AbstractLayoutFieldTag in_tag, List in_errors) {
		for (int i=0;i<in_errors.size();i++) {
			in_buffer.append(in_errors.get(i));
			if (i<in_errors.size()) in_buffer.append("<br />");
		}
		in_buffer.append("</td></tr></table>");
	}	
}