/*
 * Created on Nov 18, 2005
 *
 */
package mojo.pattern;

/**
 * @author Jim Fisher
 *
 * A marker interface for creating <code>ResponseEvent</code> objects.  A single Factory class 
 * instance may be used for creating multiple types of <code>ResponseEvent</code> 
 * objects for a given coarse grained concept or root composition abstraction.
 * 
 * For instance, while a subsystem may have a total of ten entity classes, it may only need 
 * five or six IResponseFactory classes.
 * 
 * The following is an example for using a class that implements the
 * <code>IResponseFactory</code> interface:
 * 
 * JuvenileWarrantResponseFactory factory = JuvenileWarrantResponseFactory.getInstance(); 
 * ResponseEvent responseEvent = factory.createJuvenileWarrantResponse(juvenileWarrant);
 *
 * [MARKER] Reference the following URL for more information on the marker interface pattern:
 * http://en.wikipedia.org/wiki/Marker_interface_pattern 
 */
public interface IResponseFactory
{
}
