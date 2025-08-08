/*
 * Created on Feb 03, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import messaging.codetable.reply.CodeResponseEvent;
import ui.common.CodeHelper;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SortingTag extends TagSupport
{
	private String url;
	
	/**
	 * Load the Character codes and process the submit
	 */
	public int doStartTag() throws JspException
	{
		try
		{
			Collection characterDescriptions = this.loadCharacterCodes();
			this.renderOption(characterDescriptions);
			return SKIP_BODY;
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
	}
	
	/**
	 * @param characterDescriptions
	 */
	private void renderOption(Collection characterDescriptions) throws IOException
	{
		JspWriter writer = pageContext.getOut();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select name=\"character\" onchange=\"submitSortForm(");
		buffer.append("this.form");
		buffer.append(",");
		buffer.append("\'");
		buffer.append(getServerUrl(this.url));
		buffer.append("\'");
		buffer.append(",this");
		buffer.append(")\">");
		
		Iterator iter =  characterDescriptions.iterator();
		
		while(iter.hasNext()){
			CodeResponseEvent responseEvent = (CodeResponseEvent) iter.next();
			buffer.append("<OPTION ");
			buffer.append("value=\"");
			buffer.append(responseEvent.getCode());
			buffer.append("\">");
			buffer.append(responseEvent.getDescription());
			buffer.append("</OPTION> ");
		}
		buffer.append("</select> ");
		writer.print(buffer.toString());
	}

	/**
	 * @return
	 */
	private Collection loadCharacterCodes()
	{
		Collection col = new ArrayList();
		CodeResponseEvent resp = new CodeResponseEvent();
		resp.setCode("0");
		resp.setDescription("-");
		col.add(resp);
		col.addAll(sort(CodeHelper.getCharacterDescription()));
		return col;		
	}

	/**
	 * @param collection
	 * @return
	 */
	private Collection sort(Collection collection)
	{
		SortedMap map = new TreeMap();
		Iterator iter = collection.iterator();
		while(iter.hasNext()){
		    CodeResponseEvent response = (CodeResponseEvent) iter.next();
		    map.put(response.getCode(),response);	
		}
		return map.values();
	}

	/**
	 * Executes after the body of the tag has been processed.  This
	 * implementation will write out the contents of the body and determine
	 * whether to perform another iteration by seearching for another instance
	 * of the event.  If it finds another instance, this method will return
	 * EVAL_BODY_TAG, which will re-evaluate the body with respect to the next
	 * instance of the event.  Otherwise, it will return SKIP_BODY and end this
	 * tag.
	 * @return int
	 * @exception javax.servlet.jsp.JspTagException
	 */
	public int doAfterBody() throws javax.servlet.jsp.JspTagException
	{
		return SKIP_BODY;
	}

	/**
	 * Executes after the body of the tag has finished being processed.  It is
	 * only executed once for every instance of the tag in the MSP file.  This
	 * implemenation removes the page-level attribute that held the event
	 * instance.
	 * @return int
	 * @exception javax.servlet.jsp.JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}

	/**
	 * @return
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string)
	{
		url = string;
	}
	
	/**
	 * @param url
	 * @return String
	 */
	private String getServerUrl(String url){
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(pageContext.getRequest().getScheme());
		urlBuffer.append("://");
		urlBuffer.append(pageContext.getRequest().getServerName());
		urlBuffer.append(":");
		urlBuffer.append(pageContext.getRequest().getServerPort());
		urlBuffer.append("/");
		HttpServletRequest lRequest = (HttpServletRequest)pageContext.getRequest();
		String contextPath =lRequest.getContextPath();
		String webapp = "";
		if (!contextPath.equals("")) {
			webapp = contextPath.substring(1) + "/";
		}
		urlBuffer.append(webapp);
		urlBuffer.append(url);
		return urlBuffer.toString(); 
	}
}