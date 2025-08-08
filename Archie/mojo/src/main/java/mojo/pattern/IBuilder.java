package mojo.pattern;

/**
 * @author Jim Fisher
 *
 * Separates the construction of a complex object from its representation so that 
 * the same construction process can create different representations. [BUILDER]
 * 
 * This pattern is useful when there are several complex strategies for constructing an
 * object.  Thus making it more effective to separate the creation process into separate
 * abstractions as opposed to a single Factory Method for all the different object types.
 * 
 * [BUILDER] Reference the following URL for more information on the Builder pattern:
 * http://en.wikipedia.org/wiki/Builder_pattern 
 */
public interface IBuilder
{
	public void build();
	public Object getResult();
}
