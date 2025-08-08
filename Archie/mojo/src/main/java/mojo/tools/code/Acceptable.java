/*
 * Created on Dec 14, 2005
 *
 */
package mojo.tools.code;

/**
 * @author eamundson
 *
 */
public interface Acceptable
{
	/**
	 * Enable a visitor implementation to visit an element type.
	 * @param visitor
	 */
	void accept(IElementVisitor visitor);

}
