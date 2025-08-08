/*
 * Created on Dec 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.tools.code;

import mojo.tools.code.KeyWord;

/**
 * This class represents a parameter in a method definition.j
 * 
 * @author Saleem Shafi
 */
public class Parameter extends CodeElement
{
	private String type;

	private String name;

	private boolean isFinal;

	/**
	 * Creates a parameter with the given type and name.
	 * 
	 * @param String
	 *            the parameter type
	 * @param String
	 *            the parameter name
	 */
	public Parameter(String aType, String aName)
	{
		setType(aType);
		setName(aName);
	}

	/**
	 * Returns whether or not this parameter is defined as final.
	 * 
	 * @return true iff this parameter is defined as final
	 */
	public boolean isFinal()
	{
		return isFinal;
	}

	/**
	 * Sets whether or not this method should be declared as final.
	 * 
	 * @param boolean
	 *            whether or not this method should be final
	 */
	public void setFinal(boolean isFinal)
	{
		this.isFinal = isFinal;
	}

	/**
	 * Returns the type of this parameter.
	 * 
	 * @return the name of the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the type of this parameter
	 * 
	 * @param String
	 *            the name of the type
	 */
	public void setType(String aType)
	{
		this.type = aType;
	}

	/**
	 * Returns the name of the parameter
	 * 
	 * @return the parameter name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name of the parameter.
	 * 
	 * @param String
	 *            the parameter name
	 */
	public void setName(String aName)
	{
		this.name = aName;
	}

	/**
	 * Returns the Java code representation of the method parameter.
	 * 
	 * @return String representation of the method parameter
	 */
	public String toString()
	{
		StringBuffer lText = new StringBuffer();
		if (isFinal)
		{
			lText.append("final ");
		}
		lText.append(getType()).append(KeyWord.SPACE).append(getName());
		return lText.toString();
	}

	public void accept(IElementVisitor visitor)
	{
		visitor.visit(this);
	}

}
