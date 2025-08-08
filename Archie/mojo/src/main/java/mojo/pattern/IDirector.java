/*
 * Created on Nov 18, 2005
 *
 */
package mojo.pattern;

/**
 * @author Jim Fisher
 * 
 * Instances of this interface will be used by controller classes to set properties
 * on model objects.
 * 
 * This interface utilizes the marker interface [MARKER] pattern for designating its use
 * in the already stated purpose.
 * 
 * The following are two examples for using a classes that implement the 
 * <code>Director</code> interface:
 * 
 * Boundary Layer Example:
 * JuvenileWarrantFormDirector director = JuvenileWarrantFormDirector.getInstance(); 
 * director.setRecallProperties(form);
 * 
 * Problem Domain Layer Example:
 * JuvenileWarrantEntityDirector director = JuvenileWarrantEntityDirector.getInstance(); 
 * director.setRecallProperties(requestEvent, warrant);
 * 
 * [MARKER] Reference the following URL for more information on the marker interface pattern:
 * http://en.wikipedia.org/wiki/Marker_interface_pattern
 */
public interface IDirector
{

}
