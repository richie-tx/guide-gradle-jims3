/*
 * Created on Aug 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.taglib;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;

/**
 * Used to determine if the current user is allowed access to a 
 * resource.  The tag checks the attributes of requiredFeatures and 
 * requiredUserTypes both optional.  If the they are blank, the user
 * has access.  If user must have the requiredFeature if it is passed and 
 * the requiredUserType if its passed.
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IsAllowedTag extends TagSupport
{
	private String delimiter = ",";
	private String requiredFeatures;
	private String requiredUserTypes;
	private boolean value = true;

	/**
	 * Sets the delimiter for the required 
	 * attributes
	 * @param delim
	 */
	public void setDelimiter(String delim)
	{
		this.delimiter = delim;
	}

	/**
	 * Gets the delimiter for the required 
	 * attributes
	 * @return delimiter
	 */
	public String getDelimiter()
	{
		return this.delimiter;
	}

	/**
	 * Sets the required features that should be
	 * seperated by the delimiter
	 * @param features
	 */
	public void setRequiredFeatures(String features)
	{
		this.requiredFeatures = features;
	}

	/**
	 * Returns the required features
	 * @return
	 */
	public String getRequiredFeatures()
	{
		return this.requiredFeatures;
	}

	/**
	 * Sets the required user types that are seperated 
	 * by the delimiter
	 * @param userTypes
	 */
	public void setRequiredUserTypes(String userTypes)
	{
		this.requiredUserTypes = userTypes;
	}

	/**
	 * Returns the required user types
	 * @return user types
	 */
	public String getRequiredUserTypes()
	{
		return this.requiredUserTypes;
	}

	public void setValue(boolean val)
	{
		this.value = val;
	}

	public boolean getValue()
	{
		return this.value;
	}

	/**
	 * Determines if the current user has the appropriate access
	 * to a runtime determined resource in the UI
	 */
	public int doStartTag() throws JspException
	{
		boolean grantedFeature = false;
		boolean grantedType = false;
		try
		{

			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

			if (securityManager != null)
			{

				String[] features = this.getAsArray(this.requiredFeatures, this.delimiter);

				grantedFeature = securityManager.isAllowed(features);

				String[] userTypes = this.getAsArray(this.requiredUserTypes, this.delimiter);

				grantedType = securityManager.isUserOfType(userTypes);
			}
			if ((grantedFeature && grantedType) == this.value)
			{
				return EVAL_BODY_INCLUDE;
			}
			else
			{
				return SKIP_BODY;
			}

		}
		catch (Throwable e)
		{
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}
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
	* Returns a String[] based off the deliminted string.
	* @param delimitedString
	* @param delimiter
	* @return String[] of the delimited string
	*/
	private String[] getAsArray(String delimitedString, String delimiter)
	{
		if (delimitedString == null)
		{
			return new String[0];
		}
		StringTokenizer st = new StringTokenizer(delimitedString, delimiter);
		Vector items = new Vector();
		while (st.hasMoreTokens())
		{
			String token = st.nextToken();
			items.add(token);
		}
		String[] retArray = new String[items.size()];
		items.copyInto(retArray);
		return retArray;
	}

}