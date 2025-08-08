/*
 * Created on Dec 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.tools.code;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Initializer extends CodeElement
{
	public Initializer()
	{
		super();
		
	}
	private String body = null;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public String getBody()
	{
		return body;
	}

	/**
	 * @param string
	 */
	public void setBody(String string)
	{
		body = string;
	}

	/* (non-Javadoc)
	 * @see mojo.tools.code.CodeElement#accept(mojo.tools.code.IElementVisitor)
	 */
	public void accept(IElementVisitor visitor)
	{
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}
